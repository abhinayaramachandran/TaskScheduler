

/*
	 * @author: Abhinaya Ramachandran
	 * Completely fair scheduler
	 * 
	 */

import java.util.ArrayList;

public class HeapImpl
{
    private static ArrayList<Process> Heap;
    private static final int FRONT = 1;
 
    public HeapImpl(){
    	
    	Heap=new ArrayList<Process>();
    	Heap.add(null);
    	
    }

    private int parent(int pos)
    {
        return pos / 2;
    }
 
    private int leftChild(int pos)
    {
        return (2 * pos);
    }
 
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }
 
    private boolean isLeaf(int pos)
    {
        if (pos >=  (Heap.size() / 2)  &&  pos <= Heap.size())
        { 
            return true;
        }
        return false;
    }
 
    private void swap(int fpos, int spos)
    {
        Process tmp;
        tmp = Heap.get(fpos);
        Heap.set (fpos, Heap.get(spos));
        Heap.set(spos, tmp);
    }
 
    private void minHeapify(int pos)
    {
        if (!isLeaf(pos))
        { 
            if ( (Heap.get(pos)).unfairness > Heap.get(leftChild(pos)).unfairness  || (Heap.get(pos)).unfairness > Heap.get(rightChild(pos)).unfairness)
            {
                if (Heap.get(leftChild(pos)).unfairness < Heap.get(rightChild(pos)).unfairness)
                {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }
                else 
                {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }
 
    public void insert(Process p)
    {
        Heap.add(p);
        int current = Heap.size()-1;
       
        while (current!=1&&(Heap.get(current).unfairness < Heap.get(parent(current)).unfairness))
        {
        	 
            swap(current,parent(current));
            current = parent(current);
        	
        }
        //print();
    }
 
    public void print()
    {
        for (int i = 1; i <=Heap.size() -1; i++ )
        {
           System.out.print("P"+Heap.get(i).processId +" "+Heap.get(i).unfairness+" ");
        } 
        System.out.println(" ");
    }
 
    public void minHeap()
    {
        for (int pos = (Heap.size()-1 / 2); pos >= 1 ; pos--)
        {
            minHeapify(pos);
        }
    }
 
    public Process remove()
    {
    	Process pr=null;
    	if(Heap.size()>1)
    	{
    	pr = Heap.get(FRONT);
        Heap.set(FRONT,Heap.get(Heap.size()-1)); 
        Heap.remove(Heap.size()-1);
        minHeapify(FRONT);
    	}
        return pr;
    }
    
    public int getSize()
    {
    	return Heap.size()-1;
    }
 
   
}