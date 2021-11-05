package org.cruciata.dictserver.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class Test01 {
    

    
    public static void main(String[] args) {


        int start = 629;
        int end = 630;
        String str = "629.  耶稣为造福每个人而尝到了死亡的滋味，死而被埋葬的那一位确是降\n" +
                "\t\t\t生成人的天主子。   \n" +
                "\t\t\t \n" +
                "\t\t\t630.  在基督被埋在墓中的期间，祂天主性的位格仍继续统摄著被死亡所分\n" +
                "\t\t\t离的灵魂和肉身。为此，基督死后的身体，却“没有见到腐朽”(宗 \n" +
                "\t\t\t13:37)。";


        str = str.replaceAll("\t","").replaceAll("\n","");


        List<Mode> json = new ArrayList<>();
        for(int i= start;i<=end;i++){

            String substring;
            if(i+1>end){
                substring = str.substring(str.indexOf(start + "."));
            }else {
                substring = str.substring(str.indexOf(start + "."), str.indexOf(i + 1 + "."));
            }
            json.add(new Mode(substring));
            start +=1;
        }
        System.out.println(JSON.toJSONString(json));


    }



    static class Mode{
        private String key;
        private String name;
        private String type = "text";
        private String parent;
        private boolean leaf = true;
        public Mode(String name){
            this.name = name;
        }
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }

        public boolean isLeaf() {
            return leaf;
        }

        public void setLeaf(boolean leaf) {
            this.leaf = leaf;
        }
    }
}
