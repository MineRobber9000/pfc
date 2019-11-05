package PFCpack;

public class TeamStrategy
{
  private int passAgBonus;
  private int passYdBonus;
  private int rushAgBonus;
  private int rushYdBonus;
  private String stratDescription;
  private String stratName;
  
  public TeamStrategy()
  {
    stratName = "No Preference";
    stratDescription = "Will play a normal O/D with no bonus either way, but no penalties either.";
    rushYdBonus = 0;
    rushAgBonus = 0;
    passYdBonus = 0;
    passAgBonus = 0;
  }
  
  public TeamStrategy(String paramString1, String paramString2, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    stratName = paramString1;
    stratDescription = paramString2;
    rushYdBonus = paramInt1;
    rushAgBonus = paramInt2;
    passYdBonus = paramInt3;
    passAgBonus = paramInt4;
  }
  
  public int getPAB()
  {
    return passAgBonus;
  }
  
  public int getPYB()
  {
    return passYdBonus;
  }
  
  public int getRAB()
  {
    return rushAgBonus;
  }
  
  public int getRYB()
  {
    return rushYdBonus;
  }
  
  public String getStratDescription()
  {
    return stratDescription;
  }
  
  public String getStratName()
  {
    return stratName;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamStrategy
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */