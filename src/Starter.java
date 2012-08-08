
public class Starter {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		String p = "movies";
		Scanner scan = new Scanner(p);
		scan.scanPath();
		scan.showResult();
	}

}
