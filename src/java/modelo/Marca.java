 
package modelo;

import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Luis Fernando Paxel
 */
public class Marca {
    private String marca;
    private int id_marca;
    Conexion cn;

    public Marca(){}
    
    public Marca(String marca, int id_marca) {
        this.marca = marca;
        this.id_marca = id_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }
    
    
    public HashMap mostrar_marcas()
    {
        HashMap<String, String> drop_marca = new HashMap();
        try
        {
            String codigo_sql="Select idmarca as id, marca from marcas order by id";
            cn = new Conexion();
            cn.abrirCon();
            ResultSet consulta=cn.conexiondb.createStatement().executeQuery(codigo_sql);
            while(consulta.next())
            {
                drop_marca.put(consulta.getString("id"), consulta.getString("marca"));
            }
            
            cn.cerrarCon();
        }catch(SQLException ex)
        {
         System.out.println(ex.getMessage());   
        }
        
        return drop_marca;
    }
    
     public int eliminar()
    {
        int valor=0;
        
        try
        {
            PreparedStatement parametro;
            String codigo_sql="delete from db_punto_venta.marcas  where idmarca=?";
            cn = new Conexion();
            cn.abrirCon();
            parametro=(PreparedStatement) cn.conexiondb.prepareStatement(codigo_sql);
            parametro.setInt(1,getId_marca());
            
            valor=parametro.executeUpdate();
            cn.cerrarCon();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return valor;
    }
    
     public int modificar()
    {
        int valor=0;
        
        try
        {
            PreparedStatement parametro;
            String codigo_sql="update db_punto_venta.marcas set marca=? where idmarca=?";
            cn = new Conexion();
            cn.abrirCon();
            parametro=(PreparedStatement) cn.conexiondb.prepareStatement(codigo_sql);
            parametro.setString(1, getMarca());
            parametro.setInt(2,getId_marca());
            
            valor=parametro.executeUpdate();
            cn.cerrarCon();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return valor;
    }
    
    public int agregar()
    {
        int valor=0;
        
        try
        {
            PreparedStatement parametro;
            String codigo_sql="Insert into db_punto_venta.marcas (marca) values (?)";
            cn = new Conexion();
            cn.abrirCon();
            parametro=(PreparedStatement) cn.conexiondb.prepareStatement(codigo_sql);
            parametro.setString(1, getMarca());
            
            valor=parametro.executeUpdate();
            cn.cerrarCon();
        }catch(HeadlessException | SQLException ex)
        {
            System.out.println("error........"+ex.getMessage());
        }
        
        return valor;
    }
    
    public DefaultTableModel leer()
    {
        DefaultTableModel tabla = new DefaultTableModel();
        try
        {
            cn = new Conexion();
            String query="Select idmarca as id, marca from marcas";
            cn.abrirCon();
            ResultSet consulta=cn.conexiondb.createStatement().executeQuery(query);
            String encabezado[]={"id","Marca"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[]= new String [10];
            while(consulta.next())
            {
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("marca");
                tabla.addRow(datos);
            }
            cn.cerrarCon();
        }catch(SQLException ex)
        {
            System.out.println("Error:"+ex.getMessage());
        }
        return tabla;
    }
    
    
}