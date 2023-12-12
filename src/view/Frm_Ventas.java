package view;

import controller.controladores.AutoController;
import controller.controladores.VendedorController;
import controller.controladores.VentaController;
import controller.tda_listas.exceptions.VacioExceptions;
import model.Auto;
import model.Vendedor;
import view.tablas.ModeloTablaAuto;
import view.tablas.ModeloTablaVendedor;
import view.tablas.ModeloTablaVenta;
import view.util.TextPrompt;
import view.util.UtilVista;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Frm_Ventas extends JFrame {
    private JPanel mainPanel;
    private JTextField txtModelo;
    private JTable tblTable2;
    private JTable tblTable3;
    private JButton btnguardar;
    private JButton btncancelar;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtRuc;
    private JTextField txtDni;
    private JComboBox cbxVendedores;
    private JComboBox cbxAutos;
    private JComboBox cbxMarca;
    private JTextField txtColor;
    private JTextField txtAnio;
    private JTextArea txtDescripcion;
    private JTextField txtNroVenta;
    private JTable tblTable1;
    private JFormattedTextField txtFecha;
    private JTextField txtPrecioTotal;

    //Constructor
    public Frm_Ventas() {
        initFrame();
        limpiar();
        cargarPH();

        btnguardar.addActionListener(e -> guardar());
        btncancelar.addActionListener(e -> System.exit(0));

        cbxVendedores.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        cargarVistaVendedor();
                    } catch (VacioExceptions ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        cbxAutos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    try {
                        cargarVistaAuto();
                    } catch (VacioExceptions ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }

    // Atributos
    private AutoController ac = new AutoController();
    private VendedorController vc = new VendedorController();
    private VentaController venc = new VentaController();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    ModeloTablaVenta mtv = new ModeloTablaVenta();
    ModeloTablaVendedor mtv2 = new ModeloTablaVendedor();
    ModeloTablaAuto mta = new ModeloTablaAuto();

    // Metodos
    public void cargarVistaAuto() throws VacioExceptions {
        int selectedIndex = cbxAutos.getSelectedIndex();

        if (selectedIndex == 0) {
            txtModelo.setText("");
            txtColor.setText("");
            txtAnio.setText("");
            cbxMarca.setSelectedIndex(0);

            // Habilitar los campos para la edición
            txtModelo.setEditable(true);
            txtColor.setEditable(true);
            txtAnio.setEditable(true);
            cbxMarca.setEnabled(true);

        } else {
            Auto selectedAuto = null;
            try {
                selectedAuto = mta.getAutos().get(selectedIndex - 1);
            } catch (VacioExceptions ex) {
                throw new RuntimeException(ex);
            }
            txtModelo.setText(selectedAuto.getModelo());
            txtColor.setText(selectedAuto.getColor());
            txtAnio.setText(selectedAuto.getAnio());
            cbxMarca.setSelectedIndex(selectedAuto.getId_marca());

            // Deshabilitar los campos para la edición
            txtModelo.setEditable(false);
            txtColor.setEditable(false);
            txtAnio.setEditable(false);
            cbxMarca.setEnabled(false);
        }
    }

    public void cargarVistaVendedor() throws VacioExceptions {
        int selectedIndex = cbxVendedores.getSelectedIndex();

        if (selectedIndex == 0) {
            txtNombre.setText("");
            txtApellido.setText("");
            txtTelefono.setText("");
            txtRuc.setText("");
            txtDni.setText("");

            // Habilitar los campos para la edición
            txtNombre.setEditable(true);
            txtApellido.setEditable(true);
            txtTelefono.setEditable(true);
            txtRuc.setEditable(true);
            txtDni.setEditable(true);
        } else {
            Vendedor selectedVendedor = null;
            try {
                selectedVendedor = mtv2.getVendedores().get(selectedIndex - 1);
            } catch (VacioExceptions ex) {
                throw new RuntimeException(ex);
            }
            txtNombre.setText(selectedVendedor.getNombre());
            txtApellido.setText(selectedVendedor.getApellido());
            txtTelefono.setText(selectedVendedor.getTelefono());
            txtRuc.setText(selectedVendedor.getRuc());
            txtDni.setText(selectedVendedor.getDni());

            // Deshabilitar los campos para la edición
            txtNombre.setEditable(false);
            txtApellido.setEditable(false);
            txtTelefono.setEditable(false);
            txtRuc.setEditable(false);
            txtDni.setEditable(false);
        }
    }

    public void cargarTabla() {
        mta.setAutos(ac.getAutos());
        tblTable2.setModel(mta);
        tblTable2.updateUI();

        mtv2.setVendedores(vc.getVendedores());
        tblTable3.setModel(mtv2);
        tblTable3.updateUI();

        mtv.setVentas(venc.getVentas());
        tblTable1.setModel(mtv);
        tblTable1.updateUI();
    }

    public void guardar() {
        if (validar()) {
            try {
                // Vendedor
                vc.getVendedor().setNombre(txtNombre.getText());
                vc.getVendedor().setApellido(txtApellido.getText());
                vc.getVendedor().setTelefono(txtTelefono.getText());
                vc.getVendedor().setRuc(txtRuc.getText());
                vc.getVendedor().setDni(txtDni.getText());

                // Auto
                ac.getAuto().setModelo(txtModelo.getText());
                ac.getAuto().setColor(txtColor.getText());
                ac.getAuto().setAnio(txtAnio.getText());
                ac.getAuto().setId_marca(cbxMarca.getSelectedIndex() + 1);

                // Venta
                Date fecha = dateFormat.parse(txtFecha.getText());
                venc.getVenta().setFecha(fecha);
                venc.getVenta().setDescripcion(txtDescripcion.getText());
                venc.getVenta().setTotal(Double.parseDouble(txtNroVenta.getText()));
                venc.getVenta().setNro_venta(txtNroVenta.getText());

                if (ac.guardar() && vc.guardar()) {
                    venc.getVenta().setId_auto(vc.getVendedor().getId());
                    venc.getVenta().setId_vendedor(ac.getAuto().getId());
                    if (venc.guardar()) {
                        limpiar();
                        JOptionPane.showMessageDialog(null, "Se guardó correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error al guardar datos: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Complete todos los campos");
        }
    }

    public Boolean validar() {
        return !txtModelo.getText().trim().isEmpty() &&
                !txtNombre.getText().trim().isEmpty() &&
                !txtApellido.getText().trim().isEmpty() &&
                !txtTelefono.getText().trim().isEmpty() &&
                !txtRuc.getText().trim().isEmpty() &&
                !txtDni.getText().trim().isEmpty() &&
                !txtColor.getText().trim().isEmpty() &&
                !txtAnio.getText().trim().isEmpty() &&
                !txtDescripcion.getText().trim().isEmpty() &&
                !txtNroVenta.getText().trim().isEmpty() &&
                !txtFecha.getText().trim().isEmpty();
    }


    public void limpiar() {
        txtModelo.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtRuc.setText("");
        txtDni.setText("");
        txtColor.setText("");
        txtAnio.setText("");
        txtDescripcion.setText("");
        txtNroVenta.setText("");
        txtFecha.setText("");
        cbxVendedores.setSelectedItem(0);
        cbxAutos.setSelectedItem(0);
        cargarTabla();
        try {
            UtilVista.cargaMarca(cbxMarca);
            UtilVista.cargaAuto(cbxAutos);
            UtilVista.cargaVendedor(cbxVendedores);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initFrame() {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 640);
        setLocationRelativeTo(null);
        setVisible(true);
        setTitle("Registro de Ventas");

        try {
            MaskFormatter dateMask = new MaskFormatter("##/##/####");
            dateMask.setPlaceholderCharacter('_');
            txtFecha.setFormatterFactory(new DefaultFormatterFactory(dateMask));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public void cargarPH() {
        TextPrompt nombre = new TextPrompt("Nombre", txtNombre);
        TextPrompt apellido = new TextPrompt("Apellido", txtApellido);
        TextPrompt telefono = new TextPrompt("Telefono", txtTelefono);
        TextPrompt ruc = new TextPrompt("Ruc", txtRuc);
        TextPrompt email = new TextPrompt("DNI", txtDni);
        TextPrompt modelo = new TextPrompt("Modelo", txtModelo);
        TextPrompt color = new TextPrompt("Color", txtColor);
        TextPrompt anio = new TextPrompt("Año", txtAnio);
        TextPrompt descripcion = new TextPrompt("Descripcion", txtDescripcion);
        TextPrompt precioT = new TextPrompt("Precio Total", txtPrecioTotal);

    }


}