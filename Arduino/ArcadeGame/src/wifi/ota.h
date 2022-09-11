#ifndef ARCADE_OTA_H
#define ARCADE_OTA_H

#include <ESP8266WiFi.h>
#include <ESP8266mDNS.h>
#include <WiFiUdp.h>
#include <neopixel.h>
#include <ArduinoOTA.h>

void setupOTA()
{

    ArduinoOTA.setHostname("arcade");

    ArduinoOTA.onStart([]()
                       {
    String type;
    if (ArduinoOTA.getCommand() == U_FLASH) {
      type = "sketch";
    } else {  // U_FS
      type = "filesystem";
    }

    // NOTE: if updating FS this would be the place to unmount FS using FS.end()
    Serial.println("Start updating " + type); });

    ArduinoOTA.onEnd([]()
        { 
            clearNeoPixel();

            renderR(0,10,color(64,255,64));
            renderE(4,10,color(64,255,64));
            renderB(8,10,color(64,255,64));
            renderO(12,10,color(64,255,64));
            renderO(16,10,color(64,255,64));
            renderT(20,10,color(64,255,64));

            showNeoPixel(); 

        });

    ArduinoOTA.onProgress([](unsigned int progress, unsigned int total)
        {
            int p = (int)(progress / (total / 100)) / 10;

            clearNeoPixel();

            renderU(0,17,color(64,255,64));
            renderP(4,17,color(64,255,64));
            renderD(8,17,color(64,255,64));
            renderA(12,17,color(64,255,64));
            renderT(16,17,color(64,255,64));
            renderE(20,17,color(64,255,64));


            for(int i = 0; i < 20; i++) {
            setColor(2 +i, 10, 128,128,128);
            setColor(2 +i, 15, 128,128,128);
            }  

            for(int i = 0; i < p * 2; i++) {
            setColor(2 +i, 11, 0,255,0);
            setColor(2 +i, 12, 0,255,0);
            setColor(2 +i, 13, 0,255,0);
            setColor(2 +i, 14, 0,255,0);
            }  

            showNeoPixel(); 
        });

    ArduinoOTA.onError([](ota_error_t error)
                       {
    Serial.printf("Error[%u]: ", error);
    if (error == OTA_AUTH_ERROR) {
      Serial.println("Auth Failed");
    } else if (error == OTA_BEGIN_ERROR) {
      Serial.println("Begin Failed");
    } else if (error == OTA_CONNECT_ERROR) {
      Serial.println("Connect Failed");
    } else if (error == OTA_RECEIVE_ERROR) {
      Serial.println("Receive Failed");
    } else if (error == OTA_END_ERROR) {
      Serial.println("End Failed");
    } });

    ArduinoOTA.begin(false);
}

#endif