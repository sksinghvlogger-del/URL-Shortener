import java.util.*;
public class URLShortener {
    private static Map<String, String> shortToLong = new HashMap<>();
    private static final String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_LENGTH = 6;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n=== URL Shortener ===");
            System.out.println("1. Shorten URL");
            System.out.println("2. Retrieve Original URL");
            System.out.println("3. View All URLs");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter long URL: ");
                    String longUrl = sc.nextLine();
                    String shortUrl = shortenURL(longUrl);
                    System.out.println("Short URL: http://localhost:8080/" + shortUrl);
                    break;
                case 2:
                    System.out.print("Enter short code: ");
                    String code = sc.nextLine();
                    String original = shortToLong.get(code);
                    if (original != null) {
                        System.out.println("Original URL: " + original);
                    } else {
                        System.out.println("URL not found!");
                    }
                    break;
                case 3:
                    if (shortToLong.isEmpty()) {
                        System.out.println("No URLs stored.");
                    } else {
                        for (Map.Entry<String, String> entry : shortToLong.entrySet()) {
                            System.out.println("short.ly/" + entry.getKey() + " -> " + entry.getValue());
                        }
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
    private static String shortenURL(String longUrl) {
        String shortCode;
        do {
            shortCode = generateCode();
        } while (shortToLong.containsKey(shortCode));
        shortToLong.put(shortCode, longUrl);
        return shortCode;
    }
    private static String generateCode() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SHORT_LENGTH; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
