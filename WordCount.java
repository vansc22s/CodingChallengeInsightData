import java.io.*;
import java.util.*;
//imports libraries that I need for this class

public class WordCount {

	//list of file names, read in alphabetical order
	//inside of that, for each file, read in every line in the file
	
	//map contains the word counts from the strings
	public static HashMap<String, Integer> map = 
			new HashMap<String, Integer>();
	
	
	//method to read the parameter file
	public static void readFile ( String filename ) throws IOException
	{
		//buffered reader used
		BufferedReader input = new BufferedReader
				(new FileReader(filename));

		//variable line to get the word count from
		String line;
			

		// read in all the lines from the file
		while ((line = input.readLine()) != null) {
			//this line is important, converts letters to lowercase
			//and ignores all punctuation
			line = line.replaceAll("[^a-zA-Z ]", "").toLowerCase();
			//this is to get each new word, tokenized by a space
			StringTokenizer st = new StringTokenizer(line, " ");

			// process each word wrt the map
			while (st.hasMoreTokens()) {
				//each time in the while loop declare string word
				//which gets the next token of the stringTokenizer
				String word = st.nextToken();
				
				//if there is a word (getting the word is not null) 
				if (map.get(word) != null) {
					// another occurence of an existing
					// word
					int count = map.get(word);
					//increment count by one
					count++;
					//add one to the count for that word
					map.put(word, count);
				} else {
					// first occurence of this word
					map.put(word, 1);
				}
			}
		}
	}
			
		//method which is used to put in output
		public static void 	outputResults() throws FileNotFoundException, UnsupportedEncodingException
		{
			//use PrintWriter to print results into the text file
			PrintWriter writer = new PrintWriter( "./wc_output/wc_result.txt", "UTF-8");
		
			//a string iterator for each new word you're iterating through
			Iterator<String> iter;
			//string for the words in the count
			Set<String> words;

			// Create an array list for the list of words
			ArrayList<String> list = 
				new ArrayList<String>(map.size());

			// Get the set of words from the map
			words = map.keySet();

			// Loop over the words in the map
			iter = words.iterator();
			while (iter.hasNext()) {
				//each time we loop through 
				//set variable word equal to the next iteration
				String word = iter.next();

				// Add it into the list
				list.add(word);
				
			}

			// sort the list (which implements
			// Comparable)
			Collections.sort(list);
			
			//iter now for the ArrayList
			iter = list.iterator();
			//while there's still another element in list
			while( iter.hasNext()) 
			{
				//set a string to take the value of the next element
				String word = iter.next();
				
				//write the resulting word and its count, with a tab in between
				writer.println(word + "\t" + map.get(word));
				
			}
			
			//close the writer
			writer.close();
	}
	
	
	
	   public static void main(String[] args)  throws IOException{

			//a file called wc_input
		   File folder = new File("./wc_input");
		   //file array for files in folder
		   File[] listOfFiles = folder.listFiles();

		   //for loop through the listOfFIles array
		   for (int i = 0; i < listOfFiles.length; i++) 
		   {
			   //if the ith entry is a file
		     if (listOfFiles[i].isFile()) 
		     {
		    	 //invoke readFile on the name of it within wc_input
		    	 readFile("./wc_input/" + listOfFiles[i].getName());
		   
		   }  
		     }
		   //invoke outputResults function to write the results to a text file
		   outputResults();
	   }}
	
