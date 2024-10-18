import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class FileStorage {
	/*
	 * Methods for storing in a file and retrieving from a file
	 */

	private String filePath;
	private HashMap<String, Content> contentMap;
	private File file = new File("src/AllContent.txt");
	private BufferedReader br = null;
	private BufferedWriter bw = null;

	public FileStorage(HashMap<String, Content> cMap) {
		contentMap = cMap;
	}

	public FileStorage(HashMap<String, Content> cMap, String path) {
		contentMap = cMap;
		filePath = path;
	}

	/*
	 * Method to read data from a text file
	 * 
	 * @return a HashMap containing the content from the file
	 */
	public HashMap<String, Content> readFromFile() {

		try {
			// create BufferedReader object from the File
			br = new BufferedReader(new FileReader(file));

			String line = null;

			// read file line by line
			while ((line = br.readLine()) != null) {

				// split the line by :
				String[] parts = line.split(":::");

				// first part is title
				String title = parts[0].trim();
				Content c = null;

				String[] attributes = parts[1].split(",");

				String[] tags = parts[1].substring(parts[1].indexOf("[") + 1, parts[1].length() - 1).split(",");

				String type = parts[3].substring(6);

				if (type.equals("Book"))
					c = new Book(attributes[0], attributes[1], Integer.parseInt(attributes[2]), attributes[3], tags);
				if (type.equals("Article"))
					c = new Article(attributes[0], attributes[1], Integer.parseInt(attributes[2]), attributes[3], tags);

				if (parts[2] != null) {
					c.setNotes(parts[2]);
				} else {
					c.setNotes("");
				}

				// put title, content in HashMap if they are not empty
				if (!title.equals("") && !c.equals(null))
					contentMap.put(title, c);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// Close the BufferedReader
			if (br != null) {
				try {
					br.close();
				} catch (Exception e) {
				}
				;
			}
		}

		return contentMap;

	}

	/*
	 * Method to write data from the content list to a text file
	 * 
	 * @param a Map containg the data to be stored
	 */
	public void writeToFile(Map<String, Content> map) {

		contentMap = (HashMap<String, Content>) map;
		try {

			// create new BufferedWriter for the output file
			bw = new BufferedWriter(new FileWriter(filePath));

			// iterate map entries
			for (Entry<String, Content> entry : map.entrySet()) {

				// put key and value separated by a colon
				bw.write(entry.getKey() + ":::" + entry.getValue() + ":::" + entry.getValue().getNotes() + ":::"
						+ entry.getValue().getClass());
				bw.newLine();

			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// close the writer
				bw.close();
			} catch (Exception e) {
			}
		}

	}

	/*
	 * Method to add a new piece of content to a text file
	 * 
	 * @param the title, author, and status of the item, followed by its tags, type,
	 * and length
	 */
	public void addContentToFile(String title, String author, String status, String[] tags, String type, int len) {
		Content c = null;
		if (type.equals("Book"))
			c = new Book(title, author, len, status, tags);
		if (type.equals("Article"))
			c = new Article(title, author, len, status, tags);
		contentMap.put(title, c);
		writeToFile(contentMap);
	}

	// get the filePath for the text file
	public String getFilePath() {
		return filePath;
	}

}
