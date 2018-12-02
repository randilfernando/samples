import os
from ftplib import FTP, all_errors


ftp = FTP('')

pwd = ""
address = ""
connected = False


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


def parse_command(s):
    if s == "":
        return

    parts = s.split(" ")
    command = parts[0]

    arguments = []
    if len(parts) > 1:
        arguments = parts[1:]

    if command == "con":
        connect(arguments)
    elif command == "dis":
        disconnect()
    elif command == "cd":
        go_to_dir(arguments)
    elif command == "ls":
        list_dir()
    elif command == "up":
        upload_file(arguments)
    elif command == "down":
        download_file(arguments)
    else:
        print(get_prompt() + "invalid command")


def main():
    s = input(get_prompt())
    while s != 'exit':
        parse_command(s)
        s = input(get_prompt())

    disconnect()


if __name__ == "__main__":
    main()
