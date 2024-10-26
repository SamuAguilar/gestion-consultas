package gestion_hospitalaria.iu.menu;
import javax.swing.JOptionPane;

public class MenuInicioSesion {

    // Almacenar los usuarios y las contraseñas
    private static String[] usuarios;
    private static String[] contraseñas;
    private static int numeroDeUsuarios;

    // Contadores para el numero de cuentas creadas
    private static int adminsGenerales = 0;
    private static int adminsComunes = 0;

    public static void main(String[] args) {
        usuarios = new String[100]; 
        contraseñas = new String[100]; 
        numeroDeUsuarios = 0;

        boolean salir = false;

        while (!salir) {
            // MENU 
            String opcionStr = JOptionPane.showInputDialog(
                "=== MENÚ PRINCIPAL ===\n" +
                "\n1. Inicio de sesión\n" +
                "2. Crear cuenta\n" +
                "3. Olvidé mi contraseña\n" +
                "4. Salir\n" +
                "\nIngrese el número de la opción:"
            );

            if (opcionStr == null) {
                // Si el usuario cancela o cierra la ventana
                salir = true;
            } else {
                try {
                    int opcion = Integer.parseInt(opcionStr);
                    switch (opcion) {
                        case 1:
                            iniciarSesion();
                            break;
                        case 2:
                            crearCuenta();
                            break;
                        case 3:
                            recuperarContrasena();
                            break;
                        case 4:
                            salir = true;
                            JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción no válida. Inténtelo de nuevo.");
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Entrada no válida. Inténtelo de nuevo.");
                }
            }
        }
    }



    private static void iniciarSesion() {
        String usuario = JOptionPane.showInputDialog("Ingrese su usuario:");
        String contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");

        if (usuario != null && contrasena != null) {
            for (int i = 0; i < numeroDeUsuarios; i++) {
                if (usuarios[i].equals(usuario) && contraseñas[i].equals(contrasena)) {
                    JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. Bienvenido " + usuario + "!");
                    // iniciar
                    SistemaDeGestionHospitalaria.main(new String[]{});
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos. Inténtelo de nuevo.");
    }



    private static void crearCuenta() {
        String[] opciones = {"1. Administrador General", "2. Administrador Común", "3. Médico"};
        String tipoCuenta = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de cuenta:",
                "Crear Cuenta", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);


        if (tipoCuenta != null) {
            switch (tipoCuenta) {
                case "1. Administrador General":
                    if (adminsGenerales < 1) {
                        String nuevoUsuarioAdminGeneral = JOptionPane.showInputDialog("Ingrese el nombre de usuario para el Administrador General:");
                        String nuevaContrasenaAdminGeneral = JOptionPane.showInputDialog("Ingrese la contraseña:");
                        if (nuevoUsuarioAdminGeneral != null && nuevaContrasenaAdminGeneral != null && !usuarioExiste(nuevoUsuarioAdminGeneral)) {
                            agregarUsuario(nuevoUsuarioAdminGeneral, nuevaContrasenaAdminGeneral);
                            adminsGenerales++;
                            JOptionPane.showMessageDialog(null, "Cuenta de Administrador General creada exitosamente para el usuario: " + nuevoUsuarioAdminGeneral);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nombre de usuario ya existe o valores ingresados inválidos. Inténtelo de nuevo.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya se ha creado una cuenta de Administrador General.");
                    }
                    break;

                case "2. Administrador Común":
                    if (adminsComunes < 3) {
                        String nuevoUsuarioAdminComun = JOptionPane.showInputDialog("Ingrese el nombre de usuario para el Administrador Común:");
                        String nuevaContrasenaAdminComun = JOptionPane.showInputDialog("Ingrese la contraseña:");
                        if (nuevoUsuarioAdminComun != null && nuevaContrasenaAdminComun != null && !usuarioExiste(nuevoUsuarioAdminComun)) {
                            agregarUsuario(nuevoUsuarioAdminComun, nuevaContrasenaAdminComun);
                            adminsComunes++;
                            JOptionPane.showMessageDialog(null, "Cuenta de Administrador Común creada exitosamente para el usuario: " + nuevoUsuarioAdminComun);
                        } else {
                            JOptionPane.showMessageDialog(null, "Nombre de usuario ya existe o valores ingresados inválidos. Inténtelo de nuevo.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Ya se han creado 3 cuentas de Administrador Común.");
                    }
                    break;
                case "3. Médico":
                    String nuevoUsuarioMedico = JOptionPane.showInputDialog("Ingrese el nombre de usuario para el Médico: ");
                    String nuevaContrasenaMedico = JOptionPane.showInputDialog("Ingrese la contraseña: ");
                    if (nuevoUsuarioMedico != null && nuevaContrasenaMedico != null && !usuarioExiste(nuevoUsuarioMedico)) {
                        agregarUsuario(nuevoUsuarioMedico, nuevaContrasenaMedico);
                        JOptionPane.showMessageDialog(null, "Cuenta de Médico creada exitosamente para el usuario: " + nuevoUsuarioMedico);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nombre de usuario ya existe o valores ingresados inválidos. Inténtelo de nuevo...");
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Inténtelo de nuevo...");
                    break;
            }
        }
    }



    private static void recuperarContrasena() {
        String usuario = JOptionPane.showInputDialog("Ingrese su usuario:");

        // se muestra la contraseña actual 
        if (usuario != null) {
            for (int i = 0; i < numeroDeUsuarios; i++) {
                if (usuarios[i].equals(usuario)) {
                    String contrasena = contraseñas[i];
                    JOptionPane.showMessageDialog(null, "La contraseña para el usuario " + usuario + " es: " + contrasena);
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Usuario no encontrado. IntEntelo de nuevo.");
    }



    private static boolean usuarioExiste(String usuario) {
        for (int i = 0; i < numeroDeUsuarios; i++) {
            if (usuarios[i].equals(usuario)) {
                return true;
            }
        }
        return false;
    }


    private static void agregarUsuario(String usuario, String contrasena) {
        if (numeroDeUsuarios < usuarios.length) {
            usuarios[numeroDeUsuarios] = usuario;
            contraseñas[numeroDeUsuarios] = contrasena;
            numeroDeUsuarios++;
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden añadir más usuarios, la capacidad está llena...");
        }
    }
}
