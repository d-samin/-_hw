package view;

import presenter.Presenter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ConsoleUI implements NoteBookView{
    private Scanner scanner = new Scanner(System.in);
    private boolean exit;
    private Presenter presenter;

    public ConsoleUI(Presenter presenter) {
        this.presenter = presenter;
    }

    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    public String getCommand() {
        System.out.print("Введите команду: ");
        return scanner.nextLine();
    }

    public String getDescriptionInput() {
        System.out.print("Введите описание заметки: ");
        return scanner.nextLine();
    }

    public LocalDateTime getDateTimeInput() {
        System.out.print("Введите дату и время (формат: yyyy-MM-dd HH:mm): ");
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(input, formatter);
    }

    public String getFileNameInput() {
        System.out.print("Введите имя файла: ");
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void start() {
        displayMessage("Добро пожаловать! Выберите команду:\n1. Добавить заметку\n2. Показать все заметки\n3. Показать заметки за день\n4. Показать заметки за неделю\n5. Сохранить заметки в файл\n6. Загрузить заметки из файла\n0. Выход");
        exit = false;

        while (!exit) {
            String command = getCommand();
            switch (command) {
                case "1":
                    presenter.addNote();
                    break;
                case "2":
                    presenter.displayAllNotes();
                    break;
                case "3":
                    presenter.getNotesForDay();
                    break;
                case "4":
                    presenter.getNotesForWeek();
                    break;
                case "5":
                    presenter.saveNotesToFile();
                    break;
                case "6":
                    presenter.loadNotesFromFile();
                    break;
                case "0":
                    exit = true;
                    displayMessage("Программа завершена.");
                    break;
                default:
                    displayMessage("Неверная команда. Попробуйте снова.");
            }
        }
    }
}
