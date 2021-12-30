package org.cruciata.dictserver.tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.cruciata.dictserver.tool.entity.Node;

import java.util.ArrayList;
import java.util.List;

public class Test01 {
    

    
    public static void main(String[] args) {


        createNode(2047, 2051   ,
                "2047.  道德生活是属神的敬礼。基督徒的行为在礼仪和圣事的举行中获得\n" +
                        "\t\t滋养。 \n" +
                        "\t\t \n" +
                        "\t\t2048. 教会的规条涉及道德与基督徒的生活；这种生活与礼仪相结合，并\n" +
                        "\t\t由礼仪得到滋养。 \n" +
                        "\t\t   \n" +
                        "\t\t2049. 教会牧人有关道德的训导权通常是在教理讲授及讲道中行使，而以\n" +
                        "\t\t天主十诫为根基，因为十诫宣示为每个人都有效的道德生活原则。 \n" +
                        "\t\t   \n" +
                        "\t\t2050. 罗马教宗和主教们，以真正导师的身份，向天主子民宣讲当信和在\n" +
                        "\t\t生活中当实行的道理。对有关自然律及理性的道德问题表示立场，也属于他们的职权。   \n" +
                        "\t\t2051. 牧人的训导权的不能错误，涵盖道理的一切要素，包括道德的问题，\n" +
                        "\t\t若没有这些要素，信德的救恩真理就无法维护、宣示或遵守。");
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
