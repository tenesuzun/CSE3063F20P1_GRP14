from flask import Flask
from .DatabaseController import DatabaseController
from .ioCreator import ioCreator

app = Flask(__name__, instance_relative_config=True)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///site.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
app.config['ENV'] = 'development'
app.config['DEBUG'] = True
db_controller = DatabaseController(app)
io_creator = ioCreator(db_controller)

from poll_analyzer.views import *