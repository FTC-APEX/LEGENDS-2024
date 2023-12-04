package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.constants;

public class outtake {
    private Servo clamp;
    private Servo hingeA;
    private Servo hingeB;
    private String State;

    public void init(HardwareMap hardwareMap) {
        clamp = hardwareMap.get(Servo.class, "Clamp");
        hingeA = hardwareMap.get(Servo.class, "HingeA");
        hingeB = hardwareMap.get(Servo.class, "HingeB");
    }
    double ClampClose = -1; //change as necessary
    double ClampOpen = -0.5; //change as necessary - may be moved to constants

//    public void openClamp() {
//        clamp.setPosition(ClampClose);
//    }
//
//    public void closeClamp() {
//        clamp.setPosition(ClampOpen);
//    }

    public void state(constants.outtake state) {
        switch (state) {
            case READY:
                hingeA.setPosition(constants.hingeReady);
                hingeB.setPosition(constants.hingeReady);
                clamp.setPosition(constants.clampOpen);
                State = "Ready for Pixels";
                break;

            case MOVING:
                hingeA.setPosition(constants.hingeReady);
                hingeB.setPosition(constants.hingeReady);
                clamp.setPosition(constants.clampClose);
                State = "Loaded and Armed";
                break;

            case AIM:
                hingeA.setPosition(constants.hingeScore);
                hingeB.setPosition(constants.hingeScore);
                clamp.setPosition(constants.clampClose);
                State = "Ready for Drop";
                break;

            case SCORE:
                hingeA.setPosition(constants.hingeScore);
                hingeB.setPosition(constants.hingeScore);
                clamp.setPosition(constants.clampOpen);
                State = "Dropping";
                break;
        }
    }

    public String getStatus() {
        return State;
    }

}
