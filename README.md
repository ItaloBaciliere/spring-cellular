# spring-cellular
Batch application that generates billing reports



## Run the application

Open the terminal

```console
$ ./mvnw clean package -Dmaven.test.skip=true
```

Then, launch the job and pass the input file as a parameter with the following command:

```console
$ java -jar target/billing-job-0.0.1-SNAPSHOT.jar 
input.file=src/main/resources/billing-2023-01.csv
```

```console
$ docker exec postgres psql -U postgres -c 'select * from BATCH_STEP_EXECUTION;'
```

## Run the tests

```console
$ ./mvnw clean test -Dspring.batch.job.enabled=false
```

## Review database structure
```console
$ docker exec postgres psql -U postgres -c 'select count(*) from BILLING_DATA;'
```

The property -Dspring.batch.job.enabled=false disables the automatic execution of the job by Spring 
Boot. Without this property, the job will be executed twice: a first time at the application's startup, 
and a second time during the test. This is obviously not desired when running tests, hence the use of 
that property.


## Reading and Writing Data
### Understanding the Chunk-Oriented Processing Model
Spring Batch comes with a processing model that is designed and implemented to address those challenges. It is called the chunk-oriented processing model. The idea of this model is to process the datasource in chunks of a configurable size.