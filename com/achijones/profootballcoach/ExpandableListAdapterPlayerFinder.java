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
import java.util.Iterator;
import java.util.LinkedHashMap;
import android.graphics.Color;
import android.widget.TextView;
import java.util.List;
import java.util.Map;
import PFCpack.Player;
import java.util.ArrayList;
import android.app.AlertDialog;
import android.app.Activity;
import android.widget.BaseExpandableListAdapter;

public class ExpandableListAdapterPlayerFinder extends BaseExpandableListAdapter
{
    private Activity context;
    private AlertDialog dialog;
    private ArrayList<Player> listCurrPlayers;
    private Map<Player, List<String>> listCurrPlayersInfo;
    private MainActivity mainAct;
    
    public ExpandableListAdapterPlayerFinder(final Activity context, final MainActivity mainAct, final ArrayList<Player> listCurrPlayers, final AlertDialog dialog) {
        this.context = context;
        this.mainAct = mainAct;
        this.listCurrPlayers = listCurrPlayers;
        this.setPlayerInfoMap();
        this.dialog = dialog;
    }
    
    private void colorizeRatings(final TextView textView) {
        final String[] split = textView.getText().toString().split(" ");
        if (split.length > 0) {
            final String s = split[split.length - 1];
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
    
    private String getPlayerDetails(final Player player) {
        final String position = player.getPosition();
        final String letterGrade = Player.getLetterGrade(player.getRatFootIQ());
        final int[] ratings = player.getRatings();
        if (position.equals("QB")) {
            return "Football IQ: " + letterGrade + ">Strength: " + Player.getLetterGrade(ratings[0]) + ">Accuracy: " + Player.getLetterGrade(ratings[1]) + ">Evasion: " + Player.getLetterGrade(ratings[2]);
        }
        if (position.equals("RB")) {
            return "Football IQ: " + letterGrade + ">Power: " + Player.getLetterGrade(ratings[0]) + ">Speed: " + Player.getLetterGrade(ratings[1]) + ">Evasion: " + Player.getLetterGrade(ratings[2]);
        }
        if (position.equals("WR")) {
            return "Football IQ: " + letterGrade + ">Catching: " + Player.getLetterGrade(ratings[0]) + ">Speed: " + Player.getLetterGrade(ratings[1]) + ">Evasion: " + Player.getLetterGrade(ratings[2]);
        }
        if (position.equals("OL")) {
            return "Football IQ: " + letterGrade + ">Strength: " + Player.getLetterGrade(ratings[0]) + ">Rush Blk: " + Player.getLetterGrade(ratings[1]) + ">Pass Blk: " + Player.getLetterGrade(ratings[2]);
        }
        if (position.equals("K")) {
            return "Football IQ: " + letterGrade + ">Kick Power: " + Player.getLetterGrade(ratings[0]) + ">Accuracy: " + Player.getLetterGrade(ratings[1]) + ">Clumsiness: " + Player.getLetterGrade(ratings[2]);
        }
        if (position.equals("S")) {
            return "Football IQ: " + letterGrade + ">Coverage: " + Player.getLetterGrade(ratings[0]) + ">Speed: " + Player.getLetterGrade(ratings[1]) + ">Tackling: " + Player.getLetterGrade(ratings[2]);
        }
        if (position.equals("CB")) {
            return "Football IQ: " + letterGrade + ">Coverage: " + Player.getLetterGrade(ratings[0]) + ">Speed: " + Player.getLetterGrade(ratings[1]) + ">Tackling: " + Player.getLetterGrade(ratings[2]);
        }
        if (position.equals("DL")) {
            return "Football IQ: " + letterGrade + ">Strength: " + Player.getLetterGrade(ratings[0]) + ">Run Stop: " + Player.getLetterGrade(ratings[1]) + ">Pass Press: " + Player.getLetterGrade(ratings[2]);
        }
        if (position.equals("LB")) {
            return "Football IQ: " + letterGrade + ">Tackling: " + Player.getLetterGrade(ratings[0]) + ">Run Stop: " + Player.getLetterGrade(ratings[1]) + ">Pass Cover: " + Player.getLetterGrade(ratings[2]);
        }
        return "ERROR";
    }
    
    private void setPlayerInfoMap() {
        this.listCurrPlayersInfo = new LinkedHashMap<Player, List<String>>();
        for (final Player player : this.listCurrPlayers) {
            final ArrayList<String> list = new ArrayList<String>();
            list.add(this.getPlayerDetails(player));
            this.listCurrPlayersInfo.put(player, list);
        }
    }
    
    public String getChild(final int n, final int n2) {
        return this.listCurrPlayersInfo.get(this.listCurrPlayers.get(n)).get(n2);
    }
    
    public long getChildId(final int n, final int n2) {
        return n2;
    }
    
    public View getChildView(int i, int length, final boolean b, final View view, final ViewGroup viewGroup) {
        final String child = this.getChild(i, length);
        final Player group = this.getGroup(i);
        final LayoutInflater layoutInflater = this.context.getLayoutInflater();
        View inflate = view;
        if (view == null) {
            inflate = layoutInflater.inflate(2130968610, (ViewGroup)null);
        }
        final TextView textView = (TextView)inflate.findViewById(2131558557);
        final TextView textView2 = (TextView)inflate.findViewById(2131558559);
        final TextView textView3 = (TextView)inflate.findViewById(2131558561);
        final TextView textView4 = (TextView)inflate.findViewById(2131558558);
        final TextView textView5 = (TextView)inflate.findViewById(2131558560);
        final TextView textView6 = (TextView)inflate.findViewById(2131558562);
        final TextView textView7 = (TextView)inflate.findViewById(2131558563);
        textView.setText((CharSequence)("Potential: " + Player.getLetterGrade(group.getRatPot())));
        textView2.setText((CharSequence)("Durability: " + Player.getLetterGrade(group.getRatDur())));
        final String[] split = child.split(">");
        textView3.setText((CharSequence)split[0]);
        textView4.setText((CharSequence)split[1]);
        textView5.setText((CharSequence)split[2]);
        textView6.setText((CharSequence)split[3]);
        textView7.setText((CharSequence)("Contract: " + group.getContract().toString()));
        final TextView[] array = { textView, textView2, textView3, textView4, textView5, textView6 };
        for (length = array.length, i = 0; i < length; ++i) {
            this.colorizeRatings(array[i]);
        }
        ((Button)inflate.findViewById(2131558565)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ExpandableListAdapterPlayerFinder.this.mainAct.examinePlayer(group);
            }
        });
        final Button button = (Button)inflate.findViewById(2131558566);
        button.setText((CharSequence)("View " + group.getTeam().abbr + " Roster"));
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                ExpandableListAdapterPlayerFinder.this.mainAct.examineTeamRoster(group.getTeam().name);
                ExpandableListAdapterPlayerFinder.this.dialog.dismiss();
            }
        });
        return inflate;
    }
    
    public int getChildrenCount(final int n) {
        if (this.listCurrPlayers.get(n) != null) {
            return this.listCurrPlayersInfo.get(this.listCurrPlayers.get(n)).size();
        }
        return 0;
    }
    
    public Player getGroup(final int n) {
        return this.listCurrPlayers.get(n);
    }
    
    public int getGroupCount() {
        return this.listCurrPlayers.size();
    }
    
    public long getGroupId(final int n) {
        return n;
    }
    
    public View getGroupView(final int n, final boolean b, final View view, final ViewGroup viewGroup) {
        final String[] split = this.listCurrPlayers.get(n).getPosNameYrOvrPot_Split().split(">");
        final String s = split[0];
        final String text = split[1];
        View inflate = view;
        if (view == null) {
            inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968634, (ViewGroup)null);
        }
        final TextView textView = (TextView)inflate.findViewById(2131558634);
        textView.setTypeface((Typeface)null, 1);
        textView.setText((CharSequence)(this.listCurrPlayers.get(n).getTeam().abbr + " " + s));
        final TextView textView2 = (TextView)inflate.findViewById(2131558636);
        textView2.setTypeface((Typeface)null, 1);
        textView2.setText((CharSequence)text);
        final TextView textView3 = (TextView)inflate.findViewById(2131558635);
        textView3.setTypeface((Typeface)null, 1);
        textView3.setText((CharSequence)"");
        return inflate;
    }
    
    public boolean hasStableIds() {
        return true;
    }
    
    public boolean isChildSelectable(final int n, final int n2) {
        return true;
    }
}
