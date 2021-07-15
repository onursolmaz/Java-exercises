package com.company.FlightTicketReservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class baseReservation {

    int seatCount;

    boolean seats[];

    static Scanner scanner = new Scanner(System.in);

    public void baseReservation(int seatCount) {
        setSeatCount(seatCount);
    }

    // if seat count is smaller than 10, default seat count=10
    public void setSeatCount(int seatCount) {
        if (seatCount < 10) {
            this.seatCount = 10;
            System.out.println("Koltuk sayısı hatalı otamatik olarak 10 yapıldı.");
        } else
            this.seatCount = seatCount;

        this.seats = new boolean[this.seatCount];
    }

    public int getSeatCount() {
        return this.seatCount;
    }


    public void reservation(String selectAction) {
        String message = selectAction.equals("1") ? "THY" : "PEGASUS";
        System.out.println(message + " rezervasyon sistemine hoşgeldiniz");

        if (!getAirplaneStatus())
            System.out.println("Malesef tüm koltuklarımız doludur");

        else {
            System.out.println("Bussiness için 1'e Economy için 2'ye basınız, bir önceki işleme dönmek herhangi bir tuşa basınız");
            String businessOrEconomy = scanner.next();

            if (businessOrEconomy.equals("1") || businessOrEconomy.equals("2")) {
                List<Integer> seatList = getEmptySeats(businessOrEconomy); // seçilen işleme göre koltuları getiriyor
                selectSeat(seatList, businessOrEconomy);
            } else
                System.out.println("Çıkış yapılıyor lütfen bekleyiniz...");
        }
    }

    // koltuk seçme ve bilet alma işlemleri
    private void selectSeat(List<Integer> seatList, String businessOrEconomy) {
        String message = businessOrEconomy.equals("1") ? "Business" : "Economy";
        if (isEmpty(seatList)) {
            showEmptySeat(seatList);
            int selectedSeat = scanner.nextInt();
            getTicket(selectedSeat);
        } else
            System.out.println(message + " için tüm koltuklarımız malesef doludur!!!");
    }


    private void getTicket(int selectedSeat) {
        // geçerli aralıkta koltuk numarası ve seçilen koltuk boş mu kontrolü
        if (selectedSeat > 0 && selectedSeat <= this.seatCount && !seats[selectedSeat - 1]) {
            seats[selectedSeat - 1] = true;
            System.out.println(selectedSeat + " nolu koltuk başarıyla rezerve edildi!");
        } else
            System.out.println("Yanlış koltuk numarası girildi. !!!");

    }

    public boolean isEmpty(List<Integer> seatList) {

        return seatList.size() > 0 ? true : false;

    }

    public void showEmptySeat(List<Integer> emptySeats) {
        System.out.println("Aşağıdaki bol koltuklardan birini seçiniz.");
        for (int i : emptySeats) {
            System.out.print((i + 1) + " ");
        }
        System.out.println("");
    }


    public boolean getAirplaneStatus() {
        for (int i = 0; i < seats.length; i++) {
            if (!seats[i])
                return true;
        }
        return false;
    }

    public List<Integer> getEmptySeats(String businessOrEconomy) {
        List<Integer> emptySeats = new ArrayList<>();
        int businessOrEconomyLimit = businessOrEconomy.equals("1") ? 4 : this.seatCount - 1;
        int startIndex = businessOrEconomy.equals("1") ? 0 : 5;
        for (int i = startIndex; i <= businessOrEconomyLimit; i++) {
            if (!seats[i]) {
                emptySeats.add(i);
            }
        }
        return emptySeats;
    }

//    public List<Integer> getEmptySeatsForEconomy() {
//        List<Integer> businessStatus = new ArrayList<>();
//        for (int i = 5 ; i < this.seatCount ;i++){
//            if (!seats[i]){
//                businessStatus.add(i);
//            }
//        }
//        return businessStatus;
//    }


}
