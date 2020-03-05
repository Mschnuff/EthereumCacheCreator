package de.internetsicherheit.brl.bloxberg.cache;


import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;

public class DataBlockSummerizer {

    // Nummer des ersten Blocks
    private int start;

    // Gesamtanzahl der Blöcke => ende = start + range - 1
    private int range;

    private BlockGroup[] blockGroups;

    private CacheFileReader cfr;

    private Path workDir;

    public DataBlockSummerizer(Path workDir) {
        this.workDir = workDir;
        this.cfr = new CacheFileReader(workDir);

    }

    /**
     * Mehtod that generates an Array of Type Blockgroup, that can be easily used for visualization
     * @param start the blocknumber of the first block to be looked at
     * @param end the blocknumber of the last block to be looked at
     * @param groupSize the Size of groups of blocks.
     * @return
     * @throws IOException
     */
        public BlockGroup[] summerizeData(int start, int end , int groupSize) throws IOException {
            int startNextGroup = start;
            blockGroups = new BlockGroup[end/groupSize +1];
            int currentIndex = 0;

            while(startNextGroup < (start+end)){
                BlockWithTransactionCombination[] blocks = this.cfr.readLinesAndMakeArray(startNextGroup, startNextGroup+groupSize-1);
                int sum = 0;

                for (int i = 0; i < blocks.length; i++){
                    sum = sum + blocks[i].transactionCount;
                }
                this.blockGroups[currentIndex] = new BlockGroup(BigInteger.valueOf(startNextGroup), sum);
                System.out.println("New Blockgroup \n" + "Blocknumber of starting block: " + BigInteger.valueOf(startNextGroup)
                        + "\n" + "Sum of all Transactions for this Blockgroup: " + sum);
                currentIndex++;
                startNextGroup = startNextGroup + groupSize;

        }

       return blockGroups;

    }


}
