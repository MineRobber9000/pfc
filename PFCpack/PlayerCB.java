package PFCpack;

import java.util.ArrayList;

public class PlayerCB
  extends Player
{
  public int ratCBCov;
  public int ratCBSpd;
  public int ratCBTkl;
  
  public PlayerCB(Team paramTeam, String[] paramArrayOfString)
  {
    super(paramTeam, paramArrayOfString);
  }
  
  public PlayerCB(String paramString, int paramInt)
  {
    super(paramString, "CB", paramInt);
    position = "CB";
  }
  
  public PlayerCB(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "CB", true);
    position = "CB";
  }
  
  public PlayerCB(String paramString1, Team paramTeam, int[] paramArrayOfInt, String paramString2)
  {
    super(paramString1, paramTeam, "CB", false);
    setVariables(paramArrayOfInt, paramString2);
    position = "CB";
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Coverage: " + getLetterGrade(ratCBCov));
    localArrayList.add("Speed: " + getLetterGrade(ratCBSpd) + ">Tackling: " + getLetterGrade(ratCBTkl));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Coverage: " + getLetterGrade(ratCBCov));
    localArrayList.add("Speed: " + getLetterGrade(ratCBSpd) + ">Tackling: " + getLetterGrade(ratCBTkl));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add(" > ");
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Potential: " + getLetterGrade(ratPot) + ">Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Coverage: " + getLetterGrade(ratCBCov));
    localArrayList.add("Speed: " + getLetterGrade(ratCBSpd) + ">Tackling: " + getLetterGrade(ratCBTkl));
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
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratCBCov) + ", " + getLetterGrade(ratCBSpd) + ", " + getLetterGrade(ratCBTkl) + ")";
  }
  
  public int[] getRatings()
  {
    return new int[] { ratCBCov, ratCBSpd, ratCBTkl };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratCBCov * 2 + ratCBSpd + ratCBTkl) / 4);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratCBCov = paramArrayOfInt[0];
    ratCBSpd = paramArrayOfInt[1];
    ratCBTkl = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerCB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */