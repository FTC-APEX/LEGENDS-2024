package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.constants;

public class outtake {
    public Servo clamp;
    private Servo left;
    private Servo right;
    private String State;

    public void init(HardwareMap hardwareMap) {
        clamp = hardwareMap.get(Servo.class, "outtake");
        left = hardwareMap.get(Servo.class, "left");
        right = hardwareMap.get(Servo.class, "right");
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

    public void move(constants.outtake state) {
        switch (state) {
            case READY:
                left.setPosition(constants.hingeReadyA);
                right.setPosition(constants.hingeReadyB);
                clamp.setPosition(constants.outtakeOpen);
                State = "Ready for Pixels";
                break;

            case MOVING:
                left.setPosition(constants.hingeReadyA);
                right.setPosition(constants.hingeReadyB);
                clamp.setPosition(constants.outtakeClose);
                State = "Loaded and Armed";
                break;

            case AIM:
                left.setPosition(constants.hingeScoreA);
                right.setPosition(constants.hingeScoreB);
                clamp.setPosition(constants.outtakeClose);
                State = "Ready for Drop";
                break;

            case SCORE:
                left.setPosition(constants.hingeScoreA);
                right.setPosition(constants.hingeScoreB);
                clamp.setPosition(constants.outtakeOpen);
                State = "Dropping";
                break;
        }
    }

    public String getStatus() {
        return State;
    }

}
