import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.io.*;

public class MainClass 
{
	public static void main(String[] args) throws FileNotFoundException
	{
	/*	File file = new File("/Users/justinroberts/eclipse-workspace/M2_SDLC_Assingment/The_Raven.txt");
		Scanner scan = new Scanner(file);
		
		
		while (scan.hasNextLine())
		      System.out.println(scan.nextLine());  */
		
		HashMap<String, Integer> words = new HashMap<String, Integer>();

		// Retrieving the path as parameter to Method1()
		// above to get the file to be read
		getWords("/Users/justinroberts/eclipse-workspace/M2_SDLC_Assingment/The_Raven.txt", words);

		Map<String, Integer>sortedwords = sortByValue(words);
		// Variable holding the maximum
		// repeated word count in a file
		//int max = getMaxOccurrence(words);

		for (Iterator<Entry<String, Integer>> iterator = sortedwords.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, Integer> word = iterator.next();
			// Print and display word-count pair
			System.out.println("Word: " + word.getKey() + " \t\tTimes Counted: "+ word.getValue());
      // }
		}

}

	static void getWords(String fileName, Map<String, Integer> words) throws FileNotFoundException
	{
		// Creating a Scanner class object
		Scanner file = new Scanner(new File(fileName));
		
		// Condition check using hasNext() method which
		// holds true till there is word being read from the
		// file.
		// As the end of file content,condition violates
		while (file.hasNext()) {

			// Reading word using next() method
			String word = file.next();
			word = word.toLowerCase(); word = word.replaceAll("[^a-zA-Z0-9]", "");
			// Frequency count variable
			Integer count = words.get(word);

			// If the same word is repeating
			if (count != null) {

				// Incrementing corresponding count by unity
				// every time it repeats
				// while reading from the file
				count++;
			}
			else

				// If word never occurred after occurring
				// once, set count as unity
				count = 1;
			words.put(word, count);
		}

		// Close the file and free up the resources
		file.close();
	}

	// Method 2 - getMaxOccurrence()
	// To get maximum occurred Word
	static int getMaxOccurrence(Map<String, Integer> words)
	{
		// Initially set maximum count as unity
		int max = 1;

		// Iterating over above Map using for-each loop
		for (Entry<String, Integer> word :
			words.entrySet()) {

			// Condition check
			// Update current max value  with the value
			// exceeding unity in Map while traversing
			if (word.getValue() > max) {
				max = word.getValue();
			}
		}

		// Return the maximum value from the Map
		return max;
	}
	
	// function to sort hashmap by values
    public static Map<String, Integer> sortByValue(HashMap<String, Integer> sortw)
    {
//    	Map<String, Integer> res = sortw.entrySet().stream()
//    	        .sorted((o1, o2) -> (o2.getValue()).compareTo(o1.getValue()))
//    	        .limit(20)
//    	        .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
    	
    	Map<String, Integer> res = sortw.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
//        // Create a list from elements of HashMap
//        List<Map.Entry<String, Integer> > list =
//               new LinkedList<Map.Entry<String, Integer> >(sortw.entrySet());
// 
//        // Sort the list
//        Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
//            public int compare(Map.Entry<String, Integer> o1,
//                               Map.Entry<String, Integer> o2)
//            {
//                return (o2.getValue()).compareTo(o1.getValue());
//            }
//        });
//         
//        // put data from sorted list to hashmap
//        HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
//        	 for (Map.Entry<String, Integer> aa : list) {
//                 temp.put(aa.getKey(), aa.getValue());
//        	 }
//        	
//        return temp;
		return res;
    }
}
