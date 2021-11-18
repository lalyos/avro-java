import example.avro.User;
import org.apache.avro.specific.*;
import org.apache.avro.file.*;
import org.apache.avro.io.*;
import java.io.*;

public class Reader {
    public static void main(String[] args) {
      try{
        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(new File("users.avro"), userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
            // Reuse user object by passing it to next(). This saves us from
            // allocating and garbage collecting many objects for files with
            // many items.
            user = dataFileReader.next(user);
            System.out.println(user);
        }
      } catch(Throwable e){
        System.out.println(e);
      }
    }

}
