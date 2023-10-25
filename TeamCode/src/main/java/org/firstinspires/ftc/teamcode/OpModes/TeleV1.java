package org.firstinspires.ftc.teamcode.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;


@TeleOp(name="TeleV1", group="TeleOp")

public class TeleV1 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotorEx Slides = null;
    private Servo Servo = null;

    private NormalizedColorSensor MainColorSensor;


    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        Slides = hardwareMap.get(DcMotorEx.class, "Slides");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            telemetry.addData("Robot", "Initialized");
            telemetry.addData("Front Left: ", frontLeft.getCurrentPosition());
            telemetry.addData("Front Right: ", frontRight.getCurrentPosition());
            telemetry.addData("Back Left: ", backLeft.getCurrentPosition());
            telemetry.addData("Back Right: ", backRight.getCurrentPosition());
            telemetry.addData("left stick y", gamepad1.left_stick_y);
            telemetry.addData("left stick x", gamepad1.left_stick_x);
            telemetry.addData("right stick x", gamepad1.right_stick_x);
            telemetry.addData("A", gamepad2.a);
            telemetry.addData("B", gamepad2.b);
            telemetry.addData("X", gamepad2.x);
            telemetry.addData("Y", gamepad2.y);
            telemetry.addData("Right Trigger", gamepad2.right_trigger);
            telemetry.addData("Right Bumper", gamepad2.right_bumper);
            telemetry.addData("Left Trigger", gamepad2.left_trigger);
            telemetry.addData("Left Bumper", gamepad2.left_bumper);
            telemetry.addLine();
            /*telemetry.addData("Red", "%.3f", colors.red) //missing some sort of color import
            .addData("Green", "%.3f", colors.green)
            .addData("Blue", "%.3f", colors.blue);
            telemetry.addLine()
            .addData("Hue", "%.3f", hsvValues[0])
            .addData("Saturation", "%.3f", hsvValues[1])
            .addData("Value", "%.3f", hsvValues[2]);
            telemetry.addData("Alpha", "%.3f", colors.alpha);*/
            telemetry.addData("Code uploaded", "yes");




                telemetry.update();
            }
        }
    }}