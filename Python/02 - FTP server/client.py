import glob
import os
import readline
from ftplib import FTP, all_errors

ftp = FTP('')

pwd = ""
address = "disconnected"
connected = False


def complete(text, state):
    """
    generate path from input string and auto complete
    :param text: text to complete
    :param state: input state
    :return: completed text
    """
    return (glob.glob(text + '*') + [None])[state]


def get_prompt(s):
    """
    generate formatted message for the end user
    :param s: message
    :return: formatted message
    """
    return "{0}@anonymous:{1}$ {2}".format(address, pwd, s)


def connect(arguments):
    """
    connect with the FTP server
    :param arguments: [ip, port]
    :return: none
    """
    global address, connected

    if connected:
        disconnect()

    ip = arguments[0]
    port = int(arguments[1])

    try:
        # connect with the FTP server
        ftp.connect(ip, port)
        ftp.login()
        address = ip
        connected = True
        print(get_prompt("connected"))

        go_to_dir("/")
    except all_errors:
        print(get_prompt("connection failed"))


def disconnect():
    """
    disconnect with the FTP server
    :return: none
    """
    global pwd, address, connected

    if not connected:
        return

    # disconnect with the FTP server
    ftp.quit()

    pwd = ""
    address = "disconnected"
    connected = False
    print(get_prompt("disconnected"))


def go_to_dir(arguments):
    """
    change current working directory in the FTP server
    :param arguments: [directory]
    :return: none
    """
    global pwd

    if not connected:
        print(get_prompt("please connect first"))
        return

    directory = arguments[0]

    try:
        # change current working directory in the FTP server
        ftp.cwd(directory)
        pwd = ftp.pwd()
    except all_errors:
        print(get_prompt("invalid directory"))


def list_dir():
    """
    list directories and files inside current working directory
    :return: none
    """
    if not connected:
        print(get_prompt("please connect first"))
        return

    # list directories and files inside current working directory
    ftp.retrlines('LIST')


def view_dir():
    """
    view current working directory
    :return: none
    """
    if not connected:
        print(get_prompt("please connect first"))
        return

    print(pwd)


def clear_console(command):
    """
    clear console window
    :param command: clear or cls
    :return: none
    """

    # get system command and execute
    def clear(): os.system(command)
    clear()


def upload_file(arguments):
    """
    upload a file into current working directory of FTP server from source location
    :param arguments: [file path]
    :return: none
    """
    if not connected:
        print(get_prompt("please connect first"))
        return

    source = arguments[0]
    filename = os.path.basename(source)

    # upload a file into the FTP server
    try:
        with open(source, 'rb') as f:
            ftp.storbinary("STOR " + filename, f)
            print("uploaded file {0}".format(source))
    except IOError:
        print("error when uploading file {0}".format(source))
    except all_errors:
        print("error when uploading file {0}".format(source))


def download_file(arguments):
    """
    download a file from current working directory of FTP server to destination location
    :param arguments: [file name, destination path]
    :return: none
    """
    if not connected:
        print(get_prompt("please connect first"))
        return

    filename = arguments[0]
    destination = os.path.join(arguments[1], filename)

    try:
        with open(destination, 'wb') as f:
            ftp.retrbinary("RETR " + filename, f.write, 1024)
            print("downloaded file {0}".format(destination))
    except IOError:
        print("error when downloading file {0}".format(filename))
    except all_errors:
        print("error when downloading file {0}".format(filename))


def split_arguments(s):
    """
    split command line input and extract arguments
    :param s: command line input
    :return: (command, arguments)
    """

    # split with "'"
    parts_quote = s.split("'")
    parts_final = []

    for i in range(len(parts_quote)):
        if i == "":
            # skip empty parts
            continue
        elif i % 2 == 1:
            # parts surrounded by quotation marks (eg: "word documents.doc")
            # do not need further splittings
            parts_final.append(parts_quote[i])
        else:
            # other parts do need further splittings
            # split and add non empty parts
            parts_final = parts_final + [x for x in parts_quote[i].split(" ") if not x == ""]

    command = parts_final[0]

    arguments = []
    if len(parts_final) > 1:
        arguments = parts_final[1:]

    return command, arguments


def parse_command(s):
    """
    map command with function to execute
    :param s: user input
    :return: none
    """
    if s == "":
        return

    (command, arguments) = split_arguments(s)

    if command == "cls" or command == "clear":
        # example command (mac or linux): clear
        # example command (windows): cls
        clear_console(command)
    elif command == "pwd":
        # example command: pwd
        view_dir()
    elif command == "cd":
        # example command: cd /home/user/Documents
        go_to_dir(arguments)
    elif command == "ls":
        # example command: ls
        list_dir()
    elif command == "con":
        # example command: con 127.0.0.1 1026
        connect(arguments)
    elif command == "dis":
        # example command: dis
        disconnect()
    elif command == "up":
        # example command: up /home/user/Documents/file.txt
        # will upload into the current working directory of FTP server
        upload_file(arguments)
    elif command == "down":
        # example command: down file.txt /home/user/Documents
        # download file inside current working directory of FTP server
        download_file(arguments)
    else:
        print(get_prompt("invalid command"))


def main():
    # enable auto completion for paths (optional)
    readline.set_completer_delims(' \t\n;')
    readline.parse_and_bind("tab: complete")
    readline.set_completer(complete)

    # start application and get user input
    s = input(get_prompt(""))
    while s != 'exit':
        parse_command(s)
        s = input(get_prompt(""))

    # clean up
    disconnect()


if __name__ == "__main__":
    main()
