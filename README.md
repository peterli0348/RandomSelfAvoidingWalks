# RandomSelfAvoidingWalks

Project is for CSCI370 Software Engineering

*In-depth summary can be found in **[Cover_Document.pdf](https://github.com/peterli0348/RandomSelfAvoidingWalks/blob/main/Cover_Document.pdf)***

*In-depth requirments can be found in **[Project_Requirements.pdf](https://github.com/peterli0348/RandomSelfAvoidingWalks/blob/main/Project_Requirements.pdf)***

## Highlights

* **Multithreading**
* **Performance Optimization**
* **Statistical Analysis**

## Summary

Random self avoiding walks (SAW) are created randomly using *thread safe* random functions. 
Walks are done on a grid of variable dimentions and comprise of variable steps (length).
To get the average squared start to end distance of non-intersecting walks, thousands of walks need to be generated for a sufficent sample size.

By generating 1,000,000 walks on each of the 1000 threads, I found that the average squared distance of a 10 step SAW is 26.24 steps with only 4.2% of the generated walks reaching the 10th step.

Since there is such a large sample size and limited CPU power, I had to optimize the program to be able to finish generating all the data in a timely fashion.
By using a hashmap to store the hashcode of previously visited steps, I was able to bring the programs runtime from 10 minutes down to 1 minute on my machine.
