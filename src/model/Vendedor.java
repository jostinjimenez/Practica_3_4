package model;

import lombok.Data;

@Data
public class Vendedor {
    // Atributos
    private Integer id;
    private String nombre;
    private String apellido;
    private String dni;
    private String telefono;
    private String Ruc;
    private String Email;


    // toString
    @Override
    public String toString() {
        return nombre;
    }
}
