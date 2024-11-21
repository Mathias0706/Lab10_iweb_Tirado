package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.pelicula;
import com.example.pruebalaboratorio1.beans.streaming;

import java.sql.*;
import java.util.ArrayList;

public class peliculaDao extends baseDao{

    public ArrayList<pelicula> listarPeliculas() {

        ArrayList<pelicula> listaPeliculas = new ArrayList<>();

        try (Connection conn = this.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT A.*, B.NOMBRE, C.NOMBRESERVICIO FROM  \n" +
                     "(SELECT * FROM PELICULA ) AS A \n" +
                     "INNER JOIN \n" +
                     "(SELECT * FROM GENERO) AS B\n" +
                     "ON A.IDGENERO = B.IDGENERO\n" +
                     "INNER JOIN \n" +
                     "(SELECT * FROM STREAMING) AS C\n" +
                     "ON A.IDSTREAMING = C.IDSTREAMING\n" +
                     "ORDER BY RATING DESC , BOXOFFICE DESC")){


            while (rs.next()) {
                pelicula movie = new pelicula();
                genero genero = new genero();
                streaming streaming = new streaming();
                int idPelicula = rs.getInt(1);
                movie.setIdPelicula(idPelicula);
                String titulo = rs.getString("titulo");
                movie.setTitulo(titulo);
                String director = rs.getString("director");
                movie.setDirector(director);
                int anoPublicacion = rs.getInt("anoPublicacion");
                movie.setAnoPublicacion(anoPublicacion);
                double rating = rs.getDouble("rating");
                movie.setRating(rating);
                double boxOffice = rs.getDouble("boxOffice");
                movie.setBoxOffice(boxOffice);
                int idGenero = rs.getInt("idGenero");
                int idStreaming = rs.getInt("idStreaming");
                String nombreServ = rs.getString("nombreServicio");
                movie.setStreaming(nombreServ);
                String nombregenero = rs.getString("nombre");
                movie.setGenero(nombregenero);
                String duracion = rs.getString("duracion");
                movie.setDuracion(duracion);
                boolean oscar = rs.getBoolean("premioOscar");
                movie.setPremioOscar(oscar);


                //boolean validador= validarBorrado(movie);
                //movie.setValidadorBorrado(validador);

                listaPeliculas.add(movie);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaPeliculas;
    }

    public ArrayList<pelicula> listarPeliculasFiltradas(int idGenero, int idStreaming) {

        ArrayList<pelicula> listaPeliculasFiltradas= new ArrayList<>();


        return listaPeliculasFiltradas;
    }

    // AGREGAR CAMPOS FALTANTES (GENERO, STREAMING)
    public void editarPelicula(int idPelicula, String titulo, String director, int anoPublicacion, double rating, double boxOffice){

            String sql = "UPDATE PELICULA SET titulo = ?, director = ?, anoPublicacion = ? ," +
            "rating = ?, boxOffice = ? WHERE IDPELICULA = ?";

            try (Connection conn = this.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, titulo);
                    pstmt.setString(2, director);
                    pstmt.setInt(3, anoPublicacion);
                    pstmt.setDouble(4, rating);
                    pstmt.setDouble(5, boxOffice);
                    pstmt.setInt(6, idPelicula);
                    pstmt.executeUpdate();
                }catch (SQLException e) {
                e.printStackTrace();
            }



    }


    public void borrarPelicula(int idPelicula) {

        String sql = "DELETE FROM protagonistas\n" +
                "WHERE idPelicula = ?;\n" +
                "\n"+
                "DELETE FROM pelicula\n" +
                "WHERE idPelicula = ?";
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);) {
            pstmt.setInt(1,idPelicula);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
