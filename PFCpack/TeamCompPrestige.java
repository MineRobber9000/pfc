// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompPrestige implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (team.teamPrestige > team2.teamPrestige) {
            return -1;
        }
        if (team.teamPrestige == team2.teamPrestige) {
            return 0;
        }
        return 1;
    }
}
