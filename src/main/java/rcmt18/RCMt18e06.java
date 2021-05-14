/*
Realizar una versión del programa anterior en la que el usuario no introduce el ‘id’. El 
sistema la calcula como el máximo almacenado en la tabla + 1

Fecha: 12/05/2021
Alumno: Rafael Chamorro Maceiras
 */
package rcmt18;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class RCMt18e06 {
static Scanner teclado;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        teclado = new Scanner(System.in);
        int id;
        String nombre, categoria;
        float salario;
        Date fn;
        boolean salir = false;
        
       
        while(!salir){
            
            
            
            
            //System.out.println("Introduce id:");
            //id = Integer.parseInt(teclado.nextLine());
            System.out.println("nombre:");
            nombre = teclado.nextLine();
            System.out.println("fecha de nacimiento: ");
            System.out.println("dia:"); int dia = Integer.parseInt(teclado.nextLine());
            System.out.println("mes:"); int mes = Integer.parseInt(teclado.nextLine());
            System.out.println("año:"); int año = Integer.parseInt(teclado.nextLine()); año = año - 1900;
            System.out.println("Categoria:");
            categoria = teclado.nextLine();
            fn = new Date(año,mes,dia);
            System.out.println("Salario");
            salario = Float.parseFloat(teclado.nextLine());
            String sql = "INSERT INTO empleado (id, nombre, fechaNacimiento,categoria, salario) VALUES (?,?,?,?,?)";
            String sqlID = "SELECT count(*)  FROM empleado";

            
        try ( Connection conexion = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/empresa", "root", "");
                PreparedStatement ps = conexion.prepareStatement(sql);
                PreparedStatement psid = conexion.prepareStatement(sqlID)) {
       
            //consulta para obtener el número de registros
       ResultSet rs = psid.executeQuery();
       rs.next(); 
       id = rs.getInt(1)+1;
            System.out.println("se asignará el id: "+ id);
       
       
       
       //preparamos la consulta sql
       
        ps.setInt(1,id);
        ps.setString(2,nombre);
        ps.setDate(3, fn);
        ps.setString(4, categoria);
        ps.setFloat(5,salario);
        //ejecutamos la consulta INSERT
        int cantFilas = ps.executeUpdate();
        System.out.println("Añadido "+cantFilas+" registro.");
        char c;    
            do{
            System.out.println("continuar(S/N): ");
            c = teclado.nextLine().charAt(0);
            if (c == 'S' || c == 's') salir = false;
            else if (c == 'N' || c == 'n') salir = true;
            else System.out.println("introduce S o N");
            }while (c != 's' && c != 'S' && c!='n' && c!='N');
            
            
            
        }catch(SQLException e) {
            System.out.println("Código de Error: " + e.getErrorCode() + "\n"
                    + "SLQState: " + e.getSQLState() + "\n"
                    + "Mensaje: " + e.getMessage() + "\n");
        }//fin catch
        
        
        
       // while(!salir){
           
            
        }
        
        
    }
    
}
