package org.firstinspires.ftc.teamcode.OpModes.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.subsystems.shooter;


@TeleOp(name = "Turkey")
public class turkey extends LinearOpMode{
    //Subsystems
    SampleMecanumDrive drivetrain;
    intake intake;
    outtake outtake;
    slides slides;
    shooter shooter;

    @Override
    public void runOpMode() {

        while (opModeInInit()) {

        }

        waitForStart();
            while (!isStopRequested()) {
                while (opModeIsActive()) {
                }
            }
    }

}




