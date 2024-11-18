package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.dto.companhia.AlterarCompanhiaDTO;
import com.fiap.guardian_bff.dto.companhia.CadastroCompanhiaDTO;
import com.fiap.guardian_bff.exceptions.BusinessException;
import com.fiap.guardian_bff.model.Companhia;
import com.fiap.guardian_bff.repository.CompanhiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanhiaService {

    @Autowired
    private CompanhiaRepository companhiaRepository;

    public List<Companhia> listarTodos(){
        return companhiaRepository.findAll();
    }

    public Companhia cadastrar(CadastroCompanhiaDTO cadastroCompanhiaDTO){
        Companhia companhia = new Companhia(cadastroCompanhiaDTO);
        return companhiaRepository.save(companhia);
    }

    public Companhia obterPorCodigo(Long codigoCompanhia) throws BusinessException {
        Optional<Companhia> companhia = companhiaRepository.findById(codigoCompanhia);

        if(!companhia.isPresent()){
            throw new BusinessException("Companhia não encontrada");
        }

        return companhia.get();
    }

    public Companhia alterarCompanhia(Long codigoCompanhia, AlterarCompanhiaDTO alterarCompanhiaDTO) throws BusinessException {
        Optional<Companhia> companhia = companhiaRepository.findById(codigoCompanhia);

        if(!companhia.isPresent()){
            throw new BusinessException("Companhia não encontrada");
        }

        companhia.get().alterar(alterarCompanhiaDTO);
        return companhiaRepository.save(companhia.get());
    }

    public void deletarCompanhia(Long codigoCompanhia){
        companhiaRepository.deleteById(codigoCompanhia);
    }
}
