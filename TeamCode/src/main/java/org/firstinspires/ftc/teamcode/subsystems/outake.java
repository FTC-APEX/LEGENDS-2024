package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class outake {
    private Servo Clamp;

    public void init(HardwareMap hardwareMap) {
        Clamp = hardwareMap.get(Servo.class, "Clamp");
    }
    double ClampClose = -1; //change as necessary
    double ClampOpen = -0.5;

    public void openClamp() {
        Clamp.setPosition(ClampClose);
    }

    public void closeClamp() {
        Clamp.setPosition(ClampOpen);
    }

}
