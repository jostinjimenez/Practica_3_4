package view.util;

import controller.controladores.AutoController;
import controller.controladores.MarcaController;
import controller.controladores.VendedorController;
import controller.tda_listas.exceptions.VacioExceptions;

import javax.swing.*;

public class UtilVista {
    public static void cargaMarca(JComboBox cbxmarca) {
        MarcaController mc = new MarcaController();
        cbxmarca.removeAllItems();
        try {
            for (int i = 0; i < mc.getMarcas().getSize(); i++) {
                cbxmarca.addItem(mc.getMarcas().get(i));
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static void cargaAuto(JComboBox cbxauto) {
        AutoController ac = new AutoController();
        cbxauto.removeAllItems();

        cbxauto.addItem("Seleccione un auto");
        try {
            for (int i = 0; i < ac.getAutos().getSize(); i++) {
                cbxauto.addItem(ac.getAutos().get(i).getModelo());
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }

    public static void cargaVendedor(JComboBox cbxvendedor) {
        VendedorController venc = new VendedorController();
        cbxvendedor.removeAllItems();

        cbxvendedor.addItem("Seleccione un vendedor");

        try {
            for (int i = 0; i < venc.getVendedores().getSize(); i++) {
                cbxvendedor.addItem(venc.getVendedores().get(i).getNombre() + " " + venc.getVendedores().get(i).getApellido());
            }
        } catch (VacioExceptions e) {
            e.printStackTrace();
        }
    }
}