import example.avro.User;

public class Generator {
    public static void main(String[] args) {
        User user1 = new User();
        user1.setName("Alyssa");
        user1.setFavoriteNumber(256);
        // Leave favorite color null

        // Alternate constructor
        User user2 = new User("Ben", 7, "red");

        // Construct via builder
        User user3 = User.newBuilder()
             .setName("Charlie")
             .setFavoriteColor("blue")
             .setFavoriteNumber(null)
             .build();

        System.out.println("u1: "+ user1);
        System.out.println("u2: "+ user2);
        System.out.println("u3: "+ user3);
    }

}
