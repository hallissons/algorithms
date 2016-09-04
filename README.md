#Study notes for coursera's algorithm design analysis 1

##Big Oh notation
When analyzing an algorithm is very hard to make an accurate assumption on how long a program will take to run. To do a proper analysis one has to take all variables into account: hardware used to execute the algorithm, cost of each step on that hardware, input size, etc.

The big oh notation was created to simplify the analysis of an algorithm since it considers only the order of grown based on a single variable: the input size. While analyzing an algorithm, the key point is to take out low order terms and focus on the high order terms so, if the time running an algorithm is T(n) = 5nˆ3 + 2nˆ2 + 16n + 2, where n is the input size, we can say that the O(T(n)) is nˆ3. 

But why? the answer is very simple, for large values of n, the low order terms has a very small contribution to the grand total of the running time for the algorithm. To prove matematically that f(n) = O(g(n)), assuming that g(n) is just an arbitrary function that describe the running time for a particular algorithm, we multiply f(n) by a constant c and we take a c0 (starting point) where the running time of c.f(n) is equal to c.g(n). If f(n) grows faster than g(n) after multiplying by that constant we can say that f(n) = O(g(n)).


## Divide and Conquer Algorithms

### Introduction

Divide and conquer usually takes three steps:

- Divide: Divide the problem and smaller sub problems.
- Conquer: Solve the problems recursively, if the problem is small enough, use a brute force algorithm.
- Combine: Combine the sub problems into a solution of the bigger problem.

### Binary search:

Iterates through a ordered list of elements splitting the list in half in each iteration. If the mid element is equal to the "key" which we're looking for, returns the index of the key, otherwise checks if the key is bigger or lower than the mid element and splits the list again until the low index is less or equal than the high index. This algorithm has the complexity of O(log n) since the array is splited in half each time we look for the element, it also assumes that the list is sorted, otherwise they result cannot be predicted.

**Key points:**
- Doesn't have a combine step
- Array is assumed to be sorted, otherwise the result cannot be predicted.

### Merge sort:

Recursively divides an array/list into two different lists splited in half combining them into a third list. While inserting the elements, the elements will be compared and the final result is a ordered collection. This approach is found more often and it's called "top-down". A different approach "non-recursive" will create small sub arrays and pass them to an also called merge step, this approach is called "bottom-up". The complexity of the merge sort is O(n (log n)) for both approaches.

**Key points:**
	- It consumes more resources than other sorting algorithms because of the third list created on the merge step.
	- It has a very good stability since it's best case is same of it's worst case (Theta(n(log n))).
	- Wrong implementations of the merge step leads to O(n2).

Example: http://algs4.cs.princeton.edu/22mergesort/

### Quick sort:

It chooses a pivot element and iterates through the array/list putting all the elements greater than the pivot after the pivot and the elements which are lesser then the pivot before the pivot, creating a partition. Each partition returns the index of the last swap made by the algorithm containing the current position of the pivot. The complexity of the quick sort has an average of O(n (log n)) but it's worse case can be O(nˆ2). Introduction to algorithms uses the simplest Lomuto's partition approach, which does more comparisons and can lead to O(nˆ2) when the array is already sorted. Hoare's partition approach on the other hand, uses a more complex approach but it also reduces the amount of comparisons.

**Key points:**
	- It consumes less resources if compared with merge sort.
	- It's very simple to implement.
	- Several pivot strategies can be used and each one can lead to different results. Bad pivot strategies can take the average case to O(n2).
	- Common pivot strategies: first element, last element, middle of three (first, mid, last), random. A random pivot choice is preferable due to the fact that long runs increases the occurrences of the average cases.

Example: http://algs4.cs.princeton.edu/23quicksort/

### Insertion sort:

 Iterates through an array/list, simulating the insertion the elements in a sorted order (same comparison with a hand full of cards and a second had with no cards), each iteration chooses an element and inserts it to the right place. It's worst case is O(n^2) but it's best case is O(n) and it occurs when the array is already sorted.

**Key points:**
 	- The number of comparisons can be very high due to the worst case (inverted array/list).
 	- The insertion logic don't use a new array, the key element of each iteration will be placed on it's right place (higher elements on the left, lesser elements on the right).
 	- It can be very fast for smaller sets.

 Example: http://algs4.cs.princeton.edu/21elementary/

### Karatsuba multiplication

One of the most used examples to exercise divide-and-conquer algorithms. The current third grade approach to multiply numbers has O(n2) complexity and Karatsuba's has only O(nlog3). It divides the multiplying elements in half, applying the following formula to each pair: xy = a # B^2m + b # B^m + c. This current formula also takes O(n^2) but Karatsuba had the brilliant idea to replace b by the following formula: b = (x1 + x2)(y1 + y2) - a - c;

**Key points:**
	- Uses math to improve the current third grade multiplication algorithm.
	- Exercise to divide-and-conquer approach.

Example: https://dzone.com/articles/algorithm-week-karatsuba-fast

### Master theorem

General formula applied to divide and conquer algorithms to facilitate the analisis based on big oh notation.

- T(n) <= at (n/b) + f(n)

f(n) can also be replaced by the O(nˆd) where d is equivalent of the expoent used to express the running time of the combined step

a: recursive calls
b: input shrink factor
d: exponent used on the running time for the combined step

- O(nˆd (log n)) 	if a = bˆd 
- O(nˆd) 			if a < bˆd
- O(nˆlogˆ(bˆa))	if a > bˆd



### Other divide and conquer algorithms:

	- Closest pair problem: https://rosettacode.org/wiki/Closest-pair_problem#Java
	- Strassen's matrix multiplication: http://www.stoimen.com/blog/2012/11/26/computer-algorithms-strassens-matrix-multiplication/


## Books

- CLRS: Cormen, Leiserson, Rivest, and Stein, Introduction to Algorithms (3rd edition)
- DPV: Dasgupta, Papadimitriou, and Vazirani, Algorithms
- KT: Kleinberg and Tardos, Algorithm Design
- SW: Sedgewick and Wayne, Algorithms (4th edition)
