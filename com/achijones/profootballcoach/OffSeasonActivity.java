// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.widget.BaseExpandableListAdapter;
import android.content.DialogInterface$OnCancelListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.app.ProgressDialog;
import android.view.View$OnClickListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import PFCpack.DraftGenerator;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.app.AlertDialog;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;
import android.widget.CheckBox;
import android.view.ViewGroup;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.content.Intent;
import android.widget.ExpandableListAdapter;
import android.app.Activity;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import android.content.Context;
import android.widget.Toast;
import PFCpack.DraftPick;
import android.graphics.Color;
import PFCpack.Contract;
import PFCpack.PlayerPickerLogic;
import java.util.Comparator;
import java.util.Collections;
import PFCpack.Team;
import PFCpack.League;
import java.io.File;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ExpandableListView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import java.util.List;
import java.util.Map;
import PFCpack.Player;
import java.util.ArrayList;
import android.support.v7.app.AppCompatActivity;

public class OffSeasonActivity extends AppCompatActivity
{
    private final int STAGE_ALL_FREE_AGENCY;
    private final int STAGE_DRAFT;
    private final int STAGE_TEAM_FREE_AGENCY;
    private ArrayList<Player> currPlayers;
    private Map<Player, List<String>> currPlayersInfo;
    private ArrayList<Player> currPlayersUnfiltered;
    private String currentPosition;
    private ArrayAdapter dataAdapterPosition;
    int dayFreeAgency;
    private Button doneButton;
    int draftPickNum;
    ArrayList<ArrayList<Player>> draftPlayers;
    ArrayList<String> draftResultsList;
    int draftRound;
    private ExpandableListAdapterDraft expListAdapter;
    private ExpandableListView expandList;
    ArrayList<ArrayList<Player>> freeAgencyPlayers;
    private int[] indexHeaderPosition;
    boolean isDraft;
    ArrayList<String> leagueFAResultsList;
    private int maximumAge;
    private int minimumAge;
    private int minimumDur;
    private int minimumFootIQ;
    private int minimumPot;
    private TextView offSeasonText;
    private Spinner positionSpinner;
    private ArrayList<String> positions;
    File regSaveLeagueFile;
    String regSaveLeagueFileStr;
    File regSaveScheduleFile;
    String regSaveScheduleFileStr;
    File regSaveTeamHistFile;
    String regSaveTeamHistFileStr;
    File regSaveTeamsFile;
    String regSaveTeamsFileStr;
    private boolean removeUnaffordable;
    private Button resultsButton;
    File saveLeagueFile;
    String saveLeagueFileStr;
    File saveScheduleFile;
    String saveScheduleFileStr;
    File saveTeamHistFile;
    String saveTeamHistFileStr;
    File saveTeamsFile;
    String saveTeamsFileStr;
    int season;
    private boolean showConfirmation;
    League simLeague;
    private int stage;
    ArrayList<String> teamFAResultsList;
    ArrayList<ArrayList<Player>> teamFreeAgencyPlayers;
    Team userTeam;
    
    public OffSeasonActivity() {
        this.STAGE_TEAM_FREE_AGENCY = 0;
        this.STAGE_ALL_FREE_AGENCY = 1;
        this.STAGE_DRAFT = 2;
    }
    
    private void beginDraft(final boolean b) {
        Team team = this.getDraftTeam();
        Collections.sort(this.draftPlayers.get(0), new PlayerComparator());
        while (this.draftRound <= 7 && ((team != this.userTeam && !b) || b)) {
            boolean b2 = false;
            if (this.draftPickNum > 75) {
                b2 = true;
            }
            final Player draftPlayer = PlayerPickerLogic.draftPlayer(team, this.draftPlayers.get(0), b2);
            draftPlayer.setContract(Contract.getContractDraft(draftPlayer, this.draftPickNum));
            draftPlayer.setDraftPickNum(this.draftPickNum);
            draftPlayer.fudgePotentialRookie();
            draftPlayer.randomJumpRookie(this.draftPickNum);
            this.draftPlayerLists(draftPlayer);
            team.draftPicks.remove(this.getDraftPick());
            this.getDraftPick().getTeamOriginal().positionalDraftPicks.remove(this.getDraftPick());
            this.expListAdapter.notifyDataSetChanged();
            ++this.draftPickNum;
            this.draftRound = (this.draftPickNum - 1) / 32 + 1;
            this.offSeasonText.setText((CharSequence)("Draft: Round " + this.draftRound + ", Pick " + this.draftPickNum));
            team = this.getDraftTeam();
        }
        if (this.draftRound > 7) {
            this.currPlayers.clear();
            this.expListAdapter.notifyDataSetChanged();
            this.showDraftSummaryDialog(true);
        }
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
    
    private void draftPlayerLists(final Player player) {
        for (int i = 0; i < this.draftPlayers.size(); ++i) {
            if (this.draftPlayers.get(i).contains(player)) {
                this.draftPlayers.get(i).remove(player);
            }
        }
        this.currPlayers.remove(player);
        final DraftPick draftPick = this.getDraftPick();
        this.draftResultsList.add(this.draftPickNum + ": " + draftPick.getTeamOwner().abbr + " drafts " + player.getPosNameYrOvrPot_Compact() + "\n\t\tUsed " + draftPick.getTeamOriginal().getStrAbbrWL() + "'s round " + draftPick.getRound() + " pick\n\t\tSigned to a " + Contract.getContractDraft(player, this.draftPickNum).toString() + " contract");
    }
    
    private void draftPlayerUserTeamConfirm(final Player player) {
        player.setContract(Contract.getContractDraft(player, this.draftPickNum));
        player.setTeam(this.userTeam);
        player.setDraftPickNum(this.draftPickNum);
        player.fudgePotentialRookie();
        if (player.randomJumpRookie(this.draftPickNum)) {
            Toast.makeText((Context)this, (CharSequence)("Drafted " + player.getPosNameYrOvrPot_OneLine() + " with the number " + this.draftPickNum + " pick. He looks much better than expected!"), 0).show();
        }
        else {
            Toast.makeText((Context)this, (CharSequence)("Drafted " + player.getPosNameYrOvrPot_OneLine() + " with the number " + this.draftPickNum + " pick."), 0).show();
        }
        this.draftPlayerLists(player);
        this.userTeam.addPlayer(player);
        player.addTeamPlayedFor(this.userTeam.abbr, this.simLeague.getYear());
        this.updatePositionNeeds();
        this.userTeam.draftPicks.remove(this.getDraftPick());
        this.getDraftPick().getTeamOriginal().positionalDraftPicks.remove(this.getDraftPick());
        this.expListAdapter.notifyDataSetChanged();
        ++this.draftPickNum;
        this.draftRound = (this.draftPickNum - 1) / 32 + 1;
        this.offSeasonText.setText((CharSequence)("Draft: Round " + this.draftRound + ", Pick " + this.draftPickNum));
        this.beginDraft(false);
    }
    
    private void faPlayerLists(final Player player) {
        for (int i = 0; i < this.freeAgencyPlayers.size(); ++i) {
            if (this.freeAgencyPlayers.get(i).contains(player)) {
                this.freeAgencyPlayers.get(i).remove(player);
            }
        }
        this.simLeague.freeAgents.remove(player);
    }
    
    private DraftPick getDraftPick() {
        return this.simLeague.teamList.get((this.draftPickNum - 1) % 32).positionalDraftPicks.get(0);
    }
    
    private Team getDraftTeam() {
        return this.simLeague.teamList.get((this.draftPickNum - 1) % 32).positionalDraftPicks.get(0).getTeamOwner();
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
        this.currPlayersInfo = new LinkedHashMap<Player, List<String>>();
        for (final Player player : this.currPlayers) {
            final ArrayList<String> list = new ArrayList<String>();
            list.add(this.getPlayerDetails(player));
            this.currPlayersInfo.put(player, list);
        }
    }
    
    private Map<Player, List<String>> setPlayerInfoMapRoster(final ArrayList<Player> list) {
        final LinkedHashMap<Player, ArrayList<String>> linkedHashMap = (LinkedHashMap<Player, ArrayList<String>>)new LinkedHashMap<Player, List<String>>();
        for (final Player player : list) {
            if (player != null) {
                final ArrayList<String> list2 = new ArrayList<String>();
                list2.add(this.getPlayerDetails(player) + ">Contract: " + player.getContract().toString());
                linkedHashMap.put(player, list2);
            }
        }
        return (Map<Player, List<String>>)linkedHashMap;
    }
    
    private void setPlayerList(final String s) {
        if (this.stage == 0) {
            if (!s.equals("All Players")) {
                this.currPlayersUnfiltered = this.teamFreeAgencyPlayers.get(Player.getPosNumber(s) + 1);
            }
            else {
                this.currPlayersUnfiltered = this.teamFreeAgencyPlayers.get(0);
            }
        }
        else if (this.stage == 2) {
            if (!s.equals("All Players")) {
                this.currPlayersUnfiltered = this.draftPlayers.get(Player.getPosNumber(s) + 1);
            }
            else {
                this.currPlayersUnfiltered = this.draftPlayers.get(0);
            }
        }
        else if (!s.equals("All Players")) {
            this.currPlayersUnfiltered = this.freeAgencyPlayers.get(Player.getPosNumber(s) + 1);
        }
        else {
            this.currPlayersUnfiltered = this.freeAgencyPlayers.get(0);
        }
        (this.currPlayers = new ArrayList<Player>()).addAll(this.currPlayersUnfiltered);
        int i = 0;
        while (i < this.currPlayers.size()) {
            if (this.currPlayers.get(i).getRatDur() < this.minimumDur) {
                this.currPlayers.remove(i);
            }
            else if (this.currPlayers.get(i).getRatPot() < this.minimumPot) {
                this.currPlayers.remove(i);
            }
            else if (this.currPlayers.get(i).getRatFootIQ() < this.minimumFootIQ) {
                this.currPlayers.remove(i);
            }
            else if (this.currPlayers.get(i).getAge() < this.minimumAge) {
                this.currPlayers.remove(i);
            }
            else if (this.currPlayers.get(i).getAge() > this.maximumAge) {
                this.currPlayers.remove(i);
            }
            else if (this.removeUnaffordable && this.stage != 2 && this.userTeam.getSalaryCapRoom() < Contract.getContractFA(this.currPlayers.get(i)).getMoneyPerYear() && Contract.getContractFA(this.currPlayers.get(i)).getMoneyPerYear() > 0.5) {
                this.currPlayers.remove(i);
            }
            else {
                ++i;
            }
        }
        this.setPlayerInfoMap();
    }
    
    private void signFAUserTeamConfirm(final Player player) {
        final Contract contractFA = Contract.getContractFA(player);
        player.setContract(contractFA);
        player.setTeam(this.userTeam);
        this.faPlayerLists(player);
        this.userTeam.addPlayer(player);
        this.updatePositionNeeds();
        this.currPlayers.remove(player);
        player.addTeamPlayedFor(this.userTeam.abbr, this.simLeague.getYear());
        Toast.makeText((Context)this, (CharSequence)("Signed " + player.getPosNameYrOvrPot_OneLine() + ", advancing day in free agency"), 0).show();
        this.leagueFAResultsList.add(this.userTeam.abbr + " signs " + player.getPosNameAge_Str() + "\n\t\t" + player.getOvrPotDurFootIQ_Str() + "\n\t\tSigned to a " + contractFA.toString() + " contract");
        int i = 0;
        while (i < this.currPlayers.size()) {
            if (this.removeUnaffordable && this.stage != 2 && this.userTeam.getSalaryCapRoom() < Contract.getContractFA(this.currPlayers.get(i)).getMoneyPerYear() && Contract.getContractFA(this.currPlayers.get(i)).getMoneyPerYear() > 0.5) {
                this.currPlayers.remove(i);
            }
            else {
                ++i;
            }
        }
        ++this.dayFreeAgency;
        this.simLeague.freeAgencyDay(this.leagueFAResultsList);
        this.freeAgencyPlayers = this.transferListsFA(this.simLeague.freeAgents);
        this.currentPosition = this.positionSpinner.getItemAtPosition(this.positionSpinner.getSelectedItemPosition()).toString();
        if (this.positionSpinner.getSelectedItemPosition() == 0) {
            this.setPlayerList(this.currentPosition);
        }
        else {
            this.setPlayerList(this.currentPosition.substring(0, 2).trim());
        }
        this.expListAdapter = new ExpandableListAdapterDraft((Activity)this, this.currPlayers, this.currPlayersInfo, true);
        this.expandList.setAdapter((ExpandableListAdapter)this.expListAdapter);
        this.expListAdapter.notifyDataSetChanged();
        this.offSeasonText.setText((CharSequence)("Day " + this.dayFreeAgency + ", Free Agency: $" + this.userTeam.getSalaryCapRoom() + " mil room"));
    }
    
    private void signTeamFAPlayerUserTeamConfirm(final Player player) {
        final Contract contractFA = Contract.getContractFA(player);
        player.setContract(contractFA);
        this.teamFAPlayerLists(player);
        this.userTeam.addPlayer(player);
        this.userTeam.playersFAs.remove(player);
        this.updatePositionNeeds();
        this.currPlayers.remove(player);
        Toast.makeText((Context)this, (CharSequence)("Signed " + player.getPosNameYrOvrPot_OneLine()), 0).show();
        this.teamFAResultsList.add(this.userTeam.abbr + " signs " + player.getPosNameAge_Str() + "\n\t\t" + player.getOvrPotDurFootIQ_Str() + "\n\t\tSigned to a " + contractFA.toString() + " contract");
        int i = 0;
        while (i < this.currPlayers.size()) {
            if (this.removeUnaffordable && this.stage != 2 && this.userTeam.getSalaryCapRoom() < Contract.getContractFA(this.currPlayers.get(i)).getMoneyPerYear()) {
                this.currPlayers.remove(i);
            }
            else {
                ++i;
            }
        }
        this.expListAdapter.notifyDataSetChanged();
        this.offSeasonText.setText((CharSequence)("Team Free Agency: $" + this.userTeam.getSalaryCapRoom() + " mil room"));
    }
    
    private void teamFAPlayerLists(final Player player) {
        for (int i = 0; i < this.teamFreeAgencyPlayers.size(); ++i) {
            if (this.teamFreeAgencyPlayers.get(i).contains(player)) {
                this.teamFreeAgencyPlayers.get(i).remove(player);
            }
        }
    }
    
    public void advanceNextSeason() {
        this.simLeague.resetForNewSeason();
        this.simLeague.saveLeague(this.regSaveLeagueFile, this.regSaveTeamsFile, this.regSaveScheduleFile, this.regSaveTeamHistFile, false);
        Toast.makeText((Context)this, (CharSequence)"Saved league!", 0).show();
        final Intent intent = new Intent((Context)this, (Class)MainActivity.class);
        intent.putExtra("SAVE_FILE", "LOAD," + this.regSaveLeagueFileStr + "," + this.regSaveTeamsFileStr + "," + this.regSaveScheduleFileStr + "," + this.regSaveTeamHistFileStr);
        this.startActivity(intent);
    }
    
    public void draftPlayerUserTeam(final Player player) {
        if (this.showConfirmation) {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
            alertDialog$Builder.setTitle((CharSequence)"Confirm Signing").setPositiveButton((CharSequence)"Yes", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    OffSeasonActivity.this.draftPlayerUserTeamConfirm(player);
                }
            }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    dialogInterface.dismiss();
                }
            }).setView(this.getLayoutInflater().inflate(2130968673, (ViewGroup)null));
            final AlertDialog create = alertDialog$Builder.create();
            create.show();
            ((TextView)create.findViewById(2131558714)).setText((CharSequence)("Are you sure you want to draft this player?\n" + player.getPosNameYrOvrPot_Compact()));
            final CheckBox checkBox = (CheckBox)create.findViewById(2131558549);
            checkBox.setText((CharSequence)"Show Confirmation Dialogs");
            checkBox.setChecked(this.showConfirmation);
            checkBox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener() {
                public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                    OffSeasonActivity.this.showConfirmation = b;
                }
            });
            return;
        }
        this.draftPlayerUserTeamConfirm(player);
    }
    
    public String[] getStrArrayFromList(final ArrayList<String> list) {
        final String[] array = new String[list.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = list.get(i);
        }
        return array;
    }
    
    public String getUserDraftPicksStr() {
        final StringBuilder sb = new StringBuilder();
        for (final DraftPick draftPick : this.userTeam.draftPicks) {
            if (draftPick.getYear() == this.simLeague.getYear() - 1) {
                sb.append("\t\t" + draftPick.getTeamOriginal().getStrAbbrWL() + "'s Round " + draftPick.getRound() + " pick\n");
            }
        }
        return sb.toString();
    }
    
    public void onBackPressed() {
    }
    
    protected void onCreate(Bundle extras) {
        super.onCreate(extras);
        this.setContentView(2130968605);
        this.setSupportActionBar((Toolbar)this.findViewById(2131558542));
        extras = this.getIntent().getExtras();
        if (extras != null) {
            final String[] split = extras.getString("SAVE_FILE_OFFSEASON").split(",");
            this.saveLeagueFileStr = split[1];
            this.saveTeamsFileStr = split[2];
            this.saveScheduleFileStr = split[3];
            this.saveTeamHistFileStr = split[4];
            this.saveLeagueFile = new File(this.getFilesDir(), split[1]);
            this.saveTeamsFile = new File(this.getFilesDir(), split[2]);
            this.saveScheduleFile = new File(this.getFilesDir(), split[3]);
            this.saveTeamHistFile = new File(this.getFilesDir(), split[4]);
            final String[] split2 = extras.getString("SAVE_FILE").split(",");
            this.regSaveLeagueFileStr = split2[1];
            this.regSaveTeamsFileStr = split2[2];
            this.regSaveScheduleFileStr = split2[3];
            this.regSaveTeamHistFileStr = split2[4];
            this.regSaveLeagueFile = new File(this.getFilesDir(), split2[1]);
            this.regSaveTeamsFile = new File(this.getFilesDir(), split2[2]);
            this.regSaveScheduleFile = new File(this.getFilesDir(), split2[3]);
            this.regSaveTeamHistFile = new File(this.getFilesDir(), split2[4]);
            this.simLeague = new League(this.saveLeagueFile, this.saveTeamsFile, this.saveScheduleFile, this.saveTeamHistFile, this.getString(2131165241), this.getString(2131165240));
            this.userTeam = this.simLeague.userTeam;
            this.season = this.simLeague.getYear();
        }
        this.getSupportActionBar().setTitle((CharSequence)(this.userTeam.abbr + " Offseason, " + (this.simLeague.getYear() - 1)));
        this.draftPlayers = DraftGenerator.generateRookies(this.simLeague);
        this.draftRound = 1;
        this.draftPickNum = 1;
        this.isDraft = false;
        this.dayFreeAgency = 1;
        this.showConfirmation = true;
        this.draftResultsList = new ArrayList<String>();
        this.teamFAResultsList = new ArrayList<String>();
        this.leagueFAResultsList = new ArrayList<String>();
        this.indexHeaderPosition = new int[8];
        for (int i = 0; i < 8; ++i) {
            this.indexHeaderPosition[i] = -1;
        }
        (this.offSeasonText = (TextView)this.findViewById(2131558578)).setText((CharSequence)("Team Free Agency: $" + this.userTeam.getSalaryCapRoom() + " mil room"));
        this.positionSpinner = (Spinner)this.findViewById(2131558580);
        (this.positions = new ArrayList<String>()).add("All Players");
        this.positions.add("QB (Needs: " + this.userTeam.getNeedsPosition("QB") + ")");
        this.positions.add("RB (Needs: " + this.userTeam.getNeedsPosition("RB") + ")");
        this.positions.add("WR (Needs: " + this.userTeam.getNeedsPosition("WR") + ")");
        this.positions.add("OL (Needs: " + this.userTeam.getNeedsPosition("OL") + ")");
        this.positions.add("K (Needs: " + this.userTeam.getNeedsPosition("K") + ")");
        this.positions.add("S (Needs: " + this.userTeam.getNeedsPosition("S") + ")");
        this.positions.add("CB (Needs: " + this.userTeam.getNeedsPosition("CB") + ")");
        this.positions.add("DL (Needs: " + this.userTeam.getNeedsPosition("DL") + ")");
        this.positions.add("LB (Needs: " + this.userTeam.getNeedsPosition("LB") + ")");
        (this.dataAdapterPosition = new ArrayAdapter((Context)this, 17367048, (List)this.positions)).setDropDownViewResource(17367049);
        this.positionSpinner.setAdapter((SpinnerAdapter)this.dataAdapterPosition);
        this.positionSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                OffSeasonActivity.this.currentPosition = adapterView.getItemAtPosition(n).toString();
                if (n == 0) {
                    OffSeasonActivity.this.setPlayerList(OffSeasonActivity.this.currentPosition);
                }
                else {
                    OffSeasonActivity.this.setPlayerList(OffSeasonActivity.this.currentPosition.substring(0, 2).trim());
                }
                OffSeasonActivity.this.expListAdapter = new ExpandableListAdapterDraft((Activity)OffSeasonActivity.this, OffSeasonActivity.this.currPlayers, OffSeasonActivity.this.currPlayersInfo, true);
                OffSeasonActivity.this.expandList.setAdapter((ExpandableListAdapter)OffSeasonActivity.this.expListAdapter);
                OffSeasonActivity.this.expListAdapter.notifyDataSetChanged();
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        (this.resultsButton = (Button)this.findViewById(2131558583)).setText((CharSequence)"Team FA Results");
        this.resultsButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (OffSeasonActivity.this.stage == 2) {
                    OffSeasonActivity.this.showDraftSummaryDialog(false);
                }
                else {
                    if (OffSeasonActivity.this.stage == 0) {
                        OffSeasonActivity.this.showFreeAgencyResultsDialog(OffSeasonActivity.this.teamFAResultsList, "Team Free Agency");
                        return;
                    }
                    if (OffSeasonActivity.this.stage == 1) {
                        OffSeasonActivity.this.showFreeAgencyResultsDialog(OffSeasonActivity.this.leagueFAResultsList, "League Free Agency");
                    }
                }
            }
        });
        (this.doneButton = (Button)this.findViewById(2131558584)).setText((CharSequence)"Done");
        this.doneButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (OffSeasonActivity.this.stage == 2) {
                    final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)OffSeasonActivity.this);
                    alertDialog$Builder.setMessage((CharSequence)("Are you sure you want to finish the draft? The rest of your picks will be selected for you:\n\n" + OffSeasonActivity.this.getUserDraftPicksStr())).setPositiveButton((CharSequence)"Yes, simulate the draft", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            OffSeasonActivity.this.beginDraft(true);
                        }
                    }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                        }
                    });
                    alertDialog$Builder.create().show();
                }
                else {
                    if (OffSeasonActivity.this.stage == 0) {
                        final AlertDialog$Builder alertDialog$Builder2 = new AlertDialog$Builder((Context)OffSeasonActivity.this);
                        alertDialog$Builder2.setMessage((CharSequence)"Are you sure you are done resigning your team free agents?").setPositiveButton((CharSequence)"Yes, go to league FA", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                            public void onClick(final DialogInterface dialogInterface, final int n) {
                                OffSeasonActivity.this.simLeague.transferTeamFAs();
                                OffSeasonActivity.this.stage = 1;
                                OffSeasonActivity.this.resultsButton.setText((CharSequence)"League FA Results");
                                OffSeasonActivity.this.isDraft = false;
                                OffSeasonActivity.this.simLeague.sortFAs();
                                OffSeasonActivity.this.freeAgencyPlayers = OffSeasonActivity.this.transferListsFA(OffSeasonActivity.this.simLeague.freeAgents);
                                OffSeasonActivity.this.positionSpinner.setSelection(0);
                                OffSeasonActivity.this.setPlayerList("All Players");
                                OffSeasonActivity.this.setPlayerInfoMap();
                                OffSeasonActivity.this.expListAdapter = new ExpandableListAdapterDraft((Activity)OffSeasonActivity.this, OffSeasonActivity.this.currPlayers, OffSeasonActivity.this.currPlayersInfo, true);
                                OffSeasonActivity.this.expandList.setAdapter((ExpandableListAdapter)OffSeasonActivity.this.expListAdapter);
                                OffSeasonActivity.this.expListAdapter.notifyDataSetChanged();
                                OffSeasonActivity.this.offSeasonText.setText((CharSequence)("Day " + OffSeasonActivity.this.dayFreeAgency + ", Free Agency: $" + OffSeasonActivity.this.userTeam.getSalaryCapRoom() + " mil room"));
                                OffSeasonActivity.this.showIntroLeagueFADialog();
                            }
                        }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                            public void onClick(final DialogInterface dialogInterface, final int n) {
                            }
                        });
                        alertDialog$Builder2.create().show();
                        return;
                    }
                    if (OffSeasonActivity.this.stage == 1) {
                        final StringBuilder sb = new StringBuilder();
                        sb.append("Are you sure you are done signing free agents? Once you are done you cannot go back.\n\n");
                        sb.append("In the upcoming draft you have the following picks:\n");
                        sb.append(OffSeasonActivity.this.getUserDraftPicksStr());
                        final AlertDialog$Builder alertDialog$Builder3 = new AlertDialog$Builder((Context)OffSeasonActivity.this);
                        alertDialog$Builder3.setMessage((CharSequence)sb.toString()).setPositiveButton((CharSequence)"Yes, go to Draft", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                            public void onClick(final DialogInterface dialogInterface, final int n) {
                                final ProgressDialog show = ProgressDialog.show((Context)OffSeasonActivity.this, (CharSequence)"", (CharSequence)"Simulating Free Agency. Please wait...", true);
                                show.setCancelable(false);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        OffSeasonActivity.this.simLeague.freeAgency();
                                        OffSeasonActivity.this.runOnUiThread((Runnable)new Runnable() {
                                            @Override
                                            public void run() {
                                                show.dismiss();
                                                OffSeasonActivity.this.stage = 2;
                                                OffSeasonActivity.this.simLeague.prepareForDraft();
                                                OffSeasonActivity.this.positionSpinner.setSelection(0);
                                                OffSeasonActivity.this.setPlayerList("All Players");
                                                OffSeasonActivity.this.setPlayerInfoMap();
                                                OffSeasonActivity.this.expListAdapter = new ExpandableListAdapterDraft((Activity)OffSeasonActivity.this, OffSeasonActivity.this.currPlayers, OffSeasonActivity.this.currPlayersInfo, true);
                                                OffSeasonActivity.this.expandList.setAdapter((ExpandableListAdapter)OffSeasonActivity.this.expListAdapter);
                                                OffSeasonActivity.this.offSeasonText.setText((CharSequence)("Draft: Round " + OffSeasonActivity.this.draftRound + ", Pick " + OffSeasonActivity.this.draftPickNum));
                                                OffSeasonActivity.this.resultsButton.setText((CharSequence)"Draft Results");
                                                OffSeasonActivity.this.isDraft = true;
                                                OffSeasonActivity.this.beginDraft(false);
                                            }
                                        });
                                    }
                                }).start();
                            }
                        }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                            public void onClick(final DialogInterface dialogInterface, final int n) {
                            }
                        });
                        alertDialog$Builder3.create().show();
                    }
                }
            }
        });
        ((Button)this.findViewById(2131558582)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)OffSeasonActivity.this);
                alertDialog$Builder.setTitle((CharSequence)"Current Roster");
                OffSeasonActivity.this.indexHeaderPosition = new int[8];
                final ArrayList<Player> list = new ArrayList<Player>();
                OffSeasonActivity.this.userTeam.sortPlayers();
                list.add(null);
                list.addAll(OffSeasonActivity.this.userTeam.teamQBs);
                OffSeasonActivity.this.indexHeaderPosition[0] = OffSeasonActivity.this.userTeam.teamQBs.size() + 1;
                list.add(null);
                list.addAll(OffSeasonActivity.this.userTeam.teamRBs);
                OffSeasonActivity.this.indexHeaderPosition[1] = OffSeasonActivity.this.indexHeaderPosition[0] + OffSeasonActivity.this.userTeam.teamRBs.size() + 1;
                list.add(null);
                list.addAll(OffSeasonActivity.this.userTeam.teamWRs);
                OffSeasonActivity.this.indexHeaderPosition[2] = OffSeasonActivity.this.indexHeaderPosition[1] + OffSeasonActivity.this.userTeam.teamWRs.size() + 1;
                list.add(null);
                list.addAll(OffSeasonActivity.this.userTeam.teamOLs);
                OffSeasonActivity.this.indexHeaderPosition[3] = OffSeasonActivity.this.indexHeaderPosition[2] + OffSeasonActivity.this.userTeam.teamOLs.size() + 1;
                list.add(null);
                list.addAll(OffSeasonActivity.this.userTeam.teamKs);
                OffSeasonActivity.this.indexHeaderPosition[4] = OffSeasonActivity.this.indexHeaderPosition[3] + OffSeasonActivity.this.userTeam.teamKs.size() + 1;
                list.add(null);
                list.addAll(OffSeasonActivity.this.userTeam.teamSs);
                OffSeasonActivity.this.indexHeaderPosition[5] = OffSeasonActivity.this.indexHeaderPosition[4] + OffSeasonActivity.this.userTeam.teamSs.size() + 1;
                list.add(null);
                list.addAll(OffSeasonActivity.this.userTeam.teamCBs);
                OffSeasonActivity.this.indexHeaderPosition[6] = OffSeasonActivity.this.indexHeaderPosition[5] + OffSeasonActivity.this.userTeam.teamCBs.size() + 1;
                list.add(null);
                list.addAll(OffSeasonActivity.this.userTeam.teamDLs);
                OffSeasonActivity.this.indexHeaderPosition[7] = OffSeasonActivity.this.indexHeaderPosition[6] + OffSeasonActivity.this.userTeam.teamDLs.size() + 1;
                list.add(null);
                list.addAll(OffSeasonActivity.this.userTeam.teamLBs);
                final Map access$1300 = OffSeasonActivity.this.setPlayerInfoMapRoster(list);
                final ExpandableListView view2 = new ExpandableListView((Context)OffSeasonActivity.this);
                view2.setAdapter((ExpandableListAdapter)new ExpandableListAdapterDraft((Activity)OffSeasonActivity.this, list, access$1300, false));
                alertDialog$Builder.setView((View)view2);
                alertDialog$Builder.setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog$Builder.create().show();
            }
        });
        this.stage = 0;
        this.simLeague.signExpiringFAs();
        Collections.sort(this.userTeam.playersFAs, new PlayerComparator());
        this.teamFreeAgencyPlayers = this.transferListsFA(this.userTeam.playersFAs);
        this.expandList = (ExpandableListView)this.findViewById(2131558581);
        this.setPlayerList("All Players");
        this.setPlayerInfoMap();
        this.expListAdapter = new ExpandableListAdapterDraft((Activity)this, this.currPlayers, this.currPlayersInfo, false);
        this.expandList.setAdapter((ExpandableListAdapter)this.expListAdapter);
        this.minimumDur = 50;
        this.minimumPot = 50;
        this.minimumFootIQ = 50;
        this.minimumAge = 20;
        this.maximumAge = 40;
        this.removeUnaffordable = false;
        ((Button)this.findViewById(2131558579)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                OffSeasonActivity.this.showFilterDialog();
            }
        });
    }
    
    public void setMaxAge(final int maximumAge) {
        this.maximumAge = maximumAge;
    }
    
    public void setMinAge(final int minimumAge) {
        this.minimumAge = minimumAge;
    }
    
    public void setMinDurability(final int minimumDur) {
        this.minimumDur = minimumDur;
    }
    
    public void setMinFootIQ(final int minimumFootIQ) {
        this.minimumFootIQ = minimumFootIQ;
    }
    
    public void setMinPotential(final int minimumPot) {
        this.minimumPot = minimumPot;
    }
    
    public void setRemoveUnaffordable(final boolean removeUnaffordable) {
        this.removeUnaffordable = removeUnaffordable;
    }
    
    public void showDraftSummaryDialog(final boolean b) {
        if (!b) {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
            alertDialog$Builder.setTitle((CharSequence)"Draft Summary").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                }
            }).setView(this.getLayoutInflater().inflate(2130968660, (ViewGroup)null));
            final AlertDialog create = alertDialog$Builder.create();
            create.show();
            ((ListView)create.findViewById(2131558679)).setAdapter((ListAdapter)new LeagueHistoryListArrayAdapter((Context)this, this.getStrArrayFromList(this.draftResultsList), this.userTeam.abbr));
            return;
        }
        final AlertDialog$Builder alertDialog$Builder2 = new AlertDialog$Builder((Context)this);
        alertDialog$Builder2.setTitle((CharSequence)"Draft Summary").setPositiveButton((CharSequence)"Advance to Season", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                OffSeasonActivity.this.advanceNextSeason();
            }
        }).setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                OffSeasonActivity.this.advanceNextSeason();
            }
        }).setCancelable(false).setView(this.getLayoutInflater().inflate(2130968660, (ViewGroup)null));
        final AlertDialog create2 = alertDialog$Builder2.create();
        create2.show();
        ((ListView)create2.findViewById(2131558679)).setAdapter((ListAdapter)new LeagueHistoryListArrayAdapter((Context)this, this.getStrArrayFromList(this.draftResultsList), this.userTeam.abbr));
    }
    
    public void showFilterDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Filter Players").setPositiveButton((CharSequence)"Apply", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                OffSeasonActivity.this.currentPosition = OffSeasonActivity.this.positionSpinner.getItemAtPosition(OffSeasonActivity.this.positionSpinner.getSelectedItemPosition()).toString();
                if (OffSeasonActivity.this.positionSpinner.getSelectedItemPosition() == 0) {
                    OffSeasonActivity.this.setPlayerList(OffSeasonActivity.this.currentPosition);
                }
                else {
                    OffSeasonActivity.this.setPlayerList(OffSeasonActivity.this.currentPosition.substring(0, 2).trim());
                }
                OffSeasonActivity.this.expListAdapter = new ExpandableListAdapterDraft((Activity)OffSeasonActivity.this, OffSeasonActivity.this.currPlayers, OffSeasonActivity.this.currPlayersInfo, true);
                OffSeasonActivity.this.expandList.setAdapter((ExpandableListAdapter)OffSeasonActivity.this.expListAdapter);
                OffSeasonActivity.this.expListAdapter.notifyDataSetChanged();
            }
        }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        }).setView(this.getLayoutInflater().inflate(2130968629, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        ((Button)create.findViewById(2131558601)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                for (int i = 0; i < OffSeasonActivity.this.currPlayers.size(); ++i) {
                    OffSeasonActivity.this.expandList.expandGroup(i, false);
                }
                create.dismiss();
            }
        });
        ((Button)create.findViewById(2131558602)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                for (int i = 0; i < OffSeasonActivity.this.currPlayers.size(); ++i) {
                    OffSeasonActivity.this.expandList.collapseGroup(i);
                }
                create.dismiss();
            }
        });
        final String[] array = { "F", "D", "C", "B", "A" };
        final String[] array2 = new String[21];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = i + 20 + "";
        }
        final Spinner spinner = (Spinner)create.findViewById(2131558603);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])array);
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        spinner.setSelection((this.minimumPot - 50) / 10);
        final Spinner spinner2 = (Spinner)create.findViewById(2131558604);
        final ArrayAdapter adapter2 = new ArrayAdapter((Context)this, 17367048, (Object[])array);
        adapter2.setDropDownViewResource(17367049);
        spinner2.setAdapter((SpinnerAdapter)adapter2);
        spinner2.setSelection((this.minimumDur - 50) / 10);
        final Spinner spinner3 = (Spinner)create.findViewById(2131558605);
        final ArrayAdapter adapter3 = new ArrayAdapter((Context)this, 17367048, (Object[])array);
        adapter3.setDropDownViewResource(17367049);
        spinner3.setAdapter((SpinnerAdapter)adapter3);
        spinner3.setSelection((this.minimumFootIQ - 50) / 10);
        final Spinner spinner4 = (Spinner)create.findViewById(2131558606);
        final ArrayAdapter adapter4 = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
        adapter4.setDropDownViewResource(17367049);
        spinner4.setAdapter((SpinnerAdapter)adapter4);
        spinner4.setSelection(this.minimumAge - 20);
        final Spinner spinner5 = (Spinner)create.findViewById(2131558607);
        final ArrayAdapter adapter5 = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
        adapter5.setDropDownViewResource(17367049);
        spinner5.setAdapter((SpinnerAdapter)adapter5);
        spinner5.setSelection(this.maximumAge - 20);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                OffSeasonActivity.this.setMinPotential(n * 10 + 50);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner2.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                OffSeasonActivity.this.setMinDurability(n * 10 + 50);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner3.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                OffSeasonActivity.this.setMinFootIQ(n * 10 + 50);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner4.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                OffSeasonActivity.this.setMinAge(n + 20);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner5.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                OffSeasonActivity.this.setMaxAge(n + 20);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        final CheckBox checkBox = (CheckBox)create.findViewById(2131558608);
        checkBox.setChecked(this.removeUnaffordable);
        checkBox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean removeUnaffordable) {
                OffSeasonActivity.this.setRemoveUnaffordable(removeUnaffordable);
            }
        });
    }
    
    public void showFreeAgencyResultsDialog(final ArrayList<String> list, final String title) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)title).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968660, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        ((ListView)create.findViewById(2131558679)).setAdapter((ListAdapter)new LeagueHistoryListArrayAdapter((Context)this, this.getStrArrayFromList(list), this.userTeam.abbr));
    }
    
    public void showIntroLeagueFADialog() {
        final String string = this.getResources().getString(2131165250);
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setMessage((CharSequence)string).setTitle((CharSequence)"League Free Agency").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        ((TextView)create.findViewById(16908299)).setTextSize(2, 14.0f);
    }
    
    public void signFAPlayerUserTeam(final Player player) {
        if (this.showConfirmation) {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
            alertDialog$Builder.setTitle((CharSequence)"Confirm Signing").setPositiveButton((CharSequence)"Yes", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    OffSeasonActivity.this.signFAUserTeamConfirm(player);
                }
            }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    dialogInterface.dismiss();
                }
            }).setView(this.getLayoutInflater().inflate(2130968673, (ViewGroup)null));
            final AlertDialog create = alertDialog$Builder.create();
            create.show();
            ((TextView)create.findViewById(2131558714)).setText((CharSequence)("Are you sure you want to sign this player?\n" + player.getPosNameYrOvrPot_Compact()));
            final CheckBox checkBox = (CheckBox)create.findViewById(2131558549);
            checkBox.setText((CharSequence)"Show Confirmation Dialogs");
            checkBox.setChecked(this.showConfirmation);
            checkBox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener() {
                public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                    OffSeasonActivity.this.showConfirmation = b;
                }
            });
            return;
        }
        this.signFAUserTeamConfirm(player);
    }
    
    public void signTeamFAPlayerUserTeam(final Player player) {
        if (this.showConfirmation) {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
            alertDialog$Builder.setTitle((CharSequence)"Confirm Signing").setPositiveButton((CharSequence)"Yes", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    OffSeasonActivity.this.signTeamFAPlayerUserTeamConfirm(player);
                }
            }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    dialogInterface.dismiss();
                }
            }).setView(this.getLayoutInflater().inflate(2130968673, (ViewGroup)null));
            final AlertDialog create = alertDialog$Builder.create();
            create.show();
            ((TextView)create.findViewById(2131558714)).setText((CharSequence)("Are you sure you want to sign this player?\n" + player.getPosNameYrOvrPot_Compact()));
            final CheckBox checkBox = (CheckBox)create.findViewById(2131558549);
            checkBox.setText((CharSequence)"Show Confirmation Dialogs");
            checkBox.setChecked(this.showConfirmation);
            checkBox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener() {
                public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                    OffSeasonActivity.this.showConfirmation = b;
                }
            });
            return;
        }
        this.signTeamFAPlayerUserTeamConfirm(player);
    }
    
    public ArrayList<ArrayList<Player>> transferListsFA(final ArrayList<Player> list) {
        final ArrayList<ArrayList<Player>> list2 = new ArrayList<ArrayList<Player>>();
        for (int i = 0; i < 10; ++i) {
            list2.add(new ArrayList<Player>());
        }
        for (final Player player : list) {
            list2.get(0).add(player);
            list2.get(Player.getPosNumber(player.getPosition()) + 1).add(player);
        }
        return list2;
    }
    
    public void updatePositionNeeds() {
        if (this.dataAdapterPosition != null) {
            (this.positions = new ArrayList<String>()).add("All Players");
            this.positions.add("QB (Needs: " + this.userTeam.getNeedsPosition("QB") + ")");
            this.positions.add("RB (Needs: " + this.userTeam.getNeedsPosition("RB") + ")");
            this.positions.add("WR (Needs: " + this.userTeam.getNeedsPosition("WR") + ")");
            this.positions.add("OL (Needs: " + this.userTeam.getNeedsPosition("OL") + ")");
            this.positions.add("K (Needs: " + this.userTeam.getNeedsPosition("K") + ")");
            this.positions.add("S (Needs: " + this.userTeam.getNeedsPosition("S") + ")");
            this.positions.add("CB (Needs: " + this.userTeam.getNeedsPosition("CB") + ")");
            this.positions.add("DL (Needs: " + this.userTeam.getNeedsPosition("DL") + ")");
            this.positions.add("LB (Needs: " + this.userTeam.getNeedsPosition("LB") + ")");
            this.dataAdapterPosition.clear();
            final Iterator<String> iterator = this.positions.iterator();
            while (iterator.hasNext()) {
                this.dataAdapterPosition.add((Object)iterator.next());
            }
            this.dataAdapterPosition.notifyDataSetChanged();
        }
    }
    
    public class ExpandableListAdapterDraft extends BaseExpandableListAdapter
    {
        private Activity context;
        private boolean isDraftOrFA;
        private ArrayList<Player> listCurrPlayers;
        private Map<Player, List<String>> listCurrPlayersInfo;
        
        public ExpandableListAdapterDraft(final Activity context, final ArrayList<Player> listCurrPlayers, final Map<Player, List<String>> listCurrPlayersInfo, final boolean isDraftOrFA) {
            this.context = context;
            this.listCurrPlayers = listCurrPlayers;
            this.listCurrPlayersInfo = listCurrPlayersInfo;
            this.isDraftOrFA = isDraftOrFA;
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
            if (split.length > 4) {
                textView7.setText((CharSequence)split[4]);
            }
            else {
                textView7.setVisibility(8);
            }
            final TextView[] array = { textView, textView2, textView3, textView4, textView5, textView6 };
            for (length = array.length, i = 0; i < length; ++i) {
                OffSeasonActivity.this.colorizeRatings(array[i]);
            }
            final Button button = (Button)inflate.findViewById(2131558565);
            if (OffSeasonActivity.this.stage != 2 || !this.isDraftOrFA) {
                button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)OffSeasonActivity.this);
                        final ArrayList<String> detailStatsOffseason = group.getDetailStatsOffseason();
                        detailStatsOffseason.add(0, "[B]" + group.getAgeOvrPot_Str());
                        alertDialog$Builder.setAdapter((ListAdapter)new PlayerStatsListArrayAdapter((Context)OffSeasonActivity.this, detailStatsOffseason.toArray(new String[detailStatsOffseason.size()])), (DialogInterface$OnClickListener)null).setTitle((CharSequence)(group.getPosition() + " " + group.getName())).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                            public void onClick(final DialogInterface dialogInterface, final int n) {
                            }
                        });
                        alertDialog$Builder.create().show();
                    }
                });
            }
            else {
                button.setVisibility(8);
            }
            if (this.isDraftOrFA) {
                final Button button2 = (Button)inflate.findViewById(2131558566);
                Contract contract;
                if (OffSeasonActivity.this.stage == 2) {
                    contract = Contract.getContractDraft(group, OffSeasonActivity.this.draftPickNum);
                }
                else {
                    contract = Contract.getContractFA(group);
                }
                button2.setText((CharSequence)("Sign for " + contract.getYearsLeft() + " yrs, $" + contract.getMoneyPerYear() + "mil/yr"));
                button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        if (OffSeasonActivity.this.stage == 2 && OffSeasonActivity.this.draftRound <= 7) {
                            OffSeasonActivity.this.draftPlayerUserTeam(group);
                            return;
                        }
                        if (OffSeasonActivity.this.stage == 0) {
                            if (OffSeasonActivity.this.userTeam.getSalaryCapRoom() >= Contract.getContractFA(group).getMoneyPerYear() || Contract.getContractFA(group).getMoneyPerYear() <= 0.5) {
                                OffSeasonActivity.this.signTeamFAPlayerUserTeam(group);
                                return;
                            }
                            Toast.makeText((Context)OffSeasonActivity.this, (CharSequence)"Not enough cap room!", 0).show();
                        }
                        else {
                            if (OffSeasonActivity.this.userTeam.getSalaryCapRoom() >= Contract.getContractFA(group).getMoneyPerYear() || Contract.getContractFA(group).getMoneyPerYear() <= 0.5) {
                                OffSeasonActivity.this.signFAPlayerUserTeam(group);
                                return;
                            }
                            Toast.makeText((Context)OffSeasonActivity.this, (CharSequence)"Not enough cap room!", 0).show();
                        }
                    }
                });
                return inflate;
            }
            ((Button)inflate.findViewById(2131558566)).setVisibility(8);
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
        
        public View getGroupView(final int n, final boolean b, View view, final ViewGroup viewGroup) {
            if (this.listCurrPlayers.size() > 0) {
                if (this.isDraftOrFA && this.listCurrPlayers.get(n) != null) {
                    final String[] split = this.listCurrPlayers.get(n).getPosNameYrOvrPotImprove_Str().split(">");
                    final String text = split[0];
                    final String text2 = split[1];
                    View inflate;
                    if ((inflate = view) == null) {
                        inflate = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968634, (ViewGroup)null);
                    }
                    final TextView textView = (TextView)inflate.findViewById(2131558634);
                    textView.setTypeface((Typeface)null, 1);
                    textView.setText((CharSequence)text);
                    final TextView textView2 = (TextView)inflate.findViewById(2131558636);
                    textView2.setTypeface((Typeface)null, 1);
                    textView2.setText((CharSequence)text2);
                    final TextView textView3 = (TextView)inflate.findViewById(2131558635);
                    textView3.setTypeface((Typeface)null, 1);
                    textView3.setText((CharSequence)"");
                    view = inflate;
                    if (!this.isDraftOrFA) {
                        view = inflate;
                        if (this.listCurrPlayers.get(n).isStarter) {
                            textView.setTextColor(Color.parseColor("#1A75FF"));
                            textView2.setTextColor(Color.parseColor("#1A75FF"));
                            view = inflate;
                        }
                    }
                }
                else {
                    final View inflate2 = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(2130968634, (ViewGroup)null);
                    if (n == 0) {
                        final TextView textView4 = (TextView)inflate2.findViewById(2131558635);
                        textView4.setTypeface((Typeface)null, 1);
                        textView4.setText((CharSequence)"Quarterback (1 starter)");
                        view = inflate2;
                    }
                    else if (n == OffSeasonActivity.this.indexHeaderPosition[0]) {
                        final TextView textView5 = (TextView)inflate2.findViewById(2131558635);
                        textView5.setTypeface((Typeface)null, 1);
                        textView5.setText((CharSequence)"Runningback (2 starters)");
                        view = inflate2;
                    }
                    else if (n == OffSeasonActivity.this.indexHeaderPosition[1]) {
                        final TextView textView6 = (TextView)inflate2.findViewById(2131558635);
                        textView6.setTypeface((Typeface)null, 1);
                        textView6.setText((CharSequence)"Wide Receiver (3 starters)");
                        view = inflate2;
                    }
                    else if (n == OffSeasonActivity.this.indexHeaderPosition[2]) {
                        final TextView textView7 = (TextView)inflate2.findViewById(2131558635);
                        textView7.setTypeface((Typeface)null, 1);
                        textView7.setText((CharSequence)"Offensive Line (5 starters)");
                        view = inflate2;
                    }
                    else if (n == OffSeasonActivity.this.indexHeaderPosition[3]) {
                        final TextView textView8 = (TextView)inflate2.findViewById(2131558635);
                        textView8.setTypeface((Typeface)null, 1);
                        textView8.setText((CharSequence)"Kicker (1 starter)");
                        view = inflate2;
                    }
                    else if (n == OffSeasonActivity.this.indexHeaderPosition[4]) {
                        final TextView textView9 = (TextView)inflate2.findViewById(2131558635);
                        textView9.setTypeface((Typeface)null, 1);
                        textView9.setText((CharSequence)"Safety (1 starter)");
                        view = inflate2;
                    }
                    else if (n == OffSeasonActivity.this.indexHeaderPosition[5]) {
                        final TextView textView10 = (TextView)inflate2.findViewById(2131558635);
                        textView10.setTypeface((Typeface)null, 1);
                        textView10.setText((CharSequence)"Cornerback (3 starters)");
                        view = inflate2;
                    }
                    else if (n == OffSeasonActivity.this.indexHeaderPosition[6]) {
                        final TextView textView11 = (TextView)inflate2.findViewById(2131558635);
                        textView11.setTypeface((Typeface)null, 1);
                        textView11.setText((CharSequence)"Defensive Line (4 starters)");
                        view = inflate2;
                    }
                    else if (n == OffSeasonActivity.this.indexHeaderPosition[7]) {
                        final TextView textView12 = (TextView)inflate2.findViewById(2131558635);
                        textView12.setTypeface((Typeface)null, 1);
                        textView12.setText((CharSequence)"Linebacker (3 starters)");
                        view = inflate2;
                    }
                    else {
                        view = inflate2;
                        if (this.listCurrPlayers.get(n) != null) {
                            final String[] split2 = this.listCurrPlayers.get(n).getPosNameYrOvrPotImprove_Str().split(">");
                            final String text3 = split2[0];
                            final String text4 = split2[1];
                            final TextView textView13 = (TextView)inflate2.findViewById(2131558634);
                            textView13.setTypeface((Typeface)null, 1);
                            textView13.setText((CharSequence)text3);
                            final TextView textView14 = (TextView)inflate2.findViewById(2131558636);
                            textView14.setTypeface((Typeface)null, 1);
                            textView14.setText((CharSequence)text4);
                            final TextView textView15 = (TextView)inflate2.findViewById(2131558635);
                            textView15.setTypeface((Typeface)null, 1);
                            textView15.setText((CharSequence)"");
                            view = inflate2;
                            if (this.listCurrPlayers.get(n).isStarter) {
                                textView13.setTextColor(Color.parseColor("#1A75FF"));
                                textView14.setTextColor(Color.parseColor("#1A75FF"));
                                view = inflate2;
                            }
                        }
                    }
                }
                return view;
            }
            return null;
        }
        
        public boolean hasStableIds() {
            return true;
        }
        
        public boolean isChildSelectable(final int n, final int n2) {
            return true;
        }
    }
}
