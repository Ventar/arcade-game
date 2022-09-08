#include <Arduino.h>
#include <neopixel.h>
#include <ESP8266WiFi.h>
#include <ESP8266WebServer.h>
#include <wifi_pwd.h>

/**
 * HTTP web server on port 80
 */
ESP8266WebServer server(80);

int arg(int pos)
{
  return server.arg(pos).toInt();
}

void handleLEDHTTP()
{

  String message = "Number of args received : ";
  message += server.args(); // Get number of parameters
  message += "\n";

  for (int i = 0; i < server.args(); i++)
  {
    message += "(" + (String)i + ") -> ";
    message += server.argName(i) + ": ";
    message += server.arg(i) + "\n";
  }

  switch (arg(0))
  {
  case 0:
    strip.clear();
    break;
  case 1:
    int ledsToSet = arg(1);
    for (int led = 0; led < ledsToSet; led++)
    {
      setColor(arg(2 + 5 * led), arg(3 + 5 * led), arg(4 + 5 * led), arg(5 + 5 * led), arg(6 + 5 * led));
    }

    break;
  }

  strip.show();

  server.send(200, "text / plain", message); // Response to the HTTP request
}
