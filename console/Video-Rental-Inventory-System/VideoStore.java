public class VideoStore 
{
	static int qnt = 0;
	Video[] store;
	int getindex(String name)
	{
		for(int k=0; k<qnt; k++)
		{
			if(name.equalsIgnoreCase(store[k].getName()))
			{
				return k;
			}
		}
		return -1;
	}
	public VideoStore() 
	{
		store=new Video[10];
	}
	public void addVideo(String name)
	{
		if(getindex(name) != -1)
		{
			System.err.println("Video Already Exists");
			return;
		}
		store[qnt]=new Video(name);
		System.err.println("Video "+'"'+store[qnt].getName()+'"'+" added successfully");
		qnt++;
	}
	public void doCheckout(String name)
	{
		int temp = getindex(name);
		if(temp == -1)
		{
			System.err.println("Video Does not Exist");
			return;
		}
		store[temp].doCheckout();
	}
	public void doReturn(String name)
	{
		int temp = getindex(name);
		if(temp == -1)
		{
			System.err.println("Video Does not Exist");
			return;
		}
		store[temp].doReturn();
	}
	public void receiveRating(String name, int rating) {
		
		int temp = getindex(name);
		if(temp == -1)
		{
			System.err.println("Video Does not Exist");
			return;
		}
		System.err.println("Rating "+'"'+store[temp].getRating()+'"'+" has been mapped to the Video ''"+store[temp].getName()+'"');

	}
	public void listInventory() 
	{
		System.out.println("------------------------------------------");
		System.out.println("Video Name | Checkout Status | Rating");
		
		for(int k=0; k<qnt; k++)
		{
			System.out.println(store[k].getName()+"  \t|\t" +store[k].getCheckout()+ "\t|\t"+ store[k].getRating());
		}
		System.out.println("------------------------------------------");
	}
}