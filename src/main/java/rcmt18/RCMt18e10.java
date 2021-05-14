/*
Partiendo de la clase Empleado definida en un ejercicio anterior, crea una clase DAO 
con las operaciones de:
• Conexión a la base de datos, con patrón Singleton y fichero Properties con usuario/pass.
• Buscar un empleado: se le pasará ‘id’ y devolverá un objeto Empleado.
• Borrar empleado: se le pasará ‘id’ y devolverá boolean ( encontrado y borrado o no).
• Insertar empleado: se le pasarán todos los datos y y devolverá boolean según lo haya 
encontrado e insertado o no.
• Listar empleados: devuelve un ArrayList de Empleados, con todos los registros de la tabla.
Finalmente, hacer un programa con un menú, que permita: buscar, borrar, insertar y listar, 
empleando la clase anterior.

fecha: 14/05/2021
Alumno: Rafael Chamorro Maceiras
*/
package rcmt18;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rchamac
 */
public class RCMt18e10 {

    static Scanner teclado;
    public static void main(String[] args) throws IOException {
        
        teclado = new Scanner(System.in);
        int x;
        
         do{
                x=-1;
                System.out.println("Operaciones a través de un Patrón Dao:");
                System.out.println("1.- Buscar empleado.");
                System.out.println("2.- Borrar empleado.");
                System.out.println("3.- Listar empleados.");
                System.out.println("0.- Salir.");

                while(x<0 || x>3){
                    System.out.println("Introduce opción:");
                    x = Integer.parseInt(teclado.nextLine());
                }
                
                switch (x){
                    case 1: buscarEmpleado();break;
                    case 2: borrarEmpleado();break;
                    case 3: listarEmpleados();break;
                }
                
                
            }while(x!=0);
 
 

    }
    
    public static void buscarEmpleado() throws IOException{
        System.out.println("Introduce 'id' del empleado a buscar:");
        int id = Integer.parseInt(teclado.nextLine());
         try {
            DaoEmpleado dao = DaoEmpleado.getInstance();
            Empleado empleado = dao.buscarEmpleado(id);
             System.out.println(empleado.nombre);
            
        } catch (SQLException e) { e.printStackTrace();}
        
    }
    
    
    
    public static void borrarEmpleado() throws IOException{
        System.out.println("Introduce 'id' del empleado a borrar");
        int id = Integer.parseInt(teclado.nextLine());
        try {
            DaoEmpleado dao = DaoEmpleado.getInstance();
            boolean resultado = dao.borrarEmpleado(id);
            if (resultado)System.out.println("Encontrado y borrado");
            else System.out.println("Empleado no encontrado.");
            
        } catch (SQLException e) { e.printStackTrace();}
        
    }
    
    public static void listarEmpleados() throws IOException{
        ArrayList<Empleado>lista = new ArrayList<>();
         try {
            DaoEmpleado dao = DaoEmpleado.getInstance();
            lista = dao.listarEmpleados();
            for (Empleado x: lista)System.out.println(x);
            
        } catch (SQLException e) { e.printStackTrace();}
    }
}
