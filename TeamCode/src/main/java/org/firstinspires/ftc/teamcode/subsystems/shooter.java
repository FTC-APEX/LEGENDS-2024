package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class shooter {
        private Servo Shooter;

        public void init(HardwareMap hardwareMap) {
            Shooter = hardwareMap.get(Servo.class, "Shooter");
        }

        double ShooterTense = 0.4; //change later
        double ShooterLoose = 0; //changer later

        public void ShooterLoad() {
            Shooter.setPosition(ShooterTense);
        }

        public void ShooterRelease() {
            Shooter.setPosition(ShooterLoose);
        }
}

