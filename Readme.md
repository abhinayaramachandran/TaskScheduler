***********************************************************************************************
A simple implementation of the Completely Fair Scheduler using binary heaps and Red black trees.
***********************************************************************************************


Project Description:

A simplified simulation of Linux’s scheduling algorithm (Completely Fair Scheduler) has been

implemented. The objective of this scheduling algorithm is to ensure that each process/task which is

active gets fair access to the CPU. We have used the data structures Red Black Tree and Heap (min heap)

for implementing this algorithm and a comparison is made between Red Black Tree and Heap. An

unfairness value has been used to decide the priority of processes getting access to the processor.



Input:

An input file which consists of number of processes and the quantum time of the CPU. Each process is

given an id, arrival time and total execution time it needs. The following figure shows how an input file is

given.

Data structures and Modules used - Task Scheduler Implementation

Data Structures:

* The heap implementation required the use of a data structure that can be accessed by indices so

that the parent and the children nodes can be accessed by the indices (like i, 2i and 2i+1). In

addition the implementation also required storing the process id of the process to which the

unfairness value belongs. So, an ArrayList data structure was used to implement this.



* The red black tree data structure required a component to store the process id , unfairness value,

color of the node, etc in addition to the parent and child pointers. A nested class structure was

used to implement this.


FileReaderClass.java (Main class):

This class takes a text file as an input. On running this class, it provides information on the file on how

many tasks are input and the quantum period1

. It then asks the user to choose a metric on which the

program would calculate the time taken using the given input.

RedBlackTree.java:

This class has the following functions to operate on a Red Black Tree.

* printRBtree(): This function prints the Red Black Tree by passing the root node to the

printTreee(RBNode rbn) method.

* printTree(RBNode rbn): It prints the red black tree by performing an inorder traversal. It prints

the process id followed by the unfairness value and the color of the node containing the

unfairness value.

* insert(Process key): Inserts a new node to the Red Black Tree

* fixTree(RBNode node): When a node is inserted to or deleted from Red Black Tree, a fixTree

operation is performed in order to balance the tree. This is achieved with the help of rotateLeft()

or rotateRight() operations.

* rotateLeft(RBNode node): This performs a left rotation operation on the Red Black Tree.

* rotateRight(RBNode node): This performs a right rotation operation on the Red Black Tree.

* transplant(): This method is called by the delete() method when the parent and child pointers are

to be adjusted.

* findLeftmost(): This function finds the leftmost node in the Red Black Tree.

* delete(): This operation deletes the node selected. This issues calls to deleteFixup() and

transplant() methods.

* deleteFixup(): After the deletion of a node, the Red Black Tree would have to be rebalanced.




There are six cases for the deletion of red black trees and this is handled by delete fixup.



HeapImpl.java:

This class performs task scheduling operations using a heap. Heap uses array data structure. This class has

the following functions:

* parent(int pos): Takes in the position of the current node and returns the position of the parent of

the current node.

* leftChild(int pos):Takes in the position of the current node and returns the position of the left

child of the current node.

* rightChild(int pos): Takes in the position of the current node and returns the position of the right

child of the current node.
isLeaf(int pos): Checks whether a given node is a leaf or not. It is a boolean function.

* swap(int fpos, int spos): Swaps two nodes in the heap. It is used in the minHeapify() function.

* minHeapify(int pos): This operation is performed when an insertion or deletion operation is

done on heap to rebalance the heap when the min heap property is violated.

* insert(Process p): inserts a process in form of a node into the heap.

* print(): Prints the heap in the order in which the values are placed in the array list.

* minHeap(): Forms a min heap by calling the minHeapify() function.

* remove(): Removes a node from the heap.

* getSize(): Returns the size of the heap.



Process.java:

This class takes into account many parameters related to the process to be inserted into the Heap and Red

Black Tree. This includes the process ID, arrival time, execution time, wait time and the unfairness value.

When this class is called, the insertion of these processes are done into the heap and red black tree

respectively.



Scheduler.java:

This class has the following functions.

public void scheduleHeap(HeapImpl hp):

This function is invoked for the case where the unfairness value is calculated based on Time in CPU. This

function Schedules the jobs from the Min-Heap data structure by deleting the minimum element from the

Heap Array. It recalculates the unfairness and execution time left values after the task completes its CPU

cycle. If the task still has some execution time left, then it is inserted back to the Heap or else the task is

permanently deleted. The process is repeated till the Heap is empty.

This function also calculates the totalRunTime, totalTurnAroundTime, totalWaitTime and Throughput etc

and logs those values.

public void scheduleRBTree(RedBlackTree rt):

This function is invoked for the case where the unfairness value is calculated based on Time in CPU. This

functions schedules the jobs in the Red Black Tree data structure by deleting the leftmost node from the

Red Black Tree. It recalculates the unfairness and execution time left values after the task completes its

CPU cycle. If the task still has some execution time left, then it is inserted back to the Red Black Tree or

else the task is completely deleted. The process is repeated till no nodes left in the Red Black Tree.

This function also calculates the totalRunTime, totalTurnAroundTime, totalWaitTime and Throughput etc

and logs those values.



public void scheduleHeapRe(HeapImpl hp):

This function is invoked for the Heap Scheduling case where the unfairness value is calculated based on

Remaining execution time. The functionality of this method is same as scheduleHeap as described above

except that the unfairness measure is the remaining execution time.



public void scheduleRBTreeRe(RedBlackTree rt):

This function is invoked for the Red Black Tree Scheduling case where the unfairness value is calculated

based on Remaining execution time. The functionality of this method is same as scheduleRBTree as

described above except that the unfairness value is the remaining execution time required for each

process.



public void scheduleHeapBoth(HeapImpl hp):

This function is invoked for the Heap Scheduling case where the unfairness value is calculated based on

both Time In CPU and Remaining Execution Time.


public void scheduleRBTreeBoth(RedBlackTree rt):

This function is invoked for the Red Black Tree Scheduling case where the unfairness value is calculated

based on both Time In CPU and Remaining Execution Time.

Unfairness Measure:

In the implementation, three different unfairness measures were used, they are

* Time in CPU : The amount of time the process has spent in the CPU

* Remaining execution time: The amount of time a process would need to complete execution.

* Time in CPU + Remaining execution time : Both the above said metrics together deccide the

unfairness.

Scheduling Metrics:

Running time(nanoseconds): The amount of time required to schedule and execute all the processes in

the queue.

Average running time(nanoseconds)/ Running time per process:Total running time divided by the

number of processes.

Waiting time (nanoseconds): The sum of the periods when the process is in the ready queue, waiting for

the processor.

Average waiting time(nanoseconds): Total waiting time divided by the number of processes.

Turn around time(nanoseconds): The duration between the time the process enters the ready queue for

scheduling and the time when the process exits the processor after execution.

Average turnaround time(nanoseconds): Total turnaround time divided by the number of processes.

Throughput: The number of processes per unit time (Here unit time is one millisecond).
