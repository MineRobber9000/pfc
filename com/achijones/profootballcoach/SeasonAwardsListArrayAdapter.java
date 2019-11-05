// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.graphics.Color;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.ArrayAdapter;

public class SeasonAwardsListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private String userTeamAbbr;
    private final String[] values;
    
    public SeasonAwardsListArrayAdapter(final Context context, final String[] values, final String userTeamAbbr) {
        super(context, 2130968637, (Object[])values);
        this.context = context;
        this.values = values;
        this.userTeamAbbr = userTeamAbbr;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968637, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558642);
        final TextView textView2 = (TextView)inflate.findViewById(2131558643);
        final TextView textView3 = (TextView)inflate.findViewById(2131558644);
        final String[] split = this.values[n].split("\n");
        if (split.length == 3) {
            textView.setText((CharSequence)split[0]);
            textView2.setText((CharSequence)split[1]);
            textView3.setText((CharSequence)split[2]);
            if (split[0].split("\\(")[0].equals(this.userTeamAbbr)) {
                textView.setTextColor(Color.parseColor("#1A75FF"));
            }
        }
        else {
            if (split.length != 2) {
                textView2.setText((CharSequence)this.values[n]);
                return inflate;
            }
            textView.setText((CharSequence)split[0]);
            textView2.setText((CharSequence)split[1]);
            textView3.setVisibility(8);
            if (split[0].split("\\(")[0].equals(this.userTeamAbbr)) {
                textView.setTextColor(Color.parseColor("#1A75FF"));
                return inflate;
            }
        }
        return inflate;
    }
}
