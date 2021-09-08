package ar.edu.unlu.Chinchon.Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ar.edu.unlu.Chinchon.Controlador.Controlador;
import ar.edu.unlu.Chinchon.Juego.IJugador;
import ar.edu.unlu.Chinchon.Juego.Juego;
import ar.edu.unlu.Chinchon.Juego.Jugador;
import net.miginfocom.swing.MigLayout;
import vistaGUI.CartaClickeable;
import vistaGUI.CartaGrafica;
import vistaGUI.Clickeable;

public class VentanaJuego extends JFrame implements Clickeable, IVista{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final int BTN_AGREGAR = 1;
	protected static final int BTN_INICIAR = 2;
	protected static final int BTN_MOSTRARJ = 3;
	protected static final int BTN_ROBAR = 4;
	protected static final int BTN_TIRAR = 5;
	protected static final int BTN_ROBARDESCARTE = 6;
	protected static final int BTN_TERMINARMANO = 7;
	protected static final int BTN_VERREGLAS = 8;
	protected static final int BTN_CARGARPARTIDA = 9;
	protected static final int BTN_GUARDARPARTIDA = 10;
	protected static final int BTN_VERTOP5 = 11;
	private JMenu mnArchivo;
	private JButton btnAgregarJugador;
	private JMenuBar menuGeneral;
	private Controlador miControlador;
	private JButton btnRobar;
	private JButton btnTirar;
	private JButton btnrobarDescarte;
	private JTextField txtJugadorActual;
	private JTextField textField;
	private JPanel panelPrincipal;
	private JPanel panelContenedorCartas;
	private JButton btnIniciarJuego;
	private CartaGrafica cartaDescarte;
	private CartaClickeable[] cartasJugador;
	private JPanel areaJugadores;
	private JPanel panelBotones;
	private JTable tblJugadores;
	private JScrollPane scrollPane;
	private JMenuItem mntmVerReglas;
	private JMenuItem mntmCargarPartida;
	private JMenuItem mntmGuardarPartida;
	private JMenuItem mntmVerTop;
	private JMenu mnReglas;
	private JMenuItem mnSalir;
	private JLabel JLmensajes;
	private JPanel panelCartas;
	private TablaTop tablaTop;
	private FondoPanel mesa;
	private GridBagConstraints gbc_descarte;
	private JTextField txtMensajes;
	private CartaGrafica cartaReverso;
	private GridBagConstraints gbc_reverso;
	
	public VentanaJuego(Controlador controlador) {
		this.miControlador = controlador;
		this.miControlador.setVista(this);
		iniciarVentana();
	}

	private void iniciarVentana() {

		this.setTitle("Chinchon");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
		
		menuGeneral = new JMenuBar();
		setJMenuBar(menuGeneral);
		mnArchivo = new JMenu("Archivo");
		menuGeneral.add(mnArchivo);
		setJMenuBar(menuGeneral);
		
		mnReglas = new JMenu("Reglas");
		menuGeneral.add(mnReglas);
		
		mntmVerReglas = new JMenuItem("Ver reglas");
		mntmVerReglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_VERREGLAS);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		mnReglas.add(mntmVerReglas);
		
		mntmCargarPartida = new JMenuItem("Cargar Partida");
		mntmCargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					seleccionarAccion(BTN_CARGARPARTIDA);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mntmGuardarPartida = new JMenuItem("Guardar partida");
		mntmGuardarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					seleccionarAccion(BTN_GUARDARPARTIDA);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		mntmVerTop = new JMenuItem("Ver Top 5");
		mntmVerTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					seleccionarAccion(BTN_VERTOP5);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		mnSalir = new JMenuItem("Salir");
		mnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
		
		mnArchivo.add(mntmCargarPartida);
		mnArchivo.add(mntmGuardarPartida);
		mnArchivo.add(mntmVerTop);
		mnArchivo.add(mnSalir);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
		
		JScrollPane scrollPane = new JScrollPane();
	//	panelPrincipal.add(scrollPane, BorderLayout.EAST);
		
		panelBotones = new JPanel();
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[] { 113, 0 };
		gbl_panelBotones.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelBotones.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelBotones.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelBotones.setLayout(gbl_panelBotones);
		
		JLabel lblMenuPrincipal = new JLabel("Menu Principal");
		lblMenuPrincipal.setBackground(Color.orange);
		lblMenuPrincipal.setOpaque(true);
		
		GridBagConstraints gbc_MenuPrincipal = new GridBagConstraints();
		gbc_MenuPrincipal.fill = GridBagConstraints.HORIZONTAL;
		gbc_MenuPrincipal.insets = new Insets(0, 0, 5, 0);
		gbc_MenuPrincipal.gridx = 0;
		gbc_MenuPrincipal.gridy = 1;
		lblMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(lblMenuPrincipal, gbc_MenuPrincipal);
		
		btnAgregarJugador = new JButton("Agregar Jugador");
		btnAgregarJugador.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_AGREGAR);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnAgregarJugador = new GridBagConstraints();
		gbc_btnAgregarJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAgregarJugador.insets = new Insets(0, 0, 5, 0);
		gbc_btnAgregarJugador.gridx = 0;
		gbc_btnAgregarJugador.gridy = 2;
		panelBotones.add(btnAgregarJugador, gbc_btnAgregarJugador);
		
		btnIniciarJuego = new JButton("Comenzar Partida");
		btnIniciarJuego.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_INICIAR);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnIniciarJuego = new GridBagConstraints();
		gbc_btnIniciarJuego.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciarJuego.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciarJuego.gridx = 0;
		gbc_btnIniciarJuego.gridy = 3;
		panelBotones.add(btnIniciarJuego, gbc_btnIniciarJuego);
		
		JLabel lblMenuJugador = new JLabel("Menu Jugador");
		lblMenuJugador.setBackground(Color.orange);
		lblMenuJugador.setOpaque(true);
		
		GridBagConstraints gbc_menuJugador = new GridBagConstraints();
		gbc_menuJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuJugador.insets = new Insets(0, 0, 5, 0);
		gbc_menuJugador.gridx = 0;
		gbc_menuJugador.gridy = 9;
		lblMenuJugador.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(lblMenuJugador, gbc_menuJugador);
		
		
		btnRobar = new JButton("Robar Carta");
		btnRobar.setEnabled(false);
		btnRobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_ROBAR);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnRobar = new GridBagConstraints();
		gbc_btnRobar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnRobar.insets = new Insets(0, 0, 5, 0);
		gbc_btnRobar.gridx = 0;
		gbc_btnRobar.gridy = 10;
		panelBotones.add(btnRobar, gbc_btnRobar);
		
		btnTirar = new JButton("Tirar Carta");
		btnTirar.setEnabled(false);
		btnTirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_TIRAR);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_btnTirar = new GridBagConstraints();
		gbc_btnTirar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTirar.insets = new Insets(0, 0, 5, 0);
		gbc_btnTirar.gridx = 0;
		gbc_btnTirar.gridy = 11;
		panelBotones.add(btnTirar, gbc_btnTirar);
		
		btnrobarDescarte = new JButton("Robar carta del descarte");
		btnrobarDescarte.setEnabled(false);
		btnrobarDescarte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					seleccionarAccion(BTN_ROBARDESCARTE);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		GridBagConstraints gbc_robarDescarte = new GridBagConstraints();
		gbc_robarDescarte.fill = GridBagConstraints.HORIZONTAL;
		gbc_robarDescarte.insets = new Insets(1, 0, 5, 0);
		gbc_robarDescarte.gridx = 0;
		gbc_robarDescarte.gridy = 12;
		panelBotones.add(btnrobarDescarte, gbc_robarDescarte);
		
		scrollPane.setViewportView(panelBotones);
		panelPrincipal.add(scrollPane, BorderLayout.EAST);
		
		JPanel panelJugadorActual = new JPanel();
		GridBagLayout gbl_panelJugadorActual = new GridBagLayout();
		gbl_panelJugadorActual.columnWidths = new int[] { 180, 86, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelJugadorActual.rowHeights = new int[] { 20, 0 };
		gbl_panelJugadorActual.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelJugadorActual.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelJugadorActual.setLayout(gbl_panelJugadorActual);
		
		
		txtJugadorActual = new JTextField();
		txtJugadorActual.setForeground(Color.WHITE);
		txtJugadorActual.setBackground(Color.BLUE);
		txtJugadorActual.setEditable(false);
		txtJugadorActual.setHorizontalAlignment(SwingConstants.CENTER);
		txtJugadorActual.setText("Jugador Actual");
		GridBagConstraints gbc_txtJugadorActual = new GridBagConstraints();
		gbc_txtJugadorActual.insets = new Insets(0, 0, 0, 5);
		gbc_txtJugadorActual.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtJugadorActual.gridx = 0;
		gbc_txtJugadorActual.gridy = 0;
		txtJugadorActual.setColumns(10);
		panelJugadorActual.add(txtJugadorActual, gbc_txtJugadorActual);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		textField.setColumns(10);
		panelJugadorActual.add(textField, gbc_textField);
		
		panelPrincipal.add(panelJugadorActual, BorderLayout.NORTH);
		
		panelCartas = new JPanel();
		GridBagLayout gbl_panelCartas = new GridBagLayout();
		gbl_panelCartas.columnWidths = new int[] { 900, 0 };
		gbl_panelCartas.rowHeights = new int[] { 50, 0, 80, 0 };
		gbl_panelCartas.columnWeights = new double[] { 10, Double.MIN_VALUE };
		gbl_panelCartas.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panelCartas.setLayout(gbl_panelCartas);
		
		txtMensajes = new JTextField();
		GridBagConstraints gbc_txtDados = new GridBagConstraints();
		txtMensajes.setEditable(false);
		gbc_txtDados.insets = new Insets(0, 0, 5, 0);
		gbc_txtDados.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDados.gridx = 0;
		gbc_txtDados.gridy = 0;
		panelCartas.add(txtMensajes, gbc_txtDados);
		txtMensajes.setColumns(10);
		
		
		panelContenedorCartas = new JPanel();
		GridBagConstraints gbc_panelContenedorCartas = new GridBagConstraints();
		gbc_panelContenedorCartas.anchor = GridBagConstraints.WEST;
		gbc_panelContenedorCartas.fill = GridBagConstraints.VERTICAL;
		gbc_panelContenedorCartas.gridx = 0;
		gbc_panelContenedorCartas.gridy = 2;
		panelContenedorCartas.setLayout(new GridLayout(0, 10, 5, 25));
		panelCartas.add(panelContenedorCartas, gbc_panelContenedorCartas);
		panelPrincipal.add(panelCartas, BorderLayout.SOUTH);
		
		buildListaJugadores();
		

		
		mesa = new FondoPanel();
		
		GridBagLayout gbl_mesa = new GridBagLayout();
		gbl_mesa.columnWidths = new int[] { 18, 70, 0, 0, 0, 0, 0, 0, 0 };
		gbl_mesa.rowHeights = new int[] { 20, 0 };
		gbl_mesa.columnWeights = new double[] { 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_mesa.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		mesa.setLayout(gbl_mesa);
		
		cartaReverso = new CartaGrafica("reverso");
		gbc_reverso = new GridBagConstraints();
		gbc_reverso.fill = GridBagConstraints.HORIZONTAL;
		gbc_reverso.insets = new Insets(0, 0, 0, 0);
		gbc_reverso.gridx = 1;
		gbc_reverso.gridy = 1;
		
		
		cartaDescarte = new CartaGrafica("descarte vacio");
		 gbc_descarte = new GridBagConstraints();
		gbc_descarte.fill = GridBagConstraints.HORIZONTAL;
		gbc_descarte.insets = new Insets(0, 0, 0, 0);
		gbc_descarte.gridx = 2;
		gbc_descarte.gridy = 1;
		
		
		panelPrincipal.add(mesa, BorderLayout.CENTER);
	}

	private void seleccionarAccion(int btnAgregar) throws RemoteException {
		switch (btnAgregar) {
		case BTN_VERREGLAS:
			VentanaReglas vr = new VentanaReglas();
			vr.setVisible(true);
			break;
		case BTN_CARGARPARTIDA:
			miControlador.cargarPartida("Chinchon.dat");
			break;
		case BTN_GUARDARPARTIDA:
			miControlador.guardarPartida("Chinchon.dat");
			break;
		case BTN_VERTOP5:
			verTop();
			break;
		case BTN_AGREGAR:
			agregarJugador();
			break;
		case BTN_INICIAR:
			miControlador.iniciarJuego();
			break;
		case BTN_ROBAR:
			txtMensajes.setText("");
			miControlador.robaCarta();
			break;
		case BTN_TIRAR:
			txtMensajes.setText("");
			tiraCarta();
			break;
		case BTN_ROBARDESCARTE:
			txtMensajes.setText("");
			miControlador.robaCartaDescarte();
			break;
		case BTN_TERMINARMANO:
			miControlador.terminaMano();
			break;
		}
	}	
	
	public void robaCartaDescarte() throws RemoteException{
		txtMensajes.setText("");
		actualizarCartas();
		
			actualizaDescarte();
	
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void buildListaJugadores() {
		areaJugadores = new JPanel();
		panelPrincipal.add(areaJugadores, BorderLayout.WEST);
		areaJugadores.setLayout(new MigLayout("", "[150px:n:180px]", "[grow]"));
				
		darFormatoJugadores(null);
		
	}
	
	public void actualizaDescarte() {
		mesa.remove(cartaDescarte);
		if (miControlador.getCartasDescarte() != 0) {
			cartaDescarte = getImage(miControlador.mostrarDescarte().mostrarCarta());
			mesa.add(cartaDescarte,gbc_descarte); }
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	private int posicionCartaClickeada() {
		for (int i = 0; i < cartasJugador.length; i++) {
			if (cartasJugador[i].isClickeada())
				return i;
		}
		return 0;
	}
	
	private void tiraCarta() throws RemoteException{
		if (manejaCartaClickeada() != null) {
		miControlador.tiraCarta(posicionCartaClickeada()+1);
		if (miControlador.checkeaMano()) {
			int dialogButton = JOptionPane.showConfirmDialog(null,"Terminar mano?", "En condiciones de terminar la mano",JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION)
				seleccionarAccion(BTN_TERMINARMANO);
			 else
				miControlador.limpiarCombinaciones();
		}
		if (miControlador.getEstado() != Juego.FIN_JUEGO) {
			if (miControlador.getEstado() != Juego.FIN_MANO && miControlador.getEstado() != 0)
			 {
				miControlador.cambiaTurno();
				turno();
			 }}
		} else
			notificarError("Debe seleccionar una carta antes de tirar");
		getContentPane().validate();
		getContentPane().repaint();
	}

	public void actualizarCartas() throws RemoteException {
		panelContenedorCartas.removeAll();
		if (miControlador.getEstado() != Juego.FIN_JUEGO &&
				miControlador.getEstado() != 0) 
			mostrarCartas();
		
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	private TableModel datosJugador(IJugador[] jugadores) {
		// Crea el conjunto de datos
		Object[][] datos;
		if (jugadores == null){
			datos = new Object[][]{}; 
		} else {
			datos = new Object[jugadores.length][2];
			int i = 0 ;
			for(IJugador j: jugadores){
				datos[i][0] = j.getNombre();
				datos[i++][1] = j.getPuntos();
			}
		}
	
		@SuppressWarnings("serial")
		DefaultTableModel dtm = new DefaultTableModel(
				datos,
				new String[] {
					"Jugador", "Puntos"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		return dtm;
	}
	
	public void darFormatoJugadores(IJugador[] jugadores) {
		tblJugadores = new JTable();
		tblJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblJugadores.setModel(datosJugador(null));
		tblJugadores.getColumnModel().getColumn(0).setResizable(true);
		tblJugadores.getColumnModel().getColumn(0).setPreferredWidth(65);
		tblJugadores.getColumnModel().getColumn(0).setMinWidth(30);
		tblJugadores.getColumnModel().getColumn(0).setMaxWidth(85);
		tblJugadores.getColumnModel().getColumn(1).setResizable(false);
		tblJugadores.getColumnModel().getColumn(1).setMinWidth(45);
		tblJugadores.getColumnModel().getColumn(1).setMaxWidth(45);
		tblJugadores.getColumnModel().getColumn(1).setPreferredWidth(45);
		tblJugadores.setModel(datosJugador(jugadores));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tblJugadores);
		areaJugadores.add(scrollPane, "cell 0 0,alignx left,growy");
		areaJugadores.repaint();
	}
	
	@Override
	public void actualizaPuntaje() throws RemoteException {
		tblJugadores.setModel(datosJugador(miControlador.getJugadores()));
	}
	
	private void agregarJugador() throws RemoteException {
		String nombre = "";
		txtMensajes.setText("");
		nombre = JOptionPane.showInputDialog(null,"Nombre del Jugador", "Agregar Jugador", JOptionPane.INFORMATION_MESSAGE);
		if (nombre != null)
			miControlador.AgregarJugador(nombre);
	}

	@Override
	public void setControlador(Controlador controlador) {
		this.miControlador = controlador;
	}

	@Override
	public void reiniciarMano() throws RemoteException{
		txtMensajes.setForeground(Color.BLUE);
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText("Mano finalizada. El ganador fue " + miControlador.getJugadorEnTurno().getNombre());
		descarteVacio();
		panelContenedorCartas.removeAll();
		actualizaPuntaje();
		getContentPane().validate();
		getContentPane().repaint();
	}

	@Override
	public void finJuego() throws RemoteException {
	//	mesa.remove(cartaDescarte);
		for (int i = 0; i < cartasJugador.length; i++) {
			panelContenedorCartas.remove(cartasJugador[i]);
		}
		btnRobar.setEnabled(false);
		btnTirar.setEnabled(false);
		btnrobarDescarte.setEnabled(false);
		btnAgregarJugador.setEnabled(true);
		btnIniciarJuego.setEnabled(true);
		actualizaPuntaje();
		mesa.remove(cartaReverso);
		mesa.remove(cartaDescarte);
		txtMensajes.setText("");
		txtMensajes.setBackground(Color.BLACK);
		txtMensajes.setForeground(Color.GREEN);
		txtMensajes.setText("Chinchon Finalizado. El ganador es " + miControlador.getGanador() + " . Cantidad de rondas : " + miControlador.obtieneRondas());
		getContentPane().validate();
		getContentPane().repaint();
	}

	@Override
	public void notificarMensaje(String mensaje) {
		txtMensajes.setForeground(Color.GREEN);
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText(mensaje);
		getContentPane().validate();
		getContentPane().repaint();
	}

	@Override
	public void notificarError(String error) {
		txtMensajes.setForeground(Color.RED);
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText(error);
		getContentPane().validate();
		getContentPane().repaint();
	}

	
	@Override
	public void juegoIniciado() throws RemoteException {
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText("");
		mostrarCartas();
		turno();
		cartaReverso = getImage("reverso");
		cartaDescarte = getImage("descarte vacio");
		mesa.add(cartaReverso, gbc_reverso);
		mesa.add(cartaDescarte, gbc_descarte);
		btnRobar.setEnabled(true);
		btnTirar.setEnabled(true);
		btnrobarDescarte.setEnabled(true);
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	private void mostrarCartas() throws RemoteException {
		miControlador.getJugadorEnTurno().ordenarPorNumero();
		cartasJugador = new CartaClickeable[miControlador.getJugadorEnTurno().getCantCartas()];
		for (int i = 0; i < miControlador.getJugadorEnTurno().getCantCartas(); i++) {
			cartasJugador[i] = getClickedImage(miControlador.getJugadorEnTurno().getCartas().get(i).mostrarCarta());
			panelContenedorCartas.add(cartasJugador[i]);
		}
		getContentPane().validate();
		getContentPane().repaint();
	}

	@Override
	public void turno() throws RemoteException {
		textField.setText(miControlador.getJugadorEnTurno().getNombre());
	}
	
					
	private CartaGrafica getImage(String archivo) {
		CartaGrafica imagen_1 = new CartaGrafica(archivo);
		imagen_1.setLayout(new BoxLayout(imagen_1, BoxLayout.X_AXIS));
		return imagen_1;

	}

	private CartaClickeable getClickedImage(String archivo) {
		CartaClickeable imagen_1 = new CartaClickeable(archivo, this);
		imagen_1.setLayout(new BoxLayout(imagen_1, BoxLayout.X_AXIS));
		return imagen_1;
		
	}

	private CartaClickeable manejaCartaClickeada() {
		for (int i = 0; i < cartasJugador.length; i++) {
			if (cartasJugador[i].isClickeada())
				return cartasJugador[i];
		}
		return null;	
	}
	
	@Override
	public void MouseClicked(Object objeto) {
		// TODO Auto-generated method stub
		CartaClickeable cartaClickeada = manejaCartaClickeada();
		if (cartaClickeada != null)
			for (int i = 0; i < cartasJugador.length; i++) {
				if (cartasJugador[i] != cartaClickeada)
					cartasJugador[i].reset();
			}
	}
	
	@Override
	public void verTop() throws HeadlessException, RemoteException {
		tablaTop = new TablaTop(miControlador.getTop());
	}

	@Override
	public void cargaJuego() throws RemoteException {
		turno();
		actualizaPuntaje();
		actualizarCartas();
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		btnRobar.setEnabled(true);
		btnTirar.setEnabled(true);
		btnrobarDescarte.setEnabled(true);
		if (miControlador.getCartasDescarte() == 0) {
			if (cartaDescarte != null)
				mesa.remove(cartaDescarte);
		}else
			actualizaDescarte();
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	@Override
	public void iniciar() {
		this.setVisible(true);
	}

	public void cambiaTurno() throws RemoteException {
		actualizarCartas();
		turno();	
		btnRobar.setEnabled(true);
		btnTirar.setEnabled(true);
		btnrobarDescarte.setEnabled(true);
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void limpiaTurno() throws RemoteException {
		turno();
		btnRobar.setEnabled(false);
		btnrobarDescarte.setEnabled(false);
		btnTirar.setEnabled(false);
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void deshabilitaBotones() {
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		btnrobarDescarte.setEnabled(false);
		btnRobar.setEnabled(false);
		btnTirar.setEnabled(false);
	}
	
	public void mostrarCartasJugadorNoActual() throws RemoteException {
		Jugador jugador;
		if (miControlador.getJugadorEnTurno().getNombre().equals(miControlador.getListaJugadores().get(0).getNombre()))
			jugador = miControlador.getListaJugadores().get(1);
		else
			jugador = miControlador.getListaJugadores().get(0);
		cartasJugador = new CartaClickeable[jugador.getCantCartas()];
		for (int i = 0; i < jugador.getCantCartas(); i++) {
			cartasJugador[i] = getClickedImage(jugador.getCartas().get(i).mostrarCarta());
			panelContenedorCartas.add(cartasJugador[i]);
		}
		getContentPane().validate();
		getContentPane().repaint();
	}

	class FondoPanel extends JPanel {
        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Imagenes/FONDO3.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);
        }
    }
	
	public void descarteVacio() {
		mesa.remove(cartaDescarte);
	}	
	
	public void muestraMesa() {
		mesa.add(cartaDescarte,gbc_descarte);
		mesa.add(cartaReverso,gbc_reverso);
	}
	
}




