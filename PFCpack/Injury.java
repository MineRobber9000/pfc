// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Random;

public class Injury
{
    private static final String[] injuries;
    private static final Random rando;
    private String description;
    private int duration;
    private Player player;
    
    static {
        injuries = new String[] { "Knee", "Head", "Shoulder", "Wrist", "Ankle", "Foot", "Arm", "Back", "Brain" };
        rando = new Random();
    }
    
    public Injury(final int duration, final String description, final Player player) {
        this.duration = duration;
        this.description = description;
        this.player = player;
        this.player.isInjured = true;
    }
    
    public Injury(final Player player) {
        this.duration = Math.abs((int)(Injury.rando.nextGaussian() * 3.0 + 1.0));
        if (this.duration == 0) {
            this.duration = 1;
        }
        if (Math.random() < 0.01) {
            this.duration = 100;
        }
        this.description = Injury.injuries[(int)(Math.random() * Injury.injuries.length)];
        this.player = player;
        this.player.isInjured = true;
    }
    
    public Injury(final Player player, final int n, final int duration) {
        this.player = player;
        this.description = Injury.injuries[n];
        this.duration = duration;
        this.player.isInjured = true;
    }
    
    public void advanceGame() {
        --this.duration;
        if (this.duration <= 0) {
            this.player.isInjured = false;
            this.player.injury = null;
        }
    }
    
    public String getCSV() {
        final int n = 0;
        int n2 = 1;
        int n3;
        while (true) {
            n3 = n;
            if (n2 >= Injury.injuries.length) {
                break;
            }
            if (this.description.equals(Injury.injuries[n2])) {
                n3 = n2;
                break;
            }
            ++n2;
        }
        return n3 + "," + this.duration;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getDuration() {
        return this.duration;
    }
    
    @Override
    public String toString() {
        if (this.duration < 20) {
            return this.description + " (" + this.duration + " wk)";
        }
        return this.description + " (Season)";
    }
}
