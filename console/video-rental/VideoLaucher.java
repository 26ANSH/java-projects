import java.util.Scanner;

public class VideoLaucher
{
	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int choice, user;
		String admin_pass= "admin";
		VideoStore videoStore=new VideoStore();
		do {
			System.out.println("~~~~~~ Welcome to Video Library ~~~~~~");
			System.out.println("1. Login as GUEST");
			System.out.println("2. Login as ADMIN");
			System.out.println("3. Logout");
			user=input.nextInt();
			switch (user) 
			{
			case 1:
			{
				System.err.println("GUEST Successfuly Logged IN");
				
				do {
					System.out.println("========= GUEST MENU =========");
					System.out.println("1. Rent Video");
					System.out.println("2. Return Video");
					System.out.println("3. Receive Rating");
					System.out.println("4. List Inventory");
					System.out.println("5. Logout");
					
					choice=input.nextInt();
					switch (choice) {
					case 1:
						System.out.print("Enter the name of the video you want to check out: ");
						videoStore.doCheckout(input.next());
						break;
					case 2:
						System.out.print("Enter the name of the video you want to Return:");
						videoStore.doReturn(input.next());;
						break;
					case 3:
						System.out.println("Enter the name of the video you want to Rate: ");
						videoStore.receiveRating(input.next(), input.nextInt());
						break;
					case 4:
						videoStore.listInventory();
						break;
					case 5:
						System.err.println("GUEST logout Successful");
						break;
					}
			}while(!(choice==5));		
			}break;
			case 2:
			{
				System.out.println("Enter ADMIN Password");
				String password =input.next();
				if(password.equals(admin_pass))
				{
					System.err.println("ADMIN Successfuly Logged IN");
				}
				else
				{
					System.err.println("WRONG PASSWORD");
					break;
				}
				do {
					System.out.println("========= ADMIN MENU =========");
					System.out.println("1. ADD Video");
					System.out.println("2. List Inventory");
					System.out.println("3. Change ADMIN password");
					System.out.println("4. Logout");
					
					choice=input.nextInt();
					switch (choice) {
					case 1:
						System.out.println("Enter the name of the video you want to add: ");
						videoStore.addVideo(input.next());
						break;
					case 2:
						videoStore.listInventory();
						break;
					case 3:
						System.out.println("Enter Your new Password");
						admin_pass = input.next();
						System.err.println("Admin Password Updated");
						break;
					case 4:
						System.err.println("ADMIN logout Successful");
						break;
					}
			}while(!(choice==4));		
			}break;
			case 3:
			{
				System.err.println("Good Bye ...!! \nThanks for using the application");
			}
			}
		}while(!(user==3));		
		input.close();
	}
}