package PFCpack;

import java.util.Comparator;

class TeamCompPoll
  implements Comparator<Team>
{
  public int compare(Team paramTeam1, Team paramTeam2)
  {
    if (teamPollScore > teamPollScore) {
      return -1;
    }
    if (teamPollScore == teamPollScore) {
      return 0;
    }
    return 1;
  }
}

/* Location:
 * Qualified Name:     PFCpack.TeamCompPoll
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */