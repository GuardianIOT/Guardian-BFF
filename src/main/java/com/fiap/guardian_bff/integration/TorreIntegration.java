package com.fiap.guardian_bff.integration;

import com.fiap.guardian_bff.model.Torre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "torre-service", url = "https://localhost:58194/api/Torre")
public interface TorreIntegration {

    @PostMapping
    Torre adicionarTorre(@RequestBody Torre torre);

    @GetMapping("/{id}")
    Torre obterTorrePorId(@PathVariable("id") int id);

    @GetMapping
    List<Torre> obterTorres();

    @PutMapping("/{id}")
    Torre alterarTorre(@PathVariable("id") int id, @RequestBody Torre torre);

    @DeleteMapping("/{id}")
    void deletarTorre(@PathVariable("id") int id);
}
