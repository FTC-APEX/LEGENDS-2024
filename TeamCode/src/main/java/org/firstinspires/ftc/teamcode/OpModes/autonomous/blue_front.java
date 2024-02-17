package org.firstinspires.ftc.teamcode.OpModes.autonomous;

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
import org.firstinspires.ftc.teamcode.subsystems.kameraBLUE;
import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.util.OpenCVBLUE;
import org.firstinspires.ftc.teamcode.util.constantsAutonomous.redBack;
import org.firstinspires.ftc.teamcode.util.constantsRobot;

@Autonomous (name = "Blue Front Auto w/ Cam")
public class blue_front extends LinearOpMode {
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
                    .addTemporalMarker(2.25, ()-> {
                        outtake.openBlocker();
                    })
                    .waitSeconds(1)
                    .build();
        }
        else if(zone == OpenCVBLUE.Pipeline.position.CENTER) {
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
                    .addTemporalMarker(1.5, ()-> {
                        outtake.setState(constantsRobot.outtake.AIM);
                    })
                    .addTemporalMarker(2.25, ()-> {
                        outtake.openBlocker();
                    })
                    .waitSeconds(2)
                    .build();
        }
        else if (zone == OpenCVBLUE.Pipeline.position.RIGHT) {
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
                    .addTemporalMarker(2.25, ()-> {
                        outtake.openBlocker();
                    })
                    .waitSeconds(2)
                    .build();
        }
        else { //lucky 2
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



        TrajectorySequence TO_STACK = drive.trajectorySequenceBuilder(SCORE_YELLOW.end())
                .splineTo(new Vector2d(18, 14), Math.toRadians(180))
                .splineTo(new Vector2d(-55, 14), Math.toRadians(180)) // add -48 to the x value (currently at 5 tile length for testing
                .addDisplacementMarker(0.5, () -> {
                    outtake.setState(constantsRobot.outtake.READY);
                    outtake.openBlocker();
                    slides.preset(constantsRobot.slides.READY);
                    //intake.setIntake(constantsRobot.intake.SUCK);
                })
                .build();

        TrajectorySequence BURST = drive.trajectorySequenceBuilder(TO_STACK.end())
                .waitSeconds(1.5)
                .lineToConstantHeading(new Vector2d(-50, 12))// add -24 to the x value (currently at 6 tile length for testing
                .lineToConstantHeading(new Vector2d(-56, 12))// add -24 to the x value (currently at 6 tile length for testing
                .waitSeconds(1)
                .lineToConstantHeading(new Vector2d(-50, 12))// add -24 to the x value (currently at 6 tile length for testing
                .lineToConstantHeading(new Vector2d(-56, 12))// add -24 to the x value (currently at 6 tile length for testing
                .waitSeconds(1)
                .addTemporalMarker(0.5, () -> {
                    intake.setIntake(constantsRobot.intake.CONTROLLED);
                })
                .addTemporalMarker(0.8, () -> {
                    intake.setIntake(constantsRobot.intake.OFF);
                })
                .addTemporalMarker(1, () -> {
                    intake.setIntake(constantsRobot.intake.CONTROLLED);
                })
                .addTemporalMarker(1.3, () -> {
                    intake.setIntake(constantsRobot.intake.OFF);
                })
                .addTemporalMarker(1.5, () -> {
                    intake.setIntake(constantsRobot.intake.SUCK);
                })
                .addTemporalMarker(2.4, () -> {
                    intake.setIntake(constantsRobot.intake.SPIT);
                })
                .addTemporalMarker(2.6, () -> {
                    intake.setIntake(constantsRobot.intake.SUCK);
                })
                .build();

        TrajectorySequence TO_BACKBOARD = drive.trajectorySequenceBuilder(BURST.end())
                .lineToConstantHeading(new Vector2d(18, 12))
                .splineTo(new Vector2d(53, 42), Math.toRadians(0))
                .addTemporalMarker(0, () -> {
                    intake.setIntake(constantsRobot.intake.SPIT);
                    outtake.closeBlocker();
                })
                .addTemporalMarker(3, () -> {
                    intake.setIntake(constantsRobot.intake.OFF);
                    slides.preset(constantsRobot.slides.SECOND);
                })
                .addTemporalMarker(4, () -> {
                    outtake.setState(constantsRobot.outtake.AIM);
                })
                .addTemporalMarker(4.5, ()-> {
                    outtake.openBlocker();
                })
                .waitSeconds(2)
                .build();

        TrajectorySequence RESET = drive.trajectorySequenceBuilder(TO_BACKBOARD.end())
                .addTemporalMarker(0, () -> {
                    outtake.openBlocker();
                    outtake.setState(constantsRobot.outtake.READY);
                    slides.preset(constantsRobot.slides.READY);
                })
                .waitSeconds(3)
                .build();

        TrajectorySequence PARK_LEFT = drive.trajectorySequenceBuilder(TO_BACKBOARD.end())
                .lineToConstantHeading(new Vector2d(48, 12))
                .lineToConstantHeading(new Vector2d(60, 12))
                .addTemporalMarker(0, () -> {
                    outtake.setState(constantsRobot.outtake.READY);
                    slides.preset(constantsRobot.slides.READY);
                })
                .build();

        TrajectorySequence PARK_RIGHT = drive.trajectorySequenceBuilder(TO_BACKBOARD.end())
                .lineToConstantHeading(new Vector2d(48, 60))
                .lineToConstantHeading(new Vector2d(60, 60))
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
                        nextTraj(redBack.TO_STACK);
                    }
                    break;
                case TO_STACK:
                    if (!drive.isBusy()) {
                        drive.followTrajectorySequence(TO_STACK);
                        nextTraj(redBack.BURST);
                    }
                    break;
                case BURST:
                    if (!drive.isBusy()) {
                        drive.followTrajectorySequence(BURST);
                        nextTraj(redBack.TO_BACKBOARD);
                    }
                case TO_BACKBOARD:
                    if (!drive.isBusy()) {
                        drive.followTrajectorySequence(TO_BACKBOARD);
                        cycles++;
                        nextTraj(redBack.RESET);
                    }
                    break;
                case RESET:
                    if (!drive.isBusy()) {
                        drive.followTrajectorySequence(RESET);
                        nextTraj(redBack.END);
                    }
                case PARK_LEFT:
                    if (!drive.isBusy()) {
                        drive.followTrajectorySequence(PARK_LEFT);
                        nextTraj(redBack.END);
                    }
                    break;
                case PARK_RIGHT:
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
