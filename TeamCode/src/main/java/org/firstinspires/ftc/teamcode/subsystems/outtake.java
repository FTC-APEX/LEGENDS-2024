package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.constantsRobot;
import org.java_websocket.enums.ReadyState;

public class outtake {
    private Servo blocker;
    private Servo pivotA;
    private Servo pivotB;

    private boolean isBlocking = false;
    private String state = "PRE-INIT";

    public void init(HardwareMap hardwareMap) {
        blocker = hardwareMap.get(Servo.class, "blocker");
        //Add whatever code needed to tune these servos --> Remember to change values in constantsRobot.java
        pivotA = hardwareMap.get(Servo.class, "left");
        pivotB = hardwareMap.get(Servo.class, "right");
        //Add whatever code needed to tune these servos --> Remember to change values in constantsRobot.java
        state = "INIT";
        isBlocking = false;
        setState(constantsRobot.outtake.READY);
    }

    public void setState(constantsRobot.outtake outtakeState) {
        switch(outtakeState) {
            case READY:
//                blocker.setPosition(constantsRobot.BLOCKER_OPEN);
                pivotA.setPosition(constantsRobot.PIVOT_A_READY);
                pivotB.setPosition(constantsRobot.PIVOT_B_READY);

                state = "READY";
                isBlocking = false;
                break;

            case MOVING:
//                blocker.setPosition(constantsRobot.BLOCKER_CLOSED);
                pivotA.setPosition(constantsRobot.PIVOT_A_READY);
                pivotB.setPosition(constantsRobot.PIVOT_B_READY);

                state = "MOVING";
                isBlocking = true;
                break;

            case AIM:
//                blocker.setPosition(constantsRobot.BLOCKER_CLOSED);
                pivotA.setPosition(constantsRobot.PIVOT_A_SCORE);
                pivotB.setPosition(constantsRobot.PIVOT_B_SCORE);

                state = "AIM";
                isBlocking = true;
                break;

            case SCORE:
//                blocker.setPosition(constantsRobot.BLOCKER_OPEN);
                pivotA.setPosition(constantsRobot.PIVOT_A_SCORE);
                pivotB.setPosition(constantsRobot.PIVOT_B_SCORE);

                state = "SCORE";
                isBlocking = false;
                break;

            default: //same as READY -- always initialize at READY, but if that fails, default will run (failsafe)
                setState(constantsRobot.outtake.READY);
                state = "DEFAULT --> READY (SOMETHING WENT WRONG IN INIT)";
                break;
        }
    }

    public void openBlocker() {
        blocker.setPosition(constantsRobot.BLOCKER_OPEN);
    }

    public void closeBlocker() {
        blocker.setPosition(constantsRobot.BLOCKER_CLOSED);
    }

    public void reset() {
        if ((!state.equals("READY") && !state.equals("INIT")) || isBlocking != false) {
            setState(constantsRobot.outtake.READY);
        }
        else if(state.equals("READY")) {
            state = "READY";
        }
        else {
            state = "RESET ERROR";
        }
    }



    public boolean isBlocking() {
        return isBlocking;
    }

    public String getState() {
        return state;
    }

}
