package model;

import lombok.Data;

import java.util.Date;

@Data
public class Venta {
    // Atributos
    private Integer id;
    private Double total;
    private Date fecha;
    private String nro_venta;
    private String descripcion;

    // Relaciones
    private Integer id_auto;
    private Integer id_vendedor;

    // Metodos
    public Boolean compareTo(Venta c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("total")) {
                    return this.getTotal() > (c.getTotal());
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() > (c.getId());
                } else if (field.equalsIgnoreCase("fecha")) {
                    return this.getFecha().compareTo(c.getFecha()) > 0;
                } else if (field.equalsIgnoreCase("nro_venta")) {
                    return this.getNro_venta().compareTo(c.getNro_venta()) > 0;
                } else if (field.equalsIgnoreCase("descripcion")) {
                    return this.getDescripcion().compareTo(c.getDescripcion()) > 0;
                } else if (field.equalsIgnoreCase("id_auto")) {
                    return this.getId_auto().compareTo(c.getId_auto()) > 0;
                } else if (field.equalsIgnoreCase("id_vendedor")) {
                    return this.getId_vendedor().compareTo(c.getId_vendedor()) > 0;
                }
            case 0:
                if (field.equalsIgnoreCase("total")) {
                    return this.getTotal() < (c.getTotal());
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() < (c.getId());
                } else if (field.equalsIgnoreCase("fecha")) {
                    return this.getFecha().compareTo(c.getFecha()) < 0;
                } else if (field.equalsIgnoreCase("nro_venta")) {
                    return this.getNro_venta().compareTo(c.getNro_venta()) < 0;
                } else if (field.equalsIgnoreCase("descripcion")) {
                    return this.getDescripcion().compareTo(c.getDescripcion()) < 0;
                } else if (field.equalsIgnoreCase("id_auto")) {
                    return this.getId_auto().compareTo(c.getId_auto()) < 0;
                } else if (field.equalsIgnoreCase("id_vendedor")) {
                    return this.getId_vendedor().compareTo(c.getId_vendedor()) < 0;
                }
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", total=" + total +
                ", fecha=" + fecha +
                ", nro_venta='" + nro_venta + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", id_auto=" + id_auto +
                ", id_vendedor=" + id_vendedor +
                '}';
    }
}