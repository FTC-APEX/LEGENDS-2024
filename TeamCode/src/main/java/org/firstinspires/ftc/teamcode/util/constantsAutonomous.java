package org.firstinspires.ftc.teamcode.util;

public class constantsAutonomous {
    public enum redFront {
        PURPLE_CAM,
        SCORE_YELLOW,
        TO_STACK, // BOOK IT
        BURST,
        TO_BACKBOARD,
        STRAFE_TO_BACKDROP_INLINE,
        CYCLE_SCORE,
    }

    public enum redBack {
        IDLE,
        PURPLE_CAM,
        SCORE_YELLOW,
        TO_STACK, // BOOK IT
        BURST,
        TO_BACKBOARD,
        RESET,
        PARK_LEFT,
        PARK_RIGHT,
        END
    }

    public enum blueFront {
        CAMERA_TRAJECTORY,
        TO_BACKDROP,
        REVERSE_AND_PIVOT,
        TO_STACK_INLINE_AND_PIVOT,
        TO_STACK, // BOOK IT
        BACK_TO_5TH_LANE_AND_PIVOT,
        STRAFE_TO_BACKDROP_INLINE,
        CYCLE_SCORE,
    }

    public enum blueBack {
        CAMERA_TRAJECTORY,
        TO_BACKDROP,
        REVERSE_AND_PIVOT,
        TO_STACK_INLINE_AND_PIVOT,
        TO_STACK, // BOOK IT
        BACK_TO_5TH_LANE_AND_PIVOT,
        STRAFE_TO_BACKDROP_INLINE,
        CYCLE_SCORE,
    }
}
