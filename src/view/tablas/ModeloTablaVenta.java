package view.tablas;

import controller.tda_listas.ListaEnlazada;
import controller.tda_listas.exceptions.VacioExceptions;
import model.Venta;

import javax.swing.table.AbstractTableModel;
import java.text.SimpleDateFormat;

public class ModeloTablaVenta extends AbstractTableModel {

    private ListaEnlazada<Venta> ventas;

    @Override
    public int getRowCount() {
        return ventas.getSize();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    public ListaEnlazada<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ListaEnlazada<Venta> ventas) {
        this.ventas = ventas;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Venta venta = null;
        try {
            venta = ventas.get(rowIndex);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        switch (columnIndex) {
            case 0 -> {
                return (venta != null) ? venta.getNro_venta() : "";
            }
            case 1 -> {
                return (venta != null) ?  "$" + venta.getTotal() : "";
            }
            case 2 -> {
                if (venta != null) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    return dateFormat.format(venta.getFecha());
                } else {
                    return "";
                }
            }
            case 3 -> {
                return (venta != null) ? venta.getId_vendedor() : "";
            }
            case 4 -> {
                return (venta != null) ? venta.getId_auto() : "";
            }
            default -> {
                return null;
            }
        }
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Nro. Venta";
            case 1 -> "Total";
            case 2 -> "Fecha";
            case 3 -> "Id Vendedor";
            case 4 -> "Id Auto";
            default -> null;
        };
    }
}
