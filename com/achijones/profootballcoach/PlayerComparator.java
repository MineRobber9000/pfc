// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import PFCpack.Player;
import java.util.Comparator;

class PlayerComparator implements Comparator<Player>
{
    @Override
    public int compare(final Player player, final Player player2) {
        if (player.isInjured() || !player2.isInjured()) {
            if (player.isInjured() && !player2.isInjured()) {
                return 1;
            }
            if (player.getRatOvr() <= player2.getRatOvr()) {
                if (player.getRatOvr() == player2.getRatOvr()) {
                    return 0;
                }
                return 1;
            }
        }
        return -1;
    }
}
