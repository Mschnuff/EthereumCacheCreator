package de.internetsicherheit.brl.bloxberg.cache;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class CacheFileReader {
    private final Path inputFilePath;

    /**
     *
     * @param inputFilePath
     */
    public CacheFileReader(Path inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    /**
     * Liest vollständige Datei ein
     * @return
     * @throws IOException
     */
    public Stream<BlockWithTransactionCombination> readAllLines() throws IOException {
        return Files.lines(inputFilePath)
                .map(this::parseLine);
    }

    /**
     * Liest alle zeilen von start inklusive bis ende exklusive
     * Bsp: start = 0; end = 100 => Block in [0,100)
     * @param start
     * @param end
     * @return
     * @throws IOException
     */
    public Stream<BlockWithTransactionCombination> readLines(int start, int end) throws IOException {
        return Files.lines(inputFilePath)
                .skip(start)
                .map(this::parseLine)
                .limit(end);
    }

    /**
     *
     * @param start
     * @param end number of lines read
     * @return
     * @throws IOException
     */
    public BlockWithTransactionCombination[] readLinesAndMakeArray(int start, int end) throws IOException {
         return Files.lines(inputFilePath)
                .skip(start)
                .map(this::parseLine)
                .limit(end).toArray(BlockWithTransactionCombination[]::new);

    }

    /**
     * repräsentiert die Kombination aus Blocknummer und Anzahl der Transaktionen im jeweiligen Block
     * @param line
     * @return
     */
    public BlockWithTransactionCombination parseLine(String line) {
        String[] lineparts = line.split(",");
        long blockNumber = Long.parseLong(lineparts[0]);
        int numberOfTransactions = Integer.parseInt(lineparts[1]);
        return new BlockWithTransactionCombination(BigInteger.valueOf(blockNumber), numberOfTransactions);
    }

}
