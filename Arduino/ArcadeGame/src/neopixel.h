

#ifndef ARCADE_NEOPIXEL_H
#define ARCADE_NEOPIXEL_H

#include <Arduino.h>
#include <Adafruit_NeoPixel.h>

uint32_t color(int red, int green, int blue);
void setColor(int column, int row, int red, int green, int blue);
void setColor(int column, int row, uint32_t color);

void setupNeopixel();
void clearNeoPixel();
void clearAndShowNeoPixel();
void showNeoPixel();

void animateWifiSymbol(int red, int green, int blue);
void renderArcade();


void renderA(int x, int y, uint32_t color);
void renderB(int x, int y, uint32_t color);
void renderC(int x, int y, uint32_t color);
void renderD(int x, int y, uint32_t color);
void renderE(int x, int y, uint32_t color);
void renderF(int x, int y, uint32_t color);
void renderG(int x, int y, uint32_t color);
void renderH(int x, int y, uint32_t color);
void renderI(int x, int y, uint32_t color);
void renderJ(int x, int y, uint32_t color);
void renderK(int x, int y, uint32_t color);
void renderL(int x, int y, uint32_t color);
void renderM(int x, int y, uint32_t color);
void renderN(int x, int y, uint32_t color);
void renderO(int x, int y, uint32_t color);
void renderP(int x, int y, uint32_t color);
void renderQ(int x, int y, uint32_t color);
void renderR(int x, int y, uint32_t color);
void renderS(int x, int y, uint32_t color);
void renderT(int x, int y, uint32_t color);
void renderU(int x, int y, uint32_t color);
void renderV(int x, int y, uint32_t color);
void renderW(int x, int y, uint32_t color);
void renderX(int x, int y, uint32_t color);
void renderY(int x, int y, uint32_t color);
void renderZ(int x, int y, uint32_t color);

#endif