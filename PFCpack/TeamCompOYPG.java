package PFCpack;

import java.util.Comparator;

class TeamCompOYPG
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamOppYards / paramTeam1.numGames() < teamOppYards / paramTeam2.numGames()) {
      return -1;
    }
    if (teamOppYards / paramTeam1.numGames() == teamOppYards / paramTeam2.numGames()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompOYPG
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */