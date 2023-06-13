echo -------- ATUALIZANDO GIT --------------------
git pull
echo -------- MAVEN  --------------------
mvn package
echo -------- DESATIVA DOCKER COMPOSE --------------------
docker-compose down
echo -------- REMOVE IMAGEM DOCKER --------------------
docker rmi -f san-security-server
echo -------- CRIA NOVA IMAGEM DOCKER --------------------
docker build --tag=san-security-server:latest .
echo -------- LEVANTA DOCKER COMPOSE --------------------
docker-compose up --d
echo -------- CONCLUIDO COM SUCESSO --------------------
