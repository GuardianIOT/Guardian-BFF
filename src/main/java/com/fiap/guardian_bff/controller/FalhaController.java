package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.model.Falha;
import com.fiap.guardian_bff.service.FalhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Falha", description = "API que realiza integração com o serviço de falhas")
@Controller
@RequestMapping("/falha")
public class FalhaController {

    @Autowired
    private FalhaService falhaService;

    @Operation(summary = "Cadastra uma Falha utilizando o microsserviço adequado", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso", content = @Content(schema = @Schema(implementation = Falha.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @PostMapping
    public ResponseEntity criarFalha(@RequestBody Falha falha) {
        try {
            Falha falhaCriada = falhaService.criarFalha(falha);
            return ResponseEntity.status(HttpStatus.CREATED).body(falhaCriada);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }

    @Operation(summary = "Busca uma Falha na base de dados usando o id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = Falha.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping("/{id}")
    public ResponseEntity obterFalhaPorId(@PathVariable("id") int id) {
        try {
            Falha falha = falhaService.obterFalhaPorId(id);
            return ResponseEntity.ok(falha);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }

    @Operation(summary = "Lista as falhas", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Falha.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping
    public ResponseEntity obterFalhas() {
        try {
            List<Falha> falhas = falhaService.obterFalhas();
            return ResponseEntity.ok(falhas);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");

        }
    }

    @Operation(summary = "Altera uma Falha na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = Falha.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PutMapping("/{id}")
    public ResponseEntity alterarFalha(@PathVariable("id") int id, @RequestBody Falha falha) {
        try {
            Falha falhaAlterada = falhaService.alterarFalha(id, falha);
            return ResponseEntity.ok(falhaAlterada);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");

        }
    }

    @Operation(summary = "Deleta uma Falha na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = Falha.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @DeleteMapping("/{id}")
    public ResponseEntity deletarFalha(@PathVariable("id") int id) {
        try {
            falhaService.deletarFalha(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }
}
