package nomelin;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileInput {
    public static List<List<String>> readAndParseFile(String filePath) {
        List<List<String>> nestedList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                List<String> dataList = new ArrayList<>();
                Collections.addAll(dataList, attributes);
                nestedList.add(dataList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("[FI]读取文件成功：" + filePath);
        return nestedList;
    }
}
