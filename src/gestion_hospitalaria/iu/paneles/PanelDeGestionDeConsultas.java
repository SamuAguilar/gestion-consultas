package gestion_hospitalaria.iu.paneles;

import gestion_hospitalaria.clases.Consulta;

import java.awt.*;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelDeGestionDeConsultas {

    private static int indiceConsultas = 15;

    public static void iniciar() {
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog("Seleccione una opción:\n1. Registrar consulta\n2. Buscar consulta\n3. Listar consultas\n4. Modificar consulta\n5. Eliminar consulta\n6. Cerrar gestión");
            if (opcion == null) {
                // Si se presionó "Cancelar", salir del bucle
                salir = true;
            } else {
                switch (opcion) {
                    case "1":
                        registrarConsulta();
                        break;
                    case "2":
                        buscarConsulta();
                        break;
                    case "3":
                        listarConsultas();
                        break;
                    case "4":
                        modificarConsulta();
                        break;
                    case "5":
                        eliminarConsulta();
                        break;
                    case "6":
                        salir = true;
                        JOptionPane.showMessageDialog(null, "Gestión cerrada.");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida");
                }
            }
        }
    }

    private static void registrarConsulta() {
        if (indiceConsultas < Datos.CONSULTAS.length) {
            Consulta consulta = new Consulta();
            boolean salirRegistro = false;
            while (!salirRegistro) {
                String idStr = JOptionPane.showInputDialog("Ingrese el ID de la consulta:");
                if (idStr == null) {
                    salirRegistro = true;
                    return;
                }
                try {
                    int id = Integer.parseInt(idStr);
                    boolean idExistente = false;
                    for (int i = 0; i < indiceConsultas; i++) {
                        if (Datos.CONSULTAS[i].getIdConsulta() == id) {
                            idExistente = true;
                            break;
                        }
                    }
                    if (idExistente) {
                        JOptionPane.showMessageDialog(null, "Error: El ID de la consulta ya está registrado.");
                        return;
                    }
                    consulta.setIdConsulta(id);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: El ID de la consulta debe ser un número entero.");
                    return;
                }

                LocalDate fecha = null;
                while (fecha == null) {
                    String fechaStr = JOptionPane.showInputDialog("Ingrese la fecha de la consulta (dd-MM-yyyy):");
                    if (fechaStr == null) {
                        salirRegistro = true;
                        return;
                    }
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");
                        fecha = LocalDate.parse(fechaStr, formatter);
                        if (fecha.getYear() < 2024 || fecha.getYear() > 2100) {
                            JOptionPane.showMessageDialog(null, "Error: La fecha ingresada no es válida. Intente nuevamente.");
                            fecha = null;
                        }
                    } catch (DateTimeParseException e) {
                        JOptionPane.showMessageDialog(null, "Error: La fecha ingresada no es válida. Intente nuevamente.");
                    }
                }

                Time hora = null;
                while (hora == null) {
                    String horaStr = JOptionPane.showInputDialog("Ingrese la hora de la consulta (HH:mm):");
                    if (horaStr == null) {
                        salirRegistro = true;
                        return;
                    }
                    try {
                        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
                        formatoHora.setLenient(false); // No permite horas inválidas
                        hora = new Time(formatoHora.parse(horaStr).getTime());
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(null, "Error: La hora ingresada no es válida. Intente nuevamente.");
                    }
                }
                // Verificar si la consulta que se está registrando coincide en fecha y hora con otras consultas ya registradas
                boolean existeConsulta = false;
                for (int i = 0; i < indiceConsultas; i++) {
                    if (Datos.CONSULTAS[i].getFecha().equals(fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))) && Datos.CONSULTAS[i].getHora().equals(hora.toString())) {
                        existeConsulta = true;
                        break;
                    }
                }

                if (existeConsulta) {
                    JOptionPane.showMessageDialog(null, "Error: Ya existe una consulta registrada para esta fecha y hora.");
                    continue;
                }
                
                String pacienteConsulta = null;
                while (pacienteConsulta == null) {
                    String pacienteStr = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
                    pacienteConsulta = pacienteStr;
                    if (pacienteStr == null) {
                        return;
                    }
                    if (!PanelDeGestionDePacientes.pacienteExiste(pacienteStr)) {
                        JOptionPane.showMessageDialog(null, "Error: El paciente no existe.");
                        pacienteConsulta = null;
                    }
                }

                String medicoConsulta = null;
                while (medicoConsulta == null) {
                    String medicoStr = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
                    medicoConsulta = medicoStr;
                    if (medicoStr == null) {
                        return;
                    }
                    if (!PanelDeGestionDeMedicos.medicoExiste(medicoStr)) {
                        JOptionPane.showMessageDialog(null, "Error: El médico no existe.");
                        medicoConsulta = null;
                    }
                }

                consulta.setFecha(fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                consulta.setHora(hora.toString());
                consulta.setPaciente(pacienteConsulta);
                consulta.setMedico(medicoConsulta);

                Datos.CONSULTAS[indiceConsultas] = consulta;
                indiceConsultas++;

                JOptionPane.showMessageDialog(null, "¡Consulta registrada exitosamente!");
                salirRegistro = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: No se pueden registrar más consultas.");
        }
    }

    private static void buscarConsulta() {
        String idBuscar = JOptionPane.showInputDialog("Ingrese el ID de la consulta:");
        if (idBuscar == null) {
            return;
        }
        try {
            int id = Integer.parseInt(idBuscar);
            boolean encontrado = false;
            for (int i = 0; i < indiceConsultas; i++) {
                if (Datos.CONSULTAS[i].getIdConsulta() == id) {
                    JOptionPane.showMessageDialog(null, "Consulta encontrada exitosamente:"
                            + "\nID: " + Datos.CONSULTAS[i].getIdConsulta()
                            + "\nFecha: " + Datos.CONSULTAS[i].getFecha()
                            + "\nHora: " + Datos.CONSULTAS[i].getHora()
                            + "\nMédico: " + Datos.CONSULTAS[i].getMedico()
                            + "\nPaciente: " + Datos.CONSULTAS[i].getPaciente());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Consulta no encontrada");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El ID de la consulta debe ser un número entero.");
        }
    }

    private static void listarConsultas() {
        Consulta[] consultasTemp = new Consulta[indiceConsultas];

        for (int i = 0; i < indiceConsultas; i++) {
            consultasTemp[i] = Datos.CONSULTAS[i];
        }

        // Ordenar las consultas por fecha y hora en orden ascendente utilizando el algoritmo de inserción
        for (int i = 1; i < consultasTemp.length; i++) {
            Consulta auxiliar = consultasTemp[i];
            int j = i - 1;
            while (j >= 0 && (consultasTemp[j].getFecha().compareTo(auxiliar.getFecha()) > 0
                    || (consultasTemp[j].getFecha().equals(auxiliar.getFecha())
                    && consultasTemp[j].getHora().compareTo(auxiliar.getHora()) > 0))) {
                consultasTemp[j + 1] = consultasTemp[j];
                j--;
            }
            consultasTemp[j + 1] = auxiliar;
        }

        // Construir la lista de consultas
        StringBuilder listaConsultas = new StringBuilder();
        for (Consulta consulta : consultasTemp) {
            listaConsultas.append("ID: ").append(consulta.getIdConsulta()).append("\n")
                    .append("Fecha: ").append(consulta.getFecha()).append("\n")
                    .append("Hora: ").append(consulta.getHora()).append("\n")
                    .append("Médico: ").append(consulta.getMedico()).append("\n")
                    .append("Paciente: ").append(consulta.getPaciente()).append("\n\n");
        }

        if (listaConsultas.length() == 0) {
            JOptionPane.showMessageDialog(null, "No hay consultas registradas");
        } else {
            JTextArea textArea = new JTextArea(listaConsultas.toString());
            textArea.setEditable(false);
            textArea.setCaretPosition(0); // Asegura que el texto comience desde el principio

            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new Dimension(500, 300)); // Ajusta el tamaño según sea necesario

            JOptionPane.showMessageDialog(null, scrollPane, "Lista de Consultas", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static void modificarConsulta() {
        String idModificar = JOptionPane.showInputDialog("Ingrese el ID de la consulta a modificar:");
        if (idModificar == null) {
            return;
        }
        try {
            int id = Integer.parseInt(idModificar);
            boolean encontrado = false;
            for (int i = 0; i < indiceConsultas; i++) {
                if (Datos.CONSULTAS[i].getIdConsulta() == id) {
                    encontrado = true;
                    Consulta consulta = Datos.CONSULTAS[i];
                    String nuevaFecha = JOptionPane.showInputDialog("Ingrese la nueva fecha de la consulta:", consulta.getFecha());
                    if (nuevaFecha != null) {
                        consulta.setFecha(nuevaFecha);
                    } else {
                        return;
                    }
                    String nuevaHora = JOptionPane.showInputDialog("Ingrese la nueva hora de la consulta:", consulta.getHora());
                    if (nuevaHora != null) {
                        consulta.setHora(nuevaHora);
                    } else {
                        return;
                    }
                    String nuevoMedico = JOptionPane.showInputDialog("Ingrese el nuevo nombre del médico:", consulta.getMedico());
                    if (nuevoMedico != null) {
                        consulta.setMedico(nuevoMedico);
                    } else {
                        return;
                    }
                    String nuevoPaciente = JOptionPane.showInputDialog("Ingrese el nuevo nombre del paciente:", consulta.getPaciente());
                    if (nuevoPaciente != null) {
                        consulta.setPaciente(nuevoPaciente);
                    } else {
                        return;
                    }
                    JOptionPane.showMessageDialog(null, "Consulta modificada exitosamente");
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Consulta no encontrada");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El ID de la consulta debe ser un número entero.");
        }
    }

    private static void eliminarConsulta() {
        String idEliminar = JOptionPane.showInputDialog("Ingrese el ID de la consulta a eliminar:");
        if (idEliminar == null) {
            return;
        }
        try {
            int id = Integer.parseInt(idEliminar);
            boolean encontrado = false;
            for (int i = 0; i < indiceConsultas; i++) {
                if (Datos.CONSULTAS[i].getIdConsulta() == id) {
                    encontrado = true;
                    for (int j = i; j < indiceConsultas - 1; j++) {
                        Datos.CONSULTAS[j] = Datos.CONSULTAS[j + 1];
                    }
                    Datos.CONSULTAS[indiceConsultas - 1] = null;
                    indiceConsultas--;
                    JOptionPane.showMessageDialog(null, "Consulta eliminada exitosamente");
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Consulta no encontrada");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El ID de la consulta debe ser un número entero.");
        }
    }
}
