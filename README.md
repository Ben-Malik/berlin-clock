## Tech Stack
* Java 17
* Maven
* JUnit 5

```
BerlinClock.getBerlinClock(time:String):String
```
This method takes a string value of the time whose BerlinClock version is sought. 
After precondition checks of the given time (nullability and validity of time format), all computations are done and 
the Berlin clock version of the given time is returned as a String.

## How to run and test?

Run `BerlinClockTest` to test the scenarios.

[![Java CI with Maven](https://github.com/Ben-Malik/berlin-clock/actions/workflows/maven.yml/badge.svg?branch=main)](https://github.com/Ben-Malik/berlin-clock/actions/workflows/maven.yml)
