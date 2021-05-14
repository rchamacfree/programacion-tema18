/*
Realizar un programa que solicite al usuario una fecha y obtenga de la base de datos los 
empleados nacidos después de esa fecha y muestre su nombre y edad.
Pista: hay que convertir de LocalDate a java.sql.Date
Fecha: 11/05/2021
Alumno: Rafael Chamorro Maceiras
 */
package rcmt18;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 *
 * @author rchamac
 */
public class RCMt18e02 {

    static Scanner teclado;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        System.out.println("Introduce una fecha (AAAA-MM-DD):");
        String stringfecha = teclado.nextLine();
        LocalDate fecha = LocalDate.parse(stringfecha);

        String sql = "SELECT nombre, fechaNacimiento FROM empleado WHERE fechaNacimiento > ?";
        try ( Connection conexion = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/empresa", "root", "");  PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setDate(1, java.sql.Date.valueOf(fecha));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                          
                //convierto el Date que obtengo de la base de datos a LocalDate.
                LocalDate fn = rs.getDate(2).toLocalDate();
                long edad = ChronoUnit.YEARS.between(fn,LocalDate.now());
                System.out.printf("%s ---> %d años %n",rs.getString(1),edad);
                

            }
        } catch (SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n"
                    + "SLQState: " + e.getSQLState() + "\n"
                    + "Mensaje: " + e.getMessage() + "\n");
        }//fin catch

    }//fin main

}//fin class
