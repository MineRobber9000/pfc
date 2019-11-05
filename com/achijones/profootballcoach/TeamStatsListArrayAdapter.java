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
import android.content.Context;
import android.widget.ArrayAdapter;

public class TeamStatsListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final MainActivity mainAct;
    private final String[] values;
    
    public TeamStatsListArrayAdapter(final Context context, final MainActivity mainAct, final String[] values) {
        super(context, 2130968667, (Object[])values);
        this.context = context;
        this.values = values;
        this.mainAct = mainAct;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968667, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558695);
        final TextView textView2 = (TextView)inflate.findViewById(2131558696);
        final TextView textView3 = (TextView)inflate.findViewById(2131558697);
        final Button button = (Button)inflate.findViewById(2131558698);
        if (!this.values[n].equals("PROFILE_BUTTON")) {
            final String[] split = this.values[n].split(",");
            textView.setText((CharSequence)split[0]);
            textView2.setText((CharSequence)split[1]);
            textView3.setText((CharSequence)split[2]);
            button.setVisibility(8);
            return inflate;
        }
        textView.setVisibility(8);
        textView2.setVisibility(8);
        textView3.setVisibility(8);
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                TeamStatsListArrayAdapter.this.mainAct.showCurrTeamProfile();
            }
        });
        return inflate;
    }
}
