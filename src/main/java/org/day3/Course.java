package org.day3;
import org.bson.Document;
public class Course {
    public static Document create(String title, int credits) {
        return new Document("title", title).append("credits",credits);
    }
}
