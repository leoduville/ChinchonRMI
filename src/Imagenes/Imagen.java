package Imagenes;


import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;


public class Imagen extends javax.swing.JPanel  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ImageIcon image;
	private String archivo;
	
	public Imagen(String archivo,int ancho,int largo) {
		super();
		this.setSize(ancho, largo );
		this.setPreferredSize( new Dimension( ancho, largo ) );
		this.setArchivo(archivo);
	}
	
	public String getArchivo() {
		return archivo;
	}
	
	public void setArchivo(String archivo) {
		this.archivo = archivo;
		String path = System.getProperty("user.dir");
		
		archivo = path + "/src/Imagenes/" + archivo + ".jpg";
		try {
			image = new ImageIcon(archivo);
		} catch (Exception ex) {
			System.out.println(" no encontre el archivo " + archivo);
		}
		this.repaint();
	}


	@Override
	protected void paintComponent(Graphics grafico) {
		super.paintComponent(grafico);
		grafico.drawImage(image.getImage(), 0, 0, this);
	}

}
