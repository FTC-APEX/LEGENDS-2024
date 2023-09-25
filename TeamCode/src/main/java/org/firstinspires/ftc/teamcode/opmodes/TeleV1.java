package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.SwitchableLight;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name="TeleV1", group="TeleOp")

public class TeleV1 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private DcMotorEx elevator = null;
    private Servo leftServo = null;
    private Servo rightServo = null;
    private Claw mainClaw = new Claw();
    //private SampleMecanumDrive mainMechanum = new SampleMecanumDrive(); -add later



    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        elevator = hardwareMap.get(DcMotorEx.class, "elevator");
        leftServo = hardwareMap.get(Servo.class, "leftServo");
        rightServo = hardwareMap.get(Servo.class, "rightServo");

        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        waitForStart();
        mainClaw.init(hardwareMap);
        runtime.reset();

        while (opModeIsActive()) {
            //change naming conventions to match SampleMecanumDrive
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
            telemetry.addData("Left Servo Position", leftServo.getPosition());
            telemetry.addData("Right Servo Position", rightServo.getPosition());

            telemetry.addData("Code uploaded", "yes");

            while (!isStopRequested()) {
                // Translation
                if (Math.abs(gamepad1.left_stick_y) > 0.2 || Math.abs(gamepad1.left_stick_x) > 0.2 || Math.abs(gamepad1.right_stick_x) > 0.2) {
                    frontLeft.setPower(((gamepad1.left_stick_y - gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                    frontRight.setPower(((gamepad1.left_stick_y * -1 - gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                    backRight.setPower(((gamepad1.left_stick_y  + gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                    backLeft.setPower(((gamepad1.left_stick_y * -1 + gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                } else {
                    frontLeft.setPower(0);
                    frontRight.setPower(0);
                    backRight.setPower(0);
                    backLeft.setPower(0);
                }
                //change later?
                if (gamepad2.right_trigger > 0.3) {
                    mainClaw.openClaw();
                }

                if (gamepad2.right_bumper) {
                    mainClaw.closeClaw();
                }

                if (gamepad2.right_stick_x > 0.3) { //change to if statements
                    leftServo.setPosition(leftServo.getPosition() + 0.002);
                    rightServo.setPosition(rightServo.getPosition() - 0.002);
                }

                if (gamepad2.right_stick_x < -0.3) { //change to if statements
                    leftServo.setPosition(leftServo.getPosition() - 0.002);
                    rightServo.setPosition(rightServo.getPosition() + 0.002);
                }

                telemetry.update();
            }
        }
    }}