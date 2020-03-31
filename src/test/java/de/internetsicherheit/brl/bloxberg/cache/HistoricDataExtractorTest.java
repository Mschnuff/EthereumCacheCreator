package de.internetsicherheit.brl.bloxberg.cache;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class HistoricDataExtractorTest {
    private HistoricDataExtractor extractor;

    /**
     * tests the retry mechanism of the historicDataExtractor using a stub. however the test doesnt make much sense to me
     */
    @DisplayName("testing retry - not really.")
    @Test
    void canExtractBlockWithRetry() {

        extractor = mock(HistoricDataExtractor.class);
        int blockNumber = 1000;

        when(extractor.getNumberOfTransactionsInBlockWithRetry(blockNumber)).thenReturn(0);

        assertThat(extractor.getNumberOfTransactionsInBlockWithRetry(blockNumber)).isEqualTo(0);

    }
}