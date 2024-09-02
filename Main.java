import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(67, 9, 1, 214.82);
        GameProgress gameProgress2 = new GameProgress(100, 6, 5, 244.67);
        GameProgress gameProgress3 = new GameProgress(32, 6, 6, 556.23);

        saveGame("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save1", gameProgress1);
        saveGame("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save2", gameProgress2);
        saveGame("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save3", gameProgress3);
        ArrayList<String> list = new ArrayList<>();
        list.add("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save1.dat");
        list.add("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save2.dat");
        list.add("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save3.dat");
        zipFiles("/Users/ekaterinapyhtina/Desktop/Games/saveGames/zip.zip", list);
        File game1 = new File("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save1.dat");
        File game2 = new File("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save2.dat");
        File game3 = new File("/Users/ekaterinapyhtina/Desktop/Games/saveGames/save3.dat");
        if (game1.delete()) System.out.println("Файл game1 удален");
        if (game2.delete()) System.out.println("Файл game2 удален");
        if (game3.delete()) System.out.println("Файл game3 удален");

    }

    private static void saveGame(String filePath, GameProgress gameProgress) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void zipFiles(String filePath, ArrayList<String> list) {
        try
                (ZipOutputStream zop = new ZipOutputStream(new FileOutputStream(filePath))) {
            for (String arrList : list) {
                try (FileInputStream fis = new FileInputStream(arrList)) {
                    ZipEntry entry = new ZipEntry(arrList);
                    zop.putNextEntry(entry);
                    while (fis.available() > 0) {
                        zop.write(fis.read());
                    }
                    zop.closeEntry();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}






