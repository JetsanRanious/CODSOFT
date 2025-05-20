import java.util.*;

public class EngineeringCourseRecommender {

    static Map<String, Integer> collegeCutoffs = Map.of(
        "Tech Institute A", 250,
        "Engineering College B", 200,
        "National Institute C", 180,
        "Private College D", 220,
        "State University E", 190
    );

    static List<String> courses = List.of(
        "Computer Science",
        "Electrical Engineering",
        "Mechanical Engineering",
        "Civil Engineering",
        "Chemical Engineering"
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your cutoff score: ");
        int cutoff = scanner.nextInt();

        System.out.println("\nBased on your cutoff (" + cutoff + "), you can consider the following colleges:");

        boolean found = false;
        for (Map.Entry<String, Integer> entry : collegeCutoffs.entrySet()) {
            if (cutoff >= entry.getValue()) {
                found = true;
                System.out.println("College: " + entry.getKey());
                System.out.println("Available Courses:");
                for (String course : courses) {
                    System.out.println(" - " + course);
                }
                System.out.println();
            }
        }

        if (!found) {
            System.out.println("Sorry, no colleges found for this cutoff.");
        }

        scanner.close();
    }
}
