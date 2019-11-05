// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerWR extends Player
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
    
    public PlayerWR(final Team team, final String[] array) {
        super(team, array);
        this.statsTargets = 0;
        this.statsReceptions = 0;
        this.statsRecYards = 0;
        this.statsTD = 0;
        this.statsDrops = 0;
        this.statsFumbles = 0;
        this.careerTargets = 0;
        this.careerReceptions = 0;
        this.careerRecYards = 0;
        this.careerTD = 0;
        this.careerDrops = 0;
        this.careerFumbles = 0;
    }
    
    public PlayerWR(final String s, final int n) {
        super(s, "WR", n);
        this.statsTargets = 0;
        this.statsReceptions = 0;
        this.statsRecYards = 0;
        this.statsTD = 0;
        this.statsDrops = 0;
        this.statsFumbles = 0;
        this.careerTargets = 0;
        this.careerReceptions = 0;
        this.careerRecYards = 0;
        this.careerTD = 0;
        this.careerDrops = 0;
        this.careerFumbles = 0;
    }
    
    public PlayerWR(final String s, final Team team) {
        super(s, team, "WR", true);
        this.statsTargets = 0;
        this.statsReceptions = 0;
        this.statsRecYards = 0;
        this.statsTD = 0;
        this.statsDrops = 0;
        this.statsFumbles = 0;
        this.careerTargets = 0;
        this.careerReceptions = 0;
        this.careerRecYards = 0;
        this.careerTD = 0;
        this.careerDrops = 0;
        this.careerFumbles = 0;
    }
    
    public PlayerWR(final String s, final Team team, final int[] array, final String s2, final int[] array2) {
        super(s, team, "WR", false);
        this.setVariables(array, s2);
        this.statsTargets = array2[0];
        this.statsReceptions = array2[1];
        this.statsRecYards = array2[2];
        this.statsTD = array2[3];
        this.statsDrops = array2[4];
        this.statsFumbles = array2[5];
        this.careerTargets = array2[6];
        this.careerReceptions = array2[7];
        this.careerRecYards = array2[8];
        this.careerTD = array2[9];
        this.careerDrops = array2[10];
        this.careerFumbles = array2[11];
    }
    
    @Override
    public void advanceSeason() {
        super.advanceSeason();
        this.careerTargets += this.statsTargets;
        this.careerReceptions += this.statsReceptions;
        this.careerRecYards += this.statsRecYards;
        this.careerTD += this.statsTD;
        this.careerDrops += this.statsDrops;
        this.careerFumbles += this.statsFumbles;
        this.statsTargets = 0;
        this.statsReceptions = 0;
        this.statsRecYards = 0;
        this.statsTD = 0;
        this.statsDrops = 0;
        this.statsFumbles = 0;
    }
    
    @Override
    public String getCSV() {
        return super.getCSV() + ">" + this.statsTargets + "," + this.statsReceptions + "," + this.statsRecYards + "," + this.statsTD + "," + this.statsDrops + "," + this.statsFumbles + "," + this.careerTargets + "," + this.careerReceptions + "," + this.careerRecYards + "," + this.careerTD + "," + this.careerDrops + "," + this.careerFumbles;
    }
    
    @Override
    public ArrayList<String> getCareerStatsList() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("TDs/Fumbles: " + (this.statsTD + this.careerTD) + "/" + (this.statsFumbles + this.careerFumbles) + ">Catch Percent: " + (this.statsReceptions + this.careerReceptions) * 100 / (this.statsTargets + this.careerTargets + 1) + "%");
        list.add("Rec Yards: " + (this.statsRecYards + this.careerRecYards) + " yds>Yards/Tgt: " + (this.statsRecYards + this.careerRecYards) * 10 / (this.statsTargets + this.careerTargets + 1) / 10.0 + " yds");
        list.add("Yds/Game: " + (this.statsRecYards + this.careerRecYards) / (this.getGamesPlayed() + this.careerGamesPlayed) + " yds/g>Drops: " + (this.statsDrops + this.careerDrops));
        list.addAll(super.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("TDs/Fumbles: " + this.statsTD + "/" + this.statsFumbles + ">Catch Percent: " + this.statsReceptions * 100 / (this.statsTargets + 1) + "%");
        list.add("Rec Yards: " + this.statsRecYards + " yds>Yards/Tgt: " + this.statsRecYards * 10 / (this.statsTargets + 1) / 10.0 + " yds");
        list.add("Yds/Game: " + this.statsRecYards / this.getGamesPlayed() + " yds/g>Drops: " + this.statsDrops);
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Catching: " + Player.getLetterGrade(this.ratRecCat));
        list.add("Rec Speed: " + Player.getLetterGrade(this.ratRecSpd) + ">Evasion: " + Player.getLetterGrade(this.ratRecEva));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("TDs/Fumbles: " + this.statsTD + "/" + this.statsFumbles + ">Catch Percent: " + this.statsReceptions * 100 / (this.statsTargets + 1) + "%");
        list.add("Rec Yards: " + this.statsRecYards + " yds>Yards/Tgt: " + this.statsRecYards * 10 / (this.statsTargets + 1) / 10.0 + " yds");
        list.add("Yds/Game: " + this.statsRecYards / this.getGamesPlayed() + " yds/g>Drops: " + this.statsDrops);
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Catching: " + Player.getLetterGrade(this.ratRecCat));
        list.add("Rec Speed: " + Player.getLetterGrade(this.ratRecSpd) + ">Evasion: " + Player.getLetterGrade(this.ratRecEva));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsOffseason() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Potential: " + Player.getLetterGrade(this.ratPot) + ">Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Catching: " + Player.getLetterGrade(this.ratRecCat));
        list.add("Rec Speed: " + Player.getLetterGrade(this.ratRecSpd) + ">Evasion: " + Player.getLetterGrade(this.ratRecEva));
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
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratRecCat) + ", " + Player.getLetterGrade(this.ratRecSpd) + ", " + Player.getLetterGrade(this.ratRecEva) + ")";
    }
    
    @Override
    public int getMVPScore() {
        return this.statsTD * 150 - this.statsFumbles * 100 - this.statsDrops * 50 + this.statsRecYards * 2;
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratRecCat, this.ratRecSpd, this.ratRecEva };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratRecCat * 2 + this.ratRecSpd + this.ratRecEva) / 4;
    }
    
    public void setRatings(final int[] array) {
        this.ratRecCat = array[0];
        this.ratRecSpd = array[1];
        this.ratRecEva = array[2];
        this.setOvr();
    }
}
