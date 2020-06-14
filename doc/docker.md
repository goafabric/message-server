#Docker
docker run --name activemq -d -p 61616:61616 -p 8161:8161 rmohr/activemq

docker run --name activemq-artemis -d -p 61616:61616 -p 8161:8161 \
-e ARTEMIS_USERNAME=admin -e ARTEMIS_PASSWORD=admin vromero/activemq-artemis


# Web Console
http://localhost:8161/admin/
admin/admin 