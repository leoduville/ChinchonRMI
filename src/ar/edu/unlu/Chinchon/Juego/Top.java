package ar.edu.unlu.Chinchon.Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Top implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<Jugador> topFive;

	public Top() {
		topFive = new ArrayList<Jugador>();

	/*	topFive.add(new Jugador(""));
		topFive.add(new Jugador(""));
		topFive.add(new Jugador(""));
		topFive.add(new Jugador(""));
		topFive.add(new Jugador("")); */
	}

	protected void agregarAlTop(Jugador jugador) {
		boolean bandera = false;
		
		for (int i = 0; i < topFive.size(); i++) {
			if (jugador.getNombre().equals(topFive.get(i).getNombre())) {
				topFive.get(i).aumentaVictoria();
				bandera = true;
			}
		}
		
		if (!bandera) {
			jugador.aumentaVictoria();
			topFive.add(jugador);
		

		Collections.sort(topFive, new Comparator<Jugador>(){

			@SuppressWarnings("deprecation")
			@Override
			public int compare(Jugador o1, Jugador o2) {
				return new Integer(o2.getVictorias()).compareTo(new Integer(o1.getVictorias()));
			}	
		});
		}
	}
		
	protected ArrayList<Jugador> getTop() {
		return this.topFive;
	}

}

