import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Movie {
	
	private String movietitle = null;
	private String moviepath = null;
	private String imdburl = null;
	private static Image moviePoster = null;
	private float rating = 0;
	//Movie m;

	Movie(String movietitle, String moviepath, boolean mov_exists) throws Exception{
		
		this.movietitle = movietitle;
		this.moviepath = moviepath;
		//System.out.println(mov_exists);
		if(mov_exists) {
			MyDB getdata = new MyDB(movietitle);
			this.movietitle = getdata.getTitle();
			this.moviepath = getdata.getPath();
			this.rating = getdata.getRating();
			//System.out.println(this.movietitle);
			this.moviePoster = getdata.getPoster();
		}
		else{
			try {
				IMDB checktitle = new IMDB(movietitle);
				rating = checktitle.movrating;
				moviePoster = checktitle.moviePoster;
				saveImage(moviepath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				saveNotFoundTitles();
			}
		}
	}
	
	private void saveNotFoundTitles() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("Not Found.txt"));
			bw.append(movietitle + "\n");
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getMovietitle(){
		return movietitle;
	}
	
	public void saveImage(String moviepath) throws IOException {
		BufferedImage aux = toBufferedImage(moviePoster);
	    File outputfile = new File(moviepath + "/poster.jpg");
	    ImageIO.write(aux, "jpg", outputfile);
	}
	private static BufferedImage toBufferedImage(Image src) {
        int w = src.getWidth(null);
        int h = src.getHeight(null);
        int type = BufferedImage.TYPE_INT_RGB;  // other options
        BufferedImage dest = new BufferedImage(w, h, type);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }
	
	public void setImdburl(String imdburl){
		
		this.imdburl = imdburl;
	}
	
	public void setRating(float rating){
		
		this.rating = rating;
	}
	public float getRating() {
		return rating;
	}
	
	public String getMoviePath() {
		return moviepath;
	}
	
	/* sinn?
	public void setObject(Movie n){
		
		this.m = n;
	}*/
}
