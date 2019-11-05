package PFCpack;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Team
{
  private final int RETIREMENT_AGE = 33;
  private final double RETIREMENT_CHANCE = 0.35D;
  public String abbr;
  public String divChampion;
  public String division;
  public ArrayList<DraftPick> draftPicks;
  public String evenYearHomeOpp;
  public ArrayList<Game> gameSchedule;
  public ArrayList<String> gameWLSchedule;
  public ArrayList<Team> gameWinsAgainst;
  public League league;
  public int losses;
  public String name;
  public String natChampWL;
  public int numRecruits;
  public ArrayList<Player> playersFAs;
  public ArrayList<Player> playersInjured;
  public ArrayList<Player> playersInjuredAll;
  public ArrayList<Player> playersRecovered;
  public ArrayList<Player> playersRetiring;
  public ArrayList<DraftPick> positionalDraftPicks;
  public int rankTeamDefTalent;
  public int rankTeamOffTalent;
  public int rankTeamOppPassYards;
  public int rankTeamOppPoints;
  public int rankTeamOppRushYards;
  public int rankTeamOppYards;
  public int rankTeamPassYards;
  public int rankTeamPoints;
  public int rankTeamPollScore;
  public int rankTeamPrestige;
  public int rankTeamRushYards;
  public int rankTeamStrengthOfWins;
  public int rankTeamTODiff;
  public int rankTeamYards;
  public Game[] regSeasonSchedule;
  public boolean showPopups;
  public ArrayList<PlayerCB> teamCBs;
  public ArrayList<PlayerDL> teamDLs;
  public int teamDefTalent;
  public int teamELO;
  public ArrayList<String> teamHistory;
  public ArrayList<PlayerK> teamKs;
  public ArrayList<PlayerLB> teamLBs;
  public ArrayList<PlayerOL> teamOLs;
  public int teamOffTalent;
  public int teamOppPassYards;
  public int teamOppPoints;
  public int teamOppRushYards;
  public int teamOppYards;
  public int teamPassYards;
  public int teamPoints;
  public int teamPollScore;
  public int teamPrestige;
  public ArrayList<PlayerQB> teamQBs;
  public ArrayList<PlayerRB> teamRBs;
  public int teamRushYards;
  public ArrayList<PlayerS> teamSs;
  public TeamStrategy teamStratDef;
  public int teamStratDefNum;
  public TeamStrategy teamStratOff;
  public int teamStratOffNum;
  public int teamStrengthOfWins;
  public int teamTODiff;
  public ArrayList<PlayerWR> teamWRs;
  public int teamYards;
  public int totalDivChamps;
  public int totalLosses;
  public int totalPlayoffLosses;
  public int totalPlayoffWins;
  public int totalSuperBowlLosses;
  public int totalSuperBowlWins;
  public int totalWins;
  public ArrayList<TradePiece> tradingBlock;
  public boolean userControlled;
  public TeamStreak winStreak;
  public int wins;
  public TeamStreak yearStartWinStreak;
  
  public Team(String paramString, League paramLeague)
  {
    league = paramLeague;
    userControlled = false;
    showPopups = true;
    teamHistory = new ArrayList();
    playersInjuredAll = new ArrayList();
    playersInjured = new ArrayList();
    playersRecovered = new ArrayList();
    tradingBlock = new ArrayList();
    teamQBs = new ArrayList();
    teamRBs = new ArrayList();
    teamWRs = new ArrayList();
    teamKs = new ArrayList();
    teamOLs = new ArrayList();
    teamDLs = new ArrayList();
    teamLBs = new ArrayList();
    teamSs = new ArrayList();
    teamCBs = new ArrayList();
    gameSchedule = new ArrayList();
    regSeasonSchedule = new Game[16];
    gameWinsAgainst = new ArrayList();
    gameWLSchedule = new ArrayList();
    divChampion = "";
    natChampWL = "";
    totalDivChamps = 0;
    totalPlayoffWins = 0;
    totalPlayoffLosses = 0;
    totalSuperBowlLosses = 0;
    totalSuperBowlWins = 0;
    teamPoints = 0;
    teamOppPoints = 0;
    teamYards = 0;
    teamOppYards = 0;
    teamPassYards = 0;
    teamRushYards = 0;
    teamOppPassYards = 0;
    teamOppRushYards = 0;
    teamTODiff = 0;
    teamOffTalent = 0;
    teamDefTalent = 0;
    teamPollScore = 0;
    teamStratOffNum = 1;
    teamStratDefNum = 1;
    paramString = paramString.split("%");
    setVarsFromCSV(paramString[0]);
    playersRetiring = new ArrayList();
    playersFAs = new ArrayList();
    int i = 1;
    if (i < paramString.length)
    {
      if (paramString[i].substring(0, 4).equals("[FA]")) {
        addFAPlayerCSV(paramString[i]);
      }
      for (;;)
      {
        i += 1;
        break;
        recruitPlayerCSV(paramString[i]);
      }
    }
    sortPlayers();
    getPlayersInjuredAll();
    teamStratOff = getTeamStrategiesOff()[teamStratOffNum];
    teamStratDef = getTeamStrategiesDef()[teamStratDefNum];
    numRecruits = 30;
  }
  
  public Team(String paramString1, String paramString2, String paramString3, League paramLeague)
  {
    league = paramLeague;
    userControlled = false;
    showPopups = true;
    teamHistory = new ArrayList();
    playersInjuredAll = new ArrayList();
    playersInjured = new ArrayList();
    playersRecovered = new ArrayList();
    tradingBlock = new ArrayList();
    teamQBs = new ArrayList();
    teamRBs = new ArrayList();
    teamWRs = new ArrayList();
    teamKs = new ArrayList();
    teamOLs = new ArrayList();
    teamDLs = new ArrayList();
    teamLBs = new ArrayList();
    teamSs = new ArrayList();
    teamCBs = new ArrayList();
    gameSchedule = new ArrayList();
    regSeasonSchedule = new Game[16];
    gameWinsAgainst = new ArrayList();
    gameWLSchedule = new ArrayList();
    divChampion = "";
    natChampWL = "";
    name = paramString1;
    abbr = paramString2;
    division = paramString3;
  }
  
  public Team(JSONObject paramJSONObject)
    throws JSONException
  {
    name = paramJSONObject.get("name").toString();
    abbr = paramJSONObject.get("abbr").toString();
    teamStratOffNum = Integer.parseInt(paramJSONObject.get("off_strat").toString());
    teamStratDefNum = Integer.parseInt(paramJSONObject.get("def_strat").toString());
    teamELO = Integer.parseInt(paramJSONObject.get("elo").toString());
    wins = Integer.parseInt(paramJSONObject.get("wins").toString());
    losses = Integer.parseInt(paramJSONObject.get("losses").toString());
    teamPoints = Integer.parseInt(paramJSONObject.get("points").toString());
    teamOppPoints = Integer.parseInt(paramJSONObject.get("opp_points").toString());
    teamYards = Integer.parseInt(paramJSONObject.get("yards").toString());
    teamOppYards = Integer.parseInt(paramJSONObject.get("opp_yards").toString());
    teamPassYards = Integer.parseInt(paramJSONObject.get("pass_yards").toString());
    teamRushYards = Integer.parseInt(paramJSONObject.get("rush_yards").toString());
    teamOppPassYards = Integer.parseInt(paramJSONObject.get("opp_pass_yards").toString());
    teamOppRushYards = Integer.parseInt(paramJSONObject.get("opp_rush_yards").toString());
    teamTODiff = Integer.parseInt(paramJSONObject.get("to_diff").toString());
    teamQBs = new ArrayList();
    teamRBs = new ArrayList();
    teamWRs = new ArrayList();
    teamKs = new ArrayList();
    teamOLs = new ArrayList();
    teamDLs = new ArrayList();
    teamLBs = new ArrayList();
    teamSs = new ArrayList();
    teamCBs = new ArrayList();
    division = "XXX";
    gameSchedule = new ArrayList();
    regSeasonSchedule = new Game[16];
    gameWinsAgainst = new ArrayList();
    gameWLSchedule = new ArrayList();
    divChampion = "";
    natChampWL = "";
    totalDivChamps = 0;
    totalPlayoffWins = 0;
    totalPlayoffLosses = 0;
    totalSuperBowlLosses = 0;
    totalSuperBowlWins = 0;
    paramJSONObject = paramJSONObject.get("players").toString().split(">");
    int j = paramJSONObject.length;
    while (i < j)
    {
      recruitPlayerCSV(paramJSONObject[i]);
      i += 1;
    }
    teamStratOff = getTeamStrategiesOff()[teamStratOffNum];
    teamStratDef = getTeamStrategiesDef()[teamStratDefNum];
  }
  
  private void addFAPlayerCSV(String paramString)
  {
    Object localObject = paramString.substring(4).split(">");
    String[] arrayOfString1 = localObject[0].split(",");
    String str1 = arrayOfString1[1];
    int[] arrayOfInt = new int[arrayOfString1.length - 2];
    int i = 0;
    while (i < arrayOfInt.length)
    {
      arrayOfInt[i] = Integer.parseInt(arrayOfString1[(i + 2)]);
      i += 1;
    }
    String str2 = localObject[1];
    paramString = null;
    if (localObject.length == 3)
    {
      String[] arrayOfString2 = localObject[2].split(",");
      localObject = new int[arrayOfString2.length];
      i = 0;
      for (;;)
      {
        paramString = (String)localObject;
        if (i >= arrayOfString2.length) {
          break;
        }
        localObject[i] = Integer.parseInt(arrayOfString2[i]);
        i += 1;
      }
    }
    if (arrayOfString1[0].equals("QB")) {
      playersFAs.add(new PlayerQB(str1, this, arrayOfInt, str2, paramString));
    }
    do
    {
      return;
      if (arrayOfString1[0].equals("RB"))
      {
        playersFAs.add(new PlayerRB(str1, this, arrayOfInt, str2, paramString));
        return;
      }
      if (arrayOfString1[0].equals("WR"))
      {
        playersFAs.add(new PlayerWR(str1, this, arrayOfInt, str2, paramString));
        return;
      }
      if (arrayOfString1[0].equals("OL"))
      {
        playersFAs.add(new PlayerOL(str1, this, arrayOfInt, str2));
        return;
      }
      if (arrayOfString1[0].equals("K"))
      {
        playersFAs.add(new PlayerK(str1, this, arrayOfInt, str2, paramString));
        return;
      }
      if (arrayOfString1[0].equals("S"))
      {
        playersFAs.add(new PlayerS(str1, this, arrayOfInt, str2));
        return;
      }
      if (arrayOfString1[0].equals("CB"))
      {
        playersFAs.add(new PlayerCB(str1, this, arrayOfInt, str2));
        return;
      }
      if (arrayOfString1[0].equals("DL"))
      {
        playersFAs.add(new PlayerDL(str1, this, arrayOfInt, str2));
        return;
      }
    } while (!arrayOfString1[0].equals("LB"));
    playersFAs.add(new PlayerLB(str1, this, arrayOfInt, str2));
  }
  
  private void addGamePlayedList(ArrayList<? extends Player> paramArrayList, int paramInt, boolean paramBoolean)
  {
    int i = 0;
    while (i < paramInt)
    {
      Player localPlayer = (Player)paramArrayList.get(i);
      gamesPlayed += 1;
      if (paramBoolean)
      {
        localPlayer = (Player)paramArrayList.get(i);
        statsWins += 1;
      }
      i += 1;
    }
  }
  
  private void checkInjuryPosition(ArrayList<? extends Player> paramArrayList, int paramInt)
  {
    int i = 0;
    Object localObject = paramArrayList.iterator();
    int j;
    while (((Iterator)localObject).hasNext())
    {
      Player localPlayer = (Player)((Iterator)localObject).next();
      if (injury != null)
      {
        injury.advanceGame();
        j = i + 1;
        i = j;
        if (injury == null)
        {
          playersRecovered.add(localPlayer);
          playersInjuredAll.remove(localPlayer);
          i = j;
        }
      }
    }
    int k = i;
    if (i < paramInt)
    {
      j = 0;
      for (;;)
      {
        k = i;
        if (j >= paramInt) {
          break;
        }
        localObject = (Player)paramArrayList.get(j);
        double d = Math.pow(1.0D - ratDur / 100.0D, 3.0D);
        if ((position.equals("QB")) || (position.equals("K"))) {
          d = Math.pow(1.0D - ratDur / 100.0D, 4.0D);
        }
        if (Math.random() >= d)
        {
          k = i;
          if (Math.random() >= 0.005D) {}
        }
        else
        {
          k = i;
          if (i < paramInt)
          {
            injury = new Injury((Player)localObject);
            playersInjured.add(localObject);
            playersInjuredAll.add(localObject);
            k = i + 1;
          }
        }
        j += 1;
        i = k;
      }
    }
    if (k > 0) {
      Collections.sort(paramArrayList, new PlayerComparator());
    }
  }
  
  private void checkRetirementList(ArrayList<? extends Player> paramArrayList)
  {
    double d1 = 0.0D;
    if (natChampWL.equals("CBW")) {
      d1 = 0.0D + 0.2D;
    }
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Player localPlayer = (Player)paramArrayList.next();
      double d2 = (80 - ratOvr) / 80.0D;
      int i = 0;
      if ((position.equals("QB")) || (position.equals("K"))) {
        i = 2;
      }
      if (((age >= i + 33) && (Math.random() < 0.35D + d1 + d2)) || (age >= 39)) {
        playersRetiring.add(localPlayer);
      }
    }
  }
  
  private void curePlayersPosition(ArrayList<? extends Player> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Player localPlayer = (Player)paramArrayList.next();
      injury = null;
      isInjured = false;
    }
  }
  
  private int getValueAddedList(ArrayList<? extends Player> paramArrayList, Player paramPlayer, int paramInt)
  {
    if (paramArrayList.size() >= paramInt * 2 + 2) {
      return 0;
    }
    int k = 0 + (paramInt * 2 - paramArrayList.size()) * 5;
    int j = 0;
    if (j < paramArrayList.size())
    {
      int i = k;
      if (j < paramInt * 2)
      {
        i = paramPlayer.getRatOvr() - ((Player)paramArrayList.get(j)).getRatOvr();
        if (i <= 0) {
          break label114;
        }
        if (j >= paramInt) {
          break label104;
        }
        i = k + i * 3;
      }
      for (;;)
      {
        j += 1;
        k = i;
        break;
        label104:
        i = k + i;
        continue;
        label114:
        i = k + i;
      }
    }
    return k;
  }
  
  private int getWeightedAvgOverallList(ArrayList<? extends Player> paramArrayList, int paramInt)
  {
    int k = 0;
    int j = 0;
    if (j < paramArrayList.size())
    {
      int i = k;
      if (j < paramInt * 2) {
        if (j >= paramInt) {
          break label62;
        }
      }
      label62:
      for (i = k + getratOvr * 3;; i = k + getratOvr)
      {
        j += 1;
        k = i;
        break;
      }
    }
    return (int)(k / (paramInt * 4));
  }
  
  private void recruitPlayerCSV(String paramString)
  {
    if ((paramString.split(",").length == 12) || (paramString.split(",").length == 9))
    {
      paramString = paramString.split(",");
      if (paramString[0].equals("QB")) {
        teamQBs.add(new PlayerQB(this, paramString));
      }
    }
    String[] arrayOfString1;
    String str1;
    int[] arrayOfInt;
    String str2;
    do
    {
      do
      {
        return;
        if (paramString[0].equals("RB"))
        {
          teamRBs.add(new PlayerRB(this, paramString));
          return;
        }
        if (paramString[0].equals("WR"))
        {
          teamWRs.add(new PlayerWR(this, paramString));
          return;
        }
        if (paramString[0].equals("OL"))
        {
          teamOLs.add(new PlayerOL(this, paramString));
          return;
        }
        if (paramString[0].equals("K"))
        {
          teamKs.add(new PlayerK(this, paramString));
          return;
        }
        if (paramString[0].equals("S"))
        {
          teamSs.add(new PlayerS(this, paramString));
          return;
        }
        if (paramString[0].equals("CB"))
        {
          teamCBs.add(new PlayerCB(this, paramString));
          return;
        }
        if (paramString[0].equals("DL"))
        {
          teamDLs.add(new PlayerDL(this, paramString));
          return;
        }
      } while (!paramString[0].equals("LB"));
      teamLBs.add(new PlayerLB(this, paramString));
      return;
      Object localObject = paramString.split(">");
      arrayOfString1 = localObject[0].split(",");
      str1 = arrayOfString1[1];
      arrayOfInt = new int[arrayOfString1.length - 2];
      int i = 0;
      while (i < arrayOfInt.length)
      {
        arrayOfInt[i] = Integer.parseInt(arrayOfString1[(i + 2)]);
        i += 1;
      }
      str2 = localObject[1];
      paramString = null;
      if (localObject.length == 3)
      {
        String[] arrayOfString2 = localObject[2].split(",");
        localObject = new int[arrayOfString2.length];
        i = 0;
        for (;;)
        {
          paramString = (String)localObject;
          if (i >= arrayOfString2.length) {
            break;
          }
          localObject[i] = Integer.parseInt(arrayOfString2[i]);
          i += 1;
        }
      }
      if (arrayOfString1[0].equals("QB"))
      {
        teamQBs.add(new PlayerQB(str1, this, arrayOfInt, str2, paramString));
        return;
      }
      if (arrayOfString1[0].equals("RB"))
      {
        teamRBs.add(new PlayerRB(str1, this, arrayOfInt, str2, paramString));
        return;
      }
      if (arrayOfString1[0].equals("WR"))
      {
        teamWRs.add(new PlayerWR(str1, this, arrayOfInt, str2, paramString));
        return;
      }
      if (arrayOfString1[0].equals("OL"))
      {
        teamOLs.add(new PlayerOL(str1, this, arrayOfInt, str2));
        return;
      }
      if (arrayOfString1[0].equals("K"))
      {
        teamKs.add(new PlayerK(str1, this, arrayOfInt, str2, paramString));
        return;
      }
      if (arrayOfString1[0].equals("S"))
      {
        teamSs.add(new PlayerS(str1, this, arrayOfInt, str2));
        return;
      }
      if (arrayOfString1[0].equals("CB"))
      {
        teamCBs.add(new PlayerCB(str1, this, arrayOfInt, str2));
        return;
      }
      if (arrayOfString1[0].equals("DL"))
      {
        teamDLs.add(new PlayerDL(str1, this, arrayOfInt, str2));
        return;
      }
    } while (!arrayOfString1[0].equals("LB"));
    teamLBs.add(new PlayerLB(str1, this, arrayOfInt, str2));
  }
  
  public void addGamePlayedPlayers(boolean paramBoolean)
  {
    addGamePlayedList(teamQBs, 1, paramBoolean);
    addGamePlayedList(teamRBs, 2, paramBoolean);
    addGamePlayedList(teamWRs, 3, paramBoolean);
    addGamePlayedList(teamOLs, 5, paramBoolean);
    addGamePlayedList(teamKs, 1, paramBoolean);
    addGamePlayedList(teamSs, 1, paramBoolean);
    addGamePlayedList(teamCBs, 3, paramBoolean);
    addGamePlayedList(teamDLs, 4, paramBoolean);
    addGamePlayedList(teamLBs, 3, paramBoolean);
  }
  
  public void addPlayer(Player paramPlayer)
  {
    if ((paramPlayer instanceof PlayerQB)) {
      teamQBs.add((PlayerQB)paramPlayer);
    }
    for (;;)
    {
      team = this;
      return;
      if ((paramPlayer instanceof PlayerRB)) {
        teamRBs.add((PlayerRB)paramPlayer);
      } else if ((paramPlayer instanceof PlayerWR)) {
        teamWRs.add((PlayerWR)paramPlayer);
      } else if ((paramPlayer instanceof PlayerOL)) {
        teamOLs.add((PlayerOL)paramPlayer);
      } else if ((paramPlayer instanceof PlayerK)) {
        teamKs.add((PlayerK)paramPlayer);
      } else if ((paramPlayer instanceof PlayerS)) {
        teamSs.add((PlayerS)paramPlayer);
      } else if ((paramPlayer instanceof PlayerCB)) {
        teamCBs.add((PlayerCB)paramPlayer);
      } else if ((paramPlayer instanceof PlayerDL)) {
        teamDLs.add((PlayerDL)paramPlayer);
      } else if ((paramPlayer instanceof PlayerLB)) {
        teamLBs.add((PlayerLB)paramPlayer);
      } else {
        System.out.println(abbr + " couldn't add " + paramPlayer.getPosNameYrOvrPot_OneLine());
      }
    }
  }
  
  public void advanceInjuriesWeek()
  {
    Iterator localIterator = getAllPlayers().iterator();
    while (localIterator.hasNext())
    {
      Player localPlayer = (Player)localIterator.next();
      if (injury != null)
      {
        injury.advanceGame();
        if (injury == null)
        {
          playersRecovered.add(localPlayer);
          playersInjuredAll.remove(localPlayer);
        }
      }
    }
    sortPlayers();
  }
  
  public void advanceSeason()
  {
    league.checkHallofFame(playersRetiring);
    checkCareerRecords(league.leagueRecords);
    if (league.userTeam == this) {
      checkCareerRecords(league.userTeamRecords);
    }
    advanceSeasonPlayers();
  }
  
  public void advanceSeasonPlayers()
  {
    int i = 0;
    int i4 = 0;
    int i3 = 0;
    int i2 = 0;
    int i1 = 0;
    int n = 0;
    int m = 0;
    int k = 0;
    int j = 0;
    while (j < teamQBs.size()) {
      if (playersRetiring.contains(teamQBs.get(j)))
      {
        teamQBs.remove(j);
        i += 1;
      }
      else if (playersFAs.contains(teamQBs.get(j)))
      {
        ((PlayerQB)teamQBs.get(j)).advanceSeason();
        teamQBs.remove(j);
        i += 1;
      }
      else
      {
        ((PlayerQB)teamQBs.get(j)).advanceSeason();
        j += 1;
      }
    }
    j = 0;
    i = i4;
    while (j < teamRBs.size()) {
      if (playersRetiring.contains(teamRBs.get(j)))
      {
        teamRBs.remove(j);
        i += 1;
      }
      else if (playersFAs.contains(teamRBs.get(j)))
      {
        ((PlayerRB)teamRBs.get(j)).advanceSeason();
        teamRBs.remove(j);
        i += 1;
      }
      else
      {
        ((PlayerRB)teamRBs.get(j)).advanceSeason();
        j += 1;
      }
    }
    j = 0;
    i = i3;
    while (j < teamWRs.size()) {
      if (playersRetiring.contains(teamWRs.get(j)))
      {
        teamWRs.remove(j);
        i += 1;
      }
      else if (playersFAs.contains(teamWRs.get(j)))
      {
        ((PlayerWR)teamWRs.get(j)).advanceSeason();
        teamWRs.remove(j);
        i += 1;
      }
      else
      {
        ((PlayerWR)teamWRs.get(j)).advanceSeason();
        j += 1;
      }
    }
    j = 0;
    i = i2;
    while (j < teamKs.size()) {
      if (playersRetiring.contains(teamKs.get(j)))
      {
        teamKs.remove(j);
        i += 1;
      }
      else if (playersFAs.contains(teamKs.get(j)))
      {
        ((PlayerK)teamKs.get(j)).advanceSeason();
        teamKs.remove(j);
        i += 1;
      }
      else
      {
        ((PlayerK)teamKs.get(j)).advanceSeason();
        j += 1;
      }
    }
    j = 0;
    i = i1;
    while (j < teamOLs.size()) {
      if (playersRetiring.contains(teamOLs.get(j)))
      {
        teamOLs.remove(j);
        i += 1;
      }
      else if (playersFAs.contains(teamOLs.get(j)))
      {
        ((PlayerOL)teamOLs.get(j)).advanceSeason();
        teamOLs.remove(j);
        i += 1;
      }
      else
      {
        ((PlayerOL)teamOLs.get(j)).advanceSeason();
        j += 1;
      }
    }
    j = 0;
    i = n;
    while (j < teamSs.size()) {
      if (playersRetiring.contains(teamSs.get(j)))
      {
        teamSs.remove(j);
        i += 1;
      }
      else if (playersFAs.contains(teamSs.get(j)))
      {
        ((PlayerS)teamSs.get(j)).advanceSeason();
        teamSs.remove(j);
        i += 1;
      }
      else
      {
        ((PlayerS)teamSs.get(j)).advanceSeason();
        j += 1;
      }
    }
    j = 0;
    i = m;
    while (j < teamCBs.size()) {
      if (playersRetiring.contains(teamCBs.get(j)))
      {
        teamCBs.remove(j);
        i += 1;
      }
      else if (playersFAs.contains(teamCBs.get(j)))
      {
        ((PlayerCB)teamCBs.get(j)).advanceSeason();
        teamCBs.remove(j);
        i += 1;
      }
      else
      {
        ((PlayerCB)teamCBs.get(j)).advanceSeason();
        j += 1;
      }
    }
    j = 0;
    i = k;
    while (j < teamDLs.size()) {
      if (playersRetiring.contains(teamDLs.get(j)))
      {
        teamDLs.remove(j);
        i += 1;
      }
      else if (playersFAs.contains(teamDLs.get(j)))
      {
        ((PlayerDL)teamDLs.get(j)).advanceSeason();
        teamDLs.remove(j);
        i += 1;
      }
      else
      {
        ((PlayerDL)teamDLs.get(j)).advanceSeason();
        j += 1;
      }
    }
    j = 0;
    while (j < teamLBs.size()) {
      if (playersRetiring.contains(teamLBs.get(j)))
      {
        teamLBs.remove(j);
        i += 1;
      }
      else if (playersFAs.contains(teamLBs.get(j)))
      {
        ((PlayerLB)teamLBs.get(j)).advanceSeason();
        teamLBs.remove(j);
        i += 1;
      }
      else
      {
        ((PlayerLB)teamLBs.get(j)).advanceSeason();
        j += 1;
      }
    }
    resetStats();
  }
  
  public void checkCareerRecords(LeagueRecords paramLeagueRecords)
  {
    Iterator localIterator = playersRetiring.iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Player)localIterator.next();
      if ((localObject instanceof PlayerQB))
      {
        localObject = (PlayerQB)localObject;
        paramLeagueRecords.checkRecord("Career Pass Yards", statsPassYards + careerPassYards, abbr + " " + ((PlayerQB)localObject).getInitialName(), league.getYear() - 1);
        paramLeagueRecords.checkRecord("Career Pass TDs", statsTD + careerTDs, abbr + " " + ((PlayerQB)localObject).getInitialName(), league.getYear() - 1);
        paramLeagueRecords.checkRecord("Career Interceptions", statsInt + careerInt, abbr + " " + ((PlayerQB)localObject).getInitialName(), league.getYear() - 1);
      }
      else if ((localObject instanceof PlayerRB))
      {
        localObject = (PlayerRB)localObject;
        paramLeagueRecords.checkRecord("Career Rush Yards", statsRushYards + careerRushYards, abbr + " " + ((PlayerRB)localObject).getInitialName(), league.getYear() - 1);
        paramLeagueRecords.checkRecord("Career Rush TDs", statsTD + careerTDs, abbr + " " + ((PlayerRB)localObject).getInitialName(), league.getYear() - 1);
        paramLeagueRecords.checkRecord("Career Rush Fumbles", statsFumbles + careerFumbles, abbr + " " + ((PlayerRB)localObject).getInitialName(), league.getYear() - 1);
      }
      else if ((localObject instanceof PlayerWR))
      {
        localObject = (PlayerWR)localObject;
        paramLeagueRecords.checkRecord("Career Rec Yards", statsRecYards + careerRecYards, abbr + " " + ((PlayerWR)localObject).getInitialName(), league.getYear() - 1);
        paramLeagueRecords.checkRecord("Career Rec TDs", statsTD + careerTD, abbr + " " + ((PlayerWR)localObject).getInitialName(), league.getYear() - 1);
      }
    }
  }
  
  public void checkExpiringFAs(ArrayList<? extends Player> paramArrayList)
  {
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Player localPlayer = (Player)paramArrayList.next();
      if ((localPlayer.getContract().getYearsLeft() == 1) && (!playersRetiring.contains(localPlayer))) {
        playersFAs.add(localPlayer);
      }
    }
  }
  
  public void checkForInjury()
  {
    playersInjured = new ArrayList();
    playersRecovered = new ArrayList();
    checkInjuryPosition(teamQBs, 1);
    checkInjuryPosition(teamRBs, 2);
    checkInjuryPosition(teamWRs, 3);
    checkInjuryPosition(teamOLs, 5);
    checkInjuryPosition(teamKs, 1);
    checkInjuryPosition(teamSs, 1);
    checkInjuryPosition(teamCBs, 3);
    checkInjuryPosition(teamDLs, 4);
    checkInjuryPosition(teamLBs, 3);
    Iterator localIterator = playersInjuredAll.iterator();
    while (localIterator.hasNext())
    {
      Player localPlayer = (Player)localIterator.next();
      if (tradingBlock.contains(localPlayer)) {
        tradingBlock.remove(localPlayer);
      }
    }
  }
  
  public void checkLeagueRecords(LeagueRecords paramLeagueRecords)
  {
    paramLeagueRecords.checkRecord("Team PPG", teamPoints / numGames(), abbr, league.getYear());
    paramLeagueRecords.checkRecord("Team Opp PPG", teamOppPoints / numGames(), abbr, league.getYear());
    paramLeagueRecords.checkRecord("Team YPG", teamYards / numGames(), abbr, league.getYear());
    paramLeagueRecords.checkRecord("Team Opp YPG", teamOppYards / numGames(), abbr, league.getYear());
    paramLeagueRecords.checkRecord("Team PPG", teamPoints / numGames(), abbr, league.getYear());
    paramLeagueRecords.checkRecord("Team TO Diff", teamTODiff, abbr, league.getYear());
    int i = 0;
    while (i < teamQBs.size())
    {
      if (getQBgamesPlayed > 6)
      {
        paramLeagueRecords.checkRecord("Pass Yards", getQBstatsPassYards, abbr + " " + getQB(i).getInitialName(), league.getYear());
        paramLeagueRecords.checkRecord("Pass TDs", getQBstatsTD, abbr + " " + getQB(i).getInitialName(), league.getYear());
        paramLeagueRecords.checkRecord("Interceptions", getQBstatsInt, abbr + " " + getQB(i).getInitialName(), league.getYear());
        paramLeagueRecords.checkRecord("Comp Percent", getQBstatsPassComp * 100 / (getQBstatsPassAtt + 1), abbr + " " + getQB(i).getInitialName(), league.getYear());
      }
      i += 1;
    }
    i = 0;
    while (i < teamRBs.size())
    {
      if (getRBgamesPlayed > 6)
      {
        paramLeagueRecords.checkRecord("Rush Yards", getRBstatsRushYards, abbr + " " + getRB(i).getInitialName(), league.getYear());
        paramLeagueRecords.checkRecord("Rush TDs", getRBstatsTD, abbr + " " + getRB(i).getInitialName(), league.getYear());
        paramLeagueRecords.checkRecord("Rush Fumbles", getRBstatsFumbles, abbr + " " + getRB(i).getInitialName(), league.getYear());
      }
      i += 1;
    }
    i = 0;
    while (i < teamWRs.size())
    {
      if (getWRgamesPlayed > 6)
      {
        paramLeagueRecords.checkRecord("Rec Yards", getWRstatsRecYards, abbr + " " + getWR(i).getInitialName(), league.getYear());
        paramLeagueRecords.checkRecord("Rec TDs", getWRstatsTD, abbr + " " + getWR(i).getInitialName(), league.getYear());
        paramLeagueRecords.checkRecord("Catch Percent", getWRstatsReceptions * 100 / (getWRstatsTargets + 1), abbr + " " + getWR(i).getInitialName(), league.getYear());
      }
      i += 1;
    }
  }
  
  public void curePlayers()
  {
    curePlayersPosition(teamQBs);
    curePlayersPosition(teamRBs);
    curePlayersPosition(teamWRs);
    curePlayersPosition(teamOLs);
    curePlayersPosition(teamKs);
    curePlayersPosition(teamSs);
    curePlayersPosition(teamCBs);
    curePlayersPosition(teamDLs);
    curePlayersPosition(teamLBs);
    sortPlayers();
  }
  
  public Player findBenchPlayer(String paramString)
  {
    Iterator localIterator = teamQBs.iterator();
    Player localPlayer;
    while (localIterator.hasNext())
    {
      localPlayer = (Player)localIterator.next();
      if (localPlayer.getPosNameYrOvrPot_Str().equals(paramString)) {
        return localPlayer;
      }
    }
    localIterator = teamRBs.iterator();
    while (localIterator.hasNext())
    {
      localPlayer = (Player)localIterator.next();
      if (localPlayer.getPosNameYrOvrPot_Str().equals(paramString)) {
        return localPlayer;
      }
    }
    localIterator = teamWRs.iterator();
    while (localIterator.hasNext())
    {
      localPlayer = (Player)localIterator.next();
      if (localPlayer.getPosNameYrOvrPot_Str().equals(paramString)) {
        return localPlayer;
      }
    }
    localIterator = teamOLs.iterator();
    while (localIterator.hasNext())
    {
      localPlayer = (Player)localIterator.next();
      if (localPlayer.getPosNameYrOvrPot_Str().equals(paramString)) {
        return localPlayer;
      }
    }
    localIterator = teamKs.iterator();
    while (localIterator.hasNext())
    {
      localPlayer = (Player)localIterator.next();
      if (localPlayer.getPosNameYrOvrPot_Str().equals(paramString)) {
        return localPlayer;
      }
    }
    localIterator = teamSs.iterator();
    while (localIterator.hasNext())
    {
      localPlayer = (Player)localIterator.next();
      if (localPlayer.getPosNameYrOvrPot_Str().equals(paramString)) {
        return localPlayer;
      }
    }
    localIterator = teamCBs.iterator();
    while (localIterator.hasNext())
    {
      localPlayer = (Player)localIterator.next();
      if (localPlayer.getPosNameYrOvrPot_Str().equals(paramString)) {
        return localPlayer;
      }
    }
    localIterator = teamDLs.iterator();
    while (localIterator.hasNext())
    {
      localPlayer = (Player)localIterator.next();
      if (localPlayer.getPosNameYrOvrPot_Str().equals(paramString)) {
        return localPlayer;
      }
    }
    localIterator = teamLBs.iterator();
    while (localIterator.hasNext())
    {
      localPlayer = (Player)localIterator.next();
      if (localPlayer.getPosNameYrOvrPot_Str().equals(paramString)) {
        return localPlayer;
      }
    }
    return null;
  }
  
  public DraftPick findPick(String paramString)
  {
    Iterator localIterator = draftPicks.iterator();
    while (localIterator.hasNext())
    {
      DraftPick localDraftPick = (DraftPick)localIterator.next();
      if (localDraftPick.getStrRepSplit().equals(paramString)) {
        return localDraftPick;
      }
    }
    return null;
  }
  
  public String gameSummaryStr(Game paramGame)
  {
    if (homeTeam == this) {
      return homeScore + " - " + awayScore + " vs " + awayTeam.abbr + " (" + awayTeam.wins + "-" + awayTeam.losses + ")";
    }
    return awayScore + " - " + homeScore + " @ " + homeTeam.abbr + " (" + homeTeam.wins + "-" + homeTeam.losses + ")";
  }
  
  public String gameSummaryStrOpponent(Game paramGame)
  {
    if (homeTeam == this) {
      return "vs " + awayTeam.abbr + " (" + awayTeam.wins + "-" + awayTeam.losses + ")";
    }
    return "@ " + homeTeam.abbr + " (" + homeTeam.wins + "-" + homeTeam.losses + ")";
  }
  
  public String gameSummaryStrScore(Game paramGame)
  {
    if (homeTeam == this) {
      return homeScore + " - " + awayScore;
    }
    return awayScore + " - " + homeScore;
  }
  
  public void generatePlayers(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
  {
    int i = teamPrestige / 20;
    i = teamPrestige;
    i = teamPrestige / 20;
    i = 0;
    while (i < paramInt1)
    {
      teamQBs.add(new PlayerQB(league.getRandName(), this));
      i += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt2)
    {
      teamRBs.add(new PlayerRB(league.getRandName(), this));
      paramInt1 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt3)
    {
      teamWRs.add(new PlayerWR(league.getRandName(), this));
      paramInt1 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt5)
    {
      teamOLs.add(new PlayerOL(league.getRandName(), this));
      paramInt1 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt4)
    {
      teamKs.add(new PlayerK(league.getRandName(), this));
      paramInt1 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt6)
    {
      teamSs.add(new PlayerS(league.getRandName(), this));
      paramInt1 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt7)
    {
      teamCBs.add(new PlayerCB(league.getRandName(), this));
      paramInt1 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt8)
    {
      teamDLs.add(new PlayerDL(league.getRandName(), this));
      paramInt1 += 1;
    }
    paramInt1 = 0;
    while (paramInt1 < paramInt9)
    {
      teamLBs.add(new PlayerLB(league.getRandName(), this));
      paramInt1 += 1;
    }
    sortPlayers();
  }
  
  public ArrayList<Player> getAllPlayers()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(teamQBs);
    localArrayList.addAll(teamRBs);
    localArrayList.addAll(teamWRs);
    localArrayList.addAll(teamOLs);
    localArrayList.addAll(teamKs);
    localArrayList.addAll(teamSs);
    localArrayList.addAll(teamCBs);
    localArrayList.addAll(teamDLs);
    localArrayList.addAll(teamLBs);
    return localArrayList;
  }
  
  public PlayerCB getCB(int paramInt)
  {
    if ((paramInt < teamCBs.size()) && (paramInt >= 0)) {
      return (PlayerCB)teamCBs.get(paramInt);
    }
    return (PlayerCB)teamCBs.get(0);
  }
  
  public String getCSV()
  {
    StringBuilder localStringBuilder = new StringBuilder().append(division).append(",").append(name).append(",").append(abbr).append(",").append(totalPlayoffWins).append(",").append(totalPlayoffLosses).append(",").append(totalSuperBowlWins).append(",").append(totalSuperBowlLosses).append(",").append(totalWins).append(",").append(totalLosses).append(",").append(teamStratOffNum).append(",").append(teamStratDefNum).append(",");
    if (showPopups) {}
    for (int i = 1;; i = 0) {
      return i + "," + winStreak.getStreakCSV() + "," + wins + "," + losses + "," + teamPoints + "," + teamOppPoints + "," + teamYards + "," + teamOppYards + "," + teamPassYards + "," + teamRushYards + "," + teamOppPassYards + "," + teamOppRushYards + "," + teamTODiff + ",:" + divChampion + ",:" + natChampWL + "," + totalDivChamps;
    }
  }
  
  public String[] getCapRoomList()
  {
    String[] arrayOfString = new String[5];
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(getAllPlayers());
    int i = 0;
    while (i < 5)
    {
      double d1 = 0.0D;
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        Player localPlayer = (Player)localIterator.next();
        if (localPlayer.getContract().getYearsLeft() >= i + 1) {
          d1 += localPlayer.getContract().getMoneyPerYear();
        }
      }
      d1 = (int)(10.0D * d1) / 10.0D;
      double d2 = (int)(10.0D * (150.0D - d1)) / 10.0D;
      arrayOfString[i] = (league.getYear() + i + ":>Total Salary Committed: $" + d1 + " mil>Cap Room: $" + d2 + "mil");
      i += 1;
    }
    return arrayOfString;
  }
  
  public int getCompositeF7Pass()
  {
    int i = 0;
    int j = 0;
    int k;
    while (j < 4)
    {
      k = teamDLs.get(j)).ratDLPow;
      i += (teamDLs.get(j)).ratDLPas + k) / 2;
      j += 1;
    }
    j = 0;
    while (j < 3)
    {
      k = teamLBs.get(j)).ratLBTck;
      i += (teamLBs.get(j)).ratLBPas + k) / 2;
      j += 1;
    }
    return i / 7;
  }
  
  public int getCompositeF7Rush()
  {
    int i = 0;
    int j = 0;
    int k;
    while (j < 4)
    {
      k = teamDLs.get(j)).ratDLPow;
      i += (teamDLs.get(j)).ratDLRsh + k) / 2;
      j += 1;
    }
    j = 0;
    while (j < 3)
    {
      k = teamLBs.get(j)).ratLBTck;
      i += (teamLBs.get(j)).ratLBRsh + k) / 2;
      j += 1;
    }
    return i / 7;
  }
  
  public int getCompositeFootIQ()
  {
    int j = 0 + getQB0ratFootIQ * 5 + (getRB0ratFootIQ + getRB1ratFootIQ) + (getWR0ratFootIQ + getWR1ratFootIQ + getWR2ratFootIQ);
    int i = 0;
    while (i < 5)
    {
      j += getOLratFootIQ / 5;
      i += 1;
    }
    i = j + getS0ratFootIQ * 5 + (getCB0ratFootIQ + getCB1ratFootIQ + getCB2ratFootIQ);
    j = 0;
    while (j < 4)
    {
      i += getDLratFootIQ / 7;
      j += 1;
    }
    j = 0;
    while (j < 3)
    {
      i += getLBratFootIQ / 7;
      j += 1;
    }
    return i / 20;
  }
  
  public int getCompositeOLPass()
  {
    int j = 0;
    int i = 0;
    while (i < 5)
    {
      int k = teamOLs.get(i)).ratOLPow;
      j += (teamOLs.get(i)).ratOLBkP + k) / 2;
      i += 1;
    }
    return j / 5;
  }
  
  public int getCompositeOLRush()
  {
    int j = 0;
    int i = 0;
    while (i < 5)
    {
      int k = teamOLs.get(i)).ratOLPow;
      j += (teamOLs.get(i)).ratOLBkR + k) / 2;
      i += 1;
    }
    return j / 5;
  }
  
  public PlayerDL getDL(int paramInt)
  {
    if ((paramInt < teamDLs.size()) && (paramInt >= 0)) {
      return (PlayerDL)teamDLs.get(paramInt);
    }
    return (PlayerDL)teamDLs.get(0);
  }
  
  public int getDefTalent()
  {
    return (getRushDef() + getPassDef()) / 2;
  }
  
  public int getDivLosses()
  {
    int k = 0;
    int j = 0;
    if (j < gameWLSchedule.size())
    {
      Game localGame = (Game)gameSchedule.get(j);
      int i = k;
      if (gameName.equals("Division"))
      {
        if ((homeTeam != this) || (homeScore >= awayScore)) {
          break label79;
        }
        i = k + 1;
      }
      for (;;)
      {
        j += 1;
        k = i;
        break;
        label79:
        i = k;
        if (awayTeam == this)
        {
          i = k;
          if (homeScore > awayScore) {
            i = k + 1;
          }
        }
      }
    }
    return k;
  }
  
  public int getDivWins()
  {
    int k = 0;
    int j = 0;
    if (j < gameWLSchedule.size())
    {
      Game localGame = (Game)gameSchedule.get(j);
      int i = k;
      if (gameName.equals("Division"))
      {
        if ((homeTeam != this) || (homeScore <= awayScore)) {
          break label79;
        }
        i = k + 1;
      }
      for (;;)
      {
        j += 1;
        k = i;
        break;
        label79:
        i = k;
        if (awayTeam == this)
        {
          i = k;
          if (homeScore < awayScore) {
            i = k + 1;
          }
        }
      }
    }
    return k;
  }
  
  public String[] getGameSummaryStr(int paramInt)
  {
    String[] arrayOfString = new String[3];
    Game localGame = (Game)gameSchedule.get(paramInt);
    arrayOfString[0] = gameName;
    if (paramInt < gameWLSchedule.size())
    {
      arrayOfString[1] = ((String)gameWLSchedule.get(paramInt) + " " + gameSummaryStrScore(localGame));
      if (numOT > 0) {
        arrayOfString[1] = (arrayOfString[1] + " (" + numOT + "OT)");
      }
    }
    for (;;)
    {
      arrayOfString[2] = gameSummaryStrOpponent(localGame);
      return arrayOfString;
      arrayOfString[1] = "---";
    }
  }
  
  public String[] getInjuryReport()
  {
    String[] arrayOfString2;
    if ((playersInjured.size() > 0) || (playersRecovered.size() > 0))
    {
      if (playersRecovered.size() > 0) {}
      int i;
      for (String[] arrayOfString1 = new String[playersInjured.size() + playersRecovered.size() + 1];; arrayOfString1 = new String[playersInjured.size()])
      {
        i = 0;
        while (i < playersInjured.size())
        {
          arrayOfString1[i] = ((Player)playersInjured.get(i)).getPosNameYrOvrPot_Str();
          i += 1;
        }
      }
      arrayOfString2 = arrayOfString1;
      if (playersRecovered.size() > 0)
      {
        arrayOfString1[playersInjured.size()] = "Players Recovered from Injuries:";
        i = 0;
        for (;;)
        {
          arrayOfString2 = arrayOfString1;
          if (i >= playersRecovered.size()) {
            break;
          }
          arrayOfString1[(playersInjured.size() + i + 1)] = ((Player)playersRecovered.get(i)).getPosNameYrOvrPot_Str();
          i += 1;
        }
      }
    }
    else
    {
      arrayOfString2 = null;
    }
    return arrayOfString2;
  }
  
  public PlayerK getK(int paramInt)
  {
    if ((paramInt < teamKs.size()) && (paramInt >= 0)) {
      return (PlayerK)teamKs.get(paramInt);
    }
    return (PlayerK)teamKs.get(0);
  }
  
  public PlayerLB getLB(int paramInt)
  {
    if ((paramInt < teamLBs.size()) && (paramInt >= 0)) {
      return (PlayerLB)teamLBs.get(paramInt);
    }
    return (PlayerLB)teamLBs.get(0);
  }
  
  public String getNeededPosition(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      arrayOfInt = new int[9];
      arrayOfInt[0] = getWeightedAvgOverallList(teamQBs, 1);
      arrayOfInt[1] = getWeightedAvgOverallList(teamRBs, 2);
      arrayOfInt[2] = getWeightedAvgOverallList(teamWRs, 3);
      arrayOfInt[3] = getWeightedAvgOverallList(teamOLs, 5);
      arrayOfInt[4] = getWeightedAvgOverallList(teamKs, 1);
      arrayOfInt[5] = getWeightedAvgOverallList(teamSs, 1);
      arrayOfInt[6] = getWeightedAvgOverallList(teamCBs, 3);
      arrayOfInt[7] = getWeightedAvgOverallList(teamDLs, 4);
      arrayOfInt[8] = getWeightedAvgOverallList(teamLBs, 3);
      Arrays.sort(arrayOfInt);
      if ((arrayOfInt[0] == getWeightedAvgOverallList(teamQBs, 1)) && (teamQBs.size() < 4)) {
        return "QB";
      }
      if ((arrayOfInt[0] == getWeightedAvgOverallList(teamRBs, 2)) && (teamRBs.size() < 6)) {
        return "RB";
      }
      if ((arrayOfInt[0] == getWeightedAvgOverallList(teamSs, 1)) && (teamSs.size() < 4)) {
        return "S";
      }
      if ((arrayOfInt[0] == getWeightedAvgOverallList(teamWRs, 3)) && (teamWRs.size() < 8)) {
        return "WR";
      }
      if ((arrayOfInt[0] == getWeightedAvgOverallList(teamOLs, 5)) && (teamOLs.size() < 12)) {
        return "OL";
      }
      if ((arrayOfInt[0] == getWeightedAvgOverallList(teamCBs, 3)) && (teamCBs.size() < 8)) {
        return "CB";
      }
      if ((arrayOfInt[0] == getWeightedAvgOverallList(teamDLs, 4)) && (teamDLs.size() < 10)) {
        return "DL";
      }
      if ((arrayOfInt[0] == getWeightedAvgOverallList(teamLBs, 3)) && (teamLBs.size() < 8)) {
        return "LB";
      }
      if ((arrayOfInt[0] == getWeightedAvgOverallList(teamKs, 1)) && (teamKs.size() < 4)) {
        return "K";
      }
      i = (int)(Math.random() * 8.0D);
      return new String[] { "QB", "RB", "WR", "OL", "K", "S", "CB", "DL", "LB" }[i];
    }
    int[] arrayOfInt = new int[8];
    arrayOfInt[0] = getWeightedAvgOverallList(teamQBs, 1);
    arrayOfInt[1] = getWeightedAvgOverallList(teamRBs, 2);
    arrayOfInt[2] = getWeightedAvgOverallList(teamWRs, 3);
    arrayOfInt[3] = getWeightedAvgOverallList(teamOLs, 5);
    arrayOfInt[4] = getWeightedAvgOverallList(teamSs, 1);
    arrayOfInt[5] = getWeightedAvgOverallList(teamCBs, 3);
    arrayOfInt[6] = getWeightedAvgOverallList(teamDLs, 4);
    arrayOfInt[7] = getWeightedAvgOverallList(teamLBs, 3);
    Arrays.sort(arrayOfInt);
    if ((arrayOfInt[0] == getWeightedAvgOverallList(teamQBs, 1)) && (teamQBs.size() < 4)) {
      return "QB";
    }
    if ((arrayOfInt[0] == getWeightedAvgOverallList(teamRBs, 2)) && (teamRBs.size() < 6)) {
      return "RB";
    }
    if ((arrayOfInt[0] == getWeightedAvgOverallList(teamSs, 1)) && (teamSs.size() < 4)) {
      return "S";
    }
    if ((arrayOfInt[0] == getWeightedAvgOverallList(teamWRs, 3)) && (teamWRs.size() < 9)) {
      return "WR";
    }
    if ((arrayOfInt[0] == getWeightedAvgOverallList(teamOLs, 5)) && (teamOLs.size() < 14)) {
      return "OL";
    }
    if ((arrayOfInt[0] == getWeightedAvgOverallList(teamCBs, 3)) && (teamCBs.size() < 9)) {
      return "CB";
    }
    if ((arrayOfInt[0] == getWeightedAvgOverallList(teamDLs, 4)) && (teamDLs.size() < 10)) {
      return "DL";
    }
    if ((arrayOfInt[0] == getWeightedAvgOverallList(teamLBs, 3)) && (teamLBs.size() < 8)) {
      return "LB";
    }
    int i = (int)(Math.random() * 8.0D);
    return new String[] { "QB", "RB", "WR", "OL", "K", "S", "CB", "DL", "LB" }[i];
  }
  
  public ArrayList<String> getNeededPositionsSorted()
  {
    int[] arrayOfInt = new int[9];
    arrayOfInt[0] = getWeightedAvgOverallList(teamQBs, 1);
    arrayOfInt[1] = getWeightedAvgOverallList(teamRBs, 2);
    arrayOfInt[2] = getWeightedAvgOverallList(teamWRs, 3);
    arrayOfInt[3] = getWeightedAvgOverallList(teamOLs, 5);
    arrayOfInt[4] = getWeightedAvgOverallList(teamKs, 1);
    arrayOfInt[5] = getWeightedAvgOverallList(teamSs, 1);
    arrayOfInt[6] = getWeightedAvgOverallList(teamCBs, 3);
    arrayOfInt[7] = getWeightedAvgOverallList(teamDLs, 4);
    arrayOfInt[8] = getWeightedAvgOverallList(teamLBs, 3);
    Arrays.sort(arrayOfInt);
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < arrayOfInt.length) {
      i += 1;
    }
    return localArrayList;
  }
  
  public int getNeedsPosition(String paramString)
  {
    if (paramString.equals("QB")) {
      return 2 - teamQBs.size();
    }
    if (paramString.equals("RB")) {
      return 4 - teamRBs.size();
    }
    if (paramString.equals("WR")) {
      return 6 - teamWRs.size();
    }
    if (paramString.equals("OL")) {
      return 10 - teamOLs.size();
    }
    if (paramString.equals("K")) {
      return 2 - teamKs.size();
    }
    if (paramString.equals("S")) {
      return 2 - teamSs.size();
    }
    if (paramString.equals("CB")) {
      return 6 - teamCBs.size();
    }
    if (paramString.equals("DL")) {
      return 8 - teamDLs.size();
    }
    if (paramString.equals("LB")) {
      return 8 - teamLBs.size();
    }
    return 0;
  }
  
  public PlayerOL getOL(int paramInt)
  {
    if ((paramInt < teamOLs.size()) && (paramInt >= 0)) {
      return (PlayerOL)teamOLs.get(paramInt);
    }
    return (PlayerOL)teamOLs.get(0);
  }
  
  public int getOPPG()
  {
    return teamOppPoints / numGames();
  }
  
  public int getOPYPG()
  {
    return teamOppPassYards / numGames();
  }
  
  public int getORYPG()
  {
    return teamOppRushYards / numGames();
  }
  
  public int getOYPG()
  {
    return teamOppYards / numGames();
  }
  
  public int getOffTalent()
  {
    int i = getQB0ratOvr;
    int j = teamWRs.get(0)).ratOvr;
    int k = teamWRs.get(1)).ratOvr;
    int m = teamWRs.get(2)).ratOvr;
    int n = teamRBs.get(0)).ratOvr;
    return (teamRBs.get(1)).ratOvr + (i * 5 + j + k + m + n) + getCompositeOLPass() + getCompositeOLRush()) / 12;
  }
  
  public String[] getOnlineGameSummaryStr(int paramInt)
  {
    String[] arrayOfString = new String[3];
    Game localGame = (Game)gameSchedule.get(paramInt);
    if (affectsELO)
    {
      arrayOfString[0] = "Ranked";
      if (paramInt >= gameWLSchedule.size()) {
        break label146;
      }
      arrayOfString[1] = ((String)gameWLSchedule.get(paramInt) + " " + gameSummaryStrScore(localGame));
      if (numOT > 0) {
        arrayOfString[1] = (arrayOfString[1] + " (" + numOT + "OT)");
      }
    }
    for (;;)
    {
      arrayOfString[2] = gameSummaryStrOpponent(localGame);
      return arrayOfString;
      arrayOfString[0] = "Custom";
      break;
      label146:
      arrayOfString[1] = "---";
    }
  }
  
  public Map<String, List<String>> getOnlinePlayerStatsExpandListMap(List<String> paramList)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put((String)paramList.get(0), getQB(0).getDetailStatsOffseason().subList(0, 3));
    int i = 1;
    while (i < 3)
    {
      localLinkedHashMap.put((String)paramList.get(i), getRB(i - 1).getDetailStatsOffseason().subList(0, 3));
      i += 1;
    }
    i = 3;
    while (i < 6)
    {
      localLinkedHashMap.put((String)paramList.get(i), getWR(i - 3).getDetailStatsOffseason().subList(0, 3));
      i += 1;
    }
    i = 6;
    while (i < 11)
    {
      localLinkedHashMap.put((String)paramList.get(i), getOL(i - 6).getDetailStatsOffseason().subList(0, 3));
      i += 1;
    }
    localLinkedHashMap.put((String)paramList.get(11), getK(0).getDetailStatsOffseason().subList(0, 3));
    localLinkedHashMap.put((String)paramList.get(12), getS(0).getDetailStatsOffseason().subList(0, 3));
    i = 13;
    while (i < 16)
    {
      localLinkedHashMap.put((String)paramList.get(i), getCB(i - 13).getDetailStatsOffseason().subList(0, 3));
      i += 1;
    }
    i = 16;
    while (i < 20)
    {
      localLinkedHashMap.put((String)paramList.get(i), getDL(i - 16).getDetailStatsOffseason().subList(0, 3));
      i += 1;
    }
    i = 20;
    while (i < 23)
    {
      localLinkedHashMap.put((String)paramList.get(i), getLB(i - 20).getDetailStatsOffseason().subList(0, 3));
      i += 1;
    }
    return localLinkedHashMap;
  }
  
  public List<String> getOnlinePlayerStatsExpandListStr()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getQB(0).getPosNameYrOvrPot_Str());
    int i = 0;
    while (i < 2)
    {
      localArrayList.add(getRB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 0;
    while (i < 3)
    {
      localArrayList.add(getWR(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 0;
    while (i < 5)
    {
      localArrayList.add(getOL(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    localArrayList.add(getK(0).getPosNameYrOvrPot_Str());
    localArrayList.add(getS(0).getPosNameYrOvrPot_Str());
    i = 0;
    while (i < 3)
    {
      localArrayList.add(getCB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 0;
    while (i < 4)
    {
      localArrayList.add(getDL(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 0;
    while (i < 3)
    {
      localArrayList.add(getLB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    return localArrayList;
  }
  
  public int getPPG()
  {
    return teamPoints / numGames();
  }
  
  public int getPYPG()
  {
    return teamPassYards / numGames();
  }
  
  public int getPassDef()
  {
    int i = teamCBs.get(0)).ratOvr;
    int j = teamCBs.get(1)).ratOvr;
    i = (teamCBs.get(2)).ratOvr + (i + j)) / 3;
    return (teamSs.get(0)).ratOvr + i * 3 + getCompositeF7Pass() * 2) / 6;
  }
  
  public int getPassProf()
  {
    int i = teamWRs.get(0)).ratOvr;
    int j = teamWRs.get(1)).ratOvr;
    i = (teamWRs.get(2)).ratOvr + (i + j)) / 3;
    return (getCompositeOLPass() + getQB0ratOvr * 2 + i) / 4;
  }
  
  public String getPlayerInfoSaveFile(boolean paramBoolean)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = teamQBs.iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (PlayerQB)localIterator.next();
      localStringBuilder.append(((PlayerQB)localObject).getCSV() + "%\n");
    }
    localIterator = teamRBs.iterator();
    while (localIterator.hasNext())
    {
      localObject = (PlayerRB)localIterator.next();
      localStringBuilder.append(((PlayerRB)localObject).getCSV() + "%\n");
    }
    localIterator = teamWRs.iterator();
    while (localIterator.hasNext())
    {
      localObject = (PlayerWR)localIterator.next();
      localStringBuilder.append(((PlayerWR)localObject).getCSV() + "%\n");
    }
    localIterator = teamKs.iterator();
    while (localIterator.hasNext())
    {
      localObject = (PlayerK)localIterator.next();
      localStringBuilder.append(((PlayerK)localObject).getCSV() + "%\n");
    }
    localIterator = teamOLs.iterator();
    while (localIterator.hasNext())
    {
      localObject = (PlayerOL)localIterator.next();
      localStringBuilder.append(((PlayerOL)localObject).getCSV() + "%\n");
    }
    localIterator = teamSs.iterator();
    while (localIterator.hasNext())
    {
      localObject = (PlayerS)localIterator.next();
      localStringBuilder.append(((PlayerS)localObject).getCSV() + "%\n");
    }
    localIterator = teamCBs.iterator();
    while (localIterator.hasNext())
    {
      localObject = (PlayerCB)localIterator.next();
      localStringBuilder.append(((PlayerCB)localObject).getCSV() + "%\n");
    }
    localIterator = teamDLs.iterator();
    while (localIterator.hasNext())
    {
      localObject = (PlayerDL)localIterator.next();
      localStringBuilder.append(((PlayerDL)localObject).getCSV() + "%\n");
    }
    localIterator = teamLBs.iterator();
    while (localIterator.hasNext())
    {
      localObject = (PlayerLB)localIterator.next();
      localStringBuilder.append(((PlayerLB)localObject).getCSV() + "%\n");
    }
    if (paramBoolean)
    {
      localIterator = playersFAs.iterator();
      while (localIterator.hasNext())
      {
        localObject = (Player)localIterator.next();
        localStringBuilder.append("[FA]" + ((Player)localObject).getCSV() + "%\n");
      }
    }
    return localStringBuilder.toString();
  }
  
  public Map<String, List<String>> getPlayerStatsExpandListMap(List<String> paramList)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    localLinkedHashMap.put((String)paramList.get(0), getQB(0).getDetailStatsList(numGames()));
    int i = 1;
    while (i < 3)
    {
      localLinkedHashMap.put((String)paramList.get(i), getRB(i - 1).getDetailStatsList(numGames()));
      i += 1;
    }
    i = 3;
    while (i < 6)
    {
      localLinkedHashMap.put((String)paramList.get(i), getWR(i - 3).getDetailStatsList(numGames()));
      i += 1;
    }
    i = 6;
    while (i < 11)
    {
      localLinkedHashMap.put((String)paramList.get(i), getOL(i - 6).getDetailStatsList(numGames()));
      i += 1;
    }
    localLinkedHashMap.put((String)paramList.get(11), getK(0).getDetailStatsList(numGames()));
    localLinkedHashMap.put((String)paramList.get(12), getS(0).getDetailStatsList(numGames()));
    i = 13;
    while (i < 16)
    {
      localLinkedHashMap.put((String)paramList.get(i), getCB(i - 13).getDetailStatsList(numGames()));
      i += 1;
    }
    i = 16;
    while (i < 20)
    {
      localLinkedHashMap.put((String)paramList.get(i), getDL(i - 16).getDetailStatsList(numGames()));
      i += 1;
    }
    i = 20;
    while (i < 23)
    {
      localLinkedHashMap.put((String)paramList.get(i), getLB(i - 20).getDetailStatsList(numGames()));
      i += 1;
    }
    Object localObject1 = (String)paramList.get(23);
    Object localObject2 = new ArrayList();
    i = 1;
    while (i < teamQBs.size())
    {
      ((ArrayList)localObject2).add(getQB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 2;
    while (i < teamRBs.size())
    {
      ((ArrayList)localObject2).add(getRB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 3;
    while (i < teamWRs.size())
    {
      ((ArrayList)localObject2).add(getWR(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 5;
    while (i < teamOLs.size())
    {
      ((ArrayList)localObject2).add(getOL(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 1;
    while (i < teamKs.size())
    {
      ((ArrayList)localObject2).add(getK(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 1;
    while (i < teamSs.size())
    {
      ((ArrayList)localObject2).add(getS(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 3;
    while (i < teamCBs.size())
    {
      ((ArrayList)localObject2).add(getCB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 4;
    while (i < teamDLs.size())
    {
      ((ArrayList)localObject2).add(getDL(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 3;
    while (i < teamLBs.size())
    {
      ((ArrayList)localObject2).add(getLB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    localLinkedHashMap.put(localObject1, localObject2);
    paramList = (String)paramList.get(24);
    localObject1 = new ArrayList();
    localObject2 = draftPicks.iterator();
    while (((Iterator)localObject2).hasNext()) {
      ((ArrayList)localObject1).add(((DraftPick)((Iterator)localObject2).next()).getStrRepSplit());
    }
    localLinkedHashMap.put(paramList, localObject1);
    return localLinkedHashMap;
  }
  
  public List<String> getPlayerStatsExpandListStr()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getQB(0).getPosNameYrOvrPot_Str());
    int i = 0;
    while (i < 2)
    {
      localArrayList.add(getRB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 0;
    while (i < 3)
    {
      localArrayList.add(getWR(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 0;
    while (i < 5)
    {
      localArrayList.add(getOL(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    localArrayList.add(getK(0).getPosNameYrOvrPot_Str());
    localArrayList.add(getS(0).getPosNameYrOvrPot_Str());
    i = 0;
    while (i < 3)
    {
      localArrayList.add(getCB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 0;
    while (i < 4)
    {
      localArrayList.add(getDL(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    i = 0;
    while (i < 3)
    {
      localArrayList.add(getLB(i).getPosNameYrOvrPot_Str());
      i += 1;
    }
    localArrayList.add("BENCH > BENCH");
    localArrayList.add("DRAFT PICKS > DRAFT PICKS");
    return localArrayList;
  }
  
  public String getPlayersCSV_Online()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getQB(0).getCSV_Online()).append(">");
    localStringBuilder.append(getRB(0).getCSV_Online()).append(">");
    localStringBuilder.append(getRB(1).getCSV_Online()).append(">");
    localStringBuilder.append(getWR(0).getCSV_Online()).append(">");
    localStringBuilder.append(getWR(1).getCSV_Online()).append(">");
    localStringBuilder.append(getWR(2).getCSV_Online()).append(">");
    localStringBuilder.append(getOL(0).getCSV_Online()).append(">");
    localStringBuilder.append(getOL(1).getCSV_Online()).append(">");
    localStringBuilder.append(getOL(2).getCSV_Online()).append(">");
    localStringBuilder.append(getOL(3).getCSV_Online()).append(">");
    localStringBuilder.append(getOL(4).getCSV_Online()).append(">");
    localStringBuilder.append(getK(0).getCSV_Online()).append(">");
    localStringBuilder.append(getS(0).getCSV_Online()).append(">");
    localStringBuilder.append(getCB(0).getCSV_Online()).append(">");
    localStringBuilder.append(getCB(1).getCSV_Online()).append(">");
    localStringBuilder.append(getCB(2).getCSV_Online()).append(">");
    localStringBuilder.append(getDL(0).getCSV_Online()).append(">");
    localStringBuilder.append(getDL(1).getCSV_Online()).append(">");
    localStringBuilder.append(getDL(2).getCSV_Online()).append(">");
    localStringBuilder.append(getDL(3).getCSV_Online()).append(">");
    localStringBuilder.append(getLB(0).getCSV_Online()).append(">");
    localStringBuilder.append(getLB(1).getCSV_Online()).append(">");
    localStringBuilder.append(getLB(2).getCSV_Online());
    return localStringBuilder.toString();
  }
  
  public String[] getPlayersFAList()
  {
    String[] arrayOfString = new String[playersFAs.size()];
    int i = 0;
    while (i < arrayOfString.length)
    {
      arrayOfString[i] = ((Player)playersFAs.get(i)).getPosNameYrOvrPot_Split();
      i += 1;
    }
    return arrayOfString;
  }
  
  public String getPlayersFAStr()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = playersFAs.iterator();
    while (localIterator.hasNext())
    {
      Player localPlayer = (Player)localIterator.next();
      localStringBuilder.append(localPlayer.getPosNameYrOvrPot_OneLine() + "\n");
    }
    return localStringBuilder.toString();
  }
  
  public void getPlayersInjuredAll()
  {
    Object localObject = getAllPlayers();
    playersInjuredAll = new ArrayList();
    localObject = ((ArrayList)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Player localPlayer = (Player)((Iterator)localObject).next();
      if (injury != null) {
        playersInjuredAll.add(localPlayer);
      }
    }
  }
  
  public void getPlayersLeaving()
  {
    if ((playersRetiring.isEmpty()) && (playersFAs.isEmpty()))
    {
      checkRetirementList(teamQBs);
      checkRetirementList(teamRBs);
      checkRetirementList(teamWRs);
      checkRetirementList(teamOLs);
      checkRetirementList(teamKs);
      checkRetirementList(teamSs);
      checkRetirementList(teamCBs);
      checkRetirementList(teamDLs);
      checkRetirementList(teamLBs);
      checkExpiringFAs(getAllPlayers());
    }
    for (;;)
    {
      return;
      if (userControlled)
      {
        Iterator localIterator = playersFAs.iterator();
        while (localIterator.hasNext())
        {
          Player localPlayer = (Player)localIterator.next();
          System.out.println("Team FA: " + localPlayer.getPosNameYrOvrPot_OneLine());
        }
      }
    }
  }
  
  public String[] getPlayersRetiringList()
  {
    String[] arrayOfString = new String[playersRetiring.size()];
    int i = 0;
    while (i < arrayOfString.length)
    {
      arrayOfString[i] = ((Player)playersRetiring.get(i)).getPosNameYrOvrPot_Split();
      i += 1;
    }
    return arrayOfString;
  }
  
  public String getPlayersRetiringStr()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = playersRetiring.iterator();
    while (localIterator.hasNext())
    {
      Player localPlayer = (Player)localIterator.next();
      localStringBuilder.append(localPlayer.getPosNameYrOvrPot_OneLine() + "\n");
    }
    return localStringBuilder.toString();
  }
  
  public ArrayList<? extends Player> getPosList(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return getAllPlayers();
    case 0: 
      return getAllPlayers();
    case 1: 
      return teamQBs;
    case 2: 
      return teamRBs;
    case 3: 
      return teamWRs;
    case 4: 
      return teamOLs;
    case 5: 
      return teamKs;
    case 6: 
      return teamSs;
    case 7: 
      return teamCBs;
    case 8: 
      return teamDLs;
    }
    return teamLBs;
  }
  
  public PlayerQB getQB(int paramInt)
  {
    if ((paramInt < teamQBs.size()) && (paramInt >= 0)) {
      return (PlayerQB)teamQBs.get(paramInt);
    }
    return (PlayerQB)teamQBs.get(0);
  }
  
  public PlayerRB getRB(int paramInt)
  {
    if ((paramInt < teamRBs.size()) && (paramInt >= 0)) {
      return (PlayerRB)teamRBs.get(paramInt);
    }
    return (PlayerRB)teamRBs.get(0);
  }
  
  public int getRYPG()
  {
    return teamRushYards / numGames();
  }
  
  public String getRankStr(int paramInt)
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
  
  public String getRankStrStarUser(int paramInt)
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
  
  public int getRushDef()
  {
    return getCompositeF7Rush();
  }
  
  public int getRushProf()
  {
    int i = teamRBs.get(0)).ratOvr;
    i = (teamRBs.get(1)).ratOvr + i) / 2;
    return (getCompositeOLRush() + i) / 2;
  }
  
  public PlayerS getS(int paramInt)
  {
    if ((paramInt < teamSs.size()) && (paramInt >= 0)) {
      return (PlayerS)teamSs.get(paramInt);
    }
    return (PlayerS)teamSs.get(0);
  }
  
  public double getSalaryCapRoom()
  {
    return (int)(10.0D * ((int)((150.0D - getTotalSalary()) * 10.0D) / 10.0D)) / 10.0D;
  }
  
  public String getStrAbbrWL()
  {
    return abbr + " (" + wins + "-" + losses + ")";
  }
  
  public String getStrAbbrWL_2Lines()
  {
    return abbr + "\n(" + wins + "-" + losses + ")";
  }
  
  public String getTODiff()
  {
    if (teamTODiff > 0) {
      return "+" + teamTODiff;
    }
    return teamTODiff + "";
  }
  
  public String[] getTeamHistoryList()
  {
    String[] arrayOfString = new String[teamHistory.size() + 5];
    arrayOfString[0] = ("Overall W-L: " + totalWins + "-" + totalLosses);
    arrayOfString[1] = ("Division Champions: " + totalDivChamps);
    arrayOfString[2] = ("Playoff Record: " + totalPlayoffWins + "-" + totalPlayoffLosses);
    arrayOfString[3] = ("Champ Bowl Record: " + totalSuperBowlWins + "-" + totalSuperBowlLosses);
    arrayOfString[4] = " ";
    int i = 0;
    while (i < teamHistory.size())
    {
      arrayOfString[(i + 5)] = ((String)teamHistory.get(i));
      i += 1;
    }
    return arrayOfString;
  }
  
  public String getTeamNeeds()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\t\t" + (2 - teamQBs.size()) + "QBs, ");
    localStringBuilder.append(4 - teamRBs.size() + "RBs, ");
    localStringBuilder.append(6 - teamWRs.size() + "WRs, ");
    localStringBuilder.append(2 - teamKs.size() + "Ks\n");
    localStringBuilder.append("\t\t" + (10 - teamOLs.size()) + "OLs, ");
    localStringBuilder.append(2 - teamSs.size() + "Ss, ");
    localStringBuilder.append(6 - teamCBs.size() + "CBs, ");
    localStringBuilder.append(8 - teamDLs.size() + "DLs");
    localStringBuilder.append(6 - teamLBs.size() + "LBs");
    return localStringBuilder.toString();
  }
  
  public String getTeamStatsStrCSV()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(teamPollScore + ",");
    localStringBuilder.append("Power Rank,");
    localStringBuilder.append(getRankStr(rankTeamPollScore) + "%\n");
    localStringBuilder.append(teamStrengthOfWins + ",");
    localStringBuilder.append("SOS,");
    localStringBuilder.append(getRankStr(rankTeamStrengthOfWins) + "%\n");
    localStringBuilder.append(teamPoints / numGames() + ",");
    localStringBuilder.append("Points,");
    localStringBuilder.append(getRankStr(rankTeamPoints) + "%\n");
    localStringBuilder.append(teamOppPoints / numGames() + ",");
    localStringBuilder.append("Opp Points,");
    localStringBuilder.append(getRankStr(rankTeamOppPoints) + "%\n");
    localStringBuilder.append(teamYards / numGames() + ",");
    localStringBuilder.append("Yards,");
    localStringBuilder.append(getRankStr(rankTeamYards) + "%\n");
    localStringBuilder.append(teamOppYards / numGames() + ",");
    localStringBuilder.append("Opp Yards,");
    localStringBuilder.append(getRankStr(rankTeamOppYards) + "%\n");
    localStringBuilder.append(teamPassYards / numGames() + ",");
    localStringBuilder.append("Pass Yards,");
    localStringBuilder.append(getRankStr(rankTeamPassYards) + "%\n");
    localStringBuilder.append(teamRushYards / numGames() + ",");
    localStringBuilder.append("Rush Yards,");
    localStringBuilder.append(getRankStr(rankTeamRushYards) + "%\n");
    localStringBuilder.append(teamOppPassYards / numGames() + ",");
    localStringBuilder.append("Opp Pass YPG,");
    localStringBuilder.append(getRankStr(rankTeamOppPassYards) + "%\n");
    localStringBuilder.append(teamOppRushYards / numGames() + ",");
    localStringBuilder.append("Opp Rush YPG,");
    localStringBuilder.append(getRankStr(rankTeamOppRushYards) + "%\n");
    if (teamTODiff > 0) {
      localStringBuilder.append("+" + teamTODiff + ",");
    }
    for (;;)
    {
      localStringBuilder.append("TO Diff,");
      localStringBuilder.append(getRankStr(rankTeamTODiff) + "%\n");
      localStringBuilder.append(teamOffTalent + ",");
      localStringBuilder.append("Off Talent,");
      localStringBuilder.append(getRankStr(rankTeamOffTalent) + "%\n");
      localStringBuilder.append(teamDefTalent + ",");
      localStringBuilder.append("Def Talent,");
      localStringBuilder.append(getRankStr(rankTeamDefTalent) + "%\n");
      localStringBuilder.append("$" + getTotalSalary() + ",");
      localStringBuilder.append("Total Salary,");
      localStringBuilder.append("$" + getSalaryCapRoom() + " room%\n");
      localStringBuilder.append("PROFILE_BUTTON");
      return localStringBuilder.toString();
      localStringBuilder.append(teamTODiff + ",");
    }
  }
  
  public TeamStrategy[] getTeamStrategiesDef()
  {
    return new TeamStrategy[] { new TeamStrategy("Stack the Box", "Focus on stopping the run. Will give up more big passing plays but will allow less rushing yards and far less big plays from rushing.", 1, 0, -1, -1), new TeamStrategy("No Preference", "Will play a normal defense with no bonus either way, but no penalties either.", 0, 0, 0, 0), new TeamStrategy("No Fly Zone", "Focus on stopping the pass. Will give up less yards on catches and will be more likely to intercept passes, but will allow more rushing yards.", -1, 0, 1, 1) };
  }
  
  public TeamStrategy[] getTeamStrategiesOff()
  {
    return new TeamStrategy[] { new TeamStrategy("Aggressive", "Play a more aggressive offense. Will pass with lower completion percentage and higher chance of interception. However, catches will go for more yards.", -1, 2, 3, 1), new TeamStrategy("No Preference", "Will play a normal offense with no bonus either way, but no penalties either.", 0, 0, 0, 0), new TeamStrategy("Conservative", "Play a more conservative offense, running a bit more and passing slightly less. Passes are more accurate but shorter. Rushes are more likely to gain yards but less likely to break free for big plays.", 1, -2, -3, -1) };
  }
  
  public double getTotalSalary()
  {
    double d = 0.0D;
    Iterator localIterator = getAllPlayers().iterator();
    while (localIterator.hasNext())
    {
      Player localPlayer = (Player)localIterator.next();
      if (localPlayer.getContract() != null) {
        d += localPlayer.getContract().getMoneyPerYear();
      } else {
        System.out.println("NULL CONTRACT: " + localPlayer.getPosNameYrOvrPot_OneLine() + " from team " + team.abbr);
      }
    }
    return (int)(10.0D * d) / 10.0D;
  }
  
  public int getValueAdded(Player paramPlayer)
  {
    if ((paramPlayer instanceof PlayerQB)) {
      return getValueAddedList(teamQBs, paramPlayer, 1);
    }
    if ((paramPlayer instanceof PlayerRB)) {
      return getValueAddedList(teamRBs, paramPlayer, 2);
    }
    if ((paramPlayer instanceof PlayerWR)) {
      return getValueAddedList(teamWRs, paramPlayer, 3);
    }
    if ((paramPlayer instanceof PlayerOL)) {
      return getValueAddedList(teamOLs, paramPlayer, 5);
    }
    if ((paramPlayer instanceof PlayerK)) {
      return getValueAddedList(teamKs, paramPlayer, 1);
    }
    if ((paramPlayer instanceof PlayerS)) {
      return getValueAddedList(teamSs, paramPlayer, 1);
    }
    if ((paramPlayer instanceof PlayerCB)) {
      return getValueAddedList(teamCBs, paramPlayer, 3);
    }
    if ((paramPlayer instanceof PlayerDL)) {
      return getValueAddedList(teamDLs, paramPlayer, 4);
    }
    if ((paramPlayer instanceof PlayerLB)) {
      return getValueAddedList(teamLBs, paramPlayer, 3);
    }
    return 0;
  }
  
  public PlayerWR getWR(int paramInt)
  {
    if ((paramInt < teamWRs.size()) && (paramInt >= 0)) {
      return (PlayerWR)teamWRs.get(paramInt);
    }
    return (PlayerWR)teamWRs.get(0);
  }
  
  public int getYPG()
  {
    return teamYards / numGames();
  }
  
  public int numGames()
  {
    if (wins + losses > 0) {
      return wins + losses;
    }
    return 1;
  }
  
  public void recruitUDFA()
  {
    int j = teamQBs.size();
    int i = 0;
    while (i < 2 - j)
    {
      teamQBs.add(new PlayerQB(league.getRandName(), this));
      i += 1;
    }
    j = teamRBs.size();
    i = 0;
    while (i < 4 - j)
    {
      teamRBs.add(new PlayerRB(league.getRandName(), this));
      i += 1;
    }
    j = teamWRs.size();
    i = 0;
    while (i < 6 - j)
    {
      teamWRs.add(new PlayerWR(league.getRandName(), this));
      i += 1;
    }
    j = teamOLs.size();
    i = 0;
    while (i < 10 - j)
    {
      teamOLs.add(new PlayerOL(league.getRandName(), this));
      i += 1;
    }
    j = teamKs.size();
    i = 0;
    while (i < 2 - j)
    {
      teamKs.add(new PlayerK(league.getRandName(), this));
      i += 1;
    }
    j = teamSs.size();
    i = 0;
    while (i < 2 - j)
    {
      teamSs.add(new PlayerS(league.getRandName(), this));
      i += 1;
    }
    j = teamCBs.size();
    i = 0;
    while (i < 6 - j)
    {
      teamCBs.add(new PlayerCB(league.getRandName(), this));
      i += 1;
    }
    j = teamDLs.size();
    i = 0;
    while (i < 8 - j)
    {
      teamDLs.add(new PlayerDL(league.getRandName(), this));
      i += 1;
    }
    j = teamLBs.size();
    i = 0;
    while (i < 8 - j)
    {
      teamLBs.add(new PlayerLB(league.getRandName(), this));
      i += 1;
    }
    sortPlayers();
  }
  
  public void removePlayer(Player paramPlayer)
  {
    if ((paramPlayer instanceof PlayerQB))
    {
      teamQBs.remove(paramPlayer);
      return;
    }
    if ((paramPlayer instanceof PlayerRB))
    {
      teamRBs.remove(paramPlayer);
      return;
    }
    if ((paramPlayer instanceof PlayerWR))
    {
      teamWRs.remove(paramPlayer);
      return;
    }
    if ((paramPlayer instanceof PlayerOL))
    {
      teamOLs.remove(paramPlayer);
      return;
    }
    if ((paramPlayer instanceof PlayerK))
    {
      teamKs.remove(paramPlayer);
      return;
    }
    if ((paramPlayer instanceof PlayerS))
    {
      teamSs.remove(paramPlayer);
      return;
    }
    if ((paramPlayer instanceof PlayerCB))
    {
      teamCBs.remove(paramPlayer);
      return;
    }
    if ((paramPlayer instanceof PlayerDL))
    {
      teamDLs.remove(paramPlayer);
      return;
    }
    if ((paramPlayer instanceof PlayerLB))
    {
      teamLBs.remove(paramPlayer);
      return;
    }
    System.out.println(abbr + " couldn't find to remove " + paramPlayer.getPosNameYrOvrPot_OneLine());
  }
  
  public void resetStats()
  {
    gameSchedule = new ArrayList();
    regSeasonSchedule = new Game[16];
    gameWinsAgainst = new ArrayList();
    gameWLSchedule = new ArrayList();
    divChampion = "";
    natChampWL = "";
    teamPoints = 0;
    teamOppPoints = 0;
    teamYards = 0;
    teamOppYards = 0;
    teamPassYards = 0;
    teamRushYards = 0;
    teamOppPassYards = 0;
    teamOppRushYards = 0;
    teamTODiff = 0;
  }
  
  public String seasonSummaryStr()
  {
    String str = "Your team, " + name + ", finished the season with " + wins + " wins and " + losses + " losses.";
    if (natChampWL.equals("CBW")) {
      return str + "\n\nYou won the Champ Bowl! Congratulations! There are parades in the street celebrating your achievement.";
    }
    if (divChampion.equals("DW")) {
      return str + "\n\nEven though you didn't win the Champ Bowl, your team still won their division and your fanbase is pleased.";
    }
    if ((league.playoffsNA.contains(this)) || (league.playoffsAM.contains(this))) {
      return str + "\n\nEven though you didn't win the Champ Bowl, you still made the playoffs.";
    }
    return str + "\n\nYour team failed to make the playoffs this year, which is disappointing. Hopefully you can make the jump next year.";
  }
  
  public void setInitialStats()
  {
    signUDFAs();
    sortPlayers();
    totalWins = 0;
    totalLosses = 0;
    winStreak = new TeamStreak(league.getYear(), league.getYear(), 0, abbr);
    yearStartWinStreak = new TeamStreak(league.getYear(), league.getYear(), 0, abbr);
    totalDivChamps = 0;
    totalPlayoffWins = 0;
    totalPlayoffLosses = 0;
    totalSuperBowlLosses = 0;
    totalSuperBowlWins = 0;
    gameWinsAgainst = new ArrayList();
    gameWLSchedule = new ArrayList();
    divChampion = "";
    natChampWL = "";
    teamPoints = 0;
    teamOppPoints = 0;
    teamYards = 0;
    teamOppYards = 0;
    teamPassYards = 0;
    teamRushYards = 0;
    teamOppPassYards = 0;
    teamOppRushYards = 0;
    teamTODiff = 0;
    teamOffTalent = getOffTalent();
    teamDefTalent = getDefTalent();
    teamPollScore = (teamPrestige + getOffTalent() + getDefTalent());
    teamStratOff = new TeamStrategy();
    teamStratDef = new TeamStrategy();
    teamStratOffNum = 1;
    teamStratDefNum = 1;
    numRecruits = 30;
    playersRetiring = new ArrayList();
    playersFAs = new ArrayList();
    positionalDraftPicks = new ArrayList();
    draftPicks = new ArrayList();
    int i = 0;
    while (i < 2)
    {
      int j = 0;
      while (j < 7)
      {
        draftPicks.add(new DraftPick(i + 2016, j + 1, this, this));
        j += 1;
      }
      i += 1;
    }
  }
  
  public void setIsStarters(ArrayList<? extends Player> paramArrayList, int paramInt)
  {
    int i = 0;
    if (i < paramArrayList.size())
    {
      if (i < paramInt) {}
      for (getisStarter = true;; getisStarter = false)
      {
        i += 1;
        break;
      }
    }
  }
  
  public void setStarters(ArrayList<Player> paramArrayList, int paramInt)
  {
    switch (paramInt)
    {
    }
    for (;;)
    {
      league.setTeamRanks();
      return;
      Object localObject = new ArrayList();
      ((ArrayList)localObject).addAll(teamQBs);
      teamQBs.clear();
      paramArrayList = paramArrayList.iterator();
      Player localPlayer;
      while (paramArrayList.hasNext())
      {
        localPlayer = (Player)paramArrayList.next();
        teamQBs.add((PlayerQB)localPlayer);
      }
      Collections.sort(teamQBs, new PlayerComparator());
      paramArrayList = ((ArrayList)localObject).iterator();
      while (paramArrayList.hasNext())
      {
        localObject = (PlayerQB)paramArrayList.next();
        if (!teamQBs.contains(localObject)) {
          teamQBs.add(localObject);
        }
      }
      localObject = new ArrayList();
      ((ArrayList)localObject).addAll(teamRBs);
      teamRBs.clear();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        localPlayer = (Player)paramArrayList.next();
        teamRBs.add((PlayerRB)localPlayer);
      }
      Collections.sort(teamRBs, new PlayerComparator());
      paramArrayList = ((ArrayList)localObject).iterator();
      while (paramArrayList.hasNext())
      {
        localObject = (PlayerRB)paramArrayList.next();
        if (!teamRBs.contains(localObject)) {
          teamRBs.add(localObject);
        }
      }
      localObject = new ArrayList();
      ((ArrayList)localObject).addAll(teamWRs);
      teamWRs.clear();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        localPlayer = (Player)paramArrayList.next();
        teamWRs.add((PlayerWR)localPlayer);
      }
      Collections.sort(teamWRs, new PlayerComparator());
      paramArrayList = ((ArrayList)localObject).iterator();
      while (paramArrayList.hasNext())
      {
        localObject = (PlayerWR)paramArrayList.next();
        if (!teamWRs.contains(localObject)) {
          teamWRs.add(localObject);
        }
      }
      localObject = new ArrayList();
      ((ArrayList)localObject).addAll(teamOLs);
      teamOLs.clear();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        localPlayer = (Player)paramArrayList.next();
        teamOLs.add((PlayerOL)localPlayer);
      }
      Collections.sort(teamOLs, new PlayerComparator());
      paramArrayList = ((ArrayList)localObject).iterator();
      while (paramArrayList.hasNext())
      {
        localObject = (PlayerOL)paramArrayList.next();
        if (!teamOLs.contains(localObject)) {
          teamOLs.add(localObject);
        }
      }
      localObject = new ArrayList();
      ((ArrayList)localObject).addAll(teamKs);
      teamKs.clear();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        localPlayer = (Player)paramArrayList.next();
        teamKs.add((PlayerK)localPlayer);
      }
      Collections.sort(teamKs, new PlayerComparator());
      paramArrayList = ((ArrayList)localObject).iterator();
      while (paramArrayList.hasNext())
      {
        localObject = (PlayerK)paramArrayList.next();
        if (!teamKs.contains(localObject)) {
          teamKs.add(localObject);
        }
      }
      localObject = new ArrayList();
      ((ArrayList)localObject).addAll(teamSs);
      teamSs.clear();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        localPlayer = (Player)paramArrayList.next();
        teamSs.add((PlayerS)localPlayer);
      }
      Collections.sort(teamSs, new PlayerComparator());
      paramArrayList = ((ArrayList)localObject).iterator();
      while (paramArrayList.hasNext())
      {
        localObject = (PlayerS)paramArrayList.next();
        if (!teamSs.contains(localObject)) {
          teamSs.add(localObject);
        }
      }
      localObject = new ArrayList();
      ((ArrayList)localObject).addAll(teamCBs);
      teamCBs.clear();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        localPlayer = (Player)paramArrayList.next();
        teamCBs.add((PlayerCB)localPlayer);
      }
      Collections.sort(teamCBs, new PlayerComparator());
      paramArrayList = ((ArrayList)localObject).iterator();
      while (paramArrayList.hasNext())
      {
        localObject = (PlayerCB)paramArrayList.next();
        if (!teamCBs.contains(localObject)) {
          teamCBs.add(localObject);
        }
      }
      localObject = new ArrayList();
      ((ArrayList)localObject).addAll(teamDLs);
      teamDLs.clear();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        localPlayer = (Player)paramArrayList.next();
        teamDLs.add((PlayerDL)localPlayer);
      }
      Collections.sort(teamDLs, new PlayerComparator());
      paramArrayList = ((ArrayList)localObject).iterator();
      while (paramArrayList.hasNext())
      {
        localObject = (PlayerDL)paramArrayList.next();
        if (!teamDLs.contains(localObject)) {
          teamDLs.add(localObject);
        }
      }
      localObject = new ArrayList();
      ((ArrayList)localObject).addAll(teamLBs);
      teamLBs.clear();
      paramArrayList = paramArrayList.iterator();
      while (paramArrayList.hasNext())
      {
        localPlayer = (Player)paramArrayList.next();
        teamLBs.add((PlayerLB)localPlayer);
      }
      Collections.sort(teamLBs, new PlayerComparator());
      paramArrayList = ((ArrayList)localObject).iterator();
      while (paramArrayList.hasNext())
      {
        localObject = (PlayerLB)paramArrayList.next();
        if (!teamLBs.contains(localObject)) {
          teamLBs.add(localObject);
        }
      }
    }
  }
  
  public void setVarsFromCSV(String paramString)
  {
    boolean bool = false;
    paramString = paramString.split(",");
    if (paramString.length == 2)
    {
      name = paramString[0].trim();
      abbr = paramString[1].trim();
      return;
    }
    division = paramString[0];
    name = paramString[1];
    abbr = paramString[2];
    totalPlayoffWins = Integer.parseInt(paramString[3]);
    totalPlayoffLosses = Integer.parseInt(paramString[4]);
    totalSuperBowlWins = Integer.parseInt(paramString[5]);
    totalSuperBowlLosses = Integer.parseInt(paramString[6]);
    totalWins = Integer.parseInt(paramString[7]);
    totalLosses = Integer.parseInt(paramString[8]);
    teamStratOffNum = Integer.parseInt(paramString[9]);
    teamStratDefNum = Integer.parseInt(paramString[10]);
    if (Integer.parseInt(paramString[11]) == 1) {
      bool = true;
    }
    showPopups = bool;
    winStreak = new TeamStreak(Integer.parseInt(paramString[14]), Integer.parseInt(paramString[15]), Integer.parseInt(paramString[12]), paramString[13]);
    wins = Integer.parseInt(paramString[16]);
    losses = Integer.parseInt(paramString[17]);
    teamPoints = Integer.parseInt(paramString[18]);
    teamOppPoints = Integer.parseInt(paramString[19]);
    teamYards = Integer.parseInt(paramString[20]);
    teamOppYards = Integer.parseInt(paramString[21]);
    teamPassYards = Integer.parseInt(paramString[22]);
    teamRushYards = Integer.parseInt(paramString[23]);
    teamOppPassYards = Integer.parseInt(paramString[24]);
    teamOppRushYards = Integer.parseInt(paramString[25]);
    teamTODiff = Integer.parseInt(paramString[26]);
    divChampion = paramString[27].substring(1);
    natChampWL = paramString[28].substring(1);
    totalDivChamps = Integer.parseInt(paramString[29]);
  }
  
  public void signUDFAs()
  {
    int j = getNeedsPosition("QB");
    int i = 0;
    Object localObject;
    while (i < j)
    {
      localObject = new PlayerQB(league.getRandName(), this);
      ((Player)localObject).setRatingsUDFA();
      ((Player)localObject).setContract(new Contract((Player)localObject, 1, 0.5D));
      addPlayer((Player)localObject);
      i += 1;
    }
    j = getNeedsPosition("RB");
    i = 0;
    while (i < j)
    {
      localObject = new PlayerRB(league.getRandName(), this);
      ((Player)localObject).setRatingsUDFA();
      ((Player)localObject).setContract(new Contract((Player)localObject, 1, 0.5D));
      addPlayer((Player)localObject);
      i += 1;
    }
    j = getNeedsPosition("WR");
    i = 0;
    while (i < j)
    {
      localObject = new PlayerWR(league.getRandName(), this);
      ((Player)localObject).setRatingsUDFA();
      ((Player)localObject).setContract(new Contract((Player)localObject, 1, 0.5D));
      addPlayer((Player)localObject);
      i += 1;
    }
    j = getNeedsPosition("OL");
    i = 0;
    while (i < j)
    {
      localObject = new PlayerOL(league.getRandName(), this);
      ((Player)localObject).setRatingsUDFA();
      ((Player)localObject).setContract(new Contract((Player)localObject, 1, 0.5D));
      addPlayer((Player)localObject);
      i += 1;
    }
    j = getNeedsPosition("K");
    i = 0;
    while (i < j)
    {
      localObject = new PlayerK(league.getRandName(), this);
      ((Player)localObject).setRatingsUDFA();
      ((Player)localObject).setContract(new Contract((Player)localObject, 1, 0.5D));
      addPlayer((Player)localObject);
      i += 1;
    }
    j = getNeedsPosition("S");
    i = 0;
    while (i < j)
    {
      localObject = new PlayerS(league.getRandName(), this);
      ((Player)localObject).setRatingsUDFA();
      ((Player)localObject).setContract(new Contract((Player)localObject, 1, 0.5D));
      addPlayer((Player)localObject);
      i += 1;
    }
    j = getNeedsPosition("CB");
    i = 0;
    while (i < j)
    {
      localObject = new PlayerCB(league.getRandName(), this);
      ((Player)localObject).setRatingsUDFA();
      ((Player)localObject).setContract(new Contract((Player)localObject, 1, 0.5D));
      addPlayer((Player)localObject);
      i += 1;
    }
    j = getNeedsPosition("DL");
    i = 0;
    while (i < j)
    {
      localObject = new PlayerDL(league.getRandName(), this);
      ((Player)localObject).setRatingsUDFA();
      ((Player)localObject).setContract(new Contract((Player)localObject, 1, 0.5D));
      addPlayer((Player)localObject);
      i += 1;
    }
    j = getNeedsPosition("LB");
    i = 0;
    while (i < j)
    {
      localObject = new PlayerLB(league.getRandName(), this);
      ((Player)localObject).setRatingsUDFA();
      ((Player)localObject).setContract(new Contract((Player)localObject, 1, 0.5D));
      addPlayer((Player)localObject);
      i += 1;
    }
  }
  
  public void sortPlayers()
  {
    Collections.sort(teamQBs, new PlayerComparator());
    setIsStarters(teamQBs, 1);
    Collections.sort(teamRBs, new PlayerComparator());
    setIsStarters(teamRBs, 2);
    Collections.sort(teamWRs, new PlayerComparator());
    setIsStarters(teamWRs, 3);
    Collections.sort(teamKs, new PlayerComparator());
    setIsStarters(teamKs, 1);
    Collections.sort(teamOLs, new PlayerComparator());
    setIsStarters(teamOLs, 5);
    Collections.sort(teamCBs, new PlayerComparator());
    setIsStarters(teamCBs, 3);
    Collections.sort(teamSs, new PlayerComparator());
    setIsStarters(teamSs, 1);
    Collections.sort(teamDLs, new PlayerComparator());
    setIsStarters(teamDLs, 4);
    Collections.sort(teamLBs, new PlayerComparator());
    setIsStarters(teamLBs, 3);
  }
  
  public String strRep()
  {
    return "#" + rankTeamPollScore + " " + abbr + " (" + wins + "-" + losses + ")";
  }
  
  public String strRepWithBowlResults()
  {
    return "#" + rankTeamPollScore + " " + abbr + " (" + wins + "-" + losses + ") " + divChampion + " " + natChampWL;
  }
  
  public void updatePollScore()
  {
    updateStrengthOfWins();
    int j = 8 - (wins + losses);
    int i = j;
    if (j < 0) {
      i = 0;
    }
    teamPollScore = ((wins * 200 + (teamPoints - teamOppPoints) * 3 + (teamYards - teamOppYards) / 40 + i * 3 * (teamPrestige + getOffTalent() + getDefTalent()) + teamStrengthOfWins) / 10);
    if ("DW".equals(divChampion)) {
      teamPollScore += 50;
    }
    if ("CBW".equals(natChampWL)) {
      teamPollScore += 1000;
    }
    if (losses == 0) {
      teamPollScore += 30;
    }
    for (;;)
    {
      teamOffTalent = getOffTalent();
      teamDefTalent = getDefTalent();
      return;
      if (losses == 1) {
        teamPollScore += 15;
      }
    }
  }
  
  public void updateStrengthOfWins()
  {
    int i = 0;
    int j = 0;
    if (j < gameSchedule.size())
    {
      Game localGame = (Game)gameSchedule.get(j);
      if (localGame.getWinner() == this) {
        i += getLoserwins * 2;
      }
      for (;;)
      {
        j += 1;
        break;
        i += getWinnerwins - 1;
      }
    }
    teamStrengthOfWins = i;
  }
  
  public void updateTalentRatings()
  {
    teamOffTalent = getOffTalent();
    teamDefTalent = getDefTalent();
    teamPollScore = (teamPrestige + getOffTalent() + getDefTalent());
  }
  
  public void updateTeamHistory()
  {
    String str = league.getYear() + ": #" + rankTeamPollScore + " " + abbr + " (" + wins + "-" + losses + ") " + divChampion + " " + natChampWL;
    if (gameSchedule.size() > 16)
    {
      int i = 16;
      for (;;)
      {
        localObject = str;
        if (i >= gameSchedule.size()) {
          break;
        }
        localObject = (Game)gameSchedule.get(i);
        str = str + ">" + gameName + ": ";
        localObject = getGameSummaryStr(i);
        str = str + localObject[1] + " " + localObject[2];
        i += 1;
      }
    }
    Object localObject = str + ">Missed Playoffs";
    teamHistory.add(localObject);
  }
  
  public String weekSummaryStr()
  {
    int i = wins + losses - 1;
    Object localObject = (Game)gameSchedule.get(i);
    localObject = (String)gameWLSchedule.get(i) + " " + gameSummaryStr((Game)localObject);
    return name + " " + (String)localObject + "\nNew power rank: #" + rankTeamPollScore + " " + abbr + " (" + wins + "-" + losses + ")";
  }
}

/* Location:
 * Qualified Name:     PFCpack.Team
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */