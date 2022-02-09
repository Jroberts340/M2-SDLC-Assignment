import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.io.*;

public class MainClass 
{
	public static void main(String[] args) throws FileNotFoundException
	{	
		HashMap<String, Integer> words = new HashMap<String, Integer>();

		// Pass text through getWords Method and retrieve word count
		getWords("/Users/justinroberts/eclipse-workspace/M2_SDLC_Assingment/The_Raven.txt", words);

		Map<String, Integer>sortedwords = sortByValue(words);

		for (Entry<String, Integer> word : sortedwords.entrySet()) 
		{
			
			System.out.println("\tWord: " + word.getKey() + " \t\tTimes Counted: "+ word.getValue());
		}
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Method to turn file contents into String and count words
	static void getWords(String fileName, Map<String, Integer> words) throws FileNotFoundException
	{
		Scanner file = new Scanner(new File(fileName));
		
		while (file.hasNext())
		{
			String word = file.next();
			word = word.toLowerCase(); word = word.replaceAll("[^a-zA-Z0-9]", "");
			// Frequency count variable
			Integer count = words.get(word);

			// If the same word is repeating
			if (count != null) 
			{
				count++;
			}
			else
				// If word never occurred after occurring once, set count as unity
				count = 1;
			words.put(word, count);
		}
		
		// Close the file
		file.close();
	}
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Method to sort hashmap by values and limit hashmap output to only 20
    public static Map<String, Integer> sortByValue(HashMap<String, Integer> wordsort)
    {	
    	Map<String, Integer> res = wordsort.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(20)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    	
    	return res;
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
		
    }
}
