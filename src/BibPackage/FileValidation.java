package BibPackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Exception.FileInvalidException;

//Author: Dinh Hoang Nhan Nguyen
//Checked & modified by Thi Hong Phuc Nguyen.
public class FileValidation {
	public static int countInvalidInputFile = 0;

	public static void processFilesForValidation(int inputFileNumber, BufferedReader inputFile, PrintWriter pwIEEE,
			PrintWriter pwACM, PrintWriter pwNJ, File ACMFile, File IEEEFile, File NJFile)
			throws FileInvalidException, IOException {

		String emptyField;
		String author = null;
		String journal = null;
		String title = null;
		String year = null;
		String volume = null;
		String number = null;
		String pages = null;
		// String keywords=null;
		String doi = null;
		// String ISSN=null;
		String month = null;
		String line;
		int countACM = 0;
		try {
			while ((line = inputFile.readLine()) != null) {
				boolean lineError = false;

				if (line.equals("")) {
					// Do nothing
				} else if (line.contains("author={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "author";
					lineError = true;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("journal={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					lineError = true;
					emptyField = "journal";
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("title={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "title";
					lineError = true;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("year={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "year";
					lineError = true;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("volume={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "volume";
					lineError = true;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("number={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "number";
					lineError = true;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("pages={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "pages";
					lineError = true;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("keywords={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "keywords";
					lineError = false;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("doi={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "doi";
					lineError = true;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("ISSN={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "ISSN";
					lineError = false;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("month={},")) {
					pwIEEE.close();
					pwACM.close();
					pwNJ.close();
					ACMFile.delete();
					IEEEFile.delete();
					NJFile.delete();
					emptyField = "month";
					lineError = true;
					throw new FileInvalidException(inputFileNumber, emptyField);
				} else if (line.contains("author={") && !lineError) {
					author = line.substring(8, line.indexOf("}"));
				} else if (line.contains("journal={") && !lineError) {
					journal = line.substring(9, line.indexOf("}"));
				} else if (line.contains("title={") && !lineError) {
					title = line.substring(7, line.indexOf("}"));
				} else if (line.contains("year={") && !lineError) {
					year = line.substring(6, line.indexOf("}"));
				} else if (line.contains("volume={") && !lineError) {
					volume = line.substring(8, line.indexOf("}"));
				} else if (line.contains("number={") && !lineError) {
					number = line.substring(8, line.indexOf("}"));
				} else if (line.contains("pages={") && !lineError) {
					pages = line.substring(7, line.indexOf("}"));
				} else if (line.contains("doi={") && !lineError) {
					doi = line.substring(5, line.indexOf("}"));
				} else if (line.contains("month={") && !lineError) {
					
					//If the article does not contain any empty fields, write the article in 3 formats
					month = line.substring(7, line.indexOf("}"));
					countACM++;
					Article article = new Article(author, journal, title, year, volume, number, pages, doi, month);
					pwIEEE.println(article.formatIEEE());
					pwACM.println(article.formatACM(countACM));
					pwNJ.println(article.formatNJ());
				}
			}
		} catch (FileInvalidException e) {
			countInvalidInputFile++;
		}
	}

	public static void reviewFile() throws FileNotFoundException, IOException {
		System.out.print("Please enter the name of one of the files that you need to review:");
		Scanner sc = new Scanner(System.in);
		String nameFileReview = sc.next();
		BufferedReader brFileReview = new BufferedReader(new FileReader("OutputFiles/" + nameFileReview));

		System.out.println("Here are the contents of the successfully created Jason Files: " + nameFileReview);
		String line;
		while ((line = brFileReview.readLine()) != null) {
			System.out.println(line + "\n");
		}
		brFileReview.close();
		sc.close();
	}
}
