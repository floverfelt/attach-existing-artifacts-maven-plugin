# attach-existing-artifacts-maven-plugin
POC/early draft of a plugin which attaches already built artifacts to the maven build.


The primary purpose of this plugin is to allow independent execution of the [maven-deploy-plugin](https://maven.apache.org/plugins/maven-deploy-plugin/)'s [deploy:deploy](https://maven.apache.org/plugins/maven-deploy-plugin/deploy-mojo.html) goal without the prior [build stages in Maven](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html).


The deploy:deploy goal requires artifacts to already be attached to the build. If those artifacts are not attached the following error is raised:
`
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-deploy-plugin:2.8:deploy (default-cli) on project: The packaging for this project did not assign a file to the build artifact -> [Help 1]
`

This plugin quickly attaches the already generated artifacts so that the deploy:deploy goal can take place without needing to call all lifecycle phases leading up to deploy.

This is useful in situations where a prior process (Jenkins, Bamboo, etc.) has already built the artifacts for the repo and you need to simply deploy them. The plugin minimizes build times by not necessitating a full rebuild in order to deploy.

# Sample execution

`mvn com.attach.existing.artifacts.plugin:attach-existing-artifacts-maven-plugin:attachExisting`

With deployment:

`mvn com.attach.existing.artifacts.plugin:attach-existing-artifacts-maven-plugin:attachExisting deploy:deploy`
