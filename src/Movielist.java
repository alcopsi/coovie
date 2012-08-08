import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collections;
import java.util.Vector;

public class Movielist {
	
	private Vector movie_vec;

	Movielist(){
		
		movie_vec = new Vector();
				
	}
	
	public void addMovie(Movie m){
		
		movie_vec.add(m);
	}
	//BubbleSort -> spaeter was aus der Klasse List(?) oder Collections (Collections.sort() implementieren)
	public void sortList() {
		Movie a,b;
		for(int i = 0; i<movie_vec.size(); i++) {
			for(int j = i; j< movie_vec.size(); j++) {
				a = (Movie)movie_vec.get(i);
				b = (Movie)movie_vec.get(j);
				if(a.getRating() <  b.getRating()) {
					Collections.swap(movie_vec,i,j);
				}
			}
		}
	}
	
	public void showList() {
		Movie aux;
		for(int i = 0; i < movie_vec.size(); i++) {
			aux = (Movie) movie_vec.get(i);
			//System.out.println(aux.getRating());
		}
	}
	
	public void saveToFile() {
		Movie aux;
		try{
			  // Create file 
			  FileWriter fstream = new FileWriter("movies.txt");
			  BufferedWriter out = new BufferedWriter(fstream);
				for(int i = 0; i < movie_vec.size(); i++) {
					aux = (Movie) movie_vec.get(i);
					out.write(aux.getMovietitle()+ "#" + aux.getMoviePath()+ "~" + aux.getRating()+"\n");
				}
			  //Close the output stream
			  out.close();
		}catch (Exception e){//Catch exception if any
				  System.err.println("Error: " + e.getMessage());
		}
	}
	
	public int getLength() {
		return movie_vec.size();
	}
	
	public Movie getMovie(int index) {
		return (Movie) movie_vec.get(index);
	}
}
