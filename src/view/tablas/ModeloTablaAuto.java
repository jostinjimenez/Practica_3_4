package view.tablas;

import controller.tda_listas.ListaEnlazada;
import controller.tda_listas.exceptions.VacioExceptions;
import model.Auto;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaAuto extends AbstractTableModel {

    private ListaEnlazada<Auto> autos;

    @Override
    public int getRowCount() {
        return autos.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    public ListaEnlazada<Auto> getAutos() {
        return autos;
    }

    public void setAutos(ListaEnlazada<Auto> autos) {
        this.autos = autos;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Auto auto = null;
        try {
            auto = autos.get(rowIndex);
        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        return switch (columnIndex) {
            case 0 -> (auto != null) ? auto.getId_marca().toString() : "";
            case 1 -> (auto != null) ? auto.getModelo() : "";
            case 2 -> (auto != null) ? auto.getColor() : "";
            case 3 -> (auto != null) ? auto.getAnio(): "";
            default -> null;
        };
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Marca";
            case 1 -> "Modelo";
            case 2 -> "Color";
            case 3 -> "Anio";
            default -> null;
        };
    }
}
