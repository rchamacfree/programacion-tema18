/*
Realizar un programa que actualice el salario de los empleados para que no haya ningún 
empleado que cobre menos de 1100 euros. Hacerlo actualizando un ResultSet. A continuación, 
volver al principio ese ResultSet y mostrar nombre y salario de todos los empleados.

fecha: 14/05/2021
Alumno: Rafael Chamorro Maceiras
 */
package rcmt18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RCMt18e09 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String sql = "SELECT * FROM empleado";
        float sal;
        try ( Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empresa", "root", "");  
                PreparedStatement ps = conexion.prepareStatement(sql,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sal = rs.getFloat(5);
                if (sal < 1100){ rs.updateFloat(5, 1100);
                                 rs.updateRow();
                                }

            }
            rs.beforeFirst(); //vuelve al principio
            while (rs.next()) {
                System.out.println(rs.getString(2)+ " ------ " + rs.getFloat(5));
            }
        } catch (SQLException e) {
            System.out.println("Código de Error:" + e.getErrorCode() + "\n"
                    + "SLQState:" + e.getSQLState() + "\n"
                    + "Mensaje:" + e.getMessage() + "\n");
        }

    }

}
