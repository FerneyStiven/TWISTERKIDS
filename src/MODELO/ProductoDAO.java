
package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class ProductoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps ;
    
    public boolean RegistarProductos(Producto pro){
        String sql = "Insert into producto ( codigo,marca,talla, descripcion ,cantidad, precio, provedor )values(?,?,?,?,?,?,?)";
        try {
            con =cn.getConnection();
            ps= con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2,pro.getMarca());
            ps.setInt(3,pro.getTalla());
            ps.setString(4,pro.getDescripcion());
            ps.setInt(5,pro.getCantidad());
            ps.setInt(6,pro.getPrecio());
            ps.setString(7,pro.getProvedor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
        
        
    }
}
