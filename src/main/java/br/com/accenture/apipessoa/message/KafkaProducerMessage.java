package br.com.accenture.apipessoa.message;

import br.com.accenture.apipessoa.domain.dto.PessoaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerMessage {

    @Autowired
    private KafkaTemplate<String, PessoaDTO> kafkaTemplate;

    private final String KAFKA_TOPIC = "pessoa-post-topic";

    public void sendMessage(PessoaDTO pessoaDTO){
        kafkaTemplate.send(KAFKA_TOPIC, pessoaDTO);
    }

}