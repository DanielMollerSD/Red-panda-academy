package com.example.redpandaacademy;

import com.fazecast.jSerialComm.SerialPort;

public class MicrobitController implements Runnable {
    private GameController gameController;

    public MicrobitController(GameController gameController) {
        this.gameController = gameController;
    }
    @Override
    public void run() {
        SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort microBitPort = null;

        for (SerialPort port : ports) {
            System.out.println(port.getDescriptivePortName());
            if (port.getDescriptivePortName().contains("COM5")) {
                microBitPort = port;
                break;
            }
        }

        if (microBitPort != null) {
            if (microBitPort.openPort()) {
                microBitPort.setBaudRate(115200);

                while (!Thread.currentThread().isInterrupted()) {
                    byte[] buffer = new byte[1024];
                    int numRead = microBitPort.readBytes(buffer, buffer.length);

                    if (numRead > 0) {
                        String eventData = new String(buffer, 0, numRead).trim();
                        processEvent(eventData);
                    }
                }
            } else {
                System.out.println("Failed to open the serial port.");
            }
        } else {
            System.out.println("Micro:bit not found.");
        }
    }

    private void processEvent(String data) {
        if (data != null && !data.trim().isEmpty()) {
            // Remove leading and trailing double-quote characters
            data = data.replaceAll("^\"|\"$", "");

            switch (data) {
                case "A":
                    gameController.onMicrobitButtonAClick(data);
                    break;
                case "B":
                    gameController.onMicrobitButtonBClick(data);
                    break;
                case "C":
                    gameController.onMicrobitButtonCClick(data);
                    break;
                case "D":
                    gameController.onMicrobitButtonDClick(data);
                    break;
                case "S":
                    gameController.onMicrobitButtonSClick(data);
                    break;
                default:
                    break;
            }
        }
    }
}
