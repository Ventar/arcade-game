#ifndef ARCADE_UDP_CONNECTION_H
#define ARCADE_UDP_CONNECTION_H

#include <wifi/wifi_setup.h>
#include <WiFiUdp.h>

/**
 * The UDP server to receive messages from the neoboard game server
 */
WiFiUDP Udp;

/**
 * The name of the MDNS service
 */
const char *NB_MDNS_SERVICE = "arcade";
/**
 * The UDP port to start the UDP server on
 */
const uint16_t NB_UDP_PORT = 5000;

const uint16_t PACKET_SIZE = 4096;

/**
 * The size of incoming UDP packets
 */
uint8_t incomingPacket[PACKET_SIZE];

/**
 * Sleep while no game is in progress
 */
int sleepMS = 500;

void handleUDP()
{

    int packetSize = Udp.parsePacket();

    if (packetSize)
    {
        Serial.printf("Received %d bytes from %s, port %d\n", packetSize, Udp.remoteIP().toString().c_str(), Udp.remotePort());
        int len = Udp.read(incomingPacket, PACKET_SIZE);

        if (len > 0)
        {
            incomingPacket[len] = '\0';
        }

        switch (incomingPacket[0])
        {
        case 0:
        {
            Serial.println("command: CLEAR LED STRIP");
            clearAndShowNeoPixel();
            sleepMS = 500;
            break;
        }
        case 1:
        {
            Serial.printf("command: SET PIXEL COLOR\n");

            int recordSize = 5;
            for (int i = 0; i < (incomingPacket[1] << 8) + (incomingPacket[2]); i++)
            {

                int column = incomingPacket[3 + (i * recordSize) + 0];
                int row = incomingPacket[3 + (i * recordSize) + 1];
                int red = incomingPacket[3 + (i * recordSize) + 2];
                int green = incomingPacket[3 + (i * recordSize) + 3];
                int blue = incomingPacket[3 + (i * recordSize) + 4];

                setColor(column, row, red, green, blue);
            }

            showNeoPixel();
            sleepMS = 0;
            break;
        }
        case 2:
        {
            Serial.println("command: Render Logo");
            renderArcade();

            showNeoPixel();
            sleepMS = 500;
            break;
        }
        case 3:
        {
            Serial.println("command: Reset WIFI");
            wifiManager.resetSettings();
            sleepMS = 500;
            break;
        }
        case 4:
        {
            Serial.println("command: Clear And Set");
            clearNeoPixel();

            int recordSize = 5;
            for (int i = 0; i < (incomingPacket[1] << 8) + (incomingPacket[2]); i++)
            {

                int column = incomingPacket[3 + (i * recordSize) + 0];
                int row = incomingPacket[3 + (i * recordSize) + 1];
                int red = incomingPacket[3 + (i * recordSize) + 2];
                int green = incomingPacket[3 + (i * recordSize) + 3];
                int blue = incomingPacket[3 + (i * recordSize) + 4];

                setColor(column, row, red, green, blue);
            }

            showNeoPixel();


            sleepMS = 0;
            break;
        }
        }
    }
}

/**
 * Setup the WiFi Module for the communication
 */
void setupUDP()
{

    if (!MDNS.begin(NB_MDNS_SERVICE))
    {
        Serial.println("Error setting up MDNS responder!");
    }

    Serial.print("\nSetup MDNS module name ::= [" + String(NB_MDNS_SERVICE) + "], service ::= [" + NB_MDNS_SERVICE + "]");

    MDNS.addService(NB_MDNS_SERVICE, "udp", NB_UDP_PORT);

    Udp.begin(NB_UDP_PORT);
    Serial.printf("\nSetup UDP server on port ::= [%d]\n", NB_UDP_PORT);
}

#endif