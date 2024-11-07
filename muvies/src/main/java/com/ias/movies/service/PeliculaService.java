package com.ias.movies.service;


import com.ias.movies.model.Pelicula;
import com.ias.movies.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;


@Service
public class PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    public Page<Pelicula> getAllPeliculas(int page, int size) {
        return peliculaRepository.findAll(PageRequest.of(page, size));
    }

    public Pelicula getPeliculaById(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    public Pelicula savePelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    public void deletePelicula(Long id) {
        peliculaRepository.deleteById(id);
    }
}