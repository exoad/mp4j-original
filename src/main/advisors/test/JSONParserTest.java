package main.advisors.test;

import main.advisors.JSONParser;

public class JSONParserTest {
  public static void TestOne() {
    final String JSON = "\"{\"name\":\"test\",\"version\":\"1.0.0\"}\"";
    final String name = "name";
    final String version = "version";
    final String nameValue = "test";
    System.out.println(JSONParser.parseElement(name, JSON));
  }
  public static void main(String[] args) {
    TestOne();
  }
}
