import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class ContentManagerGUI extends JFrame {
	/*
	 * Builds the graphical user interface for the program
	 */

	private HashMap<String, TreeMap<String, Content>> contentMap;
	private JList<String> contentList;
	private JTextArea notesTextArea;

	private final Color backgroundMain = Color.decode("#222629"); // Darker gray
	private final Color textColor = Color.decode("#F3F5F6"); // White
	private final Color backgroundSecondary = Color.decode("#6B6E70"); // Lighter gray
	private final Color accentMain = Color.decode("#BDD7DB"); // Light Blue

	private HashMap<String, Content> contentHashMap;

	private Content run = new Content();

	private FileStorage store;

	JComboBox<String> filterComboBox = new JComboBox<>(
			new String[] { "All", "Completed", "In Progress", "Not Started", "Books", "Articles" });
	JComboBox<String> sortComboBox = new JComboBox<>(
			new String[] { "Title", "Author", "Status", "Length" });

	public ContentManagerGUI() {

		TreeMap<String, Content> InProgressItems = new TreeMap<String, Content>();
		TreeMap<String, Content> CompletedItems = new TreeMap<String, Content>();
		TreeMap<String, Content> NotStartedItems = new TreeMap<String, Content>();

		contentMap = new HashMap<String, TreeMap<String, Content>>();

		contentMap.put("In Progress", InProgressItems);
		contentMap.put("Not Started", NotStartedItems);
		contentMap.put("Completed", CompletedItems);

		contentHashMap = new HashMap<String, Content>();

		store = new FileStorage(contentHashMap, "/AllContent.txt");

		if (contentHashMap.isEmpty()) {
			contentHashMap.putAll(contentMap.get("In Progress"));
			contentHashMap.putAll(contentMap.get("Not Started"));
			contentHashMap.putAll(contentMap.get("Completed"));
		}

		contentHashMap = store.readFromFile();

		// Set up GUI components
		contentList = new JList<>();
		contentList.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 16));
		contentList.setBackground(backgroundMain);

		refreshContentList(contentHashMap);

		notesTextArea = new JTextArea();
		notesTextArea.setEditable(false);
		notesTextArea.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 16));
		// Insets param goes top, left, bottom, right
		notesTextArea.setMargin(new Insets(7, 10, 10, 10));
		notesTextArea.setBackground(backgroundMain);
		notesTextArea.setForeground(textColor);

		JButton addButton = new JButton("Add Content");
		JButton removeButton = new JButton("Remove Content");
		JButton editButton = new JButton("Edit Content");

		addButton.setForeground(backgroundMain);
		addButton.setOpaque(true);
		addButton.setBorderPainted(false);
		addButton.setBackground(backgroundSecondary);
		addButton.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 17));
		addButton.setPreferredSize(new Dimension(148, 40));
		addButton.setFocusPainted(false);
		addButton.setRolloverEnabled(true);
		addButton.setToolTipText("Click to add content");
		addButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse enters the button
				addButton.setBackground(accentMain);
				Font originalFont = addButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize() - 1);
				addButton.setFont(newFont);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse exits the button
				addButton.setBackground(backgroundSecondary);
				Font originalFont = addButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize() + 1);
				addButton.setFont(newFont);
			}
		});

		removeButton.setForeground(backgroundMain);
		removeButton.setOpaque(true);
		removeButton.setBorderPainted(false);
		removeButton.setBackground(backgroundSecondary);
		removeButton.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 17));
		removeButton.setPreferredSize(new Dimension(178, 40));
		removeButton.setFocusPainted(false);
		removeButton.setToolTipText("Select content and then click this to remove");
		removeButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse enters the button
				removeButton.setBackground(accentMain);
				Font originalFont = removeButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize() - 1);
				removeButton.setFont(newFont);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse exits the button
				removeButton.setBackground(backgroundSecondary);
				Font originalFont = removeButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize() + 1);
				removeButton.setFont(newFont);
			}
		});

		editButton.setForeground(backgroundMain);
		editButton.setOpaque(true);
		editButton.setBorderPainted(false);
		editButton.setBackground(backgroundSecondary);
		editButton.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 17));
		editButton.setPreferredSize(new Dimension(145, 40));
		editButton.setFocusPainted(false);
		editButton.setToolTipText("Select content and then click this to edit");
		editButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse enters the button
				editButton.setBackground(accentMain);
				Font originalFont = editButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize() - 1);
				editButton.setFont(newFont);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse exits the button
				editButton.setBackground(backgroundSecondary);
				Font originalFont = editButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize() + 1);
				editButton.setFont(newFont);
			}
		});

		sortComboBox.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 16));
		sortComboBox.setPreferredSize(new Dimension(100, 40));
		sortComboBox.setForeground(backgroundMain);
		sortComboBox.setOpaque(false);
		sortComboBox.setRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList list,
					Object value,
					int index,
					boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				if (isSelected)
					c.setBackground(backgroundSecondary);
				else
					c.setBackground(textColor);

				return c;
			}
		});

		filterComboBox.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 16));
		filterComboBox.setPreferredSize(new Dimension(150, 60));
		filterComboBox.setForeground(backgroundMain);
		filterComboBox.setRenderer(new DefaultListCellRenderer() {
			public Component getListCellRendererComponent(JList list,
					Object value,
					int index,
					boolean isSelected,
					boolean cellHasFocus) {
				Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

				if (isSelected)
					c.setBackground(backgroundSecondary);
				else
					c.setBackground(textColor);

				return c;
			}
		});

		JScrollPane jspList = new JScrollPane(contentList);
		jspList.setOpaque(true);
		jspList.setBackground(backgroundMain);
		jspList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JScrollPane jspNotes = new JScrollPane(notesTextArea);
		jspNotes.setOpaque(true);
		jspNotes.setBackground(backgroundMain);

		// Set layout
		setLayout(new BorderLayout());

		// Create panels
		JPanel topPanel = new JPanel();
		topPanel.setBackground(backgroundMain);

		JPanel middlePanel = new JPanel(new GridLayout(1, 3));
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, jspList, jspNotes);
		splitPane.setResizeWeight(0.4);
		splitPane.setDividerSize(5);
		splitPane.setBackground(backgroundSecondary);
		middlePanel.add(splitPane);
		middlePanel.setBackground(backgroundSecondary);
		middlePanel.setOpaque(true);
		middlePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(backgroundMain);
		bottomPanel.setPreferredSize(new Dimension(getWidth(), 15));

		JLabel sortLabel = new JLabel("Sort by:");
		sortLabel.setFont(new Font("Courier", Font.PLAIN, 17));
		sortLabel.setForeground(textColor);

		JLabel filterLabel = new JLabel("Filter by:");
		filterLabel.setFont(new Font("Courier", Font.PLAIN, 17));
		filterLabel.setForeground(textColor);

		// Add components to panels
		topPanel.add(addButton);
		topPanel.add(removeButton);
		topPanel.add(editButton);
		topPanel.add(sortLabel);
		topPanel.add(sortComboBox);
		topPanel.add(filterLabel);
		topPanel.add(filterComboBox);

		JButton editNotesButton = new JButton("Edit Notes");
		topPanel.add(editNotesButton);
		editNotesButton.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 17));
		editNotesButton.setPreferredSize(new Dimension(127, 40));
		editNotesButton.setForeground(backgroundMain);
		editNotesButton.setBorderPainted(false);
		editNotesButton.setOpaque(true);
		editNotesButton.setBackground(backgroundSecondary);
		editNotesButton.setFocusPainted(false);
		editNotesButton.setToolTipText("Select content and then click this to edit your notes");
		editNotesButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse enters the button
				editNotesButton.setBackground(accentMain);
				Font originalFont = editNotesButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize() - 1);
				editNotesButton.setFont(newFont);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse exits the button
				editNotesButton.setBackground(backgroundSecondary);
				Font originalFont = editNotesButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize() + 1);
				editNotesButton.setFont(newFont);
			}
		});

		JButton searchButton = new JButton("\uD83D\uDD0D");
		searchButton.setOpaque(true);
		searchButton.setBorderPainted(false);
		searchButton.setBackground(backgroundSecondary);
		searchButton.setForeground(backgroundMain);
		searchButton.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 30));
		searchButton.setPreferredSize(new Dimension(75, 40));
		searchButton.setFocusPainted(false);
		searchButton.setRolloverEnabled(true);
		searchButton.setToolTipText("Click to add content");
		searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse enters the button
				searchButton.setBackground(accentMain);
				Font originalFont = searchButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize() - 1);
				searchButton.setFont(newFont);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse exits the button
				searchButton.setBackground(backgroundSecondary);
				Font originalFont = searchButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize() + 1);
				searchButton.setFont(newFont);
			}
		});
		topPanel.add(searchButton);

		JButton resetButton = new JButton("RESET");
		resetButton.setOpaque(true);
		resetButton.setBorderPainted(false);
		resetButton.setBackground(backgroundSecondary);
		resetButton.setForeground(backgroundMain);
		resetButton.setFont(new Font("Proxima Nova Semibold", Font.PLAIN, 17));
		resetButton.setPreferredSize(new Dimension(105, 40));
		resetButton.setFocusPainted(false);
		resetButton.setRolloverEnabled(true);
		resetButton.setToolTipText("Click to add content");
		resetButton.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse enters the button
				resetButton.setBackground(accentMain);
				Font originalFont = resetButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.BOLD, originalFont.getSize() - 1);
				resetButton.setFont(newFont);
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				// Code to execute when mouse exits the button
				resetButton.setBackground(backgroundSecondary);
				Font originalFont = resetButton.getFont();
				Font newFont = new Font(originalFont.getName(), Font.PLAIN, originalFont.getSize() + 1);
				resetButton.setFont(newFont);
			}
		});
		topPanel.add(resetButton);

		contentList.setFixedCellHeight(30);
		contentList.setSelectionBackground(accentMain);
		contentList.setSelectionForeground(backgroundMain);
		contentList.setForeground(textColor);

		// Add panels to the layout
		add(topPanel, BorderLayout.NORTH);
		add(middlePanel, BorderLayout.CENTER);
		add(bottomPanel, BorderLayout.SOUTH);

		// Set selection mode to single selection
		contentList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Set a default selection
		if (contentList.getModel().getSize() > 0) {
			contentList.setSelectedIndex(0);
			String selectedTitle = contentList.getSelectedValue();

			if (selectedTitle != null) {
				// Check if the selected title is present in the content map
				Content selectedContent = contentHashMap.get(selectedTitle);

				if (selectedContent != null) {
					// Logic for handling non-null selectedContent
					notesTextArea.setText("Title: " + selectedContent.getTitle() + "\nAuthor: "
							+ selectedContent.getAuthor() + "\nStatus: " + selectedContent.getStatus());

					if (selectedContent instanceof Book) {
						notesTextArea.append("\nLength: " + ((Book) selectedContent).getLength() + " pages");
					} else if (selectedContent instanceof Article) {
						notesTextArea.append("\nLength: " + ((Article) selectedContent).getLength() + " words");
					}

					notesTextArea.append("\nTags: ");
					ArrayList<String> tags = selectedContent.getTags();

					if (!tags.isEmpty()) {
						notesTextArea.append(tags.get(0).trim());

						for (int i = 1; i < tags.size(); i++) {
							notesTextArea.append(", " + tags.get(i).trim());
						}
					}

					notesTextArea.append("\nNotes on this content: \n" + selectedContent.getNotes());
				} else {
					// Handle case where selectedTitle is null
					notesTextArea.setText("Invalid category or title selected.");
				}
			}
		} else {
			// No item is selected, display a message or take appropriate action
			// System.out.println("No item selected.");
		}

		// Add an event listener to handle selection changes
		contentList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedIndex = contentList.getSelectedIndex();
					if (selectedIndex != -1) {
						// Get the selected content title
						String selectedTitle = contentList.getSelectedValue();

						if (selectedTitle != null) {
							// Check if the selected title is present in the content map
							Content selectedContent = contentHashMap.get(selectedTitle);

							if (selectedContent != null) {
								// Logic for handling non-null selectedContent
								notesTextArea.setText("Title: " + selectedContent.getTitle() + "\nAuthor: "
										+ selectedContent.getAuthor() + "\nStatus: " + selectedContent.getStatus());

								if (selectedContent instanceof Book) {
									notesTextArea
											.append("\nLength: " + ((Book) selectedContent).getLength() + " pages");
								} else if (selectedContent instanceof Article) {
									notesTextArea.append(
											"\nLength: " + ((Article) selectedContent).getLength() + " words");
								}

								notesTextArea.append("\nTags: ");
								ArrayList<String> tags = selectedContent.getTags();

								if (!tags.isEmpty()) {
									notesTextArea.append(tags.get(0).trim());

									for (int i = 1; i < tags.size(); i++) {
										notesTextArea.append(", " + tags.get(i).trim());
									}
								}

								notesTextArea.append("\nNotes on this content: \n" + selectedContent.getNotes());
							} else {
								// Handle case where selectedCategory or selectedTitle is null
								notesTextArea.setText("Invalid category or title selected.");
							}
						}
					} else {
						// No item is selected, display a message or take appropriate action
						// System.out.println("No item selected.");
					}
				}
			}
		});

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JTextField titleField = new JTextField();
				JTextField authorField = new JTextField();
				JTextField tagsField = new JTextField();
				JComboBox<String> progressComboBox = new JComboBox<>(
						new String[] { "Not Started", "In Progress", "Completed" });
				JTextField lengthField = new JTextField();

				JComboBox<String> typeComboBox = new JComboBox<>(new String[] { "Book", "Article" });

				titleField.setColumns(20);

				progressComboBox.setSelectedItem("Not Started");

				Object[] message = { "Title:", titleField, "Author:", authorField, "Tags (comma-separated):", tagsField,
						"Type:", typeComboBox, "Status:", progressComboBox, "Length:", lengthField };

				int option = JOptionPane.showConfirmDialog(null, message, "Add Content", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					// Retrieve user inputs
					String title = titleField.getText();
					title = title.replaceAll(",", "");
					String author = authorField.getText();
					String[] tags = tagsField.getText().split(",");
					String status = (String) progressComboBox.getSelectedItem();
					int length = Integer.parseInt(lengthField.getText());
					String selectedType = (String) typeComboBox.getSelectedItem();

					// Trim each tag to remove leading and trailing spaces
					for (int i = 0; i < tags.length; i++) {
						tags[i] = tags[i].trim();
					}

					// Validate inputs
					if (title.isEmpty() || author.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Title and author are required fields.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Check if the content with the same title already exists in the map
					if (contentHashMap.containsKey(title)) {
						JOptionPane.showMessageDialog(null, "Content with the same title already exists.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}

					// Update the content file
					store.addContentToFile(title, author, status, tags, selectedType, length);
					contentHashMap = store.readFromFile();

					// Refresh the content list for the "Not Started" category
					refreshContentList(contentHashMap);
				}
			}
		});

		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = contentList.getSelectedIndex();

				if (selectedIndex != -1) {
					// Get the selected content title
					String selectedTitle = contentList.getSelectedValue();

					Content selectedContent = contentHashMap.get(selectedTitle);

					// Confirm removal with a dialog
					int option = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to remove \"" + selectedContent.getTitle() + "\"?", "Remove Content",
							JOptionPane.YES_NO_OPTION);

					if (option == JOptionPane.YES_OPTION) {
						// Remove the content from the map
						contentHashMap.remove(selectedTitle);
						System.out.println(selectedContent);

						store.writeToFile(contentHashMap);
						contentHashMap = store.readFromFile();

						// Refresh the content list for the selected category
						refreshContentList(contentHashMap);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select an item to remove.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = contentList.getSelectedIndex();

				if (selectedIndex != -1) {
					// Get the selected category and content title
					String selectedTitle = contentList.getSelectedValue();

					// Retrieve the content object from the map
					Content selectedContent = contentHashMap.get(selectedTitle);

					// Create a dialog for editing content
					JTextField titleField = new JTextField(selectedContent.getTitle());
					JTextField authorField = new JTextField(selectedContent.getAuthor());
					JTextField tagsField = new JTextField(String.join(", ", selectedContent.getTags()));
					JComboBox<String> progressComboBox = new JComboBox<>(
							new String[] { "Completed", "In Progress", "Not Started" });
					JTextField lengthField = new JTextField(Integer.toString(selectedContent.getLength()));

					progressComboBox.setSelectedItem(selectedContent.getStatus());

					titleField.setColumns(20);

					Object[] message = { "Title:", titleField, "Author:", authorField, "Tags (comma-separated):",
							tagsField, "Status (In Progress, Completed, or Not Started):", progressComboBox, "Length:",
							lengthField };

					int option = JOptionPane.showConfirmDialog(null, message, "Edit Content",
							JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						// Retrieve edited user inputs
						String editedTitle = titleField.getText();
						String editedAuthor = authorField.getText();
						String[] editedTags = tagsField.getText().split(",");
						String editedStatus = (String) progressComboBox.getSelectedItem();
						int editedLength = Integer.parseInt(lengthField.getText());

						// Trim each tag to remove leading and trailing spaces
						for (int i = 0; i < editedTags.length; i++) {
							editedTags[i] = editedTags[i].trim();
						}

						// Validate edited inputs
						if (editedTitle.isEmpty() || editedAuthor.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Title and author are required fields.", "Error",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

						// Update the content object with edited information
						selectedContent.setTitle(editedTitle);
						selectedContent.setAuthor(editedAuthor);
						selectedContent.setTags(new ArrayList<>(Arrays.asList(editedTags)));
						selectedContent.setStatus(editedStatus);
						selectedContent.setLength(editedLength);

						// Update cMapAsHash
						contentHashMap.remove(selectedTitle);
						contentHashMap.put(editedTitle, selectedContent);

						store.writeToFile(contentHashMap);
						contentHashMap = store.readFromFile();

						// Refresh the content list
						refreshContentList(contentHashMap);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select an item to edit.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		sortComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String sortBy = (String) sortComboBox.getSelectedItem();

				if (sortBy != null) {

					// Call the sort method on cMapAsHash
					TreeMap<String, Content> sortedMap = run.sort(contentHashMap, sortBy);

					// Refresh the content list directly
					refreshContentList(sortedMap);
				}
			}
		});

		filterComboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Get the filtering type
				String filterBy = (String) filterComboBox.getSelectedItem();

				if (filterBy != null) {
					if (filterBy.equals("All")) {
						refreshContentList(contentHashMap);
					} else {
						// Filter the map and refresh
						TreeMap<String, Content> filteredMap = run.filter(new TreeMap<String, Content>(contentHashMap),
								filterBy);
						refreshContentList(new TreeMap<>(filteredMap));
					}
				}
			}
		});

		editNotesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedIndex = contentList.getSelectedIndex();

				if (selectedIndex != -1) {
					// Get the selected content title
					String selectedTitle = contentList.getSelectedValue();

					// Retrieve the content object from the map
					Content selectedContent = contentHashMap.get(selectedTitle);

					// Create a dialog for editing notes
					JTextArea notesField = new JTextArea(selectedContent.getNotes());
					notesField.setColumns(50);
					notesField.setRows(10);

					Object[] message = { "Notes:", new JScrollPane(notesField) };

					int option = JOptionPane.showConfirmDialog(null, message, "Edit Notes",
							JOptionPane.OK_CANCEL_OPTION);
					if (option == JOptionPane.OK_OPTION) {
						// Retrieve edited notes
						String editedNotes = notesField.getText();

						// Update the content object with edited notes
						selectedContent.setNotes(editedNotes);

						// Update the cMapAsHash
						contentHashMap.put(selectedTitle, selectedContent);

						// Update the file
						store.writeToFile(contentHashMap);
						contentHashMap = store.readFromFile();

						// Refresh the content list
						refreshContentList(contentHashMap);
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please select an item to edit notes.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		searchButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JTextField searchField = new JTextField();

				searchField.setColumns(20);

				Object[] message = { "Enter a title, author, or tag", "Search Term:", searchField };

				int option = JOptionPane.showConfirmDialog(null, message, "Add Content", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					String searchTerm = searchField.getText();
					if (searchTerm.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please enter a search term.", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						// Call the search method on cMapAsHash
						HashMap<String, Content> titleSearchResults = run.searchByTitle(searchTerm, contentHashMap);
						HashMap<String, Content> authorSearchResults = run.searchByAuthor(searchTerm, contentHashMap);
						HashMap<String, Content> tagSearchResults = run.searchByTag(searchTerm, contentHashMap);

						HashMap<String, Content> searchResults = new HashMap<>();
						searchResults.putAll(titleSearchResults);
						searchResults.putAll(authorSearchResults);
						searchResults.putAll(tagSearchResults);

						// Refresh the content list directly
						refreshContentList(searchResults);
					}
				}
			}
		});

		resetButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refreshContentList(contentHashMap);
				sortComboBox.setSelectedItem("Title");
				filterComboBox.setSelectedItem("All");
			}
		});

		// Set JFrame properties
		setTitle("Content Manager");
		setSize(1600, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	private void refreshContentList(Map<String, Content> map) {
		DefaultListModel<String> listModel = new DefaultListModel<>();

		// Store the selected item
		contentList.setSelectedIndex(contentList.getSelectedIndex());
		String selectedTitle = contentList.getSelectedValue();

		// Populate the model directly from the Map
		for (Map.Entry<String, Content> entry : map.entrySet()) {
			listModel.addElement(entry.getKey());
		}

		// Set the custom renderer
		contentList.setCellRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,
						cellHasFocus);

				// Customize how each item is displayed
				Content content = map.get(value);

				label.setText(getFormattedContentText(content));
				return label;
			}

			private String getFormattedContentText(Content content) {
				if (content.getClassOfObj().equals("Book"))
					return String.format("%s by %s -- %s -- length is %s pages", content.getTitle(),
							content.getAuthor(),
							content.getStatus(), content.getLength());

				if (content.getClassOfObj().equals("Article"))
					return String.format("%s by %s -- %s -- length is %s words", content.getTitle(),
							content.getAuthor(),
							content.getStatus(), content.getLength());

				return String.format("%s by %s -- %s -- length is %s", content.getTitle(), content.getAuthor(),
						content.getStatus(), content.getLength());
			}
		});

		// Set the updated model
		contentList.setModel(listModel);

		// Set the selected item back
		if (selectedTitle != null && listModel.contains(selectedTitle)) {
			contentList.setSelectedValue(selectedTitle, true);
		}
	}

	// main method for the program
	public static void main(String[] args) {
		new ContentManagerGUI().setVisible(true);
	}
}
