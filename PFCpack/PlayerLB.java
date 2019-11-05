// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerLB extends Player
{
    public int ratLBPas;
    public int ratLBRsh;
    public int ratLBTck;
    
    public PlayerLB(final Team team, final String[] array) {
        super(team, array);
    }
    
    public PlayerLB(final String s, final int n) {
        super(s, "LB", n);
    }
    
    public PlayerLB(final String s, final Team team) {
        super(s, team, "LB", true);
    }
    
    public PlayerLB(final String s, final Team team, final int[] array, final String s2) {
        super(s, team, "LB", false);
        this.setVariables(array, s2);
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Tackling: " + Player.getLetterGrade(this.ratLBTck));
        list.add("Run Stop: " + Player.getLetterGrade(this.ratLBRsh) + ">Pass Coverage: " + Player.getLetterGrade(this.ratLBPas));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Tackling: " + Player.getLetterGrade(this.ratLBTck));
        list.add("Run Stop: " + Player.getLetterGrade(this.ratLBRsh) + ">Pass Coverage: " + Player.getLetterGrade(this.ratLBPas));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsOffseason() {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Potential: " + Player.getLetterGrade(this.ratPot) + ">Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Tackling: " + Player.getLetterGrade(this.ratLBTck));
        list.add("Run Stop: " + Player.getLetterGrade(this.ratLBRsh) + ">Pass Coverage: " + Player.getLetterGrade(this.ratLBPas));
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
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratLBTck) + ", " + Player.getLetterGrade(this.ratLBRsh) + ", " + Player.getLetterGrade(this.ratLBPas) + ")";
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratLBTck, this.ratLBRsh, this.ratLBPas };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratLBTck * 3 + this.ratLBRsh + this.ratLBPas) / 5;
    }
    
    public void setRatings(final int[] array) {
        this.ratLBTck = array[0];
        this.ratLBRsh = array[1];
        this.ratLBPas = array[2];
        this.setOvr();
    }
}
