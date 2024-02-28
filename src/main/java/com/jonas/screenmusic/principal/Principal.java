package com.jonas.screenmusic.principal;

import java.util.Random;
import java.util.Scanner;

public class Principal {

	Scanner leitura = new Scanner(System.in);

	public void exibeMenu() {

		int opcao = -1;

		while (opcao != 9) {
			var menu = """
				   **** Screen Music ****
				   
				   1 - Cadastrar artistas
				   2 - Cadastrar músicas
				   3 - Listar músicas
				   4 - Buscar músicas por artistas
				   5 - Pesquisar dados sobre um artista
				   
				   9 - Sair!
				""";

			System.out.println(menu);
			opcao = leitura.nextInt();
			leitura.nextLine();

			switch (opcao) {
			case 1:
				cadastrarArtistas();
				break;
			case 2:
				cadastrarMusicas();
				break;
			case 3:
				listarMusicas();
				break;
			case 4:
				buscarMusicasPorArtistas();
				break;
			case 5:
				pesquisarDadosSobreUmArtista();
				break;
			default:
				System.out.println("Saindo.....");
			}

		}

	}
	private void cadastrarArtistas () {
		System.out.println("teste 1");
	}

	private void cadastrarMusicas () {
	}

	private void listarMusicas () {
	}

	private void buscarMusicasPorArtistas () {
	}

	private void pesquisarDadosSobreUmArtista () {
	}
}
