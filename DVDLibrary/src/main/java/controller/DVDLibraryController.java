package main.java.controller;

import main.java.dao.DVDLibraryDao;
import main.java.dao.DVDLibraryDaoException;
import main.java.dao.DVDLibraryDaoFileImpl;
import main.java.dto.DVD;
import main.java.ui.DVDLibraryView;
import main.java.ui.UserIO;
import main.java.ui.UserIOConsoleImpl;

import java.util.List;

public class DVDLibraryController {
//    private DVDLibraryView view = new DVDLibraryView();
    private DVDLibraryView view;
//    private DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
    private DVDLibraryDao dao;


    public DVDLibraryController(DVDLibraryView view,DVDLibraryDao dao){
        this.view = view;
        this.dao = dao;
    }


    public void run() throws DVDLibraryDaoException {
        Boolean keepGoing = true;

        try {
            while (keepGoing) {
                int choice = getMenuSelection();

                switch (choice) {
                    case 1:
                        listDvds();
                        break;
                    case 2:
                        addDvd();
                        break;
                    case 3:
                        showDvd();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        editDvd();
                        break;
                    case 6:
                        keepGoing = false;
                        break;
                }
            }
            }catch (DVDLibraryDaoException e) {
                view.displayErrorMessage(e.getMessage());

            }

        }




    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    public void listDvds() throws DVDLibraryDaoException {
        view.displayDisplayAllBanner();
        List<DVD> dvdList = dao.getAllDvds();
        view.displayDvdList(dvdList);
    }

    public void addDvd() throws DVDLibraryDaoException {
        view.displayAddBanner();
        DVD newDvd = view.getAddDvdInfo();
        dao.add(newDvd.getId(), newDvd);
    }

    public void removeDvd() throws DVDLibraryDaoException {
        view.displayRemoveBanner();
        String dvdId = view.getRemoveDvdInfo();
        DVD removedDvd = dao.remove(dvdId);
        view.displayRemovedDvd(removedDvd);
    }

    public void showDvd() throws DVDLibraryDaoException {
        view.displayShowBanner();
        String dvdId = view.getDvdInfo();
        DVD dvd = dao.getDvd(dvdId);
        view.displayRequestedDvd(dvd);

    }

    private void editDvd() throws DVDLibraryDaoException {
        view.displayEditDvdBanner();
        String id = view.getDvdIdChoice();
        DVD dvdToEdit = dao.getDvd(id);
        if (dvdToEdit==null) {
            view.displayNullDvd();
        } else {
            view.displayDvd(dvdToEdit);
            int editMenuSelection = 0;
            boolean keepGoing = true;

            while (keepGoing) {
                editMenuSelection = view.printEditMenuAndGetSelection();

                switch (editMenuSelection){
                    case 1:
                        editRating(id);
                        break;
                    case 2:
                        editDirector(id);
                        break;
                    case 3:
                        editStudio(id);
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                }
                if (keepGoing == false) {
                    break;
                }
            }
        }
    }



    private void editRating(String id) throws DVDLibraryDaoException {
        //view.displayEditMpaaRatingBanner();
        String newRating = view.getRating();
        DVD editedDvd = dao.changeRating(id, newRating);
        view.displayEditResult();
    }
    private void editDirector(String id) throws DVDLibraryDaoException {
        //view.displayEditDirectorNameBanner();
        String newDirector = view.getDirector();
        DVD editedDvd = dao.changeDirector(id, newDirector);
        view.displayEditResult();
    }


    private void editStudio(String title) throws DVDLibraryDaoException {
        String newStudio = view.getStudio();
        DVD editedDvd = dao.changeStudio(title, newStudio);
        view.displayEditResult();
    }



}


