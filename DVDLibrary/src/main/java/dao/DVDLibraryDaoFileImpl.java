package main.java.dao;

import main.java.dto.DVD;

import java.io.*;
import java.util.*;

public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    Map<String, DVD> dvds = new HashMap<>();
    public static final String FILE = "DVDLibraryData.txt";

    public DVD add(String dvdId, DVD dvd) throws DVDLibraryDaoException {
        loadLibrary();
        DVD prevDVD = dvds.put(dvdId, dvd);
        writeLibrary();
        return prevDVD;
    }

    public DVD remove(String dvdId) throws DVDLibraryDaoException {
        loadLibrary();
        DVD removedDvd = dvds.remove(dvdId);
        writeLibrary();
        return removedDvd;
    }

    public List<DVD> getAllDvds() throws DVDLibraryDaoException {
        loadLibrary();
        return new ArrayList<>(dvds.values());
    }

    public DVD getDvd(String dvdId) throws DVDLibraryDaoException {
        loadLibrary();
        return dvds.get(dvdId);
    }

    private DVD unmarshalData(String data) {
        String[] array = data.split("::");
        String id = array[0];
        String title = array[1];
        String rating = array[2];
        String director = array[3];
        String studio = array[4];
        String note = array[5];

        DVD dvdFromFile = new DVD(id);
        dvdFromFile.setTitle(title);
        dvdFromFile.setRating(rating);
        dvdFromFile.setDirector(director);
        dvdFromFile.setStudio(studio);
        dvdFromFile.setNote(note);
        return dvdFromFile;
    }

    private String marshalData(DVD dvd) {
        String dataToFile = String.format("%s::%s::%s::%s::%s::%s", dvd.getId(), dvd.getTitle(), dvd.getRating(), dvd.getDirector(), dvd.getStudio(), dvd.getNote());
        return dataToFile;
    }


    private void loadLibrary() throws DVDLibraryDaoException {
        Scanner scan;
        try {
            scan = new Scanner(new BufferedReader(new FileReader(FILE)));
        } catch (FileNotFoundException e) {
            throw new DVDLibraryDaoException("Could not load library data into memory");
        }

        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            DVD currentDvd = unmarshalData(currentLine);
            dvds.put(currentDvd.getId(), currentDvd);
        }
        scan.close();
    }


    private void writeLibrary() throws DVDLibraryDaoException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(FILE));
        } catch (IOException e) {
            throw new DVDLibraryDaoException("File does not exist", e);
        }

        List<DVD> dvdList = this.getAllDvds();

        for (DVD currentDvd : dvdList) {
            String dvdToFile = marshalData(currentDvd);
            out.println(dvdToFile);
            out.flush();
        }

        out.close();

    }

    public DVD changeRating(String id, String Rating) throws DVDLibraryDaoException {
        loadLibrary();
        DVD dvdToEdit = dvds.get(id);
        dvdToEdit.setRating(Rating);
        writeLibrary();
        return dvdToEdit;
    }


    public DVD changeDirector(String id, String directorName) throws DVDLibraryDaoException {
        loadLibrary();
        DVD dvdToEdit = dvds.get(id);
        dvdToEdit.setDirector(directorName);
        writeLibrary();
        return dvdToEdit;
    }

    public DVD changeStudio(String id, String studioName) throws DVDLibraryDaoException {
        loadLibrary();
        DVD dvdToEdit = dvds.get(id);
        dvdToEdit.setStudio(studioName);
        writeLibrary();
        return dvdToEdit;
    }


}
