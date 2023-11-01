package org.firstinspires.ftc.teamcode.util;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.ColorSensor;




public class environment {

    ColorSensor colorSensor;

    int alpha;
    int red;
    int green;
    int blue;

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
