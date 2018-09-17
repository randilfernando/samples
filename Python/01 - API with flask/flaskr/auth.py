from flask import (
    Blueprint,
    jsonify,
    request,
    abort)

from werkzeug.security import (
    check_password_hash,
    generate_password_hash
)

from flaskr.db import get_db

import jwt

bp = Blueprint('auth', __name__, url_prefix='/auth')

secret = 'secret'


@bp.route('/register', methods=['POST'])
def register():
    content = request.json

    if 'username' not in content or 'password' not in content:
        abort(400)
    else:
        username = content['username']
        password = content['password']

        user = get_db().execute('SELECT id FROM user WHERE username = ?', (
            username,
        )).fetchone()

        if user is not None:
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
        abort(400)
    else:
        username = content['username']
        password = content['password']
        user = get_db().execute('SELECT * FROM user WHERE username = ?', (
            username,
        )).fetchone()

        if user is None:
            abort(404)
        elif not check_password_hash(user['password'], password):
            abort(401)
        else:
            token = jwt.encode({'uid': user['id']}, secret, algorithm='HS256')
            data = {'status': 200, 'message': 'Success', 'data': {'access_token': token.decode('utf-8')}}
            res = jsonify(data)
            return res
