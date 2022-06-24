package main.java;

import main.java.controller.DVDLibraryController;
import main.java.dao.DVDLibraryDao;
import main.java.dao.DVDLibraryDaoException;
import main.java.dao.DVDLibraryDaoFileImpl;
import main.java.ui.DVDLibraryView;
import main.java.ui.UserIO;
import main.java.ui.UserIOConsoleImpl;

public class App {
    public static void main(String[] args) throws DVDLibraryDaoException {
        UserIO myIo = new UserIOConsoleImpl();
        DVDLibraryView myView = new DVDLibraryView(myIo);
        DVDLibraryDao myDao = new DVDLibraryDaoFileImpl();
        DVDLibraryController controller = new DVDLibraryController(myView,myDao);
        controller.run();
    }
}
