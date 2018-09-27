package br.com.unisinos.redes.ftp.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.unisinos.redes.ftp.tarefas.DistribuirTarefas;

public class ServidorTarefas {

	public static void main(String[] args) throws Exception {

		System.out.println("---- Iniciando Servidor ----");
		try (ServerSocket servidor = new ServerSocket(12345)) {
			ExecutorService threadPool = Executors.newCachedThreadPool();

			while (true) {

				Socket socket = servidor.accept();
				System.out.println("Aceitando novo cliente na porta " + socket.getPort());

				DistribuirTarefas distribuirTarefas = new DistribuirTarefas(socket);

				threadPool.execute(distribuirTarefas);
			}
		}
	}
}
