package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.util.constantsRobot;
import org.firstinspires.ftc.teamcode.utility.constants;

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
                setHeight(constantsRobot.READY);
                State = "Retracted";
                break;

            case FIRST:
                setHeight(constantsRobot.LINE1);
                State = "First Stage (Line 1)";
                break;

            case SECOND:
                setHeight(constantsRobot.LINE2);
                State = "Second Stage (Line 2)";
                break;

            case THIRD:
                setHeight(constantsRobot.LINE3);
                State = "Third State (Line 3)";
                break;

            case FULL:
                setHeight(constantsRobot.FULL);
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
            leftPower = 0.3;
            rightPower = 0.3;
        }

        if (currentHeight > target) {
            leftPower = -0.3;
            rightPower = -0.3;
        }

        left.setTargetPosition(this.target);
        right.setTargetPosition(-this.target);

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
