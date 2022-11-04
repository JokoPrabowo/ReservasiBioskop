package org.binar.SpringJPA.services;

import org.binar.SpringJPA.dto.FirebaseMessage;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface FirebaseService {
    public String sendNotification(FirebaseMessage data) throws FirebaseMessagingException;
}
