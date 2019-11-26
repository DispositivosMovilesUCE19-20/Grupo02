import json
from flask import Flask, jsonify, request
app = Flask(__name__)

@app.route('/', methods=['GET', 'POST' ])
def index():
	if(request.method== 'POST'):
		some_json= request.get_text();
		return jsonify({'you:sent' : some_json}),201
	else:
		return jsonify({"msg": "Grupo 02"})

@app.route('/oper/<int:num>', methods=['GET'])
def mensaje1(num):
	mensaje="indefinido";
	if(num==1):
		mensaje="El Estudiante se ha eliminado correctamente";
	if(num==2):
		mensaje="El Estudiante se ha editado correctamente";
	if(num==3):
		mensaje="Error";
	return jsonify({"msg": mensaje})

@app.route('/escribir', methods=['POST'])
def escribir():
	#data = request.get_text();
	#data = request.json['msg']
	data = request.get_json()
	n = json.dumps(data)
	o = json.loads(n)
	f = open ('holamundo.txt','w');
	f.write(str(o)) 
	f.close();
	#mensaje="Se guardo en el servidor estudiantes";
	#return jsonify({"msg": mensaje})
	return "holamundo"

@app.route('/servicioExamen', methods=['GET'])
def servicioexamen():
	mensaje="Examen primer hemisemestre";
	return jsonify({"msg": mensaje})

if __name__ == '__main__':
	app.run(debug=True)