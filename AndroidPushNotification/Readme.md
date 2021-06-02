# Client Side (Android)

As you already created a firebase project and added firebase configuration file in
your spring boot application so now its time to add android app to get started.

1) Goto `Cloud Messaging` in `firebase console` and click on android icon to add new android app.

**NOTE**: If already has some app in the project you can go to `Project settings`(gear icon near the Project Overview menu item) and add new app in General tab.

In the `Android package name` put your android app package name from `src/main/AndroidManifest.xml` file in your android project.

![Add Firebase to your Android App](./androidfirebasecreate.png)

2) Then click on button Register App.

3) After that download `google-services.json` in `app` folder in your android project(`springboot-firebase-pushnotifications-demo/AndroidPushNotification/app`).

4) Run app

Easy way to run app is to open it in android studio and run directly form IDE.


## MyFirebaseMessagingService

By this service class this app will be registered as a
   firebase user and generate unique device/user/token id. So in later time if you want to
   send notification to a specific user you use this specific token to send it. During token
   registration this onNewToken() is being called and if you want to store this token to
   database, you can make an API call inside this method.
   When data is sent from server, onMessageReceived() method will be called,
   received the data and generates notification and perform further actions if required
   like opening a new activity etc.
   

In order to send push notification you will need a server side application: `FirebaseSpringbootDemo`


