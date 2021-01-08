#Windows部署redis主从、哨兵、集群  
#redis主从
**下载redis文件，版本：3.0.501**  
https://github.com/MicrosoftArchive/redis/releases  
解压安装包，复制2份redis.windows.conf配置文件，分别命名为redis.windows6380.conf和redis.windows6381.conf  

**修改配置**  
redis.windows6380.conf如下：  
port 6380  
bind 127.0.0.1  
slaveof 127.0.0.1 6379     
redis.windows6381.conf如下：  
port 6381  
bind 127.0.0.1  
slaveof 127.0.0.1 6379    

**启动服务**  
.\redis-server.exe redis.windows.conf  
.\redis-server.exe redis.windows6380.conf  
.\redis-server.exe redis.windows6381.conf    

**查看主从**  
.\redis-cli.exe -h 127.0.0.1 -p 6380  
127.0.0.1:6380> info replication  
role:slave  
master_host:127.0.0.1  
master_port:6379  
master_link_status:up  
master_last_io_seconds_ago:4  
master_sync_in_progress:0  
slave_repl_offset:155  
slave_priority:100  
slave_read_only:1  
connected_slaves:0  
master_repl_offset:0  
repl_backlog_active:0  
repl_backlog_size:1048576  
repl_backlog_first_byte_offset:0  
repl_backlog_histlen:0  

**验证主从同步**  
主节点  
127.0.0.1:6379> set aa 123  
OK  
从节点  
127.0.0.1:6380> get aa  
"123"  

#redis哨兵  
创建sentinel26379.conf文件，然后复制两份sentinel26380.conf、sentinel26381.conf  

**sentinel26379.conf文件内容**  
port 26379  
sentinel monitor mymaster 127.0.0.1 6379 2  
sentinel down-after-milliseconds mymaster 10000  
sentinel failover-timeout mymaster 15000    

**sentinel26380.conf文件内容**  
port 26380  
sentinel monitor mymaster 127.0.0.1 6379 2  
sentinel down-after-milliseconds mymaster 10000  
sentinel failover-timeout mymaster 15000    

**sentinel26381.conf文件内容**  
port 26381  
sentinel monitor mymaster 127.0.0.1 6379 2  
sentinel down-after-milliseconds mymaster 10000  
sentinel failover-timeout mymaster 15000    

**启动哨兵服务**  
.\redis-server.exe .\sentinel26379.conf --sentinel  
.\redis-server.exe .\sentinel26380.conf --sentinel  
.\redis-server.exe .\sentinel26381.conf --sentinel  
  
 **关闭6379节点前查看主节点信息**  
 PS D:\redis\Redis-x64-3.0.501> .\redis-cli.exe -h 127.0.0.1 -p 6379  
 127.0.0.1:6379> info replication  
 role:master  
 connected_slaves:2  
 slave0:ip=127.0.0.1,port=6380,state=online,offset=28943,lag=1  
 slave1:ip=127.0.0.1,port=6381,state=online,offset=28943,lag=1  
 master_repl_offset:29076  
 repl_backlog_active:1  
 repl_backlog_size:1048576  
 repl_backlog_first_byte_offset:2  
 repl_backlog_histlen:29075    
 
**关闭6379节点，查看6380节点，可以看到主节点变成了6381**  
  PS D:\redis\Redis-x64-3.0.501> .\redis-cli.exe -h 127.0.0.1 -p 6380  
  127.0.0.1:6380> info replication  
  role:slave  
  master_host:127.0.0.1  
  master_port:6381  
  master_link_status:up  
  master_last_io_seconds_ago:0  
  master_sync_in_progress:0  
  slave_repl_offset:16894  
  slave_priority:100  
  slave_read_only:1  
  connected_slaves:0  
  master_repl_offset:0  
  repl_backlog_active:0  
  repl_backlog_size:1048576  
  repl_backlog_first_byte_offset:0  
  repl_backlog_histlen:0  
  
  **再启动6379节点，可以看出6379变成了6381的slave**  
  PS D:\redis\Redis-x64-3.0.501> .\redis-cli.exe -h 127.0.0.1 -p 6379  
  127.0.0.1:6379> info replication  
  role:slave  
  master_host:127.0.0.1  
  master_port:6381  
  master_link_status:down  
  master_last_io_seconds_ago:-1  
  master_sync_in_progress:0  
  slave_repl_offset:1  
  master_link_down_since_seconds:jd  
  slave_priority:100  
  slave_read_only:1  
  connected_slaves:0  
  master_repl_offset:0  
  repl_backlog_active:0  
  repl_backlog_size:1048576  
  repl_backlog_first_byte_offset:0  
  repl_backlog_histlen:0    
  
  #集群
  1.安装ruby环境  
  双击下载的“rubyinstaller-2.7.2-1-x64.exe”安装即可，同样，为了操作方便，也是建议安装在盘符根目录下，如： D:\Ruby27-x64 ，安装会默认把ruby添加到path环境变量  
  2.安装Ruby环境下Redis的驱动
  将下载的"Ruby环境下Redis的驱动文件(redis-3.2.2.gem)"拷贝到Ruby安装根目录（D:\Ruby27-x64）下。  
  然后执行安装命令如下：  
  gem install --local D:/Ruby27-x64/redis-3.2.2.gem  
  3.将下载的“创建Redis集群的ruby脚本文件redis-trib.rb”文件拷贝到Redis安装根目录(D:\redis\Redis-x64-3.0.501)下  
  4.新建配置文件redis.windows-service-6380.conf，复制两份6381,6382  
  
  **redis.windows-service-6380.conf内容如下：**  
  port 6380        
  loglevel notice      
  logfile "D:/redis/Redis-x64-3.0.501/Logs/redis6380_log.txt"       
  appendonly yes
  appendfilename "appendonly.6380.aof"   
  cluster-enabled yes                                    
  cluster-config-file nodes.6380.conf  
  cluster-node-timeout 15000  
  cluster-slave-validity-factor 10  
  cluster-migration-barrier 1  
  cluster-require-full-coverage yes  
  
**其他5个类似**
   
 **启动服务**  
 .\redis-server.exe redis.windows-service-6380.conf  
 .\redis-server.exe redis.windows-service-6381.conf  
 .\redis-server.exe redis.windows-service-6382.conf  
 .\redis-server.exe redis.windows-service-6383.conf   
 .\redis-server.exe redis.windows-service-6384.conf   
 .\redis-server.exe redis.windows-service-6385.conf 
 
 
 **创建集群**  
PS D:\redis\Redis-x64-3.0.501> .\redis-trib.rb create --replicas 1 127.0.0.1:6380 127.0.0.1:6381 127.0.0.1:6382 127.0.0.1:6383 127.0.0.1:6384 127.0.0.1:6385  
>>> Creating cluster
Connecting to node 127.0.0.1:6380: OK
Connecting to node 127.0.0.1:6381: OK
Connecting to node 127.0.0.1:6382: OK
Connecting to node 127.0.0.1:6383: OK
Connecting to node 127.0.0.1:6384: OK
Connecting to node 127.0.0.1:6385: OK
>>> Performing hash slots allocation on 6 nodes...
Using 3 masters:
127.0.0.1:6380
127.0.0.1:6381
127.0.0.1:6382
Adding replica 127.0.0.1:6383 to 127.0.0.1:6380
Adding replica 127.0.0.1:6384 to 127.0.0.1:6381
Adding replica 127.0.0.1:6385 to 127.0.0.1:6382
M: d9e1bb260df8eeb521c8f63494513d373c4adcce 127.0.0.1:6380
   slots:0-5460 (5461 slots) master
M: 1ca2c99d5d87f2abf022277072d1915fa6afa516 127.0.0.1:6381
   slots:5461-10922 (5462 slots) master
M: 3905c2d976ad48e2476815c5a569e2544724f04d 127.0.0.1:6382
   slots:10923-16383 (5461 slots) master
S: 3d3e63d919dadebd7150b2cc92215e5ff53acaea 127.0.0.1:6383
   replicates d9e1bb260df8eeb521c8f63494513d373c4adcce
S: 896cea67625975f030d6f710e479751928f28e02 127.0.0.1:6384
   replicates 1ca2c99d5d87f2abf022277072d1915fa6afa516
S: 39fd08da0cbe17189b7bcb641e7e7e3441021d54 127.0.0.1:6385
   replicates 3905c2d976ad48e2476815c5a569e2544724f04d
Can I set the above configuration? (type 'yes' to accept): yes
>>> Nodes configuration updated
>>> Assign a different config epoch to each node
>>> Sending CLUSTER MEET messages to join the cluster
Waiting for the cluster to join...
>>> Performing Cluster Check (using node 127.0.0.1:6380)
M: d9e1bb260df8eeb521c8f63494513d373c4adcce 127.0.0.1:6380
   slots:0-5460 (5461 slots) master
M: 1ca2c99d5d87f2abf022277072d1915fa6afa516 127.0.0.1:6381
   slots:5461-10922 (5462 slots) master
M: 3905c2d976ad48e2476815c5a569e2544724f04d 127.0.0.1:6382
   slots:10923-16383 (5461 slots) master
M: 3d3e63d919dadebd7150b2cc92215e5ff53acaea 127.0.0.1:6383
   slots: (0 slots) master
   replicates d9e1bb260df8eeb521c8f63494513d373c4adcce
M: 896cea67625975f030d6f710e479751928f28e02 127.0.0.1:6384
   slots: (0 slots) master
   replicates 1ca2c99d5d87f2abf022277072d1915fa6afa516
M: 39fd08da0cbe17189b7bcb641e7e7e3441021d54 127.0.0.1:6385
   slots: (0 slots) master
   replicates 3905c2d976ad48e2476815c5a569e2544724f04d
[OK] All nodes agree about slots configuration.
>>> Check for open slots...
>>> Check slots coverage...
[OK] All 16384 slots covered.
  
  
 **集群启动成功**