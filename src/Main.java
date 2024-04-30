import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.*;
import service.*;

public class Main {
    public static void main(String[] args) {



        /* acestea au fost testele mele inainte sa incep sa creez meniul
        // Crearea furnizorilor
        Furnizor furnizor1 = new Furnizor("Furnizor1", "Str. Exemplu nr. 1", "RO123456789", "furnizor1@example.com", "+40123456789");
        Furnizor furnizor2 = new Furnizor("Furnizor2", "Str. Test nr. 2", "RO987654321", "furnizor2@example.com", "+40987654321");

        ///////TEST CATEGORIE

        // Creare listă de categorii
        List<Categorie> listaCategorii = new ArrayList<>();

        // Adăugare categorii în listă
        listaCategorii.add(new Categorie("Cafea", "Băuturi pe bază de cafea"));
        listaCategorii.add(new Categorie("Ceaiuri", "Diverse tipuri de ceaiuri"));
        listaCategorii.add(new Categorie("Produse de patiserie", "Patiserie proaspătă"));

        // Afisare categorii
        for (Categorie categorie : listaCategorii) {
            System.out.println(categorie);
        }


        //test stoc produse cu hasmap

        // Creare unui obiect de tip Stoc
        Stoc stoc = new Stoc();

        // Adăugare unor produse în stoc
        Produs cafea2 = new Produs("Cafea", "Cafea arabica", 10.0, null, furnizor1);
        stoc.adaugaProdus(cafea2, 50); // Adaugăm 50 de unități de cafea în stoc

        Produs ceai = new Produs("Ceai", "Ceai verde", 5.0, null, furnizor2);
//        stoc.adaugaProdus(ceai, 30); // Adaugăm 30 de unități de ceai în stoc

//        // Accesarea cantității de cafea din stoc
//        int cantitateCafea = stoc.getStocProdus(cafea);
//        System.out.println("Cantitatea de cafea din stoc: " + cantitateCafea);
//
//        // Accesarea cantității de ceai din stoc
//        int cantitateCeai = stoc.getStocProdus(ceai);
//        System.out.println("Cantitatea de ceai din stoc: " + cantitateCeai);

        // Eliminarea produsului de cafea din stoc
        int cantitateCafea = stoc.getStocProdus(cafea2);
        System.out.println("Cantitatea de cafea din stoc: " + cantitateCafea);
        stoc.eliminaProdus(cafea2);
        int cantitateCafeanoua = stoc.getStocProdus(cafea2);

        // Verificarea dacă produsul de cafea a fost eliminat din stoc
        if (cantitateCafeanoua == 0) {
            System.out.println("Produsul de cafea a fost eliminat din stoc.");
        } else {
            System.out.println("Produsul de cafea nu a fost eliminat din stoc.");
        }


        //test compareTo
        Produs produs1 = new Produs("Cafea", "Descriere", 10.0, null, furnizor1);
        Produs produs2 = new Produs("Ceai", "Descriere", 8.0, null, furnizor2);

        int rezultatComparatie = produs1.compareTo(produs2);

        if (rezultatComparatie < 0) {
            System.out.println("Produsul 1 este mai mic decât produsul 2.");
        } else if (rezultatComparatie > 0) {
            System.out.println("Produsul 1 este mai mare decât produsul 2.");
        } else {
            System.out.println("Produsul 1 este egal cu produsul 2.");
        }


        ////////////////test
        // Crearea categoriilor
        Categorie alimentarCategory = new Categorie("Alimentar", "Produse alimentare");
        Categorie retailCategory = new Categorie("Retail", "Produse de uz casnic");

        // Crearea produselor alimentare
        ProdusAlimentar lapte = new ProdusAlimentar("Lapte", "Lapte proaspăt", 2.5, furnizor1, alimentarCategory, LocalDate.now().plusDays(7));
        ProdusAlimentar cafea = new ProdusAlimentar("Cafea", "Cafea măcinată", 10.0, furnizor1, alimentarCategory, LocalDate.now().plusMonths(3));

        // Crearea produsului de retail
        Retail paharPlastic = new Retail("Pahar de plastic", "Pahar de plastic 500ml", 1.0, retailCategory, furnizor1, "Transparent");

        // Crearea produsului de vânzare
        Map<Produs, Double> ingredienteCappuccino = new HashMap<>();
        ingredienteCappuccino.put(cafea, 0.3); // 300g de cafea
        ingredienteCappuccino.put(lapte, 0.7); // 700ml de lapte
        ingredienteCappuccino.put(paharPlastic, 1.0); // 1 pahar

        ProdusVanzare cappuccino = new ProdusVanzare("Cappuccino", "Băutură delicioasă", 15.0, alimentarCategory, furnizor1);
        cappuccino.setIngredienteCantitate(ingredienteCappuccino);

        // Crearea vânzării cu Cappuccino
        Map<ProdusVanzare, Double> listaProduseVanzareCantitate = new HashMap<>();
        listaProduseVanzareCantitate.put(cappuccino, 1.0); // O singură porție de Cappuccino

        Vanzare vanzare = new Vanzare(listaProduseVanzareCantitate, 15.0, LocalDate.now(), "card");

        // Afisarea vânzării
        System.out.println("Vânzare efectuată:\n" + vanzare);


        //test pt stocuri. creare si actualizare automata la o vanzare
        // Crearea categoriilor
        Categorie alimentarCategory3 = new Categorie("Alimentar", "Produse alimentare");
        Categorie retailCategory3 = new Categorie("Retail", "Produse de uz casnic");


        // Crearea produselor
        ProdusAlimentar lapte3 = new ProdusAlimentar("Lapte", "Lapte proaspăt", 2.5, furnizor1, alimentarCategory, LocalDate.now().plusDays(7));
        ProdusAlimentar cafea3 = new ProdusAlimentar("Cafea", "Cafea măcinată", 10.0, furnizor1, alimentarCategory, LocalDate.now().plusMonths(3));
        Retail paharPlastic3 = new Retail("Pahar de plastic", "Pahar de plastic 500ml", 1.0, retailCategory, furnizor2, "Transparent");
        ProdusVanzare cappuccino3 = new ProdusVanzare("Cappuccino", "Băutură delicioasă", 15.0, alimentarCategory, furnizor1);

        // Crearea stocului și adăugarea produselor
        Stoc stoc3 = new Stoc();
        stoc3.adaugaProdus(lapte, 10);
        stoc3.adaugaProdus(cafea, 15);
        stoc3.adaugaProdus(paharPlastic, 20);
        stoc3.adaugaProdus(cappuccino, 12);

        // Afisarea stocurilor
        System.out.println("Stoc initial:");
        stoc3.afiseazaStocuri();

        // Vanzarea a 3 Cappuccino
        Map<ProdusVanzare, Double> listaProduseVanzareCantitate3 = new HashMap<>();
        listaProduseVanzareCantitate3.put(cappuccino, 3.0); // Vânzare de 3 Cappuccino

        Vanzare vanzare3 = new Vanzare(listaProduseVanzareCantitate3, 15.0 * 3, LocalDate.now(), "card");

        // Actualizarea stocului după vânzare
        for (Map.Entry<ProdusVanzare, Double> entry : listaProduseVanzareCantitate3.entrySet()) {
            ProdusVanzare produsVanzare = entry.getKey();
            double cantitateVanduta = entry.getValue();
            int stocCurent = stoc3.getStocProdus(produsVanzare);
            stoc3.actualizeazaStoc(produsVanzare, stocCurent - (int) cantitateVanduta);
        }

        // Afisarea stocurilor actualizate
        System.out.println("\nStoc dupa vanzare:");
        stoc3.afiseazaStocuri();


        //test implementare categorieservice
        CategorieService categorieService = new CategorieService();

        // Adăugare categorii
        Categorie categorie1 = new Categorie("Băuturi", "Categorii de băuturi");
        Categorie categorie2 = new Categorie("Alimente", "Categorii de alimente");
        categorieService.adaugaCategorie(categorie1);
        categorieService.adaugaCategorie(categorie2);

        // Afișare categorii
        System.out.println("Categorii:");
        categorieService.displayCategories();

        // Actualizare categorie
        Categorie categorieActualizata = new Categorie("Băuturi", "Băuturi calde și reci");
        categorieService.actualizeazaCategorie(categorie1.getIdCategorie(), categorieActualizata);
        System.out.println("\nCategorii după actualizare:");
        categorieService.displayCategories();

        // Ștergere categorie
        categorieService.stergeCategorie(categorie2.getIdCategorie());
        System.out.println("\nCategorii după ștergere:");
        categorieService.displayCategories();
         */


        //incepem sa cream meniul!!!!!!!!!!!
        //ProdusService produsService1 = new ProdusService();
        Scanner scanner = new Scanner(System.in);
        CategorieService categorieService1 = new CategorieService();
        FurnizorService furnizorService1 = new FurnizorService();
        ProdusAlimentarService produsAlimentarService1 = new ProdusAlimentarService();
        RetailService retailService1 = new RetailService();
        ProdusVanzareService produsVanzareService1 = new ProdusVanzareService();
        AchizitieService achizitieService1 = new AchizitieService();
        VanzareService vanzareService1 = new VanzareService();

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
                    break;
                case 2:
                    System.out.print("Introdu id-ul categoriei de actualizat: ");
                    int idActualizare = scanner.nextInt();
                    scanner.nextLine(); // consuma newline lăsat de nextInt()
                    System.out.print("Introdu noul nume al categoriei: ");
                    String numeNou = scanner.nextLine();
                    System.out.print("Introdu noua descriere a categoriei: ");
                    String descriereNoua = scanner.nextLine();
                    Categorie categorieNoua = new Categorie(numeNou, descriereNoua);
                    categorieService1.update(idActualizare, categorieNoua);
                    break;
                case 3:
                    System.out.print("Introdu id-ul categoriei de șters: ");
                    int idStergere = scanner.nextInt();
                    categorieService1.remove(idStergere);
                    break;
                case 4:
                    categorieService1.display();
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
                    break;
                case 6:
                    System.out.println("Introdu id-ul furnizorului de actualizat: ");
                    int idFurnizorDeActualizat = scanner.nextInt();
                    scanner.nextLine();
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
                    break;
                case 7:
                    System.out.println("Introdu id-ul furnizorului de sters: ");
                    int idFurnizorDeSters = scanner.nextInt();
                    furnizorService1.remove(idFurnizorDeSters);
                    break;
                case 8:
                    furnizorService1.display();
                    break;
                case 9:
                    System.out.println("Introdu numele produsului alimentar de adaugat: ");
                    String numeProdus = scanner.nextLine();
                    System.out.println("Introdu descrierea produsului alimentar de adaugat: ");
                    String descriereProdus = scanner.nextLine();
                    System.out.println("Introdu pretul produsului alimentar de adaugat: ");
                    double pretProdus = scanner.nextDouble();
                    System.out.println("Introdu id-ul categoriei produsului alimentar: ");
                    int idCategorie = scanner.nextInt();
                    Categorie categorieProdus = categorieService1.getCategorieByID(idCategorie);
                    System.out.println("Introdu id-ul furnizorului de produs alimentar: ");
                    int idFurnizor = scanner.nextInt();
                    Furnizor furnizorProdus = furnizorService1.getFurnizorByID(idFurnizor);

//                    Produs produsNou = new Produs(numeProdus, descriereProdus, pretProdus, categorieProdus, furnizorProdus);
//                    produsService1.adaugaProdus(produsNou);

                    scanner.nextLine();
                    System.out.println("Introdu data expirarii produsului alimentar (YYYY-MM-DD):");
                    String dataExpirareString = scanner.next();
                    LocalDate dataExpirareProdus = LocalDate.parse(dataExpirareString);

                    System.out.println("Introdu cantitatea din stoc a produsului alimentar: ");
                    int stocProdus = scanner.nextInt();

                    //acum pot crea obiectul de tip ProdusAlimentar si sa-l adaug in lista sa
                    ProdusAlimentar produsAlimentarNou = new ProdusAlimentar(numeProdus, descriereProdus, pretProdus, idFurnizor, idCategorie, dataExpirareProdus, stocProdus);
                    produsAlimentarService1.add(produsAlimentarNou);

                    System.out.println("Produsul a fost adaugat cu succes!");
                    break;
                case 10:
                    System.out.println("Introdu id-ul produsului alimentar de actualizat: ");
                    int idProdusActualizat = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Introdu numele produsului alimentar de actualizat: ");
                    String numeProdusActualizat = scanner.nextLine();
                    System.out.println("Introdu descrierea produsului alimentar de actualizat: ");
                    String descriereProdusActualizat = scanner.nextLine();
                    System.out.println("Introdu pretul produsului alimentar de actualizat: ");
                    double pretProdusActualizat = scanner.nextDouble();
                    System.out.println("Introdu id-ul categoriei produsului alimentar: ");
                    int idCategorieActualizat = scanner.nextInt();
                    scanner.nextLine();
                    Categorie categorieProdusActualizat = categorieService1.getCategorieByID(idCategorieActualizat);
                    System.out.println("Introdu id-ul furnizorului produsului alimentar: ");
                    int idFurnizorActualizat = scanner.nextInt();
                    Furnizor furnizorProdusActualizat = furnizorService1.getFurnizorByID(idFurnizorActualizat);

//                    Produs produsNouDeActualizat = new Produs(numeProdusActualizat,descriereProdusActualizat,pretProdusAcrualizat,categorieProdusActualizat,furnizorProdusActualizat);
//                    produsService1.actualizeazaProdus(idProdusActualizat,produsNouDeActualizat);

                    scanner.nextLine();
                    System.out.println("Introdu data expirarii produsului (YYYY-MM-DD):");
                    String dataExpirareActualizataString = scanner.next();
                    LocalDate dataExpirareActualizataProdus = LocalDate.parse(dataExpirareActualizataString);

                    System.out.println("Introdu cantitatea din stoc a produsului alimentar de actualizat: ");
                    int stocProdusActualizat = scanner.nextInt();

                    ProdusAlimentar produsAlimentarActualizat = new ProdusAlimentar(numeProdusActualizat, descriereProdusActualizat, pretProdusActualizat, idFurnizorActualizat, idCategorieActualizat, dataExpirareActualizataProdus, stocProdusActualizat);
                    produsAlimentarService1.update(idProdusActualizat,produsAlimentarActualizat);
                    break;
                case 11:
                    System.out.println("Introdu id-ul produsului alimentar de sters: ");
                    int idProdusSters = scanner.nextInt();
                    produsAlimentarService1.remove(idProdusSters);
                    break;
                case 12:
                    produsAlimentarService1.display();
                    break;
                case 13:
                    System.out.println("Introdu numele retail-ului de adaugat: ");
                    String numeRetail = scanner.nextLine();
                    System.out.println("Introdu descrierea retail-ului de adaugat: ");
                    String descriereRetail = scanner.nextLine();
                    System.out.println("Introdu pretul retail-ului de adaugat: ");
                    double pretRetail = scanner.nextDouble();
                    System.out.println("Introdu id-ul categoriei retail-ului: ");
                    int idCategorieRetail = scanner.nextInt();
                    Categorie categorieRetail = categorieService1.getCategorieByID(idCategorieRetail);
                    System.out.println("Introdu id-ul furnizorului de retail: ");
                    int idFurnizorRetail = scanner.nextInt();
                    Furnizor furnizorRetail = furnizorService1.getFurnizorByID(idFurnizorRetail);

//                    Produs produsNou = new Produs(numeProdus, descriereProdus, pretProdus, categorieProdus, furnizorProdus);
//                    produsService1.adaugaProdus(produsNou);

                    scanner.nextLine();
                    System.out.println("Introdu materialul retail-ului: ");
                    String materialRetail = scanner.nextLine();

                    System.out.println("Introdu cantitatea din stoc a retail-ului de adaugat: ");
                    int stocRetailAdaugat = scanner.nextInt();

                    //acum pot crea obiectul de tip ProdusAlimentar si sa-l adaug in lista sa
                    Retail retailNou = new Retail(numeRetail, descriereRetail, pretRetail, idCategorieRetail, idFurnizorRetail, materialRetail, stocRetailAdaugat);
                    retailService1.add(retailNou);

                    System.out.println("Retail-ul a fost adaugat cu succes!");
                    break;
                case 14:
                    System.out.println("Introdu id-ul retail-ului de actualizat: ");
                    int idRetailActualizat = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Introdu numele retail-ului de actualizat: ");
                    String numeRetailActualizat = scanner.nextLine();
                    System.out.println("Introdu descrierea retail-ului de actualizat: ");
                    String desccriereRetailActualizat = scanner.nextLine();
                    System.out.println("Introdu pretul retail-ului de actualizat: ");
                    double pretRetailActualizat = scanner.nextDouble();
                    System.out.println("Introdu id-ul categoriei retail-ului de actualizat: ");
                    int idCategorieRetailActualizat = scanner.nextInt();
                    scanner.nextLine();
                    Categorie categorieRetailActualizat = categorieService1.getCategorieByID(idCategorieRetailActualizat);
                    System.out.println("Introdu id-ul furnizorului retail-ului de actualizat: ");
                    int idFurnizorRetailActualizat = scanner.nextInt();
                    Furnizor furnizorRetailActualizat = furnizorService1.getFurnizorByID(idFurnizorRetailActualizat);

//                    Produs produsNouDeActualizat = new Produs(numeProdusActualizat,descriereProdusActualizat,pretProdusAcrualizat,categorieProdusActualizat,furnizorProdusActualizat);
//                    produsService1.actualizeazaProdus(idProdusActualizat,produsNouDeActualizat);

                    scanner.nextLine();
                    System.out.println("Introdu materialul retail-ului de actualizat:");
                    String materialRetailActualizat = scanner.nextLine();

                    System.out.println("Introdu cantitatea din stoc a retail-ului de actualizat: ");
                    int stocRetailActualizat = scanner.nextInt();

                    Retail retailActualizat = new Retail(numeRetailActualizat, desccriereRetailActualizat, pretRetailActualizat, idCategorieRetailActualizat, idFurnizorRetailActualizat, materialRetailActualizat, stocRetailActualizat);
                    retailService1.update(idRetailActualizat,retailActualizat);
                    break;
                case 15:
                    System.out.println("Introdu id-ul retail-ului de sters: ");
                    int idRetailSters = scanner.nextInt();
                    retailService1.remove(idRetailSters);
                    break;
                case 16:
                    retailService1.display();
                    break;
                case 17:
                    System.out.println("Introdu numele produsului de vanzare: ");
                    String numeProdusVanzare = scanner.nextLine();
                    System.out.println("Introdu descrierea produsului de vanzare: ");
                    String descriereProdusVanzare = scanner.nextLine();
                    System.out.println("Introdu pretul produsului de vanzare: ");
                    double pretProdusVanzare = scanner.nextDouble();
                    System.out.println("Introdu id-ul categoriei produsului de vanzare: ");
                    int idCategorieProdusVanzare = scanner.nextInt();
                    Categorie categorieProdusVanzare = categorieService1.getCategorieByID(idCategorieProdusVanzare);

                    //trb sa fac lista de ingrediente-cantitate
                    Map<ProdusAlimentar, Double> ingredienteCantitate = new HashMap<>();
                    boolean continuaAdaugare = true;

                    while (continuaAdaugare) {
                        System.out.println("Introdu id-ul produsului alimentar din lista de ingrediente sau tasteaza 0 pentru a iesi: ");
                        int idProdusAlimentar = scanner.nextInt();
                        ProdusAlimentar produsAlimentar = produsAlimentarService1.getProdusAlimentarByID(idProdusAlimentar);

                        if(idProdusAlimentar == 0){
                            continuaAdaugare = false;
                        }

                        if (produsAlimentar != null) {
                            System.out.println("Introdu cantitatea pentru produsul alimentar din lista de ingrediente: ");
                            double cantitate = scanner.nextDouble();

                            ingredienteCantitate.put(produsAlimentar, cantitate);

                            System.out.println("Doriți să mai adăugați ingrediente? (da/nu)");
                            String raspuns = scanner.next();

                            if (!raspuns.equalsIgnoreCase("da")) {
                                continuaAdaugare = false;
                            }
                        }
                    }


                    ProdusVanzare produsVanzareNou = new ProdusVanzare(numeProdusVanzare,descriereProdusVanzare,pretProdusVanzare,idCategorieProdusVanzare,ingredienteCantitate);
                    produsVanzareService1.adaugaProdusVanzare(produsVanzareNou);
                    System.out.println(ingredienteCantitate);
                    System.out.println(produsVanzareNou);
                    System.out.println("Produsul a fost adaugat cu succes!");
                    break;
                case 18:
                    System.out.println("Introdu id-ul produsului de vanzare de actualizat: ");
                    int idProdusVanzareActualizat = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Introdu numele produsului de vanzare de actualizat: ");
                    String numeProdusVanzareActualizat = scanner.nextLine();
                    System.out.println("Introdu descrierea produsului de vanzare de actualizat: ");
                    String descriereProdusVanzareActualizat = scanner.nextLine();
                    System.out.println("Introdu pretul produsului de vanzare de actualizat: ");
                    double pretProdusVanzareActualizat = scanner.nextDouble();
                    System.out.println("Introdu id-ul categoriei produsului de vanzare de actualizat: ");
                    int idCategorieProdusVanzareActualizat = scanner.nextInt();
                    Categorie categorieProdusVanzareActualizat = categorieService1.getCategorieByID(idCategorieProdusVanzareActualizat);

                    //trb sa fac lista de ingrediente-cantitate
                    Map<ProdusAlimentar, Double> ingredienteCantitateActualizare = new HashMap<>();
                    boolean continuaAdaugareActualizare = true;

                    while (continuaAdaugareActualizare) {
                        System.out.println("Introdu id-ul produsului alimentar din lista de ingrediente sau tasteaza 0 pentru a iesi: ");
                        int idProdusAlimentarActualizare = scanner.nextInt();

                        if(idProdusAlimentarActualizare == 0){
                            continuaAdaugare = false;
                            break;
                        }

                        ProdusAlimentar produsAlimentarDeActualizare = produsAlimentarService1.getProdusAlimentarByID(idProdusAlimentarActualizare);


                        if (produsAlimentarDeActualizare != null) {
                            System.out.println("Introdu cantitatea pentru produsul alimentar din lista de ingrediente: ");
                            double cantitate = scanner.nextDouble();

                            ingredienteCantitateActualizare.put(produsAlimentarDeActualizare, cantitate);

                            System.out.println("Doriți să mai adăugați ingrediente? (da/nu)");
                            String raspuns = scanner.next();

                            if (!raspuns.equalsIgnoreCase("da")) {
                                continuaAdaugareActualizare = false;
                            }
                        }
                    }


                    ProdusVanzare produsVanzareActualizare = new ProdusVanzare(numeProdusVanzareActualizat,descriereProdusVanzareActualizat,pretProdusVanzareActualizat,idCategorieProdusVanzareActualizat,ingredienteCantitateActualizare);
                    produsVanzareService1.actualizeazaProdusVanzare(idProdusVanzareActualizat,produsVanzareActualizare);
                    System.out.println("Produsul a fost actualizat cu succes!");
                    break;
                case 19:
                    System.out.println("Introdu id-ul produsului de vanzare de sters: ");
                    int idProdusVanzareSters = scanner.nextInt();
                    produsVanzareService1.stergeProdusVanzare(idProdusVanzareSters);
                case 20:
                    produsVanzareService1.afiseazaProduseVanzare();
                    break;
                case 21:
                    System.out.println("Introdu pretul total al achizitiei: ");
                    int pretAchizitie = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Introdu data achizitiei (YYYY-MM-DD): ");
                    String dataAchizitieString = scanner.next();
                    LocalDate dataAchizitie = LocalDate.parse(dataAchizitieString);
                    scanner.nextLine();
                    System.out.println("Introdu tipul tranzactiei (cash/card): ");
                    String tipAchizitie = scanner.nextLine();

                    //trb sa fac lista de produse alimentare - cantitate
                    Map<ProdusAlimentar, Integer> listaProduseAlimentareCantitate = new HashMap<>();
                    boolean continuaAdaugareAchizitii = true;

                    while (continuaAdaugareAchizitii) {
                        System.out.println("Introdu id-ul produsului alimentar din achizitie sau tasteaza 0 pentru a iesi: ");
                        int idProdusAlimentarAchizitie = scanner.nextInt();
                        ProdusAlimentar produsAlimentarAchizitie = produsAlimentarService1.getProdusAlimentarByID(idProdusAlimentarAchizitie);

                        if(idProdusAlimentarAchizitie == 0){
                            continuaAdaugareAchizitii = false;
                        }

                        if (produsAlimentarAchizitie != null) {
                            System.out.println("Introdu cantitatea pentru produsul alimentar: ");
                            Integer cantitateAchizitie = scanner.nextInt();
                            scanner.nextLine();

                            listaProduseAlimentareCantitate.put(produsAlimentarAchizitie, cantitateAchizitie);

                            System.out.println("Doriți să mai adăugați produse in achizitie? (da/nu)");
                            String raspunsAchizitie = scanner.next();

                            if (!raspunsAchizitie.equalsIgnoreCase("da")) {
                                continuaAdaugareAchizitii = false;
                            }
                        }
                    }


                    //trb sa fac lista de produse retail - cantitate
                    Map<Retail, Integer> listaRetailCantitate = new HashMap<>();
                    boolean continuaAdaugareAchizitiiRetail = true;

                    while (continuaAdaugareAchizitiiRetail) {
                        System.out.println("Introdu id-ul retail-ului din achizitie sau tasteaza 0 pentru a iesi: ");
                        int idRetailAchizitie = scanner.nextInt();
                        // Obțineți produsul alimentar folosind id-ul introdus
                        Retail retailAchizitie = retailService1.getRetailByID(idRetailAchizitie);

                        if(idRetailAchizitie == 0){
                            continuaAdaugareAchizitiiRetail = false;
                        }

                        if (retailAchizitie != null) {
                            System.out.println("Introdu cantitatea pentru retail-ul achizitionat: ");
                            Integer cantitateAchizitieRetail = scanner.nextInt();
                            scanner.nextLine();

                            listaRetailCantitate.put(retailAchizitie, cantitateAchizitieRetail);

                            System.out.println("Doriți să mai adăugați retail in achizitie? (da/nu)");
                            String raspunsAchizitieRetail = scanner.next();

                            if (!raspunsAchizitieRetail.equalsIgnoreCase("da")) {
                                continuaAdaugareAchizitiiRetail = false;
                            }
                        }
                    }

                    Achizitie achizitieNoua = new Achizitie(listaProduseAlimentareCantitate,listaRetailCantitate,pretAchizitie,dataAchizitie,tipAchizitie);
                    achizitieService1.adaugaAchizitie(achizitieNoua);
                    System.out.println("Achizitia a fost adaugata cu succes!");
                    break;
                case 22:
                    System.out.println("Introdu id-ul achizitiei de sters: ");
                    int idAchizitieSters = scanner.nextInt();
                    achizitieService1.stergeAchizitie(idAchizitieSters);
                    break;
                case 23:
                    achizitieService1.afiseazaAchizitii();
                    break;
                case 24:
                    System.out.println("Introdu pretul total al vanzarii: ");
                    int pretVanzare = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Introdu data vanzarii (YYYY-MM-DD): ");
                    String dataVanzareString = scanner.next();
                    LocalDate dataVanzare = LocalDate.parse(dataVanzareString);
                    scanner.nextLine();
                    System.out.println("Introdu tipul tranzactiei (cash/card): ");
                    String tipVanzare = scanner.nextLine();

                    //trb sa fac lista de produse vanzare - cantitate
                    Map<ProdusVanzare, Integer> listaProduseVanzareCantitate = new HashMap<>();
                    boolean continuaAdaugareVanzari = true;

                    while (continuaAdaugareVanzari) {
                        System.out.println("Introdu id-ul produsului de vanzare din vanzare sau tasteaza 0 pentru a iesi: ");
                        int idProdusVanzareVanzare = scanner.nextInt();
                        ProdusVanzare produsVanzareVanzare = produsVanzareService1.getProdusVanzareByID(idProdusVanzareVanzare);

                        if(idProdusVanzareVanzare == 0){
                            continuaAdaugareVanzari = false;
                        }

                        if (produsVanzareVanzare != null) {
                            System.out.println("Introdu cantitatea pentru produsul de vanzare: ");
                            Integer cantitateVanzare = scanner.nextInt();
                            scanner.nextLine();

                            listaProduseVanzareCantitate.put(produsVanzareVanzare, cantitateVanzare);

                            System.out.println("Doriți să mai adăugați produse in achizitie? (da/nu)");
                            String raspunsVanzare = scanner.next();

                            if (!raspunsVanzare.equalsIgnoreCase("da")) {
                                continuaAdaugareVanzari = false;
                            }
                        }
                    }

                    Vanzare vanzareNoua = new Vanzare(listaProduseVanzareCantitate,pretVanzare,dataVanzare,tipVanzare);
                    vanzareService1.adaugaVanzare(vanzareNoua);
                    System.out.println("Vanzarea a fost adaugata cu succes!");
                    break;
                case 25:
                    System.out.println("Introdu id-ul vanzarii de sters: ");
                    int idVanzareSters = scanner.nextInt();
                    vanzareService1.stergeVanzare(idVanzareSters);
                    break;
                case 26:
                    vanzareService1.afiseazaVanzari();
                    break;
                default:
                    System.out.println("Opțiune invalidă!");
            }

        }

        //to do: cand sterg o categorie/, sa se actualizeze si produsele care au categoria respectiva
        //to do: sa nu mai lasi sa ceara toate detaliile despre un produs de actualizat daca nu l gaseste
        //ai grija: atunci cand actualizez un produs, id-ul creste automat
        //ai grija: atunci cand adaugam produse - alim 1, alim 2, retail 3, vanzare 4, alim 5
        //intrebare: care e treaba cu lista de produse, produse alim si retail
    }
}
