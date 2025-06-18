package org.day3;
import com.mongodb.client.*;
import org.bson.Document;
public class Students {
public static Document create(String name,int age) {
    return new Document("name", name).append("age", age);
}

}
