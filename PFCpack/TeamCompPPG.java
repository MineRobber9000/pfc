// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompPPG implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (team.teamPoints / team.numGames() > team2.teamPoints / team2.numGames()) {
            return -1;
        }
        if (team.teamPoints / team.numGames() == team2.teamPoints / team2.numGames()) {
            return 0;
        }
        return 1;
    }
}
