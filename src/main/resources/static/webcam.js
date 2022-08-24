const webcamElement = document.getElementById('webcam');
var emotionList = {happy:0, sad:0, angry:0, neutral:0, surprised:0, disgusted:0, fearful:0}
var userdata;
var emotion1, emotion2;

function send(userId, emotionId1, emotionId2){
  $.ajax({
    type: 'post',
    url: '/date/'+userId+'/'+emotionId1+'/'+emotionId2,
    dataType: 'html',
    success: function(data) {
      alert("당신은 " + emotionId1 + "합니다!");
    },
    error: function(data){
      alert("실패");
    }
  })
}

$(document).ready(function(){
  $.ajax({
    url: "http://localhost:8080/user/current",
    data: "get",
    contentType: "application/json;charset=UTF-8",
    dataType: "json",
    success: function(data){
      userdata= data;
    },
    error: function (data){
      console.log("data load failed");
    }
  })
})


Promise.all([
  faceapi.nets.tinyFaceDetector.loadFromUri('/models'),
  faceapi.nets.faceLandmark68Net.loadFromUri('/models'),
  faceapi.nets.faceRecognitionNet.loadFromUri('/models'),
  faceapi.nets.faceExpressionNet.loadFromUri('/models')
]).then(startVideo)

function startVideo(){
    navigator.getUserMedia(
        { video: {} },
        stream => webcamElement.srcObject = stream,
        err => console.error(err)
      )
}

webcamElement.addEventListener('play', () => {
  const canvas = faceapi.createCanvasFromMedia(webcamElement)
  document.body.append(canvas)
  const displaySize = { width: webcamElement.width, height: webcamElement.height }
  faceapi.matchDimensions(canvas, displaySize)
  let timer=setInterval(async () => {
    const detections = await faceapi.detectAllFaces(webcamElement, new faceapi.TinyFaceDetectorOptions()).withFaceLandmarks().withFaceExpressions()
    console.log(detections[0].expressions)
    if (detections.length>0){
      for (name in detections[0].expressions){
        emotionList[name]+=detections[0].expressions[name];
      }
    }
  }, 100)
  setTimeout(()=>{
    clearInterval(timer);
    var sortable = [];
    for (var name in emotionList){
      sortable.push([name, emotionList[name]]);
    }
    sortable.sort(function(a,b){
      return b[1]-a[1];
    });
    emotion1=sortable[0][0];
    emotion2=sortable[1][0];
    send(userdata.userId, emotion1, emotion2);
  }, 10000);
})