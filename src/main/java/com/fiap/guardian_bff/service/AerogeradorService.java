package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.integration.AerogeradorIntegration;
import com.fiap.guardian_bff.model.Aerogerador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AerogeradorService {

    @Autowired
    private AerogeradorIntegration aerogeradorIntegration;

    public Aerogerador criarAerogerador(Aerogerador aerogerador){
        return aerogeradorIntegration.adicionarAerogerador(aerogerador);
    }

    public Aerogerador obterAerogeradorPorId(int id){
        Aerogerador aerogerador = aerogeradorIntegration.obterAerogeradorPorId(id);
        return aerogerador;
    }

    public List<Aerogerador> obterAerogeradores(){
        return aerogeradorIntegration.obterAerogeradores();
    }

    public Aerogerador alterarAerogerador(int id, Aerogerador aerogerador){
        return aerogeradorIntegration.alterarAerogerador(id, aerogerador);
    }

    public void deletarAeroGerador(int id){
        aerogeradorIntegration.deletarAerogerador(id);
    }
}
