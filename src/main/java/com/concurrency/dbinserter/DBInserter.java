package com.concurrency.dbinserter;

import com.concurrency.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DBInserter {
    static Connection connection = ConClass.getInstance().getConnection();

    public static void main(String[] args) {
        System.out.print("           How many rows to insert into database: ");
        int dataCount = Utilities.intReceiver();
        System.out.print("           With how many threads: ");
        Integer threadCount = Utilities.intReceiver();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadCount);
        long then = System.currentTimeMillis();
        for(int i = 0; i < dataCount; i++){
            executor.submit(() -> insert(randString()));
        }
        executor.shutdown();
        long now;
        while (true){
            if(executor.isTerminated()){
                now = System.currentTimeMillis();
                break;
            }
        }
        System.out.println(now - then);
    }


    private static void insert(String tag){
        String insStmt = "INSERT INTO mock_table (tag) VALUES (?);";
        try {
            PreparedStatement ps = connection.prepareStatement(insStmt);
            ps.setString(1,tag);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String randString(){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
