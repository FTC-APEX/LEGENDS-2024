package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.subsystems.hang;
import org.firstinspires.ftc.teamcode.subsystems.shooter;
import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.outake;

@TeleOp(name = "tele")
public class tele extends LinearOpMode{
    //Input all subsystems & drivetrain
    SampleMecanumDrive drivetrain;
    slides Slides;
    outake Clamp;
    intake Sweep;
    shooter Shooter;
    hang Hanger;





    @Override
    public void runOpMode() throws InterruptedException{
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        //Initialization of all Subsystems
        drivetrain = new SampleMecanumDrive(hardwareMap);
        //mainSlides = new Slides();
        Clamp = new outake();
        Sweep = new intake();
        Shooter = new shooter();
        Hanger = new hang();
        Servo Claw = null;

        Claw = hardwareMap.get(Servo.class, "Claw");

        waitForStart();
        while (!isStopRequested()) {
            while (opModeIsActive()){
                telemetry.addData("Robot", "Initialized");



                telemetry.update();
            }
            drivetrain.update();
        }
    }
}
