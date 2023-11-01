package org.firstinspires.ftc.teamcode.OpModes.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.environment;

@TeleOp (name = "Environment Testing OpMode")
public class environmentTesting extends LinearOpMode {

    environment environment = new environment();

    @Override
    public void runOpMode() throws InterruptedException{
        environment.init(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        waitForStart();
        while (!isStopRequested()) {
            while (opModeIsActive()) {
                telemetry.addData("Tape ARGB: ", environment.getTapeColorDataRaw());
                telemetry.addData("Tape Color: ", environment.getTapeColor());
                telemetry.update();
            }
        }
    }
}
