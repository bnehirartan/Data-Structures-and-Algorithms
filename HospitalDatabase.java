package hw03_02;
import java.util.*;

//-----------------------------------------------------
//Title: HospitalDatabase class
//Description: //This program implements a hospital database using binary search trees (BST) to store and manage patient records
//and their associated care teams. It allows adding, removing, and querying patients and care team members, as well as sorting and filtering them based on 
//visit dates and doctors.
//-----------------------------------------------------

import java.util.*;

//BST Node for storing patients
	class PatientNode {
		String name; 
		int day, month, year; 
		String doctor;
		PatientNode left, right; 
		List<CareTeamMember> careTeam; // List of care team members assigned to this patient

		//--------------------------------------------------------
	    // Summary: Constructor for initializing a new patient node
	    // Precondition: name is a String, doctor is a String, day, month, year are integers
	    // Postcondition: Creates a new patient node with given data and an empty care team list
	    //--------------------------------------------------------
		
		PatientNode(String name, String doctor, int day, int month, int year) {
			this.name = name;
			this.doctor = doctor;
			this.day = day;
			this.month = month;
			this.year = year;
			this.left = this.right = null;
			this.careTeam = new ArrayList<>();
		}
	}


	class CareTeamMember {
	 String name; 
	 String role; 
	
	 	//--------------------------------------------------------
	    // Summary: Constructor for initializing a care team member
	    // Precondition: name is a String, role is a String
	    // Postcondition: Creates a new care team member with the specified name and role
	    //--------------------------------------------------------
	 CareTeamMember(String name, String role) {
	     this.name = name;
	     this.role = role;
	 }
	}

	
	// Main/primary class for the hospital database
	public class HospitalDatabase {
		
	 private PatientNode root; // Root of the BST, which stores patient data
	
	 	//--------------------------------------------------------
	    // Summary: Constructor to initialize an empty hospital database
	    // Precondition: None
	    // Postcondition: Creates an empty database (BST)
	    //--------------------------------------------------------
	 public HospitalDatabase() {
	     this.root = null;
	 }

	 	//--------------------------------------------------------
	    // Summary: Adds a new patient to the hospital database
	    // Precondition: name, doctor are Strings, day, month, year are integers
	    // Postcondition: A new patient is added to the BST or the existing patient's data is updated
	    //--------------------------------------------------------
	 public void addPatient(String name, String doctor, int day, int month, int year) {
	     root = addPatient(root, name, doctor, day, month, year);
	 }

	 	//--------------------------------------------------------
	    // Summary: Recursive helper method to add a patient to the BST
	    // Precondition: node is the current patient node, name, doctor are Strings, day, month, year are integers
	    // Postcondition: A new patient node is inserted or the existing patient's data is updated in the BST
	    //--------------------------------------------------------
	 private PatientNode addPatient(PatientNode node, String name, String doctor, int day, int month, int year) {
	     if (node == null) {
	         System.out.println("INFO: Patient " + name + " has been added");
	         return new PatientNode(name, doctor, day, month, year);
	     }
	
	     if (name.compareTo(node.name) < 0) {
	         node.left = addPatient(node.left, name, doctor, day, month, year);
	     } else if (name.compareTo(node.name) > 0) {
	         node.right = addPatient(node.right, name, doctor, day, month, year);
	     } else {
	         System.out.println("ERROR: Patient " + name + " overwritten");
	         node.day = day;
	         node.month = month;
	         node.year = year;
	         node.doctor = doctor;
	     }
	     return node;
	 }

	 	//--------------------------------------------------------
	    // Summary: Removes a patient from the hospital database
	    // Precondition: name is a String representing the patient's name
	    // Postcondition: The patient node is removed from the BST
	    //--------------------------------------------------------
	 public void removePatient(String name) {
	     root = removePatient(root, name);
	 }

	 	//--------------------------------------------------------
	    // Summary: Recursive helper method to remove a patient from the BST
	    // Precondition: node is the current patient node, name is a String
	    // Postcondition: Removes the patient node from the BST
	    //--------------------------------------------------------
	 private PatientNode removePatient(PatientNode node, String name) {
	     if (node == null) {
	         System.out.println("ERROR: Patient " + name + " does not exist");
	         return null;
	     }

	     if (name.compareTo(node.name) < 0) {
	         node.left = removePatient(node.left, name);
	     } else if (name.compareTo(node.name) > 0) {
	         node.right = removePatient(node.right, name);
	     } else {
	         if (node.left == null && node.right == null) {
	             System.out.println("INFO: Patient " + name + " has been removed");
	             return null; // No child nodes, just remove the node
	         } else if (node.left == null) {
	             System.out.println("INFO: Patient " + name + " has been removed");
	             return node.right; // Only right child
	         } else if (node.right == null) {
	             System.out.println("INFO: Patient " + name + " has been removed");
	             return node.left; // Only left child
	         } else {
	             // Patient with two children, find the node comes imm after 
	             PatientNode min = findMin(node.right);
	             node.name = min.name;
	             node.day = min.day;
	             node.month = min.month;
	             node.year = min.year;
	             node.doctor = min.doctor;
	             node.careTeam = min.careTeam;
	             node.right = removePatient(node.right, min.name);
	         }
	     }
	     return node;
	 }

	 	//--------------------------------------------------------
	    // Summary: Finds the minimum value node in the BST
	    // Precondition: node is the current node in the BST
	    // Postcondition: Returns the patient node with the smallest name
	    //--------------------------------------------------------
	 private PatientNode findMin(PatientNode node) {
	     while (node.left != null) {
	         node = node.left;
	     }
	     return node;
	 }

	 
	
	 	//--------------------------------------------------------
	    // Summary: Displays all patients sorted by their visit year
	    // Precondition: None
	    // Postcondition: Displays all patients in the BST sorted by their visit year
	    //--------------------------------------------------------
	 public void showAllPatients() {
	     if (root == null) {
	         System.out.println("---none---");
	     } else {
	         List<PatientNode> patients = new ArrayList<>();
	         inOrderTraversal(root, patients);  // Collect patients in an ArrayList
	         Collections.sort(patients, new Comparator<PatientNode>() {
	             @Override
	             public int compare(PatientNode p1, PatientNode p2) {
	                 // Sorting
	                return Integer.compare(p1.year, p2.year);
	             }
	         });

	      
	         for (PatientNode patient : patients) {
	             System.out.println(patient.name + ", " + patient.year + ", " + patient.doctor);
	         }
	     }
	 }

	 	//--------------------------------------------------------
	    // Summary: Recursive in-order traversal to collect patients in a list
	    // Precondition: node is the current node in the BST, patients is an ArrayList
	    // Postcondition: Collects all patient nodes in the patients list
	    //--------------------------------------------------------
	 private void inOrderTraversal(PatientNode node, List<PatientNode> patients) {
	     if (node != null) {
	         inOrderTraversal(node.left, patients);
	         patients.add(node);  // Add patient to the list
	         inOrderTraversal(node.right, patients);
	 }
	 }

	 	//--------------------------------------------------------
	    // Summary: Displays the details of a specific patient
	    // Precondition: name is a String representing the patient's name
	    // Postcondition: Displays the patient's details including care team members,sorting in ascending order by member name
	    //--------------------------------------------------------
	 public void showPatient(String name) {
	     PatientNode patient = searchPatient(root, name);
	     if (patient == null) {
	         System.out.println("ERROR: Patient " + name + " does not exist");
	     } else {
	         System.out.println(patient.name);
	         System.out.println(patient.day + "/" + patient.month + "/" + patient.year);
	         System.out.println(patient.doctor);
	         
	         //Sorting
	         Collections.sort(patient.careTeam, (a, b) -> a.name.compareTo(b.name));
	         
	        
	         for (CareTeamMember member : patient.careTeam) {
	             System.out.println(member.name + ", " + member.role);
	         }
	     }
	 }


	 	//--------------------------------------------------------
	    // Summary: Adds a care team member to a specific patient
	    // Precondition: patientName and memberName are Strings, role is a String
	    // Postcondition: Adds the care team member to the specified patient's care team
	    //--------------------------------------------------------
	 public void addMember(String patientName, String memberName, String role) {
	     PatientNode patient = searchPatient(root, patientName);
	     if (patient == null) {
	         System.out.println("ERROR: Patient " + patientName + " does not exist");
	     } else {
	         patient.careTeam.add(new CareTeamMember(memberName, role));
	         System.out.println("INFO: " + memberName + " has been added to the patient " + patientName);
	     }
	 }

	 	//--------------------------------------------------------
	    // Summary: Removes a care team member from a specific patient
	    // Precondition: patientName and memberName are Strings
	    // Postcondition: Removes the care team member from the specified patient's care team
	 	//--------------------------------------------------------
	 public void removeMember(String patientName, String memberName) {
	     PatientNode patient = searchPatient(root, patientName);
	     if (patient == null) {
	         System.out.println("ERROR: Patient " + patientName + " does not exist");
	     } else {
	         boolean removed = patient.careTeam.removeIf(member -> member.name.equals(memberName));
	         if (removed) {
	             System.out.println("INFO: " + memberName + " has been removed from the patient " + patientName);
	         } else {
	             System.out.println("ERROR: Member " + memberName + " does not exist in the care team of " + patientName);
	         }
	     }
	 }

	//--------------------------------------------------------
	// Summary: Displays all patients treated by a specific doctor
	// Precondition: doctorName is a non-null String 
	// Postcondition: Prints the list of patients treated by the specified doctor 
	//--------------------------------------------------------
	 public void showDoctorPatients(String doctorName) {
		 System.out.println(doctorName);
	     showDoctorPatients(root, doctorName);
	     
	 }
	 
	//--------------------------------------------------------
	// Summary: Recursive helper method to display patients treated by a specific doctor
	// Precondition: node is the current node in the BST, doctorName is a non-null String
	// Postcondition: Prints the list of patients treated by the specified doctor, in descending order by visit years
	//--------------------------------------------------------
	 private void showDoctorPatients(PatientNode node, String doctorName) {
		 
	     if (node != null) {
	         showDoctorPatients(node.left, doctorName);
	         if (node.doctor.equals(doctorName)) {
	             System.out.println(node.name + ", " + node.day + "/" + node.month + "/" + node.year);
	         }
	         showDoctorPatients(node.right, doctorName);
	     }
	 }

	//--------------------------------------------------------
	// Summary: Displays all patients admitted in a specific year
	// Precondition: year is a positive integer 
	// Postcondition: Prints the list of patients admitted in the specified year
	//--------------------------------------------------------
	 public void showPatients(int year) {
		 System.out.println(year);
	     showPatients(root, year);
	 }

	 
	//--------------------------------------------------------
	// Summary: Recursive helper method to display patients admitted in a specific year.
	// Precondition: node is the current node in the BST, year is a positive integer
	// Postcondition: Prints the list of patients admitted in the specified year, in descending order by visit year
	//--------------------------------------------------------
	 private void showPatients(PatientNode node, int year) {
	     if (node != null) {
	         showPatients(node.right, year);
	         if (node.year == year) {
	             System.out.println(node.name + ", " + node.day + "/" + node.month);
	         }
	         showPatients(node.left, year);
	     }
	 }

	//--------------------------------------------------------
	// Summary: Searches for a patient in the BST by name.
	// Precondition: node is the current node in the BST, name is a non-null String 
	// Postcondition: Returns the node (patient) if found, or null if the patient is not found in the tree
	//--------------------------------------------------------
	 private PatientNode searchPatient(PatientNode node, String name) {
	     if (node == null || name.equals(node.name)) {
	         return node;
	     }
	     if (name.compareTo(node.name) < 0) {
	         return searchPatient(node.left, name);
	     } else {
	         return searchPatient(node.right, name);
	     }
	 }

	//--------------------------------------------------------
	// Summary: Main method for testing the program, adding, removing, and displaying patients
	// Precondition: None
	// Postcondition: Tests the functionality of the HospitalDatabase class with sample data
	//--------------------------------------------------------
	 public static void main(String[] args) {
	     HospitalDatabase hd = new HospitalDatabase();
	     hd.showAllPatients();
	     hd.addPatient("Michael Johnson","Emma Thompson", 19, 12, 2022);
	     hd.addPatient("Ethan Lee", "Olivia Sanchez", 8, 9, 2020);
	     hd.addPatient("Noah Miller", "Olivia Sanchez", 27, 2, 2019);
	     hd.addPatient("Liam Davis", "Isabella Martinez", 3, 4, 2022);
	     hd.addPatient("Ava Taylor", "Isabella Martinez", 15, 5, 2024);
	     hd.addPatient("Mason Moore", "William Anderson", 7, 6, 2021);
	     hd.addPatient("Charlotte Garcia", "Lucas Lewis", 30, 10, 2023);
	     hd.addPatient("Noah Miller", "Olivia Sanchez", 27, 2, 2019);
	     hd.showAllPatients();
	     hd.removePatient("Ava Taylor");
	     hd.showAllPatients();
	     hd.showPatient("Michael Johnson");
	     hd.addMember("Mason Moore", "Daniel Roberts", "Nurse");
	     hd.addMember ("Mason Moore", "Victoria Stewart", "Radiologist");
	     hd.addMember ("Mason Moore", "Tyler Campbell", "Medical Assistant");
	     hd.addMember ("Mason Moore", "Hannah Martin", "Paramedic");
	     hd.addMember ("Michael Johnson", "Jack Allen", "Patient Care Technician");
	     hd.addMember ("Michael Johnson", "Oliver Nelson", "Anesthesiologist");
	     hd.addMember ("Michael Johnson", "Sophia Rivera", "Pathologist");
	     hd.addMember ("Michael Johnson", "Evan Hall", "Laboratory Technician");
	     hd.addMember ("Michael Johnson", "Megan Price", "Nurse");
	     hd.addMember ("Ava Taylor", "Brianna Reed", "Dietitian");
	     hd.addMember ("Charlotte Garcia", "Oliver Nelson", "Anesthesiologist");
	     hd.addMember ("Charlotte Garcia", "Trevor Jenkins", "Medical Equipment Technician");
	     hd.addMember ("Charlotte Garcia", "Justin Flores", "Speech-Language Pathologist");
	     hd.showPatient("Mason Moore");
	     hd.showPatient("Michael Johnson");
	     hd.removeMember("Michael Johnson", "Evan Hall");
	     hd.showPatient("Michael Johnson");
	     hd.showDoctorPatients("Olivia Sanchez");
	     hd.showDoctorPatients("Emma Thompson");
	     hd.showPatients(2022);

	     
	     System.exit(0);
	 }
	}
