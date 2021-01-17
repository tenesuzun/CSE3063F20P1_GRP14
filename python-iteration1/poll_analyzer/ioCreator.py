import pandas as pd
from datetime import datetime
from os import path
import os
import re
from unidecode import unidecode


class ioCreator:
    def __init__(self, db_controller):
        self.db = db_controller.get_db()

    def create_state(self):
        from poll_analyzer.models import Student, Poll, Answer
        # state file exists
        with open('state.xls', 'w') as file_handle:
            students = Student.query.all()
            polls = []
            # find all the quiz polls
            for poll in Poll.query.all():
                if len(poll.questions) > 1:
                    polls.append(poll)
            header = 'No, Ogrenci No, Adi, Soyadi, Aciklama, '
            for poll in polls:
                header += f'{poll.name}, , ,'
            header += '\n'
            file_handle.write(header)
            for student in students:
                no = student.student_id
                full_name = student.full_name.strip().split()
                name = ' '.join(full_name[:-1])
                surname = full_name[-1]
                std_number = student.student_number
                status = student.status
                line = f'{no}, {std_number}, {name}, {surname}, {status}, '
                for poll in polls:
                    total_point = 0
                    attendance_question = 0
                    for question in poll.questions:
                        if question.content == 'Are you attending this lecture?':
                            attendance_question += 1
                        std_answer = Answer.query.filter(
                            Answer.question_id == question.question_id, Answer.student_id == student.student_id).first()
                        if std_answer is not None and std_answer.correct:
                            total_point += 1
                    parsed_date = datetime.strftime(poll.date, '%d/%m/%Y')
                    line += f'{parsed_date}, {total_point}, {len(poll.questions) - attendance_question}, '
                file_handle.write(line + '\n')

    def read_answer_key(self, f_path):
        print(f'{"Reading Answer Key!":*^40}')

        answer_files = []
        poll_names = []
        # if we are reading a file
        if path.isfile(f_path):
            answer_file = pd.read_csv(
                f_path, delimiter=';', quotechar='"', skiprows=1, header=None, index_col=False)
            with open(f_path, 'r') as file_handle:
                poll_name = file_handle.readlines()[0].replace('"', '').strip()
            poll_name = poll_name[:-1]
            from poll_analyzer.models import Question, Answer, Poll
            poll = Poll.query.filter(Poll.name == poll_name).first()
            if poll is None:
                print(f'Poll {poll_name} not found!')
                return
            poll_names.append(poll_name)
            answer_files.append(answer_file)

        # if we are reading files from a folder
        elif path.isdir(f_path):
            for f in os.listdir(f_path):
                answer_file = pd.read_csv(
                    path.join(f_path, f), delimiter=';', quotechar='"', skiprows=1, header=None, index_col=False)
                with open(path.join(f_path, f), 'r') as file_handle:
                    poll_name = file_handle.readlines(
                    )[0].replace('"', '').strip()
                poll_name = poll_name[:-1]
                from poll_analyzer.models import Question, Answer, Poll
                poll = Poll.query.filter(Poll.name == poll_name).first()
                if poll is None:
                    print(f'Poll {poll_name} not found!')
                    return
                poll_names.append(poll_name)
                answer_files.append(answer_file)

        for answer_file in answer_files:
            for _, row in answer_file.iterrows():
                q_text, ans_text = row.tolist()
                questions = Question.query.filter(
                    Question.content == q_text, Question.poll_id == poll.poll_id).all()
                for question in questions:
                    answers = Answer.query.filter(
                        Answer.question_id == question.question_id).all()
                    for answer in answers:
                        if answer.given_answer == ans_text:
                            answer.correct = True

                    question.right_answer = ans_text
            self.db.session.commit()
        print(f'{"Answer Key Processed!":*^40}')

    def create_poll_report(self, poll_name):
        from poll_analyzer import Question, Answer, Student, Poll
        poll = Poll.query.filter(Poll.name == poll_name).first()
        if poll is None:
            print(f'Poll {poll_name} not found!')
            return
        students = Student.query.all()
        print(f'{poll.name} icin rapor hazirlaniyor...')
        header = 'No, Ogrenci No, Adi, Soyadi, Aciklama, '
        questions = []
        for question in poll.questions:
            if question.content != 'Are you attending this lecture?':
                questions.append(question)
        # it means it is an attendance poll
        if len(questions) == 0:
            return
        # create the header
        for i in range(1, len(questions)+1):
            header += f'Q{i}, '
        header += 'success percentage, # of correct answers, # of question\n'
        with open(f'{poll.name}', 'w') as file_handle:
            file_handle.write(header)
            for student in students:
                no = student.student_id
                full_name = student.full_name.strip().split()
                name = ' '.join(full_name[:-1])
                surname = full_name[-1]
                std_number = student.student_number
                status = student.status
                total_point = 0
                line = f'{no}, {std_number}, {name}, {surname}, {status}, '
                for question in questions:
                    q_id = question.question_id
                    std_answer = Answer.query.filter(
                        Answer.question_id == q_id, Answer.student_id == no).first()
                    if std_answer is None:
                        answer = 0
                    else:
                        answer = 1 if std_answer.correct else 0
                    total_point += answer
                    line += f' {answer}, '
                line += f'{total_point/len(questions)*100}, {total_point}, {len(questions)}'
                line = line.strip() + '\n'
                file_handle.write(line)

    def create_attendance_report(self):
        from poll_analyzer import Question, Answer, Student
        att_text = '%Are you attending this lecture%'
        questions = Question.query.filter(
            Question.content.like(att_text)).all()
        students = Student.query.all()
        att_number = len(questions)
        with open('attendance_report.xls', 'w') as file_handle:
            file_handle.write(
                'No, Ogrenci No, Adi, Soyadi, Aciklama, Katilim Yuzdesi, Katilim Sayisi, Toplam Yoklama\n')
            for student in students:
                no = student.student_id
                full_name = student.full_name.strip().split()
                name = ' '.join(full_name[:-1])
                surname = full_name[-1]
                std_number = student.student_number
                status = student.status
                answers = student.answers
                attendance = 0
                for answer in answers:
                    if answer.given_answer == 'Yes' or answer.given_answer == 'No':
                        attendance += 1

                line = f'{no}, {std_number}, {name}, {surname}, {status}, {attendance/att_number*100}, {attendance}, {att_number}\n'
                file_handle.write(line)

    def process_name(self, name):
        name = re.sub('\d', '', name).strip()
        return unidecode(name)

    def find_student(self, name):
        from poll_analyzer.models import Student
        tokens = name.split()
        # first query by first name
        f_query = Student.query.filter(
            Student.full_name.like(f'%{tokens[0]}%'))
        l_query = f_query.filter(Student.full_name.like(f'%{tokens[-1]}%'))
        if l_query.first() is None:
            # print(f'Student {name} doesn\'t exit')
            return None
        return l_query.first()

    def read_student_list(self, f_path):
        print(f'{"Reading Student List":*^40}')
        students = pd.read_excel(f_path)
        students1 = students[12:203][['Unnamed: 1', 'Unnamed: 2',
                                      'Unnamed: 4', 'Unnamed: 7', 'Unnamed: 10']]
        students2 = students[207:208][['Unnamed: 1', 'Unnamed: 2',
                                       'Unnamed: 4', 'Unnamed: 7', 'Unnamed: 10']]
        students3 = students[212:214][['Unnamed: 1', 'Unnamed: 2',
                                       'Unnamed: 4', 'Unnamed: 7', 'Unnamed: 10']]

        students = students1.append(students2).append(students3)
        students['Unnamed: 10'] = students['Unnamed: 10'].fillna('CSE3063')
        from poll_analyzer.models import Student
        for _, row in students.iterrows():
            info = [*row[1:]]
            std_number, name, surname, status = [*info]
            std_number = int(std_number)
            full_name = f'{name} {surname}'
            full_name = self.process_name(full_name)
            # student doesn't exist, add to database
            if self.find_student(full_name) is None:
                student = Student(full_name=full_name,
                                  student_number=std_number, status=status)
                self.db.session.add(student)
            else:
                # print(f'{full_name}: already exists')
                pass

        print(f'{"Student List Processed!":*^40}')
        self.db.session.commit()

    def read_poll(self, f_path):
        print(f'{"Starting to Read Poll!":*^40}')
        entries_list = []
        poll_names = []
        # if we are reading a file
        if path.isfile(f_path):
            poll_names.append(f_path.split('/')[-1])
            entries = pd.read_csv(f_path, quotechar='"', index_col=False,
                                  header=None, skiprows=1, encoding='utf-8')
            entries = entries.drop(labels=[len(entries.columns)-1], axis=1)
            entries_list.append(entries)
        # if we are reading files from a folder
        elif path.isdir(f_path):
            for f in os.listdir(f_path):
                poll_names.append(f.split('/')[-1])
                entries = pd.read_csv(os.path.join(f_path, f), quotechar='"', index_col=False,
                                      header=None, skiprows=1, encoding='utf-8')
                entries = entries.drop(labels=[len(entries.columns)-1], axis=1)
                entries_list.append(entries)

        for entries, poll_name in zip(entries_list, poll_names):
            for _, row in entries.iterrows():
                info = row[1:4]
                qa_list = row[4:].tolist()
                f_name, _, date = [*info]
                f_name = self.process_name(f_name).upper()
                # import database models
                from poll_analyzer.models import Student
                from poll_analyzer.models import Poll
                from poll_analyzer.models import Question
                from poll_analyzer.models import Answer
                for ind in range(0, len(qa_list), 2):
                    # question and answer text
                    que = qa_list[ind]
                    if isinstance(que, float):
                        break
                    ans = qa_list[ind+1]
                    # if poll doesn't exist in the database, add it
                    if Poll.query.filter_by(name=poll_name).first() is None:
                        from dateutil import parser
                        from datetime import datetime
                        year = parser.parse(date).year
                        month = parser.parse(date).month
                        day = parser.parse(date).day
                        parsed_date = f'{day}/{month}/{year}'
                        parsed_date = datetime.strptime(
                            parsed_date, '%d/%m/%Y')
                        poll = Poll(date=parsed_date, name=poll_name)
                        self.db.session.add(poll)
                        self.db.session.commit()
                    else:
                        poll = Poll.query.filter_by(name=poll_name).first()

                    # get the student from database
                    # if student is not found, notify
                    student = self.find_student(f_name)
                    if student is None:
                        print(f'{f_name}: {len(f_name)} is not in the database!')
                        student = Student(full_name=f_name,
                                          student_number=-1, status='Not known!')
                        self.db.session.add(student)
                        self.db.session.commit()
                        print(f'{f_name} added to the database!')
                    else:
                        pass

                    # if question doesn't exist in the database, add it

                    similar_question = Question.query.filter(
                        Question.poll_id == poll.poll_id, Question.content == que).first()
                    if similar_question is None:
                        question = Question(
                            poll_id=poll.poll_id, content=que, right_answer='Not Known!')
                        self.db.session.add(question)
                        self.db.session.commit()
                    else:
                        question = similar_question

                    if Answer.query.filter(Answer.student_id == student.student_id, Answer.question_id == question.question_id).first() is None:
                        answer = Answer(student_id=student.student_id, question_id=question.question_id,
                                        correct=False, given_answer=ans)
                        self.db.session.add(answer)
                    else:
                        pass
                self.db.session.commit()
        print(f'{"Poll is Processed!":*^40}')