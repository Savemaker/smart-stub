package ru.liga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import ru.liga.service.ResponseUpdaterService;

@Component
public class QueueListener {

    private final ResponseUpdaterService responseUpdaterService;

    @Autowired
    public QueueListener(ResponseUpdaterService responseUpdaterService) {
        this.responseUpdaterService = responseUpdaterService;
    }


    @JmsListener(destination = "${QUEUE_IN}")
    public void getMessageFromQueue(String message) {
        System.out.println(responseUpdaterService.getUpdatedResponse(message));
    }
}
