package lab.proj.controller;

import javax.naming.ldap.Control;

public class Controller {

	private Controller instance;

	private Controller() { }

	public Controller getInstance() {
		if (instance == null)
			instance = new Controller();

		return instance;
	}

}
