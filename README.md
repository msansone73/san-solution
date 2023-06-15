# san-solution
Package of projects in spring boot of a microservice


## Content of solution 

```
** san-eureka: ** Eureka server is a Discovery server of spring cloud
** san-gateway: ** API Gateway, porta de entrada 8000
** san-security: ** rest service with authentication service for san-solutuon  
** PostgreSql: ** relational database of san-solution
```

## Configurations
All configurations are stored in enviornment variables, follow list of vaiables:

```
export sansoneDB=jdbc:mysql://address:3306/database
export sansoneDBUser= postgres user
export sansoneDBPass= postgres password
export eurekaServerUrl= eureka server
````

## Rotas

### Conexão externa https://meu-site/api/

### nginx (rota de https://meu-site/api/ para API Gateway):
```
...
location /api/ {
    proxy_pass http://127.0.0.1:8000/api/
}
...
```

### API GAteway (exemplo de rota)

```
 spring:
   application:
     name: san-gateway
   cloud:
     gateway:
       routes:
       - id: sa-secure-gateway
         uri: lb://SAN-SECURITY
         predicates:
         - Path=/api/security/**
```