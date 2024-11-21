package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class baseDao {
    public Connection getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        String user = "root";
        String password = "123456";
        String url = "jdbc:mysql://localhost:3306/mydb?serverTimezone=America/Lima";
        return DriverManager.getConnection(url, user, password);
    }


    public boolean validarBorrado(pelicula movie) {
        boolean validador = true;
        return validador;
    }
}

