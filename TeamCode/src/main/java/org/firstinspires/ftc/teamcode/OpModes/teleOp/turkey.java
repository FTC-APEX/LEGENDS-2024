package org.firstinspires.ftc.teamcode.OpModes.teleOp;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subs.intake;
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.subsystems.shooter;
import org.firstinspires.ftc.teamcode.roadrunner.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.constantsRobot;
import org.firstinspires.ftc.teamcode.utility.constants;


@TeleOp(name = "Turkey")
public class turkey extends LinearOpMode{
    //Subsystems
    static SampleMecanumDrive drive;
    intake intake;
    outtake outtake;
    slides slides;
    shooter shooter;
    private Vector2d translation;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        drive = new SampleMecanumDrive(hardwareMap);
        intake = new intake();
        outtake = new outtake();
        slides = new slides();
        shooter = new shooter();
        intake.init(hardwareMap);
        outtake.init(hardwareMap);
        slides.init(hardwareMap);
        shooter.init(hardwareMap);


        drive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        drive.setPoseEstimate(PoseStorage.currentPose);
        Pose2d poseEstimate = drive.getPoseEstimate();

        while (opModeInInit()) {
            telemetry.addLine("Robot Initialized");
            telemetry.update();
            shooter.ShooterLoad();
        }

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {
            drive.update();
            Vector2d input = new Vector2d(
                    gamepad1.left_stick_y,
                    gamepad1.left_stick_x
            ).rotated(-poseEstimate.getHeading());

            drive.setWeightedDrivePower(
                    new Pose2d(
                            input.getX(),
                            input.getY(),
                            -gamepad1.right_stick_x
                    )
            );

            if (gamepad1.right_trigger < 0.3 | gamepad1.left_trigger < 0.3) {
                    intake.state(constants.intakeState.STOP);
            }
            if (gamepad1.right_trigger >= 0.3) {
                    intake.state(constants.intakeState.SUCK);
            }
            if (gamepad1.left_trigger >= 0.3) {
                    intake.state(constants.intakeState.SPIT);
            }

            if (gamepad2.cross) {
                outtake.setState(constantsRobot.outtake.READY);
            }
            if (gamepad2.square) {
                outtake.setState(constantsRobot.outtake.MOVING);
            }
            if (gamepad2.circle) {
                outtake.setState(constantsRobot.outtake.AIM);
            }
            if (gamepad2.triangle) {
                outtake.setState(constantsRobot.outtake.SCORE);
            }

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

            if (gamepad2.right_trigger > 0.3){
                outtake.openBlocker();
            }

            if (gamepad2.left_trigger > 0.3){
                outtake.closeBlocker();
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

            if (gamepad2.share) {
                shooter.ShooterRelease();
            }

            if (gamepad2.options) {
                shooter.ShooterLoad();
            }

            telemetry.addData("x", poseEstimate.getX());
            telemetry.addData("y", poseEstimate.getY());
            telemetry.addData("Left Stick X:", gamepad1.left_stick_x);
            telemetry.addData("Left Stick Y:", gamepad1.left_stick_y);
            telemetry.addData("Right Stick X:", gamepad1.right_stick_x);
            telemetry.addData("Right Stick Y:", gamepad1.right_stick_y);
            telemetry.addData("Right Bumper", gamepad1.right_bumper);
            telemetry.addData("Left Bumper:", gamepad1.left_bumper);
            telemetry.addData("Left Trigger:", gamepad1.left_stick_x);
            telemetry.addData("Slide Current Height", slides.getCurrentHeight());
            telemetry.addData("heading", poseEstimate.getHeading());
            telemetry.addLine("testing");
            telemetry.update();
        }
    }


    }





