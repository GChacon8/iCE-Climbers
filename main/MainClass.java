package main;

import java.io.IOException;

public class MainClass {

	public static void main(String[] args) throws IOException {
		new Game();
		Cliente cliente = new Cliente();
		cliente.iniciaSocket();
		cliente.enviar();
		cliente.recibir();
		cliente.cerrarSocket();
	}

}