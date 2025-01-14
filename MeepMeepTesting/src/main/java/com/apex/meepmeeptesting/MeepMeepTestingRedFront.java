package com.apex.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTestingRedFront {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity Bot = new DefaultBotBuilder(meepMeep) //red side near stage
                // Assume starting
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(52, 52, Math.toRadians(120), Math.toRadians(120), 14.6)
                .setDimensions(16.5, 17.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(15.625, -63.3125, Math.toRadians(90)))
                                //Initial Movement

                                //zone2
//                                .lineToConstantHeading(new Vector2d(12, -34))
//
//                                .setReversed(true)
//                                .splineTo(new Vector2d(24, -36), Math.toRadians(0))
//                                .lineToConstantHeading(new Vector2d(48, -36))
//                                .setReversed(false)
//                                .waitSeconds(2)
//
//
//                                .splineTo(new Vector2d(0, -12), Math.toRadians(180))
//
//                                .lineToConstantHeading(new Vector2d(-60, -12))
//
//                                .waitSeconds(1)
//                                .lineToConstantHeading(new Vector2d(0, -12))
//                                .splineTo(new Vector2d(48, -36), Math.toRadians(0))
//
//                                .waitSeconds(1)
//
//                                .splineTo(new Vector2d(0, -12), Math.toRadians(180))
//
//                                .lineToConstantHeading(new Vector2d(-60, -12))
//
//                                .waitSeconds(1)
//                                .lineToConstantHeading(new Vector2d(0, -12))
//                                .splineTo(new Vector2d(48, -36), Math.toRadians(0))
//
//                                .waitSeconds(1)
//
//                                //park left
//                                //.lineToConstantHeading(new Vector2d(48, -12))
//                                //.lineToConstantHeading(new Vector2d(60, -12))
//
//                                //park right
//                                .lineToConstantHeading(new Vector2d(48, -60))
//                                .lineToConstantHeading(new Vector2d(60, -60))


                                .lineToConstantHeading(new Vector2d(15.625, -48))
                                .splineTo(new Vector2d(12, -30), Math.toRadians(145))
                                .waitSeconds(1)

                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(53, -27, Math.toRadians(180)))
                                //.splineTo(new Vector2d(53, -27), Math.toRadians(0))
                                .setReversed(false)
                                .waitSeconds(1)

                                .splineTo(new Vector2d(18, -10), Math.toRadians(180))
                                .splineTo(new Vector2d(-55, -12), Math.toRadians(180))

                                .waitSeconds(1.5)
                                .lineToConstantHeading(new Vector2d(-50, -12))// add -24 to the x value (currently at 5 tile length for testing
                                .lineToConstantHeading(new Vector2d(-56, -12))// add -24 to the x value (currently at 5 tile length for testing
                                .waitSeconds(2)
                                .lineToConstantHeading(new Vector2d(-50, -12))// add -24 to the x value (currently at 5 tile length for testing
                                .lineToConstantHeading(new Vector2d(-56, -12))// add -24 to the x value (currently at 5 tile length for testing
                                .waitSeconds(2)

                                .lineToConstantHeading(new Vector2d(18, -10))
                                .splineTo(new Vector2d(53, -33), Math.toRadians(0))
                                .waitSeconds(2)

                                .lineToConstantHeading(new Vector2d(48, -12))
                                .lineToConstantHeading(new Vector2d(60, -60))

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(Bot)
                .start();
    }
}