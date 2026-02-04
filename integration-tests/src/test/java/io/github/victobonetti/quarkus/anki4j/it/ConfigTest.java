package io.github.victobonetti.quarkus.anki4j.it;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

@QuarkusTest
@TestProfile(ConfigTest.TestProfile.class)
public class ConfigTest {

    public static class TestProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of("quarkus.anki4j.max-pkg-size-kb", "100000000000000000");
        }
    }

    @Test
    public void testSystemPropertyIsSet() {
        String property = System.getProperty("ANKI4J_MAX_PKG_SIZE_KB");
        Assertions.assertNotNull(property, "System property ANKI4J_MAX_PKG_SIZE_KB should be set");
        Assertions.assertEquals("100000000000000000", property, "System property ANKI4J_MAX_PKG_SIZE_KB should match the configuration");
    }
}
