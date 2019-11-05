// 
// Decompiled by Procyon v0.5.36
// 

package PFCpack;

import java.util.Comparator;

class DraftPickComparatorRoundYear implements Comparator<DraftPick>
{
    @Override
    public int compare(final DraftPick draftPick, final DraftPick draftPick2) {
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
        return -1;
    }
}
