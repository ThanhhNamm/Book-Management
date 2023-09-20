package controller;

import jdk.internal.access.JavaIOFileDescriptorAccess;
import model.Book;
import model.BookReaderManagement;
import model.Reader;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.*;

public class DataController {

/*
Record information of Book in file
Record information of Reader in file
Record information of Book Management in file

Rule record information:
- information on 1 line
*/

    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;
    private PrintWriter printWriter;
    private Scanner scanner;



    // open file
    public void openFileToWrite(String fileName) throws IOException {

        fileWriter = new FileWriter(fileName,true);
        bufferedWriter = new BufferedWriter(fileWriter);
        printWriter = new PrintWriter(bufferedWriter);

    }

    // close file
    void closeFileAfterWrite(String fileName) throws IOException {

        printWriter.close();
        bufferedWriter.close();
        fileWriter.close();

    }


    public void writeReaderToFile(Reader reader, String fileName) throws IOException {

        openFileToWrite(fileName);

        printWriter.println(
                "Reader: {" + reader.getReaderID() + "," + reader.getFullName() + ","
                        + reader.getAddress() + "," + reader.getPhoneNumber() + "}");

        closeFileAfterWrite(fileName);

    }


    public void writeBookToFile(Book book, String fileName) throws IOException {

        openFileToWrite(fileName);

        printWriter.println(
                book.getBookID() + "|" + book.getBookName() + "|"
                        + book.getAuthor() + "|" + book.getSpecialization() + "|"
                        + book.getPublishYear() + "|" + book.getQuantity());

        closeFileAfterWrite(fileName);

    }

    void writeBrmToFile(BookReaderManagement brm, String fileName) throws IOException {

        openFileToWrite(fileName);

        printWriter.println(
                "Book Reader Management: {" + brm.getBook().getBookID() + ", " + brm.getBook().getBookName() + ", "
                        + brm.getReader().getReaderID() + ", " + brm.getReader().getFullName() + ", "
                        + brm.getState() + ", " + brm.getNumOfBorrow()
                        + "}");

        closeFileAfterWrite(fileName);

    }


    public void openFileToRead(String fileName) throws IOException {
        scanner = new Scanner(Paths.get(fileName),"UTF-8");

    }

    public void closeFileAfterRead(String fileName) {
        scanner.close();
    }

    // Read Reader
    public ArrayList<Reader> readReaderFromFile(String fileName) throws IOException {
        openFileToRead(fileName);

        ArrayList<Reader> readReader = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Reader reader = createReaderFromData(data);
            readReader.add(reader);
        }

        closeFileAfterRead(fileName);
        return readReader;
    }

    private Reader createReaderFromData(String data) {

        String[] dataSplit = data.split("\\,");
        Reader reader = new Reader(Integer.parseInt(dataSplit[0]),dataSplit[1], dataSplit[2], dataSplit[3]);

        return reader;
    }

    // Read Book
    public ArrayList<Book> readBookFromFile(String fileName) throws IOException {
        openFileToRead(fileName);

        ArrayList<Book> readBook = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            Book book = createBookFromData(data);
            readBook.add(book);
        }

        closeFileAfterRead(fileName);
        return readBook;
    }

    private Book createBookFromData(String data) {

        String[] dataSplit = data.split("\\|");
        Book book = new Book(Integer.parseInt(dataSplit[0]),dataSplit[1], dataSplit[2], dataSplit[3],Integer.parseInt(dataSplit[4]),Integer.parseInt(dataSplit[5]));

        return book;
    }

    // Read Book Reader Management
    public ArrayList<BookReaderManagement> readBrmFromFile(String fileName) throws IOException {
        openFileToRead(fileName);

        ArrayList<BookReaderManagement> readBrm = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            BookReaderManagement brm = createBrmFromData(data);
            readBrm.add(brm);
        }

        closeFileAfterRead(fileName);
        return readBrm;
    }

    private BookReaderManagement createBrmFromData(String data) {

        String[] dataSplit = data.split("\\,");
        BookReaderManagement brm = new BookReaderManagement( new Book(Integer.parseInt(dataSplit[0])), new Reader(Integer.parseInt(dataSplit[1])),  Integer.parseInt(dataSplit[2]), dataSplit[3],0);

//        (String book, String reader, String numOfBorrow,
//                String state, int totalBorrowed)

        /*"Book Reader Management: {" + brm.getBook().getBookID() + ", " + brm.getBook().getBookName() + ", "
                + brm.getReader().getReaderID() + ", " + brm.getReader().getFullName() + ", "
                + brm.getState() + ", " + brm.getNumOfBorrow()
                + "}");*/
        return brm;
    }
}
