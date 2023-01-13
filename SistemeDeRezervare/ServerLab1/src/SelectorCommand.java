public class SelectorCommand {
public static String[] convertedCommand;
    public static void ConvertCommand(String command){
        convertedCommand=command.split("\\s");
        for (int x=0; x<convertedCommand.length; x++) {
            System.out.println(convertedCommand[x]);
        }
    }
    public static String SelectCommand(){
        if( convertedCommand[0].equals("AUT")){
      System.out.println(convertedCommand[0]);
            return Rezervation.Connect(convertedCommand[1]);
        }
        else
        if(convertedCommand[0].equals("REZ"))
     return  Rezervation.RezervSeat(Integer.parseInt(convertedCommand[1]));
      else
        if(convertedCommand[0].equals("LST"))
      return Rezervation.GetSeats();
else {
            System.out.println(convertedCommand[0]);
            return "Please enter a correct command";
        }
        }
}
