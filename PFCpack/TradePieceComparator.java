// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class TradePieceComparator implements Comparator<TradePiece>
{
    @Override
    public int compare(final TradePiece tradePiece, final TradePiece tradePiece2) {
        if (!(tradePiece instanceof Player) || tradePiece2 instanceof Player) {
            if (!(tradePiece instanceof Player) && tradePiece2 instanceof Player) {
                return 1;
            }
            if (tradePiece instanceof Player && tradePiece2 instanceof Player) {
                final Player player = (Player)tradePiece;
                final Player player2 = (Player)tradePiece2;
                if (player.ratOvr <= player2.ratOvr) {
                    if (player.ratOvr == player2.ratOvr) {
                        return 0;
                    }
                    return 1;
                }
            }
            else {
                if (!(tradePiece instanceof DraftPick) || !(tradePiece2 instanceof DraftPick)) {
                    return 0;
                }
                final DraftPick draftPick = (DraftPick)tradePiece;
                final DraftPick draftPick2 = (DraftPick)tradePiece2;
                if (draftPick.getRound() >= draftPick2.getRound()) {
                    if (draftPick.getRound() != draftPick2.getRound()) {
                        return 1;
                    }
                    if (draftPick.getYear() >= draftPick2.getYear()) {
                        if (draftPick.getYear() == draftPick2.getYear()) {
                            return 0;
                        }
                        return 1;
                    }
                }
            }
        }
        return -1;
    }
}
