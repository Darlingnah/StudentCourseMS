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
-- ============================================================
-- 1. 学生信息管理微服务扩展：用户认证表
--    用于学生注册、登录时的密码存储
-- ============================================================
CREATE TABLE IF NOT EXISTS user_auth (
    auth_id       BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '认证记录ID',
    stuId         BIGINT NOT NULL UNIQUE COMMENT '学号，与学生表一对一关联',
    password VARCHAR(255) NOT NULL COMMENT '密码哈希值',
    FOREIGN KEY (stuId) REFERENCES student(stuId) ON DELETE CASCADE
) COMMENT='学生登录认证表';

-- ============================================================
-- 2. 课程信息管理微服务：课程表
-- ============================================================
CREATE TABLE IF NOT EXISTS course (
    courseId   BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '课程编号',
    courseName VARCHAR(100) NOT NULL COMMENT '课程名称',
    credit     DECIMAL(3,1) NOT NULL COMMENT '学分',
    description TEXT COMMENT '课程描述',
    department VARCHAR(50) COMMENT '开课院系'
) COMMENT='课程信息表';

-- ============================================================
-- 3. 教师管理微服务：教师表
-- ============================================================
CREATE TABLE IF NOT EXISTS teacher (
    teacherId   BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '教师编号',
    teacherName VARCHAR(50) NOT NULL COMMENT '教师姓名',
    gender      ENUM('男','女') COMMENT '性别',
    title       VARCHAR(50) COMMENT '职称',
    department  VARCHAR(50) COMMENT '所属院系',
    phone       VARCHAR(20) COMMENT '联系电话',
    email       VARCHAR(100) COMMENT '电子邮箱'
) COMMENT='教师信息表';

-- ============================================================
-- 4. 选课管理微服务核心表：排课表(课程时间表) 与 选课记录表
--    (排课表同时承载“教师课程分配”的功能)
-- ============================================================
CREATE TABLE IF NOT EXISTS schedule (
    scheduleId BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '排课编号',
    courseId   BIGINT NOT NULL COMMENT '课程编号',
    teacherId  BIGINT NOT NULL COMMENT '授课教师编号',
    classroom  VARCHAR(50) COMMENT '上课教室',
    dayOfWeek  INT COMMENT '星期几(1=周一..7=周日)',
    startTime  TIME COMMENT '开始时间',
    endTime    TIME COMMENT '结束时间',
    semester   VARCHAR(20) COMMENT '学期，如2025-2026春',
    year       INT COMMENT '学年',
    FOREIGN KEY (courseId)  REFERENCES course(courseId),
    FOREIGN KEY (teacherId) REFERENCES teacher(teacherId)
) COMMENT='课程排课/时间表';

CREATE TABLE IF NOT EXISTS enrollment (
    enrollmentId BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '选课记录ID',
    stuId        BIGINT NOT NULL COMMENT '学号',
    courseId     BIGINT NOT NULL COMMENT '课程编号',
    semester     VARCHAR(20) COMMENT '学期',
    year         INT COMMENT '学年',
    enrollDate   DATE COMMENT '选课日期',
    status       ENUM('已选','退选') DEFAULT '已选' COMMENT '选课状态',
    FOREIGN KEY (stuId)    REFERENCES student(stuId),
    FOREIGN KEY (courseId) REFERENCES course(courseId)
) COMMENT='学生选课记录表';

-- ============================================================
-- 5. 成绩管理微服务：成绩表
-- ============================================================
CREATE TABLE IF NOT EXISTS grade (
    gradeId    BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '成绩记录ID',
    stuId      BIGINT NOT NULL COMMENT '学号',
    courseId   BIGINT NOT NULL COMMENT '课程编号',
    score      DECIMAL(5,2) COMMENT '分数',
    gradePoint DECIMAL(3,2) COMMENT '绩点',
    examType   ENUM('期末考试','补考','重修') DEFAULT '期末考试' COMMENT '考试类型',
    semester   VARCHAR(20) COMMENT '学期',
    year       INT COMMENT '学年',
    teacherId  BIGINT COMMENT '录入成绩的教师编号',
    recordDate DATE COMMENT '录入日期',
    FOREIGN KEY (stuId)    REFERENCES student(stuId),
    FOREIGN KEY (courseId) REFERENCES course(courseId),
    FOREIGN KEY (teacherId) REFERENCES teacher(teacherId)
) COMMENT='学生成绩表';

-- ============================================================
-- 6. 考勤管理微服务：考勤记录表 与 请假记录表
-- ============================================================
CREATE TABLE IF NOT EXISTS attendance (
    attendanceId   BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '考勤记录ID',
    stuId          BIGINT NOT NULL COMMENT '学号',
    scheduleId     BIGINT NOT NULL COMMENT '对应排课编号',
    attendanceDate DATE NOT NULL COMMENT '考勤日期',
    status         ENUM('出勤','缺勤','迟到','早退','请假') DEFAULT '缺勤' COMMENT '考勤状态',
    FOREIGN KEY (stuId)      REFERENCES student(stuId),
    FOREIGN KEY (scheduleId) REFERENCES schedule(scheduleId)
) COMMENT='考勤记录表';

CREATE TABLE IF NOT EXISTS leave_request (
    leaveId    BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '请假记录ID',
    stuId      BIGINT NOT NULL COMMENT '学号',
    scheduleId BIGINT COMMENT '针对某次课程的排课编号（可为空）',
    courseId   BIGINT COMMENT '请假关联的课程（可为空）',
    startDate  DATE NOT NULL COMMENT '请假开始日期',
    endDate    DATE NOT NULL COMMENT '请假结束日期',
    reason     VARCHAR(255) COMMENT '请假原因',
    status     ENUM('待审批','批准','拒绝') DEFAULT '待审批' COMMENT '审批状态',
    applyDate  DATE COMMENT '申请日期',
    approverId BIGINT COMMENT '审批教师编号',
    FOREIGN KEY (stuId)      REFERENCES student(stuId),
    FOREIGN KEY (scheduleId) REFERENCES schedule(scheduleId),
    FOREIGN KEY (courseId)   REFERENCES course(courseId)
) COMMENT='学生请假申请表';

-- ============================================================
-- 统计分析与报表生成微服务无需额外建表，基于上述表即可。
-- ============================================================

-- ============================================================
-- 插入示例数据（假设学生表中已存在 stuId = 1, 2, 3 三条记录）
-- ============================================================

-- ----- 插入登录认证数据 -----
INSERT INTO user_auth (stuId, password) VALUES
(1, '123456'),  -- 假设为密码hash
(2, '123456'),
(3, '123456');

-- ----- 插入课程数据 -----
INSERT INTO course (courseName, credit, description, department) VALUES
('高等数学', 5.0, '理工科基础数学课程', '数学学院'),
('大学英语', 4.0, '大学英语综合教程', '外语学院'),
('数据结构', 4.0, '计算机专业核心课', '计算机学院'),
('数据库原理', 3.5, '关系数据库与SQL', '计算机学院');

-- ----- 插入教师数据 -----
INSERT INTO teacher (teacherName, gender, title, department, phone, email) VALUES
('张伟', '男', '教授', '数学学院', '13800001111', 'zhangwei@univ.edu'),
('李娜', '女', '副教授', '外语学院', '13800002222', 'lina@univ.edu'),
('王强', '男', '讲师', '计算机学院', '13800003333', 'wangqiang@univ.edu');

-- ----- 插入排课数据（分配教师与时间地点）-----
INSERT INTO schedule (courseId, teacherId, classroom, dayOfWeek, startTime, endTime, semester, year) VALUES
(1, 1, '教一101', 1, '08:00', '09:40', '2025-2026春', 2026),
(1, 1, '教一101', 3, '10:00', '11:40', '2025-2026春', 2026),
(2, 2, '教二201', 2, '14:00', '15:40', '2025-2026春', 2026),
(3, 3, '计科楼301', 4, '08:00', '09:40', '2025-2026春', 2026),
(4, 3, '计科楼302', 5, '10:00', '11:40', '2025-2026春', 2026);

-- ----- 插入选课数据 -----
INSERT INTO enrollment (stuId, courseId, semester, year, enrollDate, status) VALUES
(1, 1, '2025-2026春', 2026, '2026-02-20', '已选'),
(1, 3, '2025-2026春', 2026, '2026-02-20', '已选'),
(2, 1, '2025-2026春', 2026, '2026-02-21', '已选'),
(2, 2, '2025-2026春', 2026, '2026-02-21', '已选'),
(3, 3, '2025-2026春', 2026, '2026-02-22', '已选'),
(3, 4, '2025-2026春', 2026, '2026-02-22', '已选');

-- ----- 插入成绩数据 -----
INSERT INTO grade (stuId, courseId, score, gradePoint, examType, semester, year, teacherId, recordDate) VALUES
(1, 1, 85.5, 3.5, '期末考试', '2025-2026春', 2026, 1, '2026-06-20'),
(2, 1, 92.0, 4.0, '期末考试', '2025-2026春', 2026, 1, '2026-06-20'),
(2, 2, 78.0, 3.0, '期末考试', '2025-2026春', 2026, 2, '2026-06-22');

-- ----- 插入考勤数据（假设scheduleId对应上面的排课）-----
INSERT INTO attendance (stuId, scheduleId, attendanceDate, status) VALUES
(1, 1, '2026-03-03', '出勤'),
(1, 1, '2026-03-10', '迟到'),
(2, 3, '2026-03-04', '出勤'),
(3, 4, '2026-03-05', '缺勤');

-- ----- 插入请假数据 -----
INSERT INTO leave_request (stuId, scheduleId, courseId, startDate, endDate, reason, status, applyDate, approverId) VALUES
(1, 1, 1, '2026-03-17', '2026-03-17', '身体不适', '批准', '2026-03-16', 1),
(2, 3, 2, '2026-03-20', '2026-03-20', '家中有事', '待审批', '2026-03-18', NULL);


