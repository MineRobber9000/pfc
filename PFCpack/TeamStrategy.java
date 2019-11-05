// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

public class TeamStrategy
{
    private int passAgBonus;
    private int passYdBonus;
    private int rushAgBonus;
    private int rushYdBonus;
    private String stratDescription;
    private String stratName;
    
    public TeamStrategy() {
        this.stratName = "No Preference";
        this.stratDescription = "Will play a normal O/D with no bonus either way, but no penalties either.";
        this.rushYdBonus = 0;
        this.rushAgBonus = 0;
        this.passYdBonus = 0;
        this.passAgBonus = 0;
    }
    
    public TeamStrategy(final String stratName, final String stratDescription, final int rushYdBonus, final int rushAgBonus, final int passYdBonus, final int passAgBonus) {
        this.stratName = stratName;
        this.stratDescription = stratDescription;
        this.rushYdBonus = rushYdBonus;
        this.rushAgBonus = rushAgBonus;
        this.passYdBonus = passYdBonus;
        this.passAgBonus = passAgBonus;
    }
    
    public int getPAB() {
        return this.passAgBonus;
    }
    
    public int getPYB() {
        return this.passYdBonus;
    }
    
    public int getRAB() {
        return this.rushAgBonus;
    }
    
    public int getRYB() {
        return this.rushYdBonus;
    }
    
    public String getStratDescription() {
        return this.stratDescription;
    }
    
    public String getStratName() {
        return this.stratName;
    }
}
