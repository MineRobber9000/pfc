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

public class TeamHistoryListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final String[] values;
    
    public TeamHistoryListArrayAdapter(final Context context, final String[] values) {
        super(context, 2130968662, (Object[])values);
        this.context = context;
        this.values = values;
    }
    
    public View getView(int i, final View view, final ViewGroup viewGroup) {
        final View inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968662, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558680);
        final TextView textView2 = (TextView)inflate.findViewById(2131558681);
        final String[] split = this.values[i].split(">");
        if (split.length > 1) {
            textView.setText((CharSequence)split[0]);
            final String[] split2 = split[0].split(" ");
            int n = 0;
            boolean b = false;
            int length;
            String s;
            int n2;
            for (length = split2.length, i = 0; i < length; ++i, n = n2) {
                s = split2[i];
                if (s.equals("CBW")) {
                    n2 = 1;
                }
                else {
                    n2 = n;
                    if (s.equals("DW")) {
                        b = true;
                        n2 = n;
                    }
                }
            }
            if (n != 0) {
                textView.setTextColor(Color.parseColor("#FF9933"));
            }
            else if (false) {
                textView.setTextColor(Color.parseColor("#00B300"));
            }
            else if (b) {
                textView.setTextColor(Color.parseColor("#1A75FF"));
            }
            String text = "";
            String s2;
            for (i = 1; i < split.length; ++i) {
                s2 = (text += split[i]);
                if (i != split.length - 1) {
                    text = s2 + "\n";
                }
            }
            textView2.setText((CharSequence)text);
            return inflate;
        }
        textView.setText((CharSequence)this.values[i]);
        final String[] split3 = this.values[i].split(" ");
        int n3 = 0;
        boolean b2 = false;
        int length2;
        String s3;
        int n4;
        for (length2 = split3.length, i = 0; i < length2; ++i, n3 = n4) {
            s3 = split3[i];
            if (s3.equals("CBW")) {
                n4 = 1;
            }
            else {
                n4 = n3;
                if (s3.equals("DW")) {
                    b2 = true;
                    n4 = n3;
                }
            }
        }
        if (n3 != 0) {
            textView.setTextColor(Color.parseColor("#FF9933"));
        }
        else if (false) {
            textView.setTextColor(Color.parseColor("#00B300"));
        }
        else if (b2) {
            textView.setTextColor(Color.parseColor("#1A75FF"));
        }
        textView2.setVisibility(8);
        return inflate;
    }
}
