package com.jonas.screenmusic.repository;

import java.util.List;
import java.util.Optional;

import com.jonas.screenmusic.model.Artista;
import com.jonas.screenmusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

	Optional<Artista> findByNomeContainingIgnoreCase(String nome);

	@Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome%")
	List<Musica> buscaMusicasPorArtista(String nome);

}
