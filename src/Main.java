import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
	static int find(int i, int[] mat) {
		if (i == mat[i]) {
			return i;
		}
		return mat[i] = find(mat[i], mat);
	}
	public static void main(String[] args) {
		int matrix = 0;
		int[] mat = null;
		String str = null, answer = null;
		File in = new File("in.txt");
		try{
			BufferedReader reader = new BufferedReader(new FileReader(in.getAbsoluteFile()));
			str = reader.readLine();
			String[] mas = str.split(" ");
			matrix = Integer.parseInt(mas[0]);
			mat = new int[matrix];
			for (int i = 0; i < matrix; i++) {
				mat[i] = i;
			}
			while ((str = reader.readLine()) != null) {
				String[] k = str.split(" == ");
				if (k.length == 2) {
					mat[find(mat[Integer.parseInt(k[1].replaceAll("x", "")) - 1], mat)] = find(Integer.parseInt(k[0].replaceAll("x", "")) - 1, mat);
				}
			}
			BufferedReader reader1 = new BufferedReader(new FileReader(in.getAbsoluteFile()));
			reader.readLine();
			while ((str = reader1.readLine()) != null) {
				String[] k = str.split(" != ");
				if (k.length == 2) {
					if (find(Integer.parseInt(k[1].replaceAll("x", "")) - 1, mat) == find(Integer.parseInt(k[0].replaceAll("x", "")) - 1, mat)) {
						answer = "No";
						break;
					}
				}
			}
			for (int i = 0; i < matrix; i++) {
				System.out.print((find(mat[i], mat) + 1) + " ");
			}
			reader.close();
			reader1.close();
		} catch(IOException e) {System.out.print("Error"); }
		File out = new File("out.txt");
		try {
			PrintWriter writer = new PrintWriter(out.getAbsoluteFile());
			if (answer == null) {
				writer.print("Yes");
			} else {
				writer.println(answer);
			}
			writer.close();
		} catch (FileNotFoundException e) { }
	}
}