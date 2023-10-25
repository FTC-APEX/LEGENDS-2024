package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;

@TeleOp(name = "tele")
public class tele extends LinearOpMode{
    //Input all subsystems & drivetrain
    SampleMecanumDrive drivetrain;

    @Override
    public void runOpMode() throws InterruptedException{
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        //Initialization of all Subsystems
        drivetrain = new SampleMecanumDrive(hardwareMap);


        waitForStart();
        while (!isStopRequested()) {
            drivetrain.update();
        }
    }
}
