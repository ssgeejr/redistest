# Redis Active-Active Cluster

Instructions: 

https://docs.redis.com/latest/rs/getting-started/getting-started-active-active/

https://docs.redis.com/latest/rs/administering/creating-databases/create-active-active/


add to ansible: sudo pip install redis

docker run -d --cap-add sys_resource -h rp1_node1 --name rp1_node1 -p 8443:8443 -p 9443:9443 -p 12000:12000 redislabs/redis:6.2.4-54

docker run -d --cap-add sys_resource -h rp2_node1 --name rp2_node1 -p 8445:8443 -p 9445:9443 -p 12002:12000 redislabs/redis



## Node-1

docker exec -it rp1_node1 bash
redis-cli -p 12000
set key1 123
get key1

## Node-2

docker exec -it rp2_node1 bash
redis-cli -p 12000
get key1
