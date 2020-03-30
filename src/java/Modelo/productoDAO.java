
package Modelo;

import Config.conexion;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

public class productoDAO {
    conexion cn = new conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<producto>productos= new ArrayList<>();
        String sql= "select * from producto";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {                
                producto p = new producto();
                p.setId(rs.getInt(1));
                p.setNombres(rs.getString(2));
                p.setFoto(rs.getBinaryStream(3));
                p.setDescripcion(rs.getString(4));
                p.setPrecio(rs.getDouble(5));
                p.setStock(rs.getInt(6));
                productos.add(p);
            }
        } catch (SQLException e) {
        }
        return productos;
    }
    
    public void listarImg(int id, HttpServletResponse response){
        String sql = "select * from producto where idproducto ="+id;
        InputStream inputStream=null;
        OutputStream outputStream= null;
        BufferedInputStream bufferedInputStream=null;
        BufferedOutputStream bufferedOutputStream= null;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            if (rs.next()) {
                inputStream = rs.getBinaryStream("foto");
            }
            bufferedInputStream = new BufferedInputStream(inputStream);
            bufferedOutputStream = new BufferedOutputStream(outputStream);
            int i=0;
            while ((i=bufferedInputStream.read()) !=-1) {                
                bufferedOutputStream.write(i);
            }
        } catch (IOException | SQLException e) {
        }
    }
    
    
}
