from flask import jsonify


def init_app(app):
    @app.errorhandler(404)
    def not_found(e):
        data = {'status': 404, 'message': 'Not Found'}
        res = jsonify(data)
        return res

    @app.errorhandler(400)
    def not_found(e):
        data = {'status': 400, 'message': 'Bad Request'}
        res = jsonify(data)
        return res

    @app.errorhandler(409)
    def not_found(e):
        data = {'status': 409, 'message': 'Duplicate'}
        res = jsonify(data)
        return res

    @app.errorhandler(401)
    def not_found(e):
        data = {'status': 401, 'message': 'Unauthorized'}
        res = jsonify(data)
        return res
