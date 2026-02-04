package io.github.victobonetti.quarkus.anki4j.runtime;

import io.quarkus.runtime.annotations.Recorder;
import io.smallrye.config.SmallRyeConfig;
import org.eclipse.microprofile.config.ConfigProvider;

@Recorder
public class Anki4jRecorder {
    public void initSystemProperties() {
        Anki4jConfig config = ConfigProvider.getConfig().unwrap(SmallRyeConfig.class)
                .getConfigMapping(Anki4jConfig.class);

        config.maxPkgSizeKb().ifPresent(value -> {
            System.setProperty("ANKI4J_MAX_PKG_SIZE_KB", String.valueOf(value));
        });
    }
}
