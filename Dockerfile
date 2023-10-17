FROM openjdk:11
WORKDIR /workspaces/azuredevops-telegram-bot/workspace
COPY . /workspaces/azuredevops-telegram-bot/workspace
CMD [ "java", "-jar", "target/AdoBotProject-0.0.1-jar-with-dependencies.jar" ]