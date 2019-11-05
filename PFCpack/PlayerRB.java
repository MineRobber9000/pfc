package PFCpack;

import java.util.ArrayList;

public class PlayerRB
  extends Player
{
  protected int careerFumbles;
  protected int careerRushAtt;
  protected int careerRushYards;
  protected int careerTDs;
  protected int ratRushEva;
  protected int ratRushPow;
  protected int ratRushSpd;
  protected int statsFumbles;
  protected int statsRushAtt;
  protected int statsRushYards;
  protected int statsTD;
  
  public PlayerRB(Team paramTeam, String[] paramArrayOfString)
  {
    super(paramTeam, paramArrayOfString);
    statsRushAtt = 0;
    statsRushYards = 0;
    statsTD = 0;
    statsFumbles = 0;
    careerRushAtt = 0;
    careerRushYards = 0;
    careerTDs = 0;
    careerFumbles = 0;
  }
  
  public PlayerRB(String paramString, int paramInt)
  {
    super(paramString, "RB", paramInt);
    statsRushAtt = 0;
    statsRushYards = 0;
    statsTD = 0;
    statsFumbles = 0;
    careerRushAtt = 0;
    careerRushYards = 0;
    careerTDs = 0;
    careerFumbles = 0;
  }
  
  public PlayerRB(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "RB", true);
    statsRushAtt = 0;
    statsRushYards = 0;
    statsTD = 0;
    statsFumbles = 0;
    careerRushAtt = 0;
    careerRushYards = 0;
    careerTDs = 0;
    careerFumbles = 0;
  }
  
  public PlayerRB(String paramString1, Team paramTeam, int[] paramArrayOfInt1, String paramString2, int[] paramArrayOfInt2)
  {
    super(paramString1, paramTeam, "RB", false);
    setVariables(paramArrayOfInt1, paramString2);
    statsRushAtt = paramArrayOfInt2[0];
    statsRushYards = paramArrayOfInt2[1];
    statsTD = paramArrayOfInt2[2];
    statsFumbles = paramArrayOfInt2[3];
    careerRushAtt = paramArrayOfInt2[4];
    careerRushYards = paramArrayOfInt2[5];
    careerTDs = paramArrayOfInt2[6];
    careerFumbles = paramArrayOfInt2[7];
  }
  
  public void advanceSeason()
  {
    super.advanceSeason();
    careerRushAtt += statsRushAtt;
    careerRushYards += statsRushYards;
    careerTDs += statsTD;
    careerFumbles += statsFumbles;
    statsRushAtt = 0;
    statsRushYards = 0;
    statsTD = 0;
    statsFumbles = 0;
  }
  
  public String getCSV()
  {
    return super.getCSV() + ">" + statsRushAtt + "," + statsRushYards + "," + statsTD + "," + statsFumbles + "," + careerRushAtt + "," + careerRushYards + "," + careerTDs + "," + careerFumbles;
  }
  
  public ArrayList<String> getCareerStatsList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("TDs: " + (statsTD + careerTDs) + ">Fumbles: " + (statsFumbles + careerFumbles));
    localArrayList.add("Rush Yards: " + (statsRushYards + careerRushYards) + " yds>Yards/Att: " + (statsRushYards + careerRushYards) * 10 / (statsRushAtt + careerRushAtt + 1) / 10.0D + " yds");
    localArrayList.add("Yds/Game: " + (statsRushYards + careerRushYards) / (getGamesPlayed() + careerGamesPlayed) + " yds/g>Rush Att: " + (statsRushAtt + careerRushAtt));
    localArrayList.addAll(super.getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("TDs: " + statsTD + ">Fumbles: " + statsFumbles);
    localArrayList.add("Rush Yards: " + statsRushYards + " yds>Yards/Att: " + statsRushYards * 10 / (statsRushAtt + 1) / 10.0D + " yds");
    localArrayList.add("Yds/Game: " + statsRushYards / getGamesPlayed() + " yds/g>Rush Att: " + statsRushAtt);
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Rush Power: " + getLetterGrade(ratRushPow));
    localArrayList.add("Rush Speed: " + getLetterGrade(ratRushSpd) + ">Evasion: " + getLetterGrade(ratRushEva));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("TDs: " + statsTD + ">Fumbles: " + statsFumbles);
    localArrayList.add("Rush Yards: " + statsRushYards + " yds>Yards/Att: " + statsRushYards * 10 / (statsRushAtt + 1) / 10.0D + " yds");
    localArrayList.add("Yds/Game: " + statsRushYards / getGamesPlayed() + " yds/g>Rush Att: " + statsRushAtt);
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Rush Power: " + getLetterGrade(ratRushPow));
    localArrayList.add("Rush Speed: " + getLetterGrade(ratRushSpd) + ">Evasion: " + getLetterGrade(ratRushEva));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add(" > ");
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Potential: " + getLetterGrade(ratPot) + ">Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Rush Power: " + getLetterGrade(ratRushPow));
    localArrayList.add("Rush Speed: " + getLetterGrade(ratRushSpd) + ">Evasion: " + getLetterGrade(ratRushEva));
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
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratRushPow) + ", " + getLetterGrade(ratRushSpd) + ", " + getLetterGrade(ratRushEva) + ")";
  }
  
  public int getMVPScore()
  {
    return statsTD * 100 - statsFumbles * 50 + (int)(statsRushYards * 2.4D);
  }
  
  public int[] getRatings()
  {
    return new int[] { ratRushPow, ratRushSpd, ratRushEva };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratRushPow + ratRushSpd + ratRushEva) / 3);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratRushPow = paramArrayOfInt[0];
    ratRushSpd = paramArrayOfInt[1];
    ratRushEva = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerRB
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */