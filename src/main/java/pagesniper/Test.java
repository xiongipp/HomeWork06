package pagesniper;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Test {
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet("https://www.w3.org/Consortium/Member/List");//get请求
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity=response.getEntity();//获取返回实体
        String content = EntityUtils.toString(entity, "utf-8");
        response.close();
        Document doc= Jsoup.parse(content);//转为文档对象
        Elements elements=doc.getElementsByClass("member");//获取member类的元素
        StringBuilder SB=new StringBuilder();
        System.out.println(SB);
        for(Element element:elements){
            SB.append(element.text());
            SB.append("\n");
        }
        System.out.println(SB);
        File file=new File("E:\\123.txt");
        FileWriter fileWriter=new FileWriter(file);
        fileWriter.write(String.valueOf(SB));
        fileWriter.close();

    }
}
