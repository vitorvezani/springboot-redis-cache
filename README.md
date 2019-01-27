# Springboot Redis Cache Sample

This project is a simple tutorial on how to integrate SpringBoot and Redis as cache implementation

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

### Prerequisites

What things you need to install the software and how to install them

```
Java 8
Docker & Docker Compose
Maven 3
```

### Installing

A step by step series of examples that tell you how to get a development env running

```
docker-compose up -d
mvn clean package && java -jar ./target/*.jar
```

## Running the integration tests

```
docker-compose up -d && mvn clean test
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Spring](http://www.http://spring.io) - The web framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [Redis](https://redis.io/) - Redis

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/vitorvezani/springboot-redis-cache/tags). 

## Authors

* **Vitor Vezani** - *Initial work* - [github](https://github.com/vitorvezani)

See also the list of [contributors](https://github.com/vitorvezani/springboot-redis-cache/contributors) who participated in this project.

## License

This project is licensed under the cd ta - see the [LICENSE.md](LICENSE.md) file for details
