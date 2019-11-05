package PFCpack;

import java.util.ArrayList;

public class PlayerF7
  extends Player
{
  public int ratF7Pas;
  public int ratF7Pow;
  public int ratF7Rsh;
  
  public PlayerF7(String paramString, int paramInt)
  {
    super(paramString, "F7", paramInt);
  }
  
  public PlayerF7(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "F7", true);
  }
  
  public PlayerF7(String paramString, Team paramTeam, int[] paramArrayOfInt)
  {
    super(paramString, paramTeam, "F7", false);
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Strength: " + getLetterGrade(ratF7Pow));
    localArrayList.add("Run Stop: " + getLetterGrade(ratF7Rsh) + ">Pass Pressure: " + getLetterGrade(ratF7Pas));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Strength: " + getLetterGrade(ratF7Pow));
    localArrayList.add("Run Stop: " + getLetterGrade(ratF7Rsh) + ">Pass Pressure: " + getLetterGrade(ratF7Pas));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add(" > ");
    return localArrayList;
  }
  
  public String getInfoForLineup()
  {
    if (injury != null) {
      return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " " + injury.toString();
    }
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratF7Pow) + ", " + getLetterGrade(ratF7Rsh) + ", " + getLetterGrade(ratF7Pas) + ")";
  }
  
  public int[] getRatings()
  {
    return new int[] { ratF7Pow, ratF7Rsh, ratF7Pas };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratF7Pow * 3 + ratF7Rsh + ratF7Pas) / 5);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratF7Pow = paramArrayOfInt[0];
    ratF7Rsh = paramArrayOfInt[1];
    ratF7Pas = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerF7
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */