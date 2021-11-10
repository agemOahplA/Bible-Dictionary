package org.cruciata.dictserver.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.cruciata.dictserver.tool.entity.Node;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Test02 {

    // 内容
    private static List<Node> list = new ArrayList<>();
    // 子标题
    private static List<Node> subTitle = new ArrayList<>();
    // 标题
    private static List<Node> title = new ArrayList<>();
    // 节
    private static List<Node> section = new ArrayList<>();
    // 条
    private static List<Node> article = new ArrayList<>();
    // 章
    private static List<Node> chapter = new ArrayList<>();
    // 部分
    private static List<Node> part = new ArrayList<>();
    // 卷
    private static List<Node> volume = new ArrayList<>();


    public static void main(String[] args) {

        String str = readJson();

        JSONObject jsonObject = JSON.parseObject(str);

        Node node = toNode(jsonObject);
        System.out.println(JSON.toJSONString(node));

    }

    private static Node toNode(JSONObject jsonObject) {
        Node node = new Node();

        if (jsonObject.containsKey("id")) {
            node.setId(jsonObject.getString("id"));
        }

        if (jsonObject.containsKey("parentId")) {
            node.setParentId(jsonObject.getString("parentId"));
        }

        if (jsonObject.containsKey("leaf")) {
            node.setLeaf(jsonObject.getBoolean("leaf"));
        }

        if (jsonObject.containsKey("type")) {
            node.setType(jsonObject.getString("type"));
        }

        if (jsonObject.containsKey("name")) {

            String name = jsonObject.getString("name").replaceAll(" ", " ");

            if ("text".equals(node.getType())) {
                node.setName(name);
                if (!node.getName().equals("“父啊！……永生就是：认识祢，唯一的真天主，和祢所派遣来的耶稣基督”(若 17:3)。“我们的救主天主，……愿意所有的人都得救，并得以认识真理”(弟前 2:3‐4)。除了耶稣的名字外，“在天下人间，没有赐下别的名字，使我们赖以得救的”(宗 4:12)。")) {

                    String id = name.substring(0, name.indexOf(".")).trim();

                    node.setId(id);

                    // 校验
                    if (!node.getId().equals(String.valueOf(list.size()))) {
                        System.out.println("校验不通过 有遗漏" + node.getId() + "=" + list.size());
                    }
                }

                list.add(node);
            } else {
                node.setName(name);
            }

            if ("sub-title".equals(node.getType())) {
                subTitle.add(node);
            }
            if ("title".equals(node.getType())) {
                title.add(node);
            }
            if ("section".equals(node.getType())) {
                section.add(node);
            }
            if ("article".equals(node.getType())) {
                article.add(node);
            }
            if ("chapter".equals(node.getType())) {
                chapter.add(node);
            }
            if ("part".equals(node.getType())) {
                part.add(node);
            }
            if ("volume".equals(node.getType())) {
                volume.add(node);
            }
            if ("book".equals(node.getType())) {
                node.setId("0");
            }
        }


        if (jsonObject.containsKey("children")) {
            JSONArray jsonArray = jsonObject.getJSONArray("children");
            recursion(jsonArray, node);
        } else {
            node.setLeaf(true);
        }

        return node;
    }

    private static void recursion(JSONArray jsonArray, Node node) {
        for (int i = 0; i < jsonArray.size(); i++) {

            Object object = jsonArray.get(i);
            JSONObject json = (JSONObject) object;
            json.put("id", node.getId() + "-" + i);

            Node child = toNode(json);
            // 父ID
            child.setParentId(node.getId());

            node.getChildren().add(child);
        }

    }


    private static String readJson() {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL resource = classLoader.getResource("structure2.json");
        // 生成环境 resource 等于null 从docker volumes 读取BibleDictionary.json
        String filePath = resource == null ? "/data/dictserver/structure2.json" : resource.getFile();
        File file = new File(filePath);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), "utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = inputStreamReader.read()) != -1) {
                sb.append((char) ch);
            }
            inputStreamReader.close();
            return sb.toString().replaceAll("$oid", "");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String();
    }
}
