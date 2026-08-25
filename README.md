# 智慧社区物业管理平台

基于 **若依（RuoYi-Vue）** 框架扩展的智慧社区物业管理平台，提供物业服务、人员管理、人脸/车牌识别等能力。

## 项目简介

本系统定位智慧社区物业管理场景，在若依管理框架基础上增加社区业务模块，并集成 **腾讯云人脸识别 / 车牌识别** 能力，用于门禁、访客等场景的智能识别。

## 项目结构

```
├── community_springboot/   # 后端（Spring Boot）
│   └── community/
└── community_vue/          # 前端（若依 RuoYi-Vue）
    └── community/
```

## 技术栈

### 前端
- **Vue 2** + **Element UI**（若依 RuoYi-Vue 3.7.0）
- **Vue CLI** + **vuex** + **vue-router**
- **Axios** + **Sass**
- 基于若依前端框架二次开发

### 后端
- **Spring Boot**（Java）
- **MyBatis** + **MySQL** + **Redis**
- **JWT** 身份认证
- **腾讯云** 人脸识别 / 车牌识别 API

## 快速开始

### 环境要求
- JDK 8+、Maven 3.6+
- Node.js 16+
- MySQL 8+、Redis

### 后端启动
1. 复制 `community_springboot/community/src/main/resources/application-example.yml` 为 `application.yml`，填入真实数据库密码、JWT 密钥与腾讯云密钥
2. 初始化数据库（导入社区业务 SQL）
3. 启动 `community` 模块（默认端口 8181）

### 前端启动
```bash
cd community_vue/community
npm install
npm run dev
```

> 开发环境代理到后端 `/dev-api`，后端默认端口 `8181`
