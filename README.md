# clientsapis
Exercise for Platform Builders

docker network create clientsapi-app
docker run --net clientsapi-app --name clientsapis-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=mdymen_pass -d mysql:latest
docker exec -i clientsapis-mysql sh -c 'exec mysql -uroot -p"$MYSQL_ROOT_PASSWORD"' < src/main/resources/data.sql
