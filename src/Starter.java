public class Starter {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception 
	{
		InputPath i = new InputPath(); 
		String p = i.getPath(); // get ScanPath
		Scanner scan = new Scanner(p);
		scan.scanPath();
		scan.showResult();
	}
}