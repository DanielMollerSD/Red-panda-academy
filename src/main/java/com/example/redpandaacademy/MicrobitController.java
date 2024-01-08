package com.example.redpandaacademy;
import com.fazecast.jSerialComm.SerialPort;

public class MicrobitController {

    public static void main(String[] args) {
        // Find available ports and select the appropriate port for the micro:bit
        SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort microBitPort = null;
        for (SerialPort port : ports) {
            System.out.println(port.getDescriptivePortName());
            if (port.getDescriptivePortName().contains("COM")) {
                microBitPort = port;
                break;
            }
        }

        if (microBitPort != null) {
            // Open the selected port
            if (microBitPort.openPort()) {
                microBitPort.setBaudRate(115200);

                // Read data from the serial port
                while (true) {
                    byte[] buffer = new byte[1024];
                    int numRead = microBitPort.readBytes(buffer, buffer.length);

                    // Process the received data
                    if (numRead > 0) {
                        String buttonData = new String(buffer, 0, numRead).trim();
                        processButtonPress(buttonData);
                    }
                }
            } else {
                System.out.println("Failed to open the serial port.");
            }
        } else {
            System.out.println("Micro:bit not found.");
        }
    }

    private static void processButtonPress(String data) {
        if (data.equals("A")) {
            System.out.println("Button A pressed");
        } else if (data.equals("B")) {
            System.out.println("Button B pressed");
        }
    }
}