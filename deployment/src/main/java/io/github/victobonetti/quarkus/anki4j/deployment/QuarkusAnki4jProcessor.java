package io.github.victobonetti.quarkus.anki4j.deployment;

import io.github.victobonetti.quarkus.anki4j.runtime.Anki4jConfig;
import io.github.victobonetti.quarkus.anki4j.runtime.Anki4jRecorder;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.deployment.builditem.nativeimage.RuntimeInitializedClassBuildItem;

class QuarkusAnki4jProcessor {

    private static final String FEATURE = "quarkus-anki4j";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    void setupConfig(Anki4jRecorder recorder, Anki4jConfig config) {
        recorder.initSystemProperties(config);
    }

    @BuildStep
    void registerPackageForReflection(CombinedIndexBuildItem index,
                                      BuildProducer<ReflectiveClassBuildItem> reflection) {

        index.getIndex().getClassesInPackage("com.anki4j.model")
                .forEach(classInfo -> {
                    reflection.produce(ReflectiveClassBuildItem.builder(classInfo.name().toString())
                            .methods(true)
                            .fields(true)
                            .build());
                });

        reflection.produce(ReflectiveClassBuildItem.builder("com.anki4j.Anki4j")
                .methods(true)
                .fields(true)
                .build());
    }

    @BuildStep
    RuntimeInitializedClassBuildItem runtimeSqlite() {
        return new RuntimeInitializedClassBuildItem("org.sqlite.JDBC");
    }

}
