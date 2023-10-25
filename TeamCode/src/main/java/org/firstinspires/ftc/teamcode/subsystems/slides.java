package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.constants;

public class slides {
    DcMotorEx left, right;
    int height;
    int target;
    double leftPower, rightPower;

    public void init(HardwareMap map){
        left = map.get(DcMotorEx.class, "lift-L");
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        right = map.get(DcMotorEx.class, "lift-R");
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void preset(constants.slides preset){
        switch (preset) {
            case READY:
                setHeight(0);
                break;

            case FIRST:
                //setHeight(x1);
                break;

            case SECOND:
                //setHeight(x2);
                break;

            case THIRD:
                //setHeight(x3);
                break;

            case FULL:
                //setHeight(x4);
                break;

            case STANDBY: //Only used during testing as a transition state - not to be used in matches & will not have button for setting
                //setHeight(x2.5);
                break;
        }
    }

    public void setHeight(int target){
        this.target = target;
        double currentHeight = right.getCurrentPosition();

        if (currentHeight < target) {
            leftPower = 1;
            rightPower = 1;
        }

        if (currentHeight > target) {
            leftPower = -1;
            rightPower = -1;
        }

        left.setTargetPosition(this.target);
        right.setTargetPosition(this.target);

        left.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        right.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        left.setPower(leftPower);
        right.setPower(rightPower);
    }

    public int getCurrentHeight(){
        height = ((left.getCurrentPosition() + right.getCurrentPosition()) / 2);
        return height;
    }

    public void reset(){

    }

}
