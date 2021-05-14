/*
Repite el primer ejercicio de este bloque sobre la base de datos SQLite empresa.db
proporcionada por el profesor. Obviamente, no hará falta el MySQL para este ejercicio

fecha:14/05/2021
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
public class RCMt18e11 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String sql = "SELECT nombre FROM empleado";
        try (Connection conexion = DriverManager.getConnection(
                "jdbc:sqlite:Archivos/empresa.db");
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            
                ResultSet rs = ps.executeQuery();
                
                while(rs.next()){
                    System.out.println(rs.getString(1));
                }
                
            
            
            
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n"
                    + "SLQState: " + e.getSQLState() + "\n"
                    + "Mensaje: " + e.getMessage() + "\n");
        }
        
                
                
               
                
                
                
    }//fin main


    
    
}
