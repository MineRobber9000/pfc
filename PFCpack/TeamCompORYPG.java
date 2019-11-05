package PFCpack;

import java.util.Comparator;

class TeamCompORYPG
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamOppRushYards / paramTeam1.numGames() < teamOppRushYards / paramTeam2.numGames()) {
      return -1;
    }
    if (teamOppRushYards / paramTeam1.numGames() == teamOppRushYards / paramTeam2.numGames()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompORYPG
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */