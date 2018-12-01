import os
from ftplib import FTP, all_errors

ftp = FTP('')
ftp.connect('127.0.0.1', 1026)
ftp.login()


def go_to_dir(arguments):
    try:
        ftp.cwd(arguments[0])
    except all_errors:
        print("$anonymous ({0}): {1}".format(ftp.pwd(), "directory not found"))


def list_dir():
    ftp.retrlines('LIST')


def upload_file(arguments):
    source = arguments[0]
    filename = os.path.basename(source)
    ftp.storbinary("STOR " + filename, open(source, 'rb'))


def download_file(arguments):
    filename = arguments[0]
    destination = arguments[1]
    destination_file = os.path.join(destination, filename)
    local_file = open(destination_file, 'wb')
    ftp.retrbinary("RETR " + filename, local_file.write, 1024)
    local_file.close()


def parse_command(s):
    parts = s.split(" ")
    command = parts[0]

    arguments = []
    if len(parts) > 1:
        arguments = parts[1:]

    if command == "":
        return

    if command == "cd":
        go_to_dir(arguments)
    elif command == "ls":
        list_dir()
    elif command == "up":
        upload_file(arguments)
    elif command == "down":
        download_file(arguments)
    else:
        print("$anonymous ({0}): {1}".format(ftp.pwd(), "unknown command"))


def main():
    go_to_dir("/")

    s = input("$anonymous (/): ")
    while s != 'exit':
        parse_command(s)
        s = input("$anonymous ({0}): ".format(ftp.pwd()))

    ftp.quit()


if __name__ == "__main__":
    main()
