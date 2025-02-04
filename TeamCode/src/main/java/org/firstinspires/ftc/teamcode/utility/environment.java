package org.firstinspires.ftc.teamcode.utility;


import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class environment {

    ColorSensor colorSensor;


    int alpha;
    int red;
    int green;
    int blue;
    double distance;

    public void init(HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class, "color");
        

    }

    private void getColorValues() {
        alpha = colorSensor.alpha();
        red = colorSensor.red();
        green = colorSensor.green();
        blue = colorSensor.blue();
    }

    public String getTapeColorDataRaw() {
        getColorValues();

        return "(" + alpha + ", " + red + ", " + green + ", " + blue + ")";
    }

    //return 0 if red, return 1 if blue, return 2 if unknown
    public String getTapeColor() {
        getColorValues();

        if (red > green && red > blue) {
            return "Red";
        }

        if (blue > red && blue > green) {
            return "Blue";
        }

        else {
            return "Unknown";
        }
    }
}
