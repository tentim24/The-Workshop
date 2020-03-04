import java.util.Scanner;

/* Timothy Norrell
 * Dr. Nary Subramanian
 * COSC 3355.001
 * 3/4/20
 * 
 */
public class Timothy_Norrell 
{
	public static void main (String [] arguments)
	{
		//Declaring Variables
		boolean exit = false;
		String cmd;
		Scanner scan = new Scanner(System.in);
		
		while(exit != true)
		{
			System.out.print(">>>");
			cmd = scan.nextLine();
			cmd = cmd.toLowerCase();
			
		}
		
		System.out.println("Program Exited.");
		
		scan.close();
	}
}
