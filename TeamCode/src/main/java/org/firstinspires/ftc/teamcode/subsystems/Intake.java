package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private DcMotor Intake;

    public void init(HardwareMap hardwareMap) {
        Intake = hardwareMap.get(DcMotor.class, "Intake");
    }
}
