package br.com.evieira.desafioDev.controller;

import br.com.evieira.desafioDev.model.services.TransactionService;
import br.com.evieira.desafioDev.model.entities.Cnab;
import br.com.evieira.desafioDev.model.services.CnabService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(path = "/cnab")
public class CnabController {

    @Autowired
    private CnabService cnabService;

    @PostMapping
    public ResponseEntity<String> upload(@RequestParam MultipartFile file)  {
        if (file == null) {
            return ResponseEntity.notFound().build();
        }
        try {
            cnabService.read(file.getInputStream());
            return ResponseEntity.ok("Arquivo importado com sucesso!");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Erro ao realizar upload do arquivo");
        }
    }

    @GetMapping
    public ResponseEntity<List<Map>> get() {
        final List<Map> lista = cnabService.getAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/loja/{loja}")
    public ResponseEntity<List<Map>> getByLoja(@PathVariable String loja) {
        final List<Map> lista = cnabService.getAllByLoja(loja);
        return ResponseEntity.ok(lista);
    }
}
