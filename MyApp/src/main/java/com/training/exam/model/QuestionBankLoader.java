package com.training.exam.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class QuestionBankLoader {
	public static QuestionBank qb = new QuestionBank();

    public void loadQuestionsOn() {
    	Scanner s = new Scanner(System.in);
        //jdbc code will be here
        //from here we might fire a select query, for ex:
        //select * from questionbank where subjectname = ?

        //for the time being, we will hardcode few questions and return it
        qb.addNewSubject("Java");
        qb.addNewSubject("Python");

        Question q = new Question("What is a correct syntax to output \"Hello World\" in Java?");
        List<Option> ops = new ArrayList<>();
        ops.add(new Option("Console.WriteLine(..)", false));
        ops.add(new Option("System.out.println(..)", true));
        ops.add(new Option("echo(..)", false));
        ops.add(new Option("print(..)", false));
        q.setOptions(ops);
        qb.addNewQuestion("Java", q);

        q = new Question("What is G1 in Java?");
        ops = new ArrayList<Option>();
        ops.add(new Option("G1 is nickname of Jeevan", false));
        ops.add(new Option("G1 is Sequel of SRK's Ra.One", false));
        ops.add(new Option("G1 is a type of Garbage Collector", true));
        ops.add(new Option("G1 is the name of next bond movie", false));
        q.setOptions(ops);
        qb.addNewQuestion("Java", q);

        q = new Question("What is JVM in Java?");
        ops = new ArrayList<Option>();
        ops.add(new Option("Java Viral Machine", true));
        ops.add(new Option("Java Visual Machine", false));
        ops.add(new Option("Java Vending Machine", false));
        ops.add(new Option("Java Virtual Machine", true));
        q.setOptions(ops);
        q.setMultipleAnswer();
        qb.addNewQuestion("Java", q);

        q = new Question("What happens when a Java code is compiled?");
        ops = new ArrayList<Option>();
        ops.add(new Option("Bytecode is produced", true));
        ops.add(new Option("Nativecode is produced", false));
        ops.add(new Option("Assembly code is produced", false));
        ops.add(new Option("None of the above", false));
        q.setOptions(ops);
        qb.addNewQuestion("Java", q);

        q = new Question("What is an Object?");
        ops = new ArrayList<Option>();
        ops.add(new Option("Object is an instance of a class", true));
        ops.add(new Option("Object is a primitive datatype", false));
        ops.add(new Option("Object is the one who runs our code", false));
        ops.add(new Option("None of the above", false));
        q.setOptions(ops);
        qb.addNewQuestion("Python", q);
        
//        List<String> subj = qb.getSubject();
//        System.out.println("Select the number corresponding to the Subject");
//        for(int i=0;i<subj.size();i++) {
//        	System.out.print(i+1 + " " + subj.get(i) + "\t");
//        }
//        int idx = Integer.parseInt(s.nextLine());
//        return qb.getQuestionsFor("Java");

    }
}
