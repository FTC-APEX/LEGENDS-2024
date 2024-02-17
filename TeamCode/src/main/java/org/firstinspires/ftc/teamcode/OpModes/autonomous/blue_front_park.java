package org.firstinspires.ftc.teamcode.OpModes.autonomous;

import static org.firstinspires.ftc.teamcode.util.constantsRobot.startHeading;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.roadrunner.util.PoseStorage;
import org.firstinspires.ftc.teamcode.subs.kamera;
import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.kameraBLUE;
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.util.OpenCVBLUE;
import org.firstinspires.ftc.teamcode.util.constantsAutonomous.redBack;
import org.firstinspires.ftc.teamcode.util.OpenCV;
import org.firstinspires.ftc.teamcode.util.constantsRobot;

@Autonomous (name = "Blue Front w/ Parking ")
public class blue_front_park extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();

    intake intake = new intake();
    outtake outtake = new outtake();
    slides slides = new slides();
    SampleMecanumDrive drive;
    kameraBLUE kamera = new kameraBLUE();

    Pose2d startPos = new Pose2d(15.625, 62.5, Math.toRadians(270));
    OpenCVBLUE.Pipeline.position zone = OpenCVBLUE.Pipeline.position.UNKNOWN;
    int cycles = 0;
    boolean purple = false;
    boolean yellow = true;
    redBack current = redBack.IDLE; // IDLE
    redBack next = redBack.PURPLE_CAM; // PURPLE_CAM

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        drive = new SampleMecanumDrive(hardwareMap);

        kamera.init(hardwareMap);

        outtake.init(hardwareMap);

        slides.init(hardwareMap);

        intake.init(hardwareMap);

        while (opModeInInit()) {
            drive.setPoseEstimate(startPos);
            zone = kamera.getZone();
            telemetry.addLine("AUTONOMOUS READY...");
            telemetry.addData("Parking Zone:", zone);
            telemetry.update();
            outtake.closeBlocker();
        }

        waitForStart();
        TrajectorySequence PURPLE_CAM;
        TrajectorySequence SCORE_YELLOW;

        if (zone == OpenCVBLUE.Pipeline.position.LEFT) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToLinearHeading(new Pose2d(40, 28, Math.toRadians(180)))
                    .lineTo(new Vector2d(37, 28))
                    .addTemporalMarker(2.5, () -> {
                        intake.setIntake(constantsRobot.intake.SPIT);
                    })
                    .addTemporalMarker(2.545, () -> {
                        intake.setIntake(constantsRobot.intake.OFF);
                    })
                    .waitSeconds(1)
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
//                    .lineToConstantHeading(new Vector2d(24, -33))
//                    .splineTo(new Vector2d(53, -27), Math.toRadians(0))
                    .lineToConstantHeading(new Vector2d(56.5, 39))
                    .setReversed(false)
                    .addTemporalMarker(1, () -> {
                        intake.setIntake(constantsRobot.intake.OFF);
                    })
                    .addDisplacementMarker(0.5, () -> {
                        slides.preset(constantsRobot.slides.FIRST);
                    })
                    .addTemporalMarker(1.5, () -> {
                        outtake.setState(constantsRobot.outtake.AIM);
                    })
                    .addTemporalMarker(2.25, () -> {
                        outtake.openBlocker();
                    })
                    .waitSeconds(1)
                    .build();
        } else if (zone == OpenCVBLUE.Pipeline.position.CENTER) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
//                    .lineToConstantHeading(new Vector2d(12, -36))
                    .lineToConstantHeading(new Vector2d(8, 36))
                    .addTemporalMarker(2.5, () -> {
                        intake.setIntake(constantsRobot.intake.SPIT);
                    })
                    .addTemporalMarker(2.545, () -> {
                        intake.setIntake(constantsRobot.intake.OFF);
                    })
                    .waitSeconds(1)
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
                    .lineToLinearHeading(new Pose2d(24, 38, Math.toRadians(180)))
                    .lineToConstantHeading((new Vector2d(56.5, 33)))
                    .setReversed(false)
                    .addTemporalMarker(0, () -> {
                        intake.setIntake(constantsRobot.intake.OFF);
                    })
                    .addDisplacementMarker(0.5, () -> {
                        slides.preset(constantsRobot.slides.FIRST);
                    })
                    .addTemporalMarker(1.5, () -> {
                        outtake.setState(constantsRobot.outtake.AIM);
                    })
                    .addTemporalMarker(2.25, () -> {
                        outtake.openBlocker();
                    })
                    .waitSeconds(2)
                    .build();
        } else if (zone == OpenCVBLUE.Pipeline.position.RIGHT) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToLinearHeading(new Pose2d(15.625, 28, Math.toRadians(180)))
                    .lineToConstantHeading(new Vector2d(13, 28))
                    .addTemporalMarker(2.5, () -> {
                        intake.setIntake(constantsRobot.intake.SPIT);
                    })
                    .addTemporalMarker(2.545, () -> {
                        intake.setIntake(constantsRobot.intake.OFF);
                    })
                    .waitSeconds(1)
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
                    .lineToLinearHeading(new Pose2d(56.5, 27, Math.toRadians(180)))
                    .setReversed(false)
                    .addTemporalMarker(0, () -> {
                        intake.setIntake(constantsRobot.intake.OFF);
                    })
                    .addDisplacementMarker(0.5, () -> {
                        slides.preset(constantsRobot.slides.FIRST);
                    })
                    .addTemporalMarker(1.5, () -> {
                        outtake.setState(constantsRobot.outtake.AIM);
                    })
                    .addTemporalMarker(2.25, () -> {
                        outtake.openBlocker();
                    })
                    .waitSeconds(2)
                    .build();
        } else { //lucky 2
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToConstantHeading(new Vector2d(12, 34))
                    .waitSeconds(2)
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
                    .splineTo(new Vector2d(24, 33), Math.toRadians(0))
                    .lineToConstantHeading((new Vector2d(58, 33)))
                    .setReversed(false)
//                    .addSpatialMarker(new Vector2d(36, -36), () -> {
//                        slides.preset(constantsRobot.slides.FIRST);
//                    })
//                    .addSpatialMarker(new Vector2d(48, -36), () -> {
//                        outtake.setState(constantsRobot.outtake.AIM);
//                    })
//                    .addSpatialMarker(new Vector2d(58, -27), () -> {
//                        outtake.openBlocker();
//                    })
                    .build();
        }


        TrajectorySequence PARK_RIGHT = drive.trajectorySequenceBuilder(SCORE_YELLOW.end())
                .lineToConstantHeading(new Vector2d(48, 12))
                .lineToConstantHeading(new Vector2d(60, 12))
                .addTemporalMarker(0, () -> {
                    outtake.setState(constantsRobot.outtake.READY);
                    slides.preset(constantsRobot.slides.READY);
                })
                .build();


        while (!isStopRequested() && opModeIsActive()) {
            telemetry.addData("Zone: ", zone);
            telemetry.addLine("");
            telemetry.addData("Robot Position: ", drive.getPoseEstimate());
            telemetry.addLine("");
            telemetry.addData("Current Trajectory: ", current);
            telemetry.addData("Next Trajectory: ", next);
            telemetry.addLine("");
            telemetry.addData("Purple Scored: ", purple);
            telemetry.addData("Yellow Scored:", yellow);
            telemetry.addData("Cycle #", cycles);
            telemetry.update();

            switch (current) {
                case IDLE:
                    if (!drive.isBusy()) {
                        nextTraj(redBack.PURPLE_CAM);
                    }
                    break;
                case PURPLE_CAM:
                    if (!drive.isBusy()) {
                        drive.followTrajectorySequence(PURPLE_CAM);
                        nextTraj(redBack.SCORE_YELLOW);
                    }
                    break;
                case SCORE_YELLOW:
                    if (!drive.isBusy()) {
                        drive.followTrajectorySequence(SCORE_YELLOW);
                        nextTraj(redBack.PARK_LEFT);
                    }
                    break;
                case PARK_LEFT:
                    if (!drive.isBusy()) {
                        drive.followTrajectorySequence(PARK_RIGHT);
                        nextTraj(redBack.END);
                    }
                    break;
                case END:
                    PoseStorage.currentPose = drive.getPoseEstimate();
                    break;


            }
        }
    }

        private void nextTraj(redBack ts) {
            double time = runtime.seconds();
            current = ts;
            telemetry.update();


        }

    }
