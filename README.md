# helidon-books

Helidon MP application that uses the dbclient API with MySQL database.

## Getting started

To execute this example you need to run a MySQL database with (dummy?) credentials

Database: books
Username: user
Password: changeit
JDBC Url: jdbc:mysql://127.0.0.1:3306/books?useSSL=false

You could start this database by using Docker, please note this database will be deleted after stopping the container:

```bash
docker run --name helidon-books-db --rm -e MYSQL_RANDOM_ROOT_PASSWORD=yes \
  -e MYSQL_USER=user \
  -e MYSQL_PASSWORD=changeit \
  -e MYSQL_DATABASE=books -p 3306:3306 mysql:8.3
```

## Build and run


With JDK21
```bash
mvn package

#Same values as the database
export JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_URL="jdbc:mysql://127.0.0.1:3306/books?useSSL=false&allowPublicKeyRetrieval=true"
export JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_USER=user
export JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_PASSWORD=changeit

java -jar target/helidon-books.jar
```

## Build and run with Docker


### Mac

```bash
mvn clean verify k8s:build

docker run --name helidon-books --rm \
  -e JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_URL="jdbc:mysql://host.docker.internal:3306/books?useSSL=false&allowPublicKeyRetrieval=true" \
  -e JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_USER=user \
  -e JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_PASSWORD=changeit \
  -p 8080:8080 \
  tuxtor/helidon-books:latest
```

### Linux (Bare metal Docker)

```bash
mvn clean verify k8s:build

docker run --name helidon-books --rm \
  -e JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_URL="jdbc:mysql://localhost:3306/books?useSSL=false&allowPublicKeyRetrieval=true" \
  -e JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_USER=user \
  -e JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_PASSWORD=changeit \
  -p 8080:8080 \
  tuxtor/helidon-books:latest
```

## Run the application in Kubernetes

If you don’t have access to a Kubernetes cluster, you can [install one](https://helidon.io/docs/latest/#/about/kubernetes) on your desktop and use it as the default kubernetes cluster.

Currently JKube is configured to publish the image at Dockerhub

```xml
<properties>
    <jkube.generator.name>tuxtor/helidon-books:%l</jkube.generator.name>
</properties>
```

You could adapt this part to use [another registry](https://eclipse.dev/jkube/docs/kubernetes-maven-plugin/#registry) -e.g. AWS, Oracle Cloud, Azure-. Then you could simply execute

```xml
mvn k8s:build k8s:push
```

To execute the application, they you could generate the final Kubernetes descriptor and apply it

```xml
mvn k8s:resource k8s:apply
```