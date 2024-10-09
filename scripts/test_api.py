import requests
import time

DELAY = 1

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

for sensor, value in sample_data:
    requests.post("http://localhost:8080", json={"sensor": sensor, "value": value})
    time.sleep(DELAY)
