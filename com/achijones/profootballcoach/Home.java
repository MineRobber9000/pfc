// 
// Decompiled by Procyon v0.5.36
// 

package com.achijones.profootballcoach;

import android.os.AsyncTask;
import android.net.Uri;
import java.net.URL;
import android.widget.EditText;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.os.Bundle;
import android.app.AlertDialog;
import android.widget.TextView;
import android.widget.ListAdapter;
import android.widget.Toast;
import android.content.Intent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.content.Context;
import android.app.AlertDialog$Builder;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import android.support.v7.app.AppCompatActivity;

public class Home extends AppCompatActivity
{
    File rosterFile;
    int saveFileNum;
    
    private String[] getOnlineTeams() {
        final String[] array = new String[5];
        int i = 0;
    Label_0086_Outer:
        while (i < 5) {
            final File file = new File(this.getFilesDir(), "onlineTeam" + i + ".cfb");
            while (true) {
                Label_0129: {
                    if (!file.exists()) {
                        break Label_0129;
                    }
                    try {
                        final String line = new BufferedReader(new FileReader(file)).readLine();
                        array[i] = line.substring(0, line.length());
                        ++i;
                        continue Label_0086_Outer;
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
    Label_0090_Outer:
        while (i < 10) {
            final File file = new File(this.getFilesDir(), "saveFile" + i + ".cfb");
            while (true) {
                Label_0133: {
                    if (!file.exists()) {
                        break Label_0133;
                    }
                    try {
                        final String line = new BufferedReader(new FileReader(file)).readLine();
                        array[i] = line.substring(0, line.length() - 1);
                        ++i;
                        continue Label_0090_Outer;
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
    
    private void goOnline() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Choose Online Team:");
        final String[] onlineTeams = this.getOnlineTeams();
        alertDialog$Builder.setAdapter((ListAdapter)new SaveFilesListArrayAdapter((Context)this, onlineTeams), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                if (!onlineTeams[n].equals("EMPTY")) {
                    final Intent intent = new Intent((Context)Home.this, (Class)OnlineActivity.class);
                    intent.putExtra("ONLINE_TEAM", onlineTeams[n]);
                    Home.this.startActivity(intent);
                    return;
                }
                Toast.makeText((Context)Home.this, (CharSequence)"Team doesn't exist!", 0).show();
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        alertDialog$Builder.create().show();
    }
    
    private void loadLeague() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Choose File to Load:");
        final String[] saveFileInfos = this.getSaveFileInfos();
        alertDialog$Builder.setAdapter((ListAdapter)new SaveFilesListArrayAdapter((Context)this, saveFileInfos), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                if (!saveFileInfos[n].equals("EMPTY")) {
                    final Intent intent = new Intent((Context)Home.this, (Class)MainActivity.class);
                    intent.putExtra("SAVE_FILE", "LOAD,saveFile" + n + ".cfb,saveFile" + n + "_teams.cfb,saveFile" + n + "_schedules.cfb,saveFile" + n + "_teamhist.cfb");
                    Home.this.startActivity(intent);
                    return;
                }
                Toast.makeText((Context)Home.this, (CharSequence)"Cannot load empty file!", 0).show();
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
            }
        });
        alertDialog$Builder.create().show();
    }
    
    private void newLeague() {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Choose File to Use:");
        final String[] saveFileInfos = this.getSaveFileInfos();
        alertDialog$Builder.setAdapter((ListAdapter)new SaveFilesListArrayAdapter((Context)this, saveFileInfos), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                if (saveFileInfos[n].equals("EMPTY")) {
                    Home.this.beginNewLeagueDialog(n);
                    return;
                }
                final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)Home.this);
                alertDialog$Builder.setMessage((CharSequence)("Are you sure you want to use this save file? It will overwrite the league currently saved.\n\n" + saveFileInfos[n])).setPositiveButton((CharSequence)"Yes, Overwrite", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        Home.this.beginNewLeagueDialog(n);
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
    
    public void beginNewLeagueDialog(final int n) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setMessage((CharSequence)"Do you want to generate a league from scratch or load from an online roster file?").setPositiveButton((CharSequence)"Generate", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                final Intent intent = new Intent((Context)Home.this, (Class)MainActivity.class);
                intent.putExtra("SAVE_FILE", "NEW,saveFile" + n + ".cfb,saveFile" + n + "_teams.cfb,saveFile" + n + "_schedules.cfb,saveFile" + n + "_teamhist.cfb");
                Home.this.startActivity(intent);
            }
        }).setNegativeButton((CharSequence)"Load from online", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                Home.this.saveFileNum = n;
                Home.this.showRosterURLDialog();
            }
        });
        final AlertDialog create = alertDialog$Builder.create();
        create.show();
        ((TextView)create.findViewById(16908299)).setTextSize(2, 14.0f);
    }
    
    public void newLeagueRosterFile() {
        final Intent intent = new Intent((Context)this, (Class)MainActivity.class);
        intent.putExtra("SAVE_FILE", "ROSTER,saveFile" + this.saveFileNum + ".cfb,saveFile" + this.saveFileNum + "_teams.cfb,saveFile" + this.saveFileNum + "_schedules.cfb,saveFile" + this.saveFileNum + "_teamhist.cfb");
        this.startActivity(intent);
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(2130968603);
        final ImageView imageView = (ImageView)this.findViewById(2131558538);
        while (true) {
            try {
                imageView.setImageResource(2130837612);
                ((Button)this.findViewById(2131558539)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        Home.this.newLeague();
                    }
                });
                ((Button)this.findViewById(2131558540)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        Home.this.loadLeague();
                    }
                });
                ((Button)this.findViewById(2131558541)).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                    public void onClick(final View view) {
                        Home.this.goOnline();
                    }
                });
            }
            catch (OutOfMemoryError outOfMemoryError) {
                Toast.makeText((Context)this, (CharSequence)"Error displaying logo", 0).show();
                continue;
            }
            break;
        }
    }
    
    public void runTestPlayerProgression() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: iconst_2       
        //     4: newarray        I
        //     6: dup            
        //     7: iconst_0       
        //     8: bipush          10
        //    10: iastore        
        //    11: dup            
        //    12: iconst_1       
        //    13: bipush          80
        //    15: iastore        
        //    16: invokestatic    java/lang/reflect/Array.newInstance:(Ljava/lang/Class;[I)Ljava/lang/Object;
        //    19: checkcast       [[I
        //    22: astore          5
        //    24: iconst_0       
        //    25: istore_2       
        //    26: iload_2        
        //    27: sipush          10000
        //    30: if_icmpge       128
        //    33: new             LPFCpack/PlayerS;
        //    36: dup            
        //    37: ldc_w           "jonny jon"
        //    40: sipush          2016
        //    43: invokespecial   PFCpack/PlayerS.<init>:(Ljava/lang/String;I)V
        //    46: astore          6
        //    48: iconst_0       
        //    49: istore_1       
        //    50: iload_1        
        //    51: bipush          7
        //    53: if_icmpge       68
        //    56: aload           6
        //    58: invokevirtual   PFCpack/PlayerS.advanceSeasonRatingsAge:()V
        //    61: iload_1        
        //    62: iconst_1       
        //    63: iadd           
        //    64: istore_1       
        //    65: goto            50
        //    68: aload           6
        //    70: invokevirtual   PFCpack/PlayerS.getRatOvr:()I
        //    73: bipush          50
        //    75: isub           
        //    76: istore_3       
        //    77: iload_3        
        //    78: istore_1       
        //    79: iload_3        
        //    80: bipush          79
        //    82: if_icmple       88
        //    85: bipush          79
        //    87: istore_1       
        //    88: iload_1        
        //    89: istore_3       
        //    90: iload_1        
        //    91: ifge            96
        //    94: iconst_0       
        //    95: istore_3       
        //    96: aload           5
        //    98: aload           6
        //   100: invokevirtual   PFCpack/PlayerS.getRatPot:()I
        //   103: bipush          50
        //   105: isub           
        //   106: iconst_5       
        //   107: idiv           
        //   108: aaload         
        //   109: astore          6
        //   111: aload           6
        //   113: iload_3        
        //   114: aload           6
        //   116: iload_3        
        //   117: iaload         
        //   118: iconst_1       
        //   119: iadd           
        //   120: iastore        
        //   121: iload_2        
        //   122: iconst_1       
        //   123: iadd           
        //   124: istore_2       
        //   125: goto            26
        //   128: new             Ljava/lang/StringBuilder;
        //   131: dup            
        //   132: invokespecial   java/lang/StringBuilder.<init>:()V
        //   135: astore          6
        //   137: aload           6
        //   139: ldc_w           "Player Progression Testing results:"
        //   142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   145: pop            
        //   146: iconst_0       
        //   147: istore_1       
        //   148: iload_1        
        //   149: bipush          10
        //   151: if_icmpge       275
        //   154: aload           6
        //   156: new             Ljava/lang/StringBuilder;
        //   159: dup            
        //   160: invokespecial   java/lang/StringBuilder.<init>:()V
        //   163: ldc_w           "\nPOT: "
        //   166: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   169: iload_1        
        //   170: iconst_5       
        //   171: imul           
        //   172: bipush          50
        //   174: iadd           
        //   175: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   178: ldc_w           ":"
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   187: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   190: pop            
        //   191: iconst_0       
        //   192: istore_2       
        //   193: iload_2        
        //   194: bipush          12
        //   196: if_icmpge       259
        //   199: aload           6
        //   201: ldc_w           "\t"
        //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   207: pop            
        //   208: iconst_0       
        //   209: istore_3       
        //   210: iconst_0       
        //   211: istore          4
        //   213: iload           4
        //   215: iconst_5       
        //   216: if_icmpge       245
        //   219: iload_3        
        //   220: aload           5
        //   222: iload_1        
        //   223: aaload         
        //   224: iload_2        
        //   225: iconst_5       
        //   226: imul           
        //   227: iload           4
        //   229: iadd           
        //   230: bipush          20
        //   232: iadd           
        //   233: iaload         
        //   234: iadd           
        //   235: istore_3       
        //   236: iload           4
        //   238: iconst_1       
        //   239: iadd           
        //   240: istore          4
        //   242: goto            213
        //   245: aload           6
        //   247: iload_3        
        //   248: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   251: pop            
        //   252: iload_2        
        //   253: iconst_1       
        //   254: iadd           
        //   255: istore_2       
        //   256: goto            193
        //   259: aload           6
        //   261: ldc_w           "\n\n"
        //   264: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   267: pop            
        //   268: iload_1        
        //   269: iconst_1       
        //   270: iadd           
        //   271: istore_1       
        //   272: goto            148
        //   275: new             Ljava/io/BufferedWriter;
        //   278: dup            
        //   279: new             Ljava/io/OutputStreamWriter;
        //   282: dup            
        //   283: new             Ljava/io/FileOutputStream;
        //   286: dup            
        //   287: new             Ljava/io/File;
        //   290: dup            
        //   291: aload_0        
        //   292: invokevirtual   com/achijones/profootballcoach/Home.getFilesDir:()Ljava/io/File;
        //   295: ldc_w           "PlayerProgression.txt"
        //   298: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   301: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   304: ldc_w           "utf-8"
        //   307: invokespecial   java/io/OutputStreamWriter.<init>:(Ljava/io/OutputStream;Ljava/lang/String;)V
        //   310: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //   313: astore          7
        //   315: aconst_null    
        //   316: astore          5
        //   318: aload           7
        //   320: aload           6
        //   322: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   325: invokevirtual   java/io/Writer.write:(Ljava/lang/String;)V
        //   328: aload           7
        //   330: ifnull          342
        //   333: iconst_0       
        //   334: ifeq            367
        //   337: aload           7
        //   339: invokevirtual   java/io/Writer.close:()V
        //   342: return         
        //   343: astore          5
        //   345: new             Ljava/lang/NullPointerException;
        //   348: dup            
        //   349: invokespecial   java/lang/NullPointerException.<init>:()V
        //   352: athrow         
        //   353: astore          5
        //   355: getstatic       java/lang/System.out:Ljava/io/PrintStream;
        //   358: aload           5
        //   360: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
        //   363: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   366: return         
        //   367: aload           7
        //   369: invokevirtual   java/io/Writer.close:()V
        //   372: return         
        //   373: astore          5
        //   375: aload           5
        //   377: athrow         
        //   378: astore          6
        //   380: aload           7
        //   382: ifnull          395
        //   385: aload           5
        //   387: ifnull          410
        //   390: aload           7
        //   392: invokevirtual   java/io/Writer.close:()V
        //   395: aload           6
        //   397: athrow         
        //   398: astore          7
        //   400: aload           5
        //   402: aload           7
        //   404: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
        //   407: goto            395
        //   410: aload           7
        //   412: invokevirtual   java/io/Writer.close:()V
        //   415: goto            395
        //   418: astore          6
        //   420: goto            380
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  275    315    353    367    Ljava/lang/Exception;
        //  318    328    373    380    Ljava/lang/Throwable;
        //  318    328    418    423    Any
        //  337    342    343    353    Ljava/lang/Throwable;
        //  337    342    353    367    Ljava/lang/Exception;
        //  345    353    353    367    Ljava/lang/Exception;
        //  367    372    353    367    Ljava/lang/Exception;
        //  375    378    378    380    Any
        //  390    395    398    410    Ljava/lang/Throwable;
        //  390    395    353    367    Ljava/lang/Exception;
        //  395    398    353    367    Ljava/lang/Exception;
        //  400    407    353    367    Ljava/lang/Exception;
        //  410    415    353    367    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 226, Size: 226
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
    
    public void showRosterURLDialog() {
        this.rosterFile = new File(this.getFilesDir(), "roster.txt");
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)this);
        alertDialog$Builder.setTitle((CharSequence)"Roster File URL:");
        final EditText view = new EditText((Context)this);
        view.setInputType(1);
        alertDialog$Builder.setView((View)view);
        alertDialog$Builder.setPositiveButton((CharSequence)"Load", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                try {
                    new URL(view.getText().toString()).openConnection();
                    new GetRosterFileTask().execute((Object[])new String[] { view.getText().toString() });
                }
                catch (Exception ex) {
                    Toast.makeText((Context)Home.this, (CharSequence)"Error! Bad URL or unable to read file.", 0).show();
                    dialogInterface.cancel();
                }
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)"Cancel", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                dialogInterface.cancel();
            }
        });
        alertDialog$Builder.setNeutralButton((CharSequence)"How To Guide", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                final Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse("https://m.reddit.com/r/FootballCoach/wiki/rosters"));
                Home.this.startActivity(intent);
            }
        });
        alertDialog$Builder.show();
    }
    
    class GetRosterFileTask extends AsyncTask<String, Void, String>
    {
        protected String doInBackground(final String[] p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: aload_0        
            //     5: getfield        com/achijones/profootballcoach/Home$GetRosterFileTask.this$0:Lcom/achijones/profootballcoach/Home;
            //     8: getfield        com/achijones/profootballcoach/Home.rosterFile:Ljava/io/File;
            //    11: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/File;)V
            //    14: astore_3       
            //    15: aload_3        
            //    16: ldc             ""
            //    18: invokevirtual   java/io/PrintWriter.print:(Ljava/lang/String;)V
            //    21: aload_3        
            //    22: invokevirtual   java/io/PrintWriter.close:()V
            //    25: new             Ljava/io/FileWriter;
            //    28: dup            
            //    29: aload_0        
            //    30: getfield        com/achijones/profootballcoach/Home$GetRosterFileTask.this$0:Lcom/achijones/profootballcoach/Home;
            //    33: getfield        com/achijones/profootballcoach/Home.rosterFile:Ljava/io/File;
            //    36: iconst_1       
            //    37: invokespecial   java/io/FileWriter.<init>:(Ljava/io/File;Z)V
            //    40: astore          4
            //    42: new             Ljava/io/BufferedWriter;
            //    45: dup            
            //    46: aload           4
            //    48: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
            //    51: astore          5
            //    53: new             Ljava/io/PrintWriter;
            //    56: dup            
            //    57: aload           5
            //    59: invokespecial   java/io/PrintWriter.<init>:(Ljava/io/Writer;)V
            //    62: astore          6
            //    64: aconst_null    
            //    65: astore_3       
            //    66: new             Ljava/net/URL;
            //    69: dup            
            //    70: aload_1        
            //    71: iconst_0       
            //    72: aaload         
            //    73: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
            //    76: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
            //    79: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
            //    82: astore_1       
            //    83: aload_1        
            //    84: invokevirtual   java/io/InputStream.read:()I
            //    87: istore_2       
            //    88: iload_2        
            //    89: iconst_m1      
            //    90: if_icmpeq       132
            //    93: aload           6
            //    95: iload_2        
            //    96: i2c            
            //    97: invokevirtual   java/io/PrintWriter.print:(C)V
            //   100: goto            83
            //   103: astore_1       
            //   104: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   107: new             Ljava/lang/StringBuilder;
            //   110: dup            
            //   111: invokespecial   java/lang/StringBuilder.<init>:()V
            //   114: ldc             "URL: "
            //   116: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   119: aload_1        
            //   120: invokevirtual   java/net/MalformedURLException.toString:()Ljava/lang/String;
            //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   126: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   129: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   132: aload           6
            //   134: ifnull          146
            //   137: iconst_0       
            //   138: ifeq            306
            //   141: aload           6
            //   143: invokevirtual   java/io/PrintWriter.close:()V
            //   146: aload           5
            //   148: ifnull          160
            //   151: iconst_0       
            //   152: ifeq            342
            //   155: aload           5
            //   157: invokevirtual   java/io/BufferedWriter.close:()V
            //   160: aload           4
            //   162: ifnull          174
            //   165: iconst_0       
            //   166: ifeq            378
            //   169: aload           4
            //   171: invokevirtual   java/io/FileWriter.close:()V
            //   174: ldc             "Done!"
            //   176: areturn        
            //   177: astore_3       
            //   178: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   181: aload_3        
            //   182: invokevirtual   java/lang/Exception.toString:()Ljava/lang/String;
            //   185: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   188: goto            25
            //   191: astore_1       
            //   192: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   195: new             Ljava/lang/StringBuilder;
            //   198: dup            
            //   199: invokespecial   java/lang/StringBuilder.<init>:()V
            //   202: ldc             "IO: "
            //   204: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   207: aload_1        
            //   208: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
            //   211: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   214: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   217: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   220: goto            132
            //   223: astore_3       
            //   224: aload_3        
            //   225: athrow         
            //   226: astore_1       
            //   227: aload           6
            //   229: ifnull          241
            //   232: aload_3        
            //   233: ifnull          325
            //   236: aload           6
            //   238: invokevirtual   java/io/PrintWriter.close:()V
            //   241: aload_1        
            //   242: athrow         
            //   243: astore_3       
            //   244: aload_3        
            //   245: athrow         
            //   246: astore_1       
            //   247: aload           5
            //   249: ifnull          261
            //   252: aload_3        
            //   253: ifnull          361
            //   256: aload           5
            //   258: invokevirtual   java/io/BufferedWriter.close:()V
            //   261: aload_1        
            //   262: athrow         
            //   263: astore_3       
            //   264: aload_3        
            //   265: athrow         
            //   266: astore_1       
            //   267: aload           4
            //   269: ifnull          281
            //   272: aload_3        
            //   273: ifnull          397
            //   276: aload           4
            //   278: invokevirtual   java/io/FileWriter.close:()V
            //   281: aload_1        
            //   282: athrow         
            //   283: astore_1       
            //   284: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   287: aload_1        
            //   288: invokevirtual   java/io/IOException.toString:()Ljava/lang/String;
            //   291: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   294: goto            174
            //   297: astore_1       
            //   298: new             Ljava/lang/NullPointerException;
            //   301: dup            
            //   302: invokespecial   java/lang/NullPointerException.<init>:()V
            //   305: athrow         
            //   306: aload           6
            //   308: invokevirtual   java/io/PrintWriter.close:()V
            //   311: goto            146
            //   314: astore          6
            //   316: aload_3        
            //   317: aload           6
            //   319: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
            //   322: goto            241
            //   325: aload           6
            //   327: invokevirtual   java/io/PrintWriter.close:()V
            //   330: goto            241
            //   333: astore_1       
            //   334: new             Ljava/lang/NullPointerException;
            //   337: dup            
            //   338: invokespecial   java/lang/NullPointerException.<init>:()V
            //   341: athrow         
            //   342: aload           5
            //   344: invokevirtual   java/io/BufferedWriter.close:()V
            //   347: goto            160
            //   350: astore          5
            //   352: aload_3        
            //   353: aload           5
            //   355: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
            //   358: goto            261
            //   361: aload           5
            //   363: invokevirtual   java/io/BufferedWriter.close:()V
            //   366: goto            261
            //   369: astore_1       
            //   370: new             Ljava/lang/NullPointerException;
            //   373: dup            
            //   374: invokespecial   java/lang/NullPointerException.<init>:()V
            //   377: athrow         
            //   378: aload           4
            //   380: invokevirtual   java/io/FileWriter.close:()V
            //   383: goto            174
            //   386: astore          4
            //   388: aload_3        
            //   389: aload           4
            //   391: invokevirtual   java/lang/Throwable.addSuppressed:(Ljava/lang/Throwable;)V
            //   394: goto            281
            //   397: aload           4
            //   399: invokevirtual   java/io/FileWriter.close:()V
            //   402: goto            281
            //   405: astore_1       
            //   406: goto            227
            //   409: astore_1       
            //   410: aconst_null    
            //   411: astore_3       
            //   412: goto            247
            //   415: astore_1       
            //   416: aconst_null    
            //   417: astore_3       
            //   418: goto            267
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  0      25     177    191    Ljava/lang/Exception;
            //  25     42     283    297    Ljava/io/IOException;
            //  42     53     263    267    Ljava/lang/Throwable;
            //  42     53     415    421    Any
            //  53     64     243    247    Ljava/lang/Throwable;
            //  53     64     409    415    Any
            //  66     83     103    132    Ljava/net/MalformedURLException;
            //  66     83     191    223    Ljava/io/IOException;
            //  66     83     223    227    Ljava/lang/Throwable;
            //  66     83     405    409    Any
            //  83     88     103    132    Ljava/net/MalformedURLException;
            //  83     88     191    223    Ljava/io/IOException;
            //  83     88     223    227    Ljava/lang/Throwable;
            //  83     88     405    409    Any
            //  93     100    103    132    Ljava/net/MalformedURLException;
            //  93     100    191    223    Ljava/io/IOException;
            //  93     100    223    227    Ljava/lang/Throwable;
            //  93     100    405    409    Any
            //  104    132    223    227    Ljava/lang/Throwable;
            //  104    132    405    409    Any
            //  141    146    297    306    Ljava/lang/Throwable;
            //  141    146    409    415    Any
            //  155    160    333    342    Ljava/lang/Throwable;
            //  155    160    415    421    Any
            //  169    174    369    378    Ljava/lang/Throwable;
            //  169    174    283    297    Ljava/io/IOException;
            //  192    220    223    227    Ljava/lang/Throwable;
            //  192    220    405    409    Any
            //  224    226    226    227    Any
            //  236    241    314    325    Ljava/lang/Throwable;
            //  236    241    409    415    Any
            //  241    243    243    247    Ljava/lang/Throwable;
            //  241    243    409    415    Any
            //  244    246    246    247    Any
            //  256    261    350    361    Ljava/lang/Throwable;
            //  256    261    415    421    Any
            //  261    263    263    267    Ljava/lang/Throwable;
            //  261    263    415    421    Any
            //  264    266    266    267    Any
            //  276    281    386    397    Ljava/lang/Throwable;
            //  276    281    283    297    Ljava/io/IOException;
            //  281    283    283    297    Ljava/io/IOException;
            //  298    306    243    247    Ljava/lang/Throwable;
            //  298    306    409    415    Any
            //  306    311    243    247    Ljava/lang/Throwable;
            //  306    311    409    415    Any
            //  316    322    243    247    Ljava/lang/Throwable;
            //  316    322    409    415    Any
            //  325    330    243    247    Ljava/lang/Throwable;
            //  325    330    409    415    Any
            //  334    342    263    267    Ljava/lang/Throwable;
            //  334    342    415    421    Any
            //  342    347    263    267    Ljava/lang/Throwable;
            //  342    347    415    421    Any
            //  352    358    263    267    Ljava/lang/Throwable;
            //  352    358    415    421    Any
            //  361    366    263    267    Ljava/lang/Throwable;
            //  361    366    415    421    Any
            //  370    378    283    297    Ljava/io/IOException;
            //  378    383    283    297    Ljava/io/IOException;
            //  388    394    283    297    Ljava/io/IOException;
            //  397    402    283    297    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
            //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
            //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
            //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
            Home.this.newLeagueRosterFile();
        }
    }
}
