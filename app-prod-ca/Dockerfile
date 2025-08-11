# Documentation: https://snyk.io/blog/jlink-create-docker-images-spring-boot-java/
### STAGE 1: Build ###
FROM eclipse-temurin:17-jdk AS build

LABEL stage="builder"

# Only install what is necessary for jlink
RUN apt-get update && \
    apt-get install -y --no-install-recommends binutils && \
    rm -rf /var/lib/apt/lists/*

WORKDIR /usr/app

COPY gradlew ./gradlew
COPY gradle ./gradle
COPY build.gradle main.gradle settings.gradle gradle.properties lombok.config ./

# Executes tasks up to the point where it caches dependencies
RUN chmod +x gradlew && ./gradlew build -x test || true

# Copying the code
COPY applications ./applications
COPY domain ./domain
COPY infrastructure ./infrastructure

# Recompile
RUN ./gradlew bootJar --no-daemon --info --stacktrace

# Extract the JAR for analysis with jdeps
RUN mkdir unpacked && \
    cd unpacked && \
    jar -xf /usr/app/applications/app-service/build/libs/*.jar

# Discover required modules
RUN jdeps \
    --ignore-missing-deps \
    --recursive \
    --multi-release 17 \
    --print-module-deps \
    --class-path 'unpacked/BOOT-INF/lib/*' \
    unpacked/BOOT-INF/classes > /tmp/deps.info

# Create a minimal JRE with jlink
RUN jlink \
    --add-modules $(tr ',' ',' < /tmp/deps.info) \
    --strip-debug \
    --compress=2 \
    --no-header-files \
    --no-man-pages \
    --output /custom-jre

### STAGE 2: Runtime ###
FROM debian:bookworm-slim

LABEL maintainer="migucory"
LABEL stage="runtime"

# Configure environment parameters
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH="${JAVA_HOME}/bin:${PATH}"
ENV APP_HOME=/app
ENV JAR_FILE=app-prod-ca.jar
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=70"

# Security: Create a user without privileges
RUN addgroup --system appgroup && \
    adduser --system --ingroup appgroup appuser && \
    mkdir -p $APP_HOME && \
    chown -R appuser:appgroup $APP_HOME

# Copy the JRE and app from the build image
COPY --from=build /custom-jre $JAVA_HOME
COPY --from=build /usr/app/applications/app-service/build/libs/*.jar $APP_HOME/$JAR_FILE

USER appuser
WORKDIR $APP_HOME

EXPOSE 9000

# Use CMD to enable runtime override
CMD ["sh", "-c", "java $JAVA_OPTS -jar $JAR_FILE"]
