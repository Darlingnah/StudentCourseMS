# StudentCourseMS
## 项目框架
- e那个文件夹：启动中心。
- **product-service**：注册服务，**注意resourses里的yml配置文件服务用的名称是student-service**，同理client也是service-client。
- product-client：客户端
## 数据库指令
```sql
    create table student
    (   stuId bigint primary key auto_increment unique not null comment'学号',
        stuName  varchar(20) not null,
        stuGender ENUM('大一','大二','大三','大四'),
        stuAge INT not null,
        stuAddress varchar(100) not null,
        stuPhone varchar(20) not null unique
    );
    INSERT INTO student (stuName, stuGender, stuAge, stuAddress, stuPhone) VALUES
('张三', '大一', 18, '北京市朝阳区', '13800000001'),
('李四', '大二', 19, '上海市浦东新区', '13800000002'),
('王芳', '大三', 20, '广州市天河区', '13800000003'),
('赵明', '大四', 21, '深圳市南山区', '13800000004'),
('孙丽', '大一', 18, '杭州市西湖区', '13800000005'),
('周杰', '大二', 19, '成都市武侯区', '13800000006'),
('吴迪', '大三', 20, '武汉市洪山区', '13800000007'),
('郑爽', '大四', 22, '南京市玄武区', '13800000008');`
