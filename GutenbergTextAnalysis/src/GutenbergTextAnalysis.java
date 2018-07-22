import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class GutenbergTextAnalysis {
	private File file;
	private Scanner sc;
	private Queue<String> lines;
	private int totalNumWords;
	private HashMap<String, Integer> words = new HashMap<>();
	GutenbergTextAnalysis(String filename) throws FileNotFoundException{
		file = new File(filename);
		sc = new Scanner(file);
		lines = readLines();
		totalNumWords = analyzeWords();
	}
	public Queue<String> readLines() {
		Queue<String> lines = new LinkedList<>();
		while(sc.hasNextLine()) {
			String line = sc.nextLine();
			lines.add(line);
		}
		return lines;
	}
	public int getTotalNumberOfWords() {

		return totalNumWords;
	}
	public int analyzeWords() {
		int total = 0;
		HashSet<Character> seperators = new HashSet<>();
		seperators.add(' ');
		seperators.add('.');
		seperators.add('/');
		seperators.add('"');
		seperators.add('(');
		seperators.add(')');
		seperators.add(',');
		seperators.add('?');
		seperators.add("'".charAt(0));
		seperators.add(';');
		seperators.add('!');
		seperators.add('\n');
		seperators.add('\t');
		seperators.add('[');
		seperators.add(']');
		seperators.add('{');
		seperators.add('}');
		seperators.add(':');
		seperators.add('~');
		seperators.add('-');
		seperators.add('_');
		
		for(String line: lines) {
			int start=0,end = 0;
			boolean lastCharSep = false;
			for(int i = 0;i<line.length();i++) {
				if(seperators.contains(line.charAt(i))) {
					if(!lastCharSep) {
						if(!words.containsKey(line.substring(start, end))){
							words.put(line.substring(start, end),0);
						} else {
							words.replace(line.substring(start, end), words.get(line.substring(start, end))+1);
						}
						start =end;
						total++;
						lastCharSep = true;
					}
					start++;
				} else {
					lastCharSep = false;
				}
				end++;
			}
		}
		return total;
	}
	public int getTotalUniqueWords() {
		return words.size();
		
	}
	public static void main(String args[]) throws FileNotFoundException {
		GutenbergTextAnalysis gt = new GutenbergTextAnalysis("D:\\gutenberg\\GutenbergTextAnalysis\\src\\texts\\1342.txt");
		System.out.println(gt.getTotalUniqueWords());
	}
}
