// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import java.util.ArrayList;
import android.os.AsyncTask;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View$OnClickListener;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.ListAdapter;
import java.util.Map;
import android.widget.ExpandableListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import PFCpack.TeamStrategy;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.view.ViewGroup;
import org.json.JSONObject;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import java.util.List;
import android.widget.Button;
import PFCpack.Team;
import android.widget.ListView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import PFCpack.Game;
import android.support.v7.app.AppCompatActivity;

public class OnlineActivity extends AppCompatActivity
{
    Game currGame;
    int currTab;
    ArrayAdapter<String> dataAdapterTeam;
    Spinner examineTeamSpinner;
    LinearLayout examineTeamView;
    ExpandableListView expListPlayerStats;
    boolean gettingRandTeam;
    boolean gettingUserTeam;
    ListView mainList;
    Team oppTeam;
    Button playWeekButton;
    List<String> teamList;
    Team userTeam;
    String userTeamStr;
    
    private void exitToMainMeu() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setMessage((CharSequence)"Are you sure you want to return to main menu? Progress will be saved.").setPositiveButton((CharSequence)"Yes, Exit", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                OnlineActivity.this.startActivity(new Intent((Context)OnlineActivity.this, (Class)Home.class));
            }
        }).setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        alertDialog$Builder.create().show();
    }
    
    private void showTeamStrategyDialog() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Default Team Strategy").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                try {
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", (Object)OnlineActivity.this.userTeam.name);
                    jsonObject.put("off_strat", OnlineActivity.this.userTeam.teamStratOffNum);
                    jsonObject.put("def_strat", OnlineActivity.this.userTeam.teamStratDefNum);
                    new UpdateTeamStratsOnline().execute((Object[])new String[] { jsonObject.toString() });
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
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
                OnlineActivity.this.userTeam.teamStratOff = teamStrategiesOff[teamStratOffNum];
                OnlineActivity.this.userTeam.teamStratOffNum = teamStratOffNum;
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
                OnlineActivity.this.userTeam.teamStratDef = teamStrategiesDef[teamStratDefNum];
                OnlineActivity.this.userTeam.teamStratDefNum = teamStratDefNum;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    private void updatePlayerStats(final boolean b) {
        this.mainList.setVisibility(8);
        this.expListPlayerStats.setVisibility(0);
        if (this.oppTeam != null) {
            this.examineTeamView.setVisibility(0);
        }
        else {
            this.examineTeamView.setVisibility(8);
        }
        List<String> list;
        Map<String, List<String>> map;
        if (b || this.oppTeam == null) {
            list = this.userTeam.getOnlinePlayerStatsExpandListStr();
            map = this.userTeam.getOnlinePlayerStatsExpandListMap(list);
        }
        else {
            list = this.oppTeam.getOnlinePlayerStatsExpandListStr();
            map = this.oppTeam.getOnlinePlayerStatsExpandListMap(list);
        }
        this.expListPlayerStats.setAdapter((ExpandableListAdapter)new OnlineExpandableListAdapterPlayerStats((Activity)this, list, map));
    }
    
    private void updateSchedule() {
        this.mainList.setVisibility(0);
        this.expListPlayerStats.setVisibility(8);
        this.examineTeamView.setVisibility(8);
        final Game[] array = new Game[this.userTeam.gameSchedule.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = this.userTeam.gameSchedule.get(i);
        }
        this.mainList.setAdapter((ListAdapter)new OnlineGameScheduleListArrayAdapter((Context)this, this, this.userTeam, array));
    }
    
    private void updateTeamStats() {
        this.mainList.setVisibility(0);
        this.expListPlayerStats.setVisibility(8);
        this.examineTeamView.setVisibility(8);
        final String[] array = new String[15];
        if (this.oppTeam == null) {
            array[0] = "NONE,Team," + this.userTeam.abbr;
            array[1] = "--,ELO," + this.userTeam.teamELO;
            array[2] = "--,Wins," + this.userTeam.wins;
            array[3] = "--,Losses," + this.userTeam.losses;
            array[4] = "--,Points," + this.userTeam.getPPG();
            array[5] = "--,Opp Points," + this.userTeam.getOPPG();
            array[6] = "--,Yards," + this.userTeam.getYPG();
            array[7] = "--,Opp Yards," + this.userTeam.getOYPG();
            array[8] = "--,Pass Yards," + this.userTeam.getPYPG();
            array[9] = "--,Rush Yards," + this.userTeam.getRYPG();
            array[10] = "--,Opp Pass Yards," + this.userTeam.getOPYPG();
            array[11] = "--,Opp Rush Yards," + this.userTeam.getORYPG();
            array[12] = "--,TO Diff," + this.userTeam.getTODiff();
            array[13] = "--,Off Talent," + this.userTeam.getOffTalent();
            array[14] = "--,Def Talent," + this.userTeam.getDefTalent();
        }
        else {
            array[0] = this.oppTeam.abbr + ",Team," + this.userTeam.abbr;
            array[1] = this.oppTeam.teamELO + ",ELO," + this.userTeam.teamELO;
            array[2] = this.oppTeam.wins + ",Wins," + this.userTeam.wins;
            array[3] = this.oppTeam.losses + ",Losses," + this.userTeam.losses;
            array[4] = this.oppTeam.getPPG() + ",Points," + this.userTeam.getPPG();
            array[5] = this.oppTeam.getOPPG() + ",Opp Points," + this.userTeam.getOPPG();
            array[6] = this.oppTeam.getYPG() + ",Yards," + this.userTeam.getYPG();
            array[7] = this.oppTeam.getOYPG() + ",Opp Yards," + this.userTeam.getOYPG();
            array[8] = this.oppTeam.getPYPG() + ",Pass Yards," + this.userTeam.getPYPG();
            array[9] = this.oppTeam.getRYPG() + ",Rush Yards," + this.userTeam.getRYPG();
            array[10] = this.oppTeam.getOPYPG() + ",Opp Pass Yards," + this.userTeam.getOPYPG();
            array[11] = this.oppTeam.getORYPG() + ",Opp Rush Yards," + this.userTeam.getORYPG();
            array[12] = this.oppTeam.getTODiff() + ",TO Diff," + this.userTeam.getTODiff();
            array[13] = this.oppTeam.getOffTalent() + ",Off Talent," + this.userTeam.getOffTalent();
            array[14] = this.oppTeam.getDefTalent() + ",Def Talent," + this.userTeam.getDefTalent();
        }
        this.mainList.setAdapter((ListAdapter)new OnlineTeamStatsListArrayAdapter((Context)this, array));
    }
    
    public void customOnlineGame() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Opponent Team Name:");
        final EditText view = new EditText((Context)this);
        view.setInputType(1);
        alertDialog$Builder.setView((View)view);
        alertDialog$Builder.setPositiveButton((CharSequence)"Schedule", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                if (view.getText().toString().trim().equals(OnlineActivity.this.userTeam.name)) {
                    Toast.makeText((Context)OnlineActivity.this, (CharSequence)"Can't schedule your own team!", 0).show();
                    return;
                }
                try {
                    OnlineActivity.this.gettingUserTeam = false;
                    OnlineActivity.this.gettingRandTeam = false;
                    final JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", (Object)view.getText().toString().trim());
                    new GetTeamOnline().execute((Object[])new String[] { jsonObject.toString() });
                    OnlineActivity.this.playWeekButton.setText((CharSequence)"Play Custom Game");
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.cancel();
            }
        });
        alertDialog$Builder.show();
    }
    
    protected void onCreate(Bundle extras) {
        super.onCreate(extras);
        this.setContentView(2130968606);
        this.setSupportActionBar((Toolbar)this.findViewById(2131558542));
        this.mainList = (ListView)this.findViewById(2131558575);
        this.expListPlayerStats = (ExpandableListView)this.findViewById(2131558576);
        this.examineTeamSpinner = (Spinner)this.findViewById(2131558569);
        this.examineTeamView = (LinearLayout)this.findViewById(2131558585);
        extras = this.getIntent().getExtras();
        while (true) {
            if (extras == null) {
                break Label_0152;
            }
            final String string = extras.getString("ONLINE_TEAM");
            this.gettingUserTeam = true;
            this.gettingRandTeam = false;
            try {
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", (Object)string);
                new GetTeamOnline().execute((Object[])new String[] { jsonObject.toString() });
                final Button button = (Button)this.findViewById(2131558571);
                final Button button2 = (Button)this.findViewById(2131558572);
                final Button button3 = (Button)this.findViewById(2131558573);
                button.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        OnlineActivity.this.currTab = 0;
                        OnlineActivity.this.updateTeamStats();
                    }
                });
                button2.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        OnlineActivity.this.currTab = 1;
                        OnlineActivity.this.examineTeamSpinner.setSelection(0);
                        OnlineActivity.this.updatePlayerStats(true);
                    }
                });
                button3.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        OnlineActivity.this.currTab = 2;
                        OnlineActivity.this.updateSchedule();
                    }
                });
                (this.playWeekButton = (Button)this.findViewById(2131558586)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        if (!OnlineActivity.this.playWeekButton.getText().toString().equals("Schedule Ranked Game")) {
                            OnlineActivity.this.playGame();
                            OnlineActivity.this.playWeekButton.setText((CharSequence)"Schedule Ranked Game");
                            return;
                        }
                        OnlineActivity.this.gettingUserTeam = false;
                        OnlineActivity.this.gettingRandTeam = true;
                        while (true) {
                            try {
                                final JSONObject jsonObject = new JSONObject();
                                jsonObject.put("name", (Object)OnlineActivity.this.userTeam.name);
                                new GetTeamOnline().execute((Object[])new String[] { jsonObject.toString() });
                                OnlineActivity.this.playWeekButton.setText((CharSequence)"Play Ranked Game");
                            }
                            catch (Exception ex) {
                                ex.printStackTrace();
                                continue;
                            }
                            break;
                        }
                    }
                });
                this.examineTeamSpinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
                    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                        System.out.println("selected in spinner " + n);
                        if (n == 0) {
                            OnlineActivity.this.updatePlayerStats(true);
                            return;
                        }
                        OnlineActivity.this.updatePlayerStats(false);
                    }
                    
                    public void onNothingSelected(final AdapterView<?> adapterView) {
                    }
                });
            }
            catch (Exception ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        this.getMenuInflater().inflate(2131623937, menu);
        return true;
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        final int itemId = menuItem.getItemId();
        if (itemId == 2131558723) {
            this.showTeamStrategyDialog();
        }
        else if (itemId == 2131558727) {
            this.customOnlineGame();
        }
        else if (itemId == 2131558726) {
            this.exitToMainMeu();
        }
        else if (itemId == 2131558728) {
            new GetLeaderboard().execute((Object[])new String[] { "test" });
        }
        return super.onOptionsItemSelected(menuItem);
    }
    
    public void playGame() {
        this.currGame.playGame();
        if (this.currTab == 0) {
            this.updateTeamStats();
        }
        else if (this.currTab == 1) {
            this.updatePlayerStats(true);
        }
        else if (this.currTab == 2) {
            this.updateSchedule();
        }
        this.showGameDialog(this.currGame);
        this.updateDatabase(this.currGame);
        this.updateUI();
    }
    
    public void scheduleGame(final boolean affectsELO) {
        this.currGame = new Game(this.userTeam, this.oppTeam, this.oppTeam.name + " @ " + this.userTeam.name, 0);
        this.currGame.isOnline = true;
        this.currGame.affectsELO = affectsELO;
        this.userTeam.gameSchedule.add(this.currGame);
        this.showGameScoutDialog(this.currGame);
    }
    
    public void showGameDialog(final Game game) {
        final String[] gameSummaryStr = game.getGameSummaryStr();
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)game.gameName).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
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
    }
    
    public void showGameScoutDialog(final Game game) {
        final String[] gameScoutStr = game.getGameScoutStr();
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)("Opponent: " + game.awayTeam.name)).setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        }).setView(this.getLayoutInflater().inflate(2130968632, (ViewGroup)null));
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        ((TextView)create.findViewById(2131558624)).setText((CharSequence)gameScoutStr[0]);
        ((TextView)create.findViewById(2131558625)).setText((CharSequence)gameScoutStr[1]);
        ((TextView)create.findViewById(2131558626)).setText((CharSequence)gameScoutStr[2]);
        ((TextView)create.findViewById(2131558631)).setText((CharSequence)gameScoutStr[3]);
        final TextView textView = (TextView)create.findViewById(2131558627);
        final TextView textView2 = (TextView)create.findViewById(2131558629);
        textView.setText((CharSequence)(this.userTeam.abbr + " Off Strategy:"));
        textView2.setText((CharSequence)(this.userTeam.abbr + " Def Strategy:"));
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
        spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int teamStratOffNum, final long n) {
                OnlineActivity.this.userTeam.teamStratOff = teamStrategiesOff[teamStratOffNum];
                OnlineActivity.this.userTeam.teamStratOffNum = teamStratOffNum;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
        final Spinner spinner2 = (Spinner)create.findViewById(2131558630);
        final ArrayAdapter adapter2 = new ArrayAdapter((Context)this, 17367048, (Object[])array2);
        adapter2.setDropDownViewResource(17367049);
        spinner2.setAdapter((SpinnerAdapter)adapter2);
        spinner2.setSelection(selection2);
        spinner2.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, final int teamStratDefNum, final long n) {
                OnlineActivity.this.userTeam.teamStratDef = teamStrategiesDef[teamStratDefNum];
                OnlineActivity.this.userTeam.teamStratDefNum = teamStratDefNum;
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
            }
        });
    }
    
    public void updateDatabase(final Game game) {
        while (true) {
            while (true) {
                int n;
                try {
                    final JSONObject jsonGameResult = game.getJSONGameResult(true);
                    final JSONObject jsonGameResult2 = game.getJSONGameResult(false);
                    if (this.userTeam == game.homeTeam) {
                        n = game.homeELO;
                    }
                    else {
                        n = game.awayELO;
                    }
                    if (this.userTeam == game.getWinner()) {
                        if (game.affectsELO) {
                            Toast.makeText((Context)this, (CharSequence)("You Win! Gained " + n + " ELO."), 0).show();
                        }
                        else {
                            Toast.makeText((Context)this, (CharSequence)"You Win! Since it was custom game, your ELO is not changed.", 0).show();
                        }
                        new UpdateTeamOnline().execute((Object[])new String[] { jsonGameResult.toString() });
                        new UpdateTeamOnline().execute((Object[])new String[] { jsonGameResult2.toString() });
                        return;
                    }
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    return;
                }
                if (game.affectsELO) {
                    Toast.makeText((Context)this, (CharSequence)("You lost, and lost " + Math.abs(n) + " ELO."), 0).show();
                    continue;
                }
                Toast.makeText((Context)this, (CharSequence)"You Lost. Since it was custom game, your ELO is not changed.", 0).show();
                continue;
            }
        }
    }
    
    public void updateUI() {
        ((TextView)this.findViewById(2131558570)).setText((CharSequence)(this.userTeam.name + " (" + this.userTeam.wins + "-" + this.userTeam.losses + ")"));
        if (this.oppTeam != null) {
            final ArrayAdapter adapter = new ArrayAdapter((Context)this, 17367048, (Object[])new String[] { this.userTeam.name, this.oppTeam.name });
            adapter.setDropDownViewResource(17367049);
            this.examineTeamSpinner.setAdapter((SpinnerAdapter)adapter);
        }
    }
    
    class GetLeaderboard extends AsyncTask<String, String, String>
    {
        JSONObject leaderboardJSON;
        boolean success;
        
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
            //     6: astore          5
            //     8: aconst_null    
            //     9: astore_3       
            //    10: aconst_null    
            //    11: astore          6
            //    13: aconst_null    
            //    14: astore          7
            //    16: aload           6
            //    18: astore          4
            //    20: new             Ljava/net/URL;
            //    23: dup            
            //    24: ldc             "http://coachapps.io/pfc/getLeaderboard.php"
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
            //   141: aload           8
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
            //   205: astore          8
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
            //   275: ifnull          347
            //   278: aload           8
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
            //   306: astore_3       
            //   307: aload_1        
            //   308: astore          5
            //   310: aload           6
            //   312: astore_1       
            //   313: aload_3        
            //   314: astore          6
            //   316: aload_1        
            //   317: astore          4
            //   319: aload           5
            //   321: astore_3       
            //   322: aload           6
            //   324: invokevirtual   java/io/IOException.printStackTrace:()V
            //   327: aload           5
            //   329: ifnull          337
            //   332: aload           5
            //   334: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   337: aload_1        
            //   338: ifnull          345
            //   341: aload_1        
            //   342: invokevirtual   java/io/BufferedReader.close:()V
            //   345: aconst_null    
            //   346: areturn        
            //   347: aload           8
            //   349: invokevirtual   java/lang/StringBuffer.length:()I
            //   352: istore_2       
            //   353: iload_2        
            //   354: ifne            385
            //   357: aload_1        
            //   358: ifnull          365
            //   361: aload_1        
            //   362: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   365: aload           6
            //   367: ifnull          375
            //   370: aload           6
            //   372: invokevirtual   java/io/BufferedReader.close:()V
            //   375: aconst_null    
            //   376: areturn        
            //   377: astore_1       
            //   378: aload_1        
            //   379: invokevirtual   java/io/IOException.printStackTrace:()V
            //   382: goto            375
            //   385: aload           8
            //   387: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
            //   390: astore_3       
            //   391: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   394: new             Ljava/lang/StringBuilder;
            //   397: dup            
            //   398: invokespecial   java/lang/StringBuilder.<init>:()V
            //   401: ldc             "\nJsonResponse = "
            //   403: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   406: aload_3        
            //   407: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   410: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   413: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   416: new             Lorg/json/JSONObject;
            //   419: dup            
            //   420: aload_3        
            //   421: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
            //   424: astore          4
            //   426: aload           4
            //   428: ldc             "success"
            //   430: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
            //   433: checkcast       Ljava/lang/Integer;
            //   436: invokevirtual   java/lang/Integer.intValue:()I
            //   439: ifne            475
            //   442: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   445: ldc             "Something went wrong."
            //   447: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   450: aload_0        
            //   451: iconst_0       
            //   452: putfield        com/achijones/profootballcoach/OnlineActivity$GetLeaderboard.success:Z
            //   455: aload_1        
            //   456: ifnull          463
            //   459: aload_1        
            //   460: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   463: aload           6
            //   465: ifnull          473
            //   468: aload           6
            //   470: invokevirtual   java/io/BufferedReader.close:()V
            //   473: aload_3        
            //   474: areturn        
            //   475: aload_0        
            //   476: iconst_1       
            //   477: putfield        com/achijones/profootballcoach/OnlineActivity$GetLeaderboard.success:Z
            //   480: aload_0        
            //   481: aload           4
            //   483: putfield        com/achijones/profootballcoach/OnlineActivity$GetLeaderboard.leaderboardJSON:Lorg/json/JSONObject;
            //   486: goto            455
            //   489: astore          4
            //   491: aload           4
            //   493: invokevirtual   java/lang/Exception.printStackTrace:()V
            //   496: goto            455
            //   499: astore_3       
            //   500: aload           6
            //   502: astore          4
            //   504: aload_1        
            //   505: ifnull          512
            //   508: aload_1        
            //   509: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   512: aload           4
            //   514: ifnull          522
            //   517: aload           4
            //   519: invokevirtual   java/io/BufferedReader.close:()V
            //   522: aload_3        
            //   523: athrow         
            //   524: astore_1       
            //   525: aload_1        
            //   526: invokevirtual   java/io/IOException.printStackTrace:()V
            //   529: goto            473
            //   532: astore_1       
            //   533: aload_1        
            //   534: invokevirtual   java/io/IOException.printStackTrace:()V
            //   537: goto            345
            //   540: astore_1       
            //   541: aload_1        
            //   542: invokevirtual   java/io/IOException.printStackTrace:()V
            //   545: goto            522
            //   548: astore          5
            //   550: aload_3        
            //   551: astore_1       
            //   552: aload           5
            //   554: astore_3       
            //   555: goto            504
            //   558: astore          6
            //   560: aload           7
            //   562: astore_1       
            //   563: goto            316
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  20     36     558    566    Ljava/io/IOException;
            //  20     36     548    558    Any
            //  45     50     558    566    Ljava/io/IOException;
            //  45     50     548    558    Any
            //  59     65     558    566    Ljava/io/IOException;
            //  59     65     548    558    Any
            //  74     82     558    566    Ljava/io/IOException;
            //  74     82     548    558    Any
            //  91     99     558    566    Ljava/io/IOException;
            //  91     99     548    558    Any
            //  108    130    558    566    Ljava/io/IOException;
            //  108    130    548    558    Any
            //  139    146    558    566    Ljava/io/IOException;
            //  139    146    548    558    Any
            //  155    160    558    566    Ljava/io/IOException;
            //  155    160    548    558    Any
            //  169    174    558    566    Ljava/io/IOException;
            //  169    174    548    558    Any
            //  183    189    558    566    Ljava/io/IOException;
            //  183    189    548    558    Any
            //  198    207    558    566    Ljava/io/IOException;
            //  198    207    548    558    Any
            //  224    232    234    241    Ljava/io/IOException;
            //  250    268    558    566    Ljava/io/IOException;
            //  250    268    548    558    Any
            //  268    274    306    316    Ljava/io/IOException;
            //  268    274    499    504    Any
            //  278    303    306    316    Ljava/io/IOException;
            //  278    303    499    504    Any
            //  322    327    548    558    Any
            //  341    345    532    540    Ljava/io/IOException;
            //  347    353    306    316    Ljava/io/IOException;
            //  347    353    499    504    Any
            //  370    375    377    385    Ljava/io/IOException;
            //  385    416    306    316    Ljava/io/IOException;
            //  385    416    499    504    Any
            //  416    455    489    499    Ljava/lang/Exception;
            //  416    455    306    316    Ljava/io/IOException;
            //  416    455    499    504    Any
            //  468    473    524    532    Ljava/io/IOException;
            //  475    486    489    499    Ljava/lang/Exception;
            //  475    486    306    316    Ljava/io/IOException;
            //  475    486    499    504    Any
            //  491    496    306    316    Ljava/io/IOException;
            //  491    496    499    504    Any
            //  517    522    540    548    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0345:
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
        
        protected void onPostExecute(String create) {
            if (!this.success) {
                Toast.makeText((Context)OnlineActivity.this, (CharSequence)"Something went wrong.", 0).show();
                return;
            }
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)OnlineActivity.this);
            alertDialog$Builder.setTitle((CharSequence)"Online ELO Leaderboard").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                }
            }).setView(OnlineActivity.this.getLayoutInflater().inflate(2130968660, (ViewGroup)null));
            create = (String)alertDialog$Builder.create();
            ((AlertDialog)create).show();
            final ArrayList<String> list = new ArrayList<String>();
            int i = 0;
        Label_0183_Outer:
            while (i < 50) {
                while (true) {
                    try {
                        final JSONObject jsonObject = new JSONObject(this.leaderboardJSON.get("" + i).toString());
                        list.add(i + 1 + "," + jsonObject.get("name") + "," + jsonObject.get("elo"));
                        ++i;
                        continue Label_0183_Outer;
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                        continue;
                    }
                    break;
                }
                break;
            }
            ((ListView)((AlertDialog)create).findViewById(2131558679)).setAdapter((ListAdapter)new TeamRankingsListArrayAdapter((Context)OnlineActivity.this, list, OnlineActivity.this.userTeam.name));
        }
    }
    
    class GetTeamOnline extends AsyncTask<String, String, String>
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
            //     3: astore          10
            //     5: aconst_null    
            //     6: astore          7
            //     8: aconst_null    
            //     9: astore          9
            //    11: aconst_null    
            //    12: astore          6
            //    14: aconst_null    
            //    15: astore          8
            //    17: aload           6
            //    19: astore          4
            //    21: aload           9
            //    23: astore_1       
            //    24: aload           7
            //    26: astore          5
            //    28: aload_0        
            //    29: getfield        com/achijones/profootballcoach/OnlineActivity$GetTeamOnline.this$0:Lcom/achijones/profootballcoach/OnlineActivity;
            //    32: getfield        com/achijones/profootballcoach/OnlineActivity.gettingRandTeam:Z
            //    35: ifeq            276
            //    38: aload           6
            //    40: astore          4
            //    42: aload           9
            //    44: astore_1       
            //    45: aload           7
            //    47: astore          5
            //    49: new             Ljava/net/URL;
            //    52: dup            
            //    53: ldc             "http://coachapps.io/pfc/getRandomTeam.php"
            //    55: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
            //    58: astore_3       
            //    59: aload           6
            //    61: astore          4
            //    63: aload           9
            //    65: astore_1       
            //    66: aload           7
            //    68: astore          5
            //    70: aload_3        
            //    71: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
            //    74: checkcast       Ljava/net/HttpURLConnection;
            //    77: astore_3       
            //    78: aload           6
            //    80: astore          4
            //    82: aload_3        
            //    83: astore_1       
            //    84: aload_3        
            //    85: astore          5
            //    87: aload_3        
            //    88: iconst_1       
            //    89: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
            //    92: aload           6
            //    94: astore          4
            //    96: aload_3        
            //    97: astore_1       
            //    98: aload_3        
            //    99: astore          5
            //   101: aload_3        
            //   102: ldc             "POST"
            //   104: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
            //   107: aload           6
            //   109: astore          4
            //   111: aload_3        
            //   112: astore_1       
            //   113: aload_3        
            //   114: astore          5
            //   116: aload_3        
            //   117: ldc             "Content-Type"
            //   119: ldc             "application/json"
            //   121: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
            //   124: aload           6
            //   126: astore          4
            //   128: aload_3        
            //   129: astore_1       
            //   130: aload_3        
            //   131: astore          5
            //   133: aload_3        
            //   134: ldc             "Accept"
            //   136: ldc             "application/json"
            //   138: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
            //   141: aload           6
            //   143: astore          4
            //   145: aload_3        
            //   146: astore_1       
            //   147: aload_3        
            //   148: astore          5
            //   150: new             Ljava/io/BufferedWriter;
            //   153: dup            
            //   154: new             Ljava/io/OutputStreamWriter;
            //   157: dup            
            //   158: aload_3        
            //   159: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
            //   162: ldc             "UTF-8"
            //   164: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
            //   167: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
            //   170: astore          7
            //   172: aload           6
            //   174: astore          4
            //   176: aload_3        
            //   177: astore_1       
            //   178: aload_3        
            //   179: astore          5
            //   181: aload           7
            //   183: aload           10
            //   185: invokevirtual   java/io/Writer.write:(Ljava/lang/String;)V
            //   188: aload           6
            //   190: astore          4
            //   192: aload_3        
            //   193: astore_1       
            //   194: aload_3        
            //   195: astore          5
            //   197: aload           7
            //   199: invokevirtual   java/io/Writer.flush:()V
            //   202: aload           6
            //   204: astore          4
            //   206: aload_3        
            //   207: astore_1       
            //   208: aload_3        
            //   209: astore          5
            //   211: aload           7
            //   213: invokevirtual   java/io/Writer.close:()V
            //   216: aload           6
            //   218: astore          4
            //   220: aload_3        
            //   221: astore_1       
            //   222: aload_3        
            //   223: astore          5
            //   225: aload_3        
            //   226: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
            //   229: astore          9
            //   231: aload           6
            //   233: astore          4
            //   235: aload_3        
            //   236: astore_1       
            //   237: aload_3        
            //   238: astore          5
            //   240: new             Ljava/lang/StringBuffer;
            //   243: dup            
            //   244: invokespecial   java/lang/StringBuffer.<init>:()V
            //   247: astore          7
            //   249: aload           9
            //   251: ifnonnull       307
            //   254: aload_3        
            //   255: ifnull          262
            //   258: aload_3        
            //   259: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   262: iconst_0       
            //   263: ifeq            274
            //   266: new             Ljava/lang/NullPointerException;
            //   269: dup            
            //   270: invokespecial   java/lang/NullPointerException.<init>:()V
            //   273: athrow         
            //   274: aconst_null    
            //   275: areturn        
            //   276: aload           6
            //   278: astore          4
            //   280: aload           9
            //   282: astore_1       
            //   283: aload           7
            //   285: astore          5
            //   287: new             Ljava/net/URL;
            //   290: dup            
            //   291: ldc             "http://coachapps.io/pfc/getTeam.php"
            //   293: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
            //   296: astore_3       
            //   297: goto            59
            //   300: astore_1       
            //   301: aload_1        
            //   302: invokevirtual   java/io/IOException.printStackTrace:()V
            //   305: aconst_null    
            //   306: areturn        
            //   307: aload           6
            //   309: astore          4
            //   311: aload_3        
            //   312: astore_1       
            //   313: aload_3        
            //   314: astore          5
            //   316: new             Ljava/io/BufferedReader;
            //   319: dup            
            //   320: new             Ljava/io/InputStreamReader;
            //   323: dup            
            //   324: aload           9
            //   326: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
            //   329: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
            //   332: astore          6
            //   334: aload           6
            //   336: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
            //   339: astore_1       
            //   340: aload_1        
            //   341: ifnull          405
            //   344: aload           7
            //   346: new             Ljava/lang/StringBuilder;
            //   349: dup            
            //   350: invokespecial   java/lang/StringBuilder.<init>:()V
            //   353: aload_1        
            //   354: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   357: ldc             "\n"
            //   359: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   362: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   365: invokevirtual   java/lang/StringBuffer.append:(Ljava/lang/String;)Ljava/lang/StringBuffer;
            //   368: pop            
            //   369: goto            334
            //   372: astore          7
            //   374: aload           6
            //   376: astore          4
            //   378: aload_3        
            //   379: astore_1       
            //   380: aload           7
            //   382: invokevirtual   java/io/IOException.printStackTrace:()V
            //   385: aload_3        
            //   386: ifnull          393
            //   389: aload_3        
            //   390: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   393: aload           6
            //   395: ifnull          403
            //   398: aload           6
            //   400: invokevirtual   java/io/BufferedReader.close:()V
            //   403: aconst_null    
            //   404: areturn        
            //   405: aload           7
            //   407: invokevirtual   java/lang/StringBuffer.length:()I
            //   410: istore_2       
            //   411: iload_2        
            //   412: ifne            443
            //   415: aload_3        
            //   416: ifnull          423
            //   419: aload_3        
            //   420: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   423: aload           6
            //   425: ifnull          433
            //   428: aload           6
            //   430: invokevirtual   java/io/BufferedReader.close:()V
            //   433: aconst_null    
            //   434: areturn        
            //   435: astore_1       
            //   436: aload_1        
            //   437: invokevirtual   java/io/IOException.printStackTrace:()V
            //   440: goto            433
            //   443: aload           7
            //   445: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
            //   448: astore_1       
            //   449: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   452: new             Ljava/lang/StringBuilder;
            //   455: dup            
            //   456: invokespecial   java/lang/StringBuilder.<init>:()V
            //   459: ldc             "\nJsonResponse = "
            //   461: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   464: aload_1        
            //   465: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   468: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   471: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   474: new             Lorg/json/JSONObject;
            //   477: dup            
            //   478: aload_1        
            //   479: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
            //   482: astore          4
            //   484: aload           4
            //   486: ldc             "success"
            //   488: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
            //   491: checkcast       Ljava/lang/Integer;
            //   494: invokevirtual   java/lang/Integer.intValue:()I
            //   497: ifne            533
            //   500: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   503: ldc             "Something went wrong when getting a team."
            //   505: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   508: aload_0        
            //   509: iconst_0       
            //   510: putfield        com/achijones/profootballcoach/OnlineActivity$GetTeamOnline.success:Z
            //   513: aload_3        
            //   514: ifnull          521
            //   517: aload_3        
            //   518: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   521: aload           6
            //   523: ifnull          531
            //   526: aload           6
            //   528: invokevirtual   java/io/BufferedReader.close:()V
            //   531: aload_1        
            //   532: areturn        
            //   533: aload_0        
            //   534: iconst_1       
            //   535: putfield        com/achijones/profootballcoach/OnlineActivity$GetTeamOnline.success:Z
            //   538: aload_0        
            //   539: getfield        com/achijones/profootballcoach/OnlineActivity$GetTeamOnline.this$0:Lcom/achijones/profootballcoach/OnlineActivity;
            //   542: getfield        com/achijones/profootballcoach/OnlineActivity.gettingUserTeam:Z
            //   545: ifeq            602
            //   548: aload_0        
            //   549: getfield        com/achijones/profootballcoach/OnlineActivity$GetTeamOnline.this$0:Lcom/achijones/profootballcoach/OnlineActivity;
            //   552: new             LPFCpack/Team;
            //   555: dup            
            //   556: aload           4
            //   558: invokespecial   PFCpack/Team.<init>:(Lorg/json/JSONObject;)V
            //   561: putfield        com/achijones/profootballcoach/OnlineActivity.userTeam:LPFCpack/Team;
            //   564: goto            513
            //   567: astore          4
            //   569: aload           4
            //   571: invokevirtual   java/lang/Exception.printStackTrace:()V
            //   574: goto            513
            //   577: astore_1       
            //   578: aload           6
            //   580: astore          4
            //   582: aload_3        
            //   583: ifnull          590
            //   586: aload_3        
            //   587: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   590: aload           4
            //   592: ifnull          600
            //   595: aload           4
            //   597: invokevirtual   java/io/BufferedReader.close:()V
            //   600: aload_1        
            //   601: athrow         
            //   602: aload_0        
            //   603: getfield        com/achijones/profootballcoach/OnlineActivity$GetTeamOnline.this$0:Lcom/achijones/profootballcoach/OnlineActivity;
            //   606: new             LPFCpack/Team;
            //   609: dup            
            //   610: aload           4
            //   612: invokespecial   PFCpack/Team.<init>:(Lorg/json/JSONObject;)V
            //   615: putfield        com/achijones/profootballcoach/OnlineActivity.oppTeam:LPFCpack/Team;
            //   618: goto            513
            //   621: astore_3       
            //   622: aload_3        
            //   623: invokevirtual   java/io/IOException.printStackTrace:()V
            //   626: goto            531
            //   629: astore_1       
            //   630: aload_1        
            //   631: invokevirtual   java/io/IOException.printStackTrace:()V
            //   634: goto            403
            //   637: astore_3       
            //   638: aload_3        
            //   639: invokevirtual   java/io/IOException.printStackTrace:()V
            //   642: goto            600
            //   645: astore          5
            //   647: aload_1        
            //   648: astore_3       
            //   649: aload           5
            //   651: astore_1       
            //   652: goto            582
            //   655: astore          7
            //   657: aload           8
            //   659: astore          6
            //   661: aload           5
            //   663: astore_3       
            //   664: goto            374
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  28     38     655    667    Ljava/io/IOException;
            //  28     38     645    655    Any
            //  49     59     655    667    Ljava/io/IOException;
            //  49     59     645    655    Any
            //  70     78     655    667    Ljava/io/IOException;
            //  70     78     645    655    Any
            //  87     92     655    667    Ljava/io/IOException;
            //  87     92     645    655    Any
            //  101    107    655    667    Ljava/io/IOException;
            //  101    107    645    655    Any
            //  116    124    655    667    Ljava/io/IOException;
            //  116    124    645    655    Any
            //  133    141    655    667    Ljava/io/IOException;
            //  133    141    645    655    Any
            //  150    172    655    667    Ljava/io/IOException;
            //  150    172    645    655    Any
            //  181    188    655    667    Ljava/io/IOException;
            //  181    188    645    655    Any
            //  197    202    655    667    Ljava/io/IOException;
            //  197    202    645    655    Any
            //  211    216    655    667    Ljava/io/IOException;
            //  211    216    645    655    Any
            //  225    231    655    667    Ljava/io/IOException;
            //  225    231    645    655    Any
            //  240    249    655    667    Ljava/io/IOException;
            //  240    249    645    655    Any
            //  266    274    300    307    Ljava/io/IOException;
            //  287    297    655    667    Ljava/io/IOException;
            //  287    297    645    655    Any
            //  316    334    655    667    Ljava/io/IOException;
            //  316    334    645    655    Any
            //  334    340    372    374    Ljava/io/IOException;
            //  334    340    577    582    Any
            //  344    369    372    374    Ljava/io/IOException;
            //  344    369    577    582    Any
            //  380    385    645    655    Any
            //  398    403    629    637    Ljava/io/IOException;
            //  405    411    372    374    Ljava/io/IOException;
            //  405    411    577    582    Any
            //  428    433    435    443    Ljava/io/IOException;
            //  443    474    372    374    Ljava/io/IOException;
            //  443    474    577    582    Any
            //  474    513    567    577    Ljava/lang/Exception;
            //  474    513    372    374    Ljava/io/IOException;
            //  474    513    577    582    Any
            //  526    531    621    629    Ljava/io/IOException;
            //  533    564    567    577    Ljava/lang/Exception;
            //  533    564    372    374    Ljava/io/IOException;
            //  533    564    577    582    Any
            //  569    574    372    374    Ljava/io/IOException;
            //  569    574    577    582    Any
            //  595    600    637    645    Ljava/io/IOException;
            //  602    618    567    577    Ljava/lang/Exception;
            //  602    618    372    374    Ljava/io/IOException;
            //  602    618    577    582    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0403:
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
            if (!this.success) {
                Toast.makeText((Context)OnlineActivity.this, (CharSequence)"Could not find team.", 0).show();
                OnlineActivity.this.playWeekButton.setText((CharSequence)"Schedule Ranked Game");
                return;
            }
            if (!OnlineActivity.this.gettingUserTeam) {
                Toast.makeText((Context)OnlineActivity.this, (CharSequence)("Found opponent: " + OnlineActivity.this.oppTeam.name + "!"), 0).show();
                OnlineActivity.this.scheduleGame(OnlineActivity.this.gettingRandTeam);
            }
            else if (OnlineActivity.this.userTeam.wins == 0 && OnlineActivity.this.userTeam.losses == 0) {
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)OnlineActivity.this);
                alertDialog$Builder.setTitle((CharSequence)"Welcome to Online Play!").setMessage((CharSequence)"Here, you can compete with other teams from around the world!\n\n This multiplayer is asynchronous, which means that you can play against other teams at your own leisure. You can schedule teams to play against, and other players will also schedule your team to play against.\n\n By pressing Schedule Ranked Game, a team will be randomly selected for you to play against. Then you can compare the two teams by viewing their rosters, team stats, and more.\n\n When you are ready, press Play Week to play a game against them. Since you are playing from your device, you will have home field advantage. Each random game is considered Ranked, which means it will affect your ELO ranking.\n\n You can also schedule custom games against any team you like by clicking Custom Game in the menu. These games won't affect ELO.\n\n Be sure to set your default strategy by using the menu as well. This is the strategy that your team will use when other teams play against yours.\n\n Have fun and good luck!").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                    }
                });
                final AlertDialog create = alertDialog$Builder.create();
                create.show();
                ((TextView)create.findViewById(16908299)).setTextSize(2, 14.0f);
            }
            OnlineActivity.this.updateUI();
            OnlineActivity.this.updatePlayerStats(false);
            OnlineActivity.this.examineTeamSpinner.setSelection(1);
        }
    }
    
    class UpdateTeamOnline extends AsyncTask<String, String, String>
    {
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
            //    24: ldc             "http://coachapps.io/pfc/updateTeam.php"
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
            //   427: ifne            438
            //   430: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   433: ldc             "Something went wrong when updating a team."
            //   435: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   438: aload_1        
            //   439: ifnull          446
            //   442: aload_1        
            //   443: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   446: aload           6
            //   448: ifnull          456
            //   451: aload           6
            //   453: invokevirtual   java/io/BufferedReader.close:()V
            //   456: aload_3        
            //   457: areturn        
            //   458: astore          4
            //   460: aload           4
            //   462: invokevirtual   java/lang/Exception.printStackTrace:()V
            //   465: goto            438
            //   468: astore_3       
            //   469: aload           6
            //   471: astore          4
            //   473: aload_1        
            //   474: ifnull          481
            //   477: aload_1        
            //   478: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   481: aload           4
            //   483: ifnull          491
            //   486: aload           4
            //   488: invokevirtual   java/io/BufferedReader.close:()V
            //   491: aload_3        
            //   492: athrow         
            //   493: astore_1       
            //   494: aload_1        
            //   495: invokevirtual   java/io/IOException.printStackTrace:()V
            //   498: goto            456
            //   501: astore_1       
            //   502: aload_1        
            //   503: invokevirtual   java/io/IOException.printStackTrace:()V
            //   506: goto            337
            //   509: astore_1       
            //   510: aload_1        
            //   511: invokevirtual   java/io/IOException.printStackTrace:()V
            //   514: goto            491
            //   517: astore          5
            //   519: aload_3        
            //   520: astore_1       
            //   521: aload           5
            //   523: astore_3       
            //   524: goto            473
            //   527: astore          7
            //   529: aload           8
            //   531: astore          6
            //   533: aload           5
            //   535: astore_1       
            //   536: goto            308
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  20     36     527    539    Ljava/io/IOException;
            //  20     36     517    527    Any
            //  45     50     527    539    Ljava/io/IOException;
            //  45     50     517    527    Any
            //  59     65     527    539    Ljava/io/IOException;
            //  59     65     517    527    Any
            //  74     82     527    539    Ljava/io/IOException;
            //  74     82     517    527    Any
            //  91     99     527    539    Ljava/io/IOException;
            //  91     99     517    527    Any
            //  108    130    527    539    Ljava/io/IOException;
            //  108    130    517    527    Any
            //  139    146    527    539    Ljava/io/IOException;
            //  139    146    517    527    Any
            //  155    160    527    539    Ljava/io/IOException;
            //  155    160    517    527    Any
            //  169    174    527    539    Ljava/io/IOException;
            //  169    174    517    527    Any
            //  183    189    527    539    Ljava/io/IOException;
            //  183    189    517    527    Any
            //  198    207    527    539    Ljava/io/IOException;
            //  198    207    517    527    Any
            //  224    232    234    241    Ljava/io/IOException;
            //  250    268    527    539    Ljava/io/IOException;
            //  250    268    517    527    Any
            //  268    274    306    308    Ljava/io/IOException;
            //  268    274    468    473    Any
            //  278    303    306    308    Ljava/io/IOException;
            //  278    303    468    473    Any
            //  314    319    517    527    Any
            //  332    337    501    509    Ljava/io/IOException;
            //  339    345    306    308    Ljava/io/IOException;
            //  339    345    468    473    Any
            //  362    367    369    377    Ljava/io/IOException;
            //  377    408    306    308    Ljava/io/IOException;
            //  377    408    468    473    Any
            //  408    438    458    468    Ljava/lang/Exception;
            //  408    438    306    308    Ljava/io/IOException;
            //  408    438    468    473    Any
            //  451    456    493    501    Ljava/io/IOException;
            //  460    465    306    308    Ljava/io/IOException;
            //  460    465    468    473    Any
            //  486    491    509    517    Ljava/io/IOException;
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
    }
    
    class UpdateTeamStratsOnline extends AsyncTask<String, String, String>
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
            //     3: astore          8
            //     5: aconst_null    
            //     6: astore          5
            //     8: aconst_null    
            //     9: astore_3       
            //    10: aconst_null    
            //    11: astore          6
            //    13: aconst_null    
            //    14: astore          7
            //    16: aload           6
            //    18: astore          4
            //    20: new             Ljava/net/URL;
            //    23: dup            
            //    24: ldc             "http://coachapps.io/pfc/updateTeamStrats.php"
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
            //   141: aload           8
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
            //   205: astore          8
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
            //   275: ifnull          347
            //   278: aload           8
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
            //   306: astore_3       
            //   307: aload_1        
            //   308: astore          5
            //   310: aload           6
            //   312: astore_1       
            //   313: aload_3        
            //   314: astore          6
            //   316: aload_1        
            //   317: astore          4
            //   319: aload           5
            //   321: astore_3       
            //   322: aload           6
            //   324: invokevirtual   java/io/IOException.printStackTrace:()V
            //   327: aload           5
            //   329: ifnull          337
            //   332: aload           5
            //   334: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   337: aload_1        
            //   338: ifnull          345
            //   341: aload_1        
            //   342: invokevirtual   java/io/BufferedReader.close:()V
            //   345: aconst_null    
            //   346: areturn        
            //   347: aload           8
            //   349: invokevirtual   java/lang/StringBuffer.length:()I
            //   352: istore_2       
            //   353: iload_2        
            //   354: ifne            385
            //   357: aload_1        
            //   358: ifnull          365
            //   361: aload_1        
            //   362: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   365: aload           6
            //   367: ifnull          375
            //   370: aload           6
            //   372: invokevirtual   java/io/BufferedReader.close:()V
            //   375: aconst_null    
            //   376: areturn        
            //   377: astore_1       
            //   378: aload_1        
            //   379: invokevirtual   java/io/IOException.printStackTrace:()V
            //   382: goto            375
            //   385: aload           8
            //   387: invokevirtual   java/lang/StringBuffer.toString:()Ljava/lang/String;
            //   390: astore_3       
            //   391: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   394: new             Ljava/lang/StringBuilder;
            //   397: dup            
            //   398: invokespecial   java/lang/StringBuilder.<init>:()V
            //   401: ldc             "\nJsonResponse = "
            //   403: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   406: aload_3        
            //   407: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   410: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   413: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   416: new             Lorg/json/JSONObject;
            //   419: dup            
            //   420: aload_3        
            //   421: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
            //   424: ldc             "success"
            //   426: invokevirtual   org/json/JSONObject.get:(Ljava/lang/String;)Ljava/lang/Object;
            //   429: checkcast       Ljava/lang/Integer;
            //   432: invokevirtual   java/lang/Integer.intValue:()I
            //   435: ifne            471
            //   438: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   441: ldc             "Something went wrong when updating a team."
            //   443: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   446: aload_0        
            //   447: iconst_0       
            //   448: putfield        com/achijones/profootballcoach/OnlineActivity$UpdateTeamStratsOnline.success:Z
            //   451: aload_1        
            //   452: ifnull          459
            //   455: aload_1        
            //   456: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   459: aload           6
            //   461: ifnull          469
            //   464: aload           6
            //   466: invokevirtual   java/io/BufferedReader.close:()V
            //   469: aload_3        
            //   470: areturn        
            //   471: aload_0        
            //   472: iconst_1       
            //   473: putfield        com/achijones/profootballcoach/OnlineActivity$UpdateTeamStratsOnline.success:Z
            //   476: goto            451
            //   479: astore          4
            //   481: aload           4
            //   483: invokevirtual   java/lang/Exception.printStackTrace:()V
            //   486: goto            451
            //   489: astore_3       
            //   490: aload           6
            //   492: astore          4
            //   494: aload_1        
            //   495: ifnull          502
            //   498: aload_1        
            //   499: invokevirtual   java/net/HttpURLConnection.disconnect:()V
            //   502: aload           4
            //   504: ifnull          512
            //   507: aload           4
            //   509: invokevirtual   java/io/BufferedReader.close:()V
            //   512: aload_3        
            //   513: athrow         
            //   514: astore_1       
            //   515: aload_1        
            //   516: invokevirtual   java/io/IOException.printStackTrace:()V
            //   519: goto            469
            //   522: astore_1       
            //   523: aload_1        
            //   524: invokevirtual   java/io/IOException.printStackTrace:()V
            //   527: goto            345
            //   530: astore_1       
            //   531: aload_1        
            //   532: invokevirtual   java/io/IOException.printStackTrace:()V
            //   535: goto            512
            //   538: astore          5
            //   540: aload_3        
            //   541: astore_1       
            //   542: aload           5
            //   544: astore_3       
            //   545: goto            494
            //   548: astore          6
            //   550: aload           7
            //   552: astore_1       
            //   553: goto            316
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  20     36     548    556    Ljava/io/IOException;
            //  20     36     538    548    Any
            //  45     50     548    556    Ljava/io/IOException;
            //  45     50     538    548    Any
            //  59     65     548    556    Ljava/io/IOException;
            //  59     65     538    548    Any
            //  74     82     548    556    Ljava/io/IOException;
            //  74     82     538    548    Any
            //  91     99     548    556    Ljava/io/IOException;
            //  91     99     538    548    Any
            //  108    130    548    556    Ljava/io/IOException;
            //  108    130    538    548    Any
            //  139    146    548    556    Ljava/io/IOException;
            //  139    146    538    548    Any
            //  155    160    548    556    Ljava/io/IOException;
            //  155    160    538    548    Any
            //  169    174    548    556    Ljava/io/IOException;
            //  169    174    538    548    Any
            //  183    189    548    556    Ljava/io/IOException;
            //  183    189    538    548    Any
            //  198    207    548    556    Ljava/io/IOException;
            //  198    207    538    548    Any
            //  224    232    234    241    Ljava/io/IOException;
            //  250    268    548    556    Ljava/io/IOException;
            //  250    268    538    548    Any
            //  268    274    306    316    Ljava/io/IOException;
            //  268    274    489    494    Any
            //  278    303    306    316    Ljava/io/IOException;
            //  278    303    489    494    Any
            //  322    327    538    548    Any
            //  341    345    522    530    Ljava/io/IOException;
            //  347    353    306    316    Ljava/io/IOException;
            //  347    353    489    494    Any
            //  370    375    377    385    Ljava/io/IOException;
            //  385    416    306    316    Ljava/io/IOException;
            //  385    416    489    494    Any
            //  416    451    479    489    Ljava/lang/Exception;
            //  416    451    306    316    Ljava/io/IOException;
            //  416    451    489    494    Any
            //  464    469    514    522    Ljava/io/IOException;
            //  471    476    479    489    Ljava/lang/Exception;
            //  471    476    306    316    Ljava/io/IOException;
            //  471    476    489    494    Any
            //  481    486    306    316    Ljava/io/IOException;
            //  481    486    489    494    Any
            //  507    512    530    538    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0345:
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
                Toast.makeText((Context)OnlineActivity.this, (CharSequence)"Successfully updated strategies.", 0).show();
                return;
            }
            Toast.makeText((Context)OnlineActivity.this, (CharSequence)"Something went wrong when updating strategies.", 0).show();
        }
    }
}
