// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerRB extends Player
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
    
    public PlayerRB(final Team team, final String[] array) {
        super(team, array);
        this.statsRushAtt = 0;
        this.statsRushYards = 0;
        this.statsTD = 0;
        this.statsFumbles = 0;
        this.careerRushAtt = 0;
        this.careerRushYards = 0;
        this.careerTDs = 0;
        this.careerFumbles = 0;
    }
    
    public PlayerRB(final String s, final int n) {
        super(s, "RB", n);
        this.statsRushAtt = 0;
        this.statsRushYards = 0;
        this.statsTD = 0;
        this.statsFumbles = 0;
        this.careerRushAtt = 0;
        this.careerRushYards = 0;
        this.careerTDs = 0;
        this.careerFumbles = 0;
    }
    
    public PlayerRB(final String s, final Team team) {
        super(s, team, "RB", true);
        this.statsRushAtt = 0;
        this.statsRushYards = 0;
        this.statsTD = 0;
        this.statsFumbles = 0;
        this.careerRushAtt = 0;
        this.careerRushYards = 0;
        this.careerTDs = 0;
        this.careerFumbles = 0;
    }
    
    public PlayerRB(final String s, final Team team, final int[] array, final String s2, final int[] array2) {
        super(s, team, "RB", false);
        this.setVariables(array, s2);
        this.statsRushAtt = array2[0];
        this.statsRushYards = array2[1];
        this.statsTD = array2[2];
        this.statsFumbles = array2[3];
        this.careerRushAtt = array2[4];
        this.careerRushYards = array2[5];
        this.careerTDs = array2[6];
        this.careerFumbles = array2[7];
    }
    
    @Override
    public void advanceSeason() {
        super.advanceSeason();
        this.careerRushAtt += this.statsRushAtt;
        this.careerRushYards += this.statsRushYards;
        this.careerTDs += this.statsTD;
        this.careerFumbles += this.statsFumbles;
        this.statsRushAtt = 0;
        this.statsRushYards = 0;
        this.statsTD = 0;
        this.statsFumbles = 0;
    }
    
    @Override
    public String getCSV() {
        return super.getCSV() + ">" + this.statsRushAtt + "," + this.statsRushYards + "," + this.statsTD + "," + this.statsFumbles + "," + this.careerRushAtt + "," + this.careerRushYards + "," + this.careerTDs + "," + this.careerFumbles;
    }
    
    @Override
    public ArrayList<String> getCareerStatsList() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("TDs: " + (this.statsTD + this.careerTDs) + ">Fumbles: " + (this.statsFumbles + this.careerFumbles));
        list.add("Rush Yards: " + (this.statsRushYards + this.careerRushYards) + " yds>Yards/Att: " + (this.statsRushYards + this.careerRushYards) * 10 / (this.statsRushAtt + this.careerRushAtt + 1) / 10.0 + " yds");
        list.add("Yds/Game: " + (this.statsRushYards + this.careerRushYards) / (this.getGamesPlayed() + this.careerGamesPlayed) + " yds/g>Rush Att: " + (this.statsRushAtt + this.careerRushAtt));
        list.addAll(super.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("TDs: " + this.statsTD + ">Fumbles: " + this.statsFumbles);
        list.add("Rush Yards: " + this.statsRushYards + " yds>Yards/Att: " + this.statsRushYards * 10 / (this.statsRushAtt + 1) / 10.0 + " yds");
        list.add("Yds/Game: " + this.statsRushYards / this.getGamesPlayed() + " yds/g>Rush Att: " + this.statsRushAtt);
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Rush Power: " + Player.getLetterGrade(this.ratRushPow));
        list.add("Rush Speed: " + Player.getLetterGrade(this.ratRushSpd) + ">Evasion: " + Player.getLetterGrade(this.ratRushEva));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("TDs: " + this.statsTD + ">Fumbles: " + this.statsFumbles);
        list.add("Rush Yards: " + this.statsRushYards + " yds>Yards/Att: " + this.statsRushYards * 10 / (this.statsRushAtt + 1) / 10.0 + " yds");
        list.add("Yds/Game: " + this.statsRushYards / this.getGamesPlayed() + " yds/g>Rush Att: " + this.statsRushAtt);
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Rush Power: " + Player.getLetterGrade(this.ratRushPow));
        list.add("Rush Speed: " + Player.getLetterGrade(this.ratRushSpd) + ">Evasion: " + Player.getLetterGrade(this.ratRushEva));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsOffseason() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Potential: " + Player.getLetterGrade(this.ratPot) + ">Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Rush Power: " + Player.getLetterGrade(this.ratRushPow));
        list.add("Rush Speed: " + Player.getLetterGrade(this.ratRushSpd) + ">Evasion: " + Player.getLetterGrade(this.ratRushEva));
        list.add("Desired Contract: " + Contract.getContractFA(this).toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public String getInfoForLineup() {
        if (this.injury != null) {
            return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " " + this.injury.toString();
        }
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratRushPow) + ", " + Player.getLetterGrade(this.ratRushSpd) + ", " + Player.getLetterGrade(this.ratRushEva) + ")";
    }
    
    @Override
    public int getMVPScore() {
        return this.statsTD * 100 - this.statsFumbles * 50 + (int)(this.statsRushYards * 2.4);
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratRushPow, this.ratRushSpd, this.ratRushEva };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratRushPow + this.ratRushSpd + this.ratRushEva) / 3;
    }
    
    public void setRatings(final int[] array) {
        this.ratRushPow = array[0];
        this.ratRushSpd = array[1];
        this.ratRushEva = array[2];
        this.setOvr();
    }
}
