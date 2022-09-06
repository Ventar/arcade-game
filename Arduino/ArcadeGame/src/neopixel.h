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