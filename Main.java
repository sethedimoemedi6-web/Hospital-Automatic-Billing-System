

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //final String File_Name="clinic_records";

    public static void main(String[] args) {


//        try {
//            File file = new File("clinic_records.txt");
//            System.out.println("File path: " + file.getAbsolutePath()); // shows WHERE it's saving
//            System.out.println("File exists: " + file.exists());
//
//            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
//            bw.write("TEST WRITE");
//            bw.newLine();
//            bw.close();
//
//            System.out.println("Write successful!");
//
//        } catch (IOException e) {
//            System.out.println("ERROR: " + e.getMessage()); // tells us exactly what's wrong
//        }


        Scanner sc = new Scanner(System.in);

        ArrayList<patient> patients = new ArrayList<>();

        while (true) {
            System.out.println("Choose what you want to do");
            System.out.println("1. Add new patient");
            System.out.println("2. To delete patient");
            System.out.println("3. To view all  patient");
            System.out.println("4. To view patient by ID");
            System.out.println("5.To exit");
            System.out.println("Enter your choice");

            int choice = sc.nextInt();
            switch (choice) {


                case 1 -> {
                    addpatient(patients, sc);
                    writePatientsToFile(patients);
                }
                case 2 -> {
                    deletepatient(patients, sc);
                    writePatientsToFile(patients);
              }
                case 3 -> {
                    viewpatient(patients);
                }
                case 4 -> {
                    searchpatient(patients, sc);
                }
                case 5 -> {
                    System.out.println("exiting the program");
                }
            }
            //ArrayList<patient> readPatientsFromFile(){
            // ArrayList<patient> patients = new ArrayList<>();}

            File file = new File("clinic_records.txt");




            try (BufferedReader br = new BufferedReader(new FileReader("clinic_records.txt"))) {
                String line;

                br.readLine();
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) {
                        continue;
                    }


                    String[] data = line.split(",");

                    if (data.length == 5) {
                        patient patient = new patient(
                                data[0].trim(),
                                data[1].trim(),
                                data[2].trim(),
                                data[3].trim(),
                                data[4].trim().charAt(0)
                        );

                        patients.add(patient);
//                    } else {
//                        System.out.println("Skipping invalid line in file: " + line);
                    }
                }
                System.out.println("Patient file has been successfully read.");
            } catch (IOException e) {
                System.out.println("Error reading file" + e.getMessage());
            }

        }
    }

    private static void writePatientsToFile(ArrayList<patient> patients) {
    }

    //for adding the patient to the system
    public static void addpatient(ArrayList<patient> patients, Scanner keyboard) {
        System.out.print("Enter Patient firstname: ");
        String firstname = keyboard.next();

        System.out.print("Enter Patient lastname: ");
        String lastname = keyboard.next();

        System.out.print("Enter patient id: ");
        String id = keyboard.next();

        System.out.print("Enter Patient Age: ");
        String age = keyboard.next();

        System.out.print("Enter Patient Gender (M/F): ");
        char gender = keyboard.next().charAt(0);

        patient patientCopy = new patient(firstname, lastname, id, age, gender);
        patients.add(patientCopy);
        System.out.println(patientCopy.getFirstname() + " " + patientCopy.getLastname() + " added successfully.");

        String ConsultationType = "";
        int consultationPrice = 0;

        System.out.println("\nSelect Consultation Type:");
        System.out.println("1. General_checkup  P300");
        System.out.println("2. Specialist   P500");
        System.out.println("3. Emergency      P1000");
        System.out.print("Enter choice (1/2/3): ");
        int Choice = keyboard.nextInt();
        keyboard.nextLine();

        if (Choice == 1) {
            ConsultationType = "General_checkup";
            consultationPrice = 300;
        } else if (Choice == 2) {
            ConsultationType = "Specialist";
            consultationPrice = 500;
        } else if (Choice == 3) {
            ConsultationType = "Emergency";
            consultationPrice = 1000;
        }


            //writing to the file
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("clinic_records.txt", true));
            bw.write("======Patient Personal Data======");
            bw.write("Firstname: " + patientCopy.getFirstname());
            bw.newLine();
            bw.write("Lastname: " + patientCopy.getLastname());
            bw.newLine();
            bw.write("ID: " + patientCopy.getId());
            bw.newLine();
            bw.write("Age: " + patientCopy.getAge());
            bw.newLine();
            bw.write("Gender: " + patientCopy.getGender());
            bw.newLine();

            bw.write("======Consultation Section======");
            bw.newLine();
            bw.write("Consultation Type: " + ConsultationType + "\n" + "Amount: " + consultationPrice);
            bw.newLine();
            bw.write("======Selected Labtests======");
            bw.newLine();
            System.out.println("Enter Additional Labtests you wish to take: ");
            System.out.println("1. Blood Test");
            System.out.println("2. X-Ray");
            System.out.println("3. Covid Test");
            System.out.print("Enter option (1/2/3): ");
            int option = keyboard.nextInt();
            keyboard.nextLine();

            switch (option) {
                case 1 -> bw.write("labtest:Blood Test");
                case 2 -> bw.write("labtest:X-Ray");
                case 3 -> bw.write("labtest:Covid Test");
                default -> System.out.println("Invalid option, no test recorded.");
            }
            int labtest = 0;
            labtest++;
            bw.newLine();

            System.out.print("Do you wish to have a second labtest? (yes/no): ");
            String answer = keyboard.nextLine().trim().toLowerCase();

            if (answer.equals("yes")) {
                System.out.println("1. Blood Test");
                System.out.println("2. X-Ray");
                System.out.println("3. Covid Test");
                System.out.print("Enter option (1/2/3): ");
                int secondOption = keyboard.nextInt();
                keyboard.nextLine();

                switch (secondOption) {
                    case 1 -> bw.write("labtest:Blood Test");
                    case 2 -> bw.write("labtest:X-Ray");
                    case 3 -> bw.write("labtest:Covid Test");
                    default -> System.out.println("Invalid option, no test recorded.");
                }
                labtest++;
                bw.newLine();

                System.out.print("Do you wish to have a third labtest? (yes/no): ");
                String answer2 = keyboard.nextLine().trim().toLowerCase();

                if (answer2.equals("yes")) {
                    System.out.println("1. Blood Test");
                    System.out.println("2. X-Ray");
                    System.out.println("3. Covid Test");
                    System.out.print("Enter option (1/2/3): ");
                    int thirdOption = keyboard.nextInt();
                    keyboard.nextLine();

                    switch (thirdOption) {
                        case 1 -> bw.write("labtest:Blood Test");
                        case 2 -> bw.write("labtest:X-Ray");
                        case 3 -> bw.write("labtest:Covid Test");
                        default -> System.out.println("Invalid option, no test recorded.");
                    }
                    labtest++;
                    bw.newLine();}
            }
            bw.newLine();

            System.out.println("Calculating  the patient Bill");
            bw.newLine();

            PatientBilling PB = new PatientBilling(labtest, consultationPrice);
            bw.write("=======Bill Summary=======");
          bw.newLine();
            bw.write(String.valueOf("Subtotal: "+PB.calculateSubtotal()));
            bw.newLine();
            bw.write(String.valueOf("Vat Rate: "+PB.calculateVatRate()));
            bw.newLine();
            bw.write(String.valueOf("Final total: "+PB.calculateTotal()));
            bw.newLine();
            bw.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error adding patient: " + e.getMessage());
        }



    }
//for removing the patient
   public static void deletepatient(ArrayList<patient> patients, Scanner keyboard) {
        System.out.print("Enter Patient ID to delete: ");
        String searchID = keyboard.nextLine().trim();

        boolean found = false;

        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equalsIgnoreCase(searchID)) {
                System.out.println("Deleted: " + patients.get(i).getFirstname() + " " + patients.get(i).getLastname());
                patients.remove(i);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Patient record not found.");
        }
    }

    public static void viewpatient(ArrayList<patient> Patients) {
        if (Patients.isEmpty()) {
            System.out.println("No patient found.");
            return;
        }

        System.out.println("\n----- Patient list -----");
        for (patient p :Patients) {
            System.out.println("Firstname     : " + p.getFirstname()    + "Lastname: " + p.getLastname());
            System.out.println("Patient ID: " + p.getId());
            System.out.println("Age     : " + p.getAge());
            System.out.println("Gender    : " + p.getGender());
            System.out.println("-----------------------------");
        }
    }
//this is my method for searching a patient
    public static void searchpatient(ArrayList<patient> patients, Scanner keyboard) {
        System.out.print("Enter Patient ID or name to search: ");
        String searchTerm = keyboard.nextLine().trim();

        boolean found = false;

        for (patient p : patients) {

            if (p.getId().equalsIgnoreCase(searchTerm)
            ||p.getFirstname().equalsIgnoreCase(searchTerm)
        ||p.getLastname().equalsIgnoreCase(searchTerm)); {

                System.out.println("\nPatient found:");
                System.out.println("Firstname      : " + p.getFirstname() + " " + p.getLastname());
                System.out.println("Patient ID: " + p.getId());
                System.out.println("Age       : " + p.getAge());
                System.out.println("Gender    : " + p.getGender());
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Patient record not found.");
        }

    }
}



















