package PFCpack;

import java.util.Comparator;

class TeamCompRYPG
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamRushYards / paramTeam1.numGames() > teamRushYards / paramTeam2.numGames()) {
      return -1;
    }
    if (teamRushYards / paramTeam1.numGames() == teamRushYards / paramTeam2.numGames()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompRYPG
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */