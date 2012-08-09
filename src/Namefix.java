import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Namefix {
	
	private String movietitle = null;
	private int year = 0;
	private String fixedmovietitle = null;

	Namefix(String m) throws Exception{
		
		this.movietitle = m;
		replaceDots();
		removeReleaseinfo();

	}
	
	private void replaceDots(){
		
		movietitle = movietitle.replace( ".", " " );
	}
	
	private void removeReleaseinfo() throws Exception{

		int pos = movietitle.indexOf( getYear() );

		fixedmovietitle = (pos >= 0 ? movietitle.substring( 0, pos ) : movietitle);
		finalCheck(fixedmovietitle);
		
		//m.setObject(m);
		//Movielist.add
	}
	
	public String getYear(){
		
		Pattern yearPattern = Pattern.compile("\\d+");
		Matcher yearMatch = yearPattern.matcher(movietitle);
		if (yearMatch.find() == true)
		{
			String year = yearMatch.group();
			//System.out.println(year);
			return year;
		}
		return "";
	
	}
	
	private void finalCheck(String fixedmovietitle){
		String x = fixedmovietitle;
		Pattern germanPattern = Pattern.compile("German");
		Matcher germanMatch = germanPattern.matcher(x);
		if (germanMatch.find() == true)
		{
			String german = germanMatch.group();
			int pos = movietitle.indexOf(german);
			this.fixedmovietitle = (pos >= 0 ? movietitle.substring( 0, pos ) : movietitle);
		}
	}
	
	public String getFixedMovieTitle() {
		return fixedmovietitle;
	}
}
