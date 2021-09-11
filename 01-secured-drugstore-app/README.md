# not secured drugstore app

## setup keycloack

```bash
docker run --rm --name keycloack-01 -d -p 8080:8080 -e KEYCLOAK_USER=admin -e KEYCLOAK_PASSWORD=admin quay.io/keycloak/keycloak:15.0.2
```

* Login into keycloack http://127.0.0.1:8080/ with `admin` / `admin`
* Create new realm `drugstore-01-realm`
    * on top left corner hover mouse near existing `master` realm and click `Add realm` button
    * enter name `drugstore-01-realm` and click `Create` button
* Create new client `drugstore-01-client-id` with spring-boot applications convention support
    * on left panel click on `Clients` section
    * click `Create` button
    * in `Client ID` field enter `drugstore-01-client-id` and click `Save` button
    * field `Client protocol` must be `openid-connect`
    * field `Access Type` must be `confidential`
    * option `Standard Flow Enabled` must be `on`
    * field `Valid Redirect URIs` must be `http://127.0.0.1:8001/login/oauth2/code/drugstore-01-client-id`
    * click `Save` button
* Get generated client secret
    * on left panel click on `Clients` section
    * click `Credentials` tab
    * and check `Secret` field or click `Regenerate Secret` button if you want: `43206bc2-e44f-4472-b503-3c5b0f392e0a`
* Create new user
    * on left panel click `Users` section
    * click `Add user`
    * field `Username` must be `drugstore-01-user`
    * click `Save` button
    * to create password click `Credentials` button
    * enter `Password` and `Password Confirmation` with value of `drugstore-01-password`
    * switch `off` Temporary option
    * hit `Reset Password` and click `Set Password` to confirm

## configure application

update `01-secured-drugstore-app/src/main/resources/application.properties` file with:

```properties
spring.security.oauth2.client.registration.drugstore-01-client-id.client-id=drugstore-01-client-id
spring.security.oauth2.client.registration.drugstore-01-client-id.client-secret=43206bc2-e44f-4472-b503-3c5b0f392e0a
spring.security.oauth2.client.registration.drugstore-01-client-id.scope=openid, profile, roles
spring.security.oauth2.client.registration.drugstore-01-client-id.authorization-grant-type=authorization_code
# http://127.0.0.1:8080/auth/realms/drugstore-01-realm/.well-known/openid-configuration
spring.security.oauth2.client.provider.drugstore-01-client-id.issuer-uri=\
  http://127.0.0.1:8080/auth/realms/drugstore-01-realm
```

## start application

```bash
cd 01-secured-drugstore-app ; ./mvnw spring-boot:run
```

<!--

# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)
* [Liquibase Migration](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#howto-execute-liquibase-database-migrations-on-startup)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.4/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

The following guides illustrate how to use some features concretely:

* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

-->
