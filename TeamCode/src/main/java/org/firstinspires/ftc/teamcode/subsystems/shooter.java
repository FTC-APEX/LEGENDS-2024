package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class shooter {
        private CRServo Shooter;

        public void init(HardwareMap hardwareMap) {
            Shooter = hardwareMap.get(CRServo.class, "Shooter");
    }
}

