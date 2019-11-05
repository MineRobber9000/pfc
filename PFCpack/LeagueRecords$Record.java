package PFCpack;

public class LeagueRecords$Record
{
  private String holder;
  private int number;
  private int year;
  
  public LeagueRecords$Record(LeagueRecords paramLeagueRecords, int paramInt1, String paramString, int paramInt2)
  {
    number = paramInt1;
    holder = paramString;
    year = paramInt2;
  }
  
  private void changeAbbr(String paramString)
  {
    String[] arrayOfString = holder.split(" ");
    holder = paramString;
    int i = 1;
    while (i < arrayOfString.length)
    {
      holder = (holder + " " + arrayOfString[i]);
      i += 1;
    }
  }
  
  public String getHolder()
  {
    return holder;
  }
  
  public int getNumber()
  {
    return number;
  }
  
  public int getYear()
  {
    return year;
  }
}

/* Location:
 * Qualified Name:     PFCpack.LeagueRecords.Record
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */