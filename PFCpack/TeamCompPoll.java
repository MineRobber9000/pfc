// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompPoll implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (team.teamPollScore > team2.teamPollScore) {
            return -1;
        }
        if (team.teamPollScore == team2.teamPollScore) {
            return 0;
        }
        return 1;
    }
}
