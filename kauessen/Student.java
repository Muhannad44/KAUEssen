/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kauessen;

/**
 *
 * @author Mohd
 */
public class Student {
    private String name;
    private String universityId;

    public Student(String name, String universityId) {
        this.name = name;
        this.universityId = universityId;
    }

    public String getName() { return name; }
    public String getUniversityId() { return universityId; }
}
    
