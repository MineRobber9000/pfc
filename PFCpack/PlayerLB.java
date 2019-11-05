package PFCpack;

import java.util.ArrayList;

public class PlayerLB
  extends Player
{
  public int ratLBPas;
  public int ratLBRsh;
  public int ratLBTck;
  
  public PlayerLB(Team paramTeam, String[] paramArrayOfString)
  {
    super(paramTeam, paramArrayOfString);
  }
  
  public PlayerLB(String paramString, int paramInt)
  {
    super(paramString, "LB", paramInt);
  }
  
  public PlayerLB(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "LB", true);
  }
  
  public PlayerLB(String paramString1, Team paramTeam, int[] paramArrayOfInt, String paramString2)
  {
    super(paramString1, paramTeam, "LB", false);
    setVariables(paramArrayOfInt, paramString2);
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Tackling: " + getLetterGrade(ratLBTck));
    localArrayList.add("Run Stop: " + getLetterGrade(ratLBRsh) + ">Pass Coverage: " + getLetterGrade(ratLBPas));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Tackling: " + getLetterGrade(ratLBTck));
    localArrayList.add("Run Stop: " + getLetterGrade(ratLBRsh) + ">Pass Coverage: " + getLetterGrade(ratLBPas));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add(" > ");
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Potential: " + getLetterGrade(ratPot) + ">Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Tackling: " + getLetterGrade(ratLBTck));
    localArrayList.add("Run Stop: " + getLetterGrade(ratLBRsh) + ">Pass Coverage: " + getLetterGrade(ratLBPas));
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
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratLBTck) + ", " + getLetterGrade(ratLBRsh) + ", " + getLetterGrade(ratLBPas) + ")";
  }
  
  public int[] getRatings()
  {
    return new int[] { ratLBTck, ratLBRsh, ratLBPas };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratLBTck * 3 + ratLBRsh + ratLBPas) / 5);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratLBTck = paramArrayOfInt[0];
    ratLBRsh = paramArrayOfInt[1];
    ratLBPas = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerLB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */