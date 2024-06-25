package com.example.demo.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "result") // 指定根元素名称
public class ResultData {

    private String code;
    private String message;
    private Data data;

    // JAXB 需要无参数的构造函数
    public ResultData() {
    }

    // XML元素与Java属性之间的映射
    @XmlElement(name = "code")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @XmlElement(name = "message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @XmlElement(name = "data")
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    // 嵌套类Data对应于<data>元素
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Data {

        private List<Bill> bills;

        // getters and setters
        public List<Bill> getBills() {
            return bills;
        }

        public void setBills(List<Bill> bills) {
            this.bills = bills;
        }

        // 嵌套类Bill对应于<bill>元素
        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Bill {

            private String einvoiceid;
            private String einvoicecode;
            private String einvoicenumber;
            private String eticketnum;
            private String randomnumber;
            private String einvoicefilename;
            private BigDecimal amt; // 注意：这里假设amt是BigDecimal类型，而不是String
            private int isRed;
            private int state;
//            @XmlJavaTypeAdapter(XmlDateFormatAdapter.class) // 假设你需要自定义日期格式
            private Date issuedate;

            // getters and setters
//            @XmlElement(name = "einvoiceid")
//            public String getEinvoiceid() {
//                return einvoiceid;
//            }
//            public void setEinvoiceid(String einvoiceid) {
//                this.einvoiceid = einvoiceid;
//            }
//            @XmlElement(name = "einvoicecode")
//            public String getEinvoicecode() {
//                return einvoicecode;
//            }
//            public void setEinvoicecode(String einvoicecode) {
//                this.einvoicecode = einvoicecode;
//            }
//            @XmlElement(name = "einvoicenumber")
//            public String getEinvoicenumber() {
//                return einvoicenumber;
//            }
//            public void setEinvoicenumber(String einvoicenumber) {
//                this.einvoicenumber = einvoicenumber;
//            }
//            @XmlElement(name = "eticketnum")
//            public String getEticketnum() {
//                return eticketnum;
//            }
//            public void setEticketnum(String eticketnum) {
//                this.eticketnum = eticketnum;
//            }
//            @XmlElement(name = "randomnumber")
//            public String getRandomnumber() {
//                return randomnumber;
//            }
//            public void setRandomnumber(String randomnumber) {
//                this.randomnumber = randomnumber;
//            }
//            @XmlElement(name = "einvoicefilename")
//            public String getEinvoicefilename() {
//                return einvoicefilename;
//            }
//            public void setEinvoicefilename(String einvoicefilename) {
//                this.einvoicefilename = einvoicefilename;
//            }
//            @XmlElement(name = "amt")
//            public BigDecimal getAmt () {
//                return amt;
//            }
//            public void setAmt(BigDecimal amt) {
//                this.amt = amt;
//            }
//            @XmlElement(name = "isRed")
//            public int getIsRed() {
//                return isRed;
//            }
//            public void setIsRed(int isRed) {
//                this.isRed = isRed;
//            }
//            @XmlElement(name = "state")
//            public int getState() {
//                return state;
//            }
//            public void setState(int state) {
//                this.state = state;
//            }
//            @XmlElement(name = "issuedate")
//            public Date getIssuedate() {
//                return issuedate;
//            }
//            public void setIssuedate(Date issuedate) {
//                this.issuedate = issuedate;
//            }
            // ...

            // 注意：这里可能需要一个XmlDateFormatAdapter类来处理日期格式
            // XmlDateFormatAdapter应该实现javax.xml.bind.annotation.adapters.XmlAdapter<String, Date>接口

        }
    }

    // toString 方法，用于打印结果
    @Override
    public String toString() {
        return "Result{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
