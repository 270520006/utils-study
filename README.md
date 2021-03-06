# utils-study

工具包的使用现包括：

* ssh2(远程操作linux虚拟机)
* jsoup(java的一个爬虫包)



## ssh2

* 导入依赖

```xml
  <dependency>
            <groupId>ch.ethz.ganymed</groupId>
            <artifactId>ganymed-ssh2</artifactId>
            <version>262</version>
        </dependency>
```

* 直接使用

```java
@SpringBootTest
class Ssh2StudyApplicationTests {
    final static String IP="192.168.56.101";
    final static String USERNAME="root";
    final static String PASSWORD="zspzsp123";
    final static String PATH="/home";

    @Test
    void testCMD() throws IOException {
        Connection connection = new Connection(IP);
        connection.connect();
        connection.authenticateWithPassword(USERNAME,PASSWORD);
        Session session = connection.openSession();
        session.execCommand("docker ps");
        InputStream is = new StreamGobbler(session.getStdout());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            System.out.println(line);
        }
    }


    @Test
    void testScandCMD() throws IOException {
        Connection connection = new Connection(IP);
        connection.connect();
        connection.authenticateWithPassword(USERNAME,PASSWORD);
        Session session = connection.openSession();

        Scanner in=new Scanner(System.in);
        while(in.hasNextInt())
        {//nextLine()接收句子；next()接收单个字符；nextInt()返回Int型
            String cmd = in.nextLine();

            session.execCommand(cmd);
            InputStream is = new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (String line = br.readLine(); line != null; line = br.readLine()) {
                System.out.println(line);
            }
            /***********/
        }
        if (session != null) {
            session.close();
        }
        session.close();

    }

}
```

## jsoup

* 导入依赖

```xml
 <dependency>
            <groupId>org.jsoup</groupId>
            <artifactId>jsoup</artifactId>
            <version>1.10.2</version>
        </dependency>
```

* 写个实体类来记录商品信息

```java
public class GoodList {
    private String title;
    private String  img;
    private String price;

    public GoodList(String title, String img, String price) {
        this.title = title;
        this.img = img;
        this.price = price;
    }

    @Override
    public String toString() {
        return "GoodList{" +
                "title='" + title + '\'' +
                ", img='" + img + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
```

* 使用它

```java
public class JsoupDemo {
    public static void main(String[] args) throws IOException {
        String keyword="键盘";
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        Document document = Jsoup.parse(new URL(url), 30000);
        Element elemet = document.getElementById("J_goodsList");
        Elements elements = elemet.getElementsByTag("li");
        ArrayList<GoodList> list = new ArrayList<>();
        for (Element el : elements) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            list.add(new GoodList(title,img, price ));
        }
        for (GoodList goodList : list) {
            System.out.println(goodList);
        }
    }
    }
```

