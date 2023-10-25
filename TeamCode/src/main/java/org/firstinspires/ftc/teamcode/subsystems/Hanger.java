package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.*;

public class Hanger {
    private DcMotor HangerRight;
    private DcMotor HangerLeft; //I'm just going to assume that the hanger uses two motors, adjust as necessary

    public void init(HardwareMap hardwareMap) {
        HangerRight = hardwareMap.get(DcMotor.class, "HangerRight");
        HangerLeft = hardwareMap.get(DcMotor.class, "HangerLeft");
    }
}

