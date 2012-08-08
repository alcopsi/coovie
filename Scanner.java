import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

public class Scanner {
	
	private String path; // Searchpatch
	private String moviepath;
	//private int moviecount; // All gripped movies
	private File f;
	private File[] fileArray;
	private Movielist liste;
	Scanner(String path) throws Exception
	{
		this.path = path;
		f = new File(path);
		fileArray = f.listFiles();
	}
	
	public void scanPath() throws Exception
	{
		liste = new Movielist();
		if (f != null)
		{
			for(int i = 0; i < fileArray.length; i++)
			{
				if (fileArray[i].isDirectory()) 
				{
					String moviename = fileArray[i].getName();
					moviepath = path +"/"+ moviename;
					Namefix n = new Namefix(moviename);
					
					Movie m = new Movie(n.getFixedMovieTitle(), moviepath, checkAvailable(n.getFixedMovieTitle()));
					//System.out.println(m.getMovietitle() + n.getFixedMovieTitle());
					liste.addMovie(m);
				}
			}
		}
	}
	
	private boolean checkAvailable(String movietitle) {
		String inputLine, strLine,text = "";
		//movietitle = movietitle.substring(0, movietitle.length()-1);
		Vector auxvec = new Vector();
		try{
			createMovieFile();
			FileInputStream fstream = new FileInputStream("movies.txt");
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			int k;
			
			while ((inputLine = br.readLine()) != null)   {
       			if((k = inputLine.indexOf("#"))>0) {
       				
       				strLine = (String) inputLine.subSequence(0,k);
       				//System.out.println(strLine);
       				auxvec.add(strLine);
       			}
			}
			 //Close the input stream
			in.close();
		}catch (Exception e){//Catch exception if any
				  System.err.println("Error: " + e.getMessage());
			}
		//System.out.println(text);
		for(int i = 0; i<auxvec.size();i++){
			if(auxvec.get(i).equals(movietitle)){
				return true;
			}
		}
		return false;
	}
	
	private void createMovieFile() {
		File f = new File("movies.txt");
		  if(!f.exists())
			try {
				f.createNewFile();
			} catch (IOException e) {
				  System.err.println("Error: " + e.getMessage());
			}
	}
	
	public void showResult() throws IOException {
		liste.sortList();
		liste.showList();
		HTML doku = new HTML(liste);
		liste.saveToFile();
	}
	

}
