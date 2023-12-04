package org.firstinspires.ftc.teamcode.OpModes.teleOp;




import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.roadrunner.drive.StandardTrackingWheelLocalizer;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.util.constants;
import org.firstinspires.ftc.teamcode.util.environment;

import java.util.Objects;

@TeleOp(name = "Krusty")
public class tele extends LinearOpMode {
    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;

    private slides slides;
    private intake intake;
    private outtake outtake;
    private environment environment;
    StandardTrackingWheelLocalizer localizer;
    private String Alliance;
    private String Status = "Running";
    private boolean Safety = false;

    @Override
    public void runOpMode() {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        frontLeft = hardwareMap.get(DcMotorEx.class, "leftFront");
        frontRight = hardwareMap.get(DcMotorEx.class, "rightFront");
        backLeft = hardwareMap.get(DcMotorEx.class, "leftRear");
        backRight = hardwareMap.get(DcMotorEx.class, "rightRear");
        //Remember to change this to appropriate when chain drive implemented
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        slides.init(hardwareMap);
        intake.init(hardwareMap);
        outtake.init(hardwareMap);
        environment.init(hardwareMap);

        localizer = new StandardTrackingWheelLocalizer(hardwareMap);

        localizer.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(90)));

        while (opModeInInit()) {
            telemetry.addLine("Robot Initialized");
            telemetry.update();
        }


        waitForStart();
        while (!isStopRequested()) {
            while (opModeIsActive()) {
                localizer.update();
                Pose2d currentPose = localizer.getPoseEstimate();
                telemetry.addData("Robot Status: ", Status);
                telemetry.addData("Current Alliance: ", Alliance);
                telemetry.addLine(""); // Empty Line Break
                telemetry.addData("X: ", currentPose.getX());
                telemetry.addData("Y: ", currentPose.getY());
                telemetry.addData("Heading: ", currentPose.getHeading());
                telemetry.addLine(""); // Empty Line Break
                telemetry.addData("Intake: ", intake.getStatus());
                telemetry.addData("Outtake: ", outtake.getStatus());
                telemetry.addData("Slides: ", slides.getStatus());

                //Alliance choosing
                if (gamepad1.options && gamepad1.triangle) {
                    Alliance = "Blue";

                }

                if (gamepad1.options && gamepad1.square) {
                    Alliance = "Red";
                }

                if (gamepad1.options && gamepad1.cross) {
                    Safety = false;
                }

                if (gamepad1.options && gamepad1.circle) {
                    Safety = true;
                }

                //Driving - boost not implemented
                if (Objects.equals(environment.getTapeColor(), Alliance) && currentPose.getY() >= 48 && Safety == true) {
                    if (gamepad1.right_stick_y < - 0.2) {
                        frontLeft.setPower(-0.5);
                        frontRight.setPower(-0.5);
                        backLeft.setPower(-0.5);
                        backRight.setPower(-0.5);
                    }
                }
                else {
                    if (Math.abs(gamepad1.left_stick_y) > 0.2 || Math.abs(gamepad1.left_stick_x) > 0.2 || Math.abs(gamepad1.right_stick_x) > 0.2) {
                        frontLeft.setPower(((gamepad1.left_stick_y - gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                        frontRight.setPower(((gamepad1.left_stick_y * -1 - gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                        backRight.setPower(((gamepad1.left_stick_y + gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                        backLeft.setPower(((gamepad1.left_stick_y * -1 + gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                    } else {
                        frontLeft.setPower(0);
                        frontRight.setPower(0);
                        backRight.setPower(0);
                        backLeft.setPower(0);
                    }
                }

                if (gamepad1.dpad_up) { //dpad slow movement
                    frontLeft.setPower(0.3);
                    frontRight.setPower(0.3);
                    backRight.setPower(0.3);
                    backLeft.setPower(0.3);
                }
                if (gamepad1.dpad_right) {
                    frontLeft.setPower(0.5);
                    frontRight.setPower(-0.5);
                    backRight.setPower(0.5);
                    backLeft.setPower(-0.5);
                }
                if (gamepad1.dpad_left) {
                    frontLeft.setPower(-0.5);
                    frontRight.setPower(0.5);
                    backRight.setPower(-0.5);
                    backLeft.setPower(0.5);
                }
                if (gamepad1.dpad_down) {
                    frontLeft.setPower(-0.3);
                    frontRight.setPower(-0.3);
                    backRight.setPower(-0.3);
                    backLeft.setPower(-0.3);
                }

                //Intake States
                if (gamepad1.triangle) {
                    intake.height(constants.intakeHeights.SIX);
                }

                if (gamepad1.square) {
                    intake.height(constants.intakeHeights.TWO);
                }

                if (gamepad1.circle) {
                    intake.height(constants.intakeHeights.FOUR);
                }

                if (gamepad1.cross) {
                    intake.height(constants.intakeHeights.FLOOR);
                }

                if (gamepad1.right_bumper) {
                    intake.state(constants.intakeState.STOP);
                }

                if (gamepad1.right_trigger != 0) {
                    intake.state(constants.intakeState.SUCK);
                }

                if (gamepad1.left_trigger != 0) {
                    intake.state(constants.intakeState.SPIT);
                }

                //slide states
                if (gamepad2.dpad_down) {
                    slides.preset(constants.slides.READY);
                }

                if (gamepad2.dpad_left) {
                    slides.preset(constants.slides.FIRST);
                }

                if (gamepad2.dpad_right) {
                    slides.preset(constants.slides.SECOND);
                }

                if (gamepad2.dpad_up) {
                    slides.preset(constants.slides.THIRD);
                }

                if (gamepad2.right_bumper) {
                    slides.preset(constants.slides.FULL);
                }

                if (gamepad2.right_stick_y > 0.2) {
                    slides.preset(constants.slides.CUSTOM);
                    slides.setHeight(slides.getCurrentHeight() + 1); // Switch value to something during testing
                    telemetry.addData("Custom Slide Height: ", slides.getCurrentHeight());
                }

                if (gamepad2.right_stick_y < -0.2) {
                    slides.preset(constants.slides.CUSTOM);
                    slides.setHeight(slides.getCurrentHeight() - 1); // Switch value to something during testing
                    telemetry.addData("Custom Slide Height: ", slides.getCurrentHeight());
                }

                //outtake states
                if (gamepad2.cross) {
                    outtake.state(constants.outtake.READY);
                }

                if (gamepad2.square) {
                    outtake.state(constants.outtake.MOVING);
                }

                if (gamepad2.circle) {
                    outtake.state(constants.outtake.AIM);
                }

                if (gamepad2.triangle) {
                    outtake.state(constants.outtake.SCORE);
                }


                telemetry.update();
            }
        }
    }
}
