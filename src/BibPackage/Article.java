//Author: Thi Hong Phuc Nguyen
//Checked & modified by Dinh Hoang Nhan Nguyen.
package BibPackage;

import java.util.StringTokenizer;

public class Article {
	private String author;
	private String journal;
	private String title;
	private String year;
	private String volume;
	private String number;
	private String pages;
	// private String keywords;
	private String doi;
	private String month;
	// private String issn;

	public Article(String author, String journal, String title, String year, String volume, String number, String pages,
			String doi, String month) {
		this.author = author;
		this.journal = journal;
		this.title = title;
		this.year = year;
		this.volume = volume;
		this.number = number;
		this.pages = pages;
		// this.keywords = keywords;
		this.doi = doi;
		this.month = month;
		// this.issn = issn;
	}

	//Write author in IEEE format
	public String formatIEEE() {
		StringTokenizer str = new StringTokenizer(this.author, " ");
		String authors = "";
		while (str.hasMoreTokens()) {
			String token = str.nextToken();
			if (token.equals("and")) {
				token = str.nextToken();
				authors = authors.trim() + ", " + token;
			} else {
				authors = authors + token + " ";
			}
		}
		return authors.trim() + ". " + "\"" + this.title + "\", " + this.journal + ", vol. " + this.volume + ", no. "
				+ this.number + ", p. " + this.pages + ", " + this.month + " " + this.year + ".";
	}

	//Write author in ACM format
	public String formatACM(int num) {
		StringTokenizer str = new StringTokenizer(this.author, " ");
		String authors = "";
		while (str.hasMoreTokens()) {
			String token = str.nextToken();
			if (token.equals("and")) {
				token = str.nextToken();
				if (str.hasMoreTokens()) {
					authors = authors + "et al";
				}
				break;
			} else {
				authors = authors + token + " ";
			}
		}
		return "[" + num + "]\t" + authors.trim() + ". " + this.year + ". " + this.title + ". " + this.journal + ". "
				+ this.volume + ", " + this.number + "(" + this.year + "), " + this.pages + ". DOI:https://doi.org/"
				+ this.doi + ".";
	}

	//Write author in IEEE format
	public String formatNJ() {
		StringTokenizer str = new StringTokenizer(this.author, " ");
		String authors = "";
		while (str.hasMoreTokens()) {
			String token = str.nextToken();
			if (token.equals("and")) {
				token = str.nextToken();
				authors = authors + "& " + token;
			} else {
				authors = authors + token + " ";
			}
		}
		return authors.trim() + ". " + this.title + ". " + this.journal + ". " + this.volume + ", " + this.pages + "("
				+ this.year + ").";
	}
}
