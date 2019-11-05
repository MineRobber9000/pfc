// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerK extends Player
{
    protected int careerFGAtt;
    protected int careerFGMade;
    protected int careerXPAtt;
    protected int careerXPMade;
    protected int ratKickAcc;
    protected int ratKickFum;
    protected int ratKickPow;
    protected int statsFGAtt;
    protected int statsFGMade;
    protected int statsXPAtt;
    protected int statsXPMade;
    
    public PlayerK(final Team team, final String[] array) {
        super(team, array);
        this.statsXPAtt = 0;
        this.statsXPMade = 0;
        this.statsFGAtt = 0;
        this.statsFGMade = 0;
        this.careerXPAtt = 0;
        this.careerXPMade = 0;
        this.careerFGAtt = 0;
        this.careerFGMade = 0;
    }
    
    public PlayerK(final String s, final int n) {
        super(s, "K", n);
        this.statsXPAtt = 0;
        this.statsXPMade = 0;
        this.statsFGAtt = 0;
        this.statsFGMade = 0;
        this.careerXPAtt = 0;
        this.careerXPMade = 0;
        this.careerFGAtt = 0;
        this.careerFGMade = 0;
    }
    
    public PlayerK(final String s, final Team team) {
        super(s, team, "K", true);
        this.statsXPAtt = 0;
        this.statsXPMade = 0;
        this.statsFGAtt = 0;
        this.statsFGMade = 0;
        this.careerXPAtt = 0;
        this.careerXPMade = 0;
        this.careerFGAtt = 0;
        this.careerFGMade = 0;
    }
    
    public PlayerK(final String s, final Team team, final int[] array, final String s2, final int[] array2) {
        super(s, team, "K", false);
        this.setVariables(array, s2);
        this.statsXPAtt = array2[0];
        this.statsXPMade = array2[1];
        this.statsFGAtt = array2[2];
        this.statsFGMade = array2[3];
        this.careerXPAtt = array2[4];
        this.careerXPMade = array2[5];
        this.careerFGAtt = array2[6];
        this.careerFGMade = array2[7];
    }
    
    @Override
    public void advanceSeason() {
        super.advanceSeason();
        this.careerXPAtt += this.statsXPAtt;
        this.careerXPMade += this.statsXPMade;
        this.careerFGAtt += this.statsFGAtt;
        this.careerFGMade += this.statsFGMade;
        this.statsXPAtt = 0;
        this.statsXPMade = 0;
        this.statsFGAtt = 0;
        this.statsFGMade = 0;
    }
    
    @Override
    public String getCSV() {
        return super.getCSV() + ">" + this.statsXPAtt + "," + this.statsXPMade + "," + this.statsFGAtt + "," + this.statsFGMade + "," + this.careerXPAtt + "," + this.careerXPMade + "," + this.careerFGAtt + "," + this.careerFGMade;
    }
    
    @Override
    public ArrayList<String> getCareerStatsList() {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.statsXPAtt + this.careerXPAtt > 0) {
            list.add("XP Made/Att: " + (this.statsXPMade + this.careerXPMade) + "/" + (this.statsXPAtt + this.careerXPAtt) + ">XP Percent: " + (this.statsXPMade + this.careerXPMade) * 100 / (this.statsXPAtt + this.careerXPAtt) + "%");
        }
        else {
            list.add("XP Made/Att: 0/0>XP Percent: 0%");
        }
        if (this.statsFGAtt + this.careerFGAtt > 0) {
            list.add("FG Made/Att: " + (this.statsFGMade + this.careerFGMade) + "/" + (this.statsFGAtt + this.careerFGAtt) + ">FG Percent: " + (this.statsFGMade + this.careerFGMade) * 100 / (this.statsFGAtt + this.careerFGAtt) + "%");
        }
        else {
            list.add("FG Made/Att: 0/0>FG Percent: 0%");
        }
        list.addAll(super.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.statsXPAtt > 0) {
            list.add("XP Made/Att: " + this.statsXPMade + "/" + this.statsXPAtt + ">XP Percent: " + this.statsXPMade * 100 / this.statsXPAtt + "%");
        }
        else {
            list.add("XP Made/Att: 0/0>XP Percent: 0%");
        }
        if (this.statsFGAtt > 0) {
            list.add("FG Made/Att: " + this.statsFGMade + "/" + this.statsFGAtt + ">FG Percent: " + this.statsFGMade * 100 / this.statsFGAtt + "%");
        }
        else {
            list.add("FG Made/Att: 0/0>FG Percent: 0%");
        }
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Kick Strength: " + Player.getLetterGrade(this.ratKickPow));
        list.add("Kick Accuracy: " + Player.getLetterGrade(this.ratKickAcc) + ">Clumsiness: " + Player.getLetterGrade(this.ratKickFum));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        if (this.statsXPAtt > 0) {
            list.add("XP Made/Att: " + this.statsXPMade + "/" + this.statsXPAtt + ">XP Percent: " + this.statsXPMade * 100 / this.statsXPAtt + "%");
        }
        else {
            list.add("XP Made/Att: 0/0>XP Percent: 0%");
        }
        if (this.statsFGAtt > 0) {
            list.add("FG Made/Att: " + this.statsFGMade + "/" + this.statsFGAtt + ">FG Percent: " + this.statsFGMade * 100 / this.statsFGAtt + "%");
        }
        else {
            list.add("FG Made/Att: 0/0>FG Percent: 0%");
        }
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Kick Strength: " + Player.getLetterGrade(this.ratKickPow));
        list.add("Kick Accuracy: " + Player.getLetterGrade(this.ratKickAcc) + ">Clumsiness: " + Player.getLetterGrade(this.ratKickFum));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsOffseason() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Potential: " + Player.getLetterGrade(this.ratPot) + ">Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Kick Strength: " + Player.getLetterGrade(this.ratKickPow));
        list.add("Kick Accuracy: " + Player.getLetterGrade(this.ratKickAcc) + ">Clumsiness: " + Player.getLetterGrade(this.ratKickFum));
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
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratKickPow) + ", " + Player.getLetterGrade(this.ratKickAcc) + ", " + Player.getLetterGrade(this.ratKickFum) + ")";
    }
    
    @Override
    public int getMVPScore() {
        return (int)((this.statsFGMade * 5 + this.statsXPMade) * (this.statsFGMade / (double)this.statsFGAtt)) + this.ratOvr;
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratKickPow, this.ratKickAcc, this.ratKickFum };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratKickPow + this.ratKickAcc) / 2;
    }
    
    public void setRatings(final int[] array) {
        this.ratKickPow = array[0];
        this.ratKickAcc = array[1];
        this.ratKickFum = array[2];
        this.setOvr();
    }
}
