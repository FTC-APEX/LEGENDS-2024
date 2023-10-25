package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class hang {
    private Servo HangerRight;
    private Servo HangerLeft; //I'm just going to assume that the hanger uses two servos, adjust as necessary

    public void init(HardwareMap hardwareMap) {
        HangerRight = hardwareMap.get(Servo.class, "HangerRight");
        HangerLeft = hardwareMap.get(Servo.class, "HangerLeft");
    }
}

