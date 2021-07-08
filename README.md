# redistest
Testing Redis with Docker


redis-cli CONFIG GET dir

redis-cli CONFIG GET *


SET mykey "Hello"

GET mykey

SET anotherkey "will expire in a minute" EX 60
