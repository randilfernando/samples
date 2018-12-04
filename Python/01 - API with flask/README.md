# API with flask
API which support user registration and authorization using Jason Web Tokens
# How to run
## Clone repository
```bash
git clone https://github.com/randilfernando/samples.git
```
## Open project
Go to "Python/01 - API with flask"  
Open terminal  
Install dependencies with `pip install -r requirements.txt`  
## Run server
Set flask app with `export FLASK_APP=sample`  
Set flask environment with `export FLASK_ENV=development`  
Create database with `flask init-db`  
Run server with `flask run`
## Test API
Use client like Postman to test API
### Register
Send post request to [http://localhost:5000/auth/register](http://localhost:5000/auth/register) with json payload  
Sample payload
```json
{
    "username": "user",
    "password": "1234"
}
```
### Login
Send post request to [http://localhost:5000/auth/login](http://localhost:5000/auth/login) with json payload  
Sample payload
```json
{
    "username": "user",
    "password": "1234"
}
```