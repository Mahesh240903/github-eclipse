//package com.training.exam.model;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Scanner;
//import java.util.Timer;
//import java.util.TimerTask;
//
//public class App {
//
//	public static void main(String[] args) throws InterruptedException {
//		Scanner s = new Scanner(System.in);
//		QuestionBankLoader qbLoader = new QuestionBankLoader();
//		List<Question> questions = qbLoader.loadQuestionsOn();
//		
//		System.out.println("Welcome to Pariskha app v1.0");
//		System.out.println("Press any key to start the exam..");
//		s.nextLine();
//		
//		int score = 0;
//		for(int i=0; i<questions.size(); i++) {
//			Question q = questions.get(i);
//			System.out.println("\nQuestion No. " + (i+1) + ")\n" + q.getQuestion());
////			int rightAns = 0,k=0,flag=0;
//			List<Option> options = q.getOptions();
//			for(int j=0; j<options.size(); j++) {
//				Option opt = options.get(j);
////				if(opt.isRightAnswer())
////					rightAns++;
//				System.out.println((j+1) + ". " + opt.getOption());
//			}
//			
//			Timer timer = new Timer();
//			boolean[] timeUp = {false};
//			final int ansChosen;
//			boolean[] switchQuestion = {false};
//			
//			timer.schedule(new TimerTask() {
//				int timeRemaining =20;
//				@Override
//				public void run() {
//					if(timeRemaining == 0) {
//						System.out.println("Time is Up!!");
//						timeUp[0] = true;
//						timer.cancel();
//						switchQuestion[0] = true;
//					}
//					else {
//						System.out.println("Time remaining: " + timeRemaining + " seconds");
//						timeRemaining--;
//					}
//					
//				}
//			}, 0, 1000);
//			
//			if(switchQuestion[0]) {
//				
//			}
////			new Thread(()->{
////				while(!timeUp[0]) {
////					if (s.hasNextLine()) {
////	                    String userInput = s.nextLine();
//////	                    ansChosen = userInput.split(",");
////	                    ansChosen = Integer.parseInt(userInput);
////	                    timeUp[0] = true;  // Stop the timer once an answer is submitted
////	                    System.out.println("Your answer: " + userInput);
////	                }
////				}
////			}).start();
////			
////			 while (!timeUp[0]) {
////		            Thread.sleep(500);  // Sleep to allow the timer and input to run in parallel
////		        }
//			
////			Boolean allTrue = true;
////			List<Option> rightOptions = q.getRightAnswer();
////			for(String ans : ansChosen) {
////				Option selectedOption = options.get(Integer.parseInt(ans) - 1);				
//////				System.out.println(ans);
////				if(!selectedOption.isRightAnswer()) {
//////					k=0;	
////					allTrue = false;
////					break;
////				}
//////				k++;
////			}
//////			if(k==rightAns) {
//////				score++;
//////			}
////			if(allTrue) {
////				score++;
////			}
//			 
//		}
//		
//		System.out.println("\nCongratulations, you have scored " + score + " marks");
//	}
//}
