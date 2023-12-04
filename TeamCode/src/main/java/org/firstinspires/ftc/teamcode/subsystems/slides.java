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

    private String State;

    //Remember to check & change direction when needed
    public void init(HardwareMap map){
        left = map.get(DcMotorEx.class, "liftLeft");
        left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        right = map.get(DcMotorEx.class, "liftRight");
        right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    public void preset(constants.slides preset){
        switch (preset) {
            case READY:
                setHeight(constants.ready);
                State = "Retracted";
                break;

            case FIRST:
                setHeight(constants.first);
                State = "First Stage (Line 1)";
                break;

            case SECOND:
                setHeight(constants.second);
                State = "Second Stage (Line 2)";
                break;

            case THIRD:
                setHeight(constants.third);
                State = "Third State (Line 3)";
                break;

            case FULL:
                setHeight(constants.full);
                State = "Fully Extended";
                break;

            case CUSTOM:
                State = "Custom Slide Height - refer to next line";
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

    public String getStatus() {
        return State;
    }

}
