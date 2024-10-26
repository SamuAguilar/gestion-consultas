package gestion_hospitalaria.clases;

public class Medico extends Persona {
    private int idMedico;
    private String especialidad;

    public Medico() {
    }

    public Medico(int id, String nombre, String telefono, String correo, String especialidad) {
        super(id, nombre, telefono, correo);
        this.especialidad = especialidad;
        this.idMedico = id;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public int getIdMedico() {
        return getId();
    }

    public void setIdMedico(int idMedico) {
        setId(idMedico);
    }
}