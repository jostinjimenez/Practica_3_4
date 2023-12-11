package model;

import lombok.Data;

@Data
public class Marca {
    private Integer id;
    private String nombre;
    private Boolean estado;

    public Marca() {

    }

    public Marca(Integer id, String nombre, Boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
