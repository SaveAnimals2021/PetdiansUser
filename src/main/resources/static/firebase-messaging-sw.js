importScripts("https://www.gstatic.com/firebasejs/8.4.1/firebase-app.js")
importScripts("https://www.gstatic.com/firebasejs/8.4.1/firebase-messaging.js")

let firebaseConfig = {
    apiKey: "AIzaSyCkVvqvnv-QxCKcL1CYRT1wT_dsg2epioE",
    authDomain: "focused-elysium-308503.firebaseapp.com",
    databaseURL: "https://focused-elysium-308503-default-rtdb.firebaseio.com",
    projectId: "focused-elysium-308503",
    storageBucket: "focused-elysium-308503.appspot.com",
    messagingSenderId: "907968763197",
    appId: "1:907968763197:web:d1fcf378adf80192dda154",
    measurementId: "G-ZM6QQTCPG1"
};
// Initialize Firebase
firebase.initializeApp(firebaseConfig);

const messaging = firebase.messaging();
// messaging.setBackgroundMessageHandler(function(payload){
//
//     const title = "Hello World";
//     const options = {
//         body: payload.data.status
//     };
//
//     return self.registration.showNotification(title,options);
// });

// messaging.setBackgroundMessageHandler(function(payload){
//
//     const title = "파이어베이스 테스트";
//     const options = {
//         body: payload.notification.body,
//         icon: payload.notification.icon
//     };
//
//     return self.registration.showNotification(title,options);
// });

messaging.onBackgroundMessage((payload) => {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    // Customize notification here
    const notificationTitle = 'Background Message Title';
    const notificationOptions = {
        body: 'Background Message body.',
        icon: '/firebase-logo.png'
    };

    self.registration.showNotification(notificationTitle,
        notificationOptions);
});


