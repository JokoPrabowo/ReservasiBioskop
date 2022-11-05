package org.binar.bioskop.controllers;

import org.binar.bioskop.dto.FirebaseMessage;
import org.binar.bioskop.services.impl.FirebaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private FirebaseServiceImpl firebaseServiceImpl;

    @PostMapping("/send-notification")
    public String sendNotification(@RequestBody FirebaseMessage data) throws FirebaseMessagingException {
        try{
            log.info("Sending notification");
            return firebaseServiceImpl.sendNotification(data);
        }catch(Exception e){
            log.error("Error detected {}", e.getMessage());
            return null;
        }
    }
}
