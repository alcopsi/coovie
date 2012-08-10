import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.awt.*;

import javax.imageio.ImageIO;

public class IMDB {
	static String URL = null;
	public static float movrating;
	public static Image moviePoster;

	IMDB(String moviename) throws Exception {
		String myURL;
		myURL = setURL(moviename);
		myURL = getMovie(myURL);
		showMovie(myURL);
	}
	
	
	IMDB(String moviename,String year) {
		try {
			String myURL;
			myURL = setURL(moviename+"(" + year+ ")");
			myURL = getMovie(myURL);
			showMovie(myURL);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static String setURL(String mystring) {
		String foo;
		mystring = mystring.replaceAll(" ", "+");
		foo = "http://www.imdb.com/find?q=" + mystring + "&s=all";
		return foo;
	}

	public static String getURL() {
		Scanner myscan = new Scanner(System.in);
		String mystring = myscan.nextLine();
		myscan.close();

		mystring = mystring.replaceAll(" ", "+");
		return "http://www.imdb.com/find?q=" + mystring + "&s=all";

	}

	public static String getMovie(String myurl) throws Exception {
		System.out.println("Acquiring Movie's site..."+myurl);
		URL moviedb = new URL(myurl);
		URLConnection yc = moviedb.openConnection();
		yc.addRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine, movie = "error";
		int i = 0, k = 0;

		while ((inputLine = in.readLine()) != null) {
			if ((i = inputLine.indexOf("Users rated this ")) > 0) // Checking if we skipped the IMDB search overview
			{
				for (int j = i; j < inputLine.length(); j++) 
				{
					if ((k = inputLine.indexOf("/title/tt")) > 0) {
						movie = "http://www.imdb.com"
								+ inputLine.subSequence(k, k + 17);
					}
				}
				break;
			}
			else if ((i = inputLine.indexOf("<b>Popular Titles</b>")) > 0)
				for (int j = i; j < inputLine.length(); j++) {
					if ((k = inputLine.indexOf("/title/tt")) > 0) {
						movie = "http://www.imdb.com"
								+ inputLine.subSequence(k, k + 17);
					}
					break;
				}
			else if ((i = inputLine.indexOf("<b>Titles (Exact Matches)</b>")) > 0)
				for (int j = i; j < inputLine.length(); j++) {
					if ((k = inputLine.indexOf("/title/tt")) > 0) {
						movie = "http://www.imdb.com"
								+ inputLine.subSequence(k, k + 17);
					}
					break;
				}
		}

		in.close();
		return movie;
	}

	private static void findRating(String inputLine) {
		String rating;
		float rat = 0.0f;
		int i = 0, k;
		if ((k = inputLine.indexOf("Users rated this ")) > 0) {
			rating = (String) inputLine.subSequence(k + 17, k + 20);
			rat = Float.valueOf(rating).floatValue();
			movrating = rat;
		}

	}

	private static boolean findImage(String inputLine) {
		int k;
		if ((k = inputLine.indexOf("title-overview-widget\"")) > 0) {
			return true;
		}
		return false;
	}
	
	public static Image loadImage(String imgURL) throws Exception {
		URL moviedb = new URL(imgURL);

	    Image myImage = ImageIO.read(moviedb);
	    return myImage;
	}

	public static void showMovie(String myurl) throws Exception {
		System.out.println("Acquiring Movie's data..."+myurl);
		URL moviedb = new URL(myurl);
	
        URLConnection yc = moviedb.openConnection();
        yc.addRequestProperty("User-Agent", 
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
        LineNumberReader in = new LineNumberReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine, imgURL;
        int counter = 0, k = 0, l = 0;
        while ((inputLine = in.readLine()) != null) 
        {
           findRating(inputLine);
           if(findImage(inputLine)){
        	   counter = in.getLineNumber();
        	   for(int i = 0; i<5;i++) {
        		   in.readLine();
        	   }
        	   
        	   inputLine = in.readLine();
       			if(((k = inputLine.indexOf("img src=")) > 0) && ((l = inputLine.indexOf(".jpg"))>0)) {
       				
       				imgURL = (String) inputLine.subSequence(k+9, l+4);
       				moviePoster = loadImage(imgURL);
       				
       			}
       				
           }
        }
        
        in.close();
        System.out.println();
	}
}