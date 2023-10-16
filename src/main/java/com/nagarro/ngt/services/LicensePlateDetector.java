package com.nagarro.ngt.services;



//import java.util.List;
//
//import org.opencv.core.Mat;
//import org.opencv.core.Point;
//import org.opencv.core.Scalar;
//import org.opencv.core.Size;
//import org.opencv.dnn.Dnn;
//import org.opencv.dnn.Net;
//import org.opencv.imgproc.Imgproc;
//
//public class LicensePlateDetector {
//
//    private static final String YOLOV3_CONFIG = "C:/Users/sauravsahu/OneDrive - Nagarro/Desktop/model/yolov7.cfg"; // Path to YOLOv3 config file
//    private static final String YOLOV3_WEIGHTS = "C:/Users/sauravsahu/OneDrive - Nagarro/Desktop/model/yolov7.-w6.pt"; // Path to YOLOv3 weights file
//
//    public static void detectLicensePlates(Mat frame) {
//        // Load YOLOv3 model
//    	if (frame == null) {
//            System.out.println("Error: Input frame is null.");
//            return;
//        }
//    	Net net = Dnn.readNetFromDarknet(YOLOV3_CONFIG, YOLOV3_WEIGHTS);
//    	try {
//            if (net.empty()) {
//                System.err.println("Error: Failed to load YOLOv3 model.");
//                return;
//            }
//            System.out.println("Loaded YOLOv3 model successfully.");
//
//            // ... Rest of the code for object detection ...
//         // Specify the layers for object detection (YOLOv3)
//            List<String> layerNamesList = net.getLayerNames();
//            String[] layerNames = layerNamesList.toArray(new String[0]);
//            String outputLayer = layerNames[layerNames.length - 1];
//            System.out.println("On Yolo2");
//
//            // Perform object detection
//            Mat blob = Dnn.blobFromImage(frame, 1.0, new Size(416, 416), new Scalar(0, 0, 0), true, false);
//            net.setInput(blob);
//            System.out.println("On Yolo3");
//            Mat detections = net.forward(outputLayer);
//            System.out.println("On Yolo4");
//
//            // Loop through detected objects
//            for (int i = 0; i < detections.rows(); ++i) {
//                double confidence = detections.get(i, 4)[0];
//                if (confidence > 0.5) { // Adjust confidence threshold as needed
//                    // Extract bounding box coordinates
//                    double x = detections.get(i, 0)[0] * frame.cols();
//                    double y = detections.get(i, 1)[0] * frame.rows();
//                    double width = detections.get(i, 2)[0] * frame.cols();
//                    double height = detections.get(i, 3)[0] * frame.rows();
//
//                    // Draw bounding box (you can also save this region or perform OCR)
//                    Imgproc.rectangle(frame, new Point(x, y), new Point(x + width, y + height), new Scalar(0, 255, 0), 2);
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Error: An exception occurred while loading the YOLOv3 model.");
//        }
//    }
//}


//import org.opencv.core.*;
//import org.opencv.highgui.HighGui;
//import org.opencv.imgproc.Imgproc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LicensePlateDetector {
//
//    public static List<Mat> detectLicensePlates(Mat preprocessedFrame) {
//        List<Mat> licensePlates = new ArrayList<>();
//        
////        Mat edges = new Mat();
////        Imgproc.Canny(preprocessedFrame, edges, 50, 150);
////
////        Mat dilatedEdges = new Mat();
////        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
////        Imgproc.dilate(edges, dilatedEdges, element);
////        HighGui.imshow("Dil", edges);
////	    HighGui.waitKey(1);
//        List<MatOfPoint> contours = new ArrayList<>();
//        Mat hierarchy = new Mat();
//        Imgproc.findContours(preprocessedFrame, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//
////        HighGui.imshow("Processed Frame", dilatedEdges);
////        HighGui.waitKey(100); // Adjust the delay as needed
//        for (MatOfPoint contour : contours) {
//
//            MatOfPoint2f approxCurve = new MatOfPoint2f();
//            MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
//            double contourArea = Imgproc.contourArea(contour);
//
//            if (contourArea > 1000) { // Adjust the area threshold as needed
//                int contourPoints = (int) contour.total();
//                Imgproc.approxPolyDP(contour2f, approxCurve, 0.02 * Imgproc.arcLength(contour2f, true), true);
//
//                if (contourPoints == 4) { // Check if the contour has 4 corners (potential license plate)
//                    Rect rect = Imgproc.boundingRect(contour);
//
//                    double aspectRatio = (double) rect.width / rect.height;
//                    if (aspectRatio > 2.0 && aspectRatio < 6.0) { // Adjust aspect ratio range as needed
//                        Mat licensePlate = new Mat(preprocessedFrame, rect);
//                        licensePlates.add(licensePlate);
//                    }
//                }
//            }
//        }
//     // Add this code before returning the licensePlates list
//        for (Mat licensePlate : licensePlates) {
//            Rect rect = new Rect(0, 0, licensePlate.width(), licensePlate.height());
//            Imgproc.rectangle(preprocessedFrame, rect, new Scalar(0, 255, 0), 2); // Draw a green rectangle around the license plate
//        }
//
//
//        return licensePlates;
//    }
//}

//import org.opencv.core.*;
//import org.opencv.highgui.HighGui;
//import org.opencv.imgproc.Imgproc;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class LicensePlateDetector {
//
//    public static List<Mat> detectLicensePlates(Mat preprocessedFrame) {
//        List<Mat> licensePlates = new ArrayList<>();
//
//        Mat blurredFrame = new Mat();
//        Imgproc.GaussianBlur(preprocessedFrame, blurredFrame, new Size(5, 5), 0);
//
//        Mat edges = new Mat();
//        Imgproc.Canny(blurredFrame, edges, 50, 150);
//
//        Mat dilatedEdges = new Mat();
//        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(10, 10)); // Adjust the size
//        Imgproc.dilate(edges, dilatedEdges, element);
//
//        List<MatOfPoint> contours = new ArrayList<>();
//        Mat hierarchy = new Mat();
//        Imgproc.findContours(dilatedEdges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//   
//        for (MatOfPoint contour : contours) {
//            MatOfPoint2f approxCurve = new MatOfPoint2f();
//            MatOfPoint2f contour2f = new MatOfPoint2f(contour.toArray());
//            double contourArea = Imgproc.contourArea(contour);
//
//            if (contourArea > 1000) { // Adjust the area threshold as needed
//                int contourPoints = (int) contour.total();
//                Imgproc.approxPolyDP(contour2f, approxCurve, 0.04 * Imgproc.arcLength(contour2f, true), true);
//
//                if (contourPoints == 4) { // Check if the contour has 4 corners (potential license plate)
//                    Rect rect = Imgproc.boundingRect(contour);
//                    
//
//                    double aspectRatio = (double) rect.width / rect.height;
//                    if (aspectRatio > 2.0 && aspectRatio < 6.0) { // Adjust aspect ratio range as needed
//                        Mat licensePlate = new Mat(preprocessedFrame, rect);
//
//                        // Display the detected license plate
//                        HighGui.imshow("Detected License Plate", licensePlate);
//                        HighGui.waitKey(100); // Adjust the delay as needed
//
//                        licensePlates.add(licensePlate);
//                    }
//                }
//            }
//        }
//
//        // Draw bounding rectangles around the detected license plates on the preprocessed frame
//        for (Mat licensePlate : licensePlates) {
//            Rect rect = new Rect(0, 0, licensePlate.width(), licensePlate.height());
//            Imgproc.rectangle(preprocessedFrame, rect, new Scalar(0, 255, 0), 2); // Draw a green rectangle
//        }
//
//        return licensePlates;
//    }
//}



//public class LicensePlateDetector {
//
//    private CascadeClassifier plateCascade;
//    
//    public LicensePlateDetector(String cascadeFile) {
//        plateCascade = new CascadeClassifier(cascadeFile);
//        if (plateCascade.empty()) {
//            System.err.println("Cascade classifier not found");
//        }
//    }
//
//    public String detectAndRecognizeLicensePlate(Mat preprocessedFrame) {
//        MatOfRect plates = new MatOfRect();
//        plateCascade.detectMultiScale(preprocessedFrame, plates);
//
//        for (Rect rect : plates.toArray()) {
//            // Draw a rectangle around the detected license plate
//            Imgproc.rectangle(preprocessedFrame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);
//
//            // Crop the detected license plate region
//            Mat licensePlateMat = preprocessedFrame.submat(rect);
//
//            // Perform OCR on the cropped license plate region
//            String licensePlateText = recognizeLicensePlateText(licensePlateMat);
//
//            if (!licensePlateText.isEmpty()) {
//                return licensePlateText;
//            }
//        }
//        return null; // No license plate detected
//    }

//    private String recognizeLicensePlateText(Mat licensePlateMat) {
//        ITesseract tesseract = new Tesseract();
//        tesseract.setDatapath("path/to/tesseract/tessdata"); // Set the path to your Tesseract data directory
//        tesseract.setLanguage("eng"); // Set the language
//
//        try {
//            String result = tesseract.doOCR(Imgcodecs.matToBufferedImage(licensePlateMat));
//            return result.trim();
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//        return "";
    //}
//}

//import org.opencv.core.Mat;
//import org.opencv.core.MatOfRect;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.highgui.HighGui;
//import org.opencv.core.Point;
//import org.opencv.objdetect.CascadeClassifier;
//import org.opencv.imgproc.Imgproc;
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//
//public class LicensePlateDetector {
//
//    private CascadeClassifier plateCascade;
//
//    public LicensePlateDetector(String cascadeFile) {
//        plateCascade = new CascadeClassifier(cascadeFile);
//        if (plateCascade.empty()) {
//            System.err.println("Cascade classifier not found");
//        }
//    }
//
//    private BufferedImage matToBufferedImage(Mat mat) {
//        int width = mat.cols();
//        int height = mat.rows();
//        int channels = mat.channels();
//        byte[] sourcePixels = new byte[width * height * channels];
//        mat.get(0, 0, sourcePixels);
//
//        if (mat.channels() > 1) {
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//            System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
//            return image;
//        } else if (mat.channels() == 1) {
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
//            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//            System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
//            return image;
//        } else {
//            return null;
//        }
//    }
//
//    public String detectAndRecognizeLicensePlate(Mat preprocessedFrame) {
//        MatOfRect plates = new MatOfRect();
//        plateCascade.detectMultiScale(preprocessedFrame, plates);
//
//        for (Rect rect : plates.toArray()) {
//            // Draw a rectangle around the detected license plate
//            Imgproc.rectangle(preprocessedFrame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);
//
//            // Crop the detected license plate region
//            Mat licensePlateMat = preprocessedFrame.submat(rect);
//            HighGui.imshow("License Plate ", licensePlateMat);
//            if (HighGui.waitKey(1) == 'q') {
//                break;
//            }
//
//            // Perform OCR on the cropped license plate region
//            String licensePlateText = recognizeLicensePlateText(licensePlateMat);
//
//            if (!licensePlateText.isEmpty()) {
//                return licensePlateText;
//            }
//        }
//        return null; // No license plate detected
//    }
//
//    private String recognizeLicensePlateText(Mat licensePlateMat) {
//        ITesseract tesseract = new Tesseract();
//        tesseract.setDatapath("C:/Users/sauravsahu/AppData/Local/Programs/Tesseract-OCR/tessdata"); // Set the path to your Tesseract data directory
//        tesseract.setLanguage("eng"); // Set the language
//
//        try {
//            BufferedImage bufferedImage = matToBufferedImage(licensePlateMat);
//            String result = tesseract.doOCR(bufferedImage);
//            return result.trim();
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//}

//import org.opencv.core.Mat;
//import org.opencv.core.MatOfRect;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.highgui.HighGui;
//import org.opencv.core.Point;
//import org.opencv.objdetect.CascadeClassifier;
//import org.opencv.imgproc.Imgproc;
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//
//public class LicensePlateDetector {
//
//    private CascadeClassifier plateCascade;
//
//    public LicensePlateDetector(String cascadeFile) {
//        plateCascade = new CascadeClassifier(cascadeFile);
//        if (plateCascade.empty()) {
//            System.err.println("Cascade classifier not found");
//        }
//    }
//
//    private BufferedImage matToBufferedImage(Mat mat) {
//        int width = mat.cols();
//        int height = mat.rows();
//        int channels = mat.channels();
//        byte[] sourcePixels = new byte[width * height * channels];
//        mat.get(0, 0, sourcePixels);
//
//        if (mat.channels() > 1) {
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//            System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
//            return image;
//        } else if (mat.channels() == 1) {
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
//            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//            System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
//            return image;
//        } else {
//            return null;
//        }
//    }
//
//    public String detectAndRecognizeLicensePlate(Mat preprocessedFrame) {
//        MatOfRect plates = new MatOfRect();
//        plateCascade.detectMultiScale(preprocessedFrame, plates);
//
//        String bestLicensePlateText = ""; // Initialize to an empty string
//        double bestConfidence = 0.0; // Initialize to a low confidence value
//
//        for (Rect rect : plates.toArray()) {
//            // Draw a rectangle around the detected license plate
//            Imgproc.rectangle(preprocessedFrame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);
//
//            // Crop the detected license plate region
//            Mat licensePlateMat = preprocessedFrame.submat(rect);
//            HighGui.imshow("License Plate ", licensePlateMat);
//            if (HighGui.waitKey(1) == 'q') {
//                break;
//            }
//
//            // Perform OCR on the cropped license plate region
//            String licensePlateText = recognizeLicensePlateText(licensePlateMat);
//
//            // Calculate OCR confidence (if available, not all OCR engines provide confidence)
//            double confidence = getOCRConfidence(licensePlateMat);
//
//            if (!licensePlateText.isEmpty() && confidence > bestConfidence) {
//                bestLicensePlateText = licensePlateText;
//                bestConfidence = confidence;
//            }
//        }
//
//        if (!bestLicensePlateText.isEmpty()) {
//            return bestLicensePlateText;
//        }
//
//        return null; // No license plate detected
//    }
//
//    private double getOCRConfidence(Mat licensePlateMat) {
//        ITesseract tesseract = new Tesseract();
//        tesseract.setDatapath("C:/Users/sauravsahu/AppData/Local/Programs/Tesseract-OCR/tessdata");
//        tesseract.setLanguage("eng");
//
//        try {
//            BufferedImage bufferedImage = matToBufferedImage(licensePlateMat);
//            String result = tesseract.doOCR(bufferedImage);
//            // Check if the OCR engine provides confidence information
//            float confidence = tesseract.getConfidence();
//            return confidence;
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//        return 0.0; // Default to low confidence if OCR fails
//    }
//    private String recognizeLicensePlateText(Mat licensePlateMat) {
//        ITesseract tesseract = new Tesseract();
//        tesseract.setDatapath("C:/Users/sauravsahu/AppData/Local/Programs/Tesseract-OCR/tessdata");
//        tesseract.setLanguage("eng");
//
//        try {
//            BufferedImage bufferedImage = matToBufferedImage(licensePlateMat);
//            String result = tesseract.doOCR(bufferedImage);
//            return result.trim();
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//}
//

//import org.opencv.core.Mat;
//import org.opencv.core.MatOfRect;
//import org.opencv.core.Rect;
//import org.opencv.core.Scalar;
//import org.opencv.highgui.HighGui;
//import org.opencv.core.Point;
//import org.opencv.objdetect.CascadeClassifier;
//import org.opencv.imgproc.Imgproc;
//import net.sourceforge.tess4j.ITesseract;
//import net.sourceforge.tess4j.Tesseract;
//import net.sourceforge.tess4j.TesseractException;
//import java.awt.image.BufferedImage;
//import java.awt.image.DataBufferByte;
//import java.util.regex.Pattern;
//import java.util.regex.Matcher;
//
//public class LicensePlateDetector {
//
//    private CascadeClassifier plateCascade;
//    private Pattern licensePlatePattern;
//
//    public LicensePlateDetector(String cascadeFile) {
//        plateCascade = new CascadeClassifier(cascadeFile);
//        if (plateCascade.empty()) {
//            System.err.println("Cascade classifier not found");
//        }
//
//        // Define a regular expression pattern for the desired license plate format
//        licensePlatePattern = Pattern.compile("\\d{2}-[A-Z]{2}-\\d{3}");
//    }
//
//    private BufferedImage matToBufferedImage(Mat mat) {
//        int width = mat.cols();
//        int height = mat.rows();
//        int channels = mat.channels();
//        byte[] sourcePixels = new byte[width * height * channels];
//        mat.get(0, 0, sourcePixels);
//
//        if (mat.channels() > 1) {
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
//            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//            System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
//            return image;
//        } else if (mat.channels() == 1) {
//            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
//            final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//            System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
//            return image;
//        } else {
//            return null;
//        }
//    }
//
//    public String detectAndRecognizeLicensePlate(Mat preprocessedFrame) {
//        MatOfRect plates = new MatOfRect();
//        plateCascade.detectMultiScale(preprocessedFrame, plates);
//
//        for (Rect rect : plates.toArray()) {
//            // Draw a rectangle around the detected license plate
//            Imgproc.rectangle(preprocessedFrame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);
//
//            // Crop the detected license plate region
//            Mat licensePlateMat = preprocessedFrame.submat(rect);
//            HighGui.imshow("License Plate", licensePlateMat);
//            if (HighGui.waitKey(1) == 'q') {
//                break;
//            }
//            //write code for enhancing number plate here
//
//            // Perform OCR on the cropped license plate region
//            String licensePlateText = recognizeLicensePlateText(licensePlateMat);
//
//            // Check if the recognized text matches the desired format
//            if (!licensePlateText.isEmpty() && isLicensePlateFormatValid(licensePlateText)) {
//                return licensePlateText;
//            }
//        }
//        return null; // No valid license plate detected
//    }
//
//    private String recognizeLicensePlateText(Mat licensePlateMat) {
//        ITesseract tesseract = new Tesseract();
//        tesseract.setDatapath("C:/Users/sauravsahu/AppData/Local/Programs/Tesseract-OCR/tessdata"); // Set the path to your Tesseract data directory
//        tesseract.setLanguage("eng"); // Set the language
//
//        try {
//            BufferedImage bufferedImage = matToBufferedImage(licensePlateMat);
//            String result = tesseract.doOCR(bufferedImage);
//            return result.trim();
//        } catch (TesseractException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    private boolean isLicensePlateFormatValid(String licensePlateText) {
//        Matcher matcher = licensePlatePattern.matcher(licensePlateText);
//        return matcher.matches();
//    }
//}

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.core.Point;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.imgproc.CLAHE;
import org.opencv.imgproc.Imgproc;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class LicensePlateDetector {

    private CascadeClassifier plateCascade;

    public LicensePlateDetector(String cascadeFile) {
        plateCascade = new CascadeClassifier(cascadeFile);
        if (plateCascade.empty()) {
            System.err.println("Cascade classifier not found");
        }
    }

    public Mat detectAndRecognizeLicensePlate(Mat preprocessedFrame) {
        MatOfRect plates = new MatOfRect();
        plateCascade.detectMultiScale(preprocessedFrame, plates);
        
        Mat licensePlateMat = null;  // Initialize licensePlateMat as null
        
        for (Rect rect : plates.toArray()) {
            // Draw a rectangle around the detected license plate
            Imgproc.rectangle(preprocessedFrame, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0), 2);

            // Crop the detected license plate region
            licensePlateMat = preprocessedFrame.submat(rect);
//            HighGui.imshow("License Plate", licensePlateMat);
//            if (HighGui.waitKey(1) == 'q') {
//                break;
//            }
        }
        
        return licensePlateMat;  
    }


}







