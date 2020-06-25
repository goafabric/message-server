#ActiveMQ
docker run --name activemq -d -p 61616:61616 -p 8161:8161 rmohr/activemq

http://localhost:8161/admin/
admin/admin 

#Artemis
docker run --name activemq-artemis -d -p 61616:61616 -p 8161:8161 \
-e ARTEMIS_USERNAME=admin -e ARTEMIS_PASSWORD=admin \
vromero/activemq-artemis

http://localhost:8161/admin/
admin/admin 

#RabbitMQ
docker run -d --hostname my-rabbit --name rabbitmq -p 5672:5672 -p 15672:15672 \
-e RABBITMQ_DEFAULT_USER=admin -e RABBITMQ_DEFAULT_PASS=admin \
rabbitmq:3-management

http://localhost:15672/admin/
admin/admin 
