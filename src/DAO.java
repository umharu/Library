/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.Set;

//DAO(String url) {
//    }

public class DAO {

    private Connection conn = null;
    private Statement st = null;

    public DAO(String url) throws Exception {
        String driver = "jdbc:sqlite:";
        try {
            conn = DriverManager.getConnection(driver + url);
            st = conn.createStatement();
        } catch (SQLException ex) {
            throw new Exception("Error al abrir la base de datos.", ex);
        }
    }

    public void altaLibro(Libro libro) throws Exception {
        String query = "INSERT INTO Libros (Titulo, Autor, PRECIO) VALUES('"
                + libro.getTitulo() + "', '" + libro.getAutor() + "', " + 
                libro.getPrecio() + ")";

        if (existe(libro.getTitulo())) {
            throw new Exception("El libro ya existe.");
        }

        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            throw new Exception("Error al insertar el libro.", ex);
        }
    }

    private boolean existe(String Titulo) {
        ResultSet rs = null;
        String query = "SELECT * FROM Libros WHERE Titulo='" + Titulo + "'";
        try {
            rs = st.executeQuery(query);
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public Libro buscarLibro(String Titulo) throws Exception {
    Libro libro = null;
    ResultSet rs = null;
    
    // Hacer que la búsqueda sea insensible a mayúsculas/minúsculas
    String busqueda = "SELECT * FROM Libros WHERE LOWER(Titulo) = LOWER('" + Titulo + "')";
    
    // Imprimir la consulta SQL para depuración
    System.out.println("Consulta SQL: " + busqueda);
    
    try {
        rs = st.executeQuery(busqueda);
        if (rs.next()) {
            libro = new Libro();
            libro.setCod_libro(rs.getInt("ID"));
            libro.setTitulo(rs.getString("Titulo"));
            libro.setAutor(rs.getString("Autor"));
            libro.setPrecio(rs.getDouble("Precio"));
        }
    } catch (SQLException ex) {
        throw new Exception("Error al buscar el libro.", ex);
    }
    
    return libro;
}
    public void modificarLibro (Libro libro)throws Exception {
        String query = "UPDATE Libros SET Autor='" + libro.getAutor() + 
                "', Precio=" + libro.getPrecio() + " WHERE Titulo='" + 
                libro.getTitulo() + "'";
       
        if(!existe(libro.getTitulo()))
            throw new Exception("El libro no existe");
        
        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            throw new Exception("Error al modificar el libro");
        }
    }

    void eliminarLibro(String titulo) throws Exception {
        String eliminar ="DELETE FROM Libros WHERE Titulo='" + titulo + "'";
        
        try {
            st.executeUpdate(eliminar);
        } catch (SQLException ex) {
            throw new Exception("El libro no pudo ser eliminado.");
        }
    }
    
    public void desconectar() throws Exception{
        try {
            st.close();
        } catch (SQLException ex) {
            throw new Exception("Error al cerrar la base de datos");
        }
    
    }
}
