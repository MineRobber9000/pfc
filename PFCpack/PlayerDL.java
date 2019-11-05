package PFCpack;

import java.util.ArrayList;

public class PlayerDL
  extends Player
{
  public int ratDLPas;
  public int ratDLPow;
  public int ratDLRsh;
  
  public PlayerDL(Team paramTeam, String[] paramArrayOfString)
  {
    super(paramTeam, paramArrayOfString);
  }
  
  public PlayerDL(String paramString, int paramInt)
  {
    super(paramString, "DL", paramInt);
  }
  
  public PlayerDL(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "DL", true);
  }
  
  public PlayerDL(String paramString1, Team paramTeam, int[] paramArrayOfInt, String paramString2)
  {
    super(paramString1, paramTeam, "DL", false);
    setVariables(paramArrayOfInt, paramString2);
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Strength: " + getLetterGrade(ratDLPow));
    localArrayList.add("Run Stop: " + getLetterGrade(ratDLRsh) + ">Pass Pressure: " + getLetterGrade(ratDLPas));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Strength: " + getLetterGrade(ratDLPow));
    localArrayList.add("Run Stop: " + getLetterGrade(ratDLRsh) + ">Pass Pressure: " + getLetterGrade(ratDLPas));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add(" > ");
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Potential: " + getLetterGrade(ratPot) + ">Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Strength: " + getLetterGrade(ratDLPow));
    localArrayList.add("Run Stop: " + getLetterGrade(ratDLRsh) + ">Pass Pressure: " + getLetterGrade(ratDLPas));
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
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratDLPow) + ", " + getLetterGrade(ratDLRsh) + ", " + getLetterGrade(ratDLPas) + ")";
  }
  
  public int[] getRatings()
  {
    return new int[] { ratDLPow, ratDLRsh, ratDLPas };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratDLPow * 3 + ratDLRsh + ratDLPas) / 5);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratDLPow = paramArrayOfInt[0];
    ratDLRsh = paramArrayOfInt[1];
    ratDLPas = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerDL
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */