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
docker run --name helidon-books --rm -e MYSQL_USER=user -e MYSQL_PASSWORD=changeit -e MYSQL_DATABASE=books -p 3306:3306 -d mysql:8.3
docker run --name helidon-books --rm -e MYSQL_RANDOM_ROOT_PASSWORD=yes -e MYSQL_USER=user -e MYSQL_PASSWORD=changeit -e MYSQL_DATABASE=books -p 3306:3306 mysql:8.3
```

## Build and run


With JDK21
```bash
mvn package
java -jar target/helidon-books.jar
```

## Exercise the application

Basic:
```
curl -X GET http://localhost:8080/simple-greet
Hello World!
```


JSON:
```
curl -X GET http://localhost:8080/greet
{"message":"Hello World!"}

curl -X GET http://localhost:8080/greet/Joe
{"message":"Hello Joe!"}

curl -X PUT -H "Content-Type: application/json" -d '{"greeting" : "Hola"}' http://localhost:8080/greet/greeting

curl -X GET http://localhost:8080/greet/Jose
{"message":"Hola Jose!"}
```



## Try metrics

```
# Prometheus Format
curl -s -X GET http://localhost:8080/metrics
# TYPE base:gc_g1_young_generation_count gauge
. . .

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8080/metrics
{"base":...
. . .
```



## Try health

```
curl -s -X GET http://localhost:8080/health
{"outcome":"UP",...

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

