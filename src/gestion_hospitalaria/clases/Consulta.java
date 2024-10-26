
package gestion_hospitalaria.clases;

public class Consulta {
    private int idConsulta;
    private String fecha;
    private String hora;
    private String medico;
    private String paciente;

    // Constructor sin argumentos
    public Consulta() {
        
    }

    // Constructor con argumentos
    public Consulta(int idConsulta, String paciente, String medico, String fecha, String hora) {
        this.idConsulta = idConsulta;
        this.paciente = paciente;
        this.medico = medico;
        this.fecha = fecha;
        this.hora = hora;
    }

    // Getters y setters
    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }
}
