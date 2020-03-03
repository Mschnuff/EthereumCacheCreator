package de.internetsicherheit.brl.bloxberg.cache;

import java.math.BigInteger;

public class BlockWithTransactionCombination {
    public BigInteger blockNumber;
    public int transactionCount;

    public BlockWithTransactionCombination(BigInteger blockNumber, int transactionCount) {
        this.blockNumber = blockNumber;
        this.transactionCount = transactionCount;
    }

}
