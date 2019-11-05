package PFCpack;

import java.util.Comparator;

class TeamCompOPPG
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamOppPoints / paramTeam1.numGames() < teamOppPoints / paramTeam2.numGames()) {
      return -1;
    }
    if (teamOppPoints / paramTeam1.numGames() == teamOppPoints / paramTeam2.numGames()) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompOPPG
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */