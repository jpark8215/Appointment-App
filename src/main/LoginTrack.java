//package main;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.time.Instant;
//
//public class loginTrack {
//        private static final String FILENAME = "LoginTrack.txt";
//
//        public loginTrack() {}
//
//        public static void log (String username, boolean success, String message) {
//            try (FileWriter fw = new FileWriter(FILENAME, true);
//                 BufferedWriter bw = new BufferedWriter(fw);
//                 PrintWriter pw = new PrintWriter(bw)) {
//                pw.println(username + (success ? " Successful" : " Failed") + " " + message + " " + Instant.now().toString());
//            } catch (IOException e) {
//                System.out.println("Logger Error: " + e.getMessage());
//            }
//        }
//    }
