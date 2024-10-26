package gestion_hospitalaria.iu.paneles;

import gestion_hospitalaria.clases.Paciente;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelDeGestionDePacientes {

    private static int indicePaciente = 15;

    public static void iniciar() {
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog("Seleccione una opción:\n1. Registrar paciente\n2. Buscar paciente\n3. Listar pacientes\n4. Modificar paciente\n5. Eliminar paciente\n6. Cerrar gestión");
            if (opcion == null) {
                salir = true; // Si se presionó Cancelar, salir del bucle
            } else {
                switch (opcion) {
                    case "1":
                        registrarPaciente();
                        break;
                    case "2":
                        buscarPaciente();
                        break;
                    case "3":
                        listarPaciente();
                        break;
                    case "4":
                        modificarPaciente();
                        break;
                    case "5":
                        eliminarPaciente();
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

    // Métodos registrarPaciente(), buscarPaciente(), listarPaciente(), modificarPaciente(), eliminarPaciente() sin cambios...
    private static void registrarPaciente() {
        if (indicePaciente < Datos.PACIENTES.length) {
            String idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente:");
            if (idStr == null) {
                return;
            }
            int id = 0;
            while (true) {
                try {
                    id = Integer.parseInt(idStr);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: El ID del paciente debe ser un número entero.");
                    idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente:");
                    if (idStr == null) {
                        return;
                    }
                }
            }

            boolean idExistente = false;
            for (int i = 0; i < indicePaciente; i++) {
                if (Datos.PACIENTES[i].getId() == id) {
                    idExistente = true;
                    break;
                }
            }
            if (idExistente) {
                JOptionPane.showMessageDialog(null, "Error: El ID del paciente ya está registrado.");
                return;
            }

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del paciente:");
            if (nombre == null) {
                return;
            }
            nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase(); //Se pone la primera letra del paciente en mayúsculas

            LocalDate fechaNacimiento = null;
            while (fechaNacimiento == null) {
                String fechaNacimientoStr = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento del paciente (dd-mm-aaaa):");
                if (fechaNacimientoStr == null) {
                    return;
                }
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    fechaNacimiento = LocalDate.parse(fechaNacimientoStr, formatter);
                } catch (DateTimeParseException e) {
                    JOptionPane.showMessageDialog(null, "Error: La fecha de nacimiento ingresada no es válida. Intente nuevamente.");
                }
            }

            String direccion = JOptionPane.showInputDialog("Ingrese la dirección del paciente:");
            if (direccion == null) {
                return;
            }

            String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del paciente:");
            if (telefono == null) {
                return;
            }
            while (!telefono.matches("\\d{8,10}")) {
                JOptionPane.showMessageDialog(null, "Error: El teléfono debe tener entre 8 y 10 dígitos.");
                telefono = JOptionPane.showInputDialog("Ingrese el teléfono del paciente:");
                if (telefono == null) {
                    return;
                }
            }

            String correo = JOptionPane.showInputDialog("Ingrese el correo electrónico del paciente:");
            if (correo == null) {
                return;
            }
            while (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                JOptionPane.showMessageDialog(null, "Error: El correo electrónico no es válido.");
                correo = JOptionPane.showInputDialog("Ingrese el correo electrónico del paciente:");
                if (correo == null) {
                    return;
                }
            }

            Paciente paciente = new Paciente(id, nombre, telefono, correo, java.sql.Date.valueOf(fechaNacimiento), direccion);
            Datos.PACIENTES[indicePaciente] = paciente;
            indicePaciente++;

            JOptionPane.showMessageDialog(null, "Paciente registrado exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error: Se ha alcanzado el límite de pacientes registrados.");
        }
    }

    private static void buscarPaciente() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente a buscar:");
        if (idStr == null) {
            return;
        }
        int id = 0;
        while (true) {
            try {
                id = Integer.parseInt(idStr);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: El ID del paciente debe ser un número entero.");
                idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente a buscar:");
                if (idStr == null) {
                    return;
                }
            }
        }

        for (int i = 0; i < indicePaciente; i++) {
            if (Datos.PACIENTES[i].getId() == id) {
                Paciente paciente = Datos.PACIENTES[i];
                JOptionPane.showMessageDialog(null, "Paciente encontrado:\nID: " + paciente.getId() + "\nNombre: " + paciente.getNombre() + "\nTeléfono: " + paciente.getTelefono() + "\nCorreo: " + paciente.getCorreo() + "\nFecha de nacimiento: " + paciente.getFechaNacimiento() + "\nDirección: " + paciente.getDireccion());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
    }

    private static void listarPaciente() {
        if (indicePaciente == 0) {
            JOptionPane.showMessageDialog(null, "No hay pacientes registrados.");
            return;
        }

        StringBuilder lista = new StringBuilder();
        for (int i = 0; i < indicePaciente; i++) {
            Paciente paciente = Datos.PACIENTES[i];
            lista.append("ID: ").append(paciente.getId())
                    .append("\nNombre: ").append(paciente.getNombre())
                    .append("\nTeléfono: ").append(paciente.getTelefono())
                    .append("\nCorreo: ").append(paciente.getCorreo())
                    .append("\nFecha de nacimiento: ").append(paciente.getFechaNacimiento())
                    .append("\nDirección: ").append(paciente.getDireccion())
                    .append("\n\n");
        }

        // Crear un JTextArea para mostrar la lista
        JTextArea textArea = new JTextArea(lista.toString());
        textArea.setEditable(false); // El usuario no puede editar el texto
        textArea.setCaretPosition(0); // Mueve el cursor al inicio del texto

        // Crear un JScrollPane que contiene el JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300)); // Tamaño del scroll

        // Mostrar la lista en un JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Pacientes", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void modificarPaciente() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente a modificar:");
        if (idStr == null) {
            return;
        }
        int id = 0;
        while (true) {
            try {
                id = Integer.parseInt(idStr);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: El ID del paciente debe ser un número entero.");
                idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente a modificar:");
                if (idStr == null) {
                    return;
                }
            }
        }

        for (int i = 0; i < indicePaciente; i++) {
            if (Datos.PACIENTES[i].getId() == id) {
                Paciente paciente = Datos.PACIENTES[i];

                String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre del paciente (deje en blanco para no modificar):", paciente.getNombre());
                if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                    paciente.setNombre(nuevoNombre);
                }

                String nuevoTelefono = JOptionPane.showInputDialog("Ingrese el nuevo teléfono del paciente (deje en blanco para no modificar):", paciente.getTelefono());
                if (nuevoTelefono != null && !nuevoTelefono.trim().isEmpty()) {
                    paciente.setTelefono(nuevoTelefono);
                }

                String nuevoCorreo = JOptionPane.showInputDialog("Ingrese el nuevo correo electrónico del paciente (deje en blanco para no modificar):", paciente.getCorreo());
                if (nuevoCorreo != null && !nuevoCorreo.trim().isEmpty()) {
                    paciente.setCorreo(nuevoCorreo);
                }

                String nuevaFechaNacimientoStr = JOptionPane.showInputDialog("Ingrese la nueva fecha de nacimiento del paciente (dd-mm-aaaa) (deje en blanco para no modificar):", paciente.getFechaNacimiento().toString());
                if (nuevaFechaNacimientoStr != null && !nuevaFechaNacimientoStr.trim().isEmpty()) {
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        LocalDate nuevaFechaNacimiento = LocalDate.parse(nuevaFechaNacimientoStr, formatter);
                        paciente.setFechaNacimiento(java.sql.Date.valueOf(nuevaFechaNacimiento));
                    } catch (DateTimeParseException e) {
                        JOptionPane.showMessageDialog(null, "Error: La fecha de nacimiento ingresada no es válida.");
                    }
                }

                String nuevaDireccion = JOptionPane.showInputDialog("Ingrese la nueva dirección del paciente (deje en blanco para no modificar):", paciente.getDireccion());
                if (nuevaDireccion != null && !nuevaDireccion.trim().isEmpty()) {
                    paciente.setDireccion(nuevaDireccion);
                }

                JOptionPane.showMessageDialog(null, "Paciente modificado exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
    }

    private static void eliminarPaciente() {
        String idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente a eliminar:");
        if (idStr == null) {
            return;
        }
        int id = 0;
        while (true) {
            try {
                id = Integer.parseInt(idStr);
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: El ID del paciente debe ser un número entero.");
                idStr = JOptionPane.showInputDialog("Ingrese el ID del paciente a eliminar:");
                if (idStr == null) {
                    return;
                }
            }
        }

        for (int i = 0; i < indicePaciente; i++) {
            if (Datos.PACIENTES[i].getId() == id) {
                for (int j = i; j < indicePaciente - 1; j++) {
                    Datos.PACIENTES[j] = Datos.PACIENTES[j + 1];
                }
                Datos.PACIENTES[indicePaciente - 1] = null;
                indicePaciente--;
                JOptionPane.showMessageDialog(null, "Paciente eliminado exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Paciente no encontrado.");
    }

    // Método para verificar si el paciente existe
    public static boolean pacienteExiste(String pacienteStr) {
        for (Paciente paciente : Datos.PACIENTES) {
            if (paciente != null && paciente.getNombre().equalsIgnoreCase(pacienteStr)) {
                return true;
            }
        }
        return false;
    }

    // Método para agregar pacientes (para la inicialización)
    public static void agregarPaciente(Paciente paciente, int index) {
        if (index >= 0 && index < Datos.PACIENTES.length) {
            Datos.PACIENTES[index] = paciente;
        }
    }

    // Otros métodos y variables de la clase
}
