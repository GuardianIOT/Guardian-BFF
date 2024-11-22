package com.fiap.guardian_bff.integration;

import com.fiap.guardian_bff.model.Aerogerador;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "aerogerador-service", url = "https://localhost:58194/api/Aerogerador")
public interface AerogeradorIntegration {

    @PostMapping
    Aerogerador adicionarAerogerador(@RequestBody Aerogerador aerogerador);

    @GetMapping("/{id}")
    Aerogerador obterAerogeradorPorId(@PathVariable("id") int id);

    @GetMapping
    List<Aerogerador> obterAerogeradores();

    @PutMapping("/{id}")
    Aerogerador alterarAerogerador(@PathVariable("id") int id, Aerogerador aerogerador);

    @DeleteMapping("/{id}")
    void deletarAerogerador(@PathVariable("id") int id);
}
