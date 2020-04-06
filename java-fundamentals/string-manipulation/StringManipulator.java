public class StringManipulator{
    public String trimAndConcat(String str1, String str2){
        String str3 = str1.trim() + str2.trim();
        return str3;
    }

    public Integer getIndexOrNull(String word, char letter){
        int index = word.indexOf(letter);
        if (index<0){
            return null;
        }
        return index;
    }
    public Integer getIndexOrNull(String word, String sub){
        int index = word.indexOf(sub);
        if (index<0){
            return null;
        }
        return index;
    }
    public String concatSubstring(String word1, int start, int end, String word2){
        String newWord = word1.substring(start, end).concat(word2);
        return newWord;
    }
}