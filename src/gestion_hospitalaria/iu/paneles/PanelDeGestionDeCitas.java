package gestion_hospitalaria.iu.paneles;

import gestion_hospitalaria.clases.Cita;
import gestion_hospitalaria.clases.Medico;
import gestion_hospitalaria.clases.Paciente;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelDeGestionDeCitas {

    private static int indiceCitas = 15;

    public static void iniciar() {
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                    "Seleccione una opción:\n1. Registrar cita\n2. Buscar cita\n3. Listar citas\n4. Modificar cita\n5. Eliminar cita\n6. Cerrar gestión");
            if (opcion == null) {
                // Si se presionó Cancelar, salir del bucle
                salir = true;
            } else {
                switch (opcion) {
                    case "1":
                        registrarCita();
                        break;
                    case "2":
                        buscarCita();
                        break;
                    case "3":
                        listarCitas();
                        break;
                    case "4":
                        modificarCita();
                        break;
                    case "5":
                        eliminarCita();
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

    private static void registrarCita() {
        if (indiceCitas < Datos.CITAS.length) {
            Cita cita = new Cita();
            boolean salirRegistro = false;
            while (!salirRegistro) {

                Paciente paciente = null;
                while (paciente == null && !salirRegistro) {
                    String pacienteStr = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
                    if (pacienteStr == null) {
                        salirRegistro = true;
                        return;
                    }
                    for (Paciente p : Datos.PACIENTES) {
                        if (p != null && p.getNombre().equals(pacienteStr)) {
                            paciente = p;
                            break;
                        }
                    }
                    if (paciente == null) {
                        JOptionPane.showMessageDialog(null, "Error: El paciente no existe.");
                    }
                }

                Medico medico = null;
                while (medico == null) {
                    String medicoStr = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
                    if (medicoStr == null || medicoStr.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Error: Debe ingresar un nombre de médico.");
                        continue;
                    }

                    for (Medico m : Datos.MEDICOS) {
                        if (m != null && m.getNombre().equals(medicoStr)) {
                            medico = m;
                            break;
                        }
                    }
                    if (medico == null) { // Si no se encontró el médico en la lista
                        JOptionPane.showMessageDialog(null, "Error: El médico no existe.");
                        continue;
                    }
                }

                LocalDate fecha = null;
                while (fecha == null) {
                    String fechaStr = JOptionPane.showInputDialog("Ingrese la fecha de la cita (dd-MM-yyyy):");
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
                    String horaStr = JOptionPane.showInputDialog("Ingrese la hora de la cita (HH:mm):");
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

                boolean existeCita = false;
                for (int i = 0; i < indiceCitas; i++) {
                    if (Datos.CITAS[i].getFecha().equals(java.sql.Date.valueOf(fecha).toString())
                            && Datos.CITAS[i].getHora().toString().equals(hora.toString())) {
                        existeCita = true;
                        break;
                    }
                }
                if (existeCita) {
                    JOptionPane.showMessageDialog(null, "Error: Ya existe una cita registrada para esta fecha y hora.");
                    continue;
                }

                String descripcion = JOptionPane.showInputDialog("Ingrese la descripción de la cita:");
                if (descripcion == null) {
                    salirRegistro = true;
                    return;
                }

                cita.setIdCita(indiceCitas + 1);
                cita.setFecha(java.sql.Date.valueOf(fecha));
                cita.setHora(hora);
                cita.setPaciente(paciente);
                cita.setMedico(medico);
                Datos.CITAS[indiceCitas] = cita;
                indiceCitas++;

                JOptionPane.showMessageDialog(null, "Cita registrada exitosamente!");
                salirRegistro = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error: No se pueden registrar más citas.");
        }
    }

    private static void buscarCita() {
        String pacienteStr = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
        if (pacienteStr == null) {
            return;
        }
        boolean encontrado = false;
        StringBuilder mensaje = new StringBuilder("Citas encontradas para el paciente " + pacienteStr + ":\n");
        for (int i = 0; i < indiceCitas; i++) {
            if (Datos.CITAS[i].getPaciente().getNombre().equals(pacienteStr)) {
                mensaje.append("Cita ").append(i + 1).append(":\n");
                mensaje.append("Médico: ").append(Datos.CITAS[i].getMedico().getNombre()).append("\n");
                mensaje.append("Fecha: ").append(Datos.CITAS[i].getFecha()).append("\n");
                mensaje.append("Hora: ").append(Datos.CITAS[i].getHora()).append("\n");
                mensaje.append("Descripción: ").append(Datos.CITAS[i].getDescripcion()).append("\n\n");
                encontrado = true;
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, mensaje.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Cita no encontrada");
        }
    }

    private static void listarCitas() {
        Cita[] citasTemp = new Cita[indiceCitas];

        for (int i = 0; i < indiceCitas; i++) {
            citasTemp[i] = Datos.CITAS[i];
        }

        // Ordenar las citas por fecha y hora en orden ascendente utilizando el algoritmo de inserción
        for (int i = 1; i < citasTemp.length; i++) {
            Cita auxiliar = citasTemp[i];
            if (auxiliar != null) { // Verificar si auxiliar es nulo
                int j = i - 1;
                while (j >= 0 && citasTemp[j] != null) { // Verificar si citasTemp[j] es nulo
                    if (citasTemp[j].getFecha().compareTo(auxiliar.getFecha()) > 0
                            || (citasTemp[j].getFecha().equals(auxiliar.getFecha())
                            && citasTemp[j].getHora().compareTo(auxiliar.getHora()) > 0)) {
                        citasTemp[j + 1] = citasTemp[j];
                        j--;
                    } else {
                        break;
                    }
                }
                citasTemp[j + 1] = auxiliar;
            }
        }

        // Construir la lista de citas
        StringBuilder sb = new StringBuilder();
        sb.append("Lista de citas:\n");
        for (Cita cita : citasTemp) {
            if (cita != null) { // Verificar si cita es nulo
                sb.append("Paciente: ").append(cita.getPaciente().getNombre()).append("\n")
                        .append("Médico: ").append(cita.getMedico().getNombre()).append("\n")
                        .append(cita.getMedico().getEspecialidad()).append("\n")
                        .append("Fecha: ").append(cita.getFecha()).append("\n")
                        .append("Hora: ").append(cita.getHora()).append("\n")
                        .append("Descripción: ").append(cita.getDescripcion()).append("\n\n");
            }
        }

        // Crear el JTextArea para mostrar el texto largo
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false); // El área de texto no debe ser editable
        textArea.setLineWrap(true); // Ajustar el texto automáticamente
        textArea.setWrapStyleWord(true); // Ajustar el texto por palabra
        textArea.setCaretPosition(0); // Colocar el cursor al inicio

        // Crear un JScrollPane y agregar el JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(500, 400)); // Tamaño preferido del JScrollPane

        // Mostrar el JScrollPane en un JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de citas", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void modificarCita() {
        String pacienteStr = JOptionPane.showInputDialog("Ingrese el nombre del paciente de la cita a modificar:");
        if (pacienteStr == null) {
            return;
        }
        boolean encontrado = false;
        for (int i = 0; i < indiceCitas; i++) {
            if (Datos.CITAS[i].getPaciente().getNombre().equals(pacienteStr)) {
                encontrado = true;
                Cita cita = Datos.CITAS[i];
                String pacienteInput = JOptionPane.showInputDialog("Ingrese el nuevo nombre del paciente:",
                        cita.getPaciente().getNombre());
                if (pacienteInput != null) {
                    cita.getPaciente().setNombre(pacienteInput);
                } else {
                    return;
                }
                String medicoInput = JOptionPane.showInputDialog("Ingrese el nuevo nombre del médico:",
                        cita.getMedico().getNombre());
                // String especialidadInput = JOptionPane.showInputDialog("Ingrese la
                // especialidad del médico:", cita.getMedico().getEspecialidad());
                if (medicoInput != null) {
                    cita.getMedico().setNombre(medicoInput);
                } else {
                    return;
                }
                String fechaInput = JOptionPane.showInputDialog("Ingrese la nueva fecha de la cita (yyyy-MM-dd):",
                        cita.getFecha().toString());
                if (fechaInput != null) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        LocalDate fechaLocal = LocalDate.parse(fechaInput, formatter);
                        if (fechaLocal.getYear() < 2024 || fechaLocal.getYear() > 2100) {
                            JOptionPane.showMessageDialog(null, "Error: La fecha ingresada no es válida. Intente nuevamente.");
                            return;
                        }
                        cita.setFecha(java.sql.Date.valueOf(fechaLocal));
                    } catch (DateTimeParseException e) {
                        JOptionPane.showMessageDialog(null, "Error: La fecha ingresada no es válida. Intente nuevamente.");
                        return;
                    }
                } else {
                    return;
                }
                String horaInput = JOptionPane.showInputDialog("Ingrese la nueva hora de la cita (HH:mm:ss):",
                        cita.getHora().toString());
                if (horaInput != null) {
                    cita.setHora(Time.valueOf(horaInput));
                } else {
                    return;
                }
                String descripcionInput = JOptionPane.showInputDialog("Ingrese la nueva descripción de la cita:",
                        cita.getDescripcion());
                if (descripcionInput != null) {
                    cita.setDescripcion(descripcionInput);
                } else {
                    return;
                }
                JOptionPane.showMessageDialog(null, "Cita modificada exitosamente");
                break;

            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Cita no encontrada");
        }
    }

    private static void eliminarCita() {
        String pacienteStr = JOptionPane.showInputDialog("Ingrese el nombre del paciente de la cita a eliminar:");
        if (pacienteStr == null) {
            return;
        }
        boolean encontrado = false;
        int indiceCitaAEliminar = -1;
        for (int i = 0; i < indiceCitas; i++) {
            if (Datos.CITAS[i].getPaciente().getNombre().equals(pacienteStr)) {
                encontrado = true;
                indiceCitaAEliminar = i;
                break;
            }
        }
        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Cita no encontrada");
            return;
        }

        int citaActual = indiceCitaAEliminar;
        while (true) {
            // Mostrar los datos de la cita a eliminar
            Cita citaAEliminar = Datos.CITAS[citaActual];
            String mensaje = "Cita a eliminar:\n";
            mensaje += "Paciente: " + citaAEliminar.getPaciente().getNombre() + "\n";
            mensaje += "Fecha: " + citaAEliminar.getFecha() + "\n";
            mensaje += "Hora: " + citaAEliminar.getHora() + "\n";
            mensaje += "Descripción: " + citaAEliminar.getDescripcion();

            int respuesta = JOptionPane.showConfirmDialog(null, mensaje, "Eliminar cita", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.YES_OPTION) {
                // Eliminar la cita
                for (int i = citaActual; i < indiceCitas - 1; i++) {
                    Datos.CITAS[i] = Datos.CITAS[i + 1];
                }
                Datos.CITAS[indiceCitas - 1] = null;
                indiceCitas--;
                JOptionPane.showMessageDialog(null, "Cita eliminada exitosamente");
                break;
            } else {
                // Buscar la siguiente cita del paciente
                boolean encontradoSiguiente = false;
                for (int i = citaActual + 1; i < indiceCitas; i++) {
                    if (Datos.CITAS[i].getPaciente().getNombre().equals(pacienteStr)) {
                        encontradoSiguiente = true;
                        citaActual = i;
                        break;
                    }
                }
                if (!encontradoSiguiente) {
                    JOptionPane.showMessageDialog(null, "No hay más citas para este paciente");
                    break;
                }
            }
        }
    }

}
