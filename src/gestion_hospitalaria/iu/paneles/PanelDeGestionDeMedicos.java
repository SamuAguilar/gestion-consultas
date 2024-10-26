package gestion_hospitalaria.iu.paneles;

import gestion_hospitalaria.clases.Medico;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelDeGestionDeMedicos {

    private static int indiceMedico;

    public static void iniciar() {
        indiceMedico = 5; //Numero de medicos cargados 

        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog("Seleccione una opción:\n1. Registrar médico\n2. Buscar médico\n3. Listar médicos\n4. Modificar médico\n5. Eliminar médico\n6. Cerrar gestión");
            if (opcion == null) {
                // Si se presionó Cancelar salir del bucle
                salir = true;
            } else {
                switch (opcion) {
                    case "1":
                        registrarMedico();
                        break;
                    case "2":
                        buscarMedico();
                        break;
                    case "3":
                        listarMedicos();
                        break;
                    case "4":
                        modificarMedico();
                        break;
                    case "5":
                        eliminarMedico();
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

    private static void registrarMedico() {
        if (indiceMedico < Datos.MEDICOS.length) {
            String idStr = JOptionPane.showInputDialog("Ingrese el ID del médico:");
            if (idStr == null) {
                return;
            }
            int id = 0;
            while (true) {
                try {
                    id = Integer.parseInt(idStr);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error: El ID del médico debe ser un número entero.");
                    idStr = JOptionPane.showInputDialog("Ingrese el ID del médico:");
                    if (idStr == null) {
                        return;
                    }
                }
            }

            boolean idExistente = false;
            for (int i = 0; i < indiceMedico; i++) {
                if (Datos.MEDICOS[i].getIdMedico() == id) {
                    idExistente = true;
                    break;
                }
            }
            if (idExistente) {
                JOptionPane.showMessageDialog(null, "Error: El ID del médico ya está registrado.");
                return;
            }

            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del médico:");
            if (nombre == null) {
                return;
            }
            nombre = nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();

            String especialidad = JOptionPane.showInputDialog("Ingrese la especialidad del médico:");
            if (especialidad == null) {
                return;
            }

            String telefono = JOptionPane.showInputDialog("Ingrese el teléfono del médico:");
            if (telefono == null) {
                return;
            }
            while (!telefono.matches("\\d{8,10}")) {
                JOptionPane.showMessageDialog(null, "Error: El teléfono debe tener entre 8 y 10 dígitos.");
                telefono = JOptionPane.showInputDialog("Ingrese el teléfono del médico:");
                if (telefono == null) {
                    return;
                }
            }

            String correo = JOptionPane.showInputDialog("Ingrese el correo electrónico del médico:");
            if (correo == null) {
                return;
            }
            while (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                JOptionPane.showMessageDialog(null, "Error: El correo electrónico no es válido.");
                correo = JOptionPane.showInputDialog("Ingrese el correo electrónico del médico:");
                if (correo == null) {
                    return;
                }
            }

            Medico medico = new Medico(id, nombre, telefono, correo, especialidad);
            Datos.MEDICOS[indiceMedico] = medico;
            indiceMedico++;

            JOptionPane.showMessageDialog(null, "¡Médico registrado exitosamente!");
        } else {
            JOptionPane.showMessageDialog(null, "Error: ¡Se ha alcanzado el límite de médicos en la gestión!");
        }
    }

    private static void buscarMedico() {
        String idBuscar = JOptionPane.showInputDialog("Ingrese el ID del médico:");
        if (idBuscar == null) {
            return;
        }
        try {
            int id = Integer.parseInt(idBuscar);
            boolean encontrado = false;
            for (int i = 0; i < indiceMedico; i++) {
                if (Datos.MEDICOS[i].getIdMedico() == id) {
                    JOptionPane.showMessageDialog(null, "Médico encontrado exitosamente:"
                            + "\nID: " + Datos.MEDICOS[i].getIdMedico()
                            + "\nNombre: " + Datos.MEDICOS[i].getNombre()
                            + "\nEspecialidad: " + Datos.MEDICOS[i].getEspecialidad()
                            + "\nTeléfono: " + Datos.MEDICOS[i].getTelefono()
                            + "\nCorreo electrónico: " + Datos.MEDICOS[i].getCorreo());
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Médico no encontrado");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El ID del médico debe ser un número entero.");
        }
    }

    private static void listarMedicos() {
        if (indiceMedico == 0) {
            JOptionPane.showMessageDialog(null, "No hay médicos registrados.");
            return;
        }

        StringBuilder listaMedicos = new StringBuilder();
        for (int i = 0; i < indiceMedico; i++) {
            Medico medico = Datos.MEDICOS[i];
            if (medico != null) {
                listaMedicos.append("ID: ").append(medico.getIdMedico())
                        .append("\nNombre: ").append(medico.getNombre())
                        .append("\nEspecialidad: ").append(medico.getEspecialidad())
                        .append("\nTeléfono: ").append(medico.getTelefono())
                        .append("\nCorreo electrónico: ").append(medico.getCorreo())
                        .append("\n\n");
            }
        }

        // Crear un JTextArea para mostrar la lista
        JTextArea textArea = new JTextArea(listaMedicos.toString());
        textArea.setEditable(false); // El usuario no puede editar el texto
        textArea.setCaretPosition(0); // Mueve el cursor al inicio del texto

        // Crear un JScrollPane que contiene el JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(400, 300)); // Tamaño del scroll

        // Mostrar la lista en un JOptionPane
        JOptionPane.showMessageDialog(null, scrollPane, "Lista de Médicos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void modificarMedico() {
        String idModificar = JOptionPane.showInputDialog("Ingrese el ID del médico a modificar:");
        if (idModificar == null) {
            return;
        }
        try {
            int id = Integer.parseInt(idModificar);
            boolean encontrado = false;
            for (int i = 0; i < indiceMedico; i++) {
                if (Datos.MEDICOS[i].getIdMedico() == id) {
                    encontrado = true;
                    Medico medico = Datos.MEDICOS[i];
                    medico.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre del médico:", medico.getNombre()));
                    if (medico.getNombre() == null) {
                        return;
                    }
                    medico.setNombre(medico.getNombre().substring(0, 1).toUpperCase() + medico.getNombre().substring(1).toLowerCase());

                    medico.setEspecialidad(JOptionPane.showInputDialog("Ingrese la nueva especialidad del médico:", medico.getEspecialidad()));
                    if (medico.getEspecialidad() == null) {
                        return;
                    }

                    String telefono = JOptionPane.showInputDialog("Ingrese el nuevo teléfono del médico:", medico.getTelefono());
                    if (telefono == null) {
                        return;
                    }
                    while (!telefono.matches("\\d{8,10}")) {
                        JOptionPane.showMessageDialog(null, "Error: El teléfono debe tener entre 8 y 10 dígitos.");
                        telefono = JOptionPane.showInputDialog("Ingrese el nuevo teléfono del médico:", medico.getTelefono());
                        if (telefono == null) {
                            return;
                        }
                    }
                    medico.setTelefono(telefono);

                    String correo = JOptionPane.showInputDialog("Ingrese el nuevo correo electrónico del médico:", medico.getCorreo());
                    if (correo == null) {
                        return;
                    }
                    while (!correo.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                        JOptionPane.showMessageDialog(null, "Error: El correo electrónico no es válido.");
                        correo = JOptionPane.showInputDialog("Ingrese el nuevo correo electrónico del médico:", medico.getCorreo());
                        if (correo == null) {
                            return;
                        }
                    }
                    medico.setCorreo(correo);

                    JOptionPane.showMessageDialog(null, "Médico modificado exitosamente ");
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Médico no encontrado");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El ID del médico debe ser un número entero.");
        }
    }

    private static void eliminarMedico() {
        String idEliminar = JOptionPane.showInputDialog("Ingrese el ID del médico a eliminar:");
        if (idEliminar == null) {
            return;
        }
        try {
            int id = Integer.parseInt(idEliminar);
            boolean encontrado = false;
            for (int i = 0; i < indiceMedico; i++) {
                if (Datos.MEDICOS[i].getIdMedico() == id) {
                    encontrado = true;
                    for (int j = i; j < indiceMedico - 1; j++) {
                        Datos.MEDICOS[j] = Datos.MEDICOS[j + 1];
                    }
                    indiceMedico--;
                    JOptionPane.showMessageDialog(null, "Médico eliminado exitosamente");
                    break;
                }
            }
            if (!encontrado) {
                JOptionPane.showMessageDialog(null, "Médico no encontrado");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: El ID del médico debe ser un número entero.");
        }
    }

    public static boolean medicoExiste(String medicoStr) {
        for (Medico medico : Datos.MEDICOS) {
            if (medico != null && medico.getNombre().equalsIgnoreCase(medicoStr)) {
                return true;
            }
        }
        return false;
    }

}
