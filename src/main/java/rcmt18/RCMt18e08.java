/*
Realizar un programa que elimine de la tabla a los mayores de 60 años. (Hacerlo mediante 
DELETE). El programa informará de cuantos empleados se han visto afectados.

fecha: 12/05/2021
alumno: Rafael Chamorro Maceiras
 */
package rcmt18;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author rchamac
 */
public class RCMt18e08 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sql = "DELETE FROM empleado WHERE fechaNacimiento < ?";

        try ( Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empresa", "root", "");  
                PreparedStatement ps = conexion.prepareStatement(sql)) {
      
            LocalDate hoy = LocalDate.now();
            int dia = hoy.getDayOfMonth();
            int mes = hoy.getMonthValue();
            int año = hoy.getYear()-60;
            System.out.printf("Borramos todos los nacidos antes de %d/%d/%d%n",dia,mes,año);
            String fn = String.format("%d-%d-%d",año,mes,dia);
            
            
            ps.setString(1,fn);
            int cantFilas = ps.executeUpdate();
            System.out.println(cantFilas + " fila/s actualizadas");

        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n"
                    + "SLQState: " + e.getSQLState() + "\n"
                    + "Mensaje: " + e.getMessage() + "\n");
        }
    }

}
