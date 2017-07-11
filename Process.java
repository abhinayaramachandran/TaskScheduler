

/*
	 * @author: Abhinaya Ramachandran
	 * Completely fair scheduler
	 * 
	 */
	
public class Process {
	
	
	public int processId;
	public long execTime;
	public long timeInCPU;
	public long waitTime;
	public long arrivalTime;
	public RedBlackTree rbt;
	public long unfairness;
	public long startTime;
	public HeapImpl heap;
	public long turnAroundTime;
	
	public Process(HeapImpl hp,int newId,long newArrivalTime, long newExecTime){
		
		timeInCPU=0;
		turnAroundTime=0;
		
		processId=newId;
		execTime=newExecTime;
		arrivalTime=newArrivalTime;
		waitTime=arrivalTime;
		unfairness=arrivalTime;
		


		heap=hp;
		heap.insert(this);
	
		
	}
	public Process(RedBlackTree rbtree,int newId,long newArrivalTime, long newExecTime){
		
		timeInCPU=0;
		
		processId=newId;
		execTime=newExecTime;
		arrivalTime=newArrivalTime;
		waitTime=arrivalTime;
		unfairness=arrivalTime;
		
		rbt=rbtree;
		rbt.insert(this);
		


		
	}

		
	
}
