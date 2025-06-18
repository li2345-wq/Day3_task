package org.day3;
import com.mongodb.*;
import org.bson.*;
import org.bson.types.ObjectId;

public class Enrollment {
    public static Document createEmbedded(Document student,Document course) {
        return new Document("type", "embedded").append("student", student).append("course",course);
    }
    public static Document createReferenced(ObjectId student_id , ObjectId course_id) {
        return new Document("type","referenced").append("Student_id",student_id).append("courseId", course_id);
    }
}
