import socket

HOST = '127.0.0.1'
PORT = 5349

if __name__ == "__main__":
	print('inicio socket "{}", {}'.format(HOST, PORT))
	with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
		link_tuple = (HOST, PORT)

		print('conecto {}'.format(link_tuple))
		s.connect(link_tuple)
		data_int = str(input("Intro tu num. de expediente ??"))

		s.sendall(bytes(data_int, encoding='utf8'))
		print('datos enviados')
		data_bytes = s.recv(1024)

		data_anws_int = data_bytes.decode("utf-8")
		print("respuesta servidor: {}".format(data_anws_int))
		print("cliente cierra socket")