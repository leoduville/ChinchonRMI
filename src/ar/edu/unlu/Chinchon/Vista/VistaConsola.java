package ar.edu.unlu.Chinchon.Vista;


import java.rmi.RemoteException;
import java.util.Scanner;

import ar.edu.unlu.Chinchon.Controlador.Controlador;
import ar.edu.unlu.Chinchon.Juego.Carta;
import ar.edu.unlu.Chinchon.Juego.IJugador;
import ar.edu.unlu.Chinchon.Juego.Juego;
import ar.edu.unlu.Chinchon.Juego.Jugador;


 public class VistaConsola implements IVista {
	
	private Controlador miControlador;
	private Scanner teclado = new Scanner(System.in);
	
	public VistaConsola(Controlador controlador) {
		this.miControlador = controlador;
		this.miControlador.setVista(this);
	//	teclado = new Scanner(System.in);
	}
	
	public void setControlador(Controlador controlador) {
		this.miControlador = controlador;
	}
	
	private int mostrarMenu() throws RemoteException{
		int opcion = -1;
		while (opcion < 0 || opcion > 3) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                              CHINCHON                                 --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                        Menu de Configuracion                         --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("--                                Opciones                                      --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 1 - Agregar Jugadores                                                    --");
			System.out.println("-- 2 - Mostrar Lista de Jugadores                                      --");
			System.out.println("-- 3 - Comenzar Partida                                                   --");
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("-- 0 - Salir del Juego                                                        --");
			System.out.println("------------------------------------------------------------------------------");
			opcion = teclado.nextInt();
		}
		return opcion;
	}

	private void agregarJugador() throws RemoteException{
		Scanner nombre1 = new Scanner(System.in);
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                          Agregando Jugador                            --");
		System.out.println("------------------------------------------------------------------------------");
		System.out.print("-- Ingresar nombre : ");
		String nombre = nombre1.nextLine();
		miControlador.AgregarJugador(nombre);
		System.out.println("Jugador agregado con exito");
		esperarEnter();
		}
		
	
	public void mostrarJugadores(IJugador[] iJugadors) throws RemoteException {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("--                            Lista de Jugadores                             --");
		System.out.println("------------------------------------------------------------------------------");
		for (IJugador j : miControlador.getJugadores())
			System.out.println(j.getNombre());
		esperarEnter();
	}
	
	private void esperarEnter() {
		System.out.print("Presione ENTER para continuar...");
		Scanner sc = new Scanner(System.in);
		String pausa = sc.nextLine();
	}
	
	@Override
	public void iniciar() throws RemoteException {
		int opcion = -1;
		while (opcion != 0) {
			opcion = mostrarMenu();
			switch (opcion) {
			case 1:
				// Agregar Jugador
				agregarJugador();
				break;
			case 2:
				// mostrar lista de Jugadores
				mostrarJugadores(miControlador.getJugadores());
				break;
			case 3:
				// Comenzar Juego
				miControlador.iniciarJuego();
				esperarEnter();
				break;
			}
		}
		System.out.println("Juego finalizado.");

	}
	
	public void limpiarPantalla() {
		for (int i = 1; i < 100; i++)
		System.out.println("");
	}
	
	public void mostrarCartas() throws RemoteException {
		for (Carta cartas : miControlador.mostrarCartas()) {
			System.out.print("" + cartas.getNumero() + " De " + cartas.getPalo() + " - ") ;
		}
	}
	
	public String mostrarCartaDescarte() throws RemoteException {
		if (miControlador.mostrarDescarte() != null)
			return ("" + miControlador.mostrarDescarte().getNumero() + " De " + miControlador.mostrarDescarte().getPalo());
		else
			return null;
	}
	
	public int mostrarMenuJugador() throws RemoteException {
		System.out.println("----------------------------------------------");
		System.out.println("¿Que desea hacer?");
		System.out.println("1- Para robar una carta del mazo");
		System.out.println("2- Para robar una carta del descarte");
		System.out.println("3- Tirar carta");
		System.out.println("-------------------------------");
		mostrarCartas();
		System.out.println("");
		System.out.println("-------------------------------");
		System.out.println("CARTA DEL DESCARTE");
		if (mostrarCartaDescarte() == null)
			System.out.println("Descarte vacio");
		else 
		System.out.println(mostrarCartaDescarte());
		System.out.println("-------------------------------");
		int opcion = teclado.nextInt();
		while (opcion < 1 || opcion > 3) {
			System.out.println("Ingrese una opcion valida. (1-3)");
			opcion = teclado.nextInt();}
		return opcion;
	}
	
	public void turno() throws RemoteException {
		System.out.println("----------------------------------------------");
		System.out.println("Es el turno del jugador : " + miControlador.getJugadorEnTurno().getNombre());
	}
	
	@Override
	public void juegoIniciado() throws RemoteException {
	while (miControlador.getEstado() != Juego.FIN_JUEGO &&
			miControlador.getEstado() != 0) {
			turno();
			int opcion = mostrarMenuJugador();
			switch (opcion) {
			case 1:
				miControlador.robaCarta();
				esperarEnter();
				break;
			case 2:
				miControlador.robaCartaDescarte();
				esperarEnter();
				break;
			case 3:
				tirarCarta();
				break;
			}
		}
	}
	
	private void tirarCarta() throws RemoteException {
		if (miControlador.cantidadCartas() != 8) {
			System.out.println("Para tirar una carta primero tenes que robar");
			esperarEnter();
		}else {
			System.out.println("Ingrese la posicion de la carta (1-8)");
			int numero = teclado.nextInt();
			while (numero < 1 || numero > 8) {
				System.out.println("Ingrese una posicion valida (1-8)");
				numero = teclado.nextInt();}
			miControlador.tiraCarta(numero);
			esperarEnter();
			if (miControlador.checkeaMano()) {
			System.out.println("Terminar mano? 1/SI    2/NO");
			int opcion = teclado.nextInt();
			while (opcion < 1 || opcion > 2) {
				System.out.println("Error. Ingrese 1/SI  o  2/NO");
				opcion = teclado.nextInt();
			}
			if (opcion == 1)
				miControlador.terminaMano();
			else
				miControlador.limpiarCombinaciones();
			}
			
			if (miControlador.getEstado() != Juego.FIN_JUEGO) {
			if (miControlador.getEstado() != Juego.FIN_MANO && miControlador.getEstado() != 0) {
				System.out.println("Cambiando turno...");
				miControlador.cambiaTurno();
			}}
			esperarEnter();
		}
	}

	public void finJuego() throws RemoteException {
		System.out.println("-----------------------");
		System.out.println("Chinchon finalizado");
		System.out.println("-----------------------");
		System.out.println("El ganador es " + miControlador.getGanador());
		puntos();
		System.out.println("Cantidad de rondas " + miControlador.obtieneRondas());
		System.out.println("-----------------------");
	}

	
	public void notificarMensaje(String string) {
		System.out.println(string);
	}

	public void notificarError(String string) {
		System.out.println(string);
		
	}

	public void puntos() throws RemoteException {
		for (IJugador jugador : miControlador.getJugadores()) {
			System.out.println("Puntos de " + jugador.getNombre() + " : " + jugador.getPuntos());
		}
	}
	
	public void reiniciarMano() throws RemoteException {
		System.out.println("Mano finalizada.");
		System.out.println("El ganador de la mano es " + miControlador.getJugadorEnTurno().getNombre());
		puntos();
		miControlador.reiniciaMano();
		esperarEnter();
		}

	public void cargaJuego() throws RemoteException {
		mostrarMenuJugador();
	}

	public void verTop() throws RemoteException {
		for (int i = 0; i < miControlador.getTop().size(); i++) {
			System.out.println(miControlador.getTop().get(i));
		}
	}

	@Override
	public void actualizaPuntaje() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void MouseClicked(Object objeto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizarCartas() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void robaCartaDescarte() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiaTurno() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void limpiaTurno() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deshabilitaBotones() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarCartasJugadorNoActual() throws RemoteException {
		Jugador jugador;
		if (miControlador.getJugadorEnTurno().getNombre().equals(miControlador.getListaJugadores().get(0).getNombre()))
			jugador = miControlador.getListaJugadores().get(1);
		else
			jugador = miControlador.getListaJugadores().get(0);
		
		for (Carta cartas : jugador.getCartas()) {
			System.out.print("" + cartas.getNumero() + " De " + cartas.getPalo() + " - ") ;
		}
	}

	@Override
	public void descarteVacio() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actualizaDescarte() throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void muestraMesa() {
		// TODO Auto-generated method stub
		
	}
		
	
	
 }



	

