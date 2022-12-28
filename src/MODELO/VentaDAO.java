package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class VentaDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public boolean Registrarven(Venta v) {
        String sql = "Insert into venta ( cliente,vendedor,codigo,marca,talla, descripcion ,cantidad, precio, valor,total,totalp,fecha )values(?,?,?,?,?,?,?,?,?,?,?,current_timestamp())";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getVendedor());
            ps.setString(3, v.getCodigo());
            ps.setString(4, v.getMarca());
            ps.setInt(5, v.getTalla());
            ps.setString(6, v.getDescripcion());
            ps.setInt(7, v.getCantidad());
            ps.setInt(8, v.getPrecio());
            ps.setInt(9, v.getValor());
            ps.setInt(10, v.getTotal());
            ps.setInt(11, v.getTotalp());
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

}
