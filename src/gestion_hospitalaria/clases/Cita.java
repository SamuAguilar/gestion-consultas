package gestion_hospitalaria.clases;

import java.sql.Time;
import java.util.Date;

public class Cita {
    private int idCita;
    private Paciente paciente;
    private Medico medico;
    private Date fecha;
    private Time hora;
    private String descripcion;

    public Cita() {
    }

    public Cita(int idCita, Paciente paciente, Medico medico, Date fecha, Time hora, String descripcion) {
        this.idCita = idCita;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
        this.descripcion = descripcion;
    }

    public int getIdCita() {
        return idCita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public Date getFecha() {
        return fecha;
    }

    public Time getHora() {
        return hora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

