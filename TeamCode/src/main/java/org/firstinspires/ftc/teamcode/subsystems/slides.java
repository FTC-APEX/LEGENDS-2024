package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.util.constantsRobot;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class slides {
    private DcMotorEx left;
    private DcMotorEx right;

    private int tolerance = 5; // Placeholder Value --> Must be tuned with real bot

    private int lastLeftError = 0;
    private int lastRightError = 0;

    private double[] pid = {constantsRobot.kp, constantsRobot.ki, constantsRobot.kd, constantsRobot.kg}; // kp, ki, kd, kg (gravity constant) --> go tune later in constantsRobot.java

    ElapsedTime timer = new ElapsedTime();

    private String state = "PRE_INIT";

    public void init(HardwareMap hardwareMap) {
        left = hardwareMap.get(DcMotorEx.class, "left");
        right = hardwareMap.get(DcMotorEx.class, "right");
        right.setDirection(DcMotorEx.Direction.REVERSE); // This might be left, so check once bot is built

        left.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        right.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        left.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        right.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        state = "INIT";

        setPosition(constantsRobot.slides.READY);
    }

    public void runToPosition(int target) {
        while(Math.abs(lastLeftError) > tolerance | Math.abs(lastRightError) > tolerance) {
            int leftPos = left.getCurrentPosition();
            int rightPos = right.getCurrentPosition();

            int leftError = target - leftPos;
            int rightError = target - rightPos;

            double leftD = (leftError - lastLeftError)/timer.seconds();
            double rightD = (rightError - lastRightError)/timer.seconds();

            double leftIntegralSum = 0;
            leftIntegralSum = leftIntegralSum + (leftError * timer.seconds());
            double rightIntgegralSum = 0;
            rightIntgegralSum = rightIntgegralSum + (rightError * timer.seconds());

            double leftPower = (pid[0]*leftError) + (pid[1]*leftIntegralSum) + (pid[2]*leftD);
            double rightPower = (pid[0]*rightError) + (pid[1]*rightIntgegralSum) + (pid[2]*rightD);

            if (leftPower > constantsRobot.MAX_POWER) {
                leftPower = constantsRobot.MAX_POWER;
            }

            if (rightPower > constantsRobot.MAX_POWER) {
                rightPower = constantsRobot.MAX_POWER;
            }

            left.setPower(leftPower);
            right.setPower(rightPower);

            returnLeftValue(leftPower);
            returnRightValue(rightPower);

            lastLeftError = leftError;
            lastRightError = rightError;

            timer.reset();

        }
    }

    public void setPosition(constantsRobot.slides slideState) {
        switch (slideState) {
            case READY:
                runToPosition(constantsRobot.READY);
                state = "READY";
                break;

            case FIRST:
                runToPosition(constantsRobot.LINE1);
                state = "FIRST";
                break;

            case SECOND:
                runToPosition(constantsRobot.LINE2);
                state = "SECOND";
                break;

            case THIRD:
                runToPosition(constantsRobot.LINE3);
                state = "THIRD";
                break;

            case FULL:
                runToPosition(constantsRobot.FULL);
                state = "FULL";
                break;

            case HANG:
                runToPosition(constantsRobot.HANG);
                state = "HANG";
                break;

            default:
                setPosition(constantsRobot.slides.READY);
                state = "DEFAULT --> READY (SOMETHING WENT WRONG IN INIT)";
                break;
        }
    }






    public int getCurrentPosition() {
        return left.getCurrentPosition();
    }

    //used for tuning PIDs only
    public int getLastLeftError() {
        return lastLeftError;
    }

    public int getLastRightError() {
        return lastRightError;
    }

    public double returnLeftValue(double value) {
        return value;
    }

    public double returnRightValue(double value) {
        return value;
    }



}
