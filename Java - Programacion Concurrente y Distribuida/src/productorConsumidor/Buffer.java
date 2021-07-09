package productorConsumidor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Buffer {
	
	private static Queue<Integer> store = new LinkedList<Integer>();
	public static final int bSize = 5;
	private static Semaphore sNoVacio = new Semaphore(0, true);
	private static Semaphore sNoLleno = new Semaphore(bSize, true);
	
	public static Queue<Integer> getStore() {
		return store;
	}
	
	public static Semaphore getsNoVacio() {
		return sNoVacio;
	}
	
	public static Semaphore getsNoLleno() {
		return sNoLleno;
	}
}