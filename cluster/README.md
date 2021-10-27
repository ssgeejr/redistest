# Redis Active-Active Cluster

Instructions: https://docs.redis.com/latest/rs/getting-started/getting-started-active-active/


docker run -d --cap-add sys_resource -h rp1_node1 --name rp1_node1 -p 8443:8443 -p 9443:9443 -p 12000:12000 redislabs/redis:6.2.4-54

docker run -d --cap-add sys_resource -h rp2_node1 --name rp2_node1 -p 8445:8443 -p 9445:9443 -p 12002:12000 redislabs/redis

