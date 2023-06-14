echo -------- ATUALIZANDO GIT --------------------
git pull
echo -------- MAVEN  --------------------
mvn package
echo -------- DESATIVA DOCKER COMPOSE --------------------
docker-compose down
echo -------- REMOVE IMAGEM DOCKER --------------------
docker rmi -f san-gateway-server
echo -------- CRIA NOVA IMAGEM DOCKER --------------------
docker build --tag=san-gateway-server:latest .
echo -------- LEVANTA DOCKER COMPOSE --------------------
docker-compose up --d
echo -------- CONCLUIDO COM SUCESSO --------------------
