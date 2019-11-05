// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

public class TeamStreak
{
    private int endYear;
    private int startYear;
    private int streakLength;
    private String streakTeam;
    
    public TeamStreak(final int startYear, final int endYear, final int streakLength, final String streakTeam) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.streakLength = streakLength;
        this.streakTeam = streakTeam;
    }
    
    public void addWin(final int endYear) {
        ++this.streakLength;
        this.endYear = endYear;
    }
    
    public void changeAbbr(final String streakTeam) {
        this.streakTeam = streakTeam;
    }
    
    public int getEndYear() {
        return this.endYear;
    }
    
    public int getStartYear() {
        return this.startYear;
    }
    
    public String getStreakCSV() {
        return this.streakLength + "," + this.streakTeam + "," + this.startYear + "," + this.endYear;
    }
    
    public int getStreakLength() {
        return this.streakLength;
    }
    
    public String getTeam() {
        return this.streakTeam;
    }
    
    public void resetStreak(final int n) {
        this.startYear = n;
        this.endYear = n;
        this.streakLength = 0;
    }
}
