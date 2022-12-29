package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import MODELO.Producto;
import javax.swing.JOptionPane;

public class ProductoDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    public boolean RegistarProductos(Producto pro) {
        String sql = "Insert into producto ( codigo,marca,talla, descripcion ,cantidad, precio, provedor )values(?,?,?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getMarca());
            ps.setInt(3, pro.getTalla());
            ps.setString(4, pro.getDescripcion());
            ps.setInt(5, pro.getCantidad());
            ps.setInt(6, pro.getPrecio());
            ps.setString(7, pro.getProvedor());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }

    }

    public void ConsulatarProvedor(JComboBox provedor) {
        String sql = "Select nombre from provedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                provedor.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }

    }

    public List BuscarProducto() {
        String valor = JOptionPane.showInputDialog(null, "Digite una Referencia", "BUSCAR CALZADO", JOptionPane.PLAIN_MESSAGE);
       
         List<Producto> Listapro = new ArrayList();
        String sql = "SELECT * FROM producto where codigo like '"+valor+"' ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setMarca(rs.getString("marca"));
                pro.setTalla(rs.getInt("talla"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setPrecio(rs.getInt("precio"));
                pro.setProvedor(rs.getString("provedor"));
                Listapro.add(pro);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapro;
    }

    public List ListarProducto() {
        List<Producto> Listapro = new ArrayList();
        String sql = "SELECT * FROM producto";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id"));
                pro.setCodigo(rs.getString("codigo"));
                pro.setMarca(rs.getString("marca"));
                pro.setTalla(rs.getInt("talla"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setCantidad(rs.getInt("cantidad"));
                pro.setPrecio(rs.getInt("precio"));
                pro.setProvedor(rs.getString("provedor"));
                Listapro.add(pro);
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapro;
    }

    public boolean EliminarProducto(int id) {
        String sql = "DELETE FROM producto where id = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (SQLException e) {

            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {

                System.out.println(ex.toString());
            }
        }

    }

    public boolean ModificarProducto(Producto pro) {
        String sql = "Update producto SET codigo=?, marca =?, talla=? , descripcion=?, cantidad=?, precio=?, provedor=? WHERE  id=? ;";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getMarca());
            ps.setInt(3, pro.getTalla());
            ps.setString(4, pro.getDescripcion());
            ps.setInt(5, pro.getCantidad());
            ps.setInt(6, pro.getPrecio());
            ps.setString(7, pro.getProvedor());
            ps.setInt(8, pro.getId());
            ps.execute();

            return true;

        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }
    
    public Producto BuscarPro(String cod){
        Producto producto = new Producto();
        String sql = "Select * from producto where codigo = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1,cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                producto.setMarca(rs.getString("marca"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setCantidad(rs.getInt("cantidad"));
                producto.setPrecio(rs.getInt("precio"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return producto;
}
}
