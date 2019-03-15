import java.util.Scanner;
import java.io.*;

public class ReadTester {
	public static void main(String[] args) throws Exception {
		File f = new File("Data/Routes/Routes.txt");
		Scanner s = new Scanner(f);
		for (int i = 0; i < 2; i++) {
			String s1;
			String s2;
			s1 = s.next();
			s2 = s.next();
			System.out.println(s1 + ", " + s2);
		}

	}
}