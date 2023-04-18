# spring-boot-jwt-docker-postgresql

### Things todo list:

1. Clone this repository: `git clone https://github.com/hendisantika/spring-boot-jwt-docker-postgresql.git`
2. Navigate to the folder: `cd spring-boot-jwt-docker-postgresql`
3. Change DB credentials with your own in `application.properties`
4. Run the application: `./mvnw clean spring-boot:run`
5. Open your favorite browser: http://localhost:8080/swagger-ui

### Running via docker

Package the application

`./mvnw clean package`

`docker build -t hendisantika/jwt:v3 .`

```shell
docker-compose up
```

## Generate Public and Private Key

Generate an RSA keypair

```
$ openssl genrsa -out keypair.pem 2048
```

Generate public key

```
$ openssl rsa -in keypair.pem -pubout -out public.txt
```

Generate private key

```
$ openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.txt
```

Sample public key

```
-----BEGIN PUBLIC KEY-----
MIIBIjA...xxxxxx....QAB
-----END PUBLIC KEY-----
```

Sample private key

```
-----BEGIN PRIVATE KEY-----
MII....xxxxx.....C/o/q6k
-----END PRIVATE KEY-----
```

### Image Screen shots

Swagger UI

![Swagger UI](img/SwaggerUI.png "Swagger UI")

Register User

![Register User](img/signup.png "Register User")

Sign In User

![Sign In User](img/signin.png "Sign In User")

Get Name

![Get Name](img/get-name.png "Get Name")

Update Name

![Update Name](img/update.png "Update Name")
