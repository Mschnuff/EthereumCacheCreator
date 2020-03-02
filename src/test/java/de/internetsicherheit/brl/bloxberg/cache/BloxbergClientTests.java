package de.internetsicherheit.brl.bloxberg.cache;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigInteger;

import static org.assertj.core.api.Assertions.assertThat;

public class BloxbergClientTests {

    @DisplayName("Fetching block number from bloxberg gives a value bigger than 5000000")
    @Test
    void blockNumber() throws IOException {
        var client = buildClient();

        BigInteger blockNumber = client.getCurrentBlockNumber();

        assertThat(blockNumber).isGreaterThanOrEqualTo(BigInteger.valueOf(5000000L));
    }
    @DisplayName("Fetching number of transactions for blocknumber 200 returns 0")
    @Test
    void transactionsInBlock() throws IOException {
        var client = buildClient();

        BigInteger blockNumber = BigInteger.valueOf(200);

        int blockWithTransactionCount = client.getNumberOfTransactionsInBlock(blockNumber);

        assertThat(blockWithTransactionCount).isEqualTo(0);
    }


    private BloxbergClient buildClient() {
        return new BloxbergClient("https://core.bloxberg.org");
    }
}
