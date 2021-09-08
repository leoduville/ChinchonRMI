package ar.edu.unlu.Chinchon.Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mazo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Carta> cartas = new ArrayList<>();
	private int cantCartas;
	
	public Mazo() {
		llenarMazo();
		this.cantCartas = cartas.size();
	}
	
	public List<Carta> getCartas(){
		return this.cartas;
	}
	
	public void llenarMazo() {
		int i;
		for (i = 1;i <= 10; i++) {
				cartas.add(new Carta(i,Palo.BASTO));
				cartas.add(new Carta(i,Palo.ESPADA));
				cartas.add(new Carta(i,Palo.ORO));
				cartas.add(new Carta(i,Palo.COPA));
			}
		}
	
	public void vaciar() {
		this.cartas.clear();
		}
	
	public void mezclar() {
		Collections.shuffle(cartas);
	}
	
	public Carta darCarta() {
		Carta carta = cartas.get(cartas.size() - 1);
		cartas.remove(cartas.size() - 1);
		return carta;
	}

	public int getCantCartas() {
		return cantCartas;
	}


}
