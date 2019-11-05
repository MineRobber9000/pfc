// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerS extends Player
{
    protected int ratSCov;
    protected int ratSSpd;
    protected int ratSTkl;
    
    public PlayerS(final Team team, final String[] array) {
        super(team, array);
    }
    
    public PlayerS(final String s, final int n) {
        super(s, "S", n);
    }
    
    public PlayerS(final String s, final Team team) {
        super(s, team, "S", true);
    }
    
    public PlayerS(final String s, final Team team, final int[] array, final String s2) {
        super(s, team, "S", false);
        this.setVariables(array, s2);
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Coverage: " + Player.getLetterGrade(this.ratSCov));
        list.add("Speed: " + Player.getLetterGrade(this.ratSSpd) + ">Tackling: " + Player.getLetterGrade(this.ratSTkl));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Coverage: " + Player.getLetterGrade(this.ratSCov));
        list.add("Speed: " + Player.getLetterGrade(this.ratSSpd) + ">Tackling: " + Player.getLetterGrade(this.ratSTkl));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsOffseason() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Potential: " + Player.getLetterGrade(this.ratPot) + ">Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Coverage: " + Player.getLetterGrade(this.ratSCov));
        list.add("Speed: " + Player.getLetterGrade(this.ratSSpd) + ">Tackling: " + Player.getLetterGrade(this.ratSTkl));
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
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratSCov) + ", " + Player.getLetterGrade(this.ratSSpd) + ", " + Player.getLetterGrade(this.ratSTkl) + ")";
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratSCov, this.ratSSpd, this.ratSTkl };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratSCov * 2 + this.ratSSpd + this.ratSTkl) / 4;
    }
    
    public void setRatings(final int[] array) {
        this.ratSCov = array[0];
        this.ratSSpd = array[1];
        this.ratSTkl = array[2];
        this.setOvr();
    }
}
