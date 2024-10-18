# ReadingList
Program designed to allow users to keep track of a list of books and articles using a simply user interface.
## Criterion A: Planning

### Defining the Problem

My brother doesn’t have a good way to keep track of all the content he consumes.  Currently, he reads something (a book or an article), and then after some time he feels as if he loses much of what he learned from that content.  He wants a way to help him retain that information and have it with him if he ever needs it.  After consulting my client (reference Appendix A), I learned that he also would like to track the content he would like to read but hasn’t yet, and the things he is in the middle of.  He often finds himself discovering books he would like to read soon, but then forgetting about them.  This program would help him to overcome that issue.

### Rationale for the Proposed Solution:

The first thing I need is a way to store all of this information.  I’ll need titles, authors, and lengths.  Then, from each of those pieces of content, I’ll need a way to store user notes.  I will also need a way to separate content that has been finished, is in progress, or hasn’t been started and a way to move items between these categories.  Furthermore, I will implement a “tag” system so that the user can separate content by genre, topic, etc. as they please and then search for specific tags.  This will also require me to separate content by type (book or article) in order to track progress in each of the different necessary ways.  In terms of code, I will be using the Java programming language because it is the language I have the most experience with.  I think I will be able to store the bulk of the content using a hashmap with the title as the key and the content object as the value.  I will need GUI in order to allow the user to take notes and annotations, as well as to let them search for items based on titles or tags.  File storage will be necessary to log any notes the user takes.  GUI will also be used so that the user can add items to the list easily and be able to select different categories for that item.  I will be creating all of this in the Visual Studio Code IDE because I have a good understanding of its functions.

### Success Criterion:
1. Users can easily add and remove content from their list.
2. Content can be sorted by type, status, tag, alphabetically by title, alphabetically by author, or length (In pages for books or paragraphs for articles).
3. Content can be inputted as “completed”, “in progress”, or “not started”.
4. Users can take notes on the item during and/or after consummation.
5. Users can search for an item by title, author, or tag.

## Criterion B: Solution Overview

### Development Plan:
- Learn GUI
- Create objects
- Learn data file storage
- Create HashMap and TreeMap(s)
- Determine how to make a tag system
- Determine how to design sorting and filtering systems
- Design GUI
- Implement button functionalities
- Implement file storage for continuous use

### Sketch:
![image14](https://github.com/user-attachments/assets/ba26b33b-0946-4572-ad48-c652fe890c87)


### Initial UML Diagram:
My program will be based around 4 main classes: the runner class, the Content class, and the Book and Article classes that both extend the Content class.

![image9](https://github.com/user-attachments/assets/dfb4776c-5c54-4676-b852-51d9d438258c)


### Updated UML Diagram:

<img width="556" alt="Screenshot 2024-10-18 at 3 34 03 PM" src="https://github.com/user-attachments/assets/106d1f56-0415-4857-91da-51e41d56b02c">

Some get and set methods are included in the code but not shown in the UML diagram.

### Flowchart:
The program will run in a relatively self explanatory manner.  The user will add content, put in as much information as they have available, select the item’s status and tags, take notes, and then edit or remove content at their leisure.
![image10](https://github.com/user-attachments/assets/3e86ba1b-a94d-4cf0-9ab9-639e07bbe899)


### Pseudocode:

File storage methods —

<img width="419" alt="Screenshot 2024-10-18 at 3 37 24 PM" src="https://github.com/user-attachments/assets/a58bd281-36d4-4a5d-a60c-1fd75fed806a">


Sorting algorithm —

<img width="461" alt="Screenshot 2024-10-18 at 3 38 16 PM" src="https://github.com/user-attachments/assets/beb20884-f4f6-4d0a-b1cd-22992f0dd57b">

### Test case chart:

<img width="641" alt="Screenshot 2024-10-18 at 3 36 49 PM" src="https://github.com/user-attachments/assets/6fc3d380-aafe-49bc-8300-1fda8b4cda3f">




## Criterion C: Development

### Class Structure:

![image13](https://github.com/user-attachments/assets/acb6818e-ab89-47cf-bcaf-f44eaef018cf)

The main class for my program is the ContentManagerGUI class.  Within this class I have Content objects which are instantiated either as Book or Article objects.  My FileStorage class is used in the ContentManagerGUI class in order to read and write to my text file: “AllContent.txt”.  The ContentComparator class is used within my sorting method in order to sort objects by a given parameter.  The LengthComp class is used within the ContentComparator class in the case of a comparison by length.

### List of Techniques:
- HashMaps
- TreeMaps
- File Writing and Reading
- Polymorphism
- Encapsulation
- Comparators
- Algorithmic Thinking
- Exception Handling
- Graphical User Interface (GUI) Elements

### HashMaps:
Hashmaps are an advanced data structure (ADT) in the java programming language used to store data in the form of key value pairs.  They are sorted using the hash function.  The main hashmap used in my program is contentHashMap and contains keys representing the title of a piece of content and values which hold content objects (books or articles).  It is used to store the content objects to be displayed by the program and is rewritten to a text file after any changes.  When the program is run, the map is filled using the text file in order to retrieve anything the user made in their last usage.  A hashmap was useful for this because it made it easier to sort through and filter items by having their titles as a key followed by the object.

Class: ContentManagerGUI, line 27

<img width="363" alt="image20" src="https://github.com/user-attachments/assets/d5de0e42-2a61-44e5-8ecc-7a22477a36e8">


Class: ContentManagerGUI, lines 50-60

<img width="503" alt="image4" src="https://github.com/user-attachments/assets/dc5f257e-a7f4-4ce2-b49e-0addc70a0893">


### TreeMaps:
Treemaps are an ADT in java used to store data in the form of keys and values.  They are naturally ordered (ordered alphabetically) which is why I made use of them in my program.  Both my sorting and filtering methods used TreeMaps to rearrange items.  This greatly enhances the efficiency of my program because items are sorted as they enter the map as opposed to being sorted manually.  In my sorting method, I placed items into a TreeMap called sortedmap and used a comparator I wrote to sort them by the given criteria sortBy.  For filtering, I used a treeMap so that the items would be naturally ordered after I manually filtered out unwanted items.

Class: Content, lines 49-53

<img width="836" alt="image8" src="https://github.com/user-attachments/assets/13c4935a-5656-4d82-b1c3-f13193a719b7">


Class: Content, lines 76-93

<img width="760" alt="image5" src="https://github.com/user-attachments/assets/86f640ef-0440-4d64-a067-01133ebdb69e">


### File Writing and Reading:
File reading and writing in Java plays a crucial role in data storage and retrieval. In my program, I utilized Java's file I/O functionalities to read and write information. The text file involved, "AllContent.txt," serves as a database for storing the content map, using a format that allows for easy parsing. When the program is launched, it reads from this file to reconstruct the content map, ensuring that any changes made by the user in previous sessions are preserved. When updates occur, the changes are reflected in the content map and the text file. This approach provides a means to maintain a state between multiple sessions, offering a better user experience.

Class: FileStorage, lines 40-45

<img width="341" alt="image17" src="https://github.com/user-attachments/assets/e5010668-546b-4fc9-9c1b-07bab0a88ce0">


Class: FileStorage, lines 104-115

<img width="691" alt="image12" src="https://github.com/user-attachments/assets/f711b2c8-f2f1-49fd-bf9e-b66385c0b368">



### Polymorphism:
Polymorphism in java enables the treatment of objects of different kinds as objects of a single type. In my program, the "Book" and "Article" subclasses are derived from and extend the class "Content." By utilizing polymorphism, I am able to store these subclass objects in a single data structure, such as a hashmap, which makes managing a variety of content types simple. The editing and display of material are a couple of the program operations that exhibit this polymorphic behavior. Polymorphism considerably improves the program's maintainability and scalability and lays the groundwork for handling a variety of content formats uniformly.

Class: Book, line 2

<img width="260" alt="image2" src="https://github.com/user-attachments/assets/aeac8a91-0bf1-4370-aab4-859f079ccc3b">


Class: Article, line 2

<img width="279" alt="image16" src="https://github.com/user-attachments/assets/e5b5782d-057a-40ee-8d5b-320946e63fa8">


### Encapsulation:
Encapsulation in Java is the process by which data (variables) and the code that acts upon them (methods) are integrated as one class. By encapsulating a class's variables, other classes cannot access them, and only the methods of that class can reach them. In my content management program, encapsulation played a crucial role in organizing data. Each content item is within its respective class, and access to its attributes is controlled through getter and setter methods. For example, the "Content" class encapsulates information such as title, author, and notes. By providing public methods to access these attributes, I maintain control over how the data is manipulated, preventing unintended interference and ensuring data integrity. This facilitates future modifications by containing changes within the relevant class, minimizing impact on the overall system.

Class: Content, lines 124-126

<img width="196" alt="image7" src="https://github.com/user-attachments/assets/58f352c7-2fcc-483f-8b74-1f659c6d1c1c">


Class: Content, lines 150-152

<img width="316" alt="image6" src="https://github.com/user-attachments/assets/110dccca-5585-4e76-bc46-9a6437cb88d2">




### Comparators:
Comparators in Java are objects that provide a way to compare two other objects. In my program, I utilized comparators to implement sorting for the content items. I created a "ContentComparator" class that implements the Comparator interface and handles the comparison logic based on user-defined criteria such as title or author. This allowed me to employ different sorting strategies for the TreeMap data structure used to store content items. By using comparators, I achieved a flexible and interactive design that enables users to dynamically sort and organize their content based on various attributes. This approach enhances the user experience by providing more customization and demonstrates efficient use of the object oriented nature of java.

Class: ContentComparator, line 5

<img width="455" alt="image1" src="https://github.com/user-attachments/assets/fc20b2cd-5695-47d5-8192-c5df8daa3eea">


Class: Content, line 50

<img width="608" alt="image19" src="https://github.com/user-attachments/assets/1e2cbe2b-2426-4719-bf92-52d9a95d6faf">



### Algorithmic Thinking:
Algorithms play an important role in Java programming, offering systematic procedures to solve problems. In my program, algorithms are employed primarily for sorting and filtering content. The sorting algorithm utilizes Java's TreeMap and comparators to arrange content based on user-defined criteria, such as title or status. Additionally, filtering algorithms are implemented to narrow down content based on conditions, enhancing the user's ability to manage information effectively. The careful selection and implementation of algorithms contribute to the program's efficiency, ensuring that tasks are executed in a streamlined manner. This demonstrates the importance of algorithmic thinking in Java, providing solutions that are not only functional but efficient.

Class: ContentManagerGUI, lines 581-613

<img width="808" alt="image18" src="https://github.com/user-attachments/assets/3efc1eb4-13bc-45e4-bce1-869679eee4df">


### Exception Handling:
Java exception handling is fundamental to building robust software. It allows developers to handle unforeseen issues that may occur during program execution. In my program, I implemented it to address potential errors during file input/output operations, user input validation, and other sections of the code. For instance, when reading from a file, I used try-catch blocks to capture and handle FileNotFoundException and IOException, preventing the program from crashing and providing informative messages to the user. Similarly, during user interactions, exception handling ensures that invalid inputs are appropriately managed, offering a smoother user experience. 

Class: FileStorage, lines 116-117

<img width="193" alt="image3" src="https://github.com/user-attachments/assets/2f6bb467-89ba-4111-9216-a9e313aebd6d">


### Graphical User Interface (GUI) Elements:
I employed Java's Swing library to implement GUI components for my program. Swing provides a set of tools for building graphical interfaces. Through the integration of JFrame, JPanel, and other components, I crafted an intuitive and user-friendly interface that facilitates seamless navigation. Actions such as adding and sorting content are initiated through buttons and input fields, allowing users to interact with the application effortlessly. The use of layout managers, such as GridLayout and BorderLayout, ensures an organized display. (Shown on next page)

Image of user interface:

![image15](https://github.com/user-attachments/assets/24f504f7-47c8-40d3-9391-67c056a21267)



## Criterion E: Evaluation
### Evaluation of success criteria:
1. Criteria Met: Users can easily add and remove content from their list.
Users of the program can add and remove content with ease using the “Add Content” and “Remove Content” buttons found at the top of the interface.

2. Criteria Met: Content can be sorted by type, status, tag, alphabetically by title, alphabetically by author, or length (In pages or paragraphs).
By using the sort and filter menus at the top of the screen, users can easily change how much and what times of content are displayed in multiple distinct ways.

3. Criteria Met: Content can be inputted as “completed”, “in progress”, or “not started”.
When adding content to the program, the user is provided with an area to input the status of the given item.

4. Criteria Met: Users can take notes on the item during and/or after consummation.
By selecting an item from the list and then pressing the edit notes button in the top right, the user can easily add notes to any piece of content.

5. Criteria Met: Users can search for an item by title, author, or tag.
By pressing the search icon in the top right, the user can enter a search term and the list will show any items containing that term in the title, author, or a tag.

### Client Feedback:
My client was really happy with how the project turned out.  He liked how the program's interface was kept simple, especially with the usage of buttons and icons to guide users through it.  He can imagine using it consistently in his own future and in the futures of others.

### Recommendations for Further Improvements:
My client and I suggested that a possible future feature would be an archive of previously deleted items that the user could access at any time and retrieve items with ease.
My client proposed that it could be useful to allow a user to input a hyperlink that allows direct access to any article in the program.
Another possible future add-on would be the ability to customize the text of your notes.  This would mean adding bold, underlined, italicized, and other text options as buttons in the program.
My client also thought that it could be useful to allow the user to input an ISBN number and have the fields automatically populate based on that.








## Appendix A: Interview

### Transcript of Interview with the client:
9/13/23

Hey ____, thank you for agreeing to be interviewed here today.

Of course.

Do I have your consent to record and transcribe this interview?

Yeah that's fine.

Ok, so you told me the other day about your struggles with keeping track of what you read.  Could you elaborate on that a little bit?

Yeah, absolutely.  I really have a couple issues that I would like to be solved.  Firstly, I often find myself reading something really intriguing, but then soon forgetting all the things I learned from it.  And my second issue is that I often see books and articles that I would like to read, but don’t have a good place to write them down and because of that I forget about them.

Mhm, I see.  If I were to create a program to solve those problems, what other features would be important for me to implement?

Well, it would be helpful to have a way or multiple ways to sort through all the items I have saved, but I’m not sure how that would work.

Maybe some kind of tag system?  Items could be tagged first by their status, by which I mean read, in progress, or not started, and also by categories such as genre, and type.

Yeah that sounds like a great solution.  And if I could search for specific tags and have a list of only items with those tags appear that would be great as well.

Yeah, that makes sense.  Any other features you would like to have implemented?

I think it would be nice to have a way to keep track of the information I learn from the book or article.  Like a way to take notes as I read and then look back at those notes later.

Ok, so like some kind of text editor within the program that allows you to write your thoughts and observations on each item you read either during or after the reading process.

Yup, exactly.
Any other major features?

It would be good if I could easily edit entries on these notes at any time, and also if I could edit the items stored on my list with ease.

Of course, that would be very helpful.  I think this sounds like a great plan ____.  Are there any features that are less crucial but you might like to see added if I have time?

It would be neat to be able to upload articles and annotate them directly in the program, but that isn’t necessary to what I need.

Oh yeah, that would be an interesting feature.  Is that all?

Yeah that’s it.

Well thank you again for doing this interview with me.

Of course.

Bye, have a nice afternoon.

Bye.

### Transcript of Interview with the client:
12/10/23
Hey ____, thank you for letting me interview you again today.

Yup.

Do I have your consent to record and transcribe this interview?

Yeah that's fine.

I’d like to show you the program I’ve created for you.  We can start by just going through each feature individually.

Sounds good to me.

Ok so first of all, when you run the program all of the books and articles you saved the last time you ran it will appear over here on the left.  On the right is where you will see all the information on the selected item, including any notes you may have taken on it.

That’s nice, I really like the way the page is set up.  It’s not too confusing and it’s simplistic enough to be used easily.

Thank you.  Now up here on the top we have all of our buttons.  As you can see the user has the option to add an item, remove an item, edit an item, sort the items, filter the items, or edit their notes.

That sounds like all the options I requested.

Great!  First off the add button.  When you click it a menu will pop up allowing you to enter all the information you have on the item, as well as if it is a book or an article.  Then you simply hit ok and it is added to the list.

Cool, I like the way that is set up.

Mhm.  And then for the remove button, you just have to select an item, press the button, and then hit ok and it will disappear from the list.  

Very simple, perfect.

Now for the edit button, you first select the item, press edit, and then you can change any piece of information you would like.  When you’re done, hit ok and the information will be changed.

Ok, that’s a very nice feature to have.

I agree.  Now for the sort and filter buttons, all you have to do is choose an option from the list.  You can sort the list by title, author, or status, and you can filter by any status as you can see here.

Mhm, mhm.  That seems to work very well.  Is it possible to sort by length too?

Yes, I forgot about that.  That is also an option.  The final button is the edit notes button.  Just click it and you can enter whatever notes you may have on the item you have selected.

That’s perfect, thank you so much.  I really like the program.

Wonderful!  Are there any other features you would like me to try to implement?

The only other thing I can think of would be a search feature, but that is not completely necessary.  

Ok, I will look into that for the future.  Thank you for agreeing to be interviewed and have a nice evening.

Thank you, you too.

### Transcript of Interview with the client:
2/8/24
Hi ____, thank you for letting me interview you again today.

Mhm.

Do I have your consent to record and transcribe this interview?

Yeah sure.

The program has not changed a ton since I last spoke with you, but I have added a couple features.  First off, we now have a search button here in the top right.

Oh perfect, that’s just what I asked for.

Yup.  And you can see here that if we click it a screen pops up for us to enter our search term.  Using this, the program will automatically search through every title and author in the program and display any items containing the search query.

Oh that’s awesome.  Will it also search for tags?

Oh yes sorry, it will also search through all tags.

Ok perfect.

Mhm.  Next feature here, we have a reset button next to the search button.  All this button does is put every piece of content back into the display list and set all the sort and filter settings back to the default.

Ooooo that’s a good idea, I like it.

Thank you, I thought so too.  The final change I made was changing this option to select your status from a text input to a dropdown list both in the add and edit buttons.  This should just make the program more seamless and easier to use.

Yeah that’s much nicer.  Thank you.

Of course!  That’s all I had to show you today.  Thank you for your time.

Yup. See ya.

Bye.




























## Appendix B: References

Comparator Interface in Java with Examples. (n.d.). Retrieved December 10, 2023, from https://www.geeksforgeeks.org/comparator-interface-java/

Java Program to Determine the class of an object. (n.d.). Retrieved December 10, 2023, from https://www.programiz.com/java-programming/examples/get-class-of-object

Java TM Platform, Standard Edition 21 API Specification. Oracle, docs.oracle.com/javase/21/docs/api/. Accessed 6, March 2023

Minh, N. H. (2019, July 28). How to Read and Write Text File in Java. Retrieved December 10, 2023, from https://www.codejava.net/java-se/file-io/how-to-read-and-write-text-file-in-java

SS IT Solutions. (2015, November 16). Java swing tutorial 35 || jlist getSelectedValue() method || java swing [Video]. YouTube. https://www.youtube.com/watch?v=1WRh4CIrGYM

Traverse Through a HashMap in Java. (n.d.). Retrieved December 10, 2023, from https://www.geeksforgeeks.org/traverse-through-a-hashmap-in-java/

Write HashMap to a Text File in Java. (2020, December 28). Retrieved December 10, 2023, from https://www.geeksforgeeks.org/write-hashmap-to-a-text-file-in-java/


