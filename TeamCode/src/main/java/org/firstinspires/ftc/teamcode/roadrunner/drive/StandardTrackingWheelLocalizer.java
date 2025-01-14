package org.firstinspires.ftc.teamcode.roadrunner.drive;

import static org.firstinspires.ftc.teamcode.util.constantsRobot.FrontReversed;
import static org.firstinspires.ftc.teamcode.util.constantsRobot.LeftReversed;
import static org.firstinspires.ftc.teamcode.util.constantsRobot.RightReversed;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.roadrunner.util.Encoder;

import java.util.Arrays;
import java.util.List;

/*
 * Sample tracking wheel localizer implementation assuming the standard configuration:
 *
 *    /--------------\
 *    |     ____     |
 *    |     ----     |
 *    | ||        || |
 *    | ||        || |
 *    |              |
 *    |              |
 *    \--------------/
 *
 */
@Config
public class StandardTrackingWheelLocalizer extends ThreeTrackingWheelLocalizer {
    public static double TICKS_PER_REV = 8192;
    public static double WHEEL_RADIUS = 0.69; // in
    public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed

    public static double LATERAL_DISTANCE = 14.445; // in; distance between the left and right wheels
    public static double FORWARD_OFFSET = 3.39; // in; offset of the lateral wheel

    public static double X_MULTIPLIER = 1.002; //Run 1: 1.00516249299 -- Run 2: 1.00220081053 -- Run 3: 1.00121086187

    public static double Y_MULTIPLIER = 0.999; //Run 1: 0.99858012919 -- Run 2: 0.99946935315 -- Run 3: 0.99901306603

    private Encoder leftEncoder, rightEncoder, frontEncoder;

    public StandardTrackingWheelLocalizer(HardwareMap hardwareMap) {
        super(Arrays.asList(
                new Pose2d(0, LATERAL_DISTANCE / 2, Math.toRadians(0)), // left
                new Pose2d(0, -LATERAL_DISTANCE / 2, Math.toRadians(0)), // right
                new Pose2d(FORWARD_OFFSET, 0, Math.toRadians(90)) // front
        ));

        frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "frontLeft")); //c0
        rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "backRight")); //e1
        leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "backLeft")); //c1

        if(LeftReversed) leftEncoder.setDirection(Encoder.Direction.REVERSE);
        if(RightReversed) rightEncoder.setDirection(Encoder.Direction.REVERSE);
        if(FrontReversed) frontEncoder.setDirection(Encoder.Direction.REVERSE);

        // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
    }

    public static double encoderTicksToInches(double ticks) {
        return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
    }

    @NonNull
    @Override
    public List<Double> getWheelPositions() {
        return Arrays.asList(
                encoderTicksToInches(leftEncoder.getCurrentPosition()) * X_MULTIPLIER,
                encoderTicksToInches(rightEncoder.getCurrentPosition()) * X_MULTIPLIER,
                encoderTicksToInches(frontEncoder.getCurrentPosition()) * Y_MULTIPLIER
        );
    }


    @NonNull
    @Override
    public List<Double> getWheelVelocities() {
        // TODO: If your encoder velocity can exceed 32767 counts / second (such as the REV Through Bore and other
        //  competing magnetic encoders), change Encoder.getRawVelocity() to Encoder.getCorrectedVelocity() to enable a
        //  compensation method


        return Arrays.asList(
                encoderTicksToInches(leftEncoder.getCorrectedVelocity()) * X_MULTIPLIER,
                encoderTicksToInches(rightEncoder.getCorrectedVelocity()) * X_MULTIPLIER,
                encoderTicksToInches(frontEncoder.getCorrectedVelocity()) * Y_MULTIPLIER
        );
    }
}