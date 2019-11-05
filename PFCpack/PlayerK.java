package PFCpack;

import java.util.ArrayList;

public class PlayerK
  extends Player
{
  protected int careerFGAtt;
  protected int careerFGMade;
  protected int careerXPAtt;
  protected int careerXPMade;
  protected int ratKickAcc;
  protected int ratKickFum;
  protected int ratKickPow;
  protected int statsFGAtt;
  protected int statsFGMade;
  protected int statsXPAtt;
  protected int statsXPMade;
  
  public PlayerK(Team paramTeam, String[] paramArrayOfString)
  {
    super(paramTeam, paramArrayOfString);
    statsXPAtt = 0;
    statsXPMade = 0;
    statsFGAtt = 0;
    statsFGMade = 0;
    careerXPAtt = 0;
    careerXPMade = 0;
    careerFGAtt = 0;
    careerFGMade = 0;
  }
  
  public PlayerK(String paramString, int paramInt)
  {
    super(paramString, "K", paramInt);
    statsXPAtt = 0;
    statsXPMade = 0;
    statsFGAtt = 0;
    statsFGMade = 0;
    careerXPAtt = 0;
    careerXPMade = 0;
    careerFGAtt = 0;
    careerFGMade = 0;
  }
  
  public PlayerK(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "K", true);
    statsXPAtt = 0;
    statsXPMade = 0;
    statsFGAtt = 0;
    statsFGMade = 0;
    careerXPAtt = 0;
    careerXPMade = 0;
    careerFGAtt = 0;
    careerFGMade = 0;
  }
  
  public PlayerK(String paramString1, Team paramTeam, int[] paramArrayOfInt1, String paramString2, int[] paramArrayOfInt2)
  {
    super(paramString1, paramTeam, "K", false);
    setVariables(paramArrayOfInt1, paramString2);
    statsXPAtt = paramArrayOfInt2[0];
    statsXPMade = paramArrayOfInt2[1];
    statsFGAtt = paramArrayOfInt2[2];
    statsFGMade = paramArrayOfInt2[3];
    careerXPAtt = paramArrayOfInt2[4];
    careerXPMade = paramArrayOfInt2[5];
    careerFGAtt = paramArrayOfInt2[6];
    careerFGMade = paramArrayOfInt2[7];
  }
  
  public void advanceSeason()
  {
    super.advanceSeason();
    careerXPAtt += statsXPAtt;
    careerXPMade += statsXPMade;
    careerFGAtt += statsFGAtt;
    careerFGMade += statsFGMade;
    statsXPAtt = 0;
    statsXPMade = 0;
    statsFGAtt = 0;
    statsFGMade = 0;
  }
  
  public String getCSV()
  {
    return super.getCSV() + ">" + statsXPAtt + "," + statsXPMade + "," + statsFGAtt + "," + statsFGMade + "," + careerXPAtt + "," + careerXPMade + "," + careerFGAtt + "," + careerFGMade;
  }
  
  public ArrayList<String> getCareerStatsList()
  {
    ArrayList localArrayList = new ArrayList();
    if (statsXPAtt + careerXPAtt > 0)
    {
      localArrayList.add("XP Made/Att: " + (statsXPMade + careerXPMade) + "/" + (statsXPAtt + careerXPAtt) + ">XP Percent: " + (statsXPMade + careerXPMade) * 100 / (statsXPAtt + careerXPAtt) + "%");
      if (statsFGAtt + careerFGAtt <= 0) {
        break label221;
      }
      localArrayList.add("FG Made/Att: " + (statsFGMade + careerFGMade) + "/" + (statsFGAtt + careerFGAtt) + ">FG Percent: " + (statsFGMade + careerFGMade) * 100 / (statsFGAtt + careerFGAtt) + "%");
    }
    for (;;)
    {
      localArrayList.addAll(super.getCareerStatsList());
      return localArrayList;
      localArrayList.add("XP Made/Att: 0/0>XP Percent: 0%");
      break;
      label221:
      localArrayList.add("FG Made/Att: 0/0>FG Percent: 0%");
    }
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    if (statsXPAtt > 0)
    {
      localArrayList.add("XP Made/Att: " + statsXPMade + "/" + statsXPAtt + ">XP Percent: " + statsXPMade * 100 / statsXPAtt + "%");
      if (statsFGAtt <= 0) {
        break label369;
      }
      localArrayList.add("FG Made/Att: " + statsFGMade + "/" + statsFGAtt + ">FG Percent: " + statsFGMade * 100 / statsFGAtt + "%");
    }
    for (;;)
    {
      localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
      localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Kick Strength: " + getLetterGrade(ratKickPow));
      localArrayList.add("Kick Accuracy: " + getLetterGrade(ratKickAcc) + ">Clumsiness: " + getLetterGrade(ratKickFum));
      localArrayList.add("Contract: " + contract.toString());
      localArrayList.add("[B]CAREER STATS:");
      localArrayList.addAll(getCareerStatsList());
      return localArrayList;
      localArrayList.add("XP Made/Att: 0/0>XP Percent: 0%");
      break;
      label369:
      localArrayList.add("FG Made/Att: 0/0>FG Percent: 0%");
    }
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    if (statsXPAtt > 0)
    {
      localArrayList.add("XP Made/Att: " + statsXPMade + "/" + statsXPAtt + ">XP Percent: " + statsXPMade * 100 / statsXPAtt + "%");
      if (statsFGAtt <= 0) {
        break label360;
      }
      localArrayList.add("FG Made/Att: " + statsFGMade + "/" + statsFGAtt + ">FG Percent: " + statsFGMade * 100 / statsFGAtt + "%");
    }
    for (;;)
    {
      localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
      localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Kick Strength: " + getLetterGrade(ratKickPow));
      localArrayList.add("Kick Accuracy: " + getLetterGrade(ratKickAcc) + ">Clumsiness: " + getLetterGrade(ratKickFum));
      localArrayList.add("Contract: " + contract.toString());
      localArrayList.add(" > ");
      return localArrayList;
      localArrayList.add("XP Made/Att: 0/0>XP Percent: 0%");
      break;
      label360:
      localArrayList.add("FG Made/Att: 0/0>FG Percent: 0%");
    }
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Potential: " + getLetterGrade(ratPot) + ">Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Kick Strength: " + getLetterGrade(ratKickPow));
    localArrayList.add("Kick Accuracy: " + getLetterGrade(ratKickAcc) + ">Clumsiness: " + getLetterGrade(ratKickFum));
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
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratKickPow) + ", " + getLetterGrade(ratKickAcc) + ", " + getLetterGrade(ratKickFum) + ")";
  }
  
  public int getMVPScore()
  {
    return (int)((statsFGMade * 5 + statsXPMade) * (statsFGMade / statsFGAtt)) + ratOvr;
  }
  
  public int[] getRatings()
  {
    return new int[] { ratKickPow, ratKickAcc, ratKickFum };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratKickPow + ratKickAcc) / 2);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratKickPow = paramArrayOfInt[0];
    ratKickAcc = paramArrayOfInt[1];
    ratKickFum = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerK
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */