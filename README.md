#Study notes for algorithm design analysis 1 & 2

##Big Oh notation
When analyzing an algorithm is very hard to make an accurate assumption on how long a program will take to run. To do a proper analysis one has to take all variables into account: hardware used to execute the algorithm, cost of each step on that hardware, input size, etc.

The big oh notation was created to simplify the analysis of an algorithm since it considers only the order of grown based on a single variable: the input size. While analyzing an algorithm, the key point is to take out low order terms and focus on the high order terms so, if the time running an algorithm is T(n) = 5nˆ3 + 2nˆ2 + 16n + 2, where n is the input size, we can say that the O(T(n)) is nˆ3. 

But why? the answer is very simple, for large values of n, an low order term has a very small contribution to the grand total of the running time for the algorithm. To prove mathematically that f(n) = O(g(n)), assuming that g(n) is just an arbitrary function that describe the running time for a particular algorithm, we multiply f(n) by a constant c and we take a n0 (starting point) where the running time of c.f(n) is equal to c.g(n) and 0 <= f(n) <= c.g(n) for all n >= n0. If f(n) grows faster than g(n) after multiplying by that constant we can say that f(n) = O(g(n)).

### Order of growth

| Order of growth | Description 																								  | Example 			  |
| --------------- | ------------------------------------------------------------------------------------------------------------- |---------------------- |
| O(1)			  | Constant: A program which executes a fixed number of operations, does not depend on N. 						  | add two numbers 	  |
| O(log n) 		  | Logarithmic: A program which executes in logarithmic time, its barely slower than a constant-time program. 	  | binary search 		  |
| O(n) 			  | Linear: A program which executes in linear time based on the input. 										  | find the maximum	  |
| O(n (log n)) 	  | Linearithmic: A program which executes in n log n time based on the input. 									  | merge sort			  |
| O(nˆ2) 		  | Quadratic: A program which grows in quadratic time based on the input, typically two nested loops. 			  | insertion sort		  |
| O(nˆ3) 		  | Cubic: A program which grows in cubic time based on the input. 												  | matrix multiplication |
| O(bˆn) 		  | Exponential: A program which grows in exponential time based on some constant b where b > 1. 				  | Check all subsets	  |

### Other notations

The main focus of big Oh notation is to describe the worst case (upper bound) but there're other notations:

- **Big Omega:** Describes the best case of an algorithm (lower bound).
- **Theta:** It's used when the worst case is also the best case (upper bound is equal to the lower bound).


## Divide and Conquer Algorithms

### Introduction

Divide and conquer usually takes three steps:

- Divide: Divide the problem and smaller sub problems.
- Conquer: Solve the problems recursively, if the problem is small enough, use a brute force algorithm.
- Combine: Combine the sub problems into a solution of the bigger problem.

### Binary search:

Iterates through a sorted array/list of elements splitting the list in half in each iteration. If the mid element is equal to the "key" which we're looking for, returns the index of the key, otherwise checks if the key is bigger or lower than the mid element and splits the list again until the low index is less or equal than the high index. This algorithm has the complexity of O(log n) since the array is splitted in half each time we look for the element, it also assumes that the list is sorted, otherwise they result cannot be predicted.

**Key points:**
- Doesn't have a combine step
- Array/list is assumed to be sorted, otherwise the result cannot be predicted.

### Merge sort:

Recursively divides an array/list into two different lists splitted in half combining them into a third list. While inserting the elements, the elements will be compared and the final result is a ordered collection. This approach is found more often and it's called "top-down". A different approach "non-recursive" will create small sub arrays and pass them to an also called merge step, this approach is called "bottom-up". The complexity of the merge sort is O(n (log n)) for both approaches.

**Key points:**
- It consumes more resources than other sorting algorithms because of the third list created on the merge step.
- It has a very good stability since it's best case is same of it's worst case (Theta(n(log n))).
- Wrong implementations of the merge step leads to O(nˆ2).

Example: http://algs4.cs.princeton.edu/22mergesort/

### Quick sort:

It chooses an arbitrary element called pivot and iterates through the array/list putting all the elements greater than the pivot after the pivot and the elements which are lesser then the pivot before the pivot, creating a partition. Each partition returns the index of the last swap made by the algorithm containing the current position of the pivot. The complexity of the quick sort has an average of O(n (log n)) but it's worse case can be O(nˆ2). Introduction to algorithms uses the simplest Lomuto's partition approach, which does more comparisons and can lead to O(nˆ2) when the array is already sorted. Hoare's partition approach (original one) on the other hand, uses a more complex algorithm but it also reduces the amount of comparisons.

**Key points:**
- It consumes less resources if compared with merge sort.
- It's very simple to implement.
- Several pivot strategies can be used and each one can lead to different results. Bad pivot strategies can take the average case to O(nˆ2).
- Common pivot strategies: first element, last element, middle of three (first, mid, last), random. A random pivot choice is preferable due to the fact that long runs increases the occurrences of the average cases.

Example: http://algs4.cs.princeton.edu/23quicksort/

### Insertion sort:

 Iterates through an array/list, simulating the insertion the elements in a sorted order (same comparison with a hand full of cards and a second had with no cards), each iteration chooses an element and inserts it to the right place. It's worst case is O(nˆ2) but it's best case is O(n) and it occurs when the array is already sorted.

**Key points:**
- The number of comparisons can be very high due to the worst case (inverted array/list).
- The insertion logic don't use a new array, the key element of each iteration will be placed on it's right place (higher elements on the left, lesser elements on the right).
- It can be very fast for smaller sets.

 Example: http://algs4.cs.princeton.edu/21elementary/

### Karatsuba multiplication

One of the most used examples to exercise divide-and-conquer algorithms. The current third grade approach to multiply numbers has O(nˆ2) complexity and Karatsuba's has only O(n (log3)). It divides the multiplying elements in half, applying the following formula to each pair: xy = a # B^2m + b # B^m + c. This current formula also takes O(nˆ2) but Karatsuba had the brilliant idea to replace b by the following formula: b = (x1 + x2)(y1 + y2) - a - c;

**Key points:**
- Uses math to improve the current third grade multiplication algorithm.
- Exercise to divide-and-conquer approach.

Example: https://dzone.com/articles/algorithm-week-karatsuba-fast

### Master theorem

General formula applied to divide and conquer algorithms to facilitate the analysis based on big oh notation.

- **T(n) <= at (n/b) + f(n)**

f(n) can also be replaced by the O(nˆd) where d is equivalent of the exponent used to express the running time of the combined step

- **a:** recursive calls
- **b:** input shrink factor
- **d:** exponent used on the running time for the combined step

- O(nˆd (log n)) 	if a = bˆd 
- O(nˆd) 			if a < bˆd
- O(nˆlogˆ(bˆa))	if a > bˆd


### Other divide and conquer algorithms:

- Closest pair problem: https://rosettacode.org/wiki/Closest-pair_problem#Java
- Strassen's matrix multiplication: http://www.stoimen.com/blog/2012/11/26/computer-algorithms-strassens-matrix-multiplication/


## Linear time selection

When we try to find the smallest or the biggest element of an array/list, the simplest approach is to iterate through an array and find the appropriate element, which can be solved in linear time O(n). But what about getting the "second smallest" element of a list? One can say that we can sort the list and get the second item on the list but since we'll need to sort the list, how can we do better? The answer is: yes, we can :)

The linear time selection uses a modified version of the quick-sort named quickselection to find the kth-smallest element (kth order statistic) on a array/list in linear time using a random pivot. The worst case of quickselection is O(nˆ2) and it occurs only if we are very unlucky to always select the pivot as the smallest element in all times. While analyzing this 
algorithm we can see that we'll be selecting a good pivot 50% of the time which lies between the pivot in 25%-75% of the elements: [ 25% |p|   75%   ] or [   75%   |p| 25% ].

```
  // Returns the n-th smallest element of list within left..right inclusive
  // (i.e. left <= n <= right).
  // The search space within the array is changing for each round - but the list
  // is still the same size. Thus, n does not need to be updated with each round.
  function select(list, left, right, n)
     if left = right        // If the list contains only one element,
         return list[left]  // return that element
     pivotIndex  := ...     // select a pivotIndex between left and right,
                            // e.g., left + floor(rand() % (right - left + 1))
     pivotIndex  := partition(list, left, right, pivotIndex)
     // The pivot is in its final sorted position
     if n = pivotIndex
         return list[n]
     else if n < pivotIndex
         return select(list, left, pivotIndex - 1, n)
     else
         return select(list, pivotIndex + 1, right, n)
```

** A good pivot is when we create a balanced split **
## Deterministic selection

The objective of the deterministic selection is the same of linear time selection, to find the kth-smallest element (kth order statistic) on a array/list in linear time. The main differences between the two is that the first uses randomized pivot, based on quick sort executing in average of O(n) and the latest doesn't use randomization executing in O(n) on its worst case.

The master method cannot be used to measure the complexity due to the fact that the recursions (sub-problems) have different sizes.

## Sorting problems

A list cannot be sorted with a lower bound better than Omega(n (log n)) using sort comparison algorithms. To improve the running time, we need to know more information on the data: bucket sort, radix sort, counting sort.

## Graph Theory

A graph is a set of vertices connected by edges, which can be used to represent and solve several types of computational problems: computer networks, social networks, image processing, etc. A graph can be directed or undirected.

- **Common notations:** (V,E) where V = vertices and E = edges; (N,M) where N = vertices and M = edges
- **Common ways to represent a graph:** Adjacency lists and matrices.

### Adjacency lists
A list of structured objects used to represent "edges" and "vertices" where each edge has a list of vertices and each vertex has a list of edges (many-to-many relationship). The main advantages on representing a graph using this approach is the amount of space used by a graph: O(n+m) and the fact that searches can be done in a more straight forward way compared to matrices. 

### Adjacency matrix
A matrix can be used to represent a graph with a size of NxN (N = number of vertices) where each position has one bit (1 or 0). The data structure is simpler but it can spend more space since for a large set of vertices, the matrix will have a bit for every edge, even if the edge is empty leading to O(nˆ2). The main advantage on this approach is that an algorithm can take advantage on matrices operations to solve problems, which can reduce the complexity of an algorithm (e.g.: check an edge in constant time).

### Minimum cut problem

In graph theory, a minimum cut of a graph is a cut (a partition of the vertices of a graph into two disjoint subsets that are joined by at least one edge) that is minimal in some sense.

### The random contraction algorithm (Karger's algorithm)

A edge is select randomly and removed from the graph until the graph has 2 vertices: minimum cut.
		  F
|  A1  | \ /   |  B1  |

|  A2  | / \   |  B2  |

|  A3  | ---\--|  B3  |


Pr[output is (A,B)] = Pr[never contracts an edge of F] = Pr['S1 /\ 'S2 /\ 'S3.../\ 'Sn-2] <= 2/n(n-1) <= 1/2ˆn

The probability is very small, if we ran the algorithm a fixed number of times and remember the smallest cut found, how many trials we need?

O(Tm) = O(nˆ2 m log n).

A graph can have multiple min cuts (e.g. a tree with vertices has (n-1) minimum cuts). The largest number of min cuts a graph with n vertices can have is (n choose 2) = n(n-1)/2

#### **Steps:**
1. while there are > 2 vertices
2. pick the remaining edge(u,v) uniformly at random
3. merge u and v in a single vertex
4.    remove self loop
5. return cut represented by 2 final vertices

### Breadth-First Search (BFS)

Basic type of algorithm used to search elements in a graph built in top of a queue, finding all the connected elements. It has running time of O(n+m) and has several motivations:

- Find bottleneeks in a physical computer network.
- Compute the shortest path between two elements e.g.: "The Bacon number". But only if each edge weight is equal to "1".
- Compute a path based on a sequence of decisions.

One of the main advantages of the BFS is that it can compute the shortest path between two elements just maintaining the level of each vertex while visiting the graph.

### Depth-First Search (DFS)

Basic type of graph algorithm, built on top of a stack instead of a queue. It can be used to implement topological ordering and compute strongly connected components of a directed graph. The implementation is very similar to BFS but since it uses a stack as the main data structure to maintain the next node to be visited, one can use recursion to implement it.

This algorithm can be used to discover several properties from the graph, including all the forests (collection of trees). Those properties can be used as the main entry point for several algorithms.

### Topological Sort 

Used when a node has precedence from another, a very common example is a list of tasks. Two common implementations are: Kahn's algorithm or Depth-first search.

- Can only occur if the graph is not a directed cycle.
- Every direct acyclic graph has a sync vertex.
- Runs in linear time O(m+n)

### Strong Connected Components

A strong connected component is a directed graph composed by a pair of vertices which can be reachable from one to another (v <-> f). A graph can be decomposed in strong connected components running DFS routine twice on graph, where one of the runs will take a transposed version of the graph (inverted edges) named: Kosraju's two-pass algorithm. Another way to discover a component is using Tarjan's algorithm. 

Directed acyclic graphs = dags

### Kosaraju's Two-Pass Algorithm

1. Let G(rev) denote the graph G after the orientation of all arcs have been reversed.
2. Run the DFS-Loop subroutine on G(rev), processing vertices according to the given order, to obtain a
finishing time f (v) for each vertex v E V.
3. Run the DFS-Loop subroutine on G, processing vertices in decreasing order of f(v), to assign a leader
to each vertex v E V.
4. The strongly connected components of G correspond to vertices of G that share a common leader.

Runs in O(m+n)

### Dijkstra's Algorithm

Used to compute the shortest path between two vertices using weighted edges. The algorithm has several variants to solve the following problems:

- **Single-source shortest-paths problem:** given a graph G = (V,E), we want to find a shortest path from a given source vertex s E V to each vertex v E V. 
- Single-destination shortest-paths problem: find a shortest path to a given destination vertex t from each vertex.
- Single-pair shortest-path problem: Find a shortest path from u to v for given vertices u and v.
- All-pairs shortest-paths problem: Find a shortest path from u to v for every pair of vertices u and v.

```
 1  function Dijkstra(Graph, source):
 2
 3      create vertex set Q
 4
 5      for each vertex v in Graph:             // Initialization
 6          dist[v] ← INFINITY                  // Unknown distance from source to v
 7          prev[v] ← UNDEFINED                 // Previous node in optimal path from source
 8          add v to Q                          // All nodes initially in Q (unvisited nodes)
 9
10      dist[source] ← 0                        // Distance from source to source
11      
12      while Q is not empty:
13          u ← vertex in Q with min dist[u]    // Source node will be selected first
14          remove u from Q 
15          
16          for each neighbor v of u:           // where v is still in Q.
17              alt ← dist[u] + length(u, v)
18              if alt < dist[v]:               // A shorter path to v has been found
19                  dist[v] ← alt 
20                  prev[v] ← u 
21
22      return dist[], prev[]
```

- Its based on BFS's principles.
- The naive implementation takes O(m*n), but it can be improved using the correct data structure (e.g.: heap/priority queues) which takes the running time down to O(m) + the priority queues operations for each Ith element.
- Lines 18-20 implements a technique called "relaxing" where each vertex is relaxed when a new path is found navigating through the vertices.
- The algorithm can be optimized using fibonacci heap, topological sort or priority queues. When the optimization is done using fibonacci heap, the running time can be O(m log(n)).
- The longest path can be found inverting the initialization to -INFINITY and inverting the logic described on the relaxing steps.

### Parallel job scheduling

Several jobs are scheduled to run in parallel using executors but some of them has other jobs as precedence. This problem can be solved using the "critical path method", which is equivalent to the longest-path problem

### Bellman & Ford algorithm

Algorithm used on general cases when the graph has cycles and edge weights can be negative.

- Parallel job scheduling when it has negative cycles (job has starting and ending time)
- Currency rate exchanging

## Data structures

The way we organize data play a important role while implementing an algorithm. Some structures are more suitable then others depending on the situation.

- Which operations do you need as a client of a data structure?
- How much space do you have available?

- Sorted array: Supports several operations in O(1): select, min/max, pred/succ and rank. O(log n): search. O(n): insert and delete. Since the array is static, inserts and deletes are expensive.

- Queue: First in, first out (FIFO).
- Stack: Last in, first out (LIFO).
- Heap: Ordered elements
- Balanced Search Trees

### Heap

A container of objects that have keys. It supports the following operations:

1. Insertion: Add a new object to a heap. Takes O(log n).
2. Extract min/max: Extracts and remove the element with the minimum/maximum key value. Takes O(log n).
3. Delete: removes an element from the heap. Takes O(n log n).
4. Heapify: initialization of the heap using batch inserts, taking O(n) instead of O(n log n).

- Perfectly balanced binary tree

Heap data structures can be used on several applications:

- **Sorting:** Fast way to compute the repeated minimum, can be used to speed up selection sort selecting the minimum on each iteration, which takes the selection sort from O(nˆ2) to O(n log n), which can be also called as "Heap sort" with some modifications. Since this is still a comparison based sorting, it cannot be better than O(n log n). A well implemented quicksort/mergesort can still be better.

- **Event Manager:** A collection of events can take advantage on heap data structure extracting the smallest timestamp.

- **Median maintenance:** Extract the median element using two heaps, one with the smallest elements and one containing the highest elements. The median will be the max from the first list along with the min of the second list, depending on the size of each lists. If the sizes are unbalanced, one element needs to be moved to the other one.
 
### Balanced Search Trees - BST

A linked set of objects with a pointer to a parent with the following key properties:

- Each element has three pointers: right, left, parent.
- Each element has a key (which corresponds to the data for that node).
- The element on the right is bigger than the element.
- The element on the left is less than the element.

Gives O(log n) running time for the following operations: search, select, min/max, pred/succ, rank, insert, delete and O(n) running time to output in sorted order. The worst case is O(height) and it occurs when the elements are inserted in a ordered way.

### Red-black Trees

Its a BST with an extra bit to record the "color" of the node (red or black). The color will be used to keep the tree balanced, which guarantees O(log n) for most of the operations. For that to happen, the following properties must hold:

1. Every node is either red or black.
2. The root is black.
3. Every leaf (NIL) is black.
4. If a node is red, then both its children are black.
5. For each node, all simple paths from the node to descendant leaves contain the same number of black nodes.

A RB Tree must hold all the properties while inserting/deleting nodes in order to provide  O(log n) on the operations.

### Hash table

Data structure used to maintain a set of stuff (transactions, people+associated data, IP addresses, etc.)

- Basic operations which runs in O(1) time: Insert, Delete, Lookup
- Doesn't Maintain ordering
- Hash function used to map keys to positions
- Each element will be stored in a "bucket" which can have collisions, just remember the birthday paradox "How many people we need to add in a room to have 50% of chance for them to have birthday on the same day?"

- Hash function:
(Objects of some universe) -- hash code --> (integer) -- compression function --> (buckets {0,1,2,3...n-1})

1. Choose a n to be prime
2. Not too close to a power of 2
3. Not too close to a power of 10

- Pathological data set: a data set which makes a hash function h(x) to address all the elements to the same bucket, "all hash function has its kryptonite".

Solutions: use a cryptographic hash function, use randomization, design a family H of hash functions. When choosing a family H, the probability of the collision of two keys is 1/n (a.k.a universal family of hash functions).

- **Collision resolution**

- **Chaining:**
	- Insert is O(1) when the list is empty.
	- Lookup O(list length).
	- The worst case can be when there's a single bucket for all elements

- Chaining or separate chaining can be used as a technique to avoid collisions. Each bucket will have a linked list.
A[h(x)]

[ ] -> [A]
[ ] 
[ ] -> [B] -> [D]
[ ] -> [C]


- **Open Addressing:** Uses all slots on the hash table instead of storing a linked list for each slot. This is done through "probing", where a slot is evaluated and if it is empty, the key is inserted there, otherwise it is inserted on a different slot where the probing is incremented using the following strategies: linear, quadraditic and double hashing

- Its pretty easy to create bad hash functions :)
- Terrible solution: h(x) = first 3 digits of a phone number. In that case all the "bay area" will go to the same bucket
- Mediocre solution: h(x) = last 3 digits of x, multiples of power of 2.

### Bloom Filters

Lightweight version of hash table with following characteristics:

- More space efficient
- Can't store an associated object
- No deletions
- Small false positive probability

- **Applications**
- Original: early spellcheckers.
- list of forbidden passwords.
- Network routers: limited memory, need to be super-fast

Ingredients: Array of n bits (n/|S| = # of bits per object in data set S)

## Introduction to greedy algorithms

Algorithm technique which greedly evaluates each "element" and chooses the best decision at the time. A greedy algorithm doesn't revisit a solution twice and it's not correct all the times.

### The optimal cache algorithm

A solution that provides a faster container to get the data in a faster way than getting the data on the usual way, e.g.: reading the disk vs RAM access. The algorithm is optimal when it minimizes the cache misses. A greedy algorithm always makes the choice that looks best at the moment. That is, it makes a locally optimal choice in the hope that this choice will lead to a globally optimal solution

- The furthest-in-future algorithm
	When di needs to be brought into the cache, evict the item that is needed the farthest into the future
- LRU algorithm: Least recently used

**Memory Hierarchy:** There is a small amount of data that can be accessed very quickly, and a large amount of data that requires more time to access; and you must decide which pieces of data to have close at hand.

### Kruskal’s minimum spanning tree algorithm

Given a graph G, create a new Gx which repeatedly add the next lightest edge from G that doesn’t produce a cycle.

### Prim's algorithm

Given a graph G, we start with a root node s and try to greedily grow a tree from s outward. At each step, we simply add the node that can be attached as cheaply as possibly to the partial tree we already have. The algorithm runs in O(m*n) time, which can be improved using Heaps, improving the running time to O(m log n).

### Reverse-Delete Algorithm

Given a graph G, deletes edges on the decreasing cost

## Problems

### 2-SUM Problem

Given an array A and a integer T, find the two elements which sums up to T.

- Naive: Scan the array and find all pair of sums and compare with T = O(nˆ2).
- Better: Sort the array and for each element, find its complement based on T = O(n log n) -> O(n log n) to sort the array + O(log n) to find a complement for each element + O(n).
- Even better: Use the hash table to lookup for elements, the running time will decrease to O(n).

### Scheduling

A shared resource (e.g. a processor) is used to execute many jobs (e.g.: processes). In what order should we sequence the jobs?

- weight wj: priority
- height lj: size

The output will be the sequence of the jobs

Example: 3 jobs

l1=1, l2=2, l3=3


[#3] = 6
[#2] = 3
[#1] = 1

The objective is minimize the weighted sum of completion times.

### Activity-selection problem

We wish to select a maximum-size subset of mutually compatible activities. We assume that the activities are sorted in monotonically increasing order of finish time:

f1 <=  f2 <= f3 <= ... <= fn-1 <=  fn

### Minimum spanning trees

Suppose you are asked to network a collection of computers by linking selected pairs of them. This translates into a graph problem in which nodes are computers, undirected edges are potential links, and the goal is to pick enough of these edges that the nodes are connected. But this is not all; each link also has a maintenance cost, reflected in that edge’s weight. What is the cheapest possible network?

- Kruskal’s Algorithm
- 

## Books

- CLRS: Cormen, Leiserson, Rivest, and Stein, Introduction to Algorithms (3rd edition)
- DPV: Dasgupta, Papadimitriou, and Vazirani, Algorithms
- KT: Kleinberg and Tardos, Algorithm Design
- SW: Sedgewick and Wayne, Algorithms (4th edition)

## Study progress

## Module 1

### First week
- [x] CLRS Chapter 2, 3, and 4 (through Section 4.2), and Sections 28.1 and 33.4
- [x] DPV Sections 0.3, 2.1, 2.3, 2.5
- [x] KT Sections 2.1, 2.2, 2.4, 5.1, and 5.3-5.5
- [x] SW Sections 1.4 and 2.2

### Second week
- [x] CLRS Chapter 4 (Sections 4-6) and Chapter 7
- [x] DPV Section 2.2
- [x] KT Sections 5.2 and 13.5
- [x] SW Section 2.3

### Third week
- [x] CLRS Chapter 9, 22 (Only 22.1)
- [x] DPV Chapter 3 (only 3.1)
- [x] KT Chapter 13, Sections 13.2,13.5
- [x] SW Chapter 4, Section 4.1

### Fourth week
- [x] CLRS Chapter 22
- [x] DPV Chapter 3
- [x] KT Chapter 3, Section 3.5, 3.6
- [x] SW Chapter 4, Section 4.1, 4.2

### Fifth week
- [x] CLRS Chapter 6, 12, 13 and 24 (Sections 3,4)
- [x] KT Section 4.4
- [x] SW Section 3.2, 3.3, 4.4

### Sixth week
- [x] CLRS Chapter 11
- [x] DPV Section 1.5
- [x] KT Chapter 13 (Section 13.6)
- [x] SW Section 3.4, 3.5

## Module 2

### First week
- [x] CLRS Chapter 16 (Sections 1 and 2) and Chapter 23
- [x] KT Sections 4.1, 4.2, 4.3, and 4.5
- [x] DPV Sections 5.1.1, 5.1.2, and 5.1.5
- [x] SW Section 4.3



