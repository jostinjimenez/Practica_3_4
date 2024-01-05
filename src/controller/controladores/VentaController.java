package controller.controladores;

import controller.DAO.DataAccessObject;
import controller.tda_listas.ListaEnlazada;
import model.Auto;
import model.Vendedor;
import model.Venta;

import java.lang.reflect.Field;

import static controller.controladores.util.Utilidades.getField;

public class VentaController extends DataAccessObject<Venta> {

    // Atributos
    private ListaEnlazada<Venta> ventas;
    private Venta venta = new Venta();
    private Integer index = -1;

    // Constructor
    public VentaController() {
        super(Venta.class);
        this.ventas = new ListaEnlazada<>();
    }

    public ListaEnlazada<Venta> getVentas() {
        if (ventas.isEmpty()) {
            ventas = this.list_All();
        }
        return ventas;
    }

    // Getters y Setters
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public void setVentas(ListaEnlazada<Venta> ventas) {
        this.ventas = ventas;
    }

    public Venta getVenta() {
        return venta != null ? venta : new Venta();
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    // Metodos
    public Boolean guardar() {
        this.venta.setId(generarID());
        return save(venta);
    }

    public Boolean update(Integer index) {
        return update(venta, index);
    }


    public String generatedCode() {
        StringBuilder code = new StringBuilder();
        Integer legth = list_All().getSize() + 1;
        int pos = Integer.toString(legth).length();
        code.append("0".repeat(Math.max(0, (10 - pos))));
        code.append(legth);
        return code.toString();
    }


    // Ordenar por QuickSort
    public ListaEnlazada<Venta> ordenarQS(ListaEnlazada<Venta> lista, Integer type, String field) throws Exception {
        Venta[] ventas = lista.toArray();
        Field faux = getField(Venta.class, field);
        if (faux != null) {
            quickSort(ventas, 0, ventas.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(ventas);
    }

    private void quickSort(Venta[] v, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(v, primero, ultimo, type, field);

            quickSort(v, primero, pi - 1, type, field);
            quickSort(v, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Venta[] v, int primero, int ultimo, Integer type, String field) throws Exception {
        Venta pivot = v[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (v[j].compareTo(pivot, field, type)) {
                i++;
                Venta temp = v[i];
                v[i] = v[j];
                v[j] = temp;
            }
        }

        Venta aux = v[i + 1];
        v[i + 1] = v[ultimo];
        v[ultimo] = aux;

        return i + 1;
    }

    // Ordenar por MergeSort
    public ListaEnlazada<Venta> ordenarMS(ListaEnlazada<Venta> lista, Integer type, String field) throws Exception {
        Venta[] ventas = lista.toArray();
        Field faux = getField(Venta.class, field);
        if (faux != null) {
            mergeSort(ventas, 0, ventas.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(ventas);
    }

    private void mergeSort(Venta[] v, int l, int r, Integer type, String field) throws Exception {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(v, l, m, type, field);
            mergeSort(v, m + 1, r, type, field);

            merge(v, l, m, r, type, field);
        }
    }

    private void merge(Venta[] v, int l, int m, int r, Integer type, String field) throws Exception {
        int n1 = m - l + 1;
        int n2 = r - m;

        Venta[] L = new Venta[n1];
        Venta[] R = new Venta[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = v[l + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = v[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j], field, type)) {
                v[k] = L[i];
                i++;
            } else {
                v[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            v[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            v[k] = R[j];
            j++;
            k++;
        }
    }

}
