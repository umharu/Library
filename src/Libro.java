/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Maximiliano
 */
public class Libro {

    public int getCod_libro() {
        return cod_libro;
    }

    public void setCod_libro(int cod_libro) {
        this.cod_libro = cod_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) throws Exception {
        if(titulo.isEmpty())
            throw new Exception("El titulo no puede estar vacio");
        this.titulo = titulo;
    }

    public String getAutor()  {

        return autor;
    }

    public void setAutor(String autor) throws Exception {
        if (autor.isEmpty())
            throw new Exception("El titulo no puede estar vacio");
        this.autor = autor;
        
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) throws Exception {
        if(precio<0)
            throw new Exception("El precio debe superar los $0");
        this.precio = precio;
    }
    
    
    private int cod_libro;
    private String titulo, autor;
    private double precio;
}