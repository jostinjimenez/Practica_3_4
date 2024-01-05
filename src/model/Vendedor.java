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

    // Metodos

    public Boolean compareTo(Vendedor c, String field, Integer type) {
        switch (type) {
            case 1:
                if (field.equalsIgnoreCase("nombre")) {
                    return this.getNombre().compareTo(c.getNombre()) > 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() > (c.getId());
                } else if (field.equalsIgnoreCase("apellido")) {
                    return this.getApellido().compareTo(c.getApellido()) > 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return this.getDni().compareTo(c.getDni()) > 0;
                } else if (field.equalsIgnoreCase("telefono")) {
                    return this.getTelefono().compareTo(c.getTelefono()) > 0;
                } else if (field.equalsIgnoreCase("Ruc")) {
                    return this.getRuc().compareTo(c.getRuc()) > 0;
                } else if (field.equalsIgnoreCase("Email")) {
                    return this.getEmail().compareTo(c.getEmail()) > 0;
                }
            case 0:
                if (field.equalsIgnoreCase("nombre")) {
                    return this.getNombre().compareTo(c.getNombre()) < 0;
                } else if (field.equalsIgnoreCase("id")) {
                    return this.getId() < (c.getId());
                } else if (field.equalsIgnoreCase("apellido")) {
                    return this.getApellido().compareTo(c.getApellido()) < 0;
                } else if (field.equalsIgnoreCase("dni")) {
                    return this.getDni().compareTo(c.getDni()) < 0;
                } else if (field.equalsIgnoreCase("telefono")) {
                    return this.getTelefono().compareTo(c.getTelefono()) < 0;
                } else if (field.equalsIgnoreCase("Ruc")) {
                    return this.getRuc().compareTo(c.getRuc()) < 0;
                } else if (field.equalsIgnoreCase("Email")) {
                    return this.getEmail().compareTo(c.getEmail()) < 0;
                }
            default:
                return false;
        }
    }
}
