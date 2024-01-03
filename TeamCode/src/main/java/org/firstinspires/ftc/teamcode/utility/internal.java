package org.firstinspires.ftc.teamcode.utility;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class internal {
    double robotHeading = 0;
    double headingOffset = 0;

    double headingError = 0;

    IMU.Parameters parameters;
    IMU imu;

    public void init(HardwareMap hardwareMap) {
        imu = hardwareMap.get(BHI260IMU.class, "imu");
        parameters = new IMU.Parameters(
                new RevHubOrientationOnRobot(new Orientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES, 0, 0, 0, 0))
        );
        imu.initialize(parameters);
    }

    private double getHeading() {
        YawPitchRollAngles RobotOrientation = imu.getRobotYawPitchRollAngles();
        return RobotOrientation.getYaw(AngleUnit.DEGREES);
    }

    public double robotHeading() {
        return getHeading();
    }
}
