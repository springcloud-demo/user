package com.imooc.user.VO;

public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return  resultVO;
    }

    public static ResultVO error(Integer errorCode,String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(errorCode);
        resultVO.setMsg(msg);
        return  resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return  resultVO;
    }
}
