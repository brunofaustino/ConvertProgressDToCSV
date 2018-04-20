
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class ConvertProgressDToCSV {

	public static String record = "";

	// Insira o arquivo de origem (.d)
	public static String importFile = "C:\\Users\\bruno.faustino\\Downloads\\Import.d";
	// Insira o arquivo de origem (.csv)
	public static String exportFile = "C:\\Users\\bruno.faustino\\Downloads\\ExportCSV.csv";

	public static void main(String[] args) {

		// String name = "C:\\Users\\bruno.faustino\\Downloads\\paciente.d";
		String nome = importFile;

		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);
			String line = lerArq.readLine();
			String lineAlter = line;

			while (true) {
				if (line != null) {
					for (int i = 0; i < 100; i++) {
						String lineProx = lerArq.readLine();
						try {
							char s = lineProx.charAt(0);
							String l = lineProx.substring(1, 3);
							if (l.equals("PSC")) {
								System.out.println("Procedimento finalizado! ");
							}
							if (!Character.isDigit(s)) {
								lineAlter = lineAlter + lineProx;
								// System.out.println("\n"+lineAlter);
								continue;
							} else {
								// System.out.println("\n"+lineAlter);
								// Fit
								fit(lineAlter);
								lineAlter = lineProx;
								break;
							}
						} catch (Exception e) {
							System.out.println("Procedimento finalizado! ");
							System.exit(0);
						}
					}
				} else {
					break;
				}
			}
			line = lerArq.readLine();
			lerArq.close();
			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro: %s.\n", e.getMessage());
		}
	}

	public static void fit(String line) throws IOException {

		// Cleasin record
		record = "";
		boolean fieldControl = false;
		String delimited = ";";

		for (int j = 0, r = 1; j < line.length(); j++, r++) {

			char s = line.charAt(j);

			if (Character.isDigit(s) || s == '"') {
				// Open field
				// Inite String
				if (s == '"') {
					fieldControl = true;
				} else {
					fieldControl = false;
				}
				// find end of field
				int z = 1;
				while (true) {
					char possibleEnd = line.charAt(j + z);

					// Test End String
					if (possibleEnd == '"' && fieldControl == true) {
						// I came to the end
						String fieldFound = line.substring(j + 1, j + z);
						// System.out.println("\nField found (End String): " +
						// fieldFound);
						j = j + z;
						z = 0;
						// Finish String
						fieldControl = false;
						// Add field to return
						record = record + delimited + fieldFound;
						break;
					}

					// Test End Numeric
					if (possibleEnd == ' ' && fieldControl == false) {
						// I came to the end
						String fieldFound = line.substring(j, j + z);
						// System.out.println("\nField found (End Numeric): " +
						// fieldFound);
						j = j + z;
						z = 0;
						// Finish String
						fieldControl = false;
						// Add field to return
						record = record + delimited + fieldFound;
						break;
					}
					z++;
				}
			} else {
				if (!Character.isDigit(s) && s != ' ') {
					// System.out.println("\nDifferential: "+s);
					// find end of field
					int w = 1;
					while (true) {
						char possibleEnd = line.charAt(j + w);
						if (possibleEnd == ' ') {
							// I came to the end
							String fieldFound = line.substring(j, j + w);
							// System.out.println("\nField found (End
							// Differential): " + fieldFound);
							j = j + w;
							record = record + delimited + fieldFound;
							break;
						}
						w++;
					}
				}

			}

		}
		// Remove initial delimited
		record = record.substring(1, record.length());
		// System.out.println("==>> REGISTRO: " + record);
		// System.out.println("==>> REGISTRO: " + record.split(";"));
		exporToFile();
	}

	public static void exporToFile() throws IOException {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(exportFile, true));
			out.write(record);
			out.newLine();
			out.flush();
			String a[] = record.split(";");
			System.out.println("\nExportando registro: " + a[0]);
			out.close();
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());

		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
}
