package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.constantsRobot;

public class intake {

    private DcMotorEx intake = null;

    private Servo pivot = null;
    private String state = "PRE-INIT";
    private String position = "PRE-INIT";

    public void init(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT); //Not sure whether BRAKE is needed yet, but change to BRAKE if needed
        intake.setDirection(DcMotorEx.Direction.FORWARD); //Change to Reverse if Needed

        state = "INIT";

        //pivot = hardwareMap.get(Servo.class, "pivot"); //Uncomment when implemented for states robot

        position = "INIT";

        setIntake(constantsRobot.intake.OFF);
        //setPivot(constantsRobot.pivot.RETRACTED);
    }

    public void setIntake(constantsRobot.intake intakeState) {
        switch (intakeState) {
            case SUCK:
                intake.setDirection(DcMotorEx.Direction.FORWARD);
                intake.setPower(constantsRobot.STANDARD);
                state = "SUCK";
                break;

            case SPIT:
                intake.setDirection(DcMotorEx.Direction.REVERSE);
                intake.setPower(constantsRobot.STANDARD);
                state = "SPIT";
                break;

            case OFF:
                intake.setPower(0);
                state = "OFF";
                break;

            default:
                setIntake(constantsRobot.intake.OFF);
                state = "DEFAULT --> OFF (SOMETHING WENT WRONG IN INIT)";
                break;
        }
    }

    public void setPivot(constantsRobot.pivot pivotPosition) {
        switch (pivotPosition) {
            case GROUND:
                pivot.setPosition(constantsRobot.GROUND);
                position = "GROUND";
                break;

            case TWO:
                pivot.setPosition(constantsRobot.TWO);
                position = "TWO PIXELS";
                break;

            case FOUR:
                pivot.setPosition(constantsRobot.FOUR);
                position = "FOUR PIXELS";
                break;

            case SIX:
                pivot.setPosition(constantsRobot.SIX);
                position = "SIX PIXELS";
                break;

            case RETRACTED:
                pivot.setPosition(constantsRobot.RETRACTED);
                position = "RETRACTED";
                break;

            default:
                setPivot(constantsRobot.pivot.RETRACTED);
                position = "DEFAULT --> RETRACTED (SOMETHING WENT WRONG IN INIT)";
                break;
        }
    }


    public void reset() {
        if ((!state.equals("OFF") && !state.equals("INIT")) || (!position.equals("RETRACTED") && !position.equals("INIT"))) {
            setIntake(constantsRobot.intake.OFF);
            setPivot(constantsRobot.pivot.RETRACTED);
        }
        else if (state.equals("OFF") && !position.equals("RETRACTED")) {
            state = "OFF";
            setPivot(constantsRobot.pivot.RETRACTED);
        }
        else if (!state.equals("OFF") && position.equals("RETRACTED")) {
            setIntake(constantsRobot.intake.OFF);
            position = "RETRACTED";
        }
        else {
            state = "RESET ERROR";
        }
    }



    public String getState() {
        return state;
    }

    public String getPosition() {
        return position;
    }


}
