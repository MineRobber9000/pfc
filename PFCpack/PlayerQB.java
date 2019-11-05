package PFCpack;

import java.util.ArrayList;

public class PlayerQB
  extends Player
{
  protected int careerInt;
  protected int careerPassAtt;
  protected int careerPassComp;
  protected int careerPassYards;
  protected int careerSacked;
  protected int careerTDs;
  protected int ratPassAcc;
  protected int ratPassEva;
  protected int ratPassPow;
  protected int statsInt;
  protected int statsPassAtt;
  protected int statsPassComp;
  protected int statsPassYards;
  protected int statsSacked;
  protected int statsTD;
  
  public PlayerQB(Team paramTeam, String[] paramArrayOfString)
  {
    super(paramTeam, paramArrayOfString);
    statsPassAtt = 0;
    statsPassComp = 0;
    statsTD = 0;
    statsInt = 0;
    statsPassYards = 0;
    statsSacked = 0;
    careerPassAtt = 0;
    careerPassComp = 0;
    careerTDs = 0;
    careerInt = 0;
    careerPassYards = 0;
    careerSacked = 0;
  }
  
  public PlayerQB(String paramString, int paramInt)
  {
    super(paramString, "QB", paramInt);
    statsPassAtt = 0;
    statsPassComp = 0;
    statsTD = 0;
    statsInt = 0;
    statsPassYards = 0;
    statsSacked = 0;
    careerPassAtt = 0;
    careerPassComp = 0;
    careerTDs = 0;
    careerInt = 0;
    careerPassYards = 0;
    careerSacked = 0;
  }
  
  public PlayerQB(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "QB", true);
    statsPassAtt = 0;
    statsPassComp = 0;
    statsTD = 0;
    statsInt = 0;
    statsPassYards = 0;
    statsSacked = 0;
    careerPassAtt = 0;
    careerPassComp = 0;
    careerTDs = 0;
    careerInt = 0;
    careerPassYards = 0;
    careerSacked = 0;
  }
  
  public PlayerQB(String paramString1, Team paramTeam, int[] paramArrayOfInt1, String paramString2, int[] paramArrayOfInt2)
  {
    super(paramString1, paramTeam, "QB", false);
    setVariables(paramArrayOfInt1, paramString2);
    statsPassAtt = paramArrayOfInt2[0];
    statsPassComp = paramArrayOfInt2[1];
    statsTD = paramArrayOfInt2[2];
    statsInt = paramArrayOfInt2[3];
    statsPassYards = paramArrayOfInt2[4];
    statsSacked = paramArrayOfInt2[5];
    careerPassAtt = paramArrayOfInt2[6];
    careerPassComp = paramArrayOfInt2[7];
    careerTDs = paramArrayOfInt2[8];
    careerInt = paramArrayOfInt2[9];
    careerPassYards = paramArrayOfInt2[10];
    careerSacked = paramArrayOfInt2[11];
  }
  
  public void advanceSeason()
  {
    super.advanceSeason();
    careerPassAtt += statsPassAtt;
    careerPassComp += statsPassComp;
    careerTDs += statsTD;
    careerInt += statsInt;
    careerPassYards += statsPassYards;
    careerSacked += statsSacked;
    statsPassAtt = 0;
    statsPassComp = 0;
    statsTD = 0;
    statsInt = 0;
    statsPassYards = 0;
    statsSacked = 0;
  }
  
  public String getCSV()
  {
    return super.getCSV() + ">" + statsPassAtt + "," + statsPassComp + "," + statsTD + "," + statsInt + "," + statsPassYards + "," + statsSacked + "," + careerPassAtt + "," + careerPassComp + "," + careerTDs + "," + careerInt + "," + careerPassYards + "," + careerSacked;
  }
  
  public ArrayList<String> getCareerStatsList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("TD/Int: " + (statsTD + careerTDs) + "/" + (statsInt + careerInt) + ">Comp Percent: " + (statsPassComp + careerPassComp) * 100 / (statsPassAtt + careerPassAtt + 1) + "%");
    localArrayList.add("Pass Yards: " + (statsPassYards + careerPassYards) + " yds>Yards/Att: " + (statsPassYards + careerPassYards) * 10 / (statsPassAtt + careerPassAtt + 1) / 10.0D + " yds");
    localArrayList.add("Yds/Game: " + (statsPassYards + careerPassYards) / (getGamesPlayed() + careerGamesPlayed) + " yds/g>Sacks: " + (statsSacked + careerSacked));
    localArrayList.addAll(super.getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("TD/Int: " + statsTD + "/" + statsInt + ">Comp Percent: " + statsPassComp * 100 / (statsPassAtt + 1) + "%");
    localArrayList.add("Pass Yards: " + statsPassYards + " yds>Yards/Att: " + statsPassYards * 10 / (statsPassAtt + 1) / 10.0D + " yds");
    localArrayList.add("Yds/Game: " + statsPassYards / getGamesPlayed() + " yds/g>Sacks: " + statsSacked);
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Pass Strength: " + getLetterGrade(ratPassPow));
    localArrayList.add("Accuracy: " + getLetterGrade(ratPassAcc) + ">Evasion: " + getLetterGrade(ratPassEva));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("TD/Int: " + statsTD + "/" + statsInt + ">Comp Percent: " + statsPassComp * 100 / (statsPassAtt + 1) + "%");
    localArrayList.add("Pass Yards: " + statsPassYards + " yds>Yards/Att: " + statsPassYards * 10 / (statsPassAtt + 1) / 10.0D + " yds");
    localArrayList.add("Yds/Game: " + statsPassYards / getGamesPlayed() + " yds/g>Sacks: " + statsSacked);
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Pass Strength: " + getLetterGrade(ratPassPow));
    localArrayList.add("Accuracy: " + getLetterGrade(ratPassAcc) + ">Evasion: " + getLetterGrade(ratPassEva));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add(" > ");
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Potential: " + getLetterGrade(ratPot) + ">Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Pass Strength: " + getLetterGrade(ratPassPow));
    localArrayList.add("Accuracy: " + getLetterGrade(ratPassAcc) + ">Evasion: " + getLetterGrade(ratPassEva));
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
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratPassPow) + ", " + getLetterGrade(ratPassAcc) + ", " + getLetterGrade(ratPassEva) + ")";
  }
  
  public int getMVPScore()
  {
    return statsTD * 140 - statsInt * 250 + statsPassYards;
  }
  
  public int[] getRatings()
  {
    return new int[] { ratPassPow, ratPassAcc, ratPassEva };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratPassPow * 3 + ratPassAcc * 4 + ratPassEva) / 8);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratPassPow = paramArrayOfInt[0];
    ratPassAcc = paramArrayOfInt[1];
    ratPassEva = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerQB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */