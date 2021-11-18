schema-compile:
	java -jar lib/avro-tools-1.11.0.jar \
  		compile schema user.avsc .

compile:
	javac -cp ".:lib/*" *.java

run-gen: compile
	java -cp ".:lib/*" Generator

run-read: compile
	java -cp ".:lib/*" Reader

run-dynamic-gen: compile
	java -cp ".:lib/*" DynamicGenerator

run-dynamic-reader: compile
	java -cp ".:lib/*" DynamicReader
