package sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client{

	public static void main(String [] args) throws IOException{

		int num, temp;
		Scanner sc = new Scanner(System.in);
		Socket s = new Socket("127.0.0.1"/*"10.34.87.47"*/, 5349);       
		Scanner sc1 = new Scanner(s.getInputStream());
		System.out.println("Intro tu num. de expediente ??");
		num =sc.nextInt();
		PrintStream p = new PrintStream(s.getOutputStream());
		p.println(num);
		temp = sc1.nextInt();
		System.out.println(temp);
	}
}