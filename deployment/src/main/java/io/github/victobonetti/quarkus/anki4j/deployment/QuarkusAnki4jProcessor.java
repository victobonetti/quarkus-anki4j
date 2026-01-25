package io.github.victobonetti.quarkus.anki4j.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;

class QuarkusAnki4jProcessor {

    private static final String FEATURE = "quarkus-anki4j";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }
}
