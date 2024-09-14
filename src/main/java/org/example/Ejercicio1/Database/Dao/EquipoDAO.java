package org.example.Ejercicio1.Database.Dao;

import org.example.Ejercicio1.Database.Model.Equipo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EquipoDAO {
    private Connection connection;

    public EquipoDAO(Connection connection) {
        this.connection = connection;
    }

    // Método para agregar un equipo
    public void agregarEquipo(Equipo equipo) throws SQLException {
        String query = "INSERT INTO equipos_champions (nombre, pais, ciudad, estadio, fundacion, entrenador, web_oficial, facebook, twitter, instagram, patrocinador_principal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, equipo.getNombre());
        stmt.setString(2, equipo.getPais());
        stmt.setString(3, equipo.getCiudad());
        stmt.setString(4, equipo.getEstadio());
        stmt.setInt(5, equipo.getFundacion());
        stmt.setString(6, equipo.getEntrenador());
        stmt.setString(7, equipo.getWebOficial());
        stmt.setString(8, equipo.getFacebook());
        stmt.setString(9, equipo.getTwitter());
        stmt.setString(10, equipo.getInstagram());
        stmt.setString(11, equipo.getPatrocinadorPrincipal());
        stmt.executeUpdate();
    }

    // Método para actualizar un equipo
    public void actualizarEquipo(Equipo equipo) throws SQLException {
        String query = "UPDATE equipos_champions SET nombre = ?, pais = ?, ciudad = ?, estadio = ?, fundacion = ?, entrenador = ?, web_oficial = ?, facebook = ?, twitter = ?, instagram = ?, patrocinador_principal = ? WHERE id_equipo = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, equipo.getNombre());
        stmt.setString(2, equipo.getPais());
        stmt.setString(3, equipo.getCiudad());
        stmt.setString(4, equipo.getEstadio());
        stmt.setInt(5, equipo.getFundacion());
        stmt.setString(6, equipo.getEntrenador());
        stmt.setString(7, equipo.getWebOficial());
        stmt.setString(8, equipo.getFacebook());
        stmt.setString(9, equipo.getTwitter());
        stmt.setString(10, equipo.getInstagram());
        stmt.setString(11, equipo.getPatrocinadorPrincipal());
        stmt.setInt(12, equipo.getIdEquipo());
        stmt.executeUpdate();
    }

    // Método para eliminar un equipo
    public void eliminarEquipo(int idEquipo) throws SQLException {
        String query = "DELETE FROM equipos_champions WHERE id_equipo = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, idEquipo);
        stmt.executeUpdate();
    }

    // Método para buscar un equipo por su ID
    public Equipo buscarEquipoPorId(int idEquipo) throws SQLException {
        String query = "SELECT * FROM equipos_champions WHERE id_equipo = ?";
        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setInt(1, idEquipo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(rs.getInt("id_equipo"));
            equipo.setNombre(rs.getString("nombre"));
            equipo.setPais(rs.getString("pais"));
            equipo.setCiudad(rs.getString("ciudad"));
            equipo.setEstadio(rs.getString("estadio"));
            equipo.setFundacion(rs.getInt("fundacion"));
            equipo.setEntrenador(rs.getString("entrenador"));
            equipo.setWebOficial(rs.getString("web_oficial"));
            equipo.setFacebook(rs.getString("facebook"));
            equipo.setTwitter(rs.getString("twitter"));
            equipo.setInstagram(rs.getString("instagram"));
            equipo.setPatrocinadorPrincipal(rs.getString("patrocinador_principal"));
            return equipo;
        }

        return null;
    }

    // Método para obtener todos los equipos
    public List<Equipo> obtenerTodosLosEquipos() throws SQLException {
        String query = "SELECT * FROM equipos_champions";
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();

        List<Equipo> equipos = new ArrayList<>();
        while (rs.next()) {
            Equipo equipo = new Equipo();
            equipo.setIdEquipo(rs.getInt("id_equipo"));
            equipo.setNombre(rs.getString("nombre"));
            equipo.setPais(rs.getString("pais"));
            equipo.setCiudad(rs.getString("ciudad"));
            equipo.setEstadio(rs.getString("estadio"));
            equipo.setFundacion(rs.getInt("fundacion"));
            equipo.setEntrenador(rs.getString("entrenador"));
            equipo.setWebOficial(rs.getString("web_oficial"));
            equipo.setFacebook(rs.getString("facebook"));
            equipo.setTwitter(rs.getString("twitter"));
            equipo.setInstagram(rs.getString("instagram"));
            equipo.setPatrocinadorPrincipal(rs.getString("patrocinador_principal"));
            equipos.add(equipo);
        }

        return equipos;
    }
}
