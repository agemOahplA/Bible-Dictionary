//package org.cruciata.dictserver.tool;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import sun.lwawt.macosx.CSystemTray;
//
//import java.io.*;
//import java.net.URL;
//
//public class JsonToSql {
//
//    static ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//
//    public static void main(String[] args) {
//
//        JSONArray objects = JSONArray.parseArray(readJson());
//        writeSQL(createSQL(objects));
//
//    }
//
//
//    private static String readJson(){
//
//        URL resource = classLoader.getResource("BibleDictionary.json");
//        File file =new File(resource.getFile());
//        try {
//            FileReader fileReader = new FileReader(file);
//            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
//            int ch = 0;
//            StringBuffer sb = new StringBuffer();
//            while ((ch = inputStreamReader.read()) != -1){
//                sb.append((char) ch);
//            }
//            inputStreamReader.close();
//            return sb.toString();
//        } catch (FileNotFoundException | UnsupportedEncodingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
//    private static StringBuffer createSQL(JSONArray jsonArray){
//        StringBuffer sb = new StringBuffer();
//        sb.append("INSERT INTO ");
//        sb.append("dict(id,word,wordEnglish,wordLatin,explanation) ");
//        sb.append("VALUES");
//        for (Object o : jsonArray) {
//            JSONObject jsonObject = (JSONObject)o;
//            sb.append("('");
//            sb.append(((JSONObject)jsonObject.get("_id")).get("$oid"));
//            sb.append("','");
//            sb.append(jsonObject.getString("word")!=null?jsonObject.getString("word").replace("'","\\'"):"");
//            sb.append("','");
//            sb.append(jsonObject.getString("wordEnglish")!=null?jsonObject.getString("wordEnglish").replace("'","\\'"):"");
//            sb.append("','");
//            sb.append(jsonObject.getString("wordLatin")!=null?jsonObject.getString("wordLatin").replace("'","\\'"):"");
//            sb.append("','");
//            sb.append(jsonObject.getString("explanation")!=null?jsonObject.getString("explanation").replace("'","\\'"):"");
//            sb.append("')");
//            sb.append(",");
//            sb.append("\n");
//        }
//        sb.replace(sb.length()-2,sb.length()-1,";");
//        System.out.println(sb.toString());
//        return sb;
//    }
//
//    private static void writeSQL(StringBuffer sql){
//        URL resource = classLoader.getResource("db/schema.sql");
//        try {
//            BufferedWriter out = new BufferedWriter(new FileWriter(resource.getFile()));
//            out.write(sql.toString());
//            out.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
