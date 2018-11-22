# 2018-08-17Counter
使用jdbcTemplate or mybatis完成一个计数器功能
通过spring mvc提供一个add接口
数据库表
api_name, count
接口名称(固定为'add'), 0(次数)
每访问一次计数加1
当第一次访问的时候需要向数据库表中插入一条新的记录