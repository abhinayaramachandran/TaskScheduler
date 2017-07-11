public class Scheduler {
	/*
	 * @author: Abhinaya Ramachandran
	 * Completely fair scheduler
	 */
	
	private int quantumTime=FileReaderClass.period;
	
	Process p;
	public static long CLOCK=0;
	public static long CLOCK2=0;

		
		
	
	// Impl: Heap-tim in cpu
	public void scheduleHeap(HeapImpl hp)
	{
		long totalWaitTime=0;
		long totalTurnAroundTime=0;
		long timeKeeper=0;
		 
		System.out.println("\n\nScheduling using Min-Heap data structure...");
		System.out.println("\nScheduling Metric/Unfairness Measure--Time In Processor--");
		
		while(hp.getSize()>=1){
			long start = System.nanoTime();
			p=hp.remove();
		
			if(p.execTime>quantumTime)
			{
				
				p.unfairness=p.unfairness+quantumTime;
				p.timeInCPU=p.timeInCPU+quantumTime;
				p.execTime=p.execTime-quantumTime;
				CLOCK=CLOCK+quantumTime;
				p.waitTime=CLOCK- p.arrivalTime- p.timeInCPU; 
				
				if(p.execTime>0)
				{
					hp.insert(p);
				}
				else
				{
					
					totalWaitTime+=p.waitTime;
					p.turnAroundTime=CLOCK-p.arrivalTime;
					totalTurnAroundTime+=p.turnAroundTime;
				}
				
			}
			else
			{
				
				p.timeInCPU=p.timeInCPU+p.execTime;
				CLOCK=CLOCK+p.execTime;
				p.waitTime=CLOCK-p.arrivalTime-p.timeInCPU;  
				p.execTime=0;	
				totalWaitTime+=p.waitTime;
				p.turnAroundTime=CLOCK-p.arrivalTime;
				totalTurnAroundTime+=p.turnAroundTime;
				
			}
			long end = System.nanoTime();
			timeKeeper+=(end-start);
		//hp.print();
		
		}
		
		long timeUnit=timeKeeper/CLOCK;
		float thr=((float)FileReaderClass.n*100000)/((float)timeKeeper);
		System.out.println("\nCOMPLETELY FAIR SCHEDULING USING BINARY HEAP- PERFORMANCE METRICS ");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\n1.Total Number of inputs :"+FileReaderClass.n);
		System.out.println("\n2.Total Running Time: "+ timeKeeper+ " nano seconds");
		System.out.println("\n3.Running time per process:" +timeKeeper/FileReaderClass.n+ " nano seconds");
		System.out.println("\n4.Total Wait Time :"+totalWaitTime*timeUnit+" nano seconds");
		System.out.println("\n5.Average Wait Time :"+(totalWaitTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.println("\n6.Total turn around time: " +totalTurnAroundTime*timeUnit+" nano seconds");
		System.out.println("\n7.Average turn around time: " +(totalTurnAroundTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.printf("\n8.Throughput: %.2f  tasks/millisecond",thr);
		
		
		
	}

	
		
	
	
	public void scheduleRBTree(RedBlackTree rt)
	{
		
		 long totalWaitTime=0;
		 long totalTurnAroundTime=0;
		 long timeKeeper=0;
		System.out.println("\n\nScheduling using red black tree data structure...\n");
		System.out.println("\nScheduling Metric/Unfairness Measure--Time In Processor--");
		
		
		while(RedBlackTree.NodeCount>1){
			 long start = System.nanoTime();
			p=rt.delete().process;
				
			
			if(p.execTime>quantumTime)
			{
				p.unfairness=p.unfairness+quantumTime;
				p.timeInCPU=p.timeInCPU+quantumTime;
				p.execTime=p.execTime-quantumTime;
				CLOCK2=CLOCK2+quantumTime;
				p.waitTime=CLOCK2- p.arrivalTime- p.timeInCPU; 
				if(p.execTime>0)
				{
					rt.insert(p);
				}
				else
				{
					
					totalWaitTime+=p.waitTime;
					p.turnAroundTime=CLOCK2-p.arrivalTime;
					totalTurnAroundTime+=p.turnAroundTime;
				}
				
			}
			else
			{
				p.timeInCPU=p.timeInCPU+p.execTime;
				CLOCK2=CLOCK2+p.execTime;
				p.waitTime=CLOCK2-p.arrivalTime-p.timeInCPU;  
				p.execTime=0;	
				totalWaitTime+=p.waitTime;
				p.turnAroundTime=CLOCK2-p.arrivalTime;
				totalTurnAroundTime+=p.turnAroundTime;
				

			}
			
			long end = System.nanoTime();
			timeKeeper+=(end-start);
//		rt.printRBtree();
//		System.out.println("");
//	
		}
		
		float thr=((float)FileReaderClass.n*100000)/((float)timeKeeper);
		long timeUnit=timeKeeper/CLOCK2;
		System.out.println("\nCOMPLETELY FAIR SCHEDULING USING RED BLACK TREES- PERFORMANCE METRICS ");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\n1.Total Number of inputs :"+FileReaderClass.n);
		System.out.println("\n2.Total Running Time: "+ timeKeeper+ " nano seconds");
		System.out.println("\n3.Running time per process:" +timeKeeper/FileReaderClass.n+ " nano seconds");
		System.out.println("\n4.Total Wait Time :"+totalWaitTime*timeUnit+" nano seconds");
		System.out.println("\n5.Average Wait Time :"+(totalWaitTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.println("\n6.Total turn around time: " +totalTurnAroundTime*timeUnit+" nano seconds");
		System.out.println("\n7.Average turn around time: " +(totalTurnAroundTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.printf("\n8.Throughput: %.2f tasks/millisecond",thr);
		
		
	}

	public void scheduleHeapRe(HeapImpl hp) {
	
		long totalWaitTime=0;
		long totalTurnAroundTime=0;
		long timeKeeper=0;
		 
		System.out.println("\n\nScheduling using heap data structure...");
		System.out.println("\nScheduling Metric/Unfairness Measure--Remaining execution time--");
		
		while(hp.getSize()>=1){
			long start = System.nanoTime();
			p=hp.remove();
		
			if(p.execTime>quantumTime)
			{
				
				
				p.timeInCPU=p.timeInCPU+quantumTime;
				p.execTime=p.execTime-quantumTime;
				CLOCK=CLOCK+quantumTime;
				p.waitTime=CLOCK- p.arrivalTime- p.timeInCPU; 
				p.unfairness=p.unfairness+p.execTime;
				if(p.execTime>0)
				{
					hp.insert(p);
				}
				else
				{
					
					totalWaitTime+=p.waitTime;
					p.turnAroundTime=CLOCK-p.arrivalTime;
					totalTurnAroundTime+=p.turnAroundTime;
					
				}
				
			}
			else
			{
				
				p.timeInCPU=p.timeInCPU+p.execTime;
				CLOCK=CLOCK+p.execTime;
				p.waitTime=CLOCK-p.arrivalTime-p.timeInCPU;  
				p.execTime=0;	
				totalWaitTime+=p.waitTime;
				p.turnAroundTime=CLOCK-p.arrivalTime;
				totalTurnAroundTime+=p.turnAroundTime;
				p.unfairness=p.unfairness+p.execTime;
				
			}
			long end = System.nanoTime();
			timeKeeper+=(end-start);
		//hp.print();
		
		}
		
		long timeUnit=timeKeeper/CLOCK;
		float thr=((float)FileReaderClass.n*100000)/((float)timeKeeper);
		System.out.println("\nCOMPLETELY FAIR SCHEDULING USING BINARY HEAP- PERFORMANCE METRICS ");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\n1.Total Number of inputs :"+FileReaderClass.n);
		System.out.println("\n2.Total Running Time: "+ timeKeeper+ " nano seconds");
		System.out.println("\n3.Running time per process:" +timeKeeper/FileReaderClass.n+ " nano seconds");
		System.out.println("\n4.Total Wait Time :"+totalWaitTime*timeUnit+" nano seconds");
		System.out.println("\n5.Average Wait Time :"+(totalWaitTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.println("\n6.Total turn around time: " +totalTurnAroundTime*timeUnit+" nano seconds");
		System.out.println("\n7.Average turn around time: " +(totalTurnAroundTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.printf("\n8.Throughput: %.2f tasks/millisecond",thr);
		
	}

	// Impl RBTree
	public void scheduleRBTreeWtRe(RedBlackTree rt) {
		
		
		 long totalWaitTime=0;
		 long totalTurnAroundTime=0;
		 long timeKeeper=0;
		 
		System.out.println("\n\nScheduling using heap red black tree data structure...\n");
		System.out.println("\nScheduling Metric/Unfairness measure--Remaining execution time--");
		
		
		while(RedBlackTree.NodeCount>1){
			long start = System.nanoTime();
			p=rt.delete().process;
				
			
			if(p.execTime>quantumTime)
			{
				
				p.timeInCPU=p.timeInCPU+quantumTime;
				p.execTime=p.execTime-quantumTime;
				CLOCK2=CLOCK2+quantumTime;
				p.waitTime=CLOCK2- p.arrivalTime- p.timeInCPU; 
				p.unfairness=p.unfairness+p.execTime;
				if(p.execTime>0)
				{
					rt.insert(p);
				}
				else
				{
					
					totalWaitTime+=p.waitTime;
					p.turnAroundTime=CLOCK2-p.arrivalTime;
					totalTurnAroundTime+=p.turnAroundTime;
				
				}
				
			}
			else
			{
				p.timeInCPU=p.timeInCPU+p.execTime;
				CLOCK2=CLOCK2+p.execTime;
				p.waitTime=CLOCK2-p.arrivalTime-p.timeInCPU;  
				p.execTime=0;	
				totalWaitTime+=p.waitTime;
				p.turnAroundTime=CLOCK2-p.arrivalTime;
				totalTurnAroundTime+=p.turnAroundTime;
				p.unfairness=p.unfairness+p.execTime;

			}
			long end = System.nanoTime();
			timeKeeper+=(end-start);
			
//		rt.printRBtree();
//		System.out.println("");
//	
		}
		
		float thr=((float)FileReaderClass.n*100000)/((float)timeKeeper);
		long timeUnit=timeKeeper/CLOCK2;
		System.out.println("\nCOMPLETELY FAIR SCHEDULING USING RED BLACK TREES- PERFORMANCE METRICS ");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\n1.Total Number of inputs :"+FileReaderClass.n);
		System.out.println("\n2.Total Running Time: "+ timeKeeper+ " nano seconds");
		System.out.println("\n3.Running time per process:" +timeKeeper/FileReaderClass.n+ " nano seconds");
		System.out.println("\n4.Total Wait Time :"+totalWaitTime*timeUnit+" nano seconds");
		System.out.println("\n5.Average Wait Time :"+(totalWaitTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.println("\n6.Total turn around time: " +totalTurnAroundTime*timeUnit+" nano seconds");
		System.out.println("\n7.Average turn around time: " +(totalTurnAroundTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.printf("\n8.Throughput: %.2f tasks/millisecond",thr);
		
		
	}

	
	public void scheduleHeapBoth(HeapImpl hp)
	{
		long totalWaitTime=0;
		long totalTurnAroundTime=0;
		long timeKeeper=0;
		 
		System.out.println("\n\nScheduling using Min-Heap data structure...");
		System.out.println("\nScheduling Metric/Unfairness Measure--Time In Processor+ Remaining Execution Time--");
		
		while(hp.getSize()>=1){
			long start = System.nanoTime();
			p=hp.remove();
		
			if(p.execTime>quantumTime)
			{
				
				
				p.timeInCPU=p.timeInCPU+quantumTime;
				p.execTime=p.execTime-quantumTime;
				CLOCK=CLOCK+quantumTime;
				p.waitTime=CLOCK- p.arrivalTime- p.timeInCPU; 
				p.unfairness=p.unfairness+quantumTime+p.execTime;
				
				if(p.execTime>0)
				{
					hp.insert(p);
				}
				else
				{
					
					totalWaitTime+=p.waitTime;
					p.turnAroundTime=CLOCK-p.arrivalTime;
					totalTurnAroundTime+=p.turnAroundTime;
				}
				
			}
			else
			{
				
				p.timeInCPU=p.timeInCPU+p.execTime;
				CLOCK=CLOCK+p.execTime;
				p.waitTime=CLOCK-p.arrivalTime-p.timeInCPU;  
				p.execTime=0;	
				totalWaitTime+=p.waitTime;
				p.turnAroundTime=CLOCK-p.arrivalTime;
				totalTurnAroundTime+=p.turnAroundTime;
				
			}
			long end = System.nanoTime();
			timeKeeper+=(end-start);
		//hp.print();
		
		}
		
		long timeUnit=timeKeeper/CLOCK;
		float thr=((float)FileReaderClass.n*100000)/((float)timeKeeper);
		System.out.println("\nCOMPLETELY FAIR SCHEDULING USING BINARY HEAP- PERFORMANCE METRICS ");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\n1.Total Number of inputs :"+FileReaderClass.n);
		System.out.println("\n2.Total Running Time: "+ timeKeeper+ " nano seconds");
		System.out.println("\n3.Running time per process:" +timeKeeper/FileReaderClass.n+ " nano seconds");
		System.out.println("\n4.Total Wait Time :"+totalWaitTime*timeUnit+" nano seconds");
		System.out.println("\n5.Average Wait Time :"+(totalWaitTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.println("\n6.Total turn around time: " +totalTurnAroundTime*timeUnit+" nano seconds");
		System.out.println("\n7.Average turn around time: " +(totalTurnAroundTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.printf("\n8.Throughput: %.2f  tasks/millisecond",thr);
		
		
		
	}

	
		
	
	
	public void scheduleRBTreeBoth(RedBlackTree rt)
	{
		
		 long totalWaitTime=0;
		 long totalTurnAroundTime=0;
		 long timeKeeper=0;
		System.out.println("\n\nScheduling using red black tree data structure...\n");
		System.out.println("\nScheduling Metric/Unfairness Measure--Time In Processor + Remaining Execution Time--");
		
		
		while(RedBlackTree.NodeCount>1){
			 long start = System.nanoTime();
			p=rt.delete().process;
				
			
			if(p.execTime>quantumTime)
			{
				
				p.timeInCPU=p.timeInCPU+quantumTime;
				p.execTime=p.execTime-quantumTime;
				CLOCK2=CLOCK2+quantumTime;
				p.waitTime=CLOCK2- p.arrivalTime- p.timeInCPU;
				p.unfairness=p.unfairness+quantumTime+p.execTime;
				if(p.execTime>0)
				{
					rt.insert(p);
				}
				else
				{
					
					totalWaitTime+=p.waitTime;
					p.turnAroundTime=CLOCK2-p.arrivalTime;
					totalTurnAroundTime+=p.turnAroundTime;
				}
				
			}
			else
			{
				p.timeInCPU=p.timeInCPU+p.execTime;
				CLOCK2=CLOCK2+p.execTime;
				p.waitTime=CLOCK2-p.arrivalTime-p.timeInCPU;  
				p.execTime=0;	
				totalWaitTime+=p.waitTime;
				p.turnAroundTime=CLOCK2-p.arrivalTime;
				totalTurnAroundTime+=p.turnAroundTime;
				

			}
			
			long end = System.nanoTime();
			timeKeeper+=(end-start);
//		rt.printRBtree();
//		System.out.println("");
//	
		}
		
		float thr=((float)FileReaderClass.n*100000)/((float)timeKeeper);
		long timeUnit=timeKeeper/CLOCK2;
		System.out.println("\nCOMPLETELY FAIR SCHEDULING USING RED BLACK TREES- PERFORMANCE METRICS ");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("\n1.Total Number of inputs :"+FileReaderClass.n);
		System.out.println("\n2.Total Running Time: "+ timeKeeper+ " nano seconds");
		System.out.println("\n3.Running time per process:" +timeKeeper/FileReaderClass.n+ " nano seconds");
		System.out.println("\n4.Total Wait Time :"+totalWaitTime*timeUnit+" nano seconds");
		System.out.println("\n5.Average Wait Time :"+(totalWaitTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.println("\n6.Total turn around time: " +totalTurnAroundTime*timeUnit+" nano seconds");
		System.out.println("\n7.Average turn around time: " +(totalTurnAroundTime/FileReaderClass.n)*timeUnit+" nano seconds");
		System.out.printf("\n8.Throughput: %.2f tasks/millisecond",thr);
		
		
	}


}
