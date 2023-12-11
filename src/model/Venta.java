package model;

import lombok.Data;

@Data
public class Venta {
    // Atributos
    private Integer id;
    private Double subtotal;
    private Double iva;
    private Double total;
    private String fecha;
    private String nro_venta;

    // Relaciones
    private Integer id_auto;
    private Integer id_vendedor;


}