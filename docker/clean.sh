docker volume ls -qf dangling=true | xargs docker volume rm
docker-compose down -v --remove-orphans
