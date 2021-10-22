# redistest
Testing Redis with Docker


redis-cli CONFIG GET dir

redis-cli CONFIG GET *


SET mykey "Hello"

GET mykey

SET anotherkey "will expire in a minute" EX 60



https://hub.docker.com/r/redislabs/redisinsight


maybe ... 
docker run -v redisinsight:/db -p 8001:8001 redislabs/redisinsight:latest
