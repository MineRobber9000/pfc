package PFCpack;

import java.util.Comparator;

class TeamCompOffTalent
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamOffTalent > teamOffTalent) {
      return -1;
    }
    if (teamOffTalent == teamOffTalent) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompOffTalent
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */