#include <Arduino.h>
#include <Adafruit_NeoPixel.h>

Adafruit_NeoPixel strip(144, D5, NEO_GRB + NEO_KHZ800);

void setup()
{
  Serial.begin(115200);

  delay(500);

  // Initialize the NeoPixels and fill the field matrix
  strip.begin();
  strip.setBrightness(64);
  strip.clear();
  strip.show();

  Serial.println("\n\nStarted Arcade Game...");
}

void setColor(int column, int row, int red, int green, int blue)
{
  int pixel = row * 12 + column;
  strip.setPixelColor(pixel, red, green, blue);
}

void loop()
{

  for (int i = 0; i < 10; i++)
  {
    strip.clear();
    setColor(4, 11-i, 0, 255, 0);
    setColor(4, 10-i, 0, 255, 0);
    setColor(4, 9-i, 0, 255, 0);
    setColor(5, 9-i, 0, 255, 0);
    strip.show();
    delay(500);
  }

  /*
    for (size_t i = 0; i < 144; i++)
    {
      strip.clear();
      strip.setPixelColor(i, 255, 0, 0);
      delay(250);
      strip.show();
    }
    */

  delay(1000);

  Serial.println("\n\nOne Loop Finished...");
}