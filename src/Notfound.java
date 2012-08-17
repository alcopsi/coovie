import java.awt.Image;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;


public class Notfound {

	private String movietitle;
	private String year;
	private Image moviePoster;
	private static float movrating;
	
	Notfound(String movietitle, String year) throws Exception{
		this.movietitle = movietitle;
		this.year = year;
		
		getMovie(setURL());
	}
	
	private String setURL()
	{
		String tmptitle = movietitle.replace( " ", "+" );
		String myurl = "http://www.google.de/search?btnI&hl=de&q=inurl:imdb.com+%22"+tmptitle+"%22";
		return myurl;
	}
	
	private void getMovie(String myurl) throws Exception
	{
		System.out.println("Acquiring Movie's data via google..."+myurl);
		URL google = new URL(myurl);
	
	    URLConnection yc = google.openConnection();
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
	
	private static Image loadImage(String imgURL) throws Exception {
		URL moviedb = new URL(imgURL);

	    Image myImage = ImageIO.read(moviedb);
	    return myImage;
	}
	
	public float getMovrating(){
		return movrating;
	}
	
	public Image getMoviePoster(){
		return moviePoster;
	}
		
	
}
