import model.NoteBook;
import model.NoteService;
import view.ConsoleUI;
import presenter.Presenter;


public class Main {
    public static void main(String[] args) {
        NoteBook noteBook = new NoteBook();
        NoteService noteService = new NoteService(noteBook);
        ConsoleUI view = new ConsoleUI(null); 
        Presenter presenter = new Presenter(noteService, view); 
        
        view.setPresenter(presenter);
        view.start();
    }
}


