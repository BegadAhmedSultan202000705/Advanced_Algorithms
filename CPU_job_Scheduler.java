package submisson;

import java.util.*;

public class CPU_job_Scheduler {
    public static void main(String[] args) {
        // Create a list of CPU jobs with priority and duration
        List<Job> jobs = new ArrayList<>();
        jobs.add(new Job(1, 10));
        jobs.add(new Job(2, 5));
        
        // Set the scheduling policy (1 for FCFS, 2 for priority, 3 for SRTF)
        int policy = 1;
        
        // Initialize the system time, current job, and remaining time
        int time = 0;
        Job current_Job = null;
        int remainingTime = 0;
        
        // Run the simulation until all jobs are completed
        while (!jobs.isEmpty()) {
            // Choose the job based on the scheduling policy
            switch (policy) {
                case 1: // First Come First Served
                    current_Job = jobs.get(0);
                    break;
                case 2: // Highest Priority
                    current_Job = Collections.max(jobs);
                    break;
                case 3: // Shortest Remaining Time First
                    current_Job = jobs.stream()
                            .filter(j -> j.duration > 0)
                            .min(Comparator.comparingInt(j -> j.duration))
                            .orElse(null);
                    break;
            }
            
            // Update the remaining time of the previous job (if any)
            if (remainingTime > 0) {
                remainingTime--;
                if (remainingTime == 0) {
                    System.out.println("Time " + time + ": Job " + current_Job.id + " completed");
                    jobs.remove(current_Job);
                    current_Job = null;
                }
            }
            
            // Start the new job (if any)
            if (current_Job != null && remainingTime == 0) {
                remainingTime = current_Job.duration - 1;
                System.out.println("Time " + time + ": Job " + current_Job.id + " started (priority " + current_Job.priority + ")");
            }
            
            // Increment the system time
            time++;
        }
    }
    
    // A class to represent a CPU job with priority and duration
    static class Job implements Comparable<Job> {
        int id;
        int priority;
        int duration;
        
        public Job(int id, int duration) {
            this.id = id;
            this.duration = duration;
        }
        
        public int compareTo(Job other) {
            return Integer.compare(other.priority, priority);
        }
    }
}