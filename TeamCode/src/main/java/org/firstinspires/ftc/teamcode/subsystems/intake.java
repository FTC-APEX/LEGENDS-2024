package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class intake {
    private DcMotorEx Intake;

    boolean isBusy = false;

    public void init(HardwareMap hardwareMap) {
        Intake = hardwareMap.get(DcMotorEx.class, "Intake");
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        Intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void activate() {
        Intake.setPower(1);
        isBusy = true;
    }

    public void deactivate() {
        Intake.setPower(0);
        isBusy = false;
    }

    public boolean getStatus() {
        return isBusy;
    }
}