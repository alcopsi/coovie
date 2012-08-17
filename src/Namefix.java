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
		

		if(getYear() == "")
		{
			fixedmovietitle = movietitle;
			germanCheck(movietitle);
			tausendachtzigCheck(movietitle);
			siebenhunderzwanzigCheck(movietitle);
			
		}
		else
		{
			int pos = movietitle.indexOf( getYear() );
			fixedmovietitle = (pos >= 0 ? movietitle.substring( 0, pos ) : movietitle);
			germanCheck(fixedmovietitle);
			tausendachtzigCheck(movietitle);
			siebenhunderzwanzigCheck(movietitle);
		}
		
	}
	
	public String getYear(){
		
		Pattern yearPattern = Pattern.compile("\\d{4}?");
		Matcher yearMatch = yearPattern.matcher(movietitle);
		if (yearMatch.find() == true)
		{
			String year = yearMatch.group();
			
			int tmp = Integer.parseInt(year);
			if (tmp == 720)
				return "";
			else if ( tmp == 1080)
				return "";
			return year;
		}
		return "";
	
	}
	
	private void germanCheck(String fixedmovietitle){
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
	
	private void tausendachtzigCheck(String fixedmovietitle){
		String x = fixedmovietitle;
		Pattern taPattern = Pattern.compile("1080p");
		Matcher taMatch = taPattern.matcher(x);
		if (taMatch.find() == true)
		{
			String german = taMatch.group();
			int pos = movietitle.indexOf(german);
			this.fixedmovietitle = (pos >= 0 ? movietitle.substring( 0, pos ) : movietitle);
		}
	}
	
	private void siebenhunderzwanzigCheck(String fixedmovietitle){
		String x = fixedmovietitle;
		Pattern szPattern = Pattern.compile("720p");
		Matcher szMatch = szPattern.matcher(x);
		if (szMatch.find() == true)
		{
			String german = szMatch.group();
			int pos = movietitle.indexOf(german);
			this.fixedmovietitle = (pos >= 0 ? movietitle.substring( 0, pos ) : movietitle);
		}
	}
	
	public String getFixedMovieTitle() {
		return fixedmovietitle;
	}
}
