package com.nagarro.ngt.api;

//import java.util.List;

//import org.opencv.core.Core;
//import org.opencv.core.Mat;
//import org.opencv.highgui.HighGui;
//import org.opencv.videoio.VideoCapture;
//
//import com.nagarro.ngt.services.FramePreprocessor;
//import com.nagarro.ngt.services.LicensePlateDetector;
//
//public class VehicleIdentificationApp {
//
//    public static void main(String[] args) {
//        // Load OpenCV library
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        // Initialize video capture (adjust the URL or camera index as needed)
//        VideoCapture videoCapture = new VideoCapture("C:/Users/sauravsahu/Downloads/main.mp4"); // Change to your video source
//
//        if (!videoCapture.isOpened()) {
//            System.out.println("Error: Video capture not initialized.");
//            return;
//        }
//
//        try {
//            while (true) {
//                Mat frame = new Mat();
//                if (videoCapture.read(frame)) {
//                     //Preprocess the frame (e.g., resize, enhance contrast, noise reduction)
//                	Mat preprocessedFrame = FramePreprocessor.preprocessFrame(frame);
//                	
//                	HighGui.imshow("Processed Frame", preprocessedFrame);
//                    HighGui.waitKey(100); // Adjust the delay as needed
////                	
//                	List<Mat> licensePlates = LicensePlateDetector.detectLicensePlates(preprocessedFrame);
//
////                	for (Mat licensePlate : licensePlates) {
////                	    // Display the license plate
////                	    HighGui.imshow("Detected License Plate", licensePlate);
////                	    HighGui.waitKey(10);
////                	}
//                	System.out.println("Detected " + licensePlates.size() + " license plates.");
//                	
//
//                } else {
//                    System.out.println("Error: Cannot read frame from video.");
//                    break;
//                }
//            }
//        } finally {
//            // Release videoCapture and any other resources
//            videoCapture.release();
//            System.out.println("done");
//            HighGui.destroyAllWindows();
//        }
//    }
//}

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

import com.nagarro.ngt.services.FramePreprocessor;
import com.nagarro.ngt.services.LicensePlateDetector;
import com.nagarro.ngt.services.NumberPlateExtraction;

public class VehicleIdentificationApp {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Provide the path to the video file or camera index as a command-line argument
        VideoCapture videoCapture = new VideoCapture("C:/Users/sauravsahu/Downloads/ngt.mp4"); 

        if (!videoCapture.isOpened()) {
            System.out.println("Error: Couldn't open video source.");
            System.exit(1);
        }

        // Create an instance of the LicensePlateDetector
        LicensePlateDetector detector = new LicensePlateDetector("C:/Users/sauravsahu/Downloads/haarcascade_russian_plate_number.xml");

        NumberPlateExtraction plate = new NumberPlateExtraction("C:/Users/sauravsahu/Downloads/indian_license_plate.xml");
        
        Mat frame = new Mat();

        while (true) {
            if (videoCapture.read(frame)) {
                // Preprocess the frame
                Mat preprocessedFrame = FramePreprocessor.preprocessFrame(frame);

                // Detect and recognize the license plate
                Mat licensePlateMat = detector.detectAndRecognizeLicensePlate(frame);
                String realPlate = plate.detectAndRecognizeLicensePlate(licensePlateMat);
          
                if (preprocessedFrame != null) {  // Check if licensePlateMat is not null
                    HighGui.imshow("License Plate Detection", preprocessedFrame);
                    if (HighGui.waitKey(1) == 'q') {
                        break;
                    }
                }
                
                // Exit when the user presses the 'q' key
//                if (HighGui.waitKey(1) == 'q') {
//                    break;
//                }
                if (realPlate != null) {
                    System.out.println("Detected License Plate: " + realPlate);
                } else {
                    System.out.println("No license plate detected.");
                }

                // Display the preprocessed frame with detected license plate
//                HighGui.imshow("License Plate Detection", frame);
////
//                // Exit when the user presses the 'q' key
//                if (HighGui.waitKey(1) == 'q') {
//                    break;
//                }
            } else {
                System.out.println("End of video stream.");
                break;
            }
        }

        videoCapture.release();
        HighGui.destroyAllWindows();
    }
}



