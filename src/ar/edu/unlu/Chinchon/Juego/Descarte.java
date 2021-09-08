package ar.edu.unlu.Chinchon.Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Descarte implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Carta> cartas = new ArrayList<>();
	private int cantCartas;
	private Carta tope;
	
	public Descarte() {
		this.cantCartas = 0;
	}
	
	public void setTope() {
		if (cartas.size() > 0) 
			tope = cartas.get(cartas.size()-1);
		else
			tope = null;
 	}
	
	public void añadirCarta(Carta carta) {
		this.cartas.add(carta);
		cantCartas++;
		setTope();
	}

	public Carta darCarta() {
		if (cartas.size() > 0) {
		Carta carta = tope;
		cartas.remove(tope);
		cantCartas--;
		setTope();
		return carta;
		} else
			return null;
	}
	
	public Carta getTope() {
		return this.tope;
	}
	
	public void vaciar() {
		this.cartas.clear();
		this.cantCartas = 0;
		setTope();
	}
	
	public int getCantCartas() {
		return this.cantCartas;
	}
}
