package com.apex.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeRedDark;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTestingRedBack {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity Bot = new DefaultBotBuilder(meepMeep) //red side away from stage
                // Assume starting
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeRedDark())
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 16)
                .setDimensions(16.5, 17.5)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                                        //Initial Movement

//                                        //Left
//                                        .splineTo(new Vector2d(-46.5, -38), Math.toRadians(90))
//                                        .setReversed(true)
//                                        .splineTo(new Vector2d(-24, -36), Math.toRadians(0))
//                                        .splineTo(new Vector2d(48, -29), Math.toRadians(0))
//                                        .setReversed(false)
//                                        .waitSeconds(2)
                                        //Center
                                        .lineToConstantHeading(new Vector2d(-36, -34))
                                        .setReversed(true)
                                        .splineTo(new Vector2d(-24, -36), Math.toRadians(0))
                                        .lineToConstantHeading(new Vector2d(48, -36))
                                        .setReversed(false)
                                        .waitSeconds(2)
//                                        //Right
//                                        .splineTo(new Vector2d(-24, -36), Math.toRadians(90))
//                                        .setReversed(true)
//                                        .splineTo(new Vector2d(-20, -36), Math.toRadians(0))
//                                        .splineTo(new Vector2d(48, -43), Math.toRadians(0))
//                                        .setReversed(false)
//                                        .waitSeconds(2)

                                        .splineTo(new Vector2d(0, -12), Math.toRadians(180))

                                        .lineToConstantHeading(new Vector2d(-60, -12))

                                        .waitSeconds(1)
                                        .lineToConstantHeading(new Vector2d(0, -12))
                                        .splineTo(new Vector2d(48, -36), Math.toRadians(0))

                                        .waitSeconds(1)

                                        .splineTo(new Vector2d(0, -12), Math.toRadians(180))

                                        .lineToConstantHeading(new Vector2d(-60, -12))

                                        .waitSeconds(1)
                                        .lineToConstantHeading(new Vector2d(0, -12))
                                        .splineTo(new Vector2d(48, -36), Math.toRadians(0))

                                        .waitSeconds(1)

                                        //park left
                                        //.lineToConstantHeading(new Vector2d(48, -12))
                                        //.lineToConstantHeading(new Vector2d(60, -12))

                                        //park right
                                        .lineToConstantHeading(new Vector2d(48, -60))
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