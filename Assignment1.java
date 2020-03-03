/*Timothy Norrell
 * Dr. Leonard Brown
 * COSC 4315.001
 * 2/11/20
 * To read from a set of files and create an inverted index to display.
 * Used Porter Stemmer for stemming words. 
 * Created a string of stop words to compare the terms with.
 * In the class "getTerms", it removes the punctuation and capitalization in one line
 */
import java.util.*;
import java.io.*;
 
class Assignment1
{
    
	public static void main(String[]  args)
	{
		//Declaring variables
		TreeMap<String, List<Integer>> InvertedIndex = new TreeMap<String, List<Integer>>();
		String stopw[] = {"i","a","about","an","are","as","at","be","by","com","for","from","how","in",
                          "is","it","of","on","or","that","the","this","to","was","what","when","where",
                          "who","will","with","the","www"};   
                                                 
	    List<String> stopWords = Arrays.asList(stopw);
	    Stack<String> stack = new Stack<String>();
	    
		try
		{
			System.out.print("Enter name of a directory for Text Files> ");
			Scanner scan = new Scanner(System.in);
			File dir = new File(scan.nextLine());
			//Creates array of files
			File[] fileList = dir.listFiles();	

			LinkedList<String> words = new LinkedList<String>();
            int curdoc = 0;
            String term;
            
			for (File f : fileList)
			{
                words = getTerms(f);
                term = words.poll();
				while(term != null)
				{
					if(!stopWords.contains(term))
					{
						if(InvertedIndex.containsKey(term))
						{
							if(!InvertedIndex.get(term).contains(curdoc))
							{
								InvertedIndex.get(term).add(curdoc);
							}
						}
						else
						{
							InvertedIndex.put(term, new LinkedList<Integer>());
							InvertedIndex.get(term).add(curdoc);
						}	
					}
                    term = words.poll();
				}
                curdoc++;
            }
            String temp = "";
            Object terms[] = InvertedIndex.keySet().toArray();
            for(Object t: terms)
            {
                System.out.print("Term: " + t + " Frequency: " + InvertedIndex.get(t).size() + " Docs: ");
                for(int doc:InvertedIndex.get(t))
                {
                    System.out.print(doc + " ");
                }
                System.out.println();
            }
        }
		catch(Exception e)
		{
		    System.out.println("Error: " + e.toString());
		}
    }

   	//Reads document and puts words into a queue
	private static LinkedList<String> getTerms(File f)
	{
		LinkedList<String> words = new LinkedList<String>();
        try
        {
		    Stemmer stem;
		    Scanner scan = new Scanner(System.in);
		    String line = "";
		    String term = "";
		    Scanner sc = new Scanner(f);
	        while (sc.hasNextLine())
		    {
				line = sc.nextLine();
                //replace everything that's not a letter or digit with a space, converting to lowercase
			    line = line.replaceAll("[^a-zA-Z0-9' ]", " ").toLowerCase() + " ";
			    for(char c: line.toCharArray())
			    {
				    if(c == ' ')
				    {
					    if(!term.equals(""))
					    {
						    stem = new Stemmer();
						    stem.add(term.toCharArray(), term.length());
						    stem.stem();
						    words.add(stem.toString());
						    term = "";
					    }
				    }
				    else
				    {
					    term = term + c;
				    }
			    }
		    }
	    }
	    catch(Exception e)
	    {
		    System.out.println("Did not find file.");
	    }
        return words;
    }
}