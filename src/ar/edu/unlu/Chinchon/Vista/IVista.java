package ar.edu.unlu.Chinchon.Vista;

import java.awt.HeadlessException;
import java.rmi.RemoteException;

import ar.edu.unlu.Chinchon.Controlador.Controlador;

public interface IVista {

	void actualizaPuntaje() throws RemoteException;

	void setControlador(Controlador controlador);

	void reiniciarMano() throws RemoteException;

	void finJuego() throws RemoteException;

	void notificarMensaje(String string);

	void notificarError(String mensaje);

	void juegoIniciado() throws RemoteException;

	void turno() throws RemoteException;

	void MouseClicked(Object objeto);

	void verTop() throws HeadlessException, RemoteException;

	void cargaJuego() throws RemoteException;

	void iniciar() throws RemoteException;
	
	void actualizarCartas() throws RemoteException;
	
	void actualizaDescarte() throws RemoteException;
	
	void robaCartaDescarte() throws RemoteException;

	void cambiaTurno() throws RemoteException;

	void limpiaTurno() throws RemoteException;

	void deshabilitaBotones();

	void mostrarCartasJugadorNoActual() throws RemoteException;

	void descarteVacio();

	void muestraMesa();
}