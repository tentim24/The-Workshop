/*Timothy Norrell
 * Dr. Leonard Brown
 * COSC 4315.001
 * 2/11/20
 * To read two strings and creates a matrix diplaying the "Edit Distance" Algorithm
 * In the class "getTerms", it removes the punctuation and capitalization in one line
 */

import java.util.Scanner;

class Assignment2b
{
	static String term1;
	static String term2;
	public static void main(String[]  args)
	{
		//Declaring variables
		String word1;
		String word2;
		
		System.out.print("Enter 1st word -> ");
		Scanner scan = new Scanner(System.in);
		word1 = scan.nextLine();
		System.out.print("Enter 2nd word -> ");
		word2 = scan.nextLine();
        term1 = CheckTerm(word1);
        term2 = CheckTerm(word2);
        int dist[][] = EditDistance(term1, term2);
        System.out.print("\t\t");
        char char1[] = term1.toCharArray();
        char char2[] = term2.toCharArray();
        
            //display the results
            for(char c: char2)
            {
        	   System.out.print(c + "\t");
            }
            System.out.println("");
            for (int i = 0; i < term1.length(); i++)
            {
            	if (i > 0)
            	System.out.print(char1[i-1] + "\t");
            	else
            	System.out.print("\t");
            	for (int n = 0; n < term2.length(); n++)
            	{
            		System.out.print(dist[i][n] + "\t");
            	}
            	System.out.println("");
            }
	}
    

   	//Reads document and puts words into a queue
	private static String CheckTerm(String word)
	{
            //replace everything that's not a letter or digit with a space, converting to lowercase
			word = word.replaceAll("[^a-zA-Z0-9' ]", "").toLowerCase() + " ";
			return word;
    }
	
	//Utilizing the EditDistance Algorithm
	private static int[][]  EditDistance(String term1, String term2)
	{
		int len1 = term1.length();
		int len2 = term2.length();
		int x [][] = new int [len1 + 1][len2 + 1];
		for(int i = 1; i < len1; i++)
		{
			x[i][0] = i;
		}
		for(int n = 1; n < len2; n++)
		{
			x[0][n] = n;
		}
		for(int i = 1; i < len2; i++)
		{
			for(int n = 1; n < len2; n++)
			{
				x[i][n] = Math.min(x[i-1][n-1] + f(i,n),
									Math.min(x[i-1][n] + 1,
									x[i][n-1] + 1));
			}
		}
		return x;
	}
	public static int f(int i, int n)
	{
	 	char char1[] = term1.toCharArray();
		char char2[] = term2.toCharArray();
		if (char1[i-1] == char2[n-1]) 
			return 0;
		else
			return 1;
	}
}