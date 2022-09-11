#include <neopixel.h>

/**
 * Neopixel board.
 */
Adafruit_NeoPixel strip(144 * 4, D5, NEO_GRB + NEO_KHZ800);

/**
 * Counter to handle animations
 */
int wifiAnimationCounter = 0;

uint32_t color(int red, int green, int blue)
{
    return strip.Color(red, green, blue);
}

/**
 * Sets the pixel of a single color to the passed values. While the pixels are addressed in
 * a row on the PCB the method adds an abstraction to set rows and columns. The index is zero based.
 */
void setColor(int column, int row, int red, int green, int blue)
{
    setColor(column, row, strip.Color(red, green, blue));
}

/**
 * Sets the pixel of a single color to the passed values. While the pixels are addressed in
 * a row on the PCB the method adds an abstraction to set rows and columns. The index is zero based.
 */
void setColor(int column, int row, uint32_t color)
{

    int boardsIndRow = 2;

    int pixelRow = (row / 12) * 144 * boardsIndRow; // the number of pixel we need to add for the rows of the boards below
    int pixelColumn = (column / 12) * 144;          // the number of pixel we need to add for the columns of the boards to the left

    int pixel = (row % 12) * 12 + (column % 12) + pixelColumn + pixelRow;
    strip.setPixelColor(pixel, color);
}

void setupNeopixel()
{
    strip.begin();
    strip.setBrightness(16);
    strip.clear();
    strip.show();
}

void clearNeoPixel()
{
    strip.clear();
}

void clearAndShowNeoPixel()
{
    strip.clear();
    strip.show();
}

void showNeoPixel()
{
    strip.show();
}

void animateWifiSymbol(int red, int green, int blue)
{

    strip.clear();

    switch (3 - (wifiAnimationCounter++ % 4))
    {
    case 0:
        setColor(5, 14, red, green, blue);
        setColor(6, 15, red, green, blue);
        setColor(7, 16, red, green, blue);
        setColor(8, 17, red, green, blue);
        setColor(9, 17, red, green, blue);
        setColor(10, 18, red, green, blue);
        setColor(11, 18, red, green, blue);
        setColor(12, 18, red, green, blue);
        setColor(13, 18, red, green, blue);
        setColor(14, 17, red, green, blue);
        setColor(15, 17, red, green, blue);
        setColor(16, 16, red, green, blue);
        setColor(17, 15, red, green, blue);
        setColor(18, 14, red, green, blue);
    case 1:
        setColor(6, 11, red, green, blue);
        setColor(7, 12, red, green, blue);
        setColor(8, 13, red, green, blue);
        setColor(9, 13, red, green, blue);
        setColor(10, 14, red, green, blue);
        setColor(11, 14, red, green, blue);
        setColor(12, 14, red, green, blue);
        setColor(13, 14, red, green, blue);
        setColor(14, 13, red, green, blue);
        setColor(15, 13, red, green, blue);
        setColor(16, 12, red, green, blue);
        setColor(17, 11, red, green, blue);
    case 2:
        setColor(8, 8, red, green, blue);
        setColor(9, 9, red, green, blue);
        setColor(10, 10, red, green, blue);
        setColor(11, 10, red, green, blue);
        setColor(12, 10, red, green, blue);
        setColor(13, 10, red, green, blue);
        setColor(14, 9, red, green, blue);
        setColor(15, 8, red, green, blue);
    case 3:
        setColor(11, 6, red, green, blue);
        setColor(12, 6, red, green, blue);
        setColor(11, 7, red, green, blue);
        setColor(12, 7, red, green, blue);
    };

    strip.show();
}

void renderA(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 1, y + 4, color);
    setColor(x + 1, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 0, color);
}

void renderB(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 3, color);
}

void renderC(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 4, color);
}

void renderD(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
}

void renderE(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 4, color);
}

void renderF(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 4, color);
}

void renderG(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 4, color);
}

void renderH(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 2, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderI(int x, int y, uint32_t color)
{
    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 1, color);
    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 3, color);
    setColor(x + 1, y + 4, color);
}

void renderJ(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);

    setColor(x + 1, y + 0, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderK(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 2, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderL(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);

    setColor(x + 2, y + 0, color);
}

void renderM(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 3, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderN(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 1, color);
    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 3, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderO(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderP(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderQ(int x, int y, uint32_t color)
{

    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 1, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderR(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderS(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 4, color);
}

void renderT(int x, int y, uint32_t color)
{

    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 1, color);
    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 3, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 4, color);
}

void renderU(int x, int y, uint32_t color)
{
    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderV(int x, int y, uint32_t color)
{

    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);

    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderW(int x, int y, uint32_t color)
{

    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 2, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 1, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 2, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderX(int x, int y, uint32_t color)
{

    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 2, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 1, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderY(int x, int y, uint32_t color)
{

    setColor(x + 0, y + 3, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 1, color);
    setColor(x + 1, y + 2, color);

    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderZ(int x, int y, uint32_t color)
{

    setColor(x + 0, y + 0, color);
    setColor(x + 0, y + 1, color);
    setColor(x + 0, y + 4, color);

    setColor(x + 1, y + 0, color);
    setColor(x + 1, y + 2, color);
    setColor(x + 1, y + 4, color);

    setColor(x + 2, y + 0, color);
    setColor(x + 2, y + 3, color);
    setColor(x + 2, y + 4, color);
}

void renderLargeBlock(int x, int y, uint32_t color)
{

    setColor(x, y, color);
    setColor(x + 1, y, color);
    setColor(x, y + 1, color);
    setColor(x + 1, y + 1, color);
}

void renderArcade()
{

    strip.clear();

    renderA(0, 15, strip.Color(0, 128, 128));
    renderR(4, 18, strip.Color(128, 0, 128));
    renderC(8, 16, strip.Color(128, 128, 0));
    renderA(12, 15, strip.Color(0, 0, 128));
    renderD(16, 16, strip.Color(128, 0, 0));
    renderE(20, 17, strip.Color(0, 128, 0));

    renderLargeBlock(7, 1, strip.Color(255, 0, 0));
    renderLargeBlock(9, 1, strip.Color(255, 0, 0));
    renderLargeBlock(11, 1, strip.Color(255, 0, 0));
    renderLargeBlock(7, 3, strip.Color(255, 0, 0));

    renderLargeBlock(7, 5, strip.Color(255, 255, 0));
    renderLargeBlock(7, 7, strip.Color(255, 255, 0));
    renderLargeBlock(9, 3, strip.Color(255, 255, 0));
    renderLargeBlock(9, 5, strip.Color(255, 255, 0));

    renderLargeBlock(11, 3, strip.Color(0, 0, 255));
    renderLargeBlock(13, 1, strip.Color(0, 0, 255));
    renderLargeBlock(13, 3, strip.Color(0, 0, 255));
    renderLargeBlock(13, 5, strip.Color(0, 0, 255));

    renderLargeBlock(15, 1, strip.Color(0, 255, 0));
    renderLargeBlock(15, 3, strip.Color(0, 255, 0));
    renderLargeBlock(15, 5, strip.Color(0, 255, 0));
    renderLargeBlock(15, 7, strip.Color(0, 255, 0));

    renderLargeBlock(9, 11, strip.Color(255, 0, 255));
    renderLargeBlock(11, 9, strip.Color(255, 0, 255));
    renderLargeBlock(11,11, strip.Color(255, 0, 255));
    renderLargeBlock(13, 11, strip.Color(255, 0, 255));

    strip.show();
}
