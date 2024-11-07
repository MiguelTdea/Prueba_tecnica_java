package com.ias.movies.controller;

import com.ias.movies.model.Categoria;
import com.ias.movies.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }



    // Obtener una categoría por ID
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable int id) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
    }

    // Crear una nueva categoría
    @PostMapping
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.saveCategoria(categoria));
    }

    // Eliminar una categoría por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoria(@PathVariable int id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.ok("Categoría eliminada exitosamente");
    }

    // Actualizar una categoría 
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> updateCategoria(@PathVariable int id, @RequestBody Categoria categoriaDetails) {
        Categoria categoria = categoriaService.getCategoriaById(id);
        if (categoria == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Actualizar los campos de la categoría existente
        categoria.setNombre(categoriaDetails.getNombre());
       
        
        Categoria updatedCategoria = categoriaService.saveCategoria(categoria);
        return ResponseEntity.ok(updatedCategoria);
    }
    
}
