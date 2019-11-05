// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.view.View$OnClickListener;
import PFCpack.Player;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import PFCpack.Trade;
import android.content.Context;
import PFCpack.TradePiece;
import android.widget.ArrayAdapter;

public class TradeListAdapter extends ArrayAdapter<TradePiece>
{
    private final Context context;
    private final MainActivity mainAct;
    private final TradePiece[] pieces;
    private final Trade trade;
    
    public TradeListAdapter(final Context context, final MainActivity mainAct, final TradePiece[] pieces, final Trade trade) {
        super(context, 2130968671, (Object[])pieces);
        this.context = context;
        this.mainAct = mainAct;
        this.pieces = pieces;
        this.trade = trade;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968671, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558707);
        final TextView textView2 = (TextView)inflate.findViewById(2131558708);
        final Button button = (Button)inflate.findViewById(2131558709);
        if (this.pieces[n] != null) {
            if (this.pieces[n] instanceof Player) {
                button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        TradeListAdapter.this.mainAct.examinePlayer((Player)TradeListAdapter.this.pieces[n]);
                    }
                });
            }
            else {
                button.setVisibility(8);
            }
            textView.setText((CharSequence)this.pieces[n].getTradePieceInfo());
            textView2.setText((CharSequence)"");
            return inflate;
        }
        if (n == 0) {
            textView.setText((CharSequence)"");
            textView2.setText((CharSequence)(this.trade.getATeam().getStrAbbrWL() + " offer:"));
            button.setVisibility(8);
            return inflate;
        }
        textView.setText((CharSequence)"");
        textView2.setText((CharSequence)(this.trade.getBTeam().getStrAbbrWL() + " offer:"));
        button.setVisibility(8);
        return inflate;
    }
}
