/*
Realizar un programa que se conecte a la base de datos ‘empresa’ y muestre el nombre de los 
empleados de la empresa

Fecha: 10/05/2021
Alumno: Rafael Chamorro Maceiras
 */
package rcmt18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author rchamac
 */
public class RCMt18e01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sql = "SELECT nombre  FROM empleado";
        try ( Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empresa", "root", "");  PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("nombre: " + rs.getString(1));

            }
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n"
                    + "SLQState: " + e.getSQLState() + "\n"
                    + "Mensaje: " + e.getMessage() + "\n");
        }//fin catch

    }//fin main

}//fin class
