package ar.edu.unlu.Chinchon.Juego;

public interface Observador {
	
	void notificar (EventosChinchon evento);
	void notificarMensaje (String string);
	void notificarError (String string);
}
