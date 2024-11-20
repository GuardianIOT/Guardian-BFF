package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.dto.util.ErrorMessageDTO;
import com.fiap.guardian_bff.model.Alerta;
import com.fiap.guardian_bff.service.AlertasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Tag(name = "Alertas", description = "API para realizar operações relacionadas as alertas geradas pelos sensores")
@Controller
@RequestMapping("/alertas")
public class AlertasController {

    @Autowired
    private AlertasService alertasService;

    @Operation(summary = "Lista o histórico de alertas associados a companhia do usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alerta.class)))),
            @ApiResponse(responseCode = "204", description = "No Content")})
    @GetMapping
    public ResponseEntity listarAlertas(@AuthenticationPrincipal String emailUsuario) {
        try {
            return ResponseEntity.ok(alertasService.listarAlertas(emailUsuario));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorMessageDTO("Ocorreu um erro inesperado"));
        }
    }

    @Operation(summary = "Lista todo o histórico de alerta", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alerta.class)))),
            @ApiResponse(responseCode = "204", description = "No Content")})
    @GetMapping("listar-todos")
    public ResponseEntity listarTodasAlertas() {
        return ResponseEntity.ok(alertasService.listarAlertas());
    }
}
