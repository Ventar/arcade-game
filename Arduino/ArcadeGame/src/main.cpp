#include <Arduino.h>
#include <udp_connection.h>

void setup()
{
  setupNeopixel();

  Serial.begin(115200);
  delay(500);

  Serial.println("\n\nApprentice Arcade Game 1.0");

  connectToWifi();
  setupUDP();

  Serial.println("\n\nStarted Arcade Game...");
  renderArcade();
}

void loop()
{
  MDNS.update();
  handleUDP();
  //server.handleClient(); // Handling of incoming requests

  if (sleepMS != 0)
    delay(sleepMS);

  /*
    for (int i = 0; i < 144 * 2; i++)
    {
      strip.clear();
      strip.setPixelColor(i, 128, 0, 0);
      strip.show();
      delay(100);
    }
    */
}