from flask import render_template
from poll_analyzer import app
from poll_analyzer.models import Student, Poll, Answer, Question
from poll_analyzer import io_creator


@app.route('/')
def home():
    students = Student.query.all()
    return render_template('home.html', students=students)


@app.route('/poll/<poll_id>')
def poll(poll_id):
    polls = Poll.query.all()
    return render_template('poll.html', polls=polls)


@app.route('/polls')
def polls():
    polls = Poll.query.all()
    return render_template('poll.html', polls=polls)


@app.route('/answers')
def answers():
    answers = Answer.query.all()
    return render_template('answers.html', answers=answers)


@app.route('/questions')
def questions():
    questions = Question.query.all()
    return render_template('questions.html', questions=questions)