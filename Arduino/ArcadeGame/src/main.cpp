#include <udp_connection.h>

/**
 * If the device was started successfully / for the first time.
 */
boolean showLogo = true;

void setup()
{
  setupNeopixel();

  Serial.begin(115200);
  Serial.println("\n\nApprentice Arcade Game 1.0");

  connectToWifi();
  setupUDP();
  setupOTA();

  Serial.println("\n\nStarted Arcade Game...");
}

void loop()
{
 // wifiManager.process();
  //ArduinoOTA.handle();
  MDNS.update();

  if (WiFi.status() == WL_CONNECTED)
  {

    if (showLogo)
    {
      renderArcade();
      showLogo = false;
    }

    handleUDP();

    if (sleepMS != 0)
    {
      delay(sleepMS);
    }
  }
  else
  {
    // this code is only executed if the wifi Manager could not connet.
  //  delay(400);
  //  animateWifiSymbol(192, 16, 16);
  }
}