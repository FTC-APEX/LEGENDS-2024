package org.firstinspires.ftc.teamcode.subsystems.commands;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.subsystems.intake;
import org.firstinspires.ftc.teamcode.subsystems.outtake;
import org.firstinspires.ftc.teamcode.subsystems.slides;
import org.firstinspires.ftc.teamcode.util.constantsRobot;

public class baseCommands {
    class Commands extends CommandBase {
        //Slide Commands
        public void retract(slides slides) {
            slides.preset(constantsRobot.slides.READY);
        }

        public void extend1(slides slides) {
            slides.preset(constantsRobot.slides.FIRST);
        }

        public void extend2(slides slides) {
            slides.preset(constantsRobot.slides.SECOND);
        }

        public void extend3(slides slides) {
            slides.preset(constantsRobot.slides.THIRD);
        }

        public void extendFull(slides slides) {
            slides.preset(constantsRobot.slides.FULL);
        }


        //Outtake Commands
        public void ready(outtake outtake) {
            outtake.setState(constantsRobot.outtake.READY);
        }

        public void moving(outtake outtake) {
            outtake.setState(constantsRobot.outtake.SCORE);
        }

        public void aim(outtake outtake) {
            outtake.setState(constantsRobot.outtake.AIM);
        }
        public void score(outtake outtake) {
            outtake.setState(constantsRobot.outtake.SCORE);
        }

        //Intake Commands
        //Tubing Sets
        public void suck(intake intake) {
            intake.setIntake(constantsRobot.intake.SUCK);
        }

        public void spit(intake intake) {
            intake.setIntake(constantsRobot.intake.SPIT);
        }

        public void stop(intake intake) {
            intake.setIntake(constantsRobot.intake.OFF);
        }
        //Pivot sets
        public void ground(intake intake) {
            intake.setPivot(constantsRobot.pivot.GROUND);
        }

        public void one(intake intake) {
            intake.setPivot(constantsRobot.pivot.ONE);
        }

        public void three(intake intake) {
            intake.setPivot(constantsRobot.pivot.THREE);
        }

        public void five(intake intake) {
            intake.setPivot(constantsRobot.pivot.FIVE);
        }

        public void retract(intake intake) {
            intake.setPivot(constantsRobot.pivot.RETRACTED);
        }

        //
    }
}
