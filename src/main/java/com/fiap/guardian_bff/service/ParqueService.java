package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.integration.ParqueIntegration;
import com.fiap.guardian_bff.model.Parque;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParqueService {

    @Autowired
    private ParqueIntegration parqueIntegration;

    public Parque criarParque(Parque parque) {
        return parqueIntegration.adicionarParque(parque);
    }

    public Parque obterParquePorId(int id) {
        return parqueIntegration.obterParquePorId(id);
    }

    public List<Parque> obterParques() {
        return parqueIntegration.obterParques();
    }

    public Parque alterarParque(int id, Parque parque) {
        return parqueIntegration.alterarParque(id, parque);
    }

    public void deletarParque(int id) {
        parqueIntegration.deletarParque(id);
    }
}
