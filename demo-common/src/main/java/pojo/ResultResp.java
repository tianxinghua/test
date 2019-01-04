package pojo;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Company: www.shangpin.com
 * @Author: Raymond
 * @Date: 2018/12/13 16:24
 * @Description:
 */
@Getter
@Setter
@ToString
public class ResultResp {
    private Integer code;
    private String desc;
    private Object obj;
    public ResultResp setSuccessResp(String desc){
        ResultResp resultResp = new ResultResp();
        resultResp.setCode(0);
        resultResp.setDesc(desc);
        return resultResp;
    }
    public ResultResp setSuccessResp(){
        ResultResp resultResp = new ResultResp();
        resultResp.setCode(0);
        resultResp.setDesc("ok");
        return resultResp;
    }
    public ResultResp setSuccessResp(Object obj){
        ResultResp resultResp = new ResultResp();
        resultResp.setCode(0);
        resultResp.setDesc("ok");
        resultResp.setObj(obj);
        return resultResp;
    }
    public ResultResp setEerrrResp(String desc){
        ResultResp resultResp = new ResultResp();
        resultResp.setCode(1);
        resultResp.setDesc(desc);
        return resultResp;
    }
}

