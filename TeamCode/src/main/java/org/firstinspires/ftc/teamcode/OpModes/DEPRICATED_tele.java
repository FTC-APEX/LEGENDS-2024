package org.firstinspires.ftc.teamcode.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "tele")
public class DEPRICATED_tele extends LinearOpMode {
    //Input all subsystems & drivetrain
//    SampleMecanumDrive drivetrain;
//    slides Slides;
//    outake Clamp;
//    intake Sweep;
//    shooter Shooter;
//    hang Hanger;
    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.REVERSE);


//        //Initialization of all Subsystems
//        drivetrain = new SampleMecanumDrive(hardwareMap);
//        //mainSlides = new Slides();
//        Clamp = new outake();
//        Sweep = new intake();
//        Shooter = new shooter();
//        Hanger = new hang();
//        Servo Claw = null;
//        Servo Shooter = null;\

//
//        Claw = hardwareMap.get(Servo.class, "Claw");
//        Shooter = hardwareMap.get(Servo.class, "Shooter");

        waitForStart();
        while (!isStopRequested()) {
            while (opModeIsActive()) {
                telemetry.addData("Robot", "Initialized");
                telemetry.addData("Front Left: ", frontLeft.getCurrentPosition());
                telemetry.addData("Front Right: ", frontRight.getCurrentPosition());
                telemetry.addData("Back Left: ", backLeft.getCurrentPosition());
                telemetry.addData("Back Right: ", backRight.getCurrentPosition());

                    if (Math.abs(gamepad1.left_stick_y) > 0.2 || Math.abs(gamepad1.left_stick_x) > 0.2 || Math.abs(gamepad1.right_stick_x) > 0.2) {
                        frontLeft.setPower(((gamepad1.left_stick_y - gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                        frontRight.setPower(((gamepad1.left_stick_y * -1 - gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                        backRight.setPower(((gamepad1.left_stick_y + gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                        backLeft.setPower(((gamepad1.left_stick_y * -1 + gamepad1.right_stick_x * 1.22) - gamepad1.left_stick_x) / 1.2);
                    } else {
                        frontLeft.setPower(0);
                        frontRight.setPower(0);
                        backRight.setPower(0);
                        backLeft.setPower(0);
                    }

                    if (gamepad1.dpad_up) { //dpad slow movement
                        frontLeft.setPower(-0.3);
                        frontRight.setPower(0.3);
                        backRight.setPower(-0.3);
                        backLeft.setPower(0.3);
                    }
                    if (gamepad1.dpad_right) {
                        frontLeft.setPower(-0.5);
                        frontRight.setPower(-0.5);
                        backRight.setPower(-0.5);
                        backLeft.setPower(-0.5);
                    }
                    if (gamepad1.dpad_left) {
                        frontLeft.setPower(0.5);
                        frontRight.setPower(0.5);
                        backRight.setPower(0.5);
                        backLeft.setPower(0.5);
                    }
                    if (gamepad1.dpad_down) {
                        frontLeft.setPower(0.3);
                        frontRight.setPower(-0.3);
                        backRight.setPower(0.3);
                        backLeft.setPower(-0.3);
                    }


                    telemetry.update();

//                drivetrain.update();
            }
        }
    }
}
