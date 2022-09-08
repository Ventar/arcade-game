#include <Arduino.h>
#include <neopixel.h>
#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <wifi_pwd.h>
#include <ESP8266mDNS.h>
#include <WiFiUdp.h>



/**
 * The UDP server to receive messages from the neoboard game server
 */
WiFiUDP Udp;
const char *NB_MDNS_SERVICE = "arcade";
const uint16_t NB_UDP_PORT = 4000;
uint8_t incomingPacket[512];


/**
 * @brief The address of the game server
 *
 */
IPAddress serverAddress;

/**
 * @brief The port of the game server.
 *
 */
int serverPort;

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
        int len = Udp.read(incomingPacket, 512);

        if (len > 0)
        {
            incomingPacket[len] = '\0';
        }

        switch (incomingPacket[0])
        {
        case 0:
        {
            Serial.println("command: CLEAR LED STRIP");
            strip.clear();
            strip.show();
            sleepMS = 500;
            break;
        }
        case 1:
        {
            Serial.printf("command: SET PIXEL COLOR\n");

            int recordSize = 5;
            for (int i = 0; i < incomingPacket[1]; i++)
            {

                int column = incomingPacket[2 + (i * recordSize) + 0];
                int row = incomingPacket[2 + (i * recordSize) + 1];
                int red = incomingPacket[2 + (i * recordSize) + 2];
                int green = incomingPacket[2 + (i * recordSize) + 3];
                int blue = incomingPacket[2 + (i * recordSize) + 4];

                setColor(column, row, red, green, blue);
            }

            strip.show();
            sleepMS = 0;
            break;
        }
          case 2:
        {
            Serial.println("command: Render Logo");
            renderArcade();

            strip.show();
            sleepMS = 500;
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

    String name = WiFi.macAddress();
    name.replace(":", "");

    if (!MDNS.begin(name))
    {
        Serial.println("Error setting up MDNS responder!");
    }

    Serial.print("\nSetup MDNS module name ::= [" + name + "], service ::= [" + NB_MDNS_SERVICE + "]");

    MDNS.addService(NB_MDNS_SERVICE, "udp", NB_UDP_PORT);

    // Setup the UDP server
    // ----------------------------------------------------------------------------
    Udp.begin(NB_UDP_PORT);
    Serial.printf("\nSetup UDP server on port ::= [%d]\n", NB_UDP_PORT);
}


void connectToWifi()
{

  Serial.print("Connecting to WiFi");

  WiFi.begin(ssid, password);

  int x = 0;

  while (WiFi.status() != WL_CONNECTED)
  {
    delay(500);
    Serial.print(".");
    renderWifiSymbol(x++);
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  strip.clear();
  strip.show();
}