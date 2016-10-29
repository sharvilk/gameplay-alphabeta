import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class homework {
	
	static int bvalue[][];
	static char bpos[][];
	static String startpos="";
	static int n;
	static int d;
	static char start;
	
	static int evaluateBoard(String curpos)
	{
		int value=0;
		if(start=='X')
		{
			for(int i=0;i<curpos.length();i++)
			{
				if(curpos.charAt(i)=='X')
				{
					int j1=i/n;
					int j2=i%n;
					value+=bvalue[j1][j2];
				}
				else if(curpos.charAt(i)=='O')
				{
					int j1=i/n;
					int j2=i%n;
					value-=bvalue[j1][j2];
				}
			}
		}
		else		//O's turn
		{
			for(int i=0;i<curpos.length();i++)
			{
				if(curpos.charAt(i)=='X')
				{
					int j1=i/n;
					int j2=i%n;
					value-=bvalue[j1][j2];
				}
				else if(curpos.charAt(i)=='O')
				{
					int j1=i/n;
					int j2=i%n;
					value+=bvalue[j1][j2];
				}
			}
		}
		return value;
	}
	
	static boolean isBoardFull(String curpos)
	{
		if(curpos.contains("."))
			return false;
		else return true;
	}
	
	static String minimaxdecision(String startpos)
	{
		String v=maxvalue(startpos,null,d);
		return v;
	}
	
	static String maxvalue(String curpos,String move,int depth)		//check for start with X or O
	{
		//System.out.println(curpos);
		if(depth==0 || isBoardFull(curpos))
		{
			//System.out.println(evaluateBoard(curpos));
			return move+evaluateBoard(curpos);
		}
		else
		{
			if(start=='O')
			{
				int v=Integer.MIN_VALUE;
				String move1="";
				String rmove="";
				String rmove2="    ";
				//all possible stakes
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						StringBuilder dummy=new StringBuilder(curpos);
						dummy.setCharAt(i,'O');
						String rpos=new String(dummy);
						//String rpos=curpos.replace(curpos.charAt(i),'O');
						int j1=i/n;
						int j2=i%n;
						String j3="";
						if(j1>=0 && j1<=9)
							j3="0"+j1;
						else
							j3=""+j1;
						move1="S"+((char)(j2+65))+j3; //B03
						if(depth==d)
							rmove=move1;
						else 
							rmove=move;
						String rvalue=minvalue(rpos,rmove,depth-1);
						String v2=rvalue.substring(4);
						//System.out.println(rmove);
						//v=Math.max(v,Integer.parseInt(v2));
						//System.out.println(v2);
						if(v<Integer.parseInt(v2) ){
							v=Integer.parseInt(v2);
							rmove2=rvalue.substring(0,4);
						}
						//
						//System.out.println("inside max vuale");
					}
					
					
				}
				//all possible raids
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						int a[]=getSur(i); //get i1,i2,i3,i4
						boolean isRaidPos=false;
						for(int q=0;q<a.length;q++)
						{
							if(a[q]!=-1 && curpos.charAt(a[q])=='O')
							{
								isRaidPos=true;
								break;
							}
						}
						if(isRaidPos){
							isRaidPos=false;
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='X')
								{
									isRaidPos=true;
									break;
								}
							}
						}
						if(isRaidPos)	//then raid
						{
							StringBuilder dummy=new StringBuilder(curpos);
							dummy.setCharAt(i,'O');
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='X')
									dummy.setCharAt(a[q],'O');
							}
							String rpos=new String(dummy);
							int j1=i/n;
							int j2=i%n;
							String j3="";
							if(j2>=0 && j2<=9)
								j3="0"+j1;
							else
								j3=""+j1;
							move1="R"+((char)(j2+65))+j3; //B03
							
							if(depth==d)
								rmove=move1;
							else 
								rmove=move;
							//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
							String rvalue=minvalue(rpos,rmove,depth-1);
							String v2=rvalue.substring(4);
							//v=Math.max(v,Integer.parseInt(v2));
							if(v<Integer.parseInt(v2) ){
								v=Integer.parseInt(v2);
								rmove2=rvalue.substring(0,4);
							}
							//System.out.println("inside max value");
						}
						
					}
				}
				//System.out.println(v);
				return rmove2+v;
			}
			else //start== x
			{
				int v=Integer.MIN_VALUE;
				String move1="";
				String rmove="";
				String rmove2="    ";
				//all possible stakes
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						StringBuilder dummy=new StringBuilder(curpos);
						dummy.setCharAt(i,'X');
						String rpos=new String(dummy);
						//String rpos=curpos.replace(curpos.charAt(i),'O');
						int j1=i/n;
						int j2=i%n;
						String j3="";
						if(j1>=0 && j1<=9)
							j3="0"+j1;
						else
							j3=""+j1;
						move1="S"+((char)(j2+65))+j3; //B03
						if(depth==d)
							rmove=move1;
						else 
							rmove=move;
						String rvalue=minvalue(rpos,rmove,depth-1);
						String v2=rvalue.substring(4);
						//System.out.println(rmove);
						//v=Math.max(v,Integer.parseInt(v2));
						//System.out.println(v2);
						if(v<Integer.parseInt(v2) ){
							v=Integer.parseInt(v2);
							rmove2=rvalue.substring(0,4);
						}
						//System.out.println("inside max vuale");
					}
					
				}
				//all possible raids
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						int a[]=getSur(i); //get i1,i2,i3,i4
						boolean isRaidPos=false;
						for(int q=0;q<a.length;q++)
						{
							if(a[q]!=-1 && curpos.charAt(a[q])=='X')
							{
								isRaidPos=true;
								break;
							}
						}
						if(isRaidPos){
							isRaidPos=false;
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='O')
								{
									isRaidPos=true;
									break;
								}
							}
						}
						if(isRaidPos)	//then raid
						{
							StringBuilder dummy=new StringBuilder(curpos);
							dummy.setCharAt(i,'X');
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='O')
									dummy.setCharAt(a[q],'X');
							}
							String rpos=new String(dummy);
							int j1=i/n;
							int j2=i%n;
							String j3="";
							if(j2>=0 && j2<=9)
								j3="0"+j1;
							else
								j3=""+j1;
							move1="R"+((char)(j2+65))+j3; //B03
							
							if(depth==d)
								rmove=move1;
							else 
								rmove=move;
							//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
							String rvalue=minvalue(rpos,rmove,depth-1);
							String v2=rvalue.substring(4);
							//v=Math.max(v,Integer.parseInt(v2));
							if(v<Integer.parseInt(v2) ){
								v=Integer.parseInt(v2);
								rmove2=rvalue.substring(0,4);
							}
							//System.out.println("inside max value");
						}
						
					}
				}
				//System.out.println(v);
				return rmove2+v;
			}
		}
	}
	
	static String minvalue(String curpos,String move, int depth)
	{
		//System.out.println(curpos);
		if(depth==0 || isBoardFull(curpos))
			return move+evaluateBoard(curpos);
		else
		{
			if(start=='O')
			{
				int v=Integer.MAX_VALUE;
				String move1="";
				String rmove="";
				String rmove2="    ";
				//all possible stakes
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						StringBuilder dummy=new StringBuilder(curpos);
						dummy.setCharAt(i,'X');
						String rpos=new String(dummy);
						int j1=i/n;
						int j2=i%n;
						String j3="";
						if(j2>=0 && j2<=9)
							j3="0"+j1;
						else
							j3=""+j1;
						move1="S"+((char)(j2+65))+j3; //B03
						
						if(depth==d)
							rmove=move1;
						else 
							rmove=move;
						//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
						String rvalue=maxvalue(rpos,rmove,depth-1);
						String v2=rvalue.substring(4);
						//v=Math.min(v,Integer.parseInt(v2));
						if(v>Integer.parseInt(v2) ){
							v=Integer.parseInt(v2);
							rmove2=rvalue.substring(0,4);
						}
						//System.out.println("inside min value");
					}
				}
				
				//all possible raids
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						int a[]=getSur(i); //get i1,i2,i3,i4
						boolean isRaidPos=false;
						for(int q=0;q<a.length;q++)
						{
							if(a[q]!=-1 && curpos.charAt(a[q])=='X')
							{
								isRaidPos=true;
								break;
							}
						}
						if(isRaidPos){
							isRaidPos=false;
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='O')
								{
									isRaidPos=true;
									break;
								}
							}
						}
						if(isRaidPos)	//then raid
						{
							StringBuilder dummy=new StringBuilder(curpos);
							dummy.setCharAt(i,'X');
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='O')
									dummy.setCharAt(a[q],'X');
							}
							String rpos=new String(dummy);
							int j1=i/n;
							int j2=i%n;
							String j3="";
							if(j2>=0 && j2<=9)
								j3="0"+j1;
							else
								j3=""+j1;
							move1="R"+((char)(j2+65))+j3; //B03
							
							if(depth==d)
								rmove=move1;
							else 
								rmove=move;
							//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
							String rvalue=maxvalue(rpos,rmove,depth-1);
							String v2=rvalue.substring(4);
							//v=Math.max(v,Integer.parseInt(v2));
							if(v>Integer.parseInt(v2) ){
								v=Integer.parseInt(v2);
								rmove2=rvalue.substring(0,4);
							}
							//System.out.println("inside max value");
						}
						
					}
				}
				//System.out.println(v);
				return rmove2+v;
			}
			else //start==X
			{
				int v=Integer.MAX_VALUE;
				String move1="";
				String rmove="";
				String rmove2="    ";
				//all possible stakes
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						StringBuilder dummy=new StringBuilder(curpos);
						dummy.setCharAt(i,'O');
						String rpos=new String(dummy);
						int j1=i/n;
						int j2=i%n;
						String j3="";
						if(j2>=0 && j2<=9)
							j3="0"+j1;
						else
							j3=""+j1;
						move1="S"+((char)(j2+65))+j3; //B03
						
						if(depth==d)
							rmove=move1;
						else 
							rmove=move;
						//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
						String rvalue=maxvalue(rpos,rmove,depth-1);
						String v2=rvalue.substring(4);
						//v=Math.min(v,Integer.parseInt(v2));
						if(v>Integer.parseInt(v2) ){
							v=Integer.parseInt(v2);
							rmove2=rvalue.substring(0,4);
						}
						//System.out.println("inside min value");
					}
				}
				
				//all possible raids
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						int a[]=getSur(i); //get i1,i2,i3,i4
						boolean isRaidPos=false;
						for(int q=0;q<a.length;q++)
						{
							if(a[q]!=-1 && curpos.charAt(a[q])=='O')
							{
								isRaidPos=true;
								break;
							}
						}
						if(isRaidPos){
							isRaidPos=false;
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='X')
								{
									isRaidPos=true;
									break;
								}
							}
						}
						if(isRaidPos)	//then raid
						{
							StringBuilder dummy=new StringBuilder(curpos);
							dummy.setCharAt(i,'O');
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='X')
									dummy.setCharAt(a[q],'O');
							}
							String rpos=new String(dummy);
							int j1=i/n;
							int j2=i%n;
							String j3="";
							if(j2>=0 && j2<=9)
								j3="0"+j1;
							else
								j3=""+j1;
							move1="R"+((char)(j2+65))+j3; //B03
							
							if(depth==d)
								rmove=move1;
							else 
								rmove=move;
							//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
							String rvalue=maxvalue(rpos,rmove,depth-1);
							String v2=rvalue.substring(4);
							//v=Math.max(v,Integer.parseInt(v2));
							if(v>Integer.parseInt(v2) ){
								v=Integer.parseInt(v2);
								rmove2=rvalue.substring(0,4);
							}
							//System.out.println("inside max value");
						}
						
					}
				}
				//System.out.println(v);
				return rmove2+v;
			}
		}
	}
	
	static int[] getSur(int i)
	{
		int i1=-1;	//right
		int i2=-1;	//left
		int i3=-1;	//top
		int i4=-1;	//bottom
		if(i/n==0 && i%n==0)
		{
			i1=i+1;
			i2=-1;
			i3=-1;
			i4=i+n;
		}
		else if(i/n==0 && i%n==(n-1))
		{
			i1=-1;
			i2=i-1;
			i3=-1;
			i4=i+n;
		}
		else if(i/n==(n-1) && i%n==0)
		{
			i1=i+1;
			i2=-1;
			i3=i-n;
			i4=-1;
		}
		else if(i/n==(n-1) && i%n==(n-1))
		{
			i1=-1;
			i2=i-1;
			i3=i-n;
			i4=-1;
		}
		else if(i/n==0)
		{
			i1=i+1;
			i2=i-1;
			i3=-1;
			i4=i+n;
		}
		else if(i%n==(n-1))
		{
			i1=-1;
			i2=i-1;
			i3=i-n;
			i4=i+n;
		}
		else if(i/n==(n-1))
		{
			i1=i+1;
			i2=i-1;
			i3=i-n;
			i4=-1;
		}
		else if(i%n==0)
		{
			i1=i+1;
			i2=-1;
			i3=i-n;
			i4=i+n;
		}
		else  //cneter elements
		{
			i1=i+1;	//right
			i2=i-1;	//left
			i3=i-n;	//top
			i4=i+n;	//bottom
		}
		int a[]=new int[4];
		a[0]=i1;
		a[1]=i2;
		a[2]=i3;
		a[3]=i4;
		return a;
	}
	
	//ALpha Beta
	static String alphabetasearch(String startpos)
	{
		String v=abmaxvalue(startpos,null,d,Integer.MIN_VALUE,Integer.MAX_VALUE);
		return v;
	}
	
	static String abmaxvalue(String curpos,String move,int depth,int alpha,int beta)		//check for start with X or O
	{
		//System.out.println(curpos);
		if(depth==0 || isBoardFull(curpos))
		{
			//System.out.println(evaluateBoard(curpos));
			return move+evaluateBoard(curpos);
		}
		else
		{
			if(start=='O')
			{
				int v=Integer.MIN_VALUE;
				String move1="";
				String rmove="";
				String rmove2="    ";
				//all possible stakes
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						StringBuilder dummy=new StringBuilder(curpos);
						dummy.setCharAt(i,'O');
						String rpos=new String(dummy);
						//String rpos=curpos.replace(curpos.charAt(i),'O');
						int j1=i/n;
						int j2=i%n;
						String j3="";
						if(j1>=0 && j1<=9)
							j3="0"+j1;
						else
							j3=""+j1;
						move1="S"+((char)(j2+65))+j3; //B03
						if(depth==d)
							rmove=move1;
						else 
							rmove=move;
						String rvalue=abminvalue(rpos,rmove,depth-1,alpha,beta);
						String v2=rvalue.substring(4);
						if(v<Integer.parseInt(v2) ){
							v=Integer.parseInt(v2);
							rmove2=rvalue.substring(0,4);
						}
						if(v>=beta)
							return rmove2+v;
						alpha=Math.max(alpha, v);
						//System.out.println(rmove);
						//v=Math.max(v,Integer.parseInt(v2));
						//System.out.println(v2);
						
						//System.out.println("inside max vuale");
					}
					
					
				}
				//all possible raids
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						int a[]=getSur(i); //get i1,i2,i3,i4
						boolean isRaidPos=false;
						for(int q=0;q<a.length;q++)
						{
							if(a[q]!=-1 && curpos.charAt(a[q])=='O')
							{
								isRaidPos=true;
								break;
							}
						}
						if(isRaidPos){
							isRaidPos=false;
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='X')
								{
									isRaidPos=true;
									break;
								}
							}
						}
						if(isRaidPos)	//then raid
						{
							StringBuilder dummy=new StringBuilder(curpos);
							dummy.setCharAt(i,'O');
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='X')
									dummy.setCharAt(a[q],'O');
							}
							String rpos=new String(dummy);
							int j1=i/n;
							int j2=i%n;
							String j3="";
							if(j2>=0 && j2<=9)
								j3="0"+j1;
							else
								j3=""+j1;
							move1="R"+((char)(j2+65))+j3; //B03
							
							if(depth==d)
								rmove=move1;
							else 
								rmove=move;
							//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
							String rvalue=abminvalue(rpos,rmove,depth-1,alpha,beta);
							String v2=rvalue.substring(4);
							//v=Math.max(v,Integer.parseInt(v2));
							
							if(v<Integer.parseInt(v2) ){
								v=Integer.parseInt(v2);
								rmove2=rvalue.substring(0,4);
							}
							if(v>=beta)
								return rmove2+v;
							alpha=Math.max(alpha, v);
							//System.out.println("inside max value");
						}
						
					}
				}
				//System.out.println(v);
				return rmove2+v;
			}
			else //start== x
			{
				int v=Integer.MIN_VALUE;
				String move1="";
				String rmove="";
				String rmove2="    ";
				//all possible stakes
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						StringBuilder dummy=new StringBuilder(curpos);
						dummy.setCharAt(i,'X');
						String rpos=new String(dummy);
						//String rpos=curpos.replace(curpos.charAt(i),'O');
						int j1=i/n;
						int j2=i%n;
						String j3="";
						if(j1>=0 && j1<=9)
							j3="0"+j1;
						else
							j3=""+j1;
						move1="S"+((char)(j2+65))+j3; //B03
						if(depth==d)
							rmove=move1;
						else 
							rmove=move;
						String rvalue=abminvalue(rpos,rmove,depth-1,alpha,beta);
						String v2=rvalue.substring(4);
						//System.out.println(rmove);
						//v=Math.max(v,Integer.parseInt(v2));
						//System.out.println(v2);
						if(v<Integer.parseInt(v2) ){
							v=Integer.parseInt(v2);
							rmove2=rvalue.substring(0,4);
						}
						if(v>=beta)
							return rmove2+v;
						alpha=Math.max(alpha, v);
						//System.out.println("inside max vuale");
					}
					
				}
				//all possible raids
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						int a[]=getSur(i); //get i1,i2,i3,i4
						boolean isRaidPos=false;
						for(int q=0;q<a.length;q++)
						{
							if(a[q]!=-1 && curpos.charAt(a[q])=='X')
							{
								isRaidPos=true;
								break;
							}
						}
						if(isRaidPos){
							isRaidPos=false;
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='O')
								{
									isRaidPos=true;
									break;
								}
							}
						}
						if(isRaidPos)	//then raid
						{
							StringBuilder dummy=new StringBuilder(curpos);
							dummy.setCharAt(i,'X');
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='O')
									dummy.setCharAt(a[q],'X');
							}
							String rpos=new String(dummy);
							int j1=i/n;
							int j2=i%n;
							String j3="";
							if(j2>=0 && j2<=9)
								j3="0"+j1;
							else
								j3=""+j1;
							move1="R"+((char)(j2+65))+j3; //B03
							
							if(depth==d)
								rmove=move1;
							else 
								rmove=move;
							//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
							String rvalue=abminvalue(rpos,rmove,depth-1,alpha,beta);
							String v2=rvalue.substring(4);
							//v=Math.max(v,Integer.parseInt(v2));
							if(v<Integer.parseInt(v2) ){
								v=Integer.parseInt(v2);
								rmove2=rvalue.substring(0,4);
							}
							if(v>=beta)
								return rmove2+v;
							alpha=Math.max(alpha, v);
							//System.out.println("inside max value");
						}
						
					}
				}
				//System.out.println(v);
				return rmove2+v;
			}
		}
	}
	
	static String abminvalue(String curpos,String move, int depth,int alpha,int beta)
	{
		//System.out.println(curpos);
		if(depth==0 || isBoardFull(curpos))
			return move+evaluateBoard(curpos);
		else
		{
			if(start=='O')
			{
				int v=Integer.MAX_VALUE;
				String move1="";
				String rmove="";
				String rmove2="    ";
				//all possible stakes
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						StringBuilder dummy=new StringBuilder(curpos);
						dummy.setCharAt(i,'X');
						String rpos=new String(dummy);
						int j1=i/n;
						int j2=i%n;
						String j3="";
						if(j2>=0 && j2<=9)
							j3="0"+j1;
						else
							j3=""+j1;
						move1="S"+((char)(j2+65))+j3; //B03
						
						if(depth==d)
							rmove=move1;
						else 
							rmove=move;
						//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
						String rvalue=abmaxvalue(rpos,rmove,depth-1,alpha,beta);
						String v2=rvalue.substring(4);
						//v=Math.min(v,Integer.parseInt(v2));
						if(v>Integer.parseInt(v2) ){
							v=Integer.parseInt(v2);
							rmove2=rvalue.substring(0,4);
						}
						if(v<=alpha)
							return rmove2+v;
						beta=Math.min(beta, v);
						//System.out.println("inside min value");
					}
				}
				
				//all possible raids
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						int a[]=getSur(i); //get i1,i2,i3,i4
						boolean isRaidPos=false;
						for(int q=0;q<a.length;q++)
						{
							if(a[q]!=-1 && curpos.charAt(a[q])=='X')
							{
								isRaidPos=true;
								break;
							}
						}
						if(isRaidPos){
							isRaidPos=false;
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='O')
								{
									isRaidPos=true;
									break;
								}
							}
						}
						if(isRaidPos)	//then raid
						{
							StringBuilder dummy=new StringBuilder(curpos);
							dummy.setCharAt(i,'X');
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='O')
									dummy.setCharAt(a[q],'X');
							}
							String rpos=new String(dummy);
							int j1=i/n;
							int j2=i%n;
							String j3="";
							if(j2>=0 && j2<=9)
								j3="0"+j1;
							else
								j3=""+j1;
							move1="R"+((char)(j2+65))+j3; //B03
							
							if(depth==d)
								rmove=move1;
							else 
								rmove=move;
							//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
							String rvalue=abmaxvalue(rpos,rmove,depth-1,alpha,beta);
							String v2=rvalue.substring(4);
							//v=Math.max(v,Integer.parseInt(v2));
							if(v>Integer.parseInt(v2) ){
								v=Integer.parseInt(v2);
								rmove2=rvalue.substring(0,4);
							}
							if(v<=alpha)
								return rmove2+v;
							beta=Math.min(beta, v);
							//System.out.println("inside max value");
						}
						
					}
				}
				//System.out.println(v);
				return rmove2+v;
			}
			else //start==X
			{
				int v=Integer.MAX_VALUE;
				String move1="";
				String rmove="";
				String rmove2="    ";
				//all possible stakes
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						StringBuilder dummy=new StringBuilder(curpos);
						dummy.setCharAt(i,'O');
						String rpos=new String(dummy);
						int j1=i/n;
						int j2=i%n;
						String j3="";
						if(j2>=0 && j2<=9)
							j3="0"+j1;
						else
							j3=""+j1;
						move1="S"+((char)(j2+65))+j3; //B03
						
						if(depth==d)
							rmove=move1;
						else 
							rmove=move;
						//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
						String rvalue=maxvalue(rpos,rmove,depth-1);
						String v2=rvalue.substring(4);
						//v=Math.min(v,Integer.parseInt(v2));
						if(v>Integer.parseInt(v2) ){
							v=Integer.parseInt(v2);
							rmove2=rvalue.substring(0,4);
						}
						if(v<=alpha)
							return rmove2+v;
						beta=Math.min(beta, v);
						//System.out.println("inside min value");
					}
				}
				
				//all possible raids
				for(int i=0;i<curpos.length();i++)
				{
					if(curpos.charAt(i)=='.')
					{
						int a[]=getSur(i); //get i1,i2,i3,i4
						boolean isRaidPos=false;
						for(int q=0;q<a.length;q++)
						{
							if(a[q]!=-1 && curpos.charAt(a[q])=='O')
							{
								isRaidPos=true;
								break;
							}
						}
						if(isRaidPos){
							isRaidPos=false;
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='X')
								{
									isRaidPos=true;
									break;
								}
							}
						}
						if(isRaidPos)	//then raid
						{
							StringBuilder dummy=new StringBuilder(curpos);
							dummy.setCharAt(i,'O');
							for(int q=0;q<a.length;q++)
							{
								if(a[q]!=-1 && curpos.charAt(a[q])=='X')
									dummy.setCharAt(a[q],'O');
							}
							String rpos=new String(dummy);
							int j1=i/n;
							int j2=i%n;
							String j3="";
							if(j2>=0 && j2<=9)
								j3="0"+j1;
							else
								j3=""+j1;
							move1="R"+((char)(j2+65))+j3; //B03
							
							if(depth==d)
								rmove=move1;
							else 
								rmove=move;
							//v=Math.min(v,Integer.parseInt(maxvalue(rpos,move1,depth-1).substring(3)));
							String rvalue=maxvalue(rpos,rmove,depth-1);
							String v2=rvalue.substring(4);
							//v=Math.max(v,Integer.parseInt(v2));
							if(v>Integer.parseInt(v2) ){
								v=Integer.parseInt(v2);
								rmove2=rvalue.substring(0,4);
							}
							if(v<=alpha)
								return rmove2+v;
							beta=Math.min(beta, v);
							//System.out.println("inside max value");
						}
						
					}
				}
				//System.out.println(v);
				return rmove2+v;
			}
		}
	}
	
	
	
	public static void main(String[] arg){
		
		FileInputStream fstream;
		List<String> l1=new ArrayList<String>();
		try {
			fstream = new FileInputStream("input.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			while ((strLine = br.readLine()) != null)   {
				//System.out.println (strLine);
				l1.add(strLine);
			}
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(l1);
		n=Integer.parseInt(l1.get(0));
		String algo=l1.get(1);
		start=l1.get(2).charAt(0);
		int depth=Integer.parseInt(l1.get(3));
		//int depth=2;
		d=depth;
		bvalue=new int[n][n];
		//bpos=new char[n][n];
		for(int i=0;i<n;i++)
		{
			String sp[]=l1.get(i+4).split(" ");
			for(int j=0;j<sp.length;j++)
				bvalue[i][j]=Integer.parseInt(sp[j]);
		}
		for(int i=0;i<n;i++)
		{
			String sp[]=l1.get(i+4+n).split(" ");
			for(int j=0;j<sp.length;j++)
			{
				//bpos[i][j]=sp[j].charAt(0);
				startpos=startpos.concat(sp[j]);
			}
		}
		
/*		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
				System.out.print(bvalue[i][j]+" ");
			System.out.println();
		}
		System.out.println(startpos);*/
		
/*		System.out.println(startpos);
		System.out.println(evaluateBoard(startpos,start));*/
		String out1="";
		if(algo.equals("MINIMAX"))
			out1=minimaxdecision(startpos);
		else if(algo.equals("ALPHABETA"))
			out1=alphabetasearch(startpos);
	
		//System.out.println(out1);
		String printmove="";
		if(out1.substring(0,1).equals("S"))
			printmove="Stake";
		else
			printmove="Raid";
		String col=out1.substring(1,2);
		String rowString=out1.substring(2,4);
		int row=Integer.parseInt(rowString);
		int row1=row+1;
		
		
		//System.out.println(col+row1+" "+printmove);
		String endpos="";
		if(start=='X')
		{
			if(printmove.equals("Stake"))
			{
				int j1=((int)col.charAt(0))-65;
				int j2=row;
				int j3=j2*n+j1;
				StringBuilder dummy=new StringBuilder(startpos);
				dummy.setCharAt(j3,'X');
				endpos=new String(dummy);
			}
			else
			{
				int j1=((int)col.charAt(0))-65;
				int j2=row;
				int j3=j2*n+j1;
				StringBuilder dummy=new StringBuilder(startpos);
				dummy.setCharAt(j3,'X');
				int sur[]=getSur(j3);
				for(int q=0;q<sur.length;q++)
				{
					if(sur[q]!=-1 && startpos.charAt(sur[q])=='O')
						dummy.setCharAt(sur[q],'X');
				}
				
				endpos=new String(dummy);
			}
		}
		else//O's turn
		{
			if(printmove.equals("Stake"))
			{
				int j1=((int)col.charAt(0))-65;
				int j2=row;
				int j3=j2*n+j1;
				StringBuilder dummy=new StringBuilder(startpos);
				dummy.setCharAt(j3,'O');
				endpos=new String(dummy);
			}
			else
			{
				int j1=((int)col.charAt(0))-65;
				int j2=row;
				int j3=j2*n+j1;
				StringBuilder dummy=new StringBuilder(startpos);
				dummy.setCharAt(j3,'O');
				int sur[]=getSur(j3);
				for(int q=0;q<sur.length;q++)
				{
					if(sur[q]!=-1 && startpos.charAt(sur[q])=='X')
						dummy.setCharAt(sur[q],'O');
				}
				
				endpos=new String(dummy);
			}
		}
		
		//System.out.println(endpos);
		PrintWriter writer;
		try {
			writer = new PrintWriter("output.txt");
			writer.println(col+row1+" "+printmove);
			for(int i=0;i<endpos.length();i++)
			{
				if(i%n==0 && i!=0)
					writer.println();
				writer.print(endpos.charAt(i));
			}
			writer.close();
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
