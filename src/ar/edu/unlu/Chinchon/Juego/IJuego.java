package ar.edu.unlu.Chinchon.Juego;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IJuego extends IObservableRemoto{

	void repartir() throws RemoteException;

	Jugador getGanador() throws RemoteException;

	IJugador[] getJugadores() throws RemoteException;

	boolean checkeaMano() throws RemoteException;

	void terminarMano() throws RemoteException;

	void girarMazo() throws RemoteException;

	void reiniciarMano() throws RemoteException;

	int getCantRondas() throws RemoteException;

	void agregarJugador(String name) throws RemoteException;

	void iniciarJuego() throws RemoteException;

	void cambiarJugador() throws RemoteException;

	Jugador getJugadorActual() throws RemoteException;

	Carta mostrarDescarte() throws RemoteException;

	int getEstado() throws RemoteException;

	void robarCarta() throws RemoteException;

	List<Carta> mostrarCartas() throws RemoteException;

	void robarCartaDescarte() throws RemoteException;

	void tiraCarta(int numero) throws RemoteException;

	int getCartas() throws RemoteException;

	int getCartasDescarte() throws RemoteException;

	void limpiaCombinaciones() throws RemoteException;

	void reiniciaJuego() throws RemoteException;

	List<Jugador> getCantidadJugadores() throws RemoteException;

	void cargar(IJuego iJuego) throws RemoteException;

	void verTop() throws RemoteException;

	ArrayList<Jugador> getTop() throws RemoteException;
	
	int getJugadorEnTurno() throws RemoteException;
	
	Mazo getMazo() throws RemoteException;
	
	Descarte getDescarte() throws RemoteException;
}