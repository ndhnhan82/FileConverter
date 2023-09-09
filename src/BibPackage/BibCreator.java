package BibPackage;
//Author: Thi Hong Phuc Nguyen from line 1 - 120
//Author: Dinh Hoang Nhan Nguyen from line 121 - 160
//All the team members check and modify together.
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Exception.FileInvalidException;

public class BibCreator {

	
	//Method to delete all the created files
	static void deleteCreatedFiles(File[] createdFiles, FileOutputStream[] openedFiles, int countCreatedFiles)
			throws IOException {
		for (int i = 0; i <= countCreatedFiles; i++) {
			openedFiles[i].close();
			createdFiles[i].delete();
		}
	}

	static FileOutputStream[] openedFiles = new FileOutputStream[30];
	// static File dir = new File("InputFiles");
	// static File[] inputFiles = dir.listFiles();
	static int countFileOpened = 0;
	static File[] createdFiles = new File[30];
	static int countCreatedFiles = 0;
	static PrintWriter[] pwIEEE = new PrintWriter[10];
	static PrintWriter[] pwACM = new PrintWriter[10];
	static PrintWriter[] pwNJ = new PrintWriter[10];
	static File[] IEEEFile = new File[10];
	static File[] ACMFile = new File[10];
	static File[] NJFile = new File[10];

	public static void main(String[] args) throws FileInvalidException, IOException {
		System.out.println("Welcome to BibCreator!\n");
		
		// Check 10 input_files can open or not
		Scanner[] scanners = new Scanner[10];
		for (int i = 0; i < 10; i++) {
			try {
				// scanner = new Scanner(new FileInputStream(inputFiles[i]));
				scanners[i] = new Scanner(new FileInputStream("InputFiles/Latex" + (i + 1) + ".bib"));
				countFileOpened++;
			} catch (FileNotFoundException e) {
				
				// Close all open files
				for (int j = 0; j < countFileOpened; j++) {
					scanners[j].close();
				}
				
				System.out.println("Could not open input file Latex" + (i + 1) + ".bib for reading.");
				System.out.println(
						"\nPlease check if file exist! Program will terminate after closing any opened files.");
				System.exit(0);
			}
		}
		
		// Created 30 empty files;
		//In case of failing open/create new files, delete all the files
		if (countFileOpened == 10) {
			
			PrintWriter printWriter = null;
			for (int i = 0; i < 10; i++) {
				
				//Try to open/create IEEE file
				try {
					FileOutputStream openedFile = new FileOutputStream("OutputFiles/IEEE" + (i + 1) + ".json");
					printWriter = new PrintWriter(openedFile, true);
					pwIEEE[i] = printWriter;
					createdFiles[countCreatedFiles] = new File("OutputFiles/IEEE" + (i + 1) + ".json");
					IEEEFile[i] = createdFiles[countCreatedFiles];
					openedFiles[countCreatedFiles] = openedFile;
					countCreatedFiles++;
				} catch (FileNotFoundException e) {
					System.out.println("The file " + "IEEE" + (i + 1) + ".json" + " can not be opened/created!");
					deleteCreatedFiles(createdFiles, openedFiles, countCreatedFiles);
					countCreatedFiles = 0;
					System.out.println("All the previous file has been deleted.");
				}
				
				//Try to open/create ACM file
				try {
					FileOutputStream openedFile = new FileOutputStream("OutputFiles/ACM" + (i + 1) + ".json");
					printWriter = new PrintWriter(openedFile, true);
					pwACM[i] = printWriter;
					createdFiles[countCreatedFiles] = new File("OutputFiles/ACM" + (i + 1) + ".json");
					ACMFile[i] = createdFiles[countCreatedFiles];
					openedFiles[countCreatedFiles] = openedFile;
					countCreatedFiles++;
				} catch (FileNotFoundException e) {
					System.out.println("The file " + "ACM" + (i + 1) + ".json" + " can not be opened/created!");
					deleteCreatedFiles(createdFiles, openedFiles, countCreatedFiles);
					countCreatedFiles = 0;
					System.out.println("All the previous file has been deleted.");
				}
				
				//Try to open/create NJ file
				try {
					FileOutputStream openedFile = new FileOutputStream("OutputFiles/NJ" + (i + 1) + ".json");
					printWriter = new PrintWriter(openedFile, true);
					pwNJ[i] = printWriter;
					createdFiles[countCreatedFiles] = new File("OutputFiles/NJ" + (i + 1) + ".json");
					NJFile[i] = createdFiles[countCreatedFiles];
					openedFiles[countCreatedFiles] = openedFile;
					countCreatedFiles++;
				} catch (FileNotFoundException e) {
					System.out.println("The file " + "NJ" + (i + 1) + ".json" + " can not be opened/created!");
					deleteCreatedFiles(createdFiles, openedFiles, countCreatedFiles);
					countCreatedFiles = 0;
					System.out.println("All the previous file has been deleted.");
				}
			}

			// Program will call processFilesForValidation for all 10 latex files
			BufferedReader inputFiles;
			for (int i = 0; i < 10; i++) {
				inputFiles = new BufferedReader(new FileReader("InputFiles/Latex" + (i + 1) + ".bib"));

				FileValidation.processFilesForValidation(i + 1, inputFiles, pwIEEE[i], pwACM[i], pwNJ[i], ACMFile[i],
						IEEEFile[i], NJFile[i]);
				//Close all input files and output files
				inputFiles.close();
				pwIEEE[i].close();
				pwACM[i].close();
				pwNJ[i].close();

			}
			
			//Display the result after validating all the files
			System.out.println("A total of " + FileValidation.countInvalidInputFile
					+ " files were invalid, and could not be processed. All other "
					+ (10 - FileValidation.countInvalidInputFile) + " \"Valid\" files have been created.\n");

			// Review file from users:
			try {
				FileValidation.reviewFile();
			} catch (FileNotFoundException e1) {
				System.out.println("Could not open input file. File does not exist; possibly it could not be created!"
						+ "\n\nHowever, you will be allowed another chance to enter another file name.");
				try {
					FileValidation.reviewFile();
				} catch (FileNotFoundException e2) {
					System.out.println(
							"\nCould not open input file. File does not exist; possibly it could not be created!"
									+ "\nSorry! I am unable to display your desired files! Program will exit!");
					System.exit(0);
				}
			}
			System.out.println("Goodbye! Hope you have enjoyed creating the needed files using BibCreator.");
		}
	}
}