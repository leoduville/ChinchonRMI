package ar.edu.unlu.Chinchon.Controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;


import ar.edu.unlu.Chinchon.Juego.Carta;
import ar.edu.unlu.Chinchon.Juego.EventosChinchon;
import ar.edu.unlu.Chinchon.Juego.IJuego;
import ar.edu.unlu.Chinchon.Juego.IJugador;
import ar.edu.unlu.Chinchon.Juego.Juego;
import ar.edu.unlu.Chinchon.Juego.Jugador;
import ar.edu.unlu.Chinchon.Juego.Persistir;
import ar.edu.unlu.Chinchon.Vista.IVista;
import ar.edu.unlu.Chinchon.Vista.VentanaJuego;
import ar.edu.unlu.Chinchon.Vista.VistaConsola;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public class Controlador implements IControladorRemoto{
	
	private Persistir persistir = new Persistir();
	private IJuego miChinchon;
	private IVista miVista;
	private String jugador;
	
	public Controlador() {
		}
	
	
	public void AgregarJugador(String name) {
		try {
			if (jugador == null) {
				miChinchon.agregarJugador(name);
				if (getListaJugadores().size() == 1)
					this.jugador = name;
				else
					if (getListaJugadores().size() == 2 && !getListaJugadores().get(0).getNombre().equals(name))
						this.jugador = name;
				}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
	
	public String getGanador() {
		try {
			return this.miChinchon.getGanador().getNombre();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void iniciarJuego() {
		try {
			miChinchon.iniciarJuego();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cambiaTurno() {
		try {
			miChinchon.cambiarJugador();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public IJugador[] getJugadores() {
		try {
			return miChinchon.getJugadores();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void repartirCartas() {
		try {
			miChinchon.repartir();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public int cantidadCartas() {
		try {
			return miChinchon.getCartas();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public Carta mostrarDescarte() {
		try {
			return miChinchon.mostrarDescarte();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public int getEstado() {
		try {
			return miChinchon.getEstado();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public Jugador getJugadorEnTurno() {
		try {
			return miChinchon.getJugadorActual();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void robaCarta() {
		try {
			miChinchon.robarCarta();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Carta> mostrarCartas() {
		try {
			return miChinchon.mostrarCartas();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void robaCartaDescarte() {
		try {
			miChinchon.robarCartaDescarte();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tiraCarta(int numero) {
		try {
			miChinchon.tiraCarta(numero);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void terminaMano() {
		try {
			miChinchon.terminarMano();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean checkeaMano() {
		try {
			return miChinchon.checkeaMano();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void limpiarCombinaciones() {
		try {
			miChinchon.limpiaCombinaciones();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getCartasDescarte() {
		try {
			return miChinchon.getCartasDescarte();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public void reiniciaMano() {
		try {
			miChinchon.reiniciarMano();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int obtieneRondas() {
		try {
			return miChinchon.getCantRondas();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public void reiniciaJuego() {
		try {
			miChinchon.reiniciaJuego();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void guardarPartida(String ruta) {
		try {
			this.persistir.guardarObjetos(ruta, this.miChinchon);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public void cargarPartida(String ruta) {
		 try {
			this.miChinchon.cargar( (IJuego) this.persistir.cargarObjetos(ruta));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void verTop() {
		try {
			miChinchon.verTop();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Jugador> getTop() {
		try {
			return miChinchon.getTop();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private boolean manejaTurno() throws RemoteException {
		if (miChinchon.getJugadorActual().getNombre().equals(this.jugador))
				return true;
		return false;
	}
	
	@Override
	public void actualizar(IObservableRemoto modelo, Object cambio) throws RemoteException {
		if (cambio instanceof EventosChinchon) {
			EventosChinchon indice = (EventosChinchon) cambio;
			switch (indice) {
			case JUGADOR_AGREGADO:
				miVista.actualizaPuntaje();
				break;
			case FALTA_UNO:
				miVista.notificarError("Debe cargar un jugador mas para iniciar la partida");
				break;
			case FALTAN_DOS:
				miVista.notificarError("Debe cargar los dos jugadores para iniciar la partida");
				break;
			case MISMO_NOMBRE:
				miVista.notificarError("Ya existe un jugador con el mismo nombre");
				break;
			case JUEGO_INICIADO:
				if (manejaTurno())
					miVista.juegoIniciado();
				else {
					miVista.muestraMesa();
					miVista.turno();
					miVista.deshabilitaBotones();
					miVista.mostrarCartasJugadorNoActual();
				}
				break;
			case DESCARTE_VACIO:
				if (manejaTurno())
					miVista.notificarError("Descarte vacio");
				break;
			case JUGADORES_MAXIMOS:
				miVista.notificarError("Jugadores maximos cargados");
				break;
			case CARTAS_MAXIMAS:
				if (manejaTurno())
					miVista.notificarError("Cartas maximas en mano");
				break;
			case DEBE_ROBAR:
				if (manejaTurno())
				miVista.notificarError("Debe robar una carta para tirar");
				break;
			case CARTA_ROBADA:
				if (manejaTurno())
					miVista.actualizarCartas();
				break;
			case CARTA_ROBADA_DESCARTE:
				if (manejaTurno())
					miVista.robaCartaDescarte();
				break;
			case CARTA_TIRADA:
				if (manejaTurno()) {
				miVista.actualizarCartas();
				miVista.actualizaPuntaje();
				}
				if (getCartasDescarte() != 0)
					miVista.actualizaDescarte();
				else
					miVista.descarteVacio();
				break;
			case TURNO_CAMBIADO:
				if (manejaTurno())
					miVista.cambiaTurno();
				else
					miVista.limpiaTurno();
				break;
			case PUNTOS_CAMBIADOS:
				miVista.reiniciarMano();
				if (manejaTurno())
					miVista.actualizarCartas();
				else
					miVista.mostrarCartasJugadorNoActual();
				break;
			case FIN_JUEGO:
				this.jugador = null;
				miVista.finJuego();
				break;
			case CARGAR_JUEGO:
				miVista.cargaJuego();
				break;
			case VER_TOP:
				miVista.verTop();
				break;
			}
		}
	}
		

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) {
		this.miChinchon = (IJuego) modeloRemoto;	
	}

	public void setVista(IVista vista) {
		this.miVista = vista;
	}
	
	public List<Jugador> getListaJugadores() throws RemoteException {
		return this.miChinchon.getCantidadJugadores();
	} 


	
}
