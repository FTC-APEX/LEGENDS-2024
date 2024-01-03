package com.apex.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 16)
                .setDimensions(16.5, 17.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
//                                .forward(25)
//                                .waitSeconds(1.5)
//                                .back(1)
//                                .turn(Math.toRadians(-90))
//                                .forward(80)
//                                .waitSeconds(2)
//                                .back(6)
//                                .turn(Math.toRadians(90))
//                                .forward(24)
//                                .turn(Math.toRadians(90))
//                                .forward(90)
//                                .waitSeconds(1.5)
//                                .back(12)
//                                .turn(Math.toRadians(180))
//                                .forward(78)
//                                .turn(Math.toRadians(-90))
//                                .forward(24)
//                                .turn(Math.toRadians(90))
//                                .forward(8)
//                                .waitSeconds(2)


                                //Initial Movement
                                .forward(24)
                                .waitSeconds(1.5)
                                .lineToConstantHeading(new Vector2d(-36, -36))
                                //For Zone 1
                                .turn(Math.toRadians(90))
                                //For Zone 3
                                .turn(Math.toRadians(-90))

                                .lineToLinearHeading(new Pose2d(48, -36, Math.toRadians(-90)))
                                .waitSeconds(2)
                                //.lineToConstantHeading(new Vector2d(36, -36))

                                //cycles
                                //.lineToConstantHeading(new Vector2d(36, -36))
                                .back(18)
                                .strafeLeft(24)
                                .turn(Math.toRadians(180))
                                .forward(96)
                                //grab two pixels and BOOK IT
                                .waitSeconds(1)
                                .back(96)
                                .turn(Math.toRadians(-180))
                                .strafeRight(24)
                                .forward(12)
                                .waitSeconds(1)
                                //place pixel
                                // do this as many times are necessary



                                //park left
                                .back(18)

                                .strafeLeft(24)
                                .forward(24)

                                //park right
                                //.back(18)
                                //.strafeRight(24)
                                //.forward(24)

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}