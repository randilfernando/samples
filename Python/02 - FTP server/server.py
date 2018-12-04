import glob
import readline
import socket
import sys

from pyftpdlib.authorizers import DummyAuthorizer
from pyftpdlib.handlers import FTPHandler
from pyftpdlib.servers import FTPServer


def complete(text, state):
    """
    generate path from input string and auto complete
    :param text: text to complete
    :param state: input state
    :return: completed text
    """
    return (glob.glob(text + '*') + [None])[state]


def get_ip():
    """
    find ip address associated with the network interface
    :return: ip address
    """
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.connect(("8.8.8.8", 80))
    ip = s.getsockname()[0]
    s.close()
    return ip


def main():
    # enable auto completion for paths (optional)
    readline.set_completer_delims(' \t\n;')
    readline.parse_and_bind("tab: complete")
    readline.set_completer(complete)

    # get base directory path from command line inputs
    directory = sys.argv[1]

    # add authorization (anonymous authorization)
    authorizer = DummyAuthorizer()
    authorizer.add_anonymous(directory)

    # initialize FTP handler
    handler = FTPHandler
    handler.authorizer = authorizer

    # start FTP server
    server = FTPServer((get_ip(), 1026), handler)
    server.serve_forever()


if __name__ == "__main__":
    main()
