package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.dto.oauth.RealizarLoginDTO;
import com.fiap.guardian_bff.dto.oauth.TokenDTO;
import com.fiap.guardian_bff.dto.util.ErrorMessageDTO;
import com.fiap.guardian_bff.exceptions.AuthenticationException;
import com.fiap.guardian_bff.model.Usuario;
import com.fiap.guardian_bff.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Login", description = "API para realizar a autenticação do usuário no sistema")
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Operation(summary = "Valida as informações e retorna um JWT autenticado", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = TokenDTO.class))),
            @ApiResponse(responseCode = "403", description = "Erro ao realizar login",
                    content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))})
    @PostMapping
    public ResponseEntity logar(@RequestBody RealizarLoginDTO realizarLoginDTO){
        try {
            TokenDTO token = loginService.realizarLogin(realizarLoginDTO);
            return ResponseEntity.ok(token);
        }catch (AuthenticationException e){
            System.err.println("Ocorreu um erro ao realizar o login: " + e.getMessage());
            return ResponseEntity.status(403).body(new ErrorMessageDTO(e.getMessage()));
        }
    }
}
