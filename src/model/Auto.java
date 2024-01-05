package model;

import lombok.Data;

@Data
public class Auto {
    // Atributos
    private Integer id;
    private String color;
    private String modelo;
    private String anio;

    // Relaciones
    private Integer id_marca;

    // toString
    public String toString() {
        return id + " " + color + " " + " " + modelo + " " + anio + " " + id_marca;
    }

    // Metodos
    public Boolean compareTo(Auto a, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("color")) {
                    return this.getColor().compareTo(a.getColor()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() > (a.getId());
                } else if (field.equalsIgnoreCase("id_marca")) {
                    return this.getId_marca().compareTo(a.getId_marca()) > 0;
                } else if (field.equalsIgnoreCase("modelo")) {
                    return this.getModelo().compareTo(a.getModelo()) > 0;
                } else if (field.equalsIgnoreCase("anio")) {
                    return this.getAnio().compareTo(a.getAnio()) > 0;
                }
            case 0:
                if (field.equalsIgnoreCase("color")) {
                    return this.getColor().compareTo(a.getColor()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() < (a.getId());
                } else if (field.equalsIgnoreCase("id_marca")) {
                    return this.getId_marca().compareTo(a.getId_marca()) < 0;
                } else if (field.equalsIgnoreCase("modelo")) {
                    return this.getModelo().compareTo(a.getModelo()) < 0;
                } else if (field.equalsIgnoreCase("anio")) {
                    return this.getAnio().compareTo(a.getAnio()) < 0;
                }
            default:
                return false;
        }
    }
}
