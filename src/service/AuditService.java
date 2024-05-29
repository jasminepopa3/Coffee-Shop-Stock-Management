package service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AuditService {
    private static AuditService instanta = null;
    private String path = "./src/auditLog/AuditLog.csv";
    private ScrieCSVService scrieCSV = ScrieCSVService.getInstance();

    private AuditService() { }

    public static AuditService getInstance() {
        if (instanta == null)
            instanta = new AuditService();
        return instanta;
    }

    public void log(String numeActiune) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        ArrayList<String> mesaj =new ArrayList<String>();
        mesaj.add(numeActiune);
        mesaj.add(timestamp);
        scrieCSV.write(path,mesaj);
    }

}
