package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import MODELO.Detalle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VentaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int IdVenta(){
        int id =0;
        String sql = "Select MAX(id) from venta ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }

    public int Registrarven(Venta v) {
        String sql = "Insert into venta ( cliente,vendedor,total,fecha )values(?,?,?,current_timestamp())";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getVendedor());
            ps.setInt(3, v.getTotal());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        return r;
    }

    
    public int RegistrarDetalle(Detalle de){
        String sql = "Insert into detalle (codigopro,marca,talla,cantidad,precio,valor,total,id_venta,fecha) values (?,?,?,?,?,?,?,?,current_timestamp())";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, de.getCodigopro());
            ps.setString(2, de.getMarca());
            ps.setInt(3, de.getTall());
            ps.setInt(4, de.getCantidad());
            ps.setInt(5, de.getPrecio());
            ps.setInt(6, de.getValor());
            ps.setInt(7, de.getTotal());
            ps.setInt(8, de.getId_venta());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        }
        return r;
    }
    
    public boolean ActualizarCantidad(int cant,String cod,int talla){
        String sql = "update producto Set cantidad = ? where codigo = ? and talla = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cant);
            ps.setString(2,cod);
            ps.setInt(3, talla);
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    
  
    
     public List ListarVentas(String date) {
        List<Detalle> ListaVen = new ArrayList();
        String sql = "SELECT * FROM detalle where fecha like '"+date+"' ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Detalle de = new Detalle();
                de.setId(rs.getInt("id"));
                de.setCodigopro(rs.getString("codigopro"));
                de.setMarca(rs.getString("marca"));
                de.setTall(rs.getInt("talla"));
                de.setCantidad(rs.getInt("cantidad"));
                de.setPrecio(rs.getInt("precio"));
                de.setValor(rs.getInt("valor"));
                de.setTotal(rs.getInt("total"));
                de.setId_venta(rs.getInt("id_venta"));
                de.setFecha(rs.getString("fecha"));
                
                ListaVen.add(de);
                        
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return ListaVen;
    }
}
