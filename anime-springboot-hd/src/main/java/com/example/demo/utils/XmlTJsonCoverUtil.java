//package com.example.demo.utils;
//
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import org.apache.commons.lang3.StringUtils;
//import org.dom4j.*;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * XML和JSON互转
// * @author nanhs
// * @version 1.0
// * @date 2022-10-31 15:46
// * @description CoverUtil
// */
//public class XmlTJsonCoverUtil {
//
//    /**
//     * <!--xml解析-->
//     *         <dependency>
//     *             <groupId>dom4j</groupId>
//     *             <artifactId>dom4j</artifactId>
//     *             <version>1.6.1</version>
//     *         </dependency>
//     *
//     *         <dependency>
//     *             <groupId>com.alibaba</groupId>
//     *             <artifactId>fastjson</artifactId>
//     *             <version>1.1.36</version>
//     *
//     *         </dependency>
//     */
//
//    /**
//     * XML转JSON
//     * @param xmlStr
//     * @return
//     */
//    public static JSONObject xmlToJson(String xmlStr){
//        Document doc = null;
//        try {
//            doc = DocumentHelper.parseText(xmlStr);
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }
//        JSONObject json = new JSONObject();
//        dom4j2Json(doc.getRootElement(), json);
//        return json;
//    }
//
//    /**
//     * xml转json
//     *
//     * @param element
//     * @param json
//     */
//    private static void dom4j2Json(Element element, JSONObject json) {
//        // 如果是属性
//        for (Object o : element.attributes()) {
//            Attribute attr = (Attribute) o;
//            if (StringUtils.isNotBlank(attr.getValue())) {
//                json.put("@" + attr.getName(), attr.getValue());
//            }
//        }
//        List<Element> chdEl = element.elements();
//        if (chdEl.isEmpty() && StringUtils.isNotBlank(element.getText())) {// 如果没有子元素,只有一个值
//            json.put(element.getName(), element.getText());
//        }
//        for (Element e : chdEl) {// 有子元素
//            if (!e.elements().isEmpty()) {// 子元素也有子元素
//                JSONObject chdjson = new JSONObject();
//                dom4j2Json(e, chdjson);
//                Object o = json.get(e.getName());
//                if (o != null) {
//                    JSONArray jsona = null;
//                    if (o instanceof JSONObject) {// 如果此元素已存在,则转为jsonArray
//                        JSONObject jsono = (JSONObject) o;
//                        json.remove(e.getName());
//                        jsona = new JSONArray();
//                        jsona.add(jsono);
//                        jsona.add(chdjson);
//                    }
//                    if (o instanceof JSONArray) {
//                        jsona = (JSONArray) o;
//                        jsona.add(chdjson);
//                    }
//                    json.put(e.getName(), jsona);
//                } else {
//                    if (!chdjson.isEmpty()) {
//                        json.put(e.getName(), chdjson);
//                    }
//                }
//            } else {// 子元素没有子元素
//                for (Object o : element.attributes()) {
//                    Attribute attr = (Attribute) o;
//                    if (StringUtils.isNotBlank(attr.getValue())) {
//                        json.put("@" + attr.getName(), attr.getValue());
//                    }
//                }
//                if (!e.getText().isEmpty()) {
//                    json.put(e.getName(), e.getText());
//                }
//            }
//        }
//    }
//
//
//    /**
//     * JSON转XML
//     * @param json
//     * @return
//     */
//    public static String jsonToXml(String json, Element parentElement){
//        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
//        //JsonObject jsonObject = new JsonObject().getAsJsonObject(json);
//        Element ee = toXml(jsonObject, parentElement, null);
//        return ee.asXML();
//    }
//
//    /**
//     * 将json字符串转换成xml
//     * @param jsonElement 待解析json对象元素
//     * @param parentElement 上一层xml的dom对象
//     * @param name 父节点
//     */
//    public static Element toXml(JsonElement jsonElement, Element parentElement, String name) {
//        if (jsonElement instanceof JsonArray) {
//            //是json数据，需继续解析
//            JsonArray sonJsonArray = (JsonArray)jsonElement;
//            for (int i = 0; i < sonJsonArray.size(); i++) {
//                JsonElement arrayElement = sonJsonArray.get(i);
//                toXml(arrayElement, parentElement, name);
//            }
//        }else if (jsonElement instanceof JsonObject) {
//            //说明是一个json对象字符串，需要继续解析
//            JsonObject sonJsonObject = (JsonObject) jsonElement;
//            Element currentElement = null;
//            if (name != null) {
//                currentElement = parentElement.addElement(name);
//            }
//            Set<Map.Entry<String, JsonElement>> set = sonJsonObject.entrySet();
//            for (Map.Entry<String, JsonElement> s : set) {
//                toXml(s.getValue(), currentElement != null ? currentElement : parentElement, s.getKey());
//            }
//        } else {
//            //说明是一个键值对的key,可以作为节点插入了
//            addAttribute(parentElement, name, jsonElement.getAsString());
//        }
//        return parentElement;
//    }
//
//    /**
//     *
//     * @param element  	父节点
//     * @param name		子节点的名字
//     * @param value		子节点的值
//     */
//    public static void addAttribute(Element element, String name, String value) {
//        //增加子节点，并为子节点赋值
//        Element el = element.addElement(name);
//        el.addText(value);
//
//    }
//
//    public static void main(String[] args) {
////        Document document = DocumentHelper.createDocument();
////        Element root =  document.addElement("stakeapply"); //默认根节点
////        String json = "{\n" +
////                "    \"stakeapply\": {\n" +
////                "        \"voltageLevel\": \"AC00062\", \n" +
////                "        \"stakeList\": {\n" +
////                "            \"stake\": [\n" +
////                "                {\n" +
////                "                    \"stakeAssetNO\": 45754745, \n" +
////                "                    \"otherStakeTypeRemark\": \"xxx\", \n" +
////                "                    \"stationId\": \"547547547547\"\n" +
////                "                }, \n" +
////                "                {\n" +
////                "                    \"stakeAssetNO\": 34325325322, \n" +
////                "                    \"otherStakeTypeRemark\": \"xxx\", \n" +
////                "                    \"stationId\": \"52354645462\"\n" +
////                "                }\n" +
////                "            ]\n" +
////                "        }, \n" +
////                "        \"otherStationTypeRemark\": \"xxx\", \n" +
////                "        \"stationAddr\": \"哈哈\", \n" +
////                "        \"custLists\": {\n" +
////                "            \"custList\": {\n" +
////                "                \"custId\": \"7547547547\", \n" +
////                "                \"custPhone\": 13666666666, \n" +
////                "                \"contactMode\": 1\n" +
////                "            }\n" +
////                "        }, \n" +
////                "        \"principalList\": {\n" +
////                "            \"principal\": [\n" +
////                "                {\n" +
////                "                    \"principalName\": 121212, \n" +
////                "                    \"principalType\": 1\n" +
////                "                }, \n" +
////                "                {\n" +
////                "                    \"principalName\": 12121233, \n" +
////                "                    \"principalType\": 1\n" +
////                "                }\n" +
////                "            ]\n" +
////                "        }\n" +
////                "    }\n" +
////                "}";
////        String s = jsonToXml(json, root);
////        System.out.println(s);
//
//        String xml = "<stakeapply><voltageLevel>AC00062</voltageLevel><stakeList><stake><stakeAssetNO>45754745</stakeAssetNO><otherStakeTypeRemark>xxx</otherStakeTypeRemark><stationId>547547547547</stationId></stake><stake><stakeAssetNO>34325325322</stakeAssetNO><otherStakeTypeRemark>xxx</otherStakeTypeRemark><stationId>52354645462</stationId></stake></stakeList><otherStationTypeRemark>xxx</otherStationTypeRemark><stationAddr>哈哈</stationAddr><custLists><custList><custId>7547547547</custId><custPhone>13666666666</custPhone><contactMode>1</contactMode></custList></custLists><principalList><principal><principalName>121212</principalName><principalType>1</principalType></principal><principal><principalName>12121233</principalName><principalType>1</principalType></principal></principalList></stakeapply>";
//        JSONObject jsonObject = xmlToJson(xml);
//        System.out.println(jsonObject.toString());
//    }
//
//
//}
