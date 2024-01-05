package view;

import controller.controladores.AutoController;
import controller.controladores.VendedorController;
import controller.controladores.VentaController;
import controller.controladores.util.Utilidades;
import controller.tda_listas.exceptions.VacioExceptions;
import model.Auto;
import model.Vendedor;
import model.Venta;
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
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

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
    private JComboBox cbxCriterioVenta;
    private JComboBox cbxCriterioAuto;
    private JComboBox cbxCriterioVendedor;
    private JComboBox cbxAscDesc1;
    private JComboBox cbxAscDesc2;
    private JComboBox cbxAscDesc3;

    //Constructor
    public Frm_Ventas() {
        initFrame();
        limpiar();

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
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    try {
                        cargarVistaAuto();
                    } catch (VacioExceptions ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });

        cbxAscDesc1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String criterio = Objects.requireNonNull(cbxCriterioVenta.getSelectedItem()).toString().toLowerCase();
                System.out.println(criterio);
                Field field = Utilidades.getField(Venta.class, criterio);
                if (field != null) {
                    ordenarVentas();
                } else {
                    JOptionPane.showMessageDialog(null, "El atributo " + criterio + " no existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cbxAscDesc2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String criterio = Objects.requireNonNull(cbxCriterioAuto.getSelectedItem()).toString().toLowerCase();
                System.out.println(criterio);
                Field field = Utilidades.getField(Auto.class, criterio);
                if (field != null) {
                    ordenarAutos();
                } else {
                    JOptionPane.showMessageDialog(null, "El atributo " + criterio + " no existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cbxAscDesc3.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String criterio = Objects.requireNonNull(cbxCriterioVendedor.getSelectedItem()).toString().toLowerCase();
                System.out.println(criterio);
                Field field = Utilidades.getField(Vendedor.class, criterio);
                if (field != null) {
                    ordenarVendedores();
                } else {
                    JOptionPane.showMessageDialog(null, "El atributo " + criterio + " no existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    // Atributos
    private final AutoController ac = new AutoController();
    private final VendedorController vc = new VendedorController();
    private final VentaController venc = new VentaController();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private final ModeloTablaVenta mtv = new ModeloTablaVenta();
    private final ModeloTablaVendedor mtv2 = new ModeloTablaVendedor();
    private final ModeloTablaAuto mta = new ModeloTablaAuto();

    // Metodos
    private void ordenarVentas(){
        String criterio = Objects.requireNonNull(cbxCriterioVenta.getSelectedItem()).toString().toLowerCase();
        Integer ascDesc = cbxAscDesc1.getSelectedIndex();

        try {
            mtv.setVentas(venc.ordenarQS(venc.getVentas(), ascDesc, criterio));
            tblTable1.setModel(mtv);
            tblTable1.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ordenarAutos() {
        String criterio = Objects.requireNonNull(cbxCriterioAuto.getSelectedItem()).toString().toLowerCase();
        Integer ascDesc = cbxAscDesc2.getSelectedIndex();

        try {
            mta.setAutos(ac.ordenarQS(ac.getAutos(), ascDesc, criterio));
            tblTable2.setModel(mta);
            tblTable2.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void ordenarVendedores() {
        String criterio = Objects.requireNonNull(cbxCriterioVendedor.getSelectedItem()).toString().toLowerCase();
        Integer ascDesc = cbxAscDesc3.getSelectedIndex();

        try {
            mtv2.setVendedores(vc.ordenarQS(vc.getVendedores(), ascDesc, criterio));
            tblTable3.setModel(mtv2);
            tblTable3.updateUI();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

            // Reset the vendedor in the controller
            vc.setVendedor(new Vendedor());
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

            // Update the vendedor in the controller
            vc.setVendedor(selectedVendedor);
        }
    }

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

            // Reset the auto in the controller
            ac.setAuto(new Auto());
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

            // Update the auto in the controller
            ac.setAuto(selectedAuto);
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

        mtv.fireTableDataChanged();  // Notifica a la tabla que los datos han cambiado
    }


    public void guardar() {
        if (validar()) {
            try {
                // Vendedor
                if (!vc.existe()) {
                    vc.getVendedor().setNombre(txtNombre.getText());
                    vc.getVendedor().setApellido(txtApellido.getText());
                    vc.getVendedor().setTelefono(txtTelefono.getText());
                    vc.getVendedor().setRuc(txtRuc.getText());
                    vc.getVendedor().setDni(txtDni.getText());
                    vc.guardar();
                }

                // Auto
                if (!ac.existe()) {
                    ac.getAuto().setModelo(txtModelo.getText());
                    ac.getAuto().setColor(txtColor.getText());
                    ac.getAuto().setAnio(txtAnio.getText());
                    ac.getAuto().setId_marca(cbxMarca.getSelectedIndex() + 1);
                    System.out.println(ac.getAuto().getId_marca());
                    ac.guardar();
                }

                // Venta
                Date fecha = dateFormat.parse(txtFecha.getText());
                venc.getVenta().setFecha(fecha);
                venc.getVenta().setDescripcion(txtDescripcion.getText());
                venc.getVenta().setTotal(Double.parseDouble(txtPrecioTotal.getText()));
                venc.getVenta().setNro_venta(txtNroVenta.getText());
                venc.getVenta().setId_auto(ac.getAuto().getId());
                venc.getVenta().setId_vendedor(vc.getVendedor().getId());

                if (venc.guardar()) {
                    limpiar();
                    cargarTabla();
                    mtv.fireTableDataChanged();  // Notifica a la tabla que los datos han cambiado
                    JOptionPane.showMessageDialog(null, "Se guardó correctamente", "Información", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo guardar", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        txtPrecioTotal.setText("");
        txtNroVenta.setText(venc.generatedCode());
        txtNroVenta.setEnabled(false);
        cbxVendedores.setSelectedItem(0);
        cbxAutos.setSelectedItem(0);
        try {
            UtilVista.cargaMarca(cbxMarca);
            UtilVista.cargaAuto(cbxAutos);
            UtilVista.cargaVendedor(cbxVendedores);

        } catch (Exception e) {
            e.printStackTrace();
        }
        cargarPH();
        cargarTabla();
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