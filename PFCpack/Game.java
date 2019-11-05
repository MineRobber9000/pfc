package PFCpack;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public class Game
{
  public int[] AwayKStats;
  public int[] AwayQBStats;
  public int[] AwayRB1Stats;
  public int[] AwayRB2Stats;
  public int[] AwayWR1Stats;
  public int[] AwayWR2Stats;
  public int[] AwayWR3Stats;
  public int[] HomeKStats;
  public int[] HomeQBStats;
  public int[] HomeRB1Stats;
  public int[] HomeRB2Stats;
  public int[] HomeWR1Stats;
  public int[] HomeWR2Stats;
  public int[] HomeWR3Stats;
  public boolean affectsELO;
  public int awayELO;
  private PlayerK awayK;
  private PlayerQB awayQB;
  public int[] awayQScore;
  private PlayerRB[] awayRBs;
  public int awayScore;
  public int awayTOs;
  public Team awayTeam;
  private PlayerWR[] awayWRs;
  public int awayYards;
  private boolean bottomOT;
  private int gameDown;
  String gameEventLog;
  public String gameName;
  private boolean gamePoss;
  private int gameTime;
  private int gameYardLine;
  private int gameYardsNeed;
  public boolean hasData;
  public boolean hasPlayed;
  public int homeELO;
  private PlayerK homeK;
  private PlayerQB homeQB;
  public int[] homeQScore;
  private PlayerRB[] homeRBs;
  public int homeScore;
  public int homeTOs;
  public Team homeTeam;
  private PlayerWR[] homeWRs;
  public int homeYards;
  public boolean isOnline;
  public int numOT;
  private boolean playingOT;
  String tdInfo;
  public Team winner;
  
  public Game(League paramLeague, String paramString, int paramInt)
  {
    paramString = paramString.split(",");
    homeTeam = paramLeague.findTeamAbbr(paramString[0]);
    awayTeam = paramLeague.findTeamAbbr(paramString[1]);
    if (paramInt < 16)
    {
      homeTeam.regSeasonSchedule[paramInt] = this;
      awayTeam.regSeasonSchedule[paramInt] = this;
    }
    homeScore = Integer.parseInt(paramString[2]);
    awayScore = Integer.parseInt(paramString[3]);
    if ((homeScore == 0) && (awayScore == 0))
    {
      hasData = true;
      hasPlayed = false;
    }
    for (;;)
    {
      gameName = paramString[4];
      homeQScore = new int[10];
      awayQScore = new int[10];
      numOT = 0;
      homeTOs = 0;
      awayTOs = 0;
      HomeQBStats = new int[6];
      AwayQBStats = new int[6];
      HomeRB1Stats = new int[4];
      HomeRB2Stats = new int[4];
      AwayRB1Stats = new int[4];
      AwayRB2Stats = new int[4];
      HomeWR1Stats = new int[6];
      HomeWR2Stats = new int[6];
      HomeWR3Stats = new int[6];
      AwayWR1Stats = new int[6];
      AwayWR2Stats = new int[6];
      AwayWR3Stats = new int[6];
      HomeKStats = new int[6];
      AwayKStats = new int[6];
      affectsELO = false;
      isOnline = false;
      return;
      hasData = false;
      hasPlayed = true;
      if (homeScore > awayScore) {
        winner = homeTeam;
      } else {
        winner = awayTeam;
      }
    }
  }
  
  public Game(Team paramTeam1, Team paramTeam2, String paramString, int paramInt)
  {
    if (paramInt < 16)
    {
      regSeasonSchedule[paramInt] = this;
      regSeasonSchedule[paramInt] = this;
      homeTeam = paramTeam1;
      awayTeam = paramTeam2;
    }
    for (;;)
    {
      gameName = paramString;
      homeScore = 0;
      homeQScore = new int[10];
      awayScore = 0;
      awayQScore = new int[10];
      numOT = 0;
      homeTOs = 0;
      awayTOs = 0;
      HomeQBStats = new int[6];
      AwayQBStats = new int[6];
      HomeRB1Stats = new int[4];
      HomeRB2Stats = new int[4];
      AwayRB1Stats = new int[4];
      AwayRB2Stats = new int[4];
      HomeWR1Stats = new int[6];
      HomeWR2Stats = new int[6];
      HomeWR3Stats = new int[6];
      AwayWR1Stats = new int[6];
      AwayWR2Stats = new int[6];
      AwayWR3Stats = new int[6];
      HomeKStats = new int[6];
      AwayKStats = new int[6];
      hasPlayed = false;
      hasData = true;
      affectsELO = false;
      isOnline = false;
      return;
      if (wins < wins)
      {
        homeTeam = paramTeam2;
        awayTeam = paramTeam1;
      }
      else
      {
        homeTeam = paramTeam1;
        awayTeam = paramTeam2;
      }
    }
  }
  
  private void addPointsQuarter(int paramInt)
  {
    int i;
    if (gamePoss)
    {
      if (gameTime > 2700)
      {
        arrayOfInt = homeQScore;
        arrayOfInt[0] += paramInt;
        return;
      }
      if (gameTime > 1800)
      {
        arrayOfInt = homeQScore;
        arrayOfInt[1] += paramInt;
        return;
      }
      if (gameTime > 900)
      {
        arrayOfInt = homeQScore;
        arrayOfInt[2] += paramInt;
        return;
      }
      if (numOT == 0)
      {
        arrayOfInt = homeQScore;
        arrayOfInt[3] += paramInt;
        return;
      }
      if (numOT + 3 < 10)
      {
        arrayOfInt = homeQScore;
        i = numOT + 3;
        arrayOfInt[i] += paramInt;
        return;
      }
      arrayOfInt = homeQScore;
      arrayOfInt[9] += paramInt;
      return;
    }
    if (gameTime > 2700)
    {
      arrayOfInt = awayQScore;
      arrayOfInt[0] += paramInt;
      return;
    }
    if (gameTime > 1800)
    {
      arrayOfInt = awayQScore;
      arrayOfInt[1] += paramInt;
      return;
    }
    if (gameTime > 900)
    {
      arrayOfInt = awayQScore;
      arrayOfInt[2] += paramInt;
      return;
    }
    if (numOT == 0)
    {
      arrayOfInt = awayQScore;
      arrayOfInt[3] += paramInt;
      return;
    }
    if (numOT + 3 < 10)
    {
      arrayOfInt = awayQScore;
      i = numOT + 3;
      arrayOfInt[i] += paramInt;
      return;
    }
    int[] arrayOfInt = awayQScore;
    arrayOfInt[9] += paramInt;
  }
  
  private String convGameTime()
  {
    if (!playingOT)
    {
      int i = (3600 - gameTime) / 900 + 1;
      if ((i >= 4) && (numOT > 0))
      {
        i = gameTime / 60;
        j = gameTime - i * 60;
        if (j < 10) {}
        for (str = "0" + j;; str = "" + j)
        {
          if (j <= 0) {
            str = "00";
          }
          return i + ":" + str + " OT" + numOT;
        }
      }
      if ((gameTime <= 0) && (numOT <= 0)) {
        return "0:00 Q4";
      }
      int j = (gameTime - (4 - i) * 900) / 60;
      int k = gameTime - (4 - i) * 900 - j * 60;
      if (k < 10) {}
      for (String str = "0" + k;; str = "" + k) {
        return j + ":" + str + " Q" + i;
      }
    }
    if (!bottomOT) {
      return "TOP OT" + numOT;
    }
    return "BOT OT" + numOT;
  }
  
  private void fieldGoalAtt(Team paramTeam1, Team paramTeam2)
  {
    double d1 = Math.pow((110 - gameYardLine) / 50, 2.0D);
    double d2 = Math.pow((110 - gameYardLine) / 50, 1.25D);
    double d3 = getHFadv() + getK0ratKickPow;
    double d4 = getHFadv() + getK0ratKickAcc;
    if ((d3 - 80.0D * d1 > 20.0D) && (Math.random() * (d4 - 80.0D * d2) > 15.0D)) {
      if (gamePoss)
      {
        homeScore += 3;
        paramTeam2 = HomeKStats;
        paramTeam2[3] += 1;
        paramTeam2 = HomeKStats;
        paramTeam2[2] += 1;
        gameEventLog = (gameEventLog + getEventPrefix() + abbr + " K " + getK0name + " made the " + (110 - gameYardLine) + " yard FG.");
        addPointsQuarter(3);
        paramTeam2 = paramTeam1.getK(0);
        statsFGMade += 1;
        paramTeam2 = paramTeam1.getK(0);
        statsFGAtt += 1;
        if (playingOT) {
          break label319;
        }
        kickOff(paramTeam1);
      }
    }
    for (;;)
    {
      gameTime -= 20;
      return;
      awayScore += 3;
      paramTeam2 = AwayKStats;
      paramTeam2[3] += 1;
      paramTeam2 = AwayKStats;
      paramTeam2[2] += 1;
      break;
      label319:
      resetForOT();
      continue;
      gameEventLog = (gameEventLog + getEventPrefix() + abbr + " K " + getK0name + " missed the " + (110 - gameYardLine) + " yard FG.");
      paramTeam1 = paramTeam1.getK(0);
      statsFGAtt += 1;
      if (!playingOT)
      {
        gameYardLine = Math.max(100 - gameYardLine, 20);
        gameDown = 1;
        gameYardsNeed = 10;
        if (gamePoss)
        {
          paramTeam1 = HomeKStats;
          paramTeam1[3] += 1;
          label469:
          if (gamePoss) {
            break label504;
          }
        }
        label504:
        for (boolean bool = true;; bool = false)
        {
          gamePoss = bool;
          break;
          paramTeam1 = AwayKStats;
          paramTeam1[3] += 1;
          break label469;
        }
      }
      resetForOT();
    }
  }
  
  private void freeKick(Team paramTeam)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (gameTime <= 0) {
      return;
    }
    if ((gameTime < 180) && (((gamePoss) && (awayScore - homeScore <= 8) && (awayScore - homeScore > 0)) || ((!gamePoss) && (homeScore - awayScore <= 8) && (homeScore - awayScore > 0))))
    {
      if ((getK0ratKickFum * Math.random() > 60.0D) || (Math.random() < 0.1D))
      {
        gameEventLog = (gameEventLog + getEventPrefix() + abbr + " K " + getK0name + " successfully executes onside kick! " + abbr + " has possession!");
        gameYardLine = 35;
        gameDown = 1;
      }
      for (gameYardsNeed = 10;; gameYardsNeed = 10)
      {
        gameTime = ((int)(gameTime - (Math.random() * 4.0D + 4.0D)));
        return;
        gameEventLog = (gameEventLog + getEventPrefix() + abbr + " K " + getK0name + " failed the onside kick and lost possession.");
        bool1 = bool2;
        if (!gamePoss) {
          bool1 = true;
        }
        gamePoss = bool1;
        gameYardLine = 65;
        gameDown = 1;
      }
    }
    gameYardLine = ((int)(115.0D - (getK0ratKickPow + 20 - 40.0D * Math.random())));
    if (gameYardLine <= 0) {
      gameYardLine = 25;
    }
    gameDown = 1;
    gameYardsNeed = 10;
    if (!gamePoss) {}
    for (;;)
    {
      gamePoss = bool1;
      gameTime = ((int)(gameTime - 15.0D * Math.random()));
      return;
      bool1 = false;
    }
  }
  
  private String getEventPrefix()
  {
    String str1;
    String str2;
    if (gamePoss)
    {
      str1 = homeTeam.abbr;
      str2 = "" + gameYardsNeed;
      if (gameYardLine + gameYardsNeed >= 100) {
        str2 = "Goal";
      }
      if (gameDown <= 4) {
        break label208;
      }
    }
    label208:
    for (int i = 4;; i = gameDown)
    {
      return "\n\n" + homeTeam.abbr + " " + homeScore + " - " + awayScore + " " + awayTeam.abbr + ", Time: " + convGameTime() + "\n\t" + str1 + " " + i + " and " + str2 + " at " + gameYardLine + " yard line.\n";
      str1 = awayTeam.abbr;
      break;
    }
  }
  
  private int getHFadv()
  {
    int j = (homeTeam.getCompositeFootIQ() - awayTeam.getCompositeFootIQ()) / 5;
    int i = j;
    if (j > 3) {
      i = 3;
    }
    j = i;
    if (i < -3) {
      j = -3;
    }
    if (gamePoss) {
      return j + 3;
    }
    return -j;
  }
  
  private void kickOff(Team paramTeam)
  {
    boolean bool1 = true;
    boolean bool2 = false;
    if (gameTime <= 0) {
      return;
    }
    if ((gameTime < 180) && (((gamePoss) && (awayScore - homeScore <= 8) && (awayScore - homeScore > 0)) || ((!gamePoss) && (homeScore - awayScore <= 8) && (homeScore - awayScore > 0))))
    {
      if ((getK0ratKickFum * Math.random() > 60.0D) || (Math.random() < 0.1D)) {
        gameEventLog = (gameEventLog + getEventPrefix() + abbr + " K " + getK0name + " successfully executes onside kick! " + abbr + " has possession!");
      }
      for (;;)
      {
        gameYardLine = 50;
        gameDown = 1;
        gameYardsNeed = 10;
        gameTime = ((int)(gameTime - (4.0D + 5.0D * Math.random())));
        gameTime = ((int)(gameTime - 15.0D * Math.random()));
        return;
        gameEventLog = (gameEventLog + getEventPrefix() + abbr + " K " + getK0name + " failed the onside kick and lost possession.");
        bool1 = bool2;
        if (!gamePoss) {
          bool1 = true;
        }
        gamePoss = bool1;
      }
    }
    gameYardLine = ((int)(100.0D - (getK0ratKickPow + 20 - 40.0D * Math.random())));
    if (gameYardLine <= 0) {
      gameYardLine = 25;
    }
    gameDown = 1;
    gameYardsNeed = 10;
    if (!gamePoss) {}
    for (;;)
    {
      gamePoss = bool1;
      break;
      bool1 = false;
    }
  }
  
  private void kickXP(Team paramTeam1, Team paramTeam2)
  {
    if ((playingOT) && (bottomOT) && (((numOT % 2 == 0) && (awayScore > homeScore)) || ((numOT % 2 != 0) && (homeScore > awayScore))))
    {
      gameEventLog = (gameEventLog + getEventPrefix() + " " + tdInfo + "\n" + abbr + " wins on a walk-off touchdown!");
      return;
    }
    if ((!playingOT) && (gameTime <= 0) && ((homeScore - awayScore > 2) || (awayScore - homeScore > 2)))
    {
      if ((Math.abs(homeScore - awayScore) < 7) && (((gamePoss) && (homeScore > awayScore)) || ((!gamePoss) && (awayScore > homeScore))))
      {
        gameEventLog = (gameEventLog + getEventPrefix() + " " + tdInfo + "\n" + abbr + " wins on a walk-off touchdown!");
        return;
      }
      gameEventLog = (gameEventLog + getEventPrefix() + " " + tdInfo);
      return;
    }
    if ((numOT >= 3) || (((gamePoss) && (awayScore - homeScore == 2)) || ((!gamePoss) && (homeScore - awayScore == 2) && (gameTime < 300))))
    {
      if (Math.random() <= 0.5D)
      {
        i = paramTeam1.getCompositeOLRush();
        j = paramTeam2.getCompositeF7Rush();
        if ((int)((getRB0ratRushSpd + (i - j)) * Math.random() / 6.0D) > 5)
        {
          if (gamePoss) {
            homeScore += 2;
          }
          for (;;)
          {
            addPointsQuarter(2);
            gameEventLog = (gameEventLog + getEventPrefix() + " " + tdInfo + " " + getRB0name + " rushed for the 2pt conversion.");
            return;
            awayScore += 2;
          }
        }
        gameEventLog = (gameEventLog + getEventPrefix() + " " + tdInfo + " " + getRB0name + " stopped at the line of scrimmage, failed the 2pt conversion.");
        return;
      }
      int i = paramTeam2.getCompositeF7Pass();
      int j = paramTeam1.getCompositeOLPass();
      double d = (normalize(getQB0ratPassAcc) + getWR0ratRecCat - getCB0ratCBCov) / 2 + 25 - (i * 2 - j) / 20;
      if (100.0D * Math.random() < d)
      {
        if (gamePoss) {
          homeScore += 2;
        }
        for (;;)
        {
          addPointsQuarter(2);
          gameEventLog = (gameEventLog + getEventPrefix() + " " + tdInfo + " " + getQB0name + " completed the pass to " + getWR0name + " for the 2pt conversion.");
          return;
          awayScore += 2;
        }
      }
      gameEventLog = (gameEventLog + getEventPrefix() + " " + tdInfo + " " + getQB0name + "'s pass incomplete to " + getWR0name + " for the failed 2pt conversion.");
      return;
    }
    if ((Math.random() * 100.0D < (getK0ratKickAcc + 100) / 2) && (Math.random() > 0.02D)) {
      if (gamePoss)
      {
        homeScore += 1;
        paramTeam2 = HomeKStats;
        paramTeam2[0] += 1;
        paramTeam2 = HomeKStats;
        paramTeam2[1] += 1;
        gameEventLog = (gameEventLog + getEventPrefix() + " " + tdInfo + " " + getK0name + " made the XP.");
        addPointsQuarter(1);
        paramTeam2 = paramTeam1.getK(0);
        statsXPMade += 1;
      }
    }
    for (;;)
    {
      paramTeam1 = paramTeam1.getK(0);
      statsXPAtt += 1;
      return;
      awayScore += 1;
      paramTeam2 = AwayKStats;
      paramTeam2[0] += 1;
      paramTeam2 = AwayKStats;
      paramTeam2[1] += 1;
      break;
      gameEventLog = (gameEventLog + getEventPrefix() + " " + tdInfo + " " + getK0name + " missed the XP.");
      if (gamePoss)
      {
        paramTeam2 = HomeKStats;
        paramTeam2[1] += 1;
      }
      else
      {
        paramTeam2 = AwayKStats;
        paramTeam2[1] += 1;
      }
    }
  }
  
  private int normalize(int paramInt)
  {
    return (paramInt + 100) / 2;
  }
  
  private void passAttempt(Team paramTeam, PlayerWR paramPlayerWR, int[] paramArrayOfInt, int paramInt)
  {
    paramTeam = paramTeam.getQB(0);
    statsPassAtt += 1;
    statsTargets += 1;
    if (gamePoss)
    {
      homeYards += paramInt;
      paramTeam = HomeQBStats;
      paramTeam[4] += paramInt;
      paramTeam = HomeQBStats;
      paramTeam[1] += 1;
      paramArrayOfInt[2] += paramInt;
      paramArrayOfInt[1] += 1;
      return;
    }
    awayYards += paramInt;
    paramTeam = AwayQBStats;
    paramTeam[4] += paramInt;
    paramTeam = AwayQBStats;
    paramTeam[1] += 1;
    paramArrayOfInt[2] += paramInt;
    paramArrayOfInt[1] += 1;
  }
  
  private void passCompletion(Team paramTeam1, Team paramTeam2, PlayerWR paramPlayerWR, int[] paramArrayOfInt, int paramInt)
  {
    paramTeam2 = paramTeam1.getQB(0);
    statsPassComp += 1;
    paramTeam2 = paramTeam1.getQB(0);
    statsPassYards += paramInt;
    statsReceptions += 1;
    statsRecYards += paramInt;
    teamPassYards += paramInt;
    if (gamePoss)
    {
      paramTeam1 = HomeQBStats;
      paramTeam1[0] += 1;
      paramArrayOfInt[0] += 1;
      return;
    }
    paramTeam1 = AwayQBStats;
    paramTeam1[0] += 1;
    paramArrayOfInt[0] += 1;
  }
  
  private void passingPlay(Team paramTeam1, Team paramTeam2)
  {
    int n = 0;
    int m = 0;
    double d1 = Math.pow(getWR0ratOvr, 1.0D) * Math.random();
    double d2 = Math.pow(getWR1ratOvr, 1.0D) * Math.random();
    double d3 = Math.pow(getWR2ratOvr, 1.0D) * Math.random();
    PlayerWR localPlayerWR;
    PlayerCB localPlayerCB;
    int[] arrayOfInt;
    if ((d1 > d2) && (d1 > d3))
    {
      localPlayerWR = paramTeam1.getWR(0);
      localPlayerCB = paramTeam2.getCB(0);
      if (gamePoss) {
        arrayOfInt = HomeWR1Stats;
      }
    }
    int i;
    for (;;)
    {
      i = paramTeam2.getCompositeF7Pass() * 2 - paramTeam1.getCompositeOLPass() - getHFadv();
      if (Math.random() * 100.0D >= i / 8) {
        break;
      }
      qbSack(paramTeam1);
      return;
      arrayOfInt = AwayWR1Stats;
      continue;
      if ((d2 > d1) && (d2 > d3))
      {
        localPlayerWR = paramTeam1.getWR(1);
        localPlayerCB = paramTeam2.getCB(1);
        if (gamePoss) {
          arrayOfInt = HomeWR2Stats;
        } else {
          arrayOfInt = AwayWR2Stats;
        }
      }
      else
      {
        localPlayerWR = paramTeam1.getWR(2);
        localPlayerCB = paramTeam2.getCB(2);
        if (gamePoss) {
          arrayOfInt = HomeWR3Stats;
        } else {
          arrayOfInt = AwayWR3Stats;
        }
      }
    }
    d2 = (getS0ratOvr + i - (getQB0ratPassAcc + getQB0ratFootIQ + 100) / 3) / 22 + teamStratOff.getPAB() + teamStratDef.getPAB();
    d1 = d2;
    if (d2 < 0.01D) {
      d1 = 0.01D;
    }
    if (100.0D * Math.random() < d1)
    {
      qbInterception(paramTeam1);
      return;
    }
    d1 = (getHFadv() + normalize(getQB0ratPassAcc) + normalize(ratRecCat) - normalize(ratCBCov)) / 1.9D;
    d2 = i / 16;
    d3 = teamStratOff.getPAB();
    double d4 = teamStratDef.getPAB();
    int j;
    int k;
    if (100.0D * Math.random() < d1 + 20.0D - d2 - d3 - d4)
    {
      if ((100.0D * Math.random() > (ratRecCat + 185) / 3) || (Math.random() < 0.02D))
      {
        gameDown += 1;
        arrayOfInt[4] += 1;
        statsDrops += 1;
        passAttempt(paramTeam1, localPlayerWR, arrayOfInt, 0);
        gameTime = ((int)(gameTime - 15.0D * Math.random()));
        return;
      }
      i = (int)((normalize(getQB0ratPassPow) + normalize(ratRecSpd) - normalize(ratCBSpd)) * Math.random() / 3.7D + teamStratOff.getPYB() / 2 - teamStratDef.getPYB());
      d1 = (normalize(ratRecEva) * 3 - ratCBTkl - getS0ratOvr) * Math.random() + teamStratOff.getPYB() - teamStratDef.getPAB();
      if (d1 <= 92.0D)
      {
        j = i;
        if (Math.random() <= 0.95D) {}
      }
      else
      {
        j = (int)(i + (3.0D + ratRecSpd * Math.random() / 3.0D));
      }
      i = j;
      if (d1 > 75.0D)
      {
        i = j;
        if (Math.random() < 0.1D + (teamStratOff.getPAB() - teamStratDef.getPAB()) / 200) {
          i = j + 100;
        }
      }
      gameYardLine += i;
      if (gameYardLine >= 100)
      {
        j = i - (gameYardLine - 100);
        gameYardLine = (100 - j);
        addPointsQuarter(6);
        passingTD(paramTeam1, localPlayerWR, arrayOfInt, j);
        k = 1;
        if ((k == 0) && (m == 0))
        {
          gameYardsNeed -= j;
          if (gameYardsNeed > 0) {
            break label1089;
          }
          gameDown = 1;
          gameYardsNeed = 10;
        }
        label841:
        passCompletion(paramTeam1, paramTeam2, localPlayerWR, arrayOfInt, j);
        passAttempt(paramTeam1, localPlayerWR, arrayOfInt, j);
        if (m == 0) {
          break label1165;
        }
        gameEventLog = (gameEventLog + getEventPrefix() + "TURNOVER!\n" + abbr + " WR " + name + " fumbled the ball after a catch.");
        arrayOfInt[5] += 1;
        statsFumbles += 1;
        if (!gamePoss) {
          break label1141;
        }
        homeTOs += 1;
        label969:
        if (playingOT) {
          break label1160;
        }
        gameDown = 1;
        gameYardsNeed = 10;
        if (gamePoss) {
          break label1154;
        }
      }
    }
    label1089:
    label1141:
    label1154:
    for (boolean bool = true;; bool = false)
    {
      gamePoss = bool;
      gameYardLine = (100 - gameYardLine);
      gameTime = ((int)(gameTime - 15.0D * Math.random()));
      return;
      d1 = (getS0ratSTkl + ratCBTkl) / 2;
      j = i;
      k = n;
      if (100.0D * Math.random() >= d1 / 50.0D) {
        break;
      }
      m = 1;
      j = i;
      k = n;
      break;
      gameDown += 1;
      break label841;
      passAttempt(paramTeam1, localPlayerWR, arrayOfInt, 0);
      gameDown += 1;
      gameTime = ((int)(gameTime - 15.0D * Math.random()));
      return;
      awayTOs += 1;
      break label969;
    }
    label1160:
    resetForOT();
    return;
    label1165:
    if (k != 0)
    {
      gameTime = ((int)(gameTime - 15.0D * Math.random()));
      kickXP(paramTeam1, paramTeam2);
      if (!playingOT)
      {
        kickOff(paramTeam1);
        return;
      }
      resetForOT();
      return;
    }
    gameTime = ((int)(gameTime - (15.0D + 15.0D * Math.random())));
  }
  
  private void passingTD(Team paramTeam, PlayerWR paramPlayerWR, int[] paramArrayOfInt, int paramInt)
  {
    int[] arrayOfInt;
    if (gamePoss)
    {
      homeScore += 6;
      arrayOfInt = HomeQBStats;
      arrayOfInt[2] += 1;
      paramArrayOfInt[3] += 1;
    }
    for (;;)
    {
      tdInfo = (abbr + " QB " + getQB0name + " threw a " + paramInt + " yard TD to " + name + ".");
      paramTeam = paramTeam.getQB(0);
      statsTD += 1;
      statsTD += 1;
      return;
      awayScore += 6;
      arrayOfInt = AwayQBStats;
      arrayOfInt[2] += 1;
      paramArrayOfInt[3] += 1;
    }
  }
  
  private void puntPlay(Team paramTeam)
  {
    boolean bool = true;
    gameYardLine = ((int)(100.0D - (gameYardLine + getK0ratKickPow / 3 + 20 - 10.0D * Math.random())));
    if (gameYardLine < 0) {
      gameYardLine = 20;
    }
    gameDown = 1;
    gameYardsNeed = 10;
    if (!gamePoss) {}
    for (;;)
    {
      gamePoss = bool;
      gameTime = ((int)(gameTime - (20.0D + 15.0D * Math.random())));
      return;
      bool = false;
    }
  }
  
  private void qbInterception(Team paramTeam)
  {
    boolean bool = true;
    int[] arrayOfInt;
    if (gamePoss)
    {
      arrayOfInt = HomeQBStats;
      arrayOfInt[3] += 1;
      arrayOfInt = HomeQBStats;
      arrayOfInt[1] += 1;
      homeTOs += 1;
      gameEventLog = (gameEventLog + getEventPrefix() + "TURNOVER!\n" + abbr + " QB " + getQB0name + " was intercepted.");
      gameTime = ((int)(gameTime - 15.0D * Math.random()));
      paramTeam = paramTeam.getQB(0);
      statsInt += 1;
      if (playingOT) {
        break label229;
      }
      gameDown = 1;
      gameYardsNeed = 10;
      if (gamePoss) {
        break label224;
      }
    }
    for (;;)
    {
      gamePoss = bool;
      gameYardLine = (100 - gameYardLine);
      return;
      arrayOfInt = AwayQBStats;
      arrayOfInt[3] += 1;
      arrayOfInt = AwayQBStats;
      arrayOfInt[1] += 1;
      awayTOs += 1;
      break;
      label224:
      bool = false;
    }
    label229:
    resetForOT();
  }
  
  private void qbSack(Team paramTeam)
  {
    paramTeam = paramTeam.getQB(0);
    statsSacked += 1;
    gameYardsNeed += 3;
    gameYardLine -= 3;
    gameDown += 1;
    if (gamePoss)
    {
      paramTeam = HomeQBStats;
      paramTeam[5] += 1;
    }
    for (;;)
    {
      if (gameYardLine < 0)
      {
        gameTime = ((int)(gameTime - Math.random() * 10.0D));
        safety();
      }
      gameTime = ((int)(gameTime - (25.0D + Math.random() * 10.0D)));
      return;
      paramTeam = AwayQBStats;
      paramTeam[5] += 1;
    }
  }
  
  private void resetForOT()
  {
    boolean bool = false;
    if ((bottomOT) && (homeScore == awayScore))
    {
      gameYardLine = 75;
      gameYardsNeed = 10;
      gameDown = 1;
      numOT += 1;
      if (numOT % 2 == 0) {}
      for (gamePoss = true;; gamePoss = false)
      {
        gameTime = -1;
        bottomOT = false;
        return;
      }
    }
    if (!bottomOT)
    {
      if (!gamePoss) {
        bool = true;
      }
      gamePoss = bool;
      gameYardLine = 75;
      gameYardsNeed = 10;
      gameDown = 1;
      gameTime = -1;
      bottomOT = true;
      return;
    }
    playingOT = false;
  }
  
  private void runPlay(Team paramTeam1, Team paramTeam2)
  {
    if (gameDown > 4)
    {
      if (!playingOT)
      {
        gameEventLog = (gameEventLog + getEventPrefix() + "TURNOVER ON DOWNS!\n" + abbr + " failed to convert on " + (gameDown - 1) + "th down. " + abbr + " takes over possession on downs.");
        if (!gamePoss) {}
        for (boolean bool = true;; bool = false)
        {
          gamePoss = bool;
          gameDown = 1;
          gameYardsNeed = 10;
          gameYardLine = (100 - gameYardLine);
          return;
        }
      }
      gameEventLog = (gameEventLog + getEventPrefix() + "TURNOVER ON DOWNS!\n" + abbr + " failed to convert on " + (gameDown - 1) + "th down in OT and their possession is over.");
      resetForOT();
      return;
    }
    double d1 = paramTeam1.getPassProf() * 2 - paramTeam2.getPassDef();
    double d2 = Math.random();
    double d3 = paramTeam1.getRushProf() * 2 - paramTeam2.getRushDef();
    double d4 = Math.random();
    double d5 = teamStratOff.getRYB();
    if ((gameDown == 1) && (gameYardLine >= 91)) {
      gameYardsNeed = (100 - gameYardLine);
    }
    if ((gameTime <= 30) && (!playingOT) && (((gamePoss) && (awayScore >= homeScore)) || ((!gamePoss) && (homeScore >= awayScore))))
    {
      if (((gamePoss) && (awayScore - homeScore <= 3)) || ((!gamePoss) && (homeScore - awayScore <= 3) && (gameYardLine > 60)))
      {
        fieldGoalAtt(paramTeam1, paramTeam2);
        return;
      }
      passingPlay(paramTeam1, paramTeam2);
      return;
    }
    if (gameDown >= 4)
    {
      if (((gamePoss) && (awayScore - homeScore > 3)) || ((!gamePoss) && (homeScore - awayScore > 3) && (gameTime < 200)))
      {
        if (gameYardsNeed < 3)
        {
          rushingPlay(paramTeam1, paramTeam2);
          return;
        }
        passingPlay(paramTeam1, paramTeam2);
        return;
      }
      if (gameYardsNeed < 3)
      {
        if (gameYardLine > 65)
        {
          fieldGoalAtt(paramTeam1, paramTeam2);
          return;
        }
        if (gameYardLine > 55)
        {
          rushingPlay(paramTeam1, paramTeam2);
          return;
        }
        puntPlay(paramTeam1);
        return;
      }
      if (gameYardLine > 60)
      {
        fieldGoalAtt(paramTeam1, paramTeam2);
        return;
      }
      puntPlay(paramTeam1);
      return;
    }
    if (((gameDown == 3) && (gameYardsNeed > 4)) || (((gameDown == 1) || (gameDown == 2)) && (d1 * d2 - 10.0D >= d3 * d4 + d5)))
    {
      passingPlay(paramTeam1, paramTeam2);
      return;
    }
    rushingPlay(paramTeam1, paramTeam2);
  }
  
  private void rushAttempt(Team paramTeam1, Team paramTeam2, PlayerRB paramPlayerRB, double paramDouble1, double paramDouble2, int paramInt)
  {
    statsRushAtt += 1;
    statsRushYards += paramInt;
    teamRushYards += paramInt;
    if (gamePoss)
    {
      homeYards += paramInt;
      if (paramDouble1 > paramDouble2)
      {
        paramTeam1 = HomeRB1Stats;
        paramTeam1[0] += 1;
        paramTeam1 = HomeRB1Stats;
        paramTeam1[1] += paramInt;
        return;
      }
      paramTeam1 = HomeRB2Stats;
      paramTeam1[0] += 1;
      paramTeam1 = HomeRB2Stats;
      paramTeam1[1] += paramInt;
      return;
    }
    awayYards += paramInt;
    if (paramDouble1 > paramDouble2)
    {
      paramTeam1 = AwayRB1Stats;
      paramTeam1[0] += 1;
      paramTeam1 = AwayRB1Stats;
      paramTeam1[1] += paramInt;
      return;
    }
    paramTeam1 = AwayRB2Stats;
    paramTeam1[0] += 1;
    paramTeam1 = AwayRB2Stats;
    paramTeam1[1] += paramInt;
  }
  
  private void rushingPlay(Team paramTeam1, Team paramTeam2)
  {
    int k = 0;
    double d1 = Math.pow(getRB0ratOvr, 1.5D) * Math.random();
    double d2 = Math.pow(getRB1ratOvr, 1.5D) * Math.random();
    PlayerRB localPlayerRB;
    int i;
    int j;
    label161:
    int[] arrayOfInt;
    if (d1 > d2)
    {
      localPlayerRB = paramTeam1.getRB(0);
      i = paramTeam1.getCompositeOLRush();
      j = paramTeam2.getCompositeF7Rush();
      j = (int)((ratRushSpd + (i - j) + getHFadv()) * Math.random() / 12.0D + teamStratOff.getRYB() / 2.0D - teamStratDef.getRYB() / 2.0D);
      if (j >= 2) {
        break label423;
      }
      i = (int)(j + (ratRushPow / 20 - 2 - teamStratDef.getRYB() / 2.0D));
      gameYardLine += i;
      j = i;
      if (gameYardLine >= 100)
      {
        addPointsQuarter(6);
        j = i - (gameYardLine - 100);
        gameYardLine = (100 - j);
        if (!gamePoss) {
          break label506;
        }
        homeScore += 6;
        if (d1 <= d2) {
          break label487;
        }
        arrayOfInt = HomeRB1Stats;
        arrayOfInt[2] += 1;
        label253:
        tdInfo = (abbr + " RB " + name + " rushed " + j + " yards for a TD.");
        statsTD += 1;
        k = 1;
      }
      if (k == 0)
      {
        gameYardsNeed -= j;
        if (gameYardsNeed > 0) {
          break label562;
        }
        gameDown = 1;
        gameYardsNeed = 10;
      }
      label354:
      rushAttempt(paramTeam1, paramTeam2, localPlayerRB, d1, d2, j);
      if (k == 0) {
        break label580;
      }
      gameTime = ((int)(gameTime - (5.0D + 15.0D * Math.random())));
      kickXP(paramTeam1, paramTeam2);
      if (playingOT) {
        break label575;
      }
      kickOff(paramTeam1);
    }
    label423:
    label487:
    label506:
    label562:
    label575:
    label580:
    double d3;
    do
    {
      return;
      localPlayerRB = paramTeam1.getRB(1);
      break;
      i = j;
      if (Math.random() >= 0.2D + (teamStratOff.getRAB() - teamStratDef.getRYB() / 2.0D) / 50.0D) {
        break label161;
      }
      i = (int)(j + ratRushEva / 6.0D * Math.random());
      break label161;
      arrayOfInt = HomeRB2Stats;
      arrayOfInt[2] += 1;
      break label253;
      awayScore += 6;
      if (d1 > d2)
      {
        arrayOfInt = AwayRB1Stats;
        arrayOfInt[2] += 1;
        break label253;
      }
      arrayOfInt = AwayRB2Stats;
      arrayOfInt[2] += 1;
      break label253;
      gameDown += 1;
      break label354;
      resetForOT();
      return;
      gameTime = ((int)(gameTime - (25.0D + 15.0D * Math.random())));
      d3 = (getS0ratSTkl + paramTeam2.getCompositeF7Rush() - getHFadv()) / 2 + teamStratOff.getRAB();
    } while (100.0D * Math.random() >= d3 / 60.0D);
    if (gamePoss)
    {
      homeTOs += 1;
      if (d1 > d2)
      {
        paramTeam2 = HomeRB1Stats;
        paramTeam2[3] += 1;
        gameEventLog = (gameEventLog + getEventPrefix() + "TURNOVER!\n" + abbr + " RB " + name + " fumbled the ball while rushing.");
        statsFumbles += 1;
        if (playingOT) {
          break label877;
        }
        gameDown = 1;
        gameYardsNeed = 10;
        if (gamePoss) {
          break label871;
        }
      }
    }
    label871:
    for (boolean bool = true;; bool = false)
    {
      gamePoss = bool;
      gameYardLine = (100 - gameYardLine);
      return;
      paramTeam2 = HomeRB2Stats;
      paramTeam2[3] += 1;
      break;
      awayTOs += 1;
      if (d1 > d2)
      {
        paramTeam2 = AwayRB1Stats;
        paramTeam2[3] += 1;
        break;
      }
      paramTeam2 = AwayRB2Stats;
      paramTeam2[3] += 1;
      break;
    }
    label877:
    resetForOT();
  }
  
  private void safety()
  {
    if (gamePoss)
    {
      awayScore += 2;
      gameEventLog = (gameEventLog + getEventPrefix() + "SAFETY!\n" + homeTeam.abbr + " QB " + homeTeam.getQB(0).name + " was tackled in the endzone! Result is a safety and " + awayTeam.abbr + " will get possession.");
      freeKick(homeTeam);
      return;
    }
    homeScore += 2;
    gameEventLog = (gameEventLog + getEventPrefix() + "SAFETY!\n" + awayTeam.abbr + " QB " + awayTeam.getQB(0).name + " was tackled in the endzone! Result is a safety and " + homeTeam.abbr + " will get possession.");
    freeKick(awayTeam);
  }
  
  public void addNewsStory()
  {
    Team localTeam2;
    Team localTeam1;
    int j;
    int i;
    if (numOT >= 3) {
      if (awayScore > homeScore)
      {
        localTeam2 = awayTeam;
        localTeam1 = homeTeam;
        j = awayScore;
        i = homeScore;
        ((ArrayList)homeTeam.league.newsStories.get(homeTeam.league.currentWeek + 1)).add(numOT + "OT Thriller!>" + localTeam2.strRep() + " and " + localTeam1.strRep() + " played an absolutely thrilling game that went to " + numOT + " overtimes, with " + name + " finally emerging victorious " + j + " to " + i + ".");
      }
    }
    do
    {
      return;
      localTeam2 = homeTeam;
      localTeam1 = awayTeam;
      j = homeScore;
      i = awayScore;
      break;
      if ((homeScore > awayScore) && (awayTeam.losses == 1) && (awayTeam.league.currentWeek > 5))
      {
        ((ArrayList)awayTeam.league.newsStories.get(homeTeam.league.currentWeek + 1)).add("Undefeated no more! " + awayTeam.name + " suffers first loss!>" + homeTeam.strRep() + " hands " + awayTeam.strRep() + " their first loss of the season, winning " + homeScore + " to " + awayScore + ".");
        return;
      }
      if ((awayScore > homeScore) && (homeTeam.losses == 1) && (homeTeam.league.currentWeek > 5))
      {
        ((ArrayList)homeTeam.league.newsStories.get(homeTeam.league.currentWeek + 1)).add("Undefeated no more! " + homeTeam.name + " suffers first loss!>" + awayTeam.strRep() + " hands " + homeTeam.strRep() + " their first loss of the season, winning " + awayScore + " to " + homeScore + ".");
        return;
      }
      if ((awayScore > homeScore) && (homeTeam.rankTeamPollScore < 20) && (awayTeam.rankTeamPollScore - homeTeam.rankTeamPollScore > 20))
      {
        ((ArrayList)awayTeam.league.newsStories.get(awayTeam.league.currentWeek + 1)).add("Upset! " + awayTeam.strRep() + " beats " + homeTeam.strRep() + ">#" + awayTeam.rankTeamPollScore + " " + awayTeam.name + " was able to pull off the upset on the road against #" + homeTeam.rankTeamPollScore + " " + homeTeam.name + ", winning " + awayScore + " to " + homeScore + ".");
        return;
      }
    } while ((homeScore <= awayScore) || (awayTeam.rankTeamPollScore >= 20) || (homeTeam.rankTeamPollScore - awayTeam.rankTeamPollScore <= 20));
    ((ArrayList)homeTeam.league.newsStories.get(homeTeam.league.currentWeek + 1)).add("Upset! " + homeTeam.strRep() + " beats " + awayTeam.strRep() + ">#" + homeTeam.rankTeamPollScore + " " + homeTeam.name + " was able to pull off the upset at home against #" + awayTeam.rankTeamPollScore + " " + awayTeam.name + ", winning " + homeScore + " to " + awayScore + ".");
  }
  
  public String getCSV()
  {
    return homeTeam.abbr + "," + awayTeam.abbr + "," + homeScore + "," + awayScore + "," + gameName;
  }
  
  public String[] getGameScoutStr()
  {
    Object localObject1;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    if (isOnline)
    {
      localObject1 = new StringBuilder();
      localObject2 = new StringBuilder();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject1).append("\nELO\nWins\nLosses\nPPG\nOpp PPG\nYPG\nOpp YPG\n\nPass YPG\nRush YPG\nOpp PYPG\nOpp RYPG\n\nOff Talent\nDef Talent");
      localObject4 = awayTeam;
      ((StringBuilder)localObject2).append(abbr + "\n" + teamELO + "\n" + wins + "\n" + losses + "\n" + ((Team)localObject4).getPPG() + "\n" + ((Team)localObject4).getOPPG() + "\n" + ((Team)localObject4).getYPG() + "\n" + ((Team)localObject4).getOYPG() + "\n\n" + ((Team)localObject4).getPYPG() + "\n" + ((Team)localObject4).getRYPG() + "\n" + ((Team)localObject4).getOPYPG() + "\n" + ((Team)localObject4).getORYPG() + "\n\n" + ((Team)localObject4).getOffTalent() + "\n" + ((Team)localObject4).getDefTalent());
      localObject4 = homeTeam;
      ((StringBuilder)localObject3).append(abbr + "\n" + teamELO + "\n" + wins + "\n" + losses + "\n" + ((Team)localObject4).getPPG() + "\n" + ((Team)localObject4).getOPPG() + "\n" + ((Team)localObject4).getYPG() + "\n" + ((Team)localObject4).getOYPG() + "\n\n" + ((Team)localObject4).getPYPG() + "\n" + ((Team)localObject4).getRYPG() + "\n" + ((Team)localObject4).getOPYPG() + "\n" + ((Team)localObject4).getORYPG() + "\n\n" + ((Team)localObject4).getOffTalent() + "\n" + ((Team)localObject4).getDefTalent());
      return new String[] { ((StringBuilder)localObject1).toString(), ((StringBuilder)localObject2).toString(), ((StringBuilder)localObject3).toString(), "" };
    }
    if (hasData)
    {
      localObject1 = new StringBuilder();
      localObject2 = new StringBuilder();
      localObject3 = new StringBuilder();
      ((StringBuilder)localObject1).append("Ranking\nRecord\nPPG\nOpp PPG\nYPG\nOpp YPG\n\nPass YPG\nRush YPG\nOpp PYPG\nOpp RYPG\n\nOff Talent\nDef Talent");
      int i = awayTeam.numGames();
      localObject4 = awayTeam;
      ((StringBuilder)localObject2).append("#" + rankTeamPollScore + " " + abbr + "\n" + wins + "-" + losses + "\n" + teamPoints / i + " (" + rankTeamPoints + ")\n" + teamOppPoints / i + " (" + rankTeamOppPoints + ")\n" + teamYards / i + " (" + rankTeamYards + ")\n" + teamOppYards / i + " (" + rankTeamOppYards + ")\n\n" + teamPassYards / i + " (" + rankTeamPassYards + ")\n" + teamRushYards / i + " (" + rankTeamRushYards + ")\n" + teamOppPassYards / i + " (" + rankTeamOppPassYards + ")\n" + teamOppRushYards / i + " (" + rankTeamOppRushYards + ")\n\n" + teamOffTalent + " (" + rankTeamOffTalent + ")\n" + teamDefTalent + " (" + rankTeamDefTalent + ")\n");
      i = homeTeam.numGames();
      localObject4 = homeTeam;
      ((StringBuilder)localObject3).append("#" + rankTeamPollScore + " " + abbr + "\n" + wins + "-" + losses + "\n" + teamPoints / i + " (" + rankTeamPoints + ")\n" + teamOppPoints / i + " (" + rankTeamOppPoints + ")\n" + teamYards / i + " (" + rankTeamYards + ")\n" + teamOppYards / i + " (" + rankTeamOppYards + ")\n\n" + teamPassYards / i + " (" + rankTeamPassYards + ")\n" + teamRushYards / i + " (" + rankTeamRushYards + ")\n" + teamOppPassYards / i + " (" + rankTeamOppPassYards + ")\n" + teamOppRushYards / i + " (" + rankTeamOppRushYards + ")\n\n" + teamOffTalent + " (" + rankTeamOffTalent + ")\n" + teamDefTalent + " (" + rankTeamDefTalent + ")\n");
      localObject1 = ((StringBuilder)localObject1).toString();
      localObject2 = ((StringBuilder)localObject2).toString();
      localObject3 = ((StringBuilder)localObject3).toString();
      localObject4 = new StringBuilder();
      Iterator localIterator;
      Player localPlayer;
      if ((awayTeam.playersInjuredAll != null) && (!awayTeam.playersInjuredAll.isEmpty()))
      {
        Collections.sort(awayTeam.playersInjuredAll, new PlayerPositionComparator());
        ((StringBuilder)localObject4).append("\n" + awayTeam.abbr + " Injury Report:\n");
        localIterator = awayTeam.playersInjuredAll.iterator();
        while (localIterator.hasNext())
        {
          localPlayer = (Player)localIterator.next();
          ((StringBuilder)localObject4).append(localPlayer.getPosNameYrOvrPot_OneLine() + "\n");
        }
      }
      if ((homeTeam.playersInjuredAll != null) && (!homeTeam.playersInjuredAll.isEmpty()))
      {
        Collections.sort(homeTeam.playersInjuredAll, new PlayerPositionComparator());
        ((StringBuilder)localObject4).append("\n" + homeTeam.abbr + " Injury Report:\n");
        localIterator = homeTeam.playersInjuredAll.iterator();
        while (localIterator.hasNext())
        {
          localPlayer = (Player)localIterator.next();
          ((StringBuilder)localObject4).append(localPlayer.getPosNameYrOvrPot_OneLine() + "\n");
        }
      }
      return new String[] { localObject1, localObject2, localObject3, ((StringBuilder)localObject4).toString() };
    }
    return new String[] { "No data", "No data", "No data", "No data" };
  }
  
  public String[] getGameSummaryStr()
  {
    if (hasData)
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      StringBuilder localStringBuilder2 = new StringBuilder();
      StringBuilder localStringBuilder3 = new StringBuilder();
      localStringBuilder1.append("\nPoints\nYards\nPass Yards\nRush Yards\nTOs\n\n");
      localStringBuilder2.append(awayTeam.getStrAbbrWL() + "\n" + awayScore + "\n" + awayYards + " yds\n" + getPassYards(true) + " pyds\n" + getRushYards(true) + " ryds\n" + awayTOs + " TOs\n\n");
      localStringBuilder3.append(homeTeam.getStrAbbrWL() + "\n" + homeScore + "\n" + homeYards + " yds\n" + getPassYards(false) + " pyds\n" + getRushYards(false) + " ryds\n" + homeTOs + " TOs\n\n");
      localStringBuilder1.append("QBs\nName\nYr Ovr/Pot\nTD/Int\nPass Yards\nComp/Att\n");
      localStringBuilder2.append(awayTeam.abbr + "\n" + awayQB.getInitialName() + "\n");
      localStringBuilder2.append(awayQB.age + " " + awayQB.ratOvr + "/" + Player.getLetterGrade(awayQB.ratPot) + "\n");
      localStringBuilder2.append(AwayQBStats[2] + "/" + AwayQBStats[3] + "\n");
      localStringBuilder2.append(AwayQBStats[4] + " yds\n");
      localStringBuilder2.append(AwayQBStats[0] + "/" + AwayQBStats[1] + "\n");
      localStringBuilder3.append(homeTeam.abbr + "\n" + homeQB.getInitialName() + "\n");
      localStringBuilder3.append(homeQB.age + " " + homeQB.ratOvr + "/" + Player.getLetterGrade(homeQB.ratPot) + "\n");
      localStringBuilder3.append(HomeQBStats[2] + "/" + HomeQBStats[3] + "\n");
      localStringBuilder3.append(HomeQBStats[4] + " yds\n");
      localStringBuilder3.append(HomeQBStats[0] + "/" + HomeQBStats[1] + "\n");
      localStringBuilder1.append("\nRBs\nRB1 Name\nYr Ovr/Pot\nTD/Fum\nRush Yards\nYds/Att\n");
      localStringBuilder2.append("\n" + awayTeam.abbr + "\n" + awayRBs[0].getInitialName() + "\n");
      localStringBuilder2.append(awayRBs[0].age + " " + awayRBs[0].ratOvr + "/" + Player.getLetterGrade(awayRBs[0].ratPot) + "\n");
      localStringBuilder2.append(AwayRB1Stats[2] + "/" + AwayRB1Stats[3] + "\n");
      localStringBuilder2.append(AwayRB1Stats[1] + " yds\n");
      localStringBuilder2.append(AwayRB1Stats[1] * 10 / AwayRB1Stats[0] / 10.0D + "\n");
      localStringBuilder3.append("\n" + homeTeam.abbr + "\n" + homeRBs[0].getInitialName() + "\n");
      localStringBuilder3.append(homeRBs[0].age + " " + homeRBs[0].ratOvr + "/" + Player.getLetterGrade(homeRBs[0].ratPot) + "\n");
      localStringBuilder3.append(HomeRB1Stats[2] + "/" + HomeRB1Stats[3] + "\n");
      localStringBuilder3.append(HomeRB1Stats[1] + " yds\n");
      localStringBuilder3.append(HomeRB1Stats[1] * 10 / HomeRB1Stats[0] / 10.0D + "\n");
      localStringBuilder1.append("\n");
      localStringBuilder2.append("\n");
      localStringBuilder3.append("\n");
      localStringBuilder1.append("RB2 Name\nYr Ovr/Pot\nTD/Fum\nRush Yards\nYds/Att\n");
      localStringBuilder2.append(awayRBs[1].getInitialName() + "\n");
      localStringBuilder2.append(awayRBs[1].age + " " + awayRBs[1].ratOvr + "/" + Player.getLetterGrade(awayRBs[1].ratPot) + "\n");
      localStringBuilder2.append(AwayRB2Stats[2] + "/" + AwayRB2Stats[3] + "\n");
      localStringBuilder2.append(AwayRB2Stats[1] + " yds\n");
      localStringBuilder2.append(AwayRB2Stats[1] * 10 / AwayRB2Stats[0] / 10.0D + "\n");
      localStringBuilder3.append(homeRBs[1].getInitialName() + "\n");
      localStringBuilder3.append(homeRBs[1].age + " " + homeRBs[1].ratOvr + "/" + Player.getLetterGrade(homeRBs[1].ratPot) + "\n");
      localStringBuilder3.append(HomeRB2Stats[2] + "/" + HomeRB2Stats[3] + "\n");
      localStringBuilder3.append(HomeRB2Stats[1] + " yds\n");
      localStringBuilder3.append(HomeRB2Stats[1] * 10 / HomeRB2Stats[0] / 10.0D + "\n");
      localStringBuilder1.append("\nWRs\nWR1 Name\nYr Ovr/Pot\nTD/Fum\nRec Yards\nRec/Tgts\n");
      localStringBuilder2.append("\n" + awayTeam.abbr + "\n" + awayWRs[0].getInitialName() + "\n");
      localStringBuilder2.append(awayWRs[0].age + " " + awayWRs[0].ratOvr + "/" + Player.getLetterGrade(awayWRs[0].ratPot) + "\n");
      localStringBuilder2.append(AwayWR1Stats[3] + "/" + AwayWR1Stats[5] + "\n");
      localStringBuilder2.append(AwayWR1Stats[2] + " yds\n");
      localStringBuilder2.append(AwayWR1Stats[0] + "/" + AwayWR1Stats[1] + "\n");
      localStringBuilder3.append("\n" + homeTeam.abbr + "\n" + homeWRs[0].getInitialName() + "\n");
      localStringBuilder3.append(homeWRs[0].age + " " + homeWRs[0].ratOvr + "/" + Player.getLetterGrade(homeWRs[0].ratPot) + "\n");
      localStringBuilder3.append(HomeWR1Stats[3] + "/" + HomeWR1Stats[5] + "\n");
      localStringBuilder3.append(HomeWR1Stats[2] + " yds\n");
      localStringBuilder3.append(HomeWR1Stats[0] + "/" + HomeWR1Stats[1] + "\n");
      localStringBuilder1.append("\n");
      localStringBuilder2.append("\n");
      localStringBuilder3.append("\n");
      localStringBuilder1.append("WR2 Name\nYr Ovr/Pot\nTD/Fum\nRec Yards\nRec/Tgts\n");
      localStringBuilder2.append(awayWRs[1].getInitialName() + "\n");
      localStringBuilder2.append(awayWRs[1].age + " " + awayWRs[1].ratOvr + "/" + Player.getLetterGrade(awayWRs[1].ratPot) + "\n");
      localStringBuilder2.append(AwayWR2Stats[3] + "/" + AwayWR2Stats[5] + "\n");
      localStringBuilder2.append(AwayWR2Stats[2] + " yds\n");
      localStringBuilder2.append(AwayWR2Stats[0] + "/" + AwayWR2Stats[1] + "\n");
      localStringBuilder3.append(homeWRs[1].getInitialName() + "\n");
      localStringBuilder3.append(homeWRs[1].age + " " + homeWRs[1].ratOvr + "/" + Player.getLetterGrade(homeWRs[1].ratPot) + "\n");
      localStringBuilder3.append(HomeWR2Stats[3] + "/" + HomeWR2Stats[5] + "\n");
      localStringBuilder3.append(HomeWR2Stats[2] + " yds\n");
      localStringBuilder3.append(HomeWR2Stats[0] + "/" + HomeWR2Stats[1] + "\n");
      localStringBuilder1.append("\n");
      localStringBuilder2.append("\n");
      localStringBuilder3.append("\n");
      localStringBuilder1.append("WR3 Name\nYr Ovr/Pot\nTD/Fum\nRec Yards\nRec/Tgts\n");
      localStringBuilder2.append(awayWRs[2].getInitialName() + "\n");
      localStringBuilder2.append(awayWRs[2].age + " " + awayWRs[2].ratOvr + "/" + Player.getLetterGrade(awayWRs[2].ratPot) + "\n");
      localStringBuilder2.append(AwayWR3Stats[3] + "/" + AwayWR3Stats[5] + "\n");
      localStringBuilder2.append(AwayWR3Stats[2] + " yds\n");
      localStringBuilder2.append(AwayWR3Stats[0] + "/" + AwayWR3Stats[1] + "\n");
      localStringBuilder3.append(homeWRs[2].getInitialName() + "\n");
      localStringBuilder3.append(homeWRs[2].age + " " + homeWRs[2].ratOvr + "/" + Player.getLetterGrade(homeWRs[2].ratPot) + "\n");
      localStringBuilder3.append(HomeWR3Stats[3] + "/" + HomeWR3Stats[5] + "\n");
      localStringBuilder3.append(HomeWR3Stats[2] + " yds\n");
      localStringBuilder3.append(HomeWR3Stats[0] + "/" + HomeWR3Stats[1] + "\n");
      localStringBuilder1.append("\nKs\nName\nYr Ovr/Pot\nFGM/FGA\nXPM/XPA\n");
      localStringBuilder2.append("\n" + awayTeam.abbr + "\n" + awayK.getInitialName() + "\n");
      localStringBuilder2.append(awayK.age + " " + awayK.ratOvr + "/" + Player.getLetterGrade(awayK.ratPot) + "\n");
      localStringBuilder2.append(AwayKStats[2] + "/" + AwayKStats[3] + " FG\n" + AwayKStats[0] + "/" + AwayKStats[1] + " XP\n");
      localStringBuilder3.append("\n" + homeTeam.abbr + "\n" + homeK.getInitialName() + "\n");
      localStringBuilder3.append(homeK.age + " " + homeK.ratOvr + "/" + Player.getLetterGrade(homeK.ratPot) + "\n");
      localStringBuilder3.append(HomeKStats[2] + "/" + HomeKStats[3] + " FG\n" + HomeKStats[0] + "/" + HomeKStats[1] + " XP\n");
      return new String[] { localStringBuilder1.toString(), localStringBuilder2.toString(), localStringBuilder3.toString(), gameEventLog };
    }
    return new String[] { " ", " ", " ", "Game details are not saved between play sessions." };
  }
  
  public JSONObject getJSONGameResult(boolean paramBoolean)
    throws JSONException
  {
    if (paramBoolean)
    {
      localJSONObject = new JSONObject();
      localJSONObject.put("name", homeTeam.name);
      localJSONObject.put("elo", homeELO);
      if (homeScore > awayScore)
      {
        localJSONObject.put("wins", 1);
        localJSONObject.put("losses", 0);
      }
      for (;;)
      {
        localJSONObject.put("points", homeScore);
        localJSONObject.put("opp points", awayScore);
        localJSONObject.put("yards", homeYards);
        localJSONObject.put("opp yards", awayYards);
        localJSONObject.put("pass yards", getPassYards(false));
        localJSONObject.put("rush yards", getRushYards(false));
        localJSONObject.put("opp pass yards", getPassYards(true));
        localJSONObject.put("opp rush yards", getRushYards(true));
        localJSONObject.put("to diff", awayTOs - homeTOs);
        return localJSONObject;
        localJSONObject.put("wins", 0);
        localJSONObject.put("losses", 1);
      }
    }
    JSONObject localJSONObject = new JSONObject();
    localJSONObject.put("name", awayTeam.name);
    localJSONObject.put("elo", awayELO);
    if (homeScore < awayScore)
    {
      localJSONObject.put("wins", 1);
      localJSONObject.put("losses", 0);
    }
    for (;;)
    {
      localJSONObject.put("points", awayScore);
      localJSONObject.put("opp points", homeScore);
      localJSONObject.put("yards", awayYards);
      localJSONObject.put("opp yards", homeYards);
      localJSONObject.put("pass yards", getPassYards(true));
      localJSONObject.put("rush yards", getRushYards(true));
      localJSONObject.put("opp pass yards", getPassYards(false));
      localJSONObject.put("opp rush yards", getRushYards(false));
      localJSONObject.put("to diff", homeTOs - awayTOs);
      return localJSONObject;
      localJSONObject.put("wins", 0);
      localJSONObject.put("losses", 1);
    }
  }
  
  public Team getLoser()
  {
    if (homeScore < awayScore) {
      return homeTeam;
    }
    return awayTeam;
  }
  
  public int getPassYards(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return HomeQBStats[4];
    }
    return AwayQBStats[4];
  }
  
  public int getRushYards(boolean paramBoolean)
  {
    if (!paramBoolean) {
      return HomeRB1Stats[1] + HomeRB2Stats[1];
    }
    return AwayRB1Stats[1] + AwayRB2Stats[1];
  }
  
  public Team getWinner()
  {
    if (homeScore > awayScore) {
      return homeTeam;
    }
    return awayTeam;
  }
  
  public void playGame()
  {
    label457:
    Team localTeam;
    label711:
    int i;
    if (!hasPlayed)
    {
      gameEventLog = ("LOG: #" + awayTeam.rankTeamPollScore + " " + awayTeam.abbr + " (" + awayTeam.wins + "-" + awayTeam.losses + ") @ #" + homeTeam.rankTeamPollScore + " " + homeTeam.abbr + " (" + homeTeam.wins + "-" + homeTeam.losses + ")\n---------------------------------------------------------\n\n" + awayTeam.abbr + " Off Strategy: " + awayTeam.teamStratOff.getStratName() + "\n" + awayTeam.abbr + " Def Strategy: " + awayTeam.teamStratDef.getStratName() + "\n" + homeTeam.abbr + " Off Strategy: " + homeTeam.teamStratOff.getStratName() + "\n" + homeTeam.abbr + " Def Strategy: " + homeTeam.teamStratDef.getStratName() + "\n");
      gameTime = 3600;
      gameDown = 1;
      gamePoss = true;
      gameYardsNeed = 10;
      gameYardLine = 20;
      while (gameTime > 0)
      {
        if (gamePoss) {
          runPlay(homeTeam, awayTeam);
        }
        for (;;)
        {
          if ((gameTime > 0) || (homeScore != awayScore)) {
            break label457;
          }
          gameEventLog = (gameEventLog + getEventPrefix() + "OVERTIME!\nTie game at 0:00, overtime begins!");
          gamePoss = true;
          gameTime = 900;
          gameDown = 1;
          gameYardsNeed = 10;
          gameYardLine = 20;
          numOT += 1;
          break;
          runPlay(awayTeam, homeTeam);
        }
      }
      gameEventLog = (gameEventLog + getEventPrefix() + "Time has expired! The game is over.");
      if (homeScore > awayScore)
      {
        winner = homeTeam;
        localTeam = homeTeam;
        wins += 1;
        localTeam = homeTeam;
        totalWins += 1;
        homeTeam.gameWLSchedule.add("W");
        localTeam = awayTeam;
        losses += 1;
        localTeam = awayTeam;
        totalLosses += 1;
        awayTeam.gameWLSchedule.add("L");
        homeTeam.gameWinsAgainst.add(awayTeam);
        if (!isOnline)
        {
          homeTeam.winStreak.addWin(homeTeam.league.getYear());
          homeTeam.league.checkLongestWinStreak(homeTeam.winStreak);
          awayTeam.winStreak.resetStreak(awayTeam.league.getYear());
        }
        localTeam = homeTeam;
        if (homeScore <= awayScore) {
          break label1302;
        }
        bool = true;
        localTeam.addGamePlayedPlayers(bool);
        localTeam = awayTeam;
        if (awayScore <= homeScore) {
          break label1308;
        }
      }
      label1302:
      label1308:
      for (boolean bool = true;; bool = false)
      {
        localTeam.addGamePlayedPlayers(bool);
        localTeam = homeTeam;
        teamPoints += homeScore;
        localTeam = awayTeam;
        teamPoints += awayScore;
        localTeam = homeTeam;
        teamOppPoints += awayScore;
        localTeam = awayTeam;
        teamOppPoints += homeScore;
        localTeam = homeTeam;
        teamYards += homeYards;
        localTeam = awayTeam;
        teamYards += awayYards;
        localTeam = homeTeam;
        teamOppYards += awayYards;
        localTeam = awayTeam;
        teamOppYards += homeYards;
        localTeam = homeTeam;
        teamOppPassYards += getPassYards(true);
        localTeam = awayTeam;
        teamOppPassYards += getPassYards(false);
        localTeam = homeTeam;
        teamOppRushYards += getRushYards(true);
        localTeam = awayTeam;
        teamOppRushYards += getRushYards(false);
        localTeam = homeTeam;
        teamTODiff += awayTOs - homeTOs;
        localTeam = awayTeam;
        teamTODiff += homeTOs - awayTOs;
        hasPlayed = true;
        homeQB = homeTeam.getQB(0);
        homeRBs = new PlayerRB[2];
        i = 0;
        while (i < 2)
        {
          homeRBs[i] = homeTeam.getRB(i);
          i += 1;
        }
        winner = awayTeam;
        localTeam = homeTeam;
        losses += 1;
        localTeam = homeTeam;
        totalLosses += 1;
        homeTeam.gameWLSchedule.add("L");
        localTeam = awayTeam;
        wins += 1;
        localTeam = awayTeam;
        totalWins += 1;
        awayTeam.gameWLSchedule.add("W");
        awayTeam.gameWinsAgainst.add(homeTeam);
        if (isOnline) {
          break;
        }
        awayTeam.winStreak.addWin(awayTeam.league.getYear());
        awayTeam.league.checkLongestWinStreak(awayTeam.winStreak);
        homeTeam.winStreak.resetStreak(homeTeam.league.getYear());
        break;
        bool = false;
        break label711;
      }
      homeWRs = new PlayerWR[3];
      i = 0;
      while (i < 3)
      {
        homeWRs[i] = homeTeam.getWR(i);
        i += 1;
      }
      homeK = homeTeam.getK(0);
      awayQB = awayTeam.getQB(0);
      awayRBs = new PlayerRB[2];
      i = 0;
      while (i < 2)
      {
        awayRBs[i] = awayTeam.getRB(i);
        i += 1;
      }
      awayWRs = new PlayerWR[3];
      i = 0;
      while (i < 3)
      {
        awayWRs[i] = awayTeam.getWR(i);
        i += 1;
      }
      awayK = awayTeam.getK(0);
      if (!isOnline)
      {
        homeTeam.checkForInjury();
        awayTeam.checkForInjury();
      }
    }
    else
    {
      return;
    }
    if (affectsELO)
    {
      double d2 = Math.pow(10.0D, homeTeam.teamELO / 400.0D);
      double d3 = Math.pow(10.0D, awayTeam.teamELO / 400.0D);
      double d1 = d2 / (d2 + d3);
      d2 = d3 / (d2 + d3);
      int j;
      if (homeScore > awayScore) {
        j = 1;
      }
      for (i = 0;; i = 1)
      {
        System.out.println("Home change: " + 25.0D * (j - d1));
        System.out.println("Away change: " + 25.0D * (i - d2));
        homeELO = ((int)(30.0D * (j - d1)));
        awayELO = ((int)(30.0D * (i - d2)));
        localTeam = homeTeam;
        teamELO += homeELO;
        localTeam = awayTeam;
        teamELO += awayELO;
        return;
        j = 0;
      }
    }
    homeELO = 0;
    awayELO = 0;
  }
}

/* Location:
 * Qualified Name:     PFCpack.Game
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */
