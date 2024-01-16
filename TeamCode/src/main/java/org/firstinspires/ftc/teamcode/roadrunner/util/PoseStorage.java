package org.firstinspires.ftc.teamcode.roadrunner.util;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;

public class PoseStorage {
    // See this static keyword? That's what lets us share the data between opmodes.
    public static Pose2d currentPose = new Pose2d();
}