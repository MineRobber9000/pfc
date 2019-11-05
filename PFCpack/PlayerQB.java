// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerQB extends Player
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
    
    public PlayerQB(final Team team, final String[] array) {
        super(team, array);
        this.statsPassAtt = 0;
        this.statsPassComp = 0;
        this.statsTD = 0;
        this.statsInt = 0;
        this.statsPassYards = 0;
        this.statsSacked = 0;
        this.careerPassAtt = 0;
        this.careerPassComp = 0;
        this.careerTDs = 0;
        this.careerInt = 0;
        this.careerPassYards = 0;
        this.careerSacked = 0;
    }
    
    public PlayerQB(final String s, final int n) {
        super(s, "QB", n);
        this.statsPassAtt = 0;
        this.statsPassComp = 0;
        this.statsTD = 0;
        this.statsInt = 0;
        this.statsPassYards = 0;
        this.statsSacked = 0;
        this.careerPassAtt = 0;
        this.careerPassComp = 0;
        this.careerTDs = 0;
        this.careerInt = 0;
        this.careerPassYards = 0;
        this.careerSacked = 0;
    }
    
    public PlayerQB(final String s, final Team team) {
        super(s, team, "QB", true);
        this.statsPassAtt = 0;
        this.statsPassComp = 0;
        this.statsTD = 0;
        this.statsInt = 0;
        this.statsPassYards = 0;
        this.statsSacked = 0;
        this.careerPassAtt = 0;
        this.careerPassComp = 0;
        this.careerTDs = 0;
        this.careerInt = 0;
        this.careerPassYards = 0;
        this.careerSacked = 0;
    }
    
    public PlayerQB(final String s, final Team team, final int[] array, final String s2, final int[] array2) {
        super(s, team, "QB", false);
        this.setVariables(array, s2);
        this.statsPassAtt = array2[0];
        this.statsPassComp = array2[1];
        this.statsTD = array2[2];
        this.statsInt = array2[3];
        this.statsPassYards = array2[4];
        this.statsSacked = array2[5];
        this.careerPassAtt = array2[6];
        this.careerPassComp = array2[7];
        this.careerTDs = array2[8];
        this.careerInt = array2[9];
        this.careerPassYards = array2[10];
        this.careerSacked = array2[11];
    }
    
    @Override
    public void advanceSeason() {
        super.advanceSeason();
        this.careerPassAtt += this.statsPassAtt;
        this.careerPassComp += this.statsPassComp;
        this.careerTDs += this.statsTD;
        this.careerInt += this.statsInt;
        this.careerPassYards += this.statsPassYards;
        this.careerSacked += this.statsSacked;
        this.statsPassAtt = 0;
        this.statsPassComp = 0;
        this.statsTD = 0;
        this.statsInt = 0;
        this.statsPassYards = 0;
        this.statsSacked = 0;
    }
    
    @Override
    public String getCSV() {
        return super.getCSV() + ">" + this.statsPassAtt + "," + this.statsPassComp + "," + this.statsTD + "," + this.statsInt + "," + this.statsPassYards + "," + this.statsSacked + "," + this.careerPassAtt + "," + this.careerPassComp + "," + this.careerTDs + "," + this.careerInt + "," + this.careerPassYards + "," + this.careerSacked;
    }
    
    @Override
    public ArrayList<String> getCareerStatsList() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("TD/Int: " + (this.statsTD + this.careerTDs) + "/" + (this.statsInt + this.careerInt) + ">Comp Percent: " + (this.statsPassComp + this.careerPassComp) * 100 / (this.statsPassAtt + this.careerPassAtt + 1) + "%");
        list.add("Pass Yards: " + (this.statsPassYards + this.careerPassYards) + " yds>Yards/Att: " + (this.statsPassYards + this.careerPassYards) * 10 / (this.statsPassAtt + this.careerPassAtt + 1) / 10.0 + " yds");
        list.add("Yds/Game: " + (this.statsPassYards + this.careerPassYards) / (this.getGamesPlayed() + this.careerGamesPlayed) + " yds/g>Sacks: " + (this.statsSacked + this.careerSacked));
        list.addAll(super.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("TD/Int: " + this.statsTD + "/" + this.statsInt + ">Comp Percent: " + this.statsPassComp * 100 / (this.statsPassAtt + 1) + "%");
        list.add("Pass Yards: " + this.statsPassYards + " yds>Yards/Att: " + this.statsPassYards * 10 / (this.statsPassAtt + 1) / 10.0 + " yds");
        list.add("Yds/Game: " + this.statsPassYards / this.getGamesPlayed() + " yds/g>Sacks: " + this.statsSacked);
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Pass Strength: " + Player.getLetterGrade(this.ratPassPow));
        list.add("Accuracy: " + Player.getLetterGrade(this.ratPassAcc) + ">Evasion: " + Player.getLetterGrade(this.ratPassEva));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("TD/Int: " + this.statsTD + "/" + this.statsInt + ">Comp Percent: " + this.statsPassComp * 100 / (this.statsPassAtt + 1) + "%");
        list.add("Pass Yards: " + this.statsPassYards + " yds>Yards/Att: " + this.statsPassYards * 10 / (this.statsPassAtt + 1) / 10.0 + " yds");
        list.add("Yds/Game: " + this.statsPassYards / this.getGamesPlayed() + " yds/g>Sacks: " + this.statsSacked);
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Pass Strength: " + Player.getLetterGrade(this.ratPassPow));
        list.add("Accuracy: " + Player.getLetterGrade(this.ratPassAcc) + ">Evasion: " + Player.getLetterGrade(this.ratPassEva));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsOffseason() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Potential: " + Player.getLetterGrade(this.ratPot) + ">Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Pass Strength: " + Player.getLetterGrade(this.ratPassPow));
        list.add("Accuracy: " + Player.getLetterGrade(this.ratPassAcc) + ">Evasion: " + Player.getLetterGrade(this.ratPassEva));
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
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratPassPow) + ", " + Player.getLetterGrade(this.ratPassAcc) + ", " + Player.getLetterGrade(this.ratPassEva) + ")";
    }
    
    @Override
    public int getMVPScore() {
        return this.statsTD * 140 - this.statsInt * 250 + this.statsPassYards;
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratPassPow, this.ratPassAcc, this.ratPassEva };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratPassPow * 3 + this.ratPassAcc * 4 + this.ratPassEva) / 8;
    }
    
    public void setRatings(final int[] array) {
        this.ratPassPow = array[0];
        this.ratPassAcc = array[1];
        this.ratPassEva = array[2];
        this.setOvr();
    }
}
