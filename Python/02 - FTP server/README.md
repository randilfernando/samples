# FTP server
Sample FTP server and client implemented with python
# How to run
## Clone repository
```bash
git clone https://github.com/randilfernando/samples.git
```
## Open project
Go to "Python/02 - FTP server"  
Open terminal  
Install dependencies with `pip install -r requirements.txt`  
## Run server
Run FTP server with `python3 server.py`
## How to use client
Run FTP client with `python3 client.py`
## Client commands available
### con [ip] [port]
connect with ftp server
```
con 127.0.0.1 1026
```
### dis
disconnect from ftp server
```
dis
```

### up [local_file_path]
upload file from local to current directory in ftp server
```
up /home/user/Documents/file.txt
```
### down [file_name] [local_directory_path]
download file from current directory in ftp server to local directory
```
up file.txt /home/user/Documents
```

### cd, ls, pwd, clear, cls
same syntax as in system terminal