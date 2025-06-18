package org.day3;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
public class mon_con {
    //student database creation
    private static final String URL = "mongodb://localhost:27017";
    private static final String DB = "uni";

    public static MongoDatabase getDatabase() {
        MongoClient client = MongoClients.create(URL);
        return client.getDatabase(DB);
    }
}
