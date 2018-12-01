from pyftpdlib.authorizers import DummyAuthorizer
from pyftpdlib.handlers import FTPHandler
from pyftpdlib.servers import FTPServer


def main():
    authorizer = DummyAuthorizer()
    authorizer.add_anonymous("./ftp_directory")

    handler = FTPHandler
    handler.authorizer = authorizer

    server = FTPServer(("127.0.0.1", 1026), handler)
    server.serve_forever()


if __name__ == "__main__":
    main()
