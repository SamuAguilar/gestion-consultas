package gestion_hospitalaria.iu.paneles;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import gestion_hospitalaria.clases.Cita;
import gestion_hospitalaria.clases.Consulta;
import gestion_hospitalaria.clases.Medico;
import gestion_hospitalaria.clases.Paciente;

public class Datos {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static final Medico[] MEDICOS = new Medico[10];
    public static final Paciente[] PACIENTES = new Paciente[100];
    public static final Cita[] CITAS = new Cita[100];
    public static final Consulta[] CONSULTAS = new Consulta[100];

    static {
        //MEDICOS[0] = new Medico(1, "Dr. Juan Pérez", "123456789", "juan.perez@ejemplo.com", "Cardiología");
        MEDICOS[0] = new Medico(1, "doc", "123456789", "juan.perez@ejemplo.com", "Cardiología");
        MEDICOS[1] = new Medico(2, "Dra. Ana Gómez", "987654321", "ana.gomez@ejemplo.com", "Neurología");
        MEDICOS[2] = new Medico(3, "Dr. Luis Fernández", "456789123", "luis.fernandez@ejemplo.com", "Pediatría");
        MEDICOS[3] = new Medico(4, "Dra. María Rodríguez", "321654987", "maria.rodriguez@ejemplo.com", "Ginecología");
        MEDICOS[4] = new Medico(5, "Dr. Carlos López", "654321987", "carlos.lopez@ejemplo.com", "Ortopedia");

        //PACIENTES[0] = new Paciente(1, "Ana Gómez", "9876543210", "ana.gomez@gmail.com", java.sql.Date.valueOf(LocalDate.parse("01-01-1980", formatter)), "Calle 123");
        PACIENTES[0] = new Paciente(1, "pac", "9876543210", "ana.gomez@gmail.com", java.sql.Date.valueOf(LocalDate.parse("01-01-1980", formatter)), "Calle 123");
        PACIENTES[1] = new Paciente(2, "Luis Pérez", "9876543211", "luis.perez@gmail.com", java.sql.Date.valueOf(LocalDate.parse("02-02-1981", formatter)), "Avenida 456");
        PACIENTES[2] = new Paciente(3, "María Díaz", "9876543212", "maria.diaz@gmail.com", java.sql.Date.valueOf(LocalDate.parse("03-03-1982", formatter)), "Calle 789");
        PACIENTES[3] = new Paciente(4, "Carlos Ruiz", "9876543213", "carlos.ruiz@gmail.com", java.sql.Date.valueOf(LocalDate.parse("04-04-1983", formatter)), "Avenida 101");
        PACIENTES[4] = new Paciente(5, "Lucía López", "9876543214", "lucia.lopez@gmail.com", java.sql.Date.valueOf(LocalDate.parse("05-05-1984", formatter)), "Calle 112");
        PACIENTES[5] = new Paciente(6, "Javier Morales", "9876543215", "javier.morales@gmail.com", java.sql.Date.valueOf(LocalDate.parse("06-06-1985", formatter)), "Avenida 131");
        PACIENTES[6] = new Paciente(7, "Sofía Torres", "9876543216", "sofia.torres@gmail.com", java.sql.Date.valueOf(LocalDate.parse("07-07-1986", formatter)), "Calle 415");
        PACIENTES[7] = new Paciente(8, "Diego Fernández", "9876543217", "diego.fernandez@gmail.com", java.sql.Date.valueOf(LocalDate.parse("08-08-1987", formatter)), "Avenida 161");
        PACIENTES[8] = new Paciente(9, "Andrea Castillo", "9876543218", "andrea.castillo@gmail.com", java.sql.Date.valueOf(LocalDate.parse("09-09-1988", formatter)), "Calle 718");
        PACIENTES[9] = new Paciente(10, "Ricardo Herrera", "9876543219", "ricardo.herrera@gmail.com", java.sql.Date.valueOf(LocalDate.parse("10-10-1989", formatter)), "Avenida 920");
        PACIENTES[10] = new Paciente(11, "Martina Aguirre", "9876543220", "martina.aguirre@gmail.com", java.sql.Date.valueOf(LocalDate.parse("11-11-1990", formatter)), "Calle 111");
        PACIENTES[11] = new Paciente(12, "Lucas Vega", "9876543221", "lucas.vega@gmail.com", java.sql.Date.valueOf(LocalDate.parse("12-12-1991", formatter)), "Avenida 222");
        PACIENTES[12] = new Paciente(13, "Marta Ortiz", "9876543222", "marta.ortiz@gmail.com", java.sql.Date.valueOf(LocalDate.parse("13-12-1992", formatter)), "Calle 333");
        PACIENTES[13] = new Paciente(14, "Sergio Gil", "9876543223", "sergio.gil@gmail.com", java.sql.Date.valueOf(LocalDate.parse("14-12-1993", formatter)), "Avenida 444");
        PACIENTES[14] = new Paciente(15, "Isabel Ramos", "9876543224", "isabel.ramos@gmail.com", java.sql.Date.valueOf(LocalDate.parse("15-12-1994", formatter)), "Calle 555");

        CITAS[0] = new Cita(1, PACIENTES[14], MEDICOS[4], java.sql.Date.valueOf(LocalDate.of(2024, 8, 31)), java.sql.Time.valueOf(LocalTime.of(9, 15, 0)), "Consulta general");
        CITAS[1] = new Cita(2, PACIENTES[14], MEDICOS[3], java.sql.Date.valueOf(LocalDate.of(2024, 9, 5)), java.sql.Time.valueOf(LocalTime.of(14, 30, 0)), "Consulta general");
        CITAS[2] = new Cita(3, PACIENTES[1], MEDICOS[1], java.sql.Date.valueOf(LocalDate.of(2024, 8, 28)), java.sql.Time.valueOf(LocalTime.of(10, 45, 0)), "Migraña");
        CITAS[3] = new Cita(4, PACIENTES[9], MEDICOS[4], java.sql.Date.valueOf(LocalDate.of(2024, 9, 12)), java.sql.Time.valueOf(LocalTime.of(11, 0, 0)), "Dolor al caminar");
        CITAS[4] = new Cita(5, PACIENTES[7], MEDICOS[1], java.sql.Date.valueOf(LocalDate.of(2024, 8, 22)), java.sql.Time.valueOf(LocalTime.of(12, 30, 0)), "H.T.A");
        CITAS[5] = new Cita(6, PACIENTES[3], MEDICOS[0], java.sql.Date.valueOf(LocalDate.of(2024, 9, 1)), java.sql.Time.valueOf(LocalTime.of(8, 45, 0)), "M.A.P.A.");
        CITAS[6] = new Cita(7, PACIENTES[8], MEDICOS[3], java.sql.Date.valueOf(LocalDate.of(2024, 8, 25)), java.sql.Time.valueOf(LocalTime.of(13, 15, 0)), "Consulta general");
        CITAS[7] = new Cita(8, PACIENTES[2], MEDICOS[2], java.sql.Date.valueOf(LocalDate.of(2024, 9, 8)), java.sql.Time.valueOf(LocalTime.of(10, 0, 0)), "Consulta general");
        CITAS[8] = new Cita(9, PACIENTES[5], MEDICOS[2], java.sql.Date.valueOf(LocalDate.of(2024, 8, 29)), java.sql.Time.valueOf(LocalTime.of(14, 45, 0)), "Consulta general");
        CITAS[9] = new Cita(10, PACIENTES[6], MEDICOS[3], java.sql.Date.valueOf(LocalDate.of(2024, 9, 15)), java.sql.Time.valueOf(LocalTime.of(11, 45, 0)), "Consulta general");
        CITAS[10] = new Cita(11, PACIENTES[10], MEDICOS[1], java.sql.Date.valueOf(LocalDate.of(2024, 8, 18)), java.sql.Time.valueOf(LocalTime.of(9, 0, 0)), "Consulta general");
        CITAS[11] = new Cita(12, PACIENTES[13], MEDICOS[3], java.sql.Date.valueOf(LocalDate.of(2024, 9, 22)), java.sql.Time.valueOf(LocalTime.of(8, 15, 0)), "Ecocardiograma");
        CITAS[12] = new Cita(13, PACIENTES[12], MEDICOS[2], java.sql.Date.valueOf(LocalDate.of(2024, 8, 26)), java.sql.Time.valueOf(LocalTime.of(12, 15, 0)), "Consulta general");
        CITAS[13] = new Cita(14, PACIENTES[11], MEDICOS[1], java.sql.Date.valueOf(LocalDate.of(2024, 9, 10)), java.sql.Time.valueOf(LocalTime.of(13, 0, 0)), "Consulta general");
        CITAS[14] = new Cita(15, PACIENTES[4], MEDICOS[3], java.sql.Date.valueOf(LocalDate.of(2024, 9, 20)), java.sql.Time.valueOf(LocalTime.of(15, 45, 0)), "Consulta general");

        CONSULTAS[0] = new Consulta(1, PACIENTES[6].getNombre(), MEDICOS[1].getNombre(), "2024-08-20", "09:15:00");
        CONSULTAS[1] = new Consulta(2, PACIENTES[14].getNombre(), MEDICOS[3].getNombre(), "2024-09-05", "14:00:00");
        CONSULTAS[2] = new Consulta(3, PACIENTES[1].getNombre(), MEDICOS[1].getNombre(), "2024-08-28", "10:45:00");
        CONSULTAS[3] = new Consulta(4, PACIENTES[9].getNombre(), MEDICOS[4].getNombre(), "2024-09-12", "11:15:00");
        CONSULTAS[4] = new Consulta(5, PACIENTES[7].getNombre(), MEDICOS[4].getNombre(), "2024-08-22", "12:00:00");
        CONSULTAS[5] = new Consulta(6, PACIENTES[3].getNombre(), MEDICOS[2].getNombre(), "2024-09-01", "08:45:00");
        CONSULTAS[6] = new Consulta(7, PACIENTES[8].getNombre(), MEDICOS[3].getNombre(), "2024-08-25", "13:15:00");
        CONSULTAS[7] = new Consulta(8, PACIENTES[2].getNombre(), MEDICOS[2].getNombre(), "2024-09-08", "10:30:00");
        CONSULTAS[8] = new Consulta(9, PACIENTES[5].getNombre(), MEDICOS[1].getNombre(), "2024-08-29", "14:45:00");
        CONSULTAS[9] = new Consulta(10, PACIENTES[6].getNombre(), MEDICOS[3].getNombre(), "2024-09-15", "11:45:00");
        CONSULTAS[10] = new Consulta(11, PACIENTES[10].getNombre(), MEDICOS[1].getNombre(), "2024-08-18", "09:00:00");
        CONSULTAS[11] = new Consulta(12, PACIENTES[13].getNombre(), MEDICOS[3].getNombre(), "2024-09-22", "08:15:00");
        CONSULTAS[12] = new Consulta(13, PACIENTES[12].getNombre(), MEDICOS[2].getNombre(), "2024-08-26", "12:15:00");
        CONSULTAS[13] = new Consulta(14, PACIENTES[11].getNombre(), MEDICOS[1].getNombre(), "2024-09-10", "13:30:00");
        CONSULTAS[14] = new Consulta(15, PACIENTES[5].getNombre(), MEDICOS[3].getNombre(), "2024-09-20", "15:45:00");
    }

    ;
    

    public static Medico getMedico(int index) {
        return MEDICOS[index];
    }

    public static Paciente getPaciente(int index) {
        return PACIENTES[index];
    }

    public static Cita getCita(int index) {
        return CITAS[index];
    }

    public static Consulta getConsulta(int index) {
        return CONSULTAS[index];
    }
}
