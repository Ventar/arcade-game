#include <Arduino.h>
#include <Adafruit_NeoPixel.h>

/**
 * Neopixel board.
 */
Adafruit_NeoPixel strip(144 * 4, D5, NEO_GRB + NEO_KHZ800);

/**
 * Sets the pixel of a single color to the passed values. While the pixels are addressed in
 * a row on the PCB the method adds an abstraction to set rows and columns. The index is zero based.
 */
void setColor(int column, int row, int red, int green, int blue)
{

  int boardsIndRow = 2;

  int pixelRow = (row / 12) * 144 * boardsIndRow; // the number of pixel we need to add for the rows of the boards below
  int pixelColumn = (column / 12) * 144;          // the number of pixel we need to add for the columns of the boards to the left

  int pixel = (row % 12) * 12 + (column % 12) + pixelColumn + pixelRow;
  strip.setPixelColor(pixel, red, green, blue);
}

void setupNeopixel()
{

  strip.begin();
  strip.setBrightness(128);
  delay(500); // short delay so that the clear can work.
  strip.clear();
  strip.show();
}

void renderWifiSymbol(int step)
{

  strip.clear();

  Serial.println(3 - (step % 4));

  switch (3 - (step % 4))
  {
  case 0:
    setColor(5, 14, 0, 32, 128);
    setColor(6, 15, 0, 32, 128);
    setColor(7, 16, 0, 32, 128);
    setColor(8, 16, 0, 32, 128);
    setColor(9, 17, 0, 32, 128);
    setColor(10, 18, 0, 32, 128);
    setColor(11, 18, 0, 32, 128);
    setColor(12, 18, 0, 32, 128);
    setColor(13, 18, 0, 32, 128);
    setColor(14, 17, 0, 32, 128);
    setColor(15, 16, 0, 32, 128);
    setColor(16, 16, 0, 32, 128);
    setColor(17, 15, 0, 32, 128);
    setColor(18, 14, 0, 32, 128);
  case 1:
    setColor(6, 11, 0, 32, 128);
    setColor(7, 12, 0, 32, 128);
    setColor(8, 13, 0, 32, 128);
    setColor(9, 13, 0, 32, 128);
    setColor(10, 14, 0, 32, 128);
    setColor(11, 14, 0, 32, 128);
    setColor(12, 14, 0, 32, 128);
    setColor(13, 14, 0, 32, 128);
    setColor(14, 13, 0, 32, 128);
    setColor(15, 13, 0, 32, 128);
    setColor(16, 12, 0, 32, 128);
    setColor(17, 11, 0, 32, 128);
  case 2:
    setColor(8, 8, 0, 32, 128);
    setColor(9, 9, 0, 32, 128);
    setColor(10, 10, 0, 32, 128);
    setColor(11, 10, 0, 32, 128);
    setColor(12, 10, 0, 32, 128);
    setColor(13, 10, 0, 32, 128);
    setColor(14, 9, 0, 32, 128);
    setColor(15, 8, 0, 32, 128);
  case 3:
    setColor(11, 6, 0, 32, 128);
    setColor(12, 6, 0, 32, 128);
    setColor(11, 7, 0, 32, 128);
    setColor(12, 7, 0, 32, 128);
  };

  strip.show();
}