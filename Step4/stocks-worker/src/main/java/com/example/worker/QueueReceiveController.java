package com.example.worker;


import com.example.worker.model.StocksData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class QueueReceiveController {

    private static final String QUEUE_NAME = "anjnaq";

    private final Logger logger = LoggerFactory.getLogger(QueueReceiveController.class);
    @Autowired
    private StocksDataRepository stocksDataRepository;
    @JmsListener(destination = QUEUE_NAME, containerFactory = "jmsListenerContainerFactory")
    public void receiveMessage(String stock) {
        logger.info("Received message: {}", stock);
        List<StocksData> stocks = stocksDataRepository.findAll();
        StocksData max = Collections.max(stocks);
        System.out.println(max.getSymbol()+"  "+max.getLatestPrice()+"\n");
    }

}
