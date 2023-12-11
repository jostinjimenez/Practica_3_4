package controller.controladores;

import controller.DAO.DataAccessObject;
import controller.tda_listas.ListaEnlazada;
import model.Venta;

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
    public Boolean agregarVenta() {
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


}
