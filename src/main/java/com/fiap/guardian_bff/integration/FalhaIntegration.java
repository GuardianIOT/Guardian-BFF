package com.fiap.guardian_bff.integration;

import com.fiap.guardian_bff.model.Falha;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "falha-service", url = "https://localhost:58194/api/Falha")
public interface FalhaIntegration {

    @PostMapping
    Falha adicionarFalha(@RequestBody Falha falha);

    @GetMapping("/{id}")
    Falha obterFalhaPorId(@PathVariable("id") int id);

    @GetMapping
    List<Falha> obterFalhas();

    @PutMapping("/{id}")
    Falha alterarFalha(@PathVariable("id") int id, @RequestBody Falha falha);

    @DeleteMapping("/{id}")
    void deletarFalha(@PathVariable("id") int id);
}
