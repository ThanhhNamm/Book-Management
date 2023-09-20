package view;

import controller.DataController;
import model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class View {
    public static void main (String args[]) throws IOException {
        int choice = 0;
        var bookFileName = "BOOK.DAT";
        var controller = new DataController();
        var books = new ArrayList<Book>();


        Scanner scan = new Scanner(System.in);
        do {

            System.out.println("Menu");
            System.out.println("1. Add new book in file");
            System.out.println("2. Show all book in file");
            System.out.println("0. Exit");
            System.out.println("Please choose: ");

            choice = scan.nextInt();
            scan.nextLine();

            switch (choice) {
                case 0:
                    System.out.println("---------------------------");
                    System.out.println("Thank you and See you later");
                    break;
                case 1:

                    String[] specialization = {"Sienece", "Mathetic", "English"};

                    String bookname, author, specs;
                    int publishYear, quantity, sp;



                    System.out.println("Enter name of book: ");
                    bookname = scan.nextLine();

                    System.out.println("Enter name of author: ");
                    author = scan.nextLine();

                    do {
                        System.out.println("Enter specialization: ");
                        System.out.println("1. Sience\n2. Mathetic\n3. English");
                        sp = scan.nextInt();
                    } while (sp < 1 && sp > 3);

                    specs = specialization[sp - 1];

                    System.out.println("Enter year publish: ");
                    publishYear = scan.nextInt();

                    System.out.println("Enter quantity: ");
                    quantity = scan.nextInt();


                    /*public Book(int bookID, String bookName, String author,
                        String specialization, int publishYear, int quantity) {
                    this.bookID = bookID;*/
                    Book book = new Book(0, bookname, author, specs, publishYear, quantity);
                    controller.writeBookToFile(book, bookFileName);
                    break;


                case 2:
                    books = controller.readBookFromFile(bookFileName);
                    showBookInfor(books);
                    break;
            }

        } while (choice != 0);

    }

    private static void showBookInfor(ArrayList<Book> books) {
        System.out.println(" --------- Information of books in file ---------");
        for (var b : books ) {
            System.out.println(b);
        }
    }
}
