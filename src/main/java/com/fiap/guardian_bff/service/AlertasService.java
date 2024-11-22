package com.fiap.guardian_bff.service;

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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlertasService {

    @Autowired
    private AlertasRepository alertasRepository;

    @Autowired
    private CompanhiaRepository companhiaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Alerta> listarAlertas(String email, int page, int size) {
        Companhia companhia = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Usuário não encontrado")).getCompanhia();
        Pageable pageable = PageRequest.of(page, size);
        return alertasRepository.findAllByCompanhia(companhia, pageable);
    }

    public Page<ListarAlertasDTO> listarAlertas(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Alerta> alertas = alertasRepository.findAll(pageable);

        return alertas.map(alerta -> new ListarAlertasDTO(
                alerta.getId(),
                alerta.getDataAlerta(),
                alerta.getCompanhia().getCodigo()));
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
