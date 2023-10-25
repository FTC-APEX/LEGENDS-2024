package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Slides {

    private DcMotorEx linearSlide;

    double Power;

    public void init(HardwareMap hardwareMap) {
        linearSlide = hardwareMap.get(DcMotorEx.class, "slides");
        linearSlide.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void setTargetPosition(double target){
        int currentPos = linearSlide.getCurrentPosition();

        if (currentPos < target) {
            // Going up
            Power = 0.8; //adjust as necessary
        } else if (currentPos > target) {
            // Going down
            Power = -0.8;
        }

        linearSlide.setTargetPosition((int) target);
        linearSlide.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        linearSlide.setPower(Power);
    }

    public int get_lift_position(){
        return linearSlide.getCurrentPosition();
    }


}