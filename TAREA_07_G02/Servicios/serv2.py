from flask import Flask, request
from flask_restful import Resource, Api

app= Flask(__name__)
api= Api(app)

class MensajeGrupo2(Resource):
	def get(self):
		return {'msg':'SERVICIO GRUPO02'}
	
api.add_resource(MensajeGrupo2,'/')


if __name__ == '__main__':
	app.run(debug=True)