package org.firstinspires.ftc.teamcode.OpModes.teleOp;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.util.PoseStorage;
import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.subsystems.shooter;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.subsystems.sensors.breakbeam;
import org.firstinspires.ftc.teamcode.util.constantsRobot;

@TeleOp
public class turkeyV2 extends LinearOpMode {
    static SampleMecanumDrive drive;
    intake intake = new intake();
    outtake outtake = new outtake();
    slides slides = new slides();
    shooter shooter = new shooter();
    breakbeam breakbeam = new breakbeam();
    private Vector2d translation;

    @Override
    public void runOpMode() {
        drive = new SampleMecanumDrive(hardwareMap);
        intake.init(hardwareMap);
        outtake.init(hardwareMap);
        slides.init(hardwareMap);
        shooter.init(hardwareMap);
        breakbeam.init(hardwareMap);

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
            //Drivetrain Code - Roadrunner SampleMecanumDrive.java
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

            //Intake States - Added Breakbeam
            if (gamepad1.right_trigger >= 0.3) {
                intake.setIntake(constantsRobot.intake.SUCK);
            }
            if (gamepad1.left_trigger >= 0.3) {
                intake.setIntake(constantsRobot.intake.SPIT);
            }
            if (gamepad1.left_bumper) {
                intake.setIntake(constantsRobot.intake.OFF);
            }


            //Slide States








            //Slide Commands

        }
    }
}

class SequentialCommands extends SequentialCommandGroup {
            public void retractAndReset(slides slides, outtake outtake) {
                addCommands(

                );
            }
    }

class ParallelCommands extends ParallelCommandGroup {

}