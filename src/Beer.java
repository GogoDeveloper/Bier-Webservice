public class Beer {

    String id;
    String name;
    String description;
    int idStyle;

    //toString Method will be used to concatenate Strings splitted by a semicolon
    public String toString(String id, String name, String description, int idStyle)
    {
        //resultString will be returned
        String resultString = "";

        //int to String conversion
        Integer.toString(idStyle);
        resultString = id + " ; " + name + " ; " + description + " ; " + idStyle;

        return resultString;
    }
}
