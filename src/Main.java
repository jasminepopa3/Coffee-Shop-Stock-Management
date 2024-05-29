import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.*;
import service.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CategorieService categorieService1 = new CategorieService();
        FurnizorService furnizorService1 = new FurnizorService();
        ProdusAlimentarService produsAlimentarService1 = new ProdusAlimentarService();
        RetailService retailService1 = new RetailService();
        ProdusVanzareService produsVanzareService1 = new ProdusVanzareService();
        AchizitieService achizitieService1 = new AchizitieService();
        VanzareService vanzareService1 = new VanzareService();
        AuditService audit = AuditService.getInstance();

        while (true) {
            System.out.println("\n===== Meniu =====");
            System.out.println("1. Creează categorie");
            System.out.println("2. Actualizează categorie");
            System.out.println("3. Șterge categorie");
            System.out.println("4. Afișează categorii existente");
            System.out.println("5. Adauga furnizor");
            System.out.println("6. Actualizeaza furnizor");
            System.out.println("7. Sterge furnizor");
            System.out.println("8. Afiseaza furnizori existenti");
            System.out.println("9. Adauga produs alimentar");
            System.out.println("10. Actualizeaza produs alimentar");
            System.out.println("11. Sterge produs alimentar");
            System.out.println("12. Afiseaza produse alimentare existente");
            System.out.println("13. Adauga retail");
            System.out.println("14. Actualizeaza retail");
            System.out.println("15. Sterge retail");
            System.out.println("16. Afiseaza retails existente");
            System.out.println("17. Creeaza produs de vanzare");
            System.out.println("18. Actualizeaza produs de vanzare");
            System.out.println("19. Sterge produs de vanzare");
            System.out.println("20. Afiseaza produsele de vanzare existente");
            System.out.println("21. Adauga achizitie");
            System.out.println("22. Sterge achizitie");
            System.out.println("23. Afiseaza achizitiile");
            System.out.println("24. Adauga vanzare");
            System.out.println("25. Sterge vanzare");
            System.out.println("26. Afiseaza vanzarile");
            System.out.println("0. Ieșire");
            System.out.print("Alege opțiunea: ");
            int optiune = scanner.nextInt();
            scanner.nextLine();

            switch (optiune) {
                case 0:
                    System.out.println("La revedere!");
                    System.exit(0);
                case 1:
                    System.out.print("Introdu numele categoriei: ");
                    String nume = scanner.nextLine();
                    System.out.print("Introdu descrierea categoriei: ");
                    String descriere = scanner.nextLine();
                    Categorie categorie = new Categorie(nume, descriere);
                    categorieService1.add(categorie);
                    System.out.println("Categorie creată cu succes: " + categorie);
                    audit.log("adauga_categorie");
                    break;
                case 2:
                    int idActualizare = 0;
                    boolean inputValidCategActualizata = false;

                    while (!inputValidCategActualizata) {
                        System.out.println("Introdu id-ul categoriei de actualizat: ");
                        String idActualizareInput = scanner.nextLine();

                        try {
                            idActualizare = Integer.parseInt(idActualizareInput);
                            inputValidCategActualizata = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    System.out.print("Introdu noul nume al categoriei: ");
                    String numeNou = scanner.nextLine();
                    System.out.print("Introdu noua descriere a categoriei: ");
                    String descriereNoua = scanner.nextLine();
                    Categorie categorieNoua = new Categorie(numeNou, descriereNoua);
                    categorieService1.update(idActualizare, categorieNoua);
                    audit.log("actualizeaza_categorie");
                    break;
                case 3:
                    int idStergere = 0;
                    boolean inputValidCategStearsa = false;

                    while (!inputValidCategStearsa) {
                        System.out.println("Introdu id-ul categoriei de sters: ");
                        String idCategStersInput = scanner.nextLine();

                        try {
                            idStergere = Integer.parseInt(idCategStersInput);
                            inputValidCategStearsa = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    // Verificăm dacă există produse asociate categoriei pe care dorim să o ștergem
                    if (categorieService1.areProduseAsociate(idStergere)) {
                        System.out.println("Nu poți șterge această categorie deoarece există produse care fac parte din această categorie. Actualizează datele mai întâi.");
                    } else {
                        // Dacă nu există produse asociate, putem proceda cu ștergerea categoriei
                        categorieService1.remove(idStergere);
                        System.out.println("Categoria a fost ștearsă cu succes.");
                    }
                    audit.log("sterge_categorie");
                    break;
                case 4:
                    categorieService1.display();
                    audit.log("afiseaza_categorii");
                    break;
                case 5:
                    System.out.println("Introdu numele furnizorului: ");
                    String numeFurnizor = scanner.nextLine();
                    System.out.println("Introdu adresa furnizorului: ");
                    String adresaFurnizor = scanner.nextLine();
                    System.out.println("Introdu contul bancar furnizorului: ");
                    String contBancarFurnizor = scanner.nextLine();
                    System.out.println("Introdu email-ul furnizorului: ");
                    String emailFurnizor = scanner.nextLine();
                    System.out.println("Introdu nr. de telefon al furnizorului: ");
                    String telefonFurnizor = scanner.nextLine();
                    Furnizor furnizorNou = new Furnizor(numeFurnizor,adresaFurnizor,contBancarFurnizor,emailFurnizor,telefonFurnizor);
                    furnizorService1.add(furnizorNou);
                    System.out.println("Furnizor adaugat cu succes: " + furnizorNou);
                    audit.log("adauga_furnizor");
                    break;
                case 6:
                    int idFurnizorDeActualizat = 0;
                    boolean inputValidFurnizorActualizat = false;

                    while (!inputValidFurnizorActualizat) {
                        System.out.println("Introdu id-ul furnizorului de actualizat: ");
                        String idActualizareFurnizorInput = scanner.nextLine();

                        try {
                            idFurnizorDeActualizat = Integer.parseInt(idActualizareFurnizorInput);
                            inputValidFurnizorActualizat = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    System.out.println("Introdu numele furnizorului de actualizat: ");
                    String numeFurnizorDeActualizat = scanner.nextLine();
                    System.out.println("Introdu adresa furnizorului de actualizat: ");
                    String adresaFurnizorDeActualizat = scanner.nextLine();
                    System.out.println("Introdu contul bancar furnizorului de actualizat: ");
                    String contBancarFurnizorDeActualizat = scanner.nextLine();
                    System.out.println("Introdu email-ul furnizorului de actualizat: ");
                    String emailFurnizorDeActualizat = scanner.nextLine();
                    System.out.println("Introdu nr. de telefon furnizorului de actualizat: ");
                    String telefonFurnizorDeActualizat = scanner.nextLine();
                    Furnizor furnizorActualizat = new Furnizor(numeFurnizorDeActualizat,adresaFurnizorDeActualizat,contBancarFurnizorDeActualizat,emailFurnizorDeActualizat,telefonFurnizorDeActualizat);
                    furnizorService1.update(idFurnizorDeActualizat,furnizorActualizat);
                    audit.log("actualizeaza_categorie");
                    break;
                case 7:

                    int idFurnizorDeSters = 0;
                    boolean inputValidFurnizorStears = false;

                    while (!inputValidFurnizorStears) {
                        System.out.println("Introdu id-ul furnizorului de sters: ");
                        String idFurnizorStersInput = scanner.nextLine();

                        try {
                            idFurnizorDeSters = Integer.parseInt(idFurnizorStersInput);
                            inputValidFurnizorStears = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    // Verificăm dacă există produse asociate categoriei pe care dorim să o ștergem
                    if (furnizorService1.areProduseAsociate(idFurnizorDeSters)) {
                        System.out.println("Nu poți șterge aceast furnizor deoarece există produse care sunt distribuite de acest furnizor. Actualizează datele mai întâi.");
                    } else {
                        // Dacă nu există produse asociate, putem proceda cu ștergerea categoriei
                        categorieService1.remove(idFurnizorDeSters);
                        System.out.println("Furnizorul a fost ștears cu succes.");
                    }
                    audit.log("sterge_furnizor");
                    break;
                case 8:
                    audit.log("afiseaza_furnizori");
                    furnizorService1.display();
                    break;
                case 9:
                    System.out.println("Introdu numele produsului alimentar de adaugat: ");
                    String numeProdus = scanner.nextLine();
                    System.out.println("Introdu descrierea produsului alimentar de adaugat: ");
                    String descriereProdus = scanner.nextLine();


                    double pretProdus = 0.0;
                    boolean inputValid = false;

                    while (!inputValid) {
                        System.out.println("Introdu pretul produsului alimentar de adaugat: ");
                        String pretInput = scanner.nextLine();

                        try {
                            pretProdus = Double.parseDouble(pretInput);
                            inputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Pretul introdus nu este valid. Introdu un numar.");
                        }
                    }


                    int idCategorieProdAlim = 0;
                    boolean inputValidCategorieProdAlim = false;

                    while (!inputValidCategorieProdAlim) {
                        System.out.println("Introdu id-ul categoriei produsului alimentar: ");
                        String idCategorieInputProdAlim = scanner.nextLine();

                        try {
                            idCategorieProdAlim = Integer.parseInt(idCategorieInputProdAlim);
                            inputValidCategorieProdAlim = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }


                    int idFurnizorProdAlim = 0;
                    boolean inputValidFurnizorProdAlim = false;

                    while (!inputValidFurnizorProdAlim) {
                        System.out.println("Introdu id-ul furnizorului produsului alimentar: ");
                        String idFurnizorProdAlimInput = scanner.nextLine();

                        try {
                            idFurnizorProdAlim = Integer.parseInt(idFurnizorProdAlimInput);
                            inputValidFurnizorProdAlim = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    LocalDate dataExpirareProdus = null;
                    boolean inputValidDataExpProdAlim = false;

                    while (!inputValidDataExpProdAlim) {
                        System.out.println("Introdu data expirarii produsului alimentar (YYYY-MM-DD): ");
                        String dataExpirareProdusInput = scanner.nextLine();

                        try {
                            dataExpirareProdus = LocalDate.parse(dataExpirareProdusInput);
                            inputValidDataExpProdAlim = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (DateTimeParseException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Data introdusa nu este valida. Introdu o data corecta (YYYY-MM-DD).");
                        }
                    }


                    int stocProdusAlim = 0;
                    boolean inputValidStocProdAlim = false;

                    while (!inputValidStocProdAlim) {
                        System.out.println("Introdu cantitatea din stoc a produsului alimentar: ");
                        String stocProdusAlimInput = scanner.nextLine();

                        try {
                            stocProdusAlim = Integer.parseInt(stocProdusAlimInput);
                            inputValidStocProdAlim = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }


                    //acum pot crea obiectul de tip ProdusAlimentar si sa-l adaug in lista sa
                    ProdusAlimentar produsAlimentarNou = new ProdusAlimentar(numeProdus, descriereProdus, pretProdus, idFurnizorProdAlim, idCategorieProdAlim, dataExpirareProdus, stocProdusAlim);
                    produsAlimentarService1.add(produsAlimentarNou);

                    System.out.println("Produsul alimentar a fost adaugat cu succes!");
                    audit.log("adauga_prod_alim");
                    break;
                case 10:

                    int idProdusActualizat = 0;
                    boolean inputValidIdActualizareProdAlim = false;

                    while (!inputValidIdActualizareProdAlim) {
                        System.out.println("Introdu id-ul produsului alimentar de actualizat: ");
                        String idProdusActualizatInput = scanner.nextLine();

                        try {
                            idProdusActualizat = Integer.parseInt(idProdusActualizatInput);
                            inputValidIdActualizareProdAlim = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    System.out.println("Introdu numele produsului alimentar de actualizat: ");
                    String numeProdusActualizat = scanner.nextLine();
                    System.out.println("Introdu descrierea produsului alimentar de actualizat: ");
                    String descriereProdusActualizat = scanner.nextLine();

                    double pretProdusActualizat = 0.0;
                    boolean inputValidActualizat = false;

                    while (!inputValidActualizat) {
                        System.out.println("Introdu pretul produsului alimentar de actualizat: ");
                        String pretInputActualizat = scanner.nextLine();

                        try {
                            pretProdusActualizat = Double.parseDouble(pretInputActualizat);
                            inputValidActualizat = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Pretul introdus nu este valid. Introdu un numar.");
                        }
                    }


                    int idCategorieProdAlimActualizat = 0;
                    boolean inputValididCategorieProdAlimActualizat = false;

                    while (!inputValididCategorieProdAlimActualizat) {
                        System.out.println("Introdu id-ul categoriei produsului alimentar: ");
                        String idCategorieProdAlimActualizatInput = scanner.nextLine();

                        try {
                            idCategorieProdAlimActualizat = Integer.parseInt(idCategorieProdAlimActualizatInput);
                            inputValididCategorieProdAlimActualizat = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    int idFurnizorProdAlimActualizat = 0;
                    boolean inputValididFurnizorProdAlimActualizat = false;

                    while (!inputValididFurnizorProdAlimActualizat) {
                        System.out.println("Introdu id-ul furnizorului produsului alimentar: ");
                        String idFurnizorProdAlimActualizatInput = scanner.nextLine();

                        try {
                            idFurnizorProdAlimActualizat = Integer.parseInt(idFurnizorProdAlimActualizatInput);
                            inputValididFurnizorProdAlimActualizat = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    LocalDate dataExpirareActualizataProdus = null;
                    boolean inputValiddataExpirareActualizataString = false;

                    while (!inputValiddataExpirareActualizataString) {
                        System.out.println("Introdu data expirarii produsului alimentar (YYYY-MM-DD): ");
                        String dataExpirareActualizataStringInput = scanner.nextLine();

                        try {
                            dataExpirareActualizataProdus = LocalDate.parse(dataExpirareActualizataStringInput);
                            inputValiddataExpirareActualizataString = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (DateTimeParseException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Data introdusa nu este valida. Introdu o data corecta (YYYY-MM-DD).");
                        }
                    }

                    int stocProdusActualizat = 0;
                    boolean stocProdusActualizatInputValid = false;

                    while (!stocProdusActualizatInputValid) {
                        System.out.println("Introdu cantitatea din stoc a produsului alimentar de actualizat: ");
                        String stocProdusActualizatInput = scanner.nextLine();

                        try {
                            stocProdusActualizat = Integer.parseInt(stocProdusActualizatInput);
                            stocProdusActualizatInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }


                    ProdusAlimentar produsAlimentarActualizat = new ProdusAlimentar(numeProdusActualizat, descriereProdusActualizat, pretProdusActualizat, idFurnizorProdAlimActualizat, idCategorieProdAlimActualizat, dataExpirareActualizataProdus, stocProdusActualizat);
                    produsAlimentarService1.update(idProdusActualizat,produsAlimentarActualizat);
                    audit.log("actualizeaza_prod_alim");
                    break;
                case 11:

                    int idProdusSters = 0;
                    boolean idProdusStersInputValid = false;

                    while (!idProdusStersInputValid) {
                        System.out.println("Introdu id-ul produsului alimentar de sters: ");
                        String idProdusStersInput = scanner.nextLine();

                        try {
                            idProdusSters = Integer.parseInt(idProdusStersInput);
                            idProdusStersInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    // Verificăm dacă există produse asociate categoriei pe care dorim să o ștergem
                    if (produsAlimentarService1.areProduseVanzareAsociate(idProdusSters)) {
                        System.out.println("Nu poți șterge aceast produs alimentar deoarece există produse de vanzare care il contin in lista de ingrediente. Actualizează datele mai întâi.");
                    } else {
                        // Dacă nu există produse asociate, putem proceda cu ștergerea categoriei
                        produsAlimentarService1.remove(idProdusSters);
                        System.out.println("Produsul alimentar a fost șters cu succes.");
                    }
                    audit.log("sterge_prod_alim");
                    break;
                case 12:
                    audit.log("afiseaza_prod_alim");
                    produsAlimentarService1.display();
                    break;
                case 13:
                    System.out.println("Introdu numele retail-ului de adaugat: ");
                    String numeRetail = scanner.nextLine();
                    System.out.println("Introdu descrierea retail-ului de adaugat: ");
                    String descriereRetail = scanner.nextLine();

                    double pretRetail = 0.0;
                    boolean inputRetail = false;

                    while (!inputRetail) {
                        System.out.println("Introdu pretul retail-ului de adaugat: ");
                        String pretRetailInput = scanner.nextLine();

                        try {
                            pretRetail = Double.parseDouble(pretRetailInput);
                            inputRetail = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Pretul introdus nu este valid. Introdu un numar.");
                        }
                    }


                    int idCategorieRetail = 0;
                    boolean idCategorieRetailInputValid = false;

                    while (!idCategorieRetailInputValid) {
                        System.out.println("Introdu id-ul categoriei retail-ului: ");
                        String idCategorieRetailInput = scanner.nextLine();

                        try {
                            idCategorieRetail = Integer.parseInt(idCategorieRetailInput);
                            idCategorieRetailInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }


                    int idFurnizorRetail = 0;
                    boolean idFurnizorRetailInputValid = false;

                    while (!idFurnizorRetailInputValid) {
                        System.out.println("Introdu id-ul furnizorului de retail: ");
                        String idFurnizorRetailInput = scanner.nextLine();

                        try {
                            idFurnizorRetail = Integer.parseInt(idFurnizorRetailInput);
                            idFurnizorRetailInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }


                    System.out.println("Introdu materialul retail-ului: ");
                    String materialRetail = scanner.nextLine();


                    int stocRetailAdaugat = 0;
                    boolean stocRetailAdaugatInputValid = false;

                    while (!stocRetailAdaugatInputValid) {
                        System.out.println("Introdu cantitatea din stoc a retail-ului de adaugat: ");
                        String stocRetailAdaugatInput = scanner.nextLine();

                        try {
                            stocRetailAdaugat = Integer.parseInt(stocRetailAdaugatInput);
                            stocRetailAdaugatInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    //acum pot crea obiectul de tip ProdusAlimentar si sa-l adaug in lista sa
                    Retail retailNou = new Retail(numeRetail, descriereRetail, pretRetail, idCategorieRetail, idFurnizorRetail, materialRetail, stocRetailAdaugat);
                    retailService1.add(retailNou);

                    System.out.println("Retail-ul a fost adaugat cu succes!");
                    audit.log("adauga_retail");
                    break;
                case 14:

                    int idRetailActualizat = 0;
                    boolean idRetailActualizatInputValid = false;

                    while (!idRetailActualizatInputValid) {
                        System.out.println("Introdu id-ul retail-ului de actualizat: ");
                        String idRetailActualizatInput = scanner.nextLine();

                        try {
                            idRetailActualizat = Integer.parseInt(idRetailActualizatInput);
                            idRetailActualizatInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    System.out.println("Introdu numele retail-ului de actualizat: ");
                    String numeRetailActualizat = scanner.nextLine();
                    System.out.println("Introdu descrierea retail-ului de actualizat: ");
                    String desccriereRetailActualizat = scanner.nextLine();

                    double pretRetailActualizat = 0.0;
                    boolean inputRetailActualizat = false;

                    while (!inputRetailActualizat) {
                        System.out.println("Introdu pretul retail-ului de actualizat: ");
                        String pretRetailInputActualizat = scanner.nextLine();

                        try {
                            pretRetailActualizat = Double.parseDouble(pretRetailInputActualizat);
                            inputRetailActualizat = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Pretul introdus nu este valid. Introdu un numar.");
                        }
                    }

                    int idCategorieRetailActualizat = 0;
                    boolean idCategorieRetailActualizatInputValid = false;

                    while (!idCategorieRetailActualizatInputValid) {
                        System.out.println("Introdu id-ul categoriei retail-ului de actualizat: ");
                        String idCategorieRetailActualizatInput = scanner.nextLine();

                        try {
                            idCategorieRetailActualizat = Integer.parseInt(idCategorieRetailActualizatInput);
                            idCategorieRetailActualizatInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }


                    int idFurnizorRetailActualizat = 0;
                    boolean idFurnizorRetailActualizatInputValid = false;

                    while (!idFurnizorRetailActualizatInputValid) {
                        System.out.println("Introdu id-ul furnizorului retail-ului de actualizat: ");
                        String idFurnizorRetailActualizatInput = scanner.nextLine();

                        try {
                            idFurnizorRetailActualizat = Integer.parseInt(idFurnizorRetailActualizatInput);
                            idFurnizorRetailActualizatInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }


                    System.out.println("Introdu materialul retail-ului de actualizat:");
                    String materialRetailActualizat = scanner.nextLine();


                    int stocRetailActualizat = 0;
                    boolean stocRetailActualizatInputValid = false;

                    while (!stocRetailActualizatInputValid) {
                        System.out.println("Introdu cantitatea din stoc a retail-ului de actualizat: ");
                        String stocRetailActualizatInput = scanner.nextLine();

                        try {
                            stocRetailActualizat = Integer.parseInt(stocRetailActualizatInput);
                            stocRetailActualizatInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    Retail retailActualizat = new Retail(numeRetailActualizat, desccriereRetailActualizat, pretRetailActualizat, idCategorieRetailActualizat, idFurnizorRetailActualizat, materialRetailActualizat, stocRetailActualizat);
                    retailService1.update(idRetailActualizat,retailActualizat);
                    audit.log("actualizeaza_retail");
                    break;
                case 15:

                    int idRetailSters = 0;
                    boolean idRetailStersInputValid = false;

                    while (!idRetailStersInputValid) {
                        System.out.println("Introdu id-ul retail-ului de sters: ");
                        String idRetailStersInput = scanner.nextLine();

                        try {
                            idRetailSters = Integer.parseInt(idRetailStersInput);
                            idRetailStersInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    retailService1.remove(idRetailSters);
                    System.out.println("Retail-ul a fost șters cu succes.");
                    audit.log("sterge_Retail");
                    break;
                case 16:
                    audit.log("afiseaza_retails");
                    retailService1.display();
                    break;
                case 17:
                    System.out.println("Introdu numele produsului de vanzare: ");
                    String numeProdusVanzare = scanner.nextLine();
                    System.out.println("Introdu descrierea produsului de vanzare: ");
                    String descriereProdusVanzare = scanner.nextLine();

                    double pretProdusVanzare = 0.0;
                    boolean inputProdusVanzare= false;

                    while (!inputProdusVanzare) {
                        System.out.println("Introdu pretul produsului de vanzare de adaugat: ");
                        String pretProdusVanzareInput = scanner.nextLine();

                        try {
                            pretProdusVanzare = Double.parseDouble(pretProdusVanzareInput);
                            inputProdusVanzare = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Pretul introdus nu este valid. Introdu un numar.");
                        }
                    }


                    int idCategorieProdusVanzare = 0;
                    boolean idCategorieProdusVanzareInputValid = false;

                    while (!idCategorieProdusVanzareInputValid) {
                        System.out.println("Introdu id-ul categoriei produsului de vanzare: ");
                        String idCategorieProdusVanzareInput = scanner.nextLine();

                        try {
                            idCategorieProdusVanzare = Integer.parseInt(idCategorieProdusVanzareInput);
                            idCategorieProdusVanzareInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    Map<Integer, Double> ingredienteCantitate = new HashMap<>();
                    boolean continuaAdaugare = true;

                    while (continuaAdaugare) {

                        int idProdusAlimentar = 0;
                        boolean idProdusAlimentarInputValid = false;

                        while (!idProdusAlimentarInputValid) {
                            System.out.println("Introdu id-ul produsului alimentar din lista de ingrediente sau tasteaza 0 pentru a iesi: ");
                            String idProdusAlimentarInput = scanner.nextLine();

                            try {
                                idProdusAlimentar = Integer.parseInt(idProdusAlimentarInput);
                                idProdusAlimentarInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                            } catch (NumberFormatException e) {
                                // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                            }
                        }

                        if(idProdusAlimentar == 0){
                            continuaAdaugare = false;
                        }else{
                            ProdusAlimentar produsAlimentar = produsAlimentarService1.getProdusAlimentarByID(idProdusAlimentar);


                        if (produsAlimentar != null) {

                            double cantitate = 0;
                            boolean cantitateInputValid = false;

                            while (!cantitateInputValid) {
                                System.out.println("Introdu cantitatea pentru produsul alimentar din lista de ingrediente: ");
                                String cantitateInput = scanner.nextLine();

                                try {
                                    cantitate = Double.parseDouble(cantitateInput);
                                    cantitateInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                                } catch (NumberFormatException e) {
                                    // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                    System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                                }
                            }


                            ingredienteCantitate.put(idProdusAlimentar, cantitate);

                            System.out.println("Doriți să mai adăugați ingrediente? (da/nu)");
                            String raspuns = scanner.nextLine();

                            if (!raspuns.equalsIgnoreCase("da")) {
                                continuaAdaugare = false;
                            }
                        }
                        }
                    }

                    ProdusVanzare produsVanzareNou = new ProdusVanzare(numeProdusVanzare, descriereProdusVanzare, pretProdusVanzare, idCategorieProdusVanzare);
                    produsVanzareService1.add(produsVanzareNou);


                    int idProdusVanzare = produsVanzareService1.getLastInsertedProductId();

                    for (Map.Entry<Integer, Double> entry : ingredienteCantitate.entrySet()) {
                        int idProdusAlimentar = entry.getKey();
                        double cantitate = entry.getValue();

                        produsVanzareService1.insertProdusVanzareIngrediente(idProdusVanzare, idProdusAlimentar, cantitate);
                    }

                    System.out.println(produsVanzareNou);
                    System.out.println("Produsul a fost adaugat cu succes!");
                    audit.log("adauga_prod_vanzare");
                    break;
                case 18:
                    int idProdusVanzareActualizat = 0;
                    boolean idProdusVanzareActualizatInputValid= false;

                    while (!idProdusVanzareActualizatInputValid) {
                        System.out.println("Introdu id-ul produsului de vanzare de actualizat: ");
                        String idProdusVanzareActualizatInput = scanner.nextLine();

                        try {
                            idProdusVanzareActualizat = Integer.parseInt(idProdusVanzareActualizatInput);
                            idProdusVanzareActualizatInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar.");
                        }
                    }

                    System.out.println("Introdu numele produsului de vanzare de actualizat: ");
                    String numeProdusVanzareActualizat = scanner.nextLine();
                    System.out.println("Introdu descrierea produsului de vanzare de actualizat: ");
                    String descriereProdusVanzareActualizat = scanner.nextLine();

                    double pretProdusVanzareActualizat = 0.0;
                    boolean inputProdusVanzareActualizat= false;

                    while (!inputProdusVanzareActualizat) {
                        System.out.println("Introdu pretul produsului de vanzare de actualizat: ");
                        String pretProdusVanzareInputActualizat = scanner.nextLine();

                        try {
                            pretProdusVanzareActualizat = Double.parseDouble(pretProdusVanzareInputActualizat);
                            inputProdusVanzareActualizat = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Pretul introdus nu este valid. Introdu un numar.");
                        }
                    }

                    int idCategorieProdusVanzareActualizat = 0;
                    boolean idCategorieProdusVanzareActualizatInputValid= false;

                    while (!idCategorieProdusVanzareActualizatInputValid) {
                        System.out.println("Introdu id-ul categoriei produsului de vanzare de actualizat: ");
                        String idCategorieProdusVanzareActualizatInput = scanner.nextLine();

                        try {
                            idCategorieProdusVanzareActualizat = Integer.parseInt(idCategorieProdusVanzareActualizatInput);
                            idCategorieProdusVanzareActualizatInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar.");
                        }
                    }

                    Map<Integer, Double> ingredienteCantitateActualizare = new HashMap<>();
                    boolean continuaAdaugareActualizare = true;

                    while (continuaAdaugareActualizare) {

                        int idProdusAlimentarActualizare = 0;
                        boolean idProdusAlimentarActualizareInputValid = false;

                        while (!idProdusAlimentarActualizareInputValid) {
                            System.out.println("Introdu id-ul produsului alimentar din lista de ingrediente sau tasteaza 0 pentru a iesi: ");
                            String idProdusAlimentarActualizareInput = scanner.nextLine();

                            try {
                                idProdusAlimentarActualizare = Integer.parseInt(idProdusAlimentarActualizareInput);
                                idProdusAlimentarActualizareInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                            } catch (NumberFormatException e) {
                                // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                            }
                        }

                        if (idProdusAlimentarActualizare == 0) {
                            continuaAdaugareActualizare = false;
                            break;
                        }else{

                        ProdusAlimentar produsAlimentarDeActualizare = produsAlimentarService1.getProdusAlimentarByID(idProdusAlimentarActualizare);


                        if (produsAlimentarDeActualizare != null) {

                            double cantitateProdAlimProdVanzareActualizare = 0.0;
                            boolean cantitateProdAlimProdVanzareActualizareInputValid = false;

                            while (!cantitateProdAlimProdVanzareActualizareInputValid) {
                                System.out.println("Introdu cantitatea pentru produsul alimentar din lista de ingrediente:  ");
                                String cantitateProdAlimProdVanzareActualizareInput = scanner.nextLine();

                                try {
                                    cantitateProdAlimProdVanzareActualizare = Double.parseDouble(cantitateProdAlimProdVanzareActualizareInput);
                                    cantitateProdAlimProdVanzareActualizareInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                                } catch (NumberFormatException e) {
                                    // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                    System.out.println("Id-ul introdus nu este valid. Introdu un numar.");
                                }
                            }

                            ingredienteCantitateActualizare.put(idProdusAlimentarActualizare, cantitateProdAlimProdVanzareActualizare);

                            System.out.println("Doriți să mai adăugați ingrediente? (da/nu)");
                            String raspuns = scanner.nextLine();

                            if (!raspuns.equalsIgnoreCase("da")) {
                                continuaAdaugareActualizare = false;
                            }
                        }
                    }
                    }

                    // Actualizarea produsului de vânzare fără ingredientele aferente
                    ProdusVanzare produsVanzareActualizare = new ProdusVanzare(numeProdusVanzareActualizat,descriereProdusVanzareActualizat,pretProdusVanzareActualizat,idCategorieProdusVanzareActualizat);
                    produsVanzareService1.update(idProdusVanzareActualizat,produsVanzareActualizare);

                    produsVanzareService1.deleteIngredienteProductById(idProdusVanzareActualizat);

                    for (Map.Entry<Integer, Double> entry : ingredienteCantitateActualizare.entrySet()) {
                        int idProdusAlimentar = entry.getKey();
                        double cantitate = entry.getValue();

                        produsVanzareService1.insertProdusVanzareIngrediente(idProdusVanzareActualizat, idProdusAlimentar, cantitate);
                    }

                    System.out.println("Produsul a fost actualizat cu succes!");
                    audit.log("actualizeaza_prod_vanzare");
                    break;
                case 19:
                    int idProdusVanzareSters = 0;
                    boolean idProdusVanzareStersInputValid= false;

                    while (!idProdusVanzareStersInputValid) {
                        System.out.println("Introdu id-ul produsului de vanzare de sters: ");
                        String idProdusVanzareStersInput = scanner.nextLine();

                        try {
                            idProdusVanzareSters = Integer.parseInt(idProdusVanzareStersInput);
                            idProdusVanzareStersInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    produsVanzareService1.deleteIngredienteProductById(idProdusVanzareSters);
                    produsVanzareService1.remove(idProdusVanzareSters);
                    audit.log("sterge_prod_vanzare");
                    break;
                case 20:
                    produsVanzareService1.display();
                    audit.log("afiseaza_prod_vanzare");
                    break;
                case 21:
                    double pretAchizitie = 0.0;
                    boolean inputAchizitie= false;

                    while (!inputAchizitie) {
                        System.out.println("Introdu pretul achizitiei de adaugat: ");
                        String pretAchizitieInput = scanner.nextLine();

                        try {
                            pretAchizitie = Double.parseDouble(pretAchizitieInput);
                            inputAchizitie = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Pretul introdus nu este valid. Introdu un numar.");
                        }
                    }


                    LocalDate dataAchizitie = null;
                    boolean dataAchizitieInputValid = false;

                    while (!dataAchizitieInputValid) {
                        System.out.println("Introdu data achizitiei (YYYY-MM-DD): ");
                        String dataAchizitieInput = scanner.nextLine();

                        try {
                            dataAchizitie = LocalDate.parse(dataAchizitieInput);
                            dataAchizitieInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (DateTimeParseException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Data introdusa nu este valida. Introdu o data corecta (YYYY-MM-DD).");
                        }
                    }


                    String tipAchizitie = "";
                    boolean inputValidTipAchizitie = false;

                    while (!inputValidTipAchizitie) {
                        System.out.println("Introdu tipul tranzactiei (cash/card): ");
                        tipAchizitie = scanner.nextLine().trim().toLowerCase(); // Normalizăm intrarea

                        if (tipAchizitie.equals("cash") || tipAchizitie.equals("card")) {
                            inputValidTipAchizitie = true; // Intrarea este validă, ieșim din buclă
                        } else {
                            System.out.println("Tipul introdus nu este valid. Te rog introdu 'cash' sau 'card'.");
                        }
                    }


                    //trb sa fac lista de produse alimentare - cantitate
                    Map<Integer, Integer> listaProduseAlimentareCantitate = new HashMap<>();
                    boolean continuaAdaugareAchizitii = true;

                    while (continuaAdaugareAchizitii) {

                        int idProdusAlimentarAchizitie = 0;
                        boolean idProdusAlimentarAchizitieInputValid = false;

                        while (!idProdusAlimentarAchizitieInputValid) {
                            System.out.println("Introdu id-ul produsului alimentar din achizitie sau tasteaza 0 pentru a iesi: ");
                            String idProdusAlimentarAchizitieInput = scanner.nextLine();

                            try {
                                idProdusAlimentarAchizitie = Integer.parseInt(idProdusAlimentarAchizitieInput);
                                idProdusAlimentarAchizitieInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                            } catch (NumberFormatException e) {
                                // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                            }
                        }


                        ProdusAlimentar produsAlimentarAchizitie = produsAlimentarService1.getProdusAlimentarByID(idProdusAlimentarAchizitie);

                        if (idProdusAlimentarAchizitie == 0) {
                            continuaAdaugareAchizitii = false;
                        }else{

                        if (produsAlimentarAchizitie != null) {

                            int cantitateAchizitie = 0;
                            boolean cantitateAchizitieInputValid = false;

                            while (!cantitateAchizitieInputValid) {
                                System.out.println("Introdu cantitatea pentru produsul alimentar: ");
                                String cantitateAchizitieInput = scanner.nextLine();

                                try {
                                    cantitateAchizitie = Integer.parseInt(cantitateAchizitieInput);
                                    cantitateAchizitieInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                                } catch (NumberFormatException e) {
                                    // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                    System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                                }
                            }

                            listaProduseAlimentareCantitate.put(idProdusAlimentarAchizitie, cantitateAchizitie);

                            System.out.println("Doriți să mai adăugați produse in achizitie? (da/nu)");
                            String raspunsAchizitie = scanner.nextLine();

                            if (!raspunsAchizitie.equalsIgnoreCase("da")) {
                                continuaAdaugareAchizitii = false;
                            }
                        }

                    }
                    }

                    Achizitie achizitieNoua = new Achizitie(pretAchizitie,dataAchizitie,tipAchizitie);
                    achizitieService1.add(achizitieNoua);

                    int idAchizitie = achizitieService1.getLastInsertedId();

                    for (Map.Entry<Integer, Integer> entry : listaProduseAlimentareCantitate.entrySet()) {
                        int idProdusAlimentarDinAchizitie = entry.getKey();
                        int cantitate = entry.getValue();
                        achizitieService1.insertProdusAlimentarCantitate(idAchizitie, idProdusAlimentarDinAchizitie, cantitate);
                    }

                    //trb sa fac lista de produse retail - cantitate
                    Map<Integer, Integer> listaRetailCantitate = new HashMap<>();
                    boolean continuaAdaugareAchizitiiRetail = true;

                    while (continuaAdaugareAchizitiiRetail) {

                        int idRetailAchizitie = 0;
                        boolean idRetailAchizitieInputValid = false;

                        while (!idRetailAchizitieInputValid) {
                            System.out.println("Introdu id-ul retail-ului din achizitie sau tasteaza 0 pentru a iesi: ");
                            String idRetailAchizitieInput = scanner.nextLine();

                            try {
                                idRetailAchizitie = Integer.parseInt(idRetailAchizitieInput);
                                idRetailAchizitieInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                            } catch (NumberFormatException e) {
                                // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                            }
                        }

                        // Obțineți produsul alimentar folosind id-ul introdus
                        Retail retailAchizitie = retailService1.getRetailByID(idRetailAchizitie);

                        if (idRetailAchizitie == 0) {
                            continuaAdaugareAchizitiiRetail = false;
                        }else{

                        if (retailAchizitie != null) {

                            int cantitateAchizitieRetail = 0;
                            boolean cantitateAchizitieRetailInputValid = false;

                            while (!cantitateAchizitieRetailInputValid) {
                                System.out.println("Introdu cantitatea pentru retail-ul achizitionat: ");
                                String cantitateAchizitieRetailInput = scanner.nextLine();

                                try {
                                    cantitateAchizitieRetail = Integer.parseInt(cantitateAchizitieRetailInput);
                                    cantitateAchizitieRetailInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                                } catch (NumberFormatException e) {
                                    // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                    System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                                }
                            }

                            listaRetailCantitate.put(idRetailAchizitie, cantitateAchizitieRetail);

                            System.out.println("Doriți să mai adăugați retail in achizitie? (da/nu)");
                            String raspunsAchizitieRetail = scanner.nextLine();

                            if (!raspunsAchizitieRetail.equalsIgnoreCase("da")) {
                                continuaAdaugareAchizitiiRetail = false;
                            }
                        }
                       }
                    }

                    for (Map.Entry<Integer, Integer> entry : listaRetailCantitate.entrySet()) {
                        int idRetailDinAchizitie = entry.getKey();
                        int cantitate2 = entry.getValue();
                        achizitieService1.insertRetailCantitate(idAchizitie, idRetailDinAchizitie, cantitate2);
                    }


                    System.out.println("Achizitia a fost adaugata cu succes!");
                    audit.log("adauga_achizitie");
                    break;
                case 22:
                    int idAchizitieSters = 0;
                    boolean idAchizitieStersInputValid= false;

                    while (!idAchizitieStersInputValid) {
                        System.out.println("Introdu id-ul achizitiei de sters: ");
                        String idAchizitieStersInput = scanner.nextLine();

                        try {
                            idAchizitieSters = Integer.parseInt(idAchizitieStersInput);
                            idAchizitieStersInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    achizitieService1.deleteProdusAlimentarAchizitieById(idAchizitieSters);
                    achizitieService1.deleteRetailAchizitieById(idAchizitieSters);
                    achizitieService1.remove(idAchizitieSters);
                    audit.log("sterge_achizitie");
                    break;
                case 23:
                    audit.log("afiseaza_achizitii");
                    achizitieService1.display();
                    break;
                case 24:
                    double pretVanzare = 0.0;
                    boolean inputVanzare= false;

                    while (!inputVanzare) {
                        System.out.println("Introdu pretul vanzarii de adaugat: ");
                        String pretVanzareInput = scanner.nextLine();

                        try {
                            pretVanzare = Double.parseDouble(pretVanzareInput);
                            inputVanzare = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Pretul introdus nu este valid. Introdu un numar.");
                        }
                    }


                    LocalDate dataVanzare = null;
                    boolean dataVanzareInputValid = false;

                    while (!dataVanzareInputValid) {
                        System.out.println("Introdu data vanzarii (YYYY-MM-DD): ");
                        String dataVanzareInput = scanner.nextLine();

                        try {
                            dataVanzare = LocalDate.parse(dataVanzareInput);
                            dataVanzareInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (DateTimeParseException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Data introdusa nu este valida. Introdu o data corecta (YYYY-MM-DD).");
                        }
                    }


                    String tipVanzare = "";
                    boolean tipVanzareInputValid = false;

                    while (!tipVanzareInputValid) {
                        System.out.println("Introdu tipul tranzactiei (cash/card): ");
                        tipVanzare = scanner.nextLine().trim().toLowerCase(); // Normalizăm intrarea

                        if (tipVanzare.equals("cash") || tipVanzare.equals("card")) {
                            tipVanzareInputValid = true; // Intrarea este validă, ieșim din buclă
                        } else {
                            System.out.println("Tipul introdus nu este valid. Te rog introdu 'cash' sau 'card'.");
                        }
                    }

                    //trb sa fac lista de produse vanzare - cantitate
                    Map<Integer, Integer> listaProduseVanzareCantitate = new HashMap<>();
                    boolean continuaAdaugareVanzari = true;

                    while (continuaAdaugareVanzari) {

                        int idProdusVanzareVanzare = 0;
                        boolean idProdusVanzareVanzareInputValid= false;

                        while (!idProdusVanzareVanzareInputValid) {
                            System.out.println("Introdu id-ul produsului de vanzare din vanzare sau tasteaza 0 pentru a iesi: ");
                            String idProdusVanzareVanzareInput = scanner.nextLine();

                            try {
                                idProdusVanzareVanzare = Integer.parseInt(idProdusVanzareVanzareInput);
                                idProdusVanzareVanzareInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                            } catch (NumberFormatException e) {
                                // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                            }
                        }

                        ProdusVanzare produsVanzareVanzare = produsVanzareService1.getProdusVanzareByID(idProdusVanzareVanzare);

                        if(idProdusVanzareVanzare == 0){
                            continuaAdaugareVanzari = false;
                        }else{

                        if (produsVanzareVanzare != null) {


                            int cantitateVanzare = 0;
                            boolean cantitateVanzareInputValid= false;

                            while (!cantitateVanzareInputValid) {
                                System.out.println("Introdu cantitatea pentru produsul de vanzare: ");
                                String cantitateVanzareInput = scanner.nextLine();

                                try {
                                    cantitateVanzare = Integer.parseInt(cantitateVanzareInput);
                                    cantitateVanzareInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                                } catch (NumberFormatException e) {
                                    // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                                    System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                                }
                            }

                            listaProduseVanzareCantitate.put(idProdusVanzareVanzare, cantitateVanzare);

                            System.out.println("Doriți să mai adăugați produse in achizitie? (da/nu)");
                            String raspunsVanzare = scanner.nextLine();

                            if (!raspunsVanzare.equalsIgnoreCase("da")) {
                                continuaAdaugareVanzari = false;
                            }
                        }
                        }
                    }

                    // Adăugarea produsului de vânzare fără ingredientele aferente
                    Vanzare vanzareNoua = new Vanzare(pretVanzare,dataVanzare,tipVanzare);
                    vanzareService1.add(vanzareNoua);


                    int idVanzare = vanzareService1.getLastInsertedId();

                    for (Map.Entry<Integer, Integer> entry : listaProduseVanzareCantitate.entrySet()) {
                        int idProdusVanzareDinVanzare = entry.getKey();
                        int cantitate = entry.getValue();

                        vanzareService1.insertProdusVanzareCantitate(idVanzare, idProdusVanzareDinVanzare, cantitate);
                    }


                    System.out.println("Vanzarea a fost adaugata cu succes!");
                    audit.log("adauga_vanzare");
                    break;
                case 25:

                    int idVanzareSters = 0;
                    boolean idVanzareStersInputValid= false;

                    while (!idVanzareStersInputValid) {
                        System.out.println("Introdu id-ul vanzarii de sters: ");
                        String idVanzareStersInput = scanner.nextLine();

                        try {
                            idVanzareSters = Integer.parseInt(idVanzareStersInput);
                            idVanzareStersInputValid = true; // Prețul a fost introdus corect, ieșim din buclă
                        } catch (NumberFormatException e) {
                            // Dacă conversia din String în double eșuează, afișăm un mesaj și continuăm bucla
                            System.out.println("Id-ul introdus nu este valid. Introdu un numar natural.");
                        }
                    }

                    vanzareService1.deleteSellsById(idVanzareSters);
                    vanzareService1.remove(idVanzareSters);
                    audit.log("sterge_vanzare");
                    break;
                case 26:
                    vanzareService1.display();
                    audit.log("afiseaza_vanzari");
                    break;
                default:
                    System.out.println("Opțiune invalidă!");
            }

        }

    }
}
