### Guidelines to running the application

1. Our main method is contained in the RestaurantApp.java file within the src/default package. 

2. We use CSV files to store and retrieve data when the app is closed.

3. Before running the main program, please edit the filepaths within .../src/io/PathHelper.java to point to the .../src/data/ folder.

    - For example, if you are running the program on a Mac, you would go into the PathHelper.java and change the return value to "/Users/<NAME>/Downloads/MACS-grp1/src/data/".

    - For windows, you would change it to "C:\\Users\\name\\Downloads\\MACS-grp1\\src\\data\\".

    - Remember the ending slashes! ".../src/data" wouldnt work, but ".../src/data/" will!

    - Failure to do so might lead to unintended outcomes! The app will still work, but it will throw exceptions for the first time it is run and closed. Furthermore, the menu, reservations, and the revenue history will be blank.

4. To save the changes to the csv files, the app needs to be exited properly from the main page by entering (0) exit. Terminating the terminal in the middle of the process would not save any changes.

### Javadoc

1. All the related files are saved within the "doc" folder

2. Click on the index.html file to open in your browser
