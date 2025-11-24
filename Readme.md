sudo docker build -t monapp-springboot .

docker network ls
# url
- http://localhost:8080/actuator/health
- http://localhost:8080/actuator/info
# swagger
- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/v3/api-docs

https://api-ecommune.samory.army/v3/api-docs

https://api-ecommune.samory.army/swagger-ui/index.html

https://api-ecommune.samory.army/actuator/health

# Mot de passe application gmail
https://myaccount.google.com/apppasswords?rapt=AEjHL4PZ3fA045oLdSD2BjPYT3f-FH-zaj6lbE5nhdBkRz9X_KbDhIcqSPr8xL67NGNZs6TNGZvPpItwtC2osdcEFL7rHg5XWaAbDKDvrgZknsq1siuPVF4

# Rate limit
curl -X POST http://localhost:8080/api/v1/auth/login \
-H "Content-Type: application/json" \
-d '{"email": "test@example.com", "password": "test"}'

# Immédiatement après :
curl -X POST http://localhost:8080/api/v1/auth/login \
-H "Content-Type: application/json" \
-d '{"email": "test@example.com", "password": "test"}'


# Creer une api key openai
https://platform.openai.com/api-keys