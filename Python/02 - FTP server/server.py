import glob
import readline
import socket
from pyftpdlib.authorizers import DummyAuthorizer
from pyftpdlib.handlers import FTPHandler
from pyftpdlib.servers import FTPServer


def complete(text, state):
    return (glob.glob(text + '*') + [None])[state]


def get_ip():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.connect(("8.8.8.8", 80))
    ip = s.getsockname()[0]
    s.close()
    return ip


def main():
    readline.set_completer_delims(' \t\n;')
    readline.parse_and_bind("tab: complete")
    readline.set_completer(complete)

    directory = input("Enter directory: ")
    authorizer = DummyAuthorizer()
    authorizer.add_anonymous(directory)

    handler = FTPHandler
    handler.authorizer = authorizer

    server = FTPServer((get_ip(), 1026), handler)
    server.serve_forever()


if __name__ == "__main__":
    main()
