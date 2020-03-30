package de.internetsicherheit.brl.bloxberg.cache;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class HistoricDataExtractorTest {
    private HistoricDataExtractor extractor;

    @DisplayName("testing retry - not really.")
    @Test
    void canExtractBlockWithRetry() throws IOException {

        extractor = mock(HistoricDataExtractor.class);
        int blockNumber = 1000;

        when(extractor.getNumberOfTransactionsInBlockWithRetry(blockNumber)).thenReturn(0);

        assertThat(extractor.getNumberOfTransactionsInBlockWithRetry(blockNumber)).isEqualTo(5);

    }
}