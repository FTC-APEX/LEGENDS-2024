package org.firstinspires.ftc.teamcode.OpModes.testing;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subs.kamera;

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
            telemetry.addData("Avg LR:", kamera.pipeline.avgLR);
            telemetry.addData("Avg LB:", kamera.pipeline.avgLB);
            telemetry.addLine("");
            telemetry.addData("Avg CR:", kamera.pipeline.avgCR);
            telemetry.addData("Avg CB:", kamera.pipeline.avgCB);
            telemetry.addLine("");
            telemetry.addData("Avg RR:", kamera.pipeline.avgRR);
            telemetry.addData("Avg RB:", kamera.pipeline.avgRB);
            telemetry.update();

            sleep(50);
        }

    }
}
