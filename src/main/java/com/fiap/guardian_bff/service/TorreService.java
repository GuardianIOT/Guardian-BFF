package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.integration.TorreIntegration;
import com.fiap.guardian_bff.model.Torre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TorreService {

    @Autowired
    private TorreIntegration torreIntegration;

    public Torre criarTorre(Torre torre) {
        return torreIntegration.adicionarTorre(torre);
    }

    public Torre obterTorrePorId(int id) {
        return torreIntegration.obterTorrePorId(id);
    }

    public List<Torre> obterTorres() {
        return torreIntegration.obterTorres();
    }

    public Torre alterarTorre(int id, Torre torre) {
        return torreIntegration.alterarTorre(id, torre);
    }

    public void deletarTorre(int id) {
        torreIntegration.deletarTorre(id);
    }
}
