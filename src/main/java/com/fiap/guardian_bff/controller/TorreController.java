package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.model.Torre;
import com.fiap.guardian_bff.service.TorreService;
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

@Tag(name = "Torre", description = "API que realiza integração com o serviço de cadastro de torres")
@Controller
@RequestMapping("/torre")
public class TorreController {

    @Autowired
    private TorreService torreService;

    @Operation(summary = "Cadastra uma Torre utilizando o microsserviço adequado", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso", content = @Content(schema = @Schema(implementation = Torre.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @PostMapping
    public ResponseEntity criarTorre(@RequestBody Torre torre) {
        Torre torreCriada = torreService.criarTorre(torre);
        return ResponseEntity.status(HttpStatus.CREATED).body(torreCriada);
    }

    @Operation(summary = "Busca uma Torre na base de dados usando o id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = Torre.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping("/{id}")
    public ResponseEntity obterTorrePorId(@PathVariable int id) {
        Torre torre = torreService.obterTorrePorId(id);
        return ResponseEntity.ok(torre);
    }

    @Operation(summary = "Lista as torres", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Torre.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping
    public ResponseEntity obterTorres() {
        List<Torre> torres = torreService.obterTorres();
        return ResponseEntity.ok(torres);
    }

    @Operation(summary = "Altera uma Torre na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = Torre.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PutMapping("/{id}")
    public ResponseEntity alterarTorre(@PathVariable("id") int id, @RequestBody Torre torre) {
        Torre torreAlterada = torreService.alterarTorre(id, torre);
        return ResponseEntity.ok(torreAlterada);
    }

    @Operation(summary = "Deleta uma Torre na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(schema = @Schema(implementation = Torre.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @DeleteMapping("/{id}")
    public ResponseEntity deletarTorre(@PathVariable("id") int id) {
        torreService.deletarTorre(id);
        return ResponseEntity.noContent().build();
    }
}
