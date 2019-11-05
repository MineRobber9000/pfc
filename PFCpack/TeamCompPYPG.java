package PFCpack;

import java.util.Comparator;

class TeamCompPYPG
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamPassYards / paramTeam1.numGames() > teamPassYards / paramTeam2.numGames()) {
      return -1;
    }
    if (teamPassYards / paramTeam1.numGames() == teamPassYards / paramTeam2.numGames()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompPYPG
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */