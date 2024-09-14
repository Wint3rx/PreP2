package org.example.Ejercicio1.Database.Dao;

import org.example.Ejercicio1.Database.Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection conn;

    // Constructor que recibe la conexión a la base de datos
    public UsuarioDAO(Connection conn) {
        this.conn = conn;
    }

    // Método para verificar si el correo ya existe
    public boolean existeCorreo(String correo) throws SQLException {
        String query = "SELECT COUNT(*) FROM tb_usuarios WHERE correo = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, correo);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt(1) > 0;  // Si el contador es mayor que 0, el correo ya existe
        }
        return false;
    }

    // Método para insertar un usuario
    public void insertarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO tb_usuarios (carne, nombre, correo, seccion, telegramid, activo) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCarne());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getSeccion());
            stmt.setLong(5, usuario.getTelegramId());
            stmt.setString(6, usuario.getActivo());
            stmt.executeUpdate();
        }
    }

    // Método para actualizar un usuario
    public void actualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE tb_usuarios SET carne = ?, nombre = ?, correo = ?, seccion = ?, telegramid = ?, activo = ? WHERE idusuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, usuario.getCarne());
            stmt.setString(2, usuario.getNombre());
            stmt.setString(3, usuario.getCorreo());
            stmt.setString(4, usuario.getSeccion());
            stmt.setLong(5, usuario.getTelegramId());
            stmt.setString(6, usuario.getActivo());
            stmt.setInt(7, usuario.getIdUsuario());
            stmt.executeUpdate();
        }
    }

    // Método para eliminar un usuario
    public void borrarUsuario(int idUsuario) throws SQLException {
        String sql = "DELETE FROM tb_usuarios WHERE idusuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.executeUpdate();
        }
    }

    // Método para obtener un usuario por id
    public Usuario obtenerUsuarioPorId(int idUsuario) throws SQLException {
        String sql = "SELECT * FROM tb_usuarios WHERE idusuario = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getInt("idusuario"));
                    usuario.setCarne(rs.getString("carne"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setSeccion(rs.getString("seccion"));
                    usuario.setTelegramId(rs.getLong("telegramid"));
                    usuario.setActivo(rs.getString("activo"));
                    return usuario;
                }
            }
        }
        return null; // Retorna null si no encuentra el registro
    }

    // Método para obtener todos los usuarios (opcional)
    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM tb_usuarios";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(rs.getInt("idusuario"));
                usuario.setCarne(rs.getString("carne"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setCorreo(rs.getString("correo"));
                usuario.setSeccion(rs.getString("seccion"));
                usuario.setTelegramId(rs.getLong("telegramid"));
                usuario.setActivo(rs.getString("activo"));
                listaUsuarios.add(usuario);
            }
        }
        return listaUsuarios;
    }
}
