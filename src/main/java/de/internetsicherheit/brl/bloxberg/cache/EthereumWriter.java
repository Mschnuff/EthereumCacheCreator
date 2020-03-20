package de.internetsicherheit.brl.bloxberg.cache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class EthereumWriter {

    private Path outputFilePath;

    public EthereumWriter(Path dir, String filename) {
        outputFilePath = dir.resolve(filename);
    }

    public void writeBlockWithTransactions(BlockWithTransactionCombination bwtc) throws IOException {
        Files.writeString(outputFilePath,  bwtc.blockNumber + ","
                + bwtc.transactionCount + "\n", StandardOpenOption.APPEND, StandardOpenOption.CREATE);

    }
}
