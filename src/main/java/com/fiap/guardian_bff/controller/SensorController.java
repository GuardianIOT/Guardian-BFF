package com.fiap.guardian_bff.controller;

import com.fiap.guardian_bff.dto.sensor.CadastroSensorDTO;
import com.fiap.guardian_bff.dto.util.ErrorMessageDTO;
import com.fiap.guardian_bff.model.SensorClima;
import com.fiap.guardian_bff.service.SensorService;
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

@Tag(name = "Sensor", description = "API para enviar e receber dados dos sensores climáticos")
@Controller
@RequestMapping("/sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @Operation(summary = "Cadastra os dadados do sensor na base de dados", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = SensorClima.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @PostMapping
    public ResponseEntity cadastraDadosSensor(@RequestBody CadastroSensorDTO cadastroSensorDTO){
        try {
            SensorClima sensorClimaCadastro = sensorService.cadastrarDadosSensor(cadastroSensorDTO);
            return ResponseEntity.status(201).body(sensorClimaCadastro);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ErrorMessageDTO("Ocorreu um erro ao cadastrar os dados do sensor"));
        }
    }

    @Operation(summary = "Cadastra os dadados do sensor na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = SensorClima.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request")})
    @GetMapping
    public ResponseEntity listarDadosSensor(){
        try {
            List<SensorClima> sensorClima = sensorService.listarSensorClimas();
            return ResponseEntity.status(200).body(sensorClima);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ErrorMessageDTO("Ocorreu um erro ao listar os dados do sensor"));
        }
    }

    @Operation(summary = "Busca um sensor por Id na base de dados", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso",
                    content = @Content(schema = @Schema(implementation = SensorClima.class))),
            @ApiResponse(responseCode = "404", description = "Bad Request")})
    @GetMapping("/{id}")
    public ResponseEntity buscarSensorPorId(@PathVariable("id") Long id){
        try {
            SensorClima sensorClima = sensorService.buscarPorId(id);
            return ResponseEntity.status(200).body(sensorClima);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new ErrorMessageDTO("Ocorreu um erro ao buscar os dados do sensor"));
        }
    }


}
