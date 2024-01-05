package view.tablas;

import controller.controladores.AutoController;
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
        return 5;
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
        AutoController ac = new AutoController();
        try {
            auto = autos.get(rowIndex);

        } catch (VacioExceptions e) {
            throw new RuntimeException(e);
        }
        return switch (columnIndex) {
            case 0 -> (auto != null) ? auto.getId() : "";
            case 1 -> (auto != null) ? auto.getModelo() : "";
            //case 2 -> (auto != null && ac.getMarca(auto.getId_marca()) != null) ? ac.getMarca(auto.getId_marca()).getNombre() : "";
            case 2 -> (auto != null) ? auto.getId_marca() : "";
            case 3 -> (auto != null) ? auto.getColor() : "";
            case 4 -> (auto != null) ? auto.getAnio() : "";
            default -> null;
        };
    }

    public String getColumnName(int column) {
        return switch (column) {
            case 0 -> "Id";
            case 1 -> "Modelo";
            case 2 -> "Marca";
            case 3 -> "Color";
            case 4 -> "Anio";
            default -> null;
        };
    }
}
