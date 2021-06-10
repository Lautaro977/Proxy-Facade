package Modelo;

import java.util.List;
import java.util.Map;

public interface DBFacade {

	/**
	 * Abre una conexi�n a una base de datos
	 */
	public void open();

	/**
	 * Ejecuta una sentencia SQL.
	 *
	 * @return una Lista de filas, donde cada fila es un Mapa con nombreColumna =>
	 *         valorColumna. O una lista vacia.
	 * @throws RuntimeException si la conexi�n no esta abierta o existe un error en
	 *                          la sentencia sql
	 */
	public List<Map<String, String>> queryResultAsAsociation(String sql);

	/**
	 * Ejecuta una sentencia SQL.
	 *
	 * @return una Lista de filas, donde cada fila es una arreglo. O una lista
	 *         vacia. P�gina 3 de 4 Licenciatura en Sistemas Orientaci�n a Objetos
	 *         II
	 *
	 * @throws RuntimeException si la conexi�n no esta abierta o existe un error en
	 *                          la sentencia sql
	 */
	public List<String[]> queryResultAsArray(String sql);

	/**
	 * Cierra la conexi�n a la base de datos.
	 */
	public void close();
}