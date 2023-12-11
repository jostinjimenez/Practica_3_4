package controller.controladores;

import controller.DAO.DataAccessObject;
import controller.tda_listas.ListaEnlazada;
import model.Vendedor;


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

}
