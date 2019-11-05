package PFCpack;

import java.util.ArrayList;

public class DraftPick
  extends TradePiece
{
  private int round;
  private Team teamOriginal;
  private Team teamOwner;
  private int year;
  
  public DraftPick(int paramInt1, int paramInt2, Team paramTeam1, Team paramTeam2)
  {
    year = paramInt1;
    round = paramInt2;
    teamOriginal = paramTeam1;
    teamOwner = paramTeam2;
  }
  
  public DraftPick(League paramLeague, String paramString)
  {
    paramString = paramString.split(",");
    year = Integer.parseInt(paramString[0]);
    round = Integer.parseInt(paramString[1]);
    teamOriginal = paramLeague.findTeamAbbr(paramString[2]);
    teamOriginal.positionalDraftPicks.add(this);
    teamOwner = paramLeague.findTeamAbbr(paramString[3]);
  }
  
  public String getCSV()
  {
    return year + "," + round + "," + teamOriginal.abbr + "," + teamOwner.abbr;
  }
  
  public int getRound()
  {
    return round;
  }
  
  public String getStrRep()
  {
    return teamOriginal.abbr + "'s " + year + " Round " + round + " pick";
  }
  
  public String getStrRepSplit()
  {
    return year + ": Round " + round + ">" + teamOriginal.getStrAbbrWL();
  }
  
  public String getStrRepWL()
  {
    return teamOriginal.getStrAbbrWL() + "'s " + year + " Round " + round + " pick";
  }
  
  public Team getTeamOriginal()
  {
    return teamOriginal;
  }
  
  public Team getTeamOwner()
  {
    return teamOwner;
  }
  
  public String getTradePieceInfo()
  {
    return getStrRepWL();
  }
  
  public int getTradeValue()
  {
    int i = 5 - (year - teamOriginal.league.getYear());
    int j = 8 - round;
    int k = teamOriginal.losses * j / 2;
    return i * 10 + (int)(0.35D * Math.pow(j * 1.75D, 3.25D)) + k * i * 10;
  }
  
  public int getYear()
  {
    return year;
  }
  
  public void setTeamOwner(Team paramTeam)
  {
    teamOwner = paramTeam;
  }
}

/* Location:
 * Qualified Name:     PFCpack.DraftPick
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */