package org.altervista.codinghobby;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        File users = new File("useres.txt");
        File file;
        Scanner reader = new Scanner(System.in);
        boolean logged = false;
        String user="";
        while (true) {
            while (logged == false) {
                System.out.print(">> Login or Create new User \n>> ");
                String logUsr = reader.next();
                switch (logUsr) {
                    case "login":
                        user = reader.next();
                        String pswd = reader.next();
                        logged = login(user, pswd);
                        System.out.println(">> Logged in Succesfully");
                        break;
                    case "create":
                        String newUser = reader.next();
                        String newPswd = reader.next();
                        createUser(newUser, newPswd);
                        System.out.println(">> User Created");
                        break;
                    case "quit":
                        return;
                }

            }
            System.out.print(">> ");
            file=new File(user+".txt");
            String op = reader.next();
            switch (op) {
                case "generate":
                    String site = reader.next();
                    String pass = generate();
                    System.out.println(">> " + "Password for " + site + " is " + pass);
                    add(site, pass, file);
                    break;
                case "dltAll":
                    dltPswd(file);
                    System.out.println(">> All passwords Deleted");
                    break;
                case "find":
                    String search = reader.next();
                    System.out.println(">> " + find(search, file));
                    break;
                case "logout":
                    logged=false;
                break;
                /* case "rmv":
                    String rmv=reader.next();
                    rmv("passwords.txt", rmv);
                    break;*/
                case "quit":
                    return;

            }
        }
    }

    private static void add(String site, String pass, File file) {
        try (FileWriter fw = new FileWriter(file, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println("The Password for " + site + " is " + pass);
            out.close();

        } catch (IOException e) {
            System.out.println(">> Error opening file: \n");
            e.printStackTrace();
        }
    }

    private static void dltPswd(File file) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(file);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println(">> File not Found");
        }

    }

    private static String generate() {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    private static String find(String word, File file) {
        int count = 0;
        try {
            Scanner scanner;
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String nextToken = scanner.nextLine();
                if (nextToken.toLowerCase().contains(word.toLowerCase())) {
                    return nextToken;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println(">> File not Found");
        }
        return ("Unable to find Password");
    }

    /* private static void rmv(String file, String lineToRemove) {
 
    try {
 
      File inFile = new File(file);
      
      if (!inFile.isFile()) {
        System.out.println("Parameter is not an existing file");
        return;
      }
       
      //Construct the new file that will later be renamed to the original filename. 
      File tempFile = new File(inFile.getAbsolutePath() + ".tmp");
      
      BufferedReader br = new BufferedReader(new FileReader(file));
      PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
      
      String line = null;
 
      //Read from the original file and write to the new 
      //unless content matches data to be removed.
      while ((line = br.readLine()) != null) {
        
        if (!line.trim().equals(lineToRemove)) {
 
          pw.println(line);
          pw.flush();
        }
      }
      pw.close();
      br.close();
      
      //Delete the original file
      if (!inFile.delete()) {
        System.out.println("Could not delete file");
        return;
      } 
      
      //Rename the new file to the filename the original file had.
      if (!tempFile.renameTo(inFile))
        System.out.println("Could not rename file");
      
    }
    catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }*/
    private static void createUser(String name, String pswd) {
        File users=new File("users.txt");
        try (FileWriter fw = new FileWriter(users, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(name + ":  " + pswd);
            out.close();
            File file=new File(name+".txt");

        } catch (IOException e) {
            System.out.println(">> Error opening file: \n");
            e.printStackTrace();
        }
    }

    private static boolean login(String name, String pswd) {
        File file = new File("users.txt");
        String found = find(name, file);
        String lookFor = (name + ":  " + pswd);
        if (found.equals(lookFor)) {
            return true;
        } else {
            return false;
        }
    }
    private static void dltUsr(){
        PrintWriter writer;
        try {
            writer = new PrintWriter("users.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException ex) {
            System.out.println(">> File not Found");
        }
    }
}
