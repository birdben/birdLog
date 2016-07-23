package com.birdben.log.bean;

/**
 * @author birdben
 * @version V1.0
 * @name: UserInfo
 * @description 用户信息
 * @github https://github.com/birdben
 * @date 16/7/23 下午3:19
 */
public class UserInfo {

    /** 名称 */
    private String name;
    /** 年龄 */
    private int age;
    /** 工作 */
    private String job;
    /** 网站 */
    private String website;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}