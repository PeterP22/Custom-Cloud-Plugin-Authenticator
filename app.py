from flask import Flask, jsonify, request
import mysql.connector
import os

app = Flask(__name__)


def get_db_connection():
    conn = mysql.connector.connect(
        host='your-server-ip',
        user='your-mysql-username',
        password='your-mysql-password',
        database='runescape_plugins'
    )
    return conn

@app.route('/validate_license', methods=['GET'])
def validate_license():
    key = request.args.get('key')
    conn = get_db_connection()
    cursor = conn.cursor()
    cursor.execute('SELECT * FROM PluginPurchases WHERE license_key = %s', (key,))
    license = cursor.fetchone()
    cursor.close()
    conn.close()

    if license:
        return jsonify({'valid': True, 'license_info': license})
    else:
        return jsonify({'valid': False}), 404



@app.route('/')
def home():
    return "Welcome to the RuneScape PvP Plugin License API!"


if __name__ == '__main__':
    app.run(debug=True)
