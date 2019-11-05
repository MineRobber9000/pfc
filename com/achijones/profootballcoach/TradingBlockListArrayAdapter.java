// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import PFCpack.Team;
import android.content.Context;
import PFCpack.TradePiece;
import android.widget.ArrayAdapter;

public class TradingBlockListArrayAdapter extends ArrayAdapter<TradePiece>
{
    private final Context context;
    private final MainActivity mainAct;
    private final TradePiece[] pieces;
    private final Team tradeTeam;
    private final Team userTeam;
    
    public TradingBlockListArrayAdapter(final Context context, final MainActivity mainAct, final Team userTeam, final Team tradeTeam, final TradePiece[] pieces) {
        super(context, 2130968670, (Object[])pieces);
        this.context = context;
        this.mainAct = mainAct;
        this.pieces = pieces;
        this.userTeam = userTeam;
        this.tradeTeam = tradeTeam;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968670, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558704);
        final Button button = (Button)inflate.findViewById(2131558706);
        final Button button2 = (Button)inflate.findViewById(2131558705);
        if (this.pieces[n] != null) {
            button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    TradingBlockListArrayAdapter.this.mainAct.removeTradePiece(TradingBlockListArrayAdapter.this.pieces[n]);
                    TradingBlockListArrayAdapter.this.notifyDataSetChanged();
                }
            });
            button2.setVisibility(8);
            textView.setText((CharSequence)this.pieces[n].getTradePieceInfo());
            return inflate;
        }
        button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                TradingBlockListArrayAdapter.this.mainAct.tradeOffersDialog();
            }
        });
        button.setVisibility(8);
        textView.setVisibility(8);
        return inflate;
    }
}
