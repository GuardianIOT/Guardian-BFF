package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.dto.util.ErrorMessageDTO;
import com.fiap.guardian_bff.service.OllamaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Clima", description = "API para report sobre o clima na região dos parques")
@Controller
@RequestMapping("/clima")
public class ClimaController {

    @Autowired
    private OllamaService ollamaService;

    @Operation(summary = "Reliza uma análise com base IA sobre as condições climáticas", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso")})
    @GetMapping
    public ResponseEntity analisarClima() {
        try {
            return ResponseEntity.ok(ollamaService.analisarClima());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorMessageDTO("Ocorreu um erro inesperado"));
        }
    }
}
