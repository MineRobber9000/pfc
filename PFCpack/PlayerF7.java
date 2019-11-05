// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Collection;
import java.util.ArrayList;

public class PlayerF7 extends Player
{
    public int ratF7Pas;
    public int ratF7Pow;
    public int ratF7Rsh;
    
    public PlayerF7(final String s, final int n) {
        super(s, "F7", n);
    }
    
    public PlayerF7(final String s, final Team team) {
        super(s, team, "F7", true);
    }
    
    public PlayerF7(final String s, final Team team, final int[] array) {
        super(s, team, "F7", false);
    }
    
    @Override
    public ArrayList<String> getDetailAllStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Strength: " + Player.getLetterGrade(this.ratF7Pow));
        list.add("Run Stop: " + Player.getLetterGrade(this.ratF7Rsh) + ">Pass Pressure: " + Player.getLetterGrade(this.ratF7Pas));
        list.add("Contract: " + this.contract.toString());
        list.add("[B]CAREER STATS:");
        list.addAll(this.getCareerStatsList());
        return list;
    }
    
    @Override
    public ArrayList<String> getDetailStatsList(final int n) {
        final ArrayList<String> list = new ArrayList<String>();
        list.add("Games: " + this.gamesPlayed + " (" + this.statsWins + "-" + (this.gamesPlayed - this.statsWins) + ")>Durability: " + Player.getLetterGrade(this.ratDur));
        list.add("Football IQ: " + Player.getLetterGrade(this.ratFootIQ) + ">Strength: " + Player.getLetterGrade(this.ratF7Pow));
        list.add("Run Stop: " + Player.getLetterGrade(this.ratF7Rsh) + ">Pass Pressure: " + Player.getLetterGrade(this.ratF7Pas));
        list.add("Contract: " + this.contract.toString());
        list.add(" > ");
        return list;
    }
    
    @Override
    public String getInfoForLineup() {
        if (this.injury != null) {
            return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " " + this.injury.toString();
        }
        return this.getInitialName() + " [" + this.age + "] " + this.ratOvr + "/" + Player.getLetterGrade(this.ratPot) + " (" + Player.getLetterGrade(this.ratF7Pow) + ", " + Player.getLetterGrade(this.ratF7Rsh) + ", " + Player.getLetterGrade(this.ratF7Pas) + ")";
    }
    
    @Override
    public int[] getRatings() {
        return new int[] { this.ratF7Pow, this.ratF7Rsh, this.ratF7Pas };
    }
    
    public void setOvr() {
        this.ratOvr = (this.ratF7Pow * 3 + this.ratF7Rsh + this.ratF7Pas) / 5;
    }
    
    public void setRatings(final int[] array) {
        this.ratF7Pow = array[0];
        this.ratF7Rsh = array[1];
        this.ratF7Pas = array[2];
        this.setOvr();
    }
}
