// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.graphics.Color;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.ArrayAdapter;

public class MockDraftListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final String userTeamStrRep;
    private final String[] values;
    
    public MockDraftListArrayAdapter(final Context context, final String[] values, final String userTeamStrRep) {
        super(context, 2130968609, (Object[])values);
        this.context = context;
        this.values = values;
        this.userTeamStrRep = userTeamStrRep;
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968609, viewGroup, false);
        final String[] split = this.values[n].split(">");
        final TextView textView = (TextView)inflate.findViewById(2131558552);
        textView.setText((CharSequence)(n + 1 + ". " + split[0]));
        final TextView textView2 = (TextView)inflate.findViewById(2131558553);
        textView2.setText((CharSequence)split[1]);
        ((Button)inflate.findViewById(2131558555)).setVisibility(8);
        ((Button)inflate.findViewById(2131558556)).setVisibility(8);
        if (split[1].equals(this.userTeamStrRep)) {
            textView.setTextColor(Color.parseColor("#1A75FF"));
            textView2.setTextColor(Color.parseColor("#1A75FF"));
        }
        return inflate;
    }
}
