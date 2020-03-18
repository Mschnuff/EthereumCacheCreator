package de.internetsicherheit.brl.bloxberg.cache;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import static java.util.stream.IntStream.range;

public class HistoricDataExtractor {

    private static BloxbergClient client;
    private static EthereumWriter writer;
    private static final String ETHEREUM_NETWORK = "https://core.bloxberg.org";
    private static final String OUTPUTDIRECTORY = System.getProperty("user.dir") + "/output/";
    private static final int RANGE = 10000;
    private static int failedBlocks;


//    public static void nichtmain(String[] args) throws IOException {
//
//        client = new BloxbergClient(ETHEREUM_NETWORK);
//        writer = new EthereumWriter(Path.of(OUTPUTDIRECTORY), "ExtractedData2.txt");
//        failedBlocks = 0;
//        extractData(RANGE);
//        System.out.println("Anzahl gescheiterter Blöcke: " + failedBlocks);
//
//    }

    /**
     *  Extract Data from the Client
     * @param blockRange
     * @throws IOException
     */
    public static void extractData(int blockRange) throws IOException {

        range(client.getCurrentBlockNumber().intValue() - blockRange, client.getCurrentBlockNumber().intValue())
                .mapToObj(i -> new BlockWithTransactionCombination(BigInteger.valueOf(i), getNumberOfTransactionsInBlockWithRetry(i)))
                .forEach(HistoricDataExtractor::writeBlock);
    }

    private static int getNumberOfTransactionsInBlockWithRetry(int i) {
        int retryAtempts = 0;
        boolean success = false;
        while (retryAtempts < 10 && !success) {
            success = true;
            try {
                return client.getNumberOfTransactionsInBlock(BigInteger.valueOf(i));

            } catch (IOException e) {
                success = false;
                retryAtempts++;
                try {
                    TimeUnit.SECONDS.sleep(60);
                } catch (InterruptedException ex) {
                    // no handle atm.
                }
            }

        }
        failedBlocks++;
        return 0;
    }

    private static void writeBlock(BlockWithTransactionCombination b) {
        try {
            writer.writeBlockWithTransactions(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
