package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class OpenCVBLUE {

    public static class Pipeline extends OpenCvPipeline {

        public enum position {
            LEFT,
            CENTER,
            RIGHT,
            UNKNOWN
        }

        //Color Constants - Please tune these
        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar RED = new Scalar(255, 0, 0);
        static final Scalar GREEN = new Scalar(0, 255, 0);
        static final Scalar ORANGE = new Scalar(188, 57, 8);

        //Regions for detection
        //Region Anchors
        static final Point anchorLeft = new Point(200, 300);
        static final Point anchorCenter = new Point(375, 300);
        static final Point anchorRight = new Point(1100, 300);
        static final int widthL = 175;
        static final int heightL = 300;
        static final int widthC = 725;
        static final int heightC = 200;
        static final int widthR = 180;
        static final int heightR = 300;

        //Left Region Points
        Point leftA = new Point(
                anchorLeft.x,
                anchorLeft.y );
        Point leftB = new Point(
                anchorLeft.x + widthL,
                anchorLeft.y + heightL );

        //Center Region Points
        Point centerA = new Point(
                anchorCenter.x,
                anchorCenter.y );
        Point centerB = new Point(
                anchorCenter.x + widthC,
                anchorCenter.y + heightC );

        //Right Region Points
        Point rightA = new Point(
                anchorRight.x,
                anchorRight.y );
        Point rightB = new Point(
                anchorRight.x + widthR,
                anchorRight.y + heightR );



        //Variables
        Mat crL, crC, crR;
        Mat cbL, cbC, cbR;
        Mat YCrCb = new Mat();
        Mat Cb = new Mat();
        Mat Cr = new Mat();
        public int avgLR, avgCR, avgRR, avgLB, avgCB, avgRB;


        private volatile position Position = position.CENTER;

        void inputToCr(Mat input) {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cr, 1);
        }

        void inputToCb(Mat input) {
            Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(YCrCb, Cb, 2);
        }


        public void init(Mat frame1) {
            inputToCr(frame1);
            inputToCb(frame1);

            crL = Cr.submat(new Rect(leftA, leftB));
            crC = Cr.submat(new Rect(centerA, centerB));
            crR = Cr.submat(new Rect(rightA, rightB));

            cbL = Cb.submat(new Rect(leftA, leftB));
            cbC = Cb.submat(new Rect(centerA, centerB));
            cbR = Cb.submat(new Rect(rightA, rightB));

        }

        @Override
        public Mat processFrame(Mat frame) {
            inputToCr(frame);

            avgLR = (int) Core.mean(crL).val[0];
            avgCR = (int) Core.mean(crC).val[0];
            avgRR = (int) Core.mean(crR).val[0];

            inputToCb(frame);

            avgLB = (int) Core.mean(cbL).val[0];
            avgCB = (int) Core.mean(cbC).val[0];
            avgRB = (int) Core.mean(cbR).val[0];


            Imgproc.rectangle(
                    frame,
                    leftA,
                    leftB,
                    GREEN,
                    2);

            Imgproc.rectangle(
                    frame,
                    centerA,
                    centerB,
                    GREEN,
                    2);

            Imgproc.rectangle(
                    frame,
                    rightA,
                    rightB,
                    GREEN,
                    2);

            int maxLRR = Math.max(avgLR, avgRR);
            int minR = Math.max(maxLRR, avgCR);

            int maxLRB = Math.max(avgLB, avgRB);
            int minB = Math.max(maxLRB, avgCB);

            if (Math.abs((avgRB-avgLB)) < 3 && Math.abs((avgCB-avgLB)) < 3 && Math.abs((avgCB-avgRB)) < 3) {
                Position = position.RIGHT;
            }

            else if (minB == avgLB) {
                Position = position.LEFT;

                Imgproc.rectangle(
                        frame,
                        leftA,
                        leftB,
                        ORANGE,
                        2);
            }

            else if(minB == avgCB) {
                Position = position.CENTER;

                Imgproc.rectangle(
                        frame,
                        centerA,
                        centerB,
                        ORANGE,
                        2);
            }

            else if(minB == avgRB) {
                Position = position.RIGHT;

                Imgproc.rectangle(
                        frame,
                        rightA,
                        rightB,
                        ORANGE,
                        2);
            }

            return frame;

        }

        public position getPosition() {
            return Position;
        }









    }



}