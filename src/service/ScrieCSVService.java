package service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ScrieCSVService {
    private static ScrieCSVService instanta = null;

    private ScrieCSVService() {}

    public static ScrieCSVService getInstance() {
        if (instanta == null)
            instanta = new ScrieCSVService();
        return instanta;
    }

    public void write(String path, List<String> lista){

        File destinatie = new File(path);
        try {
            if (!destinatie.exists()) {
                destinatie.createNewFile();
            }

            FileWriter w = new FileWriter(destinatie,true);
            StringBuilder mesaj = new StringBuilder();
            for (String l : lista) {
                mesaj.append(l);
                mesaj.append(",");
            }
            mesaj.deleteCharAt(mesaj.length()-1);
            mesaj.append("\n");
            w.write(mesaj.toString());
            w.flush();
            w.close();
        } catch (IOException e) {
            System.out.println("Eroare de IO");
            e.printStackTrace();
        }
    }
}