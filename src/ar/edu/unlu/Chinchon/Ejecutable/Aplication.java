package ar.edu.unlu.Chinchon.Ejecutable;

import java.awt.EventQueue;

import ar.edu.unlu.Chinchon.Controlador.Controlador;
import ar.edu.unlu.Chinchon.Vista.IVista;
import ar.edu.unlu.Chinchon.Vista.VentanaJuego;
import ar.edu.unlu.Chinchon.Vista.VistaConsola;

public class Aplication {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			//Mazo mazo = new Mazo();
			//Descarte descarte = new Descarte();
			//Juego juego = new Juego();
			Controlador controlador = new Controlador();
			IVista vista = new VentanaJuego(controlador);
			
			//vista.setControlador(controlador);
			try {
				vista.iniciar();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//VentanaJuego vd = new VentanaJuego();
			//vd.setVisible(true);
			//vd2.setVisible(true);
			//VistaConsola vc = new VistaConsola();
			
		//	vd.setControlador(controlador);
		//	vd2.setControlador(controlador);
			/*try {
				vc.comenzarJuego();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*vd.setControlador(controlador);
			VistaConsola vista = new VistaConsola();
				try {
					Controlador controlador = new Controlador(vista, juego);
					vista.setControlador(controlador);
					vista.comenzarJuego();
				} catch (Exception e) {
					e.printStackTrace();
				}
			*/
			
		
			}
		});
	}
}

