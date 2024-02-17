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
import org.firstinspires.ftc.teamcode.subs.kamera;
import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.util.constantsAutonomous.redBack;
import org.firstinspires.ftc.teamcode.util.OpenCV;
import org.firstinspires.ftc.teamcode.util.constantsRobot;

@Autonomous (name = "Blue Back Auto w/ Cam")
public class blue_back extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();

    intake intake = new intake();
    outtake outtake = new outtake();
    slides slides = new slides();
    SampleMecanumDrive drive;
    kamera kamera = new kamera();

    Pose2d startPos = new Pose2d(15.625, -63.3125, Math.toRadians(90));
    OpenCV.Pipeline.position zone = OpenCV.Pipeline.position.UNKNOWN;
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

        if (zone == OpenCV.Pipeline.position.LEFT) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
//                    .lineToConstantHeading(new Vector2d(15.625, -33)) // y = -33 for jizz code
//                    .turn(Math.toRadians(90))
//                    .lineToConstantHeading(new Vector2d(14, -33))
                    .lineToLinearHeading(new Pose2d(15.625, -28, Math.toRadians(180)))
                    .lineToConstantHeading(new Vector2d(13, -28))
                    .addTemporalMarker(2.5, () -> {
                        intake.setIntake(constantsRobot.intake.SPIT);
                    })
                    .addTemporalMarker(2.545, () -> {
                        intake.setIntake(constantsRobot.intake.OFF);
                    })
                    .waitSeconds(1)
                    .build();
        }
        else if(zone == OpenCV.Pipeline.position.CENTER) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
//                    .lineToConstantHeading(new Vector2d(12, -36))
                    .lineToConstantHeading(new Vector2d(10, -36))
                    .addTemporalMarker(2.5, () -> {
                        intake.setIntake(constantsRobot.intake.SPIT);
                    })
                    .addTemporalMarker(2.545, () -> {
                        intake.setIntake(constantsRobot.intake.OFF);
                    })
                    .waitSeconds(1)
                    .build();
        }
        else if (zone == OpenCV.Pipeline.position.RIGHT) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToLinearHeading(new Pose2d(40, -28, Math.toRadians(180)))
                    .lineTo(new Vector2d(37, -28))
                    .addTemporalMarker(2.5, () -> {
                        intake.setIntake(constantsRobot.intake.SPIT);
                    })
                    .addTemporalMarker(2.545, () -> {
                        intake.setIntake(constantsRobot.intake.OFF);
                    })
                    .waitSeconds(1)
                    .build();
        }
        else { //lucky 2
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToConstantHeading(new Vector2d(12, -34))
                    .waitSeconds(2)
                    .build();
        }

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
