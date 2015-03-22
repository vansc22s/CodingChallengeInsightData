	import java.io.*;
import java.util.*;
//import these libraries for this class

public class RunningMedian {


	//int array to hold the number of words in each line of the text
	 public static ArrayList<Integer> wordCounts = new ArrayList<Integer>();
	
	//count for num lines so far
	public static int numLines = 0;
	
		//list of file names, read in alphabetical order
		//inside of that, for each file, read in every line in the file
	
	
		   public static void main(String[] args)  throws FileNotFoundException, IOException{
			   
			   //file for the input folder
			   File folder = new File("./wc_input");
			   //array for the list of files from the folder
			   File[] listOfFiles = folder.listFiles();
			   
				//public variable for the writer to the med result file
				PrintWriter writer = new PrintWriter( "./wc_output/med_result.txt", "UTF-8");
				
				//for loop to go through the length of listOfFiles
				 for (int i = 0; i < listOfFiles.length; i++) 
				 {
					 	//if the ith entry is a file
					    if (listOfFiles[i].isFile()) 
					    {
					    	//a string gets the name of the file in the directory wc_input
					       String filename = "./wc_input/" + listOfFiles[i].getName();
					    
					       //buffered reader used to get input
					   	   BufferedReader input = new BufferedReader
					   			    (new FileReader(filename));

					   	   //string to hold the value of the line
					   	   String line;
					   	   //double to take the value of the current median
					   	   double median = 0.00;

				// read in all the lines from the file
				while ((line = input.readLine()) != null) 
				{
					//StringTokenizer to tokenize the line with spaces
					StringTokenizer st = new StringTokenizer(line, " ");
					//word count is equal to the number of tokens counted
					int wordCount = st.countTokens();
					
					//adds wordCount to the next open spot in the array
					wordCounts.add(wordCount);
						
					//add one to num lines
					numLines ++;
						
					//sort the wordCounts array
					Collections.sort(wordCounts);
					
					//automatically truncates
					int j = numLines / 2;
						
					//if numLines is odd
					if( numLines %2 == 1)
					{
						//the median is the middle value, i
						median = wordCounts.get(j);
					}
					//else there are an even number of lines
					else
					{
						//the median is the average of the two middle values
						median = ((double)wordCounts.get(j - 1) + (double)wordCounts.get(j)) / 2.00;
					}
					
					//write the median to the file
					writer.println(median);
					}


		   }
				 }
				 //close the writer
				 writer.close();
		   }
}
