package sockets;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server{
	
	public static void main(String [] args) throws IOException{
		int num, temp;
		ServerSocket s1 = new ServerSocket(5349);
		Socket ss = s1.accept();
		Scanner sc = new Scanner(ss.getInputStream());
		num = sc.nextInt();
		temp = num*2;
		PrintStream p = new PrintStream(ss.getOutputStream());
		p.println(temp);
	}
}