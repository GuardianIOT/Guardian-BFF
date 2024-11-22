package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.integration.FalhaIntegration;
import com.fiap.guardian_bff.model.Falha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FalhaService {

    @Autowired
    private FalhaIntegration falhaIntegration;

    public Falha criarFalha(Falha falha) {
        return falhaIntegration.adicionarFalha(falha);
    }

    public Falha obterFalhaPorId(int id) {
        System.out.println(falhaIntegration.obterFalhaPorId(id));
        return falhaIntegration.obterFalhaPorId(id);
    }

    public List<Falha> obterFalhas() {
        System.out.println(falhaIntegration.obterFalhas());
        return falhaIntegration.obterFalhas();
    }

    public Falha alterarFalha(int id, Falha falha) {
        return falhaIntegration.alterarFalha(id, falha);
    }

    public void deletarFalha(int id) {
        falhaIntegration.deletarFalha(id);
    }
}
