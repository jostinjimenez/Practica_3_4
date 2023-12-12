package controller.controladores;

import controller.DAO.DataAccessObject;
import controller.tda_listas.ListaEnlazada;
import model.Marca;

public class MarcaController extends DataAccessObject<Marca> {
    private ListaEnlazada<Marca> marcas;
    private Marca marca = new Marca();

    public MarcaController() {
        super(Marca.class);
        this.marcas = new ListaEnlazada<>();
    }

    public ListaEnlazada<Marca> getMarcas() {
        if (marcas.isEmpty()) {
            marcas = this.list_All();
        }
        return marcas;
    }

    public void setMarcas(ListaEnlazada<Marca> marcas) {
        this.marcas = marcas;
    }

    public Marca getMarca() {
        if (marca == null) {
            marca = new Marca();
        }
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Boolean save() {
        this.marca.setId(generarID());
        return this.save(marca);
    }

    // Ordenar por QuickSort
    public ListaEnlazada<Marca> ordenar(Integer type) {
        getMarcas();
        Integer n = marcas.getSize();
        Marca[] m = marcas.toArray();

        quickSort(m, 0, n - 1, type);

        marcas = new ListaEnlazada<>();
        for (Marca marca : m) {
            marcas.add(marca);
        }
        return marcas;
    }

    /*public static void main(String[] args) {
        MarcaController mc = new MarcaController();

        mc.getMarca().setId(1);
        mc.getMarca().setNombre("Toyota");
        mc.getMarca().setEstado(true);
        mc.save();

        mc.getMarca().setId(2);
        mc.getMarca().setNombre("Nissan");
        mc.getMarca().setEstado(true);
        mc.save();

        mc.getMarca().setId(3);
        mc.getMarca().setNombre("Mazda");
        mc.getMarca().setEstado(true);
        mc.save();

        mc.getMarca().setId(4);
        mc.getMarca().setNombre("Honda");
        mc.getMarca().setEstado(true);
        mc.save();

        mc.getMarca().setId(5);
        mc.getMarca().setNombre("Hyundai");
        mc.getMarca().setEstado(true);
        mc.save();

        mc.getMarca().setId(6);
        mc.getMarca().setNombre("Kia");
        mc.getMarca().setEstado(true);
        mc.save();

        mc.getMarca().setId(7);
        mc.getMarca().setNombre("Chevrolet");
        mc.getMarca().setEstado(true);
        mc.save();
    }*/

    private void quickSort(Marca[] m, int izq, int der, Integer type) {
        Marca pivote = m[izq];
        int i = izq;
        int j = der;
        Marca aux;

        while (i < j) {
            if (type == 1) {
                while (m[i].getNombre().compareTo(pivote.getNombre()) <= 0 && i < j) {
                    i++;
                }
                while (m[j].getNombre().compareTo(pivote.getNombre()) > 0) {
                    j--;
                }
            } else {
                while (m[i].getNombre().compareTo(pivote.getNombre()) >= 0 && i < j) {
                    i++;
                }
                while (m[j].getNombre().compareTo(pivote.getNombre()) < 0) {
                    j--;
                }
            }

            if (i < j) {
                aux = m[i];
                m[i] = m[j];
                m[j] = aux;
            }
        }
        m[izq] = m[j];
        m[j] = pivote;
        if (izq < j - 1) {
            quickSort(m, izq, j - 1, type);
        }
        if (j + 1 < der) {
            quickSort(m, j + 1, der, type);
        }
    }

}
