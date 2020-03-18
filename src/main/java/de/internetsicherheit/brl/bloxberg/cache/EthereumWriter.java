package de.internetsicherheit.brl.bloxberg.cache;

import java.io.IOException;
import java.nio.file.Path;

public class EthereumWriter {

    private Path outputFilePath;

    public EthereumWriter(Path dir, String filename) {
        outputFilePath = dir.resolve(filename);
    }
//disabled du to compiler issues with Files.writeString DO NOT USE
    public void writeBlockWithTransactions(BlockWithTransactionCombination bwtc) throws IOException {
//        Files.writeString(outputFilePath,  bwtc.blockNumber + ","
//                + bwtc.transactionCount + "\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);

    }
}
