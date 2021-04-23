public class Video
{
	String videoName;
	boolean checkout = true;
	int rating;
	public Video(String name)
	{
		videoName=name;
	}
	public String getName()
	{
		return videoName;
	}
	public void doCheckout()
	{
		checkout=false;
		System.err.println("Video "+'"'+ getName()+'"' +" checked out successfully.");
	}
	public void doReturn()
	{
		checkout=true;
		System.err.println("Video "+'"'+ getName()+'"' +" returned successfully.");
	}
	public void receiveRating(int rating)
	{
		this.rating=rating;
	}
	public int getRating()
	{
		return rating;
	}
	public boolean getCheckout()
	{
		return checkout;
	}
}
