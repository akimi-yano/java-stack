// Tasks:
import java.util.HashMap;
import java.util.Set;
public class Hashmatique{
    public static void main(String[] args){
        
// ● Create a trackList of type HashMap
HashMap<String, String> trackList = new HashMap <String, String>();

// ● Add in at least 4 songs that are stored by title
trackList.put("birthdaySongs", "happyBirthdayToMe");
trackList.put("allStar", "somebodyToldMeThat");
trackList.put("whatMakesYouBeautiful", "that'sWhatMakesYouBeautiful");
trackList.put("gangnamStyle", "oppaGangnamStyle");

// ● Pull out one of the songs by its track title
String song = trackList.get("gangnamStyle");
System.out.println("My fav song is: " + song);

// ● Print out all the track names and lyrics in the format Track: Lyrics
Set<String> keys= trackList.keySet();
for (String key: keys){
    System.out.println(key + " : " + trackList.get(key));
} 
}
}

