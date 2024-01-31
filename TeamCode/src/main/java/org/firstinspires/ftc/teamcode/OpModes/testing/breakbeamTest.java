package org.firstinspires.ftc.teamcode.OpModes.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.sensors.breakbeam;

@TeleOp
public class breakbeamTest extends LinearOpMode{

    breakbeam breakbeam = new breakbeam();
    boolean state;
    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        breakbeam.init(hardwareMap);

        while (opModeInInit()) {
            telemetry.addLine("OpMode Initialized... Waiting for Start");
            telemetry.update();
        }

        while (opModeIsActive() & !isStopRequested()) {
            state = breakbeam.getState();
            telemetry.addData("IsBroken: ", state);
            telemetry.update();
        }
    }
}
