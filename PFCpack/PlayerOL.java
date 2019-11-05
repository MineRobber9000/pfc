package PFCpack;

import java.util.ArrayList;

public class PlayerOL
  extends Player
{
  protected int ratOLBkP;
  protected int ratOLBkR;
  protected int ratOLPow;
  
  public PlayerOL(Team paramTeam, String[] paramArrayOfString)
  {
    super(paramTeam, paramArrayOfString);
  }
  
  public PlayerOL(String paramString, int paramInt)
  {
    super(paramString, "OL", paramInt);
  }
  
  public PlayerOL(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "OL", true);
  }
  
  public PlayerOL(String paramString1, Team paramTeam, int[] paramArrayOfInt, String paramString2)
  {
    super(paramString1, paramTeam, "OL", false);
    setVariables(paramArrayOfInt, paramString2);
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Strength: " + getLetterGrade(ratOLPow));
    localArrayList.add("Run Block: " + getLetterGrade(ratOLBkR) + ">Pass Block: " + getLetterGrade(ratOLBkP));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Strength: " + getLetterGrade(ratOLPow));
    localArrayList.add("Run Block: " + getLetterGrade(ratOLBkR) + ">Pass Block: " + getLetterGrade(ratOLBkP));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add(" > ");
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Potential: " + getLetterGrade(ratPot) + ">Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Strength: " + getLetterGrade(ratOLPow));
    localArrayList.add("Run Block: " + getLetterGrade(ratOLBkR) + ">Pass Block: " + getLetterGrade(ratOLBkP));
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
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratOLPow) + ", " + getLetterGrade(ratOLBkR) + ", " + getLetterGrade(ratOLBkP) + ")";
  }
  
  public int[] getRatings()
  {
    return new int[] { ratOLPow, ratOLBkR, ratOLBkP };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratOLPow * 3 + ratOLBkR + ratOLBkP) / 5);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratOLPow = paramArrayOfInt[0];
    ratOLBkR = paramArrayOfInt[1];
    ratOLBkP = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerOL
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */