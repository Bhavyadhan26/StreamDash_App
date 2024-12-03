# Streamdash

A **Remote Monitoring Platform** for real-time data from sensors via mobile devices.

## Features
- Supports multiple sensor types (e.g., gas, motion, moisture).
- Real-time data transmission via UDP.
- Easy setup with custom MicroPython firmware.

## Architecture
1. **Node:** Sends sensor data via UDP.
2. **Server:** Processes data and forwards it using Firebase Cloud Messaging.
3. **App:** Displays real-time sensor updates dynamically.

## Components
- **Software:** Mobile app, middleware server, MicroPython driver for nodes.
- **Hardware:** ESP8266 microcontrollers, Android devices.

## Installation
1. Clone this repository.
2. Upload the [MicroPython driver](https://github.com/zeffo/streamdash_node) to your microcontroller.
3. Set up the server and app as per the documentation.
