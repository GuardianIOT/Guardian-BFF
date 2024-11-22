package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.dto.alertas.ListarAlertasDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Tag(name = "Alertas", description = "API para realizar operações relacionadas as alertas geradas pelos sensores")
@Controller
@RequestMapping("/alertas")
public class AlertasController {

    @Autowired
    private AlertasService alertasService;

    @Operation(summary = "Lista o histórico de alertas associados à companhia do usuário", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alerta.class)))),
            @ApiResponse(responseCode = "204", description = "No Content")})
    @GetMapping
    public ResponseEntity listarAlertas(@AuthenticationPrincipal String emailUsuario,
                                        @RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        try {
            Page<Alerta> alertasPaginados = alertasService.listarAlertas(emailUsuario, page, size);
            if (alertasPaginados.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(alertasPaginados);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(new ErrorMessageDTO("Ocorreu um erro inesperado"));
        }
    }

    @Operation(summary = "Lista todo o histórico de alertas", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Alerta.class)))),
            @ApiResponse(responseCode = "204", description = "No Content")})
    @GetMapping("listar-todos")
    public ResponseEntity listarTodasAlertas(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size) {
        Page<ListarAlertasDTO> alertasPaginados = alertasService.listarAlertas(page, size);
        if (alertasPaginados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(alertasPaginados);
    }

}
