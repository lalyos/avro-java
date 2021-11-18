import example.avro.User;
import org.apache.avro.specific.*;
import org.apache.avro.file.*;
import org.apache.avro.io.*;
import java.io.*;
import org.apache.avro.generic.*;
import org.apache.avro.file.*;
import org.apache.avro.*;

public class DynamicReader {
    public static void main(String[] args) {
      try{
        Schema schema = new Schema.Parser().parse(new File("user.avsc"));

        File file = new File("users.avro");
        // Deserialize users from disk
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader);
        GenericRecord user = null;
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
