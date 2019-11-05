// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class PlayerPositionComparator implements Comparator<Player>
{
    @Override
    public int compare(final Player player, final Player player2) {
        final int posNumber = Player.getPosNumber(player.position);
        final int posNumber2 = Player.getPosNumber(player2.position);
        if (posNumber < posNumber2) {
            return -1;
        }
        if (posNumber == posNumber2) {
            return 0;
        }
        return 1;
    }
}
