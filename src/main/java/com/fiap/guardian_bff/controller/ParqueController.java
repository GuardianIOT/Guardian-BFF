package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.model.Parque;
import com.fiap.guardian_bff.service.ParqueService;
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

@Tag(name = "Parque", description = "API que realiza integração com o serviço de cadastro de parques")
@Controller
@RequestMapping("/parque")
public class ParqueController {

    @Autowired
    private ParqueService parqueService;

    @Operation(summary = "Cadastra um Parque utilizando o microsserviço adequado", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Parque.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @PostMapping
    public ResponseEntity criarParque(@RequestBody Parque parque) {
        try {
            Parque parqueCriado = parqueService.criarParque(parque);
            return ResponseEntity.status(HttpStatus.CREATED).body(parqueCriado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }

    @Operation(summary = "Busca um Parque na base de dados usando o id", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Parque.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping("/{id}")
    public ResponseEntity obterParquePorId(@PathVariable int id) {
        try {
            Parque parque = parqueService.obterParquePorId(id);
            return ResponseEntity.ok(parque);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }

    @Operation(summary = "Lista os parques", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Parque.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping
    public ResponseEntity obterParques() {
        try {
            List<Parque> parques = parqueService.obterParques();
            return ResponseEntity.ok(parques);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }

    @Operation(summary = "Altera um Parque na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Parque.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PutMapping("/{id}")
    public ResponseEntity alterarParque(@PathVariable("id") int id, @RequestBody Parque parque) {
        try {
            Parque parqueAlterado = parqueService.alterarParque(id, parque);
            return ResponseEntity.ok(parqueAlterado);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }

    @Operation(summary = "Deleta um Parque na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Parque.class))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @DeleteMapping("/{id}")
    public ResponseEntity deletarParque(@PathVariable("id") int id) {
        try {
            parqueService.deletarParque(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Ocorreu um erro ao realizar a integração entre os serviços. Verifique o corpo da sua requisição");
        }
    }
}
