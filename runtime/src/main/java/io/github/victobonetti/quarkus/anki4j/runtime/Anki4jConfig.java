package io.github.victobonetti.quarkus.anki4j.runtime;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;

import java.util.Optional;

@ConfigMapping(prefix = "quarkus.anki4j")
public interface Anki4jConfig {

    @WithName("max-pkg-size-kb")
    @WithDefault("10240")
    Optional<Long> maxPkgSizeKb();
}