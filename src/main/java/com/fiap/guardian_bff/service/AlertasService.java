package com.fiap.guardian_bff.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fiap.guardian_bff.dto.alertas.AlertaDTO;
import com.fiap.guardian_bff.dto.alertas.ListarAlertasDTO;
import com.fiap.guardian_bff.model.Alerta;
import com.fiap.guardian_bff.model.Companhia;
import com.fiap.guardian_bff.repository.AlertasRepository;
import com.fiap.guardian_bff.repository.CompanhiaRepository;
import com.fiap.guardian_bff.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertasService {

    @Autowired
    private AlertasRepository alertasRepository;

    @Autowired
    private CompanhiaRepository companhiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Alerta> listarAlertas(String email){
        Companhia companhia = usuarioRepository.findByEmail(email).get().getCompanhia();
        return alertasRepository.findAllByCompanhia(companhia);
    }

    public List<ListarAlertasDTO> listarAlertas(){
        List<Alerta> all = alertasRepository.findAll();
        ArrayList<ListarAlertasDTO> listarAlertasDTOS = new ArrayList<>();

        for(Alerta alerta : all){
            listarAlertasDTOS.add(new ListarAlertasDTO(alerta.getId(), alerta.getDataAlerta(), alerta.getCompanhia().getCodigo()));
        }

        return listarAlertasDTOS;
    }


    @Transactional
    @RabbitListener(queues = "alertas-guardian")
    public void cadastrarAlertas(@Payload AlertaDTO alertaDTO) {
        Optional<Companhia> optionalCompanhia = companhiaRepository.findById(alertaDTO.idCompanhia());

        if (!optionalCompanhia.isPresent()) {
            System.err.println("Companhia não encontrada para o ID: " + alertaDTO.idCompanhia());
            return;
        }

        Companhia companhia = optionalCompanhia.get();

        Alerta alerta = new Alerta(alertaDTO.dataAlerta(), companhia);
        alertasRepository.save(alerta);
        System.out.println(alerta);
    }


}
