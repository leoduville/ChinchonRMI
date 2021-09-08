package ar.edu.unlu.Chinchon.Juego;

import java.io.Serializable;

public class Carta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int numero;
	private Palo palo;
	
	public Carta(int numero, Palo palo) {
		this.numero = numero;
		this.palo = palo;
	}
	
	public int getNumero() {
		return this.numero;
	}
	
	public Palo getPalo() {
		return this.palo;
	}
	
	public String mostrarCarta() {
		if (this.getPalo() != null ) {
			return (this.getNumero() + "-" + this.getPalo());
		} else {
			return null;
		}
	}
	
}
