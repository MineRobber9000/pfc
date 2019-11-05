// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.widget.Button;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.graphics.Color;
import android.widget.TextView;
import android.content.Context;
import android.widget.ArrayAdapter;

public class PlayerStatsListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final String[] values;
    
    public PlayerStatsListArrayAdapter(final Context context, final String[] values) {
        super(context, 2130968609, (Object[])values);
        this.context = context;
        this.values = values;
    }
    
    private void colorizeRatings(final TextView textView, String s) {
        final String[] split = s.split(" ");
        if (split.length > 0 && s.split(",").length == 1) {
            s = split[split.length - 1];
            if (s.equals("A") || s.equals("A+")) {
                textView.setTextColor(Color.parseColor("#006600"));
            }
            else {
                if (s.equals("B") || s.equals("B+")) {
                    textView.setTextColor(Color.parseColor("#00b300"));
                    return;
                }
                if (s.equals("C") || s.equals("C+")) {
                    textView.setTextColor(Color.parseColor("#e68a00"));
                    return;
                }
                if (s.equals("D") || s.equals("D+")) {
                    textView.setTextColor(Color.parseColor("#cc3300"));
                    return;
                }
                if (s.equals("F") || s.equals("F+")) {
                    textView.setTextColor(Color.parseColor("#990000"));
                }
            }
        }
    }
    
    public View getView(final int n, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968609, viewGroup, false);
        final String[] split = this.values[n].split(">");
        if (split.length == 2) {
            final TextView textView = (TextView)inflate.findViewById(2131558552);
            final TextView textView2 = (TextView)inflate.findViewById(2131558553);
            if (this.values[n].substring(0, 3).equals("[I]")) {
                textView.setText((CharSequence)split[0].substring(3));
                textView2.setText((CharSequence)split[1]);
                textView.setTextColor(-65536);
                textView2.setTextColor(-65536);
            }
            else {
                textView.setText((CharSequence)split[0]);
                textView2.setText((CharSequence)split[1]);
                this.colorizeRatings(textView, split[0]);
                this.colorizeRatings(textView2, split[1]);
            }
            ((TextView)inflate.findViewById(2131558554)).setText((CharSequence)"");
        }
        else {
            final TextView textView3 = (TextView)inflate.findViewById(2131558554);
            if (this.values[n].substring(0, 3).equals("[B]")) {
                textView3.setText((CharSequence)this.values[n].substring(3));
                textView3.setTypeface((Typeface)null, 1);
            }
            else if (this.values[n].substring(0, 3).equals("[I]")) {
                textView3.setText((CharSequence)this.values[n].substring(3));
                textView3.setTextColor(-65536);
            }
            else {
                textView3.setText((CharSequence)this.values[n]);
            }
            ((TextView)inflate.findViewById(2131558552)).setVisibility(8);
            ((TextView)inflate.findViewById(2131558553)).setVisibility(8);
        }
        ((Button)inflate.findViewById(2131558555)).setVisibility(8);
        ((Button)inflate.findViewById(2131558556)).setVisibility(8);
        return inflate;
    }
}
