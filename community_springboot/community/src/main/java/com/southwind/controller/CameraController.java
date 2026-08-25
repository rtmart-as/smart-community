package com.southwind.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.annotation.LogAnnotation;
import com.southwind.entity.Camera;
import com.southwind.entity.Community;
import com.southwind.entity.User;
import com.southwind.service.CameraService;
import com.southwind.service.CommunityService;
import com.southwind.util.Result;
import com.southwind.vo.CameraVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2023-07-27
 */
@RestController
@RequestMapping("/sys/camera")
public class CameraController {

    @Autowired
    private CameraService cameraService;
    @Autowired
    private CommunityService communityService;

    @GetMapping("/list/{communityId}")
    public Result list(@PathVariable("communityId") Integer communityId){
        QueryWrapper<Camera> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("community_id", communityId);
        List<Camera> cameraList = this.cameraService.list(queryWrapper);
        List<CameraVO> list = new ArrayList<>();
        for (Camera camera : cameraList) {
            CameraVO cameraVO = new CameraVO();
            BeanUtils.copyProperties(camera, cameraVO);
            Community community = this.communityService.getById(camera.getCommunityId());
            cameraVO.setCommunityName(community.getCommunityName());
            list.add(cameraVO);
        }
        return Result.ok().put("data", list);
    }

    @LogAnnotation("添加摄像头")
    @PostMapping("/add")
    public Result add(@RequestBody Camera camera, HttpSession session){
        User user = (User) session.getAttribute("user");
        camera.setCreater(user.getUsername());
        boolean save = false;
        try {
            save = this.cameraService.save(camera);
        } catch (Exception e) {
            return Result.ok().put("status", "fail");
        }
        if(!save) return Result.error("添加摄像头失败");
        return Result.ok().put("status", "success");
    }

    @GetMapping("/info/{id}")
    public Result info(@PathVariable("id") Integer id){
        Camera camera = this.cameraService.getById(id);
        if(camera == null) return Result.error("摄像头不存在");
        return Result.ok().put("data", camera);
    }

    @LogAnnotation("编辑摄像头")
    @PutMapping("/edit")
    public Result edit(@RequestBody Camera camera){
        boolean edit = false;
        try {
            edit = this.cameraService.updateById(camera);
        } catch (Exception e) {
            return Result.ok().put("status", "fail");
        }
        if(!edit) return Result.error("编辑摄像头失败");
        return Result.ok().put("status", "success");
    }

    @LogAnnotation("删除摄像头")
    @DeleteMapping("/del")
    public Result del(@RequestBody Integer[] ids){
        boolean removeByIds = this.cameraService.removeByIds(Arrays.asList(ids));
        if(!removeByIds) return Result.error("删除摄像头失败");
        return Result.ok();
    }
}

