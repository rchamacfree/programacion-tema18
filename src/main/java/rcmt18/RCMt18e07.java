/*
Realizar un programa que actualice el salario de los empleados para que no haya ningún 
empleado que cobre menos de 1000 euros. (Hacerlo mediante UPDATE). El programa 
informará de cuantos empleados se han visto afectados.

fecha: 12/05/2021
alumno: Rafael Chamorro Maceiras
 */
package rcmt18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author rchamac
 */
public class RCMt18e07 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sql = "UPDATE empleado SET salario=1000 WHERE salario<1000";

        try ( Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empresa", "root", "");  
                PreparedStatement ps = conexion.prepareStatement(sql)) {
      
            int cantFilas = ps.executeUpdate();
            System.out.println(cantFilas + " fila/s actualizadas");

        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n"
                    + "SLQState: " + e.getSQLState() + "\n"
                    + "Mensaje: " + e.getMessage() + "\n");
        }
    }

}
