package gestion_hospitalaria.clases;

import java.util.Date;

public class Paciente extends Persona {
    private Date fechaNacimiento;
    private String direccion;

    public Paciente() {
    }

    public Paciente(int id, String nombre, String telefono, String correo, Date fechaNacimiento, String direccion) {
        super(id, nombre, telefono, correo);
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    
    
}