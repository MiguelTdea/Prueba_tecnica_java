package com.ias.movies.controller;

import com.ias.movies.model.Pelicula;
import com.ias.movies.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    // Obtener todas las películas con paginación
    @GetMapping
    public ResponseEntity<Page<Pelicula>> getAllPeliculas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(peliculaService.getAllPeliculas(page, size));
    }

    // Obtener una película por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPeliculaById(@PathVariable Long id) {
        Pelicula pelicula = peliculaService.getPeliculaById(id);
        return pelicula != null ? ResponseEntity.ok(pelicula) : ResponseEntity.notFound().build();
    }

    // Crear una nueva película
    @PostMapping
    public ResponseEntity<Pelicula> createPelicula(@RequestBody Pelicula pelicula) {
        return ResponseEntity.ok(peliculaService.savePelicula(pelicula));
    }

    // Actualizar una película existente
    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula peliculaDetails) {
        Pelicula pelicula = peliculaService.getPeliculaById(id);
        if (pelicula == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Actualizar los campos de la película existente
        pelicula.setNombre(peliculaDetails.getNombre());
        pelicula.setDescripcion(peliculaDetails.getDescripcion());
        pelicula.setPuntuacion(peliculaDetails.getPuntuacion());
        pelicula.setDuracion(peliculaDetails.getDuracion());
        pelicula.setCategoria(peliculaDetails.getCategoria());
        
        Pelicula updatedPelicula = peliculaService.savePelicula(pelicula);
        return ResponseEntity.ok(updatedPelicula);
    }

    // Eliminar una película por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePelicula(@PathVariable Long id) {
        peliculaService.deletePelicula(id);
        return ResponseEntity.ok("Categoría eliminada exitosamente");
    }
}
