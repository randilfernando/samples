import glob
import os
from ftplib import FTP, all_errors

import readline

ftp = FTP('')

pwd = ""
address = ""
connected = False


def complete(text, state):
    return (glob.glob(text + '*') + [None])[state]


def get_prompt():
    return "anonymous({0}):{1}$ ".format(address, pwd)


def connect(arguments):
    global address, connected

    if connected:
        disconnect()

    ip = arguments[0]
    port = int(arguments[1])

    try:
        ftp.connect(ip, port)
        ftp.login()
        address = ip + ":" + str(port)
        connected = True
        print(get_prompt() + "connected")

        go_to_dir("/")
    except all_errors:
        print(get_prompt() + "connection failed")


def disconnect():
    global pwd, address, connected

    if not connected:
        return

    ftp.quit()

    address = ""
    pwd = ""
    connected = False
    print(get_prompt() + "disconnected")


def go_to_dir(arguments):
    global pwd

    if not connected:
        print(get_prompt() + "please connect first")
        return

    try:
        ftp.cwd(arguments[0])
        pwd = ftp.pwd()
    except all_errors:
        print(get_prompt() + "invalid directory")


def list_dir():
    if not connected:
        print(get_prompt() + "please connect first")
        return

    ftp.retrlines('LIST')


def view_dir():
    if not connected:
        print(get_prompt() + "please connect first")
        return

    print(pwd)


def clear_console(command):
    def clear(): os.system(command)
    clear()


def upload_file(arguments):
    if not connected:
        print(get_prompt() + "please connect first")
        return

    source = arguments[0]
    filename = os.path.basename(source)
    ftp.storbinary("STOR " + filename, open(source, 'rb'))

    print(get_prompt() + "uploaded file {0}".format(source))


def download_file(arguments):
    if not connected:
        print(get_prompt() + "please connect first")
        return

    filename = arguments[0]
    destination = os.path.join(arguments[1], filename)
    local_file = open(destination, 'wb')
    ftp.retrbinary("RETR " + filename, local_file.write, 1024)
    local_file.close()

    print(get_prompt() + "downloaded file {0}".format(destination))


def split_arguments(s):
    parts_quote = s.split("'")
    parts_final = []

    for i in range(len(parts_quote)):
        if i == "":
            continue
        elif i % 2 == 1:
            parts_final.append(parts_quote[i])
        else:
            parts_final = parts_final + [x for x in parts_quote[i].split(" ") if not x == ""]

    command = parts_final[0]

    arguments = []
    if len(parts_final) > 1:
        arguments = parts_final[1:]

    return command, arguments


def parse_command(s):
    if s == "":
        return

    (command, arguments) = split_arguments(s)

    if command == "cls" or command == "clear":
        clear_console(command)
    elif command == "pwd":
        view_dir()
    elif command == "cd":
        go_to_dir(arguments)
    elif command == "ls":
        list_dir()
    elif command == "con":
        connect(arguments)
    elif command == "dis":
        disconnect()
    elif command == "up":
        upload_file(arguments)
    elif command == "down":
        download_file(arguments)
    else:
        print(get_prompt() + "invalid command")


def main():
    readline.set_completer_delims(' \t\n;')
    readline.parse_and_bind("tab: complete")
    readline.set_completer(complete)

    s = input(get_prompt())
    while s != 'exit':
        parse_command(s)
        s = input(get_prompt())

    disconnect()


if __name__ == "__main__":
    main()
