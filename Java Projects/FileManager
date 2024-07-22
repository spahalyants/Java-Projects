import java.io.*;
import java.util.Scanner;

public class FileManager {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("File Manager Menu:");
            System.out.println("1 - Add folder");
            System.out.println("2 - Add file");
            System.out.println("3 - Delete file");
            System.out.println("4 - Rename file");
            System.out.println("5 - View file");
            System.out.println("6 - Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (choice) {
                case 1:
                    addFolder();
                    break;
                case 2:
                    addFile();
                    break;
                case 3:
                    deleteFile();
                    break;
                case 4:
                    renameFile();
                    break;
                case 5:
                    viewFile();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addFolder() {
        System.out.print("Enter the folder name: ");
        String folderName = scanner.nextLine();
        File folder = new File(folderName);
        if (folder.exists()) {
            System.out.println("Folder already exists.");
        } else {
            if (folder.mkdir()) {
                System.out.println("Folder created successfully.");
            } else {
                System.out.println("Failed to create folder.");
            }
        }
    }

    private static void addFile() {
        System.out.print("Enter the file name: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        if (file.exists()) {
            System.out.println("File already exists.");
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created successfully.");
                } else {
                    System.out.println("Failed to create file.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file.");
                e.printStackTrace();
            }
        }
    }

    private static void deleteFile() {
        System.out.print("Enter the file name to delete: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    private static void renameFile() {
        System.out.print("Enter the current file name: ");
        String currentFileName = scanner.nextLine();
        File currentFile = new File(currentFileName);
        if (currentFile.exists()) {
            System.out.print("Enter the new file name: ");
            String newFileName = scanner.nextLine();
            File newFile = new File(newFileName);
            if (currentFile.renameTo(newFile)) {
                System.out.println("File renamed successfully.");
            } else {
                System.out.println("Failed to rename file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    private static void viewFile() {
        System.out.print("Enter the file name to view: ");
        String fileName = scanner.nextLine();
        File file = new File(fileName);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("File does not exist.");
        }
    }
}