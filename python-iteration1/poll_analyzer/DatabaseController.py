from flask_sqlalchemy import SQLAlchemy


class DatabaseController:
    def __init__(self, app):
        self.__db = SQLAlchemy(app)

    def get_db(self):
        return self.__db

    def create_tables(self):
        # create tables in the database
        self.__db.create_all()