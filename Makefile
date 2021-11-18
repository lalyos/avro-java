schema-compile:
	java -jar lib/avro-tools-1.11.0.jar \
  		compile schema user.avsc .

compile:
	javac -cp ".:lib/*" Generator.java

run: compile
	java -cp ".:lib/*" Generator