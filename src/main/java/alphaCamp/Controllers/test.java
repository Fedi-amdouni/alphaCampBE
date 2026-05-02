package alphaCamp.Controllers;

public class test {

    public String testo(String ch) {
        String retour = String.valueOf(ch.charAt(0));
        retour += ch.length()-1;
        retour += ch.charAt(ch.length()-1);
        return retour;
    }

}