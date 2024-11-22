package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.dto.util.ErrorMessageDTO;
import com.fiap.guardian_bff.dto.util.SuccessMessageDTO;
import com.fiap.guardian_bff.exceptions.BusinessException;
import com.fiap.guardian_bff.service.OllamaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Clima", description = "API para report sobre o clima na região dos parques")
@Controller
@RequestMapping("/clima")
public class ClimaController {

    @Autowired
    private OllamaService ollamaService;

    @Operation(summary = "Reliza uma análise com base em IA sobre as condições climáticas recebendo o id dos dados a serem análisados",
            responses = { @ApiResponse(responseCode = "200", description = "Sucesso")})
    @GetMapping("/{id}")
    public ResponseEntity analisarClima(@PathVariable("id") Long idDados) {
        try {
            return ResponseEntity.ok(new SuccessMessageDTO(ollamaService.analisarClima(idDados)));
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }
}
