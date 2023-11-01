package org.firstinspires.ftc.teamcode.util;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;



public class environment {

    ColorSensor colorSensor;

    public void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "color");
        

    }
}
