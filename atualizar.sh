echo -------- ATUALIZANDO GIT --------------------
git pull
echo -------- DESATIVA DOCKER COMPOSE --------------------
docker-compose down
echo -------- MAVEN eureka --------------------
cd san-eureka
mvn package
docker rmi -f eureka-server
docker build --tag=eureka-server:latest .
echo -------- MAVEN  gateway--------------------
cd ../san-gateway
mvn package
docker rmi -f gateway-server
docker build --tag=gateway-server:latest .
echo -------- MAVEN  security--------------------
cd ../san-security
mvn package
docker rmi -f security-server
docker build --tag=security-server:latest .
echo -------- LEVANTA DOCKER COMPOSE --------------------
docker-compose up --d
echo -------- CONCLUIDO COM SUCESSO --------------------
