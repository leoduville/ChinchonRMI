package ar.edu.unlu.Chinchon.Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Jugador implements IJugador, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Carta> cartas = new ArrayList<>();
	protected int cantCartas;
	protected int puntos;
	private String nombre;
	protected boolean turno;
	List<Carta> combinacion1 = new ArrayList<>();
	List<Carta> combinacion2 = new ArrayList<>();
	private int victorias = 0;
			
	public Jugador(String nombre){
		this.nombre = nombre;
		this.puntos = 0;
		this.cantCartas = 0;
	}
	
	@Override
	public int getPuntos() {
		return puntos;
	}
	
	public int getCantCartas() {
		return cantCartas;
	}
	
	public boolean getTurno() {
		return this.turno;
	}
	
	public void limpiarMano() {
		this.cartas.clear();
		cantCartas = 0;
		this.combinacion1.clear();
		this.combinacion2.clear();
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Carta> getCartas(){
		return this.cartas;
	}
	
	public void añadirCarta(Carta carta) {
		this.cartas.add(carta);
		this.ordenarPorNumero();
		cantCartas++;
		}


	public void aumentarPuntos(int puntos) {
		this.puntos += puntos;
	}
	
	public Carta tirarCarta(int n) {
		cantCartas--;
		Carta carta = this.cartas.get(n);
		this.cartas.remove(n);
		return carta;
		}
	
	public void ordenarPorPalo() {
		List<Carta> mano = new ArrayList<>();
		while(cartas.size() > 0) {
			int pos = 0;
			Carta c = cartas.get(0);
			for (int i = 1; i < cartas.size(); i++) {
				Carta c1 = cartas.get(i);
				if(c1.getPalo().hashCode() < c.getPalo().hashCode() || 
				(c1.getPalo().hashCode() == c.getPalo().hashCode()) && c1.getNumero() < c.getNumero()) {
					pos = i;
					c = c1;
				}
			}
			cartas.remove(pos);
			mano.add(c);
		}
		cartas = mano;
	}
	
	
	 public void ordenarPorNumero() {
			List<Carta> mano = new ArrayList<>();
			while(cartas.size() > 0) {
				int pos = 0;
				Carta c = cartas.get(0);
				for (int i = 1; i < cartas.size(); i++) {
					Carta c1 = cartas.get(i);
					if(c1.getNumero() < c.getNumero()) {
						pos = i;
						c = c1;
					}
				}
				cartas.remove(pos);
				mano.add(c);
			}
			cartas = mano;
	 }
	
	@Override
	 public String getNombre() {
		return nombre;
	}
	
	public List<Carta> getCombinacion1(){
		return this.combinacion1;
	}
	
	public List<Carta> getCombinacion2(){
		return this.combinacion2;
	}

	public int getVictorias() {
		return this.victorias;
	}
	
	public void aumentaVictoria() {
		this.victorias++;
	}
	
		}
		
	
