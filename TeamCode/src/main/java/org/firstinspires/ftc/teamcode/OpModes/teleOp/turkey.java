package org.firstinspires.ftc.teamcode.OpModes.teleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.subsystems.shooter;
import org.firstinspires.ftc.teamcode.roadrunner.util.PoseStorage;


@TeleOp(name = "Turkey")
public class turkey extends LinearOpMode{
    //Subsystems
    SampleMecanumDrive driveTrain;
    //intake intake;
    //outtake outtake;
    //slides slides;
    //shooter shooter;
    private Vector2d translation;

    @Override
    public void runOpMode() {
        driveTrain = new SampleMecanumDrive(hardwareMap);


        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        drive.setPoseEstimate(PoseStorage.currentPose);
        Pose2d poseEstimate = drive
                .getPoseEstimate();

        while (opModeInInit()) {
            telemetry.addLine("Robot Initialized");
            telemetry.update();
        }

        waitForStart();
                while (opModeIsActive() && !isStopRequested()) {
                    Vector2d input = new Vector2d(
                            -gamepad1.left_stick_y,
                            -gamepad1.left_stick_x
                    ).rotated(-poseEstimate.getHeading());

                    drive.setWeightedDrivePower(
                            new Pose2d(
                                    input.getX(),
                                    input.getY(),
                                    -gamepad1.right_stick_x
                            )
                    );

                    drive.update();

                    telemetry.addData("x", poseEstimate.getX());
                    telemetry.addData("y", poseEstimate.getY());
                    telemetry.addData("heading", poseEstimate.getHeading());
                    telemetry.update();
                }
            }
    }





