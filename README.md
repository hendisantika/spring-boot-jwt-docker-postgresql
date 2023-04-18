# spring-boot-jwt-docker-postgresql

### Things todo list:

1. Clone this repository: `git clone https://github.com/hendisantika/spring-boot-jwt-docker-postgresql.git`
2. Navigate to the folder: `cd spring-boot-jwt-docker-postgresql`
3. Run the application: `mvn clean spring-boot:run`
4. Open your favorite browser: http://localhost:8080/swagger-ui

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
