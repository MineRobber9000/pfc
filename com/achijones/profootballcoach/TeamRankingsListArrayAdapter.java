// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.graphics.Color;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import java.util.List;
import java.util.ArrayList;
import android.content.Context;
import android.widget.ArrayAdapter;

public class TeamRankingsListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final String userTeamStrRep;
    private final ArrayList<String> values;
    
    public TeamRankingsListArrayAdapter(final Context context, final ArrayList<String> values, final String userTeamStrRep) {
        super(context, 2130968666, (List)values);
        this.context = context;
        this.values = values;
        this.userTeamStrRep = userTeamStrRep;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968666, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558691);
        final TextView textView2 = (TextView)inflate.findViewById(2131558693);
        final TextView textView3 = (TextView)inflate.findViewById(2131558694);
        final String[] split = this.values.get(n).split(",");
        textView.setText((CharSequence)split[0]);
        textView2.setText((CharSequence)split[1]);
        textView3.setText((CharSequence)split[2]);
        if (split[1].equals(this.userTeamStrRep)) {
            textView.setTypeface(textView.getTypeface(), 1);
            textView.setTextColor(Color.parseColor("#1A75FF"));
            textView2.setTypeface(textView2.getTypeface(), 1);
            textView2.setTextColor(Color.parseColor("#1A75FF"));
            textView3.setTypeface(textView3.getTypeface(), 1);
            textView3.setTextColor(Color.parseColor("#1A75FF"));
        }
        return inflate;
    }
}
