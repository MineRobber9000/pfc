// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerCB extends Player
{
    public int ratCBCov;
    public int ratCBSpd;
    public int ratCBTkl;
    
    public PlayerCB(final Team team, final String[] array) {
        super(team, array);
    }
    
    public PlayerCB(final String s, final int n) {
        super(s, "CB", n);
        this.position = "CB";
    }
    
    public PlayerCB(final String s, final Team team) {
        super(s, team, "CB", true);
        this.position = "CB";
    }
    
    public PlayerCB(final String s, final Team team, final int[] array, final String s2) {
        super(s, team, "CB", false);
        this.setVariables(array, s2);
        this.position = "CB";
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Coverage: " + Player.getLetterGrade(this.ratCBCov));
        list.add("Speed: " + Player.getLetterGrade(this.ratCBSpd) + ">Tackling: " + Player.getLetterGrade(this.ratCBTkl));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Coverage: " + Player.getLetterGrade(this.ratCBCov));
        list.add("Speed: " + Player.getLetterGrade(this.ratCBSpd) + ">Tackling: " + Player.getLetterGrade(this.ratCBTkl));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsOffseason() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Potential: " + Player.getLetterGrade(this.ratPot) + ">Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Coverage: " + Player.getLetterGrade(this.ratCBCov));
        list.add("Speed: " + Player.getLetterGrade(this.ratCBSpd) + ">Tackling: " + Player.getLetterGrade(this.ratCBTkl));
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
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratCBCov) + ", " + Player.getLetterGrade(this.ratCBSpd) + ", " + Player.getLetterGrade(this.ratCBTkl) + ")";
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratCBCov, this.ratCBSpd, this.ratCBTkl };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratCBCov * 2 + this.ratCBSpd + this.ratCBTkl) / 4;
    }
    
    public void setRatings(final int[] array) {
        this.ratCBCov = array[0];
        this.ratCBSpd = array[1];
        this.ratCBTkl = array[2];
        this.setOvr();
    }
}
