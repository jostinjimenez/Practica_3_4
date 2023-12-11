package view;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Frm_Ventas extends JFrame{
    private JPanel mainPanel;
    private JTextField txtModelo;
    private JTable tblTable1;
    private JTable tblTable2;
    private JTable tblTable3;
    private JButton btnguardar;
    private JButton btncancelar;
    private JTextField txtBusqueda;
    private JComboBox cbxAscDesc;
    private JComboBox cbxCriterio;
    private JComboBox cbxMarc;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtRuc;
    private JTextField txtEmail;
    private JComboBox txtVendedores;
    private JComboBox txtAutos;
    private JComboBox txtMarcas;
    private JTextField txtColor;
    private JTextField txtPrecio;
    private JTextField txtNroSerie;


    //Constructor
    public Frm_Ventas(){
        initFrame();
    }


    // Metodos
    public void initFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 670);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Registro de Ventas");
    }


    // Main
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        java.awt.EventQueue.invokeLater(Frm_Ventas::new);
    }


}

