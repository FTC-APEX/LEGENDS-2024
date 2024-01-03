package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.constantsRobot;

public class shooter {
    private Servo shooter;
    boolean isShot = false;
    String state = "PRE-INIT";

    public void init(HardwareMap hardwareMap) {
        shooter = hardwareMap.get(Servo.class, "shooter");
        shooter.setPosition(constantsRobot.SHOOTER_READY);
        state = "INIT - READY";
    }

    public void shoot() {
        shooter.setPosition(constantsRobot.SHOOTER_SHOT);
        state = "SHOT";
    }

    public String getState() {
        return state;
    }

}
