# attach-existing-artifacts-maven-plugin
POC/early draft of a plugin which attaches already built artifacts to the maven build


The purpose of this plugin is to allow independent execution of deploy:deploy without the prior build stages in Maven.


The deploy:deploy call requires artifacts to already be attached to the build. This plugin quickly attaches the already generated artifacts so that the deploy:deploy
plugin can take place without needing to call all lifecycle phases leading up to deploy.

This is useful in situations where a prior process (Jenkins, Bamboo, etc.) has already built the artifacts for the repo and you need to simply deploy them. The plugin
minimizes build times not necessitating a rebuild.

# Sample execution

`mvn com.attach.existing.artifacts.plugin:attach-existing-artifacts-maven-plugin:attachExisting`

With deployment:

`mvn com.attach.existing.artifacts.plugin:attach-existing-artifacts-maven-plugin:attachExisting deploy:deploy`
