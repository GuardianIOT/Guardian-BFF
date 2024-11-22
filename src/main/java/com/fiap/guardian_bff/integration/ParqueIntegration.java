package com.fiap.guardian_bff.integration;


import com.fiap.guardian_bff.model.Parque;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "parque-service", url = "https://localhost:58194/api/Parque")
public interface ParqueIntegration {

    @PostMapping
    Parque adicionarParque(@RequestBody Parque parque);

    @GetMapping("/{id}")
    Parque obterParquePorId(@PathVariable("id") int id);

    @GetMapping
    List<Parque> obterParques();

    @PutMapping("/{id}")
    Parque alterarParque(@PathVariable("id") int id, @RequestBody Parque parque);

    @DeleteMapping("/{id}")
    void deletarParque(@PathVariable("id") int id);
}
