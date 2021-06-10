package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class facadeImplementacion implements DBFacade {

	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/proxy";
	private static final String USUARIO = "root";
	private static final String CLAVE = "";
	Connection conexion;

	@Override
	public void open() {
		// TODO Auto-generated method stub
		Connection conexion = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			this.conexion = conexion;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Error al cargar el controlador");

		} catch (SQLException e) {
			throw new RuntimeException("Error en la conexion");
		}

	}

	@Override
	public List<Map<String, String>> queryResultAsAsociation(String sql) {
		// TODO Auto-generated method stub
		this.open();
		List<Map<String, String>> listaMap = new ArrayList<Map<String, String>>();
		try (PreparedStatement statement = this.conexion.prepareStatement(sql);) {
			ResultSet result = statement.executeQuery();

			ResultSetMetaData md = result.getMetaData();

			while (result.next()) {
				for (int i = 1; i < md.getColumnCount(); i++) {
					String nombreColumna = md.getColumnName(i);
					String valor = result.getString(i);
					Map<String, String> mapa = new HashMap<>();
					mapa.put(nombreColumna, valor);
					listaMap.add(mapa);
				}

			}

		} catch (SQLException e) {
			throw new RuntimeException("Error en la Conexion");
		}
		return listaMap;
	}

	@Override
	public List<String[]> queryResultAsArray(String sql) {
		// TODO Auto-generated method stub
		this.open();
		List<String[]> lista = new ArrayList<String[]>();
		try (PreparedStatement statement = this.conexion.prepareStatement(sql);) {
			ResultSet result = statement.executeQuery();

			ResultSetMetaData md = result.getMetaData();

			while (result.next()) {
				for (int i = 1; i < md.getColumnCount(); i++) {
					String nombreColumna = md.getColumnName(i);
					String valor = result.getString(i);
					String[] arreglo = { nombreColumna, valor };
					lista.add(arreglo);
				}

			}
			this.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error en la Conexion");
		}

		return lista;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		try {
			this.conexion.close();
		} catch (SQLException e) {
			throw new RuntimeException("Error al cerrar la conexion");
		}

	}

}
