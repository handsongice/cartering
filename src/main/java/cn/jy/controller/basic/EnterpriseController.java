package cn.jy.controller.basic;

import cn.jy.common.LayUiResultData;
import cn.jy.constent.Constent;
import cn.jy.controller.BaseController;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Enterprise;
import cn.jy.pojo.LayUiImage;
import cn.jy.pojo.LayUiPageParams;
import cn.jy.service.EnterpriseService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/11/2 13:51
 */
@Controller
public class EnterpriseController extends BaseController {
    @Autowired
    EnterpriseService enterpriseService;

    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/myEnterprise")
    public ModelAndView enterprise(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role/enterprise");
        Enterprise enterprise = getLoginEnterprise();
        Enterprise _enterprise = enterpriseService.getEnterpriseById(enterprise.getId());
        mv.addObject("enterprise", _enterprise);
        return mv;
    }

    @RequestMapping(value = "/noc/uploadFile")
    @ResponseBody
    public LayUiResultData uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        String key = null;
        StringBuffer sb = new StringBuffer();
        LayUiImage layUiImage = new LayUiImage();
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(Constent.QN_ACCESS_KEY, Constent.QN_SECRET_KEY);
            String upToken = auth.uploadToken(Constent.QN_BUCKET);
            try {
                Response response = uploadManager.put(uploadBytes, key, upToken);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                layUiImage.setName(putRet.key);
                sb.append(Constent.QN_DOMAIN).append(putRet.key);
            } catch (QiniuException ex) {
                Response r = ex.response;
                return LayUiResultData.resultError(r.toString(),null);
            }
        } catch (UnsupportedEncodingException ex) {
            return LayUiResultData.resultError(ex.toString(),null);
        }
        layUiImage.setSrc(sb.toString());
        return LayUiResultData.result("",0,layUiImage);
    }
    /**
     * 列表
     * @return
     */
    @RequestMapping(value = "/main/enterprise")
    public String enterprise() {
        return "basic/enterprise";
    }

    /**
     * 消息列表
     * @return
     */
    @RequestMapping(value = "/message")
    public String message() {
        return "basic/message";
    }

    /**
     * 编辑信息
     * @param params
     * @return
     */
    @RequestMapping(value = "/main/editEnterprise")
    public ModelAndView editEnterprise(@RequestParam Map<String, Object> params) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("basic/editEnterprise");
        Long _id = Long.parseLong(params.get("id").toString());
        Enterprise enterprise = enterpriseService.getEnterpriseById(_id);
        mv.addObject("enterprise", enterprise);
        return mv;
    }

    /**
     * 新建
     * @return
     */
    @RequestMapping(value = "/main/addEnterprise")
    public String addEnterprise() {
        return "basic/addEnterprise";
    }

    /**
     * 插入
     * @param request
     * @param enterprise
     * @return
     */
    @RequestMapping(value = "/noc/insertEnterprise")
    @ResponseBody
    public ResultMap insertEnterprise(HttpServletRequest request, Enterprise enterprise) {
        try {
            return enterpriseService.addEnterprise(enterprise);
        }catch (Exception e) {
            e.printStackTrace();
            return ResultMap.fail(e.getMessage());
        }
    }

    /**
     * 公司列表
     * @param params
     * @param request
     * @return
     */
    @RequestMapping("/noc/pageEnterpriseList")
    @ResponseBody
    public Object pageEnterpriseList(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        int pageNum = params.get("page") == null ? 1 : Integer.parseInt(params.get("page").toString());
        int pageSize = params.get("limit") == null ? 10 : Integer.parseInt(params.get("limit").toString());
        PageHelper.startPage(pageNum, pageSize, true);
        try {
            List<Enterprise> actions = enterpriseService.getEnterpriseList(params);
            PageInfo<Enterprise> pageInfo = new PageInfo<>(actions);

            LayUiPageParams<Enterprise> pageParams = LayUiPageParams.defaultResult(pageInfo.getTotal(), actions);
            return pageParams;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更新
     * @param request
     * @param enterprise
     * @return
     */
    @RequestMapping(value = "/noc/updateEnterprise")
    @ResponseBody
    public ResultMap updateEnterprise(HttpServletRequest request, Enterprise enterprise) {
        ResultMap ret = enterpriseService.updateEnterprise(enterprise);
        Enterprise _enterprise = enterpriseService.getEnterpriseById(enterprise.getId());
        HttpSession session = request.getSession();
        session.setAttribute(Constent.SESSION_ENTERPRISE,_enterprise);
        return ret;
    }
    /**
     * 逻辑删除
     * @param params
     * @param request
     * @return
     */
    @RequestMapping(value = "/noc/delEnterprise")
    @ResponseBody
    public ResultMap delEnterprise(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        return enterpriseService.delEnterprise(Long.parseLong(params.get("id").toString()));
    }
}
