package main;

import Modelo.Persona;
import Modelo.Telefono;
import Persistencia.PersonaDao;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PersonaDao dao = new PersonaDao();
		Persona p = dao.personaPorId(1);
		System.out.println(p.nombre());
		for (Telefono telefono : p.telefonos()) {
			System.out.println(telefono);
		}

	}

}
