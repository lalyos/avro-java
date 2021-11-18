import example.avro.User;
import org.apache.avro.specific.*;
import org.apache.avro.file.*;
import org.apache.avro.io.*;
import java.io.*;
import org.apache.avro.generic.*;
import org.apache.avro.*;

public class DynamicGenerator {
    public static void main(String[] args) {
        try{
            Schema schema = new Schema.Parser().parse(new File("user.avsc"));
            GenericRecord user1 = new GenericData.Record(schema);
            user1.put("name", "Alyssa");
            user1.put("favorite_number", 256);
            // Leave favorite color null

            GenericRecord user2 = new GenericData.Record(schema);
            user2.put("name", "Ben");
            //user2.put("age", 33);
            user2.put("favorite_number", 7);
            user2.put("favorite_color", "red");

            // Serialize user1 and user2 to disk
            File file = new File("users.avro");
            DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<GenericRecord>(schema);
            DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter);
            dataFileWriter.create(schema, file);
            dataFileWriter.append(user1);
            dataFileWriter.append(user2);
            dataFileWriter.close();

        } catch(Exception e) {
            System.out.println(e);

        }
    }

}
