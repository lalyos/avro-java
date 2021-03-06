import example.avro.User;
import org.apache.avro.specific.*;
import org.apache.avro.file.*;
import org.apache.avro.io.*;
import java.io.*;

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

        try{
        // Serialize user1, user2 and user3 to disk
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(), new File("users.avro"));
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();
        } catch(Exception e) {
            System.out.println(e);

        }
    }

}
