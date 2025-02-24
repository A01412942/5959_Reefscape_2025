package com.team5959.subsystems;

import java.util.Optional;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class LEDSubsystem extends SubsystemBase {
    private final AddressableLED led;
    private final AddressableLEDBuffer ledBuffer;
    private final int ledCount = 27; // Adjust based on LED count
    
    private int rainbowFirstPixelHue = 0;
    double lastTime = 0;
    double interval = 0.25;
    boolean ledBlink = true;

    Optional<Alliance> ally;

    public LEDSubsystem(int PWMPort) {
        led = new AddressableLED(PWMPort);
        ledBuffer = new AddressableLEDBuffer(ledCount);
        led.setLength(ledBuffer.getLength());
        led.start();
    }

    @Override
    public void periodic() {
        // If match is in progress (teleop or auto)
        if (DriverStation.isEnabled()) {
            double matchTime = DriverStation.getMatchTime();
          if(matchTime <= 10.0 && matchTime != -1){
            
            /*/
             && ((((int)matchTime *2 )/ 2) % 2 == 0)
                setAllLEDs(0, 0, 0); 
                if ( ((((int)matchTime *2 )/ 2) % 2 != 0)){
                    setAllLEDs(0, 255, 0); 
                }
                */
                /*double blinkFrequency = 2 + (10 - matchTime) * 1.2; // Adjust the 0.8 multiplier to change how quickly it speeds up
                */
                // Calculate the amount of time that will blink
                double blinkFrequency = 2 + (10 - (matchTime/2)) * 4;
                // Calculate whether LED should be on or off, using a sin function
                boolean shouldBeOn = Math.sin(matchTime * blinkFrequency) > 0;

                    if (shouldBeOn) {
                        setAllLEDs(0, 255, 0);  // Green

                    } else{
                        setAllLEDs(0, 5, 0);
                    }


            }
            // Last 20 seconds: Green
             else if (matchTime <= 20.0 && matchTime != -1) {
                setAllLEDs(0, 255, 0);  // Green
            } 
            // Rest of match: Red
            else {
               Optional<Alliance> ally = DriverStation.getAlliance();
                if (ally.get() == Alliance.Red){
                    setAllLEDs(255, 0, 3);  // Red   
                } else {
                    setAllLEDs(0, 0, 255);  // Blue
                }  
            }
        }
        // If disabled (including after match): Rainbow
        else {
            runRainbowAnimation();
        }
        
        led.setData(ledBuffer); } 
    

    private void setAllLEDs(int r, int g, int b) {
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            ledBuffer.setRGB(i, r, g, b);
        }
    }

    private void runRainbowAnimation() {
        for (var i = 0; i < ledBuffer.getLength(); i++) {
            final var hue = (rainbowFirstPixelHue + (i * 180 / ledBuffer.getLength())) % 180;
            ledBuffer.setHSV(i, hue, 255, 128);
        }
        rainbowFirstPixelHue += 3;
        rainbowFirstPixelHue %= 180;
    }

    // Method to manually set LED color if needed
    public void setLEDColor(int r, int g, int b) {
        setAllLEDs(r, g, b);
        led.setData(ledBuffer);
    }
}