import java.awt.Image;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;


public class MyDB {
	private String title;
	private String path;
	private float rating;
	private Image poster;
	
	
	MyDB(String movietitle) {
		getMovieInfo(movietitle);
		
	}
	private void getMovieInfo(String movietitle) {
		try{
			FileInputStream fstream = new FileInputStream("movies.txt");
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			
			String inputLine,aux;
			int k,l;
			
			while ((inputLine = br.readLine()) != null)   {
       			if((k = inputLine.indexOf("#"))>0) {
       				
       				aux = (String) inputLine.subSequence(0,k);
       				if(aux.equals(movietitle)) {
       					title = aux;
       				
	       				if((l = inputLine.indexOf("~"))>0){
	           				path = (String) inputLine.subSequence(k+1,l);	
	               			rating = Float.valueOf((String)inputLine.subSequence(l+1,l+4)).floatValue();
	
	       				}
       				}
       				
       			
       			}
			}
			 //Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
				  System.err.println("Error: " + e.getMessage());
			}
	}
	
	public String getTitle(){
		return title;
	}
	public String getPath(){
		return path;
	}
	public float getRating(){
		return rating;
	}
	
	public Image getPoster(){
		String imgpath;
		imgpath = path + "/poster.jpg";
		return poster;
	}
	
	private Image loadImage(String imgpath) {
	    // Read from a file
		System.out.println(imgpath);
	    File file = new File(imgpath);
	    Image image = null;
		try {
			image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return image;
		
	}
}
