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
        return 5;
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
            case 0 -> (vendedor != null) ? vendedor.getId(): "";
            case 1 -> (vendedor != null) ? vendedor.getNombre() : "";
            case 2 -> (vendedor != null) ? vendedor.getApellido() : "";
            case 3 -> (vendedor != null) ? vendedor.getDni() : "";
            case 4 -> (vendedor != null) ? vendedor.getRuc(): "";
            default -> null;
        };
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Id";
            case 1 -> "Nombre";
            case 2 -> "Apellido";
            case 3 -> "DNI";
            case 4 -> "RUC";
            default -> null;
        };
    }
}
