package de.internetsicherheit.brl.bloxberg.cache;

import org.rnorth.ducttape.unreliables.Unreliables;

import java.io.IOException;
import java.math.BigInteger;

import static java.util.stream.IntStream.range;

public class HistoricDataExtractor {

    private BloxbergClient client;
    private EthereumWriter writer;
    private final String ETHEREUM_NETWORK = "https://core.bloxberg.org";
    private final String OUTPUTDIRECTORY = System.getProperty("user.dir") + "/output/";
    private final int range;
    private int failedBlocks;


    /*public void main(String[] args) throws IOException {

        client = new BloxbergClient(ETHEREUM_NETWORK);
        writer = new EthereumWriter(Path.of(OUTPUTDIRECTORY), "ExtractedData3.txt");
        failedBlocks = 0;
        extractData(range);
        System.out.println("Anzahl gescheiterter Blöcke: " + failedBlocks);

    }*/

    public HistoricDataExtractor(BloxbergClient client, EthereumWriter writer, int range) {

        this.client = client;
        this.writer = writer;
        this.range = range;
        this.failedBlocks = 0;

    }

    /**
     *  Extract Data from the Client ranging from the most recent block to a past block specified by a range
     * @param blockRange how many blocks back should data be extracted from the blockchain
     * @throws IOException
     */
    public void extractData(int blockRange) throws IOException {

        range(client.getCurrentBlockNumber().intValue() - blockRange, client.getCurrentBlockNumber().intValue())
                .mapToObj(i -> new BlockWithTransactionCombination(BigInteger.valueOf(i), getNumberOfTransactionsInBlockWithRetry(i)))
                .forEach(this::writeBlock);
    }

    /**
     * Extract all the Number of Transactions per block for the entire history of the blockchain. this might take a while.
     * @throws IOException
     */
    public void extractAllData() throws IOException {
        range(0, client.getCurrentBlockNumber().intValue())
                .mapToObj(i -> new BlockWithTransactionCombination(BigInteger.valueOf(i), getNumberOfTransactionsInBlockWithRetry(i)))
                .forEach(this::writeBlock);
    }

    /**
     *
     * @param number of the block we are looking at
     * @return i number of transactions in a single block
     */
    public int getNumberOfTransactionsInBlockWithRetry(int i) {
        int transactions =
        Unreliables.retryUntilSuccess(10, () -> {
            try {
                return client.getNumberOfTransactionsInBlock(BigInteger.valueOf(i));
            } catch (IOException e) {
                return 0;
            }
        });

        return transactions;
    }


    private void writeBlock(BlockWithTransactionCombination b) {
        try {
            writer.writeBlockWithTransactions(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
