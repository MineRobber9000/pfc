package PFCpack;

import java.util.Comparator;

class TeamCompYPG
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamYards / paramTeam1.numGames() > teamYards / paramTeam2.numGames()) {
      return -1;
    }
    if (teamYards / paramTeam1.numGames() == teamYards / paramTeam2.numGames()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompYPG
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */