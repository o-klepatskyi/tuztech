# Tuzello
Barbershop app for Software Project Structures course
## How to build
Install gradle, docker.
```
./gradlew build
docker compose create
docker compose start
```
You can access the app on `http://localhost:8080`
## Ngrok setup
Ngrok is used to expose the app to the internet. You need to create `.env` file with your auth token:
```
NGROK_AUTH=<your ngrok token>
```
You can copy auth token from [here](https://dashboard.ngrok.com/get-started/your-authtoken). <br>
To start with ngrok run: `docker compose --profile use_ngrok stop` <br>
You can view your ngrok address on `http://localhost:4040` <br>
## Api docs
Api documentation is accessible on `http://localhost:8080/swagger-ui.html` or `<your ngrok address>/swagger-ui.html`
