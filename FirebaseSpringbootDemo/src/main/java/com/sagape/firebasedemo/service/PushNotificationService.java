package com.sagape.firebasedemo.service;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import com.sagape.firebasedemo.model.PushNotificationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class PushNotificationService {

    // @Value("#{${app.notifications.defaults}}")
    //private Map<String, String> defaults;

    private Logger logger = LoggerFactory.getLogger(PushNotificationService.class);
    private FCMService fcmService;

    public PushNotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public Optional<String> sendNotificationToToken(String token, String title, String body) throws FirebaseMessagingException {
        Notification notification = Notification.builder().setTitle(title).setBody(body).build();
        Message message = Message.builder()
                .setNotification(notification)
                .setToken(token)
                .build();
        String response = FirebaseMessaging.getInstance().send(message);
        logger.info("fcm message sent. response: {}", response);
        return Optional.of(response);
    }

    public void sendPushNotification(PushNotificationRequest request) {
        try {
            fcmService.sendMessage(getSamplePayloadData(), request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sendPushNotificationCustomDataWithTopic(PushNotificationRequest request) throws ExecutionException, InterruptedException {
        fcmService.sendMessageCustomDataWithTopic(getSamplePayloadDataCustom(), request);
    }

    public void sendPushNotificationCustomDataWithTopicWithSpecificJson(PushNotificationRequest request) throws ExecutionException, InterruptedException {
        fcmService.sendMessageCustomDataWithTopic(getSamplePayloadDataWithSpecificJsonFormat(), request);
    }

    public void sendPushNotificationWithoutData(PushNotificationRequest request) throws ExecutionException, InterruptedException {
        fcmService.sendMessageWithoutData(request);
    }


    public void sendPushNotificationToToken(PushNotificationRequest request) {
        try {
            fcmService.sendMessageToToken(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    private Map<String, String> getSamplePayloadData() {
        Map<String, String> pushData = new HashMap<>();
        Map<String, String> data = new HashMap<>();
        Map<String, String> payload = new HashMap<>();
        Map<String, String> article_data = new HashMap<>();

        pushData.put("title", "Notification for pending work");
        pushData.put("message", "pls complete your pending task immediately");
        pushData.put("image", "https://raw.githubusercontent.com/Firoz-Hasan/SpringBootPushNotification/master/pushnotificationconcept.png");
        pushData.put("timestamp", "2020-07-11 19:23:21");
        pushData.put("article_data", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        return pushData;
    }


    private Map<String, String> getSamplePayloadDataCustom() {
        Map<String, String> pushData = new HashMap<>();
        pushData.put("title", "Notification for pending work-custom");
        pushData.put("message", "pls complete your pending task immediately-custom");
        pushData.put("image", "https://raw.githubusercontent.com/Firoz-Hasan/SpringBootPushNotification/master/pushnotificationconcept.png");
        pushData.put("timestamp", String.valueOf(new Date()));
        pushData.put("article_data", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.");
        return pushData;
    }


    private Map<String, String> getSamplePayloadDataWithSpecificJsonFormat() {
        Map<String, String> pushData = new HashMap<>();
        Map<String, String> data = new HashMap<>();
       ArrayList<Map<String, String>> payload = new ArrayList<>();
        Map<String, String> article_data = new HashMap<>();

        pushData.put("title", "jsonformat");
        pushData.put("message", "itsworkingkudussssssssssssssssssssssssssssssssssss");
        pushData.put("image", "qqq");
        pushData.put("timestamp", "fefe");
        article_data.put("article_data", "ffff");
        payload.add(article_data);
        pushData.put("payload", String.valueOf(payload));
        data.put("data", String.valueOf(pushData));
        return data;
    }


}