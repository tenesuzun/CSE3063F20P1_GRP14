from poll_analyzer import app, db_controller, io_creator
import os

config = {}
config['std_list'] = f'/home/{os.getlogin()}/Projects/iter1/poll_analyzer/xls_files/std_list.XLS'
config['mon_poll'] = f'/home/{os.getlogin()}/Projects/iter1/poll_analyzer/xls_files/mon_poll.xls'
config['mon_poll_2'] = f'/home/{os.getlogin()}/Projects/iter1/poll_analyzer/xls_files/mon_poll_2.xls'
config['tue_poll'] = f'/home/{os.getlogin()}/Projects/iter1/poll_analyzer/xls_files/tue_poll.xls'
config['mon_poll_key'] = f'/home/{os.getlogin()}/Projects/iter1/poll_analyzer/xls_files/mon_poll_key.xls'
config['mon_poll_2_key'] = f'/home/{os.getlogin()}/Projects/iter1/poll_analyzer/xls_files/mon_poll_2_key.xls'
config['mon_poll_folder'] = f'/home/{os.getlogin()}/Projects/iter1/poll_analyzer/xls_files/mon_poll_folder/'
config['answer_keys'] = f'/home/{os.getlogin()}/Projects/iter1/poll_analyzer/xls_files/answer_keys/'

if __name__ == '__main__':
    db_controller.create_tables()

    io_creator.read_student_list(config['std_list'])

    io_creator.read_poll(config['tue_poll'])

    io_creator.read_poll(config['mon_poll'])
    io_creator.read_poll(config['mon_poll_2'])
    io_creator.read_answer_key(config['mon_poll_key'])
    io_creator.read_answer_key(config['mon_poll_2_key'])

    # io_creator.read_poll(config['mon_poll_folder'])
    # io_creator.read_answer_key(config['answer_keys'])

    io_creator.create_poll_report('mon_poll.xls')
    io_creator.create_poll_report('mon_poll_2.xls')
    io_creator.create_attendance_report()

    io_creator.create_state()
    app.run(debug=True, use_reloader=False)