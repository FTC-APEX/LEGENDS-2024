package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.constants;

public class intake {
    private DcMotorEx intake;
    private Servo pivot;

    private String State;
    private String Position;

    public void init(HardwareMap hardwareMap) {
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void state(constants.intakeState state) {
        switch (state) {
            case SUCK:
                intake.setDirection(DcMotorEx.Direction.FORWARD);
                intake.setPower(constants.intakeSpeed);
                State = "Sucking In";
                break;

            case SPIT:
                intake.setDirection(DcMotorEx.Direction.REVERSE);
                intake.setPower(constants.intakeSpeed);
                State = "Spitting Out";
                break;

            case PURPLE:
                intake.setDirection(DcMotorEx.Direction.REVERSE);
                intake.setPower(constants.purpleSpeed);
                State = "Autonomous Purple";
                break;

            case STOP:
                intake.setPower(0);
                State = "Idle";
                break;
        }
    }

    public void height(constants.intakeHeights height) {
        switch (height) {
            case FLOOR:
                pivot.setPosition(constants.floor);
                Position = "Floor";
                break;

            case TWO:
                pivot.setPosition(constants.two);
                Position = "Two";
                break;

            case FOUR:
                pivot.setPosition(constants.four);
                Position = "Four";
                break;

            case SIX:
                pivot.setPosition(constants.six);
                Position = "Six";
                break;

            case RETRACTED:
                pivot.setPosition(constants.retracted);
                Position = "Retracted";
                break;
        }
    }

    public String getStatus() {
        return State;
    }
}