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

public class LeagueRecordsListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final String userTeamAbbr;
    private final String[] values;
    
    public LeagueRecordsListArrayAdapter(final Context context, final String[] values, final String userTeamAbbr) {
        super(context, 2130968638, (Object[])values);
        this.context = context;
        this.values = values;
        this.userTeamAbbr = userTeamAbbr;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968638, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558645);
        final TextView textView2 = (TextView)inflate.findViewById(2131558646);
        final TextView textView3 = (TextView)inflate.findViewById(2131558647);
        final String[] split = this.values[n].split(",");
        if (split[1].equals("-1")) {
            textView.setText((CharSequence)"");
            textView2.setText((CharSequence)split[0]);
            textView2.setTextSize(2, 18.0f);
            textView3.setText((CharSequence)"");
        }
        else if (!split[2].equals("XXX")) {
            textView.setText((CharSequence)split[1]);
            textView2.setText((CharSequence)split[0]);
            textView3.setText((CharSequence)(split[2] + "\n" + split[3]));
            if (split[2].split(" ")[0].equals(this.userTeamAbbr)) {
                textView3.setTextColor(Color.parseColor("#1A75FF"));
                return inflate;
            }
        }
        return inflate;
    }
}
