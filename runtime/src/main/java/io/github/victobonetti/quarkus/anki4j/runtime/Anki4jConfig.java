package io.github.victobonetti.quarkus.anki4j.runtime;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithDefault;
import io.smallrye.config.WithName;
import java.util.Optional;

/**
 * Configurações de runtime para a extensão Anki4J.
 * <p>
 * Estas propriedades permitem ajustar o comportamento da biblioteca interna
 * de processamento de pacotes Anki dentro do ecossistema Quarkus.
 */
@ConfigMapping(prefix = "quarkus.anki4j")
public interface Anki4jConfig {

    /**
     * Define o tamanho máximo permitido para o pacote (em KB).
     * <p>
     * Este valor é repassado para a variável de sistema {@code ANKI4J_MAX_PKG_SIZE_KB}
     * utilizada pela biblioteca interna. Se não for definido no {@code application.properties},
     * o valor padrão de 10MB (10240 KB) será aplicado.
     * * @return O tamanho máximo do pacote em Kilobytes.
     */
    @WithName("max-pkg-size-kb")
    @WithDefault("10240")
    Optional<Long> maxPkgSizeKb();
}