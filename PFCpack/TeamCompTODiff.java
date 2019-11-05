// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompTODiff implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (team.teamTODiff > team2.teamTODiff) {
            return -1;
        }
        if (team.teamTODiff == team2.teamTODiff) {
            return 0;
        }
        return 1;
    }
}
