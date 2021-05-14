
package rcmt18;

import java.sql.Date;

/**
 *
 * @author rchamac
 */
public class Empleado {
    public String nombre;
    public Date fn;
    public String categoria;
    public float salario;
    
        Empleado(String n, Date fn, String c, float s){
            this.nombre = n;
            this.fn = fn;
            this.categoria = c;
            this.salario = s;
                 
        }
        
        public String toString(){
            return String.format("%s - %s - %s - %.2f%n",this.nombre,this.fn.toString(),this.categoria,this.salario);
        }
    
}
