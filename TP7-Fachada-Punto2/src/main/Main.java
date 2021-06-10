package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import Modelo.DBFacade;
import Modelo.facadeImplementacion;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		DBFacade dbf = new facadeImplementacion();

		String sql = "select * from personas, telefonos where telefonos.idPersona = personas.id";

		List<Map<String, String>> lista = new ArrayList<Map<String, String>>();
		lista = dbf.queryResultAsAsociation(sql);

		System.out.println(lista);

		List<String[]> listaArreglo = new ArrayList<>();
		listaArreglo = dbf.queryResultAsArray(sql);

		System.out.println("---------------------------------------------------------");

		for (String[] strings : listaArreglo) {
			System.out.println(Arrays.deepToString(strings));
		}

	}

}
