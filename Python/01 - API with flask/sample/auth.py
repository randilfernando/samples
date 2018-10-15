from flask import (
    Blueprint,
    jsonify,
    request,
    abort
)

from werkzeug.security import (
    check_password_hash,
    generate_password_hash
)

from sample.db import get_db

import jwt

bp = Blueprint('auth', __name__, url_prefix='/auth')

secret = 'secret'


@bp.route('/register', methods=['POST'])
def register():
    data = request.json

    if 'username' not in data or 'password' not in data:
        # bad request (request validation failed)
        abort(400)
    else:
        username = data['username']
        password = data['password']

        user = get_db().execute('SELECT id FROM user WHERE username = ?', (
            username,
        )).fetchone()

        if user is not None:
            # conflict (because user already exist)
            abort(409)
        else:
            get_db().execute('INSERT INTO user (username, password) VALUES (?,?)', (
                username,
                generate_password_hash(password),
            ))
            get_db().commit()
            data = {'status': 200, 'message': 'Success'}
            res = jsonify(data)
            return res


@bp.route('/login', methods=['POST'])
def login():
    content = request.json

    if 'username' not in content or 'password' not in content:
        # bad request (request validation failed)
        abort(400)
    else:
        username = content['username']
        password = content['password']
        user = get_db().execute('SELECT * FROM user WHERE username = ?', (
            username,
        )).fetchone()

        if user is None:
            # not found (user not exist)
            abort(404)
        elif not check_password_hash(user['password'], password):
            # not authorized (credentials not match)
            abort(401)
        else:
            # generate jwt token when there is a successful authentication
            token = jwt.encode({'uid': user['id']}, secret, algorithm='HS256')
            data = {'status': 200, 'message': 'Success', 'data': {'access_token': token.decode('utf-8')}}
            res = jsonify(data)
            return res
