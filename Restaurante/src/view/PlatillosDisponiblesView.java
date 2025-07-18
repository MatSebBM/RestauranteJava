package view;

import controller.PlatilloController;
import model.Platillo;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlatillosDisponiblesView extends JFrame {
    private PlatilloController platilloController;
    private JPanel panelPlatillos;

    public PlatillosDisponiblesView(PlatilloController controller) {
        this.platilloController = controller;
        initComponents();
        cargarPlatillos();
    }

    private void initComponents() {
        setTitle("Platillos Disponibles");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel superior
        JLabel titulo = new JLabel("Menú del Restaurante", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        add(titulo, BorderLayout.NORTH);

        // Panel central con los platillos
        panelPlatillos = new JPanel(new GridLayout(0, 2, 15, 15));
        panelPlatillos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelPlatillos.setBackground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(panelPlatillos);
        add(scrollPane, BorderLayout.CENTER);

        // Botón inferior
        JButton btnAgregar = new JButton("Agregar Platillo");
        btnAgregar.setBackground(new Color(92, 136, 255));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAgregar.setPreferredSize(new Dimension(200, 40));
        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(Color.WHITE);
        panelInferior.add(btnAgregar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void cargarPlatillos() {
        panelPlatillos.removeAll();
        List<Platillo> lista = platilloController.listarPlatillos();

        for (Platillo p : lista) {
            JPanel card = new JPanel(new GridLayout(3, 1));
            card.setBackground(new Color(255, 245, 230));
            card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 150, 100), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
            ));

            card.add(new JLabel("🍽 Platillo: " + p.getNombre()));
            card.add(new JLabel("🥕 Ingredientes: " + p.getIngredientes()));
            card.add(new JLabel("💰 Precio: $" + p.getPrecio()));

            panelPlatillos.add(card);
        }

        panelPlatillos.revalidate();
        panelPlatillos.repaint();
    }

    private void mostrarDialogoAgregar() {
        JTextField txtNombre = new JTextField();
        JTextField txtIngredientes = new JTextField();
        JTextField txtPrecio = new JTextField();

        Object[] campos = {
            "Nombre del Platillo:", txtNombre,
            "Ingredientes:", txtIngredientes,
            "Precio:", txtPrecio
        };

        int result = JOptionPane.showConfirmDialog(
            this, campos, "Agregar Platillo", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String nombre = txtNombre.getText().trim();
                String ingredientes = txtIngredientes.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText());

                if (nombre.isEmpty() || ingredientes.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.");
                    return;
                }

                platilloController.agregarPlatillo(new Platillo(nombre, ingredientes, precio));
                cargarPlatillos();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Precio inválido.");
            }
        }
    }
}
