// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerDL extends Player
{
    public int ratDLPas;
    public int ratDLPow;
    public int ratDLRsh;
    
    public PlayerDL(final Team team, final String[] array) {
        super(team, array);
    }
    
    public PlayerDL(final String s, final int n) {
        super(s, "DL", n);
    }
    
    public PlayerDL(final String s, final Team team) {
        super(s, team, "DL", true);
    }
    
    public PlayerDL(final String s, final Team team, final int[] array, final String s2) {
        super(s, team, "DL", false);
        this.setVariables(array, s2);
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Strength: " + Player.getLetterGrade(this.ratDLPow));
        list.add("Run Stop: " + Player.getLetterGrade(this.ratDLRsh) + ">Pass Pressure: " + Player.getLetterGrade(this.ratDLPas));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Strength: " + Player.getLetterGrade(this.ratDLPow));
        list.add("Run Stop: " + Player.getLetterGrade(this.ratDLRsh) + ">Pass Pressure: " + Player.getLetterGrade(this.ratDLPas));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsOffseason() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Potential: " + Player.getLetterGrade(this.ratPot) + ">Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Strength: " + Player.getLetterGrade(this.ratDLPow));
        list.add("Run Stop: " + Player.getLetterGrade(this.ratDLRsh) + ">Pass Pressure: " + Player.getLetterGrade(this.ratDLPas));
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
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratDLPow) + ", " + Player.getLetterGrade(this.ratDLRsh) + ", " + Player.getLetterGrade(this.ratDLPas) + ")";
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratDLPow, this.ratDLRsh, this.ratDLPas };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratDLPow * 3 + this.ratDLRsh + this.ratDLPas) / 5;
    }
    
    public void setRatings(final int[] array) {
        this.ratDLPow = array[0];
        this.ratDLRsh = array[1];
        this.ratDLPas = array[2];
        this.setOvr();
    }
}
