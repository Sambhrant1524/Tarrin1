# Meeting Scheduler

A Java implementation that determines the minimum number of days required to schedule all meetings when meetings cannot overlap.

## Problem Statement

Given a set of meeting intervals, find the minimum number of days needed to schedule all meetings such that no two meetings overlap on the same day. This is equivalent to finding the maximum number of overlapping meetings at any point in time.

## Algorithm Approach

The solution uses a **sweep line algorithm** with the following key insights:

### Core Concept
- The minimum number of days required equals the maximum number of meetings that overlap at any given time
- If at most `k` meetings overlap simultaneously, then `k` days are sufficient to schedule all meetings

### Algorithm Steps

1. **Separate Start and End Times**: Extract all start times and end times into separate arrays
2. **Sort Independently**: Sort both arrays independently 
3. **Sweep Line Traversal**: Use two pointers to traverse through time points:
   - When encountering a start time: increment ongoing meetings counter
   - When encountering an end time: decrement ongoing meetings counter
   - Track the maximum number of simultaneous meetings

### Key Implementation Details

- **Time Complexity**: O(n log n) due to sorting
- **Space Complexity**: O(n) for the separate arrays
- **Boundary Handling**: Start times are processed before end times when they're equal (meetings ending exactly when another starts don't overlap)

## Code Structure

```java
public class MeetingScheduler {
    public int minDaysRequired(int[][] intervals)
}
```

### Method Parameters
- `intervals`: 2D array where each element `[start, end]` represents a meeting from time `start` to time `end`

### Return Value
- Integer representing the minimum number of days required to schedule all meetings

## Example Walkthrough

Consider meetings: `[[1, 4], [2, 5], [3, 6]]`

1. **Sorted start times**: [1, 2, 3]
2. **Sorted end times**: [4, 5, 6]
3. **Sweep process**:
   - Time 1: +1 meeting (total: 1)
   - Time 2: +1 meeting (total: 2) 
   - Time 3: +1 meeting (total: 3) ← Maximum overlap
   - Time 4: -1 meeting (total: 2)
   - Time 5: -1 meeting (total: 1)
   - Time 6: -1 meeting (total: 0)

**Result**: 3 days required (maximum overlap was 3)

## Test Cases

The implementation includes several test cases demonstrating different scenarios:

- **Overlapping meetings**: `[[0, 30], [5, 10], [15, 20]]` → 2 days
- **Non-overlapping meetings**: `[[7, 10], [2, 4]]` → 1 day  
- **Identical meetings**: `[[1, 5], [8, 9], [8, 9]]` → 2 days
- **Cascading overlaps**: `[[1, 4], [2, 5], [3, 6]]` → 3 days
- **Adjacent meetings**: `[[1, 2], [2, 3], [3, 4], [4, 5]]` → 1 day

## Usage

```java
MeetingScheduler scheduler = new MeetingScheduler();
int[][] meetings = {{1, 4}, {2, 5}, {3, 6}};
int daysNeeded = scheduler.minDaysRequired(meetings);
System.out.println("Days required: " + daysNeeded); // Output: 3
```

## Edge Cases Handled

- Empty input array returns 0
- Null input returns 0
- Single meeting returns 1
- Meetings with same start/end times
- Adjacent meetings (end time of one equals start time of another)
