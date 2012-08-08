import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.*;

public class HTML {

	HTML(Movielist mlist) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("meindoku.html"));
		bw.write("<!DOCTYPE html>\n");
		bw.write("<html lang=\" \">\n");
		bw.write("\n<head>\n<meta charset=\"utf-8\">\n<title>Movies</title><link rel=\"stylesheet\" type=\"text/css\" href=\"style.css\">");
		bw.write("<link href=\"scrollbar/jquery.mCustomScrollbar.css\" rel=\"stylesheet\" type=\"text/css\" />\n <script src=\"http://ajax.googleapis.com/ajax/libs/jquery/1.7/jquery.min.js\"></script>\n<script src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js\"></script>\n<script src=\"scrollbar/jquery.mousewheel.min.js\"></script>\n<script src=\"scrollbar/jquery.mCustomScrollbar.js\"></script>\n");
		bw.write("</head>\n<body><h1>Movies:</h1>\n");
		bw.write("<div id=\"box\">\n");

		bw.write("<script>(function($){$(window).load(function(){$(\"#thumb\").mCustomScrollbar();});})(jQuery);</script>");
		bw.write("<ul id=\"slider\">\n");
		
		Movie aux;
		//int elem = mlist.getLength();
		//int pagination = elem/4;
		
		
		for(int i = 0; i < mlist.getLength(); i++){
			aux = mlist.getMovie(i);
			bw.write("<li id=\"" + (i+1) +"\">\n");
			bw.write("<a href=\""+ aux.getMoviePath() + "\"><img src=\""+ aux.getMoviePath()+"/poster.jpg\" alt=\""+ aux.getMovietitle() + "\" /></a>\n");
			bw.write("<span><a href=\""+ aux.getMoviePath() + "\"><p> "+aux.getMovietitle() +" </p></a></span>\n");
			bw.write("</li>\n");
		}
		
		bw.write("</ul>\n");
		
		
		
		bw.write("<ul id=\"thumb\">\n");
		
		for(int i = 0; i < mlist.getLength(); i++) {
			aux = mlist.getMovie(i);
			bw.write("<li><a href=\"#"+(i+1)+"\">"+ aux.getMovietitle()+ " - " + aux.getRating() + "</a></li>\n");
		}
		
		/*
		for(int i = 0; i < mlist.getLength(); i++) {
			aux = mlist.getMovie(i);
			bw.write("<li><a href=\"#"+(i+1)+"\"><img src=\""+aux.getMoviePath()+"\""+" alt=\""+aux.getMovietitle()+"\" width=\"50\" height=\"50\" /></a></li>\n");
		}*/
		
		bw.write("</ul>\n");
		/*
		bw.write("<div id=\"pag\">\n");

		for(int i = 0; i<pagination; i++) {
			//System.out.println(i);
			bw.write("<a>" + i+1 + "</a>\n");
		}
		
		bw.write("</div>\n");*/
		
		bw.write("</div>\n");
		
		
		bw.write("</body></html>\n");
		bw.close();
	}
	
	private void makePoster() {
		
	}
}
