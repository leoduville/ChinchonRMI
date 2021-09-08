package ar.edu.unlu.Chinchon.Vista;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class VentanaReglas extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel label1;
    
    private JTextArea area;
    
    private JScrollPane panel;
    
    
    public VentanaReglas() {
        this.setTitle("Reglas de juego");
        iniciar();
        alinear();
        setSize(530,350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void alinear() {
        setLayout(null);
        label1.setBounds(160,0,500,50);
        getContentPane().add(label1);
        panel.setBounds(0,50,530,200);
        getContentPane().add(panel);
    }
    
    public void iniciar() {
        label1 = new JLabel("Chinchon");
        label1.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
        area = new JTextArea();
        area.setEditable(false);
        area.setText("Objetivo del juego\r\n" 
                + "El objetivo en cada una de las manos o repartos de cartas para cada jugador es sumar el menor número de puntos posible.\r\n" 
                + "Para ello deberá ligar sus cartas antes que sus rivales.\r\n" 
                + "Repartidas siete cartas a cada jugador, deberá descubrir la siguiente del mazo y dejarla visible en el centro del tapete,\r\n" 
                + "al lado del resto del mazo, que dejará boca abajo.\r\n"
                + "\r\n"
                + "Una vez tomada una de estas cartas (tras lo cual tendrá 8 cartas en la mano) deberá inmediatamente lanzar una de ellas,\r\n"  
                + "la que desee, descubierta, al centro del tapete, sobre las que pudiera haber allí descubiertas "
                + "En los turnos sucesivos todos los jugadores deberán repetir este movimiento de tomar una de las cartas superiores,\r\n"
                + "bien del mazo, cubiertas, o bien la carta superior del montón que se vaya formando, descubiertas, lanzando posteriormente una descubierta.\r\n" 
                + "Si las cartas del mazo se agotan antes de finalizar la mano, se toman las cartas lanzadas y, una vez barajadas, se forma el mazo con ellas." 
                + "\r\n"
                + "En cada mano, cada jugador tiene como objetivo intentar ligar sus cartas \r\n"
                + "Se entiende por ligar las cartas hacer grupos de al menos tres cartas unidas por uno de los criterios siguientes:.\r\n" 
                + "\r\n" 
                + "Cartas del mismo índice.\r\n"
                + "Cartas que forman una escalera (un grupo de cartas del mismo palo con índices correlativos).\r\n"
                + "\r\n"
                + "Un jugador en cualquiera de sus turnos, en el momento de deshacerse de una carta, puede cerrar la mano si tuviera todas las cartas ligadas\r\n"
                + "(sea en dos grupos de tres y cuatro cartas o en un único grupo de 7 cartas) \r\n"
                + "o si sólo le quedara una carta sin ligar y ésta tuviera un valor igual o inferior a 4 "
                + "Para cerrar la mano echará la carta de la que se va a deshacer, sobre el resto de cartas desechadas, pero vuelta hacia abajo, y seguidamente mostrará su jugada." 
                + "\r\n"
                + "En el Chinchón, una vez que la mano ha sido cerrada por un jugador, el objetivo de los demás es intentar sumar la menor cantidad posible de puntos.\r\n"
                + "Si quien cerró tenía todas sus cartas ligadas obtendrá una puntuación negativa en base a las siguientes reglas:\r\n"
                + "\r\n"
                + "Con dos grupos de cartas (uno de tres y otro de cuatro) será -10.\r\n"
                + "Con una escalera de siete cartas con dos comodines será -25.\r\n"
                + "Con una escalera de siete cartas con un comodín será -50.\r\n"
                + "Con una escalera de siete cartas sin comodines (chinchón) ganará la partida directamente.\r\n"
                + "Cuando un jugador supera el número de puntos previamente establecido como límite de la partida, es eliminado de la misma\r\n"
                + "\r\n"
                + "Final del juego\r\n" 
                + "En el Chinchón vence la partida aquel jugador que hubiera conseguido eliminar a todos los demás jugadores.");
        panel = new JScrollPane(area);
}
}