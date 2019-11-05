package PFCpack;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public abstract class Player
  extends TradePiece
{
  protected static final String[] letterGrades = { "F", "F+", "D", "D+", "C", "C+", "B", "B+", "A", "A+" };
  private static final Random rando = new Random();
  protected int age;
  protected int careerAllPro;
  protected int careerChamps;
  protected int careerGamesPlayed;
  protected int careerMVP;
  protected int careerWins;
  protected Contract contract;
  protected int draftPickNum;
  protected int gamesPlayed;
  protected Injury injury;
  protected boolean isInjured;
  public boolean isStarter;
  protected String name;
  protected String position;
  protected int ratDur;
  protected int ratFootIQ;
  protected int ratImprovement;
  protected int ratOvr;
  protected int ratPot;
  protected int rookieYear;
  protected int statsWins;
  protected Team team;
  protected ArrayList<String> teamsPlayedFor;
  protected boolean wonAllPro;
  protected boolean wonMVP;
  
  public Player(Team paramTeam, String[] paramArrayOfString)
  {
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      paramArrayOfString[i] = paramArrayOfString[i].trim();
      i += 1;
    }
    team = paramTeam;
    gamesPlayed = 0;
    statsWins = 0;
    wonAllPro = false;
    wonMVP = false;
    careerGamesPlayed = 0;
    careerWins = 0;
    careerAllPro = 0;
    careerMVP = 0;
    careerChamps = 0;
    setInjury(null);
    draftPickNum = -1;
    teamsPlayedFor = new ArrayList();
    addTeamPlayedFor(abbr, 2016);
    if (paramArrayOfString.length == 12)
    {
      position = paramArrayOfString[0];
      name = paramArrayOfString[1];
      age = Integer.parseInt(paramArrayOfString[2]);
      rookieYear = Integer.parseInt(paramArrayOfString[3]);
      ratPot = Integer.parseInt(paramArrayOfString[4]);
      ratFootIQ = Integer.parseInt(paramArrayOfString[5]);
      ratDur = Integer.parseInt(paramArrayOfString[6]);
      setRatings(new int[] { Integer.parseInt(paramArrayOfString[7]), Integer.parseInt(paramArrayOfString[8]), Integer.parseInt(paramArrayOfString[9]) });
      d = Double.parseDouble(paramArrayOfString[10]);
      contract = new Contract(this, Integer.parseInt(paramArrayOfString[11]), d);
    }
    while (paramArrayOfString.length != 9)
    {
      double d;
      return;
    }
    position = paramArrayOfString[0];
    name = paramArrayOfString[1];
    age = Integer.parseInt(paramArrayOfString[2]);
    ratPot = Integer.parseInt(paramArrayOfString[3]);
    ratFootIQ = Integer.parseInt(paramArrayOfString[4]);
    ratDur = Integer.parseInt(paramArrayOfString[5]);
    setRatings(new int[] { Integer.parseInt(paramArrayOfString[6]), Integer.parseInt(paramArrayOfString[7]), Integer.parseInt(paramArrayOfString[8]) });
    rookieYear = 2016;
    contract = new Contract(this, 1, 0.5D);
  }
  
  public Player(String paramString1, Team paramTeam, String paramString2, boolean paramBoolean)
  {
    team = paramTeam;
    name = paramString1;
    position = paramString2;
    gamesPlayed = 0;
    statsWins = 0;
    wonAllPro = false;
    wonMVP = false;
    careerGamesPlayed = 0;
    careerWins = 0;
    careerAllPro = 0;
    careerMVP = 0;
    careerChamps = 0;
    setInjury(null);
    draftPickNum = -1;
    teamsPlayedFor = new ArrayList();
    if (paramBoolean)
    {
      ratPot = ((int)(Math.random() * 50.0D + 50.0D));
      ratFootIQ = ((int)(Math.random() * 30.0D + 55.0D));
      ratDur = ((int)(Math.random() * 50.0D + 50.0D));
      setRatingsRookie();
      age = 22;
      gamesPlayed = 10;
      int j = (int)(Math.random() * 12.0D);
      int i = 0;
      while (i < j)
      {
        advanceSeasonRatingsAge();
        i += 1;
      }
      gamesPlayed = 0;
      rookieYear = (league.getYear() - (age - 22));
      contract = Contract.getContractFA(this);
      contract.setYearsLeft((int)(Math.random() * 4.0D) + 1);
      addTeamPlayedFor(abbr, league.getYear());
    }
    checkMinimums();
  }
  
  public Player(String paramString1, String paramString2, int paramInt)
  {
    team = null;
    name = paramString1;
    gamesPlayed = 0;
    statsWins = 0;
    wonAllPro = false;
    wonMVP = false;
    careerGamesPlayed = 0;
    careerWins = 0;
    careerAllPro = 0;
    careerMVP = 0;
    careerChamps = 0;
    setInjury(null);
    draftPickNum = -1;
    teamsPlayedFor = new ArrayList();
    age = ((int)(Math.random() * 3.0D + 21.0D));
    rookieYear = paramInt;
    ratPot = ((int)(Math.random() * 50.0D + 50.0D));
    ratFootIQ = ((int)(Math.random() * 30.0D + 55.0D));
    ratDur = ((int)(Math.random() * 50.0D + 50.0D));
    ratImprovement = 0;
    setRatingsRookie();
    contract = null;
    position = paramString2;
    checkMinimums();
  }
  
  private void checkMinimums()
  {
    if (ratPot < 50) {
      ratPot = 50;
    }
    if (ratPot > 99) {
      ratPot = 99;
    }
    if (ratDur < 50) {
      ratDur = 50;
    }
    if (ratFootIQ < 50) {
      ratFootIQ = 50;
    }
  }
  
  private int[] getImprovementsArray()
  {
    String str = position;
    int i = -1;
    switch (str.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
        if (str.equals("QB"))
        {
          i = 0;
          continue;
          if (str.equals("RB"))
          {
            i = 1;
            continue;
            if (str.equals("WR"))
            {
              i = 2;
              continue;
              if (str.equals("OL"))
              {
                i = 3;
                continue;
                if (str.equals("K"))
                {
                  i = 4;
                  continue;
                  if (str.equals("S"))
                  {
                    i = 5;
                    continue;
                    if (str.equals("CB"))
                    {
                      i = 6;
                      continue;
                      if (str.equals("DL"))
                      {
                        i = 7;
                        continue;
                        if (str.equals("LB")) {
                          i = 8;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        break;
      }
    }
    return new int[] { 2, 2, 2, 1, 1, 1, 0, 0, 0, 0, 0, -2, -4, -5 };
    return new int[] { 3, 3, 3, 3, 2, 2, 0, 0, -1, -2, -4, -5, -8, -9 };
    return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
    return new int[] { 3, 3, 2, 2, 1, 1, 0, 0, 0, -1, -2, -4, -6, -8 };
    return new int[] { 3, 3, 2, 1, 1, 1, 0, 0, -1, -1, -1, -2, -4, -8 };
    return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
    return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
    return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
    return new int[] { 3, 3, 3, 2, 2, 1, 0, 0, -1, -2, -3, -4, -6, -8 };
  }
  
  public static String getLetterGrade(int paramInt)
  {
    int i = (paramInt - 50) / 5;
    paramInt = i;
    if (i > 9) {
      paramInt = 9;
    }
    i = paramInt;
    if (paramInt < 0) {
      i = 0;
    }
    return letterGrades[i];
  }
  
  public static String getLetterGrade(String paramString)
  {
    int j = (Integer.parseInt(paramString) - 50) / 5;
    int i = j;
    if (j > 9) {
      i = 9;
    }
    j = i;
    if (i < 0) {
      j = 0;
    }
    return letterGrades[j];
  }
  
  public static int getPosNumber(String paramString)
  {
    int j = 0;
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        j = 9;
      case 0: 
        return j;
        if (paramString.equals("QB"))
        {
          i = 0;
          continue;
          if (paramString.equals("RB"))
          {
            i = 1;
            continue;
            if (paramString.equals("WR"))
            {
              i = 2;
              continue;
              if (paramString.equals("OL"))
              {
                i = 3;
                continue;
                if (paramString.equals("K"))
                {
                  i = 4;
                  continue;
                  if (paramString.equals("S"))
                  {
                    i = 5;
                    continue;
                    if (paramString.equals("CB"))
                    {
                      i = 6;
                      continue;
                      if (paramString.equals("DL"))
                      {
                        i = 7;
                        continue;
                        if (paramString.equals("LB")) {
                          i = 8;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        break;
      }
    }
    return 1;
    return 2;
    return 3;
    return 4;
    return 5;
    return 6;
    return 7;
    return 8;
  }
  
  public static double getPosValue(String paramString)
  {
    double d = 3.0D;
    int i = -1;
    switch (paramString.hashCode())
    {
    }
    for (;;)
    {
      switch (i)
      {
      default: 
        d = 1.0D;
      case 1: 
      case 2: 
      case 5: 
        return d;
        if (paramString.equals("QB"))
        {
          i = 0;
          continue;
          if (paramString.equals("RB"))
          {
            i = 1;
            continue;
            if (paramString.equals("WR"))
            {
              i = 2;
              continue;
              if (paramString.equals("OL"))
              {
                i = 3;
                continue;
                if (paramString.equals("K"))
                {
                  i = 4;
                  continue;
                  if (paramString.equals("S"))
                  {
                    i = 5;
                    continue;
                    if (paramString.equals("CB"))
                    {
                      i = 6;
                      continue;
                      if (paramString.equals("DL"))
                      {
                        i = 7;
                        continue;
                        if (paramString.equals("LB")) {
                          i = 8;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
        break;
      }
    }
    return 6.0D;
    return 2.0D;
    return 0.5D;
    return 2.0D;
    return 1.0D;
    return 1.0D;
  }
  
  public void addTeamPlayedFor(String paramString, int paramInt)
  {
    if (teamsPlayedFor == null) {
      teamsPlayedFor = new ArrayList();
    }
    if ((!teamsPlayedFor.isEmpty()) && (!((String)teamsPlayedFor.get(teamsPlayedFor.size() - 1)).split(":")[0].equals(paramString))) {
      teamsPlayedFor.add(paramString + ":" + paramInt);
    }
    while (!teamsPlayedFor.isEmpty()) {
      return;
    }
    teamsPlayedFor.add(paramString + ":" + paramInt);
  }
  
  public void advanceSeason()
  {
    int i = ratOvr;
    advanceSeasonRatingsAge();
    ratImprovement = (ratOvr - i);
    careerGamesPlayed += gamesPlayed;
    careerWins += statsWins;
    if (wonMVP) {
      careerMVP += 1;
    }
    if (wonAllPro) {
      careerAllPro += 1;
    }
    if (team.natChampWL.equals("CBW"))
    {
      System.out.println(getPosNameYrOvr_Str() + " added champ, his team " + team.abbr + " won the Champ Bowl");
      careerChamps += 1;
    }
    contract.advanceSeason();
    gamesPlayed = 0;
    statsWins = 0;
    wonAllPro = false;
    wonMVP = false;
    checkMinimums();
  }
  
  public void advanceSeasonRatingsAge()
  {
    int[] arrayOfInt1 = getRatings();
    int[] arrayOfInt2 = getImprovementsArray();
    double d1 = gamesPlayed / 3 - 3;
    double d2 = (ratPot - 70 + d1) / 10.0D;
    d1 = d2;
    if (d2 > 4.0D) {
      d1 = 4.0D;
    }
    int j = age - 20;
    int i = j;
    if (j > 13) {
      i = 13;
    }
    d2 = d1 + arrayOfInt2[i];
    ratFootIQ += (int)(Math.random() * d1) + 1;
    arrayOfInt1[0] += (int)(rando.nextGaussian() * 2.0D + d2 - Math.random() * d2);
    arrayOfInt1[1] += (int)(rando.nextGaussian() * 2.0D + d2 - Math.random() * d2);
    arrayOfInt1[2] += (int)(rando.nextGaussian() * 2.0D + d2 - Math.random() * d2);
    ratDur = ((int)(ratDur + rando.nextGaussian() * 3.0D));
    ratPot = ((int)(ratPot + rando.nextGaussian() * 3.0D));
    checkMinimums();
    setRatings(arrayOfInt1);
    age += 1;
  }
  
  public void fudgePotentialRookie()
  {
    ratPot += (int)(30.0D * Math.random()) - 15;
    if (ratPot < 50) {
      ratPot = 50;
    }
    if (ratPot > 100) {
      ratPot = 100;
    }
  }
  
  public int getAge()
  {
    return age;
  }
  
  public String getAgeOvrPot_Str()
  {
    return "Age: " + age + ", Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot);
  }
  
  public String getAwards()
  {
    int j = 1;
    ArrayList localArrayList = new ArrayList();
    int k = careerMVP;
    if (wonMVP)
    {
      i = 1;
      i = k + i;
      if (i > 0) {
        localArrayList.add(i + "x MVP");
      }
      k = careerAllPro;
      if (!wonAllPro) {
        break label271;
      }
      i = 1;
      label73:
      i = k + i;
      if (i > 0) {
        localArrayList.add(i + "x All-Pro");
      }
      k = careerChamps;
      if (!team.natChampWL.equals("CBW")) {
        break label276;
      }
    }
    label271:
    label276:
    for (int i = j;; i = 0)
    {
      i = k + i;
      if (i > 0) {
        localArrayList.add(i + "x Champ");
      }
      if (localArrayList.size() <= 0) {
        break label281;
      }
      Object localObject1 = "";
      i = 0;
      for (;;)
      {
        localObject2 = localObject1;
        if (i >= localArrayList.size()) {
          break;
        }
        localObject2 = (String)localObject1 + (String)localArrayList.get(i);
        localObject1 = localObject2;
        if (i != localArrayList.size() - 1) {
          localObject1 = (String)localObject2 + ", ";
        }
        i += 1;
      }
      i = 0;
      break;
      i = 0;
      break label73;
    }
    label281:
    Object localObject2 = "None";
    return (String)localObject2;
  }
  
  public String getCSV()
  {
    int j = 1;
    String str;
    StringBuilder localStringBuilder;
    if (injury != null)
    {
      str = injury.getCSV();
      localStringBuilder = new StringBuilder().append(position).append(",").append(name).append(",").append(age).append(",").append(rookieYear).append(",").append(ratPot).append(",").append(ratFootIQ).append(",").append(ratDur).append(",").append(ratImprovement).append(",").append(getRatings()[0]).append(",").append(getRatings()[1]).append(",").append(getRatings()[2]).append(",").append(gamesPlayed).append(",").append(statsWins).append(",");
      if (!wonAllPro) {
        break label388;
      }
      i = 1;
      label210:
      localStringBuilder = localStringBuilder.append(i).append(",");
      if (!wonMVP) {
        break label393;
      }
    }
    label388:
    label393:
    for (int i = j;; i = 0)
    {
      return i + "," + careerGamesPlayed + "," + careerWins + "," + careerAllPro + "," + careerMVP + "," + careerChamps + "," + contract.getYearsLeft() + "," + (int)(contract.getMoneyPerYear() * 10.0D) + "," + str + "," + draftPickNum + ">" + getTeamsPlayedForCSV();
      str = "-1,-1";
      break;
      i = 0;
      break label210;
    }
  }
  
  public String getCSVRosterFile()
  {
    String[] arrayOfString = new String[12];
    arrayOfString[0] = position;
    arrayOfString[1] = name;
    arrayOfString[2] = (age + "");
    arrayOfString[3] = (rookieYear + "");
    arrayOfString[4] = (ratPot + "");
    arrayOfString[5] = (ratFootIQ + "");
    arrayOfString[6] = (ratDur + "");
    arrayOfString[7] = (getRatings()[0] + "");
    arrayOfString[8] = (getRatings()[1] + "");
    arrayOfString[9] = (getRatings()[2] + "");
    arrayOfString[10] = (contract.getMoneyPerYear() + "");
    arrayOfString[11] = (contract.getYearsLeft() + "");
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < 11)
    {
      localStringBuilder.append(arrayOfString[i]).append(",");
      i += 1;
    }
    localStringBuilder.append(arrayOfString[11]);
    return localStringBuilder.toString();
  }
  
  public String getCSV_Online()
  {
    return position + "," + name + "," + age + "," + ratPot + "," + ratFootIQ + "," + ratDur + "," + getRatings()[0] + "," + getRatings()[1] + "," + getRatings()[2];
  }
  
  public int getCareerAllPro()
  {
    return careerAllPro;
  }
  
  public int getCareerGamesPlayed()
  {
    return careerGamesPlayed;
  }
  
  public int getCareerMVP()
  {
    return careerMVP;
  }
  
  public ArrayList<String> getCareerStatsList()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("Games: " + (gamesPlayed + careerGamesPlayed) + " (" + (statsWins + careerWins) + "-" + (gamesPlayed + careerGamesPlayed - (statsWins + careerWins)) + ")>" + getDraftPickStr());
    localArrayList.add("Teams: " + getTeamsPlayedForStr());
    localArrayList.add("Awards: " + getAwards());
    return localArrayList;
  }
  
  public int getCareerWins()
  {
    return careerWins;
  }
  
  public Contract getContract()
  {
    return contract;
  }
  
  public ArrayList<String> getDetailAllStatsList(int paramInt)
  {
    return null;
  }
  
  public ArrayList<String> getDetailStatsList(int paramInt)
  {
    return null;
  }
  
  public ArrayList<String> getDetailStatsOffseason()
  {
    return null;
  }
  
  public String getDraftPickStr()
  {
    if (draftPickNum < 0) {
      return "Undrafted, " + rookieYear;
    }
    return "Drafted " + getPickStr(draftPickNum) + ", " + rookieYear;
  }
  
  public int getGamesPlayed()
  {
    if (gamesPlayed == 0) {
      return 1;
    }
    return gamesPlayed;
  }
  
  public String getInfoForLineup()
  {
    return null;
  }
  
  public String getInfoLineupInjury()
  {
    if (injury != null) {
      return getInitialName() + " [" + age + "] " + injury.toString();
    }
    return getInitialName() + " [" + age + "] Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot);
  }
  
  public String getInitialName()
  {
    String[] arrayOfString = name.split(" ");
    return arrayOfString[0].substring(0, 1) + ". " + arrayOfString[1];
  }
  
  public Injury getInjury()
  {
    return injury;
  }
  
  public int getMVPScore()
  {
    int j = gamesPlayed;
    int i = j;
    if (j > 10) {
      i = 10;
    }
    return ratOvr * i;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getOvrPotDurFootIQ_Str()
  {
    return "Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot) + ", Dur: " + getLetterGrade(ratDur) + ", Football IQ: " + getLetterGrade(ratFootIQ);
  }
  
  public String getPickStr(int paramInt)
  {
    if (paramInt == 11) {
      return "11th";
    }
    if (paramInt == 12) {
      return "12th";
    }
    if (paramInt == 13) {
      return "13th";
    }
    if (paramInt % 10 == 1) {
      return paramInt + "st";
    }
    if (paramInt % 10 == 2) {
      return paramInt + "nd";
    }
    if (paramInt % 10 == 3) {
      return paramInt + "rd";
    }
    return paramInt + "th";
  }
  
  public String getPosInitialNameYrOvr_Str()
  {
    return position + " " + getInitialName() + " [" + age + "] Ovr: " + ratOvr;
  }
  
  public String getPosNameAge_Str()
  {
    return position + " " + name + " [" + age + "]";
  }
  
  public String getPosNameYrOvrPotImprove_Str()
  {
    String str;
    if (ratImprovement > 0) {
      str = " (+" + ratImprovement + ")";
    }
    for (;;)
    {
      return position + " " + getInitialName() + " [" + age + "]>" + ratOvr + str + ", Pot: " + getLetterGrade(ratPot);
      if (ratImprovement < 0) {
        str = " (" + ratImprovement + ")";
      } else {
        str = "";
      }
    }
  }
  
  public String getPosNameYrOvrPot_Compact()
  {
    return position + " " + getInitialName() + " [" + age + "] " + ratOvr + " / " + getLetterGrade(ratPot);
  }
  
  public String getPosNameYrOvrPot_NoInjury()
  {
    return position + " " + getInitialName() + " [" + age + "] Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot);
  }
  
  public String getPosNameYrOvrPot_OneLine()
  {
    if (injury != null) {
      return position + " " + getInitialName() + " [" + age + "] Ovr: " + ratOvr + " " + injury.toString();
    }
    return position + " " + getInitialName() + " [" + age + "] Ovr: " + ratOvr + ", Pot: " + getLetterGrade(ratPot);
  }
  
  public String getPosNameYrOvrPot_Split()
  {
    return position + " " + getInitialName() + " [" + age + "]>" + ratOvr + ", Pot: " + getLetterGrade(ratPot);
  }
  
  public String getPosNameYrOvrPot_Str()
  {
    if (injury != null) {
      return "[I]" + position + " " + getInitialName() + " [" + age + "] Ovr: " + ratOvr + ">" + injury.toString();
    }
    return position + " " + name + " [" + age + "]>" + ratOvr + ", Pot: " + getLetterGrade(ratPot);
  }
  
  public String getPosNameYrOvr_Str()
  {
    return position + " " + name + " [" + age + "] Ovr: " + ratOvr;
  }
  
  public String getPosition()
  {
    return position;
  }
  
  public int getRatDur()
  {
    return ratDur;
  }
  
  public int getRatFootIQ()
  {
    return ratFootIQ;
  }
  
  public int getRatImprovement()
  {
    return ratImprovement;
  }
  
  public int getRatOvr()
  {
    return ratOvr;
  }
  
  public int getRatPot()
  {
    return ratPot;
  }
  
  public abstract int[] getRatings();
  
  public int getRookieYear()
  {
    return rookieYear;
  }
  
  public int getStatsWins()
  {
    return statsWins;
  }
  
  public Team getTeam()
  {
    return team;
  }
  
  public String getTeamsPlayedForCSV()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < teamsPlayedFor.size())
    {
      localStringBuilder.append((String)teamsPlayedFor.get(i));
      if (i < teamsPlayedFor.size() - 1) {
        localStringBuilder.append(",");
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public String getTeamsPlayedForStr()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < teamsPlayedFor.size())
    {
      String[] arrayOfString = ((String)teamsPlayedFor.get(i)).split(":");
      localStringBuilder.append(arrayOfString[0] + " '" + arrayOfString[1].substring(2));
      if (i < teamsPlayedFor.size() - 1) {
        localStringBuilder.append(", ");
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public String getTradePieceInfo()
  {
    String str = "";
    if (isInjured) {
      str = "\n\t\tInjured: " + injury.toString();
    }
    return getPosNameYrOvrPot_Compact() + str + "\n\t\tContract: " + contract.toString();
  }
  
  public int getTradeValue()
  {
    if (ratOvr > 65)
    {
      double d4 = getPosValue(position);
      double d5 = (ratDur + 200) / 300.0D;
      double d6 = (65 - age) / 40.0D;
      double d7 = (ratPot + 200 - age * 4) / 200.0D;
      double d2 = 1.0D;
      double d1 = d2;
      if (isInjured())
      {
        d1 = d2;
        if (team.userControlled) {
          d1 = 0.3D;
        }
      }
      double d3 = 1.0D;
      d2 = d3;
      if (!team.userControlled)
      {
        d2 = d3;
        if (ratOvr > 90)
        {
          d2 = d3;
          if (!position.equals("K")) {
            d2 = Math.pow(ratOvr / 85.0D, 3.0D);
          }
        }
      }
      return (int)(Math.pow(ratOvr - 60, 2.0D) * d7 * d5 * d6 * d1 * d2 * d4);
    }
    if (age < 25) {
      return ratPot;
    }
    return 0;
  }
  
  public String getYearsPlayed()
  {
    int i = rookieYear;
    int j = team.league.getYear();
    return i + "-" + j;
  }
  
  public boolean hasWonAllPro()
  {
    return wonAllPro;
  }
  
  public boolean hasWonMVP()
  {
    return wonMVP;
  }
  
  public boolean isInjured()
  {
    return isInjured;
  }
  
  public boolean randomJumpRookie(int paramInt)
  {
    if (0.1D + paramInt / 896 > Math.random())
    {
      System.out.print("Player of pick #" + paramInt + " got a jump from " + ratOvr);
      int[] arrayOfInt = getRatings();
      int i = 0;
      while (i < 3)
      {
        arrayOfInt[i] += (int)(Math.random() * (paramInt / 32 + 4)) + 3;
        i += 1;
      }
      setRatings(arrayOfInt);
      System.out.println(" to " + ratOvr);
      return true;
    }
    return false;
  }
  
  public void setContract(Contract paramContract)
  {
    contract = paramContract;
  }
  
  public void setDraftPickNum(int paramInt)
  {
    draftPickNum = paramInt;
  }
  
  public void setInjury(Injury paramInjury)
  {
    injury = paramInjury;
    if (paramInjury == null)
    {
      isInjured = false;
      return;
    }
    isInjured = true;
  }
  
  abstract void setOvr();
  
  abstract void setRatings(int[] paramArrayOfInt);
  
  public void setRatingsRookie()
  {
    int[] arrayOfInt = new int[3];
    int i = 0;
    while (i < 3)
    {
      arrayOfInt[i] = ((int)Math.abs(rando.nextGaussian() * 15.0D) + 55);
      if (arrayOfInt[i] > 95) {
        arrayOfInt[i] = 95;
      }
      i += 1;
    }
    setRatings(arrayOfInt);
  }
  
  public void setRatingsUDFA()
  {
    int[] arrayOfInt = new int[3];
    int i = 0;
    while (i < 3)
    {
      arrayOfInt[i] = ((int)(Math.random() * 15.0D) + 55);
      i += 1;
    }
    setRatings(arrayOfInt);
  }
  
  public void setTeam(Team paramTeam)
  {
    team = paramTeam;
  }
  
  public void setTeamsPlayedForCSV(String paramString)
  {
    paramString = paramString.split(",");
    teamsPlayedFor.addAll(Arrays.asList(paramString));
  }
  
  protected void setVariables(int[] paramArrayOfInt, String paramString)
  {
    boolean bool2 = true;
    age = paramArrayOfInt[0];
    rookieYear = paramArrayOfInt[1];
    ratPot = paramArrayOfInt[2];
    ratFootIQ = paramArrayOfInt[3];
    ratDur = paramArrayOfInt[4];
    ratImprovement = paramArrayOfInt[5];
    setRatings(Arrays.copyOfRange(paramArrayOfInt, 6, 9));
    gamesPlayed = paramArrayOfInt[9];
    statsWins = paramArrayOfInt[10];
    if (paramArrayOfInt[11] == 1)
    {
      bool1 = true;
      wonAllPro = bool1;
      if (paramArrayOfInt[12] != 1) {
        break label220;
      }
    }
    label220:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      wonMVP = bool1;
      careerGamesPlayed = paramArrayOfInt[13];
      careerWins = paramArrayOfInt[14];
      careerAllPro = paramArrayOfInt[15];
      careerMVP = paramArrayOfInt[16];
      careerChamps = paramArrayOfInt[17];
      contract = new Contract(this, paramArrayOfInt[18], paramArrayOfInt[19] / 10.0D);
      if (paramArrayOfInt[20] != -1) {
        injury = new Injury(this, paramArrayOfInt[20], paramArrayOfInt[21]);
      }
      checkMinimums();
      draftPickNum = paramArrayOfInt[22];
      setTeamsPlayedForCSV(paramString);
      return;
      bool1 = false;
      break;
    }
  }
  
  public void setWonAllPro(boolean paramBoolean)
  {
    wonAllPro = paramBoolean;
  }
  
  public void setWonMVP(boolean paramBoolean)
  {
    wonMVP = paramBoolean;
  }
}

/* Location:
 * Qualified Name:     PFCpack.Player
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */