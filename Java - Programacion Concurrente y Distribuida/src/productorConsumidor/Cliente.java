package productorConsumidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce la direccion IP del servidor");
		String serverAddress = sc.nextLine();
		while (true) {
			System.out.println("Introduce numero del 1 al 6");
			String number = sc.nextLine();
			Socket socket = new Socket(serverAddress, 704);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			out.println(number);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String answer = in.readLine();
			System.out.println(answer);
			socket.close();
		}
	}
}
