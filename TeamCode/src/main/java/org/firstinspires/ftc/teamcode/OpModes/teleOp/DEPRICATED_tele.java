package org.firstinspires.ftc.teamcode.OpModes.teleOp;




import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.roadrunner.drive.StandardTrackingWheelLocalizer;
import org.firstinspires.ftc.teamcode.subs.slides;
import org.firstinspires.ftc.teamcode.subs.intake;
import org.firstinspires.ftc.teamcode.subs.outtake;
import org.firstinspires.ftc.teamcode.utility.constants;
import org.firstinspires.ftc.teamcode.utility.environment;

@TeleOp(name = "Krusty")
public class DEPRICATED_tele extends LinearOpMode {
    private DcMotorEx frontLeft;
    private DcMotorEx frontRight;
    private DcMotorEx backLeft;
    private DcMotorEx backRight;

    private slides slides = new slides();
    private outtake outtake = new outtake();
    private environment environment;
    StandardTrackingWheelLocalizer localizer;
    private String Alliance;
    private String Status = "Running";
    private boolean Safety = false;

    @Override
    public void runOpMode() {

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        //Remember to change this to appropriate when chain drive implemented
        backLeft.setDirection(DcMotorEx.Direction.REVERSE);
        backRight.setDirection(DcMotorEx.Direction.REVERSE);
        frontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        frontRight.setDirection(DcMotorEx.Direction.REVERSE);

        //slides.init(hardwareMap);
//        intake.init(hardwareMap);
        //outtake.init(hardwareMap);
        //environment.init(hardwareMap);

        //localizer = new StandardTrackingWheelLocalizer(hardwareMap);

        //localizer.setPoseEstimate(new Pose2d(0, 0, Math.toRadians(90)));

        while (opModeInInit()) {
            telemetry.addLine("Robot Initialized");
            telemetry.update();
        }


        waitForStart();
        while (!isStopRequested()) {
            while (opModeIsActive()) {
                //localizer.update();
                //Pose2d currentPose = localizer.getPoseEstimate();
                telemetry.addData("Robot Status: ", Status);
                telemetry.addData("Current Alliance: ", Alliance);
                telemetry.addLine(""); // Empty Line Break
                //telemetry.addData("X: ", currentPose.getX());
                //telemetry.addData("Y: ", currentPose.getY());
                //telemetry.addData("Heading: ", currentPose.getHeading());
                telemetry.addLine(""); // Empty Line Break
                //telemetry.addData("Intake: ", intake.getStatus());
                //telemetry.addData("Outtake: ", outtake.getStatus());
                telemetry.addData("Slides: ", slides.getStatus());
                telemetry.addLine(""); // Empty Line Break


                //Alliance choosing
                if (gamepad1.options && gamepad1.triangle) {
                    Alliance = "Blue";

                }

                if (gamepad1.options && gamepad1.square) {
                    Alliance = "Red";
                }

//                if (gamepad1.options && gamepad1.cross) {
//                    Safety = false;
//                }
//
//                if (gamepad1.options && gamepad1.circle) {
//                    Safety = true;
//                }

//              Driving - boost not implemented
                if (Safety == true) { //&& currentPose.getY() >= 48 Objects.equals(environment.getTapeColor(), Alliance)  &&
                    if (gamepad1.right_stick_y < - 0.2) {
                        frontLeft.setPower(0.5);
                        frontRight.setPower(0.5);
                        backLeft.setPower(0.5);
                        backRight.setPower(0.5);
                    }
                }
                else {
                    if (Math.abs(gamepad1.left_stick_y) > 0.2 || Math.abs(gamepad1.left_stick_x) > 0.2 || Math.abs(gamepad1.right_stick_x) > 0.2) {
                        frontLeft.setPower(((gamepad1.left_stick_y - gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x));
                        frontRight.setPower(((gamepad1.left_stick_y * -1 - gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x));
                        backRight.setPower(((gamepad1.left_stick_y + gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x));
                        backLeft.setPower(((gamepad1.left_stick_y * -1 + gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x));
                    } else {
                        frontLeft.setPower(0);
                        frontRight.setPower(0);
                        backRight.setPower(0);
                        backLeft.setPower(0);
                    }
                }

                if (gamepad1.dpad_up) { //dpad slow movement
                    frontLeft.setPower(-0.5);
                    frontRight.setPower(0.5);
                    backRight.setPower(-0.5);
                    backLeft.setPower(0.5);
                }
                if (gamepad1.dpad_right) {
                    frontLeft.setPower(-0.75);
                    frontRight.setPower(-0.75);
                    backRight.setPower(-0.75);
                    backLeft.setPower(-0.75);
                }
                if (gamepad1.dpad_left) {
                    frontLeft.setPower(0.75);
                    frontRight.setPower(0.75);
                    backRight.setPower(0.75);
                    backLeft.setPower(0.75);
                }
                if (gamepad1.dpad_down) {
                    frontLeft.setPower(0.5);
                    frontRight.setPower(-0.5);
                    backRight.setPower(0.5);
                    backLeft.setPower(-0.5);
                }

                //Intake States
//                if (gamepad1.triangle) {
//                    intake.height(constants.intakeHeights.SIX);
//                }
//
//                if (gamepad1.square) {
//                    intake.height(constants.intakeHeights.TWO);
//                }
//
//                if (gamepad1.circle) {
//                    intake.height(constants.intakeHeights.FOUR);
//                }
//
//                if (gamepad1.cross) {
//                    intake.height(constants.intakeHeights.FLOOR);
//                }
//
//                if (gamepad1.right_bumper) {
//                    intake.state(constants.intakeState.STOP);
//                }

//                if (gamepad1.right_trigger != 0) {
//                    intake.state(constants.intakeState.SUCK);
//                }
//
//                if (gamepad1.left_trigger != 0) {
//                    intake.state(constants.intakeState.SPIT);
//                }

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

                if (gamepad2.left_bumper) {
                    slides.preset(constants.slides.HANG);
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
                    outtake.move(constants.outtake.READY);
                }

                if (gamepad2.square) {
                    outtake.move(constants.outtake.MOVING);
                }

                if (gamepad2.circle) {
                    outtake.move(constants.outtake.AIM);
                }

                if (gamepad2.triangle) {
                    outtake.move(constants.outtake.SCORE);
                }


                telemetry.update();
            }
        }
    }
}
