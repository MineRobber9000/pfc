// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

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
    
    public Game(final League league, final String s, final int n) {
        final String[] split = s.split(",");
        this.homeTeam = league.findTeamAbbr(split[0]);
        this.awayTeam = league.findTeamAbbr(split[1]);
        if (n < 16) {
            this.homeTeam.regSeasonSchedule[n] = this;
            this.awayTeam.regSeasonSchedule[n] = this;
        }
        this.homeScore = Integer.parseInt(split[2]);
        this.awayScore = Integer.parseInt(split[3]);
        if (this.homeScore == 0 && this.awayScore == 0) {
            this.hasData = true;
            this.hasPlayed = false;
        }
        else {
            this.hasData = false;
            this.hasPlayed = true;
            if (this.homeScore > this.awayScore) {
                this.winner = this.homeTeam;
            }
            else {
                this.winner = this.awayTeam;
            }
        }
        this.gameName = split[4];
        this.homeQScore = new int[10];
        this.awayQScore = new int[10];
        this.numOT = 0;
        this.homeTOs = 0;
        this.awayTOs = 0;
        this.HomeQBStats = new int[6];
        this.AwayQBStats = new int[6];
        this.HomeRB1Stats = new int[4];
        this.HomeRB2Stats = new int[4];
        this.AwayRB1Stats = new int[4];
        this.AwayRB2Stats = new int[4];
        this.HomeWR1Stats = new int[6];
        this.HomeWR2Stats = new int[6];
        this.HomeWR3Stats = new int[6];
        this.AwayWR1Stats = new int[6];
        this.AwayWR2Stats = new int[6];
        this.AwayWR3Stats = new int[6];
        this.HomeKStats = new int[6];
        this.AwayKStats = new int[6];
        this.affectsELO = false;
        this.isOnline = false;
    }
    
    public Game(final Team homeTeam, final Team awayTeam, final String gameName, final int n) {
        if (n < 16) {
            homeTeam.regSeasonSchedule[n] = this;
            awayTeam.regSeasonSchedule[n] = this;
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
        }
        else if (homeTeam.wins < awayTeam.wins) {
            this.homeTeam = awayTeam;
            this.awayTeam = homeTeam;
        }
        else {
            this.homeTeam = homeTeam;
            this.awayTeam = awayTeam;
        }
        this.gameName = gameName;
        this.homeScore = 0;
        this.homeQScore = new int[10];
        this.awayScore = 0;
        this.awayQScore = new int[10];
        this.numOT = 0;
        this.homeTOs = 0;
        this.awayTOs = 0;
        this.HomeQBStats = new int[6];
        this.AwayQBStats = new int[6];
        this.HomeRB1Stats = new int[4];
        this.HomeRB2Stats = new int[4];
        this.AwayRB1Stats = new int[4];
        this.AwayRB2Stats = new int[4];
        this.HomeWR1Stats = new int[6];
        this.HomeWR2Stats = new int[6];
        this.HomeWR3Stats = new int[6];
        this.AwayWR1Stats = new int[6];
        this.AwayWR2Stats = new int[6];
        this.AwayWR3Stats = new int[6];
        this.HomeKStats = new int[6];
        this.AwayKStats = new int[6];
        this.hasPlayed = false;
        this.hasData = true;
        this.affectsELO = false;
        this.isOnline = false;
    }
    
    private void addPointsQuarter(final int n) {
        if (this.gamePoss) {
            if (this.gameTime > 2700) {
                final int[] homeQScore = this.homeQScore;
                homeQScore[0] += n;
                return;
            }
            if (this.gameTime > 1800) {
                final int[] homeQScore2 = this.homeQScore;
                homeQScore2[1] += n;
                return;
            }
            if (this.gameTime > 900) {
                final int[] homeQScore3 = this.homeQScore;
                homeQScore3[2] += n;
                return;
            }
            if (this.numOT == 0) {
                final int[] homeQScore4 = this.homeQScore;
                homeQScore4[3] += n;
                return;
            }
            if (this.numOT + 3 < 10) {
                final int[] homeQScore5 = this.homeQScore;
                final int n2 = this.numOT + 3;
                homeQScore5[n2] += n;
                return;
            }
            final int[] homeQScore6 = this.homeQScore;
            homeQScore6[9] += n;
        }
        else {
            if (this.gameTime > 2700) {
                final int[] awayQScore = this.awayQScore;
                awayQScore[0] += n;
                return;
            }
            if (this.gameTime > 1800) {
                final int[] awayQScore2 = this.awayQScore;
                awayQScore2[1] += n;
                return;
            }
            if (this.gameTime > 900) {
                final int[] awayQScore3 = this.awayQScore;
                awayQScore3[2] += n;
                return;
            }
            if (this.numOT == 0) {
                final int[] awayQScore4 = this.awayQScore;
                awayQScore4[3] += n;
                return;
            }
            if (this.numOT + 3 < 10) {
                final int[] awayQScore5 = this.awayQScore;
                final int n3 = this.numOT + 3;
                awayQScore5[n3] += n;
                return;
            }
            final int[] awayQScore6 = this.awayQScore;
            awayQScore6[9] += n;
        }
    }
    
    private String convGameTime() {
        if (!this.playingOT) {
            final int n = (3600 - this.gameTime) / 900 + 1;
            if (n >= 4 && this.numOT > 0) {
                final int n2 = this.gameTime / 60;
                final int n3 = this.gameTime - n2 * 60;
                String s;
                if (n3 < 10) {
                    s = "0" + n3;
                }
                else {
                    s = "" + n3;
                }
                if (n3 <= 0) {
                    s = "00";
                }
                return n2 + ":" + s + " OT" + this.numOT;
            }
            if (this.gameTime <= 0 && this.numOT <= 0) {
                return "0:00 Q4";
            }
            final int n4 = (this.gameTime - (4 - n) * 900) / 60;
            final int n5 = this.gameTime - (4 - n) * 900 - n4 * 60;
            String s2;
            if (n5 < 10) {
                s2 = "0" + n5;
            }
            else {
                s2 = "" + n5;
            }
            return n4 + ":" + s2 + " Q" + n;
        }
        else {
            if (!this.bottomOT) {
                return "TOP OT" + this.numOT;
            }
            return "BOT OT" + this.numOT;
        }
    }
    
    private void fieldGoalAtt(final Team team, final Team team2) {
        final double pow = Math.pow((110 - this.gameYardLine) / 50, 2.0);
        final double pow2 = Math.pow((110 - this.gameYardLine) / 50, 1.25);
        final double n = this.getHFadv() + team.getK(0).ratKickPow;
        final double n2 = this.getHFadv() + team.getK(0).ratKickAcc;
        if (n - 80.0 * pow > 20.0 && Math.random() * (n2 - 80.0 * pow2) > 15.0) {
            if (this.gamePoss) {
                this.homeScore += 3;
                final int[] homeKStats = this.HomeKStats;
                ++homeKStats[3];
                final int[] homeKStats2 = this.HomeKStats;
                ++homeKStats2[2];
            }
            else {
                this.awayScore += 3;
                final int[] awayKStats = this.AwayKStats;
                ++awayKStats[3];
                final int[] awayKStats2 = this.AwayKStats;
                ++awayKStats2[2];
            }
            this.gameEventLog = this.gameEventLog + this.getEventPrefix() + team.abbr + " K " + team.getK(0).name + " made the " + (110 - this.gameYardLine) + " yard FG.";
            this.addPointsQuarter(3);
            final PlayerK k = team.getK(0);
            ++k.statsFGMade;
            final PlayerK i = team.getK(0);
            ++i.statsFGAtt;
            if (!this.playingOT) {
                this.kickOff(team);
            }
            else {
                this.resetForOT();
            }
        }
        else {
            this.gameEventLog = this.gameEventLog + this.getEventPrefix() + team.abbr + " K " + team.getK(0).name + " missed the " + (110 - this.gameYardLine) + " yard FG.";
            final PlayerK j = team.getK(0);
            ++j.statsFGAtt;
            if (!this.playingOT) {
                this.gameYardLine = Math.max(100 - this.gameYardLine, 20);
                this.gameDown = 1;
                this.gameYardsNeed = 10;
                if (this.gamePoss) {
                    final int[] homeKStats3 = this.HomeKStats;
                    ++homeKStats3[3];
                }
                else {
                    final int[] awayKStats3 = this.AwayKStats;
                    ++awayKStats3[3];
                }
                this.gamePoss = !this.gamePoss;
            }
            else {
                this.resetForOT();
            }
        }
        this.gameTime -= 20;
    }
    
    private void freeKick(final Team team) {
        boolean gamePoss = true;
        final boolean b = false;
        if (this.gameTime <= 0) {
            return;
        }
        if (this.gameTime < 180 && ((this.gamePoss && this.awayScore - this.homeScore <= 8 && this.awayScore - this.homeScore > 0) || (!this.gamePoss && this.homeScore - this.awayScore <= 8 && this.homeScore - this.awayScore > 0))) {
            if (team.getK(0).ratKickFum * Math.random() > 60.0 || Math.random() < 0.1) {
                this.gameEventLog = this.gameEventLog + this.getEventPrefix() + team.abbr + " K " + team.getK(0).name + " successfully executes onside kick! " + team.abbr + " has possession!";
                this.gameYardLine = 35;
                this.gameDown = 1;
                this.gameYardsNeed = 10;
            }
            else {
                this.gameEventLog = this.gameEventLog + this.getEventPrefix() + team.abbr + " K " + team.getK(0).name + " failed the onside kick and lost possession.";
                boolean gamePoss2 = b;
                if (!this.gamePoss) {
                    gamePoss2 = true;
                }
                this.gamePoss = gamePoss2;
                this.gameYardLine = 65;
                this.gameDown = 1;
                this.gameYardsNeed = 10;
            }
            this.gameTime -= (int)(Math.random() * 4.0 + 4.0);
            return;
        }
        this.gameYardLine = (int)(115.0 - (team.getK(0).ratKickPow + 20 - 40.0 * Math.random()));
        if (this.gameYardLine <= 0) {
            this.gameYardLine = 25;
        }
        this.gameDown = 1;
        this.gameYardsNeed = 10;
        if (this.gamePoss) {
            gamePoss = false;
        }
        this.gamePoss = gamePoss;
        this.gameTime -= (int)(15.0 * Math.random());
    }
    
    private String getEventPrefix() {
        String s;
        if (this.gamePoss) {
            s = this.homeTeam.abbr;
        }
        else {
            s = this.awayTeam.abbr;
        }
        String string = "" + this.gameYardsNeed;
        if (this.gameYardLine + this.gameYardsNeed >= 100) {
            string = "Goal";
        }
        int gameDown;
        if (this.gameDown > 4) {
            gameDown = 4;
        }
        else {
            gameDown = this.gameDown;
        }
        return "\n\n" + this.homeTeam.abbr + " " + this.homeScore + " - " + this.awayScore + " " + this.awayTeam.abbr + ", Time: " + this.convGameTime() + "\n\t" + s + " " + gameDown + " and " + string + " at " + this.gameYardLine + " yard line.\n";
    }
    
    private int getHFadv() {
        int n;
        if ((n = (this.homeTeam.getCompositeFootIQ() - this.awayTeam.getCompositeFootIQ()) / 5) > 3) {
            n = 3;
        }
        int n2;
        if ((n2 = n) < -3) {
            n2 = -3;
        }
        if (this.gamePoss) {
            return n2 + 3;
        }
        return -n2;
    }
    
    private void kickOff(final Team team) {
        boolean gamePoss = true;
        final boolean b = false;
        if (this.gameTime <= 0) {
            return;
        }
        if (this.gameTime < 180 && ((this.gamePoss && this.awayScore - this.homeScore <= 8 && this.awayScore - this.homeScore > 0) || (!this.gamePoss && this.homeScore - this.awayScore <= 8 && this.homeScore - this.awayScore > 0))) {
            if (team.getK(0).ratKickFum * Math.random() > 60.0 || Math.random() < 0.1) {
                this.gameEventLog = this.gameEventLog + this.getEventPrefix() + team.abbr + " K " + team.getK(0).name + " successfully executes onside kick! " + team.abbr + " has possession!";
            }
            else {
                this.gameEventLog = this.gameEventLog + this.getEventPrefix() + team.abbr + " K " + team.getK(0).name + " failed the onside kick and lost possession.";
                boolean gamePoss2 = b;
                if (!this.gamePoss) {
                    gamePoss2 = true;
                }
                this.gamePoss = gamePoss2;
            }
            this.gameYardLine = 50;
            this.gameDown = 1;
            this.gameYardsNeed = 10;
            this.gameTime -= (int)(4.0 + 5.0 * Math.random());
        }
        else {
            this.gameYardLine = (int)(100.0 - (team.getK(0).ratKickPow + 20 - 40.0 * Math.random()));
            if (this.gameYardLine <= 0) {
                this.gameYardLine = 25;
            }
            this.gameDown = 1;
            this.gameYardsNeed = 10;
            if (this.gamePoss) {
                gamePoss = false;
            }
            this.gamePoss = gamePoss;
        }
        this.gameTime -= (int)(15.0 * Math.random());
    }
    
    private void kickXP(final Team team, final Team team2) {
        if (this.playingOT && this.bottomOT && ((this.numOT % 2 == 0 && this.awayScore > this.homeScore) || (this.numOT % 2 != 0 && this.homeScore > this.awayScore))) {
            this.gameEventLog = this.gameEventLog + this.getEventPrefix() + " " + this.tdInfo + "\n" + team.abbr + " wins on a walk-off touchdown!";
            return;
        }
        if (!this.playingOT && this.gameTime <= 0 && (this.homeScore - this.awayScore > 2 || this.awayScore - this.homeScore > 2)) {
            if (Math.abs(this.homeScore - this.awayScore) < 7 && ((this.gamePoss && this.homeScore > this.awayScore) || (!this.gamePoss && this.awayScore > this.homeScore))) {
                this.gameEventLog = this.gameEventLog + this.getEventPrefix() + " " + this.tdInfo + "\n" + team.abbr + " wins on a walk-off touchdown!";
                return;
            }
            this.gameEventLog = this.gameEventLog + this.getEventPrefix() + " " + this.tdInfo;
        }
        else {
            if (this.numOT < 3 && (((this.gamePoss || this.awayScore - this.homeScore != 2) && (this.gamePoss || this.homeScore - this.awayScore != 2)) || this.gameTime >= 300)) {
                if (Math.random() * 100.0 < (team.getK(0).ratKickAcc + 100) / 2 && Math.random() > 0.02) {
                    if (this.gamePoss) {
                        ++this.homeScore;
                        final int[] homeKStats = this.HomeKStats;
                        ++homeKStats[0];
                        final int[] homeKStats2 = this.HomeKStats;
                        ++homeKStats2[1];
                    }
                    else {
                        ++this.awayScore;
                        final int[] awayKStats = this.AwayKStats;
                        ++awayKStats[0];
                        final int[] awayKStats2 = this.AwayKStats;
                        ++awayKStats2[1];
                    }
                    this.gameEventLog = this.gameEventLog + this.getEventPrefix() + " " + this.tdInfo + " " + team.getK(0).name + " made the XP.";
                    this.addPointsQuarter(1);
                    final PlayerK k = team.getK(0);
                    ++k.statsXPMade;
                }
                else {
                    this.gameEventLog = this.gameEventLog + this.getEventPrefix() + " " + this.tdInfo + " " + team.getK(0).name + " missed the XP.";
                    if (this.gamePoss) {
                        final int[] homeKStats3 = this.HomeKStats;
                        ++homeKStats3[1];
                    }
                    else {
                        final int[] awayKStats3 = this.AwayKStats;
                        ++awayKStats3[1];
                    }
                }
                final PlayerK i = team.getK(0);
                ++i.statsXPAtt;
                return;
            }
            if (Math.random() <= 0.5) {
                if ((int)((team.getRB(0).ratRushSpd + (team.getCompositeOLRush() - team2.getCompositeF7Rush())) * Math.random() / 6.0) > 5) {
                    if (this.gamePoss) {
                        this.homeScore += 2;
                    }
                    else {
                        this.awayScore += 2;
                    }
                    this.addPointsQuarter(2);
                    this.gameEventLog = this.gameEventLog + this.getEventPrefix() + " " + this.tdInfo + " " + team.getRB(0).name + " rushed for the 2pt conversion.";
                    return;
                }
                this.gameEventLog = this.gameEventLog + this.getEventPrefix() + " " + this.tdInfo + " " + team.getRB(0).name + " stopped at the line of scrimmage, failed the 2pt conversion.";
            }
            else {
                if (100.0 * Math.random() < (this.normalize(team.getQB(0).ratPassAcc) + team.getWR(0).ratRecCat - team2.getCB(0).ratCBCov) / 2 + 25 - (team2.getCompositeF7Pass() * 2 - team.getCompositeOLPass()) / 20) {
                    if (this.gamePoss) {
                        this.homeScore += 2;
                    }
                    else {
                        this.awayScore += 2;
                    }
                    this.addPointsQuarter(2);
                    this.gameEventLog = this.gameEventLog + this.getEventPrefix() + " " + this.tdInfo + " " + team.getQB(0).name + " completed the pass to " + team.getWR(0).name + " for the 2pt conversion.";
                    return;
                }
                this.gameEventLog = this.gameEventLog + this.getEventPrefix() + " " + this.tdInfo + " " + team.getQB(0).name + "'s pass incomplete to " + team.getWR(0).name + " for the failed 2pt conversion.";
            }
        }
    }
    
    private int normalize(final int n) {
        return (n + 100) / 2;
    }
    
    private void passAttempt(final Team team, final PlayerWR playerWR, final int[] array, final int n) {
        final PlayerQB qb = team.getQB(0);
        ++qb.statsPassAtt;
        ++playerWR.statsTargets;
        if (this.gamePoss) {
            this.homeYards += n;
            final int[] homeQBStats = this.HomeQBStats;
            homeQBStats[4] += n;
            final int[] homeQBStats2 = this.HomeQBStats;
            ++homeQBStats2[1];
            array[2] += n;
            ++array[1];
            return;
        }
        this.awayYards += n;
        final int[] awayQBStats = this.AwayQBStats;
        awayQBStats[4] += n;
        final int[] awayQBStats2 = this.AwayQBStats;
        ++awayQBStats2[1];
        array[2] += n;
        ++array[1];
    }
    
    private void passCompletion(final Team team, final Team team2, final PlayerWR playerWR, final int[] array, final int n) {
        final PlayerQB qb = team.getQB(0);
        ++qb.statsPassComp;
        final PlayerQB qb2 = team.getQB(0);
        qb2.statsPassYards += n;
        ++playerWR.statsReceptions;
        playerWR.statsRecYards += n;
        team.teamPassYards += n;
        if (this.gamePoss) {
            final int[] homeQBStats = this.HomeQBStats;
            ++homeQBStats[0];
            ++array[0];
            return;
        }
        final int[] awayQBStats = this.AwayQBStats;
        ++awayQBStats[0];
        ++array[0];
    }
    
    private void passingPlay(final Team team, final Team team2) {
        final int n = 0;
        boolean b = false;
        final double n2 = Math.pow(team.getWR(0).ratOvr, 1.0) * Math.random();
        final double n3 = Math.pow(team.getWR(1).ratOvr, 1.0) * Math.random();
        final double n4 = Math.pow(team.getWR(2).ratOvr, 1.0) * Math.random();
        PlayerWR playerWR;
        PlayerCB playerCB;
        int[] array;
        if (n2 > n3 && n2 > n4) {
            playerWR = team.getWR(0);
            playerCB = team2.getCB(0);
            if (this.gamePoss) {
                array = this.HomeWR1Stats;
            }
            else {
                array = this.AwayWR1Stats;
            }
        }
        else if (n3 > n2 && n3 > n4) {
            playerWR = team.getWR(1);
            playerCB = team2.getCB(1);
            if (this.gamePoss) {
                array = this.HomeWR2Stats;
            }
            else {
                array = this.AwayWR2Stats;
            }
        }
        else {
            playerWR = team.getWR(2);
            playerCB = team2.getCB(2);
            if (this.gamePoss) {
                array = this.HomeWR3Stats;
            }
            else {
                array = this.AwayWR3Stats;
            }
        }
        final int n5 = team2.getCompositeF7Pass() * 2 - team.getCompositeOLPass() - this.getHFadv();
        if (Math.random() * 100.0 < n5 / 8) {
            this.qbSack(team);
            return;
        }
        double n6;
        if ((n6 = (team2.getS(0).ratOvr + n5 - (team.getQB(0).ratPassAcc + team.getQB(0).ratFootIQ + 100) / 3) / 22 + team.teamStratOff.getPAB() + team2.teamStratDef.getPAB()) < 0.01) {
            n6 = 0.01;
        }
        if (100.0 * Math.random() < n6) {
            this.qbInterception(team);
            return;
        }
        if (100.0 * Math.random() >= (this.getHFadv() + this.normalize(team.getQB(0).ratPassAcc) + this.normalize(playerWR.ratRecCat) - this.normalize(playerCB.ratCBCov)) / 1.9 + 20.0 - n5 / 16 - team.teamStratOff.getPAB() - team2.teamStratDef.getPAB()) {
            this.passAttempt(team, playerWR, array, 0);
            ++this.gameDown;
            this.gameTime -= (int)(15.0 * Math.random());
            return;
        }
        if (100.0 * Math.random() > (playerWR.ratRecCat + 185) / 3 || Math.random() < 0.02) {
            ++this.gameDown;
            ++array[4];
            ++playerWR.statsDrops;
            this.passAttempt(team, playerWR, array, 0);
            this.gameTime -= (int)(15.0 * Math.random());
            return;
        }
        final int n7 = (int)((this.normalize(team.getQB(0).ratPassPow) + this.normalize(playerWR.ratRecSpd) - this.normalize(playerCB.ratCBSpd)) * Math.random() / 3.7 + team.teamStratOff.getPYB() / 2 - team2.teamStratDef.getPYB());
        final double n8 = (this.normalize(playerWR.ratRecEva) * 3 - playerCB.ratCBTkl - team2.getS(0).ratOvr) * Math.random() + team.teamStratOff.getPYB() - team2.teamStratDef.getPAB();
        int n9 = 0;
        Label_0687: {
            if (n8 <= 92.0) {
                n9 = n7;
                if (Math.random() <= 0.95) {
                    break Label_0687;
                }
            }
            n9 = (int)(n7 + (3.0 + playerWR.ratRecSpd * Math.random() / 3.0));
        }
        int n10 = n9;
        if (n8 > 75.0) {
            n10 = n9;
            if (Math.random() < 0.1 + (team.teamStratOff.getPAB() - team2.teamStratDef.getPAB()) / 200) {
                n10 = n9 + 100;
            }
        }
        this.gameYardLine += n10;
        int n11;
        int n12;
        if (this.gameYardLine >= 100) {
            n11 = n10 - (this.gameYardLine - 100);
            this.gameYardLine = 100 - n11;
            this.addPointsQuarter(6);
            this.passingTD(team, playerWR, array, n11);
            n12 = 1;
        }
        else {
            final double n13 = (team2.getS(0).ratSTkl + playerCB.ratCBTkl) / 2;
            n11 = n10;
            n12 = n;
            if (100.0 * Math.random() < n13 / 50.0) {
                b = true;
                n11 = n10;
                n12 = n;
            }
        }
        if (n12 == 0 && !b) {
            this.gameYardsNeed -= n11;
            if (this.gameYardsNeed <= 0) {
                this.gameDown = 1;
                this.gameYardsNeed = 10;
            }
            else {
                ++this.gameDown;
            }
        }
        this.passCompletion(team, team2, playerWR, array, n11);
        this.passAttempt(team, playerWR, array, n11);
        if (b) {
            this.gameEventLog = this.gameEventLog + this.getEventPrefix() + "TURNOVER!\n" + team.abbr + " WR " + playerWR.name + " fumbled the ball after a catch.";
            ++array[5];
            ++playerWR.statsFumbles;
            if (this.gamePoss) {
                ++this.homeTOs;
            }
            else {
                ++this.awayTOs;
            }
            if (!this.playingOT) {
                this.gameDown = 1;
                this.gameYardsNeed = 10;
                this.gamePoss = !this.gamePoss;
                this.gameYardLine = 100 - this.gameYardLine;
                this.gameTime -= (int)(15.0 * Math.random());
                return;
            }
            this.resetForOT();
        }
        else {
            if (n12 == 0) {
                this.gameTime -= (int)(15.0 + 15.0 * Math.random());
                return;
            }
            this.gameTime -= (int)(15.0 * Math.random());
            this.kickXP(team, team2);
            if (!this.playingOT) {
                this.kickOff(team);
                return;
            }
            this.resetForOT();
        }
    }
    
    private void passingTD(final Team team, final PlayerWR playerWR, final int[] array, final int n) {
        if (this.gamePoss) {
            this.homeScore += 6;
            final int[] homeQBStats = this.HomeQBStats;
            ++homeQBStats[2];
            ++array[3];
        }
        else {
            this.awayScore += 6;
            final int[] awayQBStats = this.AwayQBStats;
            ++awayQBStats[2];
            ++array[3];
        }
        this.tdInfo = team.abbr + " QB " + team.getQB(0).name + " threw a " + n + " yard TD to " + playerWR.name + ".";
        final PlayerQB qb = team.getQB(0);
        ++qb.statsTD;
        ++playerWR.statsTD;
    }
    
    private void puntPlay(final Team team) {
        boolean gamePoss = true;
        this.gameYardLine = (int)(100.0 - (this.gameYardLine + team.getK(0).ratKickPow / 3 + 20 - 10.0 * Math.random()));
        if (this.gameYardLine < 0) {
            this.gameYardLine = 20;
        }
        this.gameDown = 1;
        this.gameYardsNeed = 10;
        if (this.gamePoss) {
            gamePoss = false;
        }
        this.gamePoss = gamePoss;
        this.gameTime -= (int)(20.0 + 15.0 * Math.random());
    }
    
    private void qbInterception(final Team team) {
        boolean gamePoss = true;
        if (this.gamePoss) {
            final int[] homeQBStats = this.HomeQBStats;
            ++homeQBStats[3];
            final int[] homeQBStats2 = this.HomeQBStats;
            ++homeQBStats2[1];
            ++this.homeTOs;
        }
        else {
            final int[] awayQBStats = this.AwayQBStats;
            ++awayQBStats[3];
            final int[] awayQBStats2 = this.AwayQBStats;
            ++awayQBStats2[1];
            ++this.awayTOs;
        }
        this.gameEventLog = this.gameEventLog + this.getEventPrefix() + "TURNOVER!\n" + team.abbr + " QB " + team.getQB(0).name + " was intercepted.";
        this.gameTime -= (int)(15.0 * Math.random());
        final PlayerQB qb = team.getQB(0);
        ++qb.statsInt;
        if (!this.playingOT) {
            this.gameDown = 1;
            this.gameYardsNeed = 10;
            if (this.gamePoss) {
                gamePoss = false;
            }
            this.gamePoss = gamePoss;
            this.gameYardLine = 100 - this.gameYardLine;
            return;
        }
        this.resetForOT();
    }
    
    private void qbSack(final Team team) {
        final PlayerQB qb = team.getQB(0);
        ++qb.statsSacked;
        this.gameYardsNeed += 3;
        this.gameYardLine -= 3;
        ++this.gameDown;
        if (this.gamePoss) {
            final int[] homeQBStats = this.HomeQBStats;
            ++homeQBStats[5];
        }
        else {
            final int[] awayQBStats = this.AwayQBStats;
            ++awayQBStats[5];
        }
        if (this.gameYardLine < 0) {
            this.gameTime -= (int)(Math.random() * 10.0);
            this.safety();
        }
        this.gameTime -= (int)(25.0 + Math.random() * 10.0);
    }
    
    private void resetForOT() {
        boolean gamePoss = false;
        if (this.bottomOT && this.homeScore == this.awayScore) {
            this.gameYardLine = 75;
            this.gameYardsNeed = 10;
            this.gameDown = 1;
            ++this.numOT;
            if (this.numOT % 2 == 0) {
                this.gamePoss = true;
            }
            else {
                this.gamePoss = false;
            }
            this.gameTime = -1;
            this.bottomOT = false;
            return;
        }
        if (!this.bottomOT) {
            if (!this.gamePoss) {
                gamePoss = true;
            }
            this.gamePoss = gamePoss;
            this.gameYardLine = 75;
            this.gameYardsNeed = 10;
            this.gameDown = 1;
            this.gameTime = -1;
            this.bottomOT = true;
            return;
        }
        this.playingOT = false;
    }
    
    private void runPlay(final Team team, final Team team2) {
        if (this.gameDown > 4) {
            if (!this.playingOT) {
                this.gameEventLog = this.gameEventLog + this.getEventPrefix() + "TURNOVER ON DOWNS!\n" + team.abbr + " failed to convert on " + (this.gameDown - 1) + "th down. " + team2.abbr + " takes over possession on downs.";
                this.gamePoss = !this.gamePoss;
                this.gameDown = 1;
                this.gameYardsNeed = 10;
                this.gameYardLine = 100 - this.gameYardLine;
                return;
            }
            this.gameEventLog = this.gameEventLog + this.getEventPrefix() + "TURNOVER ON DOWNS!\n" + team.abbr + " failed to convert on " + (this.gameDown - 1) + "th down in OT and their possession is over.";
            this.resetForOT();
        }
        else {
            final double n = team.getPassProf() * 2 - team2.getPassDef();
            final double random = Math.random();
            final double n2 = team.getRushProf() * 2 - team2.getRushDef();
            final double random2 = Math.random();
            final double n3 = team.teamStratOff.getRYB();
            if (this.gameDown == 1 && this.gameYardLine >= 91) {
                this.gameYardsNeed = 100 - this.gameYardLine;
            }
            if (this.gameTime <= 30 && !this.playingOT && ((this.gamePoss && this.awayScore >= this.homeScore) || (!this.gamePoss && this.homeScore >= this.awayScore))) {
                if (((this.gamePoss && this.awayScore - this.homeScore <= 3) || (!this.gamePoss && this.homeScore - this.awayScore <= 3)) && this.gameYardLine > 60) {
                    this.fieldGoalAtt(team, team2);
                    return;
                }
                this.passingPlay(team, team2);
            }
            else if (this.gameDown >= 4) {
                if (((this.gamePoss && this.awayScore - this.homeScore > 3) || (!this.gamePoss && this.homeScore - this.awayScore > 3)) && this.gameTime < 200) {
                    if (this.gameYardsNeed < 3) {
                        this.rushingPlay(team, team2);
                        return;
                    }
                    this.passingPlay(team, team2);
                }
                else if (this.gameYardsNeed < 3) {
                    if (this.gameYardLine > 65) {
                        this.fieldGoalAtt(team, team2);
                        return;
                    }
                    if (this.gameYardLine > 55) {
                        this.rushingPlay(team, team2);
                        return;
                    }
                    this.puntPlay(team);
                }
                else {
                    if (this.gameYardLine > 60) {
                        this.fieldGoalAtt(team, team2);
                        return;
                    }
                    this.puntPlay(team);
                }
            }
            else {
                if ((this.gameDown == 3 && this.gameYardsNeed > 4) || ((this.gameDown == 1 || this.gameDown == 2) && n * random - 10.0 >= n2 * random2 + n3)) {
                    this.passingPlay(team, team2);
                    return;
                }
                this.rushingPlay(team, team2);
            }
        }
    }
    
    private void rushAttempt(final Team team, final Team team2, final PlayerRB playerRB, final double n, final double n2, final int n3) {
        ++playerRB.statsRushAtt;
        playerRB.statsRushYards += n3;
        team.teamRushYards += n3;
        if (this.gamePoss) {
            this.homeYards += n3;
            if (n > n2) {
                final int[] homeRB1Stats = this.HomeRB1Stats;
                ++homeRB1Stats[0];
                final int[] homeRB1Stats2 = this.HomeRB1Stats;
                homeRB1Stats2[1] += n3;
                return;
            }
            final int[] homeRB2Stats = this.HomeRB2Stats;
            ++homeRB2Stats[0];
            final int[] homeRB2Stats2 = this.HomeRB2Stats;
            homeRB2Stats2[1] += n3;
        }
        else {
            this.awayYards += n3;
            if (n > n2) {
                final int[] awayRB1Stats = this.AwayRB1Stats;
                ++awayRB1Stats[0];
                final int[] awayRB1Stats2 = this.AwayRB1Stats;
                awayRB1Stats2[1] += n3;
                return;
            }
            final int[] awayRB2Stats = this.AwayRB2Stats;
            ++awayRB2Stats[0];
            final int[] awayRB2Stats2 = this.AwayRB2Stats;
            awayRB2Stats2[1] += n3;
        }
    }
    
    private void rushingPlay(final Team team, final Team team2) {
        boolean b = false;
        final double n = Math.pow(team.getRB(0).ratOvr, 1.5) * Math.random();
        final double n2 = Math.pow(team.getRB(1).ratOvr, 1.5) * Math.random();
        PlayerRB playerRB;
        if (n > n2) {
            playerRB = team.getRB(0);
        }
        else {
            playerRB = team.getRB(1);
        }
        final int n3 = (int)((playerRB.ratRushSpd + (team.getCompositeOLRush() - team2.getCompositeF7Rush()) + this.getHFadv()) * Math.random() / 12.0 + team.teamStratOff.getRYB() / 2.0 - team2.teamStratDef.getRYB() / 2.0);
        int n4;
        if (n3 < 2) {
            n4 = (int)(n3 + (playerRB.ratRushPow / 20 - 2 - team2.teamStratDef.getRYB() / 2.0));
        }
        else {
            n4 = n3;
            if (Math.random() < 0.2 + (team.teamStratOff.getRAB() - team2.teamStratDef.getRYB() / 2.0) / 50.0) {
                n4 = (int)(n3 + playerRB.ratRushEva / 6.0 * Math.random());
            }
        }
        this.gameYardLine += n4;
        int n5 = n4;
        if (this.gameYardLine >= 100) {
            this.addPointsQuarter(6);
            n5 = n4 - (this.gameYardLine - 100);
            this.gameYardLine = 100 - n5;
            if (this.gamePoss) {
                this.homeScore += 6;
                if (n > n2) {
                    final int[] homeRB1Stats = this.HomeRB1Stats;
                    ++homeRB1Stats[2];
                }
                else {
                    final int[] homeRB2Stats = this.HomeRB2Stats;
                    ++homeRB2Stats[2];
                }
            }
            else {
                this.awayScore += 6;
                if (n > n2) {
                    final int[] awayRB1Stats = this.AwayRB1Stats;
                    ++awayRB1Stats[2];
                }
                else {
                    final int[] awayRB2Stats = this.AwayRB2Stats;
                    ++awayRB2Stats[2];
                }
            }
            this.tdInfo = team.abbr + " RB " + playerRB.name + " rushed " + n5 + " yards for a TD.";
            ++playerRB.statsTD;
            b = true;
        }
        if (!b) {
            this.gameYardsNeed -= n5;
            if (this.gameYardsNeed <= 0) {
                this.gameDown = 1;
                this.gameYardsNeed = 10;
            }
            else {
                ++this.gameDown;
            }
        }
        this.rushAttempt(team, team2, playerRB, n, n2, n5);
        if (b) {
            this.gameTime -= (int)(5.0 + 15.0 * Math.random());
            this.kickXP(team, team2);
            if (this.playingOT) {
                this.resetForOT();
                return;
            }
            this.kickOff(team);
        }
        else {
            this.gameTime -= (int)(25.0 + 15.0 * Math.random());
            if (100.0 * Math.random() < ((team2.getS(0).ratSTkl + team2.getCompositeF7Rush() - this.getHFadv()) / 2 + team.teamStratOff.getRAB()) / 60.0) {
                if (this.gamePoss) {
                    ++this.homeTOs;
                    if (n > n2) {
                        final int[] homeRB1Stats2 = this.HomeRB1Stats;
                        ++homeRB1Stats2[3];
                    }
                    else {
                        final int[] homeRB2Stats2 = this.HomeRB2Stats;
                        ++homeRB2Stats2[3];
                    }
                }
                else {
                    ++this.awayTOs;
                    if (n > n2) {
                        final int[] awayRB1Stats2 = this.AwayRB1Stats;
                        ++awayRB1Stats2[3];
                    }
                    else {
                        final int[] awayRB2Stats2 = this.AwayRB2Stats;
                        ++awayRB2Stats2[3];
                    }
                }
                this.gameEventLog = this.gameEventLog + this.getEventPrefix() + "TURNOVER!\n" + team.abbr + " RB " + playerRB.name + " fumbled the ball while rushing.";
                ++playerRB.statsFumbles;
                if (!this.playingOT) {
                    this.gameDown = 1;
                    this.gameYardsNeed = 10;
                    this.gamePoss = !this.gamePoss;
                    this.gameYardLine = 100 - this.gameYardLine;
                    return;
                }
                this.resetForOT();
            }
        }
    }
    
    private void safety() {
        if (this.gamePoss) {
            this.awayScore += 2;
            this.gameEventLog = this.gameEventLog + this.getEventPrefix() + "SAFETY!\n" + this.homeTeam.abbr + " QB " + this.homeTeam.getQB(0).name + " was tackled in the endzone! Result is a safety and " + this.awayTeam.abbr + " will get possession.";
            this.freeKick(this.homeTeam);
            return;
        }
        this.homeScore += 2;
        this.gameEventLog = this.gameEventLog + this.getEventPrefix() + "SAFETY!\n" + this.awayTeam.abbr + " QB " + this.awayTeam.getQB(0).name + " was tackled in the endzone! Result is a safety and " + this.homeTeam.abbr + " will get possession.";
        this.freeKick(this.awayTeam);
    }
    
    public void addNewsStory() {
        if (this.numOT >= 3) {
            Team team;
            Team team2;
            int n;
            int n2;
            if (this.awayScore > this.homeScore) {
                team = this.awayTeam;
                team2 = this.homeTeam;
                n = this.awayScore;
                n2 = this.homeScore;
            }
            else {
                team = this.homeTeam;
                team2 = this.awayTeam;
                n = this.homeScore;
                n2 = this.awayScore;
            }
            this.homeTeam.league.newsStories.get(this.homeTeam.league.currentWeek + 1).add(this.numOT + "OT Thriller!>" + team.strRep() + " and " + team2.strRep() + " played an absolutely thrilling game that went to " + this.numOT + " overtimes, with " + team.name + " finally emerging victorious " + n + " to " + n2 + ".");
        }
        else {
            if (this.homeScore > this.awayScore && this.awayTeam.losses == 1 && this.awayTeam.league.currentWeek > 5) {
                this.awayTeam.league.newsStories.get(this.homeTeam.league.currentWeek + 1).add("Undefeated no more! " + this.awayTeam.name + " suffers first loss!>" + this.homeTeam.strRep() + " hands " + this.awayTeam.strRep() + " their first loss of the season, winning " + this.homeScore + " to " + this.awayScore + ".");
                return;
            }
            if (this.awayScore > this.homeScore && this.homeTeam.losses == 1 && this.homeTeam.league.currentWeek > 5) {
                this.homeTeam.league.newsStories.get(this.homeTeam.league.currentWeek + 1).add("Undefeated no more! " + this.homeTeam.name + " suffers first loss!>" + this.awayTeam.strRep() + " hands " + this.homeTeam.strRep() + " their first loss of the season, winning " + this.awayScore + " to " + this.homeScore + ".");
                return;
            }
            if (this.awayScore > this.homeScore && this.homeTeam.rankTeamPollScore < 20 && this.awayTeam.rankTeamPollScore - this.homeTeam.rankTeamPollScore > 20) {
                this.awayTeam.league.newsStories.get(this.awayTeam.league.currentWeek + 1).add("Upset! " + this.awayTeam.strRep() + " beats " + this.homeTeam.strRep() + ">#" + this.awayTeam.rankTeamPollScore + " " + this.awayTeam.name + " was able to pull off the upset on the road against #" + this.homeTeam.rankTeamPollScore + " " + this.homeTeam.name + ", winning " + this.awayScore + " to " + this.homeScore + ".");
                return;
            }
            if (this.homeScore > this.awayScore && this.awayTeam.rankTeamPollScore < 20 && this.homeTeam.rankTeamPollScore - this.awayTeam.rankTeamPollScore > 20) {
                this.homeTeam.league.newsStories.get(this.homeTeam.league.currentWeek + 1).add("Upset! " + this.homeTeam.strRep() + " beats " + this.awayTeam.strRep() + ">#" + this.homeTeam.rankTeamPollScore + " " + this.homeTeam.name + " was able to pull off the upset at home against #" + this.awayTeam.rankTeamPollScore + " " + this.awayTeam.name + ", winning " + this.homeScore + " to " + this.awayScore + ".");
            }
        }
    }
    
    public String getCSV() {
        return this.homeTeam.abbr + "," + this.awayTeam.abbr + "," + this.homeScore + "," + this.awayScore + "," + this.gameName;
    }
    
    public String[] getGameScoutStr() {
        if (this.isOnline) {
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            final StringBuilder sb3 = new StringBuilder();
            sb.append("\nELO\nWins\nLosses\nPPG\nOpp PPG\nYPG\nOpp YPG\n\nPass YPG\nRush YPG\nOpp PYPG\nOpp RYPG\n\nOff Talent\nDef Talent");
            final Team awayTeam = this.awayTeam;
            sb2.append(awayTeam.abbr + "\n" + awayTeam.teamELO + "\n" + awayTeam.wins + "\n" + awayTeam.losses + "\n" + awayTeam.getPPG() + "\n" + awayTeam.getOPPG() + "\n" + awayTeam.getYPG() + "\n" + awayTeam.getOYPG() + "\n\n" + awayTeam.getPYPG() + "\n" + awayTeam.getRYPG() + "\n" + awayTeam.getOPYPG() + "\n" + awayTeam.getORYPG() + "\n\n" + awayTeam.getOffTalent() + "\n" + awayTeam.getDefTalent());
            final Team homeTeam = this.homeTeam;
            sb3.append(homeTeam.abbr + "\n" + homeTeam.teamELO + "\n" + homeTeam.wins + "\n" + homeTeam.losses + "\n" + homeTeam.getPPG() + "\n" + homeTeam.getOPPG() + "\n" + homeTeam.getYPG() + "\n" + homeTeam.getOYPG() + "\n\n" + homeTeam.getPYPG() + "\n" + homeTeam.getRYPG() + "\n" + homeTeam.getOPYPG() + "\n" + homeTeam.getORYPG() + "\n\n" + homeTeam.getOffTalent() + "\n" + homeTeam.getDefTalent());
            return new String[] { sb.toString(), sb2.toString(), sb3.toString(), "" };
        }
        if (this.hasData) {
            final StringBuilder sb4 = new StringBuilder();
            final StringBuilder sb5 = new StringBuilder();
            final StringBuilder sb6 = new StringBuilder();
            sb4.append("Ranking\nRecord\nPPG\nOpp PPG\nYPG\nOpp YPG\n\nPass YPG\nRush YPG\nOpp PYPG\nOpp RYPG\n\nOff Talent\nDef Talent");
            final int numGames = this.awayTeam.numGames();
            final Team awayTeam2 = this.awayTeam;
            sb5.append("#" + awayTeam2.rankTeamPollScore + " " + awayTeam2.abbr + "\n" + awayTeam2.wins + "-" + awayTeam2.losses + "\n" + awayTeam2.teamPoints / numGames + " (" + awayTeam2.rankTeamPoints + ")\n" + awayTeam2.teamOppPoints / numGames + " (" + awayTeam2.rankTeamOppPoints + ")\n" + awayTeam2.teamYards / numGames + " (" + awayTeam2.rankTeamYards + ")\n" + awayTeam2.teamOppYards / numGames + " (" + awayTeam2.rankTeamOppYards + ")\n\n" + awayTeam2.teamPassYards / numGames + " (" + awayTeam2.rankTeamPassYards + ")\n" + awayTeam2.teamRushYards / numGames + " (" + awayTeam2.rankTeamRushYards + ")\n" + awayTeam2.teamOppPassYards / numGames + " (" + awayTeam2.rankTeamOppPassYards + ")\n" + awayTeam2.teamOppRushYards / numGames + " (" + awayTeam2.rankTeamOppRushYards + ")\n\n" + awayTeam2.teamOffTalent + " (" + awayTeam2.rankTeamOffTalent + ")\n" + awayTeam2.teamDefTalent + " (" + awayTeam2.rankTeamDefTalent + ")\n");
            final int numGames2 = this.homeTeam.numGames();
            final Team homeTeam2 = this.homeTeam;
            sb6.append("#" + homeTeam2.rankTeamPollScore + " " + homeTeam2.abbr + "\n" + homeTeam2.wins + "-" + homeTeam2.losses + "\n" + homeTeam2.teamPoints / numGames2 + " (" + homeTeam2.rankTeamPoints + ")\n" + homeTeam2.teamOppPoints / numGames2 + " (" + homeTeam2.rankTeamOppPoints + ")\n" + homeTeam2.teamYards / numGames2 + " (" + homeTeam2.rankTeamYards + ")\n" + homeTeam2.teamOppYards / numGames2 + " (" + homeTeam2.rankTeamOppYards + ")\n\n" + homeTeam2.teamPassYards / numGames2 + " (" + homeTeam2.rankTeamPassYards + ")\n" + homeTeam2.teamRushYards / numGames2 + " (" + homeTeam2.rankTeamRushYards + ")\n" + homeTeam2.teamOppPassYards / numGames2 + " (" + homeTeam2.rankTeamOppPassYards + ")\n" + homeTeam2.teamOppRushYards / numGames2 + " (" + homeTeam2.rankTeamOppRushYards + ")\n\n" + homeTeam2.teamOffTalent + " (" + homeTeam2.rankTeamOffTalent + ")\n" + homeTeam2.teamDefTalent + " (" + homeTeam2.rankTeamDefTalent + ")\n");
            final String string = sb4.toString();
            final String string2 = sb5.toString();
            final String string3 = sb6.toString();
            final StringBuilder sb7 = new StringBuilder();
            if (this.awayTeam.playersInjuredAll != null && !this.awayTeam.playersInjuredAll.isEmpty()) {
                Collections.sort(this.awayTeam.playersInjuredAll, new PlayerPositionComparator());
                sb7.append("\n" + this.awayTeam.abbr + " Injury Report:\n");
                final Iterator<Player> iterator = this.awayTeam.playersInjuredAll.iterator();
                while (iterator.hasNext()) {
                    sb7.append(iterator.next().getPosNameYrOvrPot_OneLine() + "\n");
                }
            }
            if (this.homeTeam.playersInjuredAll != null && !this.homeTeam.playersInjuredAll.isEmpty()) {
                Collections.sort(this.homeTeam.playersInjuredAll, new PlayerPositionComparator());
                sb7.append("\n" + this.homeTeam.abbr + " Injury Report:\n");
                final Iterator<Player> iterator2 = this.homeTeam.playersInjuredAll.iterator();
                while (iterator2.hasNext()) {
                    sb7.append(iterator2.next().getPosNameYrOvrPot_OneLine() + "\n");
                }
            }
            return new String[] { string, string2, string3, sb7.toString() };
        }
        return new String[] { "No data", "No data", "No data", "No data" };
    }
    
    public String[] getGameSummaryStr() {
        if (this.hasData) {
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            final StringBuilder sb3 = new StringBuilder();
            sb.append("\nPoints\nYards\nPass Yards\nRush Yards\nTOs\n\n");
            sb2.append(this.awayTeam.getStrAbbrWL() + "\n" + this.awayScore + "\n" + this.awayYards + " yds\n" + this.getPassYards(true) + " pyds\n" + this.getRushYards(true) + " ryds\n" + this.awayTOs + " TOs\n\n");
            sb3.append(this.homeTeam.getStrAbbrWL() + "\n" + this.homeScore + "\n" + this.homeYards + " yds\n" + this.getPassYards(false) + " pyds\n" + this.getRushYards(false) + " ryds\n" + this.homeTOs + " TOs\n\n");
            sb.append("QBs\nName\nYr Ovr/Pot\nTD/Int\nPass Yards\nComp/Att\n");
            sb2.append(this.awayTeam.abbr + "\n" + this.awayQB.getInitialName() + "\n");
            sb2.append(this.awayQB.age + " " + this.awayQB.ratOvr + "/" + Player.getLetterGrade(this.awayQB.ratPot) + "\n");
            sb2.append(this.AwayQBStats[2] + "/" + this.AwayQBStats[3] + "\n");
            sb2.append(this.AwayQBStats[4] + " yds\n");
            sb2.append(this.AwayQBStats[0] + "/" + this.AwayQBStats[1] + "\n");
            sb3.append(this.homeTeam.abbr + "\n" + this.homeQB.getInitialName() + "\n");
            sb3.append(this.homeQB.age + " " + this.homeQB.ratOvr + "/" + Player.getLetterGrade(this.homeQB.ratPot) + "\n");
            sb3.append(this.HomeQBStats[2] + "/" + this.HomeQBStats[3] + "\n");
            sb3.append(this.HomeQBStats[4] + " yds\n");
            sb3.append(this.HomeQBStats[0] + "/" + this.HomeQBStats[1] + "\n");
            sb.append("\nRBs\nRB1 Name\nYr Ovr/Pot\nTD/Fum\nRush Yards\nYds/Att\n");
            sb2.append("\n" + this.awayTeam.abbr + "\n" + this.awayRBs[0].getInitialName() + "\n");
            sb2.append(this.awayRBs[0].age + " " + this.awayRBs[0].ratOvr + "/" + Player.getLetterGrade(this.awayRBs[0].ratPot) + "\n");
            sb2.append(this.AwayRB1Stats[2] + "/" + this.AwayRB1Stats[3] + "\n");
            sb2.append(this.AwayRB1Stats[1] + " yds\n");
            sb2.append(this.AwayRB1Stats[1] * 10 / this.AwayRB1Stats[0] / 10.0 + "\n");
            sb3.append("\n" + this.homeTeam.abbr + "\n" + this.homeRBs[0].getInitialName() + "\n");
            sb3.append(this.homeRBs[0].age + " " + this.homeRBs[0].ratOvr + "/" + Player.getLetterGrade(this.homeRBs[0].ratPot) + "\n");
            sb3.append(this.HomeRB1Stats[2] + "/" + this.HomeRB1Stats[3] + "\n");
            sb3.append(this.HomeRB1Stats[1] + " yds\n");
            sb3.append(this.HomeRB1Stats[1] * 10 / this.HomeRB1Stats[0] / 10.0 + "\n");
            sb.append("\n");
            sb2.append("\n");
            sb3.append("\n");
            sb.append("RB2 Name\nYr Ovr/Pot\nTD/Fum\nRush Yards\nYds/Att\n");
            sb2.append(this.awayRBs[1].getInitialName() + "\n");
            sb2.append(this.awayRBs[1].age + " " + this.awayRBs[1].ratOvr + "/" + Player.getLetterGrade(this.awayRBs[1].ratPot) + "\n");
            sb2.append(this.AwayRB2Stats[2] + "/" + this.AwayRB2Stats[3] + "\n");
            sb2.append(this.AwayRB2Stats[1] + " yds\n");
            sb2.append(this.AwayRB2Stats[1] * 10 / this.AwayRB2Stats[0] / 10.0 + "\n");
            sb3.append(this.homeRBs[1].getInitialName() + "\n");
            sb3.append(this.homeRBs[1].age + " " + this.homeRBs[1].ratOvr + "/" + Player.getLetterGrade(this.homeRBs[1].ratPot) + "\n");
            sb3.append(this.HomeRB2Stats[2] + "/" + this.HomeRB2Stats[3] + "\n");
            sb3.append(this.HomeRB2Stats[1] + " yds\n");
            sb3.append(this.HomeRB2Stats[1] * 10 / this.HomeRB2Stats[0] / 10.0 + "\n");
            sb.append("\nWRs\nWR1 Name\nYr Ovr/Pot\nTD/Fum\nRec Yards\nRec/Tgts\n");
            sb2.append("\n" + this.awayTeam.abbr + "\n" + this.awayWRs[0].getInitialName() + "\n");
            sb2.append(this.awayWRs[0].age + " " + this.awayWRs[0].ratOvr + "/" + Player.getLetterGrade(this.awayWRs[0].ratPot) + "\n");
            sb2.append(this.AwayWR1Stats[3] + "/" + this.AwayWR1Stats[5] + "\n");
            sb2.append(this.AwayWR1Stats[2] + " yds\n");
            sb2.append(this.AwayWR1Stats[0] + "/" + this.AwayWR1Stats[1] + "\n");
            sb3.append("\n" + this.homeTeam.abbr + "\n" + this.homeWRs[0].getInitialName() + "\n");
            sb3.append(this.homeWRs[0].age + " " + this.homeWRs[0].ratOvr + "/" + Player.getLetterGrade(this.homeWRs[0].ratPot) + "\n");
            sb3.append(this.HomeWR1Stats[3] + "/" + this.HomeWR1Stats[5] + "\n");
            sb3.append(this.HomeWR1Stats[2] + " yds\n");
            sb3.append(this.HomeWR1Stats[0] + "/" + this.HomeWR1Stats[1] + "\n");
            sb.append("\n");
            sb2.append("\n");
            sb3.append("\n");
            sb.append("WR2 Name\nYr Ovr/Pot\nTD/Fum\nRec Yards\nRec/Tgts\n");
            sb2.append(this.awayWRs[1].getInitialName() + "\n");
            sb2.append(this.awayWRs[1].age + " " + this.awayWRs[1].ratOvr + "/" + Player.getLetterGrade(this.awayWRs[1].ratPot) + "\n");
            sb2.append(this.AwayWR2Stats[3] + "/" + this.AwayWR2Stats[5] + "\n");
            sb2.append(this.AwayWR2Stats[2] + " yds\n");
            sb2.append(this.AwayWR2Stats[0] + "/" + this.AwayWR2Stats[1] + "\n");
            sb3.append(this.homeWRs[1].getInitialName() + "\n");
            sb3.append(this.homeWRs[1].age + " " + this.homeWRs[1].ratOvr + "/" + Player.getLetterGrade(this.homeWRs[1].ratPot) + "\n");
            sb3.append(this.HomeWR2Stats[3] + "/" + this.HomeWR2Stats[5] + "\n");
            sb3.append(this.HomeWR2Stats[2] + " yds\n");
            sb3.append(this.HomeWR2Stats[0] + "/" + this.HomeWR2Stats[1] + "\n");
            sb.append("\n");
            sb2.append("\n");
            sb3.append("\n");
            sb.append("WR3 Name\nYr Ovr/Pot\nTD/Fum\nRec Yards\nRec/Tgts\n");
            sb2.append(this.awayWRs[2].getInitialName() + "\n");
            sb2.append(this.awayWRs[2].age + " " + this.awayWRs[2].ratOvr + "/" + Player.getLetterGrade(this.awayWRs[2].ratPot) + "\n");
            sb2.append(this.AwayWR3Stats[3] + "/" + this.AwayWR3Stats[5] + "\n");
            sb2.append(this.AwayWR3Stats[2] + " yds\n");
            sb2.append(this.AwayWR3Stats[0] + "/" + this.AwayWR3Stats[1] + "\n");
            sb3.append(this.homeWRs[2].getInitialName() + "\n");
            sb3.append(this.homeWRs[2].age + " " + this.homeWRs[2].ratOvr + "/" + Player.getLetterGrade(this.homeWRs[2].ratPot) + "\n");
            sb3.append(this.HomeWR3Stats[3] + "/" + this.HomeWR3Stats[5] + "\n");
            sb3.append(this.HomeWR3Stats[2] + " yds\n");
            sb3.append(this.HomeWR3Stats[0] + "/" + this.HomeWR3Stats[1] + "\n");
            sb.append("\nKs\nName\nYr Ovr/Pot\nFGM/FGA\nXPM/XPA\n");
            sb2.append("\n" + this.awayTeam.abbr + "\n" + this.awayK.getInitialName() + "\n");
            sb2.append(this.awayK.age + " " + this.awayK.ratOvr + "/" + Player.getLetterGrade(this.awayK.ratPot) + "\n");
            sb2.append(this.AwayKStats[2] + "/" + this.AwayKStats[3] + " FG\n" + this.AwayKStats[0] + "/" + this.AwayKStats[1] + " XP\n");
            sb3.append("\n" + this.homeTeam.abbr + "\n" + this.homeK.getInitialName() + "\n");
            sb3.append(this.homeK.age + " " + this.homeK.ratOvr + "/" + Player.getLetterGrade(this.homeK.ratPot) + "\n");
            sb3.append(this.HomeKStats[2] + "/" + this.HomeKStats[3] + " FG\n" + this.HomeKStats[0] + "/" + this.HomeKStats[1] + " XP\n");
            return new String[] { sb.toString(), sb2.toString(), sb3.toString(), this.gameEventLog };
        }
        return new String[] { " ", " ", " ", "Game details are not saved between play sessions." };
    }
    
    public JSONObject getJSONGameResult(final boolean b) throws JSONException {
        if (b) {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", (Object)this.homeTeam.name);
            jsonObject.put("elo", this.homeELO);
            if (this.homeScore > this.awayScore) {
                jsonObject.put("wins", 1);
                jsonObject.put("losses", 0);
            }
            else {
                jsonObject.put("wins", 0);
                jsonObject.put("losses", 1);
            }
            jsonObject.put("points", this.homeScore);
            jsonObject.put("opp points", this.awayScore);
            jsonObject.put("yards", this.homeYards);
            jsonObject.put("opp yards", this.awayYards);
            jsonObject.put("pass yards", this.getPassYards(false));
            jsonObject.put("rush yards", this.getRushYards(false));
            jsonObject.put("opp pass yards", this.getPassYards(true));
            jsonObject.put("opp rush yards", this.getRushYards(true));
            jsonObject.put("to diff", this.awayTOs - this.homeTOs);
            return jsonObject;
        }
        final JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", (Object)this.awayTeam.name);
        jsonObject2.put("elo", this.awayELO);
        if (this.homeScore < this.awayScore) {
            jsonObject2.put("wins", 1);
            jsonObject2.put("losses", 0);
        }
        else {
            jsonObject2.put("wins", 0);
            jsonObject2.put("losses", 1);
        }
        jsonObject2.put("points", this.awayScore);
        jsonObject2.put("opp points", this.homeScore);
        jsonObject2.put("yards", this.awayYards);
        jsonObject2.put("opp yards", this.homeYards);
        jsonObject2.put("pass yards", this.getPassYards(true));
        jsonObject2.put("rush yards", this.getRushYards(true));
        jsonObject2.put("opp pass yards", this.getPassYards(false));
        jsonObject2.put("opp rush yards", this.getRushYards(false));
        jsonObject2.put("to diff", this.homeTOs - this.awayTOs);
        return jsonObject2;
    }
    
    public Team getLoser() {
        if (this.homeScore < this.awayScore) {
            return this.homeTeam;
        }
        return this.awayTeam;
    }
    
    public int getPassYards(final boolean b) {
        if (!b) {
            return this.HomeQBStats[4];
        }
        return this.AwayQBStats[4];
    }
    
    public int getRushYards(final boolean b) {
        if (!b) {
            return this.HomeRB1Stats[1] + this.HomeRB2Stats[1];
        }
        return this.AwayRB1Stats[1] + this.AwayRB2Stats[1];
    }
    
    public Team getWinner() {
        if (this.homeScore > this.awayScore) {
            return this.homeTeam;
        }
        return this.awayTeam;
    }
    
    public void playGame() {
        if (!this.hasPlayed) {
            this.gameEventLog = "LOG: #" + this.awayTeam.rankTeamPollScore + " " + this.awayTeam.abbr + " (" + this.awayTeam.wins + "-" + this.awayTeam.losses + ") @ #" + this.homeTeam.rankTeamPollScore + " " + this.homeTeam.abbr + " (" + this.homeTeam.wins + "-" + this.homeTeam.losses + ")\n---------------------------------------------------------\n\n" + this.awayTeam.abbr + " Off Strategy: " + this.awayTeam.teamStratOff.getStratName() + "\n" + this.awayTeam.abbr + " Def Strategy: " + this.awayTeam.teamStratDef.getStratName() + "\n" + this.homeTeam.abbr + " Off Strategy: " + this.homeTeam.teamStratOff.getStratName() + "\n" + this.homeTeam.abbr + " Def Strategy: " + this.homeTeam.teamStratDef.getStratName() + "\n";
            this.gameTime = 3600;
            this.gameDown = 1;
            this.gamePoss = true;
            this.gameYardsNeed = 10;
            this.gameYardLine = 20;
            while (this.gameTime > 0) {
                if (this.gamePoss) {
                    this.runPlay(this.homeTeam, this.awayTeam);
                }
                else {
                    this.runPlay(this.awayTeam, this.homeTeam);
                }
                if (this.gameTime <= 0 && this.homeScore == this.awayScore) {
                    this.gameEventLog = this.gameEventLog + this.getEventPrefix() + "OVERTIME!\nTie game at 0:00, overtime begins!";
                    this.gamePoss = true;
                    this.gameTime = 900;
                    this.gameDown = 1;
                    this.gameYardsNeed = 10;
                    this.gameYardLine = 20;
                    ++this.numOT;
                }
            }
            this.gameEventLog = this.gameEventLog + this.getEventPrefix() + "Time has expired! The game is over.";
            if (this.homeScore > this.awayScore) {
                this.winner = this.homeTeam;
                final Team homeTeam = this.homeTeam;
                ++homeTeam.wins;
                final Team homeTeam2 = this.homeTeam;
                ++homeTeam2.totalWins;
                this.homeTeam.gameWLSchedule.add("W");
                final Team awayTeam = this.awayTeam;
                ++awayTeam.losses;
                final Team awayTeam2 = this.awayTeam;
                ++awayTeam2.totalLosses;
                this.awayTeam.gameWLSchedule.add("L");
                this.homeTeam.gameWinsAgainst.add(this.awayTeam);
                if (!this.isOnline) {
                    this.homeTeam.winStreak.addWin(this.homeTeam.league.getYear());
                    this.homeTeam.league.checkLongestWinStreak(this.homeTeam.winStreak);
                    this.awayTeam.winStreak.resetStreak(this.awayTeam.league.getYear());
                }
            }
            else {
                this.winner = this.awayTeam;
                final Team homeTeam3 = this.homeTeam;
                ++homeTeam3.losses;
                final Team homeTeam4 = this.homeTeam;
                ++homeTeam4.totalLosses;
                this.homeTeam.gameWLSchedule.add("L");
                final Team awayTeam3 = this.awayTeam;
                ++awayTeam3.wins;
                final Team awayTeam4 = this.awayTeam;
                ++awayTeam4.totalWins;
                this.awayTeam.gameWLSchedule.add("W");
                this.awayTeam.gameWinsAgainst.add(this.homeTeam);
                if (!this.isOnline) {
                    this.awayTeam.winStreak.addWin(this.awayTeam.league.getYear());
                    this.awayTeam.league.checkLongestWinStreak(this.awayTeam.winStreak);
                    this.homeTeam.winStreak.resetStreak(this.homeTeam.league.getYear());
                }
            }
            this.homeTeam.addGamePlayedPlayers(this.homeScore > this.awayScore);
            this.awayTeam.addGamePlayedPlayers(this.awayScore > this.homeScore);
            final Team homeTeam5 = this.homeTeam;
            homeTeam5.teamPoints += this.homeScore;
            final Team awayTeam5 = this.awayTeam;
            awayTeam5.teamPoints += this.awayScore;
            final Team homeTeam6 = this.homeTeam;
            homeTeam6.teamOppPoints += this.awayScore;
            final Team awayTeam6 = this.awayTeam;
            awayTeam6.teamOppPoints += this.homeScore;
            final Team homeTeam7 = this.homeTeam;
            homeTeam7.teamYards += this.homeYards;
            final Team awayTeam7 = this.awayTeam;
            awayTeam7.teamYards += this.awayYards;
            final Team homeTeam8 = this.homeTeam;
            homeTeam8.teamOppYards += this.awayYards;
            final Team awayTeam8 = this.awayTeam;
            awayTeam8.teamOppYards += this.homeYards;
            final Team homeTeam9 = this.homeTeam;
            homeTeam9.teamOppPassYards += this.getPassYards(true);
            final Team awayTeam9 = this.awayTeam;
            awayTeam9.teamOppPassYards += this.getPassYards(false);
            final Team homeTeam10 = this.homeTeam;
            homeTeam10.teamOppRushYards += this.getRushYards(true);
            final Team awayTeam10 = this.awayTeam;
            awayTeam10.teamOppRushYards += this.getRushYards(false);
            final Team homeTeam11 = this.homeTeam;
            homeTeam11.teamTODiff += this.awayTOs - this.homeTOs;
            final Team awayTeam11 = this.awayTeam;
            awayTeam11.teamTODiff += this.homeTOs - this.awayTOs;
            this.hasPlayed = true;
            this.homeQB = this.homeTeam.getQB(0);
            this.homeRBs = new PlayerRB[2];
            for (int i = 0; i < 2; ++i) {
                this.homeRBs[i] = this.homeTeam.getRB(i);
            }
            this.homeWRs = new PlayerWR[3];
            for (int j = 0; j < 3; ++j) {
                this.homeWRs[j] = this.homeTeam.getWR(j);
            }
            this.homeK = this.homeTeam.getK(0);
            this.awayQB = this.awayTeam.getQB(0);
            this.awayRBs = new PlayerRB[2];
            for (int k = 0; k < 2; ++k) {
                this.awayRBs[k] = this.awayTeam.getRB(k);
            }
            this.awayWRs = new PlayerWR[3];
            for (int l = 0; l < 3; ++l) {
                this.awayWRs[l] = this.awayTeam.getWR(l);
            }
            this.awayK = this.awayTeam.getK(0);
            if (!this.isOnline) {
                this.homeTeam.checkForInjury();
                this.awayTeam.checkForInjury();
            }
            else {
                if (this.affectsELO) {
                    final double pow = Math.pow(10.0, this.homeTeam.teamELO / 400.0);
                    final double pow2 = Math.pow(10.0, this.awayTeam.teamELO / 400.0);
                    final double n = pow / (pow + pow2);
                    final double n2 = pow2 / (pow + pow2);
                    boolean b;
                    boolean b2;
                    if (this.homeScore > this.awayScore) {
                        b = true;
                        b2 = false;
                    }
                    else {
                        b = false;
                        b2 = true;
                    }
                    System.out.println("Home change: " + 25.0 * ((b ? 1 : 0) - n));
                    System.out.println("Away change: " + 25.0 * ((b2 ? 1 : 0) - n2));
                    this.homeELO = (int)(30.0 * ((b ? 1 : 0) - n));
                    this.awayELO = (int)(30.0 * ((b2 ? 1 : 0) - n2));
                    final Team homeTeam12 = this.homeTeam;
                    homeTeam12.teamELO += this.homeELO;
                    final Team awayTeam12 = this.awayTeam;
                    awayTeam12.teamELO += this.awayELO;
                    return;
                }
                this.homeELO = 0;
                this.awayELO = 0;
            }
        }
    }
}
