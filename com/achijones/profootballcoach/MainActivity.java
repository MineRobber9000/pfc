// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.os.AsyncTask;
import android.content.DialogInterface$OnShowListener;
import android.view.MenuItem;
import android.view.Menu;
import android.app.ProgressDialog;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;
import android.content.DialogInterface$OnCancelListener;
import PFCpack.DraftPick;
import PFCpack.TradePiece;
import PFCpack.Game;
import android.widget.AdapterView$OnItemLongClickListener;
import android.widget.ExpandableListView$OnChildClickListener;
import android.widget.ExpandableListAdapter;
import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.CompoundButton$OnCheckedChangeListener;
import PFCpack.TeamStrategy;
import java.util.Collection;
import android.widget.ListAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.widget.Toast;
import android.view.View;
import android.app.AlertDialog;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.view.ViewGroup;
import android.content.Context;
import android.app.AlertDialog$Builder;
import PFCpack.Player;
import android.widget.TextView;
import java.util.ArrayList;
import PFCpack.League;
import java.io.File;
import org.json.JSONObject;
import android.widget.ListView;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import PFCpack.Trade;
import PFCpack.Team;
import PFCpack.Division;
import java.util.List;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity
{
    List<String> confList;
    int currTab;
    Division currentDivision;
    Team currentTeam;
    Trade currentTrade;
    ArrayAdapter<String> dataAdapterConf;
    ArrayAdapter<String> dataAdapterTeam;
    Spinner examineConfSpinner;
    Spinner examineTeamSpinner;
    ExpandableListView expListPlayerStats;
    final int[] findFilter;
    ListView mainList;
    JSONObject onlineTeamDict;
    int recruitingStage;
    File saveLeagueFile;
    String saveLeagueFileStr;
    File saveScheduleFile;
    String saveScheduleFileStr;
    File saveTeamHistFile;
    String saveTeamHistFileStr;
    File saveTeamsFile;
    String saveTeamsFileStr;
    int season;
    boolean showInjuryReport;
    boolean showToasts;
    boolean showTutorial;
    boolean shownTutRoster;
    boolean shownTutSchedule;
    boolean shownTutTeamStats;
    boolean shownTutTrading;
    League simLeague;
    List<String> teamList;
    Team userTeam;
    String userTeamStr;
    int wantUpdateConf;
    
    public MainActivity() {
        this.findFilter = new int[] { 0, 50, 50, 50, 50, 20, 40, 0 };
    }
    
    private void changeTeamNameDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Settings / Change Name").setView(this.getLayoutInflater().inflate(2130968608, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final EditText editText = (EditText)create.findViewById(2131558545);
        editText.setText((CharSequence)this.userTeam.name);
        editText.addTextChangedListener((TextWatcher)new TextWatcher() {
            String newName;
            final /* synthetic */ TextView val$invalidNameText = (TextView)create.findViewById(2131558546);
            
            public void afterTextChanged(final Editable editable) {
                this.newName = editable.toString().trim();
                if (!MainActivity.this.simLeague.isNameValid(this.newName)) {
                    this.val$invalidNameText.setText((CharSequence)"Name already in use or has illegal characters!");
                    return;
                }
                this.val$invalidNameText.setText((CharSequence)"");
            }
            
            public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                this.newName = charSequence.toString().trim();
                if (!MainActivity.this.simLeague.isNameValid(this.newName)) {
                    this.val$invalidNameText.setText((CharSequence)"Name already in use or has illegal characters!");
                    return;
                }
                this.val$invalidNameText.setText((CharSequence)"");
            }
            
            public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
                this.newName = charSequence.toString().trim();
                if (!MainActivity.this.simLeague.isNameValid(this.newName)) {
                    this.val$invalidNameText.setText((CharSequence)"Name already in use or has illegal characters!");
                    return;
                }
                this.val$invalidNameText.setText((CharSequence)"");
            }
        });
        final CheckBox checkBox = (CheckBox)create.findViewById(2131558547);
        checkBox.setChecked(this.showToasts);
        final CheckBox checkBox2 = (CheckBox)create.findViewById(2131558548);
        checkBox2.setChecked(this.showInjuryReport);
        final CheckBox checkBox3 = (CheckBox)create.findViewById(2131558549);
        checkBox3.setChecked(this.showTutorial);
        final Button button = (Button)create.findViewById(2131558550);
        final Button button2 = (Button)create.findViewById(2131558551);
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                MainActivity.this.showToasts = checkBox.isChecked();
                MainActivity.this.showInjuryReport = checkBox2.isChecked();
                MainActivity.this.showTutorial = checkBox3.isChecked();
                MainActivity.this.userTeam.showPopups = MainActivity.this.showToasts;
                create.dismiss();
            }
        });
        button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final String trim = editText.getText().toString().trim();
                if (MainActivity.this.simLeague.isNameValid(trim)) {
                    MainActivity.this.userTeam.name = trim;
                    MainActivity.this.getSupportActionBar().setTitle((CharSequence)(MainActivity.this.userTeam.name + " " + MainActivity.this.season + " Season"));
                    MainActivity.this.examineTeam(MainActivity.this.userTeam.name);
                }
                else if (MainActivity.this.showToasts) {
                    Toast.makeText((Context)MainActivity.this, (CharSequence)"Invalid name! Name not changed.", 0).show();
                }
                MainActivity.this.showToasts = checkBox.isChecked();
                MainActivity.this.showInjuryReport = checkBox2.isChecked();
                MainActivity.this.showTutorial = checkBox3.isChecked();
                MainActivity.this.userTeam.showPopups = MainActivity.this.showToasts;
                create.dismiss();
            }
        });
    }
    
    private void exitMainActivity() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setMessage((CharSequence)"Are you sure you want to return to main menu? Progress will be saved.").setPositiveButton((CharSequence)"Yes, Exit", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                MainActivity.this.saveLeagueOnFile();
                MainActivity.this.startActivity(new Intent((Context)MainActivity.this, (Class)Home.class));
            }
        }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        alertDialog$Builder.create().show();
    }
    
    private String[] getOnlineTeams() {
        final String[] array = new String[5];
        int i = 0;
    Label_0088_Outer:
        while (i < 5) {
            final File file = new File(this.getFilesDir(), "onlineTeam" + i + ".cfb");
            while (true) {
                Label_0134: {
                    if (!file.exists()) {
                        break Label_0134;
                    }
                    try {
                        final String line = new BufferedReader(new FileReader(file)).readLine();
                        array[i] = line.substring(0, line.length());
                        ++i;
                        continue Label_0088_Outer;
                    }
                    catch (FileNotFoundException ex) {
                        System.out.println("Unable to open file");
                        continue;
                    }
                    catch (IOException ex2) {
                        System.out.println("Error reading file");
                        continue;
                    }
                    catch (NullPointerException ex3) {
                        System.out.println("Null pointer exception!");
                        continue;
                    }
                }
                array[i] = "EMPTY";
                continue;
            }
        }
        return array;
    }
    
    private String[] getSaveFileInfos() {
        final String[] array = new String[10];
        int i = 0;
    Label_0092_Outer:
        while (i < 10) {
            final File file = new File(this.getFilesDir(), "saveFile" + i + ".cfb");
            while (true) {
                Label_0125: {
                    if (!file.exists()) {
                        break Label_0125;
                    }
                    try {
                        final String line = new BufferedReader(new FileReader(file)).readLine();
                        array[i] = line.substring(0, line.length() - 1);
                        ++i;
                        continue Label_0092_Outer;
                    }
                    catch (FileNotFoundException ex) {
                        System.out.println("Unable to open file");
                        continue;
                    }
                    catch (IOException ex2) {
                        System.out.println("Error reading file");
                        continue;
                    }
                }
                array[i] = "EMPTY";
                continue;
            }
        }
        return array;
    }
    
    private void mvpCeremony() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Regular Season Summary").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968665, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        String[] array;
        if (this.simLeague.currentWeek < 16) {
            array = new String[] { "MVP" };
        }
        else {
            array = new String[] { "MVP", "All Pro", "Playoff Picture", "Division Standings" };
        }
        final Spinner spinner = (Spinner)create.findViewById(2131558689);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])array);
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            final /* synthetic */ String[] val$allPros = MainActivity.this.simLeague.getAllProStr().split(">");
            final /* synthetic */ ListView val$potyList = (ListView)create.findViewById(2131558690);
            
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (n == 0) {
                    this.val$potyList.setAdapter((ListAdapter)new SeasonAwardsListArrayAdapter((Context)MainActivity.this, MainActivity.this.simLeague.getMVPCeremonyStr().split(">"), MainActivity.this.userTeam.abbr));
                }
                else {
                    if (n == 1) {
                        this.val$potyList.setAdapter((ListAdapter)new SeasonAwardsListArrayAdapter((Context)MainActivity.this, this.val$allPros, MainActivity.this.userTeam.abbr));
                        return;
                    }
                    if (n == 2) {
                        this.val$potyList.setAdapter((ListAdapter)new TeamRankingsListArrayAdapter((Context)MainActivity.this, MainActivity.this.simLeague.getTeamRankingsStr(1), MainActivity.this.userTeam.strRepWithBowlResults()));
                        return;
                    }
                    if (n == 3) {
                        this.val$potyList.setAdapter((ListAdapter)new TeamRankingsListArrayAdapter((Context)MainActivity.this, MainActivity.this.simLeague.getTeamRankingsStr(2), MainActivity.this.userTeam.strRepWithBowlResults()));
                    }
                }
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    private void saveLeague() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Choose Save File to Overwrite:");
        final String[] saveFileInfos = this.getSaveFileInfos();
        alertDialog$Builder.setAdapter((ListAdapter)new SaveFilesListArrayAdapter((Context)this, saveFileInfos), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                if (saveFileInfos[n].equals("EMPTY")) {
                    MainActivity.this.saveLeagueFile = new File(MainActivity.this.getFilesDir(), "saveFile" + n + ".cfb");
                    MainActivity.this.saveTeamsFile = new File(MainActivity.this.getFilesDir(), "saveFile" + n + "_teams.cfb");
                    MainActivity.this.saveScheduleFile = new File(MainActivity.this.getFilesDir(), "saveFile" + n + "_schedules.cfb");
                    MainActivity.this.saveTeamHistFile = new File(MainActivity.this.getFilesDir(), "saveFile" + n + "_teamhist.cfb");
                    MainActivity.this.simLeague.saveLeague(MainActivity.this.saveLeagueFile, MainActivity.this.saveTeamsFile, MainActivity.this.saveScheduleFile, MainActivity.this.saveTeamHistFile, false);
                    Toast.makeText((Context)MainActivity.this, (CharSequence)"Saved league!", 0).show();
                    dialogInterface.dismiss();
                    return;
                }
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)MainActivity.this);
                alertDialog$Builder.setMessage((CharSequence)("Are you sure you want to overwrite this save file?\n\n" + saveFileInfos[n])).setPositiveButton((CharSequence)"Yes, Overwrite", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        MainActivity.this.saveLeagueFile = new File(MainActivity.this.getFilesDir(), "saveFile" + n + ".cfb");
                        MainActivity.this.saveTeamsFile = new File(MainActivity.this.getFilesDir(), "saveFile" + n + "_teams.cfb");
                        MainActivity.this.saveScheduleFile = new File(MainActivity.this.getFilesDir(), "saveFile" + n + "_schedules.cfb");
                        MainActivity.this.saveTeamHistFile = new File(MainActivity.this.getFilesDir(), "saveFile" + n + "_teamhist.cfb");
                        MainActivity.this.simLeague.saveLeague(MainActivity.this.saveLeagueFile, MainActivity.this.saveTeamsFile, MainActivity.this.saveScheduleFile, MainActivity.this.saveTeamHistFile, false);
                        Toast.makeText((Context)MainActivity.this, (CharSequence)"Saved league!", 0).show();
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        dialogInterface.dismiss();
                    }
                });
                final AlertDialog create = alertDialog$Builder.create();
                create.show();
                ((TextView)create.findViewById(16908299)).setTextSize(2, 14.0f);
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        alertDialog$Builder.create().show();
    }
    
    private void saveLeagueOnFile() {
        this.simLeague.saveLeague(this.saveLeagueFile, this.saveTeamsFile, this.saveScheduleFile, this.saveTeamHistFile, false);
        Toast.makeText((Context)this, (CharSequence)"Saved league!", 0).show();
    }
    
    private void scrollToLatestGame() {
        if (this.currTab == 2 && this.simLeague.currentWeek > 2) {
            this.mainList.setSelection(this.currentTeam.numGames() - 3);
        }
    }
    
    private void setShowTutorial(final boolean showTutorial) {
        this.showTutorial = showTutorial;
    }
    
    private void showTeamLineupDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Set Team Lineup").setView(this.getLayoutInflater().inflate(2130968663, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final String[] array = { "QB (1 starter)", "RB (2 starters)", "WR (3 starters)", "OL (5 starters)", "K (1 starter)", "S (1 starter)", "CB (3 starters)", "DL (4 starters)", "LB (3 starters" };
        final int[] array3;
        final int[] array2 = array3 = new int[9];
        array3[0] = 1;
        array3[array3[1] = 2] = 3;
        array3[array3[3] = 5] = (array3[4] = 1);
        array3[6] = 3;
        array3[7] = 4;
        array3[8] = 3;
        final Spinner spinner = (Spinner)create.findViewById(2131558682);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])array);
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        final TextView textView = (TextView)create.findViewById(2131558683);
        final ArrayList<Player> list = new ArrayList<Player>();
        list.addAll(this.userTeam.teamQBs);
        final ListView listView = (ListView)create.findViewById(2131558684);
        final TeamLineupArrayAdapter adapter2 = new TeamLineupArrayAdapter((Context)this, list, 1);
        listView.setAdapter((ListAdapter)adapter2);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.updateLineupList(n, adapter2, array2, list, textView);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        final Button button = (Button)create.findViewById(2131558685);
        ((Button)create.findViewById(2131558686)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                create.dismiss();
                MainActivity.this.updateCurrTeam();
            }
        });
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final int selectedItemPosition = spinner.getSelectedItemPosition();
                if (adapter2.playersSelected.size() == adapter2.playersRequired) {
                    MainActivity.this.userTeam.setStarters(adapter2.playersSelected, selectedItemPosition);
                    MainActivity.this.updateLineupList(selectedItemPosition, adapter2, array2, list, textView);
                    Toast.makeText((Context)MainActivity.this, (CharSequence)("Saved lineup for " + array[selectedItemPosition] + "!"), 0).show();
                    return;
                }
                Toast.makeText((Context)MainActivity.this, (CharSequence)(adapter2.playersSelected.size() + " players selected.\nNot the correct number of starters (" + adapter2.playersRequired + ")"), 0).show();
            }
        });
    }
    
    private void showTeamStrategyDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Team Strategy").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968668, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final TeamStrategy[] teamStrategiesOff = this.userTeam.getTeamStrategiesOff();
        final TeamStrategy[] teamStrategiesDef = this.userTeam.getTeamStrategiesDef();
        int selection = 0;
        int selection2 = 0;
        final String[] array = new String[teamStrategiesOff.length];
        for (int i = 0; i < teamStrategiesOff.length; ++i) {
            array[i] = teamStrategiesOff[i].getStratName();
            if (array[i].equals(this.userTeam.teamStratOff.getStratName())) {
                selection = i;
            }
        }
        final String[] array2 = new String[teamStrategiesDef.length];
        for (int j = 0; j < teamStrategiesDef.length; ++j) {
            array2[j] = teamStrategiesDef[j].getStratName();
            if (array2[j].equals(this.userTeam.teamStratDef.getStratName())) {
                selection2 = j;
            }
        }
        final TextView textView = (TextView)create.findViewById(2131558700);
        final TextView textView2 = (TextView)create.findViewById(2131558702);
        final Spinner spinner = (Spinner)create.findViewById(2131558699);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])array);
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        spinner.setSelection(selection);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int teamStratOffNum, final long n) {
                textView.setText((CharSequence)teamStrategiesOff[teamStratOffNum].getStratDescription());
                MainActivity.this.userTeam.teamStratOff = teamStrategiesOff[teamStratOffNum];
                MainActivity.this.userTeam.teamStratOffNum = teamStratOffNum;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        final Spinner spinner2 = (Spinner)create.findViewById(2131558701);
        final ArrayAdapter adapter2 = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
        adapter2.setDropDownViewResource(17367049);
        spinner2.setAdapter((SpinnerAdapter)adapter2);
        spinner2.setSelection(selection2);
        spinner2.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int teamStratDefNum, final long n) {
                textView2.setText((CharSequence)teamStrategiesDef[teamStratDefNum].getStratDescription());
                MainActivity.this.userTeam.teamStratDef = teamStrategiesDef[teamStratDefNum];
                MainActivity.this.userTeam.teamStratDefNum = teamStratDefNum;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    private void showTutorialDialog(final String title, final String text) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)title).setPositiveButton((CharSequence)"Ok", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        }).setView(this.getLayoutInflater().inflate(2130968673, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        ((TextView)create.findViewById(2131558714)).setText((CharSequence)text);
        final CheckBox checkBox = (CheckBox)create.findViewById(2131558549);
        checkBox.setChecked(this.showTutorial);
        checkBox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                MainActivity.this.setShowTutorial(b);
            }
        });
    }
    
    private void updateCurrDivision() {
        if (this.wantUpdateConf >= 1) {
            this.teamList = new ArrayList<String>();
            this.dataAdapterTeam.clear();
            for (int i = 0; i < this.currentDivision.divTeams.size(); ++i) {
                this.teamList.add(this.currentDivision.divTeams.get(i).strRep());
                this.dataAdapterTeam.add((Object)this.teamList.get(i));
            }
            this.dataAdapterTeam.notifyDataSetChanged();
            this.examineTeamSpinner.setSelection(0);
            this.currentTeam = this.currentDivision.divTeams.get(0);
            this.updateCurrTeam();
            return;
        }
        ++this.wantUpdateConf;
    }
    
    private void updateCurrTeam() {
        this.teamList = new ArrayList<String>();
        this.dataAdapterTeam.clear();
        for (int i = 0; i < this.currentDivision.divTeams.size(); ++i) {
            this.teamList.add(this.currentDivision.divTeams.get(i).strRep());
            this.dataAdapterTeam.add((Object)this.teamList.get(i));
        }
        this.dataAdapterTeam.notifyDataSetChanged();
        ((TextView)this.findViewById(2131558570)).setText((CharSequence)(this.currentTeam.name + " (" + this.currentTeam.wins + "-" + this.currentTeam.losses + ") " + this.currentTeam.divChampion + " " + this.currentTeam.natChampWL));
        if (this.currTab == 0) {
            this.updateTeamStats();
            return;
        }
        if (this.currTab == 1) {
            this.updatePlayerStats();
            return;
        }
        if (this.currTab == 2) {
            this.updateSchedule();
            return;
        }
        this.updateTradeBlock();
    }
    
    private void updateLineupList(int i, final TeamLineupArrayAdapter teamLineupArrayAdapter, final int[] array, final ArrayList<Player> list, final TextView textView) {
        teamLineupArrayAdapter.playersRequired = array[i];
        teamLineupArrayAdapter.playersSelected.clear();
        teamLineupArrayAdapter.players.clear();
        list.clear();
        switch (i) {
            case 0: {
                textView.setText((CharSequence)"Name [Yr] Ovr/Pot (Str, Acc, Eva)");
                list.addAll(this.userTeam.teamQBs);
                break;
            }
            case 1: {
                textView.setText((CharSequence)"Name [Yr] Ovr/Pot (Pow, Spd, Eva)");
                list.addAll(this.userTeam.teamRBs);
                break;
            }
            case 2: {
                textView.setText((CharSequence)"Name [Yr] Ovr/Pot (Cat, Spd, Eva)");
                list.addAll(this.userTeam.teamWRs);
                break;
            }
            case 3: {
                textView.setText((CharSequence)"Name [Yr] Ovr/Pot (Str, RunBlk, PassBlk)");
                list.addAll(this.userTeam.teamOLs);
                break;
            }
            case 4: {
                textView.setText((CharSequence)"Name [Yr] Ovr/Pot (KStr, KAcc, Clum)");
                list.addAll(this.userTeam.teamKs);
                break;
            }
            case 5: {
                textView.setText((CharSequence)"Name [Yr] Ovr/Pot (Cov, Spd, Tack)");
                list.addAll(this.userTeam.teamSs);
                break;
            }
            case 6: {
                textView.setText((CharSequence)"Name [Yr] Ovr/Pot (Cov, Spd, Tack)");
                list.addAll(this.userTeam.teamCBs);
                break;
            }
            case 7: {
                textView.setText((CharSequence)"Name [Yr] Ovr/Pot (Str, RunDef, PassDef)");
                list.addAll(this.userTeam.teamDLs);
                break;
            }
            case 8: {
                textView.setText((CharSequence)"Name [Yr] Ovr/Pot (Tack, RunDef, PassCov)");
                list.addAll(this.userTeam.teamLBs);
                break;
            }
        }
        for (i = 0; i < teamLineupArrayAdapter.playersRequired; ++i) {
            teamLineupArrayAdapter.playersSelected.add(list.get(i));
        }
        teamLineupArrayAdapter.notifyDataSetChanged();
    }
    
    private void updatePlayerStats() {
        this.mainList.setVisibility(8);
        this.expListPlayerStats.setVisibility(0);
        final List<String> playerStatsExpandListStr = this.currentTeam.getPlayerStatsExpandListStr();
        final ExpandableListAdapterPlayerStats adapter = new ExpandableListAdapterPlayerStats((Activity)this, this, playerStatsExpandListStr, this.currentTeam.getPlayerStatsExpandListMap(playerStatsExpandListStr));
        this.expListPlayerStats.setAdapter((ExpandableListAdapter)adapter);
        this.expListPlayerStats.setOnChildClickListener((ExpandableListView$OnChildClickListener)new ExpandableListView$OnChildClickListener() {
            public boolean onChildClick(final ExpandableListView expandableListView, final View view, final int n, final int n2, final long n3) {
                if (adapter.getGroup(n).equals("BENCH > BENCH")) {
                    MainActivity.this.examinePlayer(adapter.getChild(n, n2));
                }
                else if (adapter.getGroup(n).equals("DRAFT PICKS > DRAFT PICKS")) {
                    MainActivity.this.addTradePick(adapter.getChild(n, n2));
                }
                return false;
            }
        });
        this.expListPlayerStats.setOnItemLongClickListener((AdapterView$OnItemLongClickListener)new AdapterView$OnItemLongClickListener() {
            public boolean onItemLongClick(final AdapterView<?> adapterView, final View view, int packedPositionGroup, final long n) {
                if (ExpandableListView.getPackedPositionType(n) == 1) {
                    packedPositionGroup = ExpandableListView.getPackedPositionGroup(n);
                    final int packedPositionChild = ExpandableListView.getPackedPositionChild(n);
                    if (adapter.getGroup(packedPositionGroup).equals("BENCH > BENCH")) {
                        MainActivity.this.addTradePlayer(adapter.getChild(packedPositionGroup, packedPositionChild));
                    }
                    else if (adapter.getGroup(packedPositionGroup).equals("DRAFT PICKS > DRAFT PICKS")) {
                        MainActivity.this.addTradePick(adapter.getChild(packedPositionGroup, packedPositionChild));
                        return true;
                    }
                    return true;
                }
                return false;
            }
        });
    }
    
    private void updateSchedule() {
        this.mainList.setVisibility(0);
        this.expListPlayerStats.setVisibility(8);
        final Game[] array = new Game[this.currentTeam.gameSchedule.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.currentTeam.gameSchedule.get(i);
        }
        this.mainList.setAdapter((ListAdapter)new GameScheduleListArrayAdapter((Context)this, this, this.currentTeam, array));
    }
    
    private void updateTeamStats() {
        this.mainList.setVisibility(0);
        this.expListPlayerStats.setVisibility(8);
        this.mainList.setAdapter((ListAdapter)new TeamStatsListArrayAdapter((Context)this, this, this.currentTeam.getTeamStatsStrCSV().split("%\n")));
    }
    
    private void updateTradeBlock() {
        this.mainList.setVisibility(0);
        this.expListPlayerStats.setVisibility(8);
        if (this.currentTeam.tradingBlock.size() > 0) {
            final TradePiece[] array = new TradePiece[this.currentTeam.tradingBlock.size() + 1];
            for (int i = 0; i < array.length - 1; ++i) {
                array[i] = this.currentTeam.tradingBlock.get(i);
            }
            array[array.length - 1] = null;
            this.mainList.setAdapter((ListAdapter)new TradingBlockListArrayAdapter((Context)this, this, this.userTeam, this.currentTeam, array));
            return;
        }
        this.mainList.setAdapter((ListAdapter)new PlayerStatsListArrayAdapter((Context)this, new String[] { "Nothing on trade block. Click TRADE on starters or hold down on bench players and draft picks to add them. > " }));
    }
    
    public void addTradePick(final String s) {
        if (this.simLeague.currentWeek <= 7 && this.currentTeam.tradingBlock.size() < 5) {
            DraftPick pick;
            if ((pick = this.currentTeam.findPick(s)) == null) {
                pick = this.currentTeam.draftPicks.get(0);
            }
            if (!this.currentTeam.tradingBlock.contains(pick)) {
                this.currentTeam.tradingBlock.add(pick);
            }
            Toast.makeText((Context)this, (CharSequence)("Added " + pick.getStrRep() + " to trading block."), 0).show();
            return;
        }
        if (this.currentTeam.tradingBlock.size() >= 5) {
            Toast.makeText((Context)this, (CharSequence)"Cannot trade more than 5 players/picks at a time.", 0).show();
            return;
        }
        Toast.makeText((Context)this, (CharSequence)"Trades are not allowed past the deadline.", 0).show();
    }
    
    public void addTradePlayer(final String s) {
        if (this.simLeague.currentWeek > 7) {
            Toast.makeText((Context)this, (CharSequence)"Trades are not allowed past the deadline.", 0).show();
            return;
        }
        Player player;
        if ((player = this.currentTeam.findBenchPlayer(s)) == null) {
            player = this.currentTeam.getQB(0);
        }
        if (player.isInjured()) {
            Toast.makeText((Context)this, (CharSequence)"Injured players cannot be traded.", 0).show();
            return;
        }
        if (this.userTeam != this.currentTeam && Trade.checkUntradable(this.currentTeam, player)) {
            Toast.makeText((Context)this, (CharSequence)(this.currentTeam.abbr + " has no desire to trade " + player.getPosNameYrOvrPot_OneLine() + "."), 0).show();
            return;
        }
        if (this.currentTeam.tradingBlock.size() >= 5) {
            Toast.makeText((Context)this, (CharSequence)"Cannot trade more than 5 players/picks at a time.", 0).show();
            return;
        }
        if (!this.currentTeam.tradingBlock.contains(player)) {
            this.currentTeam.tradingBlock.add(player);
        }
        Toast.makeText((Context)this, (CharSequence)("Added " + player.getPosNameYrOvrPot_OneLine() + " to trading block."), 0).show();
    }
    
    public void advanceOffSeasonDialog() {
        this.simLeague.getPlayersLeaving();
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)(this.userTeam.abbr + " Players Leaving")).setPositiveButton((CharSequence)"Go to Offseason", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                MainActivity.this.advanceOffSeasonLeague();
            }
        }).setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                MainActivity.this.advanceOffSeasonLeague();
            }
        }).setView(this.getLayoutInflater().inflate(2130968665, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final Spinner spinner = (Spinner)create.findViewById(2131558689);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])new String[] { "Players Retiring", "Team Free Agents" });
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        final ListView listView = (ListView)create.findViewById(2131558690);
        final PlayerStatsListArrayAdapter adapter2 = new PlayerStatsListArrayAdapter((Context)this, this.userTeam.getPlayersRetiringList());
        final PlayerStatsListArrayAdapter playerStatsListArrayAdapter = new PlayerStatsListArrayAdapter((Context)this, this.userTeam.getPlayersFAList());
        listView.setAdapter((ListAdapter)adapter2);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (n == 0) {
                    listView.setAdapter((ListAdapter)adapter2);
                    return;
                }
                listView.setAdapter((ListAdapter)playerStatsListArrayAdapter);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void advanceOffSeasonLeague() {
        this.simLeague.advanceSeason();
        this.simLeague.saveLeague(new File(this.getFilesDir(), "offseason_" + this.saveLeagueFileStr), new File(this.getFilesDir(), "offseason_" + this.saveTeamsFileStr), new File(this.getFilesDir(), "offseason_" + this.saveScheduleFileStr), new File(this.getFilesDir(), "offseason_" + this.saveTeamHistFileStr), true);
        final Intent intent = new Intent((Context)this, (Class)OffSeasonActivity.class);
        intent.putExtra("SAVE_FILE_OFFSEASON", "OFFSEASON,offseason_" + this.saveLeagueFileStr + ",offseason_" + this.saveTeamsFileStr + ",offseason_" + this.saveScheduleFileStr + ",offseason_" + this.saveTeamHistFileStr);
        intent.putExtra("SAVE_FILE", "REGULAR," + this.saveLeagueFileStr + "," + this.saveTeamsFileStr + "," + this.saveScheduleFileStr + "," + this.saveTeamHistFileStr);
        this.startActivity(intent);
    }
    
    public int checkAwardPlayer(final String s) {
        final Player benchPlayer = this.currentTeam.findBenchPlayer(s);
        if (benchPlayer != null) {
            if (benchPlayer.hasWonMVP()) {
                return 2;
            }
            if (benchPlayer.hasWonAllPro()) {
                return 1;
            }
        }
        return 0;
    }
    
    public void examinePlayer(final Player player) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        final ArrayList<String> detailAllStatsList = player.getDetailAllStatsList(this.currentTeam.numGames());
        if (player.getInjury() != null) {
            detailAllStatsList.add(0, "[I]Injured: " + player.getInjury().toString());
        }
        detailAllStatsList.add(0, "[B]" + player.getAgeOvrPot_Str());
        alertDialog$Builder.setAdapter((ListAdapter)new PlayerStatsListArrayAdapter((Context)this, detailAllStatsList.toArray(new String[detailAllStatsList.size()])), (DialogInterface$OnClickListener)null).setTitle((CharSequence)(player.getPosition() + " " + player.getName())).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        alertDialog$Builder.create().show();
    }
    
    public void examinePlayer(final String s) {
        Player player;
        if ((player = this.currentTeam.findBenchPlayer(s)) == null) {
            player = this.currentTeam.getQB(0);
        }
        this.examinePlayer(player);
    }
    
    public void examineTeam(final String s) {
        this.wantUpdateConf = 0;
        final Team team = this.simLeague.teamList.get(0);
        final Iterator<Team> iterator = this.simLeague.teamList.iterator();
        while (true) {
            Team team2;
            do {
                team2 = team;
                if (!iterator.hasNext()) {
                    for (int i = 0; i < this.simLeague.divisions.size(); ++i) {
                        final Division currentDivision = this.simLeague.divisions.get(i);
                        if (currentDivision.divName.equals(this.currentTeam.division)) {
                            if (currentDivision == this.currentDivision) {
                                this.wantUpdateConf = 1;
                            }
                            this.currentDivision = currentDivision;
                            this.examineConfSpinner.setSelection(i);
                            break;
                        }
                    }
                    this.teamList = new ArrayList<String>();
                    this.dataAdapterTeam.clear();
                    for (int j = 0; j < this.currentDivision.divTeams.size(); ++j) {
                        this.teamList.add(this.currentDivision.divTeams.get(j).strRep());
                        this.dataAdapterTeam.add((Object)this.teamList.get(j));
                    }
                    this.dataAdapterTeam.notifyDataSetChanged();
                    int k = 0;
                    while (k < this.currentDivision.divTeams.size()) {
                        if (((String)this.dataAdapterTeam.getItem(k)).split(" ")[1].equals(team2.abbr)) {
                            this.examineTeamSpinner.setSelection(k);
                            this.currentTeam = team2;
                            ((TextView)this.findViewById(2131558570)).setText((CharSequence)(this.currentTeam.name + " (" + this.currentTeam.wins + "-" + this.currentTeam.losses + ") " + this.currentTeam.divChampion + " " + this.currentTeam.natChampWL));
                            if (this.currTab == 0) {
                                this.updateTeamStats();
                                break;
                            }
                            if (this.currTab == 1) {
                                this.updatePlayerStats();
                                return;
                            }
                            if (this.currTab == 2) {
                                this.updateSchedule();
                                return;
                            }
                            this.updateTradeBlock();
                        }
                        else {
                            ++k;
                        }
                    }
                    return;
                }
                team2 = iterator.next();
            } while (!team2.name.equals(s));
            this.currentTeam = team2;
            continue;
        }
    }
    
    public void examineTeamRoster(final String s) {
        this.examineTeam(s);
        this.currTab = 1;
        this.updatePlayerStats();
    }
    
    public void findPlayers(final int[] array) {
        final ArrayList<Object> list = new ArrayList<Object>();
        final Iterator<Team> iterator = this.simLeague.teamList.iterator();
        while (iterator.hasNext()) {
            list.addAll(iterator.next().getPosList(array[0]));
        }
        int i = 0;
        while (i < list.size()) {
            final Player player = list.get(i);
            if (player.getRatOvr() < array[1]) {
                list.remove(i);
            }
            else if (player.getRatPot() < array[2]) {
                list.remove(i);
            }
            else if (player.getRatDur() < array[3]) {
                list.remove(i);
            }
            else if (player.getRatFootIQ() < array[4]) {
                list.remove(i);
            }
            else if (player.getAge() < array[5]) {
                list.remove(i);
            }
            else if (player.getAge() > array[6]) {
                list.remove(i);
            }
            else if (player.isInjured() && array[7] == 1) {
                list.remove(i);
            }
            else {
                ++i;
            }
        }
        Collections.sort(list, (Comparator<? super Object>)new PlayerComparatorNoInjury());
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Player Finder Results").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        }).setNegativeButton((CharSequence)"Change Search", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
                MainActivity.this.showPlayerFinderDialog();
            }
        });
        final ExpandableListView view = new ExpandableListView((Context)this);
        alertDialog$Builder.setView((View)view);
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        view.setAdapter((ExpandableListAdapter)new ExpandableListAdapterPlayerFinder((Activity)this, this, (ArrayList<Player>)list, create));
    }
    
    public void goOnline() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Send Team Online").setView(this.getLayoutInflater().inflate(2130968659, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final TeamStrategy[] teamStrategiesOff = this.userTeam.getTeamStrategiesOff();
        final TeamStrategy[] teamStrategiesDef = this.userTeam.getTeamStrategiesDef();
        int selection = 0;
        int selection2 = 0;
        final String[] array = new String[teamStrategiesOff.length];
        for (int i = 0; i < teamStrategiesOff.length; ++i) {
            array[i] = teamStrategiesOff[i].getStratName();
            if (array[i].equals(this.userTeam.teamStratOff.getStratName())) {
                selection = i;
            }
        }
        final String[] array2 = new String[teamStrategiesDef.length];
        for (int j = 0; j < teamStrategiesDef.length; ++j) {
            array2[j] = teamStrategiesDef[j].getStratName();
            if (array2[j].equals(this.userTeam.teamStratDef.getStratName())) {
                selection2 = j;
            }
        }
        final Spinner spinner = (Spinner)create.findViewById(2131558628);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])array);
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        spinner.setSelection(selection);
        final Spinner spinner2 = (Spinner)create.findViewById(2131558630);
        final ArrayAdapter adapter2 = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
        adapter2.setDropDownViewResource(17367049);
        spinner2.setAdapter((SpinnerAdapter)adapter2);
        spinner2.setSelection(selection2);
        final EditText editText = (EditText)create.findViewById(2131558673);
        editText.setText((CharSequence)this.userTeam.name);
        final EditText editText2 = (EditText)create.findViewById(2131558675);
        editText2.setText((CharSequence)this.userTeam.abbr);
        final Button button = (Button)create.findViewById(2131558677);
        final Button button2 = (Button)create.findViewById(2131558678);
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                create.dismiss();
            }
        });
        button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: getfield        com/achijones/profootballcoach/MainActivity$75.val$onlineNameEditText:Landroid/widget/EditText;
                //     4: invokevirtual   android/widget/EditText.getText:()Landroid/text/Editable;
                //     7: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
                //    10: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
                //    13: astore_1       
                //    14: aload_0        
                //    15: getfield        com/achijones/profootballcoach/MainActivity$75.val$onlineAbbrEditText:Landroid/widget/EditText;
                //    18: invokevirtual   android/widget/EditText.getText:()Landroid/text/Editable;
                //    21: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
                //    24: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
                //    27: astore_2       
                //    28: aload_0        
                //    29: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //    32: getfield        com/achijones/profootballcoach/MainActivity.simLeague:LPFCpack/League;
                //    35: aload_1        
                //    36: invokevirtual   PFCpack/League.isNameValidOnline:(Ljava/lang/String;)Z
                //    39: ifeq            227
                //    42: aload_0        
                //    43: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //    46: getfield        com/achijones/profootballcoach/MainActivity.simLeague:LPFCpack/League;
                //    49: aload_2        
                //    50: invokevirtual   PFCpack/League.isAbbrValidOnline:(Ljava/lang/String;)Z
                //    53: ifeq            227
                //    56: aload_0        
                //    57: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //    60: new             Lorg/json/JSONObject;
                //    63: dup            
                //    64: invokespecial   org/json/JSONObject.<init>:()V
                //    67: putfield        com/achijones/profootballcoach/MainActivity.onlineTeamDict:Lorg/json/JSONObject;
                //    70: aload_0        
                //    71: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //    74: getfield        com/achijones/profootballcoach/MainActivity.onlineTeamDict:Lorg/json/JSONObject;
                //    77: ldc             "name"
                //    79: aload_1        
                //    80: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //    83: pop            
                //    84: aload_0        
                //    85: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //    88: getfield        com/achijones/profootballcoach/MainActivity.onlineTeamDict:Lorg/json/JSONObject;
                //    91: ldc             "abbr"
                //    93: aload_2        
                //    94: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //    97: pop            
                //    98: aload_0        
                //    99: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //   102: getfield        com/achijones/profootballcoach/MainActivity.onlineTeamDict:Lorg/json/JSONObject;
                //   105: ldc             "off strat"
                //   107: aload_0        
                //   108: getfield        com/achijones/profootballcoach/MainActivity$75.val$stratOffSelectionSpinner:Landroid/widget/Spinner;
                //   111: invokevirtual   android/widget/Spinner.getSelectedItemPosition:()I
                //   114: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
                //   117: pop            
                //   118: aload_0        
                //   119: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //   122: getfield        com/achijones/profootballcoach/MainActivity.onlineTeamDict:Lorg/json/JSONObject;
                //   125: ldc             "def strat"
                //   127: aload_0        
                //   128: getfield        com/achijones/profootballcoach/MainActivity$75.val$stratDefSelectionSpinner:Landroid/widget/Spinner;
                //   131: invokevirtual   android/widget/Spinner.getSelectedItemPosition:()I
                //   134: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;I)Lorg/json/JSONObject;
                //   137: pop            
                //   138: aload_0        
                //   139: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //   142: getfield        com/achijones/profootballcoach/MainActivity.onlineTeamDict:Lorg/json/JSONObject;
                //   145: ldc             "players"
                //   147: aload_0        
                //   148: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //   151: getfield        com/achijones/profootballcoach/MainActivity.userTeam:LPFCpack/Team;
                //   154: invokevirtual   PFCpack/Team.getPlayersCSV_Online:()Ljava/lang/String;
                //   157: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   160: pop            
                //   161: new             Lorg/json/JSONObject;
                //   164: dup            
                //   165: invokespecial   org/json/JSONObject.<init>:()V
                //   168: astore_2       
                //   169: aload_2        
                //   170: ldc             "name"
                //   172: aload_1        
                //   173: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
                //   176: pop            
                //   177: new             Lcom/achijones/profootballcoach/MainActivity$CheckNameUniqueOnline;
                //   180: dup            
                //   181: aload_0        
                //   182: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //   185: invokespecial   com/achijones/profootballcoach/MainActivity$CheckNameUniqueOnline.<init>:(Lcom/achijones/profootballcoach/MainActivity;)V
                //   188: iconst_1       
                //   189: anewarray       Ljava/lang/String;
                //   192: dup            
                //   193: iconst_0       
                //   194: aload_2        
                //   195: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
                //   198: aastore        
                //   199: invokevirtual   com/achijones/profootballcoach/MainActivity$CheckNameUniqueOnline.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
                //   202: pop            
                //   203: aload_0        
                //   204: getfield        com/achijones/profootballcoach/MainActivity$75.val$dialog:Landroid/app/AlertDialog;
                //   207: invokevirtual   android/app/AlertDialog.dismiss:()V
                //   210: return         
                //   211: astore_2       
                //   212: aload_2        
                //   213: invokevirtual   org/json/JSONException.printStackTrace:()V
                //   216: goto            161
                //   219: astore_1       
                //   220: aload_1        
                //   221: invokevirtual   org/json/JSONException.printStackTrace:()V
                //   224: goto            203
                //   227: aload_0        
                //   228: getfield        com/achijones/profootballcoach/MainActivity$75.this$0:Lcom/achijones/profootballcoach/MainActivity;
                //   231: ldc             "Invalid name or abbr! No special characters are allowed. Name max len = 20, Abbr max len = 5."
                //   233: iconst_1       
                //   234: invokestatic    android/widget/Toast.makeText:(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
                //   237: invokevirtual   android/widget/Toast.show:()V
                //   240: return         
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                    
                //  -----  -----  -----  -----  ------------------------
                //  70     161    211    219    Lorg/json/JSONException;
                //  169    203    219    227    Lorg/json/JSONException;
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0203:
                //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
                //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
                //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
                //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
                //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
                //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
                //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
                // 
                throw new IllegalStateException("An error occurred while decompiling this method.");
            }
        });
    }
    
    public void insertTeamOnline(final int p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_0        
        //     5: invokevirtual   com/achijones/profootballcoach/MainActivity.getFilesDir:()Ljava/io/File;
        //     8: new             Ljava/lang/StringBuilder;
        //    11: dup            
        //    12: invokespecial   java/lang/StringBuilder.<init>:()V
        //    15: ldc_w           "onlineTeam"
        //    18: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    21: iload_1        
        //    22: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    25: ldc_w           ".cfb"
        //    28: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    31: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    34: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    37: astore_3       
        //    38: new             Ljava/io/BufferedWriter;
        //    41: dup            
        //    42: new             Ljava/io/OutputStreamWriter;
        //    45: dup            
        //    46: new             Ljava/io/FileOutputStream;
        //    49: dup            
        //    50: aload_3        
        //    51: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    54: ldc_w           "utf-8"
        //    57: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
        //    60: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //    63: astore          5
        //    65: aconst_null    
        //    66: astore          4
        //    68: aload           5
        //    70: aload_0        
        //    71: getfield        com/achijones/profootballcoach/MainActivity.onlineTeamDict:Lorg/json/JSONObject;
        //    74: ldc_w           "name"
        //    77: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
        //    80: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //    83: invokevirtual   java/io/Writer.write:(Ljava/lang/String;)V
        //    86: aload           5
        //    88: ifnull          100
        //    91: iconst_0       
        //    92: ifeq            160
        //    95: aload           5
        //    97: invokevirtual   java/io/Writer.close:()V
        //   100: aload_2        
        //   101: ldc_w           ""
        //   104: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   107: ifeq            211
        //   110: new             Lcom/achijones/profootballcoach/MainActivity$InsertTeamOnline;
        //   113: dup            
        //   114: aload_0        
        //   115: invokespecial   com/achijones/profootballcoach/MainActivity$InsertTeamOnline.<init>:(Lcom/achijones/profootballcoach/MainActivity;)V
        //   118: iconst_1       
        //   119: anewarray       Ljava/lang/String;
        //   122: dup            
        //   123: iconst_0       
        //   124: aload_0        
        //   125: getfield        com/achijones/profootballcoach/MainActivity.onlineTeamDict:Lorg/json/JSONObject;
        //   128: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   131: aastore        
        //   132: invokevirtual   com/achijones/profootballcoach/MainActivity$InsertTeamOnline.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   135: pop            
        //   136: return         
        //   137: astore_3       
        //   138: new             Ljava/lang/NullPointerException;
        //   141: dup            
        //   142: invokespecial   java/lang/NullPointerException.<init>:()V
        //   145: athrow         
        //   146: astore_3       
        //   147: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   150: aload_3        
        //   151: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   154: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   157: goto            100
        //   160: aload           5
        //   162: invokevirtual   java/io/Writer.close:()V
        //   165: goto            100
        //   168: astore          4
        //   170: aload           4
        //   172: athrow         
        //   173: astore_3       
        //   174: aload           5
        //   176: ifnull          189
        //   179: aload           4
        //   181: ifnull          203
        //   184: aload           5
        //   186: invokevirtual   java/io/Writer.close:()V
        //   189: aload_3        
        //   190: athrow         
        //   191: astore          5
        //   193: aload           4
        //   195: aload           5
        //   197: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   200: goto            189
        //   203: aload           5
        //   205: invokevirtual   java/io/Writer.close:()V
        //   208: goto            189
        //   211: new             Lorg/json/JSONObject;
        //   214: dup            
        //   215: invokespecial   org/json/JSONObject.<init>:()V
        //   218: astore_3       
        //   219: aload_3        
        //   220: ldc_w           "name"
        //   223: aload_2        
        //   224: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   227: pop            
        //   228: new             Lcom/achijones/profootballcoach/MainActivity$RemoveAndInsertTeamOnline;
        //   231: dup            
        //   232: aload_0        
        //   233: invokespecial   com/achijones/profootballcoach/MainActivity$RemoveAndInsertTeamOnline.<init>:(Lcom/achijones/profootballcoach/MainActivity;)V
        //   236: iconst_1       
        //   237: anewarray       Ljava/lang/String;
        //   240: dup            
        //   241: iconst_0       
        //   242: aload_3        
        //   243: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   246: aastore        
        //   247: invokevirtual   com/achijones/profootballcoach/MainActivity$RemoveAndInsertTeamOnline.execute:([Ljava/lang/Object;)Landroid/os/AsyncTask;
        //   250: pop            
        //   251: return         
        //   252: astore_2       
        //   253: aload_2        
        //   254: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   257: return         
        //   258: astore_3       
        //   259: goto            174
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  38     65     146    160    Ljava/lang/Exception;
        //  68     86     168    174    Ljava/lang/Throwable;
        //  68     86     258    262    Any
        //  95     100    137    146    Ljava/lang/Throwable;
        //  95     100    146    160    Ljava/lang/Exception;
        //  138    146    146    160    Ljava/lang/Exception;
        //  160    165    146    160    Ljava/lang/Exception;
        //  170    173    173    174    Any
        //  184    189    191    203    Ljava/lang/Throwable;
        //  184    189    146    160    Ljava/lang/Exception;
        //  189    191    146    160    Ljava/lang/Exception;
        //  193    200    146    160    Ljava/lang/Exception;
        //  203    208    146    160    Ljava/lang/Exception;
        //  211    251    252    258    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 125, Size: 125
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:657)
        //     at java.util.ArrayList.get(ArrayList.java:433)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void insertTeamOnlineDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Choose Team to Replace:");
        final String[] onlineTeams = this.getOnlineTeams();
        alertDialog$Builder.setAdapter((ListAdapter)new SaveFilesListArrayAdapter((Context)this, onlineTeams), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                if (onlineTeams[n].equals("EMPTY")) {
                    MainActivity.this.insertTeamOnline(n, "");
                    return;
                }
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)MainActivity.this);
                alertDialog$Builder.setMessage((CharSequence)("Are you sure you want to use this? It will replace the team currently online:\n\n" + onlineTeams[n])).setPositiveButton((CharSequence)"Yes, Overwrite", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        MainActivity.this.insertTeamOnline(n, onlineTeams[n]);
                    }
                }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        dialogInterface.dismiss();
                    }
                });
                final AlertDialog create = alertDialog$Builder.create();
                create.show();
                ((TextView)create.findViewById(16908299)).setTextSize(2, 14.0f);
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        alertDialog$Builder.create().show();
    }
    
    public void onBackPressed() {
        this.exitMainActivity();
    }
    
    protected void onCreate(Bundle extras) {
        super.onCreate(extras);
        this.setContentView(2130968604);
        this.setSupportActionBar((Toolbar)this.findViewById(2131558542));
        this.mainList = (ListView)this.findViewById(2131558575);
        this.expListPlayerStats = (ExpandableListView)this.findViewById(2131558576);
        extras = this.getIntent().getExtras();
        boolean b = false;
        boolean b2 = false;
        if (extras != null) {
            final String string = extras.getString("SAVE_FILE");
            if (string.equals("NEW_LEAGUE_EASY")) {
                this.simLeague = new League(this.getString(2131165241), this.getString(2131165240), false);
                this.season = 2016;
            }
            else if (string.equals("NEW_LEAGUE_HARD")) {
                this.simLeague = new League(this.getString(2131165241), this.getString(2131165240), true);
                this.season = 2016;
            }
            else {
                final String[] split = string.split(",");
                this.saveLeagueFileStr = split[1];
                this.saveTeamsFileStr = split[2];
                this.saveScheduleFileStr = split[3];
                this.saveTeamHistFileStr = split[4];
                this.saveLeagueFile = new File(this.getFilesDir(), this.saveLeagueFileStr);
                this.saveTeamsFile = new File(this.getFilesDir(), this.saveTeamsFileStr);
                this.saveScheduleFile = new File(this.getFilesDir(), this.saveScheduleFileStr);
                this.saveTeamHistFile = new File(this.getFilesDir(), this.saveTeamHistFileStr);
                if (split[0].equals("LOAD")) {
                    if (this.saveLeagueFile.exists() && this.saveTeamsFile.exists() && this.saveScheduleFile.exists()) {
                        this.simLeague = new League(this.saveLeagueFile, this.saveTeamsFile, this.saveScheduleFile, this.saveTeamHistFile, this.getString(2131165241), this.getString(2131165240));
                        this.userTeam = this.simLeague.userTeam;
                        this.userTeamStr = this.userTeam.name;
                        this.simLeague.updateTeamTalentRatings();
                        this.season = this.simLeague.getYear();
                        this.currentTeam = this.userTeam;
                        b = true;
                    }
                    else {
                        this.simLeague = new League(this.getString(2131165241), this.getString(2131165240), false);
                        this.season = 2016;
                    }
                }
                else if (split[0].equals("ROSTER")) {
                    b2 = true;
                    try {
                        this.simLeague = new League(this.getString(2131165241), this.getString(2131165240), new File(this.getFilesDir(), "roster.txt"));
                        this.season = 2016;
                    }
                    catch (Exception ex) {
                        b2 = false;
                        Toast.makeText((Context)this, (CharSequence)"Error making league from roster file! Generating new league.", 1).show();
                        this.simLeague = new League(this.getString(2131165241), this.getString(2131165240), false);
                        this.season = 2016;
                    }
                }
                else {
                    this.simLeague = new League(this.getString(2131165241), this.getString(2131165240), false);
                    this.season = 2016;
                }
            }
        }
        else {
            this.simLeague = new League(this.getString(2131165241), this.getString(2131165240), false);
            this.season = 2016;
        }
        this.recruitingStage = -1;
        this.wantUpdateConf = 2;
        this.showToasts = true;
        this.showInjuryReport = true;
        this.shownTutRoster = false;
        this.shownTutTeamStats = false;
        this.shownTutSchedule = false;
        this.shownTutTrading = false;
        if (!b) {
            this.userTeam = this.simLeague.teamList.get(0);
            this.simLeague.userTeam = this.userTeam;
            this.userTeam.userControlled = true;
            this.userTeamStr = this.userTeam.name;
            this.currentTeam = this.userTeam;
            this.getSupportActionBar().setTitle((CharSequence)(this.userTeam.name + " " + this.season + " Season"));
            this.showTutorial = true;
            final ProgressDialog show = ProgressDialog.show((Context)this, (CharSequence)"", (CharSequence)"Setting up League. Please wait...", true);
            show.setCancelable(false);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (!b2) {
                        MainActivity.this.simLeague.generatePlayersNewLeague();
                    }
                    MainActivity.this.runOnUiThread((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            show.dismiss();
                            MainActivity.this.currentTeam = MainActivity.this.simLeague.teamList.get(0);
                            MainActivity.this.currentDivision = MainActivity.this.simLeague.divisions.get(0);
                            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)MainActivity.this);
                            alertDialog$Builder.setTitle((CharSequence)"Choose your team:");
                            alertDialog$Builder.setItems((CharSequence[])MainActivity.this.simLeague.getTeamListStr(), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                                public void onClick(final DialogInterface dialogInterface, final int n) {
                                    MainActivity.this.userTeam.userControlled = false;
                                    MainActivity.this.userTeam = MainActivity.this.simLeague.teamList.get(n);
                                    MainActivity.this.simLeague.userTeam = MainActivity.this.userTeam;
                                    MainActivity.this.userTeam.userControlled = true;
                                    MainActivity.this.userTeamStr = MainActivity.this.userTeam.name;
                                    MainActivity.this.currentTeam = MainActivity.this.userTeam;
                                    MainActivity.this.getSupportActionBar().setTitle((CharSequence)(MainActivity.this.userTeam.name + " " + MainActivity.this.season + " Season"));
                                    MainActivity.this.examineTeam(MainActivity.this.currentTeam.name);
                                    MainActivity.this.currTab = 1;
                                    MainActivity.this.updatePlayerStats();
                                    MainActivity.this.saveLeagueOnFile();
                                    MainActivity.this.showTutorialTeamRoster();
                                }
                            });
                            alertDialog$Builder.create().show();
                        }
                    });
                }
            }).start();
        }
        else {
            this.getSupportActionBar().setTitle((CharSequence)(this.userTeam.name + " " + this.season + " Season"));
            this.showTutorial = false;
        }
        this.examineTeamSpinner = (Spinner)this.findViewById(2131558569);
        this.teamList = new ArrayList<String>();
        for (int i = 0; i < 10; ++i) {
            this.teamList.add(this.simLeague.teamList.get(i).strRep());
        }
        (this.dataAdapterTeam = (ArrayAdapter<String>)new ArrayAdapter((Context)this, 17367048, (List)this.teamList)).setDropDownViewResource(17367049);
        this.examineTeamSpinner.setAdapter((SpinnerAdapter)this.dataAdapterTeam);
        this.examineTeamSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.currentTeam = MainActivity.this.simLeague.findTeam(adapterView.getItemAtPosition(n).toString());
                MainActivity.this.updateCurrTeam();
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        this.examineConfSpinner = (Spinner)this.findViewById(2131558568);
        this.confList = new ArrayList<String>();
        for (int j = 0; j < this.simLeague.divisions.size(); ++j) {
            this.confList.add(this.simLeague.divisions.get(j).divName);
        }
        (this.dataAdapterConf = (ArrayAdapter<String>)new ArrayAdapter((Context)this, 17367048, (List)this.confList)).setDropDownViewResource(17367049);
        this.examineConfSpinner.setAdapter((SpinnerAdapter)this.dataAdapterConf);
        this.examineConfSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.currentDivision = MainActivity.this.simLeague.findDivision(adapterView.getItemAtPosition(n).toString());
                MainActivity.this.updateCurrDivision();
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        final Button button = (Button)this.findViewById(2131558571);
        final Button button2 = (Button)this.findViewById(2131558572);
        final Button button3 = (Button)this.findViewById(2131558573);
        final Button button4 = (Button)this.findViewById(2131558574);
        if (this.simLeague.currentWeek >= 8) {
            button4.setEnabled(false);
        }
        final Button button5 = (Button)this.findViewById(2131558577);
        if (this.simLeague.currentWeek < 16) {
            button5.setText((CharSequence)("Play Week " + (this.simLeague.currentWeek + 1)));
        }
        else if (this.simLeague.currentWeek == 16) {
            button5.setText((CharSequence)"Playoffs Round 1");
        }
        else if (this.simLeague.currentWeek == 17) {
            button5.setText((CharSequence)"Playoffs Round 2");
        }
        else if (this.simLeague.currentWeek == 18) {
            button5.setText((CharSequence)"Play Conf Championships");
        }
        else if (this.simLeague.currentWeek == 19) {
            button5.setText((CharSequence)"Play Champ Bowl");
        }
        else {
            button5.setText((CharSequence)"Advance to Offseason");
        }
        button5.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (MainActivity.this.simLeague.currentWeek == 20) {
                    MainActivity.this.advanceOffSeasonDialog();
                    return;
                }
                button5.setEnabled(false);
                final int size = MainActivity.this.userTeam.gameWLSchedule.size();
                MainActivity.this.simLeague.playWeek();
                if (MainActivity.this.simLeague.currentWeek == 7) {
                    final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)MainActivity.this);
                    alertDialog$Builder.setMessage((CharSequence)"This week is the last week for trades! Once the next week is played, trades will not be allowed.").setPositiveButton((CharSequence)"Ok", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog$Builder.create().show();
                }
                else if (MainActivity.this.simLeague.currentWeek >= 8) {
                    if (MainActivity.this.currTab == 3) {
                        MainActivity.this.currTab = 2;
                        MainActivity.this.updateSchedule();
                    }
                    button4.setEnabled(false);
                }
                if (MainActivity.this.simLeague.currentWeek == 16) {
                    MainActivity.this.mvpCeremony();
                    if (MainActivity.this.simLeague.playoffsNA.contains(MainActivity.this.userTeam)) {
                        if (MainActivity.this.simLeague.playoffsNA.get(0) == MainActivity.this.userTeam || MainActivity.this.simLeague.playoffsNA.get(1) == MainActivity.this.userTeam) {
                            if (MainActivity.this.showToasts) {
                                Toast.makeText((Context)MainActivity.this, (CharSequence)(MainActivity.this.userTeam.abbr + " made the playoffs!\nThey receive a BYE week for Round 1."), 0).show();
                            }
                        }
                        else {
                            Team team;
                            if (MainActivity.this.userTeam.gameSchedule.get(16).homeTeam == MainActivity.this.userTeam) {
                                team = MainActivity.this.userTeam.gameSchedule.get(16).awayTeam;
                            }
                            else {
                                team = MainActivity.this.userTeam.gameSchedule.get(16).homeTeam;
                            }
                            if (MainActivity.this.showToasts) {
                                Toast.makeText((Context)MainActivity.this, (CharSequence)(MainActivity.this.userTeam.abbr + " made the playoffs!\nThey play " + team.abbr + " in Round 1."), 0).show();
                            }
                        }
                    }
                    else if (MainActivity.this.simLeague.playoffsAM.contains(MainActivity.this.userTeam)) {
                        if (MainActivity.this.simLeague.playoffsAM.get(0) == MainActivity.this.userTeam || MainActivity.this.simLeague.playoffsAM.get(1) == MainActivity.this.userTeam) {
                            if (MainActivity.this.showToasts) {
                                Toast.makeText((Context)MainActivity.this, (CharSequence)(MainActivity.this.userTeam.abbr + " made the playoffs!\nThey receive a BYE week for Round 1."), 0).show();
                            }
                        }
                        else {
                            Team team2;
                            if (MainActivity.this.userTeam.gameSchedule.get(16).homeTeam == MainActivity.this.userTeam) {
                                team2 = MainActivity.this.userTeam.gameSchedule.get(16).awayTeam;
                            }
                            else {
                                team2 = MainActivity.this.userTeam.gameSchedule.get(16).homeTeam;
                            }
                            if (MainActivity.this.showToasts) {
                                Toast.makeText((Context)MainActivity.this, (CharSequence)(MainActivity.this.userTeam.abbr + " made the playoffs!\nThey play " + team2.abbr + " in Round 1."), 0).show();
                            }
                        }
                    }
                    else if (MainActivity.this.showToasts) {
                        Toast.makeText((Context)MainActivity.this, (CharSequence)(MainActivity.this.userTeam.abbr + " did not make the playoffs."), 0).show();
                    }
                }
                if (MainActivity.this.userTeam.gameWLSchedule.size() > size) {
                    if (MainActivity.this.showToasts) {
                        Toast.makeText((Context)MainActivity.this, (CharSequence)MainActivity.this.userTeam.weekSummaryStr(), 0).show();
                    }
                    if (MainActivity.this.showInjuryReport) {
                        MainActivity.this.showInjuryReportDialog();
                    }
                }
                MainActivity.this.updateCurrTeam();
                MainActivity.this.scrollToLatestGame();
                if (MainActivity.this.simLeague.currentWeek < 16) {
                    button5.setText((CharSequence)("Play Week " + (MainActivity.this.simLeague.currentWeek + 1)));
                }
                else if (MainActivity.this.simLeague.currentWeek == 16) {
                    button5.setText((CharSequence)"Playoffs Round 1");
                    MainActivity.this.examineTeam(MainActivity.this.currentTeam.name);
                }
                else if (MainActivity.this.simLeague.currentWeek == 17) {
                    button5.setText((CharSequence)"Playoffs Round 2");
                    MainActivity.this.examineTeam(MainActivity.this.currentTeam.name);
                }
                else if (MainActivity.this.simLeague.currentWeek == 18) {
                    button5.setText((CharSequence)"Play Conf Championships");
                }
                else if (MainActivity.this.simLeague.currentWeek == 19) {
                    button5.setText((CharSequence)"Play Champions Bowl");
                }
                else {
                    button5.setText((CharSequence)"Advance to Offseason");
                    MainActivity.this.simLeague.curePlayers();
                    MainActivity.this.simLeague.checkLeagueRecords();
                    MainActivity.this.showSeasonSummaryDialog();
                    MainActivity.this.saveLeagueOnFile();
                }
                button5.setEnabled(true);
            }
        });
        button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                MainActivity.this.currTab = 0;
                MainActivity.this.updateTeamStats();
                MainActivity.this.showTutorialTeamStats();
            }
        });
        button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                MainActivity.this.currTab = 1;
                MainActivity.this.updatePlayerStats();
                MainActivity.this.showTutorialTeamRoster();
            }
        });
        button3.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                MainActivity.this.currTab = 2;
                MainActivity.this.updateSchedule();
                MainActivity.this.showTutorialTeamSchedule();
            }
        });
        button4.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                MainActivity.this.currTab = 3;
                MainActivity.this.updateTradeBlock();
                MainActivity.this.showTutorialTrading();
            }
        });
        if (b) {
            this.simLeague.setTeamRanks();
            this.examineTeam(this.userTeam.name);
            this.showToasts = this.userTeam.showPopups;
        }
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(2131623936, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        final int itemId = menuItem.getItemId();
        if (itemId == 2131558723) {
            this.showTeamStrategyDialog();
        }
        else if (itemId == 2131558720) {
            if (this.simLeague.currentWeek < 16) {
                final String top5MVPStr = this.simLeague.getTop5MVPStr();
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
                alertDialog$Builder.setMessage((CharSequence)top5MVPStr).setTitle((CharSequence)"MVP Watch").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                    }
                });
                final AlertDialog create = alertDialog$Builder.create();
                create.show();
                ((TextView)create.findViewById(16908299)).setTextSize(2, 14.0f);
            }
            else {
                this.mvpCeremony();
            }
        }
        else if (itemId == 2131558719) {
            this.showTeamRankingsDialog();
        }
        else if (itemId == 2131558718) {
            this.showLeagueHistoryDialog();
        }
        else if (itemId == 2131558725) {
            this.saveLeagueOnFile();
        }
        else if (itemId == 2131558726) {
            this.exitMainActivity();
        }
        else if (itemId == 2131558717) {
            this.changeTeamNameDialog();
        }
        else if (itemId == 2131558722) {
            this.showTeamLineupDialog();
        }
        else if (itemId == 2131558721) {
            this.showPlayerFinderDialog();
        }
        else if (itemId == 2131558724) {
            if (this.simLeague.canGoOnline) {
                this.goOnline();
            }
            else {
                final AlertDialog$Builder alertDialog$Builder2 = new AlertDialog$Builder((Context)this);
                alertDialog$Builder2.setMessage((CharSequence)"Teams generated from roster files or made before the online update cannot be taken online.").setTitle((CharSequence)"Cannot go online").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                    }
                });
                final AlertDialog create2 = alertDialog$Builder2.create();
                create2.show();
                ((TextView)create2.findViewById(16908299)).setTextSize(2, 14.0f);
            }
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public void removeTradePiece(final TradePiece tradePiece) {
        if (this.currentTeam.tradingBlock.contains(tradePiece)) {
            this.currentTeam.tradingBlock.remove(tradePiece);
        }
        this.updateTradeBlock();
    }
    
    public void setShowInjuryReport(final boolean showInjuryReport) {
        this.showInjuryReport = showInjuryReport;
    }
    
    public void showCurrTeamProfile() {
        final Team currentTeam = this.currentTeam;
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)(this.currentTeam.abbr + " Team Profile")).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968665, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final Spinner spinner = (Spinner)create.findViewById(2131558689);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])new String[] { "Team History", "Team Salary Info" });
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            final /* synthetic */ ListView val$teamHistoryList = (ListView)create.findViewById(2131558690);
            
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (n == 0) {
                    this.val$teamHistoryList.setAdapter((ListAdapter)new TeamHistoryListArrayAdapter((Context)MainActivity.this, currentTeam.getTeamHistoryList()));
                }
                else if (n == 1) {
                    this.val$teamHistoryList.setAdapter((ListAdapter)new TeamHistoryListArrayAdapter((Context)MainActivity.this, currentTeam.getCapRoomList()));
                }
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void showGameDialog(final Game game) {
        if (game.hasPlayed) {
            final String[] gameSummaryStr = game.getGameSummaryStr();
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
            alertDialog$Builder.setTitle((CharSequence)(game.awayTeam.abbr + " @ " + game.homeTeam.abbr + ": " + game.gameName)).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                }
            }).setView(this.getLayoutInflater().inflate(2130968630, (ViewGroup)null));
            final AlertDialog create = alertDialog$Builder.create();
            create.show();
            final TextView textView = (TextView)create.findViewById(2131558610);
            final TextView textView2 = (TextView)create.findViewById(2131558612);
            final TextView textView3 = (TextView)create.findViewById(2131558613);
            final TextView textView4 = (TextView)create.findViewById(2131558615);
            textView.setText((CharSequence)(game.awayScore + ""));
            textView2.setText((CharSequence)(game.homeScore + ""));
            textView3.setText((CharSequence)game.awayTeam.getStrAbbrWL_2Lines());
            textView4.setText((CharSequence)game.homeTeam.getStrAbbrWL_2Lines());
            final TextView textView5 = (TextView)create.findViewById(2131558614);
            if (game.numOT > 0) {
                textView5.setText((CharSequence)(game.numOT + "OT"));
            }
            else {
                textView5.setText((CharSequence)"@");
            }
            ((TextView)create.findViewById(2131558616)).setText((CharSequence)gameSummaryStr[0]);
            ((TextView)create.findViewById(2131558617)).setText((CharSequence)gameSummaryStr[1]);
            ((TextView)create.findViewById(2131558618)).setText((CharSequence)gameSummaryStr[2]);
            ((TextView)create.findViewById(2131558619)).setText((CharSequence)(gameSummaryStr[3] + "\n\n"));
            return;
        }
        final String[] gameScoutStr = game.getGameScoutStr();
        final AlertDialog$Builder alertDialog$Builder2 = new AlertDialog$Builder((Context)this);
        alertDialog$Builder2.setTitle((CharSequence)(game.awayTeam.abbr + " @ " + game.homeTeam.abbr + ": " + game.gameName)).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968632, (ViewGroup)null));
        final AlertDialog create2 = alertDialog$Builder2.create();
        create2.show();
        ((TextView)create2.findViewById(2131558624)).setText((CharSequence)gameScoutStr[0]);
        ((TextView)create2.findViewById(2131558625)).setText((CharSequence)gameScoutStr[1]);
        ((TextView)create2.findViewById(2131558626)).setText((CharSequence)gameScoutStr[2]);
        ((TextView)create2.findViewById(2131558631)).setText((CharSequence)gameScoutStr[3]);
        if (game.awayTeam == this.userTeam || game.homeTeam == this.userTeam) {
            final TextView textView6 = (TextView)create2.findViewById(2131558627);
            final TextView textView7 = (TextView)create2.findViewById(2131558629);
            textView6.setText((CharSequence)(this.userTeam.abbr + " Off Strategy:"));
            textView7.setText((CharSequence)(this.userTeam.abbr + " Def Strategy:"));
            final TeamStrategy[] teamStrategiesOff = this.userTeam.getTeamStrategiesOff();
            final TeamStrategy[] teamStrategiesDef = this.userTeam.getTeamStrategiesDef();
            int selection = 0;
            int selection2 = 0;
            final String[] array = new String[teamStrategiesOff.length];
            for (int i = 0; i < teamStrategiesOff.length; ++i) {
                array[i] = teamStrategiesOff[i].getStratName();
                if (array[i].equals(this.userTeam.teamStratOff.getStratName())) {
                    selection = i;
                }
            }
            final String[] array2 = new String[teamStrategiesDef.length];
            for (int j = 0; j < teamStrategiesDef.length; ++j) {
                array2[j] = teamStrategiesDef[j].getStratName();
                if (array2[j].equals(this.userTeam.teamStratDef.getStratName())) {
                    selection2 = j;
                }
            }
            final Spinner spinner = (Spinner)create2.findViewById(2131558628);
            final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])array);
            adapter.setDropDownViewResource(17367049);
            spinner.setAdapter((SpinnerAdapter)adapter);
            spinner.setSelection(selection);
            spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int teamStratOffNum, final long n) {
                    MainActivity.this.userTeam.teamStratOff = teamStrategiesOff[teamStratOffNum];
                    MainActivity.this.userTeam.teamStratOffNum = teamStratOffNum;
                }
                
                public void onNothingSelected(final AdapterView<?> adapterView) {
                }
            });
            final Spinner spinner2 = (Spinner)create2.findViewById(2131558630);
            final ArrayAdapter adapter2 = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
            adapter2.setDropDownViewResource(17367049);
            spinner2.setAdapter((SpinnerAdapter)adapter2);
            spinner2.setSelection(selection2);
            spinner2.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                public void onItemSelected(final AdapterView<?> adapterView, final View view, final int teamStratDefNum, final long n) {
                    MainActivity.this.userTeam.teamStratDef = teamStrategiesDef[teamStratDefNum];
                    MainActivity.this.userTeam.teamStratDefNum = teamStratDefNum;
                }
                
                public void onNothingSelected(final AdapterView<?> adapterView) {
                }
            });
            return;
        }
        final Spinner spinner3 = (Spinner)create2.findViewById(2131558628);
        final Spinner spinner4 = (Spinner)create2.findViewById(2131558630);
        spinner3.setVisibility(8);
        spinner4.setVisibility(8);
        final TextView textView8 = (TextView)create2.findViewById(2131558627);
        final TextView textView9 = (TextView)create2.findViewById(2131558629);
        textView8.setVisibility(8);
        textView9.setVisibility(8);
    }
    
    public void showInjuryReportDialog() {
        final String[] injuryReport = this.userTeam.getInjuryReport();
        if (injuryReport != null) {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
            alertDialog$Builder.setTitle((CharSequence)"Injury Report").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                }
            }).setNegativeButton((CharSequence)"Set Lineup", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    MainActivity.this.showTeamLineupDialog();
                }
            }).setView(this.getLayoutInflater().inflate(2130968636, (ViewGroup)null));
            final AlertDialog create = alertDialog$Builder.create();
            create.show();
            ((ListView)create.findViewById(2131558640)).setAdapter((ListAdapter)new PlayerStatsListArrayAdapter((Context)this, injuryReport));
            final CheckBox checkBox = (CheckBox)create.findViewById(2131558641);
            checkBox.setChecked(true);
            checkBox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener() {
                public void onCheckedChanged(final CompoundButton compoundButton, final boolean showInjuryReport) {
                    MainActivity.this.setShowInjuryReport(showInjuryReport);
                }
            });
        }
    }
    
    public void showLeagueHistoryDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"League History / Records").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968665, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final Spinner spinner = (Spinner)create.findViewById(2131558689);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])new String[] { "League History", "League Records", "Hall of Fame" });
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        final ListView listView = (ListView)create.findViewById(2131558690);
        final String[] array = new String[this.simLeague.hallOfFame.size()];
        for (int i = 0; i < this.simLeague.hallOfFame.size(); ++i) {
            array[i] = this.simLeague.hallOfFame.get(i);
        }
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (n == 0) {
                    listView.setAdapter((ListAdapter)new LeagueHistoryListArrayAdapter((Context)MainActivity.this, MainActivity.this.simLeague.getLeagueHistoryStr().split("%"), MainActivity.this.userTeam.abbr));
                    return;
                }
                if (n == 1) {
                    listView.setAdapter((ListAdapter)new LeagueRecordsListArrayAdapter((Context)MainActivity.this, MainActivity.this.simLeague.getLeagueRecordsStr().split("\n"), MainActivity.this.userTeam.abbr));
                    return;
                }
                listView.setAdapter((ListAdapter)new HallOfFameListArrayAdapter((Context)MainActivity.this, array));
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void showNewsStoriesDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"News Stories").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968665, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final ArrayList<String> list = new ArrayList<String>();
        final String[] array = new String[this.simLeague.currentWeek + 1];
        for (int i = 0; i < array.length; ++i) {
            if (i == 13) {
                array[i] = "Conf Champ Week";
            }
            else if (i == 14) {
                array[i] = "Bowl Game Week";
            }
            else if (i == 15) {
                array[i] = "National Champ";
            }
            else {
                array[i] = "Week " + i;
            }
        }
        final Spinner spinner = (Spinner)create.findViewById(2131558689);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])array);
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        spinner.setSelection(this.simLeague.currentWeek);
        final ListView listView = (ListView)create.findViewById(2131558690);
        final NewsStoriesListArrayAdapter adapter2 = new NewsStoriesListArrayAdapter((Context)this, list);
        listView.setAdapter((ListAdapter)adapter2);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, int n, final long n2) {
                final ArrayList<String> list = MainActivity.this.simLeague.newsStories.get(n);
                n = 0;
                if (list.size() == 0) {
                    n = 1;
                    list.add("No news stories.>I guess this week was a bit boring, huh?");
                }
                adapter2.clear();
                adapter2.addAll((Collection)list);
                adapter2.notifyDataSetChanged();
                if (n != 0) {
                    list.remove(0);
                }
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void showPlayerFinderDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Player Finder").setPositiveButton((CharSequence)"Find", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                MainActivity.this.findPlayers(MainActivity.this.findFilter);
            }
        }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.dismiss();
            }
        }).setView(this.getLayoutInflater().inflate(2130968655, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final CheckBox checkBox = (CheckBox)create.findViewById(2131558669);
        checkBox.setChecked(this.findFilter[7] == 1);
        checkBox.setOnCheckedChangeListener((CompoundButton$OnCheckedChangeListener)new CompoundButton$OnCheckedChangeListener() {
            public void onCheckedChanged(final CompoundButton compoundButton, final boolean b) {
                final int[] findFilter = MainActivity.this.findFilter;
                int n;
                if (b) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                findFilter[7] = n;
            }
        });
        final String[] array = new String[11];
        for (int i = 0; i < array.length; ++i) {
            array[i] = i * 5 + 50 + "";
        }
        final String[] array2 = { "F", "D", "C", "B", "A" };
        final String[] array3 = new String[21];
        for (int j = 0; j < array3.length; ++j) {
            array3[j] = j + 20 + "";
        }
        final Spinner spinner = (Spinner)create.findViewById(2131558671);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])new String[] { "All", "QB", "RB", "WR", "OL", "K", "S", "CB", "DL", "LB" });
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        spinner.setSelection(this.findFilter[0]);
        final Spinner spinner2 = (Spinner)create.findViewById(2131558672);
        final ArrayAdapter adapter2 = new ArrayAdapter((Context)this, 17367048, (Object[])array);
        adapter2.setDropDownViewResource(17367049);
        spinner2.setAdapter((SpinnerAdapter)adapter2);
        spinner2.setSelection((this.findFilter[1] - 50) / 5);
        final Spinner spinner3 = (Spinner)create.findViewById(2131558603);
        final ArrayAdapter adapter3 = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
        adapter3.setDropDownViewResource(17367049);
        spinner3.setAdapter((SpinnerAdapter)adapter3);
        spinner3.setSelection((this.findFilter[2] - 50) / 10);
        final Spinner spinner4 = (Spinner)create.findViewById(2131558604);
        final ArrayAdapter adapter4 = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
        adapter4.setDropDownViewResource(17367049);
        spinner4.setAdapter((SpinnerAdapter)adapter4);
        spinner4.setSelection((this.findFilter[3] - 50) / 10);
        final Spinner spinner5 = (Spinner)create.findViewById(2131558605);
        final ArrayAdapter adapter5 = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
        adapter5.setDropDownViewResource(17367049);
        spinner5.setAdapter((SpinnerAdapter)adapter5);
        spinner5.setSelection((this.findFilter[4] - 50) / 10);
        final Spinner spinner6 = (Spinner)create.findViewById(2131558606);
        final ArrayAdapter adapter6 = new ArrayAdapter((Context)this, 17367048, (Object[])array3);
        adapter6.setDropDownViewResource(17367049);
        spinner6.setAdapter((SpinnerAdapter)adapter6);
        spinner6.setSelection(this.findFilter[5] - 20);
        final Spinner spinner7 = (Spinner)create.findViewById(2131558607);
        final ArrayAdapter adapter7 = new ArrayAdapter((Context)this, 17367048, (Object[])array3);
        adapter7.setDropDownViewResource(17367049);
        spinner7.setAdapter((SpinnerAdapter)adapter7);
        spinner7.setSelection(this.findFilter[6] - 20);
        ((Button)create.findViewById(2131558670)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                boolean checked = true;
                MainActivity.this.findFilter[0] = 0;
                MainActivity.this.findFilter[1] = 50;
                MainActivity.this.findFilter[2] = 50;
                MainActivity.this.findFilter[3] = 50;
                MainActivity.this.findFilter[4] = 50;
                MainActivity.this.findFilter[5] = 20;
                MainActivity.this.findFilter[6] = 40;
                MainActivity.this.findFilter[7] = 0;
                spinner.setSelection(MainActivity.this.findFilter[0]);
                spinner2.setSelection((MainActivity.this.findFilter[1] - 50) / 5);
                spinner3.setSelection((MainActivity.this.findFilter[2] - 50) / 10);
                spinner4.setSelection((MainActivity.this.findFilter[3] - 50) / 10);
                spinner5.setSelection((MainActivity.this.findFilter[4] - 50) / 10);
                spinner6.setSelection(MainActivity.this.findFilter[5] - 20);
                spinner7.setSelection(MainActivity.this.findFilter[6] - 20);
                final CheckBox val$filterInjuredPlayers = checkBox;
                if (MainActivity.this.findFilter[7] != 1) {
                    checked = false;
                }
                val$filterInjuredPlayers.setChecked(checked);
            }
        });
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.findFilter[0] = n;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner2.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.findFilter[1] = n * 5 + 50;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner3.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.findFilter[2] = n * 10 + 50;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner4.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.findFilter[3] = n * 10 + 50;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner5.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.findFilter[4] = n * 10 + 50;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner6.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.findFilter[5] = n + 20;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        spinner7.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                MainActivity.this.findFilter[6] = n + 20;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void showSeasonSummaryDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setMessage((CharSequence)this.simLeague.seasonSummaryStr()).setTitle((CharSequence)(this.userTeam.teamHistory.size() + 2016 + " Season Summary")).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        ((TextView)create.findViewById(16908299)).setTextSize(2, 14.0f);
    }
    
    public void showTeamHistory(final Team team) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)(team.abbr + " Team History")).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968665, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final Spinner spinner = (Spinner)create.findViewById(2131558689);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])new String[] { "Team History" });
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            final /* synthetic */ ListView val$teamHistoryList = (ListView)create.findViewById(2131558690);
            
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (n == 0) {
                    this.val$teamHistoryList.setAdapter((ListAdapter)new TeamHistoryListArrayAdapter((Context)MainActivity.this, team.getTeamHistoryList()));
                }
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void showTeamHistoryDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Team History").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968665, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final Spinner spinner = (Spinner)create.findViewById(2131558689);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])new String[] { "Team History", "Team Records" });
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            final /* synthetic */ ListView val$teamHistoryList = (ListView)create.findViewById(2131558690);
            
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                if (n == 0) {
                    this.val$teamHistoryList.setAdapter((ListAdapter)new TeamHistoryListArrayAdapter((Context)MainActivity.this, MainActivity.this.userTeam.getTeamHistoryList()));
                }
                else if (n == 1) {
                    this.val$teamHistoryList.setAdapter((ListAdapter)new LeagueRecordsListArrayAdapter((Context)MainActivity.this, MainActivity.this.simLeague.userTeamRecords.getRecordsStr().split("\n"), "---"));
                }
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void showTeamRankingsDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Team Rankings").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968665, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        final ArrayList<String> list = new ArrayList<String>();
        final Spinner spinner = (Spinner)create.findViewById(2131558689);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])new String[] { "Power Ranking", "Playoff Picture", "Division Standings", "Strength of Schedule", "Points Per Game", "Opp Points Per Game", "Yards Per Game", "Opp Yards Per Game", "Pass Yards Per Game", "Rush Yards Per Game", "Opp Pass YPG", "Opp Rush YPG", "TO Differential", "Offensive Talent", "Defensive Talent", "Salary Cap Room" });
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        final ListView listView = (ListView)create.findViewById(2131558690);
        final TeamRankingsListArrayAdapter adapter2 = new TeamRankingsListArrayAdapter((Context)this, list, this.userTeam.strRepWithBowlResults());
        listView.setAdapter((ListAdapter)adapter2);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                final ArrayList<String> teamRankingsStr = MainActivity.this.simLeague.getTeamRankingsStr(n);
                adapter2.clear();
                adapter2.addAll((Collection)teamRankingsStr);
                adapter2.notifyDataSetChanged();
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void showTradeLogDialog() {
        final StringBuilder sb = new StringBuilder();
        for (final Trade trade : this.simLeague.tradeLog) {
            sb.append(trade.getATeam().getStrAbbrWL() + " traded:\n");
            final Iterator<TradePiece> iterator2 = trade.getAOffer().iterator();
            while (iterator2.hasNext()) {
                sb.append(iterator2.next().getTradePieceInfo() + "\n");
            }
            sb.append("\n" + trade.getBTeam().getStrAbbrWL() + " traded:\n");
            final Iterator<TradePiece> iterator3 = trade.getBOffer().iterator();
            while (iterator3.hasNext()) {
                sb.append(iterator3.next().getTradePieceInfo() + "\n");
            }
            sb.append("\n\n\n");
        }
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setMessage((CharSequence)sb.toString()).setTitle((CharSequence)"Trade Log").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        ((TextView)create.findViewById(16908299)).setTextSize(2, 14.0f);
    }
    
    public void showTutorialTeamRoster() {
        if (this.showTutorial && !this.shownTutRoster) {
            this.showTutorialDialog("Team Roster", this.getResources().getString(2131165251));
            this.shownTutRoster = true;
        }
    }
    
    public void showTutorialTeamSchedule() {
        if (this.showTutorial && !this.shownTutSchedule) {
            this.showTutorialDialog("Game Schedule", this.getResources().getString(2131165252));
            this.shownTutSchedule = true;
        }
    }
    
    public void showTutorialTeamStats() {
        if (this.showTutorial && !this.shownTutTeamStats) {
            this.showTutorialDialog("Team Stats", this.getResources().getString(2131165253));
            this.shownTutTeamStats = true;
        }
    }
    
    public void showTutorialTrading() {
        if (this.showTutorial && !this.shownTutTrading) {
            this.showTutorialDialog("Game Schedule", this.getResources().getString(2131165254));
            this.shownTutTrading = true;
        }
    }
    
    public void tradeOffersDialog() {
        if (this.simLeague.currentWeek >= 8) {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
            alertDialog$Builder.setMessage((CharSequence)"You past the trade deadline! Trades are not allowed past week 8.").setPositiveButton((CharSequence)"Ok", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    dialogInterface.dismiss();
                }
            });
            alertDialog$Builder.create().show();
            return;
        }
        final AlertDialog$Builder alertDialog$Builder2 = new AlertDialog$Builder((Context)this);
        alertDialog$Builder2.setTitle((CharSequence)"Trade Offers").setView(this.getLayoutInflater().inflate(2130968672, (ViewGroup)null)).setPositiveButton((CharSequence)"Accept Trade", (DialogInterface$OnClickListener)null).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                MainActivity.this.currentTrade = null;
                dialogInterface.dismiss();
            }
        });
        final AlertDialog create = alertDialog$Builder2.create();
        create.setOnShowListener((DialogInterface$OnShowListener)new DialogInterface$OnShowListener() {
            public void onShow(final DialogInterface dialogInterface) {
                create.getButton(-1).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)MainActivity.this);
                        alertDialog$Builder.setMessage((CharSequence)"Are you sure you want to accept this trade? This cannot be undone.").setPositiveButton((CharSequence)"Yes, Accept", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                            public void onClick(final DialogInterface dialogInterface, final int n) {
                                if (MainActivity.this.currentTrade != null) {
                                    MainActivity.this.currentTrade.completeTrade();
                                    MainActivity.this.simLeague.tradeLog.add(MainActivity.this.currentTrade);
                                }
                                MainActivity.this.currentTrade = null;
                                MainActivity.this.examineTeam(MainActivity.this.userTeam.name);
                                MainActivity.this.currTab = 1;
                                MainActivity.this.updatePlayerStats();
                                MainActivity.this.saveLeagueOnFile();
                                create.dismiss();
                            }
                        }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                            public void onClick(final DialogInterface dialogInterface, final int n) {
                            }
                        });
                        alertDialog$Builder.create().show();
                    }
                });
            }
        });
        create.show();
        final ArrayList<Trade> list = new ArrayList<Trade>();
        final boolean b = true;
        int n2;
        String[] array2;
        if (this.currentTeam.userControlled) {
            final ArrayList<String> list2 = new ArrayList<String>();
            for (final Team team : this.simLeague.teamList) {
                if (!team.userControlled) {
                    final Trade tradeOfferFromAI = Trade.getTradeOfferFromAI(team, this.userTeam, this.userTeam.tradingBlock);
                    if (tradeOfferFromAI.getAOffer().isEmpty()) {
                        continue;
                    }
                    list2.add(team.abbr);
                    list.add(tradeOfferFromAI);
                }
            }
            if (list2.size() > 0) {
                final String[] array = new String[list2.size()];
                int n = 0;
                while (true) {
                    n2 = (b ? 1 : 0);
                    array2 = array;
                    if (n >= array.length) {
                        break;
                    }
                    array[n] = list2.get(n) + " [" + (n + 1) + "/" + list2.size() + "]";
                    ++n;
                }
            }
            else {
                n2 = 0;
                array2 = new String[] { "ERROR" };
            }
        }
        else {
            array2 = new String[] { this.currentTeam.abbr + " [1/1]" };
            n2 = (b ? 1 : 0);
        }
        if (n2 == 0) {
            Toast.makeText((Context)this, (CharSequence)"No trades were found!", 0).show();
            create.dismiss();
            return;
        }
        final Spinner spinner = (Spinner)create.findViewById(2131558711);
        final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
        adapter.setDropDownViewResource(17367049);
        spinner.setAdapter((SpinnerAdapter)adapter);
        final ListView listView = (ListView)create.findViewById(2131558713);
        listView.setAdapter((ListAdapter)null);
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, int i, final long n) {
                Trade tradeOfferFromUserTeam;
                if (MainActivity.this.currentTeam.userControlled) {
                    tradeOfferFromUserTeam = list.get(i);
                }
                else {
                    tradeOfferFromUserTeam = Trade.getTradeOfferFromUserTeam(MainActivity.this.userTeam, MainActivity.this.currentTeam, MainActivity.this.currentTeam.tradingBlock);
                }
                final TradePiece[] array = new TradePiece[tradeOfferFromUserTeam.getAOffer().size() + 2 + tradeOfferFromUserTeam.getBOffer().size()];
                array[0] = null;
                for (i = 0; i < tradeOfferFromUserTeam.getAOffer().size(); ++i) {
                    array[i + 1] = tradeOfferFromUserTeam.getAOffer().get(i);
                }
                array[tradeOfferFromUserTeam.getAOffer().size() + 1] = null;
                for (i = 0; i < tradeOfferFromUserTeam.getBOffer().size(); ++i) {
                    array[tradeOfferFromUserTeam.getAOffer().size() + i + 2] = tradeOfferFromUserTeam.getBOffer().get(i);
                }
                listView.setAdapter((ListAdapter)new TradeListAdapter((Context)MainActivity.this, MainActivity.this, array, tradeOfferFromUserTeam));
                MainActivity.this.currentTrade = tradeOfferFromUserTeam;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        final Button button = (Button)create.findViewById(2131558712);
        final Button button2 = (Button)create.findViewById(2131558710);
        if (this.currentTeam.userControlled) {
            button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    int selection;
                    if ((selection = spinner.getSelectedItemPosition() + 1) > array2.length - 1) {
                        selection = 0;
                    }
                    spinner.setSelection(selection);
                }
            });
            button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    int selection;
                    if ((selection = spinner.getSelectedItemPosition() - 1) < 0) {
                        selection = array2.length - 1;
                    }
                    spinner.setSelection(selection);
                }
            });
            return;
        }
        button.setEnabled(false);
        button2.setEnabled(false);
    }
    
    class CheckNameUniqueOnline extends AsyncTask<String, String, String>
    {
        boolean unique;
        
        CheckNameUniqueOnline() {
            this.unique = false;
        }
        
        protected String doInBackground(final String... p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: iconst_0       
            //     2: aaload         
            //     3: astore          8
            //     5: aconst_null    
            //     6: astore          6
            //     8: aconst_null    
            //     9: astore          4
            //    11: aconst_null    
            //    12: astore          7
            //    14: aconst_null    
            //    15: astore          9
            //    17: aload           7
            //    19: astore          5
            //    21: new             Ljava/net/URL;
            //    24: dup            
            //    25: ldc             "http://coachapps.io/pfc/checkUniqueName.php"
            //    27: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
            //    30: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
            //    33: checkcast       Ljava/net/HttpURLConnection;
            //    36: astore_1       
            //    37: aload           7
            //    39: astore          5
            //    41: aload_1        
            //    42: astore          4
            //    44: aload_1        
            //    45: astore          6
            //    47: aload_1        
            //    48: iconst_1       
            //    49: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
            //    52: aload           7
            //    54: astore          5
            //    56: aload_1        
            //    57: astore          4
            //    59: aload_1        
            //    60: astore          6
            //    62: aload_1        
            //    63: ldc             "POST"
            //    65: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
            //    68: aload           7
            //    70: astore          5
            //    72: aload_1        
            //    73: astore          4
            //    75: aload_1        
            //    76: astore          6
            //    78: aload_1        
            //    79: ldc             "Content-Type"
            //    81: ldc             "application/json"
            //    83: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
            //    86: aload           7
            //    88: astore          5
            //    90: aload_1        
            //    91: astore          4
            //    93: aload_1        
            //    94: astore          6
            //    96: aload_1        
            //    97: ldc             "Accept"
            //    99: ldc             "application/json"
            //   101: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
            //   104: aload           7
            //   106: astore          5
            //   108: aload_1        
            //   109: astore          4
            //   111: aload_1        
            //   112: astore          6
            //   114: new             Ljava/io/BufferedWriter;
            //   117: dup            
            //   118: new             Ljava/io/OutputStreamWriter;
            //   121: dup            
            //   122: aload_1        
            //   123: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
            //   126: ldc             "UTF-8"
            //   128: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
            //   131: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
            //   134: astore          10
            //   136: aload           7
            //   138: astore          5
            //   140: aload_1        
            //   141: astore          4
            //   143: aload_1        
            //   144: astore          6
            //   146: aload           10
            //   148: aload           8
            //   150: invokevirtual   java/io/Writer.write:(Ljava/lang/String;)V
            //   153: aload           7
            //   155: astore          5
            //   157: aload_1        
            //   158: astore          4
            //   160: aload_1        
            //   161: astore          6
            //   163: aload           10
            //   165: invokevirtual   java/io/Writer.flush:()V
            //   168: aload           7
            //   170: astore          5
            //   172: aload_1        
            //   173: astore          4
            //   175: aload_1        
            //   176: astore          6
            //   178: aload           10
            //   180: invokevirtual   java/io/Writer.close:()V
            //   183: aload           7
            //   185: astore          5
            //   187: aload_1        
            //   188: astore          4
            //   190: aload_1        
            //   191: astore          6
            //   193: aload_1        
            //   194: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
            //   197: astore          10
            //   199: aload           7
            //   201: astore          5
            //   203: aload_1        
            //   204: astore          4
            //   206: aload_1        
            //   207: astore          6
            //   209: new             Ljava/lang/StringBuffer;
            //   212: dup            
            //   213: invokespecial   java/lang/StringBuffer.<init>:()V
            //   216: astore          8
            //   218: aload           10
            //   220: ifnonnull       252
            //   223: aload_1        
            //   224: ifnull          231
            //   227: aload_1        
            //   228: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   231: iconst_0       
            //   232: ifeq            243
            //   235: new             Ljava/lang/NullPointerException;
            //   238: dup            
            //   239: invokespecial   java/lang/NullPointerException.<init>:()V
            //   242: athrow         
            //   243: aconst_null    
            //   244: areturn        
            //   245: astore_1       
            //   246: aload_1        
            //   247: invokevirtual   java/io/IOException.printStackTrace:()V
            //   250: aconst_null    
            //   251: areturn        
            //   252: aload           7
            //   254: astore          5
            //   256: aload_1        
            //   257: astore          4
            //   259: aload_1        
            //   260: astore          6
            //   262: new             Ljava/io/BufferedReader;
            //   265: dup            
            //   266: new             Ljava/io/InputStreamReader;
            //   269: dup            
            //   270: aload           10
            //   272: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
            //   275: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
            //   278: astore          7
            //   280: aload           7
            //   282: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
            //   285: astore          4
            //   287: aload           4
            //   289: ifnull          355
            //   292: aload           8
            //   294: new             Ljava/lang/StringBuilder;
            //   297: dup            
            //   298: invokespecial   java/lang/StringBuilder.<init>:()V
            //   301: aload           4
            //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   306: ldc             "\n"
            //   308: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   311: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   314: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   317: pop            
            //   318: goto            280
            //   321: astore          8
            //   323: aload           7
            //   325: astore          5
            //   327: aload_1        
            //   328: astore          4
            //   330: aload           8
            //   332: invokevirtual   java/io/IOException.printStackTrace:()V
            //   335: aload_1        
            //   336: ifnull          343
            //   339: aload_1        
            //   340: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   343: aload           7
            //   345: ifnull          353
            //   348: aload           7
            //   350: invokevirtual   java/io/BufferedReader.close:()V
            //   353: aconst_null    
            //   354: areturn        
            //   355: aload           8
            //   357: invokevirtual   java/lang/StringBuffer.length:()I
            //   360: istore_2       
            //   361: iload_2        
            //   362: ifne            393
            //   365: aload_1        
            //   366: ifnull          373
            //   369: aload_1        
            //   370: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   373: aload           7
            //   375: ifnull          383
            //   378: aload           7
            //   380: invokevirtual   java/io/BufferedReader.close:()V
            //   383: aconst_null    
            //   384: areturn        
            //   385: astore_1       
            //   386: aload_1        
            //   387: invokevirtual   java/io/IOException.printStackTrace:()V
            //   390: goto            383
            //   393: aload           8
            //   395: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
            //   398: astore          4
            //   400: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   403: new             Ljava/lang/StringBuilder;
            //   406: dup            
            //   407: invokespecial   java/lang/StringBuilder.<init>:()V
            //   410: ldc             "JsonResponse = "
            //   412: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   415: aload           4
            //   417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   420: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   423: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   426: new             Lorg/json/JSONObject;
            //   429: dup            
            //   430: aload           4
            //   432: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
            //   435: astore          5
            //   437: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   440: new             Ljava/lang/StringBuilder;
            //   443: dup            
            //   444: invokespecial   java/lang/StringBuilder.<init>:()V
            //   447: ldc             "unique parse = "
            //   449: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   452: aload           5
            //   454: ldc             "unique"
            //   456: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
            //   459: checkcast       Ljava/lang/Integer;
            //   462: invokevirtual   java/lang/Integer.intValue:()I
            //   465: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   468: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   471: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   474: aload           5
            //   476: ldc             "unique"
            //   478: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
            //   481: checkcast       Ljava/lang/Integer;
            //   484: invokevirtual   java/lang/Integer.intValue:()I
            //   487: iconst_1       
            //   488: if_icmpne       519
            //   491: iconst_1       
            //   492: istore_3       
            //   493: aload_0        
            //   494: iload_3        
            //   495: putfield        com/achijones/profootballcoach/MainActivity$CheckNameUniqueOnline.unique:Z
            //   498: aload_1        
            //   499: ifnull          506
            //   502: aload_1        
            //   503: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   506: aload           7
            //   508: ifnull          516
            //   511: aload           7
            //   513: invokevirtual   java/io/BufferedReader.close:()V
            //   516: aload           4
            //   518: areturn        
            //   519: iconst_0       
            //   520: istore_3       
            //   521: goto            493
            //   524: astore          5
            //   526: aload           5
            //   528: invokevirtual   java/lang/Exception.printStackTrace:()V
            //   531: goto            498
            //   534: astore          4
            //   536: aload           7
            //   538: astore          5
            //   540: aload_1        
            //   541: ifnull          548
            //   544: aload_1        
            //   545: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   548: aload           5
            //   550: ifnull          558
            //   553: aload           5
            //   555: invokevirtual   java/io/BufferedReader.close:()V
            //   558: aload           4
            //   560: athrow         
            //   561: astore_1       
            //   562: aload_1        
            //   563: invokevirtual   java/io/IOException.printStackTrace:()V
            //   566: goto            516
            //   569: astore_1       
            //   570: aload_1        
            //   571: invokevirtual   java/io/IOException.printStackTrace:()V
            //   574: goto            353
            //   577: astore_1       
            //   578: aload_1        
            //   579: invokevirtual   java/io/IOException.printStackTrace:()V
            //   582: goto            558
            //   585: astore          6
            //   587: aload           4
            //   589: astore_1       
            //   590: aload           6
            //   592: astore          4
            //   594: goto            540
            //   597: astore          8
            //   599: aload           9
            //   601: astore          7
            //   603: aload           6
            //   605: astore_1       
            //   606: goto            323
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  21     37     597    609    Ljava/io/IOException;
            //  21     37     585    597    Any
            //  47     52     597    609    Ljava/io/IOException;
            //  47     52     585    597    Any
            //  62     68     597    609    Ljava/io/IOException;
            //  62     68     585    597    Any
            //  78     86     597    609    Ljava/io/IOException;
            //  78     86     585    597    Any
            //  96     104    597    609    Ljava/io/IOException;
            //  96     104    585    597    Any
            //  114    136    597    609    Ljava/io/IOException;
            //  114    136    585    597    Any
            //  146    153    597    609    Ljava/io/IOException;
            //  146    153    585    597    Any
            //  163    168    597    609    Ljava/io/IOException;
            //  163    168    585    597    Any
            //  178    183    597    609    Ljava/io/IOException;
            //  178    183    585    597    Any
            //  193    199    597    609    Ljava/io/IOException;
            //  193    199    585    597    Any
            //  209    218    597    609    Ljava/io/IOException;
            //  209    218    585    597    Any
            //  235    243    245    252    Ljava/io/IOException;
            //  262    280    597    609    Ljava/io/IOException;
            //  262    280    585    597    Any
            //  280    287    321    323    Ljava/io/IOException;
            //  280    287    534    540    Any
            //  292    318    321    323    Ljava/io/IOException;
            //  292    318    534    540    Any
            //  330    335    585    597    Any
            //  348    353    569    577    Ljava/io/IOException;
            //  355    361    321    323    Ljava/io/IOException;
            //  355    361    534    540    Any
            //  378    383    385    393    Ljava/io/IOException;
            //  393    426    321    323    Ljava/io/IOException;
            //  393    426    534    540    Any
            //  426    491    524    534    Ljava/lang/Exception;
            //  426    491    321    323    Ljava/io/IOException;
            //  426    491    534    540    Any
            //  493    498    524    534    Ljava/lang/Exception;
            //  493    498    321    323    Ljava/io/IOException;
            //  493    498    534    540    Any
            //  511    516    561    569    Ljava/io/IOException;
            //  526    531    321    323    Ljava/io/IOException;
            //  526    531    534    540    Any
            //  553    558    577    585    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0353:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        protected void onPostExecute(final String s) {
            if (this.unique) {
                MainActivity.this.insertTeamOnlineDialog();
                return;
            }
            Toast.makeText((Context)MainActivity.this, (CharSequence)"Team name not unique, or could not connect.", 0).show();
            MainActivity.this.goOnline();
        }
    }
    
    class InsertTeamOnline extends AsyncTask<String, String, String>
    {
        boolean success;
        
        protected String doInBackground(final String... p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: iconst_0       
            //     2: aaload         
            //     3: astore          7
            //     5: aconst_null    
            //     6: astore          5
            //     8: aconst_null    
            //     9: astore_3       
            //    10: aconst_null    
            //    11: astore          6
            //    13: aconst_null    
            //    14: astore          8
            //    16: aload           6
            //    18: astore          4
            //    20: new             Ljava/net/URL;
            //    23: dup            
            //    24: ldc             "http://coachapps.io/pfc/insertTeam.php"
            //    26: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
            //    29: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
            //    32: checkcast       Ljava/net/HttpURLConnection;
            //    35: astore_1       
            //    36: aload           6
            //    38: astore          4
            //    40: aload_1        
            //    41: astore_3       
            //    42: aload_1        
            //    43: astore          5
            //    45: aload_1        
            //    46: iconst_1       
            //    47: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
            //    50: aload           6
            //    52: astore          4
            //    54: aload_1        
            //    55: astore_3       
            //    56: aload_1        
            //    57: astore          5
            //    59: aload_1        
            //    60: ldc             "POST"
            //    62: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
            //    65: aload           6
            //    67: astore          4
            //    69: aload_1        
            //    70: astore_3       
            //    71: aload_1        
            //    72: astore          5
            //    74: aload_1        
            //    75: ldc             "Content-Type"
            //    77: ldc             "application/json"
            //    79: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
            //    82: aload           6
            //    84: astore          4
            //    86: aload_1        
            //    87: astore_3       
            //    88: aload_1        
            //    89: astore          5
            //    91: aload_1        
            //    92: ldc             "Accept"
            //    94: ldc             "application/json"
            //    96: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
            //    99: aload           6
            //   101: astore          4
            //   103: aload_1        
            //   104: astore_3       
            //   105: aload_1        
            //   106: astore          5
            //   108: new             Ljava/io/BufferedWriter;
            //   111: dup            
            //   112: new             Ljava/io/OutputStreamWriter;
            //   115: dup            
            //   116: aload_1        
            //   117: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
            //   120: ldc             "UTF-8"
            //   122: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
            //   125: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
            //   128: astore          9
            //   130: aload           6
            //   132: astore          4
            //   134: aload_1        
            //   135: astore_3       
            //   136: aload_1        
            //   137: astore          5
            //   139: aload           9
            //   141: aload           7
            //   143: invokevirtual   java/io/Writer.write:(Ljava/lang/String;)V
            //   146: aload           6
            //   148: astore          4
            //   150: aload_1        
            //   151: astore_3       
            //   152: aload_1        
            //   153: astore          5
            //   155: aload           9
            //   157: invokevirtual   java/io/Writer.flush:()V
            //   160: aload           6
            //   162: astore          4
            //   164: aload_1        
            //   165: astore_3       
            //   166: aload_1        
            //   167: astore          5
            //   169: aload           9
            //   171: invokevirtual   java/io/Writer.close:()V
            //   174: aload           6
            //   176: astore          4
            //   178: aload_1        
            //   179: astore_3       
            //   180: aload_1        
            //   181: astore          5
            //   183: aload_1        
            //   184: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
            //   187: astore          9
            //   189: aload           6
            //   191: astore          4
            //   193: aload_1        
            //   194: astore_3       
            //   195: aload_1        
            //   196: astore          5
            //   198: new             Ljava/lang/StringBuffer;
            //   201: dup            
            //   202: invokespecial   java/lang/StringBuffer.<init>:()V
            //   205: astore          7
            //   207: aload           9
            //   209: ifnonnull       241
            //   212: aload_1        
            //   213: ifnull          220
            //   216: aload_1        
            //   217: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   220: iconst_0       
            //   221: ifeq            232
            //   224: new             Ljava/lang/NullPointerException;
            //   227: dup            
            //   228: invokespecial   java/lang/NullPointerException.<init>:()V
            //   231: athrow         
            //   232: aconst_null    
            //   233: areturn        
            //   234: astore_1       
            //   235: aload_1        
            //   236: invokevirtual   java/io/IOException.printStackTrace:()V
            //   239: aconst_null    
            //   240: areturn        
            //   241: aload           6
            //   243: astore          4
            //   245: aload_1        
            //   246: astore_3       
            //   247: aload_1        
            //   248: astore          5
            //   250: new             Ljava/io/BufferedReader;
            //   253: dup            
            //   254: new             Ljava/io/InputStreamReader;
            //   257: dup            
            //   258: aload           9
            //   260: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
            //   263: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
            //   266: astore          6
            //   268: aload           6
            //   270: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
            //   273: astore_3       
            //   274: aload_3        
            //   275: ifnull          339
            //   278: aload           7
            //   280: new             Ljava/lang/StringBuilder;
            //   283: dup            
            //   284: invokespecial   java/lang/StringBuilder.<init>:()V
            //   287: aload_3        
            //   288: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   291: ldc             "\n"
            //   293: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   296: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   299: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   302: pop            
            //   303: goto            268
            //   306: astore          7
            //   308: aload           6
            //   310: astore          4
            //   312: aload_1        
            //   313: astore_3       
            //   314: aload           7
            //   316: invokevirtual   java/io/IOException.printStackTrace:()V
            //   319: aload_1        
            //   320: ifnull          327
            //   323: aload_1        
            //   324: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   327: aload           6
            //   329: ifnull          337
            //   332: aload           6
            //   334: invokevirtual   java/io/BufferedReader.close:()V
            //   337: aconst_null    
            //   338: areturn        
            //   339: aload           7
            //   341: invokevirtual   java/lang/StringBuffer.length:()I
            //   344: istore_2       
            //   345: iload_2        
            //   346: ifne            377
            //   349: aload_1        
            //   350: ifnull          357
            //   353: aload_1        
            //   354: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   357: aload           6
            //   359: ifnull          367
            //   362: aload           6
            //   364: invokevirtual   java/io/BufferedReader.close:()V
            //   367: aconst_null    
            //   368: areturn        
            //   369: astore_1       
            //   370: aload_1        
            //   371: invokevirtual   java/io/IOException.printStackTrace:()V
            //   374: goto            367
            //   377: aload           7
            //   379: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
            //   382: astore_3       
            //   383: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   386: new             Ljava/lang/StringBuilder;
            //   389: dup            
            //   390: invokespecial   java/lang/StringBuilder.<init>:()V
            //   393: ldc             "\nJsonResponse = "
            //   395: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   398: aload_3        
            //   399: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   402: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   405: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   408: new             Lorg/json/JSONObject;
            //   411: dup            
            //   412: aload_3        
            //   413: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
            //   416: ldc             "success"
            //   418: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
            //   421: checkcast       Ljava/lang/Integer;
            //   424: invokevirtual   java/lang/Integer.intValue:()I
            //   427: ifne            463
            //   430: aload_0        
            //   431: iconst_0       
            //   432: putfield        com/achijones/profootballcoach/MainActivity$InsertTeamOnline.success:Z
            //   435: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   438: ldc             "Something went wrong when inserting a team."
            //   440: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   443: aload_1        
            //   444: ifnull          451
            //   447: aload_1        
            //   448: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   451: aload           6
            //   453: ifnull          461
            //   456: aload           6
            //   458: invokevirtual   java/io/BufferedReader.close:()V
            //   461: aload_3        
            //   462: areturn        
            //   463: aload_0        
            //   464: iconst_1       
            //   465: putfield        com/achijones/profootballcoach/MainActivity$InsertTeamOnline.success:Z
            //   468: goto            443
            //   471: astore          4
            //   473: aload           4
            //   475: invokevirtual   java/lang/Exception.printStackTrace:()V
            //   478: goto            443
            //   481: astore_3       
            //   482: aload           6
            //   484: astore          4
            //   486: aload_1        
            //   487: ifnull          494
            //   490: aload_1        
            //   491: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   494: aload           4
            //   496: ifnull          504
            //   499: aload           4
            //   501: invokevirtual   java/io/BufferedReader.close:()V
            //   504: aload_3        
            //   505: athrow         
            //   506: astore_1       
            //   507: aload_1        
            //   508: invokevirtual   java/io/IOException.printStackTrace:()V
            //   511: goto            461
            //   514: astore_1       
            //   515: aload_1        
            //   516: invokevirtual   java/io/IOException.printStackTrace:()V
            //   519: goto            337
            //   522: astore_1       
            //   523: aload_1        
            //   524: invokevirtual   java/io/IOException.printStackTrace:()V
            //   527: goto            504
            //   530: astore          5
            //   532: aload_3        
            //   533: astore_1       
            //   534: aload           5
            //   536: astore_3       
            //   537: goto            486
            //   540: astore          7
            //   542: aload           8
            //   544: astore          6
            //   546: aload           5
            //   548: astore_1       
            //   549: goto            308
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  20     36     540    552    Ljava/io/IOException;
            //  20     36     530    540    Any
            //  45     50     540    552    Ljava/io/IOException;
            //  45     50     530    540    Any
            //  59     65     540    552    Ljava/io/IOException;
            //  59     65     530    540    Any
            //  74     82     540    552    Ljava/io/IOException;
            //  74     82     530    540    Any
            //  91     99     540    552    Ljava/io/IOException;
            //  91     99     530    540    Any
            //  108    130    540    552    Ljava/io/IOException;
            //  108    130    530    540    Any
            //  139    146    540    552    Ljava/io/IOException;
            //  139    146    530    540    Any
            //  155    160    540    552    Ljava/io/IOException;
            //  155    160    530    540    Any
            //  169    174    540    552    Ljava/io/IOException;
            //  169    174    530    540    Any
            //  183    189    540    552    Ljava/io/IOException;
            //  183    189    530    540    Any
            //  198    207    540    552    Ljava/io/IOException;
            //  198    207    530    540    Any
            //  224    232    234    241    Ljava/io/IOException;
            //  250    268    540    552    Ljava/io/IOException;
            //  250    268    530    540    Any
            //  268    274    306    308    Ljava/io/IOException;
            //  268    274    481    486    Any
            //  278    303    306    308    Ljava/io/IOException;
            //  278    303    481    486    Any
            //  314    319    530    540    Any
            //  332    337    514    522    Ljava/io/IOException;
            //  339    345    306    308    Ljava/io/IOException;
            //  339    345    481    486    Any
            //  362    367    369    377    Ljava/io/IOException;
            //  377    408    306    308    Ljava/io/IOException;
            //  377    408    481    486    Any
            //  408    443    471    481    Ljava/lang/Exception;
            //  408    443    306    308    Ljava/io/IOException;
            //  408    443    481    486    Any
            //  456    461    506    514    Ljava/io/IOException;
            //  463    468    471    481    Ljava/lang/Exception;
            //  463    468    306    308    Ljava/io/IOException;
            //  463    468    481    486    Any
            //  473    478    306    308    Ljava/io/IOException;
            //  473    478    481    486    Any
            //  499    504    522    530    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0337:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        protected void onPostExecute(final String s) {
            if (this.success) {
                Toast.makeText((Context)MainActivity.this, (CharSequence)"Successfully sent team online.", 0).show();
                return;
            }
            Toast.makeText((Context)MainActivity.this, (CharSequence)"Something went wrong! Team not placed online.", 0).show();
        }
    }
    
    class RemoveAndInsertTeamOnline extends AsyncTask<String, String, String>
    {
        boolean success;
        
        RemoveAndInsertTeamOnline() {
            this.success = false;
        }
        
        protected String doInBackground(final String... p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: iconst_0       
            //     2: aaload         
            //     3: astore          8
            //     5: aconst_null    
            //     6: astore          6
            //     8: aconst_null    
            //     9: astore          4
            //    11: aconst_null    
            //    12: astore          7
            //    14: aconst_null    
            //    15: astore          9
            //    17: aload           7
            //    19: astore          5
            //    21: new             Ljava/net/URL;
            //    24: dup            
            //    25: ldc             "http://coachapps.io/pfc/removeTeam.php"
            //    27: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
            //    30: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
            //    33: checkcast       Ljava/net/HttpURLConnection;
            //    36: astore_1       
            //    37: aload           7
            //    39: astore          5
            //    41: aload_1        
            //    42: astore          4
            //    44: aload_1        
            //    45: astore          6
            //    47: aload_1        
            //    48: iconst_1       
            //    49: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
            //    52: aload           7
            //    54: astore          5
            //    56: aload_1        
            //    57: astore          4
            //    59: aload_1        
            //    60: astore          6
            //    62: aload_1        
            //    63: ldc             "POST"
            //    65: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
            //    68: aload           7
            //    70: astore          5
            //    72: aload_1        
            //    73: astore          4
            //    75: aload_1        
            //    76: astore          6
            //    78: aload_1        
            //    79: ldc             "Content-Type"
            //    81: ldc             "application/json"
            //    83: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
            //    86: aload           7
            //    88: astore          5
            //    90: aload_1        
            //    91: astore          4
            //    93: aload_1        
            //    94: astore          6
            //    96: aload_1        
            //    97: ldc             "Accept"
            //    99: ldc             "application/json"
            //   101: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
            //   104: aload           7
            //   106: astore          5
            //   108: aload_1        
            //   109: astore          4
            //   111: aload_1        
            //   112: astore          6
            //   114: new             Ljava/io/BufferedWriter;
            //   117: dup            
            //   118: new             Ljava/io/OutputStreamWriter;
            //   121: dup            
            //   122: aload_1        
            //   123: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
            //   126: ldc             "UTF-8"
            //   128: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
            //   131: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
            //   134: astore          10
            //   136: aload           7
            //   138: astore          5
            //   140: aload_1        
            //   141: astore          4
            //   143: aload_1        
            //   144: astore          6
            //   146: aload           10
            //   148: aload           8
            //   150: invokevirtual   java/io/Writer.write:(Ljava/lang/String;)V
            //   153: aload           7
            //   155: astore          5
            //   157: aload_1        
            //   158: astore          4
            //   160: aload_1        
            //   161: astore          6
            //   163: aload           10
            //   165: invokevirtual   java/io/Writer.flush:()V
            //   168: aload           7
            //   170: astore          5
            //   172: aload_1        
            //   173: astore          4
            //   175: aload_1        
            //   176: astore          6
            //   178: aload           10
            //   180: invokevirtual   java/io/Writer.close:()V
            //   183: aload           7
            //   185: astore          5
            //   187: aload_1        
            //   188: astore          4
            //   190: aload_1        
            //   191: astore          6
            //   193: aload_1        
            //   194: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
            //   197: astore          10
            //   199: aload           7
            //   201: astore          5
            //   203: aload_1        
            //   204: astore          4
            //   206: aload_1        
            //   207: astore          6
            //   209: new             Ljava/lang/StringBuffer;
            //   212: dup            
            //   213: invokespecial   java/lang/StringBuffer.<init>:()V
            //   216: astore          8
            //   218: aload           10
            //   220: ifnonnull       252
            //   223: aload_1        
            //   224: ifnull          231
            //   227: aload_1        
            //   228: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   231: iconst_0       
            //   232: ifeq            243
            //   235: new             Ljava/lang/NullPointerException;
            //   238: dup            
            //   239: invokespecial   java/lang/NullPointerException.<init>:()V
            //   242: athrow         
            //   243: aconst_null    
            //   244: areturn        
            //   245: astore_1       
            //   246: aload_1        
            //   247: invokevirtual   java/io/IOException.printStackTrace:()V
            //   250: aconst_null    
            //   251: areturn        
            //   252: aload           7
            //   254: astore          5
            //   256: aload_1        
            //   257: astore          4
            //   259: aload_1        
            //   260: astore          6
            //   262: new             Ljava/io/BufferedReader;
            //   265: dup            
            //   266: new             Ljava/io/InputStreamReader;
            //   269: dup            
            //   270: aload           10
            //   272: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
            //   275: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
            //   278: astore          7
            //   280: aload           7
            //   282: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
            //   285: astore          4
            //   287: aload           4
            //   289: ifnull          355
            //   292: aload           8
            //   294: new             Ljava/lang/StringBuilder;
            //   297: dup            
            //   298: invokespecial   java/lang/StringBuilder.<init>:()V
            //   301: aload           4
            //   303: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   306: ldc             "\n"
            //   308: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   311: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   314: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   317: pop            
            //   318: goto            280
            //   321: astore          8
            //   323: aload           7
            //   325: astore          5
            //   327: aload_1        
            //   328: astore          4
            //   330: aload           8
            //   332: invokevirtual   java/io/IOException.printStackTrace:()V
            //   335: aload_1        
            //   336: ifnull          343
            //   339: aload_1        
            //   340: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   343: aload           7
            //   345: ifnull          353
            //   348: aload           7
            //   350: invokevirtual   java/io/BufferedReader.close:()V
            //   353: aconst_null    
            //   354: areturn        
            //   355: aload           8
            //   357: invokevirtual   java/lang/StringBuffer.length:()I
            //   360: istore_2       
            //   361: iload_2        
            //   362: ifne            393
            //   365: aload_1        
            //   366: ifnull          373
            //   369: aload_1        
            //   370: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   373: aload           7
            //   375: ifnull          383
            //   378: aload           7
            //   380: invokevirtual   java/io/BufferedReader.close:()V
            //   383: aconst_null    
            //   384: areturn        
            //   385: astore_1       
            //   386: aload_1        
            //   387: invokevirtual   java/io/IOException.printStackTrace:()V
            //   390: goto            383
            //   393: aload           8
            //   395: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
            //   398: astore          4
            //   400: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   403: new             Ljava/lang/StringBuilder;
            //   406: dup            
            //   407: invokespecial   java/lang/StringBuilder.<init>:()V
            //   410: ldc             "\nJsonResponse = "
            //   412: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   415: aload           4
            //   417: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   420: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   423: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   426: new             Lorg/json/JSONObject;
            //   429: dup            
            //   430: aload           4
            //   432: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
            //   435: ldc             "success"
            //   437: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
            //   440: checkcast       Ljava/lang/Integer;
            //   443: invokevirtual   java/lang/Integer.intValue:()I
            //   446: iconst_1       
            //   447: if_icmpne       478
            //   450: iconst_1       
            //   451: istore_3       
            //   452: aload_0        
            //   453: iload_3        
            //   454: putfield        com/achijones/profootballcoach/MainActivity$RemoveAndInsertTeamOnline.success:Z
            //   457: aload_1        
            //   458: ifnull          465
            //   461: aload_1        
            //   462: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   465: aload           7
            //   467: ifnull          475
            //   470: aload           7
            //   472: invokevirtual   java/io/BufferedReader.close:()V
            //   475: aload           4
            //   477: areturn        
            //   478: iconst_0       
            //   479: istore_3       
            //   480: goto            452
            //   483: astore          5
            //   485: aload           5
            //   487: invokevirtual   java/lang/Exception.printStackTrace:()V
            //   490: goto            457
            //   493: astore          4
            //   495: aload           7
            //   497: astore          5
            //   499: aload_1        
            //   500: ifnull          507
            //   503: aload_1        
            //   504: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   507: aload           5
            //   509: ifnull          517
            //   512: aload           5
            //   514: invokevirtual   java/io/BufferedReader.close:()V
            //   517: aload           4
            //   519: athrow         
            //   520: astore_1       
            //   521: aload_1        
            //   522: invokevirtual   java/io/IOException.printStackTrace:()V
            //   525: goto            475
            //   528: astore_1       
            //   529: aload_1        
            //   530: invokevirtual   java/io/IOException.printStackTrace:()V
            //   533: goto            353
            //   536: astore_1       
            //   537: aload_1        
            //   538: invokevirtual   java/io/IOException.printStackTrace:()V
            //   541: goto            517
            //   544: astore          6
            //   546: aload           4
            //   548: astore_1       
            //   549: aload           6
            //   551: astore          4
            //   553: goto            499
            //   556: astore          8
            //   558: aload           9
            //   560: astore          7
            //   562: aload           6
            //   564: astore_1       
            //   565: goto            323
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  21     37     556    568    Ljava/io/IOException;
            //  21     37     544    556    Any
            //  47     52     556    568    Ljava/io/IOException;
            //  47     52     544    556    Any
            //  62     68     556    568    Ljava/io/IOException;
            //  62     68     544    556    Any
            //  78     86     556    568    Ljava/io/IOException;
            //  78     86     544    556    Any
            //  96     104    556    568    Ljava/io/IOException;
            //  96     104    544    556    Any
            //  114    136    556    568    Ljava/io/IOException;
            //  114    136    544    556    Any
            //  146    153    556    568    Ljava/io/IOException;
            //  146    153    544    556    Any
            //  163    168    556    568    Ljava/io/IOException;
            //  163    168    544    556    Any
            //  178    183    556    568    Ljava/io/IOException;
            //  178    183    544    556    Any
            //  193    199    556    568    Ljava/io/IOException;
            //  193    199    544    556    Any
            //  209    218    556    568    Ljava/io/IOException;
            //  209    218    544    556    Any
            //  235    243    245    252    Ljava/io/IOException;
            //  262    280    556    568    Ljava/io/IOException;
            //  262    280    544    556    Any
            //  280    287    321    323    Ljava/io/IOException;
            //  280    287    493    499    Any
            //  292    318    321    323    Ljava/io/IOException;
            //  292    318    493    499    Any
            //  330    335    544    556    Any
            //  348    353    528    536    Ljava/io/IOException;
            //  355    361    321    323    Ljava/io/IOException;
            //  355    361    493    499    Any
            //  378    383    385    393    Ljava/io/IOException;
            //  393    426    321    323    Ljava/io/IOException;
            //  393    426    493    499    Any
            //  426    450    483    493    Ljava/lang/Exception;
            //  426    450    321    323    Ljava/io/IOException;
            //  426    450    493    499    Any
            //  452    457    483    493    Ljava/lang/Exception;
            //  452    457    321    323    Ljava/io/IOException;
            //  452    457    493    499    Any
            //  470    475    520    528    Ljava/io/IOException;
            //  485    490    321    323    Ljava/io/IOException;
            //  485    490    493    499    Any
            //  512    517    536    544    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0353:
            //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
            //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
            //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        protected void onPostExecute(final String s) {
            if (this.success) {
                new InsertTeamOnline().execute((Object[])new String[] { MainActivity.this.onlineTeamDict.toString() });
                return;
            }
            Toast.makeText((Context)MainActivity.this, (CharSequence)"Something went wrong when replacing a team.", 0).show();
            new InsertTeamOnline().execute((Object[])new String[] { MainActivity.this.onlineTeamDict.toString() });
        }
    }
}
