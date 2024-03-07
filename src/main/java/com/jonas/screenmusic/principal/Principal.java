package com.jonas.screenmusic.principal;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.jonas.screenmusic.model.Artista;
import com.jonas.screenmusic.model.Musica;
import com.jonas.screenmusic.model.TipoArtista;
import com.jonas.screenmusic.repository.ArtistaRepository;
import com.jonas.screenmusic.service.ConsultaChatGPT;

public class Principal {

	private final ArtistaRepository repositorio;
	Scanner leitura = new Scanner(System.in);

	public Principal(ArtistaRepository repositorio) {
		this.repositorio = repositorio;
	}

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
		var cadastrarNovo = "s";

		while(cadastrarNovo.equalsIgnoreCase("s")) {
			System.out.println("Informe o nome do artista: ");
			var nome = leitura.nextLine();
			System.out.println("Informe o tipo do artista:  (solo, dupla ou banda)");
			var tipo = leitura.nextLine();
			TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
			Artista artista = new Artista(nome, tipoArtista);
			repositorio.save(artista);
			System.out.println("Cadastrar novo artista? (S/N)");
			cadastrarNovo = leitura.nextLine();
		}
	}

	private void cadastrarMusicas () {
		System.out.println("Cadastrar música de qual artista? ");
		var nome = leitura.nextLine();
		Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
		if (artista.isPresent()) {
			System.out.println("Informe o título da música: ");
			var nomeMusica = leitura.nextLine();
			Musica musica = new Musica(nomeMusica);
			musica.setArtista(artista.get());
			artista.get().getMusicas().add(musica);
			repositorio.save(artista.get());
		} else {
			System.out.println("Artista não encontrado");
		}
	}

	private void listarMusicas () {
		List<Artista> artista = repositorio.findAll();
		artista.forEach(a -> a.getMusicas().forEach(System.out::println));
	}

	private void buscarMusicasPorArtistas () {
		System.out.println("Buscar músicas de que artista?");
		var nome = leitura.nextLine();
		List<Musica> musicas = repositorio.buscaMusicasPorArtista(nome);
		musicas.forEach(System.out::println);
	}

	private void pesquisarDadosSobreUmArtista () {
		System.out.println("Pesquisar dados sobre qual artista?");
		var nome = leitura.nextLine();
		var resposta = ConsultaChatGPT.obterInformacao(nome);
		System.out.println(resposta.trim());

	}
}
