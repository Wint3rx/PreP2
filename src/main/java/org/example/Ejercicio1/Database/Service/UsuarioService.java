package org.example.Ejercicio1.Database.Service;

import org.example.Ejercicio1.Database.Dao.UsuarioDAO;
import org.example.Ejercicio1.Database.Model.Usuario;
import java.util.List;

import java.sql.SQLException;

public class UsuarioService {

    private static UsuarioDAO usuarioDAO;

    public UsuarioService(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    // Método para verificar si el correo ya existe
    public static boolean existeCorreo(String correo) throws SQLException {
        return usuarioDAO.existeCorreo(correo);
    }

    public static void agregarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.insertarUsuario(usuario);
    }

    public static void modificarUsuario(Usuario usuario) throws SQLException {
        usuarioDAO.actualizarUsuario(usuario);
    }

    public static void eliminarUsuario(int idUsuario) throws SQLException {
        usuarioDAO.borrarUsuario(idUsuario);
    }

    public static Usuario buscarUsuarioPorId(int idUsuario) throws SQLException {
        return usuarioDAO.obtenerUsuarioPorId(idUsuario);
    }

    public List<Usuario> obtenerTodosLosUsuarios() throws SQLException {
        return usuarioDAO.obtenerTodosLosUsuarios();
    }

}
