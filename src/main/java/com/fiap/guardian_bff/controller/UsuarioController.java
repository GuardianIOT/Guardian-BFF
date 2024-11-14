package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.dto.usuario.CadastroUsuarioDTO;
import com.fiap.guardian_bff.dto.util.ErrorMessageDTO;
import com.fiap.guardian_bff.exceptions.BusinessException;
import com.fiap.guardian_bff.model.Usuario;
import com.fiap.guardian_bff.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "Usuario", description = "API para realizar CRUD dos usuários do sistema")
@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Cadastra um usuário no sistema", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400", description = "Erro ao cadastrar usuário",
                    content = @Content(schema = @Schema(implementation = ErrorMessageDTO.class)))})
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody CadastroUsuarioDTO cadastroUsuarioDTO){
        try {
            Usuario usuario = usuarioService.cadastrar(cadastroUsuarioDTO);
            return ResponseEntity.status(201).body(usuario);
        } catch (BusinessException e) {
            return ResponseEntity.badRequest().body(new ErrorMessageDTO(e.getMessage()));
        }
    }

    @Operation(summary = "Lista os usuários do sistema", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Usuario.class))))})
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios(){
        return ResponseEntity.ok(usuarioService.listarTodos());
    }
}
