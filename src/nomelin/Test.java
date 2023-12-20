package nomelin;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        List<List<String>> lists=FileInput.readAndParseFile("src/resources/成绩.txt");
        Set set=new HashSet<>();
        for(List<String> dataList: lists) {
            set.add(dataList.get(1));
        }
        System.out.println(set);
    }

}
