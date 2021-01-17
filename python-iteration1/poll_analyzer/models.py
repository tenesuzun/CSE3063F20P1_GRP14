from poll_analyzer import db_controller

db = db_controller.get_db()


class Poll(db.Model):
    poll_id = db.Column(db.Integer, primary_key=True)
    date = db.Column(db.DateTime)
    name = db.Column(db.String(100))
    questions = db.relationship('Question', backref='of_poll', lazy=True)


class Student(db.Model):
    student_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    full_name = db.Column(db.String(100))
    student_number = db.Column(db.Integer)
    status = db.Column(db.String(20))
    answers = db.relationship('Answer', backref='answered', lazy=True)

    def __repr__(self):
        return f'{self.full_name}: {self.student_number}'


class Question(db.Model):
    question_id = db.Column(db.Integer, primary_key=True, autoincrement=True)
    poll_id = db.Column(db.Integer, db.ForeignKey(
        'poll.poll_id'), nullable=False)
    content = db.Column(db.String(100))
    right_answer = db.Column(db.String(100))


class Answer(db.Model):
    answer_id = db.Column(db.Integer, primary_key=True)
    student_id = db.Column(db.Integer, db.ForeignKey(
        'student.student_id'), nullable=False)
    question_id = db.Column(db.Integer, db.ForeignKey(
        'question.question_id'), nullable=False)
    correct = db.Column(db.Boolean)
    given_answer = db.Column(db.String(99), nullable=False)