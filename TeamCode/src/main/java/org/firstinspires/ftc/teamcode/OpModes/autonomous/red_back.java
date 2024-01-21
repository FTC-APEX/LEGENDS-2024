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
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.util.constantsAutonomous.redBack;
import org.firstinspires.ftc.teamcode.utility.OpenCV;

@Autonomous (name = "Red Back Auto w/ Cam")
public class red_back extends LinearOpMode {
    ElapsedTime runtime = new ElapsedTime();

    intake intake = new intake();
    outtake outtake = new outtake();
    slides slides = new slides();
    SampleMecanumDrive drive;
    kamera kamera = new kamera();

    Pose2d startPos = new Pose2d(-36, -60, Math.toRadians(startHeading));
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

        outtake.init(hardwareMap);

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
                    .lineToConstantHeading(new Vector2d(-26, -48))
                    .splineTo(new Vector2d(-40, -32), Math.toRadians(125))
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
                    .splineTo(new Vector2d(-24, -36), Math.toRadians(0))
                    .lineToConstantHeading((new Vector2d(0, -36)))
                    .splineTo(new Vector2d(48, -30), Math.toRadians(0))
                    .setReversed(false)
                    .build();
        }
        else if(zone == OpenCV.Pipeline.position.CENTER) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToConstantHeading(new Vector2d(-36, -34))
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                .setReversed(true)
                .splineTo(new Vector2d(-24, -36), Math.toRadians(0))
                    .lineToConstantHeading((new Vector2d(48, -36)))
                    //.lineToConstantHeading((new Vector2d(0, -36)))
                //.splineTo(new Vector2d(48, -36), Math.toRadians(0))
                .setReversed(false)
                .build();
        }
        else if (zone == OpenCV.Pipeline.position.RIGHT) {
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToConstantHeading(new Vector2d(-36, -48))
                    .splineTo(new Vector2d(-32, -32), Math.toRadians(55))
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                    .setReversed(true)
                    .splineTo(new Vector2d(-24, -36), Math.toRadians(0))
                    .lineToConstantHeading((new Vector2d(0, -36)))
                    .splineTo(new Vector2d(48, -42), Math.toRadians(0))
                    .setReversed(false)
                    .build();
        }
        else { //lucky 2
            PURPLE_CAM = drive.trajectorySequenceBuilder(startPos)
                    .lineToConstantHeading(new Vector2d(-36, -34))
                    .build();

            SCORE_YELLOW = drive.trajectorySequenceBuilder(PURPLE_CAM.end())
                .setReversed(true)
                .splineTo(new Vector2d(-24, -36), Math.toRadians(0))
                .lineToConstantHeading((new Vector2d(0, -36)))
                .splineTo(new Vector2d(48, -36), Math.toRadians(0))
                .setReversed(false)
                .build();
        }



        TrajectorySequence TO_STACK = drive.trajectorySequenceBuilder(SCORE_YELLOW.end())
                .splineTo(new Vector2d(0, -12), Math.toRadians(180))
                .lineToConstantHeading(new Vector2d(-60, -12))
                .build();

        TrajectorySequence TO_BACKBOARD = drive.trajectorySequenceBuilder(TO_STACK.end())
                .lineToConstantHeading(new Vector2d(0, -12))
                .splineTo(new Vector2d(48, -36), Math.toRadians(0))
                .build();

        TrajectorySequence PARK_LEFT = drive.trajectorySequenceBuilder(TO_BACKBOARD.end())
                .lineToConstantHeading(new Vector2d(48, -12))
                .lineToConstantHeading(new Vector2d(60, -60))
                .build();

        TrajectorySequence PARK_RIGHT = drive.trajectorySequenceBuilder(TO_BACKBOARD.end())
                .lineToConstantHeading(new Vector2d(48, -60))
                .lineToConstantHeading(new Vector2d(60, -60))
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
                        nextTraj(redBack.TO_BACKBOARD);
                    }
                    break;
                case TO_BACKBOARD:
                    if (!drive.isBusy()) {
                        drive.followTrajectorySequence(TO_BACKBOARD);
                        cycles++;
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
