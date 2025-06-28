import java.util.Arrays;

public class MeetingScheduler {

    public int minDaysRequired(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;

        int n = intervals.length;
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];

        for (int i = 0; i < n; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int startPtr = 0, endPtr = 0;
        int ongoingMeetings = 0, maxOverlap = 0;

        while (startPtr < n) {
            if (startTimes[startPtr] < endTimes[endPtr]) {
                ongoingMeetings++;
                maxOverlap = Math.max(maxOverlap, ongoingMeetings);
                startPtr++;
            } else {
                ongoingMeetings--;
                endPtr++;
            }
        }

        return maxOverlap;
    }

    public static void main(String[] args) {
        MeetingScheduler scheduler = new MeetingScheduler();

        int[][] intervals1 = {{0, 30}, {5, 10}, {15, 20}};
        System.out.println(scheduler.minDaysRequired(intervals1)); // Output: 2

        int[][] intervals2 = {{7, 10}, {2, 4}};
        System.out.println(scheduler.minDaysRequired(intervals2)); // Output: 1

        int[][] intervals3 = {{1, 5}, {8, 9}, {8, 9}};
        System.out.println(scheduler.minDaysRequired(intervals3)); // Output: 2

        int[][] intervals4 = {{1, 4}, {2, 5}, {3, 6}};
        System.out.println(scheduler.minDaysRequired(intervals4)); // Output: 3

        int[][] intervals5 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}};
        System.out.println(scheduler.minDaysRequired(intervals5)); // Output: 1
    }
}