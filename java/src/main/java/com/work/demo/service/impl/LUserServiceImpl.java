package com.work.demo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.work.demo.common.exception.BizException;
import com.work.demo.entity.LUser;
import com.work.demo.entity.request.ChangeInfoRequest;
import com.work.demo.entity.request.CommonIdRequest;
import com.work.demo.entity.vo.InfoVo;
import com.work.demo.mapper.LUserMapper;
import com.work.demo.service.ILUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author liuxiaozhen
 * @since 2022-04-20
 */
@Service
public class LUserServiceImpl extends ServiceImpl<LUserMapper, LUser> implements ILUserService {

    @Resource
    LUserMapper lUserMapper;

    /**
     * 查看个人信息
     * @param commonIdRequest
     * @return
     */
    @Override
    public InfoVo viewInfo(CommonIdRequest commonIdRequest){
        LUser lUser=lUserMapper.selectOne(
                Wrappers.lambdaQuery(LUser.class)
                .eq(LUser::getLoginId,commonIdRequest.getId())
                .eq(LUser::getIsDel,"0")
        );
        if(lUser==null){
            throw new BizException("在数据库中未找到该用户的个人信息！");
        }
        InfoVo infoVo = new InfoVo();
        infoVo.setUserName(lUser.getUserName());
        infoVo.setAddress(lUser.getAddress());
        infoVo.setEmail(lUser.getEmail());
        String gender=lUser.getGender();
        if("0".equals(gender)){
            infoVo.setGender("未知");
        } else if("1".equals(gender)){
            infoVo.setGender("男");
        } else {
            infoVo.setGender("女");
        }
        infoVo.setLoginId(lUser.getLoginId());
        infoVo.setOccupation(lUser.getOccupation());
        infoVo.setPhone(lUser.getPhone());
        Date birthday=lUser.getBirthday();
        String[] strNow1 = new SimpleDateFormat("yyyy-MM-dd").format(birthday).toString().split("-");
        String year=strNow1[0];
        int month=Integer.parseInt(strNow1[1]);
        int day=Integer.parseInt(strNow1[2]);
        infoVo.setYear(year);
        infoVo.setMonth(""+month);
        infoVo.setDay(""+day);
        return infoVo;
    }

    /**
     * 修改个人信息
     * @param changeInfoRequest
     */
    @Override
    public void changeInfo(ChangeInfoRequest changeInfoRequest) throws ParseException {
        LUser lUser=lUserMapper.selectOne(
                Wrappers.lambdaQuery(LUser.class)
                .eq(LUser::getLoginId,changeInfoRequest.getLoginId())
                .eq(LUser::getIsDel,"0")
        );
        //插入用户信息
        if(lUser==null){
            LUser lUser1=new LUser();
            lUser1.setUserName(changeInfoRequest.getUserName());
            lUser1.setOccupation(changeInfoRequest.getOccupation());
            lUser1.setUserId(UUID.randomUUID().toString());
            lUser1.setLoginId(changeInfoRequest.getLoginId());
            String gender=changeInfoRequest.getGender();
            if("男".equals(gender)){
                lUser1.setGender("1");
            } else if("女".equals(gender)){
                lUser1.setGender("2");
            }
            lUser1.setAddress(changeInfoRequest.getAddress());
            lUser1.setEmail(changeInfoRequest.getEmail());
            lUser1.setPhone(changeInfoRequest.getPhone());
            SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
            String birthday=changeInfoRequest.getYear()+"-"+changeInfoRequest.getMonth()+"-"
                    +changeInfoRequest.getDay();
            Date date=ft.parse(birthday);
            lUser1.setBirthday(date);
            lUserMapper.insert(lUser1);
        } else{
            lUser.setUserName(changeInfoRequest.getUserName());
            lUser.setOccupation(changeInfoRequest.getOccupation());
            lUser.setLoginId(changeInfoRequest.getLoginId());
            String gender1=changeInfoRequest.getGender();
            if("男".equals(gender1)){
                lUser.setGender("1");
            } else if("女".equals(gender1)){
                lUser.setGender("2");
            }
            lUser.setAddress(changeInfoRequest.getAddress());
            lUser.setEmail(changeInfoRequest.getEmail());
            lUser.setPhone(changeInfoRequest.getPhone());
            SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd");
            String birthday=changeInfoRequest.getYear()+"-"+changeInfoRequest.getMonth()+"-"
                    +changeInfoRequest.getDay();
            Date date=ft.parse(birthday);
            lUser.setBirthday(date);
            lUserMapper.updateById(lUser);
        }
    }

}
