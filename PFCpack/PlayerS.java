package PFCpack;

import java.util.ArrayList;

public class PlayerS
  extends Player
{
  protected int ratSCov;
  protected int ratSSpd;
  protected int ratSTkl;
  
  public PlayerS(Team paramTeam, String[] paramArrayOfString)
  {
    super(paramTeam, paramArrayOfString);
  }
  
  public PlayerS(String paramString, int paramInt)
  {
    super(paramString, "S", paramInt);
  }
  
  public PlayerS(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "S", true);
  }
  
  public PlayerS(String paramString1, Team paramTeam, int[] paramArrayOfInt, String paramString2)
  {
    super(paramString1, paramTeam, "S", false);
    setVariables(paramArrayOfInt, paramString2);
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Coverage: " + getLetterGrade(ratSCov));
    localArrayList.add("Speed: " + getLetterGrade(ratSSpd) + ">Tackling: " + getLetterGrade(ratSTkl));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Coverage: " + getLetterGrade(ratSCov));
    localArrayList.add("Speed: " + getLetterGrade(ratSSpd) + ">Tackling: " + getLetterGrade(ratSTkl));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add(" > ");
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Potential: " + getLetterGrade(ratPot) + ">Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Coverage: " + getLetterGrade(ratSCov));
    localArrayList.add("Speed: " + getLetterGrade(ratSSpd) + ">Tackling: " + getLetterGrade(ratSTkl));
    localArrayList.add("Desired Contract: " + Contract.getContractFA(this).toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public String getInfoForLineup()
  {
    if (injury != null) {
      return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " " + injury.toString();
    }
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratSCov) + ", " + getLetterGrade(ratSSpd) + ", " + getLetterGrade(ratSTkl) + ")";
  }
  
  public int[] getRatings()
  {
    return new int[] { ratSCov, ratSSpd, ratSTkl };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratSCov * 2 + ratSSpd + ratSTkl) / 4);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratSCov = paramArrayOfInt[0];
    ratSSpd = paramArrayOfInt[1];
    ratSTkl = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerS
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */