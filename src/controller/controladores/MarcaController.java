package controller.controladores;

import controller.DAO.DataAccessObject;
import controller.tda_listas.ListaEnlazada;
import controller.tda_listas.exceptions.VacioExceptions;
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

}
