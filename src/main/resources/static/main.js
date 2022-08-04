//
'use strict';
function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail()); // This is null if the 'email' scope is not present.
  }

  const webcamElement = document.getElementById('webcam');
  const canvasElement = document.getElementById('canvas');
  const snapSoundElement = document.getElementById('snapSound');
  const webcam = new Webcam(webcamElement, 'user', canvasElement, snapSoundElement);

  webcam.start()
  .then(result =>{
     console.log("webcam started");
  })
  .catch(err => {
      console.log(err);
  });

  navigator.mediaDevices.enumerateDevices()
  .then(getVideoInputs)
  .catch(errorCallback);

function getVideoInputs(mediaDevices){
      mediaDevices.forEach(mediaDevice => {
        if (mediaDevice.kind === 'videoinput') {
          this._webcamList.push(mediaDevice);
        }
      });
}

navigator.mediaDevices.getUserMedia(this.getMediaConstraints())
  .then(stream => {
      this._webcamElement.srcObject = stream;
      this._webcamElement.play();
  })
  .catch(error => {
     //...
  });

  if(this._facingMode == 'user'){
    this._webcamElement.style.transform = "scale(-1,1)";
}

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faGoogle} from '@fortawesome/free-brands-svg-icons';
import './App.scss';
import { useState, useEffect } from 'react';
import axios from 'axios';

//0529

function App() {
  const [ data, setData ] = useState(null);
  const google = <FontAwesomeIcon icon={faGoogle} size="10x"/>;
  const oAuthURL =
  `http://localhost:8080/`
  const oAuthHandler = () => {
    window.location.assign(oAuthURL);
  }

  useEffect( async () => {
    const url = new URL(window.location.href);
    const hash = url.hash;
    if (hash) {
      const accessToken = hash.split("=")[1].split("&")[0];
      await axios.get('https://www.googleapis.com/oauth2/v2/userinfo?access_token=' + accessToken, {
        headers: {
          authorization: `token ${accessToken}`,
          accept: 'application/json'
        }})
        .then(data => {
          console.log(data);
          setData(data);
      }).catch(e => console.log('oAuth token expired'));
    }
  }, [])

  return (
    <div>
      <button id="oAuthBtn" onClick={oAuthHandler}>
        {google}
        <div id="comment">구글 OAuth</div>
      </button>
    </div>
  );
}

export default App;