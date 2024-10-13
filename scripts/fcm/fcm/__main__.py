import firebase_admin
from firebase_admin import credentials
from firebase_admin import messaging
from firebase_admin import firestore


sample_data = (
    ("Temperature", 10),
    ("Humidity", 20),
    ("Ultrasonic", 30),
    ("Hydrogen", 40),
    ("Motion", 50),
    ("Ambient Light", 60),
    ("Not Really A", 70),
    ("Chocolate", 80),
    ("Crystal Methamphetamine", 90),
    ("Shiny Pokemon", 100),
    ("Heat", 110),
    ("Supercalifragilisticexpialidocious", 120),
    ("Incorrect Spelling", 130),
    ("Gay", 140),
    ("Words That Are Over Five Hundred Characters", 501),
    ("Number of Minecraft Servers in Rust", 9999999999999),
)

cred = credentials.Certificate("./service_account.json")
app = firebase_admin.initialize_app(cred)

username = "test"
store = firestore.client(app)
doc = store.collection("users").document(username).get()
token = doc.to_dict().get("deviceToken")

# See documentation on defining a message payload.

for sensor, value in sample_data:
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
