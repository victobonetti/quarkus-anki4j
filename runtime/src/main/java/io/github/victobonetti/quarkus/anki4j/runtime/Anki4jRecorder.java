package io.github.victobonetti.quarkus.anki4j.runtime;

import io.quarkus.runtime.annotations.Recorder;

@Recorder
public class Anki4jRecorder {
    public void initSystemProperties(Anki4jConfig config) {
        config.maxPkgSizeKb().ifPresent(value -> {
            // "Engana" a JAR externa setando a propriedade que ela espera
            System.setProperty("ANKI4J_MAX_PKG_SIZE_KB", String.valueOf(value));
        });
    }
}
