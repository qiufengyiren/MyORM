package com.dzq;


import com.dzq.pojo.News_Detail;

import java.sql.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) {
        /**
         * 定义连接数据录得四要素
         */
        String dirverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/new";
        String username = "root";
        String password = "123456";
        /**
         * 创建JDBC API
         */
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        /**
         * 通过反射对象创建实例
         */
        Object object = null;
        try {
            object = Class.forName("com.dzq.pojo.News_Detail").newInstance();
            //加载驱动
            Class.forName(dirverName);
            connection = DriverManager.getConnection(url, username, password);
            //书写sql语句 查询id=2的信息
            String sql = "SELECT `newscreateId`,`title`,`titleName` FROM `news`";
            //给参数赋值
            preparedStatement = connection.prepareStatement(sql);
   /*         preparedStatement.setInt(1, 2);*/
            //执行SQL
            resultSet = preparedStatement.executeQuery();
            //遍历结果
            while(resultSet.next()) {
            //获取元数据
                ResultSetMetaData data = resultSet.getMetaData();
                System.out.println("结果集中包含多少条数据？" + data.getColumnCount());
                int count=data.getColumnCount();//获取结果集中的列数
                for (int i = 1; i <=count ; i++) {
                    String columnName=data.getColumnName(i);//获取指定的列名
                    //获取属性的名字  id字段 对应的是setId（）
                    String methodName=changeName(columnName);
                    //获取字段在数据库中的类型
                    String cloumeType=data.getColumnTypeName(i);
                    if(cloumeType.equalsIgnoreCase("int")){
                        object.getClass().getMethod(methodName,int.class).invoke(object,resultSet.getInt(columnName));
                    }else if(cloumeType.equalsIgnoreCase("varchar")){
                        object.getClass().getMethod(methodName,String.class).invoke(object,resultSet.getString(columnName));
                    }
                }
                System.out.println((News_Detail)object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {//释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private  static String changeName(String columnName){
        return "set"+columnName.substring(0,1).toUpperCase()+columnName.substring(1);
    }
}

