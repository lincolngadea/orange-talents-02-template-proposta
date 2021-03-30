FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV POSTGRES_HOST: postgres
ENV DB_DATABASE: keycloak
ENV DB_USER: keycloak
ENV DB_PASSWORD: password
ENV URI_CARTAO: http://contas:8888/
ENV URI_ANALISE: http://analise:9999/
ENV KEYCLOAK_ISSUER_URI: http://keycloak:18080/auth/realms/cartao-proposta
ENV KEYCLOAK_JWKS_URI: http://keycloak:18080/auth/realms/cartao-proposta/protocol/openid-connect/certs
ENTRYPOINT java -jar /app.jar