package PFCpack;

import java.util.ArrayList;

public class PlayerWR
  extends Player
{
  protected int careerDrops;
  protected int careerFumbles;
  protected int careerRecYards;
  protected int careerReceptions;
  protected int careerTD;
  protected int careerTargets;
  protected int ratRecCat;
  protected int ratRecEva;
  protected int ratRecSpd;
  protected int statsDrops;
  protected int statsFumbles;
  protected int statsRecYards;
  protected int statsReceptions;
  protected int statsTD;
  protected int statsTargets;
  
  public PlayerWR(Team paramTeam, String[] paramArrayOfString)
  {
    super(paramTeam, paramArrayOfString);
    statsTargets = 0;
    statsReceptions = 0;
    statsRecYards = 0;
    statsTD = 0;
    statsDrops = 0;
    statsFumbles = 0;
    careerTargets = 0;
    careerReceptions = 0;
    careerRecYards = 0;
    careerTD = 0;
    careerDrops = 0;
    careerFumbles = 0;
  }
  
  public PlayerWR(String paramString, int paramInt)
  {
    super(paramString, "WR", paramInt);
    statsTargets = 0;
    statsReceptions = 0;
    statsRecYards = 0;
    statsTD = 0;
    statsDrops = 0;
    statsFumbles = 0;
    careerTargets = 0;
    careerReceptions = 0;
    careerRecYards = 0;
    careerTD = 0;
    careerDrops = 0;
    careerFumbles = 0;
  }
  
  public PlayerWR(String paramString, Team paramTeam)
  {
    super(paramString, paramTeam, "WR", true);
    statsTargets = 0;
    statsReceptions = 0;
    statsRecYards = 0;
    statsTD = 0;
    statsDrops = 0;
    statsFumbles = 0;
    careerTargets = 0;
    careerReceptions = 0;
    careerRecYards = 0;
    careerTD = 0;
    careerDrops = 0;
    careerFumbles = 0;
  }
  
  public PlayerWR(String paramString1, Team paramTeam, int[] paramArrayOfInt1, String paramString2, int[] paramArrayOfInt2)
  {
    super(paramString1, paramTeam, "WR", false);
    setVariables(paramArrayOfInt1, paramString2);
    statsTargets = paramArrayOfInt2[0];
    statsReceptions = paramArrayOfInt2[1];
    statsRecYards = paramArrayOfInt2[2];
    statsTD = paramArrayOfInt2[3];
    statsDrops = paramArrayOfInt2[4];
    statsFumbles = paramArrayOfInt2[5];
    careerTargets = paramArrayOfInt2[6];
    careerReceptions = paramArrayOfInt2[7];
    careerRecYards = paramArrayOfInt2[8];
    careerTD = paramArrayOfInt2[9];
    careerDrops = paramArrayOfInt2[10];
    careerFumbles = paramArrayOfInt2[11];
  }
  
  public void advanceSeason()
  {
    super.advanceSeason();
    careerTargets += statsTargets;
    careerReceptions += statsReceptions;
    careerRecYards += statsRecYards;
    careerTD += statsTD;
    careerDrops += statsDrops;
    careerFumbles += statsFumbles;
    statsTargets = 0;
    statsReceptions = 0;
    statsRecYards = 0;
    statsTD = 0;
    statsDrops = 0;
    statsFumbles = 0;
  }
  
  public String getCSV()
  {
    return super.getCSV() + ">" + statsTargets + "," + statsReceptions + "," + statsRecYards + "," + statsTD + "," + statsDrops + "," + statsFumbles + "," + careerTargets + "," + careerReceptions + "," + careerRecYards + "," + careerTD + "," + careerDrops + "," + careerFumbles;
  }
  
  public ArrayList<String> getCareerStatsList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("TDs/Fumbles: " + (statsTD + careerTD) + "/" + (statsFumbles + careerFumbles) + ">Catch Percent: " + (statsReceptions + careerReceptions) * 100 / (statsTargets + careerTargets + 1) + "%");
    localArrayList.add("Rec Yards: " + (statsRecYards + careerRecYards) + " yds>Yards/Tgt: " + (statsRecYards + careerRecYards) * 10 / (statsTargets + careerTargets + 1) / 10.0D + " yds");
    localArrayList.add("Yds/Game: " + (statsRecYards + careerRecYards) / (getGamesPlayed() + careerGamesPlayed) + " yds/g>Drops: " + (statsDrops + careerDrops));
    localArrayList.addAll(super.getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("TDs/Fumbles: " + statsTD + "/" + statsFumbles + ">Catch Percent: " + statsReceptions * 100 / (statsTargets + 1) + "%");
    localArrayList.add("Rec Yards: " + statsRecYards + " yds>Yards/Tgt: " + statsRecYards * 10 / (statsTargets + 1) / 10.0D + " yds");
    localArrayList.add("Yds/Game: " + statsRecYards / getGamesPlayed() + " yds/g>Drops: " + statsDrops);
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Catching: " + getLetterGrade(ratRecCat));
    localArrayList.add("Rec Speed: " + getLetterGrade(ratRecSpd) + ">Evasion: " + getLetterGrade(ratRecEva));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add("[B]CAREER STATS:");
    localArrayList.addAll(getCareerStatsList());
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("TDs/Fumbles: " + statsTD + "/" + statsFumbles + ">Catch Percent: " + statsReceptions * 100 / (statsTargets + 1) + "%");
    localArrayList.add("Rec Yards: " + statsRecYards + " yds>Yards/Tgt: " + statsRecYards * 10 / (statsTargets + 1) / 10.0D + " yds");
    localArrayList.add("Yds/Game: " + statsRecYards / getGamesPlayed() + " yds/g>Drops: " + statsDrops);
    localArrayList.add("Games: " + gamesPlayed + " (" + statsWins + "-" + (gamesPlayed - statsWins) + ")>Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Catching: " + getLetterGrade(ratRecCat));
    localArrayList.add("Rec Speed: " + getLetterGrade(ratRecSpd) + ">Evasion: " + getLetterGrade(ratRecEva));
    localArrayList.add("Contract: " + contract.toString());
    localArrayList.add(" > ");
    return localArrayList;
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Potential: " + getLetterGrade(ratPot) + ">Durability: " + getLetterGrade(ratDur));
    localArrayList.add("Football IQ: " + getLetterGrade(ratFootIQ) + ">Catching: " + getLetterGrade(ratRecCat));
    localArrayList.add("Rec Speed: " + getLetterGrade(ratRecSpd) + ">Evasion: " + getLetterGrade(ratRecEva));
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
    return getInitialName() + " [" + age + "] " + ratOvr + "/" + getLetterGrade(ratPot) + " (" + getLetterGrade(ratRecCat) + ", " + getLetterGrade(ratRecSpd) + ", " + getLetterGrade(ratRecEva) + ")";
  }
  
  public int getMVPScore()
  {
    return statsTD * 150 - statsFumbles * 100 - statsDrops * 50 + statsRecYards * 2;
  }
  
  public int[] getRatings()
  {
    return new int[] { ratRecCat, ratRecSpd, ratRecEva };
  }
  
  public void setOvr()
  {
    ratOvr = ((ratRecCat * 2 + ratRecSpd + ratRecEva) / 4);
  }
  
  public void setRatings(int[] paramArrayOfInt)
  {
    ratRecCat = paramArrayOfInt[0];
    ratRecSpd = paramArrayOfInt[1];
    ratRecEva = paramArrayOfInt[2];
    setOvr();
  }
}

/* Location:
 * Qualified Name:     PFCpack.PlayerWR
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */