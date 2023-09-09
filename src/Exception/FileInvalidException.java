package Exception;
//Author: Thi Hong Phuc Nguyen from line 1 - 10
//Author: Dinh Hoang Nhan Nguyen from line 11 -18

@SuppressWarnings("serial")
public class FileInvalidException extends Exception {
	public FileInvalidException(String emptyField) {
		super("Error: Input file cannot be parsed due to missing information\r\n" + emptyField);
	}

	public FileInvalidException(int inputFileNumber, String emptyField) {
		System.out.println("Error: Detected Empty Field!");
		System.out.println("============================");
		System.out.println("\nProblem detected with file: Latex" + inputFileNumber + ".bib.");
		System.out.println("File is Invalid: Field \"" + emptyField
				+ "\" is Empty. Processing has stopped at this point. Other empty fields may be present as well!\n");
	}
}