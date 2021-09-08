package ar.edu.unlu.Chinchon.Vista;


import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import ar.edu.unlu.Chinchon.Juego.Jugador;


public class TablaTop extends JFrame{
	private JPanel contentPane;
    private JTable table;
    private ArrayList<Jugador> jugadores;

	public TablaTop(ArrayList<Jugador> list) {
        this.jugadores = list; 
        setVisible(true);
        setTitle("Tabla de estadisticas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 604, 347);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 560, 227);
        getContentPane().add(scrollPane);

        String[] columnas = { "Jugador", "Victorias" };
        final DefaultTableModel modelo;
        modelo = new DefaultTableModel(null, columnas);


        table = new JTable(modelo) {

        };

        table.setBounds(26, 26, 463, 332);
        scrollPane.setViewportView(table);

        for (Jugador j: list) {
            Object[] nuevafila = { j.getNombre(), j.getVictorias() };
            modelo.addRow(nuevafila);
        }

    }
}
