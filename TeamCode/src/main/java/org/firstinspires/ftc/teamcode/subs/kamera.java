package org.firstinspires.ftc.teamcode.subs;



import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.util.OpenCV;
import org.openftc.easyopencv.*;


public class kamera extends LinearOpMode{
    public OpenCvWebcam cam;
    public OpenCV.Pipeline pipeline = new OpenCV.Pipeline();


    public void init(HardwareMap hardwareMap) {


        telemetry  = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        int cameraMonitorViewId = hardwareMap
                .appContext
                .getResources()
                .getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        WebcamName camName = hardwareMap.get(WebcamName.class, "webcam");

        cam = OpenCvCameraFactory.getInstance().createWebcam(camName, cameraMonitorViewId);

        //Set Pipeline Here
        cam.setPipeline(pipeline);

        cam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                //cam.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                cam.startStreaming(1280, 720, OpenCvCameraRotation.UPRIGHT);
            }

            @Override
            public void onError(int errorCode) {
                telemetry.addData("Camera Initialization: ", errorCode);
                telemetry.update();
            }
        });

        FtcDashboard.getInstance().startCameraStream(cam, 0);
    }


    //Yet to Implement
    public OpenCV.Pipeline.position getZone() {
       return pipeline.getPosition();
    }

    //Do Not Use
    @Override
    public void runOpMode() throws InterruptedException {}

}
