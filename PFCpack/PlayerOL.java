// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerOL extends Player
{
    protected int ratOLBkP;
    protected int ratOLBkR;
    protected int ratOLPow;
    
    public PlayerOL(final Team team, final String[] array) {
        super(team, array);
    }
    
    public PlayerOL(final String s, final int n) {
        super(s, "OL", n);
    }
    
    public PlayerOL(final String s, final Team team) {
        super(s, team, "OL", true);
    }
    
    public PlayerOL(final String s, final Team team, final int[] array, final String s2) {
        super(s, team, "OL", false);
        this.setVariables(array, s2);
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Strength: " + Player.getLetterGrade(this.ratOLPow));
        list.add("Run Block: " + Player.getLetterGrade(this.ratOLBkR) + ">Pass Block: " + Player.getLetterGrade(this.ratOLBkP));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Strength: " + Player.getLetterGrade(this.ratOLPow));
        list.add("Run Block: " + Player.getLetterGrade(this.ratOLBkR) + ">Pass Block: " + Player.getLetterGrade(this.ratOLBkP));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsOffseason() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Potential: " + Player.getLetterGrade(this.ratPot) + ">Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Strength: " + Player.getLetterGrade(this.ratOLPow));
        list.add("Run Block: " + Player.getLetterGrade(this.ratOLBkR) + ">Pass Block: " + Player.getLetterGrade(this.ratOLBkP));
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
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratOLPow) + ", " + Player.getLetterGrade(this.ratOLBkR) + ", " + Player.getLetterGrade(this.ratOLBkP) + ")";
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratOLPow, this.ratOLBkR, this.ratOLBkP };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratOLPow * 3 + this.ratOLBkR + this.ratOLBkP) / 5;
    }
    
    public void setRatings(final int[] array) {
        this.ratOLPow = array[0];
        this.ratOLBkR = array[1];
        this.ratOLBkP = array[2];
        this.setOvr();
    }
}
