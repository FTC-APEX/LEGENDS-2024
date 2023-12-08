package org.firstinspires.ftc.teamcode.OpModes.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.kamera;

@Autonomous (name = "Kamera Testing")
public class KAMERAtest extends LinearOpMode {
    kamera kamera = new kamera();
    @Override
    public void runOpMode() {
        kamera.init(hardwareMap);
        telemetry  = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();


        while (opModeIsActive()) {
            telemetry.addData("Position: ", kamera.getZone());
            //telemetry.addData("Avg L:", kamera.pipeline.avgL);
            //telemetry.addData("Avg C:", kamera.pipeline.avgC);
            //telemetry.addData("Avg R:", kamera.pipeline.avgR);
            telemetry.update();

            sleep(50);
        }

    }
}
