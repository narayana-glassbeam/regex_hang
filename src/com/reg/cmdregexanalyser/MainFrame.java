package com.reg.cmdregexanalyser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.util.Date;
import java.util.Scanner;

import com.reg.cmdregexanalyser.Pattern;
import com.reg.cmdregexanalyser.PatternSyntaxException;
import com.reg.cmdregexanalyser.Matcher;
import com.reg.cmdregexanalyser.Statistics;
import com.reg.cmdregexanalyser.Statistics.TraceTableModel;

@SuppressWarnings("unused")
public class MainFrame 
{
    private String RegEx;
    private String TestTextfilepath;
    private Pattern p = null;
	public MainFrame(String regfile,String textfile) throws FileNotFoundException
	{
		 RegEx =getRegEx(regfile);
		 triggerMatching();
		 TestTextfilepath=textfile;
		// System.out.println("RECEIVED REGULAR EXPRESSION :-\n"+RegEx);
	    // TestText=importTestText(textfile);
		 importTestText(TestTextfilepath);
	    // System.out.println("RECEIVED TEXT IS :-\n"+TestText);
	     
	    // System.out.println("\tPATTERN MATCHING STARTED\t");
	     
	    
	}
	
	private void triggerMatching() throws FileNotFoundException
	{
		// TODO Auto-generated method stub
		 try 
	        {
			 	long st=System.currentTimeMillis();
			 	//System.out.println("Start Time to compile Pattern \t"+st);
	            p = compilePattern();
	          //  p.getMatchPatternTree();
	            long et=System.currentTimeMillis();
	          //  System.out.println("End Time of Compiling Pattern is "+(et-st));
	        } 
		    catch (PatternSyntaxException e) 
	        {
	          System.out.println("Pattern Syntax exception \t"+e.getMessage());
	         
	        }
		 //process();
		
	}
	private void process(String sb) throws FileNotFoundException
	{
		//Date start = new Date();
		System.out.println();
		//System.out.println("Text Received is "+sb);
		long startTime = System.currentTimeMillis();
		//System.out.println("StartTime\t"+startTime);
		Matcher m = p.matcher(sb);
		Statistics.matchAsTree(m,startTime);
		long endTime   = System.currentTimeMillis();
		//System.out.println("End Time\t"+endTime);
		long totalTime = endTime - startTime;
		//System.out.println("Total Time"+totalTime);
		
		
		com.reg.cmdregexanalyser.Statistics.TraceTableModel ttm =m.getTraceAsTable();
        
      //  display(ttm);
        System.out.println(sb+"\tT= "+totalTime+"\tOps\t "+ttm.getRowCount());
        System.out.println();
		//Date stop = new Date();
	}
	private void display(com.reg.cmdregexanalyser.Statistics.TraceTableModel tt) 
	{
		// TODO Auto-generated method stub
		
		int cc=tt.getColumnCount();
    	int rc=tt.getRowCount();
    	System.out.println("\nThe number of operations trace took\t"+rc);
    	for(int i=0;i<cc;i++)
    	{
    		System.out.print("\t"+tt.getColumnName(i));
    	}
    	System.out.println("\n");
    	
    	for(int j=0;j<rc;j++)
    	{
    		for(int l=0;l<cc;l++)
    		{
    			System.out.print("\t"+tt.getValueAt(j, l));
    		}
    		System.out.println("\n");
    	}
    	
		
	}

	private Pattern compilePattern() 
	{
		// TODO Auto-generated method stub
		//System.out.println(" Inside Compile Pattern \nThe passed RegEx is :\n"+regEx);
    	if(RegEx.contains("\n"))
    	{
    		//System.out.println("Inside Multi Line");
    		return Pattern.compile(RegEx,Pattern.MULTILINE);
    	}
    	else
    	{
    		//System.out.println("Inside Single Line");
    		return Pattern.compile(RegEx);
    	}
		//return null;
	}

	private void importTestText(String textfile) throws FileNotFoundException 
	{
		
		
		//"/home/narayanar/workspace/GbRegex/src/testext.txt"
		@SuppressWarnings("resource")
		Scanner TestTextsc=new Scanner(new BufferedReader(new FileReader(textfile)));
		//StringBuilder sb=new StringBuilder();
		while(TestTextsc.hasNextLine())
		{
			//sb=sb.append(TestTextsc.next().trim());
			//System.out.println("New Line Received is "+sb);
			process(TestTextsc.nextLine().trim());
			
		}
		
		
		//return sb.toString();
		
	
		// TODO Auto-generated method stub
		//"/home/narayanar/workspace/GbRegex/src/testext.txt"
		//Scanner TestText=new Scanner(new BufferedReader(new FileReader(textfile)));
		//String st=new String();
		//StringBuilder sb=new StringBuilder();
		//st=input.nextLine();
		//System.out.println(" The line from file is "+st);
		/**while(TestText.hasNextLine())
		{
			
			sb=sb.append(TestText.nextLine()+System.lineSeparator());
			//sb.append('\n');
		}
		TestText.close();
		//System.out.println("\nThe File Contents are\n ");
		//System.out.println(sb);
		return sb.toString();*/
		//return null;
	}

	private String getRegEx(String regfile) throws FileNotFoundException 
	{
		// TODO Auto-generated method stub
		//"/home/narayanar/workspace/GbRegex/src/regex.txt"
		Scanner input=new Scanner(new BufferedReader(new FileReader(regfile)));
		//String st=new String();
		StringBuilder sb=new StringBuilder();
		//st=input.nextLine();
		//System.out.println(" The line from file is "+st);
		sb=sb.append(input.nextLine());
		while(input.hasNextLine())
		{
			
			sb=sb.append(input.nextLine()+System.lineSeparator());
			//sb.append('\n');
		}
		input.close();
		String regex=sb.toString();
		if(regex.contains("\n"))
		{
			//System.out.println("\nRegex contains new line character\n");
		}
	       
	       // System.out.println("\nRegular Expression String is:  \n"+regex);
	        return (regex == null)?(""):(regex);
		
		//return null;
	}

	public static void main(String args[]) throws FileNotFoundException 
	{
		
		String regfile=args[0];
		String textfile=args[1];
		//System.out.println("\nReceived Regex File is "+regfile);
		//System.out.println("\nReceived TestText File is"+textfile+"\n");
		MainFrame mainframe=new MainFrame(regfile,textfile);
	
	}

	

}
