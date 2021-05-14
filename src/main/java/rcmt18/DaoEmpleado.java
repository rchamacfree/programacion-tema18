package rcmt18;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 *
 * @author rchamac
 */
public class DaoEmpleado {
    private static DaoEmpleado instance = null;
    private Connection con = null;
    
    private DaoEmpleado() throws SQLException, IOException {
        String JDBC_URL = "jdbc:mysql://localhost:3306/empresa";
        
        if (con == null){
            Properties props = new Properties();
            props.load(new FileInputStream("Archivos"+File.separator+"login.props"));
            con = DriverManager.getConnection(JDBC_URL, props);
        }
    }
    
        public static DaoEmpleado getInstance() throws SQLException, IOException {
            if (instance == null) instance = new DaoEmpleado();
            return instance;
        }
        
        public Empleado buscarEmpleado(int id) throws SQLException{
            
            String sql = "SELECT * FROM empleado WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            String nombre = rs.getString(1);
            Date fn = rs.getDate(2);
            String cat = rs.getString(3);
            Float sal = rs.getFloat(4);
            return new Empleado(nombre,fn,cat,sal);
        }
    
        public boolean borrarEmpleado(int id) throws SQLException{
            String sql = "DELETE FROM empleado WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int cant = ps.executeUpdate();
            if (cant != 0)return true;
            else return false;

        }
        
        public ArrayList<Empleado> listarEmpleados() throws SQLException{
            ArrayList<Empleado>lista = new ArrayList<>();
            String sql = "SELECT * FROM empleado";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String nombre = rs.getString(2);
                Date fn = rs.getDate(3);
                String cat = rs.getString(4);
                Float sal = rs.getFloat(5);
                lista.add(new Empleado(nombre,fn,cat,sal));
             }
         return lista;

        }
    
    
}
