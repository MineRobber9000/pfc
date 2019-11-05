// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

public class DraftPick extends TradePiece
{
    private int round;
    private Team teamOriginal;
    private Team teamOwner;
    private int year;
    
    public DraftPick(final int year, final int round, final Team teamOriginal, final Team teamOwner) {
        this.year = year;
        this.round = round;
        this.teamOriginal = teamOriginal;
        this.teamOwner = teamOwner;
    }
    
    public DraftPick(final League league, final String s) {
        final String[] split = s.split(",");
        this.year = Integer.parseInt(split[0]);
        this.round = Integer.parseInt(split[1]);
        this.teamOriginal = league.findTeamAbbr(split[2]);
        this.teamOriginal.positionalDraftPicks.add(this);
        this.teamOwner = league.findTeamAbbr(split[3]);
    }
    
    public String getCSV() {
        return this.year + "," + this.round + "," + this.teamOriginal.abbr + "," + this.teamOwner.abbr;
    }
    
    public int getRound() {
        return this.round;
    }
    
    public String getStrRep() {
        return this.teamOriginal.abbr + "'s " + this.year + " Round " + this.round + " pick";
    }
    
    public String getStrRepSplit() {
        return this.year + ": Round " + this.round + ">" + this.teamOriginal.getStrAbbrWL();
    }
    
    public String getStrRepWL() {
        return this.teamOriginal.getStrAbbrWL() + "'s " + this.year + " Round " + this.round + " pick";
    }
    
    public Team getTeamOriginal() {
        return this.teamOriginal;
    }
    
    public Team getTeamOwner() {
        return this.teamOwner;
    }
    
    @Override
    public String getTradePieceInfo() {
        return this.getStrRepWL();
    }
    
    @Override
    public int getTradeValue() {
        final int n = 5 - (this.year - this.teamOriginal.league.getYear());
        final int n2 = 8 - this.round;
        return n * 10 + (int)(0.35 * Math.pow(n2 * 1.75, 3.25)) + this.teamOriginal.losses * n2 / 2 * n * 10;
    }
    
    public int getYear() {
        return this.year;
    }
    
    public void setTeamOwner(final Team teamOwner) {
        this.teamOwner = teamOwner;
    }
}
