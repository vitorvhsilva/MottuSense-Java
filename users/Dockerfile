# ===== BUILD =====
FROM maven:3.9.9-eclipse-temurin-21-alpine AS build
WORKDIR /app
# (contexto na raiz) -> caminhos com users/
COPY users/pom.xml .
COPY users/src ./src
RUN mvn -q clean package -DskipTests

# ===== RUNTIME (Alpine + JDK) =====
FROM eclipse-temurin:21-jdk-alpine

ENV APP_USER=appuser \
    APP_HOME=/opt/mottu-sense \
    APP_PORT=8080

# cria usuário/grupo no Alpine
RUN addgroup -S ${APP_USER} \
 && adduser -S ${APP_USER} -G ${APP_USER} -h ${APP_HOME} \
 && mkdir -p ${APP_HOME}

WORKDIR ${APP_HOME}

# copia o JAR gerado já com dono correto
COPY --from=build --chown=${APP_USER}:${APP_USER} /app/target/users-0.0.1-SNAPSHOT.jar ./app.jar

USER ${APP_USER}
EXPOSE ${APP_PORT}
CMD ["java","-jar","app.jar"]
