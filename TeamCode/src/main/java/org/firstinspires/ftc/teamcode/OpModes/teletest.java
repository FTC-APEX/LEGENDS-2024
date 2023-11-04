package org.firstinspires.ftc.teamcode.OpModes;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.roadrunner.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.util.environment;
import org.firstinspires.ftc.teamcode.util.internal;

import java.util.Objects;

@TeleOp(name = "KrishTestTele")
public class teletest extends LinearOpMode {
    SampleMecanumDrive drive;

    slides slides = new slides();;

    environment environment = new environment();

    internal internal = new internal();

    String alliance;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        //drive = new SampleMecanumDrive(hardwareMap);
        //slides.init(hardwareMap);
        environment.init(hardwareMap);
        internal.init(hardwareMap);

        waitForStart();
        while (!isStopRequested()) {
            while (opModeIsActive()) {
                telemetry.addData("Tape ARGB: ", environment.getTapeColorDataRaw());
                telemetry.addData("Tape Color: ", environment.getTapeColor());
                telemetry.addData("Heading: ", internal.robotHeading());
                telemetry.addData("Current Alliance: ", alliance);



                if (gamepad1.options && gamepad1.triangle) {
                    alliance = "Blue";
                }

                if (gamepad1.options && gamepad1.square) {
                    alliance = "Red";
                }

                if (Objects.equals(environment.getTapeColor(), alliance)) {
                    telemetry.addLine("Robot Stopped");
                }

                telemetry.update();

            }
        }

    }


}
