package vistaGUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class CartaClickeable extends CartaGrafica implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean hasclicked1 = false;
	private Border blackline = BorderFactory.createLineBorder(Color.black);
	private Clickeable manejador;

	public CartaClickeable(String archivo, Clickeable manejador) {
		super(archivo);
		this.manejador = manejador;
		this.addMouseListener(this);
	}

	public void mouseClicked(MouseEvent me) {
		if (!hasclicked1) {
			hasclicked1 = true;
			this.setBorder(blackline);
			
		} else {
			reset();
		}
		manejador.MouseClicked(this);
	}

	public boolean isClickeada() {
		return hasclicked1;
	}

	public void reset() {
		this.hasclicked1 = false;
		this.setBorder(null);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



}
