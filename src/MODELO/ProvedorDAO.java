package MODELO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProvedorDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

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
    
    public List ListarProvedor(){
        List<Provedor> Listapr = new ArrayList();
        String sql = "select * from provedor";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs= ps.executeQuery();
            while (rs.next()) {                
                Provedor pr = new Provedor();
                pr.setId(rs.getInt("id"));
                pr.setDocumento(rs.getString("documento"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getString("telefono"));
                pr.setCorreo(rs.getString("correo"));
               Listapr.add(pr);
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return Listapr;
    }
            
}
