FROM openjdk:19-jdk-alpine

RUN apk upgrade --update-cache --available && \
  apk add openssl && \
  rm -rf /var/cache/apk/*

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline

COPY src ./src

RUN openssl genrsa -out ./src/main/resources/certs/keypair.pem 2048
RUN openssl rsa -in ./src/main/resources/certs/keypair.pem -pubout -out ./src/main/resources/certs/public.pem
RUN openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in ./src/main/resources/certs/keypair.pem -out ./src/main/resources/certs/private.pem
RUN rm ./src/main/resources/certs/keypair.pem

# RUN ./mvnw clean install -DskipTests

CMD ["./mvnw", "spring-boot:run"]