package com.achijones.profootballcoach;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;

public class Home
  extends AppCompatActivity
{
  File rosterFile;
  int saveFileNum;
  
  private String[] getOnlineTeams()
  {
    String[] arrayOfString = new String[5];
    int i = 0;
    if (i < 5)
    {
      Object localObject = new File(getFilesDir(), "onlineTeam" + i + ".cfb");
      if (((File)localObject).exists()) {}
      for (;;)
      {
        try
        {
          localObject = new BufferedReader(new FileReader((File)localObject)).readLine();
          arrayOfString[i] = ((String)localObject).substring(0, ((String)localObject).length());
          i += 1;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          System.out.println("Unable to open file");
          continue;
        }
        catch (IOException localIOException)
        {
          System.out.println("Error reading file");
          continue;
        }
        catch (NullPointerException localNullPointerException)
        {
          System.out.println("Null pointer exception!");
          continue;
        }
        arrayOfString[i] = "EMPTY";
      }
    }
    return arrayOfString;
  }
  
  private String[] getSaveFileInfos()
  {
    String[] arrayOfString = new String[10];
    int i = 0;
    if (i < 10)
    {
      Object localObject = new File(getFilesDir(), "saveFile" + i + ".cfb");
      if (((File)localObject).exists()) {}
      for (;;)
      {
        try
        {
          localObject = new BufferedReader(new FileReader((File)localObject)).readLine();
          arrayOfString[i] = ((String)localObject).substring(0, ((String)localObject).length() - 1);
          i += 1;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          System.out.println("Unable to open file");
          continue;
        }
        catch (IOException localIOException)
        {
          System.out.println("Error reading file");
          continue;
        }
        catch (NullPointerException localNullPointerException)
        {
          System.out.println("Null pointer exception!");
          continue;
        }
        arrayOfString[i] = "EMPTY";
      }
    }
    return arrayOfString;
  }
  
  private void goOnline()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Choose Online Team:");
    final String[] arrayOfString = getOnlineTeams();
    localBuilder.setAdapter(new SaveFilesListArrayAdapter(this, arrayOfString), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (!arrayOfString[paramAnonymousInt].equals("EMPTY"))
        {
          paramAnonymousDialogInterface = new Intent(Home.this, OnlineActivity.class);
          paramAnonymousDialogInterface.putExtra("ONLINE_TEAM", arrayOfString[paramAnonymousInt]);
          startActivity(paramAnonymousDialogInterface);
          return;
        }
        Toast.makeText(Home.this, "Team doesn't exist!", 0).show();
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  private void loadLeague()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Choose File to Load:");
    final String[] arrayOfString = getSaveFileInfos();
    localBuilder.setAdapter(new SaveFilesListArrayAdapter(this, arrayOfString), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        if (!arrayOfString[paramAnonymousInt].equals("EMPTY"))
        {
          paramAnonymousDialogInterface = new Intent(Home.this, MainActivity.class);
          paramAnonymousDialogInterface.putExtra("SAVE_FILE", "LOAD,saveFile" + paramAnonymousInt + ".cfb,saveFile" + paramAnonymousInt + "_teams.cfb,saveFile" + paramAnonymousInt + "_schedules.cfb,saveFile" + paramAnonymousInt + "_teamhist.cfb");
          startActivity(paramAnonymousDialogInterface);
          return;
        }
        Toast.makeText(Home.this, "Cannot load empty file!", 0).show();
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  private void newLeague()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Choose File to Use:");
    final String[] arrayOfString = getSaveFileInfos();
    localBuilder.setAdapter(new SaveFilesListArrayAdapter(this, arrayOfString), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, final int paramAnonymousInt)
      {
        if (arrayOfString[paramAnonymousInt].equals("EMPTY"))
        {
          beginNewLeagueDialog(paramAnonymousInt);
          return;
        }
        paramAnonymousDialogInterface = new AlertDialog.Builder(Home.this);
        paramAnonymousDialogInterface.setMessage("Are you sure you want to use this save file? It will overwrite the league currently saved.\n\n" + arrayOfString[paramAnonymousInt]).setPositiveButton("Yes, Overwrite", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            beginNewLeagueDialog(paramAnonymousInt);
          }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface.dismiss();
          }
        });
        paramAnonymousDialogInterface = paramAnonymousDialogInterface.create();
        paramAnonymousDialogInterface.show();
        ((TextView)paramAnonymousDialogInterface.findViewById(16908299)).setTextSize(2, 14.0F);
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    });
    localBuilder.create().show();
  }
  
  public void beginNewLeagueDialog(final int paramInt)
  {
    Object localObject = new AlertDialog.Builder(this);
    ((AlertDialog.Builder)localObject).setMessage("Do you want to generate a league from scratch or load from an online roster file?").setPositiveButton("Generate", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent(Home.this, MainActivity.class);
        paramAnonymousDialogInterface.putExtra("SAVE_FILE", "NEW,saveFile" + paramInt + ".cfb,saveFile" + paramInt + "_teams.cfb,saveFile" + paramInt + "_schedules.cfb,saveFile" + paramInt + "_teamhist.cfb");
        startActivity(paramAnonymousDialogInterface);
      }
    }).setNegativeButton("Load from online", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        saveFileNum = paramInt;
        showRosterURLDialog();
      }
    });
    localObject = ((AlertDialog.Builder)localObject).create();
    ((AlertDialog)localObject).show();
    ((TextView)((AlertDialog)localObject).findViewById(16908299)).setTextSize(2, 14.0F);
  }
  
  public void newLeagueRosterFile()
  {
    Intent localIntent = new Intent(this, MainActivity.class);
    localIntent.putExtra("SAVE_FILE", "ROSTER,saveFile" + saveFileNum + ".cfb,saveFile" + saveFileNum + "_teams.cfb,saveFile" + saveFileNum + "_schedules.cfb,saveFile" + saveFileNum + "_teamhist.cfb");
    startActivity(localIntent);
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968603);
    paramBundle = (ImageView)findViewById(2131558538);
    try
    {
      paramBundle.setImageResource(2130837612);
      ((Button)findViewById(2131558539)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Home.this.newLeague();
        }
      });
      ((Button)findViewById(2131558540)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Home.this.loadLeague();
        }
      });
      ((Button)findViewById(2131558541)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Home.this.goOnline();
        }
      });
      return;
    }
    catch (OutOfMemoryError paramBundle)
    {
      for (;;)
      {
        Toast.makeText(this, "Error displaying logo", 0).show();
      }
    }
  }
  
  /* Error */
  public void runTestPlayerProgression()
  {
    // Byte code:
    //   0: getstatic 308	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   3: iconst_2
    //   4: newarray <illegal type>
    //   6: dup
    //   7: iconst_0
    //   8: bipush 10
    //   10: iastore
    //   11: dup
    //   12: iconst_1
    //   13: bipush 80
    //   15: iastore
    //   16: invokestatic 314	java/lang/reflect/Array:newInstance	(Ljava/lang/Class;[I)Ljava/lang/Object;
    //   19: checkcast 316	[[I
    //   22: astore 5
    //   24: iconst_0
    //   25: istore_2
    //   26: iload_2
    //   27: sipush 10000
    //   30: if_icmpge +98 -> 128
    //   33: new 318	PFCpack/PlayerS
    //   36: dup
    //   37: ldc_w 320
    //   40: sipush 2016
    //   43: invokespecial 323	PFCpack/PlayerS:<init>	(Ljava/lang/String;I)V
    //   46: astore 6
    //   48: iconst_0
    //   49: istore_1
    //   50: iload_1
    //   51: bipush 7
    //   53: if_icmpge +15 -> 68
    //   56: aload 6
    //   58: invokevirtual 326	PFCpack/PlayerS:advanceSeasonRatingsAge	()V
    //   61: iload_1
    //   62: iconst_1
    //   63: iadd
    //   64: istore_1
    //   65: goto -15 -> 50
    //   68: aload 6
    //   70: invokevirtual 329	PFCpack/PlayerS:getRatOvr	()I
    //   73: bipush 50
    //   75: isub
    //   76: istore_3
    //   77: iload_3
    //   78: istore_1
    //   79: iload_3
    //   80: bipush 79
    //   82: if_icmple +6 -> 88
    //   85: bipush 79
    //   87: istore_1
    //   88: iload_1
    //   89: istore_3
    //   90: iload_1
    //   91: ifge +5 -> 96
    //   94: iconst_0
    //   95: istore_3
    //   96: aload 5
    //   98: aload 6
    //   100: invokevirtual 332	PFCpack/PlayerS:getRatPot	()I
    //   103: bipush 50
    //   105: isub
    //   106: iconst_5
    //   107: idiv
    //   108: aaload
    //   109: astore 6
    //   111: aload 6
    //   113: iload_3
    //   114: aload 6
    //   116: iload_3
    //   117: iaload
    //   118: iconst_1
    //   119: iadd
    //   120: iastore
    //   121: iload_2
    //   122: iconst_1
    //   123: iadd
    //   124: istore_2
    //   125: goto -99 -> 26
    //   128: new 79	java/lang/StringBuilder
    //   131: dup
    //   132: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   135: astore 6
    //   137: aload 6
    //   139: ldc_w 334
    //   142: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: pop
    //   146: iconst_0
    //   147: istore_1
    //   148: iload_1
    //   149: bipush 10
    //   151: if_icmpge +124 -> 275
    //   154: aload 6
    //   156: new 79	java/lang/StringBuilder
    //   159: dup
    //   160: invokespecial 80	java/lang/StringBuilder:<init>	()V
    //   163: ldc_w 336
    //   166: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   169: iload_1
    //   170: iconst_5
    //   171: imul
    //   172: bipush 50
    //   174: iadd
    //   175: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   178: ldc_w 338
    //   181: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   184: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   187: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   190: pop
    //   191: iconst_0
    //   192: istore_2
    //   193: iload_2
    //   194: bipush 12
    //   196: if_icmpge +63 -> 259
    //   199: aload 6
    //   201: ldc_w 340
    //   204: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   207: pop
    //   208: iconst_0
    //   209: istore_3
    //   210: iconst_0
    //   211: istore 4
    //   213: iload 4
    //   215: iconst_5
    //   216: if_icmpge +29 -> 245
    //   219: iload_3
    //   220: aload 5
    //   222: iload_1
    //   223: aaload
    //   224: iload_2
    //   225: iconst_5
    //   226: imul
    //   227: iload 4
    //   229: iadd
    //   230: bipush 20
    //   232: iadd
    //   233: iaload
    //   234: iadd
    //   235: istore_3
    //   236: iload 4
    //   238: iconst_1
    //   239: iadd
    //   240: istore 4
    //   242: goto -29 -> 213
    //   245: aload 6
    //   247: iload_3
    //   248: invokevirtual 89	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   251: pop
    //   252: iload_2
    //   253: iconst_1
    //   254: iadd
    //   255: istore_2
    //   256: goto -63 -> 193
    //   259: aload 6
    //   261: ldc_w 342
    //   264: invokevirtual 86	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   267: pop
    //   268: iload_1
    //   269: iconst_1
    //   270: iadd
    //   271: istore_1
    //   272: goto -124 -> 148
    //   275: new 344	java/io/BufferedWriter
    //   278: dup
    //   279: new 346	java/io/OutputStreamWriter
    //   282: dup
    //   283: new 348	java/io/FileOutputStream
    //   286: dup
    //   287: new 73	java/io/File
    //   290: dup
    //   291: aload_0
    //   292: invokevirtual 77	com/achijones/profootballcoach/Home:getFilesDir	()Ljava/io/File;
    //   295: ldc_w 350
    //   298: invokespecial 98	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   301: invokespecial 351	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   304: ldc_w 353
    //   307: invokespecial 356	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;Ljava/lang/String;)V
    //   310: invokespecial 359	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
    //   313: astore 7
    //   315: aconst_null
    //   316: astore 5
    //   318: aload 7
    //   320: aload 6
    //   322: invokevirtual 95	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   325: invokevirtual 364	java/io/Writer:write	(Ljava/lang/String;)V
    //   328: aload 7
    //   330: ifnull +12 -> 342
    //   333: iconst_0
    //   334: ifeq +33 -> 367
    //   337: aload 7
    //   339: invokevirtual 367	java/io/Writer:close	()V
    //   342: return
    //   343: astore 5
    //   345: new 69	java/lang/NullPointerException
    //   348: dup
    //   349: invokespecial 368	java/lang/NullPointerException:<init>	()V
    //   352: athrow
    //   353: astore 5
    //   355: getstatic 129	java/lang/System:out	Ljava/io/PrintStream;
    //   358: aload 5
    //   360: invokevirtual 369	java/lang/Exception:toString	()Ljava/lang/String;
    //   363: invokevirtual 137	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   366: return
    //   367: aload 7
    //   369: invokevirtual 367	java/io/Writer:close	()V
    //   372: return
    //   373: astore 5
    //   375: aload 5
    //   377: athrow
    //   378: astore 6
    //   380: aload 7
    //   382: ifnull +13 -> 395
    //   385: aload 5
    //   387: ifnull +23 -> 410
    //   390: aload 7
    //   392: invokevirtual 367	java/io/Writer:close	()V
    //   395: aload 6
    //   397: athrow
    //   398: astore 7
    //   400: aload 5
    //   402: aload 7
    //   404: invokevirtual 373	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
    //   407: goto -12 -> 395
    //   410: aload 7
    //   412: invokevirtual 367	java/io/Writer:close	()V
    //   415: goto -20 -> 395
    //   418: astore 6
    //   420: goto -40 -> 380
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	423	0	this	Home
    //   49	223	1	i	int
    //   25	231	2	j	int
    //   76	172	3	k	int
    //   211	30	4	m	int
    //   22	295	5	arrayOfInt	int[][]
    //   343	1	5	localThrowable1	Throwable
    //   353	6	5	localException	Exception
    //   373	28	5	localThrowable2	Throwable
    //   46	275	6	localObject1	Object
    //   378	18	6	localObject2	Object
    //   418	1	6	localObject3	Object
    //   313	78	7	localBufferedWriter	java.io.BufferedWriter
    //   398	13	7	localThrowable3	Throwable
    // Exception table:
    //   from	to	target	type
    //   337	342	343	java/lang/Throwable
    //   275	315	353	java/lang/Exception
    //   337	342	353	java/lang/Exception
    //   345	353	353	java/lang/Exception
    //   367	372	353	java/lang/Exception
    //   390	395	353	java/lang/Exception
    //   395	398	353	java/lang/Exception
    //   400	407	353	java/lang/Exception
    //   410	415	353	java/lang/Exception
    //   318	328	373	java/lang/Throwable
    //   375	378	378	finally
    //   390	395	398	java/lang/Throwable
    //   318	328	418	finally
  }
  
  public void showRosterURLDialog()
  {
    rosterFile = new File(getFilesDir(), "roster.txt");
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle("Roster File URL:");
    final EditText localEditText = new EditText(this);
    localEditText.setInputType(1);
    localBuilder.setView(localEditText);
    localBuilder.setPositiveButton("Load", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          new URL(localEditText.getText().toString()).openConnection();
          new Home.GetRosterFileTask(Home.this).execute(new String[] { localEditText.getText().toString() });
          return;
        }
        catch (Exception localException)
        {
          Toast.makeText(Home.this, "Error! Bad URL or unable to read file.", 0).show();
          paramAnonymousDialogInterface.cancel();
        }
      }
    });
    localBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.cancel();
      }
    });
    localBuilder.setNeutralButton("How To Guide", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent();
        paramAnonymousDialogInterface.setAction("android.intent.action.VIEW");
        paramAnonymousDialogInterface.addCategory("android.intent.category.BROWSABLE");
        paramAnonymousDialogInterface.setData(Uri.parse("https://m.reddit.com/r/FootballCoach/wiki/rosters"));
        startActivity(paramAnonymousDialogInterface);
      }
    });
    localBuilder.show();
  }
  
  class GetRosterFileTask
    extends AsyncTask<String, Void, String>
  {
    GetRosterFileTask() {}
    
    /* Error */
    protected String doInBackground(String[] paramArrayOfString)
    {
      // Byte code:
      //   0: new 35	java/io/PrintWriter
      //   3: dup
      //   4: aload_0
      //   5: getfield 14	com/achijones/profootballcoach/Home$GetRosterFileTask:this$0	Lcom/achijones/profootballcoach/Home;
      //   8: getfield 39	com/achijones/profootballcoach/Home:rosterFile	Ljava/io/File;
      //   11: invokespecial 42	java/io/PrintWriter:<init>	(Ljava/io/File;)V
      //   14: astore_3
      //   15: aload_3
      //   16: ldc 44
      //   18: invokevirtual 48	java/io/PrintWriter:print	(Ljava/lang/String;)V
      //   21: aload_3
      //   22: invokevirtual 51	java/io/PrintWriter:close	()V
      //   25: new 53	java/io/FileWriter
      //   28: dup
      //   29: aload_0
      //   30: getfield 14	com/achijones/profootballcoach/Home$GetRosterFileTask:this$0	Lcom/achijones/profootballcoach/Home;
      //   33: getfield 39	com/achijones/profootballcoach/Home:rosterFile	Ljava/io/File;
      //   36: iconst_1
      //   37: invokespecial 56	java/io/FileWriter:<init>	(Ljava/io/File;Z)V
      //   40: astore 4
      //   42: new 58	java/io/BufferedWriter
      //   45: dup
      //   46: aload 4
      //   48: invokespecial 61	java/io/BufferedWriter:<init>	(Ljava/io/Writer;)V
      //   51: astore 5
      //   53: new 35	java/io/PrintWriter
      //   56: dup
      //   57: aload 5
      //   59: invokespecial 62	java/io/PrintWriter:<init>	(Ljava/io/Writer;)V
      //   62: astore 6
      //   64: aconst_null
      //   65: astore_3
      //   66: new 64	java/net/URL
      //   69: dup
      //   70: aload_1
      //   71: iconst_0
      //   72: aaload
      //   73: invokespecial 66	java/net/URL:<init>	(Ljava/lang/String;)V
      //   76: invokevirtual 70	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   79: invokevirtual 76	java/net/URLConnection:getInputStream	()Ljava/io/InputStream;
      //   82: astore_1
      //   83: aload_1
      //   84: invokevirtual 82	java/io/InputStream:read	()I
      //   87: istore_2
      //   88: iload_2
      //   89: iconst_m1
      //   90: if_icmpeq +42 -> 132
      //   93: aload 6
      //   95: iload_2
      //   96: i2c
      //   97: invokevirtual 85	java/io/PrintWriter:print	(C)V
      //   100: goto -17 -> 83
      //   103: astore_1
      //   104: getstatic 91	java/lang/System:out	Ljava/io/PrintStream;
      //   107: new 93	java/lang/StringBuilder
      //   110: dup
      //   111: invokespecial 94	java/lang/StringBuilder:<init>	()V
      //   114: ldc 96
      //   116: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   119: aload_1
      //   120: invokevirtual 104	java/net/MalformedURLException:toString	()Ljava/lang/String;
      //   123: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   126: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   129: invokevirtual 110	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   132: aload 6
      //   134: ifnull +12 -> 146
      //   137: iconst_0
      //   138: ifeq +168 -> 306
      //   141: aload 6
      //   143: invokevirtual 51	java/io/PrintWriter:close	()V
      //   146: aload 5
      //   148: ifnull +12 -> 160
      //   151: iconst_0
      //   152: ifeq +190 -> 342
      //   155: aload 5
      //   157: invokevirtual 111	java/io/BufferedWriter:close	()V
      //   160: aload 4
      //   162: ifnull +12 -> 174
      //   165: iconst_0
      //   166: ifeq +212 -> 378
      //   169: aload 4
      //   171: invokevirtual 112	java/io/FileWriter:close	()V
      //   174: ldc 114
      //   176: areturn
      //   177: astore_3
      //   178: getstatic 91	java/lang/System:out	Ljava/io/PrintStream;
      //   181: aload_3
      //   182: invokevirtual 115	java/lang/Exception:toString	()Ljava/lang/String;
      //   185: invokevirtual 110	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   188: goto -163 -> 25
      //   191: astore_1
      //   192: getstatic 91	java/lang/System:out	Ljava/io/PrintStream;
      //   195: new 93	java/lang/StringBuilder
      //   198: dup
      //   199: invokespecial 94	java/lang/StringBuilder:<init>	()V
      //   202: ldc 117
      //   204: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   207: aload_1
      //   208: invokevirtual 118	java/io/IOException:toString	()Ljava/lang/String;
      //   211: invokevirtual 100	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   214: invokevirtual 105	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   217: invokevirtual 110	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   220: goto -88 -> 132
      //   223: astore_3
      //   224: aload_3
      //   225: athrow
      //   226: astore_1
      //   227: aload 6
      //   229: ifnull +12 -> 241
      //   232: aload_3
      //   233: ifnull +92 -> 325
      //   236: aload 6
      //   238: invokevirtual 51	java/io/PrintWriter:close	()V
      //   241: aload_1
      //   242: athrow
      //   243: astore_3
      //   244: aload_3
      //   245: athrow
      //   246: astore_1
      //   247: aload 5
      //   249: ifnull +12 -> 261
      //   252: aload_3
      //   253: ifnull +108 -> 361
      //   256: aload 5
      //   258: invokevirtual 111	java/io/BufferedWriter:close	()V
      //   261: aload_1
      //   262: athrow
      //   263: astore_3
      //   264: aload_3
      //   265: athrow
      //   266: astore_1
      //   267: aload 4
      //   269: ifnull +12 -> 281
      //   272: aload_3
      //   273: ifnull +124 -> 397
      //   276: aload 4
      //   278: invokevirtual 112	java/io/FileWriter:close	()V
      //   281: aload_1
      //   282: athrow
      //   283: astore_1
      //   284: getstatic 91	java/lang/System:out	Ljava/io/PrintStream;
      //   287: aload_1
      //   288: invokevirtual 118	java/io/IOException:toString	()Ljava/lang/String;
      //   291: invokevirtual 110	java/io/PrintStream:println	(Ljava/lang/String;)V
      //   294: goto -120 -> 174
      //   297: astore_1
      //   298: new 120	java/lang/NullPointerException
      //   301: dup
      //   302: invokespecial 121	java/lang/NullPointerException:<init>	()V
      //   305: athrow
      //   306: aload 6
      //   308: invokevirtual 51	java/io/PrintWriter:close	()V
      //   311: goto -165 -> 146
      //   314: astore 6
      //   316: aload_3
      //   317: aload 6
      //   319: invokevirtual 125	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
      //   322: goto -81 -> 241
      //   325: aload 6
      //   327: invokevirtual 51	java/io/PrintWriter:close	()V
      //   330: goto -89 -> 241
      //   333: astore_1
      //   334: new 120	java/lang/NullPointerException
      //   337: dup
      //   338: invokespecial 121	java/lang/NullPointerException:<init>	()V
      //   341: athrow
      //   342: aload 5
      //   344: invokevirtual 111	java/io/BufferedWriter:close	()V
      //   347: goto -187 -> 160
      //   350: astore 5
      //   352: aload_3
      //   353: aload 5
      //   355: invokevirtual 125	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
      //   358: goto -97 -> 261
      //   361: aload 5
      //   363: invokevirtual 111	java/io/BufferedWriter:close	()V
      //   366: goto -105 -> 261
      //   369: astore_1
      //   370: new 120	java/lang/NullPointerException
      //   373: dup
      //   374: invokespecial 121	java/lang/NullPointerException:<init>	()V
      //   377: athrow
      //   378: aload 4
      //   380: invokevirtual 112	java/io/FileWriter:close	()V
      //   383: goto -209 -> 174
      //   386: astore 4
      //   388: aload_3
      //   389: aload 4
      //   391: invokevirtual 125	java/lang/Throwable:addSuppressed	(Ljava/lang/Throwable;)V
      //   394: goto -113 -> 281
      //   397: aload 4
      //   399: invokevirtual 112	java/io/FileWriter:close	()V
      //   402: goto -121 -> 281
      //   405: astore_1
      //   406: goto -179 -> 227
      //   409: astore_1
      //   410: aconst_null
      //   411: astore_3
      //   412: goto -165 -> 247
      //   415: astore_1
      //   416: aconst_null
      //   417: astore_3
      //   418: goto -151 -> 267
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	421	0	this	GetRosterFileTask
      //   0	421	1	paramArrayOfString	String[]
      //   87	9	2	i	int
      //   14	52	3	localPrintWriter1	java.io.PrintWriter
      //   177	5	3	localException	Exception
      //   223	10	3	localThrowable1	Throwable
      //   243	10	3	localThrowable2	Throwable
      //   263	126	3	localThrowable3	Throwable
      //   411	7	3	localObject	Object
      //   40	339	4	localFileWriter	java.io.FileWriter
      //   386	12	4	localThrowable4	Throwable
      //   51	292	5	localBufferedWriter	java.io.BufferedWriter
      //   350	12	5	localThrowable5	Throwable
      //   62	245	6	localPrintWriter2	java.io.PrintWriter
      //   314	12	6	localThrowable6	Throwable
      // Exception table:
      //   from	to	target	type
      //   66	83	103	java/net/MalformedURLException
      //   83	88	103	java/net/MalformedURLException
      //   93	100	103	java/net/MalformedURLException
      //   0	25	177	java/lang/Exception
      //   66	83	191	java/io/IOException
      //   83	88	191	java/io/IOException
      //   93	100	191	java/io/IOException
      //   66	83	223	java/lang/Throwable
      //   83	88	223	java/lang/Throwable
      //   93	100	223	java/lang/Throwable
      //   104	132	223	java/lang/Throwable
      //   192	220	223	java/lang/Throwable
      //   224	226	226	finally
      //   53	64	243	java/lang/Throwable
      //   241	243	243	java/lang/Throwable
      //   298	306	243	java/lang/Throwable
      //   306	311	243	java/lang/Throwable
      //   316	322	243	java/lang/Throwable
      //   325	330	243	java/lang/Throwable
      //   244	246	246	finally
      //   42	53	263	java/lang/Throwable
      //   261	263	263	java/lang/Throwable
      //   334	342	263	java/lang/Throwable
      //   342	347	263	java/lang/Throwable
      //   352	358	263	java/lang/Throwable
      //   361	366	263	java/lang/Throwable
      //   264	266	266	finally
      //   25	42	283	java/io/IOException
      //   169	174	283	java/io/IOException
      //   276	281	283	java/io/IOException
      //   281	283	283	java/io/IOException
      //   370	378	283	java/io/IOException
      //   378	383	283	java/io/IOException
      //   388	394	283	java/io/IOException
      //   397	402	283	java/io/IOException
      //   141	146	297	java/lang/Throwable
      //   236	241	314	java/lang/Throwable
      //   155	160	333	java/lang/Throwable
      //   256	261	350	java/lang/Throwable
      //   169	174	369	java/lang/Throwable
      //   276	281	386	java/lang/Throwable
      //   66	83	405	finally
      //   83	88	405	finally
      //   93	100	405	finally
      //   104	132	405	finally
      //   192	220	405	finally
      //   53	64	409	finally
      //   141	146	409	finally
      //   236	241	409	finally
      //   241	243	409	finally
      //   298	306	409	finally
      //   306	311	409	finally
      //   316	322	409	finally
      //   325	330	409	finally
      //   42	53	415	finally
      //   155	160	415	finally
      //   256	261	415	finally
      //   261	263	415	finally
      //   334	342	415	finally
      //   342	347	415	finally
      //   352	358	415	finally
      //   361	366	415	finally
    }
    
    protected void onPostExecute(String paramString)
    {
      newLeagueRosterFile();
    }
  }
}

/* Location:
 * Qualified Name:     com.achijones.profootballcoach.Home
 * Java Class Version: 6 (50.0)
 * JD-Core Version:    0.7.1
 */