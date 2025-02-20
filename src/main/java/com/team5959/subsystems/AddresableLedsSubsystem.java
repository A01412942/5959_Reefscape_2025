package com.team5959.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.Optional;

import com.team5959.Constants.LedConstants;

import edu.wpi.first.wpilibj.AddressableLED; //Libreria para controlar tiras led programables Neopixel.
import edu.wpi.first.wpilibj.AddressableLEDBuffer; //Libreria para darle longitud de leds de la tira neopixel.
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

public class AddresableLedsSubsystem extends SubsystemBase {

    private AddressableLED m_led;
    private AddressableLEDBuffer m_ledBuffer;

    public AddresableLedsSubsystem() {
        m_led = new AddressableLED(LedConstants.ledPort); //Indica el puerto PWM de la tira led.
        m_ledBuffer = new AddressableLEDBuffer(LedConstants.ledLength); //indica el numero maximo de leds conectados
        m_led.setLength(m_ledBuffer.getLength()); //Obtiene la longitud maxima de leds de la tira.
        m_led.setData(m_ledBuffer); //Envia los datos a todos los leds del buffer
        m_led.start(); //inicia la transmision de datos a la tira.
    }

    public void setLedColor(int r, int g, int b) {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setRGB(i, r, g, b);
        }
        m_led.setData(m_ledBuffer);

    }

    public void setRainbowLeds(){
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
            m_ledBuffer.setHSV(i, (i * 180 / m_ledBuffer.getLength()) % 180, 255, 128);
        }
        m_led.setData(m_ledBuffer);
    }

    public void setAllianceColor() {
      Optional<Alliance> ally = DriverStation.getAlliance();
      if (ally.get() == Alliance.Red) {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
          m_ledBuffer.setRGB(i, 255, 0, 0);
        }
      }
      else {
        for (var i = 0; i < m_ledBuffer.getLength(); i++) {
          m_ledBuffer.setRGB(i, 0, 0, 255);
        }
      }
      m_led.setData(m_ledBuffer);
    }
  }
