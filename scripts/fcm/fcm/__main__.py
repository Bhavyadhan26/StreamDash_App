import random
import firebase_admin
from firebase_admin import credentials
from firebase_admin import messaging
from firebase_admin import firestore


sample_data = (
    "Temperature",
    "Humidity",
    "Ultrasonic",
    "Hydrogen",
    "Motion",
    "Ambient Light",
    "Hall Effect",
    "Color",
    "Vibration",
    "Tilt",
    "Heat",
    "Pressure",
    "Touch",
    "Heartbeat",
    "Moisture",
    "Smoke",
)

cred = credentials.Certificate("./service_account.json")
app = firebase_admin.initialize_app(cred)

username = "test"
store = firestore.client(app)
doc = store.collection("users").document(username).get()
token = doc.to_dict().get("deviceToken")
# See documentation on defining a message payload.

for sensor in sample_data:
    value = random.randint(0, 1024)
    message = messaging.Message(
        data={
            "sensor": sensor,
            "value": str(value),
        },
        # topic="sensor",
        token=token,
    )

    # Send a message to the device corresponding to the provided
    # registration token.
    response = messaging.send(message)
    # Response is a message ID string.
    print("Successfully sent message:", response)
