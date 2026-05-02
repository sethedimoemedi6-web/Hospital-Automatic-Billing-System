

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    //final String File_Name="clinic_records";

    public static void main(String[] args) {


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


            if (!file.exists()) {
                System.out.println("Patient file not found. A new file will be created when data is saved.");
                return;
            }

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
                    } else {
                        System.out.println("Skipping invalid line in file: " + line);
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

        //writing to the file
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("clinic_records.txt"));
            bw.write(patientCopy.getFirstname());
            bw.newLine();
            bw.write(patientCopy.getLastname());
            bw.newLine();
            bw.write(patientCopy.getId());
            bw.newLine();
            bw.write(patientCopy.getAge());
            bw.newLine();
            bw.write(patientCopy.getGender());

        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error adding patient: " + e.getMessage());
        }


        System.out.println("Calculate the patient Bill");
        System.out.println("Enter the number of labtests");
        int labtests = keyboard.nextInt();

        System.out.println("Enter the consultation fee");
        int consultationFee = keyboard.nextInt();

        PatientBilling PB = new PatientBilling(labtests, consultationFee);
        System.out.println("\n---Bill Summary---");
        System.out.println("Subtotal:" + PB.calculateSubtotal());
        System.out.println("Vat rate:" + PB.calculateVatRate());
        System.out.println("Final Total:" + PB.calculateTotal());


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



















