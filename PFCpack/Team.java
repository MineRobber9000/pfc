// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class Team
{
    private final int RETIREMENT_AGE;
    private final double RETIREMENT_CHANCE;
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
    
    public Team(final String s, final League league) {
        this.RETIREMENT_AGE = 33;
        this.RETIREMENT_CHANCE = 0.35;
        this.league = league;
        this.userControlled = false;
        this.showPopups = true;
        this.teamHistory = new ArrayList<String>();
        this.playersInjuredAll = new ArrayList<Player>();
        this.playersInjured = new ArrayList<Player>();
        this.playersRecovered = new ArrayList<Player>();
        this.tradingBlock = new ArrayList<TradePiece>();
        this.teamQBs = new ArrayList<PlayerQB>();
        this.teamRBs = new ArrayList<PlayerRB>();
        this.teamWRs = new ArrayList<PlayerWR>();
        this.teamKs = new ArrayList<PlayerK>();
        this.teamOLs = new ArrayList<PlayerOL>();
        this.teamDLs = new ArrayList<PlayerDL>();
        this.teamLBs = new ArrayList<PlayerLB>();
        this.teamSs = new ArrayList<PlayerS>();
        this.teamCBs = new ArrayList<PlayerCB>();
        this.gameSchedule = new ArrayList<Game>();
        this.regSeasonSchedule = new Game[16];
        this.gameWinsAgainst = new ArrayList<Team>();
        this.gameWLSchedule = new ArrayList<String>();
        this.divChampion = "";
        this.natChampWL = "";
        this.totalDivChamps = 0;
        this.totalPlayoffWins = 0;
        this.totalPlayoffLosses = 0;
        this.totalSuperBowlLosses = 0;
        this.totalSuperBowlWins = 0;
        this.teamPoints = 0;
        this.teamOppPoints = 0;
        this.teamYards = 0;
        this.teamOppYards = 0;
        this.teamPassYards = 0;
        this.teamRushYards = 0;
        this.teamOppPassYards = 0;
        this.teamOppRushYards = 0;
        this.teamTODiff = 0;
        this.teamOffTalent = 0;
        this.teamDefTalent = 0;
        this.teamPollScore = 0;
        this.teamStratOffNum = 1;
        this.teamStratDefNum = 1;
        final String[] split = s.split("%");
        this.setVarsFromCSV(split[0]);
        this.playersRetiring = new ArrayList<Player>();
        this.playersFAs = new ArrayList<Player>();
        for (int i = 1; i < split.length; ++i) {
            if (split[i].substring(0, 4).equals("[FA]")) {
                this.addFAPlayerCSV(split[i]);
            }
            else {
                this.recruitPlayerCSV(split[i]);
            }
        }
        this.sortPlayers();
        this.getPlayersInjuredAll();
        this.teamStratOff = this.getTeamStrategiesOff()[this.teamStratOffNum];
        this.teamStratDef = this.getTeamStrategiesDef()[this.teamStratDefNum];
        this.numRecruits = 30;
    }
    
    public Team(final String name, final String abbr, final String division, final League league) {
        this.RETIREMENT_AGE = 33;
        this.RETIREMENT_CHANCE = 0.35;
        this.league = league;
        this.userControlled = false;
        this.showPopups = true;
        this.teamHistory = new ArrayList<String>();
        this.playersInjuredAll = new ArrayList<Player>();
        this.playersInjured = new ArrayList<Player>();
        this.playersRecovered = new ArrayList<Player>();
        this.tradingBlock = new ArrayList<TradePiece>();
        this.teamQBs = new ArrayList<PlayerQB>();
        this.teamRBs = new ArrayList<PlayerRB>();
        this.teamWRs = new ArrayList<PlayerWR>();
        this.teamKs = new ArrayList<PlayerK>();
        this.teamOLs = new ArrayList<PlayerOL>();
        this.teamDLs = new ArrayList<PlayerDL>();
        this.teamLBs = new ArrayList<PlayerLB>();
        this.teamSs = new ArrayList<PlayerS>();
        this.teamCBs = new ArrayList<PlayerCB>();
        this.gameSchedule = new ArrayList<Game>();
        this.regSeasonSchedule = new Game[16];
        this.gameWinsAgainst = new ArrayList<Team>();
        this.gameWLSchedule = new ArrayList<String>();
        this.divChampion = "";
        this.natChampWL = "";
        this.name = name;
        this.abbr = abbr;
        this.division = division;
    }
    
    public Team(final JSONObject jsonObject) throws JSONException {
        int i = 0;
        this.RETIREMENT_AGE = 33;
        this.RETIREMENT_CHANCE = 0.35;
        this.name = jsonObject.get("name").toString();
        this.abbr = jsonObject.get("abbr").toString();
        this.teamStratOffNum = Integer.parseInt(jsonObject.get("off_strat").toString());
        this.teamStratDefNum = Integer.parseInt(jsonObject.get("def_strat").toString());
        this.teamELO = Integer.parseInt(jsonObject.get("elo").toString());
        this.wins = Integer.parseInt(jsonObject.get("wins").toString());
        this.losses = Integer.parseInt(jsonObject.get("losses").toString());
        this.teamPoints = Integer.parseInt(jsonObject.get("points").toString());
        this.teamOppPoints = Integer.parseInt(jsonObject.get("opp_points").toString());
        this.teamYards = Integer.parseInt(jsonObject.get("yards").toString());
        this.teamOppYards = Integer.parseInt(jsonObject.get("opp_yards").toString());
        this.teamPassYards = Integer.parseInt(jsonObject.get("pass_yards").toString());
        this.teamRushYards = Integer.parseInt(jsonObject.get("rush_yards").toString());
        this.teamOppPassYards = Integer.parseInt(jsonObject.get("opp_pass_yards").toString());
        this.teamOppRushYards = Integer.parseInt(jsonObject.get("opp_rush_yards").toString());
        this.teamTODiff = Integer.parseInt(jsonObject.get("to_diff").toString());
        this.teamQBs = new ArrayList<PlayerQB>();
        this.teamRBs = new ArrayList<PlayerRB>();
        this.teamWRs = new ArrayList<PlayerWR>();
        this.teamKs = new ArrayList<PlayerK>();
        this.teamOLs = new ArrayList<PlayerOL>();
        this.teamDLs = new ArrayList<PlayerDL>();
        this.teamLBs = new ArrayList<PlayerLB>();
        this.teamSs = new ArrayList<PlayerS>();
        this.teamCBs = new ArrayList<PlayerCB>();
        this.division = "XXX";
        this.gameSchedule = new ArrayList<Game>();
        this.regSeasonSchedule = new Game[16];
        this.gameWinsAgainst = new ArrayList<Team>();
        this.gameWLSchedule = new ArrayList<String>();
        this.divChampion = "";
        this.natChampWL = "";
        this.totalDivChamps = 0;
        this.totalPlayoffWins = 0;
        this.totalPlayoffLosses = 0;
        this.totalSuperBowlLosses = 0;
        this.totalSuperBowlWins = 0;
        for (String[] split = jsonObject.get("players").toString().split(">"); i < split.length; ++i) {
            this.recruitPlayerCSV(split[i]);
        }
        this.teamStratOff = this.getTeamStrategiesOff()[this.teamStratOffNum];
        this.teamStratDef = this.getTeamStrategiesDef()[this.teamStratDefNum];
    }
    
    private void addFAPlayerCSV(final String s) {
        final String[] split = s.substring(4).split(">");
        final String[] split2 = split[0].split(",");
        final String s2 = split2[1];
        final int[] array = new int[split2.length - 2];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(split2[i + 2]);
        }
        final String s3 = split[1];
        int[] array2 = null;
        if (split.length == 3) {
            final String[] split3 = split[2].split(",");
            final int[] array3 = new int[split3.length];
            int n = 0;
            while (true) {
                array2 = array3;
                if (n >= split3.length) {
                    break;
                }
                array3[n] = Integer.parseInt(split3[n]);
                ++n;
            }
        }
        if (split2[0].equals("QB")) {
            this.playersFAs.add(new PlayerQB(s2, this, array, s3, array2));
        }
        else {
            if (split2[0].equals("RB")) {
                this.playersFAs.add(new PlayerRB(s2, this, array, s3, array2));
                return;
            }
            if (split2[0].equals("WR")) {
                this.playersFAs.add(new PlayerWR(s2, this, array, s3, array2));
                return;
            }
            if (split2[0].equals("OL")) {
                this.playersFAs.add(new PlayerOL(s2, this, array, s3));
                return;
            }
            if (split2[0].equals("K")) {
                this.playersFAs.add(new PlayerK(s2, this, array, s3, array2));
                return;
            }
            if (split2[0].equals("S")) {
                this.playersFAs.add(new PlayerS(s2, this, array, s3));
                return;
            }
            if (split2[0].equals("CB")) {
                this.playersFAs.add(new PlayerCB(s2, this, array, s3));
                return;
            }
            if (split2[0].equals("DL")) {
                this.playersFAs.add(new PlayerDL(s2, this, array, s3));
                return;
            }
            if (split2[0].equals("LB")) {
                this.playersFAs.add(new PlayerLB(s2, this, array, s3));
            }
        }
    }
    
    private void addGamePlayedList(final ArrayList<? extends Player> list, final int n, final boolean b) {
        for (int i = 0; i < n; ++i) {
            final Player player = (Player)list.get(i);
            ++player.gamesPlayed;
            if (b) {
                final Player player2 = (Player)list.get(i);
                ++player2.statsWins;
            }
        }
    }
    
    private void checkInjuryPosition(final ArrayList<? extends Player> list, final int n) {
        int n2 = 0;
        for (final Player player : list) {
            if (player.injury != null) {
                player.injury.advanceGame();
                final int n3 = ++n2;
                if (player.injury != null) {
                    continue;
                }
                this.playersRecovered.add(player);
                this.playersInjuredAll.remove(player);
                n2 = n3;
            }
        }
        int n4;
        if ((n4 = n2) < n) {
            int n5 = 0;
            while (true) {
                n4 = n2;
                if (n5 >= n) {
                    break;
                }
                final Player player2 = list.get(n5);
                double n6 = Math.pow(1.0 - player2.ratDur / 100.0, 3.0);
                if (player2.position.equals("QB") || player2.position.equals("K")) {
                    n6 = Math.pow(1.0 - player2.ratDur / 100.0, 4.0);
                }
                int n7 = 0;
                Label_0264: {
                    if (Math.random() >= n6) {
                        n7 = n2;
                        if (Math.random() >= 0.005) {
                            break Label_0264;
                        }
                    }
                    if ((n7 = n2) < n) {
                        player2.injury = new Injury(player2);
                        this.playersInjured.add(player2);
                        this.playersInjuredAll.add(player2);
                        n7 = n2 + 1;
                    }
                }
                ++n5;
                n2 = n7;
            }
        }
        if (n4 > 0) {
            Collections.sort((List<Object>)list, (Comparator<? super Object>)new PlayerComparator());
        }
    }
    
    private void checkRetirementList(final ArrayList<? extends Player> list) {
        double n = 0.0;
        if (this.natChampWL.equals("CBW")) {
            n = 0.0 + 0.2;
        }
        for (final Player player : list) {
            final double n2 = (80 - player.ratOvr) / 80.0;
            int n3 = 0;
            if (player.position.equals("QB") || player.position.equals("K")) {
                n3 = 2;
            }
            if ((player.age >= n3 + 33 && Math.random() < 0.35 + n + n2) || player.age >= 39) {
                this.playersRetiring.add(player);
            }
        }
    }
    
    private void curePlayersPosition(final ArrayList<? extends Player> list) {
        for (final Player player : list) {
            player.injury = null;
            player.isInjured = false;
        }
    }
    
    private int getValueAddedList(final ArrayList<? extends Player> list, final Player player, final int n) {
        if (list.size() >= n * 2 + 2) {
            return 0;
        }
        int n2 = 0 + (n * 2 - list.size()) * 5;
        int n3;
        for (int i = 0; i < list.size(); ++i, n2 = n3) {
            n3 = n2;
            if (i < n * 2) {
                final int n4 = player.getRatOvr() - ((Player)list.get(i)).getRatOvr();
                if (n4 > 0) {
                    if (i < n) {
                        n3 = n2 + n4 * 3;
                    }
                    else {
                        n3 = n2 + n4;
                    }
                }
                else {
                    n3 = n2 + n4;
                }
            }
        }
        return n2;
    }
    
    private int getWeightedAvgOverallList(final ArrayList<? extends Player> list, final int n) {
        int n2 = 0;
        int n3;
        for (int i = 0; i < list.size(); ++i, n2 = n3) {
            n3 = n2;
            if (i < n * 2) {
                if (i < n) {
                    n3 = n2 + ((Player)list.get(i)).ratOvr * 3;
                }
                else {
                    n3 = n2 + ((Player)list.get(i)).ratOvr;
                }
            }
        }
        return (int)(n2 / (double)(n * 4));
    }
    
    private void recruitPlayerCSV(final String s) {
        if (s.split(",").length == 12 || s.split(",").length == 9) {
            final String[] split = s.split(",");
            if (split[0].equals("QB")) {
                this.teamQBs.add(new PlayerQB(this, split));
            }
            else {
                if (split[0].equals("RB")) {
                    this.teamRBs.add(new PlayerRB(this, split));
                    return;
                }
                if (split[0].equals("WR")) {
                    this.teamWRs.add(new PlayerWR(this, split));
                    return;
                }
                if (split[0].equals("OL")) {
                    this.teamOLs.add(new PlayerOL(this, split));
                    return;
                }
                if (split[0].equals("K")) {
                    this.teamKs.add(new PlayerK(this, split));
                    return;
                }
                if (split[0].equals("S")) {
                    this.teamSs.add(new PlayerS(this, split));
                    return;
                }
                if (split[0].equals("CB")) {
                    this.teamCBs.add(new PlayerCB(this, split));
                    return;
                }
                if (split[0].equals("DL")) {
                    this.teamDLs.add(new PlayerDL(this, split));
                    return;
                }
                if (split[0].equals("LB")) {
                    this.teamLBs.add(new PlayerLB(this, split));
                }
            }
        }
        else {
            final String[] split2 = s.split(">");
            final String[] split3 = split2[0].split(",");
            final String s2 = split3[1];
            final int[] array = new int[split3.length - 2];
            for (int i = 0; i < array.length; ++i) {
                array[i] = Integer.parseInt(split3[i + 2]);
            }
            final String s3 = split2[1];
            int[] array2 = null;
            if (split2.length == 3) {
                final String[] split4 = split2[2].split(",");
                final int[] array3 = new int[split4.length];
                int n = 0;
                while (true) {
                    array2 = array3;
                    if (n >= split4.length) {
                        break;
                    }
                    array3[n] = Integer.parseInt(split4[n]);
                    ++n;
                }
            }
            if (split3[0].equals("QB")) {
                this.teamQBs.add(new PlayerQB(s2, this, array, s3, array2));
                return;
            }
            if (split3[0].equals("RB")) {
                this.teamRBs.add(new PlayerRB(s2, this, array, s3, array2));
                return;
            }
            if (split3[0].equals("WR")) {
                this.teamWRs.add(new PlayerWR(s2, this, array, s3, array2));
                return;
            }
            if (split3[0].equals("OL")) {
                this.teamOLs.add(new PlayerOL(s2, this, array, s3));
                return;
            }
            if (split3[0].equals("K")) {
                this.teamKs.add(new PlayerK(s2, this, array, s3, array2));
                return;
            }
            if (split3[0].equals("S")) {
                this.teamSs.add(new PlayerS(s2, this, array, s3));
                return;
            }
            if (split3[0].equals("CB")) {
                this.teamCBs.add(new PlayerCB(s2, this, array, s3));
                return;
            }
            if (split3[0].equals("DL")) {
                this.teamDLs.add(new PlayerDL(s2, this, array, s3));
                return;
            }
            if (split3[0].equals("LB")) {
                this.teamLBs.add(new PlayerLB(s2, this, array, s3));
            }
        }
    }
    
    public void addGamePlayedPlayers(final boolean b) {
        this.addGamePlayedList(this.teamQBs, 1, b);
        this.addGamePlayedList(this.teamRBs, 2, b);
        this.addGamePlayedList(this.teamWRs, 3, b);
        this.addGamePlayedList(this.teamOLs, 5, b);
        this.addGamePlayedList(this.teamKs, 1, b);
        this.addGamePlayedList(this.teamSs, 1, b);
        this.addGamePlayedList(this.teamCBs, 3, b);
        this.addGamePlayedList(this.teamDLs, 4, b);
        this.addGamePlayedList(this.teamLBs, 3, b);
    }
    
    public void addPlayer(final Player player) {
        if (player instanceof PlayerQB) {
            this.teamQBs.add((PlayerQB)player);
        }
        else if (player instanceof PlayerRB) {
            this.teamRBs.add((PlayerRB)player);
        }
        else if (player instanceof PlayerWR) {
            this.teamWRs.add((PlayerWR)player);
        }
        else if (player instanceof PlayerOL) {
            this.teamOLs.add((PlayerOL)player);
        }
        else if (player instanceof PlayerK) {
            this.teamKs.add((PlayerK)player);
        }
        else if (player instanceof PlayerS) {
            this.teamSs.add((PlayerS)player);
        }
        else if (player instanceof PlayerCB) {
            this.teamCBs.add((PlayerCB)player);
        }
        else if (player instanceof PlayerDL) {
            this.teamDLs.add((PlayerDL)player);
        }
        else if (player instanceof PlayerLB) {
            this.teamLBs.add((PlayerLB)player);
        }
        else {
            System.out.println(this.abbr + " couldn't add " + player.getPosNameYrOvrPot_OneLine());
        }
        player.team = this;
    }
    
    public void advanceInjuriesWeek() {
        for (final Player player : this.getAllPlayers()) {
            if (player.injury != null) {
                player.injury.advanceGame();
                if (player.injury != null) {
                    continue;
                }
                this.playersRecovered.add(player);
                this.playersInjuredAll.remove(player);
            }
        }
        this.sortPlayers();
    }
    
    public void advanceSeason() {
        this.league.checkHallofFame(this.playersRetiring);
        this.checkCareerRecords(this.league.leagueRecords);
        if (this.league.userTeam == this) {
            this.checkCareerRecords(this.league.userTeamRecords);
        }
        this.advanceSeasonPlayers();
    }
    
    public void advanceSeasonPlayers() {
        int n = 0;
        final int n2 = 0;
        final int n3 = 0;
        final int n4 = 0;
        final int n5 = 0;
        final int n6 = 0;
        final int n7 = 0;
        final int n8 = 0;
        int i = 0;
        while (i < this.teamQBs.size()) {
            if (this.playersRetiring.contains(this.teamQBs.get(i))) {
                this.teamQBs.remove(i);
                ++n;
            }
            else if (this.playersFAs.contains(this.teamQBs.get(i))) {
                this.teamQBs.get(i).advanceSeason();
                this.teamQBs.remove(i);
                ++n;
            }
            else {
                this.teamQBs.get(i).advanceSeason();
                ++i;
            }
        }
        int j = 0;
        int n9 = n2;
        while (j < this.teamRBs.size()) {
            if (this.playersRetiring.contains(this.teamRBs.get(j))) {
                this.teamRBs.remove(j);
                ++n9;
            }
            else if (this.playersFAs.contains(this.teamRBs.get(j))) {
                this.teamRBs.get(j).advanceSeason();
                this.teamRBs.remove(j);
                ++n9;
            }
            else {
                this.teamRBs.get(j).advanceSeason();
                ++j;
            }
        }
        int k = 0;
        int n10 = n3;
        while (k < this.teamWRs.size()) {
            if (this.playersRetiring.contains(this.teamWRs.get(k))) {
                this.teamWRs.remove(k);
                ++n10;
            }
            else if (this.playersFAs.contains(this.teamWRs.get(k))) {
                this.teamWRs.get(k).advanceSeason();
                this.teamWRs.remove(k);
                ++n10;
            }
            else {
                this.teamWRs.get(k).advanceSeason();
                ++k;
            }
        }
        int l = 0;
        int n11 = n4;
        while (l < this.teamKs.size()) {
            if (this.playersRetiring.contains(this.teamKs.get(l))) {
                this.teamKs.remove(l);
                ++n11;
            }
            else if (this.playersFAs.contains(this.teamKs.get(l))) {
                this.teamKs.get(l).advanceSeason();
                this.teamKs.remove(l);
                ++n11;
            }
            else {
                this.teamKs.get(l).advanceSeason();
                ++l;
            }
        }
        int n12 = 0;
        int n13 = n5;
        while (n12 < this.teamOLs.size()) {
            if (this.playersRetiring.contains(this.teamOLs.get(n12))) {
                this.teamOLs.remove(n12);
                ++n13;
            }
            else if (this.playersFAs.contains(this.teamOLs.get(n12))) {
                this.teamOLs.get(n12).advanceSeason();
                this.teamOLs.remove(n12);
                ++n13;
            }
            else {
                this.teamOLs.get(n12).advanceSeason();
                ++n12;
            }
        }
        int n14 = 0;
        int n15 = n6;
        while (n14 < this.teamSs.size()) {
            if (this.playersRetiring.contains(this.teamSs.get(n14))) {
                this.teamSs.remove(n14);
                ++n15;
            }
            else if (this.playersFAs.contains(this.teamSs.get(n14))) {
                this.teamSs.get(n14).advanceSeason();
                this.teamSs.remove(n14);
                ++n15;
            }
            else {
                this.teamSs.get(n14).advanceSeason();
                ++n14;
            }
        }
        int n16 = 0;
        int n17 = n7;
        while (n16 < this.teamCBs.size()) {
            if (this.playersRetiring.contains(this.teamCBs.get(n16))) {
                this.teamCBs.remove(n16);
                ++n17;
            }
            else if (this.playersFAs.contains(this.teamCBs.get(n16))) {
                this.teamCBs.get(n16).advanceSeason();
                this.teamCBs.remove(n16);
                ++n17;
            }
            else {
                this.teamCBs.get(n16).advanceSeason();
                ++n16;
            }
        }
        int n18 = 0;
        int n19 = n8;
        while (n18 < this.teamDLs.size()) {
            if (this.playersRetiring.contains(this.teamDLs.get(n18))) {
                this.teamDLs.remove(n18);
                ++n19;
            }
            else if (this.playersFAs.contains(this.teamDLs.get(n18))) {
                this.teamDLs.get(n18).advanceSeason();
                this.teamDLs.remove(n18);
                ++n19;
            }
            else {
                this.teamDLs.get(n18).advanceSeason();
                ++n18;
            }
        }
        int n20 = 0;
        while (n20 < this.teamLBs.size()) {
            if (this.playersRetiring.contains(this.teamLBs.get(n20))) {
                this.teamLBs.remove(n20);
                ++n19;
            }
            else if (this.playersFAs.contains(this.teamLBs.get(n20))) {
                this.teamLBs.get(n20).advanceSeason();
                this.teamLBs.remove(n20);
                ++n19;
            }
            else {
                this.teamLBs.get(n20).advanceSeason();
                ++n20;
            }
        }
        this.resetStats();
    }
    
    public void checkCareerRecords(final LeagueRecords leagueRecords) {
        for (final Player player : this.playersRetiring) {
            if (player instanceof PlayerQB) {
                final PlayerQB playerQB = (PlayerQB)player;
                leagueRecords.checkRecord("Career Pass Yards", playerQB.statsPassYards + playerQB.careerPassYards, this.abbr + " " + playerQB.getInitialName(), this.league.getYear() - 1);
                leagueRecords.checkRecord("Career Pass TDs", playerQB.statsTD + playerQB.careerTDs, this.abbr + " " + playerQB.getInitialName(), this.league.getYear() - 1);
                leagueRecords.checkRecord("Career Interceptions", playerQB.statsInt + playerQB.careerInt, this.abbr + " " + playerQB.getInitialName(), this.league.getYear() - 1);
            }
            else if (player instanceof PlayerRB) {
                final PlayerRB playerRB = (PlayerRB)player;
                leagueRecords.checkRecord("Career Rush Yards", playerRB.statsRushYards + playerRB.careerRushYards, this.abbr + " " + playerRB.getInitialName(), this.league.getYear() - 1);
                leagueRecords.checkRecord("Career Rush TDs", playerRB.statsTD + playerRB.careerTDs, this.abbr + " " + playerRB.getInitialName(), this.league.getYear() - 1);
                leagueRecords.checkRecord("Career Rush Fumbles", playerRB.statsFumbles + playerRB.careerFumbles, this.abbr + " " + playerRB.getInitialName(), this.league.getYear() - 1);
            }
            else {
                if (!(player instanceof PlayerWR)) {
                    continue;
                }
                final PlayerWR playerWR = (PlayerWR)player;
                leagueRecords.checkRecord("Career Rec Yards", playerWR.statsRecYards + playerWR.careerRecYards, this.abbr + " " + playerWR.getInitialName(), this.league.getYear() - 1);
                leagueRecords.checkRecord("Career Rec TDs", playerWR.statsTD + playerWR.careerTD, this.abbr + " " + playerWR.getInitialName(), this.league.getYear() - 1);
            }
        }
    }
    
    public void checkExpiringFAs(final ArrayList<? extends Player> list) {
        for (final Player player : list) {
            if (player.getContract().getYearsLeft() == 1 && !this.playersRetiring.contains(player)) {
                this.playersFAs.add(player);
            }
        }
    }
    
    public void checkForInjury() {
        this.playersInjured = new ArrayList<Player>();
        this.playersRecovered = new ArrayList<Player>();
        this.checkInjuryPosition(this.teamQBs, 1);
        this.checkInjuryPosition(this.teamRBs, 2);
        this.checkInjuryPosition(this.teamWRs, 3);
        this.checkInjuryPosition(this.teamOLs, 5);
        this.checkInjuryPosition(this.teamKs, 1);
        this.checkInjuryPosition(this.teamSs, 1);
        this.checkInjuryPosition(this.teamCBs, 3);
        this.checkInjuryPosition(this.teamDLs, 4);
        this.checkInjuryPosition(this.teamLBs, 3);
        for (final Player player : this.playersInjuredAll) {
            if (this.tradingBlock.contains(player)) {
                this.tradingBlock.remove(player);
            }
        }
    }
    
    public void checkLeagueRecords(final LeagueRecords leagueRecords) {
        leagueRecords.checkRecord("Team PPG", this.teamPoints / this.numGames(), this.abbr, this.league.getYear());
        leagueRecords.checkRecord("Team Opp PPG", this.teamOppPoints / this.numGames(), this.abbr, this.league.getYear());
        leagueRecords.checkRecord("Team YPG", this.teamYards / this.numGames(), this.abbr, this.league.getYear());
        leagueRecords.checkRecord("Team Opp YPG", this.teamOppYards / this.numGames(), this.abbr, this.league.getYear());
        leagueRecords.checkRecord("Team PPG", this.teamPoints / this.numGames(), this.abbr, this.league.getYear());
        leagueRecords.checkRecord("Team TO Diff", this.teamTODiff, this.abbr, this.league.getYear());
        for (int i = 0; i < this.teamQBs.size(); ++i) {
            if (this.getQB(i).gamesPlayed > 6) {
                leagueRecords.checkRecord("Pass Yards", this.getQB(i).statsPassYards, this.abbr + " " + this.getQB(i).getInitialName(), this.league.getYear());
                leagueRecords.checkRecord("Pass TDs", this.getQB(i).statsTD, this.abbr + " " + this.getQB(i).getInitialName(), this.league.getYear());
                leagueRecords.checkRecord("Interceptions", this.getQB(i).statsInt, this.abbr + " " + this.getQB(i).getInitialName(), this.league.getYear());
                leagueRecords.checkRecord("Comp Percent", this.getQB(i).statsPassComp * 100 / (this.getQB(i).statsPassAtt + 1), this.abbr + " " + this.getQB(i).getInitialName(), this.league.getYear());
            }
        }
        for (int j = 0; j < this.teamRBs.size(); ++j) {
            if (this.getRB(j).gamesPlayed > 6) {
                leagueRecords.checkRecord("Rush Yards", this.getRB(j).statsRushYards, this.abbr + " " + this.getRB(j).getInitialName(), this.league.getYear());
                leagueRecords.checkRecord("Rush TDs", this.getRB(j).statsTD, this.abbr + " " + this.getRB(j).getInitialName(), this.league.getYear());
                leagueRecords.checkRecord("Rush Fumbles", this.getRB(j).statsFumbles, this.abbr + " " + this.getRB(j).getInitialName(), this.league.getYear());
            }
        }
        for (int k = 0; k < this.teamWRs.size(); ++k) {
            if (this.getWR(k).gamesPlayed > 6) {
                leagueRecords.checkRecord("Rec Yards", this.getWR(k).statsRecYards, this.abbr + " " + this.getWR(k).getInitialName(), this.league.getYear());
                leagueRecords.checkRecord("Rec TDs", this.getWR(k).statsTD, this.abbr + " " + this.getWR(k).getInitialName(), this.league.getYear());
                leagueRecords.checkRecord("Catch Percent", this.getWR(k).statsReceptions * 100 / (this.getWR(k).statsTargets + 1), this.abbr + " " + this.getWR(k).getInitialName(), this.league.getYear());
            }
        }
    }
    
    public void curePlayers() {
        this.curePlayersPosition(this.teamQBs);
        this.curePlayersPosition(this.teamRBs);
        this.curePlayersPosition(this.teamWRs);
        this.curePlayersPosition(this.teamOLs);
        this.curePlayersPosition(this.teamKs);
        this.curePlayersPosition(this.teamSs);
        this.curePlayersPosition(this.teamCBs);
        this.curePlayersPosition(this.teamDLs);
        this.curePlayersPosition(this.teamLBs);
        this.sortPlayers();
    }
    
    public Player findBenchPlayer(final String s) {
        for (final PlayerQB playerQB : this.teamQBs) {
            if (playerQB.getPosNameYrOvrPot_Str().equals(s)) {
                return playerQB;
            }
        }
        for (final PlayerRB playerRB : this.teamRBs) {
            if (playerRB.getPosNameYrOvrPot_Str().equals(s)) {
                return playerRB;
            }
        }
        for (final PlayerWR playerWR : this.teamWRs) {
            if (playerWR.getPosNameYrOvrPot_Str().equals(s)) {
                return playerWR;
            }
        }
        for (final PlayerOL playerOL : this.teamOLs) {
            if (playerOL.getPosNameYrOvrPot_Str().equals(s)) {
                return playerOL;
            }
        }
        for (final PlayerK playerK : this.teamKs) {
            if (playerK.getPosNameYrOvrPot_Str().equals(s)) {
                return playerK;
            }
        }
        for (final PlayerS playerS : this.teamSs) {
            if (playerS.getPosNameYrOvrPot_Str().equals(s)) {
                return playerS;
            }
        }
        for (final PlayerCB playerCB : this.teamCBs) {
            if (playerCB.getPosNameYrOvrPot_Str().equals(s)) {
                return playerCB;
            }
        }
        for (final PlayerDL playerDL : this.teamDLs) {
            if (playerDL.getPosNameYrOvrPot_Str().equals(s)) {
                return playerDL;
            }
        }
        for (final PlayerLB playerLB : this.teamLBs) {
            if (playerLB.getPosNameYrOvrPot_Str().equals(s)) {
                return playerLB;
            }
        }
        return null;
    }
    
    public DraftPick findPick(final String s) {
        for (final DraftPick draftPick : this.draftPicks) {
            if (draftPick.getStrRepSplit().equals(s)) {
                return draftPick;
            }
        }
        return null;
    }
    
    public String gameSummaryStr(final Game game) {
        if (game.homeTeam == this) {
            return game.homeScore + " - " + game.awayScore + " vs " + game.awayTeam.abbr + " (" + game.awayTeam.wins + "-" + game.awayTeam.losses + ")";
        }
        return game.awayScore + " - " + game.homeScore + " @ " + game.homeTeam.abbr + " (" + game.homeTeam.wins + "-" + game.homeTeam.losses + ")";
    }
    
    public String gameSummaryStrOpponent(final Game game) {
        if (game.homeTeam == this) {
            return "vs " + game.awayTeam.abbr + " (" + game.awayTeam.wins + "-" + game.awayTeam.losses + ")";
        }
        return "@ " + game.homeTeam.abbr + " (" + game.homeTeam.wins + "-" + game.homeTeam.losses + ")";
    }
    
    public String gameSummaryStrScore(final Game game) {
        if (game.homeTeam == this) {
            return game.homeScore + " - " + game.awayScore;
        }
        return game.awayScore + " - " + game.homeScore;
    }
    
    public void generatePlayers(int i, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        final int n9 = this.teamPrestige / 20;
        final int teamPrestige = this.teamPrestige;
        final int n10 = this.teamPrestige / 20;
        for (int j = 0; j < i; ++j) {
            this.teamQBs.add(new PlayerQB(this.league.getRandName(), this));
        }
        for (i = 0; i < n; ++i) {
            this.teamRBs.add(new PlayerRB(this.league.getRandName(), this));
        }
        for (i = 0; i < n2; ++i) {
            this.teamWRs.add(new PlayerWR(this.league.getRandName(), this));
        }
        for (i = 0; i < n4; ++i) {
            this.teamOLs.add(new PlayerOL(this.league.getRandName(), this));
        }
        for (i = 0; i < n3; ++i) {
            this.teamKs.add(new PlayerK(this.league.getRandName(), this));
        }
        for (i = 0; i < n5; ++i) {
            this.teamSs.add(new PlayerS(this.league.getRandName(), this));
        }
        for (i = 0; i < n6; ++i) {
            this.teamCBs.add(new PlayerCB(this.league.getRandName(), this));
        }
        for (i = 0; i < n7; ++i) {
            this.teamDLs.add(new PlayerDL(this.league.getRandName(), this));
        }
        for (i = 0; i < n8; ++i) {
            this.teamLBs.add(new PlayerLB(this.league.getRandName(), this));
        }
        this.sortPlayers();
    }
    
    public ArrayList<Player> getAllPlayers() {
        final ArrayList<Player> list = new ArrayList<Player>();
        list.addAll(this.teamQBs);
        list.addAll(this.teamRBs);
        list.addAll(this.teamWRs);
        list.addAll(this.teamOLs);
        list.addAll(this.teamKs);
        list.addAll(this.teamSs);
        list.addAll(this.teamCBs);
        list.addAll(this.teamDLs);
        list.addAll(this.teamLBs);
        return list;
    }
    
    public PlayerCB getCB(final int n) {
        if (n < this.teamCBs.size() && n >= 0) {
            return this.teamCBs.get(n);
        }
        return this.teamCBs.get(0);
    }
    
    public String getCSV() {
        final StringBuilder append = new StringBuilder().append(this.division).append(",").append(this.name).append(",").append(this.abbr).append(",").append(this.totalPlayoffWins).append(",").append(this.totalPlayoffLosses).append(",").append(this.totalSuperBowlWins).append(",").append(this.totalSuperBowlLosses).append(",").append(this.totalWins).append(",").append(this.totalLosses).append(",").append(this.teamStratOffNum).append(",").append(this.teamStratDefNum).append(",");
        int n;
        if (this.showPopups) {
            n = 1;
        }
        else {
            n = 0;
        }
        return append.append(n).append(",").append(this.winStreak.getStreakCSV()).append(",").append(this.wins).append(",").append(this.losses).append(",").append(this.teamPoints).append(",").append(this.teamOppPoints).append(",").append(this.teamYards).append(",").append(this.teamOppYards).append(",").append(this.teamPassYards).append(",").append(this.teamRushYards).append(",").append(this.teamOppPassYards).append(",").append(this.teamOppRushYards).append(",").append(this.teamTODiff).append(",:").append(this.divChampion).append(",:").append(this.natChampWL).append(",").append(this.totalDivChamps).toString();
    }
    
    public String[] getCapRoomList() {
        final String[] array = new String[5];
        final ArrayList<Player> list = new ArrayList<Player>();
        list.addAll(this.getAllPlayers());
        for (int i = 0; i < 5; ++i) {
            double n = 0.0;
            for (final Player player : list) {
                if (player.getContract().getYearsLeft() >= i + 1) {
                    n += player.getContract().getMoneyPerYear();
                }
            }
            final double n2 = (int)(10.0 * n) / 10.0;
            array[i] = this.league.getYear() + i + ":>Total Salary Committed: $" + n2 + " mil>Cap Room: $" + (int)(10.0 * (150.0 - n2)) / 10.0 + "mil";
        }
        return array;
    }
    
    public int getCompositeF7Pass() {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            n += (this.teamDLs.get(i).ratDLPas + this.teamDLs.get(i).ratDLPow) / 2;
        }
        for (int j = 0; j < 3; ++j) {
            n += (this.teamLBs.get(j).ratLBPas + this.teamLBs.get(j).ratLBTck) / 2;
        }
        return n / 7;
    }
    
    public int getCompositeF7Rush() {
        int n = 0;
        for (int i = 0; i < 4; ++i) {
            n += (this.teamDLs.get(i).ratDLRsh + this.teamDLs.get(i).ratDLPow) / 2;
        }
        for (int j = 0; j < 3; ++j) {
            n += (this.teamLBs.get(j).ratLBRsh + this.teamLBs.get(j).ratLBTck) / 2;
        }
        return n / 7;
    }
    
    public int getCompositeFootIQ() {
        int n = 0 + this.getQB(0).ratFootIQ * 5 + (this.getRB(0).ratFootIQ + this.getRB(1).ratFootIQ) + (this.getWR(0).ratFootIQ + this.getWR(1).ratFootIQ + this.getWR(2).ratFootIQ);
        for (int i = 0; i < 5; ++i) {
            n += this.getOL(i).ratFootIQ / 5;
        }
        int n2 = n + this.getS(0).ratFootIQ * 5 + (this.getCB(0).ratFootIQ + this.getCB(1).ratFootIQ + this.getCB(2).ratFootIQ);
        for (int j = 0; j < 4; ++j) {
            n2 += this.getDL(j).ratFootIQ / 7;
        }
        for (int k = 0; k < 3; ++k) {
            n2 += this.getLB(k).ratFootIQ / 7;
        }
        return n2 / 20;
    }
    
    public int getCompositeOLPass() {
        int n = 0;
        for (int i = 0; i < 5; ++i) {
            n += (this.teamOLs.get(i).ratOLBkP + this.teamOLs.get(i).ratOLPow) / 2;
        }
        return n / 5;
    }
    
    public int getCompositeOLRush() {
        int n = 0;
        for (int i = 0; i < 5; ++i) {
            n += (this.teamOLs.get(i).ratOLBkR + this.teamOLs.get(i).ratOLPow) / 2;
        }
        return n / 5;
    }
    
    public PlayerDL getDL(final int n) {
        if (n < this.teamDLs.size() && n >= 0) {
            return this.teamDLs.get(n);
        }
        return this.teamDLs.get(0);
    }
    
    public int getDefTalent() {
        return (this.getRushDef() + this.getPassDef()) / 2;
    }
    
    public int getDivLosses() {
        int n = 0;
        int n2;
        for (int i = 0; i < this.gameWLSchedule.size(); ++i, n = n2) {
            final Game game = this.gameSchedule.get(i);
            n2 = n;
            if (game.gameName.equals("Division")) {
                if (game.homeTeam == this && game.homeScore < game.awayScore) {
                    n2 = n + 1;
                }
                else {
                    n2 = n;
                    if (game.awayTeam == this) {
                        n2 = n;
                        if (game.homeScore > game.awayScore) {
                            n2 = n + 1;
                        }
                    }
                }
            }
        }
        return n;
    }
    
    public int getDivWins() {
        int n = 0;
        int n2;
        for (int i = 0; i < this.gameWLSchedule.size(); ++i, n = n2) {
            final Game game = this.gameSchedule.get(i);
            n2 = n;
            if (game.gameName.equals("Division")) {
                if (game.homeTeam == this && game.homeScore > game.awayScore) {
                    n2 = n + 1;
                }
                else {
                    n2 = n;
                    if (game.awayTeam == this) {
                        n2 = n;
                        if (game.homeScore < game.awayScore) {
                            n2 = n + 1;
                        }
                    }
                }
            }
        }
        return n;
    }
    
    public String[] getGameSummaryStr(final int n) {
        final String[] array = new String[3];
        final Game game = this.gameSchedule.get(n);
        array[0] = game.gameName;
        if (n < this.gameWLSchedule.size()) {
            array[1] = this.gameWLSchedule.get(n) + " " + this.gameSummaryStrScore(game);
            if (game.numOT > 0) {
                array[1] = array[1] + " (" + game.numOT + "OT)";
            }
        }
        else {
            array[1] = "---";
        }
        array[2] = this.gameSummaryStrOpponent(game);
        return array;
    }
    
    public String[] getInjuryReport() {
        String[] array2;
        if (this.playersInjured.size() > 0 || this.playersRecovered.size() > 0) {
            String[] array;
            if (this.playersRecovered.size() > 0) {
                array = new String[this.playersInjured.size() + this.playersRecovered.size() + 1];
            }
            else {
                array = new String[this.playersInjured.size()];
            }
            for (int i = 0; i < this.playersInjured.size(); ++i) {
                array[i] = this.playersInjured.get(i).getPosNameYrOvrPot_Str();
            }
            array2 = array;
            if (this.playersRecovered.size() > 0) {
                array[this.playersInjured.size()] = "Players Recovered from Injuries:";
                int n = 0;
                while (true) {
                    array2 = array;
                    if (n >= this.playersRecovered.size()) {
                        break;
                    }
                    array[this.playersInjured.size() + n + 1] = this.playersRecovered.get(n).getPosNameYrOvrPot_Str();
                    ++n;
                }
            }
        }
        else {
            array2 = null;
        }
        return array2;
    }
    
    public PlayerK getK(final int n) {
        if (n < this.teamKs.size() && n >= 0) {
            return this.teamKs.get(n);
        }
        return this.teamKs.get(0);
    }
    
    public PlayerLB getLB(final int n) {
        if (n < this.teamLBs.size() && n >= 0) {
            return this.teamLBs.get(n);
        }
        return this.teamLBs.get(0);
    }
    
    public String getNeededPosition(final boolean b) {
        if (b) {
            final int[] array = { this.getWeightedAvgOverallList(this.teamQBs, 1), this.getWeightedAvgOverallList(this.teamRBs, 2), this.getWeightedAvgOverallList(this.teamWRs, 3), this.getWeightedAvgOverallList(this.teamOLs, 5), this.getWeightedAvgOverallList(this.teamKs, 1), this.getWeightedAvgOverallList(this.teamSs, 1), this.getWeightedAvgOverallList(this.teamCBs, 3), this.getWeightedAvgOverallList(this.teamDLs, 4), this.getWeightedAvgOverallList(this.teamLBs, 3) };
            Arrays.sort(array);
            if (array[0] == this.getWeightedAvgOverallList(this.teamQBs, 1) && this.teamQBs.size() < 4) {
                return "QB";
            }
            if (array[0] == this.getWeightedAvgOverallList(this.teamRBs, 2) && this.teamRBs.size() < 6) {
                return "RB";
            }
            if (array[0] == this.getWeightedAvgOverallList(this.teamSs, 1) && this.teamSs.size() < 4) {
                return "S";
            }
            if (array[0] == this.getWeightedAvgOverallList(this.teamWRs, 3) && this.teamWRs.size() < 8) {
                return "WR";
            }
            if (array[0] == this.getWeightedAvgOverallList(this.teamOLs, 5) && this.teamOLs.size() < 12) {
                return "OL";
            }
            if (array[0] == this.getWeightedAvgOverallList(this.teamCBs, 3) && this.teamCBs.size() < 8) {
                return "CB";
            }
            if (array[0] == this.getWeightedAvgOverallList(this.teamDLs, 4) && this.teamDLs.size() < 10) {
                return "DL";
            }
            if (array[0] == this.getWeightedAvgOverallList(this.teamLBs, 3) && this.teamLBs.size() < 8) {
                return "LB";
            }
            if (array[0] == this.getWeightedAvgOverallList(this.teamKs, 1) && this.teamKs.size() < 4) {
                return "K";
            }
            return (new String[] { "QB", "RB", "WR", "OL", "K", "S", "CB", "DL", "LB" })[(int)(Math.random() * 8.0)];
        }
        else {
            final int[] array2 = { this.getWeightedAvgOverallList(this.teamQBs, 1), this.getWeightedAvgOverallList(this.teamRBs, 2), this.getWeightedAvgOverallList(this.teamWRs, 3), this.getWeightedAvgOverallList(this.teamOLs, 5), this.getWeightedAvgOverallList(this.teamSs, 1), this.getWeightedAvgOverallList(this.teamCBs, 3), this.getWeightedAvgOverallList(this.teamDLs, 4), this.getWeightedAvgOverallList(this.teamLBs, 3) };
            Arrays.sort(array2);
            if (array2[0] == this.getWeightedAvgOverallList(this.teamQBs, 1) && this.teamQBs.size() < 4) {
                return "QB";
            }
            if (array2[0] == this.getWeightedAvgOverallList(this.teamRBs, 2) && this.teamRBs.size() < 6) {
                return "RB";
            }
            if (array2[0] == this.getWeightedAvgOverallList(this.teamSs, 1) && this.teamSs.size() < 4) {
                return "S";
            }
            if (array2[0] == this.getWeightedAvgOverallList(this.teamWRs, 3) && this.teamWRs.size() < 9) {
                return "WR";
            }
            if (array2[0] == this.getWeightedAvgOverallList(this.teamOLs, 5) && this.teamOLs.size() < 14) {
                return "OL";
            }
            if (array2[0] == this.getWeightedAvgOverallList(this.teamCBs, 3) && this.teamCBs.size() < 9) {
                return "CB";
            }
            if (array2[0] == this.getWeightedAvgOverallList(this.teamDLs, 4) && this.teamDLs.size() < 10) {
                return "DL";
            }
            if (array2[0] == this.getWeightedAvgOverallList(this.teamLBs, 3) && this.teamLBs.size() < 8) {
                return "LB";
            }
            return (new String[] { "QB", "RB", "WR", "OL", "K", "S", "CB", "DL", "LB" })[(int)(Math.random() * 8.0)];
        }
    }
    
    public ArrayList<String> getNeededPositionsSorted() {
        final int[] array = { this.getWeightedAvgOverallList(this.teamQBs, 1), this.getWeightedAvgOverallList(this.teamRBs, 2), this.getWeightedAvgOverallList(this.teamWRs, 3), this.getWeightedAvgOverallList(this.teamOLs, 5), this.getWeightedAvgOverallList(this.teamKs, 1), this.getWeightedAvgOverallList(this.teamSs, 1), this.getWeightedAvgOverallList(this.teamCBs, 3), this.getWeightedAvgOverallList(this.teamDLs, 4), this.getWeightedAvgOverallList(this.teamLBs, 3) };
        Arrays.sort(array);
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < array.length; ++i) {}
        return list;
    }
    
    public int getNeedsPosition(final String s) {
        if (s.equals("QB")) {
            return 2 - this.teamQBs.size();
        }
        if (s.equals("RB")) {
            return 4 - this.teamRBs.size();
        }
        if (s.equals("WR")) {
            return 6 - this.teamWRs.size();
        }
        if (s.equals("OL")) {
            return 10 - this.teamOLs.size();
        }
        if (s.equals("K")) {
            return 2 - this.teamKs.size();
        }
        if (s.equals("S")) {
            return 2 - this.teamSs.size();
        }
        if (s.equals("CB")) {
            return 6 - this.teamCBs.size();
        }
        if (s.equals("DL")) {
            return 8 - this.teamDLs.size();
        }
        if (s.equals("LB")) {
            return 8 - this.teamLBs.size();
        }
        return 0;
    }
    
    public PlayerOL getOL(final int n) {
        if (n < this.teamOLs.size() && n >= 0) {
            return this.teamOLs.get(n);
        }
        return this.teamOLs.get(0);
    }
    
    public int getOPPG() {
        return this.teamOppPoints / this.numGames();
    }
    
    public int getOPYPG() {
        return this.teamOppPassYards / this.numGames();
    }
    
    public int getORYPG() {
        return this.teamOppRushYards / this.numGames();
    }
    
    public int getOYPG() {
        return this.teamOppYards / this.numGames();
    }
    
    public int getOffTalent() {
        return (this.teamRBs.get(1).ratOvr + (this.getQB(0).ratOvr * 5 + this.teamWRs.get(0).ratOvr + this.teamWRs.get(1).ratOvr + this.teamWRs.get(2).ratOvr + this.teamRBs.get(0).ratOvr) + this.getCompositeOLPass() + this.getCompositeOLRush()) / 12;
    }
    
    public String[] getOnlineGameSummaryStr(final int n) {
        final String[] array = new String[3];
        final Game game = this.gameSchedule.get(n);
        if (game.affectsELO) {
            array[0] = "Ranked";
        }
        else {
            array[0] = "Custom";
        }
        if (n < this.gameWLSchedule.size()) {
            array[1] = this.gameWLSchedule.get(n) + " " + this.gameSummaryStrScore(game);
            if (game.numOT > 0) {
                array[1] = array[1] + " (" + game.numOT + "OT)";
            }
        }
        else {
            array[1] = "---";
        }
        array[2] = this.gameSummaryStrOpponent(game);
        return array;
    }
    
    public Map<String, List<String>> getOnlinePlayerStatsExpandListMap(final List<String> list) {
        final LinkedHashMap<String, List<String>> linkedHashMap = new LinkedHashMap<String, List<String>>();
        linkedHashMap.put(list.get(0), this.getQB(0).getDetailStatsOffseason().subList(0, 3));
        for (int i = 1; i < 3; ++i) {
            linkedHashMap.put(list.get(i), this.getRB(i - 1).getDetailStatsOffseason().subList(0, 3));
        }
        for (int j = 3; j < 6; ++j) {
            linkedHashMap.put(list.get(j), this.getWR(j - 3).getDetailStatsOffseason().subList(0, 3));
        }
        for (int k = 6; k < 11; ++k) {
            linkedHashMap.put(list.get(k), this.getOL(k - 6).getDetailStatsOffseason().subList(0, 3));
        }
        linkedHashMap.put(list.get(11), this.getK(0).getDetailStatsOffseason().subList(0, 3));
        linkedHashMap.put(list.get(12), this.getS(0).getDetailStatsOffseason().subList(0, 3));
        for (int l = 13; l < 16; ++l) {
            linkedHashMap.put(list.get(l), this.getCB(l - 13).getDetailStatsOffseason().subList(0, 3));
        }
        for (int n = 16; n < 20; ++n) {
            linkedHashMap.put(list.get(n), this.getDL(n - 16).getDetailStatsOffseason().subList(0, 3));
        }
        for (int n2 = 20; n2 < 23; ++n2) {
            linkedHashMap.put(list.get(n2), this.getLB(n2 - 20).getDetailStatsOffseason().subList(0, 3));
        }
        return linkedHashMap;
    }
    
    public List<String> getOnlinePlayerStatsExpandListStr() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(this.getQB(0).getPosNameYrOvrPot_Str());
        for (int i = 0; i < 2; ++i) {
            list.add(this.getRB(i).getPosNameYrOvrPot_Str());
        }
        for (int j = 0; j < 3; ++j) {
            list.add(this.getWR(j).getPosNameYrOvrPot_Str());
        }
        for (int k = 0; k < 5; ++k) {
            list.add(this.getOL(k).getPosNameYrOvrPot_Str());
        }
        list.add(this.getK(0).getPosNameYrOvrPot_Str());
        list.add(this.getS(0).getPosNameYrOvrPot_Str());
        for (int l = 0; l < 3; ++l) {
            list.add(this.getCB(l).getPosNameYrOvrPot_Str());
        }
        for (int n = 0; n < 4; ++n) {
            list.add(this.getDL(n).getPosNameYrOvrPot_Str());
        }
        for (int n2 = 0; n2 < 3; ++n2) {
            list.add(this.getLB(n2).getPosNameYrOvrPot_Str());
        }
        return list;
    }
    
    public int getPPG() {
        return this.teamPoints / this.numGames();
    }
    
    public int getPYPG() {
        return this.teamPassYards / this.numGames();
    }
    
    public int getPassDef() {
        return (this.teamSs.get(0).ratOvr + (this.teamCBs.get(2).ratOvr + (this.teamCBs.get(0).ratOvr + this.teamCBs.get(1).ratOvr)) / 3 * 3 + this.getCompositeF7Pass() * 2) / 6;
    }
    
    public int getPassProf() {
        return (this.getCompositeOLPass() + this.getQB(0).ratOvr * 2 + (this.teamWRs.get(2).ratOvr + (this.teamWRs.get(0).ratOvr + this.teamWRs.get(1).ratOvr)) / 3) / 4;
    }
    
    public String getPlayerInfoSaveFile(final boolean b) {
        final StringBuilder sb = new StringBuilder();
        final Iterator<PlayerQB> iterator = this.teamQBs.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getCSV() + "%\n");
        }
        final Iterator<PlayerRB> iterator2 = this.teamRBs.iterator();
        while (iterator2.hasNext()) {
            sb.append(iterator2.next().getCSV() + "%\n");
        }
        final Iterator<PlayerWR> iterator3 = this.teamWRs.iterator();
        while (iterator3.hasNext()) {
            sb.append(iterator3.next().getCSV() + "%\n");
        }
        final Iterator<PlayerK> iterator4 = this.teamKs.iterator();
        while (iterator4.hasNext()) {
            sb.append(iterator4.next().getCSV() + "%\n");
        }
        final Iterator<PlayerOL> iterator5 = this.teamOLs.iterator();
        while (iterator5.hasNext()) {
            sb.append(iterator5.next().getCSV() + "%\n");
        }
        final Iterator<PlayerS> iterator6 = this.teamSs.iterator();
        while (iterator6.hasNext()) {
            sb.append(iterator6.next().getCSV() + "%\n");
        }
        final Iterator<PlayerCB> iterator7 = this.teamCBs.iterator();
        while (iterator7.hasNext()) {
            sb.append(iterator7.next().getCSV() + "%\n");
        }
        final Iterator<PlayerDL> iterator8 = this.teamDLs.iterator();
        while (iterator8.hasNext()) {
            sb.append(iterator8.next().getCSV() + "%\n");
        }
        final Iterator<PlayerLB> iterator9 = this.teamLBs.iterator();
        while (iterator9.hasNext()) {
            sb.append(iterator9.next().getCSV() + "%\n");
        }
        if (b) {
            final Iterator<Player> iterator10 = this.playersFAs.iterator();
            while (iterator10.hasNext()) {
                sb.append("[FA]" + iterator10.next().getCSV() + "%\n");
            }
        }
        return sb.toString();
    }
    
    public Map<String, List<String>> getPlayerStatsExpandListMap(final List<String> list) {
        final LinkedHashMap<String, ArrayList<String>> linkedHashMap = (LinkedHashMap<String, ArrayList<String>>)new LinkedHashMap<String, List<String>>();
        linkedHashMap.put(list.get(0), this.getQB(0).getDetailStatsList(this.numGames()));
        for (int i = 1; i < 3; ++i) {
            linkedHashMap.put(list.get(i), this.getRB(i - 1).getDetailStatsList(this.numGames()));
        }
        for (int j = 3; j < 6; ++j) {
            linkedHashMap.put(list.get(j), this.getWR(j - 3).getDetailStatsList(this.numGames()));
        }
        for (int k = 6; k < 11; ++k) {
            linkedHashMap.put(list.get(k), this.getOL(k - 6).getDetailStatsList(this.numGames()));
        }
        linkedHashMap.put(list.get(11), this.getK(0).getDetailStatsList(this.numGames()));
        linkedHashMap.put(list.get(12), this.getS(0).getDetailStatsList(this.numGames()));
        for (int l = 13; l < 16; ++l) {
            linkedHashMap.put(list.get(l), this.getCB(l - 13).getDetailStatsList(this.numGames()));
        }
        for (int n = 16; n < 20; ++n) {
            linkedHashMap.put(list.get(n), this.getDL(n - 16).getDetailStatsList(this.numGames()));
        }
        for (int n2 = 20; n2 < 23; ++n2) {
            linkedHashMap.put(list.get(n2), this.getLB(n2 - 20).getDetailStatsList(this.numGames()));
        }
        final String s = list.get(23);
        final ArrayList<String> list2 = new ArrayList<String>();
        for (int n3 = 1; n3 < this.teamQBs.size(); ++n3) {
            list2.add(this.getQB(n3).getPosNameYrOvrPot_Str());
        }
        for (int n4 = 2; n4 < this.teamRBs.size(); ++n4) {
            list2.add(this.getRB(n4).getPosNameYrOvrPot_Str());
        }
        for (int n5 = 3; n5 < this.teamWRs.size(); ++n5) {
            list2.add(this.getWR(n5).getPosNameYrOvrPot_Str());
        }
        for (int n6 = 5; n6 < this.teamOLs.size(); ++n6) {
            list2.add(this.getOL(n6).getPosNameYrOvrPot_Str());
        }
        for (int n7 = 1; n7 < this.teamKs.size(); ++n7) {
            list2.add(this.getK(n7).getPosNameYrOvrPot_Str());
        }
        for (int n8 = 1; n8 < this.teamSs.size(); ++n8) {
            list2.add(this.getS(n8).getPosNameYrOvrPot_Str());
        }
        for (int n9 = 3; n9 < this.teamCBs.size(); ++n9) {
            list2.add(this.getCB(n9).getPosNameYrOvrPot_Str());
        }
        for (int n10 = 4; n10 < this.teamDLs.size(); ++n10) {
            list2.add(this.getDL(n10).getPosNameYrOvrPot_Str());
        }
        for (int n11 = 3; n11 < this.teamLBs.size(); ++n11) {
            list2.add(this.getLB(n11).getPosNameYrOvrPot_Str());
        }
        linkedHashMap.put(s, list2);
        final String s2 = list.get(24);
        final ArrayList<String> list3 = new ArrayList<String>();
        final Iterator<DraftPick> iterator = this.draftPicks.iterator();
        while (iterator.hasNext()) {
            list3.add(iterator.next().getStrRepSplit());
        }
        linkedHashMap.put(s2, list3);
        return (Map<String, List<String>>)linkedHashMap;
    }
    
    public List<String> getPlayerStatsExpandListStr() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add(this.getQB(0).getPosNameYrOvrPot_Str());
        for (int i = 0; i < 2; ++i) {
            list.add(this.getRB(i).getPosNameYrOvrPot_Str());
        }
        for (int j = 0; j < 3; ++j) {
            list.add(this.getWR(j).getPosNameYrOvrPot_Str());
        }
        for (int k = 0; k < 5; ++k) {
            list.add(this.getOL(k).getPosNameYrOvrPot_Str());
        }
        list.add(this.getK(0).getPosNameYrOvrPot_Str());
        list.add(this.getS(0).getPosNameYrOvrPot_Str());
        for (int l = 0; l < 3; ++l) {
            list.add(this.getCB(l).getPosNameYrOvrPot_Str());
        }
        for (int n = 0; n < 4; ++n) {
            list.add(this.getDL(n).getPosNameYrOvrPot_Str());
        }
        for (int n2 = 0; n2 < 3; ++n2) {
            list.add(this.getLB(n2).getPosNameYrOvrPot_Str());
        }
        list.add("BENCH > BENCH");
        list.add("DRAFT PICKS > DRAFT PICKS");
        return list;
    }
    
    public String getPlayersCSV_Online() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.getQB(0).getCSV_Online()).append(">");
        sb.append(this.getRB(0).getCSV_Online()).append(">");
        sb.append(this.getRB(1).getCSV_Online()).append(">");
        sb.append(this.getWR(0).getCSV_Online()).append(">");
        sb.append(this.getWR(1).getCSV_Online()).append(">");
        sb.append(this.getWR(2).getCSV_Online()).append(">");
        sb.append(this.getOL(0).getCSV_Online()).append(">");
        sb.append(this.getOL(1).getCSV_Online()).append(">");
        sb.append(this.getOL(2).getCSV_Online()).append(">");
        sb.append(this.getOL(3).getCSV_Online()).append(">");
        sb.append(this.getOL(4).getCSV_Online()).append(">");
        sb.append(this.getK(0).getCSV_Online()).append(">");
        sb.append(this.getS(0).getCSV_Online()).append(">");
        sb.append(this.getCB(0).getCSV_Online()).append(">");
        sb.append(this.getCB(1).getCSV_Online()).append(">");
        sb.append(this.getCB(2).getCSV_Online()).append(">");
        sb.append(this.getDL(0).getCSV_Online()).append(">");
        sb.append(this.getDL(1).getCSV_Online()).append(">");
        sb.append(this.getDL(2).getCSV_Online()).append(">");
        sb.append(this.getDL(3).getCSV_Online()).append(">");
        sb.append(this.getLB(0).getCSV_Online()).append(">");
        sb.append(this.getLB(1).getCSV_Online()).append(">");
        sb.append(this.getLB(2).getCSV_Online());
        return sb.toString();
    }
    
    public String[] getPlayersFAList() {
        final String[] array = new String[this.playersFAs.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.playersFAs.get(i).getPosNameYrOvrPot_Split();
        }
        return array;
    }
    
    public String getPlayersFAStr() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<Player> iterator = this.playersFAs.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getPosNameYrOvrPot_OneLine() + "\n");
        }
        return sb.toString();
    }
    
    public void getPlayersInjuredAll() {
        final ArrayList<Player> allPlayers = this.getAllPlayers();
        this.playersInjuredAll = new ArrayList<Player>();
        for (final Player player : allPlayers) {
            if (player.injury != null) {
                this.playersInjuredAll.add(player);
            }
        }
    }
    
    public void getPlayersLeaving() {
        if (this.playersRetiring.isEmpty() && this.playersFAs.isEmpty()) {
            this.checkRetirementList(this.teamQBs);
            this.checkRetirementList(this.teamRBs);
            this.checkRetirementList(this.teamWRs);
            this.checkRetirementList(this.teamOLs);
            this.checkRetirementList(this.teamKs);
            this.checkRetirementList(this.teamSs);
            this.checkRetirementList(this.teamCBs);
            this.checkRetirementList(this.teamDLs);
            this.checkRetirementList(this.teamLBs);
            this.checkExpiringFAs(this.getAllPlayers());
        }
        else if (this.userControlled) {
            final Iterator<Player> iterator = this.playersFAs.iterator();
            while (iterator.hasNext()) {
                System.out.println("Team FA: " + iterator.next().getPosNameYrOvrPot_OneLine());
            }
        }
    }
    
    public String[] getPlayersRetiringList() {
        final String[] array = new String[this.playersRetiring.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.playersRetiring.get(i).getPosNameYrOvrPot_Split();
        }
        return array;
    }
    
    public String getPlayersRetiringStr() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<Player> iterator = this.playersRetiring.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().getPosNameYrOvrPot_OneLine() + "\n");
        }
        return sb.toString();
    }
    
    public ArrayList<? extends Player> getPosList(final int n) {
        switch (n) {
            default: {
                return this.getAllPlayers();
            }
            case 0: {
                return this.getAllPlayers();
            }
            case 1: {
                return this.teamQBs;
            }
            case 2: {
                return this.teamRBs;
            }
            case 3: {
                return this.teamWRs;
            }
            case 4: {
                return this.teamOLs;
            }
            case 5: {
                return this.teamKs;
            }
            case 6: {
                return this.teamSs;
            }
            case 7: {
                return this.teamCBs;
            }
            case 8: {
                return this.teamDLs;
            }
            case 9: {
                return this.teamLBs;
            }
        }
    }
    
    public PlayerQB getQB(final int n) {
        if (n < this.teamQBs.size() && n >= 0) {
            return this.teamQBs.get(n);
        }
        return this.teamQBs.get(0);
    }
    
    public PlayerRB getRB(final int n) {
        if (n < this.teamRBs.size() && n >= 0) {
            return this.teamRBs.get(n);
        }
        return this.teamRBs.get(0);
    }
    
    public int getRYPG() {
        return this.teamRushYards / this.numGames();
    }
    
    public String getRankStr(final int n) {
        if (n == 11) {
            return "11th";
        }
        if (n == 12) {
            return "12th";
        }
        if (n == 13) {
            return "13th";
        }
        if (n % 10 == 1) {
            return n + "st";
        }
        if (n % 10 == 2) {
            return n + "nd";
        }
        if (n % 10 == 3) {
            return n + "rd";
        }
        return n + "th";
    }
    
    public String getRankStrStarUser(final int n) {
        if (n == 11) {
            return "11th";
        }
        if (n == 12) {
            return "12th";
        }
        if (n == 13) {
            return "13th";
        }
        if (n % 10 == 1) {
            return n + "st";
        }
        if (n % 10 == 2) {
            return n + "nd";
        }
        if (n % 10 == 3) {
            return n + "rd";
        }
        return n + "th";
    }
    
    public int getRushDef() {
        return this.getCompositeF7Rush();
    }
    
    public int getRushProf() {
        return (this.getCompositeOLRush() + (this.teamRBs.get(1).ratOvr + this.teamRBs.get(0).ratOvr) / 2) / 2;
    }
    
    public PlayerS getS(final int n) {
        if (n < this.teamSs.size() && n >= 0) {
            return this.teamSs.get(n);
        }
        return this.teamSs.get(0);
    }
    
    public double getSalaryCapRoom() {
        return (int)(10.0 * ((int)((150.0 - this.getTotalSalary()) * 10.0) / 10.0)) / 10.0;
    }
    
    public String getStrAbbrWL() {
        return this.abbr + " (" + this.wins + "-" + this.losses + ")";
    }
    
    public String getStrAbbrWL_2Lines() {
        return this.abbr + "\n(" + this.wins + "-" + this.losses + ")";
    }
    
    public String getTODiff() {
        if (this.teamTODiff > 0) {
            return "+" + this.teamTODiff;
        }
        return this.teamTODiff + "";
    }
    
    public String[] getTeamHistoryList() {
        final String[] array = new String[this.teamHistory.size() + 5];
        array[0] = "Overall W-L: " + this.totalWins + "-" + this.totalLosses;
        array[1] = "Division Champions: " + this.totalDivChamps;
        array[2] = "Playoff Record: " + this.totalPlayoffWins + "-" + this.totalPlayoffLosses;
        array[3] = "Champ Bowl Record: " + this.totalSuperBowlWins + "-" + this.totalSuperBowlLosses;
        array[4] = " ";
        for (int i = 0; i < this.teamHistory.size(); ++i) {
            array[i + 5] = this.teamHistory.get(i);
        }
        return array;
    }
    
    public String getTeamNeeds() {
        final StringBuilder sb = new StringBuilder();
        sb.append("\t\t" + (2 - this.teamQBs.size()) + "QBs, ");
        sb.append(4 - this.teamRBs.size() + "RBs, ");
        sb.append(6 - this.teamWRs.size() + "WRs, ");
        sb.append(2 - this.teamKs.size() + "Ks\n");
        sb.append("\t\t" + (10 - this.teamOLs.size()) + "OLs, ");
        sb.append(2 - this.teamSs.size() + "Ss, ");
        sb.append(6 - this.teamCBs.size() + "CBs, ");
        sb.append(8 - this.teamDLs.size() + "DLs");
        sb.append(6 - this.teamLBs.size() + "LBs");
        return sb.toString();
    }
    
    public String getTeamStatsStrCSV() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.teamPollScore + ",");
        sb.append("Power Rank,");
        sb.append(this.getRankStr(this.rankTeamPollScore) + "%\n");
        sb.append(this.teamStrengthOfWins + ",");
        sb.append("SOS,");
        sb.append(this.getRankStr(this.rankTeamStrengthOfWins) + "%\n");
        sb.append(this.teamPoints / this.numGames() + ",");
        sb.append("Points,");
        sb.append(this.getRankStr(this.rankTeamPoints) + "%\n");
        sb.append(this.teamOppPoints / this.numGames() + ",");
        sb.append("Opp Points,");
        sb.append(this.getRankStr(this.rankTeamOppPoints) + "%\n");
        sb.append(this.teamYards / this.numGames() + ",");
        sb.append("Yards,");
        sb.append(this.getRankStr(this.rankTeamYards) + "%\n");
        sb.append(this.teamOppYards / this.numGames() + ",");
        sb.append("Opp Yards,");
        sb.append(this.getRankStr(this.rankTeamOppYards) + "%\n");
        sb.append(this.teamPassYards / this.numGames() + ",");
        sb.append("Pass Yards,");
        sb.append(this.getRankStr(this.rankTeamPassYards) + "%\n");
        sb.append(this.teamRushYards / this.numGames() + ",");
        sb.append("Rush Yards,");
        sb.append(this.getRankStr(this.rankTeamRushYards) + "%\n");
        sb.append(this.teamOppPassYards / this.numGames() + ",");
        sb.append("Opp Pass YPG,");
        sb.append(this.getRankStr(this.rankTeamOppPassYards) + "%\n");
        sb.append(this.teamOppRushYards / this.numGames() + ",");
        sb.append("Opp Rush YPG,");
        sb.append(this.getRankStr(this.rankTeamOppRushYards) + "%\n");
        if (this.teamTODiff > 0) {
            sb.append("+" + this.teamTODiff + ",");
        }
        else {
            sb.append(this.teamTODiff + ",");
        }
        sb.append("TO Diff,");
        sb.append(this.getRankStr(this.rankTeamTODiff) + "%\n");
        sb.append(this.teamOffTalent + ",");
        sb.append("Off Talent,");
        sb.append(this.getRankStr(this.rankTeamOffTalent) + "%\n");
        sb.append(this.teamDefTalent + ",");
        sb.append("Def Talent,");
        sb.append(this.getRankStr(this.rankTeamDefTalent) + "%\n");
        sb.append("$" + this.getTotalSalary() + ",");
        sb.append("Total Salary,");
        sb.append("$" + this.getSalaryCapRoom() + " room%\n");
        sb.append("PROFILE_BUTTON");
        return sb.toString();
    }
    
    public TeamStrategy[] getTeamStrategiesDef() {
        return new TeamStrategy[] { new TeamStrategy("Stack the Box", "Focus on stopping the run. Will give up more big passing plays but will allow less rushing yards and far less big plays from rushing.", 1, 0, -1, -1), new TeamStrategy("No Preference", "Will play a normal defense with no bonus either way, but no penalties either.", 0, 0, 0, 0), new TeamStrategy("No Fly Zone", "Focus on stopping the pass. Will give up less yards on catches and will be more likely to intercept passes, but will allow more rushing yards.", -1, 0, 1, 1) };
    }
    
    public TeamStrategy[] getTeamStrategiesOff() {
        return new TeamStrategy[] { new TeamStrategy("Aggressive", "Play a more aggressive offense. Will pass with lower completion percentage and higher chance of interception. However, catches will go for more yards.", -1, 2, 3, 1), new TeamStrategy("No Preference", "Will play a normal offense with no bonus either way, but no penalties either.", 0, 0, 0, 0), new TeamStrategy("Conservative", "Play a more conservative offense, running a bit more and passing slightly less. Passes are more accurate but shorter. Rushes are more likely to gain yards but less likely to break free for big plays.", 1, -2, -3, -1) };
    }
    
    public double getTotalSalary() {
        double n = 0.0;
        for (final Player player : this.getAllPlayers()) {
            if (player.getContract() != null) {
                n += player.getContract().getMoneyPerYear();
            }
            else {
                System.out.println("NULL CONTRACT: " + player.getPosNameYrOvrPot_OneLine() + " from team " + player.team.abbr);
            }
        }
        return (int)(10.0 * n) / 10.0;
    }
    
    public int getValueAdded(final Player player) {
        if (player instanceof PlayerQB) {
            return this.getValueAddedList(this.teamQBs, player, 1);
        }
        if (player instanceof PlayerRB) {
            return this.getValueAddedList(this.teamRBs, player, 2);
        }
        if (player instanceof PlayerWR) {
            return this.getValueAddedList(this.teamWRs, player, 3);
        }
        if (player instanceof PlayerOL) {
            return this.getValueAddedList(this.teamOLs, player, 5);
        }
        if (player instanceof PlayerK) {
            return this.getValueAddedList(this.teamKs, player, 1);
        }
        if (player instanceof PlayerS) {
            return this.getValueAddedList(this.teamSs, player, 1);
        }
        if (player instanceof PlayerCB) {
            return this.getValueAddedList(this.teamCBs, player, 3);
        }
        if (player instanceof PlayerDL) {
            return this.getValueAddedList(this.teamDLs, player, 4);
        }
        if (player instanceof PlayerLB) {
            return this.getValueAddedList(this.teamLBs, player, 3);
        }
        return 0;
    }
    
    public PlayerWR getWR(final int n) {
        if (n < this.teamWRs.size() && n >= 0) {
            return this.teamWRs.get(n);
        }
        return this.teamWRs.get(0);
    }
    
    public int getYPG() {
        return this.teamYards / this.numGames();
    }
    
    public int numGames() {
        if (this.wins + this.losses > 0) {
            return this.wins + this.losses;
        }
        return 1;
    }
    
    public void recruitUDFA() {
        for (int size = this.teamQBs.size(), i = 0; i < 2 - size; ++i) {
            this.teamQBs.add(new PlayerQB(this.league.getRandName(), this));
        }
        for (int size2 = this.teamRBs.size(), j = 0; j < 4 - size2; ++j) {
            this.teamRBs.add(new PlayerRB(this.league.getRandName(), this));
        }
        for (int size3 = this.teamWRs.size(), k = 0; k < 6 - size3; ++k) {
            this.teamWRs.add(new PlayerWR(this.league.getRandName(), this));
        }
        for (int size4 = this.teamOLs.size(), l = 0; l < 10 - size4; ++l) {
            this.teamOLs.add(new PlayerOL(this.league.getRandName(), this));
        }
        for (int size5 = this.teamKs.size(), n = 0; n < 2 - size5; ++n) {
            this.teamKs.add(new PlayerK(this.league.getRandName(), this));
        }
        for (int size6 = this.teamSs.size(), n2 = 0; n2 < 2 - size6; ++n2) {
            this.teamSs.add(new PlayerS(this.league.getRandName(), this));
        }
        for (int size7 = this.teamCBs.size(), n3 = 0; n3 < 6 - size7; ++n3) {
            this.teamCBs.add(new PlayerCB(this.league.getRandName(), this));
        }
        for (int size8 = this.teamDLs.size(), n4 = 0; n4 < 8 - size8; ++n4) {
            this.teamDLs.add(new PlayerDL(this.league.getRandName(), this));
        }
        for (int size9 = this.teamLBs.size(), n5 = 0; n5 < 8 - size9; ++n5) {
            this.teamLBs.add(new PlayerLB(this.league.getRandName(), this));
        }
        this.sortPlayers();
    }
    
    public void removePlayer(final Player player) {
        if (player instanceof PlayerQB) {
            this.teamQBs.remove(player);
            return;
        }
        if (player instanceof PlayerRB) {
            this.teamRBs.remove(player);
            return;
        }
        if (player instanceof PlayerWR) {
            this.teamWRs.remove(player);
            return;
        }
        if (player instanceof PlayerOL) {
            this.teamOLs.remove(player);
            return;
        }
        if (player instanceof PlayerK) {
            this.teamKs.remove(player);
            return;
        }
        if (player instanceof PlayerS) {
            this.teamSs.remove(player);
            return;
        }
        if (player instanceof PlayerCB) {
            this.teamCBs.remove(player);
            return;
        }
        if (player instanceof PlayerDL) {
            this.teamDLs.remove(player);
            return;
        }
        if (player instanceof PlayerLB) {
            this.teamLBs.remove(player);
            return;
        }
        System.out.println(this.abbr + " couldn't find to remove " + player.getPosNameYrOvrPot_OneLine());
    }
    
    public void resetStats() {
        this.gameSchedule = new ArrayList<Game>();
        this.regSeasonSchedule = new Game[16];
        this.gameWinsAgainst = new ArrayList<Team>();
        this.gameWLSchedule = new ArrayList<String>();
        this.divChampion = "";
        this.natChampWL = "";
        this.teamPoints = 0;
        this.teamOppPoints = 0;
        this.teamYards = 0;
        this.teamOppYards = 0;
        this.teamPassYards = 0;
        this.teamRushYards = 0;
        this.teamOppPassYards = 0;
        this.teamOppRushYards = 0;
        this.teamTODiff = 0;
    }
    
    public String seasonSummaryStr() {
        final String string = "Your team, " + this.name + ", finished the season with " + this.wins + " wins and " + this.losses + " losses.";
        if (this.natChampWL.equals("CBW")) {
            return string + "\n\nYou won the Champ Bowl! Congratulations! There are parades in the street celebrating your achievement.";
        }
        if (this.divChampion.equals("DW")) {
            return string + "\n\nEven though you didn't win the Champ Bowl, your team still won their division and your fanbase is pleased.";
        }
        if (this.league.playoffsNA.contains(this) || this.league.playoffsAM.contains(this)) {
            return string + "\n\nEven though you didn't win the Champ Bowl, you still made the playoffs.";
        }
        return string + "\n\nYour team failed to make the playoffs this year, which is disappointing. Hopefully you can make the jump next year.";
    }
    
    public void setInitialStats() {
        this.signUDFAs();
        this.sortPlayers();
        this.totalWins = 0;
        this.totalLosses = 0;
        this.winStreak = new TeamStreak(this.league.getYear(), this.league.getYear(), 0, this.abbr);
        this.yearStartWinStreak = new TeamStreak(this.league.getYear(), this.league.getYear(), 0, this.abbr);
        this.totalDivChamps = 0;
        this.totalPlayoffWins = 0;
        this.totalPlayoffLosses = 0;
        this.totalSuperBowlLosses = 0;
        this.totalSuperBowlWins = 0;
        this.gameWinsAgainst = new ArrayList<Team>();
        this.gameWLSchedule = new ArrayList<String>();
        this.divChampion = "";
        this.natChampWL = "";
        this.teamPoints = 0;
        this.teamOppPoints = 0;
        this.teamYards = 0;
        this.teamOppYards = 0;
        this.teamPassYards = 0;
        this.teamRushYards = 0;
        this.teamOppPassYards = 0;
        this.teamOppRushYards = 0;
        this.teamTODiff = 0;
        this.teamOffTalent = this.getOffTalent();
        this.teamDefTalent = this.getDefTalent();
        this.teamPollScore = this.teamPrestige + this.getOffTalent() + this.getDefTalent();
        this.teamStratOff = new TeamStrategy();
        this.teamStratDef = new TeamStrategy();
        this.teamStratOffNum = 1;
        this.teamStratDefNum = 1;
        this.numRecruits = 30;
        this.playersRetiring = new ArrayList<Player>();
        this.playersFAs = new ArrayList<Player>();
        this.positionalDraftPicks = new ArrayList<DraftPick>();
        this.draftPicks = new ArrayList<DraftPick>();
        for (int i = 0; i < 2; ++i) {
            for (int j = 0; j < 7; ++j) {
                this.draftPicks.add(new DraftPick(i + 2016, j + 1, this, this));
            }
        }
    }
    
    public void setIsStarters(final ArrayList<? extends Player> list, final int n) {
        for (int i = 0; i < list.size(); ++i) {
            if (i < n) {
                ((Player)list.get(i)).isStarter = true;
            }
            else {
                ((Player)list.get(i)).isStarter = false;
            }
        }
    }
    
    public void setStarters(final ArrayList<Player> list, final int n) {
        switch (n) {
            case 0: {
                final ArrayList<PlayerQB> list2 = new ArrayList<PlayerQB>();
                list2.addAll(this.teamQBs);
                this.teamQBs.clear();
                final Iterator<Player> iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.teamQBs.add((PlayerQB)iterator.next());
                }
                Collections.sort(this.teamQBs, new PlayerComparator());
                for (final PlayerQB playerQB : list2) {
                    if (!this.teamQBs.contains(playerQB)) {
                        this.teamQBs.add(playerQB);
                    }
                }
                break;
            }
            case 1: {
                final ArrayList<PlayerRB> list3 = new ArrayList<PlayerRB>();
                list3.addAll(this.teamRBs);
                this.teamRBs.clear();
                final Iterator<Player> iterator3 = list.iterator();
                while (iterator3.hasNext()) {
                    this.teamRBs.add((PlayerRB)iterator3.next());
                }
                Collections.sort(this.teamRBs, new PlayerComparator());
                for (final PlayerRB playerRB : list3) {
                    if (!this.teamRBs.contains(playerRB)) {
                        this.teamRBs.add(playerRB);
                    }
                }
                break;
            }
            case 2: {
                final ArrayList<PlayerWR> list4 = new ArrayList<PlayerWR>();
                list4.addAll(this.teamWRs);
                this.teamWRs.clear();
                final Iterator<Player> iterator5 = list.iterator();
                while (iterator5.hasNext()) {
                    this.teamWRs.add((PlayerWR)iterator5.next());
                }
                Collections.sort(this.teamWRs, new PlayerComparator());
                for (final PlayerWR playerWR : list4) {
                    if (!this.teamWRs.contains(playerWR)) {
                        this.teamWRs.add(playerWR);
                    }
                }
                break;
            }
            case 3: {
                final ArrayList<PlayerOL> list5 = new ArrayList<PlayerOL>();
                list5.addAll(this.teamOLs);
                this.teamOLs.clear();
                final Iterator<Player> iterator7 = list.iterator();
                while (iterator7.hasNext()) {
                    this.teamOLs.add((PlayerOL)iterator7.next());
                }
                Collections.sort(this.teamOLs, new PlayerComparator());
                for (final PlayerOL playerOL : list5) {
                    if (!this.teamOLs.contains(playerOL)) {
                        this.teamOLs.add(playerOL);
                    }
                }
                break;
            }
            case 4: {
                final ArrayList<PlayerK> list6 = new ArrayList<PlayerK>();
                list6.addAll(this.teamKs);
                this.teamKs.clear();
                final Iterator<Player> iterator9 = list.iterator();
                while (iterator9.hasNext()) {
                    this.teamKs.add((PlayerK)iterator9.next());
                }
                Collections.sort(this.teamKs, new PlayerComparator());
                for (final PlayerK playerK : list6) {
                    if (!this.teamKs.contains(playerK)) {
                        this.teamKs.add(playerK);
                    }
                }
                break;
            }
            case 5: {
                final ArrayList<PlayerS> list7 = new ArrayList<PlayerS>();
                list7.addAll(this.teamSs);
                this.teamSs.clear();
                final Iterator<Player> iterator11 = list.iterator();
                while (iterator11.hasNext()) {
                    this.teamSs.add((PlayerS)iterator11.next());
                }
                Collections.sort(this.teamSs, new PlayerComparator());
                for (final PlayerS playerS : list7) {
                    if (!this.teamSs.contains(playerS)) {
                        this.teamSs.add(playerS);
                    }
                }
                break;
            }
            case 6: {
                final ArrayList<PlayerCB> list8 = new ArrayList<PlayerCB>();
                list8.addAll(this.teamCBs);
                this.teamCBs.clear();
                final Iterator<Player> iterator13 = list.iterator();
                while (iterator13.hasNext()) {
                    this.teamCBs.add((PlayerCB)iterator13.next());
                }
                Collections.sort(this.teamCBs, new PlayerComparator());
                for (final PlayerCB playerCB : list8) {
                    if (!this.teamCBs.contains(playerCB)) {
                        this.teamCBs.add(playerCB);
                    }
                }
                break;
            }
            case 7: {
                final ArrayList<PlayerDL> list9 = new ArrayList<PlayerDL>();
                list9.addAll(this.teamDLs);
                this.teamDLs.clear();
                final Iterator<Player> iterator15 = list.iterator();
                while (iterator15.hasNext()) {
                    this.teamDLs.add((PlayerDL)iterator15.next());
                }
                Collections.sort(this.teamDLs, new PlayerComparator());
                for (final PlayerDL playerDL : list9) {
                    if (!this.teamDLs.contains(playerDL)) {
                        this.teamDLs.add(playerDL);
                    }
                }
                break;
            }
            case 8: {
                final ArrayList<PlayerLB> list10 = new ArrayList<PlayerLB>();
                list10.addAll(this.teamLBs);
                this.teamLBs.clear();
                final Iterator<Player> iterator17 = list.iterator();
                while (iterator17.hasNext()) {
                    this.teamLBs.add((PlayerLB)iterator17.next());
                }
                Collections.sort(this.teamLBs, new PlayerComparator());
                for (final PlayerLB playerLB : list10) {
                    if (!this.teamLBs.contains(playerLB)) {
                        this.teamLBs.add(playerLB);
                    }
                }
                break;
            }
        }
        this.league.setTeamRanks();
    }
    
    public void setVarsFromCSV(final String s) {
        boolean showPopups = false;
        final String[] split = s.split(",");
        if (split.length == 2) {
            this.name = split[0].trim();
            this.abbr = split[1].trim();
            return;
        }
        this.division = split[0];
        this.name = split[1];
        this.abbr = split[2];
        this.totalPlayoffWins = Integer.parseInt(split[3]);
        this.totalPlayoffLosses = Integer.parseInt(split[4]);
        this.totalSuperBowlWins = Integer.parseInt(split[5]);
        this.totalSuperBowlLosses = Integer.parseInt(split[6]);
        this.totalWins = Integer.parseInt(split[7]);
        this.totalLosses = Integer.parseInt(split[8]);
        this.teamStratOffNum = Integer.parseInt(split[9]);
        this.teamStratDefNum = Integer.parseInt(split[10]);
        if (Integer.parseInt(split[11]) == 1) {
            showPopups = true;
        }
        this.showPopups = showPopups;
        this.winStreak = new TeamStreak(Integer.parseInt(split[14]), Integer.parseInt(split[15]), Integer.parseInt(split[12]), split[13]);
        this.wins = Integer.parseInt(split[16]);
        this.losses = Integer.parseInt(split[17]);
        this.teamPoints = Integer.parseInt(split[18]);
        this.teamOppPoints = Integer.parseInt(split[19]);
        this.teamYards = Integer.parseInt(split[20]);
        this.teamOppYards = Integer.parseInt(split[21]);
        this.teamPassYards = Integer.parseInt(split[22]);
        this.teamRushYards = Integer.parseInt(split[23]);
        this.teamOppPassYards = Integer.parseInt(split[24]);
        this.teamOppRushYards = Integer.parseInt(split[25]);
        this.teamTODiff = Integer.parseInt(split[26]);
        this.divChampion = split[27].substring(1);
        this.natChampWL = split[28].substring(1);
        this.totalDivChamps = Integer.parseInt(split[29]);
    }
    
    public void signUDFAs() {
        for (int needsPosition = this.getNeedsPosition("QB"), i = 0; i < needsPosition; ++i) {
            final PlayerQB playerQB = new PlayerQB(this.league.getRandName(), this);
            playerQB.setRatingsUDFA();
            playerQB.setContract(new Contract(playerQB, 1, 0.5));
            this.addPlayer(playerQB);
        }
        for (int needsPosition2 = this.getNeedsPosition("RB"), j = 0; j < needsPosition2; ++j) {
            final PlayerRB playerRB = new PlayerRB(this.league.getRandName(), this);
            playerRB.setRatingsUDFA();
            playerRB.setContract(new Contract(playerRB, 1, 0.5));
            this.addPlayer(playerRB);
        }
        for (int needsPosition3 = this.getNeedsPosition("WR"), k = 0; k < needsPosition3; ++k) {
            final PlayerWR playerWR = new PlayerWR(this.league.getRandName(), this);
            playerWR.setRatingsUDFA();
            playerWR.setContract(new Contract(playerWR, 1, 0.5));
            this.addPlayer(playerWR);
        }
        for (int needsPosition4 = this.getNeedsPosition("OL"), l = 0; l < needsPosition4; ++l) {
            final PlayerOL playerOL = new PlayerOL(this.league.getRandName(), this);
            playerOL.setRatingsUDFA();
            playerOL.setContract(new Contract(playerOL, 1, 0.5));
            this.addPlayer(playerOL);
        }
        for (int needsPosition5 = this.getNeedsPosition("K"), n = 0; n < needsPosition5; ++n) {
            final PlayerK playerK = new PlayerK(this.league.getRandName(), this);
            playerK.setRatingsUDFA();
            playerK.setContract(new Contract(playerK, 1, 0.5));
            this.addPlayer(playerK);
        }
        for (int needsPosition6 = this.getNeedsPosition("S"), n2 = 0; n2 < needsPosition6; ++n2) {
            final PlayerS playerS = new PlayerS(this.league.getRandName(), this);
            playerS.setRatingsUDFA();
            playerS.setContract(new Contract(playerS, 1, 0.5));
            this.addPlayer(playerS);
        }
        for (int needsPosition7 = this.getNeedsPosition("CB"), n3 = 0; n3 < needsPosition7; ++n3) {
            final PlayerCB playerCB = new PlayerCB(this.league.getRandName(), this);
            playerCB.setRatingsUDFA();
            playerCB.setContract(new Contract(playerCB, 1, 0.5));
            this.addPlayer(playerCB);
        }
        for (int needsPosition8 = this.getNeedsPosition("DL"), n4 = 0; n4 < needsPosition8; ++n4) {
            final PlayerDL playerDL = new PlayerDL(this.league.getRandName(), this);
            playerDL.setRatingsUDFA();
            playerDL.setContract(new Contract(playerDL, 1, 0.5));
            this.addPlayer(playerDL);
        }
        for (int needsPosition9 = this.getNeedsPosition("LB"), n5 = 0; n5 < needsPosition9; ++n5) {
            final PlayerLB playerLB = new PlayerLB(this.league.getRandName(), this);
            playerLB.setRatingsUDFA();
            playerLB.setContract(new Contract(playerLB, 1, 0.5));
            this.addPlayer(playerLB);
        }
    }
    
    public void sortPlayers() {
        Collections.sort(this.teamQBs, new PlayerComparator());
        this.setIsStarters(this.teamQBs, 1);
        Collections.sort(this.teamRBs, new PlayerComparator());
        this.setIsStarters(this.teamRBs, 2);
        Collections.sort(this.teamWRs, new PlayerComparator());
        this.setIsStarters(this.teamWRs, 3);
        Collections.sort(this.teamKs, new PlayerComparator());
        this.setIsStarters(this.teamKs, 1);
        Collections.sort(this.teamOLs, new PlayerComparator());
        this.setIsStarters(this.teamOLs, 5);
        Collections.sort(this.teamCBs, new PlayerComparator());
        this.setIsStarters(this.teamCBs, 3);
        Collections.sort(this.teamSs, new PlayerComparator());
        this.setIsStarters(this.teamSs, 1);
        Collections.sort(this.teamDLs, new PlayerComparator());
        this.setIsStarters(this.teamDLs, 4);
        Collections.sort(this.teamLBs, new PlayerComparator());
        this.setIsStarters(this.teamLBs, 3);
    }
    
    public String strRep() {
        return "#" + this.rankTeamPollScore + " " + this.abbr + " (" + this.wins + "-" + this.losses + ")";
    }
    
    public String strRepWithBowlResults() {
        return "#" + this.rankTeamPollScore + " " + this.abbr + " (" + this.wins + "-" + this.losses + ") " + this.divChampion + " " + this.natChampWL;
    }
    
    public void updatePollScore() {
        this.updateStrengthOfWins();
        int n;
        if ((n = 8 - (this.wins + this.losses)) < 0) {
            n = 0;
        }
        this.teamPollScore = (this.wins * 200 + (this.teamPoints - this.teamOppPoints) * 3 + (this.teamYards - this.teamOppYards) / 40 + n * 3 * (this.teamPrestige + this.getOffTalent() + this.getDefTalent()) + this.teamStrengthOfWins) / 10;
        if ("DW".equals(this.divChampion)) {
            this.teamPollScore += 50;
        }
        if ("CBW".equals(this.natChampWL)) {
            this.teamPollScore += 1000;
        }
        if (this.losses == 0) {
            this.teamPollScore += 30;
        }
        else if (this.losses == 1) {
            this.teamPollScore += 15;
        }
        this.teamOffTalent = this.getOffTalent();
        this.teamDefTalent = this.getDefTalent();
    }
    
    public void updateStrengthOfWins() {
        int teamStrengthOfWins = 0;
        for (int i = 0; i < this.gameSchedule.size(); ++i) {
            final Game game = this.gameSchedule.get(i);
            if (game.getWinner() == this) {
                teamStrengthOfWins += game.getLoser().wins * 2;
            }
            else {
                teamStrengthOfWins += game.getWinner().wins - 1;
            }
        }
        this.teamStrengthOfWins = teamStrengthOfWins;
    }
    
    public void updateTalentRatings() {
        this.teamOffTalent = this.getOffTalent();
        this.teamDefTalent = this.getDefTalent();
        this.teamPollScore = this.teamPrestige + this.getOffTalent() + this.getDefTalent();
    }
    
    public void updateTeamHistory() {
        String s = this.league.getYear() + ": #" + this.rankTeamPollScore + " " + this.abbr + " (" + this.wins + "-" + this.losses + ") " + this.divChampion + " " + this.natChampWL;
        String string;
        if (this.gameSchedule.size() > 16) {
            int n = 16;
            while (true) {
                string = s;
                if (n >= this.gameSchedule.size()) {
                    break;
                }
                final String string2 = s + ">" + this.gameSchedule.get(n).gameName + ": ";
                final String[] gameSummaryStr = this.getGameSummaryStr(n);
                s = string2 + gameSummaryStr[1] + " " + gameSummaryStr[2];
                ++n;
            }
        }
        else {
            string = s + ">Missed Playoffs";
        }
        this.teamHistory.add(string);
    }
    
    public String weekSummaryStr() {
        final int n = this.wins + this.losses - 1;
        return this.name + " " + (this.gameWLSchedule.get(n) + " " + this.gameSummaryStr(this.gameSchedule.get(n))) + "\nNew power rank: #" + this.rankTeamPollScore + " " + this.abbr + " (" + this.wins + "-" + this.losses + ")";
    }
}
