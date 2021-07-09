import socketserver

HOST = '127.0.0.1'
PORT = 5349

class Controller(socketserver.BaseRequestHandler):
    """controla peticiones servidor"""
    def handle(self):
        """controla comunicación servidor"""
        open_socket = True
        while open_socket:
            print('esperando cliente')
            data_bytes = self.request.recv(1024).strip()
            if data_bytes:
                data_str = data_bytes.decode("utf-8") 
                print('recibido cliente: {}'.format(data_str))
                data_int = int(data_str)
                
                answer_str = "## RESPUESTA DEL SERVIDOR: {} ##".format(data_int*2)
                
                self.request.sendall(bytes(answer_str, encoding='utf8'))
                print('respuesta: {}'.format(answer_str))
            else:
                print('fin cliente')
                open_socket = False

if __name__ == "__main__":
    tuple_link = (HOST, PORT)
    try:
        print('Link con socket: {}'.format(tuple_link))
        with socketserver.TCPServer(tuple_link, Controller) as ss:
            print('buble servidor. Interrumpir [Ctrl]+[C]')
            ss.serve_forever()
    except KeyboardInterrupt:
        print('interrupción por teclado')
    finally:        
        if ss is not None:
            ss.shutdown()
        print('Servidor Cerrado')