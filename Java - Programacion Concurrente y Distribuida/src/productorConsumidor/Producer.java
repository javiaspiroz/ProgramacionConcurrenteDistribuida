package productorConsumidor;

import java.util.Random;

public class Producer extends Thread{
	
	public Producer() {
		start();
	}
	
	private void produce() {
		Random rdmNum = new Random();
		int numP = rdmNum.nextInt(999) + 1;
		int sleepTime = rdmNum.nextInt(250 - 25 + 1) + 25; //Rango (max - min + 1) + min
		
		try {
			sleep(sleepTime);
			
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Productor: Número " + numP + " producido.");
		//Añadir al buffer
		Buffer.getStore().add(numP);
	}
	
	@Override
	public void run () {
		
		while(true) {			
			if(Buffer.getStore().size() == Buffer.bSize) {
				System.out.println("Productor: El buffer está lleno, esperando a que el consumidor trabaje.");
			}
			
			try {
				Buffer.getsNoLleno().acquire();
			}catch(InterruptedException e){
				e.printStackTrace();
			}

			produce();
			Buffer.getsNoVacio().release();
		}
	}
}