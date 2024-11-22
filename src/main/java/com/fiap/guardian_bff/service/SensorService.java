package com.fiap.guardian_bff.service;

import com.fiap.guardian_bff.dto.sensor.CadastroSensorDTO;
import com.fiap.guardian_bff.model.SensorClima;
import com.fiap.guardian_bff.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;


    public SensorClima cadastrarDadosSensor(CadastroSensorDTO cadastroSensorDTO) {
        SensorClima sensorClima = new SensorClima(cadastroSensorDTO);
        SensorClima save = sensorRepository.save(sensorClima);
        return save;
    }

    public List<SensorClima> listarSensorClimas(){
        return sensorRepository.findAll();
    }

    public SensorClima buscarPorId(Long id){
        return sensorRepository.findById(id).get();
    }
}
