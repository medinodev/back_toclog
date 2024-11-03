package controller;

import com.example.entity.Visitante;
import com.example.service.VisitanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/visitantes")
public class VisitanteController {

    @Autowired
    private VisitanteService visitanteService;

    @GetMapping
    public List<Visitante> getAllVisitantes(){
        return visitanteService.findAllVisitantes();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Visitante> getVisitanteById(@PathVariable Long id){
        return visitanteService.findVisitanteById(id)
                .map(visitante -> new ResponseEntity<>(visitante, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public Visitante createVisitante(@RequestBody Visitante visitante){
        return visitanteService.saveVisitante(visitante);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Visitante> updateVisitante(@PathVariable Long id, @RequestBody Visitante visitante){
        try {
            return new ResponseEntity<>(visitanteService.updateVisitante(id, visitante), HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisitante(@PathVariable Long id){
        visitanteService.deleteVisitante(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}