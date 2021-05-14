/*
Realizar un programa que solicite al usuario una categoría laboral, y que muestre la cantidad 
de empleados que hay con esa categoría

Fecha: 11/05/2021
Alumno: Rafael Chamorro Maceiras
 */
package rcmt18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author rchamac
 */
public class RCMt18e03 {
    static Scanner teclado;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        String sql = "SELECT count(*)  FROM empleado Where categoria = ?";
        
        try ( Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empresa", "root", "");  
                PreparedStatement ps = conexion.prepareStatement(sql)) {
            System.out.printf("Lista de categorias: %nDirectora General %nJefe de Proyecto %nProgramador Senior %nProgramador Junior");
            System.out.println("Introduce categoría:");
            String cat = teclado.nextLine();
            ps.setString(1,cat);
            ResultSet rs = ps.executeQuery();
            rs.next();
            System.out.println("cantidad personas de la categoria "+cat+" : " +rs.getInt(1));
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n"
                    + "SLQState: " + e.getSQLState() + "\n"
                    + "Mensaje: " + e.getMessage() + "\n");
        }//fin catch

    }//fin main

}//fin class
