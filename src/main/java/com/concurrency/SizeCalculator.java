package com.concurrency;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.*;

public class SizeCalculator {

    public static Long calcFilesSize(File file,Integer threadCount) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        final long[] finalSize = {0L};
        Future<Long> execution = executor.submit(() -> finalSize[0] += calcFileSize(file));
        return execution.get();
    }


    private static Long calcFileSize(File file) {
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

    private static Long calcDirSize(File file){
        return calcFileSize(file);
    }
}
