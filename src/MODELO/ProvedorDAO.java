package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProvedorDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;

    public boolean RegistrarProvedor(Provedor pr) {
        String sql = "INSERT INTO provedor (documento,nombre,telefono,correo) VALUES(?,?,?,?) ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, pr.getDocumento());
            ps.setString(2, pr.getNombre());
            ps.setString(3, pr.getTelefono());
            ps.setString(4, pr.getCorreo());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
                    
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }

    }
}
