// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.view.ViewGroup;
import android.view.View;
import android.graphics.Color;
import android.widget.TextView;
import java.util.Map;
import java.util.List;
import android.app.Activity;
import android.widget.BaseExpandableListAdapter;

public class ExpandableListAdapterPlayerStats extends BaseExpandableListAdapter
{
    private Activity context;
    private MainActivity mainAct;
    private List<String> players;
    private Map<String, List<String>> playersInfo;
    
    public ExpandableListAdapterPlayerStats(final Activity context, final MainActivity mainAct, final List<String> players, final Map<String, List<String>> playersInfo) {
        this.context = context;
        this.players = players;
        this.playersInfo = playersInfo;
        this.mainAct = mainAct;
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
    
    public String getChild(final int n, final int n2) {
        return this.playersInfo.get(this.players.get(n)).get(n2);
    }
    
    public long getChildId(final int n, final int n2) {
        return n2;
    }
    
    public View getChildView(final int n, final int n2, final boolean b, View inflate, final ViewGroup viewGroup) {
        final String child = this.getChild(n, n2);
        inflate = this.context.getLayoutInflater().inflate(2130968609, (ViewGroup)null);
        final TextView textView = (TextView)inflate.findViewById(2131558552);
        final TextView textView2 = (TextView)inflate.findViewById(2131558553);
        final TextView textView3 = (TextView)inflate.findViewById(2131558554);
        textView3.setText((CharSequence)"");
        textView.setText((CharSequence)"");
        textView2.setText((CharSequence)"");
        final String[] split = child.split(">");
        if (split.length == 2) {
            if (child.substring(0, 3).equals("[I]")) {
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
        }
        else {
            textView3.setText((CharSequence)child);
            textView.setVisibility(8);
            textView2.setVisibility(8);
        }
        final Button button = (Button)inflate.findViewById(2131558555);
        button.setText((CharSequence)"View Profile");
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ExpandableListAdapterPlayerStats.this.mainAct.examinePlayer(ExpandableListAdapterPlayerStats.this.players.get(n));
            }
        });
        final Button button2 = (Button)inflate.findViewById(2131558556);
        button2.setText((CharSequence)"Trade");
        button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ExpandableListAdapterPlayerStats.this.mainAct.addTradePlayer(ExpandableListAdapterPlayerStats.this.players.get(n));
            }
        });
        if (!b || this.players.get(n).equals("BENCH > BENCH") || this.players.get(n).equals("DRAFT PICKS > DRAFT PICKS")) {
            button.setVisibility(8);
            button2.setVisibility(8);
            return inflate;
        }
        button.setVisibility(0);
        button2.setVisibility(0);
        return inflate;
    }
    
    public int getChildrenCount(final int n) {
        return this.playersInfo.get(this.players.get(n)).size();
    }
    
    public String getGroup(final int n) {
        return this.players.get(n);
    }
    
    public int getGroupCount() {
        return this.players.size();
    }
    
    public long getGroupId(final int n) {
        return n;
    }
    
    public View getGroupView(int checkAwardPlayer, final boolean b, View inflate, final ViewGroup viewGroup) {
        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968633, (ViewGroup)null);
        final String[] split = this.getGroup(checkAwardPlayer).split(">");
        final TextView textView = (TextView)inflate.findViewById(2131558632);
        textView.setText((CharSequence)split[0]);
        textView.setTypeface((Typeface)null, 1);
        final TextView textView2 = (TextView)inflate.findViewById(2131558633);
        textView2.setText((CharSequence)split[1]);
        textView2.setTypeface((Typeface)null, 1);
        if (this.getGroup(checkAwardPlayer).equals("BENCH > BENCH") || this.getGroup(checkAwardPlayer).equals("DRAFT PICKS > DRAFT PICKS")) {
            checkAwardPlayer = 0;
        }
        else {
            checkAwardPlayer = this.mainAct.checkAwardPlayer(this.getGroup(checkAwardPlayer));
        }
        if (checkAwardPlayer == 2) {
            textView.setTextColor(Color.parseColor("#FF9933"));
            textView2.setTextColor(Color.parseColor("#FF9933"));
        }
        else if (checkAwardPlayer == 1) {
            textView.setTextColor(Color.parseColor("#1A75FF"));
            textView2.setTextColor(Color.parseColor("#1A75FF"));
            return inflate;
        }
        return inflate;
    }
    
    public boolean hasStableIds() {
        return true;
    }
    
    public boolean isChildSelectable(final int n, final int n2) {
        return true;
    }
}
