#Study notes for coursera's algorithm design analysis 1

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
- Wrong implementations of the merge step leads to O(n2).

Example: http://algs4.cs.princeton.edu/22mergesort/

### Quick sort:

It chooses an arbitrary element called pivot and iterates through the array/list putting all the elements greater than the pivot after the pivot and the elements which are lesser then the pivot before the pivot, creating a partition. Each partition returns the index of the last swap made by the algorithm containing the current position of the pivot. The complexity of the quick sort has an average of O(n (log n)) but it's worse case can be O(nˆ2). Introduction to algorithms uses the simplest Lomuto's partition approach, which does more comparisons and can lead to O(nˆ2) when the array is already sorted. Hoare's partition approach (original one) on the other hand, uses a more complex approach but it also reduces the amount of comparisons.

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

One of the most used examples to exercise divide-and-conquer algorithms. The current third grade approach to multiply numbers has O(n2) complexity and Karatsuba's has only O(n (log3)). It divides the multiplying elements in half, applying the following formula to each pair: xy = a # B^2m + b # B^m + c. This current formula also takes O(n^2) but Karatsuba had the brilliant idea to replace b by the following formula: b = (x1 + x2)(y1 + y2) - a - c;

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

## Books

- CLRS: Cormen, Leiserson, Rivest, and Stein, Introduction to Algorithms (3rd edition)
- DPV: Dasgupta, Papadimitriou, and Vazirani, Algorithms
- KT: Kleinberg and Tardos, Algorithm Design
- SW: Sedgewick and Wayne, Algorithms (4th edition)

## Study progress

### First week
- [x] CLRS: Chapter 2, 3, and 4 (through Section 4.2), and Sections 28.1 and 33.4
- [x] DPV: Sections 0.3, 2.1, 2.3, 2.5
- [x] KT: Sections 2.1, 2.2, 2.4, 5.1, and 5.3-5.5
- [x] SW: Sections 1.4 and 2.2

### Second week
- [x] CLRS Chapter 4 (Sections 4-6) and Chapter 7
- [x] DPV Section 2.2
- [x] KT Sections 5.2 and 13.5
- [x] SW Section 2.3

### Third week
- [ ] CLRS Chapter 9, 22 (Only 22.1)
- [ ] DPV Chapter 3 (only 3.1)
- [ ] KT Chapter 13, Sections 13.2,13.5
- [ ] SW Chapter 4, Section 4.1


