#include <Arduino.h>
#include <webserver.h>



void setup()
{
  setupNeopixel();

  Serial.begin(115200);
  delay(500);

  Serial.println("\n\nApprentice Arcade Game 1.0");

  connectToWifi();

  Serial.println("\n\nStarted Arcade Game...");
}

void loop()
{
  server.handleClient();          // Handling of incoming requests
  delay(250);
}