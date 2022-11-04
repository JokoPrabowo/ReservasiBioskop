package org.binar.SpringJPA.services.impl;

import org.binar.SpringJPA.dto.FirebaseMessage;
import org.binar.SpringJPA.services.FirebaseService;
import org.springframework.stereotype.Service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FirebaseServiceImpl implements FirebaseService{
    private final FirebaseMessaging firebaseMessaging;

    public FirebaseServiceImpl(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }
    
    public String sendNotification(FirebaseMessage data) throws FirebaseMessagingException{
        try{
            Notification notification = Notification
                .builder()
                .setTitle(data.getSubject())
                .setBody(data.getContent())
                .build();

            Message message = Message
                    .builder()
                    .setNotification(notification)
                    .setTopic(data.getSubject())
                    .build();

            return firebaseMessaging.send(message);
        }catch(Exception e){
            log.error("Error detected {}", e);
            return null;
        }
    }
}
