package org.example.Ejercicio1.Database.Dao;

import org.example.Ejercicio1.Database.Model.Datos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatosDAO {
    private Connection conn;

    public DatosDAO() {
        this.conn = conn;
    }

    // Método para obtener todos los datos
    public List<Datos> obtenerDatos() throws SQLException {
        List<Datos> lista = new ArrayList<>();
        String sql = "SELECT * FROM tb_datos";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            Datos datos = new Datos();
            datos.setCodigo(resultSet.getInt("codigo"));
            datos.setNombre(resultSet.getString("nombre"));
            datos.setApellido(resultSet.getString("apellido"));
            datos.setDepartamento(resultSet.getString("departamento"));
            datos.setFechaNacimiento(resultSet.getDate("fecha_nacimiento"));
            lista.add(datos);
        }

        return lista;
    }

    // Método para insertar datos
    public void insertarDatos(Datos datos) throws SQLException {
        String sql = "INSERT INTO tb_datos (nombre, apellido, departamento, fecha_nacimiento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, datos.getNombre());
            stmt.setString(2, datos.getApellido());
            stmt.setString(3, datos.getDepartamento());
            stmt.setDate(4, new java.sql.Date(datos.getFechaNacimiento().getTime()));
            stmt.executeUpdate();
        }
    }

    // Método para actualizar datos
    public void actualizarDatos(Datos datos) throws SQLException {
        String sql = "UPDATE tb_datos SET nombre = ?, apellido = ?, departamento = ?, fecha_nacimiento = ? WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, datos.getNombre());
            stmt.setString(2, datos.getApellido());
            stmt.setString(3, datos.getDepartamento());
            stmt.setDate(4, new java.sql.Date(datos.getFechaNacimiento().getTime()));
            stmt.setInt(5, datos.getCodigo());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar datos por código
    public void borrarDatos(int codigo) throws SQLException {
        String sql = "DELETE FROM tb_datos WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        }
    }

    // Método para buscar datos por código
    public Datos obtenerDatosPorCodigo(int codigo) throws SQLException {
        String sql = "SELECT * FROM tb_datos WHERE codigo = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Datos datos = new Datos();
                    datos.setCodigo(rs.getInt("codigo"));
                    datos.setNombre(rs.getString("nombre"));
                    datos.setApellido(rs.getString("apellido"));
                    datos.setDepartamento(rs.getString("departamento"));
                    datos.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
                    return datos;
                }
            }
        }
        return null; // Retorna null si no encuentra el registro
    }
}
