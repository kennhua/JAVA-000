**windows搭建kafka集群**

**复制三个配置文件并修改配置**  
**server-9001.properties**  
`broker.id=1`  
`listeners=PLAINTEXT://:9001`  
`log.dirs=D:/tmp/kafka-logs-9001 `

**server-9002.properties**  
`broker.id=2 `  
`listeners=PLAINTEXT://:9002 `   
`log.dirs=D:/tmp/kafka-logs-9002`

**server-9003.properties**  
`broker.id=3`  
`listeners=PLAINTEXT://:9003`  
`log.dirs=D:/tmp/kafka-logs-9003`   

启动zookeeper  
`zookeeper-server-start.bat ../../config/zookeeper.properties`    

分别启动三个节点  
`kafka-server-start.bat ../../config/server-9001.properties`  
`kafka-server-start.bat ../../config/server-9002.properties`  
`kafka-server-start.bat ../../config/server-9003.properties`    

**执行操作测试**   
创建带有副本的topic  
`kafka-topics.bat --zookeeper localhost:2181 --create --topic test32
--partitions 3 --replication-factor 2`  
进入生产者9003节点  
`kafka-console-producer.bat --bootstrap-server localhost:9003 --topic
test32`    
进入消费者9001节点   
`kafka-console-consumer.bat --bootstrap-server
localhost:9001 --topic test32 --from-beginning`
