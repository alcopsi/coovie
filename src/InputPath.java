import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputPath {

	public String getPath()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ein = null;      

		System.out.println("Enter your Moviepath: ");
		try{ein = br.readLine();}catch (IOException e){e.printStackTrace();}
		return ein;
    }
}