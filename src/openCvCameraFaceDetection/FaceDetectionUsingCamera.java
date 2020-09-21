package openCvCameraFaceDetection;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

public class FaceDetectionUsingCamera {
	public static void main(String args[]) throws IOException {
		// Loading the OpenCV core library
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			
			// Reading the Image from the file and storing it in to a Matrix object
		  
		    //  Mat src = Imgcodecs.imread(file);
		      VideoCapture vc=new VideoCapture(0);
		 	 // Reading the next video frame from the camera
		 	Mat src=new Mat();
		 
		 	vc.read(src);
		 	  // If camera is opened
		    if( vc.isOpened()) {
		       // If there is next video frame
		       if (vc.read(src)) {
		      // Instantiating the CascadeClassifier
		      String xmlFile = "C:/OpenCv/opencv/sources/data/lbpcascades/lbpcascade_frontalface.xml";
		      // give the path accordingly to the library
		      CascadeClassifier classifier = new CascadeClassifier(xmlFile);

		      // Detecting the face in the snap
		      MatOfRect faceDetections = new MatOfRect();
		      classifier.detectMultiScale(src, faceDetections);
		      System.out.println(String.format("Detected %s faces", 
		         faceDetections.toArray().length));

		      // Drawing boxes
		      for (Rect rect : faceDetections.toArray()) {
		         Imgproc.rectangle(
		            src,                                               // where to draw the box
		            new Point(rect.x, rect.y),                            // bottom left
		            new Point(rect.x + rect.width, rect.y + rect.height), // top right
		            new Scalar(0, 0, 255),
		            3                                                     // RGB colour
		         );
		      }

		      // Writing the image
		    //  Imgcodecs.imwrite("C:/Users/Home/eclipse-workspace/OpenCv/snapshot_output1.jpg", src);
		      MatOfByte mot=new MatOfByte();
		  	//Encoding the image
		  	Imgcodecs.imencode(".jpg", src, mot);
		  	 //Storing the encoded Mat in a byte array
		  	byte array[]=mot.toArray();
		  	//Preparing the Buffered Image 
		  	InputStream in=new ByteArrayInputStream(array);
		  	 BufferedImage bufImage = ImageIO.read(in);
		  	//Instantiate JFrame 
		       JFrame frame = new JFrame(); 

		       //Set Content to the JFrame 
		       frame.getContentPane().add(new JLabel(new ImageIcon(bufImage))); 
		       frame.pack(); 
		       frame.setVisible(true);
		       }}
		      System.out.println("Image Processed");
		     Thread t=new Thread();
		     try {
				t.sleep(10000); // terminate the program after 10 secs
				 System.exit(0);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		     
		   }
		

}
