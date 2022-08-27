package xmlBao;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class parseXml {
    public static void main(String[] args) throws DocumentException {

        //获取一个解析器对象
        SAXReader saxReader = new SAXReader();
        //利用解析器把xml文件加载到内存中,并返回一个文档对象
        Document document = saxReader.read(new File("XMLModule\\xml\\student.xml"));
        //获取根标签
        Element rootElement = document.getRootElement();
        //通过根标签获取student子标签
        //elements():可以获取调用者所有的子标签.会把这些子标签放到一个集合中返回.
        //elements("标签名"):可以获取调用者所有的指定的子标签,会把这些子标签放到一个集合中并返回
        List<Element> studentElements = rootElement.elements("student");

        //创建集合,装学生对象
        ArrayList<Student> studentArrayList = new ArrayList<>();

        //遍历集合，得到每一个student子标签
        for (Element studentElement : studentElements) {

            //获取id这个属性
            Attribute id = studentElement.attribute("id");
            //获取id属性值
            String idValue = id.getValue();

            //获取name标签
            //element("标签名"):获取调用者指定的子标签
            Element nameElement = studentElement.element("name");
            //获取这个标签的标签体内容
            String nameElementText = nameElement.getText();

            Element ageElement = studentElement.element("age");
            String ageElementText = ageElement.getText();

            //创建学生对象，并且根据学生类中的带参构造，将id属性值、name、age属性值传入，给当前遍历到的学生对象赋值
            Student s = new Student(idValue, nameElementText, Integer.parseInt(ageElementText));

            //将学生对象添加到集合
            studentArrayList.add(s);

        }

        //遍历集合，获取Student对象
        for (Student student : studentArrayList) {
            System.out.println(student);
        }

    }
}
