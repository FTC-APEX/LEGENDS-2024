package org.firstinspires.ftc.teamcode.utility;

import com.acmerobotics.dashboard.config.Config;

@Config
public class constants {

    public static int ready = 0;
    public static int first = 50;
    public static int second = 150;
    public static int third = 350;
    public static int full = 350;
    public static int hang = 500;
    public static int standby = 2;
    public enum slides {
        READY,
        FIRST,
        SECOND,
        THIRD,
        FULL,
        CUSTOM,
        HANG
    }

    public static double outtakeOpen = 0.5;
    public static double outtakeClose = 1;

    public static double hingeReadyA = 0.28;
    public static double hingeReadyB = 1;
    public static double hingeScoreA = 0.68;
    public static double hingeScoreB = 0.6;
    public enum outtake {
        MOVING,
        READY,
        AIM,
        SCORE
    }

    public static double floor = 0;
    public static double two = 0.25;
    public static double four = 0.5;
    public static double six = 0.75;
    public static double retracted = 1;


    public enum intakeHeights {
        FLOOR,
        TWO,
        FOUR,
        SIX,
        RETRACTED
    }

    public static double intakeSpeed = 0.8;
    public static double purpleSpeed = 0.2;

    public enum intakeState {
        SUCK,
        SPIT,
        PURPLE,
        STOP
    }

    public enum autonomous_Y_P {
        START,
        KAMERA_DECISION,
        YELLOW,
        BACK_TO_DECISION_POINT,


    }
}
