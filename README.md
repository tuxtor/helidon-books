# helidon-books

Helidon MP application that uses the dbclient API with MySQL database.

## Getting started

To execute this example you will need to run a MySQL database with the following dummy credentials

Database: books
Username: user
Password: changeit
JDBC Url: jdbc:mysql://127.0.0.1:3306/books?useSSL=false

You could start this database by using Docker, please note this database will be deleted after stopping the container:

```bash
docker run --name helidon-books --rm -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_USER=user -e MYSQL_PASSWORD=changeit -e MYSQL_DATABASE=books -p 3306:3306 mysql:8.3
```

## Build and run


With JDK21
```bash
mvn package

#Same as docker execution above
export JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_URL="jdbc:mysql://127.0.0.1:3306/books?useSSL=false&allowPublicKeyRetrieval=true"
export JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_USER=user
export JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_PASSWORD=changeit

java -jar target/helidon-books.jar
```

## Build and run with Docker

```bash
export JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_URL="jdbc:mysql://127.0.0.1:3306/books?useSSL=false&allowPublicKeyRetrieval=true"
export JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_USER=user
export JAVAX_SQL_DATASOURCE_BOOKSDS_DATASOURCE_PASSWORD=changeit

```


## Building a Native Image

The generation of native binaries requires an installation of GraalVM 22.1.0+.

You can build a native binary using Maven as follows:

```
mvn -Pnative-image install -DskipTests
```

The generation of the executable binary may take a few minutes to complete depending on
your hardware and operating system. When completed, the executable file will be available
under the `target` directory and be named after the artifact ID you have chosen during the
project generation phase.



### Database Setup

Start your database before running this example.

Example docker commands to start databases in temporary containers:

MySQL:
```
docker run --rm --name mysql -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=pokemon \
    -e MYSQL_USER=user \
    -e MYSQL_PASSWORD=changeit \
    mysql:5.7
```



## Building the Docker Image

```
docker build -t helidon-books .
```

## Running the Docker Image

```
docker run --rm -p 8080:8080 helidon-books:latest
```

Exercise the application as described above.
                                

## Run the application in Kubernetes

If you don’t have access to a Kubernetes cluster, you can [install one](https://helidon.io/docs/latest/#/about/kubernetes) on your desktop.

### Verify connectivity to cluster

```
kubectl cluster-info                        # Verify which cluster
kubectl get pods                            # Verify connectivity to cluster
```

### Deploy the application to Kubernetes

```
kubectl create -f app.yaml                              # Deploy application
kubectl get pods                                        # Wait for quickstart pod to be RUNNING
kubectl get service  helidon-books                     # Get service info
kubectl port-forward service/helidon-books 8081:8080   # Forward service port to 8081
```

You can now exercise the application as you did before but use the port number 8081.

After you’re done, cleanup.

```
kubectl delete -f app.yaml
```

