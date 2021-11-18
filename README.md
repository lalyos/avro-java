This is a sample java project using Avro.

There are 3 players:
- the schema: [user.avsc]
- Generator: creates 3 users based on the schema, and writes it into a file
- Reader: reads the file, decodes the users and prints them.

For simplicity: no maven/gradle nonsence ...

## Generate Schema
Altough one of the benefits of Avro is its dynamic nature, so most of the timeyou don't need/want to generate code, this example first demonstartes how todo it.

```
make schema-compile

## or manually:
java -jar lib/avro-tools-1.11.0.jar compile schema user.avsc .
```

## Compile

```
make compile

## or if you dont have make
javac -cp ".:lib/*" Generator.java Reader.java
```

## Run the Generator

```
make run-gen

## or manually
java -cp ".:lib/*" Generator
```

## Run the Reader
```
make run-read
## or manually
java -cp ".:lib/*" Reader
```

# Without Schema Generaton

The 2 `DynamicXXX.java` show how to use avro without generating any schema.

Modify the `user.asvc` by adding anew field: `age`
```
  "fields": [
      {"name": "name", "type": "string"},
+     {"name": "age", "type": ["int", "null"]},
      {"name": "favorite_number",  "type": ["int", "null"]},
      {"name": "favorite_color", "type": ["string", "null"]}
```

Use the newly added field in `DynamicGenerator`:
```
             GenericRecord user2 = new GenericData.Record(schema);
             user2.put("name", "Ben");
-            //user2.put("age", 33);
+            user2.put("age", 33);
             user2.put("favorite_number", 7);
             user2.put("favorite_color", "red");
```

Now try to generate a new binary file:
```
make run-dynamic-gen
```

Read it with the DynamicReader:
```
make run-dynamic-reader
```
> Notice: the age field is read!

Now try to use the outdated Reader (with old generated classes):
First try to guess what happens"
- Error: unable to read as schema doesn't matches?
- Something else ...
```
make run-read
```