package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.exceptions.BusinessException;
import com.fiap.guardian_bff.model.SensorClima;
import com.fiap.guardian_bff.repository.SensorRepository;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OllamaService {

    @Autowired
    private ChatModel chatModel;

    @Autowired
    private SensorRepository sensorRepository;

    public String analisarClima(Long idDados) throws BusinessException {
        Optional<SensorClima> dadosSensor = sensorRepository.findById(idDados);

        if(!dadosSensor.isPresent()){
            throw new BusinessException("O id passado não corresponde a nenhum registro");
        }

        String prompt = "Leia o report de clima e extraia insights sobre o clima em um breve resumo: \n " + dadosSensor.get();

        ChatResponse response = chatModel.call(
                new Prompt(
                        prompt,
                        OllamaOptions.builder()
                                .withModel(OllamaModel.LLAMA3_2)
                                .withTemperature(0.5)
                                .build()
                )
        );

        return response.getResult().getOutput().getContent();
    }
}
