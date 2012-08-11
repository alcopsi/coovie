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
		
		System.out.println("Movie1: "+movietitle);

		if(getYear() == "")
		{
			fixedmovietitle = movietitle;
			finalCheck(movietitle);
			System.out.println("Went 1");}
		else
		{
			System.out.println("Went 2");
			int pos = movietitle.indexOf( getYear() );
			fixedmovietitle = (pos >= 0 ? movietitle.substring( 0, pos ) : movietitle);
			finalCheck(fixedmovietitle);
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
