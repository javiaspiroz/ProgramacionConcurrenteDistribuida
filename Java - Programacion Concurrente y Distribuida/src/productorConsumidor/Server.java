package productorConsumidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {	
	public static void main(String[] args) throws IOException {
		ServerSocket listener = new ServerSocket(704);
		System.out.println("Servidor Iniciado");
		try {
			while(true) {
				Socket socket = listener.accept();
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				int clientNumber = 0;
				String inputString = input.readLine();
				if(isNumeric(inputString))
					clientNumber = Integer.parseInt(inputString);
				Random randomGenerator = new Random();
				int rndInt = randomGenerator.nextInt(6) + 1;
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				if(clientNumber == rndInt)
					out.println("El numero " + clientNumber + " ha resultado ganador. Enhorabuena.");
				else if(!isNumeric(inputString))
					out.println(inputString + " nisiquiera es un numero");
				else if (clientNumber < 1 || clientNumber > 6)
					out.println("El numero no entra en el sorteo");
				else
					out.println("Mala suerte, el numero premiado ha sido el " + rndInt + ", Intentalo de nuevo.");
				socket.close();
			}
		} catch (Exception e) {	
		}
	}
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
