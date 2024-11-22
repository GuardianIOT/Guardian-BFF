package com.fiap.guardian_bff.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.api.OllamaModel;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OllamaService {

    @Autowired
    private ChatModel chatModel;

    public String analisarClima() throws Exception {
        String prompt = "Leia o report de clima e extraia insights sobre o clima em um breve resumo: \n " +
                "[\n" +
                "    {\n" +
                "        \"id_sensor_clima\": 1,\n" +
                "        \"timezone\": \"America/Sao_Paulo\",\n" +
                "        \"data_hora\": \"2024-11-22T10:00:00\",\n" +
                "        \"temperatura\": 25.4,\n" +
                "        \"umidade\": 78.5,\n" +
                "        \"direcao_vento\": \"NW\",\n" +
                "        \"velocida_vento\": 12.3,\n" +
                "        \"descricao_clima\": \"Sunny\",\n" +
                "        \"id_torre\": 101\n" +
                "    },\n" +
                "    {\n" +
                "        \"id_sensor_clima\": 2,\n" +
                "        \"timezone\": \"America/Sao_Paulo\",\n" +
                "        \"data_hora\": \"2024-11-22T12:00:00\",\n" +
                "        \"temperatura\": 28.1,\n" +
                "        \"umidade\": 60.0,\n" +
                "        \"direcao_vento\": \"SE\",\n" +
                "        \"velocida_vento\": 15.7,\n" +
                "        \"descricao_clima\": \"Cloudy\",\n" +
                "        \"id_torre\": 102\n" +
                "   }\n" +
                "]";

        try {
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
        } catch (Exception e) {
            throw new Exception("Possa ser que o serviço de inteligência artificial esteja fora do ar." + e.getMessage());
        }
    }
}
