package controller.controladores;

import controller.DAO.DataAccessObject;
import controller.tda_listas.ListaEnlazada;
import model.Auto;
import model.Vendedor;
import model.Venta;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    // Buscar por Busqueda Binaria
    public ListaEnlazada<Venta> buscarIdB(ListaEnlazada<Venta> lista, Integer id) throws Exception {
        ListaEnlazada<Venta> lo = this.ordenarQS(lista, 0, "id");
        Venta[] p = lo.toArray();
        ListaEnlazada<Venta> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getId().intValue() == id.intValue()) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && p[temp].getId().intValue() == id.intValue()) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && p[temp].getId().intValue() == id.intValue()) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (p[mid].getId().intValue() < id.intValue()) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public ListaEnlazada<Venta> buscarIdLB(ListaEnlazada<Venta> lista, Integer id) throws Exception {
        lista = this.ordenarQS(lista, 0, "id");

        int n = lista.getSize();
        int segmento = (int) Math.sqrt(n);
        Venta[] personas = lista.toArray();
        ListaEnlazada<Venta> result = new ListaEnlazada<>();

        int i;
        for (i = 0; i < n; i += segmento) {
            if (personas[Math.min(i + segmento, n) - 1].getId().intValue() >= id.intValue()) {
                break;
            }
        }

        if (i >= n) {
            return result;
        }

        int lo = i;
        int hi = Math.min(i + segmento, n);
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (personas[mid].getId().intValue() == id.intValue()) {
                result.add(personas[mid]);

                int aux = mid - 1;
                while (aux >= lo && personas[aux].getId().intValue() == id.intValue()) {
                    result.add(personas[aux]);
                    aux--;
                }

                aux = mid + 1;
                while (aux < hi && personas[aux].getId().intValue() == id.intValue()) {
                    result.add(personas[aux]);
                    aux++;
                }
                return result;
            } else if (personas[mid].getId().intValue() < id.intValue()) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return result;
    }

    // Buscar Total por Binario
    public ListaEnlazada<Venta> buscarTotalB(ListaEnlazada<Venta> lista, Double total) throws Exception {
        ListaEnlazada<Venta> lo = this.ordenarQS(lista, 0, "total");
        Venta[] p = lo.toArray();
        ListaEnlazada<Venta> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (p[mid].getTotal().doubleValue() == total.doubleValue()) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && p[temp].getTotal().doubleValue() == total.doubleValue()) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && p[temp].getTotal().doubleValue() == total.doubleValue()) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (p[mid].getTotal().doubleValue() < total.doubleValue()) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public ListaEnlazada<Venta> buscarTotalLB(ListaEnlazada<Venta> lista, Double total) throws Exception {
        lista = this.ordenarQS(lista, 0, "total");

        int n = lista.getSize();
        int segmento = (int) Math.sqrt(n);
        Venta[] personas = lista.toArray();
        ListaEnlazada<Venta> result = new ListaEnlazada<>();

        int i;
        for (i = 0; i < n; i += segmento) {
            if (personas[Math.min(i + segmento, n) - 1].getTotal().doubleValue() >= total.doubleValue()) {
                break;
            }
        }

        if (i >= n) {
            return result;
        }

        int lo = i;
        int hi = Math.min(i + segmento, n);
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (personas[mid].getTotal().doubleValue() == total.doubleValue()) {
                result.add(personas[mid]);

                int aux = mid - 1;
                while (aux >= lo && personas[aux].getTotal().doubleValue() == total.doubleValue()) {
                    result.add(personas[aux]);
                    aux--;
                }

                aux = mid + 1;
                while (aux < hi && personas[aux].getTotal().doubleValue() == total.doubleValue()) {
                    result.add(personas[aux]);
                    aux++;
                }
                return result;
            } else if (personas[mid].getTotal().doubleValue() < total.doubleValue()) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return result;
    }

    // Buscar Fecha por Binario
// Buscar Fecha por Binario
    public ListaEnlazada<Venta> buscarFechaB(ListaEnlazada<Venta> lista, String fecha) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = dateFormat.parse(fecha);

        ListaEnlazada<Venta> lo = this.ordenarQS(lista, 0, "fecha");
        Venta[] p = lo.toArray();
        ListaEnlazada<Venta> result = new ListaEnlazada<>();

        int left = 0;
        int right = lista.getSize() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            Date midDate = dateFormat.parse(dateFormat.format(p[mid].getFecha()));
            if (midDate.equals(fechaDate)) {
                result.add(p[mid]);

                int temp = mid - 1;
                while (temp >= left && dateFormat.parse(dateFormat.format(p[temp].getFecha())).equals(fechaDate)) {
                    result.add(p[temp]);
                    temp--;
                }

                temp = mid + 1;
                while (temp <= right && dateFormat.parse(dateFormat.format(p[temp].getFecha())).equals(fechaDate)) {
                    result.add(p[temp]);
                    temp++;
                }
                return result;
            }
            if (midDate.before(fechaDate)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public ListaEnlazada<Venta> buscarFechaLB(ListaEnlazada<Venta> lista, String fecha) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaDate = dateFormat.parse(fecha);

        lista = this.ordenarQS(lista, 0, "fecha");

        int n = lista.getSize();
        int segmento = (int) Math.sqrt(n);
        Venta[] personas = lista.toArray();
        ListaEnlazada<Venta> result = new ListaEnlazada<>();

        int i;
        for (i = 0; i < n; i += segmento) {
            if (dateFormat.parse(dateFormat.format(personas[Math.min(i + segmento, n) - 1].getFecha())).compareTo(fechaDate) >= 0) {
                break;
            }
        }

        if (i >= n) {
            return result;
        }

        int lo = i;
        int hi = Math.min(i + segmento, n);
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            Date midDate = dateFormat.parse(dateFormat.format(personas[mid].getFecha()));
            if (midDate.equals(fechaDate)) {
                result.add(personas[mid]);

                int aux = mid - 1;
                while (aux >= lo && dateFormat.parse(dateFormat.format(personas[aux].getFecha())).equals(fechaDate)) {
                    result.add(personas[aux]);
                    aux--;
                }

                aux = mid + 1;
                while (aux < hi && dateFormat.parse(dateFormat.format(personas[aux].getFecha())).equals(fechaDate)) {
                    result.add(personas[aux]);
                    aux++;
                }
                return result;
            } else if (midDate.before(fechaDate)) {
                lo = mid + 1;
            } else {
                hi = mid;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        VentaController venc = new VentaController();

        try {
            System.out.println(venc.buscarFechaB(venc.getVentas(), "2024-01-02").print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
