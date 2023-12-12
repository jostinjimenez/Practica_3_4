package view.tablas;

import controller.tda_listas.ListaEnlazada;
import controller.tda_listas.exceptions.VacioExceptions;
import model.Vendedor;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaVendedor extends AbstractTableModel {

    private ListaEnlazada<Vendedor> vendedores;

    @Override
    public int getRowCount() {
        return vendedores.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public ListaEnlazada<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(ListaEnlazada<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Vendedor vendedor = null;
        try {
            vendedor = vendedores.get(rowIndex);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        return switch (columnIndex) {
            case 0 -> (vendedor != null) ? vendedor.getNombre() : "";
            case 1 -> (vendedor != null) ? vendedor.getApellido() : "";
            case 2 -> (vendedor != null) ? vendedor.getDni() : "";
            case 3 -> (vendedor != null) ? vendedor.getRuc(): "";
            default -> null;
        };
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Nombre";
            case 1 -> "Apellido";
            case 2 -> "DNI";
            case 3 -> "RUC";
            default -> null;
        };
    }
}
