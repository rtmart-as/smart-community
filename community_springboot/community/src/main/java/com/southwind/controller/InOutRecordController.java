package com.southwind.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.annotation.LogAnnotation;
import com.southwind.configuration.ApiConfiguration;
import com.southwind.entity.Community;
import com.southwind.entity.InOutRecord;
import com.southwind.entity.Person;
import com.southwind.form.InOutFaceForm;
import com.southwind.form.InOutQueryForm;
import com.southwind.mapper.InOutRecordMapper;
import com.southwind.service.CommunityService;
import com.southwind.service.InOutRecordService;
import com.southwind.service.PersonService;
import com.southwind.util.Base64Util;
import com.southwind.util.FaceApi;
import com.southwind.util.Result;
import com.southwind.util.RootResp;
import com.southwind.vo.PageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-07-26
 */
@RestController
@RequestMapping("/sys/inOut")
public class InOutRecordController {

    @Autowired
    private InOutRecordService inOutRecordService;
    @Autowired
    private CommunityService communityService;
    @Autowired
    private ApiConfiguration apiConfiguration;
    @Autowired
    private PersonService personService;
    @Value("${upload.face}")
    private String face;
    @Value("${upload.urlPrefix}")
    private String urlPrefix;
    @Autowired
    private InOutRecordMapper inOutRecordMapper;

    @GetMapping("/chart")
    public Result chart(){
        Map map = this.inOutRecordService.chart();
        return Result.ok().put("data", map);
    }

    @GetMapping("/communityList")
    public Result communityList(){
        List<Community> list = this.communityService.list();
        if(list == null) return Result.error("没有小区数据");
        return Result.ok().put("data", list);
    }

    @LogAnnotation("人脸识别")
    @PostMapping("/add")
    public Result add(@RequestBody InOutFaceForm inOutFaceForm){
        //调用腾讯AI接口
//        FaceApi faceApi = new FaceApi();
//        RootResp resp = faceApi.searchPersonsReturnsByGroup(apiConfiguration, inOutFaceForm.getFileBase64());
//        String msg = "";
//        JSONObject personInfo = null;
//        if(resp.getRet() == 0) {
//            JSONObject object = JSONObject.parseObject(resp.getData().toString());
//            JSONArray resultsReturnsByGroup = object.getJSONArray("ResultsReturnsByGroup");
//            JSONObject returnsByGroupJSONObject = resultsReturnsByGroup.getJSONObject(0);
//            JSONArray groupCandidates = returnsByGroupJSONObject.getJSONArray("GroupCandidates");
//            JSONObject groupCandidatesJSONObject = groupCandidates.getJSONObject(0);
//            JSONArray candidates = groupCandidatesJSONObject.getJSONArray("Candidates");
//            //返回多个人员，匹配数据库人员信息
//            String personId ="";
//            String faceId = "";
//            String personName = "";
//            String faceUrl = "";
//            long pid = 0;
//            Person p = null, p1 = null;
//            for(int i = 0;i < candidates.size();i++) {
//                candidates.getJSONObject(i);
//                personId = personInfo.getString("PersonId");
//                faceId = personInfo.getString("FaceId");
//                personName = personInfo.getString("PersonName");
//                personId = personId.substring(5);
//                pid = Integer.parseInt(personId);
//                p = personService.getById(pid);
//                if(p == null)
//                    continue;
//                else
//                    p1 = p;
//                faceUrl = p.getFaceUrl();
//                if(faceUrl == null || faceUrl.equals("")){
//                    continue;
//                }
//                faceUrl = faceUrl.substring(faceUrl.lastIndexOf("/")+1,faceUrl.lastIndexOf("."));
//                if(faceId.equals(faceUrl)) {
//                    break;
//                }
//            }
//            if(p==null) {
//                return Result.ok().put("data","人员信息不存在");
//            }
//            if(inOutFaceForm.getCommunityId() != p.getCommunityId()) {
//                return Result.ok().put("data","对不起，你不是本小区居民，请与系统管理员联系。");
//            }
//            InOutRecord inoutrecord = new InOutRecord();
//            inoutrecord.setCommunityId(p.getCommunityId());
//            inoutrecord.setPersonId(p.getPersonId());
//            try {
//                //保存图片
//                String newFileName = UUID.randomUUID()+"." + inOutFaceForm.getExtName();
//                String fileName = face + newFileName;
//                Base64Util.decoderBase64File(inOutFaceForm.getFileBase64(),fileName);
//                String basePath = urlPrefix + "villegePic/face/" + newFileName;
//                //查找系统中是否有该人员的出入场信息
//                InOutRecord inoutrecord1 = this.inOutRecordMapper.getInOutRecord(inoutrecord);
//                //进入小区
//                if(inoutrecord1 == null) {
//                    inoutrecord.setInPic(basePath);
//                    this.inOutRecordMapper.insert(inoutrecord);
//                    return Result.ok().put("status", "success").put("data", "【"+p.getUserName() + "】进入小区");
//                    //离开小区
//                } else {
//                    inoutrecord1.setOutPic(basePath);
//                    this.inOutRecordMapper.updateById(inoutrecord1);
//                    return Result.ok().put("status", "success").put("data", "【"+p.getUserName() + "】离开小区");
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }else{
//            msg = "人脸识别失败,错误码=" + resp.getRet() + "," + resp.getMsg();
//        }
//        return Result.ok().put("data",msg);

        //================== 模拟人脸识别(已注释保留) ==================
        //如需切换回模拟识别：解开下面的注释，并注释掉下面真实的腾讯AI调用
//        String faceBase = inOutFaceForm.getFileBase64().substring(0, 60);
//        //如果不是头像
//        if(faceBase.equals("iVBORw0KGgoAAAANSUhEUgAAAoAAAAHgCAYAAAA10dzkAAAAAXNSR0IArs4c")) {
//            return Result.ok().put("status", "fail").put("data", "人脸识别失败");
//        }
//        QueryWrapper<Person> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("face_base", faceBase);
//        Person person = this.personService.getOne(queryWrapper);
//        if(person == null) {
//            return Result.ok().put("status", "fail").put("data", "人员信息不存在");
//        }
//        if(inOutFaceForm.getCommunityId() != person.getCommunityId()) {
//            return Result.ok().put("status", "fail").put("data", "对不起，你不是本小区居民，请与系统管理员联系");
//        }
//        InOutRecord inOutRecord = new InOutRecord();
//        inOutRecord.setCommunityId(person.getCommunityId());
//        inOutRecord.setPersonId(person.getPersonId());
//        try {
//            //保存图片
//            String newFileName = UUID.randomUUID()+"." + inOutFaceForm.getExtName();
//            String fileName = face + newFileName;
//            Base64Util.decoderBase64File(inOutFaceForm.getFileBase64(),fileName);
//            String basePath = urlPrefix + "community/upload/face/" + newFileName;
//            //查找系统中是否有该人员的出入场信息
//            InOutRecord inOutRecord1 = this.inOutRecordMapper.getInOutRecord(inOutRecord);
//            //进入小区
//            if(inOutRecord1 == null) {
//                inOutRecord.setInPic(basePath);
//                this.inOutRecordMapper.insert(inOutRecord);
//                return Result.ok().put("status", "success").put("data", "【"+person.getUserName() + "】进入小区");
//                //离开小区
//            } else {
//                inOutRecord1.setOutPic(basePath);
//                this.inOutRecordMapper.updateById(inOutRecord1);
//                return Result.ok().put("status", "success").put("data", "【"+person.getUserName() + "】离开小区");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;

        //调用腾讯AI人脸识别
        FaceApi faceApi = new FaceApi();
        RootResp resp = faceApi.searchPersonsReturnsByGroup(apiConfiguration, inOutFaceForm.getFileBase64());
        if(resp.getRet() != 0) {
            return Result.ok().put("status", "fail").put("data", "人脸识别失败,错误码=" + resp.getRet() + "," + resp.getMsg());
        }
        try {
            JSONObject object = JSONObject.parseObject(resp.getData().toString());
            JSONArray resultsReturnsByGroup = object.getJSONArray("ResultsReturnsByGroup");
            if(resultsReturnsByGroup == null || resultsReturnsByGroup.size() == 0) {
                return Result.ok().put("status", "fail").put("data", "未识别到人脸");
            }
            JSONArray groupCandidates = resultsReturnsByGroup.getJSONObject(0).getJSONArray("GroupCandidates");
            if(groupCandidates == null || groupCandidates.size() == 0) {
                return Result.ok().put("status", "fail").put("data", "人脸库中未匹配到该人员");
            }
            JSONArray candidates = groupCandidates.getJSONObject(0).getJSONArray("Candidates");
            if(candidates == null || candidates.size() == 0) {
                return Result.ok().put("status", "fail").put("data", "人脸库中未匹配到该人员");
            }
            //取相似度最高的候选
            JSONObject candidate = candidates.getJSONObject(0);
            double score = candidate.getDoubleValue("Score");
            if(score < apiConfiguration.getPassPercent()) {
                return Result.ok().put("status", "fail").put("data", "人脸识别失败，相似度不足(" + (int)score + "%)");
            }
            //去掉前缀得到本地人员id
            String tencentPersonId = candidate.getString("PersonId");
            String pre = apiConfiguration.getPersonIdPre();
            if(pre != null && !pre.equals("") && tencentPersonId.startsWith(pre)) {
                tencentPersonId = tencentPersonId.substring(pre.length());
            }
            Person person = this.personService.getById(Integer.parseInt(tencentPersonId));
            if(person == null) {
                return Result.ok().put("status", "fail").put("data", "人员信息不存在");
            }
            if(!inOutFaceForm.getCommunityId().equals(person.getCommunityId())) {
                return Result.ok().put("status", "fail").put("data", "对不起，你不是本小区居民，请与系统管理员联系");
            }
            InOutRecord inOutRecord = new InOutRecord();
            inOutRecord.setCommunityId(person.getCommunityId());
            inOutRecord.setPersonId(person.getPersonId());
            try {
                //保存图片
                String newFileName = UUID.randomUUID()+"." + inOutFaceForm.getExtName();
                String fileName = face + newFileName;
                Base64Util.decoderBase64File(inOutFaceForm.getFileBase64(),fileName);
                String basePath = urlPrefix + "community/upload/face/" + newFileName;
                //查找系统中是否有该人员的出入场信息
                InOutRecord inOutRecord1 = this.inOutRecordMapper.getInOutRecord(inOutRecord);
                //进入小区
                if(inOutRecord1 == null) {
                    inOutRecord.setInPic(basePath);
                    this.inOutRecordMapper.insert(inOutRecord);
                    return Result.ok().put("status", "success").put("data", "【"+person.getUserName() + "】进入小区");
                    //离开小区
                } else {
                    inOutRecord1.setOutPic(basePath);
                    this.inOutRecordMapper.updateById(inOutRecord1);
                    return Result.ok().put("status", "success").put("data", "【"+person.getUserName() + "】离开小区");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok().put("status", "fail").put("data", "识别过程出错");
    }

    @GetMapping("/list")
    public Result list(InOutQueryForm inOutQueryForm){
        PageVO pageVO = this.inOutRecordService.inOutRecordList(inOutQueryForm);
        Map map = new HashMap();
        map.put("pageList",pageVO);
        return Result.ok().put("data", map);
    }
}

