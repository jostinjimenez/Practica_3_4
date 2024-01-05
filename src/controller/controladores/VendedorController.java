package controller.controladores;

import controller.DAO.DataAccessObject;
import controller.tda_listas.ListaEnlazada;
import model.Auto;
import model.Vendedor;

import java.lang.reflect.Field;

import static controller.controladores.util.Utilidades.getField;


public class VendedorController extends DataAccessObject<Vendedor> {
    // Atributos
    private ListaEnlazada<Vendedor> vendedores;
    private Vendedor vendedor = new Vendedor();
    private Integer index = -1;

    // Constructor
    public VendedorController() {
        super(Vendedor.class);
        this.vendedores = new ListaEnlazada<>();
    }

    // Getters y Setters
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public ListaEnlazada<Vendedor> getVendedores() {
        if (vendedores.isEmpty()) {
            vendedores = this.list_All();
        }
        return vendedores;
    }

    public void setVendedores(ListaEnlazada<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public Vendedor getVendedor() {
        return vendedor != null ? vendedor : new Vendedor();
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    // Metodos
    public Boolean guardar() {
        this.vendedor.setId(generarID());
        return save(vendedor);
    }

    public Boolean update(Integer index) {
        return update(vendedor, index);
    }

    public boolean existe() {
        for (Vendedor vendedorExistente : getVendedores()) {
            if (vendedorExistente.getId().equals(vendedor.getId())) {
                return true;
            }
        }
        return false;
    }

    // Ordenar por QuickSort
    public ListaEnlazada<Vendedor> ordenarQS(ListaEnlazada<Vendedor> lista, Integer type, String field) throws Exception {
        Vendedor[] vendedores = lista.toArray();
        Field faux = getField(Vendedor.class, field);
        if (faux != null) {
            quickSort(vendedores, 0, vendedores.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(vendedores);
    }

    private void quickSort(Vendedor[] v, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(v, primero, ultimo, type, field);

            quickSort(v, primero, pi - 1, type, field);
            quickSort(v, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Vendedor[] v, int primero, int ultimo, Integer type, String field) throws Exception {
        Vendedor pivot = v[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (v[j].compareTo(pivot, field, type)) {
                i++;
                Vendedor temp = v[i];
                v[i] = v[j];
                v[j] = temp;
            }
        }

        Vendedor aux = v[i + 1];
        v[i + 1] = v[ultimo];
        v[ultimo] = aux;

        return i + 1;
    }

    // Ordenar por MergeSort
    public ListaEnlazada<Vendedor> ordenarMS(ListaEnlazada<Vendedor> lista, Integer type, String field) throws Exception {
        Vendedor[] vendedores = lista.toArray();
        Field faux = getField(Auto.class, field);
        if (faux != null) {
            mergeSort(vendedores, 0, vendedores.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(vendedores);
    }

    private void mergeSort(Vendedor[] v, int l, int r, Integer type, String field) throws Exception {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(v, l, m, type, field);
            mergeSort(v, m + 1, r, type, field);

            merge(v, l, m, r, type, field);
        }
    }

    private void merge(Vendedor[] v, int l, int m, int r, Integer type, String field) throws Exception {
        int n1 = m - l + 1;
        int n2 = r - m;

        Vendedor[] L = new Vendedor[n1];
        Vendedor[] R = new Vendedor[n2];

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
