package io.github.victobonetti.quarkus.anki4j.deployment;

import io.github.victobonetti.quarkus.anki4j.runtime.Anki4jConfig;
import io.github.victobonetti.quarkus.anki4j.runtime.Anki4jRecorder;
import io.quarkus.deployment.annotations.BuildProducer;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.ExecutionTime;
import io.quarkus.deployment.builditem.CombinedIndexBuildItem;
import io.quarkus.deployment.builditem.ConfigMappingBuildItem;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import io.quarkus.deployment.builditem.nativeimage.RuntimeInitializedClassBuildItem;
import io.quarkus.deployment.annotations.Record;
import jakarta.inject.Inject;

class QuarkusAnki4jProcessor {

    private static final String FEATURE = "quarkus-anki4j";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
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

    @BuildStep
    ConfigMappingBuildItem registerConfig() {
        // Isso registra a interface no ecossistema SmallRye Config do Quarkus
        return new ConfigMappingBuildItem(Anki4jConfig.class, "quarkus.anki4j");
    }

    @BuildStep
    @Record(ExecutionTime.RUNTIME_INIT)
    void setupConfig(Anki4jRecorder recorder) {
        recorder.initSystemProperties();
    }

}
