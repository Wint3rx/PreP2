package org.example.Ejercicio1.Database.Service;

import org.example.Ejercicio1.Database.Dao.DatosDAO;
import org.example.Ejercicio1.Database.Model.Datos;

import java.sql.SQLException;

public class DatosService {
    private DatosDAO datosDAO;

    public DatosService() {
        this.datosDAO = new DatosDAO();
    }

    public void agregarDatos(Datos datos) throws SQLException {
        datosDAO.insertarDatos(datos);
    }

    public void modificarDatos(Datos datos) throws SQLException {
        datosDAO.actualizarDatos(datos);
    }

    public void eliminarDatos(int codigo) throws SQLException {
        datosDAO.borrarDatos(codigo);
    }

    public Datos buscarDatosPorCodigo(int codigo) throws SQLException {
        return datosDAO.obtenerDatosPorCodigo(codigo);
    }
}
