# Number Range Summarizer

## Overview

This project implements a solution to summarize a list of integers into a compact string format by grouping consecutive numbers into ranges.

### Example

**Input:**

"1,3,6,7,8,12,13,14,15,21,22,23,24,31"

**Output:**

"1, 3, 6-8, 12-15, 21-24, 31"

## Assumptions

* Input must be comma-separated integers
* Input may be unsorted
* Input may contain duplicates
* Invalid input (alphabets, decimals, special characters) will throw an exception
* Negative numbers are supported

## Features

* Parses comma-separated input into integers
* Handles:

  * Unsorted input
  * Duplicate values
  * Negative numbers
  * Whitespace
* Groups sequential numbers into ranges
* Validates input and throws exceptions for invalid values
* Fully unit tested using JUnit


## Technologies Used

* Java 8
* Maven
* JUnit 5


## Running Tests

Run the following command:

```
mvn test
```

## Author

Lerato Kgomoeswana

