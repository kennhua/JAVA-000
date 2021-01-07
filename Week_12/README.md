#Windows部署redis主从、哨兵、集群  
#redis主从
下载redis文件，版本：3.0.501  
https://github.com/MicrosoftArchive/redis/releases  
解压安装包，复制2份redis.windows.conf配置文件，分别命名为redis.windows6380.conf和redis.windows6381.conf  
修改配置  
redis.windows6380.conf如下：  
port 6380  
bind 127.0.0.1  
slaveof 127.0.0.1 6379     
redis.windows6381.conf如下：  
port 6381  
bind 127.0.0.1  
slaveof 127.0.0.1 6379  
启动服务  
.\redis-server.exe redis.windows.conf  
.\redis-server.exe redis.windows6380.conf  
.\redis-server.exe redis.windows6381.conf  
查看主从  
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
验证主从同步  
主节点  
127.0.0.1:6379> set aa 123  
OK  
从节点  
127.0.0.1:6380> get aa  
"123"  

#redis哨兵  
创建sentinel26379.conf文件，然后复制两份sentinel26380.conf、sentinel26381.conf  
sentinel26379.conf文件内容  
port 26379  
sentinel monitor mymaster 127.0.0.1 6379 2  
sentinel down-after-milliseconds mymaster 10000  
sentinel failover-timeout mymaster 15000    

sentinel26380.conf文件内容  
port 26380  
sentinel monitor mymaster 127.0.0.1 6379 2  
sentinel down-after-milliseconds mymaster 10000  
sentinel failover-timeout mymaster 15000    

sentinel26381.conf文件内容  
port 26381  
sentinel monitor mymaster 127.0.0.1 6379 2  
sentinel down-after-milliseconds mymaster 10000  
sentinel failover-timeout mymaster 15000    

启动哨兵服务  
.\redis-server.exe .\sentinel26379.conf --sentinel  
.\redis-server.exe .\sentinel26380.conf --sentinel  
.\redis-server.exe .\sentinel26381.conf --sentinel  
  
 关闭6379节点前查看主节点信息  
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
 
关闭6379节点，查看6380节点，可以看到主节点变成了6381  
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
  
  再启动6379节点，可以看出6379变成了6381的slave  
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
