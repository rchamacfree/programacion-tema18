/*
Crear una clase llamada Empleado cuyos atributos son los campos de la tabla ‘Empleado’. 
Realizar un programa que cargue un ArrayList de clase Empleado con todos los empleados de 
la tabla. Mostrar a continuación todo el contenido del ArrayList

Fecha: 11/05/2021
Alumno: Rafael Chamorro Maceiras
 */
package rcmt18;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author rchamac
 */
public class RCMt18e04 {
    static ArrayList<Empleado>listaempleados;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listaempleados = new ArrayList<>();
        String sql = "SELECT *  FROM empleado";
        try ( Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empresa", "root", "");  PreparedStatement ps = conexion.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
               String n = rs.getString(2);
                Date fn = rs.getDate(3);
                String c = rs.getString(4);
                float s = rs.getFloat(5);
                
                listaempleados.add(new Empleado(n,fn,c,s));
                 }
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n"
                    + "SLQState: " + e.getSQLState() + "\n"
                    + "Mensaje: " + e.getMessage() + "\n");
        }//fin catch

        
        for (Empleado x: listaempleados)System.out.println(x);
        
        
    }//fin main

}//fin class
