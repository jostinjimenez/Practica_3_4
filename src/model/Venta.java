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


}