package com.concurrency;

import java.io.File;
import java.nio.file.Files;
import java.util.Objects;
import java.util.concurrent.*;

public class SizeCalculator {
    /**
     * File to calculate its size
     */
    File file;
    /**
     * Input Number of threads
     */
    Integer threadCount;

    public SizeCalculator(String path,Integer threadCount){
        this.file = new File(path);
        this.threadCount = threadCount;
    }

    public long calcFilesSize() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        final long[] finalSize = {0L};
        Future<Long> execution = executor.submit(() -> finalSize[0] += calcFileSize(file));
        return execution.get();
    }


    private Long calcFileSize(File file) {
        Long finalSize = 0L;
        if(!file.isFile()) {
            for (File subFile : Objects.requireNonNull(file.listFiles())) {
                if (subFile.isFile()) {
                    finalSize += (subFile.length());
                } else {
                    finalSize += calcDirSize(subFile);
                }
            }
        } else {
            finalSize += (file.length());
        }
        return finalSize;
    }

    private Long calcDirSize(File file){
        return calcFileSize(file);
    }
}
