package org.day3;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) {
        MongoDatabase db = mon_con.getDatabase();

        MongoCollection<Document> students = db.getCollection("students");
        MongoCollection<Document> courses = db.getCollection("courses");
        MongoCollection<Document> enrollments = db.getCollection("enrollments");

        students.drop(); courses.drop(); enrollments.drop();

        Document student1 = Students.create("Alice", 20);
        Document student2 = Students.create("Bob", 21);
        students.insertOne(student1);
        students.insertOne(student2);

        Document course1 = Course.create("BSMathematics", 4);
        Document course2 = Course.create("BSPhysics", 3);
        courses.insertOne(course1);
        courses.insertOne(course2);

        Document embeddedEnrollment = Enrollment.createEmbedded(student1, course1);
        enrollments.insertOne(embeddedEnrollment);

        Document studentDoc = students.find(eq("name", "Bob")).first();
        Document courseDoc = courses.find(eq("title", "BSPhysics")).first();

        Document referencedEnrollment = Enrollment.createReferenced(
                studentDoc.getObjectId("_id"), courseDoc.getObjectId("_id")
        );
        enrollments.insertOne(referencedEnrollment);

        System.out.println(" Embedded Enrollment ");
        enrollments.find(eq("type", "embedded")).forEach(System.out::println);

        System.out.println(" Referenced Enrollment ");
        enrollments.find(eq("type", "referenced")).forEach(doc -> {
            ObjectId sid = doc.getObjectId("studentId");
            ObjectId cid = doc.getObjectId("courseId");
            Document stud = students.find(eq("_id", sid)).first();
            Document cour = courses.find(eq("_id", cid)).first();
            System.out.println("Student: " + stud + "\nCourse: " + cour + "\n");
        });

        students.updateOne(eq("name", "Alice"), new Document("$set", new Document("name", "Alicia")));
        System.out.println("Updated student document");

        students.createIndex(new Document("name", 1));
    }
}

