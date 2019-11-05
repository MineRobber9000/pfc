package PFCpack;

import java.util.Comparator;

class TeamCompOPYPG
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamOppPassYards / paramTeam1.numGames() < teamOppPassYards / paramTeam2.numGames()) {
      return -1;
    }
    if (teamOppPassYards / paramTeam1.numGames() == teamOppPassYards / paramTeam2.numGames()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompOPYPG
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */