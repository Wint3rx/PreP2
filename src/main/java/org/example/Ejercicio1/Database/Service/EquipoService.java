package org.example.Ejercicio1.Database.Service;

import org.example.Ejercicio1.Database.Dao.EquipoDAO;
import org.example.Ejercicio1.Database.Model.Equipo;

import java.sql.SQLException;
import java.util.List;

public class EquipoService {
    private EquipoDAO equipoDAO;

    public EquipoService(EquipoDAO equipoDAO) {
        this.equipoDAO = equipoDAO;
    }

    public void agregarEquipo(Equipo equipo) throws SQLException {
        equipoDAO.agregarEquipo(equipo);
    }

    public void actualizarEquipo(Equipo equipo) throws SQLException {
        equipoDAO.actualizarEquipo(equipo);
    }

    public void eliminarEquipo(int idEquipo) throws SQLException {
        equipoDAO.eliminarEquipo(idEquipo);
    }

    public Equipo buscarEquipoPorId(int idEquipo) throws SQLException {
        return equipoDAO.buscarEquipoPorId(idEquipo);
    }

    public List<Equipo> obtenerTodosLosEquipos() throws SQLException {
        return equipoDAO.obtenerTodosLosEquipos();
    }
}
