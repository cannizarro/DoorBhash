# DoorBhash


This is a personal project developed for getting familiar with the `WebRTC` implementation for android.


### Prerequisites

Minimum android SDK supported is 24 so anyone with Android 7.0 (Nougat) and above is fine and if you're not go see a doctor. Since you're downloading the apk from google drive, you must also give permission to `Install unknown apps from this source`. Here the source refers to the app used for downloading or opening the apk. You should also enable `Install from unknown sources` in your settings, if you haven't already soy boy.

## How to use

* You can create a room by entering the room name (which must be unique) in the edit text box and then clicking the create room button and then wait until someone joins the room you created. If someone joins your room they are connected to you through a video call.
* You can join a room by clicking join room button and then clicking on the name of the room you want to join and now you're seeing thrugh the front camera of the one that created the room.
* To hangup calls you can just press the hangup button, once you are into a call.

Please give any permission you are prompted. It will just ask to access your camera and audio, duh.

This app works with basically any network due to the robust TURN server provided by Xirsys which will help us dodge most of the NATs and firewalls, but still if you don't get any video feed even when you got `Recieved remote stream` toast, maybe there are chances that your network don't like P2P connections. So my app worked, your ISP is an asshole, hehehe.

## Built With

* [WebRTC](https://webrtc.org/native-code/android/) - Real time communication for web.
* [Firebase](https://firebase.google.com/) - Realtime database for signalling server.
* [Xirsys](https://xirsys.com/) - For STUN and TURN needs.

## Contributing

Feel free to contribute.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
