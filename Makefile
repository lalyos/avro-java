schema-compile:
	java -jar lib/avro-tools-1.11.0.jar \
  		compile schema user.avsc .

compile:
	javac -cp ".:lib/*" Generator.java Reader.java

run-gen: compile
	java -cp ".:lib/*" Generator

run-read: compile
	java -cp ".:lib/*" Reader