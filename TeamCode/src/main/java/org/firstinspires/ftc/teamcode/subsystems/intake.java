package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class intake {
    private DcMotor Sweep;

    public void init(HardwareMap hardwareMap) {
        Sweep = hardwareMap.get(DcMotor.class, "Sweep");
    }

}
