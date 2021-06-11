package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import Modelo.HashSetProxy;
import Modelo.Persona;
import Modelo.Telefono;

public class PersonaDao {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/proxy";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";

	private Connection obtenerConexion() {
		// Utilice aquí su motor de BD preferido
		Connection conexion = null;

		try {

			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Error al cargar el controlador");

		} catch (SQLException e) {
			throw new RuntimeException("Error en la conexion");
		}

		return conexion;
	}

	public Persona personaPorId(int id) {
		String sql = "select nombre from personas where id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new HashSetProxy(this, id);
			String nombrePersona = null;
			while (result.next()) {
				nombrePersona = result.getString(1);
			}
			return new Persona(id, nombrePersona, telefonos);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Set<Telefono> telefonosDePersonaId(int id) {
		String sql = "select t.numero from personas p, telefonos t where p.id = t.idPersona and p.id = ?";
		try (Connection conn = obtenerConexion(); PreparedStatement statement = conn.prepareStatement(sql);) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			Set<Telefono> telefonos = new HashSet<Telefono>();
			while (result.next()) {
				telefonos.add(new Telefono(result.getString(1)));
			}
			return telefonos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
