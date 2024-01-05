package controller.controladores;

import controller.DAO.DataAccessObject;
import controller.tda_listas.ListaEnlazada;
import model.Auto;
import model.Marca;

import java.lang.reflect.Field;

import static controller.controladores.util.Utilidades.getField;

public class AutoController extends DataAccessObject<Auto> {
    // Atributos
    private ListaEnlazada<Auto> autos;
    private Auto auto = new Auto();
    private Integer index = -1;

    // Constructor
    public AutoController() {
        super(Auto.class);
        this.autos = new ListaEnlazada<>();
    }

    // Getters y Setters
    public ListaEnlazada<Auto> getAutos() {
        if (autos.isEmpty()) {
            autos = this.list_All();
        }
        return autos;
    }

    public void setAutos(ListaEnlazada<Auto> autos) {
        this.autos = autos;
    }

    public Auto getAuto() {
        return auto != null ? auto : new Auto();
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    // Metodos
    public Boolean guardar() {
        this.auto.setId(generarID());
        return save(auto);
    }

    public Boolean update(Integer index) {
        return update(auto, index);
    }

    public boolean existe() {
        for (Auto au : getAutos()) {
            if (au.getId().equals(auto.getId())) {
                return true;
            }
        }
        return false;
    }


    // Ordenar por QuickSort
    public ListaEnlazada<Auto> ordenarQS(ListaEnlazada<Auto> lista, Integer type, String field) throws Exception {
        Auto[] autos = lista.toArray();
        Field faux = getField(Auto.class, field);
        if (faux != null) {
            quickSort(autos, 0, autos.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(autos);
    }

    private void quickSort(Auto[] a, int primero, int ultimo, Integer type, String field) throws Exception {
        if (primero < ultimo) {
            int pi = partition(a, primero, ultimo, type, field);

            quickSort(a, primero, pi - 1, type, field);
            quickSort(a, pi + 1, ultimo, type, field);
        }
    }

    private int partition(Auto[] a, int primero, int ultimo, Integer type, String field) throws Exception {
        Auto pivot = a[ultimo];
        int i = (primero - 1);

        for (int j = primero; j < ultimo; j++) {
            if (a[j].compareTo(pivot, field, type)) {
                i++;
                Auto temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }

        Auto aux = a[i + 1];
        a[i + 1] = a[ultimo];
        a[ultimo] = aux;

        return i + 1;
    }

    // Ordenar por MergeSort
    public ListaEnlazada<Auto> ordenarMS(ListaEnlazada<Auto> lista, Integer type, String field) throws Exception {
        Auto[] autos = lista.toArray();
        Field faux = getField(Auto.class, field);
        if (faux != null) {
            mergeSort(autos, 0, autos.length - 1, type, field);
        } else {
            throw new Exception("El atributo no existe");
        }
        return lista.toList(autos);
    }

    private void mergeSort(Auto[] a, int l, int r, Integer type, String field) throws Exception {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(a, l, m, type, field);
            mergeSort(a, m + 1, r, type, field);

            merge(a, l, m, r, type, field);
        }
    }

    private void merge(Auto[] a, int l, int m, int r, Integer type, String field) throws Exception {
        int n1 = m - l + 1;
        int n2 = r - m;

        Auto[] L = new Auto[n1];
        Auto[] R = new Auto[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = a[l + i];
        }

        for (int j = 0; j < n2; j++) {
            R[j] = a[m + 1 + j];
        }

        int i = 0, j = 0;

        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j], field, type)) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }


    public static void main(String[] args) {
        AutoController ac = new AutoController();
//        ac.getAuto().setId(1);
//        ac.getAuto().setModelo("2019");
//        ac.getAuto().setId_marca(1);
//        ac.save(ac.getAuto());
//
//        ac.getAuto().setId(2);
//        ac.getAuto().setModelo("2018");
//        ac.getAuto().setId_marca(2);
//        ac.save(ac.getAuto());
//
//        ac.getAuto().setId(3);
//        ac.getAuto().setModelo("2017");
//        ac.getAuto().setId_marca(3);
//        ac.save(ac.getAuto());
//
//        ac.getAuto().setId(4);
//        ac.getAuto().setModelo("2016");
//        ac.getAuto().setId_marca(4);
//        ac.save(ac.getAuto());
//
//        ac.getAuto().setId(5);
//        ac.getAuto().setModelo("2015");
//        ac.getAuto().setId_marca(5);
//        ac.save(ac.getAuto());
//
//        ac.getAuto().setId(6);
//        ac.getAuto().setModelo("2014");
//        ac.getAuto().setId_marca(6);
//        ac.save(ac.getAuto());
//
//        ac.getAuto().setId(7);
//        ac.getAuto().setModelo("2013");
//        ac.getAuto().setId_marca(7);
//        ac.save(ac.getAuto());

//        System.out.println("Ordenamiento por QuickSort");
//        System.out.println("--------------------------------");
//        try {
//            System.out.println(ac.ordenarMS(ac.getAutos(), 0, "id").print());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    }
}


