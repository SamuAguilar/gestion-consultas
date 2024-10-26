package gestion_hospitalaria.iu.menu;

import gestion_hospitalaria.iu.paneles.PanelDeGestionDePacientes;
import gestion_hospitalaria.iu.paneles.PanelDeGestionDeMedicos;
import gestion_hospitalaria.iu.paneles.PanelDeGestionDeConsultas;
import gestion_hospitalaria.iu.paneles.PanelDeGestionDeCitas;
import javax.swing.JOptionPane;

public class SistemaDeGestionHospitalaria {
    
    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            //Menú
            String opcion = JOptionPane.showInputDialog(
                    "Seleccione una opción:"
                    + "\n1. Gestión de Pacientes"
                    + "\n2. Gestión de Médicos"
                    + "\n3. Gestión de Citas"
                    + "\n4. Gestión de Consultas"
                    + "\n5. Cerrar sesión"
            );
            if (opcion == null) {
                // Si se presionó Cancelar salir del buclee
                salir = true;
            } else {
                switch (opcion) {
                    case "1":
                        PanelDeGestionDePacientes.iniciar();
                        break;
                    case "2":
                        PanelDeGestionDeMedicos.iniciar();
                        break;
                    case "3":
                        PanelDeGestionDeCitas.iniciar(); 
                        break;
                    case "4":
                        PanelDeGestionDeConsultas.iniciar(); 
                        break;
                    case "5":
                        salir = true;
                        JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Inténtelo de nuevo.");
                }
            }
        }
    }
}