#!/bin/bash

register-schema() {
    TOPIC=user
    AVRO_SCHEMA_FILE=user.avsc
    PAYLOAD=user.json

    SCHEMA=$(cat ${AVRO_SCHEMA_FILE} | sed 's/"/\\\"/g' | tr -d '\n')

SR=$( cat <<EOF
{
"schema": "${SCHEMA}"
}
EOF
)

    # Publish Schema with Schema Registry
    ID=$(curl -s -X POST -H "Content-Type: application/json" --data "$SR" http://${SCHEMA_REGISTRY:-localhost:8081}/subjects/${TOPIC}-value/versions | jq .id)
    echo "Schema Registered As : $ID"
}


send-avro() {
    #rm data_file
    # Magic Byte 0x00
    printf '\x00' > data_file

    # Schema Registry ID as 32bit WORD
    printf "0: %.8x" $ID | xxd -r -g0 >> data_file

    # Convert JSON payload into Avro using Avro's own toolage, write only the binary data of Avro to the file
    java -jar ./lib/avro-tools-1.11.0.jar jsontofrag --schema-file ${AVRO_SCHEMA_FILE} ${PAYLOAD} >> data_file

    # Publish the file using kafkacat add /dev/null to ensure file is treated as a single message
    kcat -P -t ${TOPIC} data_file
}