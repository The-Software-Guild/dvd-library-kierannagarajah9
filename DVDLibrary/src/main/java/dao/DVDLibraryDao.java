package main.java.dao;

import main.java.dto.DVD;

import java.util.ArrayList;
import java.util.List;

public interface DVDLibraryDao {

    DVD add(String dvdId, DVD dvd) throws DVDLibraryDaoException;

    DVD remove(String dvdId) throws DVDLibraryDaoException;

    List<DVD> getAllDvds() throws DVDLibraryDaoException;

    DVD getDvd(String dvdId) throws DVDLibraryDaoException;

    DVD changeRating(String id, String newRating) throws DVDLibraryDaoException;

    DVD changeDirector(String id, String newDirector) throws DVDLibraryDaoException;

    DVD changeStudio(String title, String newStudio) throws DVDLibraryDaoException;
}
