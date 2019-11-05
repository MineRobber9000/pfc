package PFCpack;

public class TeamStreak
{
  private int endYear;
  private int startYear;
  private int streakLength;
  private String streakTeam;
  
  public TeamStreak(int paramInt1, int paramInt2, int paramInt3, String paramString)
  {
    startYear = paramInt1;
    endYear = paramInt2;
    streakLength = paramInt3;
    streakTeam = paramString;
  }
  
  public void addWin(int paramInt)
  {
    streakLength += 1;
    endYear = paramInt;
  }
  
  public void changeAbbr(String paramString)
  {
    streakTeam = paramString;
  }
  
  public int getEndYear()
  {
    return endYear;
  }
  
  public int getStartYear()
  {
    return startYear;
  }
  
  public String getStreakCSV()
  {
    return streakLength + "," + streakTeam + "," + startYear + "," + endYear;
  }
  
  public int getStreakLength()
  {
    return streakLength;
  }
  
  public String getTeam()
  {
    return streakTeam;
  }
  
  public void resetStreak(int paramInt)
  {
    startYear = paramInt;
    endYear = paramInt;
    streakLength = 0;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamStreak
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */