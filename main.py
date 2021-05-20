import cv2
from flask import Flask
from flask.wrappers import Response


app = Flask(__name__)

path = open("path.txt").readline().rstrip()

vid = cv2.VideoCapture(path)

@app.route('/')
def index():
    success, img = vid.read()
    
    output = cv2.resize(img, (128, 128))
    
    ret, buf = cv2.imencode(".jpg", output)
    bytes_im = buf.tobytes()
    return Response(bytes_im, 200, mimetype="image/jpg")