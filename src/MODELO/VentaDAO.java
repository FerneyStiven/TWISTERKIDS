package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import MODELO.Detalle;

public class VentaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;

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
}
