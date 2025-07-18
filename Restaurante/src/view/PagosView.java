package view;

import controller.PagoController;
import util.SimpleDocumentListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PagosView extends JFrame {
    private PagoController pagoController;
    private JPanel panelVentas;
    private List<JPanel> listaVentas;

    public PagosView(PagoController pagoController) {
        this.pagoController = pagoController;
        this.listaVentas = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        setTitle("Historial de Ventas");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("💰 Ventas de Platillos", SwingConstants.CENTER);
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        panelVentas = new JPanel(new GridLayout(0, 2, 15, 15));
        panelVentas.setBackground(Color.WHITE);
        panelVentas.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JScrollPane scrollPane = new JScrollPane(panelVentas);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnAgregarVenta = new JButton("➕ Agregar Venta");
        btnAgregarVenta.setBackground(new Color(92, 136, 255));
        btnAgregarVenta.setForeground(Color.WHITE);
        btnAgregarVenta.setFocusPainted(false);
        btnAgregarVenta.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnAgregarVenta.setPreferredSize(new Dimension(160, 40));
        btnAgregarVenta.addActionListener(e -> mostrarDialogoAgregarVenta());

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(Color.WHITE);
        panelInferior.add(btnAgregarVenta);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void mostrarDialogoAgregarVenta() {
        JDialog dialog = new JDialog(this, "Agregar Nueva Venta", true);
        dialog.setSize(400, 320);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtPlatillo = new JTextField(20);
        JTextField txtPrecio = new JTextField(20);
        JTextField txtCantidad = new JTextField(20);
        JTextField txtTotal = new JTextField(20);
        txtTotal.setEditable(false);

        Runnable calcularTotal = () -> {
            try {
                double precio = Double.parseDouble(txtPrecio.getText());
                int cantidad = Integer.parseInt(txtCantidad.getText());
                txtTotal.setText(String.format("%.2f", precio * cantidad));
            } catch (Exception ignored) {
                txtTotal.setText("");
            }
        };

        txtPrecio.getDocument().addDocumentListener(new SimpleDocumentListener(calcularTotal));
        txtCantidad.getDocument().addDocumentListener(new SimpleDocumentListener(calcularTotal));

        gbc.gridx = 0; gbc.gridy = 0; dialog.add(new JLabel("🍽 Platillo:"), gbc);
        gbc.gridx = 1; dialog.add(txtPlatillo, gbc);

        gbc.gridx = 0; gbc.gridy = 1; dialog.add(new JLabel("💲 Precio unitario:"), gbc);
        gbc.gridx = 1; dialog.add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy = 2; dialog.add(new JLabel("🔢 Cantidad:"), gbc);
        gbc.gridx = 1; dialog.add(txtCantidad, gbc);

        gbc.gridx = 0; gbc.gridy = 3; dialog.add(new JLabel("💰 Total:"), gbc);
        gbc.gridx = 1; dialog.add(txtTotal, gbc);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setBackground(new Color(0, 153, 76));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnGuardar.addActionListener(e -> {
            JPanel card = crearCardVenta(
                txtPlatillo.getText(),
                txtPrecio.getText(),
                txtCantidad.getText(),
                txtTotal.getText()
            );
            panelVentas.add(card);
            panelVentas.revalidate();
            panelVentas.repaint();
            dialog.dispose();
        });

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        dialog.add(btnGuardar, gbc);

        dialog.setVisible(true);
    }

    private JPanel crearCardVenta(String platillo, String precio, String cantidad, String total) {
        JPanel card = new JPanel(new GridLayout(4, 1));
        card.setBackground(new Color(245, 250, 255));
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 200, 240), 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        card.add(new JLabel("🍽 Platillo: " + platillo));
        card.add(new JLabel("💲 Precio: $" + precio));
        card.add(new JLabel("🔢 Cantidad: " + cantidad));
        card.add(new JLabel("💰 Total: $" + total));

        return card;
    }

    public void mostrar() {
        setVisible(true);
    }
}
