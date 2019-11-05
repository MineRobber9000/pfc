// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.ArrayAdapter;

public class HallOfFameListArrayAdapter extends ArrayAdapter<String>
{
    private final Context context;
    private final String[] values;
    
    public HallOfFameListArrayAdapter(final Context context, final String[] values) {
        super(context, 2130968635, (Object[])values);
        this.context = context;
        this.values = values;
    }
    
    public View getView(int i, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968635, viewGroup, false);
        final TextView textView = (TextView)inflate.findViewById(2131558637);
        final TextView textView2 = (TextView)inflate.findViewById(2131558638);
        final TextView textView3 = (TextView)inflate.findViewById(2131558639);
        final String[] split = this.values[i].split("&");
        if (split.length > 1) {
            textView.setText((CharSequence)split[0]);
            final StringBuilder sb = new StringBuilder();
            final StringBuilder sb2 = new StringBuilder();
            String[] split2;
            for (i = 1; i < split.length; ++i) {
                split2 = split[i].split(">");
                sb.append(split2[0]);
                if (i != split.length - 1) {
                    sb.append("\n");
                }
                if (split2.length > 1) {
                    sb2.append(split2[1]);
                    if (i != split.length - 1) {
                        sb2.append("\n");
                    }
                }
            }
            textView2.setText((CharSequence)sb.toString());
            textView3.setText((CharSequence)sb2.toString());
        }
        return inflate;
    }
}
