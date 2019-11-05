// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TeamCompCapRoom implements Comparator<Team>
{
    @Override
    public int compare(final Team team, final Team team2) {
        if (team.getSalaryCapRoom() > team2.getSalaryCapRoom()) {
            return -1;
        }
        if (team.getSalaryCapRoom() == team2.getSalaryCapRoom()) {
            return 0;
        }
        return 1;
    }
}
