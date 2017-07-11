
/*
	 * @author: Abhinaya Ramachandran
	 * Completely fair scheduler
	 * 
	 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileReaderClass {
	
	public static RedBlackTree rbt;
	public static HeapImpl hp;
	public static int period;
	public static int node_count=0;
	public static int n;
	
	public static void main(String[] args) throws FileNotFoundException
	{
			//rbt first node
			rbt=new RedBlackTree();
			ArrayList<Process> processes=new ArrayList<Process>();
			
			//Scanner scanner = new Scanner(new File("MySampleInput.txt"));
			Scanner scanner = new Scanner(new File("Input10000.txt"));
			Scanner consoleInput =new Scanner(System.in);
			n=scanner.nextInt();
			period=scanner.nextInt();
			hp=new HeapImpl();
			System.out.println("Number of inputs="+n);
			System.out.println("Quantum period= "+period);
			System.out.println("\nChoose a metric:\n1->Time in CPU\n2->Remaining Execution Time \n3->Time in CPU +Remaining Execution Time  ");
			int ch=consoleInput.nextInt();
			
			
			for(int j=0;j<n;j++)
			{
				int l=0,m=0,n=0;
			processes.add(new Process(hp,l=scanner.nextInt(),m=scanner.nextInt(),n=scanner.nextInt()));
			processes.add(new Process(rbt,l,m,n));
			node_count++;
			
			}
						
			consoleInput.close();
			
			scanner.close();			
			
			 Scheduler sc=new Scheduler();
		    if(ch==1)
		    {
		    sc.scheduleHeap(hp);
		    sc.scheduleRBTree(rbt);
		    }
		    else if(ch==2)
		    {
		    	sc.scheduleHeapRe(hp);
		    	sc.scheduleRBTreeWtRe(rbt);
		    }
		    else if(ch==3)
		    {
		    	sc.scheduleHeapBoth(hp);
		    	sc.scheduleRBTreeBoth(rbt);
		    }
		    else 
		    {
		    	System.out.println("\nWrong Input");
		    }
	}
						}
