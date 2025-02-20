package com.team5959.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDSubsystem extends SubsystemBase {
    private final AddressableLED led;
    private final AddressableLEDBuffer ledBuffer;
    private final int ledCount = 27; // Adjust based on LED count
    
    private int rainbowFirstPixelHue = 0;

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
            
            // Last 20 seconds: Green
            if (matchTime <= 20.0 && matchTime != -1) {
                setAllLEDs(0, 255, 0);  // Green
            } 
            // Rest of match: Red
            else {
                setAllLEDs(255, 0, 0);  // Red
            }
        }
        // If disabled (including after match): Rainbow
        else {
            runRainbowAnimation();
        }
        
        led.setData(ledBuffer);
    }

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