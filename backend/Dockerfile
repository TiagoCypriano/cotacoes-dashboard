FROM eclipse-temurin:17-jdk-alpine as build
WORKDIR /app

# Copiar arquivos do Maven
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Dar permissao de execucao ao mvnw
RUN chmod +x ./mvnw

# Baixar dependencias
RUN ./mvnw dependency:go-offline -B

# Copiar codigo fonte
COPY src src

# Buildar o projeto
RUN ./mvnw clean package -DskipTests

# Imagem final
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]