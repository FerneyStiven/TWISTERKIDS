/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author ferne
 */
public class ClienteDAO {
    Conexion  cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    
            
    public boolean RegistrarCliente(Cliente cl){
        String sql= "INSERT INTO cliente (documento,nombre,correo,telefono) VALUES(?,?,?,?) ";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,cl.getDocumento());
            ps.setString(2, cl.getNombre());
            ps.setString(3, cl.getCorreo());
            ps.setInt(4, cl.getTelefono());
            ps.execute();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString());
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
