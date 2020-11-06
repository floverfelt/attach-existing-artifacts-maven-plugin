package com.attach.existing.artifacts.plugin;

import org.apache.maven.model.Build;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.io.File;

@Mojo(name="attachExisting")
public class AttachExistingArtifactMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException {

        // Fetch params & log
        Build build = project.getBuild();
        String finalName = build.getFinalName();
        String buildDirectory = build.getDirectory();
        String packaging = project.getPackaging();
        getLog().info("Build final name: " + finalName);
        getLog().info("Build output directory: " + buildDirectory);
        getLog().info("Project packaging: " + packaging);

        if ("maven-plugin".equals(packaging)) {
            getLog().info("maven-plugin packaging detected. Using jar file suffix.");
            packaging = "jar";
        }

        File expectedFile;

        if ("pom".equals(packaging)) {
            getLog().info("pom packaging detected. Attaching pom only.");
            expectedFile = project.getFile();
        } else {
            // Pom is attached as well here, no need to attach it
            expectedFile = new File(String.format("%s/%s.%s", buildDirectory, finalName, packaging));
        }

        if (!expectedFile.exists()) {
            throw new MojoExecutionException("Error attaching artifact. Artifact does not exist.");
        }

        project.getArtifact().setFile(expectedFile);

    }

}
