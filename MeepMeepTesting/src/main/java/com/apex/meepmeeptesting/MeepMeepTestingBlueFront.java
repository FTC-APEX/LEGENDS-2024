package com.apex.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTestingBlueFront {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity Bot = new DefaultBotBuilder(meepMeep) //blue side near stage
                // Assume starting
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 16)
                .setDimensions(16.5, 17.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(15.625, 63.3125, Math.toRadians(270)))
                                //Initial Movement

                                .lineToConstantHeading(new Vector2d(8, 36))
                                .waitSeconds(1)

                                .setReversed(true)
                                .lineToLinearHeading(new Pose2d(24, 38, Math.toRadians(180)))
                                .lineToConstantHeading((new Vector2d(56.5, 33)))
                                .setReversed(false)
                                .waitSeconds(1)

                                .splineTo(new Vector2d(18, 10), Math.toRadians(180))
                                .splineTo(new Vector2d(-55, 16), Math.toRadians(180))

                                .waitSeconds(1.5)
                                .lineToConstantHeading(new Vector2d(-50, 12))// add -24 to the x value (currently at 5 tile length for testing
                                .lineToConstantHeading(new Vector2d(-56, 12))// add -24 to the x value (currently at 5 tile length for testing
                                .waitSeconds(1)
                                .lineToConstantHeading(new Vector2d(-50, 12))// add -24 to the x value (currently at 5 tile length for testing
                                .lineToConstantHeading(new Vector2d(-56, 12))// add -24 to the x value (currently at 5 tile length for testing
                                .waitSeconds(1)

                                .lineToConstantHeading(new Vector2d(18, 12))
                                .splineTo(new Vector2d(53, 42), Math.toRadians(0))
                                .waitSeconds(2)

                                .waitSeconds(3)

//                                //zone2
//                                .lineToConstantHeading(new Vector2d(12, 34))
//
//                                .setReversed(true)
//                                .splineTo(new Vector2d(24, 36), Math.toRadians(0))
//                                .lineToConstantHeading(new Vector2d(48, 36))
//                                .setReversed(false)
//                                .waitSeconds(2)
//
//
//                                .splineTo(new Vector2d(0, 12), Math.toRadians(180))
//
//                                .lineToConstantHeading(new Vector2d(-60, 12))
//
//                                .waitSeconds(1)
//                                .lineToConstantHeading(new Vector2d(0, 12))
//                                .splineTo(new Vector2d(48, 36), Math.toRadians(0))
//
//                                .waitSeconds(1)
//
//                                .splineTo(new Vector2d(0, 12), Math.toRadians(180))
//
//                                .lineToConstantHeading(new Vector2d(-60, 12))
//
//                                .waitSeconds(1)
//                                .lineToConstantHeading(new Vector2d(0, 12))
//                                .splineTo(new Vector2d(48, 36), Math.toRadians(0))
//
//                                .waitSeconds(1)
//
//                                //park left
//                                //.lineToConstantHeading(new Vector2d(48, 12))
//                                //.lineToConstantHeading(new Vector2d(60, 12))
//
//                                //park right
//                                .lineToConstantHeading(new Vector2d(48, 60))
//                                .lineToConstantHeading(new Vector2d(60, 60))

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(Bot)
                .start();
    }
}