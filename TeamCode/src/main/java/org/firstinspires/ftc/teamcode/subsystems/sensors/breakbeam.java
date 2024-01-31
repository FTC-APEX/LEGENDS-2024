package org.firstinspires.ftc.teamcode.subsystems.sensors;

import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class breakbeam {

    private DigitalChannel breakbeam;

    private boolean broken = false;

    public void init(HardwareMap hardwareMap) {
        breakbeam = hardwareMap.get(DigitalChannel.class, "breakbeam");

        breakbeam.setMode(DigitalChannel.Mode.INPUT);
    }

    public boolean getState() {
        broken = !breakbeam.getState();

        if (broken) {
            return true;
        }
        else {
            return false;
        }
    }

}
