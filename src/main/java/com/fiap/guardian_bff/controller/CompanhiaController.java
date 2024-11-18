package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.dto.companhia.AlterarCompanhiaDTO;
import com.fiap.guardian_bff.dto.companhia.CadastroCompanhiaDTO;
import com.fiap.guardian_bff.dto.util.ErrorMessageDTO;
import com.fiap.guardian_bff.exceptions.BusinessException;
import com.fiap.guardian_bff.model.Companhia;
import com.fiap.guardian_bff.service.CompanhiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Companhia", description = "API para realizar CRUD das companhias do sistema")
@Controller
@RequestMapping("/companhia")
public class CompanhiaController {

    @Autowired
    private CompanhiaService companhiaService;

    @Operation(summary = "Cadastra uma companhia no sistema", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Companhia.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar companhia",
                    content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))})
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarCompanhia(@RequestBody @Valid CadastroCompanhiaDTO cadastroCompanhiaDTO) {
        Companhia companhia = companhiaService.cadastrar(cadastroCompanhiaDTO);
        return ResponseEntity.status(201).body(companhia);
    }

    @Operation(summary = "Lista os companhias do sistema", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Companhia.class))))})
    @GetMapping
    public ResponseEntity<List<Companhia>> listarCompanhias() {
        return ResponseEntity.ok(companhiaService.listarTodos());
    }

    @Operation(summary = "Busca uma companhia pelo código", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Companhia.class))),
            @ApiResponse(responseCode = "404", description = "Companhia não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))})
    @GetMapping("/{codigo}")
    public ResponseEntity buscarPorCodigo(@PathVariable Long codigo) {
        try {
            Companhia companhia = companhiaService.obterPorCodigo(codigo);
            return ResponseEntity.ok(companhia);
        } catch (BusinessException e) {
            return ResponseEntity.status(404).body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Altera uma companhia na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Companhia.class))),
            @ApiResponse(responseCode = "400", description = "Requisição incorreta",
                    content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class))),
            @ApiResponse(responseCode = "404", description = "Companhia não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))})
    @PutMapping("/{codigo}")
    public ResponseEntity alterarCompanhia(@PathVariable Long codigo, @RequestBody AlterarCompanhiaDTO alterarCompanhiaDTO){
        try {
            Companhia companhia = companhiaService.alterarCompanhia(codigo, alterarCompanhiaDTO);
            return ResponseEntity.ok(companhia);
        } catch (BusinessException e) {
            return ResponseEntity.status(404).body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Altera uma companhia na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Companhia.class))),
            @ApiResponse(responseCode = "404", description = "Companhia não encontrada",
                    content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))})
    @DeleteMapping("/{codigo}")
    public ResponseEntity deletarCompanhia(@PathVariable Long codigo){
        companhiaService.deletarCompanhia(codigo);
        return ResponseEntity.noContent().build();
    }


}
