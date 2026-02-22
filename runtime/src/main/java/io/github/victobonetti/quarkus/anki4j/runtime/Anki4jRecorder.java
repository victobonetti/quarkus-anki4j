package io.github.victobonetti.quarkus.anki4j.runtime;

import io.quarkus.runtime.annotations.Recorder;
import org.eclipse.microprofile.config.ConfigProvider;

import java.util.Optional;

@Recorder
public class Anki4jRecorder {
    public void initSystemProperties() {
        Optional<Long> maxPkgSize = ConfigProvider.getConfig()
                .getOptionalValue("quarkus.anki4j.max-pkg-size-kb", Long.class);

        maxPkgSize.ifPresent(value -> {
            System.setProperty("ANKI4J_MAX_PKG_SIZE_KB", String.valueOf(value));
        });
    }
}
