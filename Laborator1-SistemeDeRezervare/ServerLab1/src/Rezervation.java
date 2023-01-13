
public class Rezervation {
    public static int seats[]={0,0,0,0,0,0,0,0,0,0};
    public static int seats1[]=new int[10];

public static String RezervSeat(int seat){
   String result;
    if(seats[seat] != 0)
        return result="Pozitia "+seat+" este deja ocupoata";
    seats[seat]=1;
   return result="Ai rezervat pozitia "+seat;
}
public static String Connect(String name){
    String result="Hello "+name;
  
    return result;
}
public static String GetSeats(){
    String result="Seats: ";
   for(int i=0;i<seats.length;i++){
       result=result + seats[i]+" ";
   }
   return result;
}

}
