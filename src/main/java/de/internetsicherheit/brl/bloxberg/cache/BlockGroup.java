package de.internetsicherheit.brl.bloxberg.cache;

import java.math.BigInteger;

public class BlockGroup{

    // Start der Gruppe von Blöcken
    private BigInteger start;

    // Summe aller Transactionen der Blöcke in dieser Gruppe
    private int sum;
    private int range;


    public BlockGroup(BigInteger start, int sum, int range){
        this.range = range;
        this.start = start;
        this.sum = sum;
    }
    public BigInteger getStart() {
        return start;
    }
    public int getSum() {
        return sum;
    }
    public int getRange() { return  range; }
};