package org.firstinspires.ftc.teamcode.util;

import com.acmerobotics.dashboard.config.Config;

@Config
public class constantsRobot {


    //Slide Constants
    public static double MAX_POWER = 0.6;
    public static double MIN_POWER = 0.2;

    public static double kp;
    public static double ki;
    public static double kd;
    public static double kg;

    public static int READY /* = 0*/;
    public static int LINE1 /* = 1*/;
    public static int LINE2 /* = 2*/;
    public static int LINE3 /* = 3*/;
    public static int FULL /* = 4*/;
    public static int HANG /* = 3.5*/;

    public enum slides{
        READY,
        FIRST,
        SECOND,
        THIRD,
        FULL,
        HANG
    }

    //Intake Constants

    public static double STANDARD = 1.0;
    public static double CONTROLLED = 0.4;

    public enum intake {
        SUCK,
        SPIT,
        OFF
    }

    public static double GROUND = 0;
    public static double TWO = 0.1;
    public static double FOUR = 0.2;
    public static double SIX = 0.3;
    public static double RETRACTED = 1;

    public enum pivot {
        GROUND, // .5 pixel height
        TWO, // 1.5 pixel height
        FOUR, // 3.5 pixel height
        SIX, // 5.5 pixel height
        RETRACTED
    }

    //Outtake Constants
    public enum outtake {
        READY, // OPEN FRONT, ANGLED INTO INTAKE
        MOVING, // ONLY USED DURING DRIVING --> PIXELS NOT TO JOSTLE OUT OF OUTTAKE // CLOSED FRONT, ANGLED INTO INTAKE
        AIM, // CLOSED FRONT, ANGLED TO BACKDROP
        SCORE, // OPEN FRONT, ANGLED TO BACKDROP
    }

    //Temporary Values, Remember to Change
    public static int BLOCKER_OPEN = 1;
    public static int BLOCKER_CLOSED = 0;

    public static int PIVOT_A_READY = 0;
    public static int PIVOT_B_READY = 0;

    public static int PIVOT_A_SCORE = 0;
    public static int PIVOT_B_SCORE = 0;

    //shooter constants
    public static double SHOOTER_READY = 0;
    public static double SHOOTER_SHOT = 1;

}
