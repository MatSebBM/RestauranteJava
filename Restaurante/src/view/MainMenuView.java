package view;

import model.Usuario;
import controller.PlatilloController;
import controller.PagoController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class MainMenuView extends JFrame {
    private Usuario usuario;
    private PlatilloController departamentoController;
    private PagoController pagoController;

    public MainMenuView() {
        this.usuario = new Usuario("admin", "admin");
        this.departamentoController = new PlatilloController();
        this.pagoController = new PagoController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Menú Principal - Restaurante");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // PANEL SUPERIOR - MENÚ
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelSuperior.setBackground(new Color(255, 239, 213)); // tono crema cálido

        JButton btnPagos = new JButton("💰 Ventas");
        JButton btnDisponibles = new JButton("📋 Menú");
        Font fontMenu = new Font("SansSerif", Font.BOLD, 14);

        Color colorBoton = new Color(92, 136, 255);
        JButton[] botones = { btnPagos, btnDisponibles };
        for (JButton b : botones) {
            b.setFont(fontMenu);
            b.setForeground(Color.WHITE);
            b.setBackground(colorBoton);
            b.setFocusPainted(false);
            b.setBorderPainted(false);
            b.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            b.setPreferredSize(new Dimension(130, 40));
            panelSuperior.add(b);
        }

        // PANEL IZQUIERDO - LOGO
        JPanel panelIzquierdo = new JPanel(new BorderLayout());
        panelIzquierdo.setBackground(Color.WHITE);
        panelIzquierdo.setPreferredSize(new Dimension(240, 600));

        JPanel panelLogo = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 30));
        panelLogo.setBackground(Color.WHITE);

        JLabel lblLogo = new JLabel("🍽");
        lblLogo.setFont(new Font("SansSerif", Font.PLAIN, 40));

        JLabel lblTitulo = new JLabel("RESTAURANTE");
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTitulo.setForeground(new Color(60, 60, 60));

        panelLogo.add(lblLogo);
        panelLogo.add(lblTitulo);

        panelIzquierdo.add(panelLogo, BorderLayout.NORTH);

        // PANEL DERECHO - CONTENIDO PRINCIPAL
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(Color.WHITE);

        // PANEL DE BIENVENIDA
        JPanel panelBienvenida = new JPanel(new GridLayout(2, 1));
        panelBienvenida.setBackground(Color.WHITE);
        panelBienvenida.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        JLabel lblBienvenida = new JLabel("¡Bienvenido de nuevo!", SwingConstants.CENTER);
        lblBienvenida.setFont(new Font("SansSerif", Font.BOLD, 26));
        lblBienvenida.setForeground(new Color(60, 60, 60));

        JLabel lblUsuario = new JLabel("Usuario: " + usuario.getNombre() + " " + usuario.getApellido(), SwingConstants.CENTER);
        lblUsuario.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblUsuario.setForeground(new Color(100, 100, 100));

        panelBienvenida.add(lblBienvenida);
        panelBienvenida.add(lblUsuario);

        // PANEL DE PLATOS
        JPanel panelImagenes = new JPanel(new GridLayout(2, 3, 15, 15));
        panelImagenes.setBackground(new Color(255, 250, 240));
        panelImagenes.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(150, 100, 50)), 
            "🍽 Menú del Día", 
            TitledBorder.CENTER, 
            TitledBorder.TOP, 
            new Font("SansSerif", Font.BOLD, 16), 
            new Color(150, 100, 50)
        ));

        String[] platos = {
            "🍝 Pasta Alfredo", 
            "🍔 Hamburguesa BBQ", 
            "🍕 Pizza Margarita", 
            "🥗 Ensalada César", 
            "🍣 Sushi Mixto", 
            "🍰 Tarta de Queso"
        };

        for (String plato : platos) {
            JLabel lblPlato = new JLabel(plato, SwingConstants.CENTER);
            lblPlato.setFont(new Font("SansSerif", Font.PLAIN, 15));
            lblPlato.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
            lblPlato.setOpaque(true);
            lblPlato.setBackground(Color.WHITE);
            panelImagenes.add(lblPlato);
        }

        // PANEL DE SALIR
        JPanel panelSalir = new JPanel();
        panelSalir.setBackground(Color.WHITE);

        JButton btnSalir = new JButton("🚪 Salir");
        btnSalir.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSalir.setBackground(new Color(255, 105, 97));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setPreferredSize(new Dimension(120, 40));
        btnSalir.setFocusPainted(false);
        btnSalir.setBorderPainted(false);
        btnSalir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        panelSalir.add(btnSalir);

        // AGREGAR TODO AL PANEL DERECHO
        panelDerecho.add(panelBienvenida, BorderLayout.NORTH);
        panelDerecho.add(panelImagenes, BorderLayout.CENTER);
        panelDerecho.add(panelSalir, BorderLayout.SOUTH);

        // AGREGAR A FRAME
        add(panelSuperior, BorderLayout.NORTH);
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

        // EVENTOS
        btnPagos.addActionListener(e -> mostrarPagos());
        btnDisponibles.addActionListener(e -> mostrarDepartamentosDisponibles());
        btnSalir.addActionListener(e -> salir());
    }

    private void salir() {
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            this.dispose();
        }
    }

    private void mostrarPagos() {
        new PagosView(pagoController).setVisible(true);
    }

    private void mostrarDepartamentosDisponibles() {
        new PlatillosDisponiblesView(departamentoController).setVisible(true);
    }

    public void setVisible(boolean visible) {
        super.setVisible(visible);
    }
}
