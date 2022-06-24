package main.java.ui;

import main.java.dto.DVD;

import java.util.List;

public class DVDLibraryView {
//    private UserIO io = new UserIOConsoleImpl();
    private UserIO io;

    public DVDLibraryView(UserIO io){
        this.io = io;
    }



    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. List DVDs");
        io.print("2. Create New DVD");
        io.print("3. View a DVD");
        io.print("4. Remove a DVD");
        io.print("5. Edit a DVD");
        io.print("6. Exit");

        return io.readInt("Please select from the"
                + " above choices.", 1, 6);
    }

    public void displayDisplayAllBanner() {
        io.print("=== Display All Dvds ===");
    }

    public void displayDvdList(List<DVD> dvdList) {
        for (DVD currentDvd : dvdList) {
            String x = String.format("%s, %s, %s", currentDvd.getTitle(), currentDvd.getStudio(), currentDvd.getRating());
            io.print(x);
        }
        io.readString("Please hit enter to continue.");
    }

    public DVD getAddDvdInfo() {
        String id = io.readString("Dvd Id?");
        String title = io.readString("Dvd title?");
        String rating = io.readString("Dvd Rating?");
        String studio = io.readString("Dvd Studio?");
        String director = io.readString("Dvd Director?");
        String note = io.readString("Dvd Note?");

        DVD newDvd = new DVD(id);
        newDvd.setTitle(title);
        newDvd.setRating(rating);
        newDvd.setStudio(studio);
        newDvd.setDirector(director);
        newDvd.setNote(note);

        return newDvd;
    }

    public void displayAddBanner() {
        io.print("=== Add DVD ===");
    }

    public void displayShowBanner() {
        io.print("=== Show DVD ===");
    }

    public String getRemoveDvdInfo() {
        String dvdId = io.readString("Give the dvd id of the dvd you would like to delete.");
        return dvdId;
    }

    public String getDvdInfo() {
        String dvdId = io.readString("Give the dvd id of the dvd you would like to view");
        return dvdId;
    }

    public void displayRemoveBanner() {
        io.print("=== Remove DVD ===");
    }

    public void displayRemovedDvd(DVD removedDvd) {
        if (removedDvd != null) {
            io.print("Successfully removed dvd");
        } else {
            io.print("No such Dvd");
        }

        io.readString("Press enter to continue");
    }

    public void displayRequestedDvd(DVD dvd) {
        if (dvd != null) {
            String x = String.format("This DVD is %s, it is rated %s and the director is %s", dvd.getTitle(), dvd.getRating(), dvd.getDirector());
            io.print(x);
        } else {
            io.print("DVD doesnt exist");
        }

        io.print("Please hit enter to continue");
    }


    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }


    public int printEditMenuAndGetSelection() {
        io.print("Which field do you want to change?");
        io.print("Edit DVD menu");
        io.print("1. rating");
        io.print("2. Director's name");
        io.print("3. Studio name");
        io.print("4. Exit edit menu");
        return io.readInt("Please select from the above choices.", 1,6);
    }


    public void displayEditMessage() {
        io.print("=== Edit ===");

    }

    public String getDirector() {
        return io.readString("Please enter the new director's name.");
    }

    public String getRating() {
        return io.readString("Please enter the new user rating.");
    }

    public String getStudio() {
        return io.readString("Please enter the studio name.");
    }

    public void displayEditResult(){
        io.print("DVD Successfully edited.");
    }


    public void displayEditDvdBanner() {
        io.print("=== Edit DVD ===");

    }

    public void displayNullDvd(){
        io.print("No such DVD");
    }

    public String getDvdIdChoice() {
        return io.readString("Please enter the DVD ID.");
    }

    public void displayDvd(DVD dvd) {
        if (dvd != null) {
            io.print("=== "+ dvd.getTitle()+" Summary ===");
            io.print("Title: " + dvd.getTitle());
            io.print("MPAA rating: " + dvd.getRating());
            io.print("Director's name: " + dvd.getDirector());
            io.print("Studio: "+ dvd.getStudio());
        } else {
            io.print("No such DVD");
        }
        io.readString("Please hit enter to continue.");
    }


}
