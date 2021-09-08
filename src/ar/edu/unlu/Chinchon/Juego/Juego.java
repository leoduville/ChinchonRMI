package ar.edu.unlu.Chinchon.Juego;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.rmimvc.observer.ObservableRemoto;

public class Juego extends ObservableRemoto implements Serializable, IJuego  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Juego instancia;
	private List<Jugador> jugadores = new ArrayList<>();
	private Mazo mazo = new Mazo();
	private Descarte descarte = new Descarte();
	private final int MAX_PUNTOS = 50;
	private Jugador ganador;
	private Mano mano = new Mano();
	private int cantRondas = 0;
	public static final int SETEANDO = 0;
	public static final int JUGADOR_AGREGADO = 1;
	public static final int JUEGO_INICIADO = 2;
	public static final int JUGANDO = 3;
	public static final int FIN_MANO = 4;
	public static final int FIN_JUEGO = 5;
	public static final int DESCARTE_VACIO = 6;
	public static final int CARTAS_MAXIMAS = 7;
	public static final int PUNTOS_CAMBIADOS = 8;
	public static final int CARGAR_JUEGO = 9;
	public static final int VER_TOP = 10;
	private int miEstado = SETEANDO;
	//private transient ArrayList<Observador> misObservadores = new ArrayList<>();
	private int jugadorActual;
	private Top topFive = new Top();
	private Persistir persistir = new Persistir();;
	

	private Juego() {
		try {
		topFive = (Top) persistir.cargarObjetos("top.dat");
		} catch (Exception e) {
			}
		}

	@Override
	public void repartir() throws RemoteException{
		miEstado = JUGANDO;
		for (int i = 1; i <= 7; i++) {
			for (Jugador jugador : jugadores) {
				
				jugador.añadirCarta(mazo.darCarta());
				jugador.ordenarPorNumero();
			}
		} 
	/*	jugadores.get(jugadorActual).añadirCarta(new Carta(1, Palo.ORO));
		jugadores.get(jugadorActual).añadirCarta(new Carta(2, Palo.ORO));
		jugadores.get(jugadorActual).añadirCarta(new Carta(3, Palo.ORO));
		jugadores.get(jugadorActual).añadirCarta(new Carta(10, Palo.COPA));
		jugadores.get(jugadorActual).añadirCarta(new Carta(10, Palo.BASTO));
		jugadores.get(jugadorActual).añadirCarta(new Carta(10, Palo.ESPADA));
		jugadores.get(jugadorActual).añadirCarta(new Carta(10, Palo.ORO));
		
		jugadores.get(jugadorActual+1).añadirCarta(new Carta(3, Palo.ORO));
		jugadores.get(jugadorActual+1).añadirCarta(new Carta(3, Palo.ESPADA));
		jugadores.get(jugadorActual+1).añadirCarta(new Carta(3, Palo.COPA));
		jugadores.get(jugadorActual+1).añadirCarta(new Carta(3, Palo.BASTO));
		jugadores.get(jugadorActual+1).añadirCarta(new Carta(2, Palo.ESPADA));
		jugadores.get(jugadorActual+1).añadirCarta(new Carta(4, Palo.COPA));
		jugadores.get(jugadorActual+1).añadirCarta(new Carta(5, Palo.COPA));
	*/
	}
	
	@Override
	public Jugador getGanador() throws RemoteException {
		if (miEstado == FIN_JUEGO);
		return this.ganador;
	}
	
	@Override
	public IJugador[] getJugadores() throws RemoteException {
		IJugador[] misJugadores = new IJugador[jugadores.size()];
		int i = 0;
		for (Jugador j : jugadores) {
			misJugadores[i++] = j;
		}

		return misJugadores;
	}
	
	@Override
	public boolean checkeaMano() throws RemoteException {
		boolean bandera;
		bandera = mano.checkearMano(jugadores.get(jugadorActual));
		return bandera;
	}
		
	@Override
	public void terminarMano() throws RemoteException {
		Jugador jugador = this.jugadores.get(jugadorActual);
		Jugador jugador1 = null;
		if (jugadorActual == 1)
			jugador1 = this.jugadores.get(jugadorActual-1);
		else
			jugador1 = this.jugadores.get(jugadorActual+1);

	if (mano.esChinchon(jugador)) {
		ganador = jugador;
		jugador1.aumentarPuntos(100);
		miEstado = FIN_JUEGO;
		notificarObservadores(EventosChinchon.FIN_JUEGO);
		cantRondas++;
		reiniciaJuego();
	}
	else {

	mano.checkearMano(jugador1);
	List<Carta> cartasperdio = new ArrayList<>();
	for(int i = 0;i<jugador1.getCartas().size();i++)
	{
		if (!jugador1.getCombinacion1().contains(jugador1.getCartas().get(i))
				&& !jugador1.getCombinacion2().contains(jugador1.getCartas().get(i))) {
			cartasperdio.add(jugador1.getCartas().get(i));
		}
	}
	List<Carta> aeliminar = new ArrayList<Carta>();
	if(jugador.getCombinacion1().size()==4 &&
		(mano.checkearEscalera(jugador.getCombinacion1())) ||
		 jugador.getCombinacion2().size()==4 &&
		(mano.checkearEscalera(jugador.getCombinacion1()))
		)	{
				for (Carta cartasperdidas : cartasperdio) {
					if (jugador.getCombinacion1().size()==4) {
					if (cartasperdidas.getPalo() == jugador.getCombinacion1().get(0).getPalo() &&
					(cartasperdidas.getNumero() == jugador.getCombinacion1().get(0).getNumero()-1 ||
					cartasperdidas.getNumero() == jugador.getCombinacion1().get(3).getNumero()+1))
						aeliminar.add(cartasperdidas);
					}else {
					if (cartasperdidas.getPalo() == jugador.getCombinacion2().get(0).getPalo() &&
					(cartasperdidas.getNumero() == jugador.getCombinacion2().get(0).getNumero()-1 ||
					cartasperdidas.getNumero() == jugador.getCombinacion2().get(3).getNumero()+1)){
						aeliminar.add(cartasperdidas);
					}
					}
				}
			}
				
			
			if (jugador.getCombinacion1().size() == 3 &&  mano.checkearEscalera(jugador.getCombinacion1()) || 
					jugador.getCombinacion2().size() == 3 &&  mano.checkearEscalera(jugador.getCombinacion2()))	{
				for (Carta cartasperdidas : cartasperdio) {
					if (cartasperdidas.getPalo() == jugador.getCombinacion1().get(0).getPalo() &&
					(cartasperdidas.getNumero() == jugador.getCombinacion1().get(0).getNumero () - 1 ||
					cartasperdidas.getNumero() == jugador.getCombinacion1().get(2).getNumero()+1) ||
					(cartasperdidas.getPalo() == jugador.getCombinacion2().get(0).getPalo() &&
					(cartasperdidas.getNumero() == jugador.getCombinacion2().get(0).getNumero()-1 ||
					cartasperdidas.getNumero() == jugador.getCombinacion2().get(2).getNumero()+1))) {
						aeliminar.add(cartasperdidas);
					}
				}
			}
			
			if (jugador.getCombinacion1().size() == 4 &&  mano.checkearIguales(jugador.getCombinacion1()) || 
					jugador.getCombinacion2().size() == 4 &&  mano.checkearIguales(jugador.getCombinacion2()))	{
				for (Carta cartasperdidas : cartasperdio) {
					if (cartasperdidas.getNumero() == jugador.getCombinacion1().get(0).getNumero())
						aeliminar.add(cartasperdidas);
					}
				}

		
		if (jugador.getCombinacion1().size() == 3 &&  mano.checkearIguales(jugador.getCombinacion1()) || 
				jugador.getCombinacion2().size() == 3 &&  mano.checkearIguales(jugador.getCombinacion2()))	{
			for (Carta cartasperdidas : cartasperdio) {
				if(jugador.getCombinacion1().size() == 3) {
				if ((cartasperdidas.getNumero() == jugador.getCombinacion1().get(0).getNumero() &&
						cartasperdidas.getNumero() == jugador.getCombinacion1().get(1).getNumero()))
						aeliminar.add(cartasperdidas);
				else {
					if (cartasperdidas.getNumero() == jugador.getCombinacion2().get(0).getNumero() &&
					cartasperdidas.getNumero() == jugador.getCombinacion2().get(1).getNumero())
						
					aeliminar.add(cartasperdidas);
				}}
			}
			}
		cartasperdio.removeAll(aeliminar);
		
		jugador1.aumentarPuntos(mano.calcularPuntos(cartasperdio));
		Carta cartaSobrante = null;
		if (jugador.getCombinacion1().size() == 3 && jugador.getCombinacion2().size() == 3) {
			for(int i = 0; i < jugador.getCartas().size(); i++) {
				if (!jugador.getCombinacion1().contains(jugador.getCartas().get(i)) && 
						!jugador.getCombinacion2().contains(jugador.getCartas().get(i))){
					 cartaSobrante = jugador.getCartas().get(i);
				}
			}
			if (jugador1.getCombinacion1().size() == 4 &&  mano.checkearEscalera(jugador1.getCombinacion1()) || 
					jugador1.getCombinacion2().size() == 4 &&  mano.checkearEscalera(jugador1.getCombinacion2()))	{
					if (jugador1.getCombinacion1().size() == 4) {
					if (cartaSobrante.getPalo() == jugador1.getCombinacion1().get(0).getPalo() &&
					(cartaSobrante.getNumero() == jugador1.getCombinacion1().get(0).getNumero()-1 ||
					cartaSobrante.getNumero() == jugador1.getCombinacion1().get(3).getNumero()+1))
						cartaSobrante = null;
					}else {
					if (cartaSobrante.getPalo() == jugador1.getCombinacion2().get(0).getPalo() &&
					(cartaSobrante.getNumero() == jugador1.getCombinacion2().get(0).getNumero()-1 ||
					cartaSobrante.getNumero() == jugador1.getCombinacion2().get(3).getNumero()+1))
						cartaSobrante = null;
					}
			}
		
			
			if (jugador1.getCombinacion1().size() == 3 &&  mano.checkearEscalera(jugador1.getCombinacion1()) || 
					jugador1.getCombinacion2().size() == 3 &&  mano.checkearEscalera(jugador1.getCombinacion2()))	{
					if (jugador1.getCombinacion1().size() == 3) {
					if (cartaSobrante.getPalo() == jugador1.getCombinacion1().get(0).getPalo() &&
					(cartaSobrante.getNumero() == jugador1.getCombinacion1().get(0).getNumero()-1 ||
					cartaSobrante.getNumero() == jugador1.getCombinacion1().get(2).getNumero()+1 &&
					jugador1.getCombinacion1().get(2).getNumero() != jugador1.getCombinacion1().get(0).getNumero()))
					cartaSobrante = null;
					}else{
					if (cartaSobrante.getPalo() == jugador1.getCombinacion2().get(0).getPalo() &&
					(cartaSobrante.getNumero() == jugador1.getCombinacion2().get(0).getNumero()-1 ||
					cartaSobrante.getNumero() == jugador1.getCombinacion2().get(2).getNumero()+1 &&
					jugador1.getCombinacion2().get(2).getNumero() != jugador1.getCombinacion2().get(0).getNumero())){
						cartaSobrante = null;
					}
				}
			}	
			
			if (jugador1.getCombinacion1().size() == 4 &&  mano.checkearIguales(jugador1.getCombinacion1()) || 
					jugador1.getCombinacion2().size() == 4 &&  mano.checkearIguales(jugador1.getCombinacion2()))	{
					if (cartaSobrante.getNumero() == jugador1.getCombinacion1().get(0).getNumero())
						cartaSobrante = null;
					}

		
		if (jugador1.getCombinacion1().size() == 3 &&  mano.checkearIguales(jugador1.getCombinacion1()) || 
				jugador1.getCombinacion2().size() == 3 &&  mano.checkearIguales(jugador1.getCombinacion2()))	{
				if (jugador1.getCombinacion1().size() == 3) {
				if (cartaSobrante.getNumero() == jugador1.getCombinacion1().get(0).getNumero())
						cartaSobrante = null;
				}else {
					if (cartaSobrante.getNumero() == jugador1.getCombinacion2().get(0).getNumero())
						cartaSobrante = null;
				}
				}
			
			if (cartaSobrante != null) {
				jugador.aumentarPuntos(cartaSobrante.getNumero());
			}
		} else
			if (jugador.getPuntos() > 10)
			jugador.aumentarPuntos(-10);
		cantRondas++;
		if (jugador1.getPuntos() >= MAX_PUNTOS) {
			miEstado = FIN_JUEGO;
			ganador = jugador;
			notificarObservadores(EventosChinchon.FIN_JUEGO);
			}
		if (jugador.getPuntos() >= MAX_PUNTOS) {
			miEstado = FIN_JUEGO;
			ganador = jugador1;
			notificarObservadores(EventosChinchon.FIN_JUEGO);
			}
		if (this.miEstado != FIN_JUEGO) {
		this.miEstado = FIN_MANO;
		reiniciarMano();
		}
		else {
			reiniciaJuego();
		}
	}


	}

	@Override
	public void girarMazo() throws RemoteException {
		mazo.cartas = descarte.cartas;
	}

	@Override
	public void reiniciarMano() throws RemoteException {
		descarte.vaciar();
		for (Jugador jugador : jugadores) {
			jugador.limpiarMano();
		}
		mazo.vaciar();
		mazo.llenarMazo();
		mazo.mezclar();
		repartir();
		notificarObservadores(EventosChinchon.PUNTOS_CAMBIADOS);
	}

	@Override
	public int getCantRondas() throws RemoteException {
		return this.cantRondas;
	}

	@Override
	public void agregarJugador(String name) throws RemoteException {
		if (miEstado == SETEANDO) {
			if (this.jugadores.size() == 1 && this.jugadores.get(0).getNombre().equals(name))
				notificarObservadores(EventosChinchon.MISMO_NOMBRE);
			else {
			if (this.jugadores.size() < 2) {
				this.jugadores.add(new Jugador(name));
				notificarObservadores(EventosChinchon.JUGADOR_AGREGADO);
			}
			else
				notificarObservadores(EventosChinchon.JUGADORES_MAXIMOS);
		}}}
		


	@Override
	public void iniciarJuego() throws RemoteException {
		if (this.jugadores.size() == 2) {
		miEstado = JUGANDO;
		this.mazo.mezclar();
		repartir();
		jugadorActual = 0;
		this.notificarObservadores(EventosChinchon.JUEGO_INICIADO);
		}
		else {
			if (this.jugadores.size() == 1) {
				notificarObservadores(EventosChinchon.FALTA_UNO);
			}
			else {
				notificarObservadores(EventosChinchon.FALTAN_DOS);
			}
		}
	}
	
	@Override
	public void cambiarJugador() throws RemoteException {
		if (this.miEstado != FIN_MANO && this.miEstado != FIN_JUEGO && this.jugadores.get(jugadorActual).getCantCartas() == 7) {
		jugadorActual++;
		if (jugadorActual == jugadores.size())
			jugadorActual = 0;
		this.jugadores.get(jugadorActual).ordenarPorNumero();
		notificarObservadores(EventosChinchon.TURNO_CAMBIADO); }
	}

	@Override
	public Jugador getJugadorActual() throws RemoteException {
		if (jugadorActual < jugadores.size()) 
				return jugadores.get(jugadorActual);
			else
				return jugadores.get(jugadorActual + 1);
		}
		
	@Override
	public Carta mostrarDescarte() throws RemoteException {
		return this.descarte.getTope();
	}

	@Override
	public int getEstado() throws RemoteException {
		return this.miEstado;
	}

	@Override
	public void robarCarta() throws RemoteException {
		if (this.jugadores.get(jugadorActual).getCantCartas() == 8)
			notificarObservadores(EventosChinchon.CARTAS_MAXIMAS);
		else {
			if (mazo.cartas.size() == 0) {
				girarMazo();
			}
			this.jugadores.get(jugadorActual).añadirCarta(mazo.darCarta());
			this.jugadores.get(jugadorActual).ordenarPorNumero();
			notificarObservadores(EventosChinchon.CARTA_ROBADA);
		}
	}
	
	@Override
	public List<Carta> mostrarCartas() throws RemoteException{
		this.jugadores.get(jugadorActual).ordenarPorNumero();
		return this.jugadores.get(jugadorActual).getCartas();
	}

	@Override
	public void robarCartaDescarte() throws RemoteException {
		if (this.jugadores.get(jugadorActual).getCantCartas() == 8)
			notificarObservadores(EventosChinchon.CARTAS_MAXIMAS);
		else {
			if (this.descarte.getCantCartas() == 0)
				notificarObservadores(EventosChinchon.DESCARTE_VACIO);
			else {
				this.jugadores.get(jugadorActual).añadirCarta(descarte.darCarta());
				notificarObservadores(EventosChinchon.CARTA_ROBADA_DESCARTE);
			 }}
	}

	@Override
	public void tiraCarta(int numero) throws RemoteException {
		if (this.jugadores.get(jugadorActual).getCantCartas() == 8) {
		this.descarte.añadirCarta(this.jugadores.get(jugadorActual).tirarCarta(numero-1));
		notificarObservadores(EventosChinchon.CARTA_TIRADA); }
		else
			notificarObservadores(EventosChinchon.DEBE_ROBAR);
	}

	@Override
	public int getCartas() throws RemoteException {
		return this.jugadores.get(jugadorActual).getCantCartas();
	}

	@Override
	public int getCartasDescarte() throws RemoteException {
		return this.descarte.getCantCartas();
	}

	@Override
	public void limpiaCombinaciones() throws RemoteException {
		this.jugadores.get(jugadorActual).combinacion1.clear();
		this.jugadores.get(jugadorActual).combinacion2.clear();
	}
	
	@Override
	public void reiniciaJuego() throws RemoteException {
		topFive.agregarAlTop(jugadores.get(jugadorActual));
		persistir.guardarObjetos("top.dat", topFive);
		jugadores.clear();
		miEstado = SETEANDO;
		descarte = new Descarte();
		mazo = new Mazo();
		this.cantRondas = 0;
	}
	
	@Override
	public List<Jugador> getCantidadJugadores() throws RemoteException {
		return this.jugadores;
	}

	@Override
	public void cargar(IJuego juego) throws RemoteException {
		this.descarte = juego.getDescarte();
		this.mazo = juego.getMazo();
		this.jugadores = juego.getCantidadJugadores();
		this.miEstado = juego.getEstado();
		this.jugadorActual = juego.getJugadorEnTurno();
		this.cantRondas = juego.getCantRondas();
		this.notificarObservadores(EventosChinchon.CARGAR_JUEGO);
	}
	
	@Override
	public void verTop() throws RemoteException {
		topFive = (Top) persistir.cargarObjetos("top.dat");
		this.notificarObservadores(EventosChinchon.VER_TOP);
	}
	
	@Override
	public ArrayList<Jugador> getTop() throws RemoteException {
		return topFive.getTop();
	}

	public static Juego getInstancia() {
		if (instancia == null)
			instancia = new Juego();
		return instancia;
	}
	
	public int getJugadorEnTurno() throws RemoteException {
		return this.jugadorActual;
	}
	
	public Mazo getMazo() throws RemoteException{
		return this.mazo;
	}
	
	public Descarte getDescarte() throws RemoteException{
		return this.descarte;
	}
	
}	
