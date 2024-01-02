package controller.controladores;

import controller.DAO.DataAccessObject;
import controller.tda_listas.ListaEnlazada;
import model.Auto;
import model.Vendedor;

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

}