/*Timothy Norrell
 * Dr. Leonard Brown
 * COSC 4315.001
 * 3/2/20
 * To read from a file and create Permutation index to display. 
 * In the class "getTerms", it removes the punctuation and capitalization in one line
 */
import java.util.*;
import java.io.*;
 
class Assignment2a
{
    
	public static void main(String[]  args)
	{
		//Declaring variables
		TreeMap<String, String> PermuIndex = new TreeMap<String, String>();
   
		try
		{
			System.out.print("Enter name of a directory for the Text File> ");
			Scanner scan = new Scanner(System.in);
			File dir = new File(scan.nextLine());
			LinkedList<String> words = new LinkedList<String>();
            String term;
            words = getTerms(dir);
            term = words.poll();
			while(term != null)
			{
				if(!PermuIndex.containsValue(term))
				{
				String StrName = "";
				StrName = "$" + term;
				PermuIndex.put(StrName, term);
						
						//Rotating the "$"
						for(int i = 0; i < term.length(); i++)
						{
							StrName = (StrName + StrName.charAt(0)).substring(1);
							PermuIndex.put(StrName, term);
						}
				}
				term = words.poll();
			}
         
            Object terms[] = PermuIndex.keySet().toArray();
            for(Object t: terms)
            {
                System.out.println("Term: " + t + "\t" + "Permutation: " + PermuIndex.get(t));
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
		    Scanner scan = new Scanner(System.in);
		    String line = "";
		    String term = "";
		    Scanner sc = new Scanner(f);
	        while (sc.hasNextLine())
		    {
				line = sc.nextLine();
                //replace everything that's not a letter or digit with a space, converting to lowercase
			    line = line.replaceAll("[^a-zA-Z0-9 ]", " ").toLowerCase() + " ";
			    for(char c: line.toCharArray())
			    {
				    if(c == ' ')
				    {
					    if(!term.equals(""))
					    {
						    words.add(term);
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