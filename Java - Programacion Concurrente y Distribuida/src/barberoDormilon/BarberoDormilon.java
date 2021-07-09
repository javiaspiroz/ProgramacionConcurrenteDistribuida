package barberoDormilon;

import java.util.concurrent.*;

public class BarberoDormilon  extends Thread {

	/* REQUISITOS PREVIOS */

	/* creamos los sem�foros. Primero no hay clientes y
el barbero est� dormido, as� que llamamos al constructor con el par�metro
0 creando as� sem�foros con cero permisos iniciales.
El sem�foro (1) construye un sem�foro binario, como se desee. */

	public static Semaphore customers = new Semaphore(0);
	public static Semaphore barber = new Semaphore(0);
	public static Semaphore accessSeats = new Semaphore(1);

	/* denotamos que el n�mero de sillas en esta barber�a es 5. */
	public static final int CHAIRS = 5;

	/* creamos el n�mero entero NumberOfFreeSeats para que los clientes
    puede sentarse en un asiento libre o salir de la peluquer�a si hay
    no hay asientos disponibles */
	public static int numberOfFreeSeats = CHAIRS;

	/*EL HILO DEL CLIENTE */

	class Customer extends Thread {
		/* creamos el iD entero que es un n�mero de identificaci�n �nico para cada cliente
	y un booleano notCut que se usa en el ciclo de espera del Cliente */
		int iD;
		boolean notCut=true;
		/* Constructor para el Cliente */
		public Customer(int i) {
			iD = i;
		}
		public void run() {   
			while (notCut) {  // siempre y cuando el cliente no sea cortado
				try {
					accessSeats.acquire();  // intenta acceder a las sillas
					if (numberOfFreeSeats > 0) { // si hay asientos libres
						System.out.println("Cliente " + this.iD + " acaba de sentarse");
						numberOfFreeSeats--;  // sentado en una silla
						customers.release();  // notifica al barbero que hay un cliente
						accessSeats.release();//ya no es necesario cerrar las sillas
						try {
							barber.acquire(); //ahora es el turno de los clientes, pero tenemos que esperar si el barbero est� ocupado
							notCut = false;  // este cliente ahora se ir� despu�s del procedimiento
							this.get_haircut(); //cortar ...
						} catch (InterruptedException ex) {}
					}   
					else  { //no hay asientos libres
						System.out.println("No hay asientos libres. Cliente " + this.iD +" Ha salido de la barber�a");
						accessSeats.release(); //suelta la cerradura de los asientos
						notCut=false;//el cliente se ir� ya que no hay puntos en la cola izquierda.
					}
				}
				catch (InterruptedException ex) {}
			}
		}
		/*este m�todo simular� cortarse el pelo */
		public void get_haircut(){
			System.out.println("Cliente " + this.iD + " se est� cortando el pelo");
			try {
				sleep(5000);
			} catch (InterruptedException ex) {}
		}
	}

	/* EL HILO DE BARBERO */
	class Barber extends Thread {
		public Barber() {}
		public void run() {
			while(true) { //se ejecuta en un bucle infinito
				try {
					customers.acquire(); //intenta adquirir un cliente; si no hay ninguno disponible, se va a dormir
					accessSeats.release();//en este momento ha sido despertado -> quiere modificar el n�mero de asientos disponibles
					numberOfFreeSeats++; //una silla se libera
					barber.release();  //el barbero est� listo para cortar
					accessSeats.release(); // ya no necesitamos la cerradura de las sillas
					this.cutHair(); //cortar ...
				} catch (InterruptedException ex) {}
			}
		}

		/*este m�todo simular� cortar el cabello */
		public void cutHair(){
			System.out.println("El barbero est� cortando el pelo");
			try {
				sleep(5000);
			} catch (InterruptedException ex){ }
		}
	}

	//m�todo principal
	public static void main(String args[]) {

		BarberoDormilon barberShop = new BarberoDormilon();  // Crea una nueva barber�a.
		barberShop.start(); //Que comience la simulaci�n
	}

	public void run(){   
		Barber giovanni = new Barber();  //Giovanni es el mejor barbero de todos los tiempos
		giovanni.start(); //Listo para otro d�a de trabajo.
		
		int i=1;
		while (true){//bucle infinito		
			Customer aCustomer = new Customer(i);
			i++;
			aCustomer.start();
			try {
				sleep(2000);
			} catch(InterruptedException ex) {};
		}

		/*for (int i=1; i<16; i++) {//15 es el numero de clientes totales, se pueden poner los que queramos
			Customer aCustomer = new Customer(i);
			aCustomer.start();
			try {
				sleep(2000);
			} catch(InterruptedException ex) {};
		}*/
	} 
}