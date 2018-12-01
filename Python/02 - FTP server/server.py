import socket
from pyftpdlib.authorizers import DummyAuthorizer
from pyftpdlib.handlers import FTPHandler
from pyftpdlib.servers import FTPServer


def get_ip():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.connect(("8.8.8.8", 80))
    ip = s.getsockname()[0]
    s.close()
    return ip


def main():
    directory = input("Enter directory: ")
    authorizer = DummyAuthorizer()
    authorizer.add_anonymous(directory)

    handler = FTPHandler
    handler.authorizer = authorizer

    server = FTPServer((get_ip(), 1026), handler)
    server.serve_forever()


if __name__ == "__main__":
    main()
