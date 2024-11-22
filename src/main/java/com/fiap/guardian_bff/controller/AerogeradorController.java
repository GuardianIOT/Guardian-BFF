package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.model.Aerogerador;
import com.fiap.guardian_bff.service.AerogeradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Aerogerador", description = "API que realiza integração com serviço de cadastro de aerogeradores")
@Controller
@RequestMapping("/aerogerador")
public class AerogeradorController {

    @Autowired
    private AerogeradorService aerogeradorService;


    @Operation(summary = "Cadastra um Aerogerador utilizando o microsserviço adequado", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Aerogerador.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @PostMapping
    public ResponseEntity criarAerogerador(@RequestBody Aerogerador aerogerador) {
        try {
            aerogeradorService.criarAerogerador(aerogerador);
            return ResponseEntity.ok(aerogerador);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }


    @Operation(summary = "Busca um Aerogerador na base de dados usando o id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Aerogerador.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping("/{id}")
    public ResponseEntity obterAerogeradorPorId(@PathVariable int id) {
        try {
            Aerogerador aerogerador = aerogeradorService.obterAerogeradorPorId(id);
            return ResponseEntity.ok(aerogerador);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }

    @Operation(summary = "Lista os aerogeradores", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Aerogerador.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping
    public ResponseEntity obterAerogeradores() {
        try {
            List<Aerogerador> aerogeradors = aerogeradorService.obterAerogeradores();
            return ResponseEntity.ok(aerogeradors);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }

    @Operation(summary = "Altera um aerogerador na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Aerogerador.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PutMapping("/{id}")
    public ResponseEntity alterarGerador(@PathVariable("id") int id, Aerogerador aerogerador) {
        try {
            Aerogerador aerogeradors = aerogeradorService.alterarAerogerador(id, aerogerador);
            return ResponseEntity.ok(aerogeradors);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }

    @Operation(summary = "Deleta um aerogerador na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Aerogerador.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @DeleteMapping("/{id}")
    public ResponseEntity deletarAerogerador(@PathVariable("id") int id) {
        try {
            aerogeradorService.deletarAeroGerador(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }
}
