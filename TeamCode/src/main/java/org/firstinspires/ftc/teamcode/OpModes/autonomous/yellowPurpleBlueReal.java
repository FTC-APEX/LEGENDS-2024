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
import org.firstinspires.ftc.teamcode.util.constantsRobot;
import org.firstinspires.ftc.teamcode.util.OpenCV;
import org.firstinspires.ftc.teamcode.utility.constants;

@Autonomous (name = "Yellow Purple Blue -- Real")
public class yellowPurpleBlueReal extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();

    intake intake = new intake();
    outtake outtake = new outtake();
    slides slides = new slides();
    SampleMecanumDrive drive;
    kamera kamera = new kamera();

    Pose2d startPos = new Pose2d(-36, 60, Math.toRadians(90));
    OpenCV.Pipeline.position zone = OpenCV.Pipeline.position.UNKNOWN;
    int cycles = 0;
    boolean purple = false;
    boolean yellow = true;
    redBack current = redBack.IDLE;
    redBack next = redBack.PURPLE_CAM;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        drive = new SampleMecanumDrive(hardwareMap);
        intake.init(hardwareMap);

        while (opModeInInit()) {
            drive.setPoseEstimate(startPos);
            zone = kamera.getZone();
            telemetry.addLine("AUTONOMOUS READY...");
            telemetry.addData("Parking Zone:", zone);
            telemetry.update();
        }

        waitForStart();
        TrajectorySequence PURPLE_CAM;
        TrajectorySequence SCORE_YELLOW;

        if (zone == OpenCV.Pipeline.position.LEFT) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToConstantHeading(new Vector2d(-26, 48))
                    .splineTo(new Vector2d(-40, 32), Math.toRadians(125))
                    .addTemporalMarker(2, () ->
                            intake.setIntake(constantsRobot.intake.SPIT))
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
                    .splineTo(new Vector2d(-24, 36), Math.toRadians(0))
                    .lineToConstantHeading((new Vector2d(0, 36)))
                    //add yellow scoring tape here
                    .splineTo(new Vector2d(48, 30), Math.toRadians(0))
                    .setReversed(false)
                    .build();
        }
        else if(zone == OpenCV.Pipeline.position.CENTER) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToConstantHeading(new Vector2d(-36, 34))
                    .addTemporalMarker(2, () ->
                            intake.setIntake(constantsRobot.intake.SPIT))
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
                    .splineTo(new Vector2d(-24, 36), Math.toRadians(0))
                    .lineToConstantHeading((new Vector2d(0, 36)))
                    .splineTo(new Vector2d(48, 36), Math.toRadians(0))
                    .setReversed(false)
                    .build();
        }
        else if (zone == OpenCV.Pipeline.position.RIGHT) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToConstantHeading(new Vector2d(-36, 48))
                    .splineTo(new Vector2d(-32, 32), Math.toRadians(55))
                    .addTemporalMarker(2, () ->
                            intake.setIntake(constantsRobot.intake.SPIT))
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
                    .splineTo(new Vector2d(-24, 36), Math.toRadians(0))
                    .lineToConstantHeading((new Vector2d(0, 36)))
                    .splineTo(new Vector2d(48, 42), Math.toRadians(0))
                    .setReversed(false)
                    .build();
        }
        else { //lucky 2
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToConstantHeading(new Vector2d(-36, 10))
                    .addTemporalMarker(2, () ->
                            intake.setIntake(constantsRobot.intake.SPIT))
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
                    .splineTo(new Vector2d(-24, 36), Math.toRadians(0))
                    .lineToConstantHeading((new Vector2d(0, 36)))
                    .splineTo(new Vector2d(48, 36), Math.toRadians(0))
                    .setReversed(false)
                    .build();
        }

        TrajectorySequence PARK_LEFT = drive.trajectorySequenceBuilder(SCORE_YELLOW.end())
                .lineToConstantHeading(new Vector2d(48, 12))
                .lineToConstantHeading(new Vector2d(60, 60))
                .build();

        TrajectorySequence PARK_RIGHT = drive.trajectorySequenceBuilder(SCORE_YELLOW.end())
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
                    if (!drive.isBusy() && cycles == 2) {
                        drive.followTrajectorySequence(PARK_LEFT);
                        nextTraj(redBack.END);
                    }
                    break;
                case PARK_RIGHT:
                    if (!drive.isBusy() && cycles == 2) {
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