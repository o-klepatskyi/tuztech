FROM --platform=linux/amd64 gradle:8.5.0-jdk17-alpine
COPY --chown=gradle:gradle . /app
WORKDIR /app
ENTRYPOINT ["gradle", "bootRun"]