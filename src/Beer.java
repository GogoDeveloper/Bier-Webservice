public class Beer {

    String id;
    String name;
    String description;
    int idStyle;


    public String toString(String id, String name, String description, int idStyle)
    {
        String resultString = "";

        Integer.toString(idStyle);
        resultString = id + " ; " + name + " ; " + description + " ; " + idStyle;

        return resultString;
    }
}
