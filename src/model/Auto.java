package model;

import lombok.Data;

@Data
public class Auto {
    // Atributos
    private Integer id;
    private String color;
    private Double precio;
    private String modelo;
    private String anio;

    // Relaciones
    private Integer id_marca;




    // toString
    public String toString() {
        return modelo;
    }
}
