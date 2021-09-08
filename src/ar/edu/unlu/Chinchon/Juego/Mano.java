package ar.edu.unlu.Chinchon.Juego;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mano implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public boolean checkearTrioEscalera(Jugador jugador) {
		jugador.ordenarPorPalo();
		ArrayList<Carta> cloneCards = new ArrayList<>(jugador.getCartas());
		for (int i = 0; i < cloneCards.size() - 2; i++) {
			if (cloneCards.get(i).getPalo() == cloneCards.get(i + 1).getPalo()
					&& cloneCards.get(i).getPalo() == cloneCards.get(i + 2).getPalo()
					&& cloneCards.get(i).getNumero() == cloneCards.get(i + 2).getNumero() - 2) {
				if (jugador.combinacion1.size() == 0) {
					jugador.combinacion1.add(cloneCards.get(i));
					jugador.combinacion1.add(cloneCards.get(i + 1));
					jugador.combinacion1.add(cloneCards.get(i + 2));
					return true;
				} else {
					if (!jugador.combinacion1.contains(cloneCards.get(i))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 1))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 2)) &&
						!jugador.combinacion2.contains(cloneCards.get(i))
						&& !jugador.combinacion2.contains(cloneCards.get(i + 1))
						&& !jugador.combinacion2.contains(cloneCards.get(i + 2))) {
						jugador.combinacion2.add(cloneCards.get(i));
						jugador.combinacion2.add(cloneCards.get(i + 1));
						jugador.combinacion2.add(cloneCards.get(i + 2));
						return true;
					} else {
						if (jugador.combinacion2.contains(cloneCards.get(i))
						&& jugador.combinacion2.contains(cloneCards.get(i + 1))
						&& jugador.combinacion2.contains(cloneCards.get(i + 2))) {
							return true;
						}
					}
				}

			}
		}
		return false;
	}
	
	public boolean checkear4(Jugador jugador) {
		jugador.ordenarPorNumero();
		ArrayList<Carta> cloneCards = new ArrayList<>(jugador.getCartas());
		for (int i = 0; i < cloneCards.size() - 3; i++) {
			if (cloneCards.get(i).getNumero() == cloneCards.get(i + 1).getNumero()
					&& cloneCards.get(i).getNumero() == cloneCards.get(i + 2).getNumero()
					&& cloneCards.get(i).getNumero() == cloneCards.get(i + 3).getNumero()) {
				if (jugador.combinacion1.size() == 0) {
					jugador.combinacion1.add(cloneCards.get(i));
					jugador.combinacion1.add(cloneCards.get(i + 1));
					jugador.combinacion1.add(cloneCards.get(i + 2));
					jugador.combinacion1.add(cloneCards.get(i + 3));
					return true;
				} else {
					if (!jugador.combinacion1.contains(cloneCards.get(i))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 1))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 2))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 3))) {
						jugador.combinacion2.add(cloneCards.get(i));
						jugador.combinacion2.add(cloneCards.get(i + 1));
						jugador.combinacion2.add(cloneCards.get(i + 2));
						jugador.combinacion2.add(cloneCards.get(i + 3));
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean checkearEscalera4(Jugador jugador) {
		jugador.ordenarPorPalo();
		ArrayList<Carta> cloneCards = new ArrayList<>(jugador.getCartas());
		for (int i = 0; i < cloneCards.size() - 3; i++) {
			if (cloneCards.get(i).getPalo() == cloneCards.get(i + 1).getPalo()
					&& cloneCards.get(i).getPalo() == cloneCards.get(i + 2).getPalo()
					&& cloneCards.get(i).getPalo() == cloneCards.get(i + 3).getPalo()
					&& cloneCards.get(i).getNumero() == cloneCards.get(i + 3).getNumero() - 3
					&& i >= 2 && cloneCards.get(i).getNumero() != cloneCards.get(i-1).getNumero()
					&& cloneCards.get(i).getNumero() != cloneCards.get(i-2).getNumero()
					) {
				if (jugador.combinacion1.size() == 0) {
					jugador.combinacion1.add(cloneCards.get(i));
					jugador.combinacion1.add(cloneCards.get(i + 1));
					jugador.combinacion1.add(cloneCards.get(i + 2));
					jugador.combinacion1.add(cloneCards.get(i + 3));
					return true;
				} else {
					if (!jugador.combinacion1.contains(cloneCards.get(i))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 1))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 2))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 3))) {
						jugador.combinacion2.add(cloneCards.get(i));
						jugador.combinacion2.add(cloneCards.get(i + 1));
						jugador.combinacion2.add(cloneCards.get(i + 2));
						jugador.combinacion2.add(cloneCards.get(i + 3));
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean esChinchon(Jugador jugador) {
		Carta carta = jugador.getCartas().get(0);
		for (int i = 1; i <= 6; i++) {
			if (carta.getPalo() != jugador.getCartas().get(i).getPalo()) {
				return false;
			}
		}
		if (carta.getNumero() != jugador.getCartas().get(6).getNumero() - 6) {
			return false;
		}
		return true;
	}


	public Carta buscarCartaSobrante(Jugador jugador) {
		for (int i = 0; i < jugador.getCantCartas(); i++) {
			if (!jugador.getCombinacion1().contains(jugador.getCartas().get(i))
					&& !jugador.getCombinacion2().contains(jugador.getCartas().get(i))) {
					return jugador.getCartas().get(i);
			}
		}
		return null;
	}


	public boolean checkearTrio(Jugador jugador) {
		jugador.ordenarPorNumero();
		ArrayList<Carta> cloneCards = new ArrayList<>(jugador.getCartas());
		for (int i = 0; i < cloneCards.size() - 2; i++) {
			if (cloneCards.get(i).getNumero() == cloneCards.get(i + 1).getNumero()
					&& cloneCards.get(i).getNumero() == cloneCards.get(i + 2).getNumero())
					{
				if (jugador.combinacion1.size() == 0) {
					jugador.combinacion1.add(cloneCards.get(i));
					jugador.combinacion1.add(cloneCards.get(i + 1));
					jugador.combinacion1.add(cloneCards.get(i + 2));
					return true;
				} else {
					if (!jugador.combinacion1.contains(cloneCards.get(i))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 1))
							&& !jugador.combinacion1.contains(cloneCards.get(i + 2)) && 
						((!jugador.combinacion2.contains(cloneCards.get(i))
							&& !jugador.combinacion2.contains(cloneCards.get(i + 1))
							&& !jugador.combinacion2.contains(cloneCards.get(i + 2))))){
						jugador.combinacion2.add(cloneCards.get(i));
						jugador.combinacion2.add(cloneCards.get(i + 1));
						jugador.combinacion2.add(cloneCards.get(i + 2));
						return true;
					} else {
						if (jugador.combinacion2.contains(cloneCards.get(i))
							&& jugador.combinacion2.contains(cloneCards.get(i + 1))
							&& jugador.combinacion2.contains(cloneCards.get(i + 2))) {
							return true;
					} else {
						if (i != 0) {
						if (cloneCards.get(i).getNumero() == cloneCards.get(i+1).getNumero()
							&& cloneCards.get(i).getNumero() == cloneCards.get(i+2).getNumero()
							&& cloneCards.get(i).getNumero() == cloneCards.get(i-1).getNumero()
							&& jugador.combinacion1.get(0).getNumero() == jugador.combinacion1.get(1).getNumero()+1){
							jugador.combinacion2.add(cloneCards.get(i-1));
							jugador.combinacion2.add(cloneCards.get(i+1));
							jugador.combinacion2.add(cloneCards.get(i+2));
							return true;
						} else {
							if (i < 4) {
							if (cloneCards.get(i).getNumero() == cloneCards.get(i+3).getNumero() &&
								!jugador.combinacion1.contains(cloneCards.get(i))	){
								jugador.combinacion2.add(cloneCards.get(i));
								jugador.combinacion2.add(cloneCards.get(i+1));
								jugador.combinacion2.add(cloneCards.get(i+3));
								return true;
							}}
						}
					}
				}}
				}
			}}
				
		return false;
	}
	

	
	public boolean checkearMano(Jugador jugador) {
		boolean bandera = false;
		boolean Escalera4 = false;
		boolean Iguales4 = false;
		boolean bandera3 = false;
		List<Carta> combinacion = new ArrayList<Carta>();
		List<Carta> combinacion2 = new ArrayList<Carta>();
		List<Carta> combinacion3 = new ArrayList<Carta>();
		boolean Trio = false;
		
		if (esChinchon(jugador))
			return true;
		
		if (checkearEscalera4(jugador)) {
			Escalera4 = true;
			if (checkearTrio(jugador))
				return bandera = true;
			if (checkearTrioEscalera(jugador))
				return bandera = true;
		}
		//jugador.combinacion1.clear();
		if (checkear4(jugador)) {
			Iguales4 = true;
			if (checkearTrioEscalera(jugador))
				return bandera = true;
			if (checkearTrio(jugador))
				return bandera = true;
		}
		//jugador.combinacion1.clear();
		if (Escalera4) {
			combinacion.addAll(jugador.combinacion1);
			jugador.combinacion1.clear();
		}
		if (Iguales4) {
			combinacion2.addAll(jugador.combinacion1);
			jugador.combinacion1.clear();
		}
		if(checkearTrio(jugador)) {
			Trio = true;
			if (checkearTrioEscalera(jugador)) {
				bandera3 = true;
				if (buscarCartaSobrante(jugador).getNumero() <= 4)
				return bandera = true;}
			if (checkearTrio(jugador)) {
				bandera3 = true;
				if (buscarCartaSobrante(jugador).getNumero() <= 4)
				return bandera = true;}
		}
		
		if (Trio && !bandera3) {
			combinacion3.addAll(jugador.combinacion1);
			jugador.combinacion1.clear();
		}
		
		if (checkearTrioEscalera(jugador)) {
			if (checkearTrioEscalera(jugador)) {
				bandera3 = true;
				if (buscarCartaSobrante(jugador).getNumero() <= 4)
				return bandera = true;}
			if (checkearTrio(jugador)) {
				bandera3 = true;
				if (buscarCartaSobrante(jugador).getNumero() <= 4)
				return bandera = true;}
		}
		
		if (!bandera3 && Escalera4)
			jugador.combinacion1 = combinacion;
		if (!bandera3 && Iguales4)
			jugador.combinacion1 = combinacion2;
		if (!bandera3 && Trio) 
			jugador.combinacion1 = combinacion3;

	return bandera;
	}
	
	
	public boolean checkearEscalera(List<Carta> cartas) {
		if (cartas.get(0).getPalo() == cartas.get(1).getPalo()
				&& cartas.get(0).getNumero() == cartas.get(1).getNumero()-1) {
			return true;
		}
		return false;
	}

	public boolean checkearIguales(List<Carta> cartas) {
		if (cartas.get(0).getPalo() != cartas.get(1).getPalo()
				&& cartas.get(0).getNumero() == cartas.get(1).getNumero()) {
			return true;
		}
		return false;
	}

	public int calcularPuntos(List<Carta> cartas) {
		int resultado = 0;
		for (Carta carta : cartas) {
			resultado += carta.getNumero();
		}

		return resultado;
	}
}
