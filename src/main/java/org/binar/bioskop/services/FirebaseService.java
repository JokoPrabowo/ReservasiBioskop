package org.binar.bioskop.services;

import org.binar.bioskop.dto.FirebaseMessage;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface FirebaseService {
    public String sendNotification(FirebaseMessage data) throws FirebaseMessagingException;
}
