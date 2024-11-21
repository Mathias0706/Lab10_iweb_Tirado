package com.example.pruebalaboratorio1.daos;

import com.example.pruebalaboratorio1.beans.genero;
import com.example.pruebalaboratorio1.beans.streaming;
import com.example.pruebalaboratorio1.beans.pelicula;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class listasDao {

    public ArrayList<genero> listarGeneros() {

        ArrayList<genero> listaGeneros = new ArrayList<>();
        return listaGeneros;
    }

    public ArrayList<streaming> listarStraming() {

        ArrayList<streaming> listaStreaming = new ArrayList<>();
        return listaStreaming;
    }
}
