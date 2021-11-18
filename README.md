This is a sample java project using Avro.

There are 3 players:
- the schema: [user.avsc]
- Generator: creates 3 users based on the schema, and writes it into a file
- Reader: reads the file, decodes the users and prints them.

For simplicity: no maven/gradle nonsence ...

## Generate Schema
Altough one of the benefits of Avro is its dynamic nature, so most of the timeyou don't need/want to generate code, this example demonstartes how todo it.

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