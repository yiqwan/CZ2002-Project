package io;

/**
 * This class exists to aid in pointing the java environment to the correct folder which stores the saved/exported data.
 *
 */
public abstract class PathHelper {
    /**
     * Users are to manually adjust the return value within the source code to point to the <code>./src/data/</code> folder within the project directory.
     * THIS EXISTS ONLY AS A HELPER! It is not intended to be a part of the code to be assessed!
     * @return
     */
    public static String path() {
      //FOR ASSESSOR
      //Please edit the return value below to point to the correct folder within your filesystem!
      //Failure to do so might lead to exceptions and unintended presentation.

      //You should point to src/data/ located within the project folder.
      //Example: Replace "ENTER PATH HERE!" with "/Users/name/Downloads/MACS-grp1/src/data/" for Mac
      //Windows example: "C:\\Users\\name\\Downloads\\MACS-grp1\\src\\data\\"
      
      return "";




        // Mac
        //return "/Users/tangyiqwan/compsci/Finalised CZ2002/CZ2002-Assignment5.0/Code/src/data/";
        // Joshua's Mac
        // return "/Users/Joshua/Dropbox/NTU_studies/2021_22_Year_2/Semester_01_Study/CZ2002_Object_Oriented_Desisng_and_Programming/Assignment/2021.11.09.00.00.00/src/data/";
        // SC Windows
        //return "C:\\Users\\danie\\Desktop\\NTU Stuff\\Y2S1\\CZ2002 Object Oriented Design and Programming\\Project\\CZ2002-AssignmentMerge\\src\\data\\";
        //replit
        //return "/chen1411/CZ2002-AssignmentMerge2.1/src/data/";
    }
}
