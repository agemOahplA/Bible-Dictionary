package org.cruciata.dictserver.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.cruciata.dictserver.tool.entity.Node;

import java.util.ArrayList;
import java.util.List;

public class Test01 {
    

    
    public static void main(String[] args) {


        createNode(1207, 1209,
                "1207. 礼仪庆典勉力以教会所在地的人民的文化来表达，这是适当的做\n" +
                        "\t\t\t\t\t法；但不可屈就于文化之下。从另一角度看，礼仪本身也能产生并\n" +
                        "\t\t\t\t\t塑造文化。   \n" +
                        "\t\t\t\t\t \n" +
                        "\t\t\t\t\t1208. 经合法承认的各种不同礼仪传统或仪式，由于它们都标志并通传同\n" +
                        "\t\t\t\t\t一的基督奥迹，而显示教会的大公性。 \n" +
                        "\t\t\t\t\t   \n" +
                        "\t\t\t\t\t1209. 确保礼仪传统“一体多元化”的准则，就是：忠于宗徒的圣传，亦\n" +
                        "\t\t\t\t\t即在宗徒传下来的信仰和圣事上共融，这共融是借着宗徒继承而得\n" +
                        "\t\t\t\t\t到阐明和保证的。");
    }

    private static void createNode(int start,int end,String str) {
//        int start = 604;
//        int end = 605;
//        String str = "604.  天主为了我们的罪而把自己的儿子交出时，显示了祂对我们的计划是\n" +
//                "一项慈爱的计划，先于我们的任何功绩。“爱就在于此：不是我们爱\n" +
//                "了天主，而是祂爱了我们，且打发自己的儿子，为我们做赎罪祭”(若\n" +
//                "一 4:10)。“基督在我们还是罪人的时候，就为我们死了，这证明了天\n" +
//                "主怎样爱我们”(罗 5:8)。   \n" +
//                " \n" +
//                "605.  这爱不是排斥性的，耶稣在亡羊比喻的结论中说过：“同样，使这些\n" +
//                "小子中的一个丧亡，决不是你们在天之父的意愿”(玛 18:14)。祂强\n" +
//                "调“交出自己的生命，是为大众作赎价”(玛 20:28)；这“大众”一\n" +
//                "词并不表示有所限制，只是把整个人类与舍生救他们的唯一救赎者相\n" +
//                "对而已。教会追随宗徒们的训诲而宣认，基督为所有人而死，没有一\n" +
//                "个例外，“无论过去、现在或将来，没有一个人，基督没有为他受过\n" +
//                "苦的”。 ";


        str = str.replaceAll("\t","").replaceAll("\n","");


        List<Node> json = new ArrayList<>();
        for(int i= start;i<=end;i++){

            String substring;
            if(i+1>end){
                substring = str.substring(str.indexOf(start + "."));
            }else {
                substring = str.substring(str.indexOf(start + "."), str.indexOf(i + 1 + "."));
            }
            Node node = new Node();
            node.setName(substring);
            node.setLeaf(true);
            node.setType("text");
            json.add(node);
            start +=1;
        }
        System.out.println(JSON.toJSONString(json));
    }

}
